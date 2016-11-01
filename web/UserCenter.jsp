<%@ page import="DAO.AcountDAO.VO.User" %><%--
  Created by IntelliJ IDEA.
  User: geyao
  Date: 2016/11/1
  TimeImpl: 下午9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        User user = (User) request.getAttribute("user");
        String email = user.getEmail();
        String nickedName = user.getNickedName();
        int id = user.getId();
    %>
    <title>SuperLiar</title>
</head>
<body >
<center>
    <h2>
        <img src="pic/logo.jpg" height="300" width="560"><br>
    <%=nickedName%><br>
        你  好
    </h2>
    <h1>
        <%=id%><br>
    </h1>
    <h3>
        这是你的id，他将永远被保存<br>
        感谢你对我的支持<br>
        我会在网页上线以后第一时间通知你<br>
        还有很多工作要做<br>
        美工，图片，网页，以及后端等所有等一切<br>
        如果你有好的想法<br>
        gy2016@mail.ustc.edu.cn<br>
    </h3>

</center>
</body>
</html>
