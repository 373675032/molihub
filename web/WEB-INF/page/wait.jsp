<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/8/7
  Time: 11:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" dir="ltr">
<head>
    <meta charset="utf-8">
    <title>Molihub-等待激活</title>
    <link rel="icon" href="<%=request.getContextPath() %>/static/images/pagelogo.png" type="image/x-icon"/>
    <style>
        body{
            margin: 0;
            padding: 0;
            font-family: "montserrat",sans-serif;
            background: #f39c12;
        }
        .middle{
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
        }
        .middle h4{
            font-size: 60px;
            text-transform: uppercase;
            font-weight: 900;
            color: #2c3e5036;
            background-image: url(<%=request.getContextPath() %>/static/images/water.png);
            -webkit-background-clip:text;
            animation: water 15s infinite;
        }
        @keyframes water {
            from{
                background-position: left 0 top 0;
            }
            to{
                background-position: left 1000px top 0;
            }
        }
    </style>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/MyNav1.css">
    <script src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
    <script>
        function reloadPage() {
            window.document.location.reload();
        }
        window.onload = function () {
            alert("我们已经向你的邮箱发送的激活邮件,激活之后就可以登录啦~");
        }
        function changeEmail() {
            var newEmail = $("#newEmail").val();
            if(!isQualifiedEmail(newEmail)){
                alert("邮箱不符合规范!");
            }else{
                $.ajax({
                    type:"GET",
                    url:"updateEmail.do",
                    async: false,
                    contentType: "application/json; charset=utf-8",
                    dataType: "text",
                    data:{
                        email:newEmail
                    },
                    success:function (result) {
                        if(result === "yes"){
                            alert("修改成功!");
                            window.document.location.reload();
                        }else if(result === "no"){
                            alert("修改失败,这个邮箱已经被人用过了哦!");
                        }
                    },
                    error:function (msg) {
                        console.log("请求失败");
                    }
                });
            }
        }
        /**
         * 正则验证是否是合格邮箱
         * @param password
         */
        function isQualifiedEmail(email) {
            var myreg=new RegExp(/^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/);
            if (!myreg.test(email)) {
                return false;
            } else {
                return true;
            }
        }
    </script>
</head>
<body>

<div class="middle">
    <h4>请在你的邮箱中激活账号...</h4>
</div>
<div class="sidebar">
    <img class="btn" src="<%=request.getContextPath() %>/static/images/open_list.png" title="展开管理栏">
    <ul>
        <li><a class="send" href="<%=request.getContextPath() %>/login.jsp">返回登录页面</a></li>
        <li><a class="send" href="javaScript:reloadPage()">刷新页面</a></li>
        <li><a>修改邮箱地址</a></li>
        <li>
            <input id="newEmail" type="text" name="newEmail" placeholder="新邮箱地址" autocomplete="off">
        </li>
        <li><button onclick="changeEmail()">修改</button></li>
    </ul>
    <span id="list_status">isClosed</span>
</div>
<script type="text/javascript">
    $(".btn").on("click",function(){
        $(".sidebar").toggleClass("side");
        if($("#list_status").html() == "isClosed"){
            $('.btn').attr('src','<%=request.getContextPath() %>/static/images/close_list.png');
            $('.btn').attr('title','关闭管理栏');

            $("#list_status").html("isOpened");
        }else{
            $('.btn').attr('src','<%=request.getContextPath() %>/static/images/open_list.png');
            $('.btn').attr('title','展开管理栏');
            $("#list_status").html("isClosed");
        }
    });
</script>
</body>
</html>
