<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/8/6
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Molihub-发帖</title>
    <link rel="icon" href="<%=request.getContextPath() %>/static/images/pagelogo.png" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plug-ins/EditorMD/lib/codemirror/codemirror.min.css" />
    <script type="text/javascript" src="<%=request.getContextPath() %>/plug-ins/JQuery/jquery-1.12.4.js"></script>
    <script type="text/javascript" src="<%=request.getContextPath() %>/plug-ins/EditorMD/editormd.amd.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/plug-ins/EditorMD/css/editormd.css" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/publish.css">
    <script src="<%=request.getContextPath() %>/static/js/publish.js"></script>
</head>
<body onload="init()">
    <div class="title-box">
        <a href="toMain.do"><img src="<%=request.getContextPath() %>/static/images/logo1.png" title="欢迎来到Molihub!"></a>
    </div>
    <div class="nav-div">
        <ul>
            <li><a href="toMain.do">论坛首页</a></li>
            <li><a href="toMyHome.do">个人主页</a></li>
            <li><a href="toArticles.do">文章管理</a></li>
            <li><a href="toTags.do">标签管理</a></li>
            <li><span>注意:直接在内容里面编写HTML标签时,标签前后都要加上`</span></li>
        </ul>
    </div>
    <div class="main">
        <form action="publish.do" method="post" onsubmit="return checkSubmit()">
            <div class="title">
                <input autocomplete="off" id="input-title" type="text"
                       name="article_title" maxlength="200" placeholder="在此处添写文章标题" >
            </div>
            <div class="content">
                <div id="editormd">
                    <textarea id="input-content" name="article_content"  style="display:none;" placeholder="在此处填写文章正文"></textarea>
                </div>
            </div>
            <div class="kind">
                <p>文章分类:</p>
                <ul>
                    <li class="kind-li">
                        <label><span>学习笔记</span>
                        <input type="radio" name="kind" value="1" checked="checked"></label>
                    </li>
                    <li class="kind-li">
                        <label><span>技术博客</span>
                            <input type="radio" name="kind" value="2"></label>
                    </li>
                    <li class="kind-li">
                        <label><span>算法</span>
                            <input type="radio" name="kind" value="3"></label>
                    </li>
                    <li class="kind-li">
                        <label><span>Bug分析</span>
                            <input type="radio" name="kind" value="4"></label>
                    </li>
                    <li class="kind-li">
                        <label><span>面试相关</span>
                            <input type="radio" name="kind" value="5"></label>
                    </li>
                    <li class="kind-li">
                        <label>
                        <span>项目实战</span>
                        <input type="radio" name="kind" value="6">
                        </label>
                    </li>
                    <li class="kind-li">
                        <label>
                        <span>其他</span>
                        <input type="radio" name="kind" value="7">
                        </label>
                    </li>
                </ul>
            </div>
            <div class="label">
                <p>个性标签: <span>(个人中心可添加个性文章标签)</span></p>
                <ul id="my_tag">
                    <li class="label-li" id="default-label">
                        <span>无</span>
                        <input type="checkbox" name="blog_labels" value="" checked="checked">
                    </li>
                </ul>
            </div>
            <div class="doSubmit">
                <input type="submit" value="发表">
                <label>
                <span>私密文章:<input id="isPrivate" type="checkbox" name="is_private"></span>
                </label>
            </div>
        </form>
    </div>
    <script type="text/javascript">
        $(function() {
            var editor = editormd("editormd", {
                path: 'plug-ins/EditorMD/lib/',
                saveHTMLToTextarea : true,//注意3：这个配置，方便post提交表单
                /**上传图片相关配置如下*/
                imageUpload : true,
                imageFormats : ["jpg", "jpeg", "gif", "png", "bmp", "webp"],
                imageUploadURL : "uploadImg.do",//注意你后端的上传图片服务地址
            });

            /*
            // or
            var editor = editormd({
                id   : "editormd",
                path : "../lib/"
            });
            */
        });
    </script>
</body>
</html>
