<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: riain
  Date: 21.01.2024
  Time: 15:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Institution Form</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<h2>Institution Registration Form</h2>
<br>
<form:form modelAttribute="institution" method="POST">
    <label for="name">Name:</label>
    <form:input path="name" id="name" required="required"/>

    <label for="description">Description:</label>
    <form:textarea path="description" id="description" required="required"/>

    <button type="submit">Register</button>
</form:form>

<jsp:include page="footer.jsp"/>
</body>
</html>
