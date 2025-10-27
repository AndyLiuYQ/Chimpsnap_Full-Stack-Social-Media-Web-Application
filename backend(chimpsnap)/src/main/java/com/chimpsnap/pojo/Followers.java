package com.chimpsnap.pojo;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Followers
{
    private Integer id; // ID
    private LocalDateTime createTime; // 关注时间
    private LocalDateTime updateTime; // 修改时间
    private Integer followingUser; // 谁关注的
    private Integer followedUser; // 关注的谁
}
