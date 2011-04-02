package t2s.ihm;

import java.io.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.jdom.*;
import org.jdom.input.*;
import org.jdom.output.*;

import t2s.exception.SIVOXException;

/**
 * Classe SIVOXTabItem heritant de CTabItemelle represente un projet
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class SIVOXTabItem extends CTabItem {

	private Composite cadre = null;
	private SIVOXPropriete propriete = null;
	private SIVOXTabText folderTexte = null;
	private SIVOXCompositeProsodie prosodie = null;
	private SIVOXGraphe graphe = null;
	private FormLayout layoutCentral = null;
	private FormData proprieteData = null;
	private FormData texteData = null;
	private FormData prosodieData = null;
	private FormData grapheData = null;
	private String nomProjet = null;
	private boolean isModified = false;
	private InterfaceGenerale i = null;
	private Sash sashVertical = null;
	private Sash sashHorizontal = null;
	private Sash sashVerticalGraphe = null;
	private int saveValueVerticalTop=0;
	private int saveValueVerticalLeft=0;
	private int saveValueVerticalBottom=0;
	private int saveValueHorizontalTop=0;
	private int saveValueHorizontalLeft=0;
	private int saveValueHorizontalRight=0;
	private int saveValueVerticalGrapheTop=0;
	private int saveValueVerticalGrapheLeft=0;
	private int saveValueVerticalGrapheBottom=0;
	
	/**
	 * Constructeur par defaut de SIVOXTabItem
	 * @param i1 L'interface generale appelante
	 * @param folder Le CTabFolder qui contient tous les projets
	 * @param argument L'option du SIVOXTabItem (projet)
	 * @param nom Le nom du projet
	 * @param auteur L'auteur du projet
	 * @param commentaire Le commentaire du projet
	 * @param nomFichier Le nom du fichier du projet
	 */
	public SIVOXTabItem(InterfaceGenerale i1, CTabFolder folder, int argument, String nom, String auteur, String commentaire, String nomFichier) {
		super(folder, argument);
		i = i1;
		nomProjet = new String(nom);
		this.setText(nomProjet);
		cadre = new Composite(folder, SWT.NONE);
		this.setControl(cadre);
		layoutCentral = new FormLayout();
		cadre.setLayout(layoutCentral);
		propriete = new SIVOXPropriete(i, cadre, SWT.BORDER, nom, auteur, commentaire, nomFichier);
		folderTexte = new SIVOXTabText(i, cadre, SWT.BORDER);
		prosodie = new SIVOXCompositeProsodie(i, cadre, SWT.NONE);
		graphe = new SIVOXGraphe(i, cadre, SWT.BORDER);
		final Sash sashVertical = new Sash(cadre, SWT.VERTICAL);
		final Sash sashHorizontal = new Sash(cadre, SWT.HORIZONTAL);
		final Sash sashVerticalGraphe = new Sash(cadre, SWT.VERTICAL);
		
		this.sashVertical = sashVertical;
		this.sashHorizontal = sashHorizontal;
		this.sashVerticalGraphe = sashVerticalGraphe;
		
		FormData sashVerticalData = new FormData();
		sashVerticalData.left = new FormAttachment(70);
		sashVerticalData.top = new FormAttachment(0);
		sashVerticalData.bottom = new FormAttachment(40);
		sashVertical.setLayoutData(sashVerticalData);
		
		FormData sashHorizontalData = new FormData();
		sashHorizontalData.left = new FormAttachment(0);
		sashHorizontalData.right = new FormAttachment(100);
		sashHorizontalData.top = new FormAttachment(40);
		sashHorizontal.setLayoutData(sashHorizontalData);
		
		FormData sashVerticalGrapheData = new FormData();
		sashVerticalGrapheData.left = new FormAttachment(45);
		sashVerticalGrapheData.top = new FormAttachment(40);
		sashVerticalGrapheData.bottom = new FormAttachment(100);
		sashVerticalGraphe.setLayoutData(sashVerticalGrapheData);
		
		texteData = new FormData();
		texteData.left = new FormAttachment(0);
		texteData.right = new FormAttachment(sashVertical);
		texteData.top = new FormAttachment(0);
		texteData.bottom = new FormAttachment(sashHorizontal);
		folderTexte.setLayoutData(texteData);
		proprieteData = new FormData();
		proprieteData.left = new FormAttachment(sashVertical);
		proprieteData.right = new FormAttachment(100);
		proprieteData.top = new FormAttachment(0);
		proprieteData.bottom = new FormAttachment(sashHorizontal);
		propriete.setLayoutData(proprieteData);
		prosodieData = new FormData();
		prosodieData.left = new FormAttachment(0);
		prosodieData.right = new FormAttachment(sashVerticalGraphe);
		prosodieData.top = new FormAttachment(sashHorizontal);
		prosodieData.bottom = new FormAttachment(100);
		prosodie.setLayoutData(prosodieData);
		grapheData = new FormData();
		grapheData.left = new FormAttachment(sashVerticalGraphe);
		grapheData.right = new FormAttachment(100);
		grapheData.top = new FormAttachment(sashHorizontal);
		grapheData.bottom = new FormAttachment(100);
		graphe.setLayoutData(grapheData);
		
		//mouvement su sash vertical
		sashVertical.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				((FormData) sashVertical.getLayoutData()).left = new FormAttachment(0, event.x);
		          sashVertical.getParent().layout();
			}
		});
		
		//mouvement du sash horizontal
		sashHorizontal.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				((FormData) sashHorizontal.getLayoutData()).top = new FormAttachment(0, event.y);
				((FormData) sashVertical.getLayoutData()).bottom = new FormAttachment(0, event.y);
				((FormData) sashVerticalGraphe.getLayoutData()).top = new FormAttachment(0, event.y);
				sashHorizontal.getParent().layout();
				sashVertical.getParent().layout();
				sashVerticalGraphe.getParent().layout();
			}
		});
		
		//mouvement du sash vertical (graphe)
		sashVerticalGraphe.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				((FormData) sashVerticalGraphe.getLayoutData()).left = new FormAttachment(0, event.x);
		          sashVerticalGraphe.getParent().layout();
		          graphe.redraw();
			}
		});
		
		// chargement des image du SIVOXTabItem
		try {
			this.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"enceinte_16_16.png"));
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(i.getShell(), SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("les images n'ont pas pu être charge, le programme risque de ne pas fonctionner correctement !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
		}
	}
	
	/**
	 * Methode qui va importer un texte dans la partie texte du projet (SIVOXTabText)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param chaine Le chaine a importer
	 * @throws SIVOXException
	 */
	public void importerText(String chaine) throws SIVOXException
	{
		try {
			folderTexte.importerText(chaine);
		} catch(SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui va importer une prosodie 
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param fichier Le fichier de prosodie a importer
	 * @throws SIVOXException
	 */
	public void importerProsodie(String fichier) throws SIVOXException
	{
		try {
			prosodie.importerProsodie(fichier);
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui va importer un chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param fichier Le fichier de chant a importer
	 * @throws SIVOXException
	 */
	public void importerChant(String fichier) throws SIVOXException
	{
		try {
			prosodie.importerChant(fichier);
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui ouvre un projet sivox a partir du fichier de projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param pathFichier Le path racine du fichier projet
	 * @param nomFichier Le nom du fichier de projet
	 * @throws SIVOXException
	 */
	public void ouvrirProjet(String pathFichier, String nomFichier) throws SIVOXException
	{
		try {
			SAXBuilder sxb = new SAXBuilder();
			Document document = null;
			document = sxb.build(new File(pathFichier+"/"+nomFichier));
			Element elementRacine = document.getRootElement();
			Element elementProp = elementRacine.getChild("Proprietes");
			propriete.loadXML(elementProp, pathFichier+"/", nomFichier);
			Element elementText = elementRacine.getChild("Text");
			folderTexte.loadXML(elementText);
			Element elementListeProsodie = elementRacine.getChild("ListeProsodie");
			prosodie.loadXML(elementListeProsodie);
			i.setUpdate();
		} catch (SIVOXException e) {
			throw e;
		} catch (Exception z) {
			throw new SIVOXException("Erreur : impossible d'ouvrir le projet sivox","impossible douvrir le projet essi vox");
		}
	}
	
	/**
	 * Methode qui sauve le projet dans un fichier
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param force Un booleen pour enregistrer sous
	 * @throws SIVOXException
	 */
	public void sauverProjet(boolean force) throws SIVOXException
	{
		try {
			String chaine = null;
			Element elementRacine = new Element("ProjetSiVox");
			Document document = new Document(elementRacine);
			Element elementProp = new Element("Proprietes");
			Element elementText = new Element("Text");
			Element elementListeProsodie = new Element("ListeProsodie");
			if((!propriete.isSavedOnce()) || (force == true))
			{
				FileDialog browser = new FileDialog(i.getShell(), SWT.SAVE);
				browser.setFileName(nomProjet);
				browser.setFilterNames(new String [] {"Fichier SIVOX (*.siv)"});
				browser.setFilterExtensions(new String [] {"*.siv"});
				chaine = browser.open();
				if(chaine != null)
				{
					File fichierTemp = null;
					if((chaine.length() > 4) && (chaine.subSequence(chaine.length()-4, chaine.length()).toString().equalsIgnoreCase(".siv")))
					{
						fichierTemp = new File(chaine);
					}
					else
					{
						fichierTemp = new File(chaine+".siv");
					}
					if(fichierTemp.exists())
					{
						MessageBox message = new MessageBox(i.getShell(), SWT.OK | SWT.CANCEL | SWT.ICON_WARNING);
						message.setText("Attention");
						message.setMessage("Le projet "+ browser.getFileName() + " existe deja, voulez-vous l'ecraser ?");
						if(message.open() == SWT.CANCEL)
						{
							return;
						}
					}
					propriete.setRacineFichier(browser.getFilterPath()+"/");
					if((chaine.length() > 4) && (chaine.subSequence(chaine.length()-4, chaine.length()).toString().equalsIgnoreCase(".siv")))
					{
						propriete.setNomFichier(browser.getFileName());
					}
					else
					{
						propriete.setNomFichier(browser.getFileName()+".siv");
						chaine=chaine+".siv";
					}
				}
			}
			else
			{
				chaine = propriete.getRacineFichier()+propriete.getNomFichierProjet();
			}
			if(chaine != null)
			{
				propriete.saveXML(elementProp);
				elementRacine.addContent(elementProp);
				folderTexte.saveXML(elementText);
				elementRacine.addContent(elementText);
				prosodie.saveXML(elementListeProsodie);
				elementRacine.addContent(elementListeProsodie);
				XMLOutputter sortie = new XMLOutputter(Format.getPrettyFormat());
				sortie.output(document, new FileOutputStream(chaine));
				this.setUpdate();
			}
		} catch (SIVOXException e) {
			throw e;
		} catch (Exception z) {
			throw new SIVOXException("Erreur : impossible de sauver le projet sivox","impossible de sauver le projet");
		}
	}
	
	/**
	 * Methode qui synchronise la prosodie courante en demandant a SIVOXCOmpositeProsodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void synchroniserProsodie() throws SIVOXException
	{
		try {
			prosodie.miseAJourProsodie(folderTexte.getText());
			this.setModified();
			prosodie.miseAJourGraphe();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande de jouer la prosodie courante en demandant a SIVOXCompositeProsodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void jouerProsodie() throws SIVOXException
	{
		try {
			int [] tabSelection = graphe.getListeSlection();
			prosodie.jouerProsodie(tabSelection);
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande de stopper la lecture de la prosodie courante en demandant a SIVOXCompositeProsodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void stopperProsodie() throws SIVOXException
	{
		try {
			prosodie.stopperProsodie();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande al a prosodie courante de sauver le fichier son en demandant a SIVOXCompositeProsodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void sauverWav() throws SIVOXException
	{
		try {
			int [] tabSelection = graphe.getListeSlection();
			prosodie.sauverWav(tabSelection);
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui met le projet dans l'etat modifie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setModified()
	{
		this.setText("* "+nomProjet);
		isModified = true;
	}
	
	/**
	 * Methode qui met le projet dans l'etat non modifie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setUpdate()
	{
		this.setText(nomProjet);
		isModified = false;
	}
	
	/**
	 * Methode qui retourne l'etat du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return true si le projet est dans l'etat modifie ou false sinon
	 */
	public boolean getModified()
	{
		return(isModified);
	}
	
	/**
	 * Methode qui change le nom du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param nomProjet Le nouveau nom de projet
	 */
	public void setNomProjet(String nomProjet)
	{
		this.nomProjet = nomProjet;
		this.setText(nomProjet);
	}
	
	/**
	 * Methode qui retourne le nom du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le nom du projet
	 */
	public String getNomProjet()
	{
		return(nomProjet);
	}
	
	/**
	 * Methode qui met toutes les prosodies dans l'etat non synchronise
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void setAllDesynchro() throws SIVOXException
	{
		try {
			prosodie.setAllDesynchro();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui ajoute une prosodie au projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void ajouterProsodie()
	{
		prosodie.ajouterprosodie();
	}
	
	/**
	 * Methode qui renomme la prosodie courante en demandant a SIVOXCompositeProsodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void renommerProsodie() throws SIVOXException
	{
		try {
			prosodie.renommerProsodie();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui supprime la prosodie courante en demandant a SIVOXCompositeProsodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void supprimerProsodie() throws SIVOXException
	{
		try {
			prosodie.supprimerProsodie();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui insere une frequence dans la prosodie courante en demandant a SIVOXCompositeProsodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void insererFrequence() throws SIVOXException
	{
		try {
			prosodie.insererFrequence();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui insere une rapidite dans la prosodie courante en demandant a SIVOXCOmpositeProsodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void insererRapidite() throws SIVOXException
	{
		try {
			prosodie.insererRapidite();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui insere un commentaire dans la prosodie courante en demandant a SIVOXCompositeProsodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void insererCommentaire() throws SIVOXException
	{
		try {
			prosodie.insererCommentaire();
		} catch (SIVOXException e) {
			
		}
	}
	
	/**
	 * Methode qui demande a la prosodie courante de s'enregistrer en demandant a SIVOXCompositeProsodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void enregistrerProsodie() throws SIVOXException
	{
		try {
			prosodie.enregistrerProsodie();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande de supprimer la prosodie courante a SIVOXCompositeProsodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void supprimerInsertion() throws SIVOXException
	{
		try {
			prosodie.supprimerInsertion();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui sauve la position des slash
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void sauverPosition()
	{
		saveValueVerticalTop = sashVertical.getBounds().y;
		saveValueVerticalLeft = sashVertical.getBounds().x;
		saveValueVerticalBottom = sashVertical.getBounds().y+sashVertical.getBounds().height;
		saveValueHorizontalTop = sashHorizontal.getBounds().y;
		saveValueHorizontalLeft = sashHorizontal.getBounds().x;
		saveValueHorizontalRight = sashHorizontal.getBounds().x+sashHorizontal.getBounds().width;
		saveValueVerticalGrapheTop = sashVerticalGraphe.getBounds().y;
		saveValueVerticalGrapheLeft = sashVerticalGraphe.getBounds().x;
		saveValueVerticalGrapheBottom = sashVerticalGraphe.getBounds().y+sashVerticalGraphe.getBounds().height;
	}
	
	/**
	 * Methode qui restaure la position des slash
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void restorePosition()
	{
		((FormData) sashVertical.getLayoutData()).top = new FormAttachment(0,saveValueVerticalTop);
		((FormData) sashVertical.getLayoutData()).left = new FormAttachment(0,saveValueVerticalLeft);
		((FormData) sashVertical.getLayoutData()).bottom = new FormAttachment(0,saveValueVerticalBottom);
		((FormData) sashHorizontal.getLayoutData()).top = new FormAttachment(0,saveValueHorizontalTop);
		((FormData) sashHorizontal.getLayoutData()).left = new FormAttachment(0,saveValueHorizontalLeft);
		((FormData) sashHorizontal.getLayoutData()).right = new FormAttachment(0,saveValueHorizontalRight);
		((FormData) sashVerticalGraphe.getLayoutData()).top = new FormAttachment(0,saveValueVerticalGrapheTop);
		((FormData) sashVerticalGraphe.getLayoutData()).left = new FormAttachment(0,saveValueVerticalGrapheLeft);
		((FormData) sashVerticalGraphe.getLayoutData()).bottom = new FormAttachment(0,saveValueVerticalGrapheBottom);
		sashHorizontal.getParent().layout();
		sashVertical.getParent().layout();
		sashVerticalGraphe.getParent().layout();
	}
	
	/**
	 * Methode qui maximise la zone des proprietes du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserPropriete()
	{
		sauverPosition();
		((FormData) sashVertical.getLayoutData()).top = new FormAttachment(0);
		((FormData) sashVertical.getLayoutData()).left = new FormAttachment(0);
		((FormData) sashVertical.getLayoutData()).bottom = new FormAttachment(0);
		((FormData) sashHorizontal.getLayoutData()).top = new FormAttachment(100);
		((FormData) sashHorizontal.getLayoutData()).left = new FormAttachment(100);
		((FormData) sashHorizontal.getLayoutData()).right = new FormAttachment(100);
		((FormData) sashVerticalGraphe.getLayoutData()).top = new FormAttachment(100);
		((FormData) sashVerticalGraphe.getLayoutData()).left = new FormAttachment(100);
		((FormData) sashVerticalGraphe.getLayoutData()).bottom = new FormAttachment(100);
		sashHorizontal.getParent().layout();
		sashVertical.getParent().layout();
		sashVerticalGraphe.getParent().layout();
	}
	
	/**
	 * Methode qui maximise la zone de texte su projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserTexte()
	{
		sauverPosition();
		((FormData) sashVertical.getLayoutData()).top = new FormAttachment(100);
		((FormData) sashVertical.getLayoutData()).left = new FormAttachment(100);
		((FormData) sashVertical.getLayoutData()).bottom = new FormAttachment(100);
		((FormData) sashHorizontal.getLayoutData()).top = new FormAttachment(100);
		((FormData) sashHorizontal.getLayoutData()).left = new FormAttachment(100);
		((FormData) sashHorizontal.getLayoutData()).right = new FormAttachment(100);
		((FormData) sashVerticalGraphe.getLayoutData()).top = new FormAttachment(100);
		((FormData) sashVerticalGraphe.getLayoutData()).left = new FormAttachment(100);
		((FormData) sashVerticalGraphe.getLayoutData()).bottom = new FormAttachment(100);
		sashHorizontal.getParent().layout();
		sashVertical.getParent().layout();
		sashVerticalGraphe.getParent().layout();
	}
	
	/**
	 * Methode qui maximise la zone des prosodies du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserProsodie()
	{
		sauverPosition();
		((FormData) sashVertical.getLayoutData()).top = new FormAttachment(100);
		((FormData) sashVertical.getLayoutData()).left = new FormAttachment(100);
		((FormData) sashVertical.getLayoutData()).bottom = new FormAttachment(100);
		((FormData) sashHorizontal.getLayoutData()).top = new FormAttachment(0);
		((FormData) sashHorizontal.getLayoutData()).left = new FormAttachment(0);
		((FormData) sashHorizontal.getLayoutData()).right = new FormAttachment(0);
		((FormData) sashVerticalGraphe.getLayoutData()).top = new FormAttachment(100);
		((FormData) sashVerticalGraphe.getLayoutData()).left = new FormAttachment(100);
		((FormData) sashVerticalGraphe.getLayoutData()).bottom = new FormAttachment(100);
		sashHorizontal.getParent().layout();
		sashVertical.getParent().layout();
		sashVerticalGraphe.getParent().layout();
	}
	
	/**
	 * Methode qui maximise la zone de graphe du projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserGraphe()
	{
		sauverPosition();
		((FormData) sashVertical.getLayoutData()).top = new FormAttachment(100);
		((FormData) sashVertical.getLayoutData()).left = new FormAttachment(100);
		((FormData) sashVertical.getLayoutData()).bottom = new FormAttachment(100);
		((FormData) sashHorizontal.getLayoutData()).top = new FormAttachment(0);
		((FormData) sashHorizontal.getLayoutData()).left = new FormAttachment(0);
		((FormData) sashHorizontal.getLayoutData()).right = new FormAttachment(0);
		((FormData) sashVerticalGraphe.getLayoutData()).top = new FormAttachment(100);
		((FormData) sashVerticalGraphe.getLayoutData()).left = new FormAttachment(0);
		((FormData) sashVerticalGraphe.getLayoutData()).bottom = new FormAttachment(100);
		sashHorizontal.getParent().layout();
		sashVertical.getParent().layout();
		sashVerticalGraphe.getParent().layout();
	}
	
	/**
	 * Methode qui demande au graphe de se mettre a jour
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param chainePhoneme Le chaine pour le graphe
	 */
	public void miseAJourGraphe(String chainePhoneme)
	{
		graphe.initialiserPoints(chainePhoneme);
		graphe.redraw();
	}
	
	/**
	 * Methode qui demande de mettre a jour la prosodie en demandant a SIVOXCompositeProsodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param chaineProsodie La chaine a traiter pour la lise a jour
	 */
	public void miseAJourTableauProsodie(String chaineProsodie) throws SIVOXException
	{
		try {
			prosodie.miseAJourTableauProsodie(chaineProsodie);
		} catch (SIVOXException e) {
			throw e;
		}
	}
}
