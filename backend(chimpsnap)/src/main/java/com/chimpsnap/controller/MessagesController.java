package com.chimpsnap.controller;

import com.chimpsnap.pojo.Messages;
import com.chimpsnap.pojo.Result;
import com.chimpsnap.service.MessagesService;
import com.chimpsnap.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/messages")
@CrossOrigin
public class MessagesController
{
    @Autowired
    private MessagesService messagesService;

    // 获取用户所有消息
    @GetMapping("/messagesInfo")
    public Result<List<Messages>> messagesInfo(HttpServletRequest request)
    {
        String Token = request.getHeader("Authorization");
        String token = Token.substring(7);
        Map<String, Object> claims = JwtUtil.parseToken(token);
        Integer Id = (Integer) claims.get("id");
        List<Messages> m = messagesService.getMessagesByUserId(Id);
        return Result.success(m);
    }
    // 删除用户消息列表
    @PostMapping("/deleteMessages")
    public Result deleteMessages(HttpServletRequest request)
    {
        String Token = request.getHeader("Authorization");
        String token = Token.substring(7);
        Map<String, Object> claims = JwtUtil.parseToken(token);
        Integer Id = (Integer) claims.get("id");
        messagesService.deleteMessagesByUserId(Id);
        return Result.success();
    }
}
