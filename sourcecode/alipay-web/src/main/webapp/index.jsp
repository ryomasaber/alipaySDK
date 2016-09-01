<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
    <title>支付宝SDK</title>
</head>
<body>
<div align="center">
    <h2>Hello World!</h2>
    <h1><font color="red"><a href="/alipay/webPay.do?total_fee=0.01">web支付</a></font></h1>
    <h1><font color="red"><a href="/notify/notify_url.do">web异步通知</a></font></h1>
    <h1><font color="red"><a href="/alipay/web_return_url.do">web同步通知</a></font></h1>
    <h1><font color="red"><a href="/alipay/wapPay.do?total_fee=0.01">wap支付</a></font></h1>
    <h1><font color="red"><a href="/alipay/appPay.do?total_fee=0.01">app支付</a></font></h1>
    <h1><font color="red"><a href="/alipay/rsaWapPay.do?total_fee=0.01">RSAwap支付</a></font></h1>
</div>
</body>
</html>
