<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Auca Library System</title>
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
        
        <!-- Form Section -->
        <h1>Create Location</h1>
        
        <% 
        String message = (String) request.getAttribute("message");
        if (message != null) {
            String cssClass = message.contains("Error") ? "error" : "success";
        %>
            <div class="<%= cssClass %>"><%= message %></div>
        <% } %>
        
        <%
        String modalMessage = (String) request.getAttribute("modalMessage");
        // Debug output to check message
        System.out.println("Modal Message: " + modalMessage);
        %>

        <!-- Modal Structure -->
        <div id="successModal" class="modal" style="display:none;">
            <div class="modal-content">
                <span class="close" onclick="closeModal()">&times;</span>
                <p id="modalMessage"></p>
            </div>
        </div>

        <form action="LocationServlet" method="post" onsubmit="return validateForm()">
            <div class="form-group">
                <label for="locationCode">Location Code:</label>
                <input type="text" id="locationCode" name="locationCode" required>
            </div>
            
            <div class="form-group">
                <label for="locationName">Location Name:</label>
                <input type="text" id="locationName" name="locationName" required>
            </div>
            
            <div class="form-group">
                <label for="locationType">Location Type:</label>
                <select id="locationType" name="locationType" required>
                    <option value="">Select Type</option>
                    <option value="PROVINCE">Province</option>
                    <option value="DISTRICT">District</option>
                    <option value="SECTOR">Sector</option>
                    <option value="CELL">Cell</option>
                    <option value="VILLAGE">Village</option>
                </select>
            </div>
            
            <div class="form-group">
                <label for="parentId">Parent Location ID:</label>
                <input type="text" id="parentId" name="parentId">
            </div>
            
            <div class="form-group">
                <input type="submit" value="Create Location">
            </div>
        </form>
    </div>

    <!-- Scripts -->
    <script src="js/sidebar.js"></script>
    <script src="js/header.js"></script>
    <script src="js/formValidation.js"></script>
    <script>
        function openModal(message) {
            document.getElementById('modalMessage').innerText = message;
            document.getElementById('successModal').style.display = "block";
        }

        function closeModal() {
            document.getElementById('successModal').style.display = "none";
        }

        document.addEventListener("DOMContentLoaded", function() {
            <% if (modalMessage != null && !modalMessage.isEmpty()) { %>
                openModal("<%= modalMessage %>");
            <% } %>
        });
    </script>
</body>
</html>
