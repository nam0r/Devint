package salle;

import java.awt.Color;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import main.Epreuve;
import main.EpreuveHybride;
import main.EpreuveTexte;
import main.EpreuveWav;
import fichiers.ReadFile;

public class SalleEpreuve extends Salle {

	private String fichierEpreuve;
	private Epreuve epreuve;
	private boolean EpreuveDejaAccomplie;
	private int typeEpreuve; // 0 : texte; 1 : wav; 2 : hybride
	
	public SalleEpreuve(String description, String fichierEpreuve, int typeEpreuve, Color fontColor, Color backColor) {
		super(description, fontColor, backColor);
		this.fichierEpreuve = fichierEpreuve;
		this.typeEpreuve = typeEpreuve;
		EpreuveDejaAccomplie = false;
	}

	public boolean isEpreuveDejaAccomplie() {
		return EpreuveDejaAccomplie;
	}

	public void setAccomplie() {
		EpreuveDejaAccomplie = true;
	}
	
	public void newEpreuve() {
		
		File file = new File(fichierEpreuve);
		
		ArrayList<String> listQuestRep = new ArrayList<String>();
		
		try {
			ReadFile rf = new ReadFile(file);
			
			while (!rf.eof())
			{
				listQuestRep.add(rf.read());
			}
		
			rf.close();
		} catch (IOException e) {
			System.out.println("PLANTAGE FICHIERS !!!!!");
			e.printStackTrace();
		}
		
		Random r = new Random();
		
		int indQuestChoisi = r.nextInt(listQuestRep.size());
		
		// pb avec un \ car ca protege le " ou sinon on en met 2 \\, ca plante
		String[] questRepDecoup = listQuestRep.get(indQuestChoisi).split("/");
		
		ArrayList<String> listRep = new ArrayList<String>();
		
		for (int i = 1; i < questRepDecoup.length; ++i)
		{	
			listRep.add(questRepDecoup[i]);
		}	
		if(typeEpreuve==0)
			epreuve = new EpreuveTexte(questRepDecoup[0],listRep);
		else if(typeEpreuve==1)
			epreuve = new EpreuveWav(questRepDecoup[0],listRep);
		else
			epreuve = new EpreuveHybride(questRepDecoup[0],listRep);
	}
	
	public Epreuve getEpreuve() {
		return epreuve;
	}

}
