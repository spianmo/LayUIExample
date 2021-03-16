<%--
  Created by IntelliJ IDEA.
  User: Finger
  Date: 3/9/2021
  Time: 4:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    out.print(request.getParameterValues("title")[0]);
%>
</body>
</html>
