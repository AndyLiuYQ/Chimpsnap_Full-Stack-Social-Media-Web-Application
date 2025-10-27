package com.chimpsnap.mapper;

import com.chimpsnap.pojo.Posts;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;
import java.util.Map;

@Mapper
public interface PostsMapper {
    //新增帖子
    @Insert("insert into posts(title,content,cover_img,create_user,create_userpic,user_nickname,create_time,update_time)" +
            " values(#{title},#{content},#{coverImg},#{createUser},#{createUserpic},#{userNickname},now(),now())")
    void add(Posts posts);

    // 返还个人主页显示的所有帖子
    @Select("select * from posts where create_user=#{id} order by id desc")
    List<Posts> findByUserId(Integer id);

    // 网页首页的 16 个帖子信息
    @Select("select p.id as post_id, p.title, p.content, u.nickname, u.user_pic, p.create_time, p.cover_img ,p.likes " +
            "from posts p " +
            "join user u on p.create_user = u.id " +
            "order by p.id desc limit 16")
    List<Map<String, Object>> findLatestPostsWithUser();

    // 根据id找帖子
    @Select("select * from posts where id=#{postsId}")
    Posts findByPostsId(Integer postsId);

    // 通过帖子ID查找帖子
    @Select("select * from posts where id=#{likesId}")
    Posts getUserIdByPostsId(Integer likesId);

    // 为帖子评论总数+1
    @Update("update posts set num_comments=num_comments+1 where id=#{postsId}")
    void setNumcommentsAddByPostsId(Integer postsId);

    // 获取帖子总数
    @Select("select count(*) from posts")
    Integer getNumOfPosts();

    // 点赞最多的几张帖子
    @Select("select * from posts order by likes desc limit 5")
    List<Posts> getHotestPosts();

    // 通过用户ID更新帖子创建人头像
    @Update("update posts set create_userpic=#{url} where create_user=#{id}")
    void updateCreateUserPic(String url, Integer id);

/*    // 更新上传帖子的用户头像 -帖子主页显示
    @Update("update posts set create_userpic=#{userPicUrl} where id=#{id}")
    void setUserpic(String userPicUrl,Integer id);

    // 根据帖子更新用户昵称  -帖子主页显示
    @Update("update posts set user_nickname=#{userNickname} where id=#{id}")
    void setUserNickname(String userNickname, Integer id);*/
}
