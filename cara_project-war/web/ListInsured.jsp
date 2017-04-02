<%-- 
    Document   : ListInsured
    Created on : 2 avr. 2017, 16:59:56
    Author     : tostrowski
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des assurés</title>
    </head>
    <body>  
     <h1>Liste des assurés</h1>  
     
     <table>
         <tr>
            <th>Prénom</th>
            <th>Nom</th>
            <th>Login</th>
         </tr>
         <c:forEach items="${insured}" var="item">
         <tr>
            <td><c:out value="${item.firstname}" /></td>
            <td><c:out value="${item.lastname}" /></td>
            <td><c:out value="${item.login}" /></td>
            <td><a href="InsuredContractsServlet?insuredLogin=${item.login}">Voir les contrats</a></td>
         </tr>
        </c:forEach>
     </table>
     <c:if test="${insured.isEmpty()}">
            <div>Vous êtes associé avec aucun assuré.</div>
        </c:if>
    </body> 
    
</html>

