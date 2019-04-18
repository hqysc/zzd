<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>广告管理</title>
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
            width: 150px;
            height: 50px;
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
                        <h5>广告管理</h5>
                    </div>
                    <div class="ibox-content">
                        <!-- btn add -->
                        <p>
                            <button class="btn btn-success " type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加</button>
                            <button class="btn btn-danger" type="button" onclick="dels();"><i class="fa fa-remove"></i>&nbsp;删除</button>
                        </p>
                        <p>
                            <button class="btn btn-info" type="button" onclick="setAdvertType(0);"><i class="fa fa-plus"></i>&nbsp;首页广告</button>
                            <button class="btn btn-warning" type="button" onclick="setAdvertType(1);"><i class="fa fa-plus"></i>&nbsp;杂志广告</button>
                        </p>
                        <hr>
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
            //初始化表格,动态从服务器加载数据
            $("#table_list").bootstrapTable({
                //使用get请求到服务器获取数据
                method: "POST",
                //必须设置，不然request.getParameter获取不到请求参数
                contentType: "application/x-www-form-urlencoded",
                //获取数据的Servlet地址
                // url: "${ctx!}/admin/role/list",
                url: "/admin/advert/list",
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
                clickToSelect: true,
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
                },{
                    title: "序号",
                    field: "sortId",
                    sortable: true,
                },{
                    title: "序号",
                    field: "advertType",
                    formatter: function(value, row, index) {
                        if (value == '0')
                            return '<span class="label label-primary">首页广告</span>';
                        return '<span class="label label-warning">杂志广告</span>';
                    }
                },{
                    title: "图片",
                    field: "gallery",
                    align: "center",
                    formatter: function (value, row, index) {
                        if(value != null) {
                            return '<img src="'+ value.imageSrc +'" class="zzimage"/>';
                        }else {
                            return '';
                        }
                    }
                },{
                    title: "广告链接",
                    field: "advertUrl",
                },{
                    title: "更新时间",
                    field: "updateTime",
                },{
                    title: "创建时间",
                    field: "createTime",
                },{
                    title: "操作",
                    field: "empty",
                    formatter: function (value, row, index) {
                        var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="edit(\''+row.id+'\')"><i class="fa fa-edit"></i>&nbsp;修改</button> &nbsp;';
                        operateHtml = operateHtml + '<button class="btn btn-primary btn-xs" type="button" onclick="grant(\''+row.id+'\')"><i class="fa fa-edit"></i>&nbsp;图片</button> &nbsp;';
                        operateHtml = operateHtml + '<button class="btn btn-danger btn-xs" type="button" onclick="del(\''+row.id+'\')"><i class="fa fa-remove"></i>&nbsp;删除</button> &nbsp;';
                        return operateHtml;
                    }
                }],
                onLoadSuccess: function(){  //加载成功时执行
                    console.info("加载成功");
                },
                onLoadError: function(){  //加载失败时执行
                    console.info("加载数据失败");
                }
            });

        });

        // 修改
        function edit(id){
            layer.open({
                type: 2,
                title: '广告编辑',
                shadeClose: true,
                shade: false,
                area: ['893px', '600px'],
                content: '/admin/advert/form?id=' + id,
                end: function(index) {
                    $('#table_list').bootstrapTable("refresh");
                }
            });
        }

        // 添加
        function add() {
            layer.open({
                type: 2,
                title: '广告添加',
                shadeClose: true,
                shade: false,
                area: ['893px', '600px'],
                content: '/admin/advert/form',
                end: function(index) {
                    $('#table_list').bootstrapTable("refresh");
                }
            });
        }

        // 设置图片
        function grant(id){
            layer.open({
                type: 2,
                title: '广告图片选择',
                shadeClose: true,
                shade: false,
                area: ['893px', '600px'],
                content: '/admin/advert/imageList?id=' + id,
                end: function(index) {
                    $('#table_list').bootstrapTable("refresh");
                }
            });
        }

        // 删除
        function del(id) {
            layer.confirm('确定删除吗?', {icon: 1, title:'提示'}, function(index){
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/admin/advert/delete/" + id,
                    success: function(msg){
                        layer.msg(msg.message, {time: 1000},function(){
                            $('#table_list').bootstrapTable("refresh");
                            layer.close(index);
                        });
                    }
                });
            });
        }

        // 多选项删除
        function dels() {
            var items = $("#table_list").bootstrapTable('getSelections');
            if(items.length <= 0) {
                layer.msg('请先选中一项', {time: 1000});
            }else {
                layer.confirm('确定删除吗?', {icon: 1, title:'提示'}, function(index){
                    $(items).each(function (index, item) {
                        $.ajax({
                            type: "POST",
                            dataType: "json",
                            url: "${ctx!}/admin/advert/delete/" + item.id,
                            success: function(msg){
                                layer.msg(msg.message, {time: 1500},function(){
                                    $('#table_list').bootstrapTable("refresh");
                                    layer.close(index);
                                });
                            }
                        });
                    });
                });
            }
        }

        /*
            设置广告类型
            0首页广告 1杂志广告
        */
        function setAdvertType(advertType) {
            var items = $("#table_list").bootstrapTable('getSelections');
            var msg = "";
            if(items.length <= 0) {
                layer.msg('请先选中一项', {time: 1000});
            }else {
                if(advertType == 1){
                    msg = "确定设置为杂志广告吗?";
                }else {
                    msg = "确定设置为首页广告吗?"
                }
                layer.confirm(msg, {icon: 1, title:'提示'}, function(index){
                    $(items).each(function (index, item) {
                        $.ajax({
                            type: "POST",
                            dataType: "json",
                            url: "${ctx!}/admin/advert/setType/" + item.id + "/" + advertType,
                            success: function(msg){
                                layer.msg(msg.message, {time: 1500},function(){
                                    $('#table_list').bootstrapTable("refresh");
                                    layer.close(index);
                                });
                            }
                        });
                    });
                });
            }
        }
    </script>

</body>
</html>