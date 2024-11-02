<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Assign Shelf to Room</title>
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
    <h1>Assign Shelf to Room</h1>
    <form action="assignShelfToRoom" method="post">
        <label for="shelfId">Enter Shelf ID:</label>
        <input type="text" id="shelfId" name="shelfId" required placeholder="Enter Shelf ID"><br><br>

        <label for="roomId">Enter Room ID:</label>
        <input type="text" id="roomId" name="roomId" required placeholder="Enter Room ID"><br><br>

        <button type="submit">Assign Shelf to Room</button>
    </form>
    </div>
    
     <!-- Scripts -->
    <script src="js/sidebar.js"></script>
    <script src="js/header.js"></script>
</body>
</html>
