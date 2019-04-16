<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${ctx!}/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="${ctx!}/css/productList.css">
    <link rel="stylesheet" href="${ctx!}/css/detail.css">
    <link rel="stylesheet" href="${ctx!}/css/base.css">
    <link rel="stylesheet" href="${ctx!}/css/nav.css">
</head>
<body>
    <!-- 导航 -->
    <#include "/zzd/common.ftl">
    <!-- 面包屑导航 -->
    <#include "/zzd/bread-crumbs.ftl">

    <div class="main rt">
        <!-- 导航 -->
        <div class="detail">
            <!-- left -->
            <div class="left">
                <div class="image">
                    <#list product.productImage as productImage>
                        <#if productImage.sortId == 1>
                            <img id="big-img" src="${productImage.gallery.imageSrc}">
                        </#if>
                    </#list>
                </div>

                <div class="img-list">
                    <#list product.productImage as productImage>
                        <div class="item"><img src="${productImage.gallery.thumbImageSrc}"/></div>
                    </#list>
                </div>
            </div>
            <!-- right -->
            <div class="right">
                <!-- info -->
                <div class="info">
                    <!-- 按钮 -->
                    <div class="btns">
                        <button class="join-btn" onclick="javascript:addToCart(${product.id})"><span class="glyphicon glyphicon-shopping-cart"></span><span>加入书架</span></button>
                        <div class="pay-btn">
                            <button onclick="javascript:pay(${product.id})"><span class="glyphicon glyphicon-download-alt"></span><span>直接下载</span><span>${product.price}书币</span></button>
                            <p>我的书币：1000（1元=10书币）</p>
                        </div>
                    </div>
                    <!-- 信息 -->
                    <div class="content">
                        <#-- 杂志名称 -->
                        <h2>${product.productName}</h2>
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
                <div class="bottom"></div>
            </div>
        </div>
        <!-- 描述 -->
        <div class="explain">
            <div class="describe">
                ${product.describe.content}
            </div>
        </div>
        <!-- 推荐杂志 -->

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
                                    <div class="tag" style="background-color: #FF7F00">New</div>
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
        // 添加到我的书架
        function addToCart(productId) {
            // var cook = $.cookie('UserName');
            $.ajax({
                type: "post",
                dataType: "json",
                url: '${ctx!}/cart/add/' + productId,
                success: function(msg) {
                    console.log(msg.code);
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

        // 直接下载
        function pay(productId) {
            layer.confirm('确认购买吗', {
                btn : [ '支付', '取消' ]//按钮
            }, function(index) {
                $.ajax({
                    type: "post",
                    dataType: "json",
                    url: '${ctx!}/order/pay/' + productId,
                    success: function(msg) {
                        if(msg.code == 0) { // 已登录
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
            });
        }
    </script>
</body>
</html>