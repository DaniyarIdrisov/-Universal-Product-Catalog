<#ftl encoding="UTF-8"/>
<#import "parts/base.ftlh" as p/>
<@p.page>

    <title>Categories</title>

    <div class="main_container">
        <div class="jumbotron" style="width: 50%; background:none; margin: auto">
            <div class="container">
                <h1 class="display-4" style="padding-bottom:2rem; text-align:center;">Add item
                    <button
                            type="submit" class="btn btn-link"><span><a
                                    href="/profile">Go back to profile</a></span>
                    </button>
                </h1>
                <form name="my-form" action="/addItem" method="post" enctype="multipart/form-data">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="label">Label:</label>
                            <input type="text" class="form-control" id="label" name="label">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="item_price">Price:</label>
                            <input type="text" class="form-control" id="item_price" name="item_price">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="description">Description:</label>
                            <textarea class="form-control" id="description" name="description" rows="2"></textarea>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="categories">Categories:</label>
                            <select class="form-select" name="category_id" style="width: 100%; height: 61px">
                                <#list categories as category>
                                    <option value="${category.getId()}"
                                            id="${category.getId()}">${category.getCategoryName()}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="upload_file">Upload a picture:</label>
                            <input type="file" class="form-control-file" id="upload_file" name="upload_file">
                        </div>
                        <button id="signUpButton" class="btn btn-primary btn-block my-4"
                                type="submit">Add item
                        </button>
                    </div>
                </form>

                <h1 class="display-4" style="padding-bottom:2rem; text-align:center; margin-top: 100px">
                    Items</h1>
                <table class="table" style="margin-top: 30px">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Label</th>
                        <th scope="col">Description</th>
                        <th scope="col">Price</th>
                        <th scope="col">Filename</th>
                        <th scope="col">Category name</th>
                        <th scope="col">Delete button</th>
                        <th scope="col">Edit button</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#list items as item>
                        <tr>
                            <th scope="row">${item.getId()}</th>
                            <td>${item.getLabel()}</td>
                            <td>${item.getDescription()}</td>
                            <td>${item.getPrice()}</td>
                            <td>${item.getFilename()}</td>
                            <td>${item.getCategoryName()}</td>
                            <td>
                                <form action="/deleteItem/${item.getId()}" method="post">
                                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                    <button id="signUpButton" class="btn btn-primary" style="margin-top: 0px"
                                            type="submit">Delete
                                    </button>
                                </form>
                            </td>
                            <td><a href="/editItemPage/${item.getId()}">
                                    <button id="signUpButton" class="btn btn-primary" style="margin-top: 0px"
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