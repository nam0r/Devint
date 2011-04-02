package t2s.ihm;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.widgets.*;
import t2s.exception.*;

/**
 * Classe SIVOXSingerTabItem representant le folder des chants
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class SIVOXSingerTabFolder extends CTabFolder {

	private InterfaceSingerGenerale i = null;
	private SIVOXSingerTabFolder objetCourant = null;
	
	/**
	 * Constructeur par defaut de SIVOXSingerTabFolder
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param arg0 Le composite parent
	 * @param arg1 L'option de SIVOXSingerTabFolder
	 * @param i1 L'interfaceSingerGenerale appelante
	 */
	public SIVOXSingerTabFolder(Composite arg0, int arg1, InterfaceSingerGenerale i1) {
		super(arg0, arg1);
		super.setSimple(false);
		objetCourant = this;
		//affectation de l interface singer generale
		i = i1;
		
		//ecouteur sur la fermeture de chant
		this.addCTabFolder2Listener(new CTabFolder2Listener() {
			public void close(CTabFolderEvent e) {
				e.doit = false;
				try {
					if(((SIVOXSingerTabItem)(e.item)) == (SIVOXSingerTabItem)objetCourant.getSelection())
					{
						fermerChant();
					}
					else
					{
						objetCourant.setSelection((CTabItem)e.item);
					}
				} catch (SIVOXException r) {
					//nothing
				}
			}
			public void maximize(CTabFolderEvent arg0) {}
			public void minimize(CTabFolderEvent arg0) {}
			public void restore(CTabFolderEvent arg0) {}
			public void showList(CTabFolderEvent arg0) {}
		});
		
	}
	
	/**
	 * Methode qui ajoute un nouveau chant au folder des chants
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void ajouterChant()
	{
		SIVOXSingerTabItem c = new SIVOXSingerTabItem(this, SWT.BORDER, i);
		this.setSelection(c);
	}
	
	/**
	 * Methode qui ouvre un chant deja existant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void ouvrirChant() throws SIVOXException
	{
		FileDialog browser = new FileDialog(i.getShell(), SWT.OPEN);
		browser.setText("Ouvrir un chant");
		browser.setFileName("chant");
		browser.setFilterNames(new String [] {"Fichier Chant"});
		browser.setFilterExtensions(new String [] {"*.svc"});
		String chaine = browser.open();
		if(chaine != null)
		{
			ajouterChant();
			try {
				((SIVOXSingerTabItem)(this.getSelection())).ouvrirChant(chaine);
			} catch (SIVOXException e) {
				throw e;
			}
		}
	}
	
	/**
	 * Methode qui ferme le chant courant du folder des chants
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void fermerChant() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXSingerTabItem)(this.getSelection())).fermer();
		}
		else
		{
			throw new SIVOXException("Erreur : Aucun chant a fermer","il nia pa de chan a fermai");
		}
	}
	
	/**
	 * Methode qui ferme tous les chants du folder des chants
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void fermerAllChant() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			SIVOXSingerTabItem [] tableauChant = new SIVOXSingerTabItem[this.getItemCount()];
			for(int i = 0 ; i < this.getItemCount() ; i++)
			{
				tableauChant[i] = (SIVOXSingerTabItem)this.getItem(i);
			}
			for(int i = 0 ; i < tableauChant.length ; i++)
			{
				tableauChant[i].fermer();
			}
		}
		else
		{
			throw new SIVOXException("Erreur : Aucun chant fermer","il nia pa de chan a fermai");
		}
	}
	
	/**
	 * Methode qui synchronise le chant courant (texte en syllabe <-> melodie)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void synchroniser() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXSingerTabItem)(this.getSelection())).synchroniser();
		}
		else
		{
			throw new SIVOXException("Erreur : Aucun chant a synchroniser","il nia pa de chan a sincronizai");
		}
	}
	
	/**
	 * Methode qui chante le chant courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws ChantException
	 * @throws NoteException
	 */
	public void jouer() throws ChantException , NoteException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXSingerTabItem)(this.getSelection())).jouer();
			} catch (ChantException e) {
				throw e;
			} catch (NoteException r) {
				throw r;
			}
		}
		else
		{
			throw new ChantException("Erreur : aucun chant a jouer","il nia pa de chan a jouer");
		}
	}
	
	/**
	 * Methode qui stoppe la lecture du chant courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws ChantException
	 */
	public void stopper() throws ChantException
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXSingerTabItem)(this.getSelection())).stopper();
		}
		else
		{
			throw new ChantException("Erreur : aucune lecture de chant a stopper","il nia pa de lecture de chan a stopper");
		}
	}
	
	/**
	 * Methode qui enregistre/enregistre sous le chant courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param force True si on enregistre sous et false si on enregistre
	 * @throws ChantException
	 * @throws SIVOXException
	 */
	public void enregistrer(boolean force) throws ChantException , SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			try {
				((SIVOXSingerTabItem)(this.getSelection())).enregistrer(force);
			} catch (ChantException c) {
				throw c;
			}
		}
		else
		{
			throw new SIVOXException("Erreur : aucun chant a enregistrer","il nia pa de chan a enregistrer");
		}
	}
	
	/**
	 * Methode qui enregistre tous les chants
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void enregistrerTout() throws SIVOXException
	{
		if(this.getItemCount() > 0)
		{
			for(int i = 0 ; i < this.getItemCount() ; i++)
			{
				((SIVOXSingerTabItem)this.getItem(i)).enregistrer(false);
			}
		}
		else
		{
			throw new SIVOXException("Erreur : aucun chant a enregistrer","il nia pa de chan a enregistrer");
		}
	}
	
	/**
	 * Methode qui met le chant courant dans l'etat non a jour
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setNonAJour()
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXSingerTabItem)(this.getSelection())).setNonAJour();
		}
	}
	
	/**
	 * Methode qui met le chant courant dans l'etat a jour
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setAJour()
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXSingerTabItem)(this.getSelection())).setAJour();
		}
	}
	
	/**
	 * Methode qui maximise la partie melodie du chant courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserMelodie()
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXSingerTabItem)(this.getSelection())).maximiserMelodie();
		}
	}
	
	/**
	 * Methode qui maximise la partie texte-syllabe du chant courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserSyllabe()
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXSingerTabItem)(this.getSelection())).maximiserSyllabe();
		}
	}
	
	/**
	 * Methode qui restaure une des maximisation effectuee precedement
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void restaurerMaximisation()
	{
		if(this.getItemCount() > 0)
		{
			((SIVOXSingerTabItem)(this.getSelection())).restaurerPosition();
		}
	}
}
