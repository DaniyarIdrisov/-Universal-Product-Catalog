<#ftl encoding="UTF-8"/>
<#import "parts/base.ftlh" as p/>
<@p.page>

    <title>Catalog</title>

    <div class="main_container">
        <h1 class="display-4" style="padding-bottom:2rem; text-align:center;">Comments
            <button
                    type="submit" class="btn btn-link"><span><a
                            href="/catalog">Go back to catalog</a></span>
            </button>
        </h1>
        <div class="my-container" style="margin-top: 50px">
            <form action="/enterComment/${item.getId()}" method="post">
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                <div class="row" style="margin-top: 30px">
                    <div class="col-sm-3">
                        <label for="text">Enter comment:</label>
                        <input type="text" class="form-control" id="text" name="text">
                        <button id="signUpButton" class="btn btn-primary btn-block my-4"
                                type="submit">Enter
                        </button>
                    </div>
                </div>
            </form>
            <div class="row" style="margin-top: 20px">
                <div class="col-sm-3">
                    <div class="card">
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
                <div class="col-sm-3">
                    <#if comments?has_content>
                        <#list comments as comment>
                            <div class="card" style="margin-bottom: 10px">
                                <div class="card-header">${comment.getUsername()}</div>
                                <div class="card-body">
                                    <p class="card-text">${comment.getText()}</p>
                                    <#if comment.getUserId() == user.getId() >
                                        <a href="/editCommentPage/${item.getId()}/${comment.getId()}" class="btn btn-primary" style="width: 100%">Edit</a>
                                        <form action="/deleteComment/${item.getId()}/${comment.getId()}" method="post">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                            <button id="signUpButton" class="btn btn-primary" style="margin-top: 10px; width: 100%"
                                                    type="submit">Delete
                                            </button>
                                        </form>
                                        <#else>
                                        <#if user.getRole() == 'ADMIN'>
                                            <form action="/deleteComment/${item.getId()}/${comment.getId()}" method="post">
                                                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                                <button id="signUpButton" class="btn btn-primary" style="margin-top: 10px; width: 100%"
                                                        type="submit">Delete
                                                </button>
                                            </form>
                                        </#if>
                                    </#if>
                                </div>
                            </div>
                        </#list>
                    <#else>
                        <h4 style="text-align: center">There are no comments yet. Be the first to comment!</h4>
                    </#if>
                </div>
            </div>
        </div>
    </div>

</@p.page>