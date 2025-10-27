package com.chimpsnap.controller;


import com.chimpsnap.pojo.Comments;
import com.chimpsnap.pojo.Result;
import com.chimpsnap.pojo.User;
import com.chimpsnap.service.CommentsService;
import com.chimpsnap.service.PostsService;
import com.chimpsnap.service.UserService;
import com.chimpsnap.utils.JwtUtil;
import com.chimpsnap.utils.Md5Util;
import com.chimpsnap.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Resource
@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController
{
    @Autowired
    private UserService userService;
    @Autowired
    private PostsService postsService;
    @Autowired
    private CommentsService commentsService;

    @RequestMapping("/hello")
    public String hello(){
        return  "hello chimpsnap";
    }

    // 注册
    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password)
    {
        // 查询用户
        User u = userService.findByUserName(username);
        if (u == null)
        {
            // 没有占用该用户名
            // 注册
            userService.register(username, password);
            return Result.success();
        }
        else
        {
            // 占用
            return Result.error("用户名已被占用");
        }
    }

    // 登录
    @PostMapping("/login")
    public Result<String> login(@Pattern(regexp = "^\\S{5,16}$") String username, @Pattern(regexp = "^\\S{5,16}$") String password)
    {
        // 根据用户名查询用户
        User loginUser = userService.findByUserName(username);
        // 判断用户是否存在
        if(loginUser == null)
        {
            return Result.error("用户名或密码错误");
        }
        // 判断密码是否正确 - 数据库中的密码是加密后的
        if(Md5Util.getMD5String(password).equals(loginUser.getPassword()))
        {
            // 登录成功
            Map<String,Object> claims = new HashMap<>();
            claims.put("id",loginUser.getId());
            claims.put("username",loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }
        return Result.error("用户名或密码错误");
    }

    // 获取自己信息
    @GetMapping("/userInfo")
    public Result<User> userInfo(HttpServletRequest request)
    {
        String Token = request.getHeader("Authorization");
        String token = Token.substring(7);
//        System.out.println("token: " + token);
        Map<String,Object> claims = JwtUtil.parseToken(token);;
        String username = (String)claims.get("username");
//        System.out.println("username: "+username);
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    // 更改自己信息
    @PutMapping("/update")
    public Result update(@RequestBody User user,HttpServletRequest request)
    {
        String Token = request.getHeader("Authorization");
        String token = Token.substring(7);
        Map<String,Object> claims = JwtUtil.parseToken(token);;
        Integer Id = (Integer) claims.get("id");
        System.out.println("id: " + Id);
        user.setId(Id);
        user.setUpdateTime(LocalDateTime.now());
        userService.update(user);
        return Result.success();
    }

    // 更改自己头像
    @PatchMapping("/updateAvatar")
    public Result updateAvatar(@RequestParam("file") MultipartFile file, HttpServletRequest request)
    {
        String url;
        UploadUtil up = new UploadUtil();
        try
        {
            url = up.upload(file);
        }
        catch (Exception e)
        {
            throw new RuntimeException(e);
        }
        String Token = request.getHeader("Authorization");
        String token = Token.substring(7);
        Map<String,Object> claims = JwtUtil.parseToken(token);;
        Integer Id = (Integer) claims.get("id");
        userService.updateAvatar(url,Id);
        postsService.updateCreateUserPic(url,Id);
        commentsService.updateUserPic(url,Id);
        return Result.success();
    }

    // 查看别人主页信息
    @GetMapping("/othersInfo")
    public Result<User> othersInfo(HttpServletRequest request)
    {
        String getID = request.getHeader("othersId");
        Integer othersId = Integer.valueOf(getID);
        User user1 = userService.getUsernameById(othersId);
        String username = user1.getUsername();
        User user = userService.findByUserName(username);
        return Result.success(user);
    }
}
