package com.chimpsnap.controller;


import com.chimpsnap.pojo.Posts;
import com.chimpsnap.pojo.Result;
import com.chimpsnap.pojo.User;
import com.chimpsnap.service.CommentsService;
import com.chimpsnap.service.LikesService;
import com.chimpsnap.service.PostsService;
import com.chimpsnap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/datas")
@CrossOrigin
public class DatasController
{
    @Autowired
    private UserService userService;
    @Autowired
    private PostsService postsService;
    @Autowired
    private LikesService likesService;
    @Autowired
    private CommentsService commentsService;

    // 总人数
    @GetMapping("/totalusers")
    public Result<Integer> totalUserDatas()
    {
        Integer total = userService.getTotalUsers();
        return Result.success(total);
    }

    // 各个性别的人数
    @GetMapping("/gender")
    public Result<List<Integer>> genderDatas()
    {
        Integer men = userService.getNumOfMen();
        Integer women = userService.getNumOfWomen();
        Integer other = userService.getTotalUsers() - men - women;
        List<Integer> l = Arrays.asList(men, women, other);
        return Result.success(l);
    }

    // 总贴子数、点赞数、评论数
    @GetMapping("/totalposts")
    public Result<List<Integer>> totalPosts()
    {
        Integer numPosts = postsService.getNumOfPosts();
        Integer numLikes = likesService.getNumOfLikes();
        Integer numComments = commentsService.getNumOfComments();
        List<Integer> l = Arrays.asList(numPosts, numLikes, numComments);
        return Result.success(l);
    }

    // 粉丝最多的几位用户
    @GetMapping("/hotestusers")
    public Result<List<User>> hotestUers()
    {
        List<User> u = userService.getHotestUsers();
        return Result.success(u);
    }

    // 最新注册的用户相关信息
    @GetMapping("/newestuser")
    private Result<User> newestUser()
    {
        User u = userService.getNewestUser();
        return Result.success(u);
    }

    // 点赞最多的几个帖子
    @GetMapping("/hotestposts")
    public Result<List<Posts>> hotestPosts()
    {
        List<Posts> p = postsService.getHotestPosts();
        return Result.success(p);
    }
}
