<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: riain
  Date: 15.01.2024
  Time: 15:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Zaloguj się na konto użytkownika</h2>
<form:form modelAttribute="loginForm" method="post">
    <label for="email">Email:</label>
    <form:input path="email" id="email" type="email" required="required"/>

    <label for="password">Password:</label>
    <form:input path="password" id="password" required="required"/>

    <button type="submit">Zaloguj się</button>
</form:form>
</body>
</html>
