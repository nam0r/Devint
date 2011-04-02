package t2s.ihm;

import java.io.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;
import org.jdom.*;
import t2s.exception.*;

/**
 * Classe SIVOXTabText heritant de CTabFolder (texte graphique d'un projet)
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class SIVOXTabText extends CTabFolder {

	private StyledText text = null;
	private CTabItem textItem = null;
	private InterfaceGenerale i = null;
	private boolean isMaximized = false;
	
	/**
	 * Constructeur par defaut de SIVOXTabText
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param i1 L'interface generale apelante
	 * @param arg0 Le composite parent de SIVOXTabText
	 * @param arg1 L'option du SIVOXTabText
	 */
	public SIVOXTabText(InterfaceGenerale i1, Composite arg0, int arg1) {
		super(arg0, arg1);
		super.setMaximizeVisible(true);
		i = i1;
		this.setSimple(false);
		text = new StyledText(this, SWT.H_SCROLL | SWT.V_SCROLL);
		textItem = new CTabItem(this, SWT.BORDER);
		textItem.setText("Texte du projet");
		this.setSelection(0);
		textItem.setControl(text);
		
		// chargement des images
		try {
			textItem.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"nouveau_projet_16_16.png"));
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(i.getShell(), SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("les images n'ont pas pu etre charge, le programme risque de ne pas fonctionner correctement !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
		}
		
		//evenement de modification de texte
		text.addModifyListener(new ModifyListener() {
			public void modifyText(ModifyEvent e) {
				i.setModified();
				i.setAllDesynchro();
			}
		});
		
		// evenement sur le boutton reduire ou agrandir
		this.addCTabFolder2Listener(new CTabFolder2Listener() {
			public void close(CTabFolderEvent e) {}
			public void maximize(CTabFolderEvent arg0) {
				if(isMaximized == false)
				{
					setMaximized(true);
					isMaximized = true;
					i.maximiserTexte();
				}
			}
			public void minimize(CTabFolderEvent arg0) {}
			public void restore(CTabFolderEvent arg0) {
				if(isMaximized == true)
				{
					setMaximized(false);
					isMaximized = false;
					i.demaximiserTexte();
				}
			}
			public void showList(CTabFolderEvent arg0) {}
		});
	}

	/**
	 * Methode qui importe un texte d'un fichier texte
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param chaine Le chemin du fichier texte a importer
	 * @throws SIVOXException
	 */
	public void importerText(String chaine) throws SIVOXException
	{
		try {
			RandomAccessFile raf = new RandomAccessFile(chaine, "r");
			String ligne = null;
			while((ligne = raf.readLine()) != null)
			{
				text.append(ligne.replace("<", "").replace(">", "").replace("/", "").replace("=", "").replace("\"", "").replace("_", "")+"\n");
			}
		} catch (Exception e) {
			throw new SIVOXException("Erreur : le texte n'a pas pu etre importe","le texte na pa pu etre importai");
		}
	}
	
	/**
	 * Methode loadXML qui charge la partie texte a partir d'un noeud XML
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param elementText Le noeud XML a partir duquel on charge le texte
	 * @throws SIVOXException
	 */
	public void loadXML(Element elementText) throws SIVOXException
	{
		try {
			Element elementLocked = elementText.getChild("Locked");
			if(elementLocked.getText().equalsIgnoreCase("true"))
				text.setEnabled(false);
			else
				text.setEnabled(true);
			Element elementData = elementText.getChild("Data");
			text.setText(elementData.getText().toString());
		} catch(Exception e) {
			throw new SIVOXException("Erreur XML : Le chargement du texte a echoue","erreur lor du chargemen du texte");
		}
	}
	
	/**
	 * Methode saveXML qui sauve la partie texte dans un noeud XML
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param elementText Le noeud XML a partir duquel on sauve la partie texte
	 * @throws SIVOXException
	 */
	public void saveXML(Element elementText) throws SIVOXException
	{
		try {
			Element elementLocked = new Element("Locked");
			if(text.getEnabled() == true)
				elementLocked.setText("false");
			else
				elementLocked.setText("true");
			elementText.addContent(elementLocked);
			Element elementData = new Element("Data");
			elementData.setText(text.getText().replace("<", " ").replace(">", " "));
			elementText.addContent(elementData);
		} catch (Exception e) {
			throw new SIVOXException("Erreur XML : La sauvegarde du texte a echouee","erreur lor de la sauvegarde du texte");
		}
	}
	
	/**
	 * Methode qui retourne le contenu du texte sous forme de String
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le texte contenu dans SIVOXTabText
	 */
	public String getText()
	{
		return(text.getText().toString());
	}
}
