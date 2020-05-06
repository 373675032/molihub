<%--
  Created by IntelliJ IDEA.
  User: 37367
  Date: 2019/8/7
  Time: 0:45
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <link rel="icon" href="<%=request.getContextPath() %>/static/images/pagelogo.png" type="image/x-icon"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta http-equiv="refresh" content="4; url='<%=request.getContextPath() %>/toMyHome.do'">
    <meta http-equiv="X-UA-Compatible" content="ie=edge" />
    <title>Loading1</title>
    <style>
        body {
            margin: 0;
            padding: 0;
            background: #f39c12;
        }
        .middle {
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
        }
        .bar {
            width: 6px;
            height: 2px;
            background: white;
            display: inline-block;
            transform-origin: bottom center;
            animation: loading 1.5s ease-in-out infinite;
        }
        .bar1 {
            animation-delay: 0.1s;
        }
        .bar2 {
            animation-delay: 0.2s;
        }
        .bar3 {
            animation-delay: 0.3s;
        }
        .bar4 {
            animation-delay: 0.4s;
        }
        .bar5 {
            animation-delay: 0.5s;
        }
        .bar6 {
            animation-delay: 0.6s;
        }
        .bar7 {
            animation-delay: 0.7s;
        }
        .bar8 {
            animation-delay: 0.8s;
        }
        @keyframes loading {
            0% {
                transform: scaleY(0.1);
                background: #fff;
            }
            50% {
                transform: scaleY(30);
                background: #2980b9;
            }
            100% {
                transform: scaleY(0.1);
                background: transparent;
            }
        }
        audio{
            display: none;
        }
    </style>
</head>
<body>
<div class="middle">
    <div class="bar bar1"></div>
    <div class="bar bar2"></div>
    <div class="bar bar3"></div>
    <div class="bar bar4"></div>
    <div class="bar bar5"></div>
    <div class="bar bar6"></div>
    <div class="bar bar7"></div>
    <div class="bar bar8"></div>
</div>
<audio controls autoplay="autoplay">
    <source src="<%=request.getContextPath() %>/static/music/publish.mp3" type="audio/mpeg">
</audio>
</body>
</html>
