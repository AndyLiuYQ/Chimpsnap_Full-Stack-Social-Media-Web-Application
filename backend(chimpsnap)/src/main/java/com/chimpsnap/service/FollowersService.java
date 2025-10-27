package com.chimpsnap.service;

public interface FollowersService
{
    void setFollowUser(Integer followedUserId,Integer id);

    boolean ifFollowed(Integer followedUserId, Integer id);

    void deleteFollowers(Integer followedUserId, Integer id);
}
