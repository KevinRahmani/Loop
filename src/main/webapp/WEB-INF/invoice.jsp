<%@ page import="model.beans.Basket" %>
<%@ page import="model.service.BasketService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Vendor</title>

    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/facture.css">

    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://kit.fontawesome.com/82e270d318.js" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="shared/header.jsp"%>
    <section class="container-table">
        <span class="overlay"></span>
        <div class="modal-box">
            <i class="fa-regular fa-circle-check"></i>
            <h2>Transaction en cours de paiement</h2>
            <h3>Valider sur votre application bancaire par mobile !</h3>
            <div class="buttons">
                <button class="close-btn">Annuler</button>
                <button id="confirm">Confirmer</button>
            </div>
        </div>
        <div class="facture">
            <h2>Votre facture</h2>
            <table>
                <tr>
                    <th>Produit</th>
                    <th>Nom</th>
                    <th>Quantité</th>
                    <th>Référence</th>
                    <th>Prix HT</th>
                    <th>Prix TTC</th>
                    <th>TOTAL</th>
                </tr>
                <c:forEach items="${sessionScope.hashmapBasket}" var="entry">
                    <c:set var="article" value="${entry.key}" />
                    <c:set var="quantity" value="${entry.value}"/>

                    <tr class="produit">
                        <td><img src="${article.image}1.jpg" class="img_product" alt="${article.nom}"/></td>
                        <td>${article.marque} ${article.nom}</td>
                        <td>
                            <div class="quantite">
                                <input type="text" size="5" id="${article.id}" readonly="readonly" class="input_btn" value="${quantity}"/>
                            </div>
                        </td>
                        <td>${article.id}</td>
                        <td><c:out value="${article.prix * (1-20/100) }" /> €</td>
                        <td>${article.prix}</td>
                        <td class="totalprix"><b><c:out value="${article.prix * quantity }" /> €</b></td>
                    </tr>
                </c:forEach>
                <tr>
                    <td colspan = 6></td>
                    <td class="total">${requestScope.totalPriceHT} € HT</td>
                </tr>
                <hr>
                <tr>
                    <td colspan=6></td>
                    <td class="total"><b>${requestScope.totalPriceTTC} € TTC</b></td>
                </tr>

                <c:if test="${not empty requestScope.fidelity}">
                    <tr>
                        <td colspan=5></td>
                        <td colspan=2 class="total2"><b>Remise point fidelité Loop -5% : <c:out value="${requestScope.totalPriceTTC * (1-5/100) }" /> € TTC</b></td>
                    </tr>
                </c:if>
            </table>

            <div class="validation">
                <a href="redirection-servlet?requestedPage=basket" class="annuler">Annuler</a>
                <button class="envoyer showModal">Envoyer</button>
            </div>
            <div class="message_info"></div>
        </div>
    </section>

   <%@include file="shared/footer.jsp"%>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="js/header.js"></script>
    <script src="js/facture.js"></script>
</body>
</html>
