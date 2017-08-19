/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.all.manipulation;

import java.util.List;
import java.util.Scanner;
import com.all.entites.Membres;
import com.all.entites.Menu;
import com.all.entites.Taches;
import com.all.manipulation.Enumeration.GestionAction;
import com.all.manipulation.Enumeration.GestionResultat;
import com.all.manipulation.Enumeration.Status;

public class InterfaceUtilisateurUtil {

    private static Scanner allscanner = new Scanner(System.in);
    private static final ManipulationMembre memberManager = ManipulationMembre.getInstance();
    private static final ManipulationTaches taskManager = ManipulationTaches.getInstance();

    public static void modifierTache() {
    	ModificationTaches taskModification = new ModificationTaches(taskManager);
        //it will loop query
        taskModification.modifyTask();
    }

    public static void modifierMembre() {
        ModificationMembre memberModification = new ModificationMembre(memberManager);
        //it will loop query
        memberModification.modifierMembre();
    }

    public static void assignerTacheToMembre() {
    	voirTouteTache();
        int tacheId = choixTacheId();
        if (tacheId != Menu.IGNORER_ACTION) {
            //task get here cannot be null because of taskId always valid
            Taches tache = taskManager.getTaskById(tacheId);
            if (tache.getStatus() == Status.DISPONIBLE) {
            	voirTousMembre();
                int IdMembre = choixIdMembre();
                if (IdMembre != Menu.IGNORER_ACTION) {
                    //member get here cannot be null because of taskId always valid
                    Membres membre = memberManager.getMembreParID(IdMembre);
                    tache.setMember(membre);
                    tache.setStatus(Status.EN_COURS);
                    System.out.println(GestionAction.ASSIGNER + " " + GestionResultat.SUCCES);
                    voirTacheAssignee(tache);
                } else {
                    System.out.println(GestionAction.ASSIGNER + " " + GestionResultat.SUPPRIME);
                }
            }
        }
    }

    private static void voirTouteTache() {
        System.out.print("\nTaper ENTRE pour voir la liste des taches:");
        allscanner.nextLine();
        taskManager.afficheTouteLesTaches();
    }

    private static void voirTousMembre() {
        System.out.print("\n Taper ENTRE pour voir la liste des membres:");
        allscanner.nextLine();
        memberManager.afficherListeDesMembres();
    }

    private static void voirTacheAssignee(Taches tache) {
        System.out.print("\n Taper ENTRE pour voir les taches après assignation:");
        allscanner.nextLine();
        System.out.println(tache.toString());
    }

    private static int choixTacheId() {
        System.out.println(" Taper ENTRE si vous désirez annuler");
        System.out.println(" Saisir l'ID de la tache dont vous désirez assigner à un membre");
        int tacheId = Menu.choixMenu(1, taskManager.getTotalTask());
        return tacheId;
    }

    private static int choixIdMembre() {
        System.out.println("Taper ENTRE si vous désirez annuler");
        System.out.println("Saisir l'ID du membre dont vous désirez assigner cette tache ");
        int idMembre = Menu.choixMenu(1, memberManager.getTotalMembres());
        return idMembre;
    }

    public static void afficherTouteTacheParIdMembre() {
    	voirTousMembre();
        System.out.println(" Saisir l'ID du membre dont vous désirez voir les taches qui lui sont assignées");
        int Idmembre = Menu.choixMenu(1, memberManager.getTotalMembres());
        viewAllTaskByMemberId(Idmembre);
    }

    private static void viewAllTaskByMemberId(int id) {
        List<Taches> listTache = taskManager.trouverToutesLesTacheParIdMembre(id);
        for (Taches tache : listTache) {
            System.out.println(tache.toString());
        }
    }

    public static void afficherTouteTacheParStatus() {
        Menu.voirMenuEtatTaches();
        System.out.print("\n Saisir le Status de la tache");
        int choice = Menu.menuChoixStatusTache();
        Status status = getStatusByChoice(choice);
        List<Taches> listTache = taskManager.trouverToutesTachesParStatus(status);
        for (Taches task : listTache) {
            System.out.println(task.toString());
        }
    }
    
    private static Status getStatusByChoice(int choix) {
        switch (choix) {
            case Menu.TACHE_NOUVEAU_STATUS:
                return Status.DISPONIBLE;

            case Menu.TACHE_STATUS_PROGRESSION:
                return Status.EN_COURS;

            case Menu.TACHE_STATUS_FINI:
                return Status.TERMINEE;

            case Menu.QUITTER_TACHE:
                System.out.println(GestionAction.AFFICHER + " " + GestionResultat.SUPPRIME);
                return null;

            default:
                return null;
        }
    }
}