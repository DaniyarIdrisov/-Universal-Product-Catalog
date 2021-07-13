<#ftl encoding="UTF-8"/>
<#import "parts/base.ftlh" as p/>
<#import "/spring.ftl" as spring/>
<@p.page>

    <title>Confirm email</title>

    <div class="main_container">
        <h1 style="text-align:center;"><@spring.message "confirm.title"/></h1>
        <button type="submit" class="btn btn-link"><span> <a
                        href="/profile">Click to go to sign in</a></span></button>
    </div>

</@p.page>
