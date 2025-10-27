document.addEventListener("DOMContentLoaded", function () {
  document
    .querySelector(".form")
    .addEventListener("submit", async function (e) {
      e.preventDefault(); // Prevent the default form submission

      // Get the values of the account and password fields
      const account = document.getElementById("nome").value;
      const password = document.getElementById("password").value;

      // Prepare the data to be sent to the API
      const data = new URLSearchParams();
      data.append("username", account);
      data.append("password", password);

      try {
        // Send a POST request to the API
        const response = await fetch("http://103.117.121.141:8080/user/login", {
          method: "POST",
          headers: {
            'Content-Type': "application/x-www-form-urlencoded",
          },
          body: data.toString(), // Convert data to URL-encoded format
        });

        // Parse the response
        const result = await response.json();

        // Handle the result based on the code field
        if (result.code === 0) {
          alert("登录成功: " + result.message);
          // 存储账号
          // 假设 response.data 包含了你要存储的 token
          localStorage.setItem("token", result.data);

          // 从 localStorage 中获取 token
          const token = localStorage.getItem("token");

          // 显示 token
          // alert("token为: " + token);

          window.location.href = "http://www.chimpsnap.fun";
        } else if (result.code === 1) {
          alert("登录失败: " + result.message);
        } else {
          alert("未知错误: " + result.message);
        }
      } catch (error) {
        alert("请求失败: " + error.message);
      }
    });
});
