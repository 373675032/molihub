<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/8/24
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/MyNav.css">
    <script src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
</head>
<body>
<div class="sidebar">
    <img class="btn" src="<%=request.getContextPath() %>/static/images/open_list.png" title="展开管理栏">
    <ul>
        <li><a class="list-icon" href="toMain.do"><img src="<%=request.getContextPath() %>/static/images/main_page.png" title="首页"></a></li>
        <li><a class="list-icon" href="toUpdateInfo.do"><img src="<%=request.getContextPath() %>/static/images/update_info.png" title="修改资料和密码"></a></li>
        <li><a class="list-icon" href="toFileStore.do"><img src="<%=request.getContextPath() %>/static/images/file_store.png" title="莫提网盘"></a></li>
        <li><a class="list-icon" href="toSuggest.do"><img src="<%=request.getContextPath() %>/static/images/suggest.png" title="反馈"></a></li>
        <li><a class="list-icon" href="toLogout.do"><img src="<%=request.getContextPath() %>/static/images/logout.png" title="退出登录"></a></li>
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
