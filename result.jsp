<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix = "sql" uri = "http://java.sun.com/jsp/jstl/sql" %>
<html>
<head>
    <title>List Contents and Delete.</title>
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
<h2><%="Below are the current list contents, you may also delete here."%></h2>


<sql:setDataSource var = "snapshot" driver = "com.mysql.jdbc.Driver"
                   url = "jdbc:mysql://localhost:3306/Todo"
                   user = "root"  password = ""/>

<sql:query dataSource = "${snapshot}" var = "result">
    SELECT * from items;
</sql:query>
<form action="Delete-Servlet" method="post">
<table border = "1" width = "100%">
    <tr>
        <th>Item ID</th>
        <th>Item Name</th>
        <th>Item Description</th>
        <th>Item Priority</th>
        <th>Option To Delete</th>
    </tr>

    <c:forEach var = "row" items = "${result.rows}">
        <tr>
            <td><c:out value = "${row.id}"/></td>
            <td><c:out value = "${row.title}"/></td>
            <td><c:out value = "${row.description}"/></td>
            <td><c:out value = "${row.priority}"/></td>
                <td><button type="submit" name=${row.id}>Delete</button></td>
        </tr>
    </c:forEach>

</table>
</form>

<div class="button-container">
    <form action="index.jsp" method="get" style="display: inline;">
        <button type="submit">Take me back to the home menu</button>
    </form>
</div>
</body>
</html>
