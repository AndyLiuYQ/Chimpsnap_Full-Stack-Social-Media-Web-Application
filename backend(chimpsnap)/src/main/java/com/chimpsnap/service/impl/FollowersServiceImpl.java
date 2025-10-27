package com.chimpsnap.service.impl;

import com.chimpsnap.mapper.FollowersMapper;
import com.chimpsnap.service.FollowersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FollowersServiceImpl implements FollowersService
{
    @Autowired
    private FollowersMapper followersMapper;

    // 设置是谁去关注的
    @Override
    public void setFollowUser(Integer followedUserId,Integer id)
    {
        followersMapper.setFollowUser(followedUserId,id);
    }


    // 查看是否已经关注过了
    @Override
    public boolean ifFollowed(Integer followedUserId, Integer id)
    {
        Boolean f = followersMapper.ifFollowed(followedUserId,id);
        return f;
    }

    // 取消关注 删除其对于followers表
    @Override
    public void deleteFollowers(Integer followedUserId, Integer id)
    {
        followersMapper.deleteFollowers(followedUserId, id);
    }
}
