// button
const buttonShowUser = document.getElementById("button_user_info");
const buttonChevron = document.getElementById("chevron");
const buttonChevron2 = document.getElementById("chevron2");
const buttonAddVendeur = document.getElementById("chevron3");
const buttonDeleteVendeur = document.getElementById("chevron4");
const buttonAddRight = document.getElementById("chevron5");
const buttonDeleteRight = document.getElementById("chevron6");

//class active and notActive
var toggle_user = document.getElementById("toggle_user");
var toggle_chevron = document.getElementById("toggle_histo");
var toggle_coord = document.getElementById("form_coord");
var toggle_add = document.getElementById("toggle_add");
var toggle_remove = document.getElementById("form_delete_vendeur");
var toggle_add_livreur = document.getElementById("toggle_add_livreur");
var toggle_remove_livreur = document.getElementById("form_delete_livreur");

//containers
var container_user = document.querySelector(".container_user");
var container_histo = document.querySelector(".title_histo");
var container_coord = document.querySelector(".title_coord");
var container_add = document.querySelector(".title_add");
var container_remove = document.querySelector(".title_remove");
var container_add_livreur = document.querySelector(".title_add_livreur");
var container_remove_livreur = document.querySelector(".title_remove_livreur");


buttonShowUser.addEventListener("click", function(e) {
    e.preventDefault();
    if(toggle_user.getAttribute("class") == "notActive"){
        container_user.setAttribute("style", "max-height: 30rem;");
        toggle_user.classList.remove("notActive");
    } else {
        container_user.setAttribute("style", "max-height: 12rem;");
        toggle_user.classList.add("notActive");
    }
});

buttonChevron.addEventListener("click", function(e) {
    e.preventDefault();
    if(toggle_chevron.getAttribute("class") == "notActive"){
        container_histo.setAttribute("style", "max-height: 30rem;");
        toggle_chevron.classList.remove("notActive");
    } else {
        container_histo.setAttribute("style", "max-height: 7rem;");
        toggle_chevron.classList.add("notActive");
    }
});

buttonChevron2.addEventListener("click", function(e) {
    e.preventDefault();
    if(toggle_coord.getAttribute("class") == "notActive"){
        container_coord.setAttribute("style", "max-height: 30rem;");
        toggle_coord.classList.remove("notActive");
    } else {
        container_coord.setAttribute("style", "max-height: 7rem;");
        toggle_coord.classList.add("notActive");
    }
});

buttonAddVendeur.addEventListener("click", function(e) {
    e.preventDefault();
    if(toggle_add.getAttribute("class") == "notActive"){
        container_add.setAttribute("style", "max-height: 30rem;");
        toggle_add.classList.remove("notActive");
    } else {
        container_add.setAttribute("style", "max-height: 7rem;");
        toggle_add.classList.add("notActive");
    }
})

buttonDeleteVendeur.addEventListener("click", function(e) {
    e.preventDefault();
    if(toggle_remove.getAttribute("class") == "notActive"){
        container_remove.setAttribute("style", "max-height: 30rem;");
        toggle_remove.classList.remove("notActive");
    } else {
        container_remove.setAttribute("style", "max-height: 7rem;");
        toggle_remove.classList.add("notActive");
    }
});


buttonAddRight.addEventListener("click", function(e) {
    e.preventDefault();
    if(toggle_add_livreur.getAttribute("class") == "notActive"){
        container_add_livreur.setAttribute("style", "max-height: 40rem;");
        toggle_add_livreur.classList.remove("notActive");
    } else {
        container_add_livreur.setAttribute("style", "max-height: 7rem;");
        toggle_add_livreur.classList.add("notActive");
    }
})

buttonDeleteRight.addEventListener("click", function(e) {
    e.preventDefault();
    if(toggle_remove_livreur.getAttribute("class") == "notActive"){
        container_remove_livreur.setAttribute("style", "max-height: 30rem;");
        toggle_remove_livreur.classList.remove("notActive");
    } else {
        container_remove_livreur.setAttribute("style", "max-height: 7rem;");
        toggle_remove_livreur.classList.add("notActive");
    }
});


//envoie mail tous les clients
const button_mail = document.getElementById("mail_client");
var title_mail = document.querySelector(".title_mail");

button_mail.addEventListener("click", () => {
    var request = new XMLHttpRequest();
    request.open("GET","mailController-servlet", true);
    request.onload = function () {
        if (request.status >= 200 && request.status < 400) {
            var data = request.responseText;
            if(data === "ok"){
                title_mail.innerHTML = "Envoyer un mail promotionnel à tous les clients : Envoie réussi";
            }
        } else {
            title_mail.innerHTML = "Envoyer un mail promotionnel à tous les clients échoué";

        }
    }
    request.send();
});


/* AJOUTER MODIFIER SUPPRIMER UN PRODUIT  */

//PARTIE SUPPRIMER
var supp = document.getElementById("supp");
var sous_supp = document.getElementById("sous_supp");


supp.addEventListener("click", function (e) {
    e.preventDefault();

    if(sous_supp.classList.contains("notActive")){
        sous_supp.style.maxHeight="60rem";
        sous_supp.classList.remove("notActive");
    } else{
        sous_supp.style.maxHeight = "0";
        sous_supp.classList.add("notActive");
    }
});



//PARTIE MODIF

var modif = document.getElementById("modif");
var sous_modif = document.getElementById("sous_modif");

modif.addEventListener("click", function (e) {
    e.preventDefault();

    if(sous_modif.classList.contains("notActive")){
        sous_modif.style.maxHeight="60rem";
        sous_modif.classList.remove("notActive");
    } else{
        sous_modif.style.maxHeight = "0";
        sous_modif.classList.add("notActive");
    }
})



//PARTIE MODIF
const select_modif = document.getElementById('list_produit_modif');

//recupere les inputs
var modif_nom = document.getElementById('modif_nom');
var modif_prix = document.getElementById('modif_prix');
var modif_stock = document.getElementById('modif_stock');
var modif_type = document.getElementById('modif_type');
var modif_couleur = document.getElementById('modif_couleur');
var modif_description = document.getElementById('modif_description');
var modif_categorie = document.getElementById('modif_categorie');

select_modif.addEventListener("change", (e) =>{
    e.preventDefault();

    if( $("#list_produit_modif option:selected").val() == "default" ){
        $(".erreur_modif").text("Choisissez une option");
    } else {
        $.ajax({
            type: "GET",
            url: "selectModify-servlet",
            data: {"id" : $("#list_produit_modif option:selected").val()},
            dataType: "json",
            success: function (response) {
                if(response) {
                    modif_nom.setAttribute("value", response.nom);
                    modif_prix.setAttribute("value", response.prix);
                    modif_stock.setAttribute("value", response.stock);
                    modif_couleur.setAttribute("value",response.couleur);
                    modif_type.setAttribute("value",response.type);
                    for (var i = 0; i < modif_categorie.options.length; i++) {
                        if (modif_categorie.options[i].value === response.categorie) {
                            modif_categorie.selectedIndex = i;
                            break;
                        }
                    }
                    modif_description.innerHTML = response.description;
                } else {
                    console.log("fail");
                }
            }
        });
    }
})



//PARTIE AJOUTER UN PRODUIT


var add = document.getElementById("add");
var sous_add = document.getElementById("sous_add");

add.addEventListener("click", function (e) {
    e.preventDefault();

    if(sous_add.classList.contains("notActive")){
        sous_add.style.maxHeight="60rem";
        sous_add.classList.remove("notActive");
    } else{
        sous_add.style.maxHeight = "0";
        sous_add.classList.add("notActive");
    }
})
