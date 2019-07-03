<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <!-- bootstrap -->
    <link rel="stylesheet" href="${ctx!}/css/productList.css">
    <link rel="stylesheet" href="${ctx!}/css/list.css">
    <link rel="stylesheet" href="${ctx!}/css/base.css">
    <link rel="stylesheet" href="${ctx!}/css/nav.css">
    <link rel="stylesheet" href="${ctx!}/css/bottom.css">

    <link rel="stylesheet" href="${ctx!}/custom/css/check.css">
    <link rel="stylesheet" href="${ctx!}/css/page.css">


    <#--<link href="${ctx!}/hAdmin/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">-->
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
            <li><a>杂志列表</a></li>
        </ul>
    </div>

    <div class="main rt">
        <!-- 导航 -->
        <!-- 分类 -->
        <div class="cate">
            <div class="list" id="checkDiv">
                <#-- 分类 -->
                <div class="content">
                    <p class="title">杂志分类：</p>
                    <ul>
                        <#list categoryList as category>
                            <li>
                                <label class="cate-radio">
                                    <input type="checkbox" class="categoryCheck" value="${category.id}">
                                    <p>${category.catename}</p>
                                </label>
                            </li>
                        </#list>
                    </ul>
                </div>

                <#-- 分类 -->
                <div class="content">
                    <p class="title">杂志年份：</p>
                    <ul>
                        <#list yearsList as years>
                            <li>
                                <label class="cate-radio">
                                    <input type="checkbox" class="yearsCheck" value="${years.id}">
                                    <p>${years.yearsName}</p>
                                </label>
                            </li>
                        </#list>
                    </ul>
                </div>

                <#-- 分类 -->
                <div class="content">
                    <p class="title">国家语言：</p>
                    <ul>
                        <#list countryList as country>
                            <li>
                                <label class="cate-radio">
                                    <input type="checkbox" class="countryCheck" value="${country.id}">
                                    <p>${country.countryName}</p>
                                </label>
                            </li>
                        </#list>
                    </ul>
                </div>

                <#-- 分类 -->
                <div class="content">
                    <p class="title">刊期类型：</p>
                    <ul>
                        <#list typeList as type>
                            <li>
                                <label class="cate-radio">
                                    <input type="checkbox" class="typeCheck" value="${type.id}">
                                    <p>${type.typeName}</p>
                                </label>
                            </li>
                        </#list>
                    </ul>
                </div>

                <#-- 特殊选择 -->
                    <div class="content">
                        <p class="title">杂志选项：</p>
                        <ul>
                            <li>
                                <label class="cate-radio">
                                    <input type="checkbox" class="isHotCheck" value="1">
                                    <p>推荐杂志</p>
                                </label>
                            </li>
                            <li>
                                <label class="cate-radio">
                                    <input type="checkbox" class="isFreeCheck" value="1">
                                    <p>免费杂志</p>
                                </label>
                            </li>
                        </ul>
                    </div>

            </div>
            <div class="bottom"></div>
        </div>

        <div class="main-list">
            <#list productList as product>
                <!-- list item -->
                <div class="item">
                    <div class="item-inner">
                        <div class="item-img">
                            <a href="/product/detail/${product.id}" target="_blank">
                                <img src="${product.gallery.thumbImageSrc}" alt="${product.gallery.imageName}">
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
                                    <a href="/product/detail/${product.id}" target="_blank">${product.productName}</a>
                                </div>
                                <div class="join">
                                    <a href="javascript:void(0);" onclick="javascript:addToCart(${product.id})">
                                        <div class="btn-join"></div>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </#list>
        </div>

        <#-- page -->
        <div class="page">
            <ul>
                <!-- 向左 -->
                <#if pageNum gt 1>
                    <a href="javascript:toPage(${pageNum - 1});"><li><span> < </span></li></a>
                </#if>

                <#if pageNum-4 gte 1>
                    <a href="javascript:toPage(1);"><li>1</li></a>
                </#if>

                <!-- 省略号 左 -->
                <#if pageNum-5 gte 1>
                    <a href="javascript:void (0);"><li>...</li></a>
                </#if>

                <!-- list -->
                <#list pageNum-3..pageNum+3 as index>
                    <#-- +3 -->
                    <#if index lte pageTotal && index gte 1>
                        <#if index == pageNum>
                            <a href="javascript:toPage(${index});"><li class="page-selected">${index}</li></a>
                        <#else>
                            <a href="javascript:toPage(${index});"><li>${index}</li></a>
                        </#if>
                    </#if>
                </#list>

                <#if pageNum+5 lte pageTotal>
                    <a href="javascript:void (0);"><li>...</li></a>
                </#if>

                <#if pageNum+4 lte pageTotal>
                    <a href="javascript:toPage(1);"><li>${pageTotal}</li></a>
                </#if>

                <!-- 向右 -->
                <#if pageNum lt pageTotal>
                    <a href="javascript:toPage(${pageNum + 1});"><li><span> > </span></li></a>
                </#if>
            </ul>
        </div>

    </div>

    <!-- 底部 -->
    <#include "/zzd/bottom.ftl">

    <!-- js -->
    <script src="${ctx!}/hAdmin/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/custom/js/getUrl.js"></script>

    <script>
        $(document).ready(function() {
            setChecked('categoryCheck', 'categoryId');
            setChecked('yearsCheck', 'yearsId');
            setChecked('countryCheck', 'countryId');
            setChecked('typeCheck', 'typeId');
            setChecked('isHotCheck', 'isHot');
            setChecked('isFreeCheck', 'isFree');

            $("#checkDiv").change(function() {
                var url = "";
                url = "${ctx!}/product/list" + "?" +
                        getCheckVal('categoryCheck', 'categoryId') +
                        getCheckVal('yearsCheck', 'yearsId') +
                        getCheckVal('countryCheck', 'countryId') +
                        getCheckVal('typeCheck', 'typeId') +
                        getCheckVal('isHotCheck', 'isHot') +
                        getCheckVal('isFreeCheck', 'isFree');
                window.location.href = url;
            });
        });

        /*
            翻页
        */
        function toPage(index) {
            location.href = updateUrlVal("page", index);
        }

        /*
            获取checkbox value
         */
        function getCheckVal(className, param) {
            var array = new Array();
            var ids = "";
            $("input[class=" + className + "]:checked").map(function() {
                array.push($(this).val());
            });

            for(i in array) {
                array[i] = param + "=" + array[i];
                ids += array[i] + "&";
            }

            if(array.length >= 0){
                return ids;
            }else {
                return null;
            }
        }

        // url截取参数 然后设置checked
        function setChecked(className, param) {
            var arrVal = getUrlVal(param);
            for(i in arrVal) {
                $("input[class="+ className + "]").each(function() {
                    if($(this).val() == arrVal[i]) {
                        $(this).prop("checked", true);
                    }
                });
            }
        }

        // 添加 或者 修改 url中参数的值
        function updateUrlVal(name, val) {
            var thisURL = document.location.href;
            // 如果 url中包含这个参数 则修改
            if (thisURL.indexOf(name+'=') > 0) {
                var v = getUrlParam(name);
                if (v != null) {
                    // 是否包含参数
                    thisURL = thisURL.replace(name + '=' + v, name + '=' + val);
                }else {
                    thisURL = thisURL.replace(name + '=', name + '=' + val);
                }
            // 不包含这个参数 则添加
            } else {
                if (thisURL.indexOf("?") > 0) {
                    thisURL = thisURL + "&" + name + "=" + val;
                }else {
                    thisURL = thisURL + "?" + name + "=" + val;
                }
            }
            // location.href = thisURL;
            return thisURL;
        }

        //获取url中的参数
        function getUrlParam(name) {
            var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
            var r = window.location.search.substr(1).match(reg); //匹配目标参数
            if (r != null) return unescape(r[2]); return null; //返回参数值
        }

        // 添加到我的书架
        function addToCart(productId) {
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