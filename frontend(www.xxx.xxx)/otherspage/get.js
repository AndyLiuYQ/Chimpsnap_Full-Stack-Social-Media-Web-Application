document.addEventListener("DOMContentLoaded", async function () {
  try {
    // 获取存储的token (假设token已经存储在localStorage中)
    const token = localStorage.getItem("token");
    const UserID = localStorage.getItem("UserID");

    if (!token) {
      // 如果未找到token，直接跳转到登录页面
      alert("请先登录或注册！");
      window.location.href = "http://www.chimpsnap.fun/login/login.html";
      return; // 确保代码停止执行
    }
    
    
    // 从服务器获取用户信息，发送请求时附加token到请求头中
   const response = await fetch('http://103.117.121.141:8080/user/othersInfo', {
            method: "GET",
            headers: {
                "othersId": `${UserID}`,
                "Authorization": `Bearer ${token}`
            }
        });



    if (!response.ok) {
      throw new Error("网络响应不正常");
    }
    const result = await response.json();
    console.log(result.data);
    // 假设服务器返回的数据格式如下
    // {
    //   "code": 0,
    //   "userName": "User123",
    //   "realName": "Real Name",
    //   "avatar": "http://example.com/avatar.jpg",
    //   "gender": "男"
    // }
    if (result.code === 0) {
     
       const nickname = result.data.nickname;
       const signature = result.data.signature;
       const userPic = result.data.userPic;
       const gender = result.data.gender;
     
     
      document.getElementById('likes').textContent = result.data.likes;
      document.getElementById('followers').textContent = result.data.followers;
      document.getElementById('posts').textContent = result.data.posts;
      // 更新用户信息展示
      document.getElementById("profile-user-name").innerText = nickname;
      if(signature==="0")
      {
         const signaturetemp = "| 你好 | Hello | こんにちは | Bonjour | Hallo | नमस्ते | Hola | 안녕하세요 | བདེ་མོ |📷✈";
         document.getElementById("profile-real-name").innerText = signaturetemp;
      }
      else{
      document.getElementById("profile-real-name").innerText = signature;
      }

      // 更新性别展示
      const genderText = gender === "1" ? "男" : gender === "0" ? "女" : "其他";
      document.getElementById(
        "profile-gender"
      ).innerText = `性别：${genderText}`;

      // 更新头像
      if(!userPic)
      {
          document.getElementById("profileImage").src = "https://i2.imgs.ovh/d/BQACAgUAAx0EUvSR8wACNiBmyqII4cThhGNZSPWNdwGKWS-HnAACERAAApXPWFZ1NjzmQVdkETUE";
      }
      else{
      document.getElementById("profileImage").src = userPic;
      }
      //更新subscribe按钮状态
       const response1 = await fetch('http://103.117.121.141:8080/followers/iffollow', {
            method: "GET",
            headers: {
                "othersId": `${UserID}`,
                "Authorization": `Bearer ${token}`
            }
        });
        
        const result1 = await response1.json();
        if(result1.code==0)
        {
            const button = document.getElementById('button1');
            button.classList.toggle('clicked'); // 添加clicked类
        }
      
      
    } else {
      alert("加载用户信息失败: " + result.message);
    }
  } catch (error) {
    console.error("请求失败:", error);
    alert("请求失败: " + error.message);
  }
});

//控制subscribe按钮
document.getElementById('button1').addEventListener('click', function() {
    this.classList.toggle('clicked');
    const token = localStorage.getItem("token");
    const UserID = localStorage.getItem("UserID");
    
    fetch('http://103.117.121.141:8080/followers/follow', {
    method: 'POST',
    headers: {
        'Content-Type': 'application/json',
        'Authorization': `Bearer ${token}` // If you need to include the token as a header
    },
    body: JSON.stringify({ id: UserID })
})
.then(response => {
    if (!response.ok) {
        throw new Error('Network response was not ok');
    }
    return response.json();
})
.catch(error => {
    console.error('Error:', error);
});
});
