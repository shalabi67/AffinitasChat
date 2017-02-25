$(document).ready(function () {

	setTimeout(getMessages, 2000);
    $("#message-form").submit(function (event) {

        //stop submit the form, we will post it manually.
        event.preventDefault();

        send_message();

    });

});

function getMessages(){
	getUserMessages();
	setTimeout(getMessages, 2000);
}

function getUserMessages(){
	var fromUserId = $("#fromUserId").val();
	$.ajax({
        type: "GET",
        url: "/messages/user/" + fromUserId,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-message").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-message").prop("disabled", false);

        }
    });

}
	

function send_message() {

	var fromUser = {}
	fromUser["id"] = $("#fromUserId").val();
	
	var toUser = {}
	toUser["id"] = $("#toUserId").val();
	
    var message = {}
    message["message"] = $("#message").val();
    message["fromUser"] = fromUser;
    message["toUser"] = toUser;

    $("#btn-message").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/messages",
        data: JSON.stringify(message),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {

            var json = "<h4>Ajax Response</h4><pre>"
                + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);

            console.log("SUCCESS : ", data);
            $("#btn-message").prop("disabled", false);

        },
        error: function (e) {

            var json = "<h4>Ajax Response</h4><pre>"
                + e.responseText + "</pre>";
            $('#feedback').html(json);

            console.log("ERROR : ", e);
            $("#btn-message").prop("disabled", false);

        }
    });

}