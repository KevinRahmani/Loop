<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.beans.ArticleEntity" %>
<%@ page import="model.beans.Basket" %>

<%@ page import="java.util.HashMap" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Loop</title>

    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/panier.css">
    <link rel="stylesheet" href="css/footer.css">


    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://kit.fontawesome.com/82e270d318.js" crossorigin="anonymous"></script>
</head>
<body>

    <%@include file="shared/header.jsp"%>
    <div class="resume_panier">
        <c:choose>
            <c:when test="${not empty requestScope.basket}">
                <h2>Votre panier :</h2>
                <table>
                    <tr>
                        <th>Image</th>
                        <th>Vendeur</th>
                        <th>Nom</th>
                        <th>Quantité</th>
                        <th>Référence</th>
                        <th>Prix unitaire TTC</th>
                    </tr>
                    <c:forEach items="${requestScope.basket}" var="entry">
                        <c:set var="article" value="${entry.key}" />
                        <c:set var="quantity" value="${entry.value}" />
                        <tr>
                            <td><img src="${article.image}1.jpg" class="img_product" alt="${article.nom}" /></td>
                            <td>${article.vendeur}</td>
                            <td>${article.marque} ${article.nom}</td>
                            <td>
                                <div class="change_quantity">
                                    <button class="button-minus" id="minus-${article.id}" role="button"><i class="fa-solid fa-minus"></i></button>
                                    <input type="text" size="5" id="${article.id}" readonly="readonly" class="input_btn" value="${quantity}" />
                                    <button class="button-add" id="plus-${article.id}" role="button"><i class="fa-solid fa-plus"></i></button>
                                </div>
                            </td>
                            <td>${article.id}</td>
                            <td>${article.prix}</td>
                            <td class="bg_none"><button id="${article.id}"><i class="fa-solid fa-xmark"></i></button></td>
                        </tr>
                    </c:forEach>
                </table>
                <div class="validation">
                    <button id="annuler">Annuler</button>
                    <button id="valider">Valider</button>
                    <button id="opti">Optimiser le panier</button>
                </div>
                <div class="erreur" id="erreur"></div>
            </c:when>
            <c:otherwise>
                <h2>Votre panier est vide</h2>
            </c:otherwise>
        </c:choose>
    </div>

    <%@include file="shared/footer.jsp"%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="js/header.js"></script>
    <script src="js/panier.js"></script>
</body>
</html>
