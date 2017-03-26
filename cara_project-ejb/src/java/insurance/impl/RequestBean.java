/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurance.impl;

import insurance.remote.RequestBeanRemote;
import java.util.List;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import model.contract.Request;

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

    @PermitAll
    @Override
    public List<Request> selectAll() {
        List<Request> requests = persistence.createQuery(
                "select r from Request r").getResultList();
        return requests;
    }
}
