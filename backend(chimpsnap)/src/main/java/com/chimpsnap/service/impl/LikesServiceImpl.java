package com.chimpsnap.service.impl;

import com.chimpsnap.mapper.LikesMapper;
import com.chimpsnap.service.LikesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikesServiceImpl implements LikesService
{
    @Autowired
    private LikesMapper likesMapper;

    @Override
    public boolean ifLiked(Integer likesId, Integer id)
    {
        boolean f = likesMapper.ifLiked(likesId,id);
        return f;
    }

    @Override
    public void deleteLikes(Integer likesId, Integer id)
    {
        likesMapper.deleteLikes(likesId,id);
    }

    @Override
    public void setLikesSub(Integer likesId)
    {
        likesMapper.setLikesSub(likesId);
    }

    @Override
    public void setLikes(Integer likesId, Integer id)
    {
        likesMapper.setLikes(likesId,id);
    }

    @Override
    public void setLikesAdd(Integer likesId)
    {
        likesMapper.setLikesAdd(likesId);
    }

    @Override
    public void setLikesUserSub(Integer userId)
    {
        likesMapper.setLikesUserSub(userId);
    }

    @Override
    public void setLikesUserAdd(Integer userId)
    {
        likesMapper.setLikesUserAdd(userId);
    }

    // 获取点赞总数
    @Override
    public Integer getNumOfLikes()
    {
        Integer numLikes = likesMapper.getNumOfLikes();
        return numLikes;
    }
}
