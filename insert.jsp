<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Add an entry to the To-Do list.</title>
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

<h2><%="Please enter the details of your item."%></h2>
<form action="To-Do-List-Servlet" method="post">

    <table align="center">

        <tbody>

        <tr>
            <td>Name of item:</td>
            <td><input type="text" name="item_name" value="" size="50" /></td>
        </tr>

        <tr>
            <td>Description of item:</td>
            <td><input type="text" name="item_description" value="" size="50" /></td>
        </tr>

        <tr>
            <td>Priority of item:</td>
            <td><input type="text" name="item_priority" value="" size="50" /></td>
        </tr>
        </tbody>

    </table>

    <h2>
    <input type="reset" value="Clear" name="clear" />
    <input type="submit" value="Submit" name="submit" />
    </h2>
</form>

<div class="button-container">
    <form action="index.jsp" method="get" style="display: inline;">
        <button type="submit">Take me back to the home menu</button>
    </form>
</div>
</body>
</html>
