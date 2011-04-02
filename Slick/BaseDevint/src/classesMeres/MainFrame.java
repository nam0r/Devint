/**
 * 
 * Cette classe doit etre modifiee. Vous devez affecter une touche a chaque action.
 * 
 * Exemple: spacePerformed -> touche espace
 * 
 * C'est egalement dans celle-ci que l'on trouve les 3 instances de SIVOXDevint.
 * 
 */



package classesMeres;


import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import menuGraphique.ConfigurationProfil;
import menuGraphique.ConfigurationSIVOX;
import menuGraphique.MenuPanel;



import t2s.SIVOXDevint;

public class MainFrame extends JFrame implements KeyListener {

    private static final long serialVersionUID = 4723885605682118937L;
    private PaintThread paintThread;
    private JPanel currentPanel = null;
    private boolean repaint = true;
    private CommandeInterface currentControlComponent;
    public SIVOXDevint voix;
    public SIVOXDevint voix1;
    public SIVOXDevint voix2;
    private ConfigurationProfil configProfil;

	
    public MainFrame(boolean repaint){
		super("Exemple de Jeu");
		this.repaint=repaint;
		//Default behaviour when closing
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent evt)
			{	// Quand on va cliquer sur la croix (en haut de la fenetre)
				currentControlComponent.stopPerformed();
			}
		});
		//Set Jdialogs LookAndFeel
		JDialog.setDefaultLookAndFeelDecorated(true);
		//set frame as keylistener
		this.addKeyListener(this);
		
		//Set the size
		setPreferredSize(Toolkit.getDefaultToolkit().getScreenSize());
		
		configProfil = new ConfigurationProfil();
		chargerDernierProfil();
		//System.out.println(Settings.typeVoix);
		
		// Create the voice
		voix = new SIVOXDevint(Settings.typeVoix);
		//Create the first voice
		voix1 = new SIVOXDevint(Settings.typeVoix1);
		//Create the second voice
		voix2 = new SIVOXDevint(Settings.typeVoix2);
		
		//Load the MenuPanel
		switchPanel(new MenuPanel("Menu",this,true));
		
		//Start repainting
		paintThread = new PaintThread();
		Thread t= new Thread(paintThread);
		//thread.setPriority(Thread.MAX_PRIORITY);
		t.start();
	}
    
    /**Set the actual DDRPeripheralsInterface that controls the game
	 * @param d - the actual DDRPeripheralsInterface
	 */
	public void setControlComponent(CommandeInterface d){
		//System.out.println("control "+d);
		this.currentControlComponent=d;
	}
	
	/**
	 * Set the content pane of this frame
	 * @param panel
	 */
	public void switchPanel(ClasseAbstraite panel){
		currentPanel=panel;
		setContentPane(panel);
		setControlComponent(panel);
		currentPanel.setVisible(true);
		validateTree();
		requestFocus();
		System.gc();
	}
	
	
    /**
     * Affectation de toutes les touches utilisées
     * modifier CommandeInterface pour permettre une nouvelle action
     */
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if(currentControlComponent != null){
			voix.stop();
			switch(e.getKeyCode()){
			//up
			case KeyEvent.VK_UP:
				currentControlComponent.upPerformed();
				break;
			//down
			case KeyEvent.VK_DOWN:
				currentControlComponent.downPerformed();
				break;
			//stop
			case KeyEvent.VK_ESCAPE:
				currentControlComponent.stopPerformed();
				break;
			//aide
			case KeyEvent.VK_F1:
				currentControlComponent.helpPerformed();
				break;
			//règles
			case KeyEvent.VK_F2:
				currentControlComponent.reglesPerformed();
				break;
			//scores
			case KeyEvent.VK_F3:
				currentControlComponent.scorePerformed();
				break;
			//rappel de touche
			case KeyEvent.VK_F4:
				currentControlComponent.touchesPerformed();
				break;
			//info sur l'etat de la partie
			case KeyEvent.VK_F5:
				currentControlComponent.infoPerformed();
				break;
			case KeyEvent.VK_ENTER:
				currentControlComponent.enterPerformed();
				break;
			//espace
			case KeyEvent.VK_SPACE:
				currentControlComponent.spacePerformed();
				break;
			//backSpace
			case KeyEvent.VK_BACK_SPACE:
				currentControlComponent.backPerformed();
				break;
			//ctrl
			case KeyEvent.VK_CONTROL:
				currentControlComponent.ctrlPerformed();
				break;
			//touche 1
			case KeyEvent.VK_1:
				currentControlComponent.touche1Performed();
				break;
			//touche 2
			case KeyEvent.VK_2:
				currentControlComponent.touche2Performed();
				break;
			//touche 3
			case KeyEvent.VK_3:
				currentControlComponent.touche3Performed();
				break;
			//touche 4
			case KeyEvent.VK_4:
				currentControlComponent.touche4Performed();
				break;
			case KeyEvent.VK_AMPERSAND:
				System.out.println("gfcjvgh");
			}
		}
	}

	public void keyReleased(KeyEvent arg0) {		
	}

	public void keyTyped(KeyEvent arg0) {		
	}

	private class PaintThread implements Runnable{
		public void run(){
			while(true){
				try{
					if(repaint && currentPanel != null){
						currentPanel.repaint();
					}
					Thread.sleep(1000);
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/*
	 * Pour toute action, le defficient visuel doit etre guide.
	 * Si il veut quitter, il ne doit pas juste appuyer sur echap 
	 * et ne pas avoir de retour de confirmation (meme une simple voix qui dit "au-revoir"). 
	 * @see graphique.CommandeInterface#stopPerformed()
	 */
	public void stopPerformed() {
		voix.playText("Voulez-vous quitter ? Entrée pour confirmer, Echap sinon.");
		int confirm = JOptionPane.showConfirmDialog(this, 
				"Etes-vous sûr de vouloir quitter?",
				"Quitter",
				JOptionPane.OK_CANCEL_OPTION);
		if(confirm == JOptionPane.OK_OPTION){
			voix.stop();
		    voix.playText("A bientot!");
		    this.dispose();
		    System.exit(0);
		}
	}
	
	/*
	 * Cette methode permet de charger dans Settings les 
	 * valeurs des attributs contenus dans le dernier profil enregistre.
	 */
	 public void chargerDernierProfil(){
	    	try {
				configProfil.chargerDernierProfil();
				ConfigurationSIVOX.modifierSIVOX(Settings.rapidite);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
	 }
}
