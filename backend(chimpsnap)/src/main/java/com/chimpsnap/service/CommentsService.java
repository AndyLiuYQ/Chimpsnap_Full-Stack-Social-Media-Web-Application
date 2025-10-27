package com.chimpsnap.service;

import com.chimpsnap.pojo.Comments;

import java.util.List;

public interface CommentsService {
    public void AddComment(Comments comments);
    public List<Comments> findCommentsByPostsId(Integer postsId);

    // 获取评论总数
    Integer getNumOfComments();

    // 更新评论人头像
    void updateUserPic(String url, Integer id);
}
