<#ftl encoding="UTF-8"/>
<#import "parts/base.ftlh" as p/>
<@p.page>

    <title>Catalog</title>


    <div class="main_container">
        <h1 class="display-4" style="padding-bottom:2rem; text-align:center;">Catalog</h1>
        <p style="padding-top:2rem; padding-bottom:2rem;">
            <input class="form-control" type="search" placeholder="Search" aria-label="Search"
                   style="width:30%; margin-left:35%;" id="query" oninput="f()"/>
        </p>
        <div class="my-container" style="margin-top: 50px">
            <form name="my-form" action="/showItemsByCategory" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="categories">Categories:</label>
                        <select class="form-select" name="category_id">
                            <option value="-1" id="-1">Show all</option>
                            <#list categories as category>
                                <option value="${category.getId()}"
                                        id="${category.getId()}">${category.getCategoryName()}</option>
                            </#list>
                        </select>
                        <button id="signUpButton" class="btn btn-primary btn-block my-4" style="width: 27%"
                                type="submit">Show items
                        </button>
                    </div>
                </div>
            </form>
            <#if showCategory?has_content>
                <input type="hidden" id="s_category" value="${showCategory.getId()}">
                <h2 style="text-align: center; font-size: 35px" class="display-4">
                    Category: ${showCategory.getCategoryName()}</h2>
            </#if>
            <#if items?has_content>
                <div id="res" class="row" style="margin-top: 30px">
                    <#list items as item>
                        <div class="col-sm-3">
                            <div class="card">
                                <div class="card-header" style="text-align: center">${item.getLabel()}</div>
                                <img class="card-img-top" src="/img/${item.getFilename()}" style="height: 300px">
                                <p class="card-text" style="text-align: center">${item.getDescription()}</p>
                                <p class="card-text" style="text-align: center"><strong>Price: ${item.getPrice()}
                                        $</strong>
                                </p>
                                <p class="card-text" style="text-align: center">
                                    <strong>Category: ${item.getCategoryName()}</strong>
                                </p>
                                <form name="my-form" action="/addItemToCart" method="post">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <input type="hidden" value="${item.getId()}" name="id"/>
                                    <button class="btn btn-primary btn-block my-4" type="submit">Add to cart</button>
                                </form>
                                <div class="card" style="text-align: center">
                                    <a href="/showComments/${item.getId()}" class="btn btn-primary">Show comments</a>
                                </div>
                            </div>
                        </div>
                    </#list>
                </div>
            <#else>
                <h2 style='margin-top: 60px; text-align: center'>No items:(</h2>
            </#if>

        </div>
    </div>

    <script type="application/javascript">
        function f() {
            $.ajax({
                url: "/catalogSearch",
                data: {"query": $("#query").val(), "category": $("#s_category").val()},
                dataType: "json",
                success: function (message) {
                    let csrf = '${_csrf.parameterName}';
                    let token = '${_csrf.token}';
                    if (message.objects.length > 0) {
                        $("#res").html("");
                        for (var i = 0; i < message.objects.length; i++) {
                            $("#res").append("<div class='col-sm-3'><div class='card'><div class='card-header' style='text-align: center'>" + message.objects[i].label + "</div><img class='card-img-top' src='/img/" + message.objects[i].filename + "' style='height: 300px'><p class='card-text' style='text-align: center'>" + message.objects[i].description + "</p><p class='card-text' style='text-align: center'><strong>Price: " + message.objects[i].price + " $</strong></p><p class='card-text' style='text-align: center'><strong>Category: " + message.objects[i].categoryName + "</strong></p><form name='my-form' action='/addItemToCart' method='post'><input type='hidden' name='" + csrf + "' value='" + token + "'><input type='hidden' value='" + message.objects[i].id + "' name='id' /><button class='btn btn-primary btn-block my-4' type='submit'>Add to cart</button></form><div class='card' style='text-align: center'><a href='/showComments/" + message.objects[i].id + "' class='btn btn-primary'>Show comments</a></div></div></div>");
                        }
                    } else {
                        $("#res").html("</div><h2 style='text-align: center;'>No items(</h2>");
                    }
                }
            })
        }
    </script>

</@p.page>