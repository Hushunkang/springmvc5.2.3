<%--
  Created by IntelliJ IDEA.
  User: Hushunkang
  Date: 2020/3/25
  Time: 13:30
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
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
    <a href="requestMappingTest/testRequestMethod">testRequestMethod</a><br/>
    <a href="requestMappingTest/testParamsAndHeaders?userId=1&age=18">testParamsAndHeaders</a><br/>
    <a href="requestMappingTest/testAntResourceUrl/xyz/abc">testAntResourceUrl</a><br/>
    <a href="requestMappingTest/testPathVariable/1">testPathVariable</a><br/>
    <a href="requestMappingTest/testRest/1">testRest get请求</a><br/>
    <form action="requestMappingTest/testRest" method="post">
        <input type="submit" value="testRest post请求"/>
    </form>
    <form action="requestMappingTest/testRest/1" method="post">
        <input type="hidden" name="_method" value="DELETE">
        <input type="submit" value="testRest delete请求"/>
    </form>
    <form action="requestMappingTest/testRest/1" method="post">
        <input type="hidden" name="_method" value="PUT">
        <input type="submit" value="testRest put请求"/>
    </form>
    <a href="requestMappingTest/testRequestParam?userId=1&age=10">testRequestParam</a><br/>
    <a href="requestMappingTest/testRequestHeader">testRequestHeader</a><br/>
    <a href="requestMappingTest/testCookieValue">testCookieValue</a><br/>
    <form action="requestMappingTest/testPojo" method="post">
        username: <input type="text" name="username"/>
        <br/>
        password: <input type="password" name="password"/>
        <br/>
        age: <input type="text" name="age"/>
        <br/>
        email: <input type="text" name="email"/>
        <br/>
        province: <input type="text" name="address.province"/>
        <br/>
        city: <input type="text" name="address.city"/>
        <br/>
        <%-- address.province和address.city是级联属性，即属性的属性 --%>
        <input type="submit" value="Submit"/>
    </form>
    <a href="requestMappingTest/testServletApi">testServletApi</a><br/>
    <a href="requestMappingTest/testCookieValue">testCookieValue</a><br/>
    <a href="requestMappingTest/testCookieValue">testCookieValue</a><br/>
  </body>
</html>
