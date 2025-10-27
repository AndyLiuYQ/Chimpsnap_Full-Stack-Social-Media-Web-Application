package com.chimpsnap.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Posts
{
    private Integer id; // 主键ID
    private String title; // 帖子标题
    private String content; // 帖子正文
    private String coverImg; // 帖子封面
    private Integer likes; // 点赞数
    private Integer createUser; // 创建人ID
    private String createUserpic; // 发帖人头像
    private String userNickname; // 创建人昵称
    private Integer numComments; // 评论数
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
