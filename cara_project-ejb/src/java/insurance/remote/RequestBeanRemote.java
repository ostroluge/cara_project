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
}
