// File upload function  
function upload() {  
  const fileUploadInput = document.querySelector('.file-uploader');  
  const image = fileUploadInput.files[0];  

  // Check if a file is selected  
  if (!image) {  
      return alert('Please select a file!');  
  }  

  if (!image.type.includes('image')) {  
      return alert('Only images are allowed!');  
  }  

  if (image.size > 10000000) {  
      return alert('Maximum upload size is 10MB!');  
  }  

  const fileReader = new FileReader();  
  fileReader.readAsDataURL(image);  

  fileReader.onload = (fileReaderEvent) => {  
      const profilePicture = document.querySelector('.profile-picture');  
      profilePicture.style.backgroundImage = `url(${fileReaderEvent.target.result})`;  
      imageDataURL = fileReaderEvent.target.result; // Make sure imageDataURL is defined in the correct scope  
  }  
}  

document.addEventListener("DOMContentLoaded", function () {  
     try {
    // 获取存储的token (假设token已经存储在localStorage中)
    const token = localStorage.getItem("token");
    if (!token) {
      // 如果未找到token，直接跳转到登录页面
      alert("请先登录或注册！");
      window.location.href = "http://www.chimpsnap.fun/login/login.html";
      return; // 确保代码停止执行
    }
     }
    catch(error){}
  const fileUploadInput = document.querySelector('.file-uploader');  

  // Add change event to the file input  
  fileUploadInput.addEventListener('change', upload); // Call upload when a file is selected  

  document.querySelector(".form").addEventListener("submit", async function (e) {  
      e.preventDefault(); // Prevent the default form submission  
      const title = document.getElementById("email").value;
      const content = document.getElementById("textarea").value;

      const image = fileUploadInput.files[0];  
      

      if (!image) {  
          return alert('Please select a file!');  
      }  

      if (!image.type.includes('image')) {  
          return alert('Only images are allowed!');  
      }  

      if (image.size > 10000000) {  
          return alert('Maximum upload size is 10MB!');  
      }  

      // Create a FormData object  
      const formData = new FormData();  
      formData.append("file", image);  
      formData.append("title",title);
      formData.append("content",content);
      const token = localStorage.getItem("token");

      try {  
          // Send a POST request to the API  
          const response = await fetch("http://103.117.121.141:8080/posts/upload", {  
              method: "POST",  
              body: formData,
              headers:{
                  "Authorization": `Bearer ${token}`,
              },
              // Do not set Content-Type when using FormData  
          });  

          // Parse the response  
          const resultCode = await response.json();  

          // Handle the result based on the response code  
          if (resultCode.code === 0) {  
              alert("Upload successful!");  
              window.location.href = "http://www.chimpsnap.fun"; // Redirect on success  
          } else if (resultCode.code === 1) {  
              alert("Upload failed.");  
          } else {  
              alert("Unknown error occurred.");  
          }  
      } catch (error) {  
          alert("Request failed: " + error.message);  
      }  
  });  
});  