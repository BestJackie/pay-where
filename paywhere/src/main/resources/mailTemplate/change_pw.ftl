<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>监控报警系统</title>
    <meta http-equiv="keywords" content="keyPairs">
    <meta http-equiv="description" content="系统提示">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <style type="text/css">
        body {
            font-family: SimSun;
        }

        /* Border styles */
        #table-1 thead, #table-1 tr {
            border-top-width: 1px;
            border-top-style: solid;
            border-top-color: rgb(230, 189, 189);
        }

        #table-1 {
            border-bottom-width: 1px;
            border-bottom-style: solid;
            border-bottom-color: rgb(230, 189, 189);
        }

        /* Padding and font style */
        #table-1 td, #table-1 th {
            padding: 5px 10px;
            font-size: 12px;
            font-family: Verdana;
            color: rgb(177, 106, 104);
        }

        /* Alternating background colors */
        #table-1 tr:nth-child(even) {
            background: rgb(238, 211, 210)
        }

        #table-1 tr:nth-child(odd) {
            background: #FFF
        }

        .content {
            width: 500px;
            margin: 50px auto;
        }
    </style>
</head>

<body style="white-space-collapsing:preserve;margin: 0.7875in 1.0236111in 0.7875in 1.0236111in;">

<p style="text-align:justify;text-indent: 2em;font-size:10pt;">
    致${user.userName}先生/女士：
</p>
<p style="text-align:justify;text-indent: 2em;font-size:10pt;">
    您在本系统中的账号${user.userName}密码即将在15天后过期，请您访问<a href="127.0.0.1:9524/user/resetPassword" target="_blank">修改密码</a>及时更改密码，
</p>
</div>
</body>
</html>