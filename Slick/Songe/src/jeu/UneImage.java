package jeu;

import javax.swing.*;
import javax.swing.border.LineBorder;

import devintAPI.DevintFrame;

import java.awt.*;
import java.awt.event.*;

/** Etend DevintFrame pour avoir un Frame et réagir aux évênements claviers
 * Contient un exemple d'affichage d'image proportionnel à la taille de l'écran
 * 
 * @author helene
 *
 */

public class UneImage extends DevintFrame {

    public UneImage(String title) {
    	super(title,"accueilImage.wav","accueilImage.wav","aide.wav");
     }

    // initialise le frame 
    protected void init() {
    	// FlowLayout : les composants ont leur taille fixée par setPreferredSize
    	// et sont ajoutés de gauche à droite, de haut en bas
    	setLayout(new FlowLayout());

    	// la largeur et la hauteur actuelle de la fenêtre
    	// si vous fixez la taille des éléments graphiques 
    	// faites le en utilisant des valeurs proportionnelles à la taille
    	// de la fenêtre pour que différentes résolutions d'écran soient possibles
    	int largeur = Toolkit.getDefaultToolkit().getScreenSize().width;
    	int hauteur = Toolkit.getDefaultToolkit().getScreenSize().height;

    	String  texte = "\nIci le layout est un \"FlowLayout\". Les composants sont ajoutés de gauche à droite et de haut en bas.";
    	texte += "\nLa taille des composants est celle de \"setPreferredSize\" ou bien la taille optimale pour obtenir un frame le plus petit possible.";
    	texte += "\n\nVoici les personnages du jeu Léa et Théo, 2007.";
    	JTextArea theoTexte = new JTextArea(texte);
    	theoTexte.setLineWrap(true);
    	theoTexte.setEditable(false);
    	add(theoTexte);

    	// une image, voir http://java.sun.com/docs/books/tutorial/uiswing/components/icon.html
    	ImageIcon icon = new ImageIcon("../ressources/images/theo.JPG");
    	texte =  "Théo est dans un label a un fond bleu qui occupe la moitié de la largeur et le tiers de la hauteur.";
    	// on met l'image dans un label
    	JLabel jl = new JLabel(texte,icon,JLabel.CENTER);
    	jl.setAutoscrolls(true);
    	jl.add(new Scrollbar());
    	// fond bleu
    	jl.setBackground(Color.BLUE);
    	//composant opaque pour voir le fond bleu
    	jl.setOpaque(true); 
    	// (largeur de la fenetre)/2 et (hauteur fenetre)/3
    	jl.setPreferredSize(new Dimension(largeur/2,hauteur/3));
    	add(jl);

    	// Léa
    	icon = new ImageIcon("../ressources/images/lea.JPG");
    	texte = "Ceci est Léa";
    	JLabel jl2 = new JLabel(texte,icon,JLabel.CENTER);
    	add(jl2);
   }

}
