<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/8/20
  Time: 20:15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="icon" href="<%=request.getContextPath() %>/static/images/pagelogo.png" type="image/x-icon"/>
    <title>莫提网盘</title>
    <style type="text/css">
        iframe{
            width: 100%;
            height: 100%;
        }
        #return{
            top: 20px;
            left: 20px;
            position: absolute;
            line-height: 1.5;
            color: #fff;
            font-weight: bold;
            text-decoration: none;
        }
        #return:hover{
            color: red;
            text-decoration: underline;
        }
    </style>
</head>
<body>
<a id="return" href="toMain.do">返回论坛首页</a>
<iframe src="http://moti.work/FileStore">
</iframe>
</body>
</html>
