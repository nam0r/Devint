package t2s.ihm;

import java.io.*;
import java.util.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.widgets.Text;
import org.jdom.*;
import t2s.chant.*;
import t2s.exception.SIVOXException;
import t2s.prosodie.*;
import t2s.son.*;
import t2s.newProsodies.Analyser;
import t2s.util.*;

/**
 * Classe SIVOXProsodieTabItem heritant de CTabItem
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class SIVOXProsodieTabItem extends CTabItem {
	
	private SynthetiseurMbrola synthetiseur;
	private FileDialog browser = null;
	private Composite cadre = null;
	private Table tableProsodie = null;
	private CLabel bouttonVerrou = null;
	private FormData tableProsodieData = null;
	private FormData bouttonVerrouData = null;
	private String titreProsodie = null;
	private boolean isSynchro = false;
	private boolean isChantOrImport = false;
	private int myProsodie = 0;
	private InterfaceGenerale i = null;
	private SIVOXProsodieTabItem objetCourant = null;
	
	/**
	 * Constructeur par defaut de SIVOXProsodieTabItem
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param i1 L'interface appelante
	 * @param arg0 Le CTabFolder parent
	 * @param arg1 L'option du SIVOXProsodieTabItem
	 * @param titre Le titre de la prosodie
	 */
	public SIVOXProsodieTabItem(InterfaceGenerale i1, CTabFolder arg0, int arg1, String titre) {
		super(arg0, arg1);
		i = i1;
		objetCourant = this;
		titreProsodie = new String(titre);
		this.setText(titreProsodie);
		cadre = new Composite(arg0, SWT.NONE);
		cadre.setLayout(new FormLayout());
		this.setControl(cadre);
		
		bouttonVerrou = new CLabel(cadre, SWT.FLAT);
		bouttonVerrou.setText("Prosodie non synchronisee");
		bouttonVerrouData = new FormData();
		bouttonVerrouData.left = new FormAttachment(0);
		bouttonVerrouData.right = new FormAttachment(100);
		bouttonVerrouData.bottom = new FormAttachment(100);
		bouttonVerrou.setLayoutData(bouttonVerrouData);
		
		tableProsodie = new Table(cadre, SWT.BORDER | SWT.MULTI | SWT.FULL_SELECTION);
		tableProsodie.setLinesVisible(true);
		tableProsodie.setHeaderVisible(true);
		TableColumn colonnePhoneme = new TableColumn(tableProsodie, SWT.LEFT);
		colonnePhoneme.setText("Phoneme");
		colonnePhoneme.setWidth(80);
		TableColumn colonneLongueur = new TableColumn(tableProsodie, SWT.LEFT);
		colonneLongueur.setText("Longueur");
		colonneLongueur.setWidth(80);
		TableColumn colonnePourcentage1 = new TableColumn(tableProsodie, SWT.LEFT);
		colonnePourcentage1.setText("Pourcentage");
		colonnePourcentage1.setWidth(95);
		TableColumn colonneFrequence1 = new TableColumn(tableProsodie, SWT.LEFT);
		colonneFrequence1.setText("Frequence");
		colonneFrequence1.setWidth(80);
		TableColumn colonnePourcentage2 = new TableColumn(tableProsodie, SWT.LEFT);
		colonnePourcentage2.setText("Pourcentage");
		colonnePourcentage2.setWidth(95);
		TableColumn colonneFrequence2 = new TableColumn(tableProsodie, SWT.LEFT);
		colonneFrequence2.setText("Frequence");
		colonneFrequence2.setWidth(80);
		TableColumn colonnePourcentage3 = new TableColumn(tableProsodie, SWT.LEFT);
		colonnePourcentage3.setText("Pourcentage");
		colonnePourcentage3.setWidth(95);
		TableColumn colonneFrequence3 = new TableColumn(tableProsodie, SWT.LEFT);
		colonneFrequence3.setText("Frequence");
		colonneFrequence3.setWidth(80);
		TableColumn colonnePourcentage4 = new TableColumn(tableProsodie, SWT.LEFT);
		colonnePourcentage4.setText("Pourcentage");
		colonnePourcentage4.setWidth(95);
		TableColumn colonneFrequence4 = new TableColumn(tableProsodie, SWT.LEFT);
		colonneFrequence4.setText("Frequence");
		colonneFrequence4.setWidth(80);
		TableColumn colonnePourcentage5 = new TableColumn(tableProsodie, SWT.LEFT);
		colonnePourcentage5.setText("Pourcentage");
		colonnePourcentage5.setWidth(95);
		TableColumn colonneFrequence5 = new TableColumn(tableProsodie, SWT.LEFT);
		colonneFrequence5.setText("Frequence");
		colonneFrequence5.setWidth(80);
		TableColumn colonnePourcentage6 = new TableColumn(tableProsodie, SWT.LEFT);
		colonnePourcentage6.setText("Pourcentage");
		colonnePourcentage6.setWidth(95);
		TableColumn colonneFrequence6 = new TableColumn(tableProsodie, SWT.LEFT);
		colonneFrequence6.setText("Frequence");
		colonneFrequence6.setWidth(80);
		TableColumn colonnePourcentage7 = new TableColumn(tableProsodie, SWT.LEFT);
		colonnePourcentage7.setText("Pourcentage");
		colonnePourcentage7.setWidth(95);
		TableColumn colonneFrequence7 = new TableColumn(tableProsodie, SWT.LEFT);
		colonneFrequence7.setText("Frequence");
		colonneFrequence7.setWidth(80);
		
		tableProsodieData = new FormData();
		tableProsodieData.left = new FormAttachment(0);
		tableProsodieData.right = new FormAttachment(100);
		tableProsodieData.top = new FormAttachment(0);
		tableProsodieData.bottom = new FormAttachment(bouttonVerrou);
		tableProsodie.setLayoutData(tableProsodieData);
		
		final TableEditor editor = new TableEditor(tableProsodie);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		tableProsodie.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {
				Rectangle clientArea = tableProsodie.getClientArea();
				Point pt = new Point(event.x, event.y);
				int index = tableProsodie.getTopIndex();
				while (index < tableProsodie.getItemCount()) {
					boolean visible = false;
					final TableItem item = tableProsodie.getItem(index);
					for (int i = 1; i < tableProsodie.getColumnCount(); i++) {
						Rectangle rect = item.getBounds(i);
						if (rect.contains(pt)) {
							final int column = i;
							final Text text = new Text(tableProsodie, SWT.NONE);
							Listener textListener = new Listener() {
								public void handleEvent(final Event e) {
									switch (e.type) {
									case SWT.FocusOut:
											try {
												if(text.getText().length() == 0)
												{
													item.setText(column, text.getText());
													setModified();
													objetCourant.miseAJourGraphe();
												}
												else if(!item.getText(column).equalsIgnoreCase(text.getText()))
												{
													try {
														final int val = Integer.parseInt(text.getText());
														if((val >= 0) && (val < 1000))
														{
															item.setText(column, text.getText());
															setModified();
															objetCourant.miseAJourGraphe();
														}
													} catch (Exception g) {}
												}
												text.dispose();
											} catch (Exception t) {}
										break;						
									case SWT.Traverse:
										switch (e.detail) {
										case SWT.TRAVERSE_RETURN:
											try {
												if(text.getText().length() == 0)
												{
													item.setText(column, text.getText());
													setModified();
													objetCourant.miseAJourGraphe();
												}
												else if(!item.getText(column).equalsIgnoreCase(text.getText()))
												{
													try {
														final int val = Integer.parseInt(text.getText());
														if((val >= 0) && (val <= 1000))
														{
															item.setText(column, text.getText());
															setModified();
															objetCourant.miseAJourGraphe();
														}
													} catch (Exception g) {}
												}
											} catch (Exception t) {}
										case SWT.TRAVERSE_ESCAPE:
											text.dispose();
											e.doit = false;
										}
										break;
									}
								}
							};
							text.addListener(SWT.FocusOut, textListener);
							text.addListener(SWT.Traverse, textListener);
							editor.setEditor(text, item, i);
							text.setText(item.getText(i));
							text.selectAll();
							text.setFocus();
							return;
						}
						if (!visible && rect.intersects(clientArea)) {
							visible = true;
						}
					}
					if (!visible)
						return;
					index++;
				}
			}
		});
		
		// chargement des images
		try {
			bouttonVerrou.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"boutton_rouge_16_16.png"));
			this.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"prosodie_16_16.png"));
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(i.getShell(), SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("les images n'ont pas pu etre charge, le programme risque de ne pas fonctionner correctement !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
		}
		
		// creation du file dialog pour enregistrer les wav
		browser = new FileDialog(i.getShell(), SWT.SAVE);
	}
	
	/**
	 * Methode qui enregistre un morceau de la prosodie (selection) dans un fichier texte
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param fichier Le nom du fichier dans lequel on enregistre la prosodie
	 * @throws SIVOXException
	 */
	public void enregistrerMorceauProsodie(int [] tabSelection, String fichier) throws SIVOXException
	{
		try {
			boolean tout = (tabSelection.length == 0);
			int l = 0;
			int cpt = 0;
			int cptMax = tabSelection.length;
			int j, k;
			FileWriter fw;
			fw = new FileWriter(fichier);
			String chaine = new String();
			for(j = 0 ; j < tableProsodie.getItemCount() ; j++)
			{
				if(tableProsodie.getItem(j).getText(0).startsWith(";") || tout)
				{
					for(k = 0 ; k < 16 ; k++)
					{
						chaine = chaine + tableProsodie.getItem(j).getText(k) + " ";
					}
					chaine = chaine + tableProsodie.getItem(j).getText(k) + "\n";
				}
				else
				{
					if(cpt < cptMax)
					{
						if(l == tabSelection[cpt])
						{
							for(k = 0 ; k < 16 ; k++)
							{
								chaine = chaine + tableProsodie.getItem(j).getText(k) + " ";
							}
							chaine = chaine + tableProsodie.getItem(j).getText(k) + "\n";
							cpt++;
						}
						l++;
					}
				}
			}
			fw.write(chaine);
			fw.close();
			i.setIconInformationInfo();
		} catch (Exception e) {
			throw new SIVOXException("Erreur : le prosodie n'a pas pu etre enregistree correctement","le prosodi na pa pu aitre  enregistrai correctemen");
		}
	}
	
	/**
	 * Methode qui renomme la prosodie en demandant a l'utilisateur
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void renommerProsodie()
	{
		InputBox input = new InputBox(i, "Nouveau nom ?", this.getTitre());
		String chaine = input.open();
		if(!chaine.equalsIgnoreCase(""))
		{
			titreProsodie = chaine;
			if(myProsodie != 0)
			{
				this.setText(titreProsodie + " [" + myProsodie + "]");
			}
			else
			{
				this.setText(titreProsodie);
			}
			i.setModified();
		}
	}
	
	/**
	 * Methode qui renomme la prosodie sans demander a l'utilisateur
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param nouveauNom Le nouveau nom de la prosodie
	 */
	public void renommerProsodie(String nouveauNom)
	{
		titreProsodie = nouveauNom;
		if(myProsodie != 0)
		{
			this.setText(titreProsodie + " [" + myProsodie + "]");
		}
		else
		{
			this.setText(titreProsodie);
		}
		i.setModified();
	}
	
	/**
	 * Methode d'insertion de frequence/rapidite/commentaire dans la prosodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param type Le type d'element a ajouter (frequence/rapidite/commentaire)
	 * @throws SIVOXException
	 */
	public void inserer(int type) throws SIVOXException
	{
		InputBox input = null;
		if(tableProsodie.getSelectionCount() == 1)
		{
			if(type == 0)
			{
				i.setInformation("inserer une frequence");
				input = new InputBox(i, ";;F = ? - Valeur de frequence ?", "");
			}
			else if(type == 1)
			{
				i.setInformation("inserer une rapidite");
				input = new InputBox(i, ";;T = ? - Valeur de rapidite ?", "");
			}
			else
			{
				i.setInformation("inserer un commentaire");
				input = new InputBox(i, "; ? - Commentaire ?", "");
			}
			String chaine = input.open();
			if(!chaine.equalsIgnoreCase(""))
			{
				if(type == 0)
				{
					chaine = ";;F="+chaine;
					TableItem item = new TableItem(tableProsodie, SWT.NONE, tableProsodie.getSelectionIndex());
					item.setBackground(new Color(i.getDisplay(), 40, 202, 56));
					item.setText(chaine);
				}
				else if(type == 1)
				{
					chaine=";;T="+chaine;
					TableItem item = new TableItem(tableProsodie, SWT.NONE, tableProsodie.getSelectionIndex());
					item.setBackground(new Color(i.getDisplay(), 40, 202, 56));
					item.setText(chaine);
				}
				else
				{
					chaine=";"+chaine;
					TableItem item = new TableItem(tableProsodie, SWT.NONE, tableProsodie.getSelectionIndex());
					item.setBackground(new Color(i.getDisplay(), 39, 146, 203));
					item.setText(chaine);
					
				}
				i.setModified();
				miseAJourGraphe();
			}
		}
		else
		{
			if(tableProsodie.getSelectionCount() == 0)
			{
				if(type == 0)
				{
					throw new SIVOXException("Attention : vous devez selectionner une ligne pour ajouter une frequence","vous devez sailectionner une ligne pour ajouter une fraikance");
				}
				else if(type == 1)
				{
					throw new SIVOXException("Attention : vous devez selectionner une ligne pour ajouter une rapidite","vous devez sailectionner une ligne pour ajouter une rapiditai");
				}
				else
				{
					throw new SIVOXException("Attention : vous devez selectionner une ligne pour ajouter un commentaire","vous devez sailectionner une ligne pour ajouter un commentaire");
				}
			}
			else
			{
				if(type == 0)
				{
					throw new SIVOXException("Attention : vous devez selectionner une seule ligne pour ajouter une frequence","vous devez sailectionner une seule ligne pour ajouter une fraikance");
				}
				else if(type == 1)
				{
					throw new SIVOXException("Attention : vous devez selectionner une seule ligne pour ajouter une rapidite","vous devez sailectionner une seule ligne pour ajouter une rapiditai");
				}
				else
				{
					throw new SIVOXException("Attention : vous devez selectionner une seule ligne pour ajouter un commentaire","vous devez sailectionner une seule ligne pour ajouter un commentaire");
				}
			}
		}
	}
	
	/**
	 * Methode qui supprime une ligne de frequence/rapidite/commentaire
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void supprimer() throws SIVOXException
	{
		if(tableProsodie.getSelectionCount() > 0)
		{
			int [] tableauIndex = tableProsodie.getSelectionIndices();
			if(tableauIndex.length > 0)
			{
				for(int j = tableauIndex.length-1 ; j >= 0 ; j--)
				{
					if(tableProsodie.getItem(tableauIndex[j]).getText(0).startsWith(";"))
					{
						tableProsodie.remove(tableauIndex[j]);
					}
				}
			}
			i.setModified();
			miseAJourGraphe();
		}
		else
		{
			throw new SIVOXException("Attention : vous devez selectionner une ligne de frequence, rapidite ou commentaire pour la supprimer","vou devez sailectionner une ligne pour la supprimer");
		}
	}
	
	/**
	 * Methode d'importation de prosodie existante
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param fichier Le fichier de la prosodie existante
	 * @throws SIVOXException
	 */
	public void importerProsodie(String fichier) throws SIVOXException
	{
		try {
			RandomAccessFile raf = new RandomAccessFile(fichier, "r");
			String ligne = "";
			while((ligne = raf.readLine()) != null)
			{
				if(!(ligne.equalsIgnoreCase("")))
				{
					if(ligne.startsWith(";"))
					{
						// commande ou commentaire
						if(ligne.startsWith(";;"))
						{
							// commande (T ou F)
							TableItem itemLigne = new TableItem(tableProsodie, SWT.NONE);
							itemLigne.setBackground(new Color(i.getDisplay(), 40, 202, 56));
							itemLigne.setText(ligne);
						}
						else
						{
							// commentaire
							TableItem itemLigne = new TableItem(tableProsodie, SWT.NONE);
							itemLigne.setBackground(new Color(i.getDisplay(), 39, 146, 203));
							itemLigne.setText(ligne);
						}
					}
					else
					{
						while(ligne.contains("  "))
						{
							ligne = ligne.replaceAll("  ", " ");
						}
						String [] pho = ligne.split(" ");
						TableItem itemLigne = new TableItem(tableProsodie, SWT.NONE);
						itemLigne.setText(pho);
					}
				}
			}
			isChantOrImport = true;
			miseAJourGraphe();
		} catch (Exception e) {
			throw new SIVOXException("Erreur : l'importation de prosodie a echouee","impossible dimportai la prosodi");
		}
	}
	
	/**
	 * Methode qui importe un chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param fichier Le fichier de chant (.SVC)
	 * @throws SIVOXException
	 */
	public void importerChant(String fichier) throws SIVOXException
	{
		try {
			Chant c = new Chant(fichier);
			Vector<String> vectorPhoneme = null;
			vectorPhoneme = c.toPhoInt();
			for(Iterator<String> it = vectorPhoneme.iterator() ; it.hasNext() ; )
			{
				String [] ligne = ((String)it.next()).split(" ");
				if(ligne[0].startsWith(";"))
				{
					// commande ou commentaire
					if(ligne[0].startsWith(";;"))
					{
						// commande
						TableItem itemLigne = new TableItem(tableProsodie, SWT.NONE);
						itemLigne.setBackground(new Color(i.getDisplay(), 40, 202, 56));
						itemLigne.setText(ligne);
					}
					else
					{
						// commentaire
						TableItem itemLigne = new TableItem(tableProsodie, SWT.NONE);
						itemLigne.setBackground(new Color(i.getDisplay(), 39, 146, 203));
						itemLigne.setText(ligne);
					}
				}
				else
				{
					TableItem itemLigne = new TableItem(tableProsodie, SWT.NONE);
					itemLigne.setText(ligne);
				}
			}
			isChantOrImport = true;
			miseAJourGraphe();
		} catch (SIVOXException e) {
			throw e;
		} catch (Exception z) {
			throw new SIVOXException("Erreur : impossible d'importer le fichier de chant "+fichier,"impossible dimportai le chan");
		}
	}
	
	/**
	 * Methode qui charge la partie prosodie a partir d'un noeud XML
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param prosodie Le noued XML de la prosodie
	 * @throws SIVOXException
	 */
	public void loadXML(Element prosodie) throws SIVOXException
	{
		try {
			Element elementNumeroProsodie = prosodie.getChild("NumeroProsodie");
			myProsodie = Integer.parseInt(elementNumeroProsodie.getText());
			Element elementNomProsodie = prosodie.getChild("NomProsodie");
			this.renommerProsodie(elementNomProsodie.getText());
			Element elementSynchroProsodie = prosodie.getChild("Synchronise");
			if(elementSynchroProsodie.getText().equalsIgnoreCase("true"))
				this.setSynchro();
			else
				this.setNonSynchro();
			Element elementChantOrImport = prosodie.getChild("ChantOrImport");
			if(elementChantOrImport.getText().equalsIgnoreCase("true"))
				isChantOrImport = true;
			else
				isChantOrImport = false;
			tableProsodie.removeAll();
			Element elementListePhoneme = prosodie.getChild("ListePhoneme");
			java.util.List liste = (java.util.List)elementListePhoneme.getChildren("Phoneme");
			Iterator j = liste.iterator();
			while(j.hasNext())
			{
				Element elementPhoneme = (Element)j.next();
				TableItem itemLigne = new TableItem(tableProsodie, SWT.NONE);
				String [] ligne = new String[16];
				ligne[0] = elementPhoneme.getChild("Data").getText();
				ligne[1] = elementPhoneme.getChild("Longueur").getText();
				java.util.List listeCouple = (java.util.List)elementPhoneme.getChild("ListeCouple").getChildren("Couple");
				Iterator k = listeCouple.iterator();
				int indice = 2;
				while((k.hasNext()) && (indice <= 15))
				{
					Element elementCouple = (Element)k.next();
					ligne[indice] = elementCouple.getChild("Pourcentage").getText();
					ligne[indice+1] = elementCouple.getChild("Frequence").getText();
					indice = indice + 2;
				}
				itemLigne.setText(ligne);
				if(ligne[0].startsWith(";"))
				{
					if(ligne[0].startsWith(";;"))
					{
						itemLigne.setBackground(new Color(i.getDisplay(), 40, 202, 56));
					}
					else
					{
						itemLigne.setBackground(new Color(i.getDisplay(), 39, 146, 203));
					}
				}
			}
		} catch (Exception e) {
			throw new SIVOXException("Erreur XML : Le chargement de la partie prosodie a echouee","erreur lor du chargemen des prosodi");
		}
	}
	
	/**
	 * Methode qui enregistre la prosodie dans un noeud XML
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param listeProsodie Le noeud XML des prosodies
	 * @throws SIVOXException
	 */
	public void saveXML(Element listeProsodie) throws SIVOXException
	{
		try {
			Element elementProsodie = new Element("Prosodie");
			Element elementNomProsodie = new Element("NomProsodie");
			elementNomProsodie.setText(titreProsodie);
			elementProsodie.addContent(elementNomProsodie);
			Element elementNumeroProsodie = new Element("NumeroProsodie");
			elementNumeroProsodie.setText(""+myProsodie);
			elementProsodie.addContent(elementNumeroProsodie);
			Element elementSynchroProsodie = new Element("Synchronise");
			if(isSynchro == true)
				elementSynchroProsodie.setText("true");
			else
				elementSynchroProsodie.setText("false");
			elementProsodie.addContent(elementSynchroProsodie);
			Element elementChantOrImport = new Element("ChantOrImport");
			if(isChantOrImport == true)
				elementChantOrImport.setText("true");
			else
				elementChantOrImport.setText("false");
			elementProsodie.addContent(elementChantOrImport);
			Element elementListePhoneme = new Element("ListePhoneme");
			for(int i = 0 ; i < tableProsodie.getItemCount() ; i++)
			{
				Element elementPhoneme = new Element("Phoneme");
				Element elementData = new Element("Data");
				elementData.setText(tableProsodie.getItem(i).getText(0));
				elementPhoneme.addContent(elementData);
				Element elementLongueur = new Element("Longueur");
				elementLongueur.setText(tableProsodie.getItem(i).getText(1));
				elementPhoneme.addContent(elementLongueur);
				Element elementListeCouple = new Element("ListeCouple");
				for(int j = 2 ; j <= 14 ; j=j+2)
				{
					Element elementCouple = new Element("Couple");
					Element elementPourcentage = new Element("Pourcentage");
					elementPourcentage.setText(tableProsodie.getItem(i).getText(j));
					Element elementFrequence = new Element("Frequence");
					elementFrequence.setText(tableProsodie.getItem(i).getText(j+1));
					elementCouple.addContent(elementPourcentage);
					elementCouple.addContent(elementFrequence);
					elementListeCouple.addContent(elementCouple);
				}
				elementPhoneme.addContent(elementListeCouple);
				elementListePhoneme.addContent(elementPhoneme);
			}
			elementProsodie.addContent(elementListePhoneme);
			listeProsodie.addContent(elementProsodie);
		} catch (Exception e) {
			throw new SIVOXException("Erreur XML : La sauvegarde de la prosodie a echouee","erreur lor de la sauvegarde de la prosodi");
		}
	}
	
	/**
	 * Methode qui met a jour la prosodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param text Le texte a prosodier
	 * @throws SIVOXException
	 */
	public void miseAJourProsodie(String text) throws SIVOXException
	{
		if(!getChantOrImport())
		{
			tableProsodie.removeAll();
			String ligne;
			Analyser an = new Analyser(text, i.getNumeroProsodie());
			Vector<Phoneme> listePhonemes = an.analyserGroupes();
			for(Iterator<Phoneme> it = listePhonemes.iterator() ; it.hasNext() ; )
			{
				TableItem itemLigne = new TableItem(tableProsodie, SWT.NONE);
				ligne = ((Phoneme)it.next()).toString();
				while(ligne.contains("  "))
				{
					ligne = ligne.replaceAll("  ", " ");
				}
				itemLigne.setText(ligne.split(" "));
			}
			this.setSynchro();
			myProsodie = i.getNumeroProsodie();
			renommerProsodie(getTitre());
		}
		else
		{
			throw new SIVOXException("Erreur : une prosodie importee ne peut pas etre modifiee","impossible de modifiai la prosodi car sailsi aitimportai");
		}
	}
	
	/**
	 * Methode qui joue la prosodie (ou une selection) en utilisant un synthetiseur
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void jouerProsodie(int [] tabSelection) throws SIVOXException
	{
		try {
			LecteurTexte lt = new LecteurTexte();
			lt.setVoix(i.getVoix());
			enregistrerMorceauProsodie(tabSelection, ConfigFile.rechercher("REPERTOIRE_PHO_WAV")+"/"+ConfigFile.rechercher("FICHIER_PHO_WAV")+".pho");
			synthetiseur = new SynthetiseurMbrola(lt.getVoix(),ConfigFile.rechercher("REPERTOIRE_PHO_WAV")+"/",ConfigFile.rechercher("FICHIER_PHO_WAV"));
			synthetiseur.play();
		} catch (SIVOXException e) {
			throw e;
		} catch (Exception z) {
			throw new SIVOXException("Erreur : impossible de jouer la prosodie","impossible de jouer la prosodi");
		}
	}
	
	/**
	 * Methode qui stoppe la lecture courante de la prosodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void stopperProsodie() throws SIVOXException
	{
		try {
			synthetiseur.stop();
		} catch (Exception e) {
			throw new SIVOXException("Erreur : impossible de stopper la lecture de la prosodie","impossible de stopper la lecture de la prosodi");
		}
	}
	
	
	public void sauverWav(int [] tabSelection) throws SIVOXException
	{
		try {
			LecteurTexte lt = new LecteurTexte();
			lt.setVoix(i.getVoix());
			enregistrerMorceauProsodie(tabSelection, ConfigFile.rechercher("REPERTOIRE_PHO_WAV")+"/"+ConfigFile.rechercher("FICHIER_PHO_WAV")+".pho");
			SynthetiseurMbrola synthetiseur = new SynthetiseurMbrola(lt.getVoix(),ConfigFile.rechercher("REPERTOIRE_PHO_WAV")+"/",ConfigFile.rechercher("FICHIER_PHO_WAV"));
			synthetiseur.muet();
			browser.setFilterNames(new String [] {"Fichier wav"});
			browser.setFilterExtensions(new String [] {"*.wav"});
			browser.setFileName("fichier son");
			if(tabSelection.length > 0)
			{
				browser.setText("Enregistrement de la selection au format wave");
			}
			else
			{
				browser.setText("Enregistrement de la prosodie entiere au format wave");
			}
			String chaine = browser.open();
			if(chaine != null)
			{
				if((chaine.length() > 4) && (chaine.subSequence(chaine.length()-4, chaine.length()).toString().equalsIgnoreCase(".wav")))
				{
					this.copier(ConfigFile.rechercher("REPERTOIRE_PHO_WAV")+"/"+ConfigFile.rechercher("FICHIER_PHO_WAV")+".wav", chaine);
				}
				else
				{
					this.copier(ConfigFile.rechercher("REPERTOIRE_PHO_WAV")+"/"+ConfigFile.rechercher("FICHIER_PHO_WAV")+".wav", chaine+".wav");
				}					
			}
		} catch (SIVOXException e) {
			throw e;
		} catch (Exception z) {
			throw new SIVOXException("Erreur : impossible de sauver le fichier WAVE","impossible de sauver le fichier waiiv");
		}
	}
	
	/**
	 * Methode de copie de fichier
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param s Le fichier source
	 * @param d Le fichier destination
	 */
	public void copier(String s, String d) throws SIVOXException
	{
        FileInputStream sourceFile = null;
        FileOutputStream destinationFile = null;
        try {
        		File source = new File(s);
        		File destination = new File(d);
                destination.createNewFile();
                sourceFile = new FileInputStream(source);
                destinationFile = new FileOutputStream(destination);
                byte buffer[]=new byte[512*1024];
                int nbLecture;
                
                while((nbLecture = sourceFile.read(buffer)) != -1)
                {
                	destinationFile.write(buffer, 0, nbLecture);
                }
                try {
                	sourceFile.close();
                } catch(Exception z) {}
                try {
                	destinationFile.close();
                } catch(Exception x) {}
        } catch(Exception e) {
        	try {
            	sourceFile.close();
            } catch(Exception z) {}
            try {
            	destinationFile.close();
            } catch(Exception x) {}
            throw new SIVOXException("Erreur : impossible de copier le fichier son","impossible de copiai le fichier son");
        }
	}
	
	/**
	 * Methode qui met la prosodie dans l'etat synchronise
	 * @author Ecole Ploytechnique de Sophia Antipolis
	 */
	public void setSynchro()
	{
		bouttonVerrou.setText("Prosodie synchronisee");
		isSynchro = true;
		try {
			bouttonVerrou.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"boutton_vert_16_16.png"));
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(i.getShell(), SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("les images n'ont pas pu être charge, le programme risque de ne pas fonctionner correctement !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
		}
	}
	
	/**
	 * Methode qui met la prosodie dans l'etat non synchronise
	 * @author Ecole Ploytechnique de Sophia Antipolis
	 */
	public void setNonSynchro()
	{
		bouttonVerrou.setText("Prosodie non synchronisee");
		isSynchro = false;
		try {
			bouttonVerrou.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"boutton_rouge_16_16.png"));
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(i.getShell(), SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("les images n'ont pas pu être charge, le programme risque de ne pas fonctionner correctement !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
		}
	}
	
	/**
	 * Methode qui modifie l'etat chant/import de la prosodie
	 * @author Ecole Ploytechnique de Sophia Antipolis
	 * @param value True si la prosodie est une importation ou un chant
	 */
	public void setChantOrImport(boolean value)
	{
		isChantOrImport = value;
	}
	
	/**
	 * Methode qui retourne l'etat chant/import de la prosodie
	 * @author Ecole Ploytechnique de Sophia Antipolis
	 * @return Le booleen qui indique si la prosodie est un chant ou un import
	 */
	public boolean getChantOrImport()
	{
		return(isChantOrImport);
	}
	
	/**
	 * Methode qui met le projet dans l'etat modifie en demandant a l interface generale
	 * @author Ecole Ploytechnique de Sophia Antipolis
	 */
	private void setModified()
	{
		i.setModified();
	}
	
	/**
	 * Methode qui retourne le titre de la prosodie
	 * @author Ecole Ploytechnique de Sophia Antipolis
	 * @return Le titre de la prosodie
	 */
	public String getTitre()
	{
		if(titreProsodie != null)
			return(titreProsodie);
		else
			return("");
	}
	
	/**
	 * Methode qui demande a l interface generale de mettre a jour le graphe
	 * @author Ecole Ploytechnique de Sophia Antipolis
	 */
	public void miseAJourGraphe()
	{
		String chainePhoneme = new String("");
		for(int i = 0 ; i < tableProsodie.getItemCount() ; i++)
		{
			for(int j = 0 ; j < 15 ; j++)
			{
				chainePhoneme = chainePhoneme + tableProsodie.getItem(i).getText(j) + " "; 
			}
			chainePhoneme = chainePhoneme + tableProsodie.getItem(i).getText(15) + "\n";
		}
		i.miseAJourGraphe(chainePhoneme);
	}
	
	/**
	 * Methode qui met a jour la table des prosodie a partir de donnees
	 * @param chaineProsodie La chaine de donnee a traiter
	 */
	public void miseAJourTableProsodie(String chaineProsodie)
	{
		tableProsodie.removeAll();
		String [] tableauPhoneme = chaineProsodie.split("\n");
		for(int i = 0 ; i < tableauPhoneme.length ; i++)
		{
			TableItem itemLigne = new TableItem(tableProsodie, SWT.NONE);
			if(tableauPhoneme[i].split(" ")[0].startsWith(";"))
			{
				itemLigne.setText(0, tableauPhoneme[i]);
				if(tableauPhoneme[i].split(" ")[0].startsWith(";;"))
				{
					itemLigne.setBackground(new Color(this.i.getDisplay(), 40, 202, 56));
				}
				else
				{
					itemLigne.setBackground(new Color(this.i.getDisplay(), 39, 146, 203));
				}
			}
			else
			{
				itemLigne.setText(tableauPhoneme[i].split(" "));
			}
		}
		setModified();
	}
}
