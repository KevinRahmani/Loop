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
    <link rel="stylesheet" href="css/about_us.css">

    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://kit.fontawesome.com/82e270d318.js" crossorigin="anonymous"></script>
</head>
<body>
    <%@include file="shared/header.jsp"%>

    <section class="container_body">
        <h1>A propos de nous</h1>
        <p>Avec ses produits abordables, modernes et étonnants, Loop est en Europe le magasin
            favori pour les petits budgets. D'origine néerlandaise, nous avons déjà ouvert plus
            de 2 100 magasins dans 10 pays.</p>
        <p>Vous aussi, vous aimez les produits originaux et pratiques ? Laissez-vous surprendre
            par notre assortiment étendu et varié contenant pas moins de 6 000 produits dans 17
            catégories différentes. Aux prix les plus bas évidemment ! Des petits prix pour un grand
            sourire est en effet notre devise !</p>
        <p>Chaque semaine, 150 nouveaux articles sont mis en rayon et vous trouverez de bonnes
            affaires à un prix encore plus intéressant dans les promotions de la semaine. Une raison
            de plus pour passer plus souvent ! Nous vous avertissons : ne soyez pas surpris si vous
            entrez pour acheter un détergent et que vous quittez le magasin fou de joie avec un
            sac rempli de plein d'autres choses pratiques à petits prix. C'est ce qu'on appelle
            l'effet Loop !</p>
        <p>Surprendre encore et encore des clients comme vous avec un vaste assortiment qui change
            constamment, à un prix aussi bas que possible : c'est notre objectif depuis plus de 25 ans.
            Des petits prix pour un grand sourire est ce que nous visons !</p>
        <p>D'un petit magasin à Enkhuizen, nous sommes passés depuis 1993 à une entreprise de vente au
            détail internationale avec plus de 2 000 magasins dans 10 pays et un chiffre d'affaires
            annuel de plus de 6,8 milliards d'euros (2021).</p>

        &nbsp;&nbsp;&nbsp;&nbsp;
    </section>


    <%@include file="shared/footer.jsp"%>

    <script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
    <script src="js/header.js"></script>
</body>
</html>
