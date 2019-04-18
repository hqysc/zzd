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
    <link rel="stylesheet" href="${ctx!}/css/banner.css">
</head>
<body>
    <!-- 导航 -->
    <#include "/zzd/common.ftl">

    <!-- 轮播图 -->
    <div class="banner">
        <div class="middle_right">
            <div id="lunbobox">
                <div id="toleft"> < </div>
                <div class="lunbo">
                    <#list advertList as advert>
                        <a href="${advert.advertUrl}"><img src="${advert.gallery.imageSrc}"></a>
                    </#list>
                </div>
                <div id="toright"> > </div>
            </div>
        </div>
    </div>

    <!-- 内容 -->
    <!-- 标签导航 -->
    <div class="tab-nav">
        <a href="javascript:void(0);" onclick="loadHot();">精选推荐</a>
        <a href="javascript:void(0);" onclick="loadUpdate();">最新更新</a>
        <a href="javascript:void(0);" onclick="loadFree();">限时免费</a>
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
    <!-- banner -->
    <script src="${ctx!}/custom/js/banner.js"></script>

    <script>
        $(document).ready(function() {
            loadHot();
            loadIndex();
            loadFree();
        });

        function loadHot() {
            var url = '${ctx!}/product/hotList';
            loadList(url);
        }

        function loadUpdate() {
            var url = '${ctx!}/product/updateList';
            loadList(url);
        }

        function loadFree() {
            var url = '${ctx!}/product/freeList';
            loadList(url);
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
                                        "<a href='/product/detail/" + data.id + "'>" +
                                            "<img src='" + data.gallery.thumbImageSrc + "'alt='" + data.gallery.imageName + "'>" +
                                        "</a>" +
                                    "</div>"
                            );
                        }

                        innerDiv.append(
                                "<div class='item-tag'>" +
                                    "<div class='tag-content'>" +
                                        "<div class='tag-flex'>" +
                                            "<div class='tag' style='background-color: " + data.years.tagColor + "'>" + data.years.yearsName + "</div>" +
                                            "<div class='tag' style='background-color: " + data.type.tagColor + "'>" + data.type.typeName + "</div>" +
                                            "<div class='tag' style='background-color: " + data.country.tagColor + "'>" + data.country.countryName + "</div>" +
                                            "<div class='tag' style='background-color: #FF7F00'>New</div>" +
                                        "</div>" +
                                    "</div>" +
                                "</div>" +

                                "<div class='item-bar'>" +
                                    "<div class='bar-content'>" +
                                        "<div class='info'><a href='/product/detail/" + data.id + "'>" + data.productName + "</a></div>" +
                                        "<div class='join'>" +
                                            "<a href='javascript:addToCart(" + data.id + ")'><div class='btn-join'></div></a>" +
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
                        indexListDiv.append("<div class='list-title'><h2>" + data.catename + "</h2></div>");
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
                                                "<a href='/product/detail/" + product.id + "'>" +
                                                "<img src='" + product.gallery.thumbImageSrc + "'alt='" + product.gallery.imageName + "'>" +
                                                "</a>" +
                                                "</div>"
                                        );
                                    }

                                    innerDiv.append(
                                            "<div class='item-tag'>" +
                                                "<div class='tag-content'>" +
                                                    "<div class='tag-flex'>" +
                                                        "<div class='tag' style='background-color: " + product.years.tagColor + "'>" + product.years.yearsName + "</div>" +
                                                        "<div class='tag' style='background-color: " + product.type.tagColor + "'>" + product.type.typeName + "</div>" +
                                                        "<div class='tag' style='background-color: " + product.country.tagColor + "'>" + product.country.countryName + "</div>" +
                                                        "<div class='tag' style='background-color: #FF7F00'>New</div>" +
                                                    "</div>" +
                                                "</div>" +
                                            "</div>" +

                                            "<div class='item-bar'>" +
                                                "<div class='bar-content'>" +
                                                    "<div class='info'><a href='/product/detail/" + product.id + "'>" + product.productName + "</a></div>" +
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