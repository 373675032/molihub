<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/8/15
  Time: 22:17
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>${article.article_title} - ${article.user_name} - Molihub</title>

    <link rel="icon" href="<%=request.getContextPath() %>/static/images/pagelogo.png" type="image/x-icon"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plug-ins/EditorMD/css/editormd.preview.css" />
    <script src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
    <script src="<%=request.getContextPath() %>/plug-ins/EditorMD/lib/marked.min.js"></script>
    <script src="<%=request.getContextPath() %>/plug-ins/EditorMD/lib/prettify.min.js"></script>
    <script src="<%=request.getContextPath() %>/plug-ins/EditorMD/lib/raphael.min.js"></script>
    <script src="<%=request.getContextPath() %>/plug-ins/EditorMD/lib/underscore.min.js"></script>
    <script src="<%=request.getContextPath() %>/plug-ins/EditorMD/lib/sequence-diagram.min.js"></script>
    <script src="<%=request.getContextPath() %>/plug-ins/EditorMD/lib/flowchart.min.js"></script>
    <script src="<%=request.getContextPath() %>/plug-ins/EditorMD/lib/jquery.flowchart.min.js"></script>
    <script src="<%=request.getContextPath() %>/plug-ins/EditorMD/editormd.amd.min.js"></script>

    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/lib/OwlCarousel/assets/owl.carousel.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/lib/OwlCarousel/assets/owl.theme.default.min.css" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/read.css">
    <script src="<%=request.getContextPath() %>/static/js/read.js"></script>
    <script>
        $(function () {
            $(window).scroll(function () {
                //获得滑轮滚动的距离
                if($(window).scrollTop() >= 300){
                    $("#return-top").show(500);
                }else{
                    $("#return-top").hide(500);
                }
            });
        })
    </script>
    <script>
        var _h = 0;
        function SetH(o) {
            _h = o.scrollTop
            SetCookie("a", _h)
        }
        window.onload = function () {
            document.getElementById("x").scrollTop = GetCookie("a");//页面加载时设置scrolltop高度
        }
        function SetCookie(sName, sValue) {
            document.cookie = sName + "=" + escape(sValue) + "; ";
        }
        function GetCookie(sName) {
            var aCookie = document.cookie.split("; ");
            for (var i = 0; i < aCookie.length; i++) {
                var aCrumb = aCookie[i].split("=");
                if (sName == aCrumb[0])
                    return unescape(aCrumb[1]);
            }
            return 0;
        }
    </script>
</head>
<body onload="init()"  onscroll="SetH(this)">
<div class="nav-div" id="top">
    <ul>
        <li><a href="toMain.do">论坛首页</a></li>
        <li><a href="toMyHome.do">个人主页</a></li>
        <li><a href="toPublish.do">发表文章</a></li>
        <li><a href="toArticles.do">文章管理</a></li>
        <li><a href="toTags.do">标签管理</a></li>
    </ul>
</div>
<div class="main-read">
    <div class="article">
        <div class="title">
            <img src= "<%=request.getContextPath() %>/static/images/article_title.png">
            ${article.article_title}
        </div>
        <div class="content">
            <div id="doc-content">
                <textarea style="display:none;" placeholder="markdown语言">${article.article_content}</textarea>
            </div>
            <script type="text/javascript">
                var testEditor;
                $(function () {
                    testEditor = editormd.markdownToHTML("doc-content", {//注意：这里是上面DIV的id
                        htmlDecode      : "style,script,iframe", // you can filter tags decode
                        emoji           : true,
                        taskList        : true,
                        tex             : true,  // 默认不解析
                        flowChart       : true,  // 默认不解析
                        sequenceDiagram : true,  // 默认不解析
                    });
                });
            </script>
        </div>
    </div>
    <div class="other" id="like">
        <img src= "<%=request.getContextPath() %>/static/images/article_kind.png" title="类别">
        <a id="article_kind" href="toOtherHome.do?user_name=${article.user_name}">${article.article_kind}</a>
        <img src= "<%=request.getContextPath() %>/static/images/article_tag.png" title="文章标签">
        <a id="article_tag" href="toOtherHome.do?user_name=${article.user_name}">${article.article_tag}</a>
        <span><a href="javaScript:doLike()"><img id="like-img"  src="<%=request.getContextPath() %>/static/images/unlike.png" title="点个赞吧~~"></a></span>
        (<span id="like_count">${article.like_count}</span>)
        <span id="like_status">未点赞</span>
    </div>
    <div class="article_info">
        posted @ <span>${article.time_to_show} </span> <a id="user_name" href="toOtherHome.do?user_name=${article.user_name}">${article.user_name}</a>
        阅读(<span>${article.read_count}</span>) 评论(<span>${article.comment_count}</span>)&emsp;
    </div>
    <div class="comments">
        <p class="comment-title">评论</p>
        <ul id="comments-ul">
        </ul>
        <p class="comment-title" id="sent-comment">发表评论</p>
        <div class="add-comment">
            <textarea class="comment-input"></textarea>
            <button onclick="addComment()">提交</button>
            <a href="toMain.do">退出</a>
            <a href="javaScript:reload()">刷新页面</a>
        </div>
    </div>
</div>
<div id="info-space">
    <p id="article_id">${article.article_id} </p>
    <p id="userName">${onlineUser.user_name}</p>
    <p id="path"><%=request.getContextPath() %></p>
</div>
<a id="return-top" href="#"><img title="回到顶部" src="<%=request.getContextPath() %>/static/images/return_top_read.png"></a>
</body>
</html>
