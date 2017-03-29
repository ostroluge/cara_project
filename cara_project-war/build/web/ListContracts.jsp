<%-- 
    Document   : ListContracts
    Created on : 27 mars 2017, 22:18:06
    Author     : tostrowski
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des contrats</title>
    </head>
    <body>
        <h3>Automobile</h3>
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
                    <td><a href="DeleteContractServlet?idContract=${automobile.id}">Supprimer</a></td>
                </tr>
             </c:forEach>
        </table>
        <c:if test="${automobiles.isEmpty()}">
            <div>Pas de contrat de ce type.</div>
        </c:if>
        <h3>Habitation</h3>
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
                    <td><a href="DeleteContractServlet?idContract=${habitation.id}">Supprimer</a></td>
                </tr>
             </c:forEach>
        </table>
        <c:if test="${habitations.isEmpty()}">
            <div>Pas de contrat de ce type.</div>
        </c:if>
        <h3>Vie</h3>
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
                    <td><a href="DeleteContractServlet?idContract=${life.id}">Supprimer</a></td>
                </tr>
             </c:forEach>
        </table>
        <c:if test="${lifes.isEmpty()}">
            <div>Pas de contrat de ce type.</div>
        </c:if>
    </body>
</html>
