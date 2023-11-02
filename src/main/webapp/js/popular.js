$(document).ready(function () {
    $("button").click(function (e) {
        e.preventDefault();



        var idButton = $(this).attr('id');
        var parent = $(this).parent()
        var kids = parent.children();
        var idproduct = parent.attr('id');

        //button plus
        if (idButton === 'plus') {
            $.ajax({
                type: "GET",
                url: "verifyStock-servlet",
                data: {
                    'id': idproduct,
                },
                success: function (response) {
                    const stock = parseInt(response);
                    $(kids[3]).removeClass('button_red');
                    $(kids[3]).addClass('button_rose');
                    if(stock > parseInt(kids[3].value)){
                        kids[3].value = parseInt(kids[3].value) + 1;
                    }else{
                        $(kids[3]).removeClass('button_rose');
                        $(kids[3]).addClass('button_red');
                    }
                },
                error: function(textStatus, errorThrown) {
                    console.log("AJAX request failed: " + textStatus + ", " + errorThrown);
                }
            });
        }

        //button minus
        if (idButton === 'minus') {
            if (parseInt(kids[3].value) > 0) {
                $(kids[3]).removeClass('button_red');
                kids[3].value = parseInt(kids[3].value) - 1;
            } else {
                $(kids[3]).removeClass('button_rose');
                $(kids[3]).addClass('button_red');
            }
        }

        //button add panier
        if (idButton === 'submit') {
            //recupere input du nombre de produit dedmandé
            if (parseInt(kids[3].value) > 0) {
                $.ajax({
                    type: "GET",
                    url: "modifyBasket-servlet",
                    data: {
                        'quantity': kids[3].value,
                        'id': kids[3].getAttribute('id')
                    },
                    dataType: "json",
                    success: function (response) {
                        if (response.status === 'ok') {
                            kids[3].value = 0;
                            $(kids[3]).removeClass('button_red');
                            $(kids[3]).addClass('button_rose');
                        } else {
                            kids[3].value = parseInt(response.stock);
                            $(kids[3]).addClass('button_red');
                            $(kids[3]).removeClass('button_rose');
                        }
                    }
                });
            }

        }
    })
})