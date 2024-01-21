<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Institution List</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>
</head>
<body>
<jsp:include page="header.jsp"/>
<div>
    <h1>List of Institutions</h1>
</div>
<div>
    <c:url value="institution/form">Add New Institution</c:url>
</div>
<div>
    <h2>Available Institutions:</h2>
    <div class="about-us--text">
        <table>
            <c:forEach items="${institutions}" var="institution">
                <tr>
                    <td>Name: ${institution.name}</td>
                </tr>
                <tr>
                    <td>Description: ${institution.description}</td>
                </tr>
                <tr>
                    <td><c:url value="institution/update/${institution.id}">Edit Institution</c:url></td>
                </tr>
                <tr>
                    <td><c:url value="institution/delete/${institution.id}">Delete Institution</c:url></td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>

