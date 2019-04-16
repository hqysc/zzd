<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <title> - 登录</title>
    <meta name="keywords" content="">
    <meta name="description" content="">

    <link rel="shortcut icon" href="favicon.ico"> <link href="${ctx!}/hAdmin/css/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/font-awesome.css?v=4.4.0" rel="stylesheet">

    <link href="${ctx!}/hAdmin/css/animate.css" rel="stylesheet">
    <link href="${ctx!}/hAdmin/css/style.css?v=4.1.0" rel="stylesheet">
    <!--[if lt IE 9]>
    <meta http-equiv="refresh" content="0;ie.html" />
    <![endif]-->
    <script>if(window.top !== window.self){ window.top.location = window.location;}</script>
</head>

<body class="gray-bg">

    <div class="middle-box text-center loginscreen  animated fadeInDown">
        <div style="margin: 160px 0;">
            <h3>登录</h3>

            <form class="m-t" method="post" action="${ctx!}/user/login" id="frm">
                <div class="form-group">
                    <input type="text" name="username" class="form-control" placeholder="用户名">
                </div>
                <div class="form-group">
                    <input type="password" name="password" class="form-control" placeholder="密码">
                </div>

                <#if message?exists >
	            	<div class="alert alert-danger">
                        ${message!}
                    </div>
                </#if>

                <button type="submit" class="btn btn-primary block full-width m-b">登 录</button>

                <p class="text-muted text-center"> <a href="/user/login"><small>忘记密码了？</small></a> | <a href="/user/register">注册一个新账号</a>
                </p>

            </form>
        </div>
    </div>

    <!-- 全局js -->
    <script src="${ctx!}/hAdmin/js/jquery.min.js?v=2.1.4"></script>
    <script src="${ctx!}/hAdmin/js/bootstrap.min.js?v=3.3.6"></script>

    <!-- jQuery Validation plugin javascript-->
    <script src="${ctx!}/hAdmin/js/plugins/validate/jquery.validate.min.js"></script>
    <script src="${ctx!}/hAdmin/js/plugins/validate/messages_zh.min.js"></script>

    <script>
        $(document).ready(function () {
            $("#frm").validate({
                rules: {
                    username: {
                        required: true,
                    },
                    password: {
                        required: true,
                    }
                },
                messages: {
                    username: {
                        required: "请输入用户名",
                    },
                    password: {
                        required: "请输入密码",
                    }
                },
                submitHandler:function(form){
                    form.submit();
                }
            });
        })
    </script>
</body>

</html>
