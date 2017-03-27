<%-- 
    Document   : listUsers
    Created on : 27 mars 2017, 15:33:05
    Author     : tostrowski
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Liste des utilisateurs</title>
    </head>
    <body>  
     <h1>Liste des utilisateurs</h1>  
     
     <table>
         <tr>
            <th>Prénom</th>
            <th>Nom</th>
            <th>Login</th>
         </tr>
         <c:forEach items="${Users}" var="item">
         <tr>
            <td><c:out value="${item.firstname}" /></td>
            <td><c:out value="${item.lastname}" /></td>
            <td><c:out value="${item.login}" /></td>
            <td><a href="DeleteUserServlet?idUser=${item.id}">Supprimer</a></td>
         </tr>
        </c:forEach>
     </table>
    </body> 
    
</html>
