<%-- 
    Document   : SignUpForm
    Created on : 2 avr. 2017, 16:32:45
    Author     : tostrowski
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <a href="./IndexServlet">Retour</a>
          <br><br>
          <FORM ACTION = "AddUserServlet" METHOD = "POST">
            Rôle de l'utilisateur : <br>
            <input type="radio" name="role" value="insured" checked> Assuré
            <br>
            <input type="radio" name="role" value="admin" >Administrateur
            <br>
            <input type="radio" name="role" value="underwriter">Courtier
            <br>
            Login : <INPUT TYPE = "text" NAME = "login">
            <br>
            Mot de passe : <INPUT TYPE = "password" NAME = "password">
            <br>
            Nom : <INPUT TYPE = "text" NAME = "lastName">
            <br>
            Prénom : <INPUT TYPE = "text" NAME = "firstName">
            <br>
            Email : <INPUT TYPE = "text" NAME = "email">
            <br>
            <h3>Uniquement pour les assurés</h3>
            Adresse : <INPUT TYPE = "text" NAME = "address">
            <br>
            Courtier : <br/>
            <select name="underwriter">
                <c:forEach items="${underwriters}" var="item">
                    <option value="${item.login}">
                        ${item.lastname}
                    </option>
                </c:forEach>
            </select> <br/><br/>
            <input TYPE="submit" VALUE="Créer le compte">
            <input TYPE="reset"  VALUE="Reset">
        </FORM>
    </body>
</html>
