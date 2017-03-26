/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.user;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import org.apache.commons.codec.digest.DigestUtils;

/**
 *
 * @author ostrowski
 */
@Entity
public class Utilisateur implements Serializable {
    
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private int id;
    private String login;
    private String lastname;
    private String firstname;
    private String password;
    
    @JoinColumn(name = "NAME")
    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "users")
    private List<Groups> groups;
    
    public Utilisateur() {
        groups = new ArrayList<>();
    }

    public Utilisateur(String login, String lastname, String firstname,
            String password) {
        this.login = login;
        this.lastname = lastname;
        this.firstname = firstname;
        this.password = DigestUtils.sha256Hex(password);
        this.groups = new ArrayList<>();
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public List<Groups> getGroups() {
        return groups;
    }

    public void setGroups(List<Groups> groups) {
        this.groups = groups;
    }
}
