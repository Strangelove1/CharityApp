<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update Institution Form</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<h2>Update Institution</h2>
<br>
<form:form modelAttribute="institution" method="POST">
    <input type="hidden" name="id" value="${institution.id}"/>

    <label for="name">Name:</label>
    <form:input path="name" id="name" required="required" value="${institution.name}"/>

    <label for="description">Description:</label>
    <form:textarea path="description" id="description" required="required">${institution.description}</form:textarea>

    <button type="submit">Update</button>
</form:form>

<jsp:include page="footer.jsp"/>
</body>
</html>