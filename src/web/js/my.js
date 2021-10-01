$(document).ready(function() {

    // send a copy to yourself
    $('#submit-contact').click(function() {
        if ($('#sendCopy').is(':checked')) {
            var msg = $('#exampleMessage').val();
            $('#copyConfirmation').addClass("alert alert-success").html("<strong>We sent you a copy of your message </strong>: <br/>" + msg);
        }
    });

    // reason for contacting
    $('#contactReason').change(function() {
        var text = $(this).find("option:selected").text();
        if (text === "I'm just bored") {
            $('#boredOption').addClass("alert alert-primary").html("OK, but please make it interesting");
        } else {
            $('#boredOption').html("");
        }
    });

    // Donation
    $('#submit-donation').click(function() {
        var amount = $('#donation').val();
        var confirmation = $('#donation-confirmation');
        if (amount < 100) {
            alert("Thank You");
            confirmation.addClass("alert alert-primary").html("Thank you!");
        } else if (amount < 1000) {
            if (confirm("That's a generous sum! Are you sure?")) {
                confirmation.addClass("alert alert-primary").html("Thanks for confirming!");
            } else {
                confirmation.addClass("alert alert-primary").html("It's OK to change one's mind");
            }
        } else {
            var response = prompt("Please confirm you're serious by typing in 'Yes'");
            if(response === "Yes") {
                confirmation.addClass("alert alert-primary").html("Thank you for your generosity");
            }
        }
    });

    // clapping
    var clapCount = 0;
    $('#clap-image').click(function() {
        if (clapCount < 1) {
            $('#thank-you').addClass("alert alert-success").html("We appreciate it!");
            localStorage.setItem("clapped", "true");
            clapCount++;
        } else if (clapCount < 2) {
            $('#thank-you-2').addClass("alert alert-warning").html("You can only clap once, but thanks for your enthusiasm.");
            clapCount++;
        }
    });
});