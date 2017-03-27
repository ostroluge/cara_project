/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.contract;

import model.user.Insured;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author ostrowski
 */
@Entity
public class Contract implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private double subscriptionAmount;
    
    @ManyToOne
    @JoinColumn(name="insured_id")
    private Insured insured;
    
    @JoinColumn(name="contract_type_id")
    private ContractType contractType;

    public Contract() {

    }

    public Contract(double subscriptionAmount, Insured insured, ContractType contractType) {
        this.subscriptionAmount = subscriptionAmount;
        this.insured = insured;
        this.contractType = contractType;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getSubscriptionAmount() {
        return subscriptionAmount;
    }

    public void setSubscriptionAmount(double subscriptionAmount) {
        this.subscriptionAmount = subscriptionAmount;
    }

    public Insured getInsured() {
        return insured;
    }

    public void setInsured(Insured insured) {
        this.insured = insured;
    }

    public ContractType getContractType() {
        return contractType;
    }

    public void setContractType(ContractType contractType) {
        this.contractType = contractType;
    }
}
