<%--
  Created by IntelliJ IDEA.
  User: User
  Date: 18.03.2019
  Time: 11:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
<h3>Login</h3>
<div id="signin-form">
    <form method="post" action="${contextPath}${login}">
        <table>
            <tr>
                <th align="left">User Name</th>
                <td><input type="text" id="name" name="name"></td>
            </tr>
            <tr>
                <th align="left">Password</th>
                <td><input type="password" id="password" name="password"></td>
            </tr>
            <tr>
                <th></th>
                <th align="left"><input type="submit" value="Login"></th>
            </tr>
        </table>
    </form>
    If you are new user, please <a href="${contextPath}${registration}">register</a>.
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
