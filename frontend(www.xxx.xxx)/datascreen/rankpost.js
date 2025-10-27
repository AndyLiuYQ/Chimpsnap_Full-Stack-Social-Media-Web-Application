async function updateLoaders2() {
            try {
                // 调用API并获取响应
                console.log("我是帅哥");
                const response = await fetch('http://103.117.121.141:8080/datas/hotestusers',{method: "GET",});
                const data = await response.json();

                // 假设API返回的结构如下：
                // {
                //   "loaders": [
                //     {"name": "Song1", "artist": "Artist1", "img": "url1"},
                //     {"name": "Song2", "artist": "Artist2", "img": "url2"},
                //     ...
                //   ]
                // }
                console.log(data);

                for (let i = 1; i <= 5; i++) {
                    const loader = document.getElementById(`loaderp${i}`);
                    
                    

                    // 更新歌曲名称
                    loader.querySelector('.name').textContent = data.data[i-1].nickname;

                    // 更新艺术家名称
                    loader.querySelector('.artist').textContent = data.data[i-1].followers+" followers";

                    // 更新专辑封面图像
                    loader.querySelector('.albumcover img').src = data.data[i-1].userPic;
                }
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        }

        // 在页面加载完成后执行
document.addEventListener('DOMContentLoaded', () => {
    updateLoaders2(); // 初次加载页面时调用一次
    setInterval(updateLoaders2, 5000); // 每隔30秒调用一次
});