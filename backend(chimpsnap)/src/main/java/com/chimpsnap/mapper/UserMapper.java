package com.chimpsnap.mapper;

import com.chimpsnap.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface UserMapper
{
    // 根据用户名查找用户
    @Select("select * from user where username=#{username}")
    User findByUserName(String username);

    // 添加
    @Insert("insert into user(username,password,nickname,user_pic,create_time,update_time)" +
            " values(#{username},#{password},#{nickname},#{url},now(),now())")
    void add(String username, String password,String nickname,String url);

    // 更新
    @Update("update user set nickname=#{nickname},gender=#{gender},signature=#{signature},update_time=now() where id=#{id}")
    void update(User user);

    // 更新头像
    @Update("update user set user_pic=#{url},update_time=now() where id=#{id}")
    void updateAvatar(String url,Integer id);

    // 模糊查询
    @Select("select * from user where nickname like concat('%', #{nickname}, '%')")
    List<User> findByUserNickname(String nickname);

    // 根据id查询用户
    @Select("select * from user where id=#{id}")
    User getUrlById(Integer id);

    // 设置用户最新发表的帖子信息 -查询
    @Update("update user set newest_page=#{url} where id=#{id}")
    void setUserPicUrl(String url, Integer id);

    // 根据昵称查询用户 -搜索
    @Select("select * from user where id=#{id}")
    User getUserNickname(Integer id);

    // 个人被关注设置followers+1
    @Update("update user set followers=followers+1 where id=#{id}")
    void setFollowersAdd(Integer id);

    // 找自己关注的人
    @Select("select * from user " +
            "where id in (select followed_user from followers where following_user=#{id})")
    List<User> getUserByFollowingId(Integer id);

    // 被取消关注粉丝-1
    @Update("update user set followers=followers-1 where id=#{followedUserId}")
    void setFollowersSub(Integer followedUserId);

    // 根据id查找用户
    @Select("select * from user where id=#{othersId}")
    User getUsernameById(Integer othersId);

    // 更新用户发帖总量
    @Update("update user set posts=posts+1 where id=#{id}")
    void setNumPostsAddById(Integer id);

    // 获取用户总数
    @Select("select count(*) from user")
    Integer getTotalUsers();

    // 获取男性用户
    @Select("select count(*) from user where gender=1")
    Integer getNumOfMen();

    // 获取女性用户
    @Select("select count(*) from user where gender=0")
    Integer getNumOfWomen();

    // 获取粉丝最多的五位用户
    @Select("select * from user order by followers desc limit 5")
    List<User> getHotestUsers();

    // 返回最新注册的用户的信息
    @Select("select * from user order by id desc limit 1")
    User getNewestUser();
}
