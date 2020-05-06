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
    <title>Molihub-文章管理</title>
    <link rel="icon" href="<%=request.getContextPath() %>/static/images/pagelogo.png" type="image/x-icon"/>
    <script src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plug-ins/jqueryPagination/css/jquery.pagination.css" />
    <script src="<%=request.getContextPath() %>/plug-ins/jqueryPagination/js/jquery.pagination.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/simpleAlert.css">
    <script src="<%=request.getContextPath() %>/static/js/simpleAlert.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/my_articles.css">
    <script src="<%=request.getContextPath() %>/static/js/my_articles.js"></script>
</head>
<body onload="init()">
    <div class="nav-div">
        <ul>
            <li><a href="toMain.do">论坛首页</a></li>
            <li><a href="toMyHome.do">个人主页</a></li>
            <li><a href="toPublish.do">发表文章</a></li>
            <li><a href="toTags.do">标签管理</a></li>
        </ul>
    </div>
    <div class="main">
        <table id="articles-table">
            <tr>
                <th>标题</th>
                <th>阅读数</th>
                <th>点赞数</th>
                <th>评论数</th>
                <th>发表时间</th>
                <th>操作</th>
                <th>操作</th>
            </tr>
            <tr>
                <td>新的文章</td>
                <td>12</td>
                <td>2</td>
                <td>3</td>
                <td>2019年7月12日</td>
                <td>编辑</td>
                <td>删除</td>
            </tr>
        </table>
        <div class="page-box">
            <div id="pagination3" class="page fl"></div>
        </div>
        <p id="pageNum">3</p>
        <p id="pageNow">1</p>
        <script src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
        <script src="<%=request.getContextPath() %>/plug-ins/jqueryPagination/js/jquery.pagination.min.js"></script>
        <script>
            $(function() {
                $("#pagination3").pagination({
                    currentPage: 4,
                    totalPage: 16,
                    isShow: true,
                    count: 7,
                    homePageText: "首页",
                    endPageText: "尾页",
                    prevPageText: "上一页",
                    nextPageText: "下一页",
                    callback: function(current) {
                        getManageArticlesByPage(current);
                        window.location.hash = current;
                        document.body.scrollTop = 0;
                        document.documentElement.scrollTop = 0;
                    }
                });
                setMyPageNum();
                var pageNum  = $("#pageNum").html();
                pageNum++;
                pageNum--;
                var number = window.location.hash.substring(1)||1;
                number++;
                number--;
                $("#pagination3").pagination("setPage",number,pageNum);
                getManageArticlesByPage(number);
            });
        </script>
    </div>
</body>
</html>
