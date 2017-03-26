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
public class Automobile extends Contract implements Serializable {
    
    private String design;
    private String registrationNumber;
    private String nameMainDriver;
    
    public Automobile() {
        
    }

    public Automobile(String design, String registrationNumber, String nameMainDriver, double subscriptionAmount, Insured insured, ContractType contractType) {
        super(subscriptionAmount, insured, contractType);
        this.design = design;
        this.registrationNumber = registrationNumber;
        this.nameMainDriver = nameMainDriver;
    }
    
    public String getDesign() {
        return design;
    }

    public void setDesign(String design) {
        this.design = design;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public void setRegistrationNumber(String registrationNumber) {
        this.registrationNumber = registrationNumber;
    }

    public String getNameMainDriver() {
        return nameMainDriver;
    }

    public void setNameMainDriver(String nameMainDriver) {
        this.nameMainDriver = nameMainDriver;
    }
}
