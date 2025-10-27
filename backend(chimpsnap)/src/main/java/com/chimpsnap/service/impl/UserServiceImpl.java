package com.chimpsnap.service.impl;

import com.chimpsnap.mapper.UserMapper;
import com.chimpsnap.pojo.User;
import com.chimpsnap.service.UserService;
import com.chimpsnap.utils.Md5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService
{
    @Autowired
    private UserMapper userMapper;
    @Override
    public User findByUserName(String username)
    {
        User u = userMapper.findByUserName(username);
        return u;
    }

    @Override
    public void register(String username, String password)
    {
        // 加密
        String md5String = Md5Util.getMD5String(password);
        // 初始头像URL
        String url = "https://i2.imgs.ovh/d/BQACAgUAAx0EUvSR8wACNiBmyqII4cThhGNZSPWNdwGKWS-HnAACERAAApXPWFZ1NjzmQVdkETUE";
        // 添加到数据库
        userMapper.add(username,md5String,username,url);
    }

    @Override
    public void update(User user)
    {
        userMapper.update(user);
    }

    @Override
    public void updateAvatar(String avatarUrl,Integer Id)
    {
        userMapper.updateAvatar(avatarUrl,Id);
    }

    @Override
    public List<User> findByUserNickname(String nickname)
    {
        List<User> u = userMapper.findByUserNickname(nickname);
        return u;
    }

    @Override
    public String getUrlById(Integer id)
    {
        User u = userMapper.getUrlById(id);
        return u.getUserPic();
    }

    @Override
    public void setUserPicUrl(String url, Integer id)
    {
        userMapper.setUserPicUrl(url,id);
    }

    @Override
    public String getUserNickname(Integer id)
    {
        User u = userMapper.getUserNickname(id);
        return u.getNickname();
    }

    // 个人被关注followers+1
    @Override
    public void setFollowersAdd(Integer id)
    {
        userMapper.setFollowersAdd(id);
    }

    // 通过自己找自己关注的人
    @Override
    public List<User> getUserByFollowingId(Integer id)
    {
        List<User> u = userMapper.getUserByFollowingId(id);
        return u;
    }

    // 被取消关注粉丝-1
    @Override
    public void setFollowersSub(Integer followedUserId)
    {
        userMapper.setFollowersSub(followedUserId);
    }

    @Override
    public User getUsernameById(Integer othersId)
    {
        User user = userMapper.getUsernameById(othersId);
        return user;
    }

    @Override
    public void setNumPostsAddById(Integer id)
    {
        userMapper.setNumPostsAddById(id);
    }

    // 获取用户总数
    @Override
    public Integer getTotalUsers()
    {
        Integer total = userMapper.getTotalUsers();
        return total;
    }

    // 获取男性用户
    @Override
    public Integer getNumOfMen()
    {
        Integer men = userMapper.getNumOfMen();
        return men;
    }

    @Override
    public Integer getNumOfWomen()
    {
        Integer women = userMapper.getNumOfWomen();
        return women;
    }

    // 获取粉丝最多的用户
    @Override
    public List<User> getHotestUsers()
    {
        List<User> u = userMapper.getHotestUsers();
        return u;
    }

    // 返还最新注册的用户信息
    @Override
    public User getNewestUser()
    {
        User u = userMapper.getNewestUser();
        return u;
    }

}
