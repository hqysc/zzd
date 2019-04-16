<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <title>图片上传</title>
    <link rel="shortcut icon" href="${ctx!}/hAdmin/favicon.ico">
    <link href="${ctx!}/hAdmin/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/animate.css" rel="stylesheet">

    <link rel="stylesheet" type="text/css" href="${ctx!}/webuploader/webuploader.css">
    <link rel="stylesheet" type="text/css" href="${ctx!}/hAdmin/css/demo/webuploader-demo.css">
    <link href="${ctx!}/hAdmin/css/style.css?v=4.1.0" rel="stylesheet"/>

</head>

<body class="gray-bg">
    <div class="wrapper wrapper-content animated fadeIn">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>杂志图片上传</h5>
                    </div>
                    <div class="ibox-content">
                        <div class="page-container">
                            <p>您可以尝试文件拖拽，使用QQ截屏工具，然后激活窗口后粘贴，或者点击添加图片按钮，来体验此demo.</p>
                            <div id="uploader" class="wu-example">
                                <div class="queueList">
                                    <div id="dndArea" class="placeholder">
                                        <div id="filePicker"></div>
                                        <p>或将照片拖到这里，单次最多可选300张</p>
                                    </div>
                                </div>
                                <div class="statusBar" style="display:none;">
                                    <div class="progress">
                                        <span class="text">0%</span>
                                        <span class="percentage"></span>
                                    </div>
                                    <div class="info"></div>
                                    <div class="btns">
                                        <div id="filePicker2"></div>
                                        <div class="uploadBtn">开始上传</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- 全局js -->
    <script src="${ctx!}/hAdmin/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/hAdmin/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- layer -->
    <script src="${ctx!}/hAdmin/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/hAdmin/js/content.js?v=1.0.0"></script>

    <!-- Web Uploader -->
    <script type="text/javascript">
        // 添加全局站点信息
        var BASE_URL = '/webuploader';
        var imageType = 1;  // 杂志图片
    </script>

    <script src="${ctx!}/webuploader/webuploader.min.js"></script>

    <script src="${ctx!}/webuploader/js/webuploader-demo.js"></script>
</body>
</html>