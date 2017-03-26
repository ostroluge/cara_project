/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.contract;

import model.user.Insured;
import java.io.Serializable;
import javax.persistence.Entity;

/**
 *
 * @author ostrowski
 */
@Entity
public class Habitation extends Contract implements Serializable {
    
    private double maxAmount;
    private String address;
    
    public Habitation() {
        
    }

    public Habitation(double maxAmount, String address, double subscriptionAmount, Insured insured, ContractType contractType) {
        super(subscriptionAmount, insured, contractType);
        this.maxAmount = maxAmount;
        this.address = address;
    }

    
    
    public double getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(double maxAmount) {
        this.maxAmount = maxAmount;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
