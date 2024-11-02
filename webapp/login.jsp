<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
            margin: 0;
            background-color: #f4f4f9;
        }
        .container {
            display: flex;
            width: 100vw;
            height: 100vh;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
            overflow: hidden;
        }
        .image-section {
            flex: 1;
            background: url('https://sis.auca.ac.rw/assets/images/background/main_resized.jpg') no-repeat center center;
            background-size: cover;
        }
        .form-section {
            flex: 1;
            padding: 40px;
            background-color: #fff;
        }
        .form-section h2 {
            color: #333;
            font-size: 24px;
            margin-bottom: 20px;
        }
        .form-section label {
            display: block;
            color: #555;
            font-weight: bold;
            margin-bottom: 5px;
        }
        .form-section input, .form-section select, .form-section button {
            width: 100%;
            padding: 10px;
            margin-bottom: 15px;
            border-radius: 4px;
            border: 1px solid #ddd;
        }
        .form-section button {
            background-color: #4CAF50;
            color: #fff;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        .form-section button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <!-- Left side image section -->
        <div class="image-section"></div>

        <!-- Right side form section -->
         <div class="form-section">
        <h2>Login to AUCA Library System</h2>
        <form action="LoginServlet" method="post">
            <label for="username">Username:</label>
            <input type="text" id="username" name="username" required>
            
            <label for="password">Password:</label>
            <input type="password" id="password" name="password" required>
            
            <button type="submit">Login</button>
        </form>

        <% String loginError = (String) request.getAttribute("loginError");
           if (loginError != null) { %>
            <div class="error"><%= loginError %></div>
        <% } %>
    </div>
    </div>
</body>
</html>
