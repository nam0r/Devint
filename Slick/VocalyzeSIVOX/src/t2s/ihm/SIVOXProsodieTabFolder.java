package t2s.ihm;

import java.util.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.widgets.*;
import org.jdom.*;

import t2s.exception.SIVOXException;

/**
 * Classe SIVOXProsodieTabFolder heritant de CTabFolder et contenant les SIVOXProsodieTabItem
 * @author Ecole Ploytechnique de Sophia Antipolis
 * @version 1.0
 */

public class SIVOXProsodieTabFolder extends CTabFolder {

	private SIVOXProsodieTabFolder objetCourant = this;
	private InterfaceGenerale i = null;
	private boolean isMaximized = false;
	
	/**
	 * Constructeur par defaut de SIVOXProsodieTabFolder
	 * @author Ecole Ploytechnique de Sophia Antipolis
	 * @param i1 L'interface generale appelante
	 * @param arg0 Le composite parent de SIVOXProsodieTabFolder
	 * @param arg1 L'option de SIVOXProsodieTabFolder
	 */
	public SIVOXProsodieTabFolder(InterfaceGenerale i1, Composite arg0, int arg1) {
		super(arg0, arg1);
		super.setMaximizeVisible(true);
		i = i1;
		
		//selection d un onglet
		this.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(((SIVOXProsodieTabItem)objetCourant.getSelection()).getChantOrImport())
				{
					// enable du texte
					((SIVOXProsodieTabItem)objetCourant.getSelection()).miseAJourGraphe();
				}
				else
				{
					// disable du texte
					((SIVOXProsodieTabItem)objetCourant.getSelection()).miseAJourGraphe();
				}
			}
		});
		
		//evenement sur le boutton reduire ou agrandir
		this.addCTabFolder2Listener(new CTabFolder2Listener() {
			public void close(CTabFolderEvent e) {}
			public void maximize(CTabFolderEvent arg0) {
				if(isMaximized == false)
				{
					setMaximized(true);
					isMaximized = true;
					i.maximiserProsodie();
				}
			}
			public void minimize(CTabFolderEvent arg0) {}
			public void restore(CTabFolderEvent arg0) {
				if(isMaximized == true)
				{
					setMaximized(false);
					isMaximized = false;
					i.demaximiserProsodie();
				}
			}
			public void showList(CTabFolderEvent arg0) {}
		});
	}

	/**
	 * Methode qui ajoute une prosodie au SIVOXProsodieTabItem et la rend active
	 * @author Ecole Ploytechnique de Sophia Antipolis
	 */
	public void ajouterProsodie()
	{
		SIVOXProsodieTabItem p = new SIVOXProsodieTabItem(i, this, SWT.NONE, "nouvelle prosodie");
		i.setModified();
		this.setSelection(p);
	}
	
	/**
	 * Methode qui importe une prosodie en creant une nouvelle prosodie acceuillante
	 * @author Ecole Ploytechnique de Sophia Antipolis
	 * @param fichier Le fichier de la prosodie a importer
	 * @throws SIVOXException
	 */
	public void importerProsodie(String fichier) throws SIVOXException
	{
		try {
			SIVOXProsodieTabItem p = new SIVOXProsodieTabItem(i, this, SWT.NONE, "nouvelle prosodie");
			p.importerProsodie(fichier);
			this.setSelection(p);
			i.setModified();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	
	/**
	 * Methode qui demande au SIVOXProsodieTabItem d'enregistrer la prosodie
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void enregistrerProsodie() throws SIVOXException
	{
		try {
			FileDialog browser = new FileDialog(i.getShell(), SWT.SAVE);
			browser.setText("sauvegarde de la prosodie...");
			browser.setFilterNames(new String [] {"Fichier de prosodie (*.pho)"});
			browser.setFilterExtensions(new String [] {"*.pho"});
			String chaine = browser.open();
			if(chaine != null)
			{
				if((chaine.length() > 4) && (chaine.subSequence(chaine.length()-4, chaine.length()).toString().equalsIgnoreCase(".pho")))
				{
					((SIVOXProsodieTabItem)this.getSelection()).enregistrerMorceauProsodie(new int[0], chaine);
				}
				else
				{
					((SIVOXProsodieTabItem)this.getSelection()).enregistrerMorceauProsodie(new int[0], chaine+".pho");
				}
			}
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	
	/**
	 * Methode qui supprime le SIVOXProsodieTabItem courant
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void supprimerProsodie() throws SIVOXException
	{
		if(this.getItemCount()>0)
		{
			MessageBox message = new MessageBox(i.getShell(), SWT.OK | SWT.CANCEL | SWT.ICON_WARNING);
			message.setText("Suppression");
			message.setMessage("Voulez-vous supprimer la prosodie "+ ((SIVOXProsodieTabItem)this.getSelection()).getTitre() + "?");
			if(message.open() == SWT.OK)
			{
				((SIVOXProsodieTabItem)this.getSelection()).dispose();
				i.setModified();
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de prosodie a supprimer","il nia pa de prosodi a supprimer");
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabItem courant de renommer la prosodie
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void renommerProsodie() throws SIVOXException
	{
		if(this.getItemCount()>0)
		{
			((SIVOXProsodieTabItem)this.getSelection()).renommerProsodie();
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de prosodie a renommer","il nia pa de prosodi a renommer");
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabItem courant d'inserer une frequence
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void insererFrequence() throws SIVOXException
	{
		if(this.getSelection() != null)
		{
			try {
				((SIVOXProsodieTabItem)this.getSelection()).inserer(0);
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : Aucune prosodie n'est selecetionnee","aucune prosodi nai sailectionai");
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabItem courant d'inserer une rapidite
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void insererRapidite() throws SIVOXException
	{
		if(this.getSelection() != null)
		{
			try {
				((SIVOXProsodieTabItem)this.getSelection()).inserer(1);
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : Aucune prosodie n'est selecetionnee","aucune prosodi nai sailectionai");
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabItem courant d'inserer un commentaire
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void insererCommentaire() throws SIVOXException
	{
		if(this.getSelection() != null)
		{
			try {
				((SIVOXProsodieTabItem)this.getSelection()).inserer(2);
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : Aucune prosodie n'est selecetionnee","aucune prosodi nai sailectionai");
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabItem courant de supprimer une ligne frequence/rapidite/commentaire
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void supprimerLigne() throws SIVOXException
	{
		if(this.getSelection() != null)
		{
			try {
				((SIVOXProsodieTabItem)this.getSelection()).supprimer();
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : Aucune prosodie n'est selecetionnee","aucune prosodi nai sailectionai");
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabItem d'importer un chant
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @param fichier
	 * @throws SIVOXException
	 */
	public void importerChant(String fichier) throws SIVOXException
	{
		try {
			SIVOXProsodieTabItem p = new SIVOXProsodieTabItem(i, this, SWT.NONE, "nouvelle prosodie");
			p.importerChant(fichier);
			this.setSelection(p);
			i.setModified();
		} catch(SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui charge toutes les prosodies a partir d'un noeud XML
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @param listeProsodie Le noeud XML de la liste des prosodies
	 * @throws SIVOXException
	 */
	public void loadXML(Element listeProsodie) throws SIVOXException
	{
		try {
			java.util.List liste = (java.util.List)listeProsodie.getChildren("Prosodie");
			Iterator j = liste.iterator();
			if(j.hasNext())
			{
				while(j.hasNext())
				{
					SIVOXProsodieTabItem t = new SIVOXProsodieTabItem(i, this, SWT.NONE, "nouvelle prosodie");
					t.loadXML((Element)j.next());
				}
				this.setSelection(0);
				((SIVOXProsodieTabItem)this.getSelection()).miseAJourGraphe();
			}
		} catch (SIVOXException e) {
			throw e;
		} catch (Exception z) {
			throw new SIVOXException("Erreur XML : erreur de chargement , le fichier est corrompu","impossible de charger le fichiai");
		}
	}
	
	/**
	 * Methode qui va sauver l'ensemble des prosodies (SIVOXProsodieTabItem)
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @param listeProsodie Le noeud XML ou l'on sauve les prosodies
	 * @throws SIVOXException
	 */
	public void saveXML(Element listeProsodie) throws SIVOXException
	{
		try {
			for(int i = 0 ; i < this.getItemCount() ; i++)
			{
				((SIVOXProsodieTabItem)this.getItem(i)).saveXML(listeProsodie);
			}
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabItem courant de se mettre a jour
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @param text Le texte qu'il faut mettre a jour dans la prosodie
	 * @throws SIVOXException
	 */
	public void miseAJourProsodie(String text) throws SIVOXException
	{
		if((this.getItemCount() > 0) && (this.getSelection() != null))
		{
			((SIVOXProsodieTabItem)this.getSelection()).miseAJourProsodie(text);
		}
		else
		{
			i.setIconInformationWarning();
			i.setInformation("Mise a jour : Aucune prosodie a mettre a jour !");
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabItem courant de jouer la prosodie (ou une selection)
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void jouerProsodie(int [] tabSelection) throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXProsodieTabItem)this.getSelection()).jouerProsodie(tabSelection);
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de prosodie a jouer","il nia pa de prosodi a jouer");
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabItem courant de stopper la prosodie
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void stopperProsodie() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXProsodieTabItem)this.getSelection()).stopperProsodie();
			} catch(SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de prosodie a stopper","il nia pa de prosodi a stoper");
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabItem de sauver le fichier son wave
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void sauverWav(int [] tabSelection) throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXProsodieTabItem)this.getSelection()).sauverWav(tabSelection);
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de prosodie pour enregistrer un son son waive","impossible de sauver un fichier waiiv");
		}
	}
	
	/**
	 * Methode qui rend toutes les SIVOXProsodieTabItem non synchronise
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void setAllDesynchro() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			for(int i = 0 ; i < this.getItemCount() ; i++)
			{
				((SIVOXProsodieTabItem)this.getItem(i)).setNonSynchro();
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de prosodie a rendre desynchro","impossible de daisincronizai");
		}
	}
	
	/**
	 * Methode qui indique si un SIVOXProsodieTabItem est un import ou un chant
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @return True si c'est un chant ou import et false sinon
	 * @throws SIVOXException
	 */
	public boolean getChantOrImport() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			return(((SIVOXProsodieTabItem)this.getSelection()).getChantOrImport());
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de prosodie","impossible de savoir la nature de la prosodi");
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabItem courant de mettre a jour le graphe
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void miseAJourGraphe() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
				((SIVOXProsodieTabItem)this.getSelection()).miseAJourGraphe();
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de prosodie","impossible de mettre a jour le graphe");
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabItem courant de mettre a jour son tableau de prosodie
	 * @author EcolePolytechnique de Sophia Antipolis
	 * @param chaineProsodie La nouvelle chaine a afficher dans le tableau de prosodie
	 * @throws SIVOXException
	 */
	public void miseAJourTableauProsodie(String chaineProsodie) throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
				((SIVOXProsodieTabItem)this.getSelection()).miseAJourTableProsodie(chaineProsodie);
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de prosodie","impossible de mettre a jour le tablo de prosodi");
		}
	}
}
