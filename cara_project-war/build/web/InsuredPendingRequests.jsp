<%-- 
    Document   : InsuredPendingRequests
    Created on : 29 mars 2017, 17:30:01
    Author     : tostrowski
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des demandes en attente</title>
    </head>
    <body>
        <h3>Arrêt de contrat</h3>
        <h4>Automobile</h4>
        <table>
            <tr>
                <th>ID</th>
                <th>Montant souscription</th>
                <th>Modèle</th>
                <th>Immatriculation</th>
                <th>Conducteur principal</th>
            </tr>
            <c:forEach items="${automobiles}" var="automobile">
                <tr>
                    <td><c:out value="${automobile.id}" /></td>
                    <td><c:out value="${automobile.subscriptionAmount}" /></td>
                    <td><c:out value="${automobile.design}" /></td>
                    <td><c:out value="${automobile.registrationNumber}" /></td>
                    <td><c:out value="${automobile.nameMainDriver}" /></td>
                </tr>
             </c:forEach>
        </table>
        <c:if test="${automobiles.isEmpty()}">
            <div>Aucune demande en attente.</div>
        </c:if>
        <h4>Habitation</h4>
        <table>
            <tr>
                <th>ID</th>
                <th>Montant souscription</th>
                <th>Montant max</th>
                <th>Adresse</th>
            </tr>
            <c:forEach items="${habitations}" var="habitation">
                <tr>
                    <td><c:out value="${habitation.id}" /></td>
                    <td><c:out value="${habitation.subscriptionAmount}" /></td>
                    <td><c:out value="${habitation.maxAmount}" /></td>
                    <td><c:out value="${habitation.address}" /></td>
                </tr>
             </c:forEach>
        </table>
        <c:if test="${habitations.isEmpty()}">
            <div>Aucune demande en attente.</div>
        </c:if>
        <h4>Vie</h4>
        <table>
            <tr>
                <th>ID</th>
                <th>Montant souscription</th>
                <th>Montant capital</th>
                <th>Durée min souscription</th>
            </tr>
            <c:forEach items="${lifes}" var="life">
                <tr>
                    <td><c:out value="${life.id}" /></td>
                    <td><c:out value="${life.subscriptionAmount}" /></td>
                    <td><c:out value="${life.capitalAmount}" /></td>
                    <td><c:out value="${life.minimumSubscriptionDuration}" /></td>
                </tr>
             </c:forEach>
        </table>
        <c:if test="${lifes.isEmpty()}">
            <div>Aucune demande en attente.</div>
        </c:if>
        <h3>Souscription de contrat</h3>
        <table>
            <tr>
                <th>ID</th>
                <th>Catégorie</th>
                <th>Montant minimal</th>
                <th>Description</th>
            </tr>
            <c:forEach items="${createRequests}" var="createRequest">
                <tr>
                    <td><c:out value="${createRequest.id}" /></td>
                    <td><c:out value="${createRequest.contractType.category}" /></td>
                    <td><c:out value="${createRequest.contractType.minAmount}" /></td>
                    <td><c:out value="${createRequest.contractType.description}" /></td>
                </tr>
             </c:forEach>
        </table>
        <c:if test="${createRequests.isEmpty()}">
            <div>Aucune demande en attente.</div>
        </c:if>
    </body>
</html>
