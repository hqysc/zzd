<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>title</title>

    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link rel="shortcut icon" href="${ctx!}/hAdmin/favicon.ico">
    <link href="${ctx!}/hAdmin/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${ctx!}/hAdmin/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/style.css?v=4.1.0" rel="stylesheet">

</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <!-- content -->
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>帮助中心类目</h5>
                    </div>
                    <div class="ibox-content">
                        <!-- form -->
                        <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/help/save">
                            <!-- 类目名称 -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">类目名称：</label>
                                <div class="col-sm-7">
                                    <input id="id" name="id" class="form-control" type="hidden" value="${userHelp.id}">
                                    <input id="helpTitle" name="helpTitle" class="form-control" type="text" autocomplete="off" value="${userHelp.helpTitle}">
                                </div>
                            </div>
                            <!-- 序号 -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">序号：</label>
                                <div class="col-sm-7">
                                    <input id="sortId" name="sortId" class="form-control" type="text" autocomplete="off" value="${userHelp.sortId}">
                                </div>
                            </div>
                            <!-- 提交 -->
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary" type="submit">提交</button>
                                </div>
                            </div>

                        </form>

                    </div>
                </div>
            </div>
        </div>
    </div>


    <!-- 全局js -->
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
                rules: {
                    helpTitle: {
                        required: true,
                    },
                    sortId: {
                        required: true,
                    },
                },
                messages: {},
                submitHandler:function(form){
                    $.ajax({
                        type: "POST",
                        url: "${ctx!}/admin/help/save",
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
    </script>

</body>
</html>