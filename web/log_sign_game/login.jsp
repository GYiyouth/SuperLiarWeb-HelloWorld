<%--
  Created by IntelliJ IDEA.
  User: geyao
  Date: 2016/10/29
  Time: 下午11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>

    <title>SuperLiar 登录</title>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta http-equiv="content-type" content="text/html; charset=utf-8"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <link href="./resources/css/jquery-ui-themes.css" type="text/css" rel="stylesheet"/>
    <link href="./resources/css/axure_rp_page.css" type="text/css" rel="stylesheet"/>
    <link href="./data/styles.css" type="text/css" rel="stylesheet"/>
    <link href="./files/login/styles.css" type="text/css" rel="stylesheet"/>
    <script src="./resources/scripts/jquery-1.7.1.min.js"></script>
    <script src="./resources/scripts/jquery-ui-1.8.10.custom.min.js"></script>
    <script src="./resources/scripts/axure/axQuery.js"></script>
    <script src="./resources/scripts/axure/globals.js"></script>
    <script src="./resources/scripts/axutils.js"></script>
    <script src="./resources/scripts/axure/annotation.js"></script>
    <script src="./resources/scripts/axure/axQuery.std.js"></script>
    <script src="./resources/scripts/axure/doc.js"></script>
    <script src="./data/document.js"></script>
    <script src="./resources/scripts/messagecenter.js"></script>
    <script src="./resources/scripts/axure/events.js"></script>
    <script src="./resources/scripts/axure/recording.js"></script>
    <script src="./resources/scripts/axure/action.js"></script>
    <script src="./resources/scripts/axure/expr.js"></script>
    <script src="./resources/scripts/axure/geometry.js"></script>
    <script src="./resources/scripts/axure/flyout.js"></script>
    <script src="./resources/scripts/axure/ie.js"></script>
    <script src="./resources/scripts/axure/model.js"></script>
    <script src="./resources/scripts/axure/repeater.js"></script>
    <script src="./resources/scripts/axure/sto.js"></script>
    <script src="./resources/scripts/axure/utils.temp.js"></script>
    <script src="./resources/scripts/axure/variables.js"></script>
    <script src="./resources/scripts/axure/drag.js"></script>
    <script src="./resources/scripts/axure/move.js"></script>
    <script src="./resources/scripts/axure/visibility.js"></script>
    <script src="./resources/scripts/axure/style.js"></script>
    <script src="./resources/scripts/axure/adaptive.js"></script>
    <script src="./resources/scripts/axure/tree.js"></script>
    <script src="./resources/scripts/axure/init.temp.js"></script>
    <script src="./files/login/data.js"></script>
    <script src="./resources/scripts/axure/legacy.js"></script>
    <script src="./resources/scripts/axure/viewer.js"></script>
    <script src="./resources/scripts/axure/math.js"></script>
    <script type="./text/javascript">
        $axure.utils.getTransparentGifPath = function() { return './resources/images/transparent.gif'; };
        $axure.utils.getOtherPath = function() { return './resources/Other.html'; };
        $axure.utils.getReloadPath = function() { return './resources/reload.html'; };
    </script>
</head>
<body>
<div id="base" class="">

    <!-- logo (Image) -->
    <div id="u295" class="ax_default image" data-label="logo">
        <img id="u295_img" class="img " src="./images/login/logo_u295.jpg"/>
        <!-- Unnamed () -->
        <div id="u296" class="text" style="display:none; visibility: hidden">
            <p><span></span></p>
        </div>
    </div>

    <!-- logInBoxes (Group) -->
    <div id="u297" class="ax_default" data-label="logInBoxes" data-width="240" data-height="100">




<%--输入的表单在这！！！名字是 email passWord--%>

    <form method="post" action="../Servlet.LogIn" name="login">
        <!-- userNameBox (Group) -->
        <div id="u302" class="ax_default" data-label="userNameBox" data-width="240" data-height="34">

            <!-- Unnamed (Rectangle) -->
            <div id="u303" class="ax_default label">
                <img id="u303_img" class="img " src="./images/login/u299.png"/>
                <!-- Unnamed () -->
                <div id="u304" class="text" style="display:none; visibility: hidden">
                    <p><span></span></p>
                </div>
            </div>

            <!-- Email (Text Field) -->
            <div id="u305" class="ax_default text_field">
                <input id="u305_input" type="email" value="" maxlength="32" name="email"/>
            </div>
        </div>
        <!-- passWordBox (Group) -->
        <div id="u298" class="ax_default" data-label="passWordBox" data-width="240" data-height="34">

            <!-- Unnamed (Rectangle) -->
            <div id="u299" class="ax_default label">
                <img id="u299_img" class="img " src="./images/login/u299.png"/>
                <!-- Unnamed () -->
                <div id="u300" class="text" style="display:none; visibility: hidden">
                    <p><span></span></p>
                </div>
            </div>
            <!-- Unnamed (Text Field) -->
            <div id="u301" class="ax_default text_field">

                    <input id="u301_input" type="password" value="" maxlength="32" name="passWord"/>

            </div>
        </div>


    </div>
    <button type="submit" >
    <!-- goLog (Rectangle) -->
    <div id="u306" class="ax_default primary_button" data-label="goLog">

        <div id="u306_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u307" class="text">
            <p><span>登录</span></p>
        </div>
    </div>
    </button>
    </form>





    <!-- goSign (Rectangle) -->
    <div id="u308" class="ax_default primary_button" data-label="goSign">
        <div id="u308_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u309" class="text">
            <p><span>注册</span></p>
        </div>
    </div>

    <!-- Unnamed (Rectangle) -->
    <div id="u310" class="ax_default link_button">
        <div id="u310_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u311" class="text">
            <p><span>游客游玩</span></p>
        </div>
    </div>

    <!-- Unnamed (Rectangle) -->
    <div id="u312" class="ax_default link_button">
        <div id="u312_div" class=""></div>
        <!-- Unnamed () -->
        <div id="u313" class="text">
            <p><span>忘记密码</span></p>
        </div>
    </div>

    <!-- gySign (Group) -->
    <div id="u314" class="ax_default" data-label="gySign" data-width="510" data-height="76">

        <!-- Unnamed (Horizontal Line) -->
        <div id="u315" class="ax_default line disabled">
            <img id="u315_img" class="img " src="./images/gaming/u36.png"/>
            <!-- Unnamed () -->
            <div id="u316" class="text" style="display:none; visibility: hidden">
                <p><span></span></p>
            </div>
        </div>

        <!-- Unnamed (Rectangle) -->
        <div id="u317" class="ax_default heading_3">
            <div id="u317_div" class=""></div>
            <!-- Unnamed () -->
            <div id="u318" class="text">
                <p><span>SuperLiar</span></p><p><span>gy2016@mail.ustc.edu.cn</span></p>
            </div>
        </div>
    </div>
</div>
</body>
</html>
