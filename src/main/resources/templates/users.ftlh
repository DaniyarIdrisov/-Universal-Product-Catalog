<#ftl encoding="UTF-8"/>
<#import "parts/base.ftlh" as p/>
<@p.page>

    <title>Users</title>

    <div class="main_container">
        <div class="jumbotron" style="width: 50%; background:none; margin: auto">
            <div class="container">
                <h1 class="display-4" style="padding-bottom:2rem; text-align:center;">Users
                    <button
                            type="submit" class="btn btn-link"><span><a
                                    href="/profile">Go back to profile</a></span>
                    </button>
                </h1>
                <table class="table" style="margin-top: 30px">
                    <thead>
                    <tr>
                        <th scope="col">Id</th>
                        <th scope="col">Login</th>
                        <th scope="col">Email</th>
                        <th scope="col">First name</th>
                        <th scope="col">Last name</th>
                        <th scope="col">Role</th>
                        <th scope="col">State</th>
                        <th scope="col">Email state</th>
                        <th scope="col">Confirm code</th>
                        <th scope="col">Edit button</th>
                        <th scope="col">Delete button</th>
                    </tr>
                    </thead>
                    <tbody>
                    <#if thisUser.getRole() == "ADMIN">
                        <#list users as user>
                            <tr>
                                <th scope="row">${user.getId()}</th>
                                <td>${user.getLogin()}</td>
                                <td>${user.getEmail()}</td>
                                <td>${user.getFirstName()}</td>
                                <td>${user.getLastName()}</td>
                                <td>${user.getRole()}</td>
                                <td>${user.getState()}</td>
                                <td>${user.getEmailState()}</td>
                                <td>${user.getConfirmCode()}</td>
                                <#if user.getRole() != "GOD" && user.getId() != thisUser.getId()>
                                    <td>
                                        <a href="/editUserPage/${user.getId()}">
                                            <button id="signUpButton" class="btn btn-primary" style="margin-top: 0px"
                                                    type="submit">Edit
                                            </button>
                                        </a>
                                    </td>
                                </#if>
                            </tr>
                        </#list>
                    <#else>
                        <#list users as user>
                            <tr>
                                <th scope="row">${user.getId()}</th>
                                <td>${user.getLogin()}</td>
                                <td>${user.getEmail()}</td>
                                <td>${user.getFirstName()}</td>
                                <td>${user.getLastName()}</td>
                                <td>${user.getRole()}</td>
                                <td>${user.getState()}</td>
                                <td>${user.getEmailState()}</td>
                                <td>${user.getConfirmCode()}</td>
                                <#if thisUser.getId() != user.getId()>
                                    <td>
                                        <a href="/editUserPage/${user.getId()}">
                                            <button id="signUpButton" class="btn btn-primary" style="margin-top: 0px"
                                                    type="submit">Edit
                                            </button>
                                        </a>
                                    </td>
                                    <td>
                                        <form action="/deleteUser/${user.getId()}" method="post">
                                            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                                            <button id="signUpButton" class="btn btn-primary" style="margin-top: 0px"
                                                    type="submit">Delete
                                            </button>
                                        </form>
                                    </td>
                                </#if>
                            </tr>
                        </#list>
                    </#if>
                    </tbody>
                </table>
            </div>
        </div>
    </div>

</@p.page>