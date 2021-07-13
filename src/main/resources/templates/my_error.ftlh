<#ftl encoding="UTF-8"/>
<html lang="en">
<head>

    <meta charset="UTF-8">
    <title>Error!</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
          integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
          crossorigin="anonymous">
    <link rel="stylesheet" href="/static/css/style.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

</head>
<body>
<h1 class="display-4" style="padding-bottom:2rem; text-align:center; margin-top: 60px">${exception.message}</h1>
<h3 style="text-align: center; margin-top: 20px">Failed url: ${url}</h3>
<h3 style="text-align: center; margin-top: 20px">Status: ${status}</h3>
</body>
</html>