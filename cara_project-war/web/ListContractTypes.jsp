<%-- 
    Document   : ListContractTypes
    Created on : 27 mars 2017, 15:48:18
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
            <td><a href="DeleteContractTypeServlet?idContractType=${item.id}">Supprimer</a></td>
         </tr>
        </c:forEach>
     </table>
    </body> 
    
</html>
