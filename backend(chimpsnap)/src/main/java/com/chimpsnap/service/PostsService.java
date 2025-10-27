package com.chimpsnap.service;


import com.chimpsnap.pojo.Posts;

import java.util.List;
import java.util.Map;

public interface PostsService {
    //新增文章
    public void add(Posts posts);

    // 个人主页帖子信息
    public List<Posts> findByUserId(Integer id);

    // 首页帖子信息
    public List<Map<String, Object>> getLatestPostsWithUser();
    public Posts findByPostsId(Integer postsId);

    // 通过帖子找用户
    Posts getUserIdByPostsId(Integer likesId);

    // 为posts设置评论数
    void setNumcommentsAddByPostsId(Integer postsId);

    // 获取总贴子数
    Integer getNumOfPosts();

    // 点赞最多的几张帖子
    List<Posts> getHotestPosts();

    // 通过用户ID更新帖子头像
    void updateCreateUserPic(String url, Integer id);
}
