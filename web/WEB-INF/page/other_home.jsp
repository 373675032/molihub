<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/9/7
  Time: 10:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>${otherUser.user_name}的主页-Molihub</title>
    <link rel="icon" href="<%=request.getContextPath() %>/static/images/pagelogo.png" type="image/x-icon"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plug-ins/jqueryPagination/css/jquery.pagination.css" />
    <script src="<%=request.getContextPath() %>/plug-ins/jqueryPagination/js/jquery.pagination.min.js"></script>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plug-ins/mCustomScrollbar/jquery.mCustomScrollbar.css" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/other_home.css">
    <script src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
    <script src="<%=request.getContextPath() %>/plug-ins/mCustomScrollbar/jquery.mCustomScrollbar.concat.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
    <script src="<%=request.getContextPath() %>/static/js/other_home.js"></script>
    <script type="text/javascript">
        (function($){
            $(window).on("load",function(){
                $(".articles-box").mCustomScrollbar();
            });
        })(jQuery);
    </script>
</head>
<body onload="javaScript:load()">
<div class="main">
    <div class="title-info">
        <a href="">${otherUser.user_name}<span class="other-home"> 的主页</span></a>
    </div>
    <div class="nav-div">
        <ul>
            <li><a href="toMain.do">论坛首页</a></li>
            <li><a href="toMyHome.do">我的主页</a></li>
            <li><a href="toPublish.do">发表文章</a></li>
        </ul>
    </div>
    <div class="main-left">
        <div class="articles-box" data-mcs-theme="inset-3" >
            <ul id="articles">
            </ul>
        </div>
        <div class="page-box">
            <div id="pagination3" class="page fl"></div>
        </div>
        <p id="pageNum"></p>
        <p id="pageNow">1</p>
        <script src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
        <script src="<%=request.getContextPath() %>/plug-ins/jqueryPagination/js/jquery.pagination.min.js"></script>
        <script>
            $(function() {
                $("#pagination3").pagination({
                    currentPage: 1,
                    totalPage: 16,
                    isShow: true,
                    count: 7,
                    homePageText: "首页",
                    endPageText: "尾页",
                    prevPageText: "上一页",
                    nextPageText: "下一页",
                    callback: function(current) {
                        getArticlesByPage(current);
                        window.location.hash = current;
                        document.body.scrollTop = 0;
                        document.documentElement.scrollTop = 0;
                    }
                });
                setOtherPageNum();
                var pageNum  = $("#pageNum").html();
                pageNum++;
                pageNum--;
                var number = window.location.hash.substring(1)||1;
                number++;
                number--;
                $("#pagination3").pagination("setPage",number,pageNum);
                getArticlesByPage(number);
            });
        </script>
    </div>
    <div class="main-right">
        <div class="search-box">
            <h4>共发表: <span id="count"></span>篇博客</h4>
            <input id="search-input" type="text" placeholder="搜索">
            <button onclick="javaScript:search()">找一找</button>
        </div>
        <div class="read-top-box">
            <h4>阅读排行榜</h4>
            <ul id="read-top-ul">
            </ul>
        </div>
        <div class="kind-box">
            <h4>文章分类</h4>
            <ul>
                <li><a href="javaScript:getArticlesByKind('学习笔记')">学习笔记</a></li>
                <li><a href="javaScript:getArticlesByKind('技术博客')">技术博客</a></li>
                <li><a href="javaScript:getArticlesByKind('算法')">算法</a></li>
                <li><a href="javaScript:getArticlesByKind('Bug分析')">Bug分析</a></li>
                <li><a href="javaScript:getArticlesByKind('面试相关')">面试相关</a></li>
                <li><a href="javaScript:getArticlesByKind('项目实战')">项目实战</a></li>
                <li><a href="javaScript:getArticlesByKind('其他')">其他</a></li>
            </ul>
        </div>
        <div class="tag-box">
            <h4>他的个性标签</h4>
            <ul id="tag-ul">
            </ul>
        </div>
    </div>
</div>
</body>
</html>
