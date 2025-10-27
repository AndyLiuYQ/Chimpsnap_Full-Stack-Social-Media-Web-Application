function updateCard() {
    fetch('http://103.117.121.141:8080/datas/newestuser', {
        method: "GET",
    })
    .then(response => response.json())
    .then(responseData => {
        // 确保获取到正确的 data 部分
        const data = responseData.data;

        console.log(data);

        // 更新用户头像（假设 data.userPic 包含图片的 URL）
        const userHead = document.getElementById('userhead');
        if (data.userPic) {
            // 确保 userHead 内部只有一个 img 元素
            let img = userHead.querySelector('img');
            if (!img) {
                img = document.createElement('img');
                img.setAttribute('alt', 'User Picture');
                img.setAttribute('style', 'width:100%;height:100%;border-radius:50%;');
                userHead.appendChild(img);
            }
            img.setAttribute('src', data.userPic);
        } else {
            // 如果没有 userPic，恢复为默认的 SVG 图标
            userHead.innerHTML = `<svg viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg"><path d="M12,4A4,4 0 0,1 16,8A4,4 0 0,1 12,12A4,4 0 0,1 8,8A4,4 0 0,1 12,4M12,14C16.42,14 20,15.79 20,18V20H4V18C4,15.79 7.58,14 12,14Z"></path></svg>`;
        }

        // 更新用户名
        const username = document.getElementById('username');
        if (data.username) {
            username.textContent = data.username;
        }

        // 更新用户昵称
        const userNickname = document.getElementById('usernickname');
        if (data.nickname) {
            userNickname.textContent = data.nickname;
        }

        // 更新签名
        const signature = document.getElementById('signature');
        if (data.signature) {
            if(data.signature=="0")
            {
                signature.textContent = "| 你好 | Hello | こんにちは | Bonjour | Hallo | नमस्ते | Hola | 안녕하세요 | བདེ བ"
            }else{
            signature.textContent = data.signature;
            }
        }
    })
    .catch(error => {
        console.error('Error fetching data:', error);
    });
}

// 每5秒调用一次 updateCard 函数
setInterval(updateCard, 5000);
