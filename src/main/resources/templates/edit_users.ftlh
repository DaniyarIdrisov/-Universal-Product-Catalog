<#import "parts/base.ftlh" as p/>
<@p.page>

    <title>Edit user</title>

    <div class="main_container">
        <div class="jumbotron" style="width:50%; background:none; margin: auto">
            <div class="container">
                <h1 class="display-4" style="padding-bottom:2rem; text-align:center;">Edit users
                    <button
                            type="submit" class="btn btn-link"><span><a
                                    href="/usersPage">Go back to users</a></span>
                    </button>
                </h1>
                <div class="card">
                    <div class="card-header">Information about user:</div>
                    <div class="card-body">
                        <p class="card-text">ID: ${user.getId()}</p>
                        <p class="card-text">Login: ${user.getLogin()}</p>
                        <p class="card-text">Email: ${user.getEmail()}</p>
                        <p class="card-text">First name: ${user.getFirstName()}</p>
                        <p class="card-text">Last name: ${user.getLastName()}</p>
                        <p class="card-text">Role: ${user.getRole()}</p>
                        <p class="card-text">State: ${user.getState()}</p>
                        <p class="card-text">Email state: ${user.getEmailState()}</p>
                        <p class="card-text">Confirm code: ${user.getConfirmCode()}</p>
                    </div>
                </div>
                <form name="my-form" action="/editUser/${user.getId()}" method="post" enctype="multipart/form-data"
                      style="margin-top: 50px">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="form-row">
                        <#if thisUser.getRole() == "ADMIN">
                            <div class="form-group col-md-6">
                                <label for="state">New state:</label>
                                <select class="form-select" name="state" style="width: 100%; height: 61px">
                                    <option value="Default" id="Default">Default</option>
                                    <option value="ACTIVE" id="ACTIVE">ACTIVE</option>
                                    <option value="BANNED" id="BANNED">BANNED</option>
                                </select>
                            </div>
                            <div class="form-group col-md-6">
                                <label for="email_state">New email state:</label>
                                <select class="form-select" name="email_state" style="width: 100%; height: 61px">
                                    <option value="Default" id="Default">Default</option>
                                    <option value="CONFIRMED" id="CONFIRMED">CONFIRMED</option>
                                    <option value="NOT_CONFIRMED" id="NOT_CONFIRMED">NOT_CONFIRMED</option>
                                </select>
                            </div>
                        <#else>
                            <div class="form-group col-md-6">
                                <label for="role">New role:</label>
                                <select class="form-select" name="role" style="width: 100%; height: 61px">
                                    <option value="Default" id="Default">Default</option>
                                    <option value="USER" id="USER">USER</option>
                                    <option value="ADMIN" id="ADMIN">ADMIN</option>
                                </select>
                            </div>
                        </#if>
                        <button id="signUpButton" class="btn btn-primary btn-block my-4"
                                type="submit">Edit user
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</@p.page>