<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: riain
  Date: 18.01.2024
  Time: 13:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>
<h2>Update User Information</h2>
<br>
<form:form modelAttribute="user" method="POST">
    <!-- Hidden input for user ID or unique identifier -->
    <form:hidden path="id"/>

    <label for="name">Name:</label>
    <form:input path="name" id="name" required="required" value="${user.name}"/>

    <label for="lastName">Last Name:</label>
    <form:input path="lastName" id="lastName" required="required" value="${user.lastName}"/>

    <label for="email">Email:</label>
    <form:input path="email" id="email" type="email" required="required" value="${user.email}"/>


    <button type="submit">Update Information</button>
</form:form>

<jsp:include page="footer.jsp"/>
</body>
</html>
