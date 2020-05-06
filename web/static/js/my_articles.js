function init() {
    setMyPageNum();
}
/**
 * 获得文章的总页数
 */
function setMyPageNum() {
    $.ajax({
        type:"GET",
        url:"getMyPageNum.do",
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
function getManageArticlesByPage(page) {
    page++;
    page--;

    var which_a = "#page-"+page;
    $(which_a).addClass("active");
    $(which_a).parent().siblings().children().removeClass("active");
    $.ajax({
        type:"GET",
        url:"getMyArticlesByPage.do",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        data: { pageNow: page},
        success:function (result) {
            //将返回的结果转换为对象数组
            var objarr = eval("("+result+")");
            $("#articles-table").html("");
            var tr = $("<tr>" +
                "<th>标题</th>" +
                "<th>阅读数</th>" +
                "<th>点赞数</th>" +
                "<th>评论数</th>" +
                "<th>发表时间</th>" +
                "<th>操作</th>" +
                "<th>操作</th>" +
                "</tr>");
            $("#articles-table").append(tr);
            for (var i = 0; i < objarr.length; i++) {
                var path = "readArticleById.do?article_id="+objarr[i].article_id;
                var editPath = "toEdit.do?article_id="+objarr[i].article_id;
                // var deletePath = "deleteArticle.do?article_id="+objarr[i].article_id;
                var tr = $("<tr>" +
                    "<td><a href='"+path+"'>"+objarr[i].article_title+"</a></td>" +
                    " <td>"+objarr[i].read_count+"</td>" +
                    "<td>"+objarr[i].like_count+"</td>" +
                    "<td>"+objarr[i].comment_count+"</td>" +
                    "<td>"+objarr[i].time_to_show+"</td>" +
                    "<td><a href='"+editPath+"'>编辑</a></td>" +
                    "<td><a class='delete' href='javaScript:deleteMyArticle("+objarr[i].article_id+")'>删除</a></td>" +
                    "</tr>");
                $("#articles-table").append(tr);
            }
        },
        error:function (msg) {
            console.log("获取我的文章失败!");
        }
    });
}
function deleteMyArticle(id) {

    var dblChoseAlert = simpleAlert({
        "title":"操作",
        "content":"确认删除这篇文章吗?",
        "buttons":{
            "确定":function () {
                $.ajax({
                    type:"GET",
                    url:"deleteArticle.do",
                    async: false,
                    contentType: "application/json; charset=utf-8",
                    dataType: "text",
                    data:{
                        article_id:id
                    },
                    success:function (result) {
                        if (result === "yes"){
                            window.location.reload();
                        }else{
                            console.log("删除文章失败!");
                        }

                    },
                    error:function (msg) {
                        console.log("删除文章失败!");
                    }
                });
                dblChoseAlert.close();
            },
            "取消":function () {
                dblChoseAlert.close();
            }
        }
    })


}