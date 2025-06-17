<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Trang chào mừng</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-image: url("welcome.jpg"); /* Thay bằng đường dẫn ảnh của bạn */
            background-size: cover;
            background-position: center;
            height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        
         .top-left-button {
            position: absolute;
            top: 20px;
            left: 20px;
        }

        .top-left-button a {
            padding: 10px 20px;
            background-color: white;
            color: black;
            text-decoration: none;
            font-weight: bold;
            border-radius: 5px;
            box-shadow: 2px 2px 5px rgba(0,0,0,0.4);
        }

        .top-left-button a:hover {
            background-color: #f0f0f0;
        }
    </style>
</head>
<body>
	<div class="top-left-button">
        <a href="login.jsp">← Quay về đăng nhập</a>
    </div>
</body>
</html>
