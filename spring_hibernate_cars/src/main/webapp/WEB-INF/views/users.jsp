<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>

    <title>Список пользователей</title>
</head>
<body>
    <div class="container">
        <h1>Список пользователей</h1>
        <form action="${pageContext.servletContext.contextPath}/users.do" method="post">
            name : <input type="text" name="firstName"><br/>
            <input type="submit"><br/>
        </form>
        <table border="1">
            <tr>
                <td>Имя</td>
            </tr>
            <c:forEach items="${users}" var="user" varStatus="status">
                <tr valign="top">
                    <td>${user.firstName}</td>
                </tr>
            </c:forEach>
        </table>
    </div>
</body>
</html>

