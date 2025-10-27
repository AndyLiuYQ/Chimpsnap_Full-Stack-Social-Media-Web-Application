package com.chimpsnap.pojo;



import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Messages
{
    private Integer id; // ID
    private Integer userId; // 谁的消息
    private Integer postsId; // 关于哪条帖子的消息
    private Integer likes; // 点赞的消息 -为0的话说明是点赞 -为1的话说明是评论消息
    private String comments; // 评论的消息
    private String userNickname; // 评论人的昵称
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
