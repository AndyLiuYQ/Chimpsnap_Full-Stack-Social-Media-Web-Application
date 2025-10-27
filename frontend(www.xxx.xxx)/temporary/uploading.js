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

  if (image.size > 10_000_000) {  
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
  const fileUploadInput = document.querySelector('.file-uploader');  

  // Add change event to the file input  
  fileUploadInput.addEventListener('change', upload); // Call upload when a file is selected  

  document.querySelector(".form").addEventListener("submit", async function (e) {  
      e.preventDefault(); // Prevent the default form submission  

      const image = fileUploadInput.files[0];  

      if (!image) {  
          return alert('Please select a file!');  
      }  

      if (!image.type.includes('image')) {  
          return alert('Only images are allowed!');  
      }  

      if (image.size > 10_000_000) {  
          return alert('Maximum upload size is 10MB!');  
      }  

      // Create a FormData object  
      const formData = new FormData();  
      formData.append("file", image);  

      try {  
          // Send a POST request to the API  
          const response = await fetch("http://103.117.121.141:8081/upload", {  
              method: "POST",  
              body: formData,  
              // Do not set Content-Type when using FormData  
          });  

          // Parse the response  
          const resultCode = await response.json();  

          // Handle the result based on the response code  
          if (resultCode === 1) {  
              alert("Upload successful!");  
              window.location.href = "success.html"; // Redirect on success  
          } else if (resultCode === 0) {  
              alert("Upload failed.");  
          } else {  
              alert("Unknown error occurred.");  
          }  
      } catch (error) {  
          alert("Request failed: " + error.message);  
      }  
  });  
});  