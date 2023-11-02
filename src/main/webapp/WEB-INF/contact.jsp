<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <link rel="stylesheet" href="css/header.css">

    <link rel="stylesheet" href="css/contact.css">
    <link rel="stylesheet" href="css/footer.css">

    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <script src="https://kit.fontawesome.com/82e270d318.js" crossorigin="anonymous"></script>


    <title>Loop</title>
</head>
<body>

    <%@include file="shared/header.jsp"%>


    <div class="container_form">
        <div class="title">Formulaire de contact</div>
        <div class="under_title">Une remarque ou une question à nous adresser ? N'attendez plus !</div>
        <form action="contactController-servlet" method="POST" id="form_contact">
            <h2>Envoyez-nous un message</h2>

            <div class="form-group">
                <label for="name">Nom</label>
                <input type="text" id="name" name="name" required>
            </div>

            <div class="form-group">
                <label for="email">Email</label>
                <input type="email" id="email" name="mail" required>
            </div>

            <div class="form-group">
                <label for="message">Message</label>
                <textarea id="message" name="message" required></textarea>
            </div>

            <button type="submit">Envoyer</button>
        </form>
        <div class="info">
            <c:if test="${not empty requestScope.status}">
                ${requestScope.status}
            </c:if>
        </div>

    </div>

    <%@include file="shared/footer.jsp"%>

</body>
</html>
