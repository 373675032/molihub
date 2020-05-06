<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/8/31
  Time: 14:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Molihub-个性标签</title>
    <link rel="icon" href="<%=request.getContextPath() %>/static/images/pagelogo.png" type="image/x-icon"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/add_tag.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/simpleAlert.css">
    <script src="<%=request.getContextPath() %>/static/js/simpleAlert.js"></script>
    <script src="<%=request.getContextPath() %>/static/js/add_tag.js"></script>
    <script src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
    <script>
        function deleteTag(a){
            var dblChoseAlert = simpleAlert({
                "title":"操作",
                "content":"确认删除这个标签吗?",
                "buttons":{
                    "确定":function () {
                        deleteMyTag(a);
                        dblChoseAlert.close();
                    },
                    "取消":function () {
                        dblChoseAlert.close();
                    }
                }
            })
        }
    </script>
</head>
<body onload="init()">
    <div class="nav-div">
        <ul>
            <li><a href="toMain.do">论坛首页</a></li>
            <li><a href="toMyHome.do">个人主页</a></li>
            <li><a href="toPublish.do">发表文章</a></li>
            <li><a href="toArticles.do">文章管理</a></li>
        </ul>
    </div>
    <div class="main">
        <table  id="tags-table">
            <tr>
                <th>标签名称</th>
                <th>所含文章数</th>
                <th>操作</th>
                <th>操作</th>
            </tr>
        </table>
        <script>
            $(function () {
                //删除
                $(".three").click(function () {
                    var dblChoseAlert = simpleAlert({
                        "title":"操作",
                        "content":"确认删除这个标签吗?",
                        "buttons":{
                            "确定":function () {
                                alert("删除成功!");
                                dblChoseAlert.close();
                            },
                            "取消":function () {
                                dblChoseAlert.close();
                            }
                        }
                    })

                })
            })
        </script>
        <p>添加标签</p>
        <input id="add-tag-input" type="text" maxlength="20">
        <button onclick="javaScript:addTag()">添加</button>
    </div>
</body>
</html>
