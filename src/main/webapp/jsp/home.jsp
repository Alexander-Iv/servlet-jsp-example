<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" errorPage="/jsp/errors/error.jsp" %>
<html>
<head>
    <title>Home</title>
</head>
<body>
<c:if test="${sessionScope.user != null}" >
    <p>Welcome, ${sessionScope.user}!</p>
</c:if>
<div>
    <table>
        <tr>
            <th>
                <%--<form method="post" action="${contextPath}${signin}">
                    <input type="submit" name="signin" value="signin">
                    &lt;%&ndash;<br>&ndash;%&gt;
                </form>--%>
                <c:if test="${sessionScope.user == null}" >
                    <a href="${contextPath}${login}" accesskey="i" name="Login">Login</a>
                </c:if>

                <c:if test="${sessionScope.user != null}" >
                    <a href="${contextPath}${logout}" accesskey="o" name="Logout">Logout</a>
                </c:if>
            </th>
            <th>
                <%--<form method="post" action="${contextPath}${signup}">
                    <input type="submit" name="signup" value="signup">
                    &lt;%&ndash;<br>&ndash;%&gt;
                </form>--%>
                <a href="${contextPath}${registration}" accesskey="r" name="Registration">Registration</a>
            </th>
        </tr>
    </table>
</div>

<c:if test="${sessionScope.user != null}" >
    <h3>All users</h3>
    <table>
        <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Password</th>
        </tr>
        <c:forEach items="${allUsers}" var="user">
            <tr>
                <td>${user.id}</td>
                <td>${user.name}</td>
                <td>${user.password}</td>
            </tr>
        </c:forEach>
    </table>
</c:if>
</body>
</html>
