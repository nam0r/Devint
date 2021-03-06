/**
 * 
 * Cette classe ne doit pas etre modifiee.
 * 
 * 
 * Cette classe contient le menu general du jeu.
 * La particularite de l'implementation de mainFrame fait qu'on ne 
 * fait que switcher de panels en panels, tous derivant de classeAbstraite.
 * 
 * Exemple: Quand on clique sur Jouer, le panel courant(menuPanel donc) 
 * est remplace par le panel InterfaceJeu.
 * 
 */


package menuGraphique;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import jeu.InterfaceJeu;

import regle.InterfaceRegle;

import classesMeres.ClasseAbstraite;
import classesMeres.MainFrame;
import classesMeres.Settings;



public class MenuPanel extends ClasseAbstraite implements ActionListener{
	private static final long serialVersionUID = -2410732993212613901L;
	
	//titre du jeu
	protected String nomJeu;
	
	//les noms des options
	private String[] nomOptions= {"Jouer","Configuration Profil","R�gles","Quitter"};
    
    //les boutons associ�s aux options
    private JButton[] boutonOption;  // les boutons d'option
    
    //labels 
    private JPanel entete = null;
    private JLabel lb1 = null;
    private JPanel boutons = null;

    // le nombre d'options
    private  int nbOption;
    
    // attributs des textes et des boutons
    // � red�finir dans la classe concr�te  si vous le souhaitez
    // en gardant de forts contrastes et peu de couleurs diff�rentes
    private Font fonteBouton ;

    // l'option courante qui est s�lectionn�e
    private int optionCourante;

    // �l�ments de placement des composants
    private GridBagLayout placement; // le layout
    private GridBagConstraints regles; // les regles de placement   

	public MenuPanel(String title, MainFrame mainFrame,boolean music) {
		super(title, mainFrame);
		nomJeu = title;
    	optionCourante = -1;
		nbOption=nomOptions.length;
		creerAttributs();
		creerLayout();
		creerEntete();
		creerOption(nomOptions);
		activeListener();
		mainFrame.voix.stop();
		mainFrame.voix.playText("Menuu");
	}
	
	   /** cr�� les attributs (couleurs, fonte, ...)
     */
    protected void creerAttributs() {
    	// la couleur des textes 
    	couleurTexte = Settings.couleurTexte;
    	couleurFond = Settings.couleurFond;
    	// mise � jour des attributs des boutons
    	fonteBouton = new Font(Settings.police,1,Settings.taillePolice);
    }
    
    /** cr�� le layout pour placer les composants
     */
    private void creerLayout() {
	placement = new GridBagLayout();
	regles = new GridBagConstraints();
	setLayout(placement);
	// par d�faut on �tire les composants horizontalement et verticalement
	regles.fill = GridBagConstraints.BOTH;
	// par d�faut, tous les composants ont un poids de 1
	// on les r�partit donc �quitablement sur la grille
	regles.weightx = 1; 
	regles.weighty = 1;
	// espaces au bord des composants
	regles.insets = new Insets(10, 50, 10, 50);
	// pour placer en haut des zones
	regles.anchor= GridBagConstraints.NORTH;
    }
    
    /** cr�� l'ent�te avec le nom du jeu
     */
    public void creerEntete() {

	// panel d'entete de la fen�tre
	entete = new JPanel();
	FlowLayout enteteLayout = new FlowLayout();
	enteteLayout.setAlignment(FlowLayout.CENTER);
	entete.setLayout(enteteLayout);
	entete.setBorder(new LineBorder(Color.GRAY,8));
	
	entete.setOpaque(true);
	entete.setBackground(couleurTexte);
	// le label
	lb1 = new JLabel (nomJeu);
	lb1.setFont(new Font(Settings.police,1,96));
	lb1.setForeground(couleurFond);
	entete.add(lb1);

	// placement de l'entete en 1�re ligne, 1�re colonne
	regles.gridx=1; regles.gridy=1;
	placement.setConstraints(entete, regles);
	add(entete);
    }
    
    /** creer les boutons associ�s aux noms d'options
     */
    private void creerOption(String[] noms) {
	// cr�ation des boutons
	// panel des boutons
	boutons = new JPanel();
	boutons.setLayout(new GridLayout(nbOption, 1));
	// les boutons
	boutonOption = new JButton[nbOption];
	for (int i =0;i<nbOption;i++) {
	    creerBouton(i,noms[i]);
	    boutons.add(boutonOption[i]);
	}
	// poids relatif de 3 (i.e 3 fois plus grand que l'ent�te)
	regles.weighty=4;
	// placement des boutons en 2�me ligne, 1�re colonne
	regles.gridx = 1; regles.gridy = 2;
	placement.setConstraints(boutons, regles);
	add(boutons);
    }
    
    // pour cr�er un bouton associ� � un texte	
    private void creerBouton(int i,String texte) {
	boutonOption[i] = new JButton();
	boutonOption[i].setText(texte);
	setPropertiesButton(boutonOption[i]);
    }

    // mettre � jour les propri�t�s des boutons
    protected void setPropertiesButton(JButton b) {
	b.setFocusable(false);
	b.setBackground(couleurFond);
	b.setForeground(couleurTexte);
	b.setFont(fonteBouton);
	b.setBorder(new LineBorder(Color.BLACK,5));
    }

//  lance l'action associ�e au bouton n�i
    protected void lancerOption(int i) {
    	switch (i){  
    	case 0 :
    		mainFrame.switchPanel(new InterfaceJeu(this,mainFrame));
    		break;
    	case 1 :
    		InterfaceJeu game = new InterfaceJeu(this,mainFrame);
    		game.couperSon();
			mainFrame.switchPanel(new InterfaceConfigurationProfil(this,game,mainFrame));
    		break;
    	case 2 : 
    		mainFrame.switchPanel(new InterfaceRegle(this,mainFrame));
    		break;
    	case 3 : 
    		mainFrame.stopPerformed();
    	default: 
    		System.err.println("action non d�finie");
    	}
    } 
	
    // renvoie le fichier wave contenant le message d'accueil
    protected  String wavAccueil() {
	return "../ressources/accueil.wav";
    }

	public void enterPerformed() {
		// TODO Auto-generated method stub
		lancerOption(optionCourante);
	}

	public void downPerformed() {
		// TODO Auto-generated method stub
		if (optionCourante==-1) {
			optionCourante = 0;
			setFocusedButton(optionCourante);
		    } 
		    else {
			unFocusedButton(optionCourante);	    
			optionCourante = (optionCourante+1)%nbOption;
			setFocusedButton(optionCourante);
		    }
	}


	public void upPerformed() {
		// TODO Auto-generated method stub
		 if (optionCourante==-1) {
				optionCourante = 0;
				setFocusedButton(optionCourante);
			    } 
			    else {
				unFocusedButton(optionCourante);	     
				optionCourante = optionCourante-1;
				if (optionCourante==-1) optionCourante = nbOption-1;
				setFocusedButton(optionCourante);
			    }
	}
	
	 // mettre le focus sur une option
    private void setFocusedButton(int i){
	mainFrame.voix.playShortText(boutonOption[i].getText());	
	boutonOption[i].setBackground(couleurTexte);
	boutonOption[i].setForeground(couleurFond);
    }
	
    // enlever le focus d'une option
    private void unFocusedButton(int i){
	boutonOption[i].setBackground(couleurFond);
	boutonOption[i].setForeground(couleurTexte);
    }


	public void actionPerformed(ActionEvent ae) {
		// TODO Auto-generated method stub
		mainFrame.voix.stop();
		Object source = ae.getSource();
		for(int i=0; i<nbOption; i++){
		    if (source == boutonOption[i]){
			if (optionCourante!=-1) unFocusedButton(optionCourante);
			optionCourante=i;
			setFocusedButton(optionCourante);
			lancerOption(i);
		    }
		}
	}
	
	public void miseAJour()
	{
		super.miseAJour();
		fonteBouton = new Font(Settings.police,1,Settings.taillePolice);
		lb1.setForeground(couleurFond);
		entete.setBackground(couleurTexte);
		for (int i =0;i<nbOption;i++) {
			
			setPropertiesButton(boutonOption[i]);
		}
	}
	
	public void removeListener(){
		for(int i=0;i<boutonOption.length;i++){
			boutonOption[i].removeActionListener(this);
		}
	}
	
	public void activeListener(){
		for(int i=0;i<boutonOption.length;i++){
			boutonOption[i].addActionListener(this);
		}
	}	 
	 
	 public void helpPerformed(){
		 mainFrame.voix.playText("F 4 Touches");
	 }
	 
	 public void infoPerformed(){
		 mainFrame.voix.playText("Vous �tes dans le menu");
	 }
	 
	 public void touchesPerformed(){
		 mainFrame.voix.playText("Naviguer avec hoo, et bas, entrer pour valider");
	 }
}
