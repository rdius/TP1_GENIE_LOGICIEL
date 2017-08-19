/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.all.entites;
import java.io.Serializable;
import com.all.manipulation.Enumeration.Status;


@SuppressWarnings("serial")
public class Taches implements Serializable {

    private int id;
    private String nom;
    private String description;
    private Status status;
    private Membres membre;

    public Taches(int id, String nom, String description) {
        this(id, nom, description, Status.DISPONIBLE);
    }

    public Taches(int id, String nom, String description, Status status) {
        this.id = id;
        this.nom = nom;
        this.description = description;
        this.status = status;
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return nom;
    }

    public void setName(String nom) {
        this.nom = nom;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Membres getMember() {
        return membre;
    }

    public void setMember(Membres membre) {
        this.membre = membre;
    }

    @Override
    public String toString() {
    	 String delimiteur = "\n*************************************************";
        if (membre != null) {
            return delimiteur + "\nID: " + id
                    + "\nName: " + nom
                    + "\nDescription: " + description
                    + "\nStatus: " + status
                    + "\nMember: " + membre.toString() + "\n";
        }
        return delimiteur + "\nID: " + id
                + "\nName: " + nom
                + "\nDescription: " + description
                + "\nStatus: " + status + "\n";
    }
    
  
}


