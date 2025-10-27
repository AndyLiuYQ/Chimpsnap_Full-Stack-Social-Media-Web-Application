package com.chimpsnap.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Data
public class User
{
    @NotNull
    private Integer id; // 主键ID
    private String username; // 用户名
    @NotEmpty
    @Pattern(regexp = "^\\S{1,10}$")
    private String nickname; // 昵称
    @JsonIgnore // 让spring将其转换为json格式时忽略password
    private String password; // 密码
    private Integer posts; // 已发布帖子数量
    private String gender; // 性别
    private String newestPage; // 最新发布帖子封面
    private Integer likes; // 总赞数
    private Integer followers; // 总粉丝
//    @NotEmpty
    @Email
    private String email;
    private String userPic; // 用户头像
    private String signature; // 个性签名
    private LocalDateTime createTime; // 创建时间
    private LocalDateTime updateTime; // 更新时间
}
