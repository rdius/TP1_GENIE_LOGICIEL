package com.all.manipulation;

public class Enumeration {
	
	
	public enum GestionAction {
	    CREER, EDITER, SUPPRIME, ASSIGNER, AFFICHER, ACTION_UTILISATEUR
	}
	
	public enum GestionResultat {
	    SUCCES, ECHEC, SUPPRIME
	}
	
	public enum Status {
	    DISPONIBLE, EN_COURS, TERMINEE
	}
	
	public enum AttributTaches {
	    ID, NOM, DESCRIPTION, STATUS, MEMBRE
	}
	
	public enum AttributMembre {
	    ID, NOM
	}

}
