/**
 * 初始化
 */
function init(){
    getAllLabels();
}

/**
 * 发送Ajax请求,获得所有的个性标签并渲染
 */
function getAllLabels() {
    $.ajax({
        type:"GET",
        url:"getMyAllLabels.do",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        success:function (result) {
            //将返回的结果转换为对象数组
            var objarr = eval("("+result+")");
            for (var i = 0;i < objarr.length;i++){
                var li = $("<li class='label-li'>" +
                                "<label><span><xmp class = 'xmp'>"+objarr[i]+"</xmp></span>" +
                                "<input class='label-checkbox' type='checkbox' name='blog_labels' value='"+objarr[i]+"'>" +
                            "</label></li>");
                $("#my_tag").append(li);
            }
            if(objarr.length == 0){
                var li = $("<li class='label-li'><span>你还没有添加过个性标签哦~</span></li>");
                $("#my_tag").append(li);
            }
        },
        error:function (msg) {
            console.log("获取个性标签失败!");
        }
    });
}

/**
 *
 */
function checkSubmit(){
    if($("#input-title").val() == "" && $("#input-content").val() == ""){
        alert("请输入标题和正文")
        return false;
    }
    if ($("#input-title").val() == ""){
        alert("请输入标题");
        return false;
    }
    if($("#input-content").val() == ""){
        alert("请输入正文");
        return false;
    }
}
function initEdit() {
    // getAllLabels();
    setOldInfo();
}
function setOldInfo() {
    var article_id = $("#article_id").html();
    var article_tag = $("#article_tag").html();
    var article_kind = $("#article_kind").html();

    var is_private = $("#is_private").html();
    article_kind = tarnsformKind(article_kind);
    if(is_private === "yes"){
        $("#isPrivate").attr("checked",true);
    }
    $(".kind-radio").each(function () {
        if(this.value == article_kind){
            $(this).attr("checked",true);
        }
    })
    getAndSetAllLabels(article_tag);
}
function tarnsformKind(str) {
    if (str === "学习笔记"){
        return 1;
    } else if(str === "技术博客"){
        return 2;
    } else if(str === "算法"){
        return 3;
    } else if(str === "Bug分析"){
        return 4;
    } else if(str === "面试相关"){
        return 5;
    } else if(str === "项目实战"){
        return 6;
    } else if(str === "其他"){
        return 7;
    }


}
/**
 * 发送Ajax请求,获得所有的个性标签并渲染
 */
function getAndSetAllLabels(str) {

    $.ajax({
        type:"GET",
        url:"getMyAllLabels.do",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        success:function (result) {
            //将返回的结果转换为对象数组
            var objarr = eval("("+result+")");
            for (var i = 0;i < objarr.length;i++){
                if (str.indexOf(objarr[i])!= -1){
                    var li = $("<li class='label-li'>" +
                        "<label><span><xmp class = 'xmp'>"+objarr[i]+"</xmp></span>" +
                        "<input class='label-checkbox' type='checkbox' checked='checked' name='blog_labels' value='"+objarr[i]+"'>" +
                        "</label></li>");
                    $("#my_tag").append(li);
                }else{
                    var li = $("<li class='label-li'>" +
                        "<label><span><xmp class = 'xmp'>"+objarr[i]+"</xmp></span>" +
                        "<input class='label-checkbox' type='checkbox' name='blog_labels' value='"+objarr[i]+"'>" +
                        "</label></li>");
                    $("#my_tag").append(li);
                }

            }
            if(objarr.length == 0){
                var li = $("<li class='label-li'><span>你还没有添加过个性标签哦~</span></li>");
                $("#my_tag").append(li);
            }
        },
        error:function (msg) {
            console.log("获取个性标签失败!");
        }
    });
}
