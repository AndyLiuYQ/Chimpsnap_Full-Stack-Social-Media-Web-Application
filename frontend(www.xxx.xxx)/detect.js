document.addEventListener("DOMContentLoaded", function() {
    if (window.innerWidth <= 1500) { // 768px 是一个常见的手机屏幕宽度阈值
        window.location.href = "mobile.html"; // 将 "mobile.html" 替换为你的移动版本的文件名
    }
});
