<#ftl encoding="UTF-8"/>
<#import "parts/base.ftlh" as p/>
<#import "/spring.ftl" as spring/>
<@p.page>

    <title>Sign In</title>

    <style>
        .error {
            color: red;
        }
    </style>

    <div class="main_container">
        <div class="jumbotron" style="width:50%; background:none; margin: auto">
            <div class="container">
                <h1 class="display-4" style="padding-bottom:2rem;"><@spring.message "registration.title"/>
                    <button type="submit" class="btn btn-link"><span> <a
                                    href="/login"><@spring.message "registration.login_redirect"/></a></span></button>
                </h1>

                <@spring.bind "userRegistrationForm"/>
                <form name="my-form" action="/addUser" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="name"><@spring.message "registration.first_name"/></label>
                            <@spring.formInput "userRegistrationForm.firstName" "type='text' class='form-control'"/>
                            <@spring.showErrors "<br>" "error"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="lastName"><@spring.message "registration.last_name"/></label>
                            <@spring.formInput "userRegistrationForm.lastName" "type='text' class='form-control'"/>
                            <@spring.showErrors "<br>" "error"/>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="login"><@spring.message "registration.login"/></label>
                            <@spring.formInput "userRegistrationForm.login" "type='text' class='form-control'"/>
                            <@spring.showErrors "<br>" "error"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="email"><@spring.message "registration.email"/></label>
                            <@spring.formInput "userRegistrationForm.email" "type='email' class='form-control'"/>
                            <@spring.showErrors "<br>" "error"/>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="password"><@spring.message "registration.password"/></label>
                            <@spring.formPasswordInput "userRegistrationForm.password" "class='form-control'"/>
                            <@spring.showErrors "<br>" "error"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="repeat"><@spring.message "registration.repeat_password"/></label>
                            <@spring.formPasswordInput "userRegistrationForm.repeatPassword" "class='form-control'"/>
                            <#if passwordsErrorMessage??>
                                <p style="color: red">${passwordsErrorMessage}</p>
                            </#if>
                            <@spring.showErrors "<br>" "error"/>
                        </div>
                    </div>

                    <button id="signUpButton" class="btn btn-primary btn-block my-4"
                            type="submit"><@spring.message "registration.sign_up"/></button>


                </form>


            </div>
        </div>
    </div>

</@p.page>