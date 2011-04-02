/** Une classe abstraite à utiliser pour le menu de lancement du jeu
 *  La classe de menu de votre jeu doit hériter de cette classe :
 *    - définir la méthode nomOptions qui renvoie un tableau de String qui corresponds aux 
 *    options possibles de votre menu
 *    - définir la méthode lancerOption(int i) qui associe des actions aux options de votre menu 
 *    - définir les méthodes wavAccueil et wavAide qui désignent le fichier wave à lire pour l'accueil
 *    et l'aide.
 *    
 *  Ne pas modifier cette classe
 *  @author helene
 */

package devintAPI;

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

    // les boutons associés aux options
    private JButton[] boutonOption;  

    // le nombre d'options
    private  int nbOption;
    

    // attributs des textes et des boutons
    // à redéfinir dans la classe concrète  si vous le souhaitez
    // en gardant de forts contrastes et peu de couleurs différentes
    protected Font fonteBouton ;
    protected Color couleurBouton;
    protected Color couleurBoutonSelectionne;
    protected Color couleurTexte;
    protected Color couleurTexteSelectionne;

    // les éléments qui parlent et que l'on veut pouvoir interrompre
    protected SIVOXDevint voix;

    // l'option courante qui est sélectionnée
    private int optionCourante;

    // éléments de placement des composants
    private GridBagLayout placement; // le layout
    private GridBagConstraints regles; // les regles de placement

 
    //-------------------------------------------------
    // les méthodes abstraites à définir par héritage

    /** renvoie le tableau contenant le nom des options
     * méthode abstraite à définir par héritage
     */
    protected abstract String[] nomOptions();


    // méthodes à définir pour rendre la classe concrète
    /** lance l'action associée au bouton n°i
     * méthode abstraite à définir par héritage
     * PRECOND : i varie entre 0 et le nombre d'options possibles moins 1
     */
    protected abstract void lancerOption(int i);

    /** renvoie le nom du fichier wav à lire pour l'accueil
     * PRECOND : fichier est le chemin pour accéder au fichier
     */
    protected abstract String wavAccueil();

    /** renvoie le nom du fichier wav à lire pour l'aide
     * PRECOND : fichier est le chemin pour accéder au fichier
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
    	nomOptions = nomOptions(); // méthode à rendre concrète par héritage
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
    // méthodes utilisées par le constructeur


   /** créé les attributs (couleurs, fonte, ...)
     */
    protected void creerAttributs() {
    	// la couleur des textes 
    	couleurTexte = Color.WHITE;
    	couleurTexteSelectionne = new Color(10,0,150);
    	// mise à jour des attributs des boutons
    	fonteBouton = new Font("Tahoma",1,56);
    	couleurBouton=couleurTexteSelectionne;
    	couleurBoutonSelectionne=couleurTexte;
    }

    /** créé le layout pour placer les composants
     */
    private void creerLayout() {
    	placement = new GridBagLayout();
    	regles = new GridBagConstraints();
    	setLayout(placement);
    	// par défaut on étire les composants horizontalement et verticalement
    	regles.fill = GridBagConstraints.BOTH;
    	// par défaut, tous les composants ont un poids de 1
    	// on les répartit donc équitablement sur la grille
    	regles.weightx = 1; 
    	regles.weighty = 1;
    	// espaces au bord des composants
    	regles.insets = new Insets(10, 50, 10, 50);
    	// pour placer en haut des zones
    	regles.anchor= GridBagConstraints.NORTH;
    }


    /** créé l'entête avec le nom du jeu
     */
    public void creerEntete() {

    	// panel d'entete de la fenêtre
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

    	// placement de l'entete en 1ère ligne, 1ère colonne
    	regles.gridx=1; regles.gridy=1;
    	placement.setConstraints(entete, regles);
    	add(entete);
    }


    /** creer les boutons associés aux noms d'options
     */
    private void creerOption(String[] noms) {
    	// création des boutons
    	// panel des boutons
    	JPanel boutons = new JPanel();
    	boutons.setLayout(new GridLayout(nbOption, 1));
    	// les boutons
    	boutonOption = new JButton[nbOption];
    	for (int i =0;i<nbOption;i++) {
    		creerBouton(i,noms[i]);
    		boutons.add(boutonOption[i]);
    	}
    	// poids relatif de 3 (i.e 3 fois plus grand que l'entête)
    	regles.weighty=4;
    	// placement des boutons en 2ème ligne, 1ère colonne
    	regles.gridx = 1; regles.gridy = 2;
    	placement.setConstraints(boutons, regles);
    	add(boutons);
    }

    // pour créer un bouton associé à un texte	
    private void creerBouton(int i,String texte) {
    	boutonOption[i] = new JButton();
    	boutonOption[i].setText(texte);
    	setPropertiesButton(boutonOption[i]);
    }

    // mettre à jour les propriétés des boutons
    protected void setPropertiesButton(JButton b) {
    	b.setFocusable(false);
    	b.setBackground(couleurBouton);
    	b.setForeground(couleurTexte);
    	b.setFont(fonteBouton);
    	b.setBorder(new LineBorder(Color.BLACK,5));
    	b.addActionListener(this);
    }

    //-------------------------------------------------------
    // méthodes pour réagir aux évènements clavier

    // évènements clavier
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
    	// enter = sélectionner l'option
    	if (e.getKeyCode()==KeyEvent.VK_ENTER){
    		lancerOption(optionCourante);  // méthode à rendre concrète par héritage
    	}
    	// se déplacer dans les options vers le bas
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
    	// se déplacer dans les options vers le haut
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
    