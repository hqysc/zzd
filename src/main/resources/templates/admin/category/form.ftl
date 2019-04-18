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

    <link href="${ctx!}/custom/css/check.css" rel="stylesheet">

    <style>
        .imagesDiv img {
            width: 160px;
            height: 180px;
            object-fit: cover;
            outline: 1px solid #cdcdcd;
        }
    </style>

</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeInRight">
        <!-- content -->
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>杂志类别</h5>
                    </div>
                    <div class="ibox-content">
                        <!-- form -->
                        <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/category/save">
                            <!-- 类目名称 -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">类目名称：</label>
                                <div class="col-sm-7">
                                    <input id="id" name="id" class="form-control" type="hidden" value="${category.id}">
                                    <input id="catename" name="catename" class="form-control" type="text" autocomplete="off" value="${category.catename}">
                                </div>
                            </div>

                            <#-- 分隔线 -->
                            <div class="hr-line-dashed"></div>

                            <div class="form-group">
                                <label class="col-sm-3 control-label">分类类型：</label>
                                <div class="col-sm-8 col-sm-offset-3">
                                    <div class="check-div">
                                        <label class="cate-radio">
                                            <input id="type" name="type" class="radio" type="radio" <#if category.type==1>checked</#if> checked="true" value="1">
                                            <p>父级分类</p>
                                        </label>
                                        <label class="cate-radio">
                                            <input id="type" name="type" class="radio" type="radio" <#if category.type==2>checked</#if> value="2">
                                            <p>子级分类</p>
                                        </label>
                                    </div>
                                </div>
                            </div>

                            <#-- 分隔线 -->
                            <div class="hr-line-dashed"></div>

                            <!-- 分类选择 -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">父级分类：</label>
                                <div class="col-sm-8 col-sm-offset-3">
                                    <div class="check-div">
                                        <#list categoryList as categorys>
                                            <label class="cate-radio">
                                            <#if category.parentId == categorys.id>
                                                <input type="radio" class="radio" id="parentId" name="parentId" checked="true" value="${categorys.id}">
                                            <#else>
                                                <input type="radio" class="radio" id="parentId" name="parentId" value="${categorys.id}">
                                            </#if>
                                                <p>${categorys.catename}</p>
                                            </label>
                                        </#list>
                                    </div>
                                    <label id="categoryId-error" for="parentId" class="error"></label>
                                    <br>
                                </div>
                                <#-- 填充 -->
                                <input type="radio" class="radio" id="parentId" name="parentId" value="" style="position: absolute; top: -9999px;">
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
                    catename: {
                        required: true,
                    },
                    type: {
                        required: true
                    },
                    /*
                    parentId: {
                        required: true
                    },
                    */
                },
                messages: {},
                submitHandler:function(form){
                    $.ajax({
                        type: "POST",
                        url: "${ctx!}/admin/category/save",
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