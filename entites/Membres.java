package com.all.entites;
import java.io.Serializable;

public class Membres implements Serializable {
    private int id;
    private String name;

    public Membres(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
    	 String delimiteur = "\n*************************************************";
        return delimiteur + "\nID: " + id + "\nName: " + name + "\n";
    }

    public enum AttributMembres {
        ID, NOM
    }
}
