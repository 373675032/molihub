function init() {
    var path = $("#path").html();
    setPageNum();
    setBaseInfo();
    initTop();
    haveNewMessage();
}
function haveNewMessage() {
    $.ajax({
        type:"get",
        url:"haveNewMessage.do",
        success:function (result) {
            if(result === "yes"){
                $("#message-a").html("新的消息");
                $("#message-a").css({
                    'color':'red',
                    'font-weight':'700'
                });
            }
        },
        error:function (msg) {
            console.log("获取新信息失败!");
        }
    });
}
function setBaseInfo() {
    $.ajax({
        type:"POST",
        url:"getBasicInfo.do",
        success:function (result) {
           arr = result.split("-");
           var userC = arr[0];
           var articleC = arr[1];
           $("#userC").html(userC);
           $("#articleC").html(articleC);

        },
        error:function (msg) {
            console.log("获取论坛基本信息失败!");
        }
    });
}
/**
 * 获得文章的总页数
 */
function setPageNum() {
    $.ajax({
        type:"GET",
        url:"getPageNum.do",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        success:function (result) {
            result++;
            result--;
            $("#pageNum").html(result);
            if(result >= 2){
                for (var i = 1;i < result; i++){
                    var li =  $("<li><a id='page-"+(i+1)+"' href='#' onclick='getArticlesByPage("+(i+1)+")'>"+(i+1)+"</a></li>");
                    li.insertBefore($("#next-page"));
                    if(i == 5 && result > 6){
                        var li =  $("<li><a id='etc' href='#'>...</a></li>");
                        li.insertBefore($("#next-page"));
                        break;
                    }
                }
            }

        },
        error:function (msg) {
            console.log("获取文章总页数失败!");
        }
    });
}
function delHtmlTag(str){
    return str.replace(/<[^>]+>/g,"");//去掉所有的html标记
}
function getArticlesByPage(page) {
    page++;
    page--;

        //重新设置页数栏
    $("#article-ul").html("");
    var path = $("#path").html();
    var which_a = "#page-"+page;
    $(which_a).addClass("active");
    $(which_a).parent().siblings().children().removeClass("active");
    $.ajax({
        type:"GET",
        url:"getAllArticlesByPage.do",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        data: { pageNow: page},
        success:function (result) {
            //将返回的结果转换为对象数组
            var objarr = eval("("+result+")");
            for(var i = 0; i < objarr.length; i++){
                var readPath = "readArticleById.do?article_id="+objarr[i].article_id;
                var homePath = "toOtherHome.do?user_name="+objarr[i].user_name;
                var content = objarr[i].article_content;
                var html = marked(content);
                html = delHtmlTag(html);
                var li = $(
                    "<li><div class= 'article'><div class= 'article-title'><a href= '"+readPath+"'>" +
                    "<img src= '"+path+"/static/images/article_title.png'>" +
                    "<span>"+objarr[i].article_title+"</span></a><span class= 'article-id'>"+objarr[i].article_id+"</span>" +
                    "</div><div class= 'article-content'>"+html+"......<a href= '"+readPath+"'>&lt;查看全文&gt;</a>" +
                    "</div><div class= 'article-info'><div>" +
                    "<img src= '"+path+"/static/images/article_user.png'>" +
                    "作者: <a href= '"+homePath+"'>"+objarr[i].user_name+"</a></div><div>" +
                    "<img src= '"+path+"/static/images/article_kind.png'>" +
                    "类别: <a href= '#'>"+objarr[i].article_kind+"</a></div><div>" +
                    "<img src= '"+path+"/static/images/article_like.png'>" +
                    "点赞: <span>"+objarr[i].like_count+"</span></div><div>" +
                    "<img src= '"+path+"/static/images/article_comment.png'>" +
                    "评论: <span>"+objarr[i].comment_count+"</span></div><div>" +
                    "<img src= '"+path+"/static/images/article_read.png'>" +
                    "阅读: <a href= '#'>"+objarr[i].read_count+"</a></div><div>" +
                    "<img src= '"+path+"/static/images/article_time.png'>" +
                    "时间: <span>"+objarr[i].time_to_show+"</span></div></div></div></li>"
                );
                $("#article-ul").append(li);
            }
        },
        error:function (msg) {
            console.log("获取首页文章失败!");
        }
    });
}
function search() {
    var text = $("#search-input").val().trim();
    if(text == ""){
        alert("请输入搜索内容!");
        return;
    }
    $.ajax({
        type:"post",
        url:"searchArticle.do",
        data :{
            content:text
        },
        success:function (result) {
            var objarr = eval("("+result+")");
            var path = $("#path").html();
            if(objarr.length == 0){
                alert("没有找到相关文章,您可以输入用户名,文章类型,关键字等进行查找...");
            }else{
                $(".page-box").css({
                    'display':'none'
                });
                $("#article-ul").css({
                    'max-height':'1450px',
                    'overflow-y':'auto'
                })
                $("#article-ul").html("");
                for (var i = 0; i < objarr.length; i++) {
                    var readPath = "readArticleById.do?article_id="+objarr[i].article_id;
                    var homePath = "toOtherHome.do?user_name="+objarr[i].user_name;
                    var content = objarr[i].article_content;
                    var html = marked(content);
                    html = delHtmlTag(html);
                    var li = $(
                        "<li><div class= 'article'><div class= 'article-title'><a href= '"+readPath+"'>" +
                        "<img src= '"+path+"/static/images/article_title.png'>" +
                        "<span>"+objarr[i].article_title+"</span></a><span class= 'article-id'>"+objarr[i].article_id+"</span>" +
                        "</div><div class= 'article-content'>"+html+"......<a href= '"+readPath+"'>&lt;查看全文&gt;</a>" +
                        "</div><div class= 'article-info'><div>" +
                        "<img src= '"+path+"/static/images/article_user.png'>" +
                        "作者: <a href= '"+homePath+"'>"+objarr[i].user_name+"</a></div><div>" +
                        "<img src= '"+path+"/static/images/article_kind.png'>" +
                        "类别: <a href= '#'>"+objarr[i].article_kind+"</a></div><div>" +
                        "<img src= '"+path+"/static/images/article_like.png'>" +
                        "点赞: <span>"+objarr[i].like_count+"</span></div><div>" +
                        "<img src= '"+path+"/static/images/article_comment.png'>" +
                        "评论: <span>"+objarr[i].comment_count+"</span></div><div>" +
                        "<img src= '"+path+"/static/images/article_read.png'>" +
                        "阅读: <a href= '#'>"+objarr[i].read_count+"</a></div><div>" +
                        "<img src= '"+path+"/static/images/article_time.png'>" +
                        "时间: <span>"+objarr[i].time_to_show+"</span></div></div></div></li>"
                    );
                    $("#article-ul").append(li);
                }
            }
        },
        error:function (msg) {
            console.log("搜索文章失败!");
        }
    });
}
function searchKind(content) {
    $.ajax({
        type: "post",
        url: "searchArticle.do",
        data: {
            content: content
        },
        success: function (result) {
            var objarr = eval("(" + result + ")");
            var path = $("#path").html();
            if (objarr.length == 0) {
                alert("没有找到相关文章,您可以输入用户名,文章类型,关键字等进行查找...");
            } else {
                $(".page-box").css({
                    'display': 'none'
                });
                $("#article-ul").css({
                    'max-height': '1450px',
                    'overflow-y': 'auto'
                })
                $("#article-ul").html("");
                for (var i = 0; i < objarr.length; i++) {
                    var readPath = "readArticleById.do?article_id=" + objarr[i].article_id;
                    var homePath = "toOtherHome.do?user_name="+objarr[i].user_name;
                    var content = objarr[i].article_content;
                    var html = marked(content);
                    html = delHtmlTag(html);
                    var li = $(
                        "<li><div class= 'article'><div class= 'article-title'><a href= '" + readPath + "'>" +
                        "<img src= '" + path + "/static/images/article_title.png'>" +
                        "<span>" + objarr[i].article_title + "</span></a><span class= 'article-id'>" + objarr[i].article_id + "</span>" +
                        "</div><div class= 'article-content'>" + html + "......<a href= '" + readPath + "'>&lt;查看全文&gt;</a>" +
                        "</div><div class= 'article-info'><div>" +
                        "<img src= '" + path + "/static/images/article_user.png'>" +
                        "作者: <a href= '"+homePath+"'>" + objarr[i].user_name + "</a></div><div>" +
                        "<img src= '" + path + "/static/images/article_kind.png'>" +
                        "类别: <a href= '#'>" + objarr[i].article_kind + "</a></div><div>" +
                        "<img src= '" + path + "/static/images/article_like.png'>" +
                        "点赞: <span>" + objarr[i].like_count + "</span></div><div>" +
                        "<img src= '" + path + "/static/images/article_comment.png'>" +
                        "评论: <span>" + objarr[i].comment_count + "</span></div><div>" +
                        "<img src= '" + path + "/static/images/article_read.png'>" +
                        "阅读: <a href= '#'>" + objarr[i].read_count + "</a></div><div>" +
                        "<img src= '" + path + "/static/images/article_time.png'>" +
                        "时间: <span>" + objarr[i].time_to_show + "</span></div></div></div></li>"
                    );
                    $("#article-ul").append(li);
                }
            }
        },
        error: function (msg) {
            console.log("分类获得文章失败!");
        }
    });
}
function initTop() {
    $.ajax({
        type:"get",
        url:"getTop.do",
        success:function (result) {
            var arr = JSON.parse(result);
            var objarr = eval("("+result+")");
            // for (var i = 0; i <objarr.length ; i++) {
            //     console.log(objarr[i].article_title);
            // }
            var listRead = objarr["listRead"];
            var listLike = objarr["listLike"];
            for (var i = 0; i < listRead.length; i++) {
                var path = "readArticleById.do?article_id="+listRead[i].article_id;
                var li = $("<li><a href="+path+">"+listRead[i].article_title+"</a></li>");
                $("#read-top-ul").append(li);
            }
            for (var i = 0; i < listLike.length; i++) {
                var path = "readArticleById.do?article_id="+listLike[i].article_id;
                var li = $("<li><a href="+path+">"+listLike[i].article_title+"</a></li>");
                $("#like-top-ul").append(li);
            }
        },
        error:function (msg) {
            console.log("初始化排行榜信息失败!");
        }
    });
}
