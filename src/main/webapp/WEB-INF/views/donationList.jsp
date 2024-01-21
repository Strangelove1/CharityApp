<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Donation List</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<h2>Donation List</h2>
<table border="1">
    <thead>
    <tr>
        <th>ID</th>
        <th>Institution</th>
        <th>Categories</th>
        <th>Quantity</th>
        <th>Actions</th>
        <!-- Add more columns as needed -->
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${donations}" var="donation">
        <tr>
            <td>${donation.id}</td>
            <td>${donation.institution.name}</td>
            <td>
                <c:forEach items="${donation.categories}" var="category">
                    ${category.name}
                    <!-- You may format this as needed -->
                </c:forEach>
            </td>
            <td>${donation.quantity}</td>
            <td>
                <a href="<c:url value='/donation/update/${donation.id}'/>">Edit</a>
                <a href="<c:url value='/donation/delete/${donation.id}'/>">Delete</a>
                <!-- Add more actions as needed -->
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<jsp:include page="footer.jsp"/>
</body>
</html>
