/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurance.impl;

import model.user.Admin;
import model.user.Groups;
import model.user.Insured;
import model.user.Underwriter;
import model.user.Utilisateur;
import insurance.remote.UserBeanRemote;
import java.util.Collection;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.persistence.*;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.xml.registry.infomodel.User;

/**
 *
 * @author ostrowski
 */
@DeclareRoles({"Admin", "Insured", "Underwriter"})
@Stateless
public class UserBean implements UserBeanRemote {

    private static final String JPA_UNIT_NAME = "cara_project";
    private Collection<Utilisateur> users;

    @PersistenceContext(unitName = "cara_project")
    EntityManager persistence;

    public Collection<Utilisateur> getTeams() {
        if (users == null) {
            users = this.selectAll();
        }
        return users;
    }

    @PermitAll
    @Override
    public void addUser(String firstName,
                        String lastName,
                        String login,
                        String mail,
                        String password,
                        String address,
                        String role) {
        Utilisateur newUser;
        switch(role) {
            case "insured":
                Groups insured = persistence.find(Groups.class, "Insured");
                newUser = new Insured(address, mail, login, lastName, firstName, password);
                newUser.getGroups().add(insured);
                insured.getUsers().add(newUser);
                persistence.persist(newUser);
                persistence.merge(insured);
                break;
            case "underwriter":
                Groups underwriter = persistence.find(Groups.class, "Underwriter");
                newUser = new Underwriter(mail, login, lastName, firstName, password);
                newUser.getGroups().add(underwriter);
                underwriter.getUsers().add(newUser);
                persistence.persist(newUser);
                persistence.merge(underwriter);
                break;
            case "admin":
                Groups administrator = persistence.find(Groups.class, "Admin");
                newUser = new Admin(mail, login, lastName, firstName, password);
                newUser.getGroups().add(administrator);
                administrator.getUsers().add(newUser);
                persistence.persist(newUser);
                persistence.merge(administrator);
                break;
        }
    }

    @PermitAll
    @Override
    public List<Utilisateur> selectAll() {
        List<Utilisateur> user = persistence.createQuery(
                "select u from Utilisateur u").getResultList();
        return user;
    }

    @PermitAll
    @Override
    public List<Insured> selectAllInsured() {
        List<Insured> insured = persistence.createQuery(
                "select i from Insured i").getResultList();
        return insured;
    }

    @PermitAll
    @Override
    public void deleteUser(String loginUser) {
        Query query = persistence.createQuery("DELETE FROM Utilisateur u where u.login = :login"); 
        query.setParameter("login", loginUser).executeUpdate();
    }
}
