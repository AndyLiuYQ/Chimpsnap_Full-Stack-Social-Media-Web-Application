package com.chimpsnap.service;

import com.chimpsnap.pojo.User;

import java.util.List;

public interface UserService
{
    // 根据用户名查询用户
    User findByUserName(String username);

    // 注册
    void register(String username, String password);

    // 更新
    void update(User user);

    // 更新用户头像
    void updateAvatar(String avatarUrl,Integer Id);

    List<User> findByUserNickname(String nickname);

    String getUrlById(Integer id);
    void setUserPicUrl(String url,Integer id);

    String getUserNickname(Integer id);


    // 个人被关注followers+1
    void setFollowersAdd(Integer id);

    // 通过自己找自己关注的人
    List<User> getUserByFollowingId(Integer id);

    void setFollowersSub(Integer followedUserId);

    User getUsernameById(Integer othersId);

    void setNumPostsAddById(Integer id);

    // 获取用户总数
    Integer getTotalUsers();

    // 获取男性用户
    Integer getNumOfMen();

    // 获取女性用户
    Integer getNumOfWomen();

    // 获取粉丝最多的人
    List<User> getHotestUsers();

    // 传入最新注册的用户信息
    User getNewestUser();
}
