<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" >
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>title</title>

    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link rel="shortcut icon" href="${ctx!}/hAdmin/favicon.ico">
    <link href="${ctx!}/hAdmin/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${ctx!}/hAdmin/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/style.css?v=4.1.0" rel="stylesheet">
    <link href="${ctx!}/custom/css/check.css" rel="stylesheet">


</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-title">
                        <h5>图片管理</h5>
                    </div>
                    <div class="ibox-content" >
                        <p>
                            <div class="row">
                                <#-- 添加 -->
                                <div class="col-md-2 col-sm-2">
                                    <button class="btn btn-success" id="finish" type="button"><i class="fa fa-plus"></i>&nbsp;添加</button>
                                </div>
                                <#-- 搜索 -->
                                <form id="frm" action="${ctx!}/admin/product/imageList">
                                    <div class="col-md-4 col-sm-4 col-sm-offset-4">
                                        <input type="text" class="form-control" id="searchText" name="searchText" autocomplete="off">
                                    </div>
                                    <div class="col-md-2 col-sm-2">
                                        <button class="btn btn-warning" id="finish" type="submit"><i class="fa fa-plus"></i>&nbsp;搜索</button>
                                    </div>
                                </form>
                            </div>
                        </p>
                        <p></p>
                        <!-- 图片列表 -->
                        <div class="ibox-content" style="overflow-y:scroll; height: 450px;">
                            <#list galleryList as gallery >
                                <div class="col-md-3 col-sm-3 col-xs-6">
                                    <label class="picker">
                                        <input type="checkbox" class="picker-check" value="${gallery.id}">
                                        <img src="${gallery.thumbImageSrc}" draggable="false"/>
                                    </label>
                                    <p class="small text-center">${gallery.imageName}</p>
                                </div>
                            </#list>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!--&lt;!&ndash; 全局js &ndash;&gt;-->
    <script src="${ctx!}/hAdmin/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/hAdmin/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/hAdmin/js/content.js?v=1.0.0"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="${ctx!}/hAdmin/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx!}/hAdmin/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${ctx!}/hAdmin/js/plugins/layer/layer.min.js"></script>
    <script src="${ctx!}/hAdmin/js/plugins/layer/laydate/laydate.js"></script>

    <script type="text/javascript">

        $(document).ready(function(){
            $("input[class='picker-check']").each(function(key, val){
                parent.$("input[class='sort-gallery']").each(function(key2, pval){
                    if($(val).val() == $(pval).val()) {
                        $(val).attr("disabled",true);
                        $(val).siblings('img').css("outline","5px solid #f05050");
                    }
                });
            });
        })

        $('#finish').on('click', function(){
            var index = parent.layer.getFrameIndex(window.name); //获取窗口索引
            var id_array = new Array();
            var name_array = new Array();
            var imgSrc = "";

            // 把按钮先移出然后挪到最后
            parent.$(".no-sort-li").remove();

            $("input[class='picker-check']:checked").map(function() {
                id_array.push($(this).val());
                name_array.push($(this).parent().next().text());
            });

            for(var i = 0; i < id_array.length; i++) {
                imgSrc = "/images/" + name_array[i];
                parent.$(".img-sortable").append("<li class='sort-li'>"
                        + "<button class='sort-delete'><span class='glyphicon glyphicon-remove-circle'></span></button>"
                        + '<img src="' + imgSrc + '">'
                        + "<p>" + name_array[i] + "</p>"
                        + "<input class='sort-gallery' type='hidden' value='"+ id_array[i] +"'>"
                        + "</li>"
                );
                console.info(id_array[i]);
                console.info(name_array[i]);
            }

            parent.$(".img-sortable").append("<li class='no-sort-li'>"
                    + "<button onclick='chooseImgs()'>"
                    + "<span class='glyphicon glyphicon-plus-sign'></span><p>添加图片</p>"
                    + "</button>"
                    + "</li>"
            );

            parent.layer.close(index);

        });
    </script>
</body>
</html>