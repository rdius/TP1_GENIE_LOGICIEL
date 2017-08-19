/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.all.manipulation;
import java.util.Scanner;

import com.all.entites.Membres.AttributMembres;
import com.all.entites.Menu;
import com.all.manipulation.Enumeration.GestionAction;
import com.all.manipulation.Enumeration.GestionResultat;


public class ModificationMembre {

    private static Scanner allscanner = new Scanner(System.in);
    private ManipulationMembre manipulationTache;

    public ModificationMembre(ManipulationMembre manipulationTache) {
        this.manipulationTache = manipulationTache;
    }

    public void modifierMembre() {
        boolean isInModify;
        String question;
        int choix;
        do {
            Menu.voirMenuModifierMembre();
            choix = Menu.menuModificationChoix();
            if (choix == Menu.QUITTER_MODIFIER) {
                System.out.println("\n Supprimer les modifications apportées <CREER, SUPPRIMER, AJOUTER, EDITER> à Membre");
                return;
            }
            serveToMembreQuestion(choix);
            System.out.print("\nContinuer les modifications <CREER, SUPPRIMER, AJOUTER, EDITER> sur Membre <O/N>: ");
            question = allscanner.nextLine();
            isInModify = question.equalsIgnoreCase("O");
        } while (isInModify);
    }

    private void serveToMembreQuestion(int choix) {
        switch (choix) {
            case Menu.MODIFIER_NOUVEAU:
                System.out.print("\nCréer un nouveau membre:");
                creerNouveauMembre();
                break;

            case Menu.MODIFIER_SUPPRIMER:
                supprimerMembreParId();
                break;

            case Menu.MODIFIER_EDITER:
                editerMembreParId();
                break;

            case Menu.MODIFIER_AJOUTER:
//                dosmt
                break;

            default:
                System.out.println("\nLa modification sur le membre à échouée");
                break;
        }
    }

    private void creerNouveauMembre() {
        boolean isInDelete;
        do {
            boolean resultat = manipulationTache.creer();
            if (resultat) {
            	notifierResultat(GestionAction.CREER, GestionResultat.SUCCES);
            } else {
            	notifierResultat(GestionAction.CREER, GestionResultat.ECHEC);
            }
            isInDelete = demandContinuerCreation();
        } while (isInDelete);
    }

    private boolean demandContinuerCreation() {
        System.out.print("\n Voulez-vous continuer à créer des membres<O/N>: ");
        String question = allscanner.nextLine();
        boolean isInDelete = question.equalsIgnoreCase("O");
        return isInDelete;
    }

    private void supprimerMembreParId() {
        boolean isInDelete;
        do {
        	manipulationTache.afficherListeDesMembres();
            int id = getDeleteMemberId();
            if (id != Menu.IGNORER_ACTION) {
                boolean isSuccess = manipulationTache.supprimer(id);
                afficherMembresApresSuppression();
                isInDelete = demandContinuerSuppression();
                if (isSuccess) {
                    notifierResultat(GestionAction.SUPPRIME, GestionResultat.SUCCES);
                } else {
                    notifierResultat(GestionAction.SUPPRIME, GestionResultat.ECHEC);
                }
            } else {
                notifierResultat(GestionAction.SUPPRIME, GestionResultat.SUPPRIME);
                isInDelete = false;
            }
        } while (isInDelete);

    }

    private int getDeleteMemberId() {
        System.out.println("\nTaper sur ENTRER si vous désirez ignorer les modifications");
        System.out.println("Entrer l'ID du membre à supprimer");
        int id = Menu.menuDeSuppression(1, manipulationTache.getTotalMembres());
        return id;
    }

    private void afficherMembresApresSuppression() {
        System.out.print("\nTaper ENTRER pour voir la liste des membres après suppression");
        allscanner.nextLine();
        manipulationTache.afficherListeDesMembres();
    }

    private boolean demandContinuerSuppression() {
        System.out.print("\nVoulez-vous supprimer d'autres membres? <O/N>: ");
        String query = allscanner.nextLine();
        boolean isInDelete = query.equalsIgnoreCase("O");
        return isInDelete;
    }

    private void editerMembreParId() {
        boolean isInEdit;
        do {
        	manipulationTache.afficherListeDesMembres();
            int id = getMemberIdToEdit();
            if (id != Menu.IGNORER_ACTION) {
                boolean isSuccess = manipulationTache.editer(id);
                displayMemberAfterEdit(id);
                isInEdit = demandContinuerEdition();
                if (isSuccess) {
                    notifierResultat(GestionAction.EDITER, GestionResultat.SUCCES);
                } else {
                    notifierResultat(GestionAction.EDITER, GestionResultat.ECHEC);
                }
            } else {
                notifierResultat(GestionAction.EDITER, GestionResultat.SUPPRIME);
                isInEdit = false;
            }
        } while (isInEdit);
    }

    private int getMemberIdToEdit() {
        System.out.println("\nTaper ENTRERE si vous souhaiter annuler");
        System.out.println("Entrer l'ID du membre à éditer ");
        int id = Menu.menuDeSuppression(1, manipulationTache.getTotalMembres());
        return id;
    }

    private void displayMemberAfterEdit(int id) {
        System.out.print("\nTaper ENTRER pour afficher la liste des membres apres suEnter to display the member after editted");
        allscanner.nextLine();
        //result true means we have a task with the id to avoid taskManager.getMemberById(id) be null
        System.out.println(manipulationTache.getMembreParID(id).toString());
    }

    private boolean demandContinuerEdition() {
        System.out.print("\nVoulez-vous continuer à créer des membres? <O/N>: ");
        String query = allscanner.nextLine();
        boolean isInDelete = query.equalsIgnoreCase("O");
        return isInDelete;
    }

    public void notifierResultat(GestionAction action, GestionResultat result) {
        System.out.println(action + " " + result);
    }

    public void notifierResultat(GestionAction action, AttributMembres attr, GestionResultat result) {
        System.out.println(action + " " + attr + " " + result);
    }
}
