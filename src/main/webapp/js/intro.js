let intro = document.querySelector('.intro');
let logo = document.querySelector('.logo-header');
let logoSpan = document.querySelectorAll('.logo');

window.addEventListener('DOMContentLoaded', ()=> {      //moment ou le Dom va etre load la fonction va se lancer
    setTimeout(()=>{                                    //setTimeout execute la fonction associé une fois le temps imposé soit écoulé
        logoSpan.forEach((span,idx)=>{
            setTimeout(()=>{
                span.classList.add('active');
            },(idx+1) * 400)    //idx designe les deux span qui contiennent les lettres Ven et dor et on attend 400 ms avant de
        });

        setTimeout(()=>{
            logoSpan.forEach((span,idx)=>{      //encore la meme chose sauf que ici on enleve les classes active et ajoute fade pour faire disparaitre le logo par le haut avec une animation

                setTimeout(()=>{
                    span.classList.remove('active');
                    span.classList.add('fade');
                },(idx+1) * 50)
            })
        },2000)

        setTimeout(()=>{
            intro.style.top = '-100vh';
        },2300)

    })
})