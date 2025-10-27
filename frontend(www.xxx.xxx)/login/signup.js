document.addEventListener("DOMContentLoaded", function () {
  document
    .querySelector(".form")
    .addEventListener("submit", async function (e) {
      e.preventDefault(); // Prevent the default form submission

      // Get the values of the account and password fields
      const account = document.getElementById("nome").value;
      const password = document.getElementById("password").value;
      const confirmPassword = document.getElementById("password2").value;

      // Check if password and confirm password match
      if (password !== confirmPassword) {
        alert("密码不匹配");
        return;
      }

      // Prepare the data to be sent to the API
      const data = new URLSearchParams();
      data.append("username", account);
      data.append("password", password);

      try {
        // Send a POST request to the API
        const response = await fetch(
          "http://103.117.121.141:8080/user/register",
          {
            method: "POST",
            headers: {
              "Content-Type": "application/x-www-form-urlencoded",
            },
            body: data.toString(), // Convert data to URL-encoded format
          }
        );

        // Parse the response
        const result = await response.json();

        // Handle the result based on the code field
        if (result.code === 0) {
          alert("注册成功: " + result.message);
          window.location.href = "login.html";
        } else if (result.code === 1) {
          alert("注册失败: " + result.message);
        } else {
          alert("未知错误: " + result.message);
        }
      } catch (error) {
        alert("请求失败: " + error.message);
      }
    });
});
