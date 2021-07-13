<#ftl encoding="UTF-8"/>
<#import "parts/base.ftlh" as p/>
<@p.page>

    <title>Delete fail</title>

    <div class="main_container">
        <h1 style="text-align:center;">
            You cannot delete an entity as it has children!!!</h1>
        <h2 style="text-align:center;">
            <button type="submit" class="btn btn-link"><span> <a
                            href="/profile">Click to go to profile</a></span></button>
        </h2>
    </div>

</@p.page>