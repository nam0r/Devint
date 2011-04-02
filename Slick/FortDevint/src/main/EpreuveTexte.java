package main;

import java.io.Serializable;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;
import t2s.son.*;
import son.LecteurTexteThread;
import t2s.SIVOXDevint;

public class EpreuveTexte implements KeyListener, Epreuve, Serializable {

    private String question;
    private ArrayList<String> reponse;
    private int indBonne;
    private SIVOXDevint vox;
	
    public EpreuveTexte() {
	question = "";
	reponse = new ArrayList<String>();
	indBonne = 0;
	vox=new SIVOXDevint(4);
    }
		
    public EpreuveTexte(String question, ArrayList<String> rep){
	this.question = question;
	reponse = rep;
	indBonne = 0;
	melanger();
    }
	
    /*public void prononcer() {
      vox.playText(question + "Réponse 1 :" + reponse.get(0) + ".Réponse 2 :" + reponse.get(1) + ".Réponse 3 :" + reponse.get(2) + ".");
      }*/

    public void prononcer(SIVOXDevint vox) {
	vox.playText(question + "Réponse 1 :" + reponse.get(0) + ".Réponse 2 :" + reponse.get(1) + ".Réponse 3 :" + reponse.get(2) + ".");
// 	LecteurTexteThread ltt = new LecteurTexteThread(question + "Réponse 1 :" + reponse.get(0) + ".Réponse 2 :" + reponse.get(1) + ".Réponse 3 :" + reponse.get(2) + ".");
// 	ltt.play();
	}

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
	return question + "\n\t1. " + reponse.get(0) + "\n\t2. " + reponse.get(1) + "\n\t3. " + reponse.get(2);
    }
    public void keyPressed(KeyEvent evt) {
	vox.stop();
    }
    public void keyTyped(KeyEvent evt) {
    }

    public void keyReleased(KeyEvent evt) {
    }
	
		
}
