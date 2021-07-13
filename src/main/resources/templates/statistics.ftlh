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
                    <th scope="col">Sum price</th>
                    <th scope="col">Address</th>
                    <th scope="col">Order time</th>
                    <th scope="col">Username</th>
                    <th scope="col">Show order items</th>
                </tr>
                </thead>
                <tbody>
                <#list orders as order>
                    <tr>
                        <th scope="row">${order.getId()}</th>
                        <td>${order.getSumPrice()}</td>
                        <td>${order.getAddress()}</td>
                        <td>${order.getOrderTime()}</td>
                        <td>${order.getUsername()}</td>
                        <td><a href="/showOrderItems/${order.getId()}">
                                <button id="signUpButton" class="btn btn-primary" style="margin-top: 0px"
                                        type="submit">Show
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