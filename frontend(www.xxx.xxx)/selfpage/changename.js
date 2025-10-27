document.addEventListener("DOMContentLoaded", function () {
  // 处理保存按钮的点击事件
  document
    .querySelector("#save-button")
    .addEventListener("click", async function () {
      const nickname = document.getElementById("edit-user-name").value;
      const signature = document.getElementById("edit-real-name").value;
      const genderInput = document.getElementById("edit-gender").value;

      // 识别gender并转换为服务器所需的格式
      let gender;
      if (genderInput === "男") {
        gender = "1";
      } else if (genderInput === "女") {
        gender = "0";
      } else {
        gender = "2"; // 2 或其他值表示未知或其他
      }

      // 更新用户信息展示
      document.getElementById("profile-user-name").innerText = nickname;
      document.getElementById("profile-real-name").innerText = signature;
      document.getElementById(
        "profile-gender"
      ).innerText = `性别：${genderInput}`;

      // 隐藏编辑表单
      document.querySelector(".edit-form").style.display = "none";

      try {
        // 获取存储的token
        const token = localStorage.getItem("token");
        
        
        if (!token) {
          throw new Error("未找到token，请重新登录");
          return; // 确保代码在token未找到时停止执行
        }

        // 发送数据到服务器
        const response = await fetch(
          "http://103.117.121.141:8080/user/update",
          {
            method: "PUT",
            headers: {
              'Authorization': `Bearer ${token}`, // 在请求头中添加token
              "Content-Type": "application/json",
            },
            body: JSON.stringify({
              nickname: nickname,
              signature:signature,
              gender: gender,
            }),
          }
        );

        // 解析响应
        const data = await response.json();

        // 根据 code 字段处理结果
        if (data.code === 0) {
          alert("更改成功!");
        } else {
          alert("更改出现错误!");
        }
      } catch (error) {
        console.error("错误:", error);
        alert("请求失败: " + error.message);
      }
    });
});
