package com.chimpsnap.pojo;


import lombok.Data;

import java.time.LocalDateTime;
@Data
public class Comments
{
    private Integer id; // ID
    private String content; // 评论内容
    private Integer createUser; // 评论人ID
    private Integer createPosts; // 帖子ID
    private String userPic; // 评论人头像
    private String userNickname; // 评论人昵称
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 修改时间
}
