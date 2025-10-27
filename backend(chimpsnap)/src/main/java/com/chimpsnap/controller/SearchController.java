package com.chimpsnap.controller;


import com.chimpsnap.pojo.Result;
import com.chimpsnap.pojo.User;
import com.chimpsnap.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@Resource
@RestController
@CrossOrigin
@RequestMapping("/search")
public class SearchController
{
    @Autowired
    private UserService userService;

    @PostMapping("/nickname")
    public Result<List<User>> searchForNickname(@RequestBody User user)
    {
        String Nickname = user.getNickname();
        List<User> u = userService.findByUserNickname(Nickname);
        return Result.success(u);
    }
}
