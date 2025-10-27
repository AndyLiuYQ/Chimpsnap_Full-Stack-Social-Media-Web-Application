package com.chimpsnap.service.impl;

import com.chimpsnap.mapper.PostsMapper;
import com.chimpsnap.pojo.Posts;
import com.chimpsnap.service.PostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


@Service
public class PostsServiceImpl implements PostsService
{
    @Autowired
    private PostsMapper postsMapper;

    @Override
    public void add(Posts posts)
    {
        postsMapper.add(posts);
    }

    @Override
    public List<Posts> findByUserId(Integer id)
    {
        return postsMapper.findByUserId(id);
    }

    @Override
    public List<Map<String, Object>> getLatestPostsWithUser() {
        // 调用 PostsMapper 方法并返回结果
        List<Map<String, Object>> temp= postsMapper.findLatestPostsWithUser();
        List<Map<String, Object>> temp1=new ArrayList<>();
        int signal=1;
        for (Map<String, Object> map:temp){
            map.put("num_id",signal);
            temp1.add(map);
            signal++;
        }
        return temp1;
    }

    @Override
    public Posts findByPostsId(Integer postsId) {
        return postsMapper.findByPostsId(postsId);
    }

    // 通过帖子ID找帖子信息
    @Override
    public Posts getUserIdByPostsId(Integer likesId)
    {
        Posts p = postsMapper.getUserIdByPostsId(likesId);
        return p;
    }

    @Override
    public void setNumcommentsAddByPostsId(Integer postsId)
    {
        postsMapper.setNumcommentsAddByPostsId(postsId);
    }

    // 获取总帖子数
    @Override
    public Integer getNumOfPosts()
    {
        Integer numPosts = postsMapper.getNumOfPosts();
        return numPosts;
    }

    // 点赞最多的几张帖子
    @Override
    public List<Posts> getHotestPosts()
    {
        List<Posts> p = postsMapper.getHotestPosts();
        return p;
    }

    // 通过用户ID更新帖子用户头像
    @Override
    public void updateCreateUserPic(String url, Integer id)
    {
        postsMapper.updateCreateUserPic(url, id);
    }

}
