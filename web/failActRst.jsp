<%--
  Created by IntelliJ IDEA.
  User: geyao
  Date: 2016/12/3
  Time: 下午3:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Fail</title>
    <%
        String reasion = request.getAttribute("failReason").toString();
    %>
</head>
<body>
<%if (reasion.equals("actionFail")){ %>
<h2>请求的action不存在</h2>
<%} if (reasion.equals("resultFail")){%>
<h2>请求的result不存在</h2>
<%}%>
</body>
</html>
