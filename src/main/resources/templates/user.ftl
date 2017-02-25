<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">

<head>
    <title>Affinitas Chat</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>

    <link rel="stylesheet" type="text/css"
          href="webjars/bootstrap/3.3.7/css/bootstrap.min.css"/>
</head>
<body>

<nav class="navbar navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <a class="navbar-brand" href="#">www.affinitas.de</a>
        </div>
    </div>
</nav>

<div class="container" style="min-height: 500px">

    <div class="starter-template">
        <h1>Affinitas chat</h1>

        <div id="feedback"></div>

        <form class="form-horizontal" id="message-form">
            <div class="form-group form-group-lg">
                <label class="col-sm-2 control-label">Message</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" id="message"/>
                </div>
                <div class="col-sm-10">
                    <input type="hidden" class="form-control" id="fromUserId" value="${fromUserId}"/>
                </div>
                <div class="col-sm-10">
                    <input type="hidden" class="form-control" id="toUserId" value="${toUserId}"/>
                </div>
            </div>

            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" id="bth-message" class="btn btn-primary btn-lg">Send Message</button>
                </div>
            </div>
        </form>

    </div>

</div>

<div class="container">
    <footer>
        <p>
            <a href="www.affinitas.de">Affinitas.de</a> 2017
        </p>
    </footer>
</div>

<script type="text/javascript" src="webjars/jquery/2.2.4/jquery.min.js"></script>
<script type="text/javascript" src="js/message.js"></script>

</body>
</html>