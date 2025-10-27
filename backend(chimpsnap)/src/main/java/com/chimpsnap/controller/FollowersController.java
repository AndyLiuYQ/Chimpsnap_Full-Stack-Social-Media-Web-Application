package com.chimpsnap.controller;


import com.chimpsnap.pojo.Result;
import com.chimpsnap.pojo.User;
import com.chimpsnap.service.FollowersService;
import com.chimpsnap.service.UserService;
import com.chimpsnap.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/followers")
@CrossOrigin
public class FollowersController
{
    @Autowired
    private FollowersService followersService;
    @Autowired
    private UserService userService;

    @PostMapping("/follow")
    public Result followOthers(@RequestBody User user, HttpServletRequest request)
    {
        String Token = request.getHeader("Authorization");
        String token = Token.substring(7);
        Map<String, Object> claims = JwtUtil.parseToken(token);
        Integer Id = (Integer) claims.get("id");
        Integer followedUserId = user.getId();
        if(followersService.ifFollowed(followedUserId,Id))
        {
           followersService.deleteFollowers(followedUserId,Id);
           userService.setFollowersSub(followedUserId);
        }
        else
        {
            followersService.setFollowUser(followedUserId,Id);
            userService.setFollowersAdd(followedUserId); // 被关注的人粉丝+1
        }
        return Result.success();
    }

    @GetMapping("/iffollow")
    public Result ifFollow(HttpServletRequest request)
    {
        String Token = request.getHeader("Authorization");
        String token = Token.substring(7);
        Map<String, Object> claims = JwtUtil.parseToken(token);
        Integer Id = (Integer) claims.get("id");
        String getID = request.getHeader("othersId");
        Integer othersId = Integer.valueOf(getID);
        if(followersService.ifFollowed(othersId,Id))
        {
            return Result.success();
        }
        else
        {
            return Result.error("未关注");
        }
    }

    @GetMapping("/view")
    public Result<List<User>> getMySubscribe(HttpServletRequest request)
    {
        String Token = request.getHeader("Authorization");
        String token = Token.substring(7);
        Map<String, Object> claims = JwtUtil.parseToken(token);
        Integer Id = (Integer) claims.get("id");
        List<User> u = userService.getUserByFollowingId(Id);
        return Result.success(u);
    }
}
