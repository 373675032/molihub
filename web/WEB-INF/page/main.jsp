<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/8/5
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>论坛主页-Molihub-程序猿的学习论坛</title>
    <link rel="icon" href="<%=request.getContextPath() %>/static/images/pagelogo.png" type="image/x-icon"/>
    <link href="https://cdn.bootcss.com/font-awesome/5.8.0/css/all.css" rel="stylesheet" />
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/main.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/rili.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/static/style/ggl.css">
    <link rel="stylesheet" href="<%=request.getContextPath() %>/plug-ins/jqueryPagination/css/jquery.pagination.css" />
    <script src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/marked/marked.min.js"></script>
    <script src="<%=request.getContextPath() %>/static/js/main.js"></script>
    <script src="<%=request.getContextPath() %>/static/js/search.js"></script>
    <script>
        $(function () {
            $(window).scroll(function () {
                //获得滑轮滚动的距离
                if($(window).scrollTop() >= 300){
                    $("#return-top").show(500);
                }else{
                    $("#return-top").hide(500);
                }
            });
        })
    </script>
</head>
<body  onload="init()">
    <%@ include file="basepage/nav.jsp" %>
    <%@ include file="basepage/sidebar.jsp" %>
    <div class="main-left">
        <p class="two"></p>
        <div class="div">
            <div class="right-left-content">
                <div class="notice-box">
                    <img src="<%=request.getContextPath() %>/static/images/gonggao.png">
                    <div class="notice-title">MoliHub-公告</div>
                    <div class="notice-all" id='notice-all'>

                        <div class="notice-screen" id="notice-screen">
                            <ul id="notice-ul">
                                <li>
                                    <br>
                                    <h3>欢迎来到 MoliHub!</h3>
                                    <br>
                                    <h3>初次来到点击<a href="readArticleById.do?article_id=1">这里</a>哦</h3>
                                </li>
                                <li >
                                    <p style="color: #0B173B;line-height: 1.5">
                                        本论坛采用的是MD文本编辑器.注意当编写博客时需要添加HTML代码时,请使用将代码插入到行内代码
                                        或者预格式文本或者代码块.请勿直接在内容中书写HTML代码,否则可能会导致文章
                                        内容无法显示的问题,感谢您的谅解!
                                    </p>
                                </li>
                                <li id="lover-li">
                                    <h3>网站作者:</h3>
                                    <h4>莫提（QQ:373675032）</h4>
                                    <br>
                                    <h3>微信公众号:</h3>
                                    <h4>Molihub技术社区</h4>
                                </li>

                            </ul>
                            <ol></ol>
                            <!-- <div id="arr"><span id="left"><</span><span id="right">></span></div> -->
                        </div>
                    </div>
                </div>
                <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/ggl.js"></script>
                <div class ="read-top">
                    <img src="<%=request.getContextPath() %>/static/images/top1.png">
                    <div>
                        <p>阅读排行榜</p>
                    </div>
                    <div>
                        <ul id="read-top-ul">
                        </ul>
                    </div>
                </div>

                <div class ="like-top">
                    <img src="<%=request.getContextPath() %>/static/images/top2.png">
                    <div>
                        <p>人气排行榜</p>
                    </div>
                    <div>
                        <ul id="like-top-ul">
                        </ul>
                    </div>
                </div>
                <div class = "kinds">
                    <img src="<%=request.getContextPath() %>/static/images/kind.png">
                    <div>
                        <p>文章分类</p>
                    </div>
                    <div class="kinds-div">
                        <span><a href="javaScript:searchKind('学习笔记')">学习笔记</a></span>
                        <span><a href="javaScript:searchKind('技术博客')">技术博客</a></span>
                        <span><a href="javaScript:searchKind('算法')">算法</a></span>
                        <span><a href="javaScript:searchKind('Bug分析')">Bug分析</a></span>
                        <span><a href="javaScript:searchKind('面试相关')">面试相关</a></span>
                        <span><a href="javaScript:searchKind('项目实战')">项目实战</a></span>
                        <span><a href="javaScript:searchKind('其他')">其他</a></span>
                    </div>
                </div>
                <div class="baseInfo">
                    <img src="<%=request.getContextPath() %>/static/images/jibenqingkuang.png">
                    <div>
                        <p class="baseInfo-title">基本情况</p>
                    </div>
                    <div>
                        <p>用户-<span id="userC"></span></p>
                    </div>
                    <div>
                        <p>文章-<span id="articleC"></span></p>
                    </div>
                </div>
                <div id="datetime-dox">
                    <div class="calender">
                        <div class="title">
                            <h1 class="green" id="calendar-title">Month</h1>
                            <h2 class="green" id="calendar-year">Year</h2>
                            <a href="" id = "prev"></a>
                            <a href="" id = "next"></a>
                        </div>
                        <div class = "body">
                            <div class="lightgrey body-list">
                                <ul>
                                    <li>日</li>
                                    <li>一</li>
                                    <li>二</li>
                                    <li>三</li>
                                    <li>四</li>
                                    <li>五</li>
                                    <li>六</li>
                                </ul>
                            </div>
                        </div>
                        <div class="darkgrey body-list">
                            <ul id="days">
                            </ul>
                        </div>
                        <!-- 闹钟 -->
                        <iframe id="iframe" src="http://www.17sucai.com/preview/1636013/2019-07-26/时钟特效/html/demo.html" frameborder="0"></iframe>
                    </div>
                    <script type="text/javascript" src="<%=request.getContextPath() %>/static/js/rili.js"></script>
                </div>
            </div>
        </div>
    </div>
    <div class="main-middle">
        <div class="one">
            <p id="path"><%=request.getContextPath() %></p>
        </div>
            <ul id="article-ul">
            </ul>

        <div class="page-box">
            <div id="pagination3" class="page fl"></div>
        </div>
        <p id="pageNum"></p>
        <div class="copyright">
            <p>&copy;2019 Molihub · 莫提 版权所有  备案号: <a href="http://www.beian.miit.gov.cn/">冀ICP备19025674号-1</a></p>
        </div>
        <script src="<%=request.getContextPath() %>/static/js/jquery-1.12.4.js"></script>
        <script src="<%=request.getContextPath() %>/plug-ins/jqueryPagination/js/jquery.pagination.min.js"></script>
        <script>
            $(function() {
                $("#pagination3").pagination({
                    currentPage: 4,
                    totalPage: 16,
                    isShow: true,
                    count: 5,
                    homePageText: "首页",
                    endPageText: "尾页",
                    prevPageText: "上一页",
                    nextPageText: "下一页",
                    callback: function(current) {
                        getArticlesByPage(current);
                        window.location.hash = current;
                        document.body.scrollTop = 0;
                        document.documentElement.scrollTop = 0;
                    }
                });
                setPageNum();
                var pageNum  = $("#pageNum").html();
                pageNum++;
                pageNum--;
                var number = window.location.hash.substring(1)||1;
                number++;
                number--;
                $("#pagination3").pagination("setPage",number,pageNum);
                getArticlesByPage(number);
            });
        </script>
    </div>
    <div class="search-box">
        <input id="search-input" class="search-txt" type="text" placeholder="在这里输入搜索内容..." />
        <a class="search-btn" href="javaScript:search()">
            <i class="fas fa-search"></i>
        </a>
    </div>
    <a id="return-top" href="#"><img title="回到顶部" src="<%=request.getContextPath() %>/static/images/return_top.png"></a>
</body>
</html>
