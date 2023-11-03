//Requete AJAX pour annuler entièrement la commande
$(document).ready(function () {
    $("#annuler").click(function (e) {
        e.preventDefault();
        $.ajax({
            type: "GET",
            url: "basketController-servlet",
            data: {
                "action": "undo"
            },
            success: function (response) {
                if (response.stat === "ok") {
                    location.reload();
                }
            },
            error: function(textStatus, errorThrown) {
                console.log("AJAX request failed: " + textStatus + ", " + errorThrown);
            }
        });
    })
})


//Requete AJAX pour supprimer une ligne du panier
$(document).ready(function () {
    $(".bg_none > button").click(function (e) {
        e.preventDefault();
        var Button = $(this).attr('id');
        $.ajax({
            type: "GET",
            url: "basketController-servlet",
            data: {
                "id": Button,
                "action": "deleteRow"
            },
            success: function (response) {
                if (response.stat === "ok") {
                    location.reload();
                }
            },
            error: function(textStatus, errorThrown) {
                console.log("AJAX request failed: " + textStatus + ", " + errorThrown);
            }
        });
    })
})


//Requete AJAX pour incrementer ou décrémenter la quantité voulu
$(document).ready(function () {
    $(".change_quantity > button").click(function (e) {
        e.preventDefault();
        var idButton = this.getAttribute('id');
        var TrucId = document.getElementById(idButton).parentElement;
        var Pinput = TrucId.children[1];

        $.ajax({
            type: "GET",
            url: "basketController-servlet",
            data: {
                "id": idButton,
                "action": "modify"
            },
            dataType: "json",
            success: function (response) {
                if (response.stat === "plus-ok") {
                    this.disabled = false;
                    Pinput.value = parseInt(response.stock);
                } else if (response.stat === "plus-fail") {
                    this.disabled = true; // Utiliser la référence stockée
                }
                if (response.stat === "minus-ok") {
                    this.disabled = false; // Utiliser la référence stockée
                    Pinput.value = parseInt(response.stock);
                } else if (response.stat === "minus-unset"){
                    location.reload();
                }
            },
            error: function(textStatus, errorThrown) {
                console.log("AJAX request failed: " + textStatus + ", " + errorThrown);
            }
        });
    });
});
