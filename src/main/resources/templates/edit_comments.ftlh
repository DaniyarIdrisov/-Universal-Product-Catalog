<#ftl encoding="UTF-8"/>
<#import "parts/base.ftlh" as p/>
<@p.page>

    <title>Edit comment</title>

    <div class="main_container">
        <div class="jumbotron" style="width:50%; background:none; margin: auto">
            <div class="container">
                <h1 class="display-4" style="padding-bottom:2rem; text-align:center;">Edit comment
                    <button
                            type="submit" class="btn btn-link"><span><a
                                    href="/showComments/${item.getId()}">Go back to comments</a></span>
                    </button>
                </h1>
                <div class="card">
                    <div class="card-header">Information about comment:</div>
                    <div class="card-body">
                        <p class="card-text">ID: ${comment.getId()}</p>
                        <p class="card-text">Comment text: ${comment.getText()}</p>
                    </div>
                </div>
                <form name="my-form" action="/editComment/${item.getId()}/${comment.getId()}" method="post"
                      style="margin-top: 50px">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="text">New comment name:</label>
                            <input type="text" class="form-control" id="text" name="text">
                        </div>
                    </div>
                    <button id="signUpButton" class="btn btn-primary btn-block my-4"
                            type="submit">Edit comment
                    </button>
                </form>
            </div>
        </div>
    </div>

</@p.page>