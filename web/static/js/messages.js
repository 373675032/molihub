function init() {
    $.ajax({
        type:"GET",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        url:"getAllMessages.do",
        success:function (result) {
            var objarr = eval("("+result+")");
            for (var i = 0; i < objarr.length ; i++) {
                var user_name = objarr[i].user_name;
                var type = objarr[i].message_type;
                var readPath = "readArticleById.do?article_id="+objarr[i].article_id;
                var homePath = "toOtherHome.do?user_name="+objarr[i].user_name;
                var article_name = objarr[i].article_name;
                var comment_content = objarr[i].comment_content;
                var is_looked = objarr[i].is_looked;
                var message_time = objarr[i].message_time;
                var li;
                if(is_looked === "no"){
                    if(type == "1"){
                        li = $("<li><span>"+message_time+"</span><a href='"+homePath+"'>"+user_name+"</a>:点赞了你的文章<a href='"+readPath+"'>《"+article_name+"》</a></li>");
                    }else if(type == "2"){
                        li = $("<li><span>"+message_time+"</span><a href='"+homePath+"'>"+user_name+"</a>:在你的文章<a href='"+readPath+"'>《"+article_name+"》</a>下评论:"+comment_content+"</li>");
                    }else {
                        li = $("<li><span>"+message_time+"</span><a href='"+homePath+"'>"+user_name+"</a>:在你关注的文章<a href='"+readPath+"'>《"+article_name+"》</a>下评论:"+comment_content+"</li>");
                    }
                    $("#no-read-ul").append(li);
                }else{
                    if(type == "1"){
                        li = $("<li><span>"+message_time+"</span><a href='"+homePath+"'>"+user_name+"</a>:点赞了你的文章<a href='"+readPath+"'>《"+article_name+"》</a></li>");
                    }else if(type == "2"){
                        li = $("<li><span>"+message_time+"</span><a href='"+homePath+"'>"+user_name+"</a>:在你的文章<a href='"+readPath+"'>《"+article_name+"》</a>下评论:"+comment_content+"</li>");
                    }else {
                        li = $("<li><span>"+message_time+"</span><a href='"+homePath+"'>"+user_name+"</a>:在你关注的文章<a href='"+readPath+"'>《"+article_name+"》</a>下评论:"+comment_content+"</li>");
                    }
                    $("#read-ul").append(li);
                }
            }
        },
        error:function (msg) {
            console.log("获得我的消息失败!");
        }
    });
}
function readAll() {
    $.ajax({
        type:"get",
        url:"readAllMessages.do",
        success:function (result) {
            reload();
        },
        error:function (msg) {
        }
    });
}
function deleteAll() {
    $.ajax({
        type:"get",
        url:"deleteReadMessages.do",
        success:function (result) {
            reload();
        },
        error:function (msg) {
        }
    });
}
function reload() {
    var id = $("#article_id").html();
    var path = $("#path").html()+"/toMessages.do";
    $(location).attr('href', path);
}