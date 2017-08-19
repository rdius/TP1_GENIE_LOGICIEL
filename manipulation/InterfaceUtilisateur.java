/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.all.manipulation;
import com.all.entites.Membres;
import com.all.entites.Menu;
import com.all.entites.Taches;
import com.all.entresortie.FichierEntreSortie;
import com.all.manipulation.Enumeration.GestionAction;
import com.all.manipulation.Enumeration.GestionResultat;
import java.util.List;
import java.util.Scanner;


public class InterfaceUtilisateur {

    private Scanner allscanner = new Scanner(System.in);
    private static final List<Membres> listMembre = ManipulationMembre.getInstance().getmListMember();
    private static final List<Taches> listTache = ManipulationTaches.getInstance().getmListTask();

    public void execcutableProgramme() {
        boolean isInChoosen;
        int choix;
        do {
            Menu.voirMenuUtilisateur();
            choix = Menu.menuChoixUtilisateur();
            processQuery(choix);
            isInChoosen = askContinueQuery();
        } while (isInChoosen);
        FichierEntreSortie.writeListMemberToFile(listMembre);
        FichierEntreSortie.writeListTaskToFile(listTache);
    }

    private boolean askContinueQuery() {
        System.out.print("\n Voulez-vous continuer? <O/N>: ");
        String question = allscanner.nextLine();
        boolean isInChoosen = question.equalsIgnoreCase("O");
        return isInChoosen;
    }

    
    private void processQuery(int choix) {
        switch (choix) {
            case Menu.MODIFIER_MEMBRE:
                InterfaceUtilisateurUtil.modifierMembre();
                break;

            case Menu.MODIFIER_TACHE:
            	InterfaceUtilisateurUtil.modifierTache();
                break;

            case Menu.ASSIGNER_TACHE_MEMBRE:
            	InterfaceUtilisateurUtil.assignerTacheToMembre();
                break;

            case Menu.LISTER_TACHE_PAR_ID_MEMBRE:
            	InterfaceUtilisateurUtil.afficherTouteTacheParIdMembre();
                break;

            case Menu.LISTER_TACHE_PAR_ID_STATUS:
            	InterfaceUtilisateurUtil.afficherTouteTacheParStatus();
                break;

            case Menu.QUITTER:
            	FichierEntreSortie.writeListMemberToFile(listMembre);
            	FichierEntreSortie.writeListTaskToFile(listTache);
                System.exit(0);
                break;

            default:
                System.out.println(GestionAction.ACTION_UTILISATEUR + " " + GestionResultat.ECHEC);
                break;
        }
    }
}

