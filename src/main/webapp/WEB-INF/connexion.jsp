<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Loop</title>

    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://kit.fontawesome.com/82e270d318.js" crossorigin="anonymous"></script>

    <link rel="stylesheet" href="css/connexion-anim.css">
    <link rel="stylesheet" href="css/connexion.css">
</head>
<body>
<!-- neon button to access to page -->
<div class="global">
    <button id="begin">
        <span></span>
        <span></span>
        <span></span>
        <span></span>
        Loop
    </button>
</div>
<section class="start">
    <div class="container">
        <div class="signin-signup">
            <form method="POST" action="connectController-servlet" id="form_connexion" class="sign-in-form">
                <h2 class="title">Connexion</h2>
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="Identifiant" name="name">
                </div>
                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="Mot de passe" name="password">
                </div>
                <div id="erreur1">
                    <c:if test="${requestScope.err == 1}">
                        Identifiants incorrect, veuillez réessayer.
                    </c:if>
                </div>
                <input type="submit" value="Login" class="btn">
                <p class="social-text">Suivez-nous sur les réseaux sociaux !</p>
                <div class="social-media">
                    <a href="https://www.facebook.com/people/vendor/100063691533412/?fref=ts" class="social-icon">
                        <i class="fab fa-facebook"></i>
                    </a>
                    <a href="https://twitter.com/vendorOfficial" class="social-icon">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a href="redirection-servlet" class="social-icon">
                        <i class="fab fa-google"></i>
                    </a>
                    <a href="https://www.linkedin.com/products/vendor-rethink-selling-vendor/" class="social-icon">
                        <i class="fab fa-linkedin-in"></i>
                    </a>
                </div>
            </form>
            <form  method="POST" action="registerController-servlet" id="form_inscription" class="sign-up-form">
                <h2 class="title">Inscription</h2>
                <div class="input-field">
                    <i class="fas fa-user"></i>
                    <input type="text" placeholder="Identifiant" name="nom">
                </div>
                <div class="input-field">
                    <i class="fas fa-envelope"></i>
                    <input type="text" placeholder="Email" name="mail">
                </div>
                <div class="input-field">
                    <i class="fas fa-home"></i>
                    <input type="text" placeholder="Adresse" name="adresse">
                </div>
                <div class="input-field">
                    <i class="fas fa-lock"></i>
                    <input type="password" placeholder="Mot de passe" name="password">
                </div>
                <div id="erreur2">
                    <c:if test="${not empty requestScope.errRegister}">
                        ${requestScope.errRegister}
                    </c:if>
                </div>
                <input type="submit" value="Sign up" class="btn">
                <p class="social-text">Suivez-nous sur les réseaux sociaux !</p>
                <div class="social-media">
                    <a href="https://www.facebook.com/people/vendor/100063691533412/?fref=ts" class="social-icon">
                        <i class="fab fa-facebook"></i>
                    </a>
                    <a href="https://twitter.com/vendorOfficial" class="social-icon">
                        <i class="fab fa-twitter"></i>
                    </a>
                    <a href="redirection-servlet" class="social-icon">
                        <i class="fab fa-google"></i>
                    </a>
                    <a href="https://www.linkedin.com/products/vendor-rethink-selling-vendor/" class="social-icon">
                        <i class="fab fa-linkedin-in"></i>
                    </a>
                </div>
            </form>
        </div>
        <div class="panels-container">
            <div class="panel left-panel">
                <div class="content">
                    <h3>Déjà membre ?</h3>
                    <p>Bienvenue sur <a href="redirection-servlet">Loop</a></p>
                    <button class="btn" id="sign-in-btn">Connectez-vous !</button>
                </div>
                <img src="img/login.svg" alt="" class="image">
            </div>
            <div class="panel right-panel">
                <div class="content">
                    <h3>Nouveau membre ?</h3>
                    <p>Bienvenue sur <a href="redirection-servlet">Loop</a></p>
                    <button class="btn" id="sign-up-btn">Inscrivez-vous !</button>
                </div>
                <img src="img/sign_up.svg" alt="" class="image">
            </div>
        </div>
    </div>
</section>



<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="js/connexion.js"></script>
</body>
</html>
