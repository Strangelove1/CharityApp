<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%--
  Created by IntelliJ IDEA.
  User: riain
  Date: 20.01.2024
  Time: 16:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h2>Update Donation</h2>

<form:form modelAttribute="donation"  method="post">
    <!-- Hidden input for the donation ID -->
    <form:hidden path="id" />

    <label for="quantity">Quantity:</label>
    <form:input path="quantity" required="required"/>

    <label for="categories">Categories:</label>
    <form:select path="categories" multiple="multiple">
        <form:options items="${categories}" itemValue="id" itemLabel="name" />
    </form:select>

    <label for="institution.id">Institution:</label>
    <form:select path="institution.id">
        <form:options items="${institutions}" itemValue="id" itemLabel="name" />
    </form:select>

    <label for="street">Street:</label>
    <form:input path="street" required="required"/>

    <label for="city">City:</label>
    <form:input path="city" required="required"/>

    <label for="zipCode">ZIP Code:</label>
    <form:input path="zipCode" required="required"/>

    <label for="pickUpDate">Pick Up Date:</label>
    <form:input path="pickUpDate" type="date" required="required"/>

    <label for="pickUpTime">Pick Up Time:</label>
    <form:input path="pickUpTime" type="time" required="required"/>

    <label for="pickUpComment">Pick Up Comment:</label>
    <form:input path="pickUpComment"/>

    <button type="submit">Update Donation</button>
</form:form>
</body>
</html>
