<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/8/20
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="icon" href="<%=request.getContextPath() %>/static/images/pagelogo.png" type="image/x-icon"/>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/update_info.css">
    <script src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
    <script src="<%=request.getContextPath() %>/static/js/update_info.js"></script>
</head>
<body onload="init()">
<%@ include file="basepage/nav.jsp" %>
<div class="main">
    <div class="update-info">
        <p>修改资料</p>
        <div class="update">
            <span class="text">您的用户名:</span><input id="input-userName" type="text" placeholder="请输入用户名">
        </div>
        <div class="update">
            <span class="text">您的年龄:</span><input id="input-age" type="text" placeholder="请输入年龄"
            oninput = "value=value.replace(/[^\d]/g,'')" maxlength="3">
        </div>
        <div class="update">
            <span class="text">您的性别:</span>
            <label for="man">
                男<input id="man" name="sex" class="input-sex" type="radio" value="男"/>
            </label>
            <label for="woman">
                女<input id="woman" name="sex" class="input-sex" type="radio" value="女"/>
            </label>
            <label for="secret">
                保密<input id="secret" name="sex" class="input-sex" type="radio" value="保密"/>
            </label>
        </div>
        <div class="update">
            <span class="text">手机号:</span><input id="input-phone" type="text" maxlength="11"
                 oninput="value=value.replace(/[^\d]/g,'')" placeholder="请输入手机号"
                 onkeyup="this.value=this.value.replace(/\s+/g,'')">
        </div>
        <div class="update">
            <span class="text">邮箱:</span><input id="input-email" type="text"
            οnkeydοwn="if(event.keyCode==32||event.keyCode==13){return false;}" placeholder="请输入邮箱地址">
        </div>
        <div class="operate">
            <button><a href="toUpdateInfo.do">取消修改</a></button>
            <button onclick="updateInfo()">完成修改</button>
        </div>
    </div>
    <div class="update-password">
        <p>修改密码</p>
        <div class="update">
            <span class="text">原始密码:</span>
            <input id="old-password" type="password" οnkeydοwn="if(event.keyCode==32||event.keyCode==13){return false;}">
        </div>
        <div class="update">
            <span class="text">新密码:</span>
            <input id="new-password1" type="password" οnkeydοwn="if(event.keyCode==32||event.keyCode==13){return false;}">
        </div>
        <div class="update">
            <span class="text">确认新密码:</span>
            <input id="new-password2" type="password" οnkeydοwn="if(event.keyCode==32||event.keyCode==13){return false;}">
        </div>
        <div class="operate">
            <button onclick="updatePassword()">确认修改</button>
        </div>
    </div>
</div>
<div class="info-hidden">
    用户名:<span id="hidden-userName">${onlineUser.user_name}</span>
    手机号:<span id="hidden-phone">${onlineUser.phone}</span>
    邮箱地址:<span id="hidden-email">${onlineUser.email}</span>
    密码:<span id="hidden-password">${onlineUser.password}</span>
    性别:<span id="hidden-sex">${onlineUser.sex}</span>
    年龄:<span id="hidden-age">${onlineUser.age}</span>
    用户名已存在 <span id="tip1"></span>
    手机号已存在 <span id="tip2"></span>
    邮箱已存在   <span id="tip3"></span>
</div>
</body>
</html>
