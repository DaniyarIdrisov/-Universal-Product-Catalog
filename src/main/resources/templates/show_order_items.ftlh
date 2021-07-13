<#ftl encoding="UTF-8"/>
<#import "parts/base.ftlh" as p/>
<@p.page>

    <title>Catalog</title>

    <div class="main_container">
        <h1 class="display-4" style="padding-bottom:2rem; text-align:center;">Order items
            <button
                    type="submit" class="btn btn-link"><span><a
                            href="/profile">Go back to profile</a></span>
            </button>
        </h1>
        <div class="my-container" style="margin-top: 50px">
            <div class="row">
                <div class="col-sm-3">
                    <div class="card">
                        <div class="card-header">Order number: ${order.getId()}</div>
                        <div class="card-body">
                            <p class="card-text">Order time: ${order.getOrderTime()}</p>
                            <p class="card-text">Order prise: ${order.getSumPrice()}$</p>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top: 20px">
                <#list items as item>
                    <div class="col-sm-3">
                        <div class="card" style="margin-bottom: 20px;">
                            <div class="card-header" style="text-align: center">${item.getLabel()}</div>
                            <img class="card-img-top" src="/img/${item.getFilename()}" style="height: 300px">
                            <p class="card-text" style="text-align: center">${item.getDescription()}</p>
                            <p class="card-text" style="text-align: center"><strong>Price: ${item.getPrice()}$</strong>
                            </p>
                            <p class="card-text" style="text-align: center; margin-bottom: 20px">
                                <strong>Category: ${item.getCategoryName()}</strong>
                            </p>
                        </div>
                    </div>
                </#list>
            </div>
        </div>
    </div>

</@p.page>