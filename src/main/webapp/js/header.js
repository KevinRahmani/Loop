let arrow = document.querySelectorAll(".arrow");
for (let i = 0; i < arrow.length; i++) {
    arrow[i].addEventListener("click", (e)=>{
        let arrowParent = e.target.parentElement.parentElement;
        arrowParent.classList.toggle("showMenu");
    });
}
let sidebar = document.querySelector(".sidebar");
let nameLogo = document.querySelector("#nameLogo");
let sidebarBtn = document.querySelector(".bx-menu");
sidebarBtn.addEventListener("click", ()=>{
    sidebar.classList.toggle("close");
    nameLogo.classList.toggle("hidden");
    nameLogo.classList.toggle("visible");
});


/**
//logout
var logout = document.getElementById("logout");
logout.addEventListener("click", (e)=>{
    e.preventDefault();
    $.ajax({
        url: "php/logout.php",
        success: function (response) {
            if(response == "ok"){
                $(location).prop('href', 'index.php')
            }else{
                console.log("failed to log out");
            }
        },
        error: function(textStatus, errorThrown) {
            console.log("AJAX request failed: " + textStatus + ", " + errorThrown);
        }
    });
})

//navbar


// input
var search_produit = document.getElementById("search_produit");
//click loupe
var loupe = document.getElementById("search_loupe");

loupe.addEventListener("click", (e)=> {
    e.preventDefault();
    var value_research = search_produit.value;
    $.ajax({
        type: "GET",
        url: "php/searchbar.php",
        data: {
            "task": value_research,
        },
        dataType: "json",
        success: function (response) {
            if(response.status == "categorie"){
                window.location.href = "categorie.php?categorie=" + response.research;
            } else if(response.status == "produit"){
                window.location.href = "produit.php?categorie="+response.product_categorie+"&id=" + response.research["id"];
            } else {
                console.log("fail");
                search_produit.value = response.status;
            }
        }
    });
})

 **/