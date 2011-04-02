package main;
import gui.GuiFortDevint;

import t2s.SIVOXDevint;

public class Main {

    private static SIVOXDevint voix;

	public static void main(String args[]) {
		GuiFortDevint guiFD = new GuiFortDevint();
		voix=new SIVOXDevint(1);
		voix.playText("Bienvenue au fort devint."+
			"vous êtes dans le menu principal"+	  
			"Pour savoir les commandes clavier, tapez :  èfe 4.");
		guiFD.menuPrincipal();
	}
}
