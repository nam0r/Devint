/** 
Cette classe abstraite est un Frame associ� � une instance de voix 
 * SI_VOX et qui impl�mente KeyListener.
 * Elle peut servir de classe m�re � toutes les fen�tres de vos jeux :
 * il suffit de d�finir la m�thode "init" pour initialiser les �l�ments du Frame
 */


package reserveDevint;

import t2s.SIVOXDevint; // pour parler

import javax.swing.*;
import java.awt.event.*;


/** Classe abstraite avec un Frame, une instance de SI_VOX pour parler et 
 * qui �coute les �v�nements clavier.
 * Par d�faut, un son est lu � l'activation de la fen�tre, 
 * on sort de la fen�tre par ESC et on obtient la r�gle du jeu par F1, l'aide par F2
 * 
 * @author helene
 *
 */
public abstract class DevintFrame extends JFrame implements  KeyListener{

	// la voix pour annoncer les actions
    protected SIVOXDevint voix; 
    
    // le fichier wav contenant le message d'accueil (lu � l'activation du jeu)
    protected String sonAccueil;

    // le fichier wav contenant le but du jeu (activ� par F1)
    protected String sonRegle;

    // le fichier wav contenant le message d'aide (activ� par F2)
    protected String sonAide;
   

    /**
     * @param title : titre de la fen�tre
     * @param sonAccueil : nom du fichier .wav qui contient le message d'accueil 
     * @param sonAide : nom du fichier .wav qui contient le message d'aide
     * PRECOND : il s'agit de .wav qui se trouvent dans le r�pertoire "ressources/sons"
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
    	// prend toute la taille de la fen�tre
    	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        // on ferme la fen�tre en cliquant sur la croix 
    	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
     	// �coute les �v�nements clavier
       	addKeyListener(this);
       	// m�thode init � impl�menter, elle construit ce qui est dans le frame
       	init();
       	// toujours annoncer ce que l'on doit faire en entrant dans une fen�tre
    	voix = new SIVOXDevint();
    	voix.playWav("../ressources/sons/" + sonAccueil);
    }


    /** m�thode abstraite � impl�menter 
     * pour d�finir ce qu'il y a dans le Frame
     */
    protected abstract void init();

    //////////////////////////////////////////////
    // Gestion des �v�nements clavier
    /////////////////////////////////////////////
    
    // m�thodes n�cessaires pour l'interface KeyListener
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e){}

    /** gestion des touches
     * ESC fait sortir de la fen�tre courante
     * F1 invoque l'aide
     * Cette m�thode peut �tre surcharg�e par h�ritage pour r�agir � d'autres touches
     * (voir un exemple dans la classe Jeu)
     */
    public void keyPressed(KeyEvent e) {
    	// toujours arr�ter la voix courante quand l'utilisateur fait une action
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
