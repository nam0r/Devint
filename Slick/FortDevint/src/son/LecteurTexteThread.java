package son;
import t2s.son.LecteurTexte;

public class LecteurTexteThread extends Thread{

	private LecteurTexte lt;
	
	public LecteurTexteThread(String texte) {
		lt = new LecteurTexte(texte);
	}
	
	public void play() {
		lt.playAll();
	}

	public void muet() {
		lt.muet();
	}
	
	public void setTexte(String texte) {
		lt.setTexte(texte);
	}
	
	public void start() {
		play();
	}
	
}
