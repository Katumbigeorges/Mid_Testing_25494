<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Location</title>
</head>
<body>
	<h1>Create Location</h1>
	<form action="LocationServlet" method="post">
		<label for="locationCode">Location Code:</label> <input type="text"
			id="locationCode" name="locationCode" required><br> <br>
		<label for="locationName">Location Name:</label> <input type="text"
			id="locationName" name="locationName" required><br> <br>
		<label for="locationType">Location Type:</label> <select
			id="locationType" name="locationType" required>
			<option value="PROVINCE">Province</option>
			<option value="DISTRICT">District</option>
			<option value="SECTOR">Sector</option>
			<option value="CELL">Cell</option>
			<option value="VILLAGE">Village</option>
		</select><br> <br> <label for="parentId">Parent Location ID
			(if applicable):</label> <input type="text" id="parentId" name="parentId"><br>
		<br> <input type="submit" value="Create Location">
	</form>
</body>
</html>