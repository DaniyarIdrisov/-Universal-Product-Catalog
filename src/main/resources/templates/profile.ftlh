<#ftl encoding="UTF-8"/>
<#import "parts/base.ftlh" as p/>
<@p.page>

    <title>Profile</title>

    <style>
        .error {
            color: red;
        }
    </style>

    <div class="main_container">
        <h1 class="display-4" style="padding-bottom:2rem; text-align:center;">
            <#if role == "ADMIN">
                Admin panel
            <#else>
                <#if role == "GOD">
                    God panel
                <#else >
                    Your profile
                </#if>
            </#if>
            <span> <form name="my-form"

                         action="/logout"
                         method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <button
                            type="submit" class="btn btn-link"><span>Logout</span></button></form></span></h1>

        <div class="my-container" style="width:64%; margin: auto;">
            <div class="row">
                <div class="col-sm-6">
                    <div class="card">
                        <div class="card-header">Information about you:</div>
                        <div class="card-body">
                            <h5 class="card-title">Name: ${user.getFirstName()}  ${user.getLastName()}</h5>
                            <p class="card-text">Login: ${user.getLogin()}</p>
                            <p class="card-text">Email: ${user.getEmail()}</p>
                            <p class="card-text">Role: ${user.getRole()}</p>
                        </div>
                        <div class="card" style="text-align: center">
                            <a href="/editProfilePage" class="btn btn-primary">Edit your profile</a>
                        </div>
                    </div>
                </div>
                <div class="col-sm-6">
                    <#if role == "ADMIN">
                    <div class="card" style="text-align: center">
                        <a href="/categoriesPage" class="btn btn-primary">Click to go to categories</a>
                    </div>
                    <div class="card" style="text-align: center">
                        <a href="/itemsPage" class="btn btn-primary">Click to go to items</a>
                    </div>
                    <div class="card" style="text-align: center">
                        <a href="/usersPage" class="btn btn-primary">Click to go to users</a>
                    </div>
                    <div class="card" style="text-align: center">
                        <a href="/ordersPage" class="btn btn-primary">Click to go to orders</a>
                    </div>
                    <#else>
                    <#if role == "GOD">
                    <div class="card" style="text-align: center">
                        <a href="/usersPage" class="btn btn-primary">Click to go to users</a>
                    </div>
                    <#else>
                    <#if orders?has_content>
                        <h4 style="text-align: center">Your orders:</h4>
                        <#list orders as order>
                            <div class="card" style="margin-bottom: 10px">
                                <div class="card-header">Order number: ${order.getId()}</div>
                                <div class="card-body">
                                    <p class="card-text">Order time: ${order.getOrderTime()}</p>
                                    <p class="card-text">Order prise: ${order.getSumPrice()}$</p>
                                    <a href="/showOrderItems/${order.getId()}" class="btn btn-primary"
                                       style="width: 100%">Show items</a>
                                </div>
                            </div>
                        </#list>
                    <#else>
                        <div class="card" style="text-align: center">
                            <div class="card-header">No orders yet</div>
                            <a href="/catalog" class="btn btn-primary">Go shopping!</a>
                        </div>
                    </#if>
                </div>
                </#if>
                </#if>
            </div>
        </div>
    </div>
    </div>

</@p.page>