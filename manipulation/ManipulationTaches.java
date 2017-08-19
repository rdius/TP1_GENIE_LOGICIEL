/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.all.manipulation;

import com.all.entites.Membres;
import com.all.entites.Taches;
import com.all.entresortie.FichierEntreSortie;
import com.all.manipulation.Enumeration.AttributTaches;
import com.all.manipulation.Enumeration.GestionResultat;
import com.all.manipulation.Enumeration.Status;
import com.all.entites.Menu;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ManipulationTaches implements Manipulation {

    private static int id = 1;
    private Scanner allscanner = new Scanner(System.in);
    private final List<Taches > mListTask = FichierEntreSortie.getListTaskFromFile();
    private final static ManipulationTaches TaskManager = new ManipulationTaches();    

    public static ManipulationTaches getInstance() {
        return TaskManager;
    }

    private ManipulationTaches() {
    	if (!mListTask.isEmpty()) {
			id = mListTask.size() + 1;
		}
    }

    @Override
    public boolean creer() {
    	Taches tache = newTache();
        mListTask.add(tache);
        return true;
    }

    private Taches newTache() {
        System.out.print("\nEntrer le nom de la tache: ");
        String nom = allscanner.nextLine();
        System.out.print("Entrer la description de tache : ");
        String desc = allscanner.nextLine();
        return new Taches(id++, nom, desc);
    }

    @Override
    public boolean editer(int id) {
        boolean resultat = true;
        AttributTaches[] attributTâche = new AttributTaches[]{AttributTaches.NOM, AttributTaches.STATUS, AttributTaches.DESCRIPTION, AttributTaches.MEMBRE};
        int numMis = attributTâche.length;
        Taches tache = trouverTacheParId(id);
        if (tache != null) {
            for (int i = 0; i < numMis; i++) {
                boolean isInEdit;
                do {
                	GestionResultat captureResult = editerAttribut(tache, attributTâche[i], null);
                    if (captureResult == GestionResultat.SUPPRIME) {
                        break;
                    }
                    isInEdit = demanderChangerId(attributTâche[i]);
                } while (isInEdit);
            }
        } else {
        	resultat = false;
        }
        return resultat;
    }

    private boolean demanderChangerId(AttributTaches attr) {
        System.out.print("Changer " + attr + "  <O/N>: ");
        String querry = allscanner.nextLine();
        boolean isInEdit = querry.equalsIgnoreCase("O");
        return isInEdit;
    }

    private GestionResultat editerAttribut(Taches tache, AttributTaches attr, Membres membre) {
    	GestionResultat resultat = GestionResultat.ECHEC;
        switch (attr) {
            case NOM:
                System.out.println("\nTaper sur ENTRER pour annuler l'édition du nom");
                System.out.print("Entrer le nouveau nom : ");
                String newNom = allscanner.nextLine();
                resultat = editNom(tache, newNom);
                break;

            case DESCRIPTION:
                System.out.println("\nTaper sur ENTRER pour annuler l'édition de la description");
                System.out.print("Entrer la nouvelle description : ");
                String newDesc = allscanner.nextLine();
                resultat = editDescription(tache, newDesc);
                break;

            case STATUS:
                System.out.println("\nDonner le nouveau nom de la tache");
                Menu.voirMenuEtatTaches();
                int choix = Menu.menuChoixStatusTache();
                resultat = setNouvelStatus(tache, choix);
                break;

            case MEMBRE:
            	resultat = setNouveauMembre(tache, membre);
                break;

            default:
                System.out.println("Ignorer toutes les modifications");
                break;
        }
        return resultat;
    }

    private GestionResultat editNom(Taches tache, String newNom) {
        //cannot be failed
    	GestionResultat resultat = GestionResultat.ECHEC;
        if (!newNom.equals("")) {
        	tache.setName(newNom);
            resultat = GestionResultat.SUCCES;
        }
        return resultat;
    }

    private GestionResultat editDescription(Taches tache, String newDesc) {
        //cannot be failed
    	GestionResultat resultat = GestionResultat.SUPPRIME;
        if (!newDesc.equals("")) {
        	tache.setDescription(newDesc);
            resultat = GestionResultat.SUCCES;
        }
        return resultat;
    }

    private GestionResultat setNouvelStatus(Taches tache, int choix) {
    	GestionResultat resultat = GestionResultat.SUCCES;
        switch (choix) {
            case Menu.TACHE_NOUVEAU_STATUS:
            	tache.setStatus(Status.DISPONIBLE);
                break;

            case Menu.TACHE_STATUS_PROGRESSION:
            	tache.setStatus(Status.EN_COURS);
                break;

            case Menu.TACHE_STATUS_FINI:
            	tache.setStatus(Status.TERMINEE);
                break;

            case Menu.QUITTER_TACHE:
                resultat = GestionResultat.SUPPRIME;
                break;

            default:
                resultat = GestionResultat.ECHEC;
                break;
        }
        return resultat;
    }

    private GestionResultat setNouveauMembre(Taches tache, Membres membre) {
        if (membre == null) {
            return GestionResultat.ECHEC;
        }
        GestionResultat resultat = GestionResultat.ECHEC;
        if (!siAssigneTache(membre)) {
        	tache.setMember(membre);
            resultat = GestionResultat.SUCCES;
        }
        return resultat;
    }

    private boolean siAssigneTache(Membres membre) {
        if (membre == null) {
            return false;
        }
        Membres assigneMembre;
        for (Taches tache : mListTask) {
        	assigneMembre = tache.getMember();
            if (assigneMembre != null && assigneMembre.equals(membre)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean supprimer(int id) {
        boolean resultat;
        Taches tache = trouverTacheParId(id);
        if (tache != null) {
            mListTask.remove(tache);
            resultat = true;
        } else {
        	resultat = false;
        }
        return resultat;
    }

    private Taches trouverTacheParId(int id) {
        for (Taches tache : mListTask) {
            if (tache.getId() == id) {
                return tache;
            }
        }
        return null;
    }

    @Override
    public boolean ajouter(Object obj) {
        boolean resultat;
        if (obj instanceof Taches) {
            mListTask.add((Taches) obj);
            resultat = true;
        } else {
        	resultat = false;
        }
        return resultat;
    }

    public void afficheTouteLesTaches() {
        for (Taches tache : mListTask) {
            System.out.println(tache.toString());
        }
    }

    public List<Taches> trouverToutesLesTacheParIdMembre(int id) {
        List<Taches> list = new ArrayList<>();
        Membres membre;
        for (Taches tache : mListTask) {
        	membre = tache.getMember();
            if (membre != null && membre.getId() == id) {
                list.add(tache);
            }
        }
        return list;
    }

    public List<Taches> trouverToutesTachesParStatus(Status status) {
        List<Taches> list = new ArrayList<>();
        if (status == null) {
            return list;
        }
        for (Taches tache : mListTask) {
            if (tache.getStatus() == status) {
                list.add(tache);
            }
        }
        return list;
    }

    public Taches getTaskById(int id) {
        for (Taches tache : mListTask) {
            if (tache.getId() == id) {
                return tache;
            }
        }
        return null;
    }

    public int getTotalTask() {
        return mListTask.size();
    }

    public List<Taches> getmListTask() {
        return mListTask;
    }
        
}

