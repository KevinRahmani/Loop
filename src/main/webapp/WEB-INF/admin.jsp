<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Loop</title>

    <link rel="stylesheet" href="css/header.css">
    <link rel="stylesheet" href="css/footer.css">
    <link rel="stylesheet" href="css/page_admin.css">

    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://kit.fontawesome.com/82e270d318.js" crossorigin="anonymous"></script>
</head>
<body>
    
    <%@include file="shared/header.jsp"%>
    <h2 class="title">Bonjour Administrateur ${sessionScope.user.nom}, ravis de vous revoir</h2>
    <div class="sous_titre">Gérez vos informations, ainsi que la confidentialité et la sécurité de vos données pour profiter au mieux des services Loop. <a href="../about_us.jsp">En savoir plus</a></div>
    
    <!-- info admin -->
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
            <div class="num_client">ID admin : ${sessionScope.user.id}</div>
            <div class="categorie">Catégorie :${sessionScope.type}</div>
            <div class="mail_all">
                <div class="title_mail">Envoyer un mail promotionnel à tous les clients :</div>
                <button id="mail_client">Envoyer</button>
            </div>
        </div>
    </div>
    
    <!-- container liste des produits vendus et acheté  -->
    <div class="container ">
        <div class="title_histo">
            <div class="container_chevron">
                <div id ="div_histo">Chiffres de ventes : </div>
                <i class="fa-solid fa-chevron-down" id="chevron"></i>
            </div>
            <div class="sous_titre_histo">Voir mon historique de ventes</div>
    
            <!-- avoir dans un tableau l'ensemble des produits du vendeur, les afficher et afficher la quantité vendue pour chaque produit, ensuite multiplier par le prix unitaire  -->
    
            <div class="notActive" id="toggle_histo">
                <c:forEach items="${requestScope.listArticleLoop}" var="product">
                    Nom : ${product.nom}<br/>
                    Quantité vendue : ${product.sales}<br/>
                    CA produit : <c:out value="${product.sales * product.prix}" /> €<br/><br/>
                </c:forEach><br/><br/>
                TOTAL CA : ${requestScope.revenue} €
            </div>
        </div>
    
        <!-- changer les coordonnées --><!-- changer les coordonnées -->
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
    
    <!-- ajouter/supprimer vendeur Loop -->
    
    <div class="container ">
        <div class="title_add">
            <div class="container_chevron">
                <div class ="div_coord">Ajouter un vendeur</div>
                <i class="fa-solid fa-chevron-down" id="chevron3"></i>
            </div>
            <!-- ajouter un vendeur -->
            <div class="sous_titre_histo">Un nouvel arrivé dans le marketplace ?</div>
            <div class="notActive" id="toggle_add">
                <form method="POST" action="addVendeur-servlet" id="form_add_Vendeur">
                    <div class="coord1">
                        <label for="nom">Nom :</label><input type="text" placeholder="nom" name="nom">
                    </div>
                    <div class="coord1">
                        <label for="password">Password :</label><input type="password" placeholder="password" name="password">
                    </div>
                    <div class="coord1">
                        <label for="mail">Mail :</label><input type="text" placeholder="mail" name="mail">
                    </div>
                    <div class="coord1">
                        <label for="adresse">Adresse :</label><input type="text" placeholder="adresse" name="adresse">
                    </div>
                    <input type="submit" value="Envoyer" class="submit_coord" style="margin-right: 40%">
                </form>
                <div class="erreur_add_Vendeur">
                    <c:if test="${not empty requestScope.errAddVendeur}">
                        ${requestScope.errAddVendeur}
                    </c:if>
                </div>
            </div>
        </div>
    
        <!-- supprimer un vendeur -->
        <div class="title_remove">
            <div class="container_chevron">
                <div class="div_coord">Supprimer un vendeur</div>
                <i class="fa-solid fa-chevron-down" id="chevron4"></i>
            </div>
            <div class="line_coord">Un changement dans votre équipe ?</div>
            <form method="POST" action="deleteVendeur-servlet" class="notActive" id="form_delete_vendeur">
                <select name="idVendeur" class="select_class" id="select_supp_vendeur">
                    <c:forEach items="${requestScope.listVendeur}" var="vendeur">
                        <option value="${vendeur.id}">${vendeur.nom}</option>
                    </c:forEach>
                </select>
                <input type="submit" value="Envoyer" class="submit_coord">
            </form>
            <div class="erreur_add_Vendeur" >
                <c:if test="${ not empty requestScope.errDeleteVendeur}">
                    ${requestScope.errDeleteVendeur}
                </c:if>
            </div>
        </div>
    </div>
    
    <!-- Partie ajouter/supprimer droits vendeur -->
    
    <div class="container ">
        <div class="title_add_livreur">
            <div class="container_chevron">
                <div class ="div_coord">Ajouter des droits</div>
                <i class="fa-solid fa-chevron-down" id="chevron5"></i>
            </div>
            <!-- ajouter des droits -->
            <div class="sous_titre_histo">Des nouveaux droits pour un vendeur ?</div>
            <div class="notActive" id="toggle_add_livreur">
                <form method="POST" action="addRightVendeur-servlet" id="form_add_livreur">
                    <div>
                        <label for="vendeurRight">Choisir :  </label>
                        <select id="vendeurRight" name="vendeur" class="select_class">
                            <c:forEach items="${requestScope.listVendeur}" var="vendeur">
                                <option value="${vendeur.id}">${vendeur.nom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div>
                        <label for="addRight">Ajouter le : </label>
                        <select id="addRight" name="right" class="select_class">
                            <option value="addRight">Droit d'ajouter</option>
                            <option value="modifyRight">Droit de modifier</option>
                            <option value="deleteRight">Droit de supprimer</option>
                        </select>
                    </div>
                    <input type="submit" value="Envoyer" class="submit_coord" style="margin-right: 40%">
                </form>
                <div class="erreur_add_livreur">
                    <c:if test="${not empty requestScope.errAddRight}">
                        ${requestScope.errAddRight}
                    </c:if>
                </div>
            </div>
        </div>
    
        <!-- supprimer des droits -->
        <div class="title_remove_livreur">
            <div class="container_chevron">
                <div class="div_coord">Supprimer des droits</div>
                <i class="fa-solid fa-chevron-down" id="chevron6"></i>
            </div>
            <div class="line_coord">Des droits en moins pour un vendeur ?</div>
            <form method="POST" action="deleteRightVendeur-servlet" class="notActive" id="form_delete_livreur">
                <div>
                    <label for="removeRight">Choisir : </label>
                    <select id="removeRight" name="vendeur" class="select_class">
                        <c:forEach items="${requestScope.listVendeur}" var="vendeur">
                            <option value="${vendeur.id}">${vendeur.nom}</option>
                        </c:forEach>
                    </select>
                </div>
                <div>
                    <label for="removeRightVendeur">Retirer le : </label>
                    <select id="removeRightVendeur" name="right" class="select_class">
                        <option value="addRight">Droit d'ajouter</option>
                        <option value="modifyRight">Droit de modifier</option>
                        <option value="deleteRight">Droit de supprimer</option>
                    </select>
                </div>
                <input type="submit" value="Envoyer" class="submit_coord">
            </form>
            <div class="erreur_add_livreur" >
                <c:if test="${not empty requestScope.errRemoveRight}">
                    ${requestScope.errRemoveRight}
                </c:if>
            </div>
        </div>
    </div>
    
    
    <!-- partie ajouter modifier supprimer un produit -->
    <div class="container_modif">
        <div class="title_x">Gestion des produits</div>
        <div class="flex_container_modif">
            <ul id="list-first">
                <li class="deroulant">
                    <div class="title_y" id="add">Ajouter un produit</div>
                    <div class="sous_titre_y">Remplisser les champs pour ajouter un produit</div>
                    <ul class="sous notActive" id="sous_add">
                        <li>
                            <form method="POST" action="addProduct-servlet" id="add_produit">
                                <input type="text" name="nom" placeholder="Nom">
                                <input type="text" name="marque" placeholder="Marque">
                                <input type="text" name="prix" placeholder="Prix">
                                <input type="text" name="stock" placeholder="Stock">
                                <input type="text" name="type" placeholder="Type">
                                <input type="text" name="couleur" placeholder="Couleur">
                                <select name="categorie-add" id="categorie-add" class="select_class">
                                    <option value="automobile">Automobile</option>
                                    <option value="bja">Bricolage jardin et maison</option>
                                    <option value="cm">Cuisine et Maison</option>
                                    <option value="ht">High-Tech</option>
                                    <option value="livre">Livre</option>
                                    <option value="mdb">Musique, DVD et Blu-ray</option>
                                    <option value="sports">Sports</option>
                                    <option value="vetements">Vetements</option>
                                </select>
                                <textarea name="description" placeholder="Description"></textarea>
                                <input type="submit" value="Valider" class="btn_submit_add">
                            </form>
                            <div class="erreur_add">
                                <c:if test="${not empty requestScope.errAdd}">
                                    ${requestScope.errAdd}
                                </c:if>
                            </div>
                        </li>
                    </ul>
                </li>
                <li class="deroulant">
                    <div class="title_y" id="modif">Modifier un produit</div>
                    <div class="sous_titre_y">Selectionner un produit à modifier</div>
                    <ul class="sous notActive" id="sous_modif">
                        <li>
                            <c:choose>
                                <c:when test="${not empty requestScope.listArticle}">
                                    <form method="POST" action="modifyProduct-servlet" id="modif_produit">
                                        <select name="list_produit_modif" id="list_produit_modif" class="select_class">
                                            <option value="default">Choisissez une option</option>
                                            <c:forEach items="${requestScope.listArticle}" var="product">
                                                <option value="${product.id}">${product.nom}</option>
                                            </c:forEach>
                                        </select>
                                        <input type="text" name="nom" placeholder="Nom" id="modif_nom">
                                        <input type="text" name="prix" placeholder="prix" id="modif_prix">
                                        <input type="text" name="stock" placeholder="Stock" id="modif_stock">
                                        <input type="text" name="type" placeholder="Type" id="modif_type">
                                        <input type="text" name="couleur" placeholder="Couleur" id="modif_couleur">
                                        <select name="categorie-modif" id="modif_categorie" class="select_class">
                                            <option value="automobile">Automobile</option>
                                            <option value="bja">Bricolage jardin et maison</option>
                                            <option value="cm">Cuisine et Maison</option>
                                            <option value="ht">High-Tech</option>
                                            <option value="livre">Livre</option>
                                            <option value="mdb">Musique, DVD et Blu-ray</option>
                                            <option value="sports">Sports</option>
                                            <option value="vetements">Vetements</option>
                                        </select>
                                        <textarea name="description" placeholder="Description" id="modif_description"></textarea>
                                        <input type="submit" value="Valider" class="btn_submit_add">
                                    </form>
                                    <div class="erreur_modif">
                                        <c:if test="${not empty sessionScope.errModify}">
                                            ${sessionScope.errModify}
                                        </c:if>
                                    </div>
                                </c:when>
                                <c:otherwise>
                                    <div class="erreur_modif">Pas de produit à modifier</div>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </ul>
                </li>
                <li class="deroulant">
                    <div class="delete_product">
                        <div class="title_y" id="supp">Supprimer un produit</div>
                        <div class="sous_titre_y">Selectionner un produit à supprimer dans la liste déroulant : </div>
                        <ul class="sous notActive" id="sous_supp">
                            <li>
                                <c:choose>
                                    <c:when test="${not empty requestScope.listArticle}">
                                        <form method="POST" action="deleteProduct-servlet" id="supp_produit">
                                            <select name="list_produit_supp" id="list_produit_supp" class="select_class">
                                                <c:forEach items="${requestScope.listArticle}" var="product">
                                                    <option value="${product.id}">${product.nom}</option>
                                                </c:forEach>
                                            </select>
                                            <input type="submit" value="Valider" class="btn_submit_add">
                                        </form>
                                        <div class="erreur_supp" id="erreur_supp">
                                            <c:if test="${not empty requestScope.errDelete}">
                                                ${requestScope.errDelete}
                                            </c:if>
                                        </div>
                                    </c:when>
                                    <c:otherwise>
                                        <div class='erreur_modif'>Pas de produit à supprimer</div>
                                    </c:otherwise>
                                </c:choose>
                            </li>
                        </ul>
                    </div>
                </li>
            </ul>
        </div>
    </div>

    <%@include file="shared/footer.jsp"%>
    
    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="js/header.js"></script>
    <script src="js/admin.js"></script>
</body>
</html>
