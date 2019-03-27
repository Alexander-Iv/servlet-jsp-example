<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.03.2019
  Time: 13:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>
<h3>Registration</h3>
<form method="post" action="${contextPath}${registration}">
    <table>
        <tr>
            <th align="left">User Name</th>
            <td><input type="text" id="name" name="name"></td>
        </tr>
        <tr>
            <th align="left">Password</th>
            <td><input type="password" id="password" name="password"></td>
            <th align="left">Confirm</th>
            <td><input type="password" id="confirm" name="confirm"></td>
        </tr>
        <tr>
            <th></th>
            <th align="left"><input type="submit" value="Register"></th>
        </tr>
    </table>
</form>
<jsp:include page="footer.jsp"/>
</body>
</html>
