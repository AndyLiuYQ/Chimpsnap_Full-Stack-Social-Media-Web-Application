package com.chimpsnap.mapper;

import org.apache.ibatis.annotations.*;

@Mapper
public interface FollowersMapper
{
    // 关注时设置关注
    @Insert("insert into followers(following_user,followed_user,create_time,update_time)" +
            " values(#{id},#{followedUserId},now(),now())")
    void setFollowUser(Integer followedUserId,Integer id);


    // 返回是否已经关注
    @Select("select count(*)>0 from followers where followed_user=#{followedUserId} and following_user=#{id}")
    Boolean ifFollowed(Integer followedUserId, Integer id);

    // 取消关注后删除其对应表格
    @Delete("delete from followers where followed_user=#{followedUserId} and following_user=#{id}")
    void deleteFollowers(Integer followedUserId, Integer id);
}
