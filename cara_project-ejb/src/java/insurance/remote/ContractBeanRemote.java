/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurance.remote;

import model.contract.Contract;
import java.util.List;
import javax.ejb.Remote;
import model.contract.Automobile;
import model.contract.Habitation;
import model.contract.Life;

/**
 *
 * @author tostrowski
 */
@Remote
public interface ContractBeanRemote {
    
    void addContract(double subscriptionAmount,
                     int contractTypeId,
                     int insuredId,
                     String address,
                     double maxAmount,
                     double capitalAmount,
                     double minimalSubscriptionDuration,
                     String design,
                     String nameMainDriver,
                     String registrationNumber);
    
    List<Contract> selectAll();
    
    List<Automobile> selectAllAuto();
    
    List<Habitation> selectAllHabitation();
    
    List<Life> selectAllLife();
    
    List<Automobile> getAutomobileContractsByUser(String login);
    
    List<Habitation> getHabitationContractsByUser(String login);
    
    List<Life> getLifeContractsByUser(String login);
    
    void deleteContract(int contractId);
}
