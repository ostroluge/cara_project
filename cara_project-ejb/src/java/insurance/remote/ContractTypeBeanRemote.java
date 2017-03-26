/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurance.remote;

import model.contract.ContractType;
import java.util.List;
import javax.ejb.Remote;
import utils.Category;

/**
 *
 * @author tostrowski
 */
@Remote
public interface ContractTypeBeanRemote {
 
    void addContractType(Category category,
                         String description,
                         double minimalAmount);

    List<ContractType> getAllContractType();
}
