<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/8/5
  Time: 10:42
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.bootcss.com/font-awesome/5.8.0/css/all.css" rel="stylesheet" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/MyNav.css">
    <script src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
    <script>
        $(function () {
            $("#user-who").hover(function () {
                $(this).children("table").stop();
                $(this).children("table").fadeToggle(1000,function(){
                })
            },function () {
                $(this).children("table").stop();
                $(this).children("table").fadeToggle(1000,function(){
                })
            });
        })
    </script>
</head>
<body>
<div class="logo-title">
    <span>骐骥一跃，不能十步；驽马十驾，功在不舍。——《荀子》</span>
</div>
<div id="nav-box">
    <ul id="menu">
        <li><a class="menu-title" href="toMain.do">论坛首页</a></li>
        <li><a class="menu-title" href="toPublish.do">发表文章</a></li>
        <li><a class="menu-title" href="toMyHome.do">个人主页</a></li>
        <li><a class="menu-title" href="toArticles.do">文章管理</a></li>
        <li><a class="menu-title" href="toMessages.do" id="message-a">我的消息</a></li>
    </ul>
</div>
</body>
</html>
