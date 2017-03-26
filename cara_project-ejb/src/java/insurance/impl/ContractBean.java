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
    public void addContract(double subscriptionAmount, int contractTypeId, int insuredId, String address, double maxAmount, double capitalAmount, double minimalSubscriptionDuration, String design, String nameMainDriver, String registrationNumber) {
        Contract newContract = null;
        ContractType contractType = persistence.find(ContractType.class, contractTypeId);
        Insured insured = persistence.find(Insured.class, insuredId);
        
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
}
