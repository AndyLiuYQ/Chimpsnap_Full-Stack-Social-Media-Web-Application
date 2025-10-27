package com.chimpsnap.mapper;

import com.chimpsnap.pojo.Comments;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface CommentsMapper{
    // 新增评论
    @Insert("insert into comments(content, create_user,create_posts,user_pic,user_nickname,create_time,update_time)" +
            " VALUES ( #{content}, #{createUser},#{createPosts},#{userPic},#{userNickname},now(),now())")
    void AddComment(Comments comments);

    // 返回评论列表
    @Select("select * from comments where create_posts=#{postsId} order by id desc")
    List<Comments> findCommentsByPostsId(Integer postsId);

    // 获取评论总数
    @Select("select count(*) from comments")
    Integer getNumOfComments();

    // 更新评论人头像
    @Update("update comments set user_pic=#{url} where create_user=#{id}")
    void updateUserPic(String url, Integer id);
}
