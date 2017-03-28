/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurance.impl;

import model.contract.Automobile;
import model.contract.Contract;
import model.contract.ContractType;
import model.contract.Habitation;
import model.contract.Life;
import model.user.Insured;
import insurance.remote.ContractBeanRemote;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import model.user.Utilisateur;

/**
 *
 * @author tostrowski
 */
@DeclareRoles({"Admin", "Insured", "Underwriter"})
@Stateless
public class ContractBean implements ContractBeanRemote {

    private static final String JPA_UNIT_NAME = "cara_project";
    
    @PersistenceContext(unitName = "cara_project")
    EntityManager persistence;
    
    @PermitAll
    @Override
    public void addContract(double subscriptionAmount, int contractTypeId, String loginInsured, String address, double maxAmount, double capitalAmount, double minimalSubscriptionDuration, String design, String nameMainDriver, String registrationNumber) {
        Contract newContract = null;
        ContractType contractType = persistence.find(ContractType.class, contractTypeId);
        Insured insured = persistence.find(Insured.class, loginInsured);
        
        switch(contractType.getCategory()) {
            case "automobile":
                newContract = new Automobile(design, registrationNumber, nameMainDriver,
                                            subscriptionAmount, insured, contractType);
                break;
            case "habitation":
                newContract = new Habitation(maxAmount, address, subscriptionAmount,
                                             insured, contractType);
                break;
            case "vie":
                newContract = new Life(capitalAmount, minimalSubscriptionDuration,
                                       subscriptionAmount, insured, contractType);
                break;
        }
    
        if (newContract != null) {
            insured.getListContracts().add(newContract);
            persistence.persist(newContract);
        }
    }

    @PermitAll
    @Override
    public List<Contract> selectAll() {
        List<Contract> contracts = persistence.createQuery(
                "select c from Contract c").getResultList();
        return contracts;
    }

    @PermitAll
    @Override
    public List<Automobile> selectAllAuto() {
        List<Automobile> contracts = persistence.createQuery(
                "select a from Automobile a").getResultList();
        return contracts;
    }
    
    @PermitAll
    @Override
    public List<Habitation> selectAllHabitation() {
        List<Habitation> contracts = persistence.createQuery(
                "select h from Habitation h").getResultList();
        return contracts;
    }

    @PermitAll
    @Override
    public List<Life> selectAllLife() {
        List<Life> contracts = persistence.createQuery(
                "select l from Life l").getResultList();
        return contracts;
    }

    @PermitAll
    @Override
    public List<Automobile> getAutomobileContractsByUser(String login) {
        String request = "select u from Utilisateur as u where u.login ='" + login + "'";
        Query query = persistence.createQuery(request);
        Utilisateur user = null;
        List<Automobile> result = null;
        try {
            user = (Insured)query.getSingleResult();
            if (user != null) {
                System.out.println("user != null");
                if (user instanceof Insured) {
                    System.out.println("user instanceof Insured");
                    List<Contract> contracts = ((Insured) user).getListContracts();
                    System.out.println("contract size = " + contracts.size());
                    for (Contract contract : contracts) {
                        if (contract instanceof Automobile) {
                            System.out.println("add contract");
                            result.add((Automobile)contract);
                        }
                    }
                }
            }
        } catch(Exception e) {
            
        }
        return result;
    }

    @PermitAll
    @Override
    public List<Habitation> getHabitationContractsByUser(String login) {
        return null;
    }

    @PermitAll
    @Override
    public List<Life> getLifeContractsByUser(String login) {
        return null;
    }

    @Override
    public void deleteContract(int contractId) {
        Query query = persistence.createQuery("DELETE FROM Contract c where c.id = :id"); 
        query.setParameter("id", contractId).executeUpdate();
    }
}
