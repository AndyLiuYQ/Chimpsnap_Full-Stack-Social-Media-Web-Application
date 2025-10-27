package com.chimpsnap.pojo;


import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Likes
{
    private Integer id; // ID
    private Integer createUser; // 点赞的人
    private Integer createPosts; // 被点赞的帖子
    private LocalDateTime createTime; // 点赞时间
    private LocalDateTime updateTime; // 更新时间
}
