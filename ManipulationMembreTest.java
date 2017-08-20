package com.all.manipulation;
import static org.junit.Assert.*;

import java.util.List;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.all.entites.Membres;
import com.all.entresortie.FichierEntreSortie;

public class ManipulationMembreTest {
	protected final List<Membres> mListMember = FichierEntreSortie.getListMemberFromFile();
	

	
	@Before
	public void tearDown() throws Exception {
	}

	@After
	public void setUp() throws Exception {
	}
	@Test
	public void testCreer() {
		
		ManipulationMembre junit = new ManipulationMembre();
		Membres mC= junit.CreerNouveauMembre();
		mListMember.add(mC);
		System.out.println(mListMember);
		
	}

	@Test
	public void testEditer() {
		ManipulationMembre junit = new ManipulationMembre();
		Membres mE= junit.TrouverMembreParID(2);	
		
	}

	@Test
	public void testSupprimer() {
		ManipulationMembre junit = new ManipulationMembre();
		Membres mS= junit.TrouverMembreParID(5);
		mListMember.remove(mS);
		System.out.println(mListMember);
	}
	@Test
	public void testAfficherListeDesMembres() {
		
		ManipulationMembre junit = new ManipulationMembre();
		System.out.println(".....................");
		String mS= junit.toString();
		System.out.println(mS);
	}
	
	
}
