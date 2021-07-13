<#ftl encoding="UTF-8"/>
<#import "parts/base.ftlh" as p/>
<@p.page>

    <title>Edit category</title>

    <div class="main_container">
        <div class="jumbotron" style="width:50%; background:none; margin: auto">
            <div class="container">
                <h1 class="display-4" style="padding-bottom:2rem; text-align:center;">Edit category
                    <button
                            type="submit" class="btn btn-link"><span><a
                                    href="/categoriesPage">Go back to categories</a></span>
                    </button>
                </h1>
                <div class="card">
                    <div class="card-header">Information about category:</div>
                    <div class="card-body">
                        <p class="card-text">ID: ${category.getId()}</p>
                        <p class="card-text">Category name: ${category.getCategoryName()}</p>
                    </div>
                </div>
                <form name="my-form" action="/editCategory/${category.getId()}" method="post"
                      style="margin-top: 50px">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="category_name">New category name:</label>
                            <input type="text" class="form-control" id="categoryName" name="categoryName">
                        </div>
                    </div>
                    <button id="signUpButton" class="btn btn-primary btn-block my-4"
                            type="submit">Edit category
                    </button>
                </form>
            </div>
        </div>
    </div>

</@p.page>