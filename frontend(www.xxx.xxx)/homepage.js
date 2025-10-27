document.addEventListener("DOMContentLoaded", async function () {
  try {
    // Fetch data for all cards from the server
    const response = await fetch("http://103.117.121.141:8080/posts/homepage", {
      method: "GET",
      headers: {
        "Content-Type": "application/json",
      },
    });

    if (!response.ok) {
      throw new Error("从服务器获取图片失败.");
    }

    const cardsData = await response.json();

    // Store post_id by num_id in an object
    //const postIds = {}; // 确保在使用之前声明 postIds
    // 初始化 postIds 数组
    let postIds = []; // 或者使用对象: let postIds = {};
    
    // 将 postIds 数组存储到 localStorage


    cardsData.forEach((card) => {
      const createTime = card.create_time.substring(0, 10);
      
      // 将 post_id 存储在 postIds 对象中
      postIds[card.num_id] = card.post_id; 

      // 创建时间与点赞数量连接
      const footerText = `${createTime} | ${card.likes} likes`;
      
      // 根据 num_id 更新页面元素
      document.getElementById(`back-img-${card.num_id}`).src = card.cover_img;
      document.getElementById(`front-img-${card.num_id}`).src = card.user_pic;
      document.getElementById(`badge-${card.num_id}`).innerText = card.nickname;
      document.getElementById(`title-${card.num_id}`).innerText = card.title;
      document.getElementById(`footer-${card.num_id}`).innerText = footerText;

      // Debugging output to ensure num_id is correct
      console.log("获取成功: " + card.num_id);
    });

    // 在这里你可以使用 postIds 对象，因为它已经在上面定义了
    console.log("Post IDs:", postIds); 
    localStorage.setItem('postIds', JSON.stringify(postIds));

  } catch (error) {
    console.error("获取失败:", error);
  }
});
