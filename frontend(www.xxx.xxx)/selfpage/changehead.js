document.addEventListener("DOMContentLoaded", function () {
    const changeImageBtn = document.getElementById('changeImageBtn');
    const fileInput = document.getElementById('fileInput');
    const profileImage = document.getElementById('profileImage');

    // 点击按钮时触发文件输入的点击事件
    changeImageBtn.addEventListener('click', function() {
        fileInput.click();
    });

    // 当选择文件时，更新预览并上传文件
    fileInput.addEventListener('change', async function(event) {
        const file = event.target.files[0];
        if (file) {
            // 更新图片预览
            const reader = new FileReader();
            reader.onload = function(e) {
                profileImage.src = e.target.result; // 更新图片预览
            };
            reader.readAsDataURL(file);

            // 检查文件类型和大小
            if (!file.type.includes('image')) {
                return alert('Only images are allowed!');
            }

            if (file.size > 10_000_000) {
                return alert('Maximum upload size is 10MB!');
            }

            // 创建 FormData 对象
            const formData = new FormData();
            formData.append("file", file);

            const token = localStorage.getItem("token");
            if (!token) {
                alert("请先登录或注册！");
                window.location.href = "http://www.chimpsnap.fun/login/login.html";
                return;
            }

            try {
                // 发送 POST 请求
                const response = await fetch("http://103.117.121.141:8080/user/updateAvatar", {
                    method: "PATCH",
                    body: formData,
                    headers: {
                        "Authorization": `Bearer ${token}`,
                    }
                });

                // 解析响应
                const resultCode = await response.json();

                // 根据响应结果处理
                if (resultCode.code === 0) {
                    alert("Upload successful!");
                    onUploadSuccess(); // 调用上传成功后的函数
                } else if (resultCode.code === 1) {
                    alert("Upload failed.");
                } else {
                    alert("Unknown error occurred.");
                }
            } catch (error) {
                alert("Request failed: " + error.message);
            }
        }
    });

    // 上传成功后的函数
    function onUploadSuccess() {
        // 这里放置你希望在上传成功后执行的代码
        console.log("Upload Avatar was successful!");
        // 你可以在这里做其他操作，例如重定向、更新UI等
        
    }
});
