async function updateLoaders() {
            try {
                // 调用API并获取响应
                console.log("!!!!!");
                const response = await fetch('http://103.117.121.141:8080/datas/hotestposts',{method: "GET",});
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
                    const loader = document.getElementById(`loader${i}`);
                    
                    

                    // 更新歌曲名称
                    loader.querySelector('.name').textContent = data.data[i-1].title;

                    // 更新艺术家名称
                    loader.querySelector('.artist').textContent = data.data[i-1].likes+" likes";

                    // 更新专辑封面图像
                    loader.querySelector('.albumcover img').src = data.data[i-1].coverImg;
                }
            } catch (error) {
                console.error('Error fetching data:', error);
            }
        }

        // 在页面加载完成后执行
document.addEventListener('DOMContentLoaded', () => {
    updateLoaders(); // 初次加载页面时调用一次
    setInterval(updateLoaders, 5000); // 每隔30秒调用一次
});