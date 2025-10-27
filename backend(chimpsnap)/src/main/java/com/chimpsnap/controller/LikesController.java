package com.chimpsnap.controller;


import com.chimpsnap.pojo.Posts;
import com.chimpsnap.pojo.Result;
import com.chimpsnap.pojo.User;
import com.chimpsnap.service.LikesService;
import com.chimpsnap.service.MessagesService;
import com.chimpsnap.service.PostsService;
import com.chimpsnap.service.UserService;
import com.chimpsnap.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@RestController
@RequestMapping("/likes")
@CrossOrigin
public class LikesController
{
    @Autowired
    private LikesService likesService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private PostsService postsService;
    @Autowired
    private UserService userService;

    @PostMapping("/like")
    public Result likeOthers(@RequestBody Posts posts, HttpServletRequest request)
    {
        String Token = request.getHeader("Authorization");
        String token = Token.substring(7);
        Map<String, Object> claims = JwtUtil.parseToken(token);
        Integer Id = (Integer) claims.get("id"); // 点的人
        Integer likesId = posts.getId(); // 被点的帖子
        Posts p = postsService.getUserIdByPostsId(likesId);
        Integer userId = p.getCreateUser(); // 收到消息的人的ID
        if(likesService.ifLiked(likesId,Id))
        {
            likesService.deleteLikes(likesId,Id);
            likesService.setLikesSub(likesId);
            likesService.setLikesUserSub(userId);
        }
        else
        {
            likesService.setLikes(likesId,Id);
            likesService.setLikesAdd(likesId); // 被点赞的帖子赞数+1
            likesService.setLikesUserAdd(userId);
            String userNickname = userService.getUserNickname(Id);
            messagesService.setLikesMessages(likesId,userId,userNickname);
        }
        return Result.success();
    }

    @GetMapping("/iflike")
    public Result ifLike(HttpServletRequest request)
    {
        String Token = request.getHeader("Authorization");
        String token = Token.substring(7);
        Map<String, Object> claims = JwtUtil.parseToken(token);
        Integer Id = (Integer) claims.get("id"); // 人
        String PostsId = request.getHeader("postsId");
        Integer postsId = Integer.valueOf(PostsId);
        if(likesService.ifLiked(postsId,Id))
        {
            return Result.success();
        }
        else
        {
            return Result.error("未点赞");
        }
    }
}
