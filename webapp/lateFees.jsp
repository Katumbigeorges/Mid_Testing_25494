<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Late Fees</title>
    <style>
        table { width: 80%; margin: auto; border-collapse: collapse; }
        th, td { padding: 10px; border: 1px solid #ccc; text-align: left; }
        th { background-color: #f2f2f2; }
    </style>
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
    <h2 style="text-align: center;">Overdue Books and Late Fees</h2>
    <table>
        <tr>
            <th>Borrower ID</th>
            <th>Book Title</th>
            <th>Due Date</th>
            <th>Return Date</th>
            <th>Overdue Days</th>
            <th>Late Fee</th>
        </tr>
        <c:forEach var="borrower" items="${overdueBorrowers}">
            <tr>
                <td>${borrower.id}</td>
                <td>${borrower.book.title}</td>
                <td>${borrower.dueDate}</td>
                <td>${borrower.returnDate}</td>
                <td>${borrower.overdueDays}</td>
                <td>${borrower.lateChargesFees}</td>
            </tr>
        </c:forEach>
    </table>
    </div>
      <script src="js/sidebar.js"></script>
    <script src="js/header.js"></script>
</body>
</html>
