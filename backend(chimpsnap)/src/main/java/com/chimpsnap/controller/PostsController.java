package com.chimpsnap.controller;

import com.chimpsnap.pojo.Posts;
import com.chimpsnap.pojo.Result;
import com.chimpsnap.service.PostsService;
import com.chimpsnap.service.UserService;
import com.chimpsnap.utils.JwtUtil;
import com.chimpsnap.utils.UploadUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Resource
@RestController
@RequestMapping("/posts")
@CrossOrigin
public class PostsController
{

    @Autowired
    public PostsService postsService;
    @Autowired
    public UserService userService;

    @PostMapping("/upload")
    public Result add(@RequestParam("file") MultipartFile file, @RequestParam("title") String title, @RequestParam("content") String content, HttpServletRequest request) throws Exception
    {
        String url;
        UploadUtil up = new UploadUtil();
        url = up.upload(file);
        String Token = request.getHeader("Authorization");
        String token = Token.substring(7);
        Map<String,Object> claims = JwtUtil.parseToken(token);
        Integer Id = (Integer) claims.get("id");
        userService.setNumPostsAddById(Id);
        userService.setUserPicUrl(url,Id);
        String userNickname = userService.getUserNickname(Id);
        String userPicUrl = userService.getUrlById(Id);
        Posts posts = new Posts();
        posts.setContent(content);
        posts.setLikes(0);
        posts.setTitle(title);
        posts.setCoverImg(url);
        posts.setCreateUser(Id);
        posts.setCreateUserpic(userPicUrl);
        posts.setUserNickname(userNickname);
        postsService.add(posts);
        return Result.success();
    }

    // 用户主页帖子信息
    @GetMapping("/postsInfo")
    public Result<List<Posts>> postsInfo(HttpServletRequest request)
    {
        // 根据用户id查询用户帖子
        String Token = request.getHeader("Authorization");
        String token = Token.substring(7);
        Map<String, Object> claims = JwtUtil.parseToken(token);
        Integer Id = (Integer) claims.get("id");
        List<Posts> p = postsService.findByUserId(Id);
        return Result.success(p);
    }

    // 查询别人用户详情
    @GetMapping("othersInfo")
    public Result<List<Posts>> othersInfo(HttpServletRequest request)
    {
        String getID = request.getHeader("othersId");
        Integer Id = Integer.valueOf(getID);
        List<Posts> p = postsService.findByUserId(Id);
        return Result.success(p);
    }


    // 查询单个帖子详情
    @GetMapping("/uniqueInfo")
    public Result<Posts> uniqueInfo(HttpServletRequest request)
    {
        String createPosts = request.getHeader("postsId");
        System.out.println("postsId: "+ createPosts);
        Integer postsId = Integer.valueOf(createPosts);
        Posts p = postsService.findByPostsId(postsId);
        return Result.success(p);
    }

    // 主页查询帖子信息  ——不用token
    @GetMapping("/homepage")
    public List<Map<String, Object>> getLatestPostsWithUser()
    {
        return postsService.getLatestPostsWithUser();
    }
}
