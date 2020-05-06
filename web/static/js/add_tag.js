function init() {
    initTags();
}
function initTags() {
    $.ajax({
        type:"GET",
        url:"getMyAllLabelsAndArticleCount.do",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        success:function (result) {

            //将返回的结果转换为对象数组
            var objarr = eval("("+result+")");
            // console.log(objarr);
            var i = 0;
            for (var key in objarr) {
                i ++;
                var path = "toUpdateTag.do?name="+key;
                var tr = $("<tr>" +
                    "<td><span class='one'><xmp>"+key+"</xmp></span></td>" +
                    "<td><span class='article-count'>"+objarr[key]+"</span></td>" +
                    "<td><span class='two'><a href='"+path+"'>重命名</a></span></td>" +
                    "<td><span class='three'><a href='javaScript:deleteTag(\""+key+ "\")'>删除</a></span></td>" +
                    "</tr>");
                $("#tags-table").append(tr);
            }
            if(i === 0){
                $("#tags-table").html("");
            }
        },
        error:function (msg) {
            console.log("获取个性标签失败!");
        }
    });
}
function addTag() {
    var content = $("#add-tag-input").val().trim();
    if(content === ""){
        alert("请填写标签名称!");
        return;
    }
    if(!isQualifiedName(content)){
        alert("标签名只能是汉字,字母和下划线!");
        return;
    }
    $.ajax({
        type:"GET",
        url:"addLabel.do",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        data:{name:content},
        success:function (result) {
            if(result === "yes"){
            }else{
                alert("哦吼,这个标签已经存在了哦~");
            }
            location.reload();
        },
        error:function (msg) {
        }
    });
}
function deleteMyTag(name) {
    $.ajax({
        type:"GET",
        url:"deleteLabel.do",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        data:{name:name},
        success:function (result) {
            location.reload();
        },
        error:function (msg) {
        }
    });
}
function updateTag(a) {
    var oldName = a;
    var newName = $("#update-input").val().trim();
    if(newName === ""){
        alert("内容不能为空");
        return;
    }
    $.ajax({
        type:"GET",
        url:"updateLabel.do",
        async: false,
        contentType: "application/json; charset=utf-8",
        dataType: "text",
        data:{
            oldLabel:oldName,
            newLabel:newName
        },
       success:function (result) {
            alert("修改成功!");
            var path = $("#path").html()+"/toTags.do";
            $(location).attr('href', path);
        },
        error:function (msg) {
        }
    });
}
function isQualifiedName(Name) {
    var myreg=new RegExp(/^[\u4e00-\u9fa5\w]{1,40}$/);
    if (!myreg.test(Name)) {
        return false;
    } else {
        return true;
    }
}