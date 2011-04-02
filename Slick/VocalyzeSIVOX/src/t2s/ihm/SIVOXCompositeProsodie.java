package t2s.ihm;

import org.eclipse.swt.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import org.jdom.*;
import t2s.exception.*;

/**
 * Classe SIVOXCOmpositeProsodie heritant de Composite
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class SIVOXCompositeProsodie extends Composite {

	private SIVOXProsodieTabFolder folder = null;
	private FormData folderData = null;	
	private InterfaceGenerale i = null;
	
	/**
	 * Constructeur par defaut de SIVOXCompositeProsodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param i1 L'interface generale appelante
	 * @param arg0 Le composite parent de SIVOXCompositePreosodie
	 * @param arg1 L'option de SIVOXCompositeProsodie
	 */
	public SIVOXCompositeProsodie(InterfaceGenerale i1, Composite arg0, int arg1) {
		super(arg0, arg1);
		i = i1;
		this.setLayout(new FormLayout());
		
		// tabfolder des prosodie
		folder = new SIVOXProsodieTabFolder(i, this, SWT.BORDER); 
		folder.setSimple(false);
		folder.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		folderData = new FormData();
		folderData.left = new FormAttachment(0);
		folderData.right = new FormAttachment(100);
		folderData.top = new FormAttachment(0);
		folderData.bottom = new FormAttachment(100);
		folder.setLayoutData(folderData);		
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder de mettre a jour le SIVOXProsodieTabItem courante
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param text Le texte a prendre en compte pour la mise a jour de la prosodie
	 * @throws SIVOXException
	 */
	public void miseAJourProsodie(String text) throws SIVOXException
	{
		try {
			folder.miseAJourProsodie(text);
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder d'importer une prosodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param fichier Le nom du fichier de prosodie a importer
	 */
	public void importerProsodie(String fichier) throws SIVOXException
	{
		try {
			folder.importerProsodie(fichier);
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder d'importer un chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param fichier Le fichier de chant a importer
	 * @throws SIVOXException
	 */
	public void importerChant(String fichier) throws SIVOXException
	{
		try {
			folder.importerChant(fichier);
		} catch(SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder de jouer la prosodie ( ou une selection)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void jouerProsodie(int [] tabSelection) throws SIVOXException
	{
		try {
			folder.jouerProsodie(tabSelection);
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder de stopper la lecture de la prosodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void stopperProsodie() throws SIVOXException
	{
		try {
			folder.stopperProsodie();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder de sauver le fichier son
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void sauverWav(int [] tabSelection) throws SIVOXException
	{
		try {
			folder.sauverWav(tabSelection);
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFiolder de charger un projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param listeProsodie Le noeud XML des prosodies
	 * @throws SIVOXException
	 */
	public void loadXML(Element listeProsodie) throws SIVOXException
	{
		try {
			folder.loadXML(listeProsodie);
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder de sauver le projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param listeProsodie Le noeud XML des prosodies
	 * @throws SIVOXException
	 */
	public void saveXML(Element listeProsodie) throws SIVOXException
	{
		folder.saveXML(listeProsodie);
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder de desynchroniser les prosodies
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void setAllDesynchro() throws SIVOXException
	{
		try {
			folder.setAllDesynchro();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui retourne la nature de la prosodie courante de SIVOXProsodieTabFolder
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si la prosodie courante est un chant / import ou false sinon
	 * @throws SIVOXException
	 */
	public boolean getChantOrImport() throws SIVOXException
	{
		try {
			return(folder.getChantOrImport());
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui ajoute une prosodie au tableau de prosodie SIVOXProsodieTabFolder
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void ajouterprosodie()
	{
		folder.ajouterProsodie();
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder de renommer la prosodie courante
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void renommerProsodie() throws SIVOXException
	{
		try {
			folder.renommerProsodie();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder de supprimer la prosodie courante
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void supprimerProsodie() throws SIVOXException
	{
		try {
			folder.supprimerProsodie();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder d'inserer une frequence dans la prosodie courante
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void insererFrequence() throws SIVOXException
	{
		try {
			folder.insererFrequence();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXPrsodieTabFolder d'inserer une rapidite dans la prosodie courante
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void insererRapidite() throws SIVOXException
	{
		try {
			folder.insererRapidite();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder d'inserer un commentaire
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void insererCommentaire() throws SIVOXException
	{
		try {
			folder.insererCommentaire();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder d'enregistrer la prosodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void enregistrerProsodie() throws SIVOXException
	{
		try {
			folder.enregistrerProsodie();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder de supprimer une insertion (f/r/c)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void supprimerInsertion() throws SIVOXException
	{
		try {
			folder.supprimerLigne();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder de mettre a jour le graphe
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws SIVOXException
	 */
	public void miseAJourGraphe() throws SIVOXException
	{
		try {
			folder.miseAJourGraphe();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui demande au SIVOXProsodieTabFolder de mettre a jour la prosodie courante
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param chaineProsodie La chaine utilisee pour la mise a jour de la prosodie
	 * @throws SIVOXException
	 */
	public void miseAJourTableauProsodie(String chaineProsodie) throws SIVOXException
	{
		try {
			folder.miseAJourTableauProsodie(chaineProsodie);
		} catch (SIVOXException e) {
			throw e;
		}
	}
}
