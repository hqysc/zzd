<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="stylesheet" href="${ctx!}/css/userHelp.css">
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
            <li><a>帮助中心</a></li>
        </ul>
    </div>

    <!-- 内容 -->
    <div class="main">
        <div class="help">
            <!-- 类目 -->
            <div class="list">
                <div class="inner">
                    <!-- title -->
                    <div class="title">
                        <h2>帮助中心</h2>
                        <p>本站使用说明</p>
                    </div>

                    <!-- item -->
                    <#list userHelpList as userHelp>
                        <div class="item">
                            <h2>${userHelp.helpTitle}</h2>
                            <#list userHelp.helpItemList as helpItem>
                                <a href="javascript:void(0);" onclick="loadItem(${helpItem.id});"><p>${helpItem.sortId}、${helpItem.itemName}</p></a>
                            </#list>
                        </div>
                    </#list>

                    <!-- item -->
                    <div class="item">
                        <a href="#"><p>联系我们</p></a>
                    </div>
                </div>
            </div>
            <!-- 内容 -->
            <div class="content">
                <div class="inner" id="itemContent"></div>
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
        $(document).ready(function() {
            loadItem(1);
        });

        function loadItem(id) {
            var url = '${ctx!}/help/item/' + id;

            $.ajax({
                type: "get",
                url: url,
                contentType:"application/json;charset=utf-8",
                data:{},
                success: function(data) {
                    var itemContent = $("#itemContent");
                    itemContent.html("");
                    itemContent.append(data.itemContent);
                }
            });
        }
    </script>
</body>

</html>