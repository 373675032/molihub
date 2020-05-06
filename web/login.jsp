<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/8/3
  Time: 23:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <meta http-equiv="X-UA-Compatible" content="ie=edge"/>
    <title>Molihub-个人博客-程序猿的学习论坛</title>
    <link rel="icon" href="static/images/pagelogo.png" type="image/x-icon"/>
    <link rel="stylesheet" href="static/style/login.css">
    <script src="static/js/jquery-1.12.4.js"></script>
    <script>
        window.onload = function (ev) {
            var tip = document.getElementById("login-error-tip");
            if(tip.innerHTML != ""){
                alert(tip.innerHTML);
            }
        }
    </script>
    <script src="static/js/login.js"></script>
</head>
<body onload="init()">
    <h2 class="name">molihub</h2>
    <div class="nav">
        <div class="show-login-btn">
            登录
        </div>
        <div class="show-register-btn">
            注册
        </div>

    </div>
    <div class="login-box">
        <div class="hide-login-btn">
            +
        </div>
        <form action="login.do" method="POST" class="login-form" onsubmit="return checkLogin()">
            <h1>MoliHub-登录</h1>

            <div class="login-img-box">
                <img class="login_img" src="static/images/user_login.png">
            </div>
            <input id="loginUserName" class="txtb" type="text" name="user_name" placeholder="用户名" autocomplete="off"
                  maxlength="10"  onkeyup="this.value=this.value.replace(/\s+/g,'')"/>
            <div class="login-img-box">
                <img class="login_img" src="static/images/password_login.png">
            </div>
            <input id="loginPassword" class="txtb" type="password" name="password" placeholder="密码"
                   onkeyup="this.value=this.value.replace(/\s+/g,'')"/>
            <input class="login-btn" type="submit" value="登录"/>
        </form>
    </div>
    <div class="register-box">
        <div class="hide-register-btn">
            +
        </div>
        <form id="register-form" action="register.do" method="POST" class="register-form" onsubmit="return checkRegister()">
            <h1>MoliHub-注册</h1>

            <input id="RegisterUserName" class="txtb" autocomplete="off"  type="text" name="user_name" placeholder="用户名"
                   maxlength="10" onkeyup="this.value=this.value.replace(/\s+/g,'')" onblur="isExistUserName()"/>
            <div class="tip-box">
                <img id="userName-img" src="<%=request.getContextPath() %>/static/images/befovalidate.png" alt="找不到图片啦~"
                     title="请输入用户名">
            </div>

            <input id="RegisterPassword1" class="txtb" type="password" name="password" placeholder="密码"
                   onkeyup="this.value=this.value.replace(/\s+/g,'')"/>
            <input id="RegisterPassword2" class="txtb" type="password" name="" placeholder="确认密码"
                   onkeyup="this.value=this.value.replace(/\s+/g,'')"/>

            <input id="RegisterEmail" class="txtb" autocomplete="off"  type="text" name="email" placeholder="邮箱"
                   onkeyup="this.value=this.value.replace(/\s+/g,'')" onblur="isExistEmail()"/>
            <div class="tip-box">
                <img id="email-img" src="<%=request.getContextPath() %>/static/images/befovalidate.png" alt="找不到图片啦~"
                     title="请输入邮箱地址">
            </div>

            <input id="RegisterPhone" class="txtb" autocomplete="off"  type="text" maxlength="11" name="phone"
                   oninput="value=value.replace(/[^\d]/g,'')" placeholder="手机号"
                   onkeyup="this.value=this.value.replace(/\s+/g,'')" onblur="isExistPhone()"/>
            <div class="tip-box">
                <img id="phone-img" src="<%=request.getContextPath() %>/static/images/befovalidate.png" alt="找不到图片啦~"
                     title="请输入手机号码">
            </div>
            <input class="register-btn" type="submit" value="注册"/>
        </form>
    </div>
    <span id="userName-result-span"></span>
    <span id="email-result-span"></span>
    <span id="password-result-span"></span>
    <span id="login-error-tip">${error}</span>
    <script type="text/javascript">
        function hasClass(element, clssname) {
            return element.className.match(new RegExp('(\\s|^)' + clssname + '(\\s|$)'))
        }

        function addClass(element, clssname) {
            if (!this.hasClass(element, clssname)) element.className += ' ' + clssname
        }

        function removeClass(element, clssname) {
            if (hasClass(element, clssname)) {
                var reg = new RegExp('(\\s|^)' + clssname + '(\\s|$)')
                element.className = element.className.replace(reg, ' ')
            }
        }

        function toggleClass(element, clssname) {
            if (hasClass(element, clssname)) {
                removeClass(element, clssname)
            } else {
                addClass(element, clssname)
            }
        }

        var loginBox = document.getElementsByClassName('login-box')
        var showBtn = document.getElementsByClassName('show-login-btn')
        var hideBtn = document.getElementsByClassName('hide-login-btn')
        var RegisterBox = document.getElementsByClassName('register-box')
        var showBtn1 = document.getElementsByClassName('show-register-btn')
        var hideBtn1 = document.getElementsByClassName('hide-register-btn')
        showBtn[0].addEventListener('click', function () {
            toggleClass(loginBox[0], 'showed')
        })
        hideBtn[0].addEventListener('click', function () {
            toggleClass(loginBox[0], 'showed')
        })
        showBtn1[0].addEventListener('click', function () {
            toggleClass(RegisterBox[0], 'showed')
        })
        hideBtn1[0].addEventListener('click', function () {
            toggleClass(RegisterBox[0], 'showed')
        })
    </script>
</body>
</html>
