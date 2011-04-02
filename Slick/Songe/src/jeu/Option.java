package jeu;


import java.awt.Font;

import javax.swing.JTextArea;

import devintAPI.*;

/** Cette classe est un exemple d'interface pour les options
 * 
 */
public class Option extends DevintFrame{

    public Option(String title) {
    	super(title,"accueilOption.wav","accueilOption.wav","aide.wav");
    }

    public void init() {
    	String text = "Il est très intéressant de fournir un menu pour ";
    	text += "régler les préférences de l'utilisateur.\n";
    	text += "En particulier les couleurs, la police et la taille des caractères ";
    	text+= "devraient pouvoir être réglés par l'utilisateur (voir projet \"casse_brique 2006\")";
    	JTextArea lb1 = new JTextArea (text);
    	lb1.setLineWrap(true);
    	lb1.setEditable(false);
    	lb1.setFont(new Font("Times",1,80));
    	this.add(lb1);
    }

}
