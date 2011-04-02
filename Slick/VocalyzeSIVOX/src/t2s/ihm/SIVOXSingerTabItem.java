package t2s.ihm;

import java.io.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import t2s.chant.*;
import t2s.exception.*;

/**
 * Classe SIVOXSingerTabItem representant un projet de chant
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */
public class SIVOXSingerTabItem extends CTabItem {
	
	private InterfaceSingerGenerale i = null;
	private String cheminFichierEntier = "";
	private String nomFichier = "Nouveau Chant";
	private boolean isSaveOnce = false;
	private Composite compositeChant = null;
	private SIVOXSingerTextSyllabe textSyllabe = null;
	private FormData textSyllabeData = null;
	private SIVOXSingerMelodie melodie = null;
	private FormData melodieData = null;
	private boolean isModified = false;
	
	private Sash sashHorizontal = null;
	int positionSashHorizontal = 0;
	
	/**
	 * Constructeur par defaut de SIVOXSingerTabItem
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param arg0 Le composite parent
	 * @param arg1 L'option de SIVOXSingerTabItem
	 * @param i1 L'interfaceSingerGenerale appelante
	 */
	public SIVOXSingerTabItem(CTabFolder arg0, int arg1, InterfaceSingerGenerale i1) {
		super(arg0, arg1);
		super.setText(nomFichier);
		i = i1;
		compositeChant = new Composite(arg0, SWT.NONE);
		compositeChant.setLayout(new FormLayout());
		this.setControl(compositeChant);
		
		final Sash sashHorizontal = new Sash(compositeChant, SWT.HORIZONTAL);
		FormData sashHorizontalData = new FormData();
		sashHorizontalData.left = new FormAttachment(0);
		sashHorizontalData.right = new FormAttachment(100);
		sashHorizontalData.top = new FormAttachment(20);
		sashHorizontal.setLayoutData(sashHorizontalData);
		this.sashHorizontal = sashHorizontal;
		
		//creation du texte de syllabes
		textSyllabe = new SIVOXSingerTextSyllabe(compositeChant, SWT.BORDER, i1);
		textSyllabeData = new FormData();
		textSyllabeData.left = new FormAttachment(0);
		textSyllabeData.right = new FormAttachment(100);
		textSyllabeData.top = new FormAttachment(0);
		textSyllabeData.bottom = new FormAttachment(sashHorizontal);
		textSyllabe.setLayoutData(textSyllabeData);
		
		//creation de la partie melodie
		melodie = new SIVOXSingerMelodie(compositeChant, SWT.BORDER, i1);
		melodieData = new FormData();
		melodieData.left = new FormAttachment(0);
		melodieData.right = new FormAttachment(100);
		melodieData.top = new FormAttachment(sashHorizontal);
		melodieData.bottom = new FormAttachment(100);
		melodie.setLayoutData(melodieData);
		
		//mouvement du sash horizontal
		sashHorizontal.addSelectionListener(new SelectionAdapter()
		{
			public void widgetSelected(SelectionEvent event)
			{
				((FormData) sashHorizontal.getLayoutData()).top = new FormAttachment(0, event.y);
				sashHorizontal.getParent().layout();
			}
		});
	}
	
	/**
	 * Methode qui ferme le chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void fermer()
	{
		if(isModified == true)
		{
			MessageBox message = new MessageBox(i.getShell(), SWT.OK | SWT.CANCEL | SWT.ICON_WARNING);
			message.setText("Attention");
			message.setMessage("Le projet a ete modifie, voulez-vous quand meme le fermer ?");
			if(message.open() == SWT.CANCEL)
			{
				return;
			}
		}
		this.dispose();
	}
	
	/**
	 * Methode qui sauvegarde la position horizontale du sash du chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void sauvegarderPosition()
	{
		positionSashHorizontal = sashHorizontal.getBounds().y;
	}
	
	/**
	 * Methode qui restaure la position du sash horizontal du chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void restaurerPosition()
	{
		((FormData) sashHorizontal.getLayoutData()).top = new FormAttachment(0,positionSashHorizontal);
		((FormData) sashHorizontal.getLayoutData()).right = new FormAttachment(100);
		sashHorizontal.getParent().layout();
	}
	
	/**
	 * Methode qui maximise la partie melodie du chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserMelodie()
	{
		sauvegarderPosition();
		((FormData)sashHorizontal.getLayoutData()).top = new FormAttachment(0);
		((FormData)sashHorizontal.getLayoutData()).right = new FormAttachment(0);
		sashHorizontal.getParent().layout();
	}
	
	/**
	 * Methode qui maximise la partie texte-syllabe du chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserSyllabe()
	{
		sauvegarderPosition();
		((FormData)sashHorizontal.getLayoutData()).top = new FormAttachment(100);
		((FormData)sashHorizontal.getLayoutData()).right = new FormAttachment(0);
		sashHorizontal.getParent().layout();
	}
	
	/**
	 * Methode qui synchronise le texte avec la melodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void synchroniser()
	{
		melodie.synchroniser(textSyllabe.getText());
	}
	
	/**
	 * Methode qui ouvre un chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param fichier Le fichier de chant a ouvrir
	 * @throws SIVOXException
	 */
	public void ouvrirChant(String fichier) throws SIVOXException
	{
		try {
			File fic = new File(fichier);
			cheminFichierEntier = fichier;
			nomFichier = fic.getName();
			this.setText(nomFichier);
			isSaveOnce = true;
			Chant c = new Chant(fichier);
			melodie.setTempo(c.getTempo());
			textSyllabe.setText(c.texteToString());
			melodie.setMelodie(c.melodieToString());
			synchroniser();
			i.setAJour();
		} catch (SIVOXException e) {
			throw e;
		}
	}
	
	/**
	 * Methode qui enregistre le chant avec une synchronisation preventive
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param force True si on enregistre sous et false si on enregistre
	 * @throws ChantException
	 */
	public void enregistrer(boolean force) throws ChantException
	{
		if((force == true) || (isSaveOnce == false))
		{
			FileDialog browser = new FileDialog(i.getShell(), SWT.SAVE);
			browser.setText("Enregistrer un chant");
			browser.setFileName("chant");
			browser.setFilterNames(new String [] {"Fichier Chant"});
			browser.setFilterExtensions(new String [] {"*.svc"});
			String chaine = browser.open();
			if(chaine != null)
			{
				File f = new File(chaine);
				if(f.exists())
				{
					MessageBox message = new MessageBox(i.getShell(), SWT.OK | SWT.CANCEL | SWT.ICON_WARNING);
					message.setText("Attention");
					message.setMessage("Le projet existe deja, voulez-vous l'ecraser ?");
					if(message.open() == SWT.CANCEL)
					{
						return;
					}
				}
				synchroniser();
				Chant c = new Chant();
				c.setTempo(melodie.getTempo());
				c.setTexte(textSyllabe.getText());
				c.setVoix(i.getVoix());
				try {
					c.setMelodie(melodie.getMelodie());
					c.sauvegarderSVC(chaine);
					nomFichier = browser.getFileName();
					cheminFichierEntier = chaine;
					setAJour();
					isSaveOnce = true;
				} catch (ChantException e) {
					throw e;
				}
			}
		}
		else
		{
			synchroniser();
			Chant c = new Chant();
			c.setTempo(melodie.getTempo());
			c.setTexte(textSyllabe.getText());
			c.setVoix(i.getVoix());
			try {
				c.setMelodie(melodie.getMelodie());
				c.sauvegarderSVC(cheminFichierEntier);
				setAJour();
			} catch (ChantException e) {
				throw e;
			}
		}
	}
	
	/**
	 * Methode qui joue la prosodie du chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws ChantException
	 * @throws NoteException
	 */
	public void jouer() throws ChantException , NoteException
	{
		try {
			melodie.jouer();
		} catch (ChantException e) {
			throw e;
		} catch(NoteException r) {
			throw r;
		}
	}
	
	/**
	 * Methode qui stoppe la lecture de la melodie du chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void stopper()
	{
		melodie.stop();
	}
	
	/**
	 * Methode qui met le chant dans l'etat non a jour
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setNonAJour()
	{
		this.setText("* "+nomFichier);
		isModified = true;
	}
	
	/**
	 * Methode qui met le chant dans l'etat a jour
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setAJour()
	{
		this.setText(nomFichier);
		isModified = false;
	}
	
	/**
	 * Methode qui indique si le chant a ete modifie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si le chant a ete modifie ou false sinon
	 */
	public boolean getModified()
	{
		return(isModified);
	}	
}
