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
public class Life extends Contract implements Serializable {
    
    private double capitalAmount;
    private double minimumSubscriptionDuration;
    
    public Life() {
        
    }

    public Life(double capitalAmount, double minimumSubscriptionDuration, double subscriptionAmount, Insured insured, ContractType contractType) {
        super(subscriptionAmount, insured, contractType);
        this.capitalAmount = capitalAmount;
        this.minimumSubscriptionDuration = minimumSubscriptionDuration;
    }
    
    public double getCapitalAmount() {
        return capitalAmount;
    }

    public void setCapitalAmount(double capitalAmount) {
        this.capitalAmount = capitalAmount;
    }

    public double getMinimumSubscriptionDuration() {
        return minimumSubscriptionDuration;
    }

    public void setMinimumSubscriptionDuration(double minimumSubscriptionDuration) {
        this.minimumSubscriptionDuration = minimumSubscriptionDuration;
    }
}
