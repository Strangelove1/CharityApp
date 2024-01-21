<%--
  Created by IntelliJ IDEA.
  User: riain
  Date: 18.01.2024
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<c:url value="/resources/css/style.css"/>"/>

</head>
<body>
<jsp:include page="header.jsp"/>
<div>
<h1>You must first be logged in order to view your dashboard!</h1>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
