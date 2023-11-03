<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.beans.ArticleEntity" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%
    ArticleEntity product = (ArticleEntity) request.getAttribute("product");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Loop</title>

    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/produit.css">

    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://kit.fontawesome.com/ebd46122da.js" crossorigin="anonymous"></script>
</head>
<body>
<%@ include file="shared/header.jsp" %>
<section class="resume_produit">

    <div class="image-wrap">


        <div class="contour">
            <div class="image" style="background-image:url('<%=product.getImage()%>1.jpg')">
            </div>
        </div>

        <div class="description">

            <div class="name"><%=product.getMarque()%> <%=product.getNom()%> : </div>
            <hr>

            <div class="tout_prix">
                <div id="montant"><%=product.getPrix()%> €</div>
                <div id="taxe">
                    <pre> TTC</pre>
                </div>
            </div>
            <br>

            <div class="icons" id="<%=product.getId()%>">
                <button class="cart-btn envoyer" id="submit"><span>Ajouter au panier<i style="padding-left:6px;"
                                                                                       class="fa-solid fa-basket-shopping"></i></span>
                </button>
                <button class="fa-solid fa-minus button_rose" id="minus"></button>
                <button class="fa-solid fa-plus button_rose" id="plus"></button>
                <input type="text" readonly="readonly" class="number_product" id="<%=product.getId()%>"
                       value="0"/>
            </div>
            <hr>

            <div class="longue">
                <p class="titre_info"><b>Description</b></p>
            </div>
            <div class="description_txt">
                <%=product.getDescription()%>
            </div>
            <hr>

            <div class="longue">
                <p class="titre_info"><b>Caractéristique</b></p>
            </div>
            <div class="type">
                <ul>
                    <li><span>Couleur :</span>  <%=product.getCouleur()%></li>
                    <li><span>Type :</span>  <%=product.getType()%></li>
                    <li><span>Stock :</span>  <%=product.getStock()%></li>
                    <li><span>Vendeur :</span> <%=product.getVendeur()%></li>
                </ul>
            </div>
            <hr>
            <div class="ref_produit">
                <p>Référence du produit : <%=product.getId()%></p>
            </div>

        </div>
    </div>
</section>

<%@include file="shared/footer.jsp" %>


<script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
<script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
<script src="js/header.js"></script>
<script src="js/produit.js"></script>
</body>
</html>
