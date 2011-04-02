package t2s.ihm;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.widgets.*;

import t2s.exception.SIVOXException;

/**
 * Classe SIVOXTabFolder heritant de CTabFolder contenant l'enbsemble des projet (SIVOXTabItem)
 * @author Ecole Polytechnique de Sophia Antipolis
 *
 */
public class SIVOXTabFolder extends CTabFolder {

	private SIVOXTabFolder objetCourant = null;
	private InterfaceGenerale i = null;
	
	/**
	 * Constructeur par defaut de SIVOXTabFolder
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param i1 L'interface generale appelante
	 * @param arg0 Le composite parent de SIVOXTabFolder
	 * @param arg1 L'option de SIVOXTabFolder
	 */
	public SIVOXTabFolder(InterfaceGenerale i1, Composite arg0, int arg1) {
		super(arg0, arg1);
		i=i1;
		objetCourant = this;
		
		this.addCTabFolder2Listener(new CTabFolder2Listener() {
			public void close(CTabFolderEvent e) {
				e.doit = false;
				if(((SIVOXTabItem)e.item) == ((SIVOXTabItem)objetCourant.getSelection()))
				{
					fermerProjet();
				}
				else
				{
					objetCourant.setSelection((CTabItem)e.item);
				}
			}
			public void maximize(CTabFolderEvent arg0) {}
			public void minimize(CTabFolderEvent arg0) {}
			public void restore(CTabFolderEvent arg0) {}
			public void showList(CTabFolderEvent arg0) {}
		});
	}
	
	/**
	 * Methode qui ferme tous les projets ouvert
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void fermerAllProjets()
	{
		int nbProjetOpen = this.getItemCount();
		if(nbProjetOpen > 0)
		{
			SIVOXTabItem [] tableauProjet = new SIVOXTabItem[nbProjetOpen];
			for(int j = 0 ; j < nbProjetOpen ; j++)
			{
				tableauProjet[j] = (SIVOXTabItem)this.getItem(j);
			}
			for(int j = 0 ; j < nbProjetOpen ; j++)
			{
				if(tableauProjet[j].getModified())
				{
					MessageBox message = new MessageBox(i.getShell(), SWT.OK | SWT.CANCEL | SWT.ICON_WARNING);
					message.setText("Attention");
					message.setMessage("Le projet \" "+tableauProjet[j].getNomProjet()+" \" a ete modifie, voulez-vous le fermer sans l'enregistrer ?");
					if(message.open() == SWT.OK)
					{
						tableauProjet[j].dispose();
					}
				}
				else
				{
					tableauProjet[j].dispose();
				}
			}
		}
	}
	
	/**
	 * Methode qui ferme le projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void fermerProjet()
	{
		if(this.getItemCount() > 0)
		{
			SIVOXTabItem target = ((SIVOXTabItem)this.getSelection());
			if(target.getModified())
			{
				MessageBox message = new MessageBox(i.getShell(), SWT.OK | SWT.CANCEL | SWT.ICON_WARNING);
				message.setText("Attention");
				message.setMessage("Le projet \" "+target.getNomProjet()+" \" a ete modifie, voulez-vous le fermer sans l'enregistrer ?");
				if(message.open() == SWT.OK)
				{
					target.dispose();
				}
			}
			else
			{
				target.dispose();
			}
		}
	}
	
	/**
	 * Methode qui demande au projet courant d'importer du texte
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param chaine Le nom du fichier texte a importer
	 * @throws SIVOXException
	 */
	public void importerText(String chaine) throws SIVOXException
	{
		if(((SIVOXTabItem)this.getSelection()) != null)
		{
			try {
				((SIVOXTabItem)this.getSelection()).importerText(chaine);
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouver");
		}
	}
	
	/**
	 * Methode qui demande au projet courant d'importer une prosodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param fichier Le fichier de la prosodie a importer
	 * @throws SIVOXException
	 */
	public void importerProsodie(String fichier) throws SIVOXException
	{
		if(((SIVOXTabItem)this.getSelection()) != null)
		{
			try {
				((SIVOXTabItem)this.getSelection()).importerProsodie(fichier);
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouver");
		}
	}
	
	/**
	 * Methode qui demande au projet courant d'importer un chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param fichier Le fichier de chant (.SVC) a importer
	 * @throws SIVOXException
	 */
	public void importerChant(String fichier) throws SIVOXException
	{
		if(((SIVOXTabItem)this.getSelection()) != null)
		{
			try {
				((SIVOXTabItem)this.getSelection()).importerChant(fichier);
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouver");
		}
	}
	
	/**
	 * Methode qui ajoute un nouveau projet sivox (nouveau SIVOXTabItem)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param i L'interface generale appelante
	 * @param nom Le nom du nouveau projet
	 * @param auteur Le nom d'auteur du nouveau projet
	 * @param commentaire Le commentaire du nouveau projet
	 * @param nomFichier Le nom du fichier du nouveau projet
	 */
	public void ajouterProjet(InterfaceGenerale i, String nom, String auteur, String commentaire, String nomFichier)
	{
		SIVOXTabItem n = null;
		n = new SIVOXTabItem(i, this, SWT.NONE, nom, auteur, commentaire, nomFichier);
		this.setSelection(n);
	}
	
	
	public void ouvrirProjet() throws SIVOXException
	{
		try {
			i.setIconInformationInfo();
			i.setInformation("Ouverture de projet SIVOX");
			FileDialog browser = new FileDialog(i.getShell(), SWT.OPEN);
			browser.setText("Ouvrir un projet Si-Vox");
			browser.setFileName("projetSIVOX");
			browser.setFilterNames(new String [] {"Fichier SIVOX"});
			browser.setFilterExtensions(new String [] {"*.siv"});
			String chaine = browser.open();
			if(chaine != null)
			{
				this.ajouterProjet(i, "", "", "", "");
				((SIVOXTabItem)this.getSelection()).ouvrirProjet(browser.getFilterPath(), browser.getFileName());
			}
			else
			{
				throw new SIVOXException("Annulation d'ouverture","annulation");
			}
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui enregistre le projet courant (ou enregistre sous...)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param force Le booleen qui force enregistrer sous (si true)
	 * @throws SIVOXException
	 */
	public void sauverProjet(boolean force) throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXTabItem)this.getSelection()).sauverProjet(force);
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet a enregistrer","il nia pa de projet a enregistrai");
		}
	}
	
	
	/**
	 * Methode qui enregistre tous les projets
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void sauverAllProjets() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				int nbProjetOpen = this.getItemCount();
				for(int j = 0 ; j < nbProjetOpen ; j++)
				{
					this.setSelection(j);
					((SIVOXTabItem)this.getSelection()).sauverProjet(false);
				}
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet a enregistrer","il nia pa de projet a enregistrai");
		}
	}
	
	/**
	 * Methode qui synchronise la prosodie courante du projet courant en demandant au SIVOXTabItem courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void synchroniserProsodie() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXTabItem)this.getSelection()).synchroniserProsodie();
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouvert");
		}
	}
	
	/**
	 * Methode qui joue la prosodie courante du projet ocurant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void jouerProsodie() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXTabItem)this.getSelection()).jouerProsodie();
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouvert");
		}
	}
	
	/**
	 * Methode qui stoppe la lecture de la prosodie courante du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void stopperProsodie() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXTabItem)this.getSelection()).stopperProsodie();
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
		throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouvert");
		}
	}
	
	/**
	 * Methode qui demande a la prosodie courante du projet courant de sauver le fichier wave (son)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void sauverWav() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXTabItem)this.getSelection()).sauverWav();
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouvert");
		}
	}
	
	/**
	 * Methode qui desynchronise toutes les prosodies du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void setAllDesynchro() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXTabItem)this.getSelection()).setAllDesynchro();
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouver");
		}
	}
	
	/**
	 * Methode qui ajoute une prosodie vierge au projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void ajouterProsodie() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXTabItem)this.getSelection()).ajouterProsodie();
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouvert");
		}
	}
	
	/**
	 * Methode qui renomme la prosodie courante du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void renommerProsodie() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXTabItem)this.getSelection()).renommerProsodie();
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouvert");
		}
	}
	
	/**
	 * Methode qui supprime la prosodie courante du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void supprimerProsodie() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXTabItem)this.getSelection()).supprimerProsodie();
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouvert");
		}
	}
	
	/**
	 * Methode qui insrere une frequence dans la prosodie courante du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void insererFrequence() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXTabItem)this.getSelection()).insererFrequence();
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouvert");
		}
	}
	
	/**
	 * Methode qui insere une rapidite dans la prosodie courante du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void insererRapidite() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXTabItem)this.getSelection()).insererRapidite();
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouvert");
		}
	}
	
	/**
	 * Methode qui insere un commentaire dans la prosodie courante du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void insererCommentaire() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXTabItem)this.getSelection()).insererCommentaire();
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouvert");
		}
	}
	
	/**
	 * Methode qui demande a la prosodie courante du projet courant de s'enregistrer
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void enregistrerProsodie() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXTabItem)this.getSelection()).enregistrerProsodie();
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouvert");
		}
	}
	
	/**
	 * Methode qui supprimer une insertion de frequence/rapidite/commentaire dans la prosodie courante du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void supprimerInsertion() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXTabItem)this.getSelection()).supprimerInsertion();
			} catch (SIVOXException e) {
				throw e;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : il n'y a pas de projet ouvert","il nia pa de projet ouvert");
		}
	}
	
	/**
	 * Methode qui met le projet courant dans l'etat modifie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setModified()
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXTabItem)this.getSelection()).setModified();
		}
	}
	
	/**
	 * Methode qui met le projet courant dans l'etat update/a jour
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setUpdate()
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXTabItem)this.getSelection()).setUpdate();
		}
	}
	
	/**
	 * Methode qui change le nom du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param nom Le nouveau nom de projet
	 */
	public void setNomProjet(String nom)
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXTabItem)this.getSelection()).setNomProjet(nom);
		}
	}
	
	/**
	 * Methode qui maximise la partie proprietes du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserPropriete()
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXTabItem)this.getSelection()).maximiserPropriete();
		}
	}
	
	/**
	 * Methode qui demaximise la partie proprietes du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void demaximiserPropriete()
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXTabItem)this.getSelection()).restorePosition();
		}
	}
	
	/**
	 * Methode qui maximise la partie texte du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 *
	 */
	public void maximiserTexte()
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXTabItem)this.getSelection()).maximiserTexte();
		}
	}
	
	/**
	 * Methode qui demaximise la partie texte du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void demaximiserText()
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXTabItem)this.getSelection()).restorePosition();
		}
	}
	
	/**
	 * Methode qui maximise la zone prosodie du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserProsodie()
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXTabItem)this.getSelection()).maximiserProsodie();
		}
	}
	
	/**
	 * Methode qui demaximise la zone prosodie du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void demaximiserProsodie()
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXTabItem)this.getSelection()).restorePosition();
		}
	}
	
	/**
	 * Methode qui maximise la partie graphe du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserGraphe()
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXTabItem)this.getSelection()).maximiserGraphe();
		}
	}
	
	/**
	 * Methode qui demaximise la partie graphe du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void demaximiserGraphe()
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXTabItem)this.getSelection()).restorePosition();
		}
	}
	
	/**
	 * Methode qui met a jour le graphe du projet courant avec la donnees chainePhoneme
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param chainePhoneme Le donnee de mise a jour du graphe
	 */
	public void miseAJourGraphe(String chainePhoneme)
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXTabItem)this.getSelection()).miseAJourGraphe(chainePhoneme);
		}
	}
	
	/**
	 * Methode qui met a jour le tableau de la prosodie courante du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param chaineProsodie
	 * @throws SIVOXException
	 */
	public void miseAJourTableauProsodie(String chaineProsodie) throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXTabItem)this.getSelection()).miseAJourTableauProsodie(chaineProsodie);
			} catch (SIVOXException e) {
				throw e;
			}
		}
	}
}
