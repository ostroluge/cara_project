/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import model.contract.Request;

/**
 *
 * @author ostrowski
 */
@Entity
public class Underwriter extends Utilisateur implements Serializable {
    
    private String mailUnderwriter;
    
    @OneToMany(mappedBy="underwriter")
    private List<Insured> insured;

    public Underwriter() {
        
    }

    public Underwriter(String mailUnderwriter, String login, String lastname, String firstname, String password) {
        super(login, lastname, firstname, password);
        this.mailUnderwriter = mailUnderwriter;
    }
    
    public String getMailUnderwriter() {
        return mailUnderwriter;
    }

    public void setMailUnderwriter(String mailUnderwriter) {
        this.mailUnderwriter = mailUnderwriter;
    }

    public List<Insured> getInsured() {
        return insured;
    }

    public void setInsured(List<Insured> insured) {
        this.insured = insured;
    }
}
