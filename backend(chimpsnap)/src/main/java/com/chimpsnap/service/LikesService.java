package com.chimpsnap.service;


import org.springframework.stereotype.Service;

@Service
public interface LikesService
{
    // 查询是否点过赞
    boolean ifLiked(Integer likesId, Integer id);

    // 取消点赞
    void deleteLikes(Integer likesId, Integer id);

    // 帖子赞数-1
    void setLikesSub(Integer likesId);

    // 添加点赞
    void setLikes(Integer likesId, Integer id);

    // 帖子赞数+1
    void setLikesAdd(Integer likesId);

    void setLikesUserSub(Integer userId);

    void setLikesUserAdd(Integer userId);

    // 获取点赞总数
    Integer getNumOfLikes();
}
