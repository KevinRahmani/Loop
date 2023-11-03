// button
const buttonShowUser = document.getElementById("button_user_info");
const buttonChevron = document.getElementById("chevron");
const buttonChevron2 = document.getElementById("chevron2");

//class active and notActive
var toggle_user = document.getElementById("toggle_user");
var toggle_chevron = document.getElementById("toggle_histo");
var toggle_coord = document.getElementById("form_coord");

//containers
var container_user = document.querySelector(".container_user");
var container_histo = document.querySelector(".title_histo");
var container_coord = document.querySelector(".title_coord");



buttonShowUser.addEventListener("click", function(e) {
    e.preventDefault();
    if(toggle_user.getAttribute("class") == "notActive"){
        container_user.setAttribute("style", "max-height: 60rem;");
        toggle_user.classList.remove("notActive");
    } else {
        container_user.setAttribute("style", "max-height: 12rem;");
        toggle_user.classList.add("notActive");
    }
});

buttonChevron.addEventListener("click", function(e) {
    e.preventDefault();
    if(toggle_chevron.getAttribute("class") == "notActive"){
        container_histo.setAttribute("style", "max-height: 60rem;");
        toggle_chevron.classList.remove("notActive");
    } else {
        container_histo.setAttribute("style", "max-height: 7rem;");
        toggle_chevron.classList.add("notActive");
    }
});

buttonChevron2.addEventListener("click", function(e) {
    e.preventDefault();
    if(toggle_coord.getAttribute("class") == "notActive"){
        container_coord.setAttribute("style", "max-height: 60rem;");
        toggle_coord.classList.remove("notActive");
    } else {
        container_coord.setAttribute("style", "max-height: 7rem;");
        toggle_coord.classList.add("notActive");
    }
});



