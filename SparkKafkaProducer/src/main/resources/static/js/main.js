$(document).ready(function () {

    $("#registration-form").submit(function (event) {
        event.preventDefault();
        submit_data();
    });

});

function submit_data() {

    var registration = {};
    registration["studentname"] = $("#studentname").val();
    registration["mobnumber"] = $("#mobnumber").val();
    registration["emailid"] = $("#emailid").val();
    $.map($("#registration-form").serializeArray(), function(n, i) {
    	registration[n['name']] = n['value'];
    });

    console.log(JSON.stringify(registration));
    $("#btn-order").prop("disabled", true);

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "register",
        data: JSON.stringify(registration),
        dataType: 'json',
        cache: false,
        timeout: 600000,
        success: function (data) {
            $('#feedback').html("<div class='alert alert-success alert-dismissable'>" +
            		"<a href='#' class='close' data-dismiss='alert' aria-label='close'>x</a>" +
            		"<strong>Success!</strong>Registration completed." +
            		"</div>");
            $("#btn-order").prop("disabled", false);
        },
        error: function (e) {
            $('#feedback').html("<div class='alert alert-success alert-dismissable'>" +
            		"<a href='#' class='close' data-dismiss='alert' aria-label='close'>x</a>" +
            		"<strong>Failure!</strong>Error while submitting the Order. Please try later" +
            		"</div>");
            $("#btn-order").prop("disabled", false);
        }
    });
}