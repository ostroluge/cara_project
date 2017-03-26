/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author ostrowski
 */
@Entity
public class Admin extends Utilisateur implements Serializable {
    
    private String mailAdmin;
    
    public Admin() {
        
    }

    public Admin(String mailAdmin, String login, String lastname, String firstname, String password) {
        super(login, lastname, firstname, password);
        this.mailAdmin = mailAdmin;
    }
    
    public String getMailAdmin() {
        return mailAdmin;
    }

    public void setMailAdmin(String mailAdmin) {
        this.mailAdmin = mailAdmin;
    }
}
