/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurance.impl;

import insurance.remote.RequestBeanRemote;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.contract.Automobile;
import model.contract.Contract;
import model.contract.ContractType;
import model.contract.Request;
import model.user.Insured;
import model.user.Utilisateur;

/**
 *
 * @author tostrowski
 */
@DeclareRoles({"Admin", "Insured", "Underwriter"})
@Stateless
public class RequestBean implements RequestBeanRemote {
    
    private static final String JPA_UNIT_NAME = "cara_project";
    
    @PersistenceContext(unitName = "cara_project")
    EntityManager persistence;

    @RolesAllowed("Underwriter")
    @Override
    public List<Request> selectAll() {
        List<Request> requests = persistence.createQuery(
                "select r from Request r").getResultList();
        return requests;
    }

    @PermitAll
    @Override
    public List<Request> getRequestsByType(String type) {
        String request = "select r from Request as r where r.type ='" + type + "'";
        Query query = persistence.createQuery(request);
        List<Request> result = new ArrayList<>();
        try {
            result = query.getResultList();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }

    @RolesAllowed("Insured")
    @Override
    public List<Request> getRequestsByUser(String type, String login) {
        String requestText = "select u from Utilisateur as u where u.login ='" + login + "'";
        Query query = persistence.createQuery(requestText);
        Utilisateur user;
        List<Request> result = new ArrayList<>();
        try {
            user = (Insured)query.getSingleResult();
            if (user != null) {
                if (user instanceof Insured) {
                    List<Request> requests = getRequestsByType(type);
                    for (Request request : requests) {
                        if (request.getInsured().getLogin().equals(user.getLogin())) {
                            result.add(request);
                        }
                    }
                }
            }
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
        return result;
    }
    
    @RolesAllowed("Insured")
    @Override
    public void addRequest(String type, String loginInsured, Integer contractId, Integer contractTypeId) {
        Request newRequest = null;
        Insured insured = persistence.find(Insured.class, loginInsured);
        
        switch(type) {
            case "stop":
                Contract contract = persistence.find(Contract.class, contractId);
                newRequest = new Request(type, insured, contract, null);
                break;
            case "create":
                ContractType contractType = persistence.find(ContractType.class, contractTypeId);
                newRequest = new Request(type, insured, null, contractType);
                break;
        }
        
        if (newRequest != null) {
            persistence.persist(newRequest);
        }
    }

    @RolesAllowed("Underwriter")
    @Override
    public void deleteRequest(int requestId) {
        Query query = persistence.createQuery("DELETE FROM Request r where r.id = :id"); 
        query.setParameter("id", requestId).executeUpdate();
    }
}
