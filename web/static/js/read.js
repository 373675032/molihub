function init() {
    initTag();
    getAllComments();
    isLiked();
}
function tagTip() {
    alert("该文章暂无标签!");
}
function initTag() {
    var tags = $("#article_tag").html();
    if (tags == ""){
        $("#article_tag").html("作者没有给这篇文章添加标签~");
        $("#article_tag").attr("href","javaScript:tagTip()");
    }
}
function addComment() {
    if($(".comment-input").val().trim() == ""){
        alert("请填写留言内容~");
        return;
    }
    var id = $("#article_id").html();
    var content = $(".comment-input").val().trim();
    $.ajax({
        type:"get",
        url:"addComment.do",
        data :{
            article_id:id,
            content:content
        },
        success:function () {
            reloadComment();
        },
        error:function (msg) {
            reloadComment();
        }
    });

}
function getAllComments() {
    var id = $("#article_id").html();
    $.ajax({
        type:"get",
        url:"getAllComments.do",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        data :{
            article_id:id
        },
        success:function (result) {
            //清空评论区
            $("#comments").html("");
            var objarr = eval("("+result+")");
            for (var i = 0;i < objarr.length;i++){
                var userName = objarr[i].user_name;
                var content = objarr[i].comment_content;
                var time_to_show = objarr[i].time_to_show;
                var homePath = "toOtherHome.do?user_name="+userName;
                var li = $("<li>" +
                    " <div class='comment'>" +
                    "<p><span class='color-span'>#</span>"+(i+1)+"楼 <a href='"+homePath+"'>"+userName+"</a> <span class='time'>"+time_to_show+"</span></p>" +
                    "<p>"+content+"</p>" +
                    "</li>");
                $("#comments-ul").append(li);
            }
        },
        error:function (msg) {
            console.log("Ajax获取评论失败!");
        }
    });
}
function test(result) {
    alert(result);
}
function like() {
    var article_id = $("#article_id").html();
    $.ajax({
        type:"get",
        url:"like.do",
        data :{
            article_id:article_id
        },
        success:function () {
            $("#like_status").html("已点赞");
            reloadLike();
        },
        error:function (msg) {
            reloadLike();
        }
    });
}
function isLiked() {
    var article_id = $("#article_id").html();
    $.ajax({
        type:"get",
        url:"isLiked.do",
        data :{
            article_id:article_id
        },
        success:function (result) {
            if(result == "yes"){
                $("#like_status").html("已点赞");
                var pic = document.getElementById("like-img");
                pic.src = $("#path").html()+"/static/images/like.png";
            }
        },
        error:function (msg) {
            console.log("Ajax判断是否点赞失败!");
        }
    });
}
function disLike() {
    var article_id = $("#article_id").html();
    $.ajax({
        type:"get",
        url:"disLike.do",
        data :{
            article_id:article_id
        },
        success:function () {
            $("#like_status").html("未点赞");
            reloadLike();
        },
        error:function (msg) {
            reloadLike();
        }
    });
}
function doLike() {
   var like_status = $("#like_status").html();
   if(like_status === "未点赞"){
        like();
   }else{
        disLike();
   }
}
function reload() {
    var id = $("#article_id").html();
    var path = $("#path").html()+"/readArticleById.do?article_id="+id;
    $(location).attr('href', path);
}
function reloadComment() {
    window.document.location.reload();
}
function reloadLike() {
    window.document.location.reload();
}