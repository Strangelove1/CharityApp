<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: riain
  Date: 15.01.2024
  Time: 16:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<jsp:include page="header.jsp"/>

<h2><c:url value="institution/list">Lista fundacji</c:url></h2>
<br>
<h2><c:url value="/user/listUsers">Lista użytkowników</c:url> </h2>
<br>
<h2><c:url value="/donation/listDonations">Lista donacji</c:url> </h2>
<jsp:include page="footer.jsp"/>
</body>
</html>
