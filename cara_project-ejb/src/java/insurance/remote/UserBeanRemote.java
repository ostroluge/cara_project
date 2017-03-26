/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package insurance.remote;

import model.user.Insured;
import java.util.List;
import javax.ejb.Remote;

/**
 *
 * @author ostrowski
 */
@Remote
public interface UserBeanRemote {

    void addUser(String firstName,
                        String lastName,
                        String login,
                        String mail,
                        String password,
                        String address,
                        String role);
    
    List<Insured> selectAllInsured();
}
