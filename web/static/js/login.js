
function init() {
    if(/Android|webOS|iPhone|iPod|BlackBerry/i.test(navigator.userAgent)) {
        alert("Molihub暂不支持手机访问,请使用电脑访问bia~");
        window.location.href = "http://moti.work:8080";
    } else {

    }
}
/**
 * 登录检查
 * @returns {boolean}
 */
function checkLogin(){
    //获得节点对象
    var userName = document.getElementById("loginUserName");
    var password = document.getElementById("loginPassword");
    if(userName.value == ""||password.value == ""){
        if(userName.value == ""){
            //将输入框边框设为红色
            userName.classList.add("error");
            //将提示内容设为红色
            userName.classList.add("errorTip");
            //修改提示内容
            userName.setAttribute("placeholder","请填写用户名");
        }
        if (password.value == "") {
            //将输入框边框设为红色
            password.classList.add("error");
            //将提示内容设为红色
            password.classList.add("errorTip");
            //修改提示内容
            password.setAttribute("placeholder","请填写密码");
        }
        //取消表单默认提交
        return false;
    }
    if(!isQualifiedName(userName)){
        //将输入框边框设为红色
        userName.classList.add("error");
        //将提示内容设为红色
        userName.classList.add("errorTip");
        //清空内容
        userName.value = "";
        //修改提示内容
        userName.setAttribute("placeholder","1-6位的汉字、英文、数字、下划线");
        return false;
    }
}
/**
 * 注册检查
 */
function checkRegister(){
    var result = true;
    var userName = document.getElementById("RegisterUserName");
    var password1 = document.getElementById("RegisterPassword1");
    var password2 = document.getElementById("RegisterPassword2");
    var email = document.getElementById("RegisterEmail");
    var phone = document.getElementById("RegisterPhone");
    if(userName.value == ""){
        result = false;
        //将输入框边框设为红色
        userName.classList.add("error");
        //将提示内容设为红色
        userName.classList.add("errorTip");
        //修改提示内容
        userName.setAttribute("placeholder","请填写用户名");
    }else if(!isQualifiedName(userName)){
        result = false;
        //将输入框边框设为红色
        userName.classList.add("error");
        //将提示内容设为红色
        userName.classList.add("errorTip");
        //清空内容
        userName.value = "";
        //修改提示内容
        userName.setAttribute("placeholder","1-6位的汉字、英文、数字、下划线");
    }
    if(password1.value == "" || password2.value == ""){
        result = false;
        //将输入框边框设为红色
        password1.classList.add("error");
        password2.classList.add("error");
        //将提示内容设为红色
        password1.classList.add("errorTip");
        password2.classList.add("errorTip");
        //修改提示内容
        password1.setAttribute("placeholder","请输入密码");
        password2.setAttribute("placeholder","请输入密码");
    }
    if(!isQualifiedPassword(password1,password2)){
        result = false;
        //将输入框边框设为红色
        password1.classList.add("error");
        password2.classList.add("error");
        //将提示内容设为红色
        password1.classList.add("errorTip");
        password2.classList.add("errorTip")
        //清空内容
        password1.value = "";
        password2.value = "";
        //修改提示内容
        password1.setAttribute("placeholder","两次密码输入不一致");
        password2.setAttribute("placeholder","两次密码输入不一致");
    }
    if(!isPassword(password1.value)){
        result = false;
        //将输入框边框设为红色
        password1.classList.add("error");
        password2.classList.add("error");
        //将提示内容设为红色
        password1.classList.add("errorTip");
        password2.classList.add("errorTip")
        //清空内容
        password1.value = "";
        password2.value = "";
        //修改提示内容
        password1.setAttribute("placeholder","[6-12个字符:字母,数字,下划线]");
        password2.setAttribute("placeholder","[6-12个字符:字母,数字,下划线]");
    }
    if(phone.value == "" || !isQualifiedPhone(phone)){
        result = false;
        //将输入框边框设为红色
        phone.classList.add("error");
        //将提示内容设为红色
        phone.classList.add("errorTip");
        //清空内容
        phone.value = "";
        //修改提示内容
        phone.setAttribute("placeholder","请填写正确的手机号码");
    }
    if(email.value == "" || !isQualifiedEmail(email)){
        result = false;
        //将输入框边框设为红色
        email.classList.add("error");
        //将提示内容设为红色
        email.classList.add("errorTip");
        //清空内容
        email.value = "";
        //修改提示内容
        email.setAttribute("placeholder","请填写正确的邮箱地址");
    }
    var res1 = document.getElementById("userName-result-span");
    var res2 = document.getElementById("email-result-span");
    var res3 = document.getElementById("password-result-span");
    if(res1.innerHTML === "false" || res2.innerHTML  === "false" || res3.innerHTML === "false"){
        result = false;
    }
    if(result == true){
        alert("注册成功!我们已经向"+email.value+"发送了激活邮件,激活之后就可以登录啦~");
    }
    return result;
}
/**
 * 正则验证用户名
 * @param password
 */
function isQualifiedName(userName) {
    var myreg=new RegExp(/^[\u4e00-\u9fa5\w]{1,6}$/);
    if (!myreg.test(userName.value)) {
        return false;
    } else {
        return true;
    }
}
/**
 * 正则验证是否是相同密码
 * @param password
 */
function isQualifiedPassword(password1,password2) {
    if (password1.value == password2.value){
        return true;
    }else{
        return false;
    }
}
/**
 * 正则验证是否是合格邮箱
 * @param password
 */
function isQualifiedEmail(email) {
    var myreg=new RegExp(/^([a-zA-Z0-9._-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/);
    if (!myreg.test(email.value)) {
        return false;
    } else {
        return true;
    }
}
/**
 * 正则验证是否是合格手机号
 * @param password
 */
function isQualifiedPhone(phone) {
    var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
    if (!myreg.test(phone.value)) {
        return false;
    } else {
        return true;
    }
}
/**
 * 用户名是否已经存在
 * 将结果写到页面上,方便验证的时候获取
 */
function isExistUserName() {
    var userName = document.getElementById("RegisterUserName");
    var result = document.getElementById("userName-result-span");
    if(userName.value != ""){
        //1.创建一个异步对象
        var xhe;
        if(window.XMLHttpRequest){
            xhe = new XMLHttpRequest();
        }else{
            xhe = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xhe.open("post","isExistUserName.do",true);
        xhe.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xhe.send("userName="+userName.value);
        xhe.onreadystatechange = function () {
            if(xhe.readyState === 4){
                if(xhe.status >=200 && xhe.status < 300 || xhe.status === 304){
                    //5.处理返回的结果
                    var obj = xhe.responseText;
                    var img = document.getElementById("userName-img");
                    if(obj === "yes"){
                        var tip = "哇哦,这个用户名已经被占用啦~";
                        setErrorImg(img,tip);
                        result.innerHTML = "false";
                    }else{
                        var tip = "您可以使用这个名字";
                        setPassImg(img,tip);
                        result.innerHTML = "true";
                    }
                }
            }
        }
    }else{
        var img = document.getElementById("userName-img");
        var path = img.getAttribute("src");
        var str = path.split("/static");
        img.src = str[0]+"/static/images/befovalidate.png";
        img.setAttribute("title","请输入用户名");
    }
}
/**
 * 邮箱地址是否已经存在
 * 将结果写到页面上,方便验证的时候获取
 */
function isExistEmail() {
    var email = document.getElementById("RegisterEmail");
    var res2 = document.getElementById("email-result-span");
    if(email.value != ""){
        //1.创建一个异步对象
        var xhe;
        if(window.XMLHttpRequest){
            xhe = new XMLHttpRequest();
        }else{
            xhe = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xhe.open("post","isExistEmail.do",true);
        xhe.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xhe.send("email="+email.value);
        xhe.onreadystatechange = function () {
            if(xhe.readyState === 4){
                if(xhe.status >=200 && xhe.status < 300 || xhe.status === 304){
                    //5.处理返回的结果
                    var obj = xhe.responseText;
                    var img1 = document.getElementById("email-img");
                    if(obj === "yes"){
                        var tip = "哇哦,这个邮箱地址已经被占用啦~";
                        setErrorImg(img1,tip);
                        res2.innerHTML = "false";
                    }else{
                        var tip = "您可以使用这个邮箱地址";
                        setPassImg(img1,tip);
                        res2.innerHTML = "true";
                    }
                }
            }
        }
    }else{
        var img1 = document.getElementById("email-img");
        var path = img1.getAttribute("src");
        var str = path.split("/static");
        img1.src = str[0]+"/static/images/befovalidate.png";
        img1.setAttribute("title","请输入邮箱地址");
    }
}
/**
 * 电话号码是否已经存在
 * 将结果写到页面上,方便验证的时候获取
 */
function isExistPhone() {
    var phone = document.getElementById("RegisterPhone");
    var res3 = document.getElementById("password-result-span");
    if(phone.value != ""){
        //1.创建一个异步对象
        var xhe;
        if(window.XMLHttpRequest){
            xhe = new XMLHttpRequest();
        }else{
            xhe = new ActiveXObject("Microsoft.XMLHTTP");
        }
        xhe.open("post","isExistPhone.do",true);
        xhe.setRequestHeader("Content-type","application/x-www-form-urlencoded");
        xhe.send("phone="+phone.value);
        xhe.onreadystatechange = function () {
            if(xhe.readyState === 4){
                if(xhe.status >=200 && xhe.status < 300 || xhe.status === 304){
                    //5.处理返回的结果
                    var obj = xhe.responseText;
                    var img1 = document.getElementById("phone-img");
                    if(obj === "yes"){
                        var tip = "哇哦,这个手机号已经被占用啦~";
                        setErrorImg(img1,tip);
                        res3.innerHTML = "false";
                    }else{
                        var tip = "您可以使用这个手机号";
                        setPassImg(img1,tip);
                        res3.innerHTML = "true";
                    }
                }
            }
        }
    }else{
        var img1 = document.getElementById("phone-img");
        var path = img1.getAttribute("src");
        var str = path.split("/static");
        img1.src = str[0]+"/static/images/befovalidate.png";
        img1.setAttribute("title","请输入手机号码");
    }
}
/**
 * 更改图片,设置为验证错误
 */
function setErrorImg(img,tip) {
     path = img.getAttribute("src");
    var str = path.split("/static");
    img.src = str[0]+"/static/images/errorvalidate.png";
    img.setAttribute("title",tip);
}
/**
 * 更改图片,设置为验证通过
 */
function setPassImg(img,tip) {
     path = img.getAttribute("src");
    var str = path.split("/static");
    img.src = str[0]+"/static/images/passvalidate.png";
    img.setAttribute("title",tip);
}
function isPassword(password) {
    var myreg=new RegExp(/^[a-zA-Z0-9_]{6,12}$/);
    if (!myreg.test(password)) {
        return false;
    } else {
        return true;
    }
}