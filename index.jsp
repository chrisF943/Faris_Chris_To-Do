<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Main Menu</title>
    <style>
        /* Center align the header */
        h2 {
            text-align: center;
        }

        /* Center align the buttons */
        .button-container {
            text-align: center;
            margin-top: 100px; /* Adjust margin as needed */
        }

        .button-container button {
            width: 170px; /* Adjust width as needed */
            height: 40px; /* Adjust height as needed */
        }
    </style>
</head>
<body>
<h2><%= "Welcome! Please select an option." %></h2>
<div class="button-container">
    <form action="result.jsp" method="get" style="display: inline;">
        <button type="submit">I would like to view the list or delete an item</button>
    </form>

    <form action="insert.jsp" method="get" style="display: inline;">
        <button type="submit">I would like to add an item</button>
    </form>
</div>
</body>
</html>