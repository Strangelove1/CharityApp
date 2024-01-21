<%--
  Created by IntelliJ IDEA.
  User: riain
  Date: 15.01.2024
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%><%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>

</head>
<body>
<jsp:include page="header.jsp"/>
<div>
<h1>Witaj na stronie użytkownika!</h1>
</div>
<div>
    <c:url value="user/updateUser">Zaktualizuj dane użytkownika</c:url>
</div>
<div>
    <h2>Moje donacje:</h2>
    <div class="about-us--text">
    <table>
        <c:forEach items="${donations}" var="donation">
            <tr>
                <td>Dla: ${donation.institution.name} w ${donation.city} ${donation.street} ${donation.zipCode}</td>
            </tr>
            <%-- Sprawdzić czy kategorie dobrze działają --%>
            <tr>
                <td>Ile: ${donation.quantity} worków <c:forEach items="${donation.categories}" var="category"> ${category.name}, </c:forEach></td>
            </tr>
            <tr>
                <td>Kiedy: ${donation.pickUpDate} o ${donation.pickUpTime}</td>
            </tr>
            <tr>
                <td>Komentarz: ${donation.pickUpComment}</td>
            </tr>
            <tr>
                <td><c:url value="donation/update/${donation.id}">Edytuj dane donacji</c:url> </td>
            </tr>
        </c:forEach>
    </table>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
