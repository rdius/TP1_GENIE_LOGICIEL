/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.all.entresortie;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import com.all.entites.Membres;
import com.all.entites.Taches;

public class FichierEntreSortie {
    public static final String FICHIER_MEMBRE = "membres.txt";
    public static final String FICHIER_TACHE = "taches.txt";
    public static final String  SAVE_FICHIER_MEMBRE= "membre.txt";
    public static final String SAVE_FICHIER_TACHE = "tache.txt";
    
    
    @SuppressWarnings({ "unchecked", "resource" })
	public static List<Membres> getListMemberFromFile() {
        List<Membres> list = new ArrayList<>();
       // List<Membres> lists = new ArrayList<>();
        try {                                
            FileInputStream fin = new FileInputStream(FICHIER_MEMBRE);
            ObjectInputStream objIn = new ObjectInputStream(fin);
            //FileReader fin = new FileReader(FICHIER_MEMBRE);
           // ObjectInputStream objIn = new ObjectInputStream(fin);
            list = (ArrayList<Membres>) objIn.readObject();
            //lists = (ArrayList<Membres>) objIn.readObject();
        } catch (Exception ex) {
            System.out.println("Echec de l'ouverture du fichier des membres " + ex.getMessage());
        }
        return list;
    }
    
    @SuppressWarnings({ "unchecked", "resource" })
	public static List<Taches> getListTaskFromFile() {
        List<Taches> list = new ArrayList<>();
        try {                                
            FileInputStream fin = new FileInputStream(FICHIER_TACHE);
            ObjectInputStream objIn = new ObjectInputStream(fin);
            list = (ArrayList<Taches>) objIn.readObject();
        } catch (Exception ex) {
            System.out.println("Echec de l'ouverture du fichier des taches" + ex.getMessage());
        }
        return list;
    }
    
    @SuppressWarnings("resource")
	public static void writeListMemberToFile(List<Membres> list) {
        try {
            FileOutputStream fout = new FileOutputStream(FICHIER_MEMBRE);
            ObjectOutputStream objOut = new ObjectOutputStream(fout);
            objOut.writeObject(list);
        } catch (Exception ex) {
            System.out.println("Echec d'enregistrement des membres " + ex.getMessage());
        }
    }
    
    @SuppressWarnings("resource")
	public static void writeListTaskToFile(List<Taches> list) {
        try {
            FileOutputStream fout = new FileOutputStream(FICHIER_TACHE);
            ObjectOutputStream objOut = new ObjectOutputStream(fout);
            objOut.writeObject(list);
        } catch (Exception ex) {
            System.out.println("Echec d'enregistrment des taches " + ex.getMessage());
        }
    }
}
