<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
    <title>Find Location by Phone Number</title>
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
            width: 400px;
            padding: 20px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            border-radius: 8px;
            background-color: #fff;
        }
        h2 {
            color: #333;
            font-size: 24px;
            margin-bottom: 20px;
            text-align: center;
        }
        label, p {
            color: #555;
            font-weight: bold;
            margin-bottom: 10px;
        }
        input[type="text"], button {
            width: 100%;
            padding: 10px;
            margin-top: 10px;
            margin-bottom: 15px;
            border-radius: 4px;
            border: 1px solid #ddd;
        }
        button {
            background-color: #4CAF50;
            color: #fff;
            border: none;
            cursor: pointer;
            font-size: 16px;
        }
        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
    <div class="container">
        <h2>Find Location by Phone Number</h2>
        <form action="getLocation" method="POST">
            <label for="phoneNumber">Enter Phone Number:</label>
            <input type="text" id="phoneNumber" name="phoneNumber" required>
            <button type="submit">Get Location</button>
        </form>
        
        <%-- Display location if available --%>
        <p>
            <%
                String location = (String) request.getAttribute("location");
                if (location != null) {
                    out.print("Location: " + location);
                }
            %>
        </p>
    </div>
</body>
</html>
