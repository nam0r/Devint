package reserveDevint;

import javax.swing.*;
import javax.swing.border.LineBorder;


import java.awt.*;
import java.awt.event.*;

/** Etend DevintFrame pour avoir un Frame et r�agir aux �v�nements claviers
 * Contient un exemple d'affichage d'image proportionnel � la taille de l'�cran
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
    	// FlowLayout : les composants ont leur taille fix�e par setPreferredSize
    	// et sont ajout�s de gauche � droite, de haut en bas
    	setLayout(new FlowLayout());

    	// la largeur et la hauteur actuelle de la fen�tre
    	// si vous fixez la taille des �l�ments graphiques 
    	// faites le en utilisant des valeurs proportionnelles � la taille
    	// de la fen�tre pour que diff�rentes r�solutions d'�cran soient possibles
    	int largeur = Toolkit.getDefaultToolkit().getScreenSize().width;
    	int hauteur = Toolkit.getDefaultToolkit().getScreenSize().height;

    	String  texte = "\nIci le layout est un \"FlowLayout\". Les composants sont ajout�s de gauche � droite et de haut en bas.";
    	texte += "\nLa taille des composants est celle de \"setPreferredSize\" ou bien la taille optimale pour obtenir un frame le plus petit possible.";
    	texte += "\n\nVoici les personnages du jeu L�a et Th�o, 2007.";
    	JTextArea theoTexte = new JTextArea(texte);
    	theoTexte.setLineWrap(true);
    	theoTexte.setEditable(false);
    	add(theoTexte);

    	// une image, voir http://java.sun.com/docs/books/tutorial/uiswing/components/icon.html
    	ImageIcon icon = new ImageIcon("../ressources/images/theo.JPG");
    	texte =  "Th�o est dans un label a un fond bleu qui occupe la moiti� de la largeur et le tiers de la hauteur.";
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

    	// L�a
    	icon = new ImageIcon("../ressources/images/lea.JPG");
    	texte = "Ceci est L�a";
    	JLabel jl2 = new JLabel(texte,icon,JLabel.CENTER);
    	add(jl2);
   }

}
