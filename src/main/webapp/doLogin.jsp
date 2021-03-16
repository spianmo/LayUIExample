<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: Finger
  Date: 3/16/2021
  Time: 2:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%
    String username = request.getParameter("account");
    String password = request.getParameter("password");
    Class.forName("com.mysql.cj.jdbc.Driver");
    try {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/library?characterEncoding=utf-8&serverTimeZone=UTC"
                ,"Kirito66"
                ,"123456");
        String sql = "select * from borrow_card where username = ?";
        try(PreparedStatement preparedStatement = conn.prepareStatement(sql)){
            preparedStatement.setString(1,username);
            try(ResultSet rs = preparedStatement.executeQuery()){
                while(rs.next()){
                    if (password.equals(rs.getString("password"))){
                        response.sendRedirect("./main.jsp");
                    }else{
%>
    <jsp:forward page="index.jsp"></jsp:forward>
<%
                    }
                }
            }
        }
    } catch (SQLException throwables) {
        throwables.printStackTrace();
    }
%>
</body>
</html>
