<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.beans.StockEntity" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%
    @SuppressWarnings("unchecked")
    List<StockEntity> watchList = (List<StockEntity>) request.getAttribute("watchList");
    @SuppressWarnings("unchecked")
    List<StockEntity> smartphoneList = (List<StockEntity>) request.getAttribute("smartphoneList");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/intro.css">
    <link rel="stylesheet" href="css/swiper-bundle.min.css"/>
    <link rel="stylesheet" href="css/index.css">
    <link rel="stylesheet" href="css/footer.css">

    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://kit.fontawesome.com/ebd46122da.js" crossorigin="anonymous"></script>


    <title>Loop</title>
</head>
<body>
<%@ include file="shared/intro.jsp" %>
<%@ include file="shared/header.jsp" %>

<section class="container_body">
    <div class="presentation">
        <img src="img/presentation.jpg" alt="presentation">
        <div class="note">Design your life, Loop will make it</div>
    </div>
    <div class="box-container">
        <div class="title">Nos produits du moment : <span class="gras">Smartphone</span></div>
        <!-- High-tech Smartphone carousel-->
        <div class="carousel_container swiper">
            <div class="slide-container">
                <div class="card-wrapper swiper-wrapper">
                    <c:forEach items="${smartphoneList}" var="smartphone">
                        <div class="card swiper-slide">
                            <!-- Page de produit à implémenter -->
                            <a href="redirection-servlet?requestedPage=product&product=${smartphone.id}">
                                <div class="img-box">
                                    <img src="${smartphone.image}1.jpg" alt="">
                                </div>
                            </a>
                            <div class="product-details">
                                <img src="img/logo/${smartphone.marque}.jpg" alt="">
                                <div class="name-category">
                                    <h3 class="name">${smartphone.nom} - <span
                                            class="gras">${smartphone.prix} euros</span></h3>
                                    <h4 class="constructeur">${smartphone.marque}</h4>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </div>
            <div class="swiper-button-next swiper-navBtn"></div>
            <div class="swiper-button-prev swiper-navBtn"></div>
            <div class="swiper-pagination"></div>
        </div>
        <div class="vendor-name">-Loop-</div>
    </div>
    <!-- High-tech Montres carousel-->
    <section id="tranding">
        <div class="second-slide text-Montre">
            <h3 class="text-center section-subheading">Nouveautés</h3>
            <h1 class="text-center section-heading">Montres connectées</h1>
        </div>
        <div class="second-slide">
            <div class="swiper tranding-slider">
                <div class="swiper-wrapper">
                    <!-- Slide start -->
                    <c:forEach items="${watchList}" var="watch">
                        <div class="swiper-slide tranding-slide">
                            <div class="tranding-slide-img">
                                <img src="${watch.image}1.jpg" alt="">
                            </div>
                            <!-- A implementer-->
                            <a href="redirection-servlet">
                                <div class="tranding-slide-content">
                                    <h1 class="montre-prix">${watch.prix} euros</h1>
                                    <div class="tranding-slide-content-bottom">
                                        <h2 class="montre-name">${watch.nom}</h2>
                                    </div>
                                </div>
                            </a>
                        </div>
                        <!-- Slide end -->
                    </c:forEach>
                </div>
                <div class="tranding-slider-control">
                    <div class="swiper-button-prev slider-arrow">
                        <ion-icon name="arrow-back-outline"></ion-icon>
                    </div>
                    <div class="swiper-button-next slider-arrow">
                        <ion-icon name="arrow-forward-outline"></ion-icon>
                    </div>
                    <div class="swiper-pagination"></div>
                </div>
            </div>
        </div>
    </section>
    <section class="cartes_cadeau">
        <div class="titre_carte">
            <div class="sous-titre"><span>Loop</span> cartes cadeaux</div>
            <div class="sous-titre-deux">Dites Merci avec une carte cadeau</div>
            <div class="sous-titre-deux">De 10 à 1000 euros pour vous et vos proches !</div>
        </div>
        <div class="img_carte">
            <img src="img/cartes_cadeau.png" alt="">
        </div>
    </section>

    <!--A IMPLEMENTER LES LIENS -->
    <section class="categorie_moment">
        <div class="categorie-titre">Nos catégories du moment</div>
        <div class="carre-categorie">
            <div class="carre-child">
                <a href="redirection-servlet?requestedPage=categorie&categorie=bja">
                    <img src="img/bja/bja.jpg" alt="">
                    <div class="carre-info">Bricolage, Jardin et Maison</div>
                </a>
            </div>
            <div class="carre-child">
                <a href="redirection-servlet?requestedPage=categorie&categorie=livre">
                    <img src="img/livre/livres.jpg" alt="">
                    <div class="carre-info">Livres</div>
                </a>
            </div>
            <div class="carre-child">
                <a href="redirection-servlet?requestedPage=categorie&categorie=ht">
                    <img src="img/ht/ht.jpg" alt="">
                    <div class="carre-info">High-Tech</div>
                </a>
            </div>
            <div class="carre-child">
                <a href="redirection-servlet?requestedPage=categorie&categorie=sports">
                    <img src="img/sports/sports.jpg" alt="">
                    <div class="carre-info">Sports et Loisirs</div>
                </a>
            </div>
            <div class="carre-child">
                <a href="redirection-servlet?requestedPage=categorie&categorie=vetements">
                    <img src="img/vetements/vetement.jpg" alt="">
                    <div class="carre-info">Vêtements</div>
                </a>
            </div>
        </div>
    </section>
</section>


<%@ include file="shared/footer.jsp" %>

<script src="js/swiper-bundle.min.js"></script>
<script src="js/index.js"></script>
<script src="js/intro.js"></script>
<script src="js/header.js"></script>
</body>
</html>