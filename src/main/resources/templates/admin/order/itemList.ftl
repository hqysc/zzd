<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>用户订单详情</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link rel="shortcut icon" href="${ctx!}/hAdmin/favicon.ico">
    <link href="${ctx!}/hAdmin/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <!-- bootstrap table -->
    <link href="${ctx!}/hAdmin/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/style.css?v=4.1.0" rel="stylesheet">

    <style>
        .zzimage {
            width: 120px;
            height: 40px;
            outline: 1px solid #b1b1b1;
            object-fit: cover;
            object-position: top;
        }
    </style>
</head>

<body>

    <div class="wrapper wrapper-content  animated fadeInRight">
        <div class="row">
            <div class="col-sm-12">
                <div class="ibox ">
                    <div class="ibox-title">
                        <h5>用户购买订单详情</h5>
                    </div>
                    <div class="ibox-content">
                        <!-- 表格开始 -->
                        <div class="row row-lg">
                            <div class="col-sm-12">
                                <!-- Example Card View -->
                                <div class="example-wrap">
                                    <div class="example">
                                        <table id="table_list"></table>
                                    </div>
                                </div>
                                <!-- End Example Card View -->
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <input id="orderId" type="hidden" value="${orderId}">

    <!-- 全局js -->
    <script src="${ctx!}/hAdmin/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/hAdmin/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- Bootstrap table -->
    <script src="${ctx!}/hAdmin/js/plugins/bootstrap-table/bootstrap-table.min.js"></script>
    <script src="${ctx!}/hAdmin/js/plugins/bootstrap-table/bootstrap-table-mobile.min.js"></script>
    <script src="${ctx!}/hAdmin/js/plugins/bootstrap-table/locale/bootstrap-table-zh-CN.min.js"></script>

    <!-- Peity -->
    <script src="${ctx!}/hAdmin/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="${ctx!}/hAdmin/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/hAdmin/js/content.js?v=1.0.0"></script>


    <script>
        $(document).ready(function () {
            var orderId = $("#orderId").val();
            //初始化表格,动态从服务器加载数据
            $("#table_list").bootstrapTable({
                //使用get请求到服务器获取数据
                method: "POST",
                //必须设置，不然request.getParameter获取不到请求参数
                contentType: "application/x-www-form-urlencoded",
                //获取数据的Servlet地址
                // url: "${ctx!}/admin/role/list",
                url: "/admin/order/list/detail/" + orderId,
                //表格显示条纹
                striped: true,
                //启动分页
                pagination: true,
                //每页显示的记录数
                pageSize: 10,
                //当前第几页
                pageNumber: 1,
                //记录数可选列表
                pageList: [5, 10, 15, 20, 25],
                //是否启用查询
                search: false,
                //是否启用点击选中行
                clickToSelect: false,
                //是否启用详细信息视图
                detailView: true,
                // detailFormatter: detailFormatter,
                //表示服务端请求
                sidePagination: "server",
                //设置为undefined可以获取pageNumber，pageSize，searchText，sortName，sortOrder
                //设置为limit可以获取limit, offset, search, sort, order
                queryParamsType: "undefined",
                queryParams: function queryParams(params) {   //设置查询参数，都是可以自动从控件获得
                    var param = {
                        pageNumber: params.pageNumber,
                        pageSize: params.pageSize,
                    };
                    return param;
                },
                //json数据解析
                responseHandler: function(res) {
                    return {
                        "rows": res.data,
                        "total": res.total
                    };
                },
                //数据列
                columns: [{
                    checkbox: true,
                },{
                    title: "ID",
                    field: "id",
                    sortable: true,
                },{
                    title: "杂志名称",
                    field: "product",
                    formatter: function (value, row, index) {
                        if(value != null) {
                            return value.productName;
                        }else {
                            return '';
                        }
                    }
                },{
                    title: "图片",
                    field: "product",
                    align: "center",
                    formatter: function (value, row, index) {
                        if(value != null) {
                            return '<img src="'+ value.gallery.thumbImageSrc +'" class="zzimage"/>';
                        }else {
                            return '';
                        }
                    }
                },{
                    title: "杂志类别",
                    field: "product",
                    formatter: function (value, row, index) {
                        if(value != null) {
                            return value.category.catename;
                        }else {
                            return '';
                        }
                    }
                },{
                    title: "年份",
                    field: "product",
                    align: "center",
                    formatter: function (value, row, index) {
                        if(value != null) {
                            return value.years.yearsName;
                        }else {
                            return '';
                        }
                    }
                },{
                    title: "语言",
                    field: "product",
                    align: "center",
                    formatter: function (value, row, index) {
                        if(value != null) {
                            return value.country.countryName;
                        }else {
                            return '';
                        }
                    }
                },{
                    title: "类型",
                    field: "product",
                    align: "center",
                    formatter: function (value, row, index) {
                        if(value != null) {
                            return value.type.typeName;
                        }else {
                            return '';
                        }
                    }
                },{
                    title: "价格",
                    field: "price",
                    align: "center",
                }],
                onLoadSuccess: function(){  //加载成功时执行
                    console.info("加载成功");
                },
                onLoadError: function(){  //加载失败时执行
                    console.info("加载数据失败");
                }
            });

        });

    </script>

</body>
</html>