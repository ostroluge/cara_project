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
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.persistence.*;
import javax.ejb.Stateless;
import javax.persistence.PersistenceContext;
import javax.xml.registry.infomodel.User;
import model.contract.Automobile;
import model.contract.Contract;

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

    @RolesAllowed("Admin")
    @Override
    public void addUser(String firstName,
                        String lastName,
                        String login,
                        String mail,
                        String password,
                        String address,
                        String role,
                        String loginUnderwriter) {
        Utilisateur newUser;
        switch(role) {
            case "insured":
                Groups insured = persistence.find(Groups.class, "Insured");
                Underwriter mUnderwriter = persistence.find(Underwriter.class, loginUnderwriter);
                newUser = new Insured(address, mail, login, lastName, firstName, password, mUnderwriter);
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

    @RolesAllowed("Admin")
    @Override
    public List<Utilisateur> selectAll() {
        List<Utilisateur> user = persistence.createQuery(
                "select u from Utilisateur u").getResultList();
        return user;
    }

    @RolesAllowed("Underwriter")
    @Override
    public List<Insured> selectAllInsured() {
        List<Insured> insured = persistence.createQuery(
                "select i from Insured i").getResultList();
        return insured;
    }

    @RolesAllowed("Admin")
    @Override
    public void deleteUser(String loginUser) {
        Query query = persistence.createQuery("DELETE FROM Utilisateur u where u.login = :login"); 
        query.setParameter("login", loginUser).executeUpdate();
    }

    @RolesAllowed("Admin")
    @Override
    public List<Underwriter> selectAllUnderwriters() {
       List<Underwriter> underwriters = persistence.createQuery(
                "select u from Underwriter u").getResultList();
        return underwriters;
    }

    @RolesAllowed("Underwriter")
    @Override
    public List<Insured> selectInsuredByUnderwriter(String loginUnderwriter) {
        String request = "select u from Utilisateur as u where u.login ='" + loginUnderwriter + "'";
        Query query = persistence.createQuery(request);
        Utilisateur user;
        List<Insured> result = new ArrayList<>();
        try {
            user = (Underwriter)query.getSingleResult();
            if (user != null) {
                if (user instanceof Underwriter) {
                    List<Insured> insured = ((Underwriter) user).getInsured();
                    for (Insured i : insured) {
                        result.add(i);
                    }
                }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
}
