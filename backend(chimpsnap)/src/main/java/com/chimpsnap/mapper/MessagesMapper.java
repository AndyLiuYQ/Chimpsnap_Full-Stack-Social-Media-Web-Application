package com.chimpsnap.mapper;

import com.chimpsnap.pojo.Messages;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface MessagesMapper
{
    // 通过用户ID查找他所有的消息
    @Select("select * from messages where user_id=#{id}")
    List<Messages> getMessagesByUserId(Integer id);

    // 删除用户所有消息
    @Delete("delete from messages where user_id=#{id}")
    void deleteMessagesByUserId(Integer id);

    // 设置一条点赞消息
    @Insert("insert into messages(user_id,posts_id,likes,user_nickname,create_time,update_time)" +
            " values(#{userId},#{likesId},0,#{userNickname},now(),now())")
    void setLikesMessages(Integer likesId, Integer userId, String userNickname);

    // 设置一条评论消息
    @Insert("insert into messages(user_id,posts_id,likes,comments,user_nickname,create_time,update_time)" +
            " values(#{postsUserId},#{postsId},1,#{content},#{userNickname},now(),now())")
    void setCommentsMessages(String content, Integer postsUserId,Integer postsId, String userNickname);
}
