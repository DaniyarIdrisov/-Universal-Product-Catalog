<#ftl encoding="UTF-8"/>
<#import "parts/base.ftlh" as p/>
<#import "/spring.ftl" as spring/>
<@p.page>
    <title>Login</title>

    <div class="main_container">
        <div class="jumbotron" style="width:50%; background:none; margin: auto">
            <div class="container">
                <h1 class="display-4" style="padding-bottom:2rem;"><@spring.message 'login.title'/>
                    <button
                            type="submit" class="btn btn-link"><span><a
                                    href="/registration"><@spring.message 'login.registration_redirect'/></a></span>
                    </button>
                    <a style="margin-top: 20px" href="https://github.com/login/oauth/authorize?client_id=${oauthClientId}&redirect_uri=http://localhost:8080/login/oauth2/code/github" class="btn btn-primary">Sign in with GitHub</a>
                </h1>

                <@spring.bind "userLoginForm"/>
                <form name="my-form" action="/login" method="post">
                    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="login"><@spring.message 'login.enter_login'/></label>
                            <@spring.formInput "userLoginForm.login" "type='text' class='form-control'"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="password"><@spring.message 'login.password'/></label>
                            <@spring.formPasswordInput "userLoginForm.password" "class='form-control'"/>
                        </div>
                        <div class="form-group col-md-6">
                            <label>
                                <input type="checkbox"
                                       name="remember-me"><@spring.message 'login.remember_me'/>
                            </label>
                        </div>
                    </div>
                    <button id="signUpButton" class="btn btn-primary btn-block my-4"
                            type="submit"><@spring.message 'login.sign_in'/></button>

                </form>


            </div>
        </div>
    </div>
</@p.page>