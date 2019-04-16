<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Title</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="stylesheet" href="${ctx!}/css/cart.css">
    <link rel="stylesheet" href="${ctx!}/css/base.css">
    <link rel="stylesheet" href="${ctx!}/css/bottom.css">
</head>

<body class="gray-bg">
    <!-- 导航 -->
    <#include "/zzd/common.ftl">
    <!-- 面包屑导航 -->
    <#include "/zzd/bread-crumbs.ftl">

    <!-- main -->
    <div class="main">
        <div class="cart">
            <!-- 用户信息 -->
            <div class="user">
                <!-- change page -->
                <div class="switch">
                    <div class="container">
                        <!-- btns -->
                        <div class="btns">
                            <a href="${ctx!}/cart/list"><div class="btn" id="cart-list">已选收藏</div></a>
                            <a href="${ctx!}/order/list"><div class="btn" id="order-list">结算订单</div></a>
                        </div>
                    </div>
                </div>
                <!-- user info -->
                <div class="info">
                    <div class="title"><p>用户信息</p></div>
                    <div class="container">
                        <div class="msg">
                            <p>用户名：${user.userName}</p>
                            <p>等级：普通会员</p>
                            <p>书币：${user.coin}</p>
                        </div>
                        <!-- btns -->
                        <div class="btns">
                            <a href=""><div id="edit-pw" class="btn">修改登录密码</div></a>
                            <a href=""><div id="recharge" class="btn">充值书币</div></a>
                        </div>
                    </div>
                </div>
            </div>
            <!-- 书架列表 -->
            <div class="list">
                <!-- list title -->
                <div class="title">
                    <div class="name">名称</div>
                    <div class="date">收藏时间</div>
                    <div class="price">书币</div>
                    <div class="check">选择杂志</div>
                </div>
                <!-- item -->
                <#list cartList as cart>
                    <div class="item">
                        <label>
                            <div class="inner">
                                <!-- image -->
                                <div class="image">
                                    <a href=""><img src="${cart.product.gallery.thumbImageSrc}" /></a>
                                </div>
                                <!-- info -->
                                <div class="info">
                                    <!-- 内容 -->
                                    <div class="content">
                                        <!-- 标题 -->
                                        <div class="name">
                                            <a href="">${cart.product.productName}</a>
                                        </div>
                                        <!-- 标签 -->
                                        <div class="tags">
                                            <div class="container">
                                                <div class="tags-flex">
                                                    <div class="tag" style="background-color: #FF0086">2018</div>
                                                    <div class="tag" style="background-color: #7C66FF">杂志合辑</div>
                                                    <div class="tag" style="background-color: #7F7F7F">西班牙语</div>
                                                    <div class="tag" style="background-color: #FF7F00">New</div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <!-- 时间 -->
                                    <div class="date">${cart.createTime}</div>
                                    <!-- 时间 -->
                                    <div class="price">${cart.product.price}</div>
                                </div>
                                <!-- delete -->
                                <div class="del"><a href="javascript:del(${cart.id})">删除商品</a></div>
                                <!-- check box -->
                                <div class="check">
                                    <input type="checkbox" class="checkPay" value="${cart.id}">
                                </div>
                            </div>
                        </label>
                    </div>
                </#list>

                <div class="pay">
                    <form id="frm" method="post" action="${ctx!}/order/pays">
                        <div id="productIds"></div>
                        <input type="submit" class="pay-btn" value="确认结算">
                    </form>
                </div>
            </div>
        </div>
    </div>

    <!-- 底部 -->
    <#include "/zzd/bottom.ftl">

    <!-- 全局js -->
    <script src="${ctx!}/hAdmin/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/hAdmin/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- 自定义js -->
    <script src="${ctx!}/hAdmin/js/content.js?v=1.0.0"></script>
    <script src="${ctx!}/hAdmin/js/plugins/layer/layer.min.js"></script>

    <script>
        $(document).ready(function(){

        });

        $('.check').change(function() {
            var productIds = new Array();
            var frm = $("#productIds");
            var ids = "";

            frm.html("");

            $("input[class='checkPay']:checked").map(function() {
                // 获取父节点的下一个子节点的文本
                productIds.push($(this).val());
            });

            for(var i = 0; i < productIds.length; i ++ ) {
                // 传值
                frm.append("<input name='cartIds' type='hidden' value='" + productIds[i] + "'>");
                ids += productIds[i] + ",";
            }

            console.log(ids);
        });

        // 删除
        function del(id) {
            layer.confirm('确定删除吗?', {icon: 1, title: '提示'}, function(index){
                $.ajax({
                    type: "post",
                    dataType: "json",
                    url: "${ctx!}/cart/delete/" + id,
                    success: function(msg) {
                        layer.msg(msg.message, {time: 500},function() {
                            layer.close(index);
                            window.location.reload();
                        });
                    }
                });
            });
        }

    </script>

</body>

</html>