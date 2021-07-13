<#ftl encoding="UTF-8"/>
<#import "parts/base.ftlh" as p/>
<@p.page>

    <title>Categories</title>

    <div class="main_container">
        <div class="jumbotron" style="width:50%; background:none; margin: auto">
            <div class="container">
                <h1 class="display-4" style="padding-bottom:2rem; text-align:center;">Add category
                    <button
                            type="submit" class="btn btn-link""><span><a
                                    href="/profile">Go back to profile</a></span>
                    </button>
                </h1>
                <form name="my-form" action="/addCategory" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="category_name">Category name:</label>
                            <input type="text" class="form-control" id="categoryName" name="categoryName">
                        </div>
                    </div>
                    <button id="signUpButton" class="btn btn-primary btn-block my-4"
                            type="submit">Add category
                    </button>

                </form>
                <h1 class="display-4" style="padding-bottom:2rem; text-align:center; margin-top: 100px">
                    Categories</h1>
                <table class="table" style="margin-top: 30px">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Category name</th>
                        <th scope="col">Delete button</th>
                        <th scope="col">Edit button</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list categories as category>
                        <tr>
                            <th scope="row">${category.getId()}</th>
                            <td>${category.getCategoryName()}</td>
                            <td>
                                <form action="/deleteCategory/${category.getId()}" method="post">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <button id="signUpButton" class="btn btn-primary" style="margin-top: 0px"
                                            type="submit">Delete
                                    </button>
                                </form>
                            </td>
                            <td><a href="/editCategoryPage/${category.getId()}">
                                    <button class="btn btn-primary" style="margin-top: 0px"
                                            type="submit">Edit
                                    </button>
                                </a>
                            </td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>


</@p.page>