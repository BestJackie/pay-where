<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>监控报警系统</title>
    <meta http-equiv="keywords" content="keyPairs">
    <meta http-equiv="description" content="监控报警系统">
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <style type="text/css">
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

<body>
<div class="content">
    <h2> ${date!.now}监控报警</h2>
    <table id="table-1">
        <thead>
        <td width="150">status</td>
        <td width="150">msg</td>
        <td width="150">data</td>
        </thead>
        <#list serverResponses as serverResponse>
            <tr>
                <td>${serverResponse.status!''}</td>
                <td>${serverResponse.msg!''}</td>
                <td>${serverResponse.data!''}</td>
            </tr>
        </#list>
    </table>
</div>

</body>
</html>