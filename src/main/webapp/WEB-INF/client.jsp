<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Loop</title>

    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/page_client.css">

    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://kit.fontawesome.com/82e270d318.js" crossorigin="anonymous"></script>
</head>
<body>

       <%@include file="shared/header.jsp"%>
        <h2 class="title">Bonjour ${sessionScope.user.nom}, ravis de vous revoir</h2>
        <div class="sous_titre">Gérez vos informations, ainsi que la confidentialité et la sécurité de vos données pour profiter au mieux des services Loop. <a href="about_us.jsp">En savoir plus</a></div>

        <!-- info client -->
        <div class="container_user">
            <div class="container_title">
                <div class="second_container">
                    <div class="title2">Mon compte Loop :</div>
                    <div class="sous_titre2">Consulter les données de votre compte Loop !</div>
                </div>
                <img src="img/user.svg" alt="">
            </div>
            <button id="button_user_info">Consulter mes données</button>
            <div class="notActive" id="toggle_user">
                <div class="nom ">Nom : ${sessionScope.user.nom}</div>
                <div class="mail">Mail : ${sessionScope.user.mail}</div>
                <div class="num_client">ID client : ${sessionScope.user.id}</div>
                <div class="dateSignUp">Date de création du compte :<fmt:formatDate value="${sessionScope.user.datesignup}" pattern="yyyy-MM-dd HH:mm:ss" /></div>
                <div class="contrat">Nombre de points fidélité : ${sessionScope.user.fidelity}</div>
                <div class="adresse">Adresse : ${sessionScope.user.adresse}</div>
                <div class="categorie">Catégorie : Client</div>
                <div class="nbProduct">Nombre de produit acheté sur Loop : ${sessionScope.user.nbproduct}</div>
            </div>
        </div>
        <div class="container ">
            <div class="title_histo">
                <div class="container_chevron">
                    <div id ="div_histo">Historique de commande : </div>
                    <i class="fa-solid fa-chevron-down" id="chevron"></i>
                </div>
                <div class="sous_titre_histo">Voir mon historique de commande</div>
                <div class="notActive" id="toggle_histo">${sessionScope.user.histocommand}</div>
            </div>
            <div class="title_coord">
                <div class="container_chevron">
                    <div class="div_coord">Changer vos coordonnées</div>
                    <i class="fa-solid fa-chevron-down" id="chevron2"></i>
                </div>
                <div class="line_coord">Un changement dans vos coordonnées ?</div>
                <form method="POST" action="updateProfile-servlet" class="notActive" id="form_coord">
                    <div class="coord_1">
                        <label for="nom">Nom :</label><input type="text" id="nom" value="${sessionScope.user.nom}" placeholder="Nom" name="nom">
                    </div>
                    <div class="coord_1">
                        <label for="mail">Mail : </label><input type="email" id="mail" value="${sessionScope.user.mail}" placeholder="Mail" name="mail">
                    </div>
                    <div class="coord_1">
                        <label for="password"> Password : </label><input type="password" id="password" value="${sessionScope.user.password}" placeholder="Password" name="password">
                    </div>
                    <div class="coord_1">
                        <label for="adresse">Adresse : </label><input type="text" id="adresse" value="${sessionScope.user.adresse}" placeholder="Adresse" name="adresse" >
                    </div>
                    <input type="submit" value="Envoyer" class="submit_coord">
                    <div id="erreur">
                        <c:if test="${not empty requestScope.err}">
                            ${requestScope.err}
                        </c:if>
                    </div>
                </form>
            </div>
        </div>

      <%@include file="shared/footer.jsp"%>

    <script src="https://code.jquery.com/jquery-3.6.4.min.js" integrity="sha256-oP6HI9z1XaZNBrJURtCoUT5SUnxFr8s3BzRl+cbzUq8=" crossorigin="anonymous"></script>
    <script src="js/header.js"></script>
    <script src="js/page_client.js"></script>
</body>
</html>
