<%--
  Created by IntelliJ IDEA.
  User: geyao
  Date: 2016/10/30
  Time: 下午4:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Failed</title>
</head>
<body>
<%
    int flag = Integer.parseInt(request.getAttribute("flag").toString());
%>
<script type="text/javascript">
    function success1()
    {
        alert("登陆成功！");
        <%--window.location.href="<%=path %>/index.jsp";--%>
    }
    function failAccount()
    {
        alert("用户名或者密码输入错误");
        window.location.href="log_sign_game/login.jsp";

    }
    function failSQL()
    {
        alert("数据库连接失败，请联系葛尧");
        window.location.href="log_sign_game/login2.jsp";
    }
    <%if(flag==0){%>
    success1();
    <%}else if(flag==1){%>
    failAccount();
    <%}else{%>
    failSQL();
    <%}%>

</script>
</body>
</html>
