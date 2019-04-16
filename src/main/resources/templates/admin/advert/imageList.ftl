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
    <link href="${ctx!}/custom/css/advert-check.css" rel="stylesheet">
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
                                <form id="frm" action="${ctx!}/admin/advert/grant" method ="post">
                                    <div class="col-md-2 col-sm-2">
                                        <input id="id" name="id" type="hidden" value="${advert.id}">
                                        <input id="galleryId" name="galleryId" type="hidden" value="${advert.galleryId}">
                                        <button class="btn btn-success" id="finish" type="submit"><i class="fa fa-plus"></i>&nbsp;添加</button>
                                    </div>
                                </form>
                            </div>
                        </p>
                        <p></p>
                        <!-- 图片列表 -->
                        <div class="ibox-content" style="overflow-y:scroll; height: 450px;">
                            <#list galleryList as gallery>
                                <div class="col-md-3 col-sm-3 col-xs-6">
                                    <label class="picker">
                                        <input type="radio" name="picker-check" class="picker-check" value="${gallery.id}">
                                        <img src="${gallery.imageSrc}" draggable="false"/>
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
        $(document).ready(function () {

            //外部js调用
            $("#frm").validate({
                messages: {},
                submitHandler:function(form){
                    $.ajax({
                        type: "POST",
                        url: "${ctx!}/admin/advert/grant",
                        dataType: "json",
                        data: $(form).serialize(),
                        success: function(msg){
                            layer.msg(msg.message, {time: 500},function(){
                                var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                                parent.layer.close(index);
                            });
                        }
                    });
                }
            });
        });

        $('.picker').change(function() {
            var galleryId = $("input[class='picker-check']:checked").val();
            $("#galleryId").val(galleryId);
        });

        function selectChecked() {
            if($("#galleryId").val() != null) {
                var galleryId = $("#galleryId").val();
                $("input[class='picker-check'][value="+ galleryId + "]").attr("checked", true);
            }
        }
    </script>
</body>
</html>