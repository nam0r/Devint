package jeu;

import javax.swing.*;
import javax.swing.border.LineBorder;

import devintAPI.DevintFrame;

import java.awt.*;
import java.awt.event.*;

/** Cette classe est un exemple d'interface de jeu.
 *  Elle étend DevintFrame pour avoir un Frame et réagir aux évênements claviers
 * Implémente ActionListener pour réagir au clic souris sur le bouton.
 * On surchage la méthode "keyPressed" pour associer une action à la touche F3
 * 
 * @author helene
 *
 */

public class Jeu extends DevintFrame implements ActionListener{

	// le bouton pour la quetion
	// doit être accessible dans la méthode actionPerformed 
	private JButton question;

	// appel au constructeur de la classe mère
    public Jeu(String title) {
    	super(title,"accueilJeu.wav","accueil.wav","aide.wav");
     }

    // définition de la méthode abstraite "init()"
    // initialise le frame 
    protected void init() {
    	// BorderLayout, voir http://java.sun.com/docs/books/tutorial/uiswing/layout/border.html
    	setLayout(new BorderLayout());
 
    	// premier label
    	String text = "Salut, Popol. Il est important d'annoncer ce qu'il faut faire pour démarrer ";
    	text += "le jeu.\n L'interface de votre jeu est totalement libre mais vous devez suivre ";
    	text += "les conventions d'utilisation des touches.";
    	text += "En héritant de \"DevintFrame\", la touche ESC vous permez de sortir,"; 
    	text += "la touche F1 d'écouter la règle du jeu et la touche F2 d'écouter l'aide.";
    	text += "\nOn a utilisé un BorderLayout pour placer les éléments. Ce texte est au nord.";
     	JTextArea lb1 = new JTextArea (text); 
    	lb1.setLineWrap(true);
    	lb1.setEditable(false);
    	lb1.setFont(new Font("Georgia",1,30));
    	// on place le premier composant en haut
    	this.add(lb1,BorderLayout.NORTH);

    	// deuxième  label 
       	text = "C'est rigolo les jeux DeViNT";
       	text += "\nIci c'est un JLabel avec un bord gris.\n";
       	text += "Il est placé au centre.";
       	text += "\n\nExemple d'utilisation d'une touche : essayez de taper F3";
    	JTextArea lb2 = new JTextArea (text);
    	lb2.setLineWrap(true);
    	lb2.setEditable(false);
    	lb2.setFont(new Font("Georgia",1,30));
    	// on met un contour gris foncé
       	lb2.setBorder(new LineBorder(Color.GRAY,5));
       	// on met un fond noir
    	lb2.setBackground(Color.BLACK);
    	// le composant doit être opaque pour qu'on voit le fond
       	lb2.setOpaque(true);
    	// on écrit en blanc
       	lb2.setForeground(Color.WHITE);  	
       	// on place ce composant au centre
       	this.add(lb2,BorderLayout.CENTER);

    	// bouton pour poser une question
    	question = new JButton();
    	question.setText("écouter la question");
    	question.setBackground(new Color(50,50,255));
    	question.setBorder(new LineBorder(Color.BLACK,5));
     	question.setFont(new Font("Georgia",1,40));
     	// c'est l'objet Jeu lui-même qui réagit au clic souris
       	question.addActionListener(this);
    	// on met le bouton à droite
     	this.add(question,BorderLayout.EAST);
   }

    // lire la question si clic sur le bouton 
    public void actionPerformed(ActionEvent ae){
       	// toujours stopper la voix avant de parler
    	voix.stop();
    	// on récupère la source de l'évènement
     	Object source = ae.getSource();
     	// si c'est le bouton "question" on lit la question
     	// le contenu des questions est variable donc on les lit avec SI_VOX
    	if (source.equals(question)) {
    		String text = "les questions sont longues et ont un contenu variable.";
    		text += "Il ne faut pas générer un fichier wave mais lire directement les textes";
    		voix.playText(text);
    	}	
    	// on redonne le focus au JFrame principal 
    	// (après un clic, le focus est sur le bouton)
    	this.requestFocus();
    }
 
    // évènements clavier
    public void keyPressed(KeyEvent e) {
    	// appel à la méthode mère qui gère les évènements ESC, F1 
    	super.keyPressed(e);
    	// cas particulier pour ce jeu : la touche F3
    	if (e.getKeyCode()==KeyEvent.VK_F3){
    	   	voix.playText("Vous venez d'appuyer sur F3");
    	}
    }

}
