<%-- 
    Document   : index
    Created on : 28 mars 2017, 11:31:58
    Author     : tostrowski
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
    </head>
    <body>
        <div>
            <c:if test="${isAdmin}">
                <h3>Admin</h3>
                <h4>Utilisateurs</h4>
                <a href="./AddUserServlet">Créer un nouveau compte</a> <br/>
                <a href="./UserListServlet">Liste des utilisateurs</a> <br/>
                <h4>Types de contrat</h4>
                <a href="./ContractTypeForm.html">Créer un nouveau type de contrat</a> <br/>
                <a href="./ContractTypeListServlet">Liste des types de contrat</a> <br/> <br/>
            </c:if>
            <c:if test="${isUnderwriter}">
                <h3>Courtier</h3>
                <a href="./AddContractServlet">Créer un nouveau contrat</a> <br/>
                <a href="./ContractListServlet">Liste des contrats</a> <br/>
                <a href="./InsuredListServlet">Liste des assurés</a> <br/> <br/>
            </c:if>
            <c:if test="${isInsured}">
                <h3>Assuré</h3>
                <a href="./InsuredContractsServlet">Consulter la liste des contrats</a> <br/>
                <a href="./InsuredContractTypesServlet">Consulter la liste des types de contrats</a> <br/>
                <a href="./InsuredPendingRequestsServlet">Consulter les demandes en attente</a> <br/> <br/>
            </c:if>
            <FORM ACTION="LogoutServlet" METHOD=POST>
                <INPUT TYPE=SUBMIT VALUE="Logout">
            </FORM>
        </div>
    </body>
</html>
