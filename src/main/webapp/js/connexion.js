// animation btn debut

var buttonId = document.getElementById('begin');
var global = document.querySelector('.global');

window.addEventListener("DOMContentLoaded", ()=>{
    buttonId.addEventListener("click", ()=>{
        setTimeout(()=>{
            setTimeout(()=>{
                buttonId.classList.add("fade");
            }, 500)

            setTimeout(()=>{
                global.style.top='-100vh';
            },1000)
        });
    });
})

//animation form

const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener("click", ()=>{
    container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", ()=>{
    container.classList.remove("sign-up-mode");
});




