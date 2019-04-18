<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title>杂志类别</title>
    <meta name="keywords" content="" />
    <meta name="description" content="" />

    <link rel="shortcut icon" href="${ctx!}/hAdmin/favicon.ico">
    <link href="${ctx!}/hAdmin/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/font-awesome.min.css?v=4.4.0" rel="stylesheet">
    <!-- bootstrap table -->
    <link href="${ctx!}/hAdmin/css/plugins/bootstrap-table/bootstrap-table.min.css" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/style.css?v=4.1.0" rel="stylesheet">
    <!-- bootstrap tree view -->
    <link href="${ctx!}/hAdmin/css/plugins/treeview/bootstrap-treeview.css" rel="stylesheet">
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
            <!-- category tree view -->
            <div class="col-sm-2">
                <div class="ibox float-e-margins">
                    <div class="ibox-title">
                        <h5>分类选择</h5>
                        <div class="ibox-tools">
                            <a class="collapse-link">
                                <i class="fa fa-chevron-up"></i>
                            </a>
                            <a class="dropdown-toggle" data-toggle="dropdown" href="buttons.html#">
                                <i class="fa fa-wrench"></i>
                            </a>
                            <a class="close-link">
                                <i class="fa fa-times"></i>
                            </a>
                        </div>
                    </div>
                    <div class="ibox-content">
                        <p>
                            <button class="btn btn-warning" type="button" onclick="changeList('hotList');"><i class="fa fa-reorder"></i>&nbsp;推荐</button>
                            <button class="btn btn-info" type="button" onclick="changeList('freeList');"><i class="fa fa-reorder"></i>&nbsp;免费</button>
                        </p>
                        <div id="categoryTree" class="test"></div>
                    </div>
                </div>
            </div>

            <#-- product bootstrap table -->
            <div class="col-sm-10">
                <div class="ibox">
                    <div class="ibox-title">
                        <h5>杂志管理</h5>
                    </div>
                    <div class="ibox-content">
                        <!-- btn add -->
                        <p>
                            <button class="btn btn-success " type="button" onclick="add();"><i class="fa fa-plus"></i>&nbsp;添加</button>
                            <button class="btn btn-danger" type="button" onclick="dels();"><i class="fa fa-remove"></i>&nbsp;删除</button>
                            <button class="btn btn-primary" type="button" onclick="setStatus(1);"><i class="fa fa-plus"></i>&nbsp;上架</button>
                            <button class="btn btn-danger" type="button" onclick="setStatus(0);"><i class="fa fa-remove"></i>&nbsp;下架</button>
                        </p>
                        <p>
                            <button class="btn btn-warning" type="button" onclick="setIshot(1);"><i class="fa fa-plus"></i>&nbsp;推荐</button>
                            <button class="btn btn-danger" type="button" onclick="setIshot(0);"><i class="fa fa-remove"></i>&nbsp;取消推荐</button>
                            <button class="btn btn-info" type="button" onclick="setIsFree(1);"><i class="fa fa-plus"></i>&nbsp;免费</button>
                            <button class="btn btn-danger" type="button" onclick="setIsFree(0);"><i class="fa fa-remove"></i>&nbsp;取消免费</button>
                        </p>
                        <p>
                            <button class="btn btn-warning" type="button" onclick="setTagStatus(1);"><i class="fa fa-plus"></i>&nbsp;New</button>
                            <button class="btn btn-danger" type="button" onclick="setTagStatus(0);"><i class="fa fa-remove"></i>&nbsp;取消New</button>
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

    <!-- Bootstrap tree view -->
    <script src="${ctx!}/hAdmin/js/plugins/treeview/bootstrap-treeview.js"></script>

    <!-- Peity -->
    <script src="${ctx!}/hAdmin/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="${ctx!}/hAdmin/js/plugins/layer/layer.min.js"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/hAdmin/js/content.js?v=1.0.0"></script>


    <script>
        // var url = "/admin/product/list";

        $(document).ready(function () {
            //初始化表格,动态从服务器加载数据
            $("#table_list").bootstrapTable({
                //使用get请求到服务器获取数据
                method: "POST",
                //必须设置，不然request.getParameter获取不到请求参数
                contentType: "application/x-www-form-urlencoded",
                //获取数据的Servlet地址
                url: "/admin/product/list",
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
                search: true,
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
                        search: params.searchText
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
                    field: "productName"
                },{
                    title: "图片",
                    field: "gallery",
                    align: "center",
                    formatter: function (value, row, index) {
                        if(value != null) {
                            return '<img src="'+ value.thumbImageSrc +'" class="zzimage"/>';
                        }else {
                            return '';
                        }
                    }
                    /*
                    formatter: function(value, row, index) {
                        var src = "";
                        $.each(value, function(i, productImage) {
                            $.each(productImage, function (n, gallery) {
                                if(productImage.sortId == 1) {
                                    src = gallery.imageSrc;
                                }
                            });
                        });
                        return '<img src="'+ src +'" class="zzimage"/>';
                    }
                    */
                },{
                    title: "分类名称",
                    field: "category",
                    formatter: function (value, row, index) {
                        if(value != null) {
                            return value.catename;
                        }else {
                            return '';
                        }
                    }
                },{
                    title: "年份",
                    field: "years",
                    align: "center",
                    formatter: function (value, row, index) {
                        if(value != null) {
                            return value.yearsName;
                        }else {
                            return '';
                        }
                    }
                },{
                    title: "语言",
                    field: "country",
                    align: "center",
                    formatter: function (value, row, index) {
                        if(value != null) {
                            return value.countryName;
                        }else {
                            return '';
                        }
                    }
                },{
                    title: "类型",
                    field: "type",
                    align: "center",
                    formatter: function (value, row, index) {
                        if(value != null) {
                            return value.typeName;
                        }else {
                            return '';
                        }
                    }
                },{
                    title: "价格",
                    field: "price",
                    align: "center",
                },{
                    title: "状态",
                    field: "productStatus",
                    width: "80px",
                    align: "center",
                    formatter: function(value, row, index) {
                        if (value == '1')
                            return '<span class="label label-info">上架</span>';
                        return '<span class="label label-danger">下架</span>';
                    }
                },{
                    title: "推荐",
                    field: "isHot",
                    width: "80px",
                    align: "center",
                    formatter: function(value, row, index) {
                        if (value == '1')
                            return '<span class="label label-warning">推荐</span>';
                        return '<span class="label label-primary">普通</span>';
                    }
                },{
                    title: "免费",
                    field: "isFree",
                    width: "80px",
                    align: "center",
                    formatter: function(value, row, index) {
                        if (value == '1')
                            return '<span class="label label-warning">免费</span>';
                        return '<span class="label label-primary">出售</span>';
                    }
                },{
                    title: "New",
                    field: "tagStatus",
                    width: "80px",
                    align: "center",
                    formatter: function(value, row, index) {
                        if (value == '1')
                            return '<span class="label label-danger">New</span>';
                        return '<span class="label label-default">正常</span>';
                    }
                },{
                    title: "创建时间",
                    field: "createTime",
                },{
                    title: "更新时间",
                    field: "updateTime",
                },{
                    title: "操作",
                    field: "empty",
                    formatter: function (value, row, index) {
                        var operateHtml = '<button class="btn btn-primary btn-xs" type="button" onclick="edit(\''+row.id+'\')"><i class="fa fa-edit"></i>&nbsp;修改</button> &nbsp;';
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

            /*
                加载分类列表树
             */
            getCategoryTree();

            /*
                监听搜索框变化 搜索时切换url为/list
             */
            $(".search").find('input').on('input propertychange',function() {
                $("#table_list").bootstrapTable('refresh', {url: "/admin/product/list"});
            });
        });

        /*
            获取分类json数据
         */
        function getCategoryTree() {
            $.ajax({                           //异步请求
                url: "/admin/product/categoryList",
                type: 'get',
                dataType: "json",
                async: false,
                data:{},
                success:function(data) {
                    var cateList = new Array();
                    $.each(data, function(index, value) {   // cate 1
                        var cateNode = {};
                        // text是显示的内容
                        cateNode["text"] = value.catename;
                        cateNode["id"] = value.id;
                        cateNode["nodes"] = [];
                        $.each(value.categorySecList, function(index, value) {  // cate 2
                            var cateSecNode = {};
                            cateSecNode["text"] = value.catename;
                            cateSecNode["id"] = value.id;
                            cateNode["nodes"].push(cateSecNode);    // cateSecNode -> cateNode
                        });
                        cateList.push(cateNode);    //  cateNode -> cateList
                    });

                    $('#categoryTree').treeview({
                        showTags: true,
                        data: cateList,
                        onNodeSelected: function (event, node) {
                            changeCateList(node.id);    // 改变url
                        }
                    });
                },
                error:function(){

                }
            });
        }

        // change category list
        function changeCateList(cateId) {
            var url = "/admin/product/list?cateId=" + cateId;
            changeUrl(url);
        }

        /*
            杂志列表选择
         */
        function changeList(listName) {
            var url = "/admin/product/" + listName;
            changeUrl(url);
        }

        // change bootstrap table url
        function changeUrl(url) {
            $("#table_list").bootstrapTable('refresh', {url: url});
        }

        // 添加
        function add(){
            window.location.href = "${ctx!}/admin/product/form";
        }

        // 修改
        function edit(id) {
            window.location.href = "${ctx!}/admin/product/form?id=" + id;
        }

        // 删除
        function del(id) {
            layer.confirm('确定删除吗?', {icon: 1, title:'提示'}, function(index){
                $.ajax({
                    type: "POST",
                    dataType: "json",
                    url: "${ctx!}/admin/product/delete/" + id,
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
                            url: "${ctx!}/admin/product/delete/" + item.id,
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
            设置是否热卖
            0不是 1是
        */
        function setIshot(isHot) {
            var items = $("#table_list").bootstrapTable('getSelections');
            var msg = "";
            if(items.length <= 0) {
                layer.msg('请先选中一项', {time: 1000});
            }else {
                if(isHot == 1){
                    msg = "确定设置为热卖吗?";
                }else {
                    msg = "确定取消热卖吗?"
                }
                layer.confirm(msg, {icon: 1, title:'提示'}, function(index){
                    $(items).each(function (index, item) {
                        $.ajax({
                            type: "POST",
                            dataType: "json",
                            url: "${ctx!}/admin/product/setIsHot/" + item.id + "/" + isHot,
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
            设置杂志上下架
            0不是 1是
        */
        function setStatus(status) {
            var items = $("#table_list").bootstrapTable('getSelections');
            var msg = "";
            if(items.length <= 0) {
                layer.msg('请先选中一项', {time: 1000});
            }else {
                if(status == 1){
                    msg = "确定设置上架吗?";
                }else {
                    msg = "确定下架杂志吗?"
                }
                layer.confirm(msg, {icon: 1, title:'提示'}, function(index){
                    $(items).each(function (index, item) {
                        $.ajax({
                            type: "POST",
                            dataType: "json",
                            url: "${ctx!}/admin/product/setStatus/" + item.id + "/" + status,
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
            设置杂志免费
            0不是 1是
        */
        function setIsFree(isFree) {
            var items = $("#table_list").bootstrapTable('getSelections');
            var msg = "";
            if(items.length <= 0) {
                layer.msg('请先选中一项', {time: 1000});
            }else {
                if(isFree == 1){
                    msg = "确定设置免费吗?";
                }else {
                    msg = "确定取消免费吗?"
                }
                layer.confirm(msg, {icon: 1, title:'提示'}, function(index){
                    $(items).each(function (index, item) {
                        $.ajax({
                            type: "POST",
                            dataType: "json",
                            url: "${ctx!}/admin/product/setIsFree/" + item.id + "/" + isFree,
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
            设置杂志标签
            0不是 1是
        */
        function setTagStatus(tagStatus) {
            var items = $("#table_list").bootstrapTable('getSelections');
            var msg = "";
            if(items.length <= 0) {
                layer.msg('请先选中一项', {time: 1000});
            }else {
                if(tagStatus == 1){
                    msg = "确定设置New吗?";
                }else {
                    msg = "确定取消New吗?"
                }
                layer.confirm(msg, {icon: 1, title:'提示'}, function(index){
                    $(items).each(function (index, item) {
                        $.ajax({
                            type: "POST",
                            dataType: "json",
                            url: "${ctx!}/admin/product/setTagStatus/" + item.id + "/" + tagStatus,
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