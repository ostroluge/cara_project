/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurance.impl;

import model.contract.ContractType;
import model.user.Utilisateur;
import insurance.remote.ContractTypeBeanRemote;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import utils.Category;

/**
 *
 * @author tostrowski
 */
@DeclareRoles({"Admin", "Insured", "Underwriter"})
@Stateless
public class ContractTypeBean implements ContractTypeBeanRemote {

    private static final String JPA_UNIT_NAME = "cara_project";
    
    @PersistenceContext(unitName = "cara_project")
    EntityManager persistence;
    
    @PermitAll
    @Override
    public void addContractType(Category category, String description, double minimalAmount) {
        ContractType newContractType = new ContractType();
        newContractType.setCategory(category.getName());
        newContractType.setDescription(description);
        newContractType.setMinAmount(minimalAmount);
        
        persistence.persist(newContractType);
    }

    @PermitAll
    @Override
    public List<ContractType> getAllContractType() {
       List<ContractType> contractTypes = persistence.createQuery(
                "select c from ContractType c").getResultList();
        return contractTypes;
    }

    @Override
    public void deleteContractType(int contractTypeId) {
        Query query = persistence.createQuery("DELETE FROM ContractType c where c.id = :id"); 
        query.setParameter("id", contractTypeId).executeUpdate();
    }
}
