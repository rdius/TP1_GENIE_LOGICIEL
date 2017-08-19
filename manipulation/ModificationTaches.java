/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.all.manipulation;
import java.util.Scanner;

import com.all.entites.Menu;
import com.all.manipulation.Enumeration.AttributTaches;
import com.all.manipulation.Enumeration.GestionAction;
import com.all.manipulation.Enumeration.GestionResultat;

public class ModificationTaches {

    private static Scanner allscanner = new Scanner(System.in);
    private ManipulationTaches manipulationTaches;

    public ModificationTaches(ManipulationTaches manipulationTaches) {
        this.manipulationTaches = manipulationTaches;
    }
    
    public void modifyTask() {
        boolean isInModify;
        String question;
        int choix;
        do {
            Menu.voirMenuModifierTaches();
            choix = Menu.menuModificationChoix();
            if (choix == Menu.QUITTER_MODIFIER) {
                System.out.println("\n Ignorer les modifications apportées <CREER, SUPPRIMER, AJOUTER, EDITER> à Tache");
                return;
            }
            serveModifierTache(choix);
            System.out.print("\n Voulez-vous continue les modifications <CREER, SUPPRIMER, AJOUTER, EDITER> à Tache? <O/N>: ");
            question = allscanner.nextLine();
            isInModify = question.equalsIgnoreCase("O");
        } while (isInModify);
    }

    private void serveModifierTache(int choice) {
        switch (choice) {
            case Menu.MODIFIER_NOUVEAU:
                System.out.print("\nCreer une tache:");
                creerNouvelleTache();
                break;

            case Menu.MODIFIER_SUPPRIMER:
                deleteTaskById();
                break;

            case Menu.MODIFIER_EDITER:
                editTaskById();
                break;

            case Menu.MODIFIER_AJOUTER:
//                dosmt
                break;

            default:
                System.out.println("La modification à échouée");
                break;
        }
    }

    private void creerNouvelleTache() {
        boolean isInDelete;
        do {
            boolean result = manipulationTaches.creer();
            if (result) {
                notifierResultat(GestionAction.CREER, GestionResultat.SUCCES);
            } else {
                notifierResultat(GestionAction.CREER, GestionResultat.ECHEC);
            }
            isInDelete = askContinueCreate();
        } while (isInDelete);
    }

    private boolean askContinueCreate() {
        System.out.print("\nVoulez-vous creer autres taches. <O/N>: ");
        String query = allscanner.nextLine();
        boolean isInDelete = query.equalsIgnoreCase("O");
        return isInDelete;
    }

    private void deleteTaskById() {
        boolean isInDelete;
        do {
        	manipulationTaches.afficheTouteLesTaches();
            int id = getDeleteTaskId();
            if (id != Menu.IGNORER_ACTION) {
                boolean isSuccess = manipulationTaches.supprimer(id);                                
                if (isSuccess) {
                    displayTaskAfterDeleted();
                    notifierResultat(GestionAction.SUPPRIME, GestionResultat.SUCCES);
                } else {
                    notifierResultat(GestionAction.SUPPRIME, GestionResultat.ECHEC);
                }
                isInDelete = askContinueDelete();
            } else {
            	notifierResultat(GestionAction.SUPPRIME, GestionResultat.SUPPRIME);
                isInDelete = false;
            }
        } while (isInDelete);

    }

    private int getDeleteTaskId() {
        System.out.println("\n Taper sur ENTRER pour annuler la modification");
        System.out.println(" Saisir l'ID de la tache à supprimer");
        int id = Menu.menuDeSuppression(1, manipulationTaches.getTotalTask());
        return id;
    }

    private void displayTaskAfterDeleted() {
        System.out.print("\nTaper sur ENTRE pour afficher la liste des taches apres suppression");
        allscanner.nextLine();
        manipulationTaches.afficheTouteLesTaches();
    }

    private boolean askContinueDelete() {
        System.out.print("\n Voulez-vous supprimer une autre tache? <O/N>: ");
        String query = allscanner.nextLine();
        boolean isInDelete = query.equalsIgnoreCase("O");
        return isInDelete;
    }

    private void editTaskById() {
        boolean isInEdit;
        do {
        	manipulationTaches.afficheTouteLesTaches();
            int id = getTaskIdToEdit();
            if (id != Menu.IGNORER_ACTION) {
                boolean isSuccess = manipulationTaches.editer(id);
                if (isSuccess) {
                    displayTaskAfterEdit(id);                    
                    notifierResultat(GestionAction.EDITER, GestionResultat.SUCCES);
                } else {
                    notifierResultat(GestionAction.EDITER, GestionResultat.ECHEC);
                }
                isInEdit = askContinueEdit();
            } else {
                notifierResultat(GestionAction.EDITER, GestionResultat.SUPPRIME);
                isInEdit = false;
            }
        } while (isInEdit);
    }

    private int getTaskIdToEdit() {
        System.out.println("\nTaper sur ENTRER pour annuler la modification");
        System.out.println(" Saisir l'ID de la tache à éditer");
        int id = Menu.menuDeSuppression(1, manipulationTaches.getTotalTask());
        return id;
    }

    private void displayTaskAfterEdit(int id) {
        System.out.print("\n Taper sur ENTRER pour afficher les taches après édition");
        allscanner.nextLine();
        //result true means we have a task with the id to avoid taskManager.getTaskById(id) be null
        System.out.println(manipulationTaches.getTaskById(id).toString());
    }

    private boolean askContinueEdit() {
        System.out.print("\n Voulez-vous éditer une autre tache? <O/N>: ");
        String query = allscanner.nextLine();
        boolean isInDelete = query.equalsIgnoreCase("O");
        return isInDelete;
    }

    public void notifierResultat(GestionAction action, GestionResultat result) {
        System.out.println(action + " " + result);
    }

    public void notifierResultat(GestionAction action, AttributTaches attr, GestionResultat result) {
        System.out.println(action + " " + attr + " " + result);
    }
}
