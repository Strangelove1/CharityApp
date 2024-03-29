<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: riain
  Date: 15.01.2024
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>

</head>
<body>
<jsp:include page="header.jsp"/>
<h2>Formularz rejestracji użytkownika</h2>
<br>
<form:form modelAttribute="user" method="POST">
    <label for="name">Name:</label>
    <form:input path="name" id="name" required="required"/>

    <label for="lastName">Last Name:</label>
    <form:input path="lastName" id="lastName" required="required"/>

    <label for="email">Email:</label>
    <form:input path="email" id="email" type="email" required="required"/>

    <label for="password">Password:</label>
    <form:input path="password" id="password" type="password" required="required"/>

    <label for="confirmPassword">Confirm Password:</label>
    <input type="password" id="confirmPassword" name="confirmPassword" required="required"/>

    <button type="submit">Register</button>
</form:form>

<jsp:include page="footer.jsp"/>
</body>
</html>
