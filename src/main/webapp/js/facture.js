const section = document.querySelector(".container-table"),
     overlay = document.querySelector(".overlay"),
     showBtn = document.querySelector(".showModal"),
     closeBtn = document.querySelector(".close-btn");

showBtn.addEventListener("click", ()=> {
     section.classList.add("active")
})
closeBtn.addEventListener("click", ()=> {
     section.classList.remove("active")
})
overlay.addEventListener("click", ()=> {
     section.classList.remove("active")
})

const buttonCommand = document.getElementById("confirm");
const errorModif = document.querySelector(".message_info");

buttonCommand.addEventListener("click", function(e){
    e.preventDefault();
    if(section.classList.contains("active")){
     section.classList.remove("active")
    }

    var request = new XMLHttpRequest();
    request.open('GET',"commandController-servlet", true);
    request.onload = function() {
        if (request.status >= 200 && request.status < 400) {
            var data = request.responseText; // Récupérer la réponse
            if (data === "ok") {
                errorModif.innerHTML = "Achat réussi, vous allez être redirigé vers la page d'accueil";
                console.log("Réussite");
                setTimeout(function () {
                    window.location.href = "redirection-servlet";
                }, 2000);
            }
        } else {
            // Gérer les erreurs de requête HTTP
            errorModif.innerHTML = "Erreur lors de la requête : " + request.status;
        }
    }
    request.send();
})