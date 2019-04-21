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
    <link rel="stylesheet" href="${ctx!}/css/bread-crumbs.css">
</head>
<body>
    <nav class="nav">
        <div class="inner">
            <div class="left">
                <ul>
                    <a href="/index"><li class="logo"></li></a>
                    <a href="/"><li>首页</li></a>
                    <a href="/product/list"><li>查找杂志</li></a>
                    <a href="javascript:void(0);" onclick="checkLoginToUrl('/help/index');"><li>帮助中心</li></a>
                    <a href="javascript:void(0);" onclick="checkLoginToUrl('/rechargeHelp/index');"><li>VIP通道</li></a>
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
                        <a href="/user/register"><li>注册</li></a>
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

    <script>
        /*
            直接支付
        */
        function checkLoginToUrl(url) {
            $.get("${ctx!}/user/checkLogin", function (msg) {   // 先判断是否登录
                if(msg.code == 0) { // 已登录
                    window.location.href="${ctx!}" + url;
                }else if(msg.code == -1) {  // 未登录
                    layer.confirm("请先登录", {
                        btn : [ '登录', '取消' ]//按钮
                    }, function(index) {
                        window.location.href="${ctx!}/user/login";
                    });
                }
            });
        }
    </script>

</body>

</html>