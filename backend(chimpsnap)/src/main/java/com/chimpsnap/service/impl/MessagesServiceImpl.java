package com.chimpsnap.service.impl;

import com.chimpsnap.mapper.MessagesMapper;
import com.chimpsnap.pojo.Messages;
import com.chimpsnap.service.MessagesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessagesServiceImpl implements MessagesService
{
    @Autowired
    private MessagesMapper messagesMapper;

    @Override
    public List<Messages> getMessagesByUserId(Integer id)
    {
        List<Messages> m = messagesMapper.getMessagesByUserId(id);
        return m;
    }

    // 根据用户ID删除消息
    @Override
    public void deleteMessagesByUserId(Integer id)
    {
        messagesMapper.deleteMessagesByUserId(id);
    }

    // 添加一条点赞消息
    @Override
    public void setLikesMessages(Integer likesId, Integer userId, String userNickname)
    {
        messagesMapper.setLikesMessages(likesId, userId, userNickname);
    }

    // 添加一条评论消息
    @Override
    public void setCommentsMessages(String content, Integer postsUserId, Integer postsId, String userNickname)
    {
        messagesMapper.setCommentsMessages(content, postsUserId, postsId, userNickname);
    }
}
