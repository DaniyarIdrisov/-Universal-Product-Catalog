<#import "parts/base.ftlh" as p/>
<@p.page>

    <title>Edit item</title>

    <div class="main_container">
        <div class="jumbotron" style="width:50%; background:none; margin: auto">
            <div class="container">
                <h1 class="display-4" style="padding-bottom:2rem; text-align:center;">Edit item
                    <button
                            type="submit" class="btn btn-link"><span><a
                                    href="/itemsPage">Go back to items</a></span>
                    </button>
                </h1>
                <div class="card">
                    <div class="card-header">Information about item:</div>
                    <div class="card-body">
                        <p class="card-text">ID: ${item.getId()}</p>
                        <p class="card-text">Label: ${item.getLabel()}</p>
                        <p class="card-text">Description: ${item.getDescription()}</p>
                        <p class="card-text">Price: ${item.getPrice()}</p>
                        <p class="card-text">Filename: ${item.getFilename()}</p>
                        <p class="card-text">Category name: ${item.getCategoryName()}</p>
                    </div>
                </div>
                <form name="my-form" action="/editItem/${item.getId()}" method="post" enctype="multipart/form-data"
                      style="margin-top: 50px">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="label">New label:</label>
                            <input type="text" class="form-control" id="label" name="label">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="item_price">New price:</label>
                            <input type="text" class="form-control" id="item_price" name="item_price">
                        </div>
                        <div class="form-group col-md-6">
                            <label for="description">New description:</label>
                            <textarea class="form-control" id="description" name="description" rows="2"></textarea>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="categories">New categories:</label>
                            <select class="form-select" name="category_id" style="width: 100%; height: 61px">
                                <option value="-1" id="-1">Default</option>
                                <#list categories as category>
                                    <option value="${category.getId()}"
                                            id="${category.getId()}">${category.getCategoryName()}</option>
                                </#list>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="upload_new_file">Upload a new picture:</label>
                            <input type="file" class="form-control-file" id="upload_new_file" name="upload_new_file">
                        </div>
                        <button id="signUpButton" class="btn btn-primary btn-block my-4"
                                type="submit">Edit item
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</@p.page>