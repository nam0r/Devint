package main;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;
import java.awt.event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import son.LecteurFichierWav;
import son.LecteurTexteThread;
import t2s.son.*;
import t2s.SIVOXDevint;

public class EpreuveWav implements Epreuve, Serializable, KeyListener {

    private String fichierQuestion;
    private ArrayList<String> fichiersReponse;
    private int indBonne;
    private static SIVOXDevint vox=new SIVOXDevint();
	
    public EpreuveWav() {
	fichierQuestion = "";
	fichiersReponse = new ArrayList<String>();
	indBonne = 0;
    }
		
    public EpreuveWav(String fichierQuestion, ArrayList<String> rep){
	this.fichierQuestion = fichierQuestion;
	fichiersReponse = rep;
	indBonne = 0;
	melanger();
    }	

    public void prononcer(SIVOXDevint vox) {
	ArrayList travail=new ArrayList();
	travail.add("../ressources/wav/"+fichierQuestion+".wav");
 	for(int i=0;i<3;++i){
 	    travail.add("../ressources/wav/reponse"+(i+1)+".wav");
 	    travail.add("../ressources/wav/"+ fichiersReponse.get(i)+".wav");
 	}
 	for (int i=0;i<travail.size();i++) System.out.println("\n"+travail.get(i));
	AudioConcat ac=new AudioConcat(travail);
	//new AudioConcat(travail);
	vox.playWav("discours.wav");
    }

    public boolean bonneReponse(int reponse) {
	return (reponse-1==indBonne);
    }
	
    public void melanger() {

	Random r = new Random();

	// On prend 2 mauvaises réponses au hasard et on les place ds les reponses 1 et 2
	for (int i = 1; i <= 2; ++i) {
			
	    int choisi = r.nextInt(fichiersReponse.size() - i) + i;
			
	    String tmp = fichiersReponse.get(i);
	    fichiersReponse.set(i, fichiersReponse.get(choisi));
	    fichiersReponse.set(choisi, tmp);
	}
		
	// On mélange les 3 réponses, la bonne étant à l'indice 0
	int nouvelBonne = r.nextInt(3);
		
	String tmp = fichiersReponse.get(indBonne);
	fichiersReponse.set(indBonne, fichiersReponse.get(nouvelBonne));
	fichiersReponse.set(nouvelBonne, tmp);
	indBonne = nouvelBonne;

    }
	
    public String toString() {
	return "Ecoutez attentivement la question et les réponses.";
    }

    public void keyPressed(KeyEvent evt) {
	vox.stop();
    }	
    public void keyTyped(KeyEvent evt) {
    }

    public void keyReleased(KeyEvent evt) {
    }
}
