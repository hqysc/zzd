<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${ctx!}/css/base.css">
    <link rel="stylesheet" href="${ctx!}/css/nav.css">
</head>
<body>
    <nav class="nav">
        <div class="inner">
            <div class="left">
                <ul>
                    <a href="/index"><li class="logo"></li></a>
                    <a href="/"><li>首页</li></a>
                    <a href="/product/list"><li>查找杂志</li></a>
                    <a href="/help/index"><li>帮助中心</li></a>
                    <a href=""><li>VIP通道</li></a>
                </ul>
            </div>

            <div class="right">
                <ul>
                    <a href=""><li class="weixin"></li></a>
                    <#if Session.user?exists>
                        <a href="${ctx!}/user/logout"><li>退出登录</li></a>
                        <a href="${ctx!}/cart/list"><li>${Session.user.userName}</li></a>
                        <a href="${ctx!}/cart/list"><li>我的书架</li></a>
                    <#else>
                        <a href="/user/register"><li class="reg">注册</li></a>
                        <a href="${ctx!}/user/login"><li>登录</li></a>
                    </#if>
                    <li>
                        <form method="get" action="${ctx!}/product/search" id="frm">
                            <div class="search">
                                <input type="text" name="searchText" class="searchText">
                                <#--<input type="submit" class="seatch-btn" value=" ">-->
                            </div>
                        </form>
                    </li>
                </ul>
            </div>
        </div>
    </nav>
    <div class="nav-bottoms"></div>

    <#-- js -->
    <#--<script src="${ctx!}/bootstrap/js/bootstrap.min.js"></script>-->
    <script src="${ctx!}/hAdmin/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/hAdmin/js/plugins/layer/layer.min.js"></script>

    <script src="${ctx!}/hAdmin/js/content.js?v=1.0.0"></script>

</body>

</html>