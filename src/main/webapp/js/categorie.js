/*AJAX DU STOCK */

var panier_produit = document.getElementById('panier_quantite');
var select_type = document.getElementById('filter_product');
var box = document.querySelector(".box-container");
var listButton = document.querySelectorAll("button");



$(document).ready(function(){
    $("#filter_product").on("change", function(e){
        e.preventDefault();
        $.ajax({
            type: "GET",
            url: "modifyType-servlet",
            data: {'option': $(this).val(),
                    'categorie':$(this).attr('name')
            },
            dataType: "json",
            success: function (data) {
                $(".box-container").empty();
                for(var i = 0; i < (data).length; i++) {
                    //div box
                    var boxDiv = document.createElement("div");
                    boxDiv.classList.add('box');
                    box.appendChild(boxDiv);
                    //div image
                    var boxImage = document.createElement("div");
                    boxImage.classList.add('image');
                    boxDiv.appendChild(boxImage);
                    //lien
                    var lien = document.createElement("a");
                    lien.setAttribute("href","redirection-servlet?requestedPage=product&product="+data[i]['id']);
                    boxImage.append(lien);
                    //img
                    var image = document.createElement("img");
                    image.setAttribute("src", data[i]['image']+"1.jpg");
                    lien.appendChild(image);
                    //div icons
                    var icons = document.createElement("div");
                    icons.classList.add("icons");
                    icons.setAttribute("id",data[i]['id']);
                    boxImage.appendChild(icons);
                    //button_minus
                    var button_minus = document.createElement("button");
                    button_minus.classList.add("fa-solid");
                    button_minus.classList.add("fa-minus");
                    button_minus.classList.add("button_rose");
                    button_minus.setAttribute("id","minus");
                    icons.appendChild(button_minus);
                    //button_envoyer
                    var button_get = document.createElement("button");
                    button_get.classList.add("cart-btn");
                    button_get.classList.add("envoyer");
                    var buttonText = document.createTextNode("Ajouter au panier");
                    button_get.appendChild(buttonText);
                    button_get.setAttribute("id","submit");
                    icons.appendChild(button_get);
                    //button_plus
                    var button_plus = document.createElement("button");
                    button_plus.classList.add("fa-solid");
                    button_plus.classList.add("fa-plus");
                    button_plus.classList.add("button_rose");
                    button_plus.setAttribute("id","plus");
                    icons.appendChild(button_plus);
                    //input
                    var input = document.createElement("input");
                    input.setAttribute("type", "text");
                    input.setAttribute("readonly","readonly");
                    input.setAttribute("class","number_product");
                    input.setAttribute("id",data[i]['id']);
                    input.setAttribute("value", 0);
                    icons.appendChild(input);
                    //content
                    var content = document.createElement("div");
                    content.classList.add("content");
                    boxImage.appendChild(content);
                    //h3
                    var h3 = document.createElement("h3");
                    var texth3 = document.createTextNode(data[i]['marque']+" "+data[i]['nom'])
                    h3.appendChild(texth3);
                    content.appendChild(h3);
                    //price
                    var price = document.createElement("div");
                    content.appendChild(price);
                    price.appendChild(document.createTextNode(data[i]['prix']+" €"));
                    price.appendChild(document.createTextNode("   | Stock : "+data[i]['stock']));
                }

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
            },
            error: function(textStatus, errorThrown) {
                console.log("AJAX request failed: " + textStatus + ", " + errorThrown);
            }
        });
    })
})

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