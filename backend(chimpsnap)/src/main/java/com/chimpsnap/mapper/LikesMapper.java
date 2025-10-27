package com.chimpsnap.mapper;


import org.apache.ibatis.annotations.*;

@Mapper
public interface LikesMapper
{

    // 查询是否点过赞
    @Select("select count(*)>0 from likes where create_user=#{id} and create_posts=#{likesId}")
    boolean ifLiked(Integer likesId, Integer id);

    // 取消点赞
    @Delete("delete from likes where create_user=#{id} and create_posts=#{likesId}")
    void deleteLikes(Integer likesId, Integer id);

    // 帖子赞数-1
    @Update("update posts set likes=likes-1 where id=#{likesId}")
    void setLikesSub(Integer likesId);

    // 设置点赞
    @Insert("insert into likes(create_posts,create_user,create_time,update_time)" +
            " values(#{likesId},#{id},now(),now())")
    void setLikes(Integer likesId, Integer id);

    // 帖子赞数+1
    @Update("update posts set likes=likes+1 where id=#{likesId}")
    void setLikesAdd(Integer likesId);


    // 用户的总点赞量-1
    @Update("update user set likes=likes-1 where id=#{userId}")
    void setLikesUserSub(Integer userId);

    // 用户的总点赞量+1
    @Update("update user set likes=likes+1 where id=#{userId}")
    void setLikesUserAdd(Integer userId);

    // 获取点赞总数
    @Select("select count(*) from likes")
    Integer getNumOfLikes();
}
