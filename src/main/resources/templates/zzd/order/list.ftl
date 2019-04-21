<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="stylesheet" href="${ctx!}/css/order.css">
    <link rel="stylesheet" href="${ctx!}/css/base.css">
    <link rel="stylesheet" href="${ctx!}/css/bottom.css">
</head>

<body class="gray-bg">
    <!-- 导航 -->
    <#include "/zzd/common.ftl">
    <!-- 面包屑导航 -->
    <div class="bread-crumbs">
        <ul>
            <li class="title">当前位置：</li>
            <li><a href="/index">首页</a></li>
            <li> > </li>
            <li><a>购买订单</a></li>
        </ul>
    </div>

    <!-- main -->
    <div class="main">
        <div class="cart">
            <!-- 用户信息 -->
            <div class="user">
                <!-- change page -->
                <div class="switch">
                    <div class="container">
                        <!-- btns -->
                        <div class="btns">
                            <a href="${ctx!}/cart/list"><div class="btn" id="cart-list">已选收藏</div></a>
                            <a href="${ctx!}/order/list"><div class="btn" id="order-list">结算订单</div></a>
                        </div>
                    </div>
                </div>
                <!-- user info -->
                <div class="info">
                    <div class="title"><p>用户信息</p></div>
                    <div class="container">
                        <div class="msg">
                            <p>用户名：${user.userName}</p>
                            <p>等级：普通会员</p>
                            <p>书币：${user.coin}</p>
                        </div>
                        <!-- btns -->
                        <div class="btns">
                            <a href=""><div id="edit-pw" class="btn">修改登录密码</div></a>
                            <a href=""><div id="recharge" class="btn">充值书币</div></a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 书架列表 -->
            <div class="list">
                <!-- list title -->
                <div class="title">
                    <div class="name">名称</div>
                    <div class="price">书币</div>
                    <div class="bdyun-url">百度云链接</div>
                </div>
                <!-- item -->
                <#list orderList as order>
                    <!-- order -->
                    <div class="order-title">
                        <div class="date">${order.orderTime}</div>
                        <div class="info">数目：${order.itemTotal}</div>
                        <div class="info">共计：${order.priceTotal}</div>
                    </div>
                    <#list order.orderItemList as orderItem>
                        <div class="item">
                            <label>
                                <div class="inner">
                                    <!-- image -->
                                    <div class="image">
                                        <a href=""><img src="${orderItem.product.gallery.thumbImageSrc}" /></a>
                                    </div>
                                    <!-- info -->
                                    <div class="info">
                                        <!-- 内容 -->
                                        <div class="content">
                                            <!-- 标题 -->
                                            <div class="name">
                                                <a href="">${orderItem.product.productName}</a>
                                            </div>
                                            <!-- 标签 -->
                                            <div class="tags">
                                                <div class="container">
                                                    <div class="tags-flex">
                                                        <div class="tag" style="background-color: #FF0086">2018</div>
                                                        <div class="tag" style="background-color: #7C66FF">杂志合辑</div>
                                                        <div class="tag" style="background-color: #7F7F7F">西班牙语</div>
                                                        <div class="tag" style="background-color: #FF7F00">New</div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <!-- 时间 -->
                                        <div class="price">${orderItem.product.price}</div>
                                        <!-- 链接 -->
                                        <div class="bdyun-url">
                                            <div class="url-content">
                                                ${orderItem.product.baiduyunUrl}
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </label>
                        </div>
                    </#list>
                </#list>
            </div>
        </div>
    </div>

    <!-- 底部 -->
    <#include "/zzd/bottom.ftl">

    <!-- 全局js -->
    <script src="${ctx!}/hAdmin/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/hAdmin/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/hAdmin/js/content.js?v=1.0.0"></script>
    <script src="${ctx!}/hAdmin/js/plugins/layer/layer.min.js"></script>

    <script>
        $(document).ready(function(){

        });
    </script>

</body>

</html>