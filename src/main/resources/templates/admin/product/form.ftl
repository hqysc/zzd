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

    <link href="${ctx!}/hAdmin/css/plugins/summernote/summernote.css" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/plugins/summernote/summernote-bs3.css" rel="stylesheet">

    <link href="${ctx!}/hAdmin/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/style.css?v=4.1.0" rel="stylesheet">

    <#-- custom -->
    <link href="${ctx!}/custom/css/sortable.css" rel="stylesheet">
    <link href="${ctx!}/custom/css/check.css" rel="stylesheet">

    <style>
        .image-div {
            height: 300px;
        }
    </style>

</head>
<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeIn">
        <!-- content -->
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>新增杂志</h5>
                    </div>
                    <div class="ibox-content">
                        <!-- form -->
                        <form class="form-horizontal m-t" id="frm" method="post" action="${ctx!}/admin/product/save">
                            <!-- 杂志名称 -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">杂志名称：</label>
                                <div class="col-sm-7">
                                <#-- 隐藏ID -->
                                    <input id="id" name="id" class="form-control" type="hidden" value="${product.id}">
                                    <input id="productName" name="productName" class="form-control" type="text" autocomplete="off" value="${product.productName}">
                                </div>
                            </div>
                            <!-- 价格 -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">价格：</label>
                                <div class="col-sm-3">
                                    <input id="price" name="price" class="form-control" type="text" autocomplete="off" value="${product.price}">
                                </div>
                            </div>
                            <!-- 杂志内含本数 -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">本数：</label>
                                <div class="col-sm-3">
                                    <input id="num" name="num" class="form-control" type="text" autocomplete="off" value="${product.num}">
                                </div>
                            </div>
                            <!-- 杂志文件大小 -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">文件大小（MB）：</label>
                                <div class="col-sm-3">
                                    <input id="fileSize" name="fileSize" class="form-control" type="text" autocomplete="off" value="${product.fileSize}">
                                </div>
                            </div>
                            <!-- 百度云链接 -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">百度云链接：</label>
                                <div class="col-sm-7">
                                    <input id="baiduyunUrl" name="baiduyunUrl" class="form-control" type="text" autocomplete="off" value="${product.baiduyunUrl}">
                                </div>
                            </div>

                            <#-- 分隔线 -->
                            <div class="hr-line-dashed"></div>

                            <!-- 分类选择 -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">杂志分类：</label>
                                <div class="col-sm-8 col-sm-offset-3">
                                    <div class="check-div">
                                            <#list productCategory as category>
                                                <label class="cate-radio">
                                                <#if category.id == product.categoryId>
                                                    <input type="radio" class="radio" name="categoryId" checked="true" value="${category.id}">
                                                <#else>
                                                    <input type="radio" class="radio" name="categoryId" value="${category.id}">
                                                </#if>
                                                    <p>${category.catename}</p>
                                                </label>
                                            </#list>
                                    </div>
                                    <#-- 验证提示 -->
                                    <label id="categoryId-error" for="categoryId" class="error"></label>
                                    <br>
                                </div>
                                <#-- 替补 -->
                                <input type="radio" class="radio" name="categoryId" value="" style="position: absolute; top: -9999px;">
                            </div>

                            <#-- 分隔线 -->
                            <div class="hr-line-dashed"></div>

                            <!-- 年份选择 -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">杂志年份：</label>
                                <div class="col-sm-8 col-sm-offset-3">
                                    <div class="check-div">
                                        <#list yearsList as years>
                                            <label class="cate-radio">
                                            <#if years.id == product.yearsId>
                                                <input type="radio" class="radio" name="yearsId" checked="true" value="${years.id}">
                                            <#else>
                                                <input type="radio" class="radio" name="yearsId" value="${years.id}">
                                            </#if>
                                                <p>${years.yearsName}</p>
                                            </label>
                                        </#list>
                                    </div>
                                    <!-- 验证提示 -->
                                    <label id="yearsId-error" for="yearsId" class="error"></label>
                                    <br>
                                </div>
                                <!-- 替补 -->
                                <input type="radio" class="radio" name="yearsId" value="" style="position: absolute; top: -9999px;">
                            </div>

                            <!-- 分隔线 -->
                            <div class="hr-line-dashed"></div>

                            <!-- 国家语言 选择 -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">国家/语言：</label>
                                <div class="col-sm-8 col-sm-offset-3">
                                    <div class="check-div">
                                            <#list countryList as country>
                                                <label class="cate-radio">
                                                <#if country.id == product.countryId>
                                                    <input type="radio" class="radio" name="countryId" checked="true" value="${country.id}">
                                                <#else>
                                                    <input type="radio" class="radio" name="countryId" value="${country.id}">
                                                </#if>
                                                    <p>${country.countryName}</p>
                                                </label>
                                            </#list>
                                    </div>
                                    <!-- 验证提示 -->
                                    <label id="countryId-error" for="countryId" class="error"></label>
                                    <br>
                                </div>
                                <!-- 替补 -->
                                <input type="radio" class="radio" name="countryId" value="" style="position: absolute; top: -9999px;">
                            </div>

                            <!-- 分隔线 -->
                            <div class="hr-line-dashed"></div>

                            <!-- 类型 选择 -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">类型：</label>
                                <div class="col-sm-8 col-sm-offset-3">
                                    <div class="check-div">
                                            <#list typeList as type>
                                                <label class="cate-radio">
                                                <#if type.id == product.typeId>
                                                    <input type="radio" class="radio" name="typeId" checked="true" value="${type.id}">
                                                <#else>
                                                    <input type="radio" class="radio" name="typeId" value="${type.id}">
                                                </#if>
                                                    <p>${type.typeName}</p>
                                                </label>
                                            </#list>
                                    </div>
                                    <!-- 验证提示 -->
                                    <label id="typeId-error" for="typeId" class="error"></label>
                                    <br>
                                </div>
                                <!-- 替补 -->
                                <input type="radio" class="radio" name="typeId" value="" style="position: absolute; top: -9999px;">
                            </div>

                            <!-- 分隔线 -->
                            <div class="hr-line-dashed"></div>

                            <!-- 杂志描述 选择 -->
                            <div class="form-group">
                                <label class="col-sm-3 control-label">杂志描述：</label>
                                <div class="col-sm-8 col-sm-offset-3">
                                    <div class="check-div">
                                            <#list describeList as describe>
                                                <label class="cate-radio">
                                                <#if describe.id == product.describeId>
                                                    <input type="radio" class="radio" name="describeId" checked="true" value="${describe.id}">
                                                <#else>
                                                    <input type="radio" class="radio" name="describeId" value="${describe.id}">
                                                </#if>
                                                    <p>${describe.describeName}</p>
                                                </label>
                                            </#list>
                                    </div>
                                    <!-- 验证提示 -->
                                    <label id="describeId-error" for="describeId" class="error"></label>
                                    <br>
                                </div>
                                <!-- 替补 -->
                                <input type="radio" class="radio" name="describeId" value="" style="position: absolute; top: -9999px;">
                            </div>

                            <!-- 分隔线 -->
                            <div class="hr-line-dashed"></div>

                            <!-- 杂志图片 -->
                            <div class="form-group">
                                <div class="col-sm-9 col-sm-offset-2">
                                <#-- 图片 -->
                                    <div class="image-input"></div>
                                </div>
                            </div>

                            <!-- 提交 -->
                            <div class="form-group">
                                <div class="col-sm-8 col-sm-offset-3">
                                    <button class="btn btn-primary " type="submit"><i class="fa fa-check"></i>&nbsp;保存</button>
                                <#--<input class="btn btn-primary" type="submit" value="提交">-->
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

    <#-- 杂志图片 -->
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox">
                    <div class="ibox-title">
                        <h5>图片</h5>
                    </div>
                    <div class="ibox-content image-div">
                        <ul class="img-sortable">
                                <#list productImageList as productImage>
                                <#--<p>${productImage.id} ${productImage.gallery.fimageName}</p>-->
                                    <li class="sort-li">
                                        <button class="sort-delete"><span class="glyphicon glyphicon-remove-circle"></span></button>
                                        <img src="${productImage.gallery.thumbImageSrc}" alt="${productImage.gallery.imageName}">
                                        <p>${productImage.gallery.imageName}</p>
                                    <#-- 图片id -->
                                        <input class="sort-gallery" type="hidden" value="${productImage.gallery.id}">
                                    <#-- 关联id -->
                                        <input class="sort-imageId" type="hidden" value="${productImage.id}">
                                    </li>
                                </#list>
                        <#-- 这个display暂时存在 有新元素就remove掉 因为新添加的元素无法sort -->
                            <!--  -->
                            <li class="remove-sort-li" style="display: none;"></li>
                            <li class="no-sort-li"><button onclick="chooseImgs()"><span class="glyphicon glyphicon-plus-sign"></span><p>添加图片</p></button></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>

    <#-- 杂志描述 -->
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>杂志描述</h5>
                    </div>

                    <div class="ibox-content no-padding">
                    <#-- 富文本编辑器 -->
                        <div class="prodesEdit">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${ctx!}/hAdmin/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/hAdmin/js/bootstrap.min.js?v=3.3.6"></script>
    <#--<script src="${ctx!}/hAdmin/js/plugins/jquery-ui/jquery-ui.min.js"></script>-->
    <script src="${ctx!}/hAdmin/js/jquery-ui-1.10.4.min.js"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/hAdmin/js/content.js?v=1.0.0"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="${ctx!}/hAdmin/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx!}/hAdmin/js/plugins/validate/messages_zh.min.js"></script>
    <script src="${ctx!}/hAdmin/js/plugins/layer/layer.min.js"></script>
    <script src="${ctx!}/hAdmin/js/plugins/layer/laydate/laydate.js"></script>

    <!-- SUMMERNOTE -->
    <script src="${ctx!}/hAdmin/js/plugins/summernote/summernote.min.js"></script>
    <script src="${ctx!}/hAdmin/js/plugins/summernote/summernote-zh-CN.js"></script>

    <script type="text/javascript">
        /*
            图片删除
            对于append动态生成的元素要用on
        */
        $(".img-sortable").on('click', ".sort-delete", function() {
            $(this).parent().remove();
            imageUpdate();
        });

        /*
            更新关联图片表单
         */
        function imageUpdate() {
            var imageDiv = $(".image-div");
            var input_image = $(".image-input");

            var idArr = new Array();
            var indexArr = new Array();

            var ids = "";
            var indexs = "";

            // 清空input div
            input_image.html('');

            $(".sort-li").map(function() {
                idArr.push($(this).children('input[type=hidden]').val());
                indexArr.push($(this).index());
            });

            // 给imageDiv加高度 round四舍五入00
            imageDiv.css("height", ((Math.round(idArr.length / 6) * 225) + 300) + "px");

            for(var i = 0; i < idArr.length; i ++ ) {
                input_image.append("<input name='galleryIds' type='hidden' value='" + idArr[i] + "'>");
                input_image.append("<input name='sortIds' type='hidden' value='" + (indexArr[i] + 1) + "'>");

                ids += idArr[i] + ",";
                indexs += indexArr[i] + 1 + ",";
            }
            console.info("   id: " + ids);
            console.info("index: " + indexs);
        }

        $(document).ready(function () {
            // 图片表单更新 先执行一次
            imageUpdate();

            /*
                拖动排序
                jquery sortable
            */
            $('.img-sortable').sortable(
                    { opacity: 0.9 },
                    { tolerance: 'pointer' },
                    { cancel: '.no-sort-li' },
                    { items: "li:not(.no-sort-li)" },
                    { placeholder: "img-sortable-placeh"}
            );
            $(".img-sortable").disableSelection();

            /*
                当排序动作结束时且元素坐标已经发生改变时触发此事件
            */
            $('.img-sortable').sortable({ update:function(event, ui) {
                imageUpdate();
            }});

            /*
                jquery 表单验证
             */
            $("#frm").validate({
                rules: {
                    productName: {
                        required: true,
                    },
                    price: {
                        required: true,
                        number:true
                    },
                    num: {
                        required: true,
                        number:true
                    },
                    fileSize: {
                        required: true,
                        number:true
                    },
                    categoryId: {
                        required: true
                    },
                    yearsId: {
                        required: true
                    },
                    countryId: {
                        required: true
                    },
                    typeId: {
                        required: true
                    },
                    describeId: {
                        required: true
                    },
                    baiduyunUrl: {
                        required: true
                    },
                },
                messages: {},
                submitHandler:function(form) {
                    $.ajax({
                        type: "POST",
                        url: "${ctx!}/admin/product/save",
                        dataType: "json",
                        data: $(form).serialize(),
                        success: function(msg) {
                            layer.msg(msg.message, {time: 500},function(){
                                window.location.href = "${ctx!}/admin/product/index";
                            });
                        }
                    });
                }
            });
        });

        /*
            选择图片
         */
        function chooseImgs() {
            layer.open({
                type: 2,
                title: '选择图片',
                shadeClose: true,
                shade: false,
                area: ['893px', '700px'],
                content: '${ctx!}/admin/product/imageList',
                end: function(index) {
                    // 删掉顶缸的
                    $(".remove-sort-li").remove();
                    // 更新
                    imageUpdate();
                }
            });
        }

    </script>

</body>
</html>