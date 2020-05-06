<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/9/2
  Time: 14:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Molihub-我的消息</title>
    <link rel="icon" href="<%=request.getContextPath() %>/static/images/pagelogo.png" type="image/x-icon"/>
    <script src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plug-ins/jqueryPagination/css/jquery.pagination.css" />
    <script src="<%=request.getContextPath() %>/plug-ins/jqueryPagination/js/jquery.pagination.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/simpleAlert.css">
    <script src="<%=request.getContextPath() %>/static/js/simpleAlert.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/messages.css">
    <script src="<%=request.getContextPath() %>/static/js/messages.js"></script>
</head>
<body onload="init()">
    <div class="nav-div">
        <ul>
            <li><a href="toMain.do">论坛首页</a></li>
            <li><a href="toMyHome.do">个人主页</a></li>
            <li><a href="toPublish.do">发表文章</a></li>
            <li><a href="toArticles.do">文章管理</a></li>
            <li><a href="toTags.do">标签管理</a></li>
        </ul>
    </div>
    <div class="main">
        <h3>新的消息 <button onclick="readAll()">设为已读</button></h3>
        <div class="no-read">
            <ul id="no-read-ul">
            </ul>
        </div>
        <div class="hr"></div>
        <h3>已读消息 <button onclick="deleteAll()">清空</button></h3>
        <div class="read">
            <ul id="read-ul">
            </ul>
        </div>
    </div>
    <p id="path"><%=request.getContextPath() %></p>
</body>
</html>
