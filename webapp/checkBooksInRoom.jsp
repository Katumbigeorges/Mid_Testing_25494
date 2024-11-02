<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Check Books in Room</title>
 <link rel="stylesheet" href="css/styles.css">
      <link rel="stylesheet" href="css/sidebar.css">
</head>
<body>

<!-- Sidebar -->
    <div id="sidebar"></div>

    <!-- Main Content -->
    <div class="main-content">
        <!-- Header -->
        <header id="header"></header>
    <h1>Check Books in Room</h1>
    <form action="CheckBooksInRoomServlet" method="post">
        <label for="roomId">Enter Room ID:</label>
        <input type="text" id="roomId" name="roomId" required placeholder="Enter Room ID"><br><br>
        <button type="submit">Check Books</button>
    </form>
    </div>

    <% 
        // Display the book count if available in the request attribute
        Long bookCount = (Long) request.getAttribute("bookCount");
        if (bookCount != null) {
    %>
        <p>Total books in room: <%= bookCount %></p>
    <% 
        } else if (request.getAttribute("error") != null) { 
    %>
        <p style="color:red;"><%= request.getAttribute("error") %></p>
    <% 
        } 
    %>
    
      <script src="js/sidebar.js"></script>
    <script src="js/header.js"></script>
</body>
</html>
