package main;

import t2s.SIVOXDevint;

public interface Epreuve {

    //public void prononcer();

	public void prononcer(SIVOXDevint vox);

	public boolean bonneReponse(int reponse);
	
	public String toString();
	
}
