<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/9/3
  Time: 20:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Molihub-反馈</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/my_articles.css">
    <link rel="icon" href="<%=request.getContextPath() %>/static/images/pagelogo.png" type="image/x-icon"/>
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
        <%@ include file="basepage/suggest.jsp" %>
</body>
</html>
