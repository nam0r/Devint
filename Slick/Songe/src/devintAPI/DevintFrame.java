/** 
Cette classe abstraite est un Frame associé à une instance de voix 
 * SI_VOX et qui implémente KeyListener.
 * Elle peut servir de classe mère à toutes les fenêtres de vos jeux :
 * il suffit de définir la méthode "init" pour initialiser les éléments du Frame
 */


package devintAPI;

import t2s.SIVOXDevint; // pour parler

import javax.swing.*;
import java.awt.event.*;


/** Classe abstraite avec un Frame, une instance de SI_VOX pour parler et 
 * qui écoute les évènements clavier.
 * Par défaut, un son est lu à l'activation de la fenêtre, 
 * on sort de la fenêtre par ESC et on obtient la règle du jeu par F1, l'aide par F2
 * 
 * @author helene
 *
 */
public abstract class DevintFrame extends JFrame implements  KeyListener{

	// la voix pour annoncer les actions
    protected SIVOXDevint voix; 
    
    // le fichier wav contenant le message d'accueil (lu à l'activation du jeu)
    protected String sonAccueil;

    // le fichier wav contenant le but du jeu (activé par F1)
    protected String sonRegle;

    // le fichier wav contenant le message d'aide (activé par F2)
    protected String sonAide;
   

    /**
     * @param title : titre de la fenêtre
     * @param sonAccueil : nom du fichier .wav qui contient le message d'accueil 
     * @param sonAide : nom du fichier .wav qui contient le message d'aide
     * PRECOND : il s'agit de .wav qui se trouvent dans le répertoire "ressources/sons"
     */
    public DevintFrame(String title, String ac, String ar,String ai) {
    	super(title);
       	sonAccueil = ac; 
       	sonAide = ai;
            sonRegle = ar;
        // visible
    	this.setVisible(true);
    	// a le focus
    	this.requestFocus();
    	// prend toute la taille de la fenêtre
    	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // on ferme la fenêtre en cliquant sur la croix 
    	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
     	// écoute les évènements clavier
       	addKeyListener(this);
       	// méthode init à implémenter, elle construit ce qui est dans le frame
       	init();
       	// toujours annoncer ce que l'on doit faire en entrant dans une fenêtre
    	voix = new SIVOXDevint();
    	voix.playWav("../ressources/sons/" + sonAccueil);
    }


    /** méthode abstraite à implémenter 
     * pour définir ce qu'il y a dans le Frame
     */
    protected abstract void init();

    //////////////////////////////////////////////
    // Gestion des évènements clavier
    /////////////////////////////////////////////
    
    // méthodes nécessaires pour l'interface KeyListener
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e){}

    /** gestion des touches
     * ESC fait sortir de la fenêtre courante
     * F1 invoque l'aide
     * Cette méthode peut être surchargée par héritage pour réagir à d'autres touches
     * (voir un exemple dans la classe Jeu)
     */
    public void keyPressed(KeyEvent e) {
    	// toujours arrêter la voix courante quand l'utilisateur fait une action
    	voix.stop();
    	// escape = sortir
    	if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
    		dispose();
    	}
    	// F1 = regle du jeu
    	if (e.getKeyCode()==KeyEvent.VK_F1){
    		voix.playWav("../ressources/sons/" + sonRegle);
    	}

    	// F2 = aide
    	if (e.getKeyCode()==KeyEvent.VK_F2){
    		voix.playWav("../ressources/sons/" + sonAide);
    	}
    }
}
