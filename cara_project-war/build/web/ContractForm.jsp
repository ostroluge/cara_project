<%-- 
    Document   : ContractForm
    Created on : 23 mars 2017, 21:59:38
    Author     : tostrowski
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ajout d'un contrat</title>
    </head>
    <body>
        <a href="./index.html">Retour</a>
        <br><br>
        <FORM ACTION = "AddContractServlet" METHOD = "POST">
            Assuré : <br/>
            <select name="insured">
                <c:forEach items="${requestScope['users']}" var="item">
                    <option value="${item.id}">
                        ${item.lastname}
                    </option>
                </c:forEach>
            </select> <br/>
            Type de contrat : <br/>
            <select name="contractType">
                <c:forEach items="${requestScope['contractTypes']}" var="item">
                    <option value="${item.id}">
                        ${item.description}
                    </option>
                </c:forEach>
            </select> <br/>
            Montant de la souscription <INPUT TYPE = "number" NAME = "subscriptionAmount"><br/>
            
            <h3>Automobile uniquement</h3>
            Modèle <INPUT TYPE = "text" NAME = "design"><br/>
            Plaque d'immatriculation <INPUT TYPE = "text" NAME = "registrationNumber"><br/>
            Nom du chauffeur principal <INPUT TYPE = "text" NAME = "nameMainDriver"><br/>
            
            <h3>Habitation uniquement</h3>
            Montant max <INPUT TYPE = "number" NAME = "maxAmount"><br/>
            Adresse <INPUT TYPE = "text" NAME = "address"><br/>
            
            <h3>Vie uniquement</h3>
            Montant capital <INPUT TYPE = "number" NAME = "capitalAmount"><br/>
            Durée minimale de la souscription <INPUT TYPE = "number" NAME = "minimumSubscriptionDuration"><br/><br/>
            <input TYPE="submit" VALUE="Créer le contrat">
            <input TYPE="reset"  VALUE="Reset">
        </FORM>
    </body>
</html>
