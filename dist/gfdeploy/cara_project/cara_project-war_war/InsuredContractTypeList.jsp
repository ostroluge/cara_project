<%-- 
    Document   : InsuredContractTypeList
    Created on : 29 mars 2017, 16:21:58
    Author     : tostrowski
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des types de contrat</title>
    </head>
    <body>  
     <h1>Liste des types de contrat</h1>  
     <table>
         <tr>
            <th>Catégorie</th>
            <th>Description</th>
            <th>Montant minimal</th>
         </tr>
         <c:forEach items="${contractTypes}" var="item">
         <tr>
            <td><c:out value="${item.category}" /></td>
            <td><c:out value="${item.description}" /></td>
            <td><c:out value="${item.minAmount}" /></td>
            <td><a href="CreateContractRequestServlet?idContractType=${item.id}">Demande souscription</a></td>
         </tr>
        </c:forEach>
     </table>
     <c:if test="${contractTypes.isEmpty()}">
        <div>Pas de type de contrat.</div>
     </c:if>
    </body> 
    
</html>
