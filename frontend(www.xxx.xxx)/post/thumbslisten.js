document.addEventListener('DOMContentLoaded', async () => {
    // 定义按钮和图标
    const checkbox = document.querySelector('.container input');
    const svg = document.querySelector('svg');
    const token = localStorage.getItem("token");
    const postId = String(localStorage.getItem('currentPostId'));

    // 添加调试信息
    console.log("DOM content loaded");

    try {
        // 发送 GET 请求到接口
        const response = await fetch('http://103.117.121.141:8080/likes/iflike', {
            method: "GET",
            headers: {
                "Authorization": `Bearer ${token}`,
                "postsId": `${postId}`
            },
        });

        const result = await response.json();

        console.log('GET response:', result);

        // 根据接口返回的 data 值设置按钮状态
        if (result.code === 0) {
            console.log("有人点赞");
            checkbox.checked = true;
            svg.style.fill = '#2196F3'; // 初始状态亮色
        } else {
            console.log("无人点赞");
            checkbox.checked = false;
            svg.style.fill = '#666'; // 初始状态暗色
        }
    } catch (error) {
        console.error('Error fetching status:', error);
    }

    // 添加点击事件监听器
    checkbox.addEventListener('change', async () => {
        console.log('Checkbox changed:', checkbox.checked);

        if (checkbox.checked) {
            svg.style.fill = '#2196F3'; // 亮色
        } else {
            svg.style.fill = '#666'; // 暗色
        }
        const postsID = localStorage.getItem("currentPostId");

        // 发送 POST 请求到接口

        try {
            const response = await fetch('http://103.117.121.141:8080/likes/like', {
                method: "POST",
                headers: {
                    "Content-Type": "application/json",
                    "Authorization": `Bearer ${token}`,
                    
                },
                body: JSON.stringify({ id:  postsID})
                
            });

            const result = await response.json();
            
            if (result.code===0) {
                console.log('POST request successful:', result);
                
                window.location.reload(); // 刷新页面
            } else {
                console.error('POST request failed:', result);
            }
        } catch (error) {
            console.error('Error posting status:', error);
        }
    });
});
