package t2s.ihm;

import org.eclipse.swt.layout.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;

/**
 * Classe InterfaceNouveauProjet pour la creation de projet ou importation
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */
public class InterfaceNouveauProjet {

	private boolean sortie = false;
	private Shell fenetrePrincipale = null;
	private FormLayout layoutCentral = null;
	private Label labelNomProjet = null;
	private Label labelAuteurProjet = null;
	private Label labelCommentaireProjet = null;
	private Text textNomProjet = null;
	private Text textAuteurProjet = null;
	private Text textCommentaireProjet = null;
	private Label separateur = null;
	private Button bouttonCreer = null;
	private Button bouttonAnnuler = null;
	private boolean value = false;
	
	private InterfaceGenerale i = null;
	
	/**
	 * Constructeur par defaut de InterfaceNouveauProjet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param i1 L'interface generale appelante
	 * @param appendTitre Le titre a appliquer a la fenetre
	 */
	public InterfaceNouveauProjet (InterfaceGenerale i1, String appendTitre)
	{
		// affectation de l interface generale
		i = i1;
		
		// creation de la fenetre
		fenetrePrincipale = new Shell(i.getDisplay(), SWT.CLOSE);
		fenetrePrincipale.setText("VOCALYSE Si-Vox - Nouveau Projet..."+appendTitre);
		layoutCentral = new FormLayout();
		fenetrePrincipale.setLayout(layoutCentral);
		
		labelNomProjet = new Label(fenetrePrincipale, SWT.FLAT);
		labelNomProjet.setText("Nom du projet :");
		textNomProjet = new Text(fenetrePrincipale, SWT.BORDER);
		labelAuteurProjet = new Label(fenetrePrincipale, SWT.FLAT);
		labelAuteurProjet.setText("Auteur du projet :");
		textAuteurProjet = new Text(fenetrePrincipale, SWT.BORDER);
		labelCommentaireProjet = new Label(fenetrePrincipale, SWT.FLAT);
		labelCommentaireProjet.setText("Commentaires :");
		textCommentaireProjet = new Text(fenetrePrincipale, SWT.BORDER | SWT.H_SCROLL);
		bouttonCreer = new Button(fenetrePrincipale, SWT.PUSH);
		bouttonCreer.setText("&Creer");
		bouttonAnnuler = new Button(fenetrePrincipale, SWT.PUSH);
		bouttonAnnuler.setText("&Annuler");
		separateur = new Label(fenetrePrincipale, SWT.SEPARATOR | SWT.HORIZONTAL);
		
		// chargement des images
		try {
			fenetrePrincipale.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"nouveau_projet_32_32.png"));
			bouttonCreer.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"appliquer_16_16.png"));	
			bouttonAnnuler.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"annuler_16_16.png"));
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(fenetrePrincipale, SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("Le chargement des images a echoue, le programme peut ne pas fonctionner correctement !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
		}
		
		FormData labelNomProjetData = new FormData();
		labelNomProjetData.left = new FormAttachment(2);
		labelNomProjetData.right = new FormAttachment(25);
		labelNomProjetData.top = new FormAttachment(8);
		labelNomProjet.setLayoutData(labelNomProjetData);
		
		FormData textNomProjetData = new FormData();
		textNomProjetData.left = new FormAttachment(labelNomProjet);
		textNomProjetData.right = new FormAttachment(98);
		textNomProjetData.top = new FormAttachment(7);
		textNomProjet.setLayoutData(textNomProjetData);
		
		FormData labelAuteurProjetData = new FormData();
		labelAuteurProjetData.left = new FormAttachment(2);
		labelAuteurProjetData.right = new FormAttachment(25);
		labelAuteurProjetData.top = new FormAttachment(25);
		labelAuteurProjet.setLayoutData(labelAuteurProjetData);
		
		FormData textAuteurProjetData = new FormData();
		textAuteurProjetData.left = new FormAttachment(labelAuteurProjet);
		textAuteurProjetData.right = new FormAttachment(98);
		textAuteurProjetData.top = new FormAttachment(24);
		textAuteurProjet.setLayoutData(textAuteurProjetData);
		
		FormData labelCommentaireProjetData = new FormData();
		labelCommentaireProjetData.left = new FormAttachment(2);
		labelCommentaireProjetData.right = new FormAttachment(25);
		labelCommentaireProjetData.top = new FormAttachment(42);
		labelCommentaireProjet.setLayoutData(labelCommentaireProjetData);
		
		FormData textCommentaireProjetData = new FormData();
		textCommentaireProjetData.left = new FormAttachment(labelAuteurProjet);
		textCommentaireProjetData.right = new FormAttachment(98);
		textCommentaireProjetData.top = new FormAttachment(41);
		textCommentaireProjetData.bottom = new FormAttachment(69);
		textCommentaireProjet.setLayoutData(textCommentaireProjetData);
		
		FormData separateurData = new FormData();
		separateurData.left = new FormAttachment(2);
		separateurData.right = new FormAttachment(98);
		separateurData.top = new FormAttachment(73);
		separateur.setLayoutData(separateurData);
		
		FormData bouttonCreerData = new FormData();
		bouttonCreerData.left = new FormAttachment(50);
		bouttonCreerData.right = new FormAttachment(74);
		bouttonCreerData.top = new FormAttachment(76);
		bouttonCreerData.bottom = new FormAttachment(98);
		bouttonCreer.setLayoutData(bouttonCreerData);
		
		FormData bouttonAnnulerData = new FormData();
		bouttonAnnulerData.left = new FormAttachment(76);
		bouttonAnnulerData.right = new FormAttachment(98);
		bouttonAnnulerData.top = new FormAttachment(76);
		bouttonAnnulerData.bottom = new FormAttachment(98);
		bouttonAnnuler.setLayoutData(bouttonAnnulerData);
		
		// evement du boutton annuler
		bouttonAnnuler.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				sortie = true;
				value = false;
			}
		});
		
		// evenement du clic sur la croix rouge fermer
		fenetrePrincipale.addShellListener(new ShellListener () {

			public void shellActivated(ShellEvent arg0) {}
			public void shellClosed(ShellEvent arg0) {
				sortie = true;
				value = false;
				arg0.doit = false;
			}
			public void shellDeactivated(ShellEvent arg0) {}
			public void shellDeiconified(ShellEvent arg0) {}
			public void shellIconified(ShellEvent arg0) {}
		});
		
		// evenement du boutton creer
		bouttonCreer.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(textNomProjet.getText().length() == 0)
				{
					if(i.getModeParlantActif())
					{
						i.lectureAssistant("vous devai entrer un nom de projet");
					}
					MessageBox messageBox = new MessageBox(fenetrePrincipale, SWT.ICON_WARNING | SWT.OK);
			        messageBox.setMessage("Vous devez entrer un nom de projet !");
			        messageBox.setText("VOCALYSE Si-Vox - Erreur");
			        messageBox.open();
				}
				else if(textAuteurProjet.getText().length() == 0)
				{
					if(i.getModeParlantActif())
					{
						i.lectureAssistant("vous devai entrer un nom d'auteur");
					}
					MessageBox messageBox = new MessageBox(fenetrePrincipale, SWT.ICON_WARNING | SWT.OK);
			        messageBox.setMessage("Vous devez entrer un nom d'auteur !");
			        messageBox.setText("VOCALYSE Si-Vox - Erreur");
			        messageBox.open();
				}
				else
				{
					if(i.getModeParlantActif())
					{
						i.lectureAssistant("le nouvo projet a aitai crai ai");
					}
					i.setIconInformationInfo();
					i.setInformation("Creation d'un nouveau projet");
					ajouterProjet(textNomProjet.getText(), textAuteurProjet.getText(), textCommentaireProjet.getText(), "NC");
					sortie = true;
					value = true;
				}
			}
		});
		
		//evenement appui sur ALT dans le texte commentaire
		textCommentaireProjet.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent arg0) {
				if(arg0.keyCode == 9)
				{
					arg0.doit = false;
					bouttonCreer.setFocus();
				}
			}
			public void keyReleased(KeyEvent arg0) {}
		});
		
		fenetrePrincipale.setSize(600, 210);
		fenetrePrincipale.setLocation(i.getDisplay().getClientArea().x+((i.getDisplay().getClientArea().width-600)/2),i.getDisplay().getClientArea().y+((i.getDisplay().getClientArea().height-210)/2));
	}
	
	/**
	 * Methode qui cree le nouveau projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param nom Le nom du nouveau projet
	 * @param auteur Le nom d'auteur du nouveau projet
	 * @param commentaire Le commentaire du nouveau projet
	 * @param nomFichier Le nom de fichier du nouveau projet
	 */
	public void ajouterProjet(String nom, String auteur, String commentaire, String nomFichier)
	{
		i.ajouterProjet(nom, auteur, commentaire, nomFichier);
	}
	
	/**
	 * Methode qui ouvre la fenetre de l'interface nouveau projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si il y a eut creation et false sinon
	 */
	public boolean open()
	{
		fenetrePrincipale.open();
		
		while (!fenetrePrincipale.isDisposed() && (sortie == false))
	    {
	    	if (!i.getDisplay().readAndDispatch())
	    		i.getDisplay().sleep();
	    }
		
		fenetrePrincipale.dispose();
		return(value);
	}
}
