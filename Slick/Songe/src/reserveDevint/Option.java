package reserveDevint;


import java.awt.Font;

import javax.swing.JTextArea;


/** Cette classe est un exemple d'interface pour les options
 * 
 */
public class Option extends DevintFrame{

    public Option(String title) {
    	super(title,"accueilOption.wav","accueilOption.wav","aide.wav");
    }

    public void init() {
    	String text = "Il est tr�s int�ressant de fournir un menu pour ";
    	text += "r�gler les pr�f�rences de l'utilisateur.\n";
    	text += "En particulier les couleurs, la police et la taille des caract�res ";
    	text+= "devraient pouvoir �tre r�gl�s par l'utilisateur (voir projet \"casse_brique 2006\")";
    	JTextArea lb1 = new JTextArea (text);
    	lb1.setLineWrap(true);
    	lb1.setEditable(false);
    	lb1.setFont(new Font("Times",1,80));
    	this.add(lb1);
    }

}
