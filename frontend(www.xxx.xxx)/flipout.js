// 点击按钮时的处理函数
function handleButtonClick(buttonId) {
    // 从 localStorage 获取 postIds
    const postIds = JSON.parse(localStorage.getItem('postIds')) || [];
    //alert(postIds);
    // 确保 postIds 是一个数组
    if (!Array.isArray(postIds)) {
        console.error('postIds is not an array');
        //alert("is not an array");
    }
    // 检查按钮 ID 是否在数组范围内
    if (buttonId < 1 || buttonId > postIds.length) {
        console.error('Invalid buttonId');
        //alert("invalid button id");
    }
    
    // 获取相应的值
    const postId = postIds[buttonId];
    // 存储到 localStorage 中
    localStorage.setItem('currentPostId', postId);
    
    // 进行页面跳转
    window.location.href = 'http://www.chimpsnap.fun/post/post.html';
}

// 为按钮添加监听器
document.addEventListener('DOMContentLoaded', () => {
    for (let i = 1; i <= 16; i++) {
        const button = document.getElementById(`flip${i}`);
        if (button) {
            button.addEventListener('click', () => handleButtonClick(i));
        }
    }
});
