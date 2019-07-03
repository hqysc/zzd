<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${ctx!}/css/index.css">
    <link rel="stylesheet" href="${ctx!}/css/productList.css">
    <link rel="stylesheet" href="${ctx!}/css/base.css">
    <link rel="stylesheet" href="${ctx!}/css/banner/banner.css">
</head>
<body>
    <!-- 导航 -->
    <#include "/zzd/common.ftl">
    <!-- 轮播图 -->
    <div class="banner">
        <div class="pb-carouselWarp advert-banner">
            <ul class="pb-carousel">
                <#list advertList as advert>
                    <!-- 第一个要加 'pb-this' -->
                    <#if advert_index == 0>
                        <li class="pb-this" style="background-color: ${advert.advertBgColor}"><a href="${advert.advertUrl}"><img src="${advert.gallery.imageSrc}" alt=""/></a></li>
                    <#else>
                        <li style="background-color: ${advert.advertBgColor}"><a href="${advert.advertUrl}"><img src="${advert.gallery.imageSrc}" alt=""/></a></li>
                    </#if>
                </#list>
            </ul>
            <ul class="pb-carousel-ind">
                <#list advertList as advert>
                    <!-- 第一个要加 'pb-this' -->
                    <#if advert_index == 0>
                        <li class="pb-this"></li>
                    <#else>
                        <li></li>
                    </#if>
                </#list>
            </ul>
            <button class="pb-arrow pb-arrow-prev"></button>
            <button class="pb-arrow pb-arrow-next" id="aa"></button>
        </div>
    </div>

    <!-- 内容 -->
    <!-- 标签导航 -->
    <div class="tab-nav">
        <a href="javascript:void(0);" id="a-hot" onclick="loadHot();">精选推荐</a>
        <a href="javascript:void(0);" id="a-new" onclick="loadUpdate();">最新更新</a>
        <a href="javascript:void(0);" id="a-free" onclick="loadFree();">限时免费</a>
    </div>

    <!-- main 内容 -->
    <div class="main rt">
        <#-- 优选推荐 -->
        <div class="main-list" id="hotList"></div>
        <#-- 推荐 -->
        <div class="main-list" id="indexList"></div>
    </div>

    <!-- 底部 -->
    <#include "/zzd/bottom.ftl">

    <#-- js -->
    <#--<script src="${ctx!}/bootstrap/js/bootstrap.min.js"></script>-->
    <script src="${ctx!}/hAdmin/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/hAdmin/js/plugins/layer/layer.min.js"></script>

    <script src="${ctx!}/hAdmin/js/content.js?v=1.0.0"></script>
    <script src="${ctx!}/js/carousel.min.js" charset="utf-8"></script>
    <!-- 轮播图 -->

    <script>
        carousel(
            $('.advert-banner'),	//必选， 要轮播模块(id/class/tagname均可)，必须为jQuery元素
            {
                type: 'fade',	//可选，默认左右(leftright) - 'leftright' / 'updown' / 'fade' (左右/上下/渐隐渐现)
                arrowtype: 'move',	//可选，默认一直显示 - 'move' / 'none'	(鼠标移上显示 / 不显示 )
                autoplay: true,	//可选，默认true - true / false (开启轮播/关闭轮播)
                time:4000	//可选，默认3000
            }
        );

        $(document).ready(function() {
            loadHot();
            loadIndex();
        });

        function loadHot() {
            changeColor("#a6a6a6", "#4C4C4C", "#4C4C4C");
            var url = '${ctx!}/product/hotList';
            loadList(url);
        }

        function loadUpdate() {
            changeColor("#4C4C4C", "#a6a6a6", "#4C4C4C");
            var url = '${ctx!}/product/updateList';
            loadList(url);
        }

        function loadFree() {
            changeColor("#4C4C4C", "#4C4C4C", "#a6a6a6");
            var url = '${ctx!}/product/freeList';
            loadList(url);
        }

        /*
         改变颜色
        */
        function changeColor(hotColor, newColor, freeColor) {
            $("#a-hot").css("color", hotColor);
            $("#a-new").css("color", newColor);
            $("#a-free").css("color", freeColor);
        }

        function loadList(url) {
            $.ajax({
                type: "get",
                url: url,
                contentType:"application/json;charset=utf-8",
                data:{},
                success: function(data) {
                    var hotListDiv = $("#hotList");
                    hotListDiv.html("");
                    $.each(data, function (i, data) {
                        var itemDiv = $("<div class='item'></div>");
                        var innerDiv = $("<div class='item-inner'></div>");
                        itemDiv.append(innerDiv);

                        // productImage
                        if(data.gallery != null) {
                            innerDiv.append(
                                    "<div class='item-img'>" +
                                        "<a href='/product/detail/" + data.id + "' target='_blank'>" +
                                            '<img src="' + data.gallery.thumbImageSrc + '" alt="' + data.gallery.imageName + '" >' +
                                        "</a>" +
                                    "</div>"
                            );
                        }

                        if(data.tagStatus == 1) {
                            loadTag(data, innerDiv, "#FF0086", "New");
                        }else {
                            if(data.isFree == 1) {
                                loadTag(data, innerDiv, "#FF7F00", "免费");
                            }else {
                                loadTag(data, innerDiv, "#FF7F00", "VIP");
                            }
                        }

                        innerDiv.append(
                                "<div class='item-bar'>" +
                                    "<div class='bar-content'>" +
                                        "<div class='info'><a href='/product/detail/" + data.id + "'>" + data.productName + "</a></div>" +
                                        "<div class='join'>" +
                                            "<a href='javascript:addToCart(" + data.id + ")' target='_blank'><div class='btn-join'></div></a>" +
                                        "</div>" +
                                    "</div>" +
                                "</div>"
                        );
                        hotListDiv.append(itemDiv);
                    });
                }
            });
        }

        /*
        加载推荐分类
        */
        function loadIndex() {
            $.ajax({
                type: "get",
                url: '${ctx!}/product/indexList',
                contentType:"application/json;charset=utf-8",
                data:{},
                success: function(data) {
                    var indexListDiv = $("#indexList");
                    indexListDiv.html("");
                    // all
                    $.each(data, function (index, data) {
                        // title
                        indexListDiv.append("<div class='list-title'><h2>" + data.catename + "</h2><p>" + data.catename + "</p></div>");
                        // cate
                        $.each(data, function (i, cate) {
                            if(i == "product") {
                                // product
                                $.each(cate, function (i2, product) {
                                    var itemDiv = $("<div class='item'></div>");
                                    var innerDiv = $("<div class='item-inner'></div>");
                                    itemDiv.append(innerDiv);
                                    // product

                                    if(product.gallery != null) {
                                        innerDiv.append(
                                                "<div class='item-img'>" +
                                                    "<a href='/product/detail/" + product.id + "' target='_blank'>" +
                                                        '<img src="' + product.gallery.thumbImageSrc + '" alt="' + product.gallery.imageName + '" >' +
                                                    "</a>" +
                                                "</div>"
                                        );
                                    }

                                    if(product.tagStatus == 1) {
                                        loadTag(product, innerDiv, "#FF0086", "New");
                                    }else {
                                        if(product.isFree == 1) {
                                            loadTag(product, innerDiv, "#FF7F00", "免费");
                                        }else {
                                            loadTag(product, innerDiv, "#FF7F00", "VIP");
                                        }
                                    }

                                    innerDiv.append(
                                            "<div class='item-bar'>" +
                                                "<div class='bar-content'>" +
                                                    "<div class='info'><a href='/product/detail/" + product.id + "' target='_blank'>" + product.productName + "</a></div>" +
                                                    "<div class='join'>" +
                                                        "<a href='javascript:addToCart(" + product.id + ")'><div class='btn-join'></div></a>" +
                                                    "</div>" +
                                                "</div>" +
                                            "</div>"
                                    );
                                    indexListDiv.append(itemDiv);
                                });
                            }
                        });
                        indexListDiv.append("<div class='btn-more'><a href='/product/list?categoryId=" + data.id + "'><p>更多</p></a></div>");
                    });
                }
            });
        }

        /*
            加载标签
        */
        function loadTag(data, divName, bgColor, tagName) {
            divName.append(
                    "<div class='item-tag'>" +
                        "<div class='tag-content'>" +
                            "<div class='tag-flex'>" +
                                "<div class='tag' style='background-color: " + data.years.tagColor + "'>" + data.years.yearsName + "</div>" +
                                "<div class='tag' style='background-color: " + data.type.tagColor + "'>" + data.type.typeName + "</div>" +
                                "<div class='tag' style='background-color: " + data.country.tagColor + "'>" + data.country.countryName + "</div>" +
                                "<div class='tag' style='background-color: " + bgColor + "'>" + tagName + "</div>" +
                            "</div>" +
                        "</div>" +
                    "</div>"
            );
        }

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

    </script>

</body>

</html>