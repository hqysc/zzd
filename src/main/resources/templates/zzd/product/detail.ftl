<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${ctx!}/css/productList.css">
    <link rel="stylesheet" href="${ctx!}/css/detail.css">
    <link rel="stylesheet" href="${ctx!}/css/base.css">
</head>
<body>
    <!-- 导航 -->
    <#include "/zzd/common.ftl">
    <!-- 面包屑导航 -->
    <div class="bread-crumbs">
        <ul>
            <li class="title">当前位置：</li>
            <li><a href="/index">首页</a></li>
            <li> > </li>
            <li><a>杂志详情</a></li>
        </ul>
    </div>

    <div class="main rt">
        <!-- 导航 -->
        <div class="detail">
            <!-- left -->
            <div class="left">
                <!-- 大图 -->
                <div class="image">
                    <#list product.productImage as productImage>
                        <#if productImage.sortId == 1>
                            <img id="big-img" src="${productImage.gallery.imageSrc}">
                        </#if>
                    </#list>
                </div>
                <!-- 小图 -->
                <div class="img-list">
                    <#list product.productImage as productImage>
                        <div class="item"><img class="product-images" src="${productImage.gallery.imageSrc}"/></div>
                    </#list>
                </div>
            </div>
            <!-- right -->
            <div class="right">
                <!-- info -->
                <div class="info">
                    <!-- 按钮 -->
                    <div class="btns">
                        <button class="join-btn" onclick="javascript:addToCart(${product.id})"><span>加入书架</span></button>
                        <div class="pay-btn">
                            <button onclick="javascript:pay(${product.id})"><span class="glyphicon glyphicon-download-alt"></span><span>直接下载</span><span>${product.price}书币</span></button>
                            <p>我的书币：${user.coin}（1元=10书币）</p>
                        </div>
                    </div>
                    <!-- 信息 -->
                    <div class="content">
                        <#-- 杂志名称 -->
                        <h2 id="productName">${product.productName}</h2>
                        <!-- 标签 -->
                            <div class="info-tag">
                            <div class="tag" style="background-color: ${product.years.tagColor}">${product.years.yearsName}</div>
                            <div class="tag" style="background-color: #7C66FF">${product.type.typeName}</div>
                            <div class="tag" style="background-color: ${product.country.tagColor}">${product.country.countryName}</div>
                            <div class="tag" style="background-color: #FF7F00">New</div>
                        </div>
                        <div class="msg">
                            <p>文件格式：高清PDF</p>
                            <p>文件大小：${product.fileSize}&nbsp;MB</p>
                            <p>阅读方式：电脑、平板、手机</p>
                            <p>支持系统：Windows/MacOS/Andorid/iOS</p>
                            <p>阅读软件：Adobe Reader、其他PDF阅读器</p>
                            <p>下载方式：百度网盘</p>
                            <p>更新时间：${product.updateTime?string('yyyy-MM-dd')}</p>
                        </div>
                    </div>
                </div>
                <!-- 杂志广告 -->
                <div class="advert">
                    <a href="${advert.advertUrl}">
                        <img src="${advert.gallery.imageSrc}" alt="">
                    </a>
                </div>
            </div>
        </div>
        <!-- 描述 -->
        <div class="explain">
            <div class="describe">
                ${product.describe.content}
            </div>
        </div>

        <!-- 推荐杂志 -->
        <p class="recommend-title">优选推荐</p>
        <div class="main-list">
            <#list productList as product>
                <!-- list item -->
                <div class="item">
                    <div class="item-inner">

                        <div class="item-img">
                            <a href="/product/detail/${product.id}">
                                <img src="${product.gallery.imageSrc}" alt="${product.gallery.imageName}">
                            </a>
                        </div>
                        <div class="item-tag">
                            <div class="tag-content">
                                <div class="tag-flex">
                                    <div class="tag" style="background-color: ${product.years.tagColor}">${product.years.yearsName}</div>
                                    <div class="tag" style="background-color: ${product.type.tagColor}">${product.type.typeName}</div>
                                    <div class="tag" style="background-color: ${product.country.tagColor}">${product.country.countryName}</div>
                                    <#-- tag -->
                                    <#if product.tagStatus == 1>
                                        <div class="tag" style="background-color: #FF0086">New</div>
                                    <#else>
                                        <#if product.isFree == 1>
                                            <div class="tag" style="background-color: #FF7F00">免费</div>
                                        <#else>
                                            <div class="tag" style="background-color: #FF7F00">VIP</div>
                                        </#if>
                                    </#if>
                                </div>
                            </div>
                        </div>
                        <div class="item-bar">
                            <div class="bar-content">
                                <div class="info">
                                    <a href="/product/detail/${product.id}">
                                        <#if product.productName?length lt 40>
                                            ${product.productName}
                                        <#else>
                                            ${product.productName[0..40]}
                                        </#if>
                                    </a>
                                </div>
                                <div class="join">
                                    <a href="">
                                        <div class="btn-join"></div>
                                    </a>
                                </div>
                            </div>
                        </div>

                    </div>
                </div>
            </#list>
        </div>
    </div>

    <!-- 底部 -->
    <#include "/zzd/bottom.ftl">

    <!-- js -->
    <script src="${ctx!}/hAdmin/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/hAdmin/js/plugins/layer/layer.min.js"></script>
    <script>
        var bigImgSrc = $("#big-img").attr("src");
        var bigImg = $("#big-img")
        console.log(bigImgSrc);

        $(".product-images").mouseover(function(){
            bigImg.attr("src", $(this).attr("src"));
        });

        $(".product-images").mouseout(function(){
            bigImg.attr("src", bigImgSrc);
        });

        // 添加到我的书架
        function addToCart(productId) {
            // var cook = $.cookie('UserName');
            $.ajax({
                type: "post",
                dataType: "json",
                url: '${ctx!}/cart/add/' + productId,
                success: function(msg) {
                    if(msg.code == 0) {
                        layer.msg(msg.message, {time: 1000});
                    }else if(msg.code == -1) {
                        layer.confirm('请先登录', {
                            btn : [ '登录', '取消' ]//按钮
                        }, function(index) {
                            window.location.href="${ctx!}/user/login";
                        });
                    }
                }
            });
        }

        /*
            支付信息
        */
        var payDiv =
                    "<p>" + $("#productName").html() + "</p>" +
                    "<p>" + "价格：" + ${product.price} + "</p>" +
                    "<p>" + "我的书币：" + ${user.coin} + "</p>";

        /*
            直接支付
        */
        function pay(productId) {
            $.get("${ctx!}/user/checkLogin", function (msg) {   // 先判断是否登录
                if(msg.code == 0) { // 已登录
                    $.post("${ctx!}/order/checkPayCoin/" + productId, function (msg) {   // 直接支付时检查书币余额
                        if(msg.data == 1) { // 余额可支付
                            layer.confirm(payDiv, {    // 是否支付
                                btn : [ '支付', '取消' ]    // 按钮
                            }, function(index) {
                                $.post("${ctx!}/order/pay/" + productId, function (msg) {
                                    if(msg.data == 0) { // 支付成功
                                        layer.msg("支付成功", {time: 1000},function() {
                                            window.location.href="${ctx!}/order/list";
                                        });
                                    }else { // 支付失败
                                        layer.msg("支付失败", {time: 1000});
                                    }
                                });
                            });
                        }else if(msg.data == 0) {   // 书币不足 充值
                            layer.confirm('书币不足，是否充值', {
                                btn : [ '充值', '取消' ]    // 按钮
                            }, function(index) {
                                // window.location.href="${ctx!}/user/login";
                                layer.msg("书币不足，请充值", {time: 1000});
                            });
                        }else { // 支付失败
                            layer.msg("支付失败", {time: 1000});
                        }
                    });
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