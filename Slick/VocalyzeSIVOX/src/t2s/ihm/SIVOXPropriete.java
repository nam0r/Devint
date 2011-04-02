package t2s.ihm;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.MouseEvent;
import org.eclipse.swt.events.MouseListener;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;
import org.jdom.*;

import t2s.exception.SIVOXException;

/**
 * Classe SIVOXPropriete heritant de CTabFolder
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class SIVOXPropriete extends CTabFolder {

	private SIVOXPropriete objetCourant = null;
	private Tree treePropriete = null;
	private CTabItem treeItem = null;
	private TreeItem t1 = null;
	private TreeItem t11 = null;
	private TreeItem t12 = null;
	private TreeItem t13 = null;
	private TreeItem t14 = null;
	private String nom = null;
	private String auteur = null;
	private String commentaire = null;
	private String nomFichier = null;
	private String racineFichier = null;
	private boolean isMaximized = false;
	private InterfaceGenerale i = null;
	
	/**
	 * Constructeur par defaut de SIVOXPropriete
	 * @param i1 L'interface appelante
	 * @param arg0 Le composite parent de SIVOXPropriete
	 * @param arg1 L'option du SIVOXPropriete
	 * @param nom Le nom du projet
	 * @param auteur Le nom d'auteur du projet
	 * @param commentaire Le commentaire du projet
	 * @param nomFichier Le nom de fichier de projet
	 */
	public SIVOXPropriete(InterfaceGenerale i1, Composite arg0, int arg1, String nom, String auteur, String commentaire, String nomFichier) {
		super(arg0, arg1);
		super.setMaximizeVisible(true);
		objetCourant = this;
		i = i1;
		this.nom = new String(nom);
		this.auteur = new String(auteur);
		this.commentaire = new String(commentaire);
		this.nomFichier = new String(nomFichier);
		this.setSimple(false);
		treeItem = new CTabItem(this, SWT.BORDER);
		treeItem.setText("Proprietes du projet");
		treePropriete = new Tree(this, SWT.NONE);
		treeItem.setControl(treePropriete);
		this.setSelection(0);
		t1 = new TreeItem(treePropriete, 0);
		t1.setText("projet Si-Vox");
		t11 = new TreeItem(t1, 0);
		t11.setText("Nom du projet : "+this.nom);
		t12 = new TreeItem(t1, 0);
		t12.setText("Auteur du projet : "+this.auteur);
		t13 = new TreeItem(t1, 0);
		t13.setText("Commentaires : "+this.commentaire);
		t14 = new TreeItem(t1, 0);
		t14.setText("Nom du fichier : "+this.nomFichier);
		
		try {
			treeItem.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"proprietes_16_16.png"));
			t1.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"enceinte_16_16.png"));
			t11.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t12.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t13.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t14.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(i.getShell(), SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("les images n'ont pas pu etre charge, le programme risque de ne pas fonctionner correctement !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
		}
		
		//evenement sur l arbre
		treePropriete.addMouseListener(new MouseListener () {
			public void mouseDoubleClick(MouseEvent arg0) {
				TreeItem[] t = treePropriete.getSelection();
				if(t[0].equals(t11))
				{
					InputBox ipb = new InputBox(i, "nom du projet ?", objetCourant.getNomProjet());
					String result = ipb.open();
					if(!result.equalsIgnoreCase(""))
					{
						objetCourant.setNomProjet(result);
						i.setModified();
					}
				}
				else if(t[0].equals(t12))
				{
					InputBox ipb = new InputBox(i, "auteur du projet ?", objetCourant.getAuteurProjet());
					String result = ipb.open();
					if(!result.equalsIgnoreCase(""))
					{
						objetCourant.setNomAuteur(result);
						i.setModified();
					}
				}
				else if(t[0].equals(t13))
				{
					InputBox ipb = new InputBox(i, "commentaire du projet ?", objetCourant.getCommentaireProjet());
					String result = ipb.open();
					if(!result.equalsIgnoreCase(""))
					{
						objetCourant.setCommentaire(result);
						i.setModified();
					}
				}
			}
			public void mouseDown(MouseEvent arg0) {}
			public void mouseUp(MouseEvent arg0) {}
		});
		
		//evenement sur le boutton reduire ou agrandir
		this.addCTabFolder2Listener(new CTabFolder2Listener() {
			public void close(CTabFolderEvent e) {}
			public void maximize(CTabFolderEvent arg0) {
				if(isMaximized == false)
				{
					setMaximized(true);
					isMaximized = true;
					i.maximiserPropriete();
				}
			}
			public void minimize(CTabFolderEvent arg0) {}
			public void restore(CTabFolderEvent arg0) {
				if(isMaximized == true)
				{
					setMaximized(false);
					isMaximized = false;
					i.demaximiserPropriete();
				}
			}
			public void showList(CTabFolderEvent arg0) {}
		});
	}

	/**
	 * Methode qui retourne le nom du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le nom du projet
	 */
	public String getNomProjet()
	{
		return(this.nom);
	}
	
	/**
	 * Methode qui change le nom du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param nom Le nouveau nom de projet
	 */
	public void setNomProjet(String nom)
	{
		this.nom = nom;
		t11.setText("Nom du projet : "+this.nom);
	}
	
	/**
	 * Methode qui retourne le nom d'auteur du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le nom d'auteur du projet
	 */
	public String getAuteurProjet()
	{
		return(this.auteur);
	}
	
	/**
	 * Methode qui change le nom d'auteur du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param auteur Le nouveau nom d'auteur du projet
	 */
	public void setNomAuteur(String auteur)
	{
		this.auteur = auteur;
		t12.setText("Auteur du projet : "+this.auteur);
	}
	
	/**
	 * Methode qui retourne le commentaire du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le commentaire du projet
	 */
	public String getCommentaireProjet()
	{
		return(this.commentaire);
	}
	
	/**
	 * Methode qui change le commentaire du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param commentaire Le nouveau commentaire du projet
	 */
	public void setCommentaire(String commentaire)
	{
		this.commentaire = commentaire;
		t13.setText("Commentaires : "+this.commentaire);
	}
	
	/**
	 * Methode qui retourne le nom de fichier du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le nom de fichier du projet
	 */
	public String getNomFichierProjet()
	{
		return(this.nomFichier);
	}
	
	/**
	 * Methode qui change le nom de fichier du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param nomFichier Le nouveau nom de fichier du projet
	 */
	public void setNomFichier(String nomFichier)
	{
		this.nomFichier = nomFichier;
		t14.setText("Nom du fichier : "+this.nomFichier);
	}
	
	/**
	 * Methode qui retourne la racine du fichier du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return La racine du fichier de projet (chemin racine)
	 */
	public String getRacineFichier()
	{
		return(racineFichier);
	}
	
	/**
	 * Methode qui change la racine du fichier de rpojet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param racine La nouvelle racine du fichier du projet
	 */
	public void setRacineFichier(String racine)
	{
		racineFichier = racine;
	}
	
	
	/**
	 * Methode qui indique si le projet a deja ete sauve ou pas
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si le projet a deja ete sauve et false sinon
	 */
	public boolean isSavedOnce()
	{
		return(!this.nomFichier.equals("NC"));
	}
	
	/**
	 * Methode qui charge la partie propriete du projet a partir d'un noeud XML
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param elementProp Le noeud XML des proprietes
	 * @param racineFichier La racine du fichier de projet
	 * @param nomFichier Le nom du fichier de projet
	 * @throws SIVOXException
	 */
	public void loadXML(Element elementProp, String racineFichier, String nomFichier) throws SIVOXException
	{
		try {
			this.setNomFichier(nomFichier);
			this.setRacineFichier(racineFichier);
			Element elementNomProjet = elementProp.getChild("NomProjet");
			Element elementAuteurProjet = elementProp.getChild("AuteurProjet");
			Element elementCommentaire = elementProp.getChild("Commentaire");
			this.setNomProjet(elementNomProjet.getText());
			this.setNomAuteur(elementAuteurProjet.getText());
			this.setCommentaire(elementCommentaire.getText());
			i.setNomProjet(elementNomProjet.getText());
		} catch (Exception e) {
			throw new SIVOXException("Erreur XML : Le chargement des proprietes a echoue","erreur lor du chargemen des propriaitai");
		}
	}
	
	/**
	 * Methode qui sauve dans un noeud XML les proprietes du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param prop Le noeud XML dans lequel on sauve les proprietes
	 * @throws SIVOXException
	 */
	public void saveXML(Element prop) throws SIVOXException
	{
		try {
			Element elementNomProjet = new Element("NomProjet");
			elementNomProjet.setText(nom);
			Element elementNomAuteur = new Element("AuteurProjet");
			elementNomAuteur.setText(auteur);
			Element elementCommentaire = new Element("Commentaire");
			elementCommentaire.setText(commentaire);
			prop.addContent(elementNomProjet);
			prop.addContent(elementNomAuteur);
			prop.addContent(elementCommentaire);
		} catch (Exception e) {
			throw new SIVOXException("Erreur XML : La sauvegarde des proprietes a echouee","erreur lor de la sauvegarde des propriaitai");
		}
	}
}
