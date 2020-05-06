function init() {
    setInfo();
}
function setInfo() {
    var userName = $("#hidden-userName").html();
    var phone = $("#hidden-phone").html();
    var email = $("#hidden-email").html();
    var password = $("#hidden-password").html();
    var sex = $("#hidden-sex").html();
    var age = $("#hidden-age").html();

    $("#input-userName").val(userName);
    $("#input-phone").val(phone);
    $("#input-email").val(email);
    $("#input-age").val(age);

    if(sex === "男"){
        $("#man").attr("checked", true);
    }else if(sex === "女"){
        $("#woman").attr("checked", true);
    }else{
        $("#secret").attr("checked", true);
    }
}
function updateInfo() {
    var userName = $("#input-userName").val().trim();
    var phone = $("#input-phone").val().trim();
    var email = $("#input-email").val().trim();
    var age = $("#input-age").val().trim();
    var sexarr = $(".input-sex");
    var sex = "男";
    for(k in sexarr){
        if(sexarr[1].checked){
            sex = "女";
        }
        if(sexarr[2].checked){
            sex = "保密";
        }
    }
    if(userName === $("#hidden-userName").html() && phone === $("#hidden-phone").html() && email === $("#hidden-email").html() && age === $("#hidden-age").html() && sex === $("#hidden-sex").html()){
        alert("你没有更改任何信息哦");
        return;
    }
    if(userName == ""||phone == ""||email == ""){
        alert("请填全所有信息");
        return;
    }
    if(!isQualifiedName(userName)){
        alert("用户名不符合规范[1-6位的汉字、英文、数字、下划线]");
        return;
    }
    if(age<0){
        alert("请输入正确的年龄");
        return;
    }
    if(!isQualifiedPhone(phone)){
        alert("请输入正确的手机号");
        return;
    }
    if(!isQualifiedEmail(email)){
        alert("邮箱格式不正确");
        return;
    }
    $.ajax({
        type:"get",
        url:"updateInfo.do",
        data :{
            user_name:userName,
            email:email,
            phone:phone,
            sex:sex,
            age:age
        },
        success:function (result) {
            if(result === "success"){
                alert("修改成功!");
            }else if(result === "fail:user_name"){
                alert("修改失败:用户名已存在");
            }else if(result === "fail:phone"){
                alert("修改失败:手机号已被注册")
            }else if(result === "fail:email"){
                alert("修改失败:邮箱已被注册");
            }
        },
        error:function (msg) {
            alert("修改信息失败!");
        }
    });
}
function updatePassword() {
    var old = $("#hidden-password").html();
    var old_input = $("#old-password").val();
    var new_input1 = $("#new-password1").val();
    var new_input2 = $("#new-password2").val();

    if(old_input == ""){
        alert("请输入原始密码");
        return;
    }
    if(old != old_input){
        alert("原始密码不正确");
        return;
    }
    if(new_input1 == "" || new_input2 == ""){
        alert("请输入新密码");
        return;
    }
    if(new_input1 == old_input){
        alert("新密码不能和原来密码一致");
        return;
    }
    if(!isQualifiedPassword(new_input1,new_input2)){
        alert("两次密码输入不一致!");
        return;
    }
    if(!isPassword(new_input1)){
        alert("格式错误[6-12个字符:字母,数字,下划线]");
        return;
    }
    $.ajax({
        type:"get",
        url:"updatePassword.do",
        data :{
            password:new_input1
        },
        success:function (result) {
            alert("密码修改成功!");
            $("#old-password").val("");
            $("#new-password1").val("");
            $("#new-password2").val("");
        },
        error:function (msg) {
            alert("修改密码失败!");
        }
    });
}
/**
 * 正则验证用户名
 * @param password
 */
function isQualifiedName(userName) {
    var myreg=new RegExp(/^[\u4e00-\u9fa5\w]{1,6}$/);
    if (!myreg.test(userName)) {
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
    if (password1 == password2){
        return true;
    }else{
        return false;
    }
}
function isPassword(password) {
    var myreg=new RegExp(/^[a-zA-Z0-9_]{6,12}$/);
    if (!myreg.test(password)) {
        return false;
    } else {
        return true;
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
/**
 * 正则验证是否是合格手机号
 * @param password
 */
function isQualifiedPhone(phone) {
    var myreg=/^[1][3,4,5,7,8][0-9]{9}$/;
    if (!myreg.test(phone)) {
        return false;
    } else {
        return true;
    }
}