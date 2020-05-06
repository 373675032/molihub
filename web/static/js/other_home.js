function load() {
    initInfo();
    setOtherPageNum();
}
/**
 * 获得文章的总页数
 */
function setOtherPageNum() {
    $.ajax({
        type:"GET",
        url:"getOtherPageNum.do",
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

    $("#articles").html("");
    var path = $("#path").html();
    var which_a = "#page-"+page;
    $(which_a).addClass("active");
    $(which_a).parent().siblings().children().removeClass("active");
    $.ajax({
        type:"GET",
        url:"getOtherArticlesByPage.do",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        data: { pageNow: page},
        success:function (result) {
            //将返回的结果转换为对象数组
            var objarr = eval("("+result+")");
            for(var i = 0; i < objarr.length; i++){
                var content = objarr[i].article_content;
                var title = objarr[i].article_title;
                var html = marked(content);
                var status = "公开";
                var readPath = "readArticleById.do?article_id="+objarr[i].article_id;
                html = delHtmlTag(html);
                var li = $("<li><div class='article'><p class='article-title'>" +
                    "<a href='"+readPath+"'>"+title+"</a></p>" +
                    "<p class='article-content'>" +html+
                    "<a href='"+readPath+"'>&lt;查看全文&gt;</a></p>" +
                    "<div class='article-info'>posted @ <span>"+objarr[i].time_to_show+" </span>" +
                    "<span>"+objarr[i].user_name+"</span>" +
                    " 阅读(<span>"+objarr[i].read_count+")</span> 点赞(<span>"+objarr[i].like_count+")</span> 评论(<span>"+objarr[i].comment_count+") </span>" +
                    "</div></div></li>");
                $("#articles").append(li);
            }
        },
        error:function (msg) {
            console.log("获取我的文章失败!");
        }
    });
}
function search() {
    var content = $("#search-input").val().trim();
    var text = content;
    if (content === ""){
        alert("请输入搜索内容");
    }else{
        $.ajax({
            type:"GET",
            url:"searchOtherArticles.do",
            async: false,
            contentType: "application/json; charset=utf-8",
            dataType: "text",
            data: { content: content},
            success:function (result) {
                //将返回的结果转换为对象数组
                var objarr = eval("("+result+")");
                if(objarr.length == 0){
                    alert("没有找到相关的文章");
                }else{
                    $("#articles").html("");
                    $("#articles").html("<h3>与:"+text+"有关的文章如下</h3>");
                    document.body.scrollTop = 0;
                    document.documentElement.scrollTop = 0;
                    $(".page-box").css({
                        'display':'none'
                    });
                for(var i = 0; i < objarr.length; i++){
                    var content = objarr[i].article_content;
                    var title = objarr[i].article_title;
                    var html = marked(content);
                    var readPath = "readArticleById.do?article_id="+objarr[i].article_id;
                    var editPath = "toEdit.do?article_id="+objarr[i].article_id;
                    html = delHtmlTag(html);
                    var li = $("<li><div class='article'><p class='article-title'>" +
                        "<a href='"+readPath+"'>"+title+"</a></p>" +
                        "<p class='article-content'>" +html+
                        "<a href='"+readPath+"'>&lt;查看全文&gt;</a></p>" +
                        "<div class='article-info'>posted @ <span>"+objarr[i].time_to_show+" </span>" +
                        "<span>"+objarr[i].user_name+"</span>" +
                        " 阅读(<span>"+objarr[i].read_count+")</span> 点赞(<span>"+objarr[i].like_count+")</span> 评论(<span>"+objarr[i].comment_count+") </span>" +
                        "</div></div></li>");
                    $("#articles").append(li);
                }
                }
            },
            error:function (msg) {
                console.log("搜索我的文章失败!");
            }
        });
    }
}
function initInfo() {
    $.ajax({
        type:"GET",
        url:"getOtherPageInfo.do",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        success:function (result) {
            //将返回的结果转换为对象数组
            var objarr = eval("("+result+")");
            var listRead = objarr["listRead"];
            var labels = objarr["labels"];
            var sum = objarr["sum"];
            if(listRead.length === 0){
                var h5 = $("<h5>Ta还没有发表过文章哦</h5>")
                $("#read-top-ul").append(h5);
            }
            if(labels.length === 0){
                var h5 = $("<h5>Ta还没有添加过个性标签</h5>")
                $("#tag-ul").append(h5);
            }
            for (var i = 0; i < listRead.length; i++) {
                var path = "readArticleById.do?article_id="+listRead[i].article_id;
                var li = $("<li><a href="+path+">"+listRead[i].article_title+"</a></li>");
                $("#read-top-ul").append(li);
            }
            for (var i = 0; i < labels.length; i++) {
                var li = $("<li><a href='javaScript:getArticlesByTag(\""+labels[i]+ "\")'><xmp>"+labels[i]+"</xmp></a></li>");
                $("#tag-ul").append(li);
            }
            $("#count").html(sum);
        },
        error:function (msg) {
            console.log("初始化信息失败");
        }
    });
}
function getArticlesByTag(tagName) {
    var str = tagName.split("(");
    var num = str[1].split(")");
    var name = str[0];
    var count = num[0];
    if(count === "0"){
        alert("没有与这个标签相关的文章哦~");
        return;
    }
    $.ajax({
        type:"GET",
        url:"getOtherArticlesByTag.do",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        data: { tagName: name},
        success:function (result) {
            //将返回的结果转换为对象数组
            var objarr = eval("("+result+")");
            if(objarr.length == 0){
                alert("没有找到相关的文章");
            }else{
                $("#articles").html("<h3>标签:"+name+"所对应的文章如下</h3>");
                document.body.scrollTop = 0;
                document.documentElement.scrollTop = 0;
                $(".page-box").css({
                    'display':'none'
                });
                for(var i = 0; i < objarr.length; i++){
                    var content = objarr[i].article_content;
                    var title = objarr[i].article_title;
                    var html = marked(content);

                    var readPath = "readArticleById.do?article_id="+objarr[i].article_id;
                    html = delHtmlTag(html);
                    var li = $("<li><div class='article'><p class='article-title'>" +
                        "<a href='"+readPath+"'>"+title+"</a></p>" +
                        "<p class='article-content'>" +html+
                        "<a href='"+readPath+"'>&lt;查看全文&gt;</a></p>" +
                        "<div class='article-info'>posted @ <span>"+objarr[i].time_to_show+" </span>" +
                        "<span>"+objarr[i].user_name+"</span>" +
                        " 阅读(<span>"+objarr[i].read_count+")</span> 点赞(<span>"+objarr[i].like_count+")</span> 评论(<span>"+objarr[i].comment_count+") </span>" +
                        "</div></div></li>");
                    $("#articles").append(li);
                }
            }
        },
        error:function (msg) {
            console.log("根据标签获得文章失败!");
        }
    });

}
function getArticlesByKind(kindName) {
    $.ajax({
        type:"GET",
        url:"getOtherArticlesByKind.do",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        data: { kind: kindName},
        success:function (result) {
            //将返回的结果转换为对象数组
            var objarr = eval("("+result+")");
            if(objarr.length == 0 || objarr === "no"){
                alert("没有找到相关的文章");
            }else{
                $("#articles").html("<h3>类别:"+kindName+"</h3>");
                document.body.scrollTop = 0;
                document.documentElement.scrollTop = 0;
                $(".page-box").css({
                    'display':'none'
                });
                for(var i = 0; i < objarr.length; i++){
                    var content = objarr[i].article_content;
                    var title = objarr[i].article_title;
                    var html = marked(content);
                    var readPath = "readArticleById.do?article_id="+objarr[i].article_id;
                    var editPath = "toEdit.do?article_id="+objarr[i].article_id;
                    html = delHtmlTag(html);
                    var li = $("<li><div class='article'><p class='article-title'>" +
                        "<a href='"+readPath+"'>"+title+"</a></p>" +
                        "<p class='article-content'>" +html+
                        "<a href='"+readPath+"'>&lt;查看全文&gt;</a></p>" +
                        "<div class='article-info'>posted @ <span>"+objarr[i].time_to_show+" </span>" +
                        "<span>"+objarr[i].user_name+"</span>" +
                        " 阅读(<span>"+objarr[i].read_count+")</span> 点赞(<span>"+objarr[i].like_count+")</span> 评论(<span>"+objarr[i].comment_count+") </span>" +
                        "</div></div></li>");
                    $("#articles").append(li);
                }
            }
        },
        error:function (msg) {
            console.log("根据类别获得文章失败!");
        }
    });

}