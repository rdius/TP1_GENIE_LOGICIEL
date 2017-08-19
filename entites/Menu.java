package com.all.entites;
import java.util.Scanner;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
public class Menu {
    public static final int TACHE_NOUVEAU_STATUS = 1;
    public static final int TACHE_STATUS_PROGRESSION = 2;
    public static final int TACHE_STATUS_FINI = 3;
    public static final int QUITTER_TACHE = 4;
    
    public static final int MODIFIER_NOUVEAU = 1;    
    public static final int MODIFIER_EDITER = 2;    
    public static final int MODIFIER_SUPPRIMER = 3;    
    public static final int MODIFIER_AJOUTER = 4;    
    public static final int QUITTER_MODIFIER = 5;    
    
    public static final int MODIFIER_TACHE = 1;
    public static final int MODIFIER_MEMBRE = 2;
    public static final int ASSIGNER_TACHE_MEMBRE = 3;
    public static final int LISTER_TACHE_PAR_ID_MEMBRE = 4;
    public static final int LISTER_TACHE_PAR_ID_STATUS = 5;
    public static final int QUITTER = 6;
    
    public static final int IGNORER_ACTION = -1;        
    
    private static Scanner all_scanner = new Scanner(System.in);
    
    public static String delimiteur = "\n*************************************************";
    
    public static void voirMenuUtilisateur() {
        StringBuilder description = new StringBuilder();
        description.append(delimiteur);
        description.append("\n\tMENU PRINCIPAL");
        description.append(delimiteur);
        description.append("\n1. Créer, editer, suprimer, ajouter une Tâche");
        description.append("\n2. Créer, editer, suprimer, ajouter un memebre");
        description.append("\n3. Assigner une tâches à un membre");
        description.append("\n4. Retrouver toutes les tâches assignées à un membre par son ID");
        description.append("\n5. Retrouver toutes les tâches selon leur ID avec le membre assigné");
        description.append("\n6. Quitter le programme");
        System.out.println(description);
    }

    public static void voirMenuEtatTaches() {
        StringBuilder description = new StringBuilder();
        description.append(delimiteur);
        description.append("\n\tMENU TACHES");
        description.append(delimiteur);
        description.append("\n1. NOUVELLE (Disponible)");
        description.append("\n2. EN COURS d'exécution");
        description.append("\n3. TERMINEE");
        description.append("\n4. SUPPRIMER REQUETTE");
        System.out.println(description);
    }

    public static void voirMenuModifierTaches() {        
        StringBuilder description = new StringBuilder();
        description.append(delimiteur);
        description.append("\n\tMENU CHOIX DE TACHES");
        description.append(delimiteur);
        description.append("\n1. CREER");
        description.append("\n2. EDITER");
        description.append("\n3. SUPPRIMER");
        description.append("\n4. AJOUTER");
        description.append("\n5. SUPPRIMER REQUETTE");
        System.out.println(description);
    }
     
    public static void voirMenuModifierMembre() {        
        StringBuilder description = new StringBuilder();
        description.append(delimiteur);
        description.append("\n\tMENU POUR MODIFIER LES MEMBRES");
        description.append(delimiteur);
        description.append("\n1. CREER");
        description.append("\n2. EDITER");
        description.append("\n3. SUPPRIMER");
        description.append("\n4. AJOUTER");
        description.append("\n5. SUPPRIMER REQUETTE");
        System.out.println(description);
    }
    
    /*public static void erreur (){
    	System.out.println("\nChoix incorrect, veuiller choisir un des chiffres ci-dessus!");
    }  */
    
    public static int menuChoixUtilisateur() {
        return choixMenu(1, 6);
    }

    public static int menuChoixStatusTache() {
        return choixMenu(1, 4);
    }

    public static int menuModificationChoix() {
        return choixMenu(1, 5);
    }
    
    public static int choixMenu(int debut, int fin) {
        int choix = fin;
        boolean isChooseAgain;

        do {
            System.out.print("\nEntrer votre choix: ");
            isChooseAgain = false;
            try {                                
            	choix = Integer.parseInt(all_scanner.nextLine());                
                if (choix < debut || choix > fin) {
                    isChooseAgain = true;
                }
            } catch (Exception e) {
                isChooseAgain = true;
            }
            if (isChooseAgain) {
            	System.out.println("\nChoix incorrect, veuiller choisir un des chiffres ci-dessus!");
            }
        } while (isChooseAgain);

        return choix;
    }
    
      /**
     * 
     * @param start
     * @param end
     * @return -1 if cancel action
     */
    public static int menuDeSuppression(int debut, int fin) {
        int choix = fin;
        boolean isChooseAgain;

        do {
            System.out.print("\nEntrer votre choix: ");
            isChooseAgain = false;
            try {                                
                String query = all_scanner.nextLine();
                if (query.equals("")) {
                    choix = IGNORER_ACTION;
                    break;
                }
                choix = Integer.parseInt(query);                
                if (choix < debut || choix > fin) {
                    isChooseAgain = true;
                }
            } catch (Exception e) {
                isChooseAgain = true;
            }
            if (isChooseAgain) {
            	System.out.println("\nChoix incorrect, veuiller choisir un des chiffres ci-dessus!");
            }
        } while (isChooseAgain);

        return choix;
    }
}

