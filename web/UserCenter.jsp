<%@ page import="DAO.AcountDAO.VO.UserBean" %>
<%@ page import="java.util.Map" %><%--
  Created by IntelliJ IDEA.
  UserBean: geyao
  Date: 2016/11/1
  TimeImpl: 下午9:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        Map<String, String[]> map = (Map<String, String[]>) request.getAttribute("map");
        String userName = map.get("userName")[0].toString();

//        UserBean userBean = (UserBean) request.getAttribute("userBean");
//        String email = userBean.getEmail();
//        String nickedName = userBean.getNickedName();
//        int id = userBean.getId();
    %>
    <title>SuperLiar</title>
</head>
<body >
<center>
    <h2>
        <img src="${pageContext.request.contextPath}/pic/logo.jpg" height="300" width="560"><br>
    <%=userName%><br>
        你  好
    </h2>
    <h1>
        <%=userName%><br>
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
