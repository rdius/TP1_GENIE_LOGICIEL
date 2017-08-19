/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.all.manipulation;
import com.all.entites.Membres;
import com.all.entites.Membres.AttributMembres;
import com.all.entresortie.FichierEntreSortie;
import com.all.manipulation.Enumeration.GestionResultat;

import java.util.List;
import java.util.Scanner;


public class ManipulationMembre implements Manipulation {

	private static int id = 1;
	private Scanner allscanner = new Scanner(System.in);
	private final List<Membres> mListMember = FichierEntreSortie.getListMemberFromFile();
	private final static ManipulationMembre gestionMembre = new ManipulationMembre();

	public static ManipulationMembre getInstance() {
		return gestionMembre;
	}

	private ManipulationMembre() {
		if (!mListMember.isEmpty()) {
			id = mListMember.size() + 1;
		}
	}

	@Override
	public boolean creer() {
		Membres membre = CreerNouveauMembre();
		mListMember.add(membre);
		return true;
	}

	private Membres CreerNouveauMembre() {
		System.out.print("\n Saisir le nom: ");
		String nom = allscanner.nextLine();
		return new Membres(id++, nom);
	}

	@Override
	public boolean editer(int id) {
		boolean result = true;
		Membres membre = TrouverMembreParID(id);
		if (membre != null) {
			boolean isInEdit;
			do {
				GestionResultat captureResult = editAttribute(membre, AttributMembres.NOM);
				if (captureResult == GestionResultat.SUPPRIME) {
					break;
				}
				isInEdit = askContinueChangeName();
			} while (isInEdit);
		} else {
			result = false;
		}
		return result;
	}

	private boolean askContinueChangeName() {
		System.out.print("\nChange name again <y/n>: ");
		String querry = allscanner.nextLine();
		boolean isInEdit = querry.equalsIgnoreCase("y");
		return isInEdit;
	}
	
	

	private GestionResultat editAttribute(Membres membre, AttributMembres attr) {
		GestionResultat gestionresultats = GestionResultat.ECHEC;
		if (attr == AttributMembres.NOM) {
			String nouveauNom = getNouveauNom();
			if (nouveauNom.equals("")) {
				gestionresultats = GestionResultat.SUPPRIME;
			} else {
				membre.setName(nouveauNom);
			}
		}
		return gestionresultats;
	}

	private String getNouveauNom() {
		System.out.println("\nTaper sur ENTRER pour annuler la modification");
		System.out.print("Enter le  Nom : ");
		String nouveauNom = allscanner.nextLine();
		return nouveauNom;
	}

	@Override
	public boolean supprimer(int id) {
		boolean result;
		Membres membre = TrouverMembreParID(id);
		if (membre != null) {
			mListMember.remove(membre);
			result = true;
		} else {
			result = false;
		}
		return result;
	}

	private Membres TrouverMembreParID(int id) {
		for (Membres membre : mListMember) {
			if (membre.getId() == id) {
				return membre;
			}
		}
		return null;
	}

	@Override
	public boolean ajouter(Object obj) {
		boolean resultat;
		if (obj instanceof Membres) {
			mListMember.add((Membres) obj);
			resultat = true;
		} else {
			resultat = false;
		}
		return resultat;
	}

	public void afficherListeDesMembres() {
		for (Membres membre : mListMember) {
			System.out.println(membre.toString());
		}
	}

	public Membres getMembreParID(int id) {
		for (Membres membre : mListMember) {
			if (membre.getId() == id) {
				return membre;
			}
		}
		return null;
	}

	public int getTotalMembres() {
		return mListMember.size();
	}

	public List<Membres> getmListMember() {
		return mListMember;
	}
}
