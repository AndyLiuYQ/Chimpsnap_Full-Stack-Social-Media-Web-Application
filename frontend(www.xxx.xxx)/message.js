document.addEventListener("DOMContentLoaded", function() {
    const messageButton = document.getElementById("messageButton");
    const messageDropdown = document.getElementById("messageDropdown");
    const messageList = document.getElementById("messageList");

    const messages = [
     "你没有新消息"
    ];

    messageButton.addEventListener("click", function() {
        messageDropdown.style.display = messageDropdown.style.display === "block" ? "none" : "block";
        messageList.innerHTML = "";  // Clear the existing messages

        messages.forEach(function(message) {
            const listItem = document.createElement("li");
            listItem.textContent = message;
            messageList.appendChild(listItem);
        });
    });

    document.addEventListener("click", function(event) {
        if (!messageButton.contains(event.target) && !messageDropdown.contains(event.target)) {
            messageDropdown.style.display = "none";
        }
    });
});


document.getElementById('messageButton').addEventListener('click', async function() {
    // Get the token from local storage
    const token = localStorage.getItem('token');
    if (!token) {
        // If token is not found, redirect to the login page
        alert("请先登录或注册！");
        window.location.href = "http://www.chimpsnap.fun/login/login.html";
        return; // Ensure the code stops executing
    }

    try {
        // Send GET request to the server and wait for the response
        const getResponse = await fetch('http://103.117.121.141:8080/messages/messagesInfo', {
            method: 'GET',
            headers: {
                'Authorization': `Bearer ${token}`
            }
        });

        // Check if the response is successful
        if (getResponse.ok) {
            const responseData = await getResponse.json();
            const messages = responseData.data;
            // Update the message list
            const messageList = document.getElementById('messageList');
            messageList.innerHTML = ''; // Clear existing messages

            messages.forEach(message => {
                const listItem = document.createElement('li');
                if (message.likes == 0) {
                    listItem.textContent = "👍 " + message.userNickname + " 给你点了个赞";
                } else {
                    listItem.textContent = "💭 " + message.userNickname + " 给你评论了：" + message.comments;
                }
                messageList.appendChild(listItem);
            });
        } else {
            console.error('Failed to fetch messages:', getResponse.statusText);
        }

        // Send an empty POST request
        const postResponse = await fetch('http://103.117.121.141:8080/messages/deleteMessages', {
            method: 'POST',
            headers: {
                'Authorization': `Bearer ${token}`,
                'Content-Type': 'application/json'
            },
        });

        if (!postResponse.ok) {
            console.error('Failed to send POST request:', postResponse.statusText);
        }

    } catch (error) {
        console.error('Error during fetch operations:', error);
    }
});

