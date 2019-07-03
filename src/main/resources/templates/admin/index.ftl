<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <meta name="renderer" content="webkit" />

    <title>杂志多管理后台</title>

    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->

    <link rel="shortcut icon" href="${ctx!}/hAdmin/favicon.ico">
    <link href="${ctx!}/hAdmin/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/style.css?v=4.1.0" rel="stylesheet">
</head>

<body class="fixed-sidebar full-height-layout gray-bg" style="overflow:hidden">
    <div id="wrapper">
        <!-- 左侧导航开始 -->
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="nav-close"><i class="fa fa-times-circle"></i>
            </div>
            <div class="sidebar-collapse">
                <ul class="nav" id="side-menu">
                    <!-- header -->
                    <li class="nav-header">
                        <div class="dropdown profile-element">
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                                <span class="clear">
                                    <span class="block m-t-xs" style="font-size:20px;">
                                        <i class="fa fa-area-chart"></i>
                                        <#-- logo-open -->
                                        <strong class="font-bold">ZzdAdmin</strong>
                                    </span>
                                </span>
                            </a>
                        </div>
                        <!-- logo-close -->
                        <div class="logo-element">Zzd</div>
                    </li>
                    <!-- classify title -->
                    <li class="hidden-folded padder m-t m-b-sm text-muted text-xs">
                        <span class="ng-scope">分类</span>
                    </li>
                    <!-- menu items -->
                    <li>
                        <a class="J_menuItem" href="#">
                            <i class="fa fa-home"></i>
                            <span class="nav-label">主页</span>
                        </a>
                    </li>

                    <!-- 系统管理 -->
                    <li>
                        <a href="#">
                            <i class="fa fa fa-user"></i>
                            <span class="nav-label">用户管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${ctx!}/admin/user/index">用户管理</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="${ctx!}/admin/order/index">用户订单</a>
                            </li>
                            <li>
                                <a class="J_menuItem" href="${ctx!}/admin/recharge/index">用户充值订单</a>
                            </li>
                        </ul>
                    </li>
                    <!-- cut-off line -->
                    <li class="line dk"></li>
                    <!-- 杂志管理 -->
                    <li>
                        <a href="#">
                            <i class="fa fa fa-reorder"></i>
                            <span class="nav-label">杂志管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <!-- 杂志管理 -->
                            <li>
                                <a class="J_menuItem" href="${ctx!}/admin/product/index">杂志管理</a>
                            </li>
                            <!-- 分类管理 -->
                            <li>
                                <a class="J_menuItem" href="${ctx!}/admin/category/list">杂志分类</a>
                            </li>
                            <!-- 年份管理 -->
                            <li>
                                <a class="J_menuItem" href="${ctx!}/admin/years/list">杂志年份</a>
                            </li>
                            <!-- 国家语言 管理 -->
                            <li>
                                <a class="J_menuItem" href="${ctx!}/admin/country/list">国家语言</a>
                            </li>
                            <!-- 杂志类型管理 -->
                            <li>
                                <a class="J_menuItem" href="${ctx!}/admin/productType/list">杂志类型</a>
                            </li>
                            <!-- 杂志类型管理 -->
                            <li>
                                <a class="J_menuItem" href="${ctx!}/admin/describe/index">杂志描述</a>
                            </li>
                            <!-- 图片上传 -->
                            <li>
                                <a class="J_menuItem" href="${ctx!}/admin/gallery/upload">杂志图片上传</a>
                            </li>
                        </ul>
                    </li>

                    <!-- cut-off line -->
                    <li class="line dk"></li>
                    <!-- 图库管理 -->
                    <li>
                        <a href="#">
                            <i class="fa fa fa-user"></i>
                            <span class="nav-label">图库管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${ctx!}/admin/gallery/index">图片管理</a>
                            </li>
                        </ul>
                    </li>

                    <!-- cut-off line -->
                    <li class="line dk"></li>
                    <!-- 帮助中心管理 -->
                    <li>
                        <a href="#">
                            <i class="fa fa fa-user"></i>
                            <span class="nav-label">用户帮助管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${ctx!}/admin/help/index">帮助中心类目</a>
                                <a class="J_menuItem" href="${ctx!}/admin/help/item/index">帮助中心内容</a>
                            </li>
                        </ul>
                    </li>

                    <!-- cut-off line -->
                    <li class="line dk"></li>
                    <!-- 广告管理 -->
                    <li>
                        <a href="#">
                            <i class="fa fa fa-user"></i>
                            <span class="nav-label">广告管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${ctx!}/admin/advert/index">广告管理</a>
                                <a class="J_menuItem" href="${ctx!}/admin/advert/upload">广告图片上传</a>
                            </li>
                        </ul>
                    </li>
                    <!-- cut-off line -->
                    <li class="line dk"></li>
                    <!-- 充值中心管理 -->
                    <li>
                        <a href="#">
                            <i class="fa fa fa-user"></i>
                            <span class="nav-label">充值中心管理</span>
                            <span class="fa arrow"></span>
                        </a>
                        <ul class="nav nav-second-level">
                            <li>
                                <a class="J_menuItem" href="${ctx!}/admin/rechargeHelp/index">充值中心管理</a>
                            </li>
                        </ul>
                    </li>
                    <!-- cut-off line -->
                    <li class="line dk"></li>
                </ul>
            </div>
        </nav>
        <!-- 左侧导航结束 -->

        <!-- 右侧部分开始 -->
        <div id="page-wrapper" class="gray-bg dashbard-1">
            <!-- top nav -->
            <div class="row border-bottom">
                <nav class="navbar navbar-static-top" role="navigation" style="margin-bottom: 0">
                    <div class="navbar-header"><a class="navbar-minimalize minimalize-styl-2 btn btn-info " href="#"><i class="fa fa-bars"></i> </a>
                        <form role="search" class="navbar-form-custom" method="post" action="search_results.html">
                            <div class="form-group">
                                <input type="text" placeholder="请输入您需要查找的内容 …" class="form-control" name="top-search" id="top-search">
                            </div>
                        </form>
                    </div>
                    <ul class="nav navbar-top-links navbar-right">
                        <!-- 通知内容 -->
                    </ul>
                </nav>
            </div>

            <!-- iframe 内嵌页面 -->
            <div class="row J_mainContent" id="content-main">
                <iframe id="J_iframe" width="100%" height="100%" src="" frameborder="0" data-id="" seamless></iframe>
            </div>

        </div>
        <!-- 右侧部分结束 -->
        </div>
    </div>

    <!-- 全局js -->
    <script src="${ctx!}/hAdmin/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/hAdmin/js/bootstrap.min.js?v=3.3.6"></script>
    <script src="${ctx!}/hAdmin/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="${ctx!}/hAdmin/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="${ctx!}/hAdmin/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/hAdmin/js/hAdmin.js?v=4.1.0"></script>
    <script type="text/javascript" src="${ctx!}/hAdmin/js/index.js"></script>

    <!-- 第三方插件 -->
    <script src="${ctx!}/hAdmin/js/plugins/pace/pace.min.js"></script>

</body>

</html>
