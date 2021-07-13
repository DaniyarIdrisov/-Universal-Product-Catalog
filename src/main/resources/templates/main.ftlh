<#ftl encoding="UTF-8"/>
<#import "parts/base.ftlh" as p/>
<#import "/spring.ftl" as spring/>
<@p.page>
    <style>.card img {
            width: 100%;
            height: 100%;
        }</style>
    <div class="main_container">
        <div class="container text-center" style="margin-top: 40px; text-align: center">
            <h1 style="text-align: center">
                <@spring.message 'main.title'/>
            </h1>
            <p style="margin-top: 15px "><@spring.message 'main.text_1'/><br><@spring.message 'main.text_2'/></p>
            <h2 style="text-align: center; margin-top: 150px"><@spring.message 'main.stat'/></h2>
            <p style="margin-top: 15px"><@spring.message 'main.comments'/> ${comment}; <@spring.message 'main.orders'/> ${order}</p>
        </div>
            </div>
        </div>
    </div>
</@p.page>