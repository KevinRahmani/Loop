<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="model.beans.StockEntity" %>
<%@ page import="java.util.List" %>

<%
    @SuppressWarnings("unchecked")
    List<StockEntity> listCategorie = (List<StockEntity>) request.getAttribute("listCategorie");
    String categorie = (String) request.getAttribute("categorie");
%>

<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Loop</title>

    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/categorie.css">
    <link rel="stylesheet" href="css/footer.css">


    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://kit.fontawesome.com/ebd46122da.js" crossorigin="anonymous"></script>
</head>
<body>

    <%@ include file="shared/header.jsp" %>


    <section class="container-body">
        <div class="fond_accueil">
            <img src="img/<%=categorie%>/<%=categorie%>.jpg" alt="balbala">
            <div class="container">
                <div class="titre_section"><%=categorie%></div>
                <div class="citation">You want it, Loop will make it</div>
                <div class="lien_ancre">
                    <a href="#produit">Commander maintenant !</a>
                </div>
            </div>
        </div>
    </section>

    <section class="products" id="produit">
        <div class="titre-container">
            <h1 class="titre_produit">NOS DERNIERS PRODUITS :</h1>
            <select name="<%=categorie%>" id="filter_product">
                <option value="default">Selectionner une option</option>
                <option value="croissant">Prix croissant</option>
                <option value="decroissant">Prix décroissant</option>
                <option value="marque">Marque</option>
                <!-- A implementer le choix par type de produit-->
            </select>
        </div>
        <div class="box-container">
            <c:forEach items="${listCategorie}" var="product">
                <c:if test="${product.stock > 0}">
                    <div class="box">
                        <div class="image">
                            <a href="redirection-servlet?requestedPage=product&product=${product.id}">
                                <img src="${product.image}1.jpg" alt="${product.nom}">
                            </a>
                            <div class="icons" id="${product.id}">
                                <button class="fa-solid fa-minus button_rose" id="minus"></button>
                                <button class="cart-btn envoyer" id="submit"><span>Ajouter au panier</span></button>
                                <button class="fa-solid fa-plus button_rose" id="plus"></button>
                                <input type="text" readonly="readonly" class="number_product" id="${product.id}" value="0"/>
                            </div>
                            <div class="content">
                                <h3>${product.marque} ${product.nom}</h3>
                                <div class="price">
                                        ${product.prix} euros | Stock : ${product.stock}
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </c:forEach>

        </div>
    </section>

    <%@ include file="shared/footer.jsp" %>

    <script type="module" src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.esm.js"></script>
    <script nomodule src="https://unpkg.com/ionicons@5.5.2/dist/ionicons/ionicons.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="js/categorie.js"></script>
    <script src="js/header.js"></script>
</body>
</html>
