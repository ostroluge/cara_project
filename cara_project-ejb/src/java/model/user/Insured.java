/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

import model.contract.Contract;
import model.contract.Request;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author ostrowski
 */
@Entity
public class Insured extends Utilisateur implements Serializable {
    
    private String address;
    private String mailInsured;
    
    @OneToMany(mappedBy="insured")
    private List<Contract> listContracts;
    
    @OneToMany(mappedBy="insured")
    private List<Request> requests;
    
    @ManyToOne
    @JoinColumn(name="underwriter_login")
    private Underwriter underwriter;
    
    public Insured() {
        
    }

    public Insured(String address, String mailInsured, String login,
            String lastname, String firstname, String password,
            Underwriter underwriter) {
        super(login, lastname, firstname, password);
        this.address = address;
        this.mailInsured = mailInsured;
        this.listContracts = new ArrayList<>();
        this.underwriter = underwriter;
    }
    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMailInsured() {
        return mailInsured;
    }

    public void setMailInsured(String mailInsured) {
        this.mailInsured = mailInsured;
    }

    public List<Contract> getListContracts() {
        return listContracts;
    }

    public void setListContracts(List<Contract> listContracts) {
        this.listContracts = listContracts;
    }

    public List<Request> getRequests() {
        return requests;
    }

    public void setRequests(List<Request> requests) {
        this.requests = requests;
    }

    public Underwriter getUnderwriter() {
        return underwriter;
    }

    public void setUnderwriter(Underwriter underwriter) {
        this.underwriter = underwriter;
    }
}
