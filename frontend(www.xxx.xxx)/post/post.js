<!DOCTYPE html>
<html>
<head>
    <link rel="stylesheet" href="post.css">
</head>
<body>
    <section class="main">
        <div class="wrapper">
            <div class="left-col">
                <div class="post">
                    <div class="info">
                        <div class="user">
                            <div class="profile-pic"><img id="userPic" src="" alt=""></div>
                            <p class="username" id="nickname">666</p>
                        </div>
                        <img src="" class="options" alt="">
                    </div>
                    <img src="" class="post-image" id="coverImg" alt="">
                    <div class="post-content">
                        <object data="thumbsup.html" type="text/html" style="height: 58px"></object>
                        <object data="thumbsdown.html" type="text/html" style="height: 58px"></object>
                        <div class="reaction-wrapper"></div>
                        <p class="likes" id="likes"></p>
                        <p class="description" id="title"><span>TITLE </span></p>
                        <p class="post-time" id="createTime"></p>
                    </div>
                    <div class="comment-wrapper">
                        <img src="微信图片_20240820191455.jpg" class="icon" alt="">
                        <input type="text" class="comment-box" placeholder="Add a comment">
                        <button class="comment-btn">send</button>
                    </div>
                    <div class="comment-list">
                        <!-- 评论将会在这里显示 -->
                    </div>
                </div>
            </div>
        </div>
    </section>

    <script>
        document.addEventListener('DOMContentLoaded', async function() {
            const commentList = document.querySelector('.comment-list');
            const token = localStorage.getItem("token"); // 确保你正确获取了 token
            const postId = localStorage.getItem('currentPostId');
            alert(postId);

            if (!token) {
                console.error('No token found in localStorage.');
                window.location.href("http:/www.chimpsnap.fun/login/login.html");
                return;
            }

            // 获取帖子信息
            try {
                const postResponse = await fetch('http://103.117.121.141:8080/posts/uniqueInfo', {
                    method: "GET",
                    headers: {
                        "postsID": postId,
                        "Authorization": `Bearer ${token}`
                    }
                });
                if (!postResponse.ok) throw new Error('Failed to fetch post data');
                const postData = await postResponse.json();
                
                // 设置帖子信息到页面
                document.getElementById('userPic').src = postData.data.userPic;
                document.getElementById('coverImg').src = postData.data.coverImg;
                document.getElementById('likes').textContent = `${postData.data.likes} likes`;
                document.getElementById('title').innerHTML = `<span>TITLE </span>${postData.data.title}`;
                document.getElementById('createTime').textContent = new Date(postData.data.createTime).toLocaleDateString();
                document.getElementById('nickname').textContent = postData.data.nickname;

            } catch (error) {
                console.error('Error loading post data:', error);
            }

            // 获取评论信息
            try {
                const commentResponse = await fetch('http://103.117.121.141:8080/comments/commentsInfo', {
                    method: "GET",
                    headers: {
                        "postsID": postId,
                        "Authorization": `Bearer ${token}`
                    }
                });
                if (!commentResponse.ok) throw new Error('Failed to fetch comment data');
                const commentData = await commentResponse.json();
                
                commentData.data.forEach(comment => {
                    // 创建评论元素
                    const commentItem = document.createElement('div');
                    commentItem.className = 'comment-item';
                    
                    const profilePic = document.createElement('img');
                    profilePic.src = '微信图片_20240822001322.jpg'; // 替换为用户头像
                    commentItem.appendChild(profilePic);
                    
                    const commentTextElem = document.createElement('div');
                    commentTextElem.className = 'comment-text';
                    commentTextElem.textContent = comment.content;
                    
                    commentItem.appendChild(commentTextElem);

                    const commentTimeElem = document.createElement('div');
                    commentTimeElem.className = 'comment-time';
                    commentTimeElem.textContent = new Date(comment.createTime).toLocaleString();
                    
                    commentItem.appendChild(commentTimeElem);

                    // 将评论添加到评论列表中
                    commentList.appendChild(commentItem);
                });
            } catch (error) {
                console.error('Error loading comments:', error);
            }

            // 处理评论提交
            const commentBtn = document.querySelector('.comment-btn');
            const commentBox = document.querySelector('.comment-box');

            commentBtn.addEventListener('click', function() {
                const commentText = commentBox.value.trim();
                if (commentText) {
                    // 创建新的评论元素
                    const commentItem = document.createElement('div');
                    commentItem.className = 'comment-item';
                    
                    const profilePic = document.createElement('img');
                    profilePic.src = '微信图片_20240822001322.jpg'; // 替换为用户头像
                    commentItem.appendChild(profilePic);
                    
                    const commentTextElem = document.createElement('div');
                    commentTextElem.className = 'comment-text';
                    commentTextElem.textContent = commentText;
                    
                    commentItem.appendChild(commentTextElem);

                    // 创建时间元素
                    const commentTimeElem = document.createElement('div');
                    commentTimeElem.className = 'comment-time';
                    const now = new Date();
                    commentTimeElem.textContent = now.toLocaleString();
                    
                    commentItem.appendChild(commentTimeElem);

                    // 将新评论添加到评论列表中
                    commentList.appendChild(commentItem);
    
                    // 清空输入框
                    commentBox.value = '';
                }
            });
        });
    </script>
</body>
</html>
