document.addEventListener("DOMContentLoaded", async function () {
  try {
    // 获取存储的token (假设token已经存储在localStorage中)
    const token = localStorage.getItem("token");
    if (!token) {
      // 如果未找到token，直接跳转到登录页面
      alert("请先登录或注册！");
      window.location.href = "http://www.chimpsnap.fun/login/login.html";
      return; // 确保代码停止执行
    }

    // 从服务器获取用户信息，发送请求时附加token到请求头中
    const response = await fetch("http://103.117.121.141:8080/user/userInfo", {
      method: "GET",
      headers: {
        "Authorization": `Bearer ${token}`, // 在请求头中添加token
      },
    });

    if (!response.ok) {
      throw new Error("网络响应不正常");
    }

    const result = await response.json();
    // 假设服务器返回的数据格式如下
    // {
    //   "code": 0,
    //   "userName": "User123",
    //   "realName": "Real Name",
    //   "avatar": "http://example.com/avatar.jpg",
    //   "gender": "男"
    // }
    console.log(result);
    if (result.code === 0) {
     
       const nickname = result.data.nickname;
       const signature = result.data.signature;
       const userPic = result.data.userPic;
       const gender = result.data.gender;
     
      // 更新用户信息展示
      document.getElementById('likes').textContent = result.data.likes;
      document.getElementById('followers').textContent = result.data.followers;
      document.getElementById('posts').textContent = result.data.posts;
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
      
    } else {
      alert("加载用户信息失败: " + result.message);
    }
  } catch (error) {
    console.error("请求失败:", error);
    alert("请求失败: " + error.message);
  }
});
