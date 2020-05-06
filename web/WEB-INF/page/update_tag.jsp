<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/9/1
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Molihub-重命名标签</title>
    <link rel="icon" href="<%=request.getContextPath() %>/static/images/pagelogo.png" type="image/x-icon"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/add_tag.css">
    <script src="<%=request.getContextPath() %>/static/js/add_tag.js"></script>
    <script src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
</head>
<body>
    <div class="nav-div">
        <ul>
            <li><a href="toMain.do">论坛首页</a></li>
            <li><a href="toMyHome.do">个人主页</a></li>
            <li><a href="toPublish.do">发表文章</a></li>
            <li><a href="toArticles.do">文章管理</a></li>
            <li><a href="toTags.do">标签管理</a></li>
        </ul>
    </div>
    <div class="main-update">
        <p>修改标签:${tagName}</p>
        <input id="update-input" type="text" maxlength="20">
        <button onclick="updateTag('${tagName}')">修改</button>
    </div>
    <p id="path"><%=request.getContextPath() %></p>
</body>
</html>
