package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import t2s.son.*;
import son.LecteurFichierWav;
import son.LecteurTexteThread;
import t2s.SIVOXDevint;

public class EpreuveHybride implements Epreuve, Serializable {

	private ArrayList<String> reponse;
	private int indBonne;
	private String fichierQuestion;
	
	public EpreuveHybride() {
		fichierQuestion = "";
		indBonne = 0;
		reponse = new ArrayList<String>();
	}

	public EpreuveHybride(String fichierQuestion, ArrayList<String> rep) {
		this.fichierQuestion = fichierQuestion;
		reponse = rep;
		indBonne = 0;
		melanger();
	}

	public void prononcer(SIVOXDevint vox) {
	ArrayList travail=new ArrayList();

	travail.add("../ressources/wav/"+fichierQuestion+".wav");
	//LecteurFichierWav lfw = new LecteurFichierWav("../ressources/wav/"+fichierQuestion+".wav");
	vox.muet("Réponse 1 :" + reponse.get(0) + ".Réponse 2 :" + reponse.get(1) 
			+ ".Réponse 3 :" + reponse.get(2) + ".","phrase");
//	LecteurTexteThread ltt = new LecteurTexteThread("Réponse 1 :" 
//			+ reponse.get(0) + ".Réponse 2 :" + reponse.get(1) 
//			+ ".Réponse 3 :" + reponse.get(2) + ".");
// 		lfw.play();
//		ltt.muet();
	//System.out.println("j'y passe");
		//travail.add("../ressources/donneesMbrola/pho_wav/phrase.wav");
		travail.add("./phrase.wav");
	AudioConcat ac=new AudioConcat(travail);
	//new AudioConcat(travail);
	vox.playWav("discours.wav");
	}
//     public void prononcer(SIVOXDevint vox) {
// 	ArrayList travail=new ArrayList();
// 	travail.add("../ressources/wav/"+fichierQuestion+".wav");
//  	for(int i=0;i<3;++i){
//  	    travail.add("../ressources/wav/reponse"+(i+1)+".wav");
//  	    travail.add("../ressources/wav/"+ fichiersReponse.get(i)+".wav");
//  	}
//  	for (int i=0;i<travail.size();i++) System.out.println("\n"+travail.get(i));
// 	AudioConcat ac=new AudioConcat(travail);
// 	//new AudioConcat(travail);
// 	vox.playWav("discours.wav");
//     }

// 	public void prononcer(SIVOXDevint vox) {
// 		LecteurFichierWav lfw = new LecteurFichierWav("../ressources/wav/"+fichierQuestion+".wav");
// 		LecteurTexteThread ltt = new LecteurTexteThread("Réponse 1 :" + reponse.get(0) + ".Réponse 2 :" + reponse.get(1) + ".Réponse 3 :" + reponse.get(2) + ".");
// 		lfw.play();
// 		ltt.play();
// 	}
	
	public boolean bonneReponse(int reponse) {
		return (reponse-1==indBonne);
	}
	
	public void melanger() {
		Random r = new Random();

		// On prend 2 mauvaises réponses au hasard et on les place ds les reponses 1 et 2
		for (int i = 1; i <= 2; ++i) {
			
			int choisi = r.nextInt(reponse.size() - i) + i;
			
			String tmp = reponse.get(i);
			reponse.set(i, reponse.get(choisi));
			reponse.set(choisi, tmp);
		}
		
		// On mélange les 3 réponses, la bonne étant à l'indice 0
		int nouvelBonne = r.nextInt(3);
		
		String tmp = reponse.get(indBonne);
		reponse.set(indBonne, reponse.get(nouvelBonne));
		reponse.set(nouvelBonne, tmp);
		indBonne = nouvelBonne;
	}
	
	public String toString() {
		return "Ecoutez attentivement la question.\n\t1. " + reponse.get(0) + "\n\t2. " + reponse.get(1) + "\n\t3. " + reponse.get(2);
	}
}
