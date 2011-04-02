package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.WindowConstants;

import main.Partie;
import salle.SalleEntree;
import salle.SalleEpreuve;

import t2s.SIVOXDevint;

public class GuiFortDevint extends JFrame implements KeyListener {
	
	private static final long serialVersionUID = 1L;
	private JTextPane title;
	private JTextPane body;
	private JTextPane help;
	private Partie p;
	private Color backgroundColor;
	private Color fontColor;
        private SIVOXDevint voix;
	
	public GuiFortDevint() {
	    super("Fort DeViNT");
	    voix = new SIVOXDevint(1);
	    try {
		setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
		{
		    title = new JTextPane();
		    getContentPane().add(title, BorderLayout.NORTH);
		    title.setFont(new Font("Arial",0,72));
		    title.setFocusable(false);
		}
		{
		    body = new JTextPane();
		    getContentPane().add(body, BorderLayout.CENTER);
		    body.setFont(new Font("Arial",0,48));
		    body.setFocusable(false);
		}
		{
		    help = new JTextPane();
		    getContentPane().add(help, BorderLayout.SOUTH);
		    help.setFont(new Font("Arial",0,32));
		    help.setFocusable(false);
		}
		pack();
		this.setSize(800, 600);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	    this.setSize(800,600);
	    this.setLocationRelativeTo(null);
	    this.setVisible(true);
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    addKeyListener(this);
	    p = new Partie();
	}

	public void menuPrincipal() {
	    backgroundColor = new Color(255,255,0);
	    fontColor = new Color(0,0,255);
	    // toutes les fenêtres ont des zones title, body et help
	    title.setText("Menu Principal");
	    title.setBackground(new Color(255,0,0));
	    title.setBackground(backgroundColor);
	    title.setForeground(fontColor);
	    body.setText("\n\tF1. Nouvelle Partie\n" +
			 "\tF2. Hé, Hé, Reprendre la dernière partie\n" +
			 "\tF3. Connaître le but du jeu\n" +
			 "\tF4. Savoir les commandes clavier\n");
	    body.setBackground(backgroundColor);
	    body.setForeground(fontColor);
	    help.setText("Utilisez les touches F1 à F4");
	    help.setBackground(backgroundColor);
	    help.setForeground(fontColor);
	    pack();
	}

    public void menudifficulte() {
	backgroundColor = new Color(0,255,0);
	fontColor = new Color(255,0,255);
	title.setText("Difficulté : ");
	title.setBackground(new Color(255,0,0));
	title.setBackground(backgroundColor);
	title.setForeground(fontColor);
	body.setText("\n\tF1. facile\n" +
		     "\tF2. moyen\n" +
		     "\tF3. difficile\n" +
		     "\tF4. commandes clavier\n" +
		     "\tF5. retour au menu d'accueil\n");
	body.setBackground(backgroundColor);
	body.setForeground(fontColor);
	help.setText("Utilisez les touches F1 à F5");
	help.setBackground(backgroundColor);
	help.setForeground(fontColor);
	pack();
    }
	
    public void entrerPrenom() {
	backgroundColor = new Color(255,0,0);
	fontColor = new Color(0,255,255);
	title.setText("Entrez votre prénom");
	title.setBackground(new Color(255,0,0));
	title.setBackground(backgroundColor);
	title.setForeground(fontColor);
	if(p.getPrenom().equals("")) body.setText("\n\n\n\n\n\n");
	else body.setText("\n\n"+p.getPrenom()+"\n\n\n");
	body.setBackground(backgroundColor);
	body.setForeground(fontColor);
	help.setText("Validez votre prénom avec <Entrée>");
	help.setBackground(backgroundColor);
	help.setForeground(fontColor);
	pack();
    }
	
    public void salle(boolean parle) {
	if (parle) {
	    voix.playText("Vous entrez dans " 
			  + p.getSalleCourante().getDescription()
			  + ", "+p.getPrenom() +
			  ". tapez èfe 4 pour savoir les commandes clavier.");
	}
	backgroundColor = p.getSalleCourante().getBackColor();
	fontColor = p.getSalleCourante().getFontColor();
	title.setText(p.getSalleCourante().getDescription());
	title.setBackground(backgroundColor);
	title.setForeground(fontColor);
	body.setText("\n\n\n");
	body.setBackground(backgroundColor);
	body.setForeground(fontColor);
	if(p.getSalleCourante() instanceof SalleEntree) 
	    help.setText("Entrée: pour donner le mot deviné"
			 +"\nflèche droite ou gauche: changer de salle"
			 +"\nF1 : rappel des indices\tF4 : commandes clavier");
	else
	    help.setText("Entrée: écouter l'énigme"
			 +"\nflèches droite et gauche: changer de salle"
			 +"\nF1 : rappel des indices\tF4 : commandes clavier");
	help.setBackground(backgroundColor);
	help.setForeground(fontColor);
	pack();
    }
	
    public void epreuve() {
	backgroundColor = p.getSalleCourante().getBackColor();
	fontColor = p.getSalleCourante().getFontColor();
	title.setText(p.getSalleCourante().getDescription());
	title.setBackground(backgroundColor);
	title.setForeground(fontColor);
	body.setText(((SalleEpreuve)p.getSalleCourante()).getEpreuve().toString());
	body.setBackground(backgroundColor);
	body.setForeground(fontColor);
	help.setText("répondre avec F1, F2, F3 ou le pavé numérique.");
	help.setBackground(backgroundColor);
	help.setForeground(fontColor);
	pack();
    }
	
    /* (non-Javadoc)
     * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
     */
    //zone des interactions clavier    
    public void keyPressed(KeyEvent evt) {
	voix.stop();
	voix.setVoix(1);
	//sortir si escape (rajouté une ligne, on quitte si Escape
	if(evt.getKeyCode()==KeyEvent.VK_ESCAPE) dispose();
	//on est dans le menu principal, menu d'accueil
	if (p.isInMenuPrincipal()) {
	    switch (evt.getKeyCode()) {
	    case KeyEvent.VK_F1:
		p.setMenuDifficulte();
		menudifficulte();
		voix.playText("Vous devez choisir votre difficulté. "+
		       "Pour savoir les commandes clavier, tapez èfe 4."); 
		break;
	    case KeyEvent.VK_F2:
		try {p.charger();} catch(Exception e) {e.printStackTrace();}
		salle(true);
		break;
	    case KeyEvent.VK_F3:
		voix.playText("Le but du jeu est de deviner un mot." +
			      "On gagne des indices en répondant aux questions"
			      +" posées dans les 7 salles du fort devint.");
		break;
	    case KeyEvent.VK_F4:
		voix.setVoix(1);
		voix.playText("Les commandes clavier : "+
			      "èfe 1: commencer une partie." +
			      "èfe 2 : reprendre la dernière partie." +
			      "èfe 3 : connaitre le but du jeu." +
			      "èfe 4 : réécouter ces instructions."
			      +"Echap, pour terminer");
		break;
	    }
	}
	// Choix de la difficulté de la partie	
	else if (p.isInMenuDifficulte()) {
	    switch (evt.getKeyCode()) {
	    case KeyEvent.VK_F1:
		voix.playText("Tapez votre prénom et validez avec entrée.");
		p.setdifficulte(0);
		p.init();
		p.setEntrerPrenom();
		entrerPrenom();
		break;
	    case KeyEvent.VK_F2:
		voix.playText("Tapez votre prénom et validez avec entrée.");
		p.setdifficulte(1);
		p.init();
		p.setEntrerPrenom();
		entrerPrenom();
		break;
	    case KeyEvent.VK_F3:
		voix.playText("Tapez votre prénom et validez avec entrée.");
		p.setdifficulte(2);
		p.init();
		p.setEntrerPrenom();
		entrerPrenom();
		break;
	    case KeyEvent.VK_F4:
		voix.setVoix(1);
		voix.playText("Choisissez la difficulté :"+ 
			      "èfe 1 : partie facile." +
			      "èfe 2 : moins facile." +
			      "èfe 3 : difficile." +
			      "èfe 4 : rappel des commandes." +
			      "èfe 5 : retour au menu principal."
			      +"Echap, pour terminer");
		break;
	    case KeyEvent.VK_F5:
		p.setMenuPrincipal();
		menuPrincipal();
		voix.setVoix(3);
		voix.playText("Prêt pour une nouvelle partie ?"+
			      "rappelez vous ! à tout instant, "+	  
			      "la touche èfe 4 donne les commandes clavier.");
		break;
	    }
	}
	// fenêtre d'acquisition du prénom du joueur
	else if (p.isInEntrerPrenom()) {
	    switch (evt.getKeyCode()) {
	    case KeyEvent.VK_ENTER:
		p.setSalleEntree();
		salle(true);
		try {
		    p.sauvegarder();
		    System.out.println("ok: "+p.getSalleCourante().getDescription());
		}
		catch(IOException e) {
		    e.printStackTrace();
		}
		break;
	    case KeyEvent.VK_F4:
		voix.setVoix(4);
		voix.playText("BackSpace efface le dernier caractère entré,"+
			      "Entrée valide le prénom.");
	    case KeyEvent.VK_BACK_SPACE:
		voix.playText("Effacer");
		p.enleverLettrePrenom();
		entrerPrenom();
		break;
	    default:
		String c = new Character(evt.getKeyChar()).toString();
		if(c!=null) {
		    if(c.equals("t")) {
			voix.playShortText("té");
		    }
		    else if(c.equals("y")) {
			voix.playShortText("i grec");
		    }
		    else if(c.equals("s")) {
			voix.playShortText("èce");
		    }
		    else voix.playShortText(c);
		    p.ajouterLettrePrenom(c);
		    entrerPrenom();
		}
		break;
	    }	
	}
	// dans la salle d'entrée du Fort DeVint
	else if (p.isInSalleEntree()) {
	    switch (evt.getKeyCode()) {
	    case KeyEvent.VK_ENTER:
		voix.playText("Taper le mot mystérieux.");
		p.initPropositionMot();
		p.setPropositionMot();
		break;
	    case KeyEvent.VK_LEFT:
		p.allerAGauche();
		if(p.getSalleCourante() instanceof SalleEntree)
		    p.setSalleEntree();
		else
		    p.setSalle();
		salle(true);
		try {
		    p.sauvegarder();
		}
		catch(IOException e) {
		    e.printStackTrace();
		}
		break;
	    case KeyEvent.VK_RIGHT:
		p.allerADroite();
		if(p.getSalleCourante() instanceof SalleEntree)
		    p.setSalleEntree();
		else
		    p.setSalle();
		salle(true);
		try {
		    p.sauvegarder();
		}
		catch(IOException e) {
		    e.printStackTrace();
		}
		break;
	    case KeyEvent.VK_F4:
		voix.setVoix(4);
		voix.playText("flèches droite et gauche : pour changer de salle. "+
			      "Entrée : pour proposer un mot solution." +
			      "èfe 1: pour rappeler vos indices."
			      +"Echap, pour terminer");
		break;
	    case KeyEvent.VK_F1:
		String tmp;
		if(p.getNbIndicesTrouves()>0) {
		    tmp = "Voici les indices trouvés.";
		    for(int i=0;i<p.getNbIndicesTrouves();++i) {
			tmp+="Indice " + (i+1) + " : " + p.getMot().getIndice(i) + ".";
		    }
		}
		else
		    tmp = "Pas encore d'indice";
		    voix.playText(tmp);
		break;
	    }	
	}
	// en salle thématique
	else if (p.isInSalle()) {
	    switch (evt.getKeyCode()) {
	    case KeyEvent.VK_ENTER:
		if (((SalleEpreuve) p.getSalleCourante()).isEpreuveDejaAccomplie()) {
		    voix.playText("Vous avez déjà résolu cette question.");
		}
		else {
		    p.setEpreuve();
		    ((SalleEpreuve) p.getSalleCourante()).newEpreuve();
		    epreuve();
		    ((SalleEpreuve) p.getSalleCourante()).getEpreuve().prononcer(voix);
		}
		break;
	    case KeyEvent.VK_LEFT:
		p.allerAGauche();
		if(p.getSalleCourante() instanceof SalleEntree)
		    p.setSalleEntree();
		else
		    p.setSalle();
		salle(true);
		try {
		    p.sauvegarder();
		}
		catch(IOException e) {
		    e.printStackTrace();
		}
		break;
	    case KeyEvent.VK_RIGHT:
		p.allerADroite();
		if(p.getSalleCourante() instanceof SalleEntree)
		    p.setSalleEntree();
		else
		    p.setSalle();
		salle(true);
		try {
		    p.sauvegarder();
		}
		catch(IOException e) {
		    e.printStackTrace();
		}
		break;	
	    case KeyEvent.VK_F4:
		voix.setVoix(4);
		voix.playText("flèches droite ou gauche :  changer de salle."+
			      " entrée : passer l'épreuve de cette pièce." +
			      "èfe 1 : écouter les indices."
			      +"Echap, pour terminer");
		break;
	    case KeyEvent.VK_F1:
		String tmp;
		if(p.getNbIndicesTrouves()>0) {
		    tmp = "Voici les indices trouvés.";
		    for(int i=0;i<p.getNbIndicesTrouves();++i) {
			tmp+="Indice " + (i+1) + " : " + p.getMot().getIndice(i) + ".";
		    }
		}
		else
		    tmp = "Vous n'avez pas encore trouvé d'indice";
		    voix.playText(tmp);
		break;	
	    }	
	}
	//durant l'épreuve
	else if(p.isInEpreuve()) {
	    boolean oui= false;
	    switch (evt.getKeyCode()) {
	    case KeyEvent.VK_F1:
		// Verif de la bonne rép
		if(((SalleEpreuve) p.getSalleCourante()).getEpreuve().bonneReponse(1)){oui=true;}
		break;
	    case KeyEvent.VK_F2:
		// Verif de la bonne rép
		if(((SalleEpreuve) p.getSalleCourante()).getEpreuve().bonneReponse(2)){oui=true;}
		break;
	    case KeyEvent.VK_F3:
		// Verif de la bonne rép
		if(((SalleEpreuve) p.getSalleCourante()).getEpreuve().bonneReponse(3)){oui=true;}
		break;
	    }
	    System.out.println("Oui ? "+oui);
	    if (oui){
		voix.playText("Bravo, c'est la réponse, voici un indice, " 
			      + p.getMot().getIndice(p.getNbIndicesTrouves()));
		((SalleEpreuve) p.getSalleCourante()).setAccomplie();
		p.incrNbIndicesTrouves();
		p.setSalle();
		salle(false);
		try {
		    p.sauvegarder();
		}
		catch(IOException e) {
		    e.printStackTrace();
		}
	    } else {
		p.setSalle();
		salle(false);
		voix.playText("Ce n'est pas la bonne réponse."+
			      " Pour recommencer, presser entrée.");
		try {
		    p.sauvegarder();
		}
		catch(IOException e) {
		    e.printStackTrace();
		}
	    }
	}
	else if (p.isInPropositionMot()) {
	    switch (evt.getKeyCode()) {
	    case KeyEvent.VK_ENTER:
		voix=new SIVOXDevint(2);
		if(p.isBonMot()){
		    voix.playText("Félicitations, " + p.getPrenom() 
				  + ", tu as gagné !"+
				  "Pour recommencer, tapes èfe5");

		}
		else {
		    voix.playText("Non, ce n'est pas le mot mystère.");
		    p.setSalleEntree();
		    salle(false);
		    try { p.sauvegarder(); }
		    catch(IOException e) {
			e.printStackTrace();
		    }
		}
		break;
	    case KeyEvent.VK_F5:
		    p.setMenuPrincipal();
		    p = new Partie();
		    menuPrincipal();
		    voix.setVoix(3);
		voix.playText("D'accord pour une nouvelle partie."+
			      "et rappelez vous ! à tout instant, "+	  
			      "la touche èfe 4 donne les commandes clavier.");
		    break;
	    case KeyEvent.VK_BACK_SPACE:
		voix.playShortText("Effacer");
		p.enleverLettrePropositionMot();
		break;
	    default:
		String c = new Character(evt.getKeyChar()).toString();
		if(c!=null) {
		    if(c.equals("t")) {
			voix.playShortText("té");
		    }
		    else if(c.equals("y")) {
			voix.playText("i grec");
		    }
		    else if(c.equals("s")) {
			voix.playShortText("èce");
		    }
		    else
			voix.playText(c);
		    p.ajouterLettrePropositionMot(c);
		}
		break;
	    }	
	}
    }
	
    public void keyTyped(KeyEvent evt) {
    }

    public void keyReleased(KeyEvent evt) {
    }
	

}
