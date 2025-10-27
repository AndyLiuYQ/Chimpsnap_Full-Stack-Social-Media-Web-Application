package com.chimpsnap.service.impl;

import com.chimpsnap.mapper.CommentsMapper;
import com.chimpsnap.pojo.Comments;
import com.chimpsnap.service.CommentsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentsServiceImpl implements CommentsService {
    @Autowired
    private CommentsMapper commentsMapper;
    // 上传评论
    @Override
    public void AddComment(Comments comments){
        commentsMapper.AddComment(comments);
    }

    // 返还该帖子下评论列表
    @Override
    public List<Comments> findCommentsByPostsId(Integer postsId) {
        List<Comments> c = commentsMapper.findCommentsByPostsId(postsId);
        return c;
    }

    // 获取评论总数
    @Override
    public Integer getNumOfComments()
    {
        Integer numComments = commentsMapper.getNumOfComments();
        return numComments;
    }

    // 更新评论人头像
    @Override
    public void updateUserPic(String url, Integer id)
    {
        commentsMapper.updateUserPic(url, id);
    }

}