/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurance.remote;

import java.util.List;
import javax.ejb.Remote;
import model.contract.Request;

/**
 *
 * @author tostrowski
 */
@Remote
public interface RequestBeanRemote {
    
    List<Request> selectAll();
    
    List<Request> getRequestsByType(String type);
    
    List<Request> getRequestsByUser(String type, String user);
    
    void addRequest(String type,
                    String loginInsured,
                    Integer contractId,
                    Integer contractTypeId);

    void deleteRequest(int requestId);
    
    Request getRequestByContractId(int contractId);
}
