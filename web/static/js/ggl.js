    //1.老三步。获取相关元素。
    var box = document.getElementById("notice-all");
    var ul = box.children[0].children[0];
    var ol = box.children[0].children[1];
    var ulLiArr = ul.children;
    //2.补齐相互盒子
 
        //1.复制第一张图片所在的li，填入所在的ul中。
    var newLi = ulLiArr[0].cloneNode(true);
    ul.appendChild(newLi);
        //2.生成相关的ol中的li。
    for(var i=0;i<ulLiArr.length-1;i++){
        var newOlLi = document.createElement("li");
        newOlLi.innerHTML = i+1;
        ol.appendChild(newOlLi);
    }
        //3.点亮第一个ol中的li。
    var olLiArr = ol.children;
    ol.children[0].className = "current";
 
    //3.鼠标放到小方块儿上，轮播图片。
    for(var i=0;i<olLiArr.length;i++){
        olLiArr[i].index = i;
        olLiArr[i].onmouseover = function () {
            for(var j=0;j<olLiArr.length;j++){
                olLiArr[j].className = "";
            }
            olLiArr[this.index].className = "current";
            animate(ul,-this.index*ul.children[0].offsetWidth);
            key = square = this.index;
        }
    }
    //4.添加定时器。
    var timer = null;
    var key = 0;
    var square = 0;
    timer = setInterval(autoPlay,3000);
 
    function autoPlay(){
        key++;
        square++;
        if(key>olLiArr.length){
            key=1;
            ul.style.left = 0;
        }
        animate(ul,-key*ul.children[0].offsetWidth);
 
        square = square>olLiArr.length-1?0:square;
        for(var j=0;j<olLiArr.length;j++){
            olLiArr[j].className = "";
        }
        olLiArr[square].className = "current";
    }
 
    //5.鼠标移开开启定时器，鼠标放上去开启定时器。
    box.onmouseover = function () {
        clearInterval(timer);
    }
    box.onmouseout = function () {
        timer = setInterval(autoPlay,3000);
    }
    //  基本封装
    function animate(obj,target) {
        clearInterval(obj.timer);
        var speed = obj.offsetLeft < target ? 15 : -15;
        obj.timer = setInterval(function() {
            var result = target - obj.offsetLeft;
            obj.style.left = obj.offsetLeft + speed  + "px";
            if(Math.abs(result) <= 15) {
                obj.style.left = target + "px";
                clearInterval(obj.timer);
            }
        },10);
    }