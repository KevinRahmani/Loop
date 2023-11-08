const section = document.querySelector(".container-table"),
     overlay = document.querySelector(".overlay"),
     footer = document.querySelector(".footer-dark"),
     showBtn = document.querySelector(".showModal"),
     closeBtn = document.getElementById("close-btn");

showBtn.addEventListener("click", ()=> {
     section.classList.add("active")
     footer.classList.add("active")
})
closeBtn.addEventListener("click", ()=> {
     section.classList.remove("active")
     footer.classList.remove("active")
})
overlay.addEventListener("click", ()=> {
     section.classList.remove("active")
     footer.classList.remove("active")
})

const formCommand = document.getElementById("formCommand");
const errorModif = document.querySelector(".message_info");

formCommand.addEventListener("submit", function(e){
    e.preventDefault();

    const formData = new FormData(formCommand);

    var request = new XMLHttpRequest();
    request.open('POST',"commandController-servlet", true);
    request.onload = function() {
        const data = request.responseText; // Récupérer la réponse
        if (request.status >= 200 && request.status < 400) {
            if (data === "ok") {
                errorModif.innerHTML = "Achat réussi, vous allez être redirigé vers la page d'accueil";
                setTimeout(function () {
                    window.location.href = "redirection-servlet";
                }, 2000);
            } else {
                // Gérer les erreurs de requête HTTP
                errorModif.innerHTML =  data;
            }
        }
    }
    request.send(formData);
})