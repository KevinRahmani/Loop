        <%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.beans.StockEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="java.util.Arrays" %>


<%
    @SuppressWarnings("unchecked")
    List<String> listCategory = (List<String>) request.getAttribute("listCategory");
    List<StockEntity> listPopular = (List<StockEntity>) request.getAttribute("listPopular");
    List<String> listSlogan = Arrays.asList("Roulez dans nos meilleures routieres", "Bricolage et jardin ? Amusez-vous bien !", "Pour des femmes heureuses !", "Pour des Hommes heureux", "Une envie de voyager ?", "Un plaisir pour les yeux et les oreilles !", "Devenez la meilleure version de vous-même !", "Des beaux habits pour une belle journée");
    List<String> listCategoryDisplay = (List<String>) Arrays.asList("Automobiles", "Bricolage & co", "Cuisine et maison", "High-Tech", "Livres", "Musiques, DVD et Blu-ray", "Sports et Loisirs", "Vetements");
%>


<!DOCTYPE html>
<html lang="en">
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

    <%@ include file="shared/header.jsp" %>

    <section class="container_body">

        <section class="products" id="product">
            <h1>Nos produits populaires</h1>
            <% int i=0 ;
            String mode = "categorie_display_right";%>
            <c:forEach items="${listPopular}" var="product">
                <c:if test="${product.stock > 0}">
                <%--Partie titre--%>
            <div class="<%= mode %>" >
                    <div class="titre_categorie" id="${product.categorie}">
                            <div class="titre"> <%= listCategoryDisplay.get(i)%></div>
                        <div class="sous_titre"> <%= listSlogan.get(i)%></div>
                    </div>

                    <%--Partie Image--%>

                    <div class="box_truc">
                        <div class="image">
                            <a href="redirection-servlet?requestedPage=product&product=${product.id}">
                                <img src="${product.image}1.jpg" alt="${product.nom}">
                            </a>
                            <div class="icons" id="${product.id}" value = "${product.categorie}">
                                <button class="fa-solid fa-minus button_rose" id="minus"></button>
                                <button class="cart-btn envoyer" id="submit"><span>Ajouter au panier</span></button>
                                <button class="fa-solid fa-plus button_rose" id="plus"></button>
                                <input type="text" readonly="readonly" class="number_product" id="${product.id}" value="0"></input>
                            </div>
                            <div class="content">
                                <h3>${product.marque} ${product.nom}</h3>
                                <div class="price">
                                        ${product.prix} €
                                           | Stock : ${product.stock}
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <%
                                if(mode.equals("categorie_display_left")){
                                    mode = "categorie_display_right";
                                } else {
                                    mode = "categorie_display_left";
                                }
                                %>
            <%i++;%>


                </c:if>
    </c:forEach>

        </section>
    </section>

    <%@ include file="shared/footer.jsp" %>



        <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
        <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
        <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
        <script src="js/popular.js"></script>
        <script src="js/header.js"></script>


    </body>
    </html>



