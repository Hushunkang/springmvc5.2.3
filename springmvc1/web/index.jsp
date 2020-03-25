<%--
  Created by IntelliJ IDEA.
  User: Hushunkang
  Date: 2020/3/25
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>spring mvc hello world</title>
  </head>
  <body>
    <a href="helloWorld/hello">hello,world.</a><br/>
    <a href="requestMappingTest/testRequestMapping">testRequestMapping</a><br/>
<%--    <a href="requestMappingTest/testRequestMethod">testRequestMethod</a><br/>--%><%-- 超链接默认的请求类型是get请求 --%>
    <form action="requestMappingTest/testRequestMethod" method="post">
      <input type="submit" value="submit"/>
    </form>
    <a href="#">testRequestMethod</a><br/>
  </body>
</html>
