<%--
  Created by IntelliJ IDEA.
  User: Hushunkang
  Date: 2020/3/25
  Time: 13:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>success</title>
</head>
<body>
    <h1>success page.</h1>
    datetime:${requestScope.datetime}<br/>
    names:${requestScope.names}<br/>
    request user:${requestScope.user}<br/>
    session user:${sessionScope.user}<br/>
    request school:${requestScope.school}<br/>
    session school:${sessionScope.school}<br/>
</body>
</html>
