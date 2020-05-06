<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/8/19
  Time: 17:20
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>建议</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/suggest.css">
    <script src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
    <script>
        function check() {
            var content = $("#message").val();
            if(content == ""){
                alert("请输入建议内容...");
            }else{
                $.ajax({
                    type:"post",
                    url:"suggest.do",
                    data :{
                        message:content,
                    },
                    success:function () {
                        alert("我们已经收到您的建议,感谢您的提出!");
                        $("#message").val("");
                    },
                    error:function (msg) {
                        alert("反馈失败!");
                    }
                });
            }
        }
    </script>
</head>
    <body>
        <div id = "wrap">
            <br><br><br>
            <div id="form-wrap">
                <form action="#" method="post" id="suggest-form">
                    <p>你好 <span>${onlineUser.user_name}</span></p>
                    <textarea name = "message" id="message" value="your message" placeholder="在此处输入你的建议..."></textarea>
                    <input id="btn" type="button" name="submit" value="提交" onclick="check()"/>
                </form>
            </div>
        </div>
    </body>
</html>
