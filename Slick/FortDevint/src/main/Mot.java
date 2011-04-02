package main;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

import fichiers.ReadFile;

public class Mot implements Serializable {

	private String mot;
	private ArrayList<String> indices;
	
	public Mot() {
		File file = new File("../ressources/data/mots.txt");
		
		ArrayList<String> tmp = new ArrayList<String>();
		
		try {
			ReadFile rf = new ReadFile(file);
			
			while (!rf.eof())
			{
				tmp.add(rf.read());
			}
		
			rf.close();
		} catch (IOException e) {
			System.out.println("PLANTAGE FICHIERS !!!!!");
			e.printStackTrace();
		}
		Random r = new Random();
		
		int randIndex = r.nextInt(tmp.size());
		
		String[] motEtIndicesDecoup = tmp.get(randIndex).split("/");
		mot = motEtIndicesDecoup[0];
		indices = new ArrayList<String>();
		for (int i = 1; i < motEtIndicesDecoup.length; ++i)
		{	
			indices.add(motEtIndicesDecoup[i]);
		}	
	}
			
	public String getMot() {
		return mot;
	}
	
	public ArrayList<String> getIndices() {
		return indices;
	}
	
	public void setMot(String mot) {
		this.mot = mot;
	}
	
	public void setIndices(ArrayList<String> indices) {
		this.indices = indices;
	}
	
	public String getIndice(int indice) {
		return indices.get(indice);
	}
		
}
