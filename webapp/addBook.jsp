<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Shelf" %>
<%@ page import="java.util.List" %>
<html>
<head>
    <title>Add Book</title>
    
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
        
    <h1>Add a New Book</h1>
    <form action="addBook" method="post">
        <label for="title">Title:</label>
        <input type="text" id="title" name="title" required><br>

        <label for="publicationName">Publication Name:</label>
        <input type="text" id="publicationName" name="publicationName" required><br>

        <label for="isbnCode">ISBN Code:</label>
        <input type="text" id="isbnCode" name="isbnCode" required><br>

        <label for="edition">Edition:</label>
        <input type="number" id="edition" name="edition" required><br>

        <label for="shelfId">Shelf ID:</label>
        <input type="text" id="shelfId" name="shelfId" required placeholder="Enter Shelf ID"><br>

        <button type="submit">Add Book</button>
    </form>
    
     <!-- Scripts -->
    <script src="js/sidebar.js"></script>
    <script src="js/header.js"></script>
</body>
</html>
