package com.chimpsnap.controller;

import com.chimpsnap.pojo.Comments;
import com.chimpsnap.pojo.Posts;
import com.chimpsnap.pojo.Result;
import com.chimpsnap.service.CommentsService;
import com.chimpsnap.service.MessagesService;
import com.chimpsnap.service.PostsService;
import com.chimpsnap.service.UserService;
import com.chimpsnap.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("/comments")
@CrossOrigin
public class CommentsController {
    @Autowired
    private CommentsService commentsService;
    @Autowired
    private UserService userService;
    @Autowired
    private MessagesService messagesService;
    @Autowired
    private PostsService postsService;

    @PostMapping("/add")
    public Result add(@RequestBody Comments comments, HttpServletRequest request){
        String Token = request.getHeader("Authorization");
        String token = Token.substring(7);
        Map<String,Object> claims = JwtUtil.parseToken(token);;
        Integer userId = (Integer) claims.get("id");
        String userPicUrl = userService.getUrlById(userId);
        String userNickname = userService.getUserNickname(userId);
        comments.setCreateUser(userId);
        System.out.println(comments.getCreateUser());
        comments.setUserPic(userPicUrl);
        System.out.println(comments.getUserPic());
        comments.setUserNickname(userNickname);
        System.out.println(comments.getUserNickname());
        String createPosts = request.getHeader("postsId");
        Integer postsId = Integer.valueOf(createPosts); // 帖子ID
        postsService.setNumcommentsAddByPostsId(postsId);
        comments.setCreatePosts(postsId);
        System.out.println(comments);
        commentsService.AddComment(comments);
        Posts p = postsService.getUserIdByPostsId(postsId);
        Integer postsUserId = p.getCreateUser();
        String Nickname = userService.getUserNickname(userId);
        messagesService.setCommentsMessages(comments.getContent(),postsUserId,postsId,Nickname);
        return  Result.success();
    }

    @GetMapping("/commentsInfo")
    public Result<List<Comments>> commentsInfo(HttpServletRequest request)
    {
        String createPosts = request.getHeader("postsId");
        Integer postsId = Integer.valueOf(createPosts);
        List<Comments> c = commentsService.findCommentsByPostsId(postsId);
        return Result.success(c);
    }
}
