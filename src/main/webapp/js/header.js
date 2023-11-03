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
        url: "searchBarController-servlet",
        data: {
            "task": value_research,
        },
        dataType: "json",
        success: function (response) {
            if(response.status === "ok"){
                window.location.href = response.result;
            } else {
                search_produit.value = response.result;
            }
        },
        error: function(textStatus, errorThrown) {
            console.log("AJAX request failed: " + textStatus + ", " + errorThrown);
        }
    });
})
