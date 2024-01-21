<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>User List</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<h2>User List</h2>
<table border="1">
<thead>
<tr>
    <th>ID</th>
    <th>Name</th>
    <th>Email</th>
    <th>Edit</th>
    <th>Delete</th>
</tr>
</thead>
<tbody>
<c:forEach items="${users}" var="user">
    <tr>
        <td>${user.id}</td>
        <td>${user.name}</td>
        <td>${user.email}</td>
        <td><a href="<c:url value='/user/updateUser/${user.id}'/>">Edit</a></td>
        <td><a href="<c:url value='/user/deleteUser/${user.id}'/>">Delete</a></td>
    </tr>
</c:forEach>
</tbody>
</table>
<jsp:include page="footer.jsp"/>
</body>
</html>