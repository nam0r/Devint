/** Une classe abstraite � utiliser pour le menu de lancement du jeu
 *  La classe de menu de votre jeu doit h�riter de cette classe :
 *    - d�finir la m�thode nomOptions qui renvoie un tableau de String qui corresponds aux 
 *    options possibles de votre menu
 *    - d�finir la m�thode lancerOption(int i) qui associe des actions aux options de votre menu 
 *    - d�finir les m�thodes wavAccueil et wavAide qui d�signent le fichier wave � lire pour l'accueil
 *    et l'aide.
 *    
 *  Ne pas modifier cette classe
 *  @author helene
 */

package reserveDevint;

import t2s.SIVOXDevint; // pour lire les textes et les wav

import javax.swing.*;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;

public abstract class MenuAbstrait
    extends JFrame 
    implements KeyListener, ActionListener {

    //-------------------------------------------------------
    // les attributs

    // le nom du jeu
    protected final String nomJeu;

    // les noms des options
    private String[] nomOptions;  

    // les boutons associ�s aux options
    private JButton[] boutonOption;  

    // le nombre d'options
    private  int nbOption;
    

    // attributs des textes et des boutons
    // � red�finir dans la classe concr�te  si vous le souhaitez
    // en gardant de forts contrastes et peu de couleurs diff�rentes
    protected Font fonteBouton ;
    protected Color couleurBouton;
    protected Color couleurBoutonSelectionne;
    protected Color couleurTexte;
    protected Color couleurTexteSelectionne;

    // les �l�ments qui parlent et que l'on veut pouvoir interrompre
    protected SIVOXDevint voix;

    // l'option courante qui est s�lectionn�e
    private int optionCourante;

    // �l�ments de placement des composants
    private GridBagLayout placement; // le layout
    private GridBagConstraints regles; // les regles de placement

 
    //-------------------------------------------------
    // les m�thodes abstraites � d�finir par h�ritage

    /** renvoie le tableau contenant le nom des options
     * m�thode abstraite � d�finir par h�ritage
     */
    protected abstract String[] nomOptions();


    // m�thodes � d�finir pour rendre la classe concr�te
    /** lance l'action associ�e au bouton n�i
     * m�thode abstraite � d�finir par h�ritage
     * PRECOND : i varie entre 0 et le nombre d'options possibles moins 1
     */
    protected abstract void lancerOption(int i);

    /** renvoie le nom du fichier wav � lire pour l'accueil
     * PRECOND : fichier est le chemin pour acc�der au fichier
     */
    protected abstract String wavAccueil();

    /** renvoie le nom du fichier wav � lire pour l'aide
     * PRECOND : fichier est le chemin pour acc�der au fichier
     */
    protected abstract String wavAide();

    //-------------------------------------------------------
    /** constructeur,
     * @param title : le nom du jeu 
     * @param noms : liste des options du menu
     */
    public MenuAbstrait(String title) {
    	super(title);
    	nomJeu = title;
    	optionCourante = -1;
    	nomOptions = nomOptions(); // m�thode � rendre concr�te par h�ritage
    	nbOption=nomOptions.length;
    	creerAttributs();
    	creerLayout();
    	creerEntete();
    	creerOption(nomOptions);
    	setExtendedState(JFrame.MAXIMIZED_BOTH);
    	setVisible(true);
    	requestFocus();
    	setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    	addKeyListener(this);
    	voix = new SIVOXDevint();
    	voix.playWav(wavAccueil());
    }


    //-------------------------------------------------------
    // m�thodes utilis�es par le constructeur


   /** cr�� les attributs (couleurs, fonte, ...)
     */
    protected void creerAttributs() {
    	// la couleur des textes 
    	couleurTexte = Color.WHITE;
    	couleurTexteSelectionne = new Color(10,0,150);
    	// mise � jour des attributs des boutons
    	fonteBouton = new Font("Tahoma",1,56);
    	couleurBouton=couleurTexteSelectionne;
    	couleurBoutonSelectionne=couleurTexte;
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
    	JPanel entete=new JPanel();
    	FlowLayout enteteLayout = new FlowLayout();
    	enteteLayout.setAlignment(FlowLayout.CENTER);
    	entete.setLayout(enteteLayout);
    	entete.setBorder(new LineBorder(Color.GRAY,8));

    	// le label
    	JLabel lb1 = new JLabel (nomJeu);
    	lb1.setFont(new Font("Georgia",1,96));
    	lb1.setForeground(couleurTexteSelectionne);
    	lb1.setBackground(couleurBoutonSelectionne);
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
    	JPanel boutons = new JPanel();
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
    	b.setBackground(couleurBouton);
    	b.setForeground(couleurTexte);
    	b.setFont(fonteBouton);
    	b.setBorder(new LineBorder(Color.BLACK,5));
    	b.addActionListener(this);
    }

    //-------------------------------------------------------
    // m�thodes pour r�agir aux �v�nements clavier

    // �v�nements clavier
    public void keyReleased(KeyEvent e) {}
    public void keyTyped(KeyEvent e){}

    public void keyPressed(KeyEvent e) {
    	voix.stop();
    	// escape = sortir
    	if (e.getKeyCode()==KeyEvent.VK_ESCAPE){
    		System.exit(0);
    	}
    	// F1 = aide
    	if (e.getKeyCode()==KeyEvent.VK_F1){
    		voix.playWav(wavAide());
    	}
    	// enter = s�lectionner l'option
    	if (e.getKeyCode()==KeyEvent.VK_ENTER){
    		lancerOption(optionCourante);  // m�thode � rendre concr�te par h�ritage
    	}
    	// se d�placer dans les options vers le bas
    	if (e.getKeyCode() == KeyEvent.VK_DOWN){
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
    	// se d�placer dans les options vers le haut
    	if (e.getKeyCode() == KeyEvent.VK_UP){
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
    }

    // activer l'option si clic sur le bouton 
    public void actionPerformed(ActionEvent ae){
    	voix.stop();
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

    // mettre le focus sur une option
    private void setFocusedButton(int i){
    	voix.playShortText(boutonOption[i].getText());	
    	boutonOption[i].setBackground(couleurBoutonSelectionne);
    	boutonOption[i].setForeground(couleurTexteSelectionne);
    }

    // enlever le focus d'une option
    private void unFocusedButton(int i){
    	boutonOption[i].setBackground(couleurBouton);
    	boutonOption[i].setForeground(couleurTexte);
    }

}
    