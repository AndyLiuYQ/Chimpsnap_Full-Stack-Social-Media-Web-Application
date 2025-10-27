package com.chimpsnap.service;

import com.chimpsnap.pojo.Messages;

import java.util.List;

public interface MessagesService
{
    // 通过用户ID找用户消息
    List<Messages> getMessagesByUserId(Integer id);

    // 删除用户消息
    void deleteMessagesByUserId(Integer id);

    // 添加一条点赞消息
    void setLikesMessages(Integer likesId, Integer userId,String userNickname);

    // 添加一条评论消息
    void setCommentsMessages(String content, Integer postsUserId,Integer postsId,String userNickname);
}
