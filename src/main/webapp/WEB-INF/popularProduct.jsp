<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
    <link rel="stylesheet" href="css/popular.css">

    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://kit.fontawesome.com/82e270d318.js" crossorigin="anonymous"></script>
</head>
<body>
    
    <%@include file="shared/header.jsp"%>

    <section class="container_body">
        <section class="products" id="produit">
            <h1>Nos produits populaires</h1>
            <c:choose>
                <c:when test="${not empty requestScope.popularList}">
                    <c:set var="mode" value="categorie_display_left" />
                    <c:forEach items="${requestScope.popularList}" var="popular">

                        <!-- Partie titre -->
                        <div class="${mode}">
                            <div class="titre_categorie" id="${popular.article.categorie}">
                                <div class="titre">${popular.categorie}</div>
                                <div class="sous_titre">${popular.citation}</div>
                            </div>

                            <!-- Partie Image -->

                            <div class="box_truc">
                                <div class="image">
                                    <a href="redirection-servlet?requestedPage=product&product=${popular.article.id}">
                                        <img src="${popular.article.image}1.jpg" alt="${popular.article.nom}">
                                    </a>
                                    <div class="icons" id="${popular.article.id}">
                                        <button class="fa-solid fa-minus button_rose" id="minus"></button>
                                        <button class="cart-btn envoyer" id="submit"><span>Ajouter au panier</span></button>
                                        <button class="fa-solid fa-plus button_rose" id="plus"></button>
                                        <input type="text" readonly="readonly" class="number_product" id="${popular.article.id}" value="0"/>
                                    </div>
                                    <div class="content">
                                        <h3>${popular.article.marque} ${popular.article.nom}</h3>
                                        <div class="price">${popular.article.prix} € | Stock : ${popular.article.stock}</div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <c:choose>
                            <c:when test="${mode eq 'categorie_display_left'}">
                                <c:set var="mode" value="categorie_display_right" />
                            </c:when>
                            <c:otherwise>
                                <c:set var="mode" value="categorie_display_left" />
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>
                </c:when>
                <c:otherwise>
                    Problème lors de la récupération des données, veuillez contacter l'administrateur
                </c:otherwise>
            </c:choose>
        </section>
    </section>

    <%@include file="shared/footer.jsp"%>


    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="js/popular.js"></script>
    <script src="js/header.js"></script>
</body>
</html>
