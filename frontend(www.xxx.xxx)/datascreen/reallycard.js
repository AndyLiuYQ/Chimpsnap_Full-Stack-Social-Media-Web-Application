async function fetchData() {
        try {
            const response = await fetch("http://103.117.121.141:8080/datas/totalposts", {  
              method: "GET",  
          });  
          
          

          // Parse the response  
             
            const data = await response.json();
            console.log(data);

            // Assuming data contains "github", "code", and "earn" keys
            document.getElementById('github').setAttribute('data-text', "帖子总数: "+data.data[0]);
            document.getElementById('code').setAttribute('data-text', "点赞总数: "+data.data[1]);
            document.getElementById('earn').setAttribute('data-text', "评论总数: "+data.data[2]);
        } catch (error) {
            console.error('Error fetching data:', error);
        }
    }


    
document.addEventListener('DOMContentLoaded', () => {
    fetchData(); // 初次加载页面时调用一次
    setInterval(fetchData, 5000); // 每隔5秒调用一次
});