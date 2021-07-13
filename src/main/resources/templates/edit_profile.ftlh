<#import "parts/base.ftlh" as p/>
<#import "/spring.ftl" as spring/>
<@p.page>

    <title>Edit profile</title>

    <style>
        .error {
            color: red;
        }
    </style>

    <div class="main_container">
        <div class="jumbotron" style="width:50%; background:none; margin: auto">
            <div class="container">
                <h1 class="display-4" style="padding-bottom:2rem; text-align:center;">Edit profile
                    <button
                            type="submit" class="btn btn-link"><span><a
                                    href="/profile">Go back to profile</a></span>
                    </button>
                </h1>
                <div class="card">
                    <div class="card-header">Information about you:</div>
                    <div class="card-body">
                        <p class="card-text">ID: ${user.getId()}</p>
                        <p class="card-text">Login: ${user.getLogin()}</p>
                        <p class="card-text">Email: ${user.getEmail()}</p>
                        <p class="card-text">First name: ${user.getFirstName()}</p>
                        <p class="card-text">Last name: ${user.getLastName()}</p>
                    </div>
                </div>
                <@spring.bind "userEditProfileForm"/>
                <form name="my-form" action="/editProfile" method="post" enctype="multipart/form-data"
                      style="margin-top: 50px">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <input type="hidden" name="id" value="${user.getId()}" id="id">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="firstName">New first name:</label>
                            <@spring.formInput "userEditProfileForm.firstName" "type='text' class='form-control'"/>
                            <@spring.showErrors "<br>" "error"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="lastName">New last name:</label>
                            <@spring.formInput "userEditProfileForm.lastName" "type='text' class='form-control'"/>
                            <@spring.showErrors "<br>" "error"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="password">New password:</label>
                            <@spring.formPasswordInput "userEditProfileForm.password" "class='form-control'"/>
                            <@spring.showErrors "<br>" "error"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="repeatPassword">Repeat new password:</label>
                            <@spring.formPasswordInput "userEditProfileForm.repeatPassword" "class='form-control'"/>
                            <#if passwordsErrorMessage??>
                                <p style="color: red">${passwordsErrorMessage}</p>
                            </#if>
                            <@spring.showErrors "<br>" "error"/>
                        </div>
                        <button id="signUpButton" class="btn btn-primary btn-block my-4"
                                type="submit">Edit profile
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

</@p.page>