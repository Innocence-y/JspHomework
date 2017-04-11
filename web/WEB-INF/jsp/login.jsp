<%--
  Created by IntelliJ IDEA.
  User: JiYongGuang
  Date: 2017/3/8
  Time: 12:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>YongGuang Ji</title>
    <link rel="stylesheet" type="text/css" href="../css/jyg.css">
    <link rel="stylesheet" type="text/css" href="../css/normalize.css">
    <script src="../js/jyg.js" type="text/javascript"></script>
</head>

<body>

<div id="jyg_news_bg">

    <div id="jyg_news_wrapper">
        <nav>
            <h1>YongGuang ★ Ji</h1>
        </nav>

        <div id="jyg_banner">
            <div id="jyg_pic">
                <img src="../img/front_background.jpg" width="100%" height="100%"/>
            </div>
            <div id="jyg_skip">
                <ul>
                    <li><a href="#">★</a></li>
                    <li><a href="#">文章</a></li>
                    <li><a href="#">漫画</a></li>
                    <li><a href="#">动漫</a></li>
                    <li><a href="#">音乐</a></li>
                    <li><a href="#">游戏</a></li>
                    <li><a href="#">★</a></li>
                </ul>
            </div>
        </div>

        <article id="news_art">
            <!--
                <用户列表></用户列表>       分页查询    用户头像，点击用户头像更改用户信息
                按条件查询的选项框
            -->
        </article>
        <aside id="news_login">
            <h1>Login</h1>
            <br>
            <div id="jyg_info">
                <form name="jyg_form" method="post" action="">
                    <h3 style="margin: 0px">Username</h3>
                    <input class="info" type="text" name="Username" id="user_login"
                           style="margin: 0px 0px 15px 0px"><br>
                    <h3 style="margin: 0px">Password</h3>
                    <input class="info" type="password" name="password" style="margin: 0px 0px 0px 0px"><br>
                    <input class="button" type="submit" name="submit" value="确认" onclick="check()"
                           style="margin: 20px 0px">
                    <input class="button" type="submit" name="register" value="注册"
                           style="margin: 20px 0px" >
                </form>
            </div>
        </aside>
    </div>

</div>

</body>
</html>