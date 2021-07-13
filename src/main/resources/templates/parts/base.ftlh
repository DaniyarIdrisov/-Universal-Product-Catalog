<#ftl encoding="UTF-8"/>
<#import "/spring.ftl" as spring/>
<#macro page>
    <html lang="en">
    <head>

        <meta charset="UTF-8">
        <title></title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.5.3/dist/css/bootstrap.min.css"
              integrity="sha384-TX8t27EcRE3e/ihU7zmQxVncDAy5uIKz4rEkgIXeMed4M0jlfIDPvg6uqKI2xXr2"
              crossorigin="anonymous">
        <link rel="stylesheet" href="/css/style.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

    </head>

    <body style="background:url(/images/style/background.jpg) no-repeat center top / cover;">
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <!-- Brand/logo -->
        <a class="navbar-brand" href="/main"><img src="/images/style/logo.png" width="40" height="40"> <@spring.message 'base.logo'/></a>

        <!-- Links -->
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="/catalog"><@spring.message 'base.catalog'/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/profile"><@spring.message 'base.profile'/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/login"><@spring.message 'base.login'/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/registration"><@spring.message 'base.registration'/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/cart"><@spring.message 'base.cart'/></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main/?lang=ru">РУС</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/main/?lang=en">EN</a>
            </li>
        </ul>
    </nav>
    <#nested>
    </body>
    </html>
</#macro>