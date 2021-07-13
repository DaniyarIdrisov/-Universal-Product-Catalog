<#ftl encoding="UTF-8"/>
<#import "parts/base.ftlh" as p/>
<@p.page>

    <title>Cart</title>


    <div class="main_container">
        <h1 class="display-4" style="padding-bottom:2rem; text-align:center;">Your cart:</h1>
        <#if items?has_content>
        <div class="my-container" style="margin-top: 50px">
            <div class="row">
                <div class="col-sm-3">
                    <h2 style="padding-left:7%;">Total price: ${sum}$</h2>
                </div>
            </div>
            <div class="row">
                <div class="col-sm-3">
                    <form name="my-form" action="/makeOrder" method="post">
                        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                        <label for="address" style="text-align: center; margin-top: 20px">Enter address:</label>
                        <input type="text" class="form-control" id="address" name="address">
                        <button type="submit" class="btn btn-primary" style="margin-top: 10px">Make order!</button>
                    </form>
                </div>
            </div>
            <div id="res" class="row">
                <#list items as item>
                    <div class="col-sm-3">
                        <div class="card">
                            <div class="card-header" style="text-align: center">${item.getLabel()}</div>
                            <img style="height: 300px" src="/img/${item.getFilename()}">
                            <p class="card-text" style="text-align: center">${item.getDescription()}</p>
                            <p class="card-text" style="text-align: center"><strong>Price: ${item.getPrice()}$</strong>
                            </p>
                            <form name="my-form" action="/removeFromCart" method="post">
                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                <input type="hidden" name="id" value="${item.getId()}">
                                <button type="submit" class="btn btn-primary btn-block my-4">Remove from cart</button>
                            </form>
                        </div>
                    </div>
                </#list>
            </div>
            <#else>
                <h2 style="text-align: center; margin-top: 30px">No items:(</h2>
            </#if>

        </div>
    </div>

</@p.page>