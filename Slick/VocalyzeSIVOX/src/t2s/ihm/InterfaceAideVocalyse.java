package t2s.ihm;

import java.io.*;
import org.eclipse.swt.*;
import org.eclipse.swt.browser.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * Classe InterfaceAide qui affiche l'aide de l'application SIVOX vocalyse
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class InterfaceAideVocalyse {

	private boolean sortie = false;
	private Shell fenetrePrincipale = null;
	private FormLayout layoutCentral = null;
	private CTabFolder folderTree = null;
	private CTabFolder folderExplorateur = null;
	private CTabItem ctabItemTree = null;
	private CTabItem ctabItemExplorateur = null;
	private Browser explorateur = null;
	private Tree treeAide = null;
	private FormData folderTreeData = null;
	private FormData folderExplorateurData = null;
	
	private InterfaceGenerale i = null;
	
	/**
	 * Constructeur par defaut de InterfaceAideVocalyse
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param i1 L'interface generale appelante
	 */
	public InterfaceAideVocalyse (InterfaceGenerale i1)
	{
		i = i1;
		fenetrePrincipale = new Shell(i.getDisplay(), SWT.CLOSE | SWT.RESIZE);
		fenetrePrincipale.setText("VOCALYSE S.I. Vox - Aide");
		layoutCentral = new FormLayout();
		fenetrePrincipale.setLayout(layoutCentral);
		
		// creation des CTabFolder
		folderTree = new CTabFolder(fenetrePrincipale, SWT.MULTI | SWT.RESIZE);
		folderExplorateur = new CTabFolder(fenetrePrincipale, SWT.MULTI | SWT.RESIZE);
		folderTree.setBorderVisible(true);
		folderTree.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		folderTree.setSimple(false);
		folderTree.setMinimizeVisible(false);
		folderTree.setMaximizeVisible(true);
		folderExplorateur.setBorderVisible(true);
		folderExplorateur.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		folderExplorateur.setSimple(false);
		folderExplorateur.setMinimizeVisible(false);
		folderExplorateur.setMaximizeVisible(true);
		
		final Sash sashVertical = new Sash(fenetrePrincipale, SWT.VERTICAL);
		FormData sashVerticalData = new FormData();
		sashVerticalData.left = new FormAttachment(30);
		sashVerticalData.top = new FormAttachment(0);
		sashVerticalData.bottom = new FormAttachment(100);
		sashVertical.setLayoutData(sashVerticalData);
		
		folderTreeData = new FormData();
		folderTreeData.left = new FormAttachment(0);
		folderTreeData.right = new FormAttachment(sashVertical);
		folderTreeData.top = new FormAttachment(0);
		folderTreeData.bottom = new FormAttachment(100);
		folderTree.setLayoutData(folderTreeData);
		folderExplorateurData = new FormData();
		folderExplorateurData.left = new FormAttachment(sashVertical);
		folderExplorateurData.right = new FormAttachment(100);
		folderExplorateurData.top = new FormAttachment(0);
		folderExplorateurData.bottom = new FormAttachment(100);
		folderExplorateur.setLayoutData(folderExplorateurData);
		
		// creation des CtabItem dans les CTabFolder
		ctabItemTree = new CTabItem(folderTree, SWT.NONE);
		ctabItemTree.setText("Chapitres");
		folderTree.setSelection(0);
		ctabItemExplorateur = new CTabItem(folderExplorateur, SWT.NONE);
		ctabItemExplorateur.setText("Page d'aide");
		folderExplorateur.setSelection(0);
		
		// creation du treeView
		treeAide = new Tree(folderTree, SWT.BORDER);
		
		//Manipulation de projet SIVOX
		final TreeItem t1 = new TreeItem(treeAide, 0);
		t1.setText("AIDE VOCALYSE S.I. Vox");
		final TreeItem t11 = new TreeItem(t1, 0);
		t11.setText("Manipulation de projet");
		final TreeItem t111 = new TreeItem(t11, 0);
		t111.setText("Definition d'un projet SIVOX");
		final TreeItem t112 = new TreeItem(t11, 0);
		t112.setText("Creer un projet SIVOX");
		final TreeItem t113 = new TreeItem(t11, 0);
		t113.setText("Ouvrir un projet SIVOX");
		
		//Importations
		final TreeItem t12 = new TreeItem(t1, 0);
		t12.setText("Importations");
		final TreeItem t121 = new TreeItem(t12, 0);
		t121.setText("Importer un texte");
		final TreeItem t122 = new TreeItem(t12, 0);
		t122.setText("Importer une prosodie");
		final TreeItem t123 = new TreeItem(t12, 0);
		t123.setText("Importer un chant");
		
		//Prosodie
		final TreeItem t13 = new TreeItem(t1, 0);	
		t13.setText("Prosodie");
		final TreeItem t131 = new TreeItem(t13, 0);
		t131.setText("Manipulation");
		final TreeItem t1311 = new TreeItem(t131, 0);
		t1311.setText("Ajouter une prosodie");
		final TreeItem t1312 = new TreeItem(t131, 0);
		t1312.setText("Supprimer une prosodie");
		final TreeItem t132 = new TreeItem(t13, 0);
		t132.setText("Modification");
		final TreeItem t1321 = new TreeItem(t132, 0);
		t1321.setText("Renommer une prosodie");
		final TreeItem t1322 = new TreeItem(t132, 0);
		t1322.setText("Inserer une frequence");
		final TreeItem t1323 = new TreeItem(t132, 0);
		t1323.setText("Inserer une rapidite");
		final TreeItem t1324 = new TreeItem(t132, 0);
		t1324.setText("Inserer un commentaire");
		final TreeItem t1325 = new TreeItem(t132, 0);
		t1325.setText("Supprimer une insertion");
		final TreeItem t133 = new TreeItem(t13, 0);
		t133.setText("Algorithme de prosodie");
		
		//Graphe
		final TreeItem t14 = new TreeItem(t1, 0);	
		t14.setText("Graphe");
		final TreeItem t141 = new TreeItem(t14, 0);
		t141.setText("Modification sur les phonemes");
		final TreeItem t142 = new TreeItem(t14, 0);
		t142.setText("Modification des points");
		
		//Synchronisation
		final TreeItem t15 = new TreeItem(t1, 0);
		t15.setText("Synchronisation");
		
		//lecture
		final TreeItem t16 = new TreeItem(t1, 0);
		t16.setText("Lecture");
		final TreeItem t161 = new TreeItem(t16, 0);
		t161.setText("Selection de voix");
		final TreeItem t162 = new TreeItem(t16, 0);
		t162.setText("Lecture la prosodie");
		final TreeItem t163 = new TreeItem(t16, 0);
		t163.setText("Enregistrement WAVE");
		
		//Chant
		final TreeItem t17 = new TreeItem(t1, 0);
		t17.setText("Chant");
		
		//Affichage
		final TreeItem t18 = new TreeItem(t1, 0);
		t18.setText("Affichage");
		
		//Configuration
		final TreeItem t19 = new TreeItem(t1, 0);
		t19.setText("Configuration");
		final TreeItem t191 = new TreeItem(t19, 0);
		t191.setText("Configuration sonore");
		final TreeItem t192 = new TreeItem(t19, 0);
		t192.setText("Configuration SIVOX");
		
		// accrochage de l arbre
		ctabItemTree.setControl(treeAide);
		
		// chargement des images
		try {
			fenetrePrincipale.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"aide_sivox_32_32.png"));
			ctabItemTree.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"aide_sivox_16_16.png"));
			ctabItemExplorateur.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"aide_sivox_16_16.png"));
			t1.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t11.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t12.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t13.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t131.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t132.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t14.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t16.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t19.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t111.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t112.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t113.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t121.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t122.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t123.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t1311.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t1312.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t1321.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t1322.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t1323.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t1324.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t1325.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t133.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t141.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t142.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t15.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t161.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t162.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t163.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t17.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t18.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t191.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t192.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(fenetrePrincipale, SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("Le chargement des images a echoue, le programme peut ne pas fonctionner correctement !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
		}
		
		// creation de l explorateur si c'est possible
		try {
	         explorateur = new Browser(folderExplorateur, SWT.NONE);
	         ctabItemExplorateur.setControl(explorateur);
		} catch (Exception e) {
	         MessageBox messageBox = new MessageBox(fenetrePrincipale, SWT.ICON_ERROR | SWT.OK);
	         messageBox.setMessage("le navigateur n'a pas pu être charge, l'aide va se fermer");
	         messageBox.setText("VOCALYSE Si-Vox - Erreur");
	         messageBox.open();
	         sortie = true;
	      }
		
		// evenement de deplacement du sash vertical
	    sashVertical.addSelectionListener(new SelectionAdapter()
	    {
	    	public void widgetSelected(SelectionEvent event)
	    	{
	          ((FormData) sashVertical.getLayoutData()).left = new FormAttachment(0, event.x);
	          sashVertical.getParent().layout();
	        }
	      });
	    
	    // evenement sur le clic croix rouge ou ALT+F4
	    fenetrePrincipale.addShellListener(new ShellListener() {

			public void shellActivated(ShellEvent arg0) {}
			public void shellClosed(ShellEvent arg0) {
				arg0.doit = false;
				sortie = true;
			}
			public void shellDeactivated(ShellEvent arg0) {}
			public void shellDeiconified(ShellEvent arg0) {}
			public void shellIconified(ShellEvent arg0) {}
		});
	    
	    // appui sur escape qui effectue la meme action que bouttonOk
	    folderTree.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 27)
				{
					sortie = true;
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
	    treeAide.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 27)
				{
					sortie = true;
				}
			}
			public void keyReleased(KeyEvent e) {}
	    });
	    folderExplorateur.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 27)
				{
					sortie = true;
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
	    explorateur.addListener(SWT.KeyDown, new Listener() {
			public void handleEvent(Event e) {
				if(e.keyCode == 27)
				{
					sortie = true;
				}	
			}
	    });
	    
	    // evenement sur le double clic du tree general
	    treeAide.addMouseListener(new MouseListener() {
			public void mouseDoubleClick(MouseEvent arg0) {}
			public void mouseDown(MouseEvent arg0) {
				TreeItem[] t = treeAide.getSelection();
				String path = new String("");
				if(t[0].equals(t111))
				{
					path = "aideVocalyseDefinitionProjetSivox.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t112))
				{
					path = "aideVocalyseCreerProjetSivox.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t113))
				{
					path = "aideVocalyseOuvrirProjetSivox.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t121))
				{
					path = "aideVocalyseImporterTexte.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t122))
				{
					path = "aideVocalyseImporterProsodie.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t123))
				{
					path = "aideVocalyseImporterChant.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t1311))
				{
					path = "aideVocalyseAjouterProsodie.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t1312))
				{
					path = "aideVocalyseSupprimerProsodie.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t1321))
				{
					path = "aideVocalyseRenommerProsodie.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t1322))
				{
					path = "aideVocalyseInsererFrequence.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t1323))
				{
					path = "aideVocalyseInsererRapidite.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t1324))
				{
					path = "aideVocalyseInsererCommentaire.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t1325))
				{
					path = "aideVocalyseSupprimerInsertion.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t133))
				{
					path = "aideVocalyseAlgorithmeprosodie.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t141))
				{
					path = "aideVocalyseModificationPhoneme.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t142))
				{
					path = "aideVocalyseModificationPoint.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t15))
				{
					path = "aideVocalyseSynchronisation.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t161))
				{
					path = "aideVocalyseSelectionVoix.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t162))
				{
					path = "aideVocalyseLecture.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t163))
				{
					path = "aideVocalyseEnregistrement.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t17))
				{
					path = "aideVocalyseChant.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t18))
				{
					path = "aideVocalyseAffichage.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t191))
				{
					path = "aideVocalyseConfigurationSonore.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t192))
				{
					path = "aideVocalyseConfigurationSivox.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
			}
			public void mouseUp(MouseEvent arg0) {}
	    });
	    
	    loadExplorateur(InformationSysteme.getRepertoireAide()+"introVocalyse.html");
		fenetrePrincipale.setSize(1100, 650);
		fenetrePrincipale.setLocation(i.getDisplay().getClientArea().x+((i.getDisplay().getClientArea().width-1100)/2),i.getDisplay().getClientArea().y+((i.getDisplay().getClientArea().height-650)/2));	
	}
	
	/**
	 * Methode qui charge une page dans le browser
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param nomFichier Le nom de la page locale a afficher
	 * @return True si l'affichage a reussi et false sinon
	 */
	public boolean loadExplorateur(String nomFichier)
	{
		FileInputStream fichierAide;
		try {					
			fichierAide = new FileInputStream(nomFichier);
			BufferedReader brAide = new BufferedReader(new InputStreamReader(fichierAide));
	        String line = brAide.readLine();
	        StringBuffer aide = new StringBuffer("");
	        while(line != null)
	        {
	        	aide.append(line);
	        	line = brAide.readLine();
	         }
	         brAide.close();
	         explorateur.setText(aide.toString());
	         File f = new File(nomFichier);
	         explorateur.setUrl(f.getAbsolutePath());
	         return(true);
		} catch (Exception e) {
			return(false);
		}
	}
	
	/**
	 * Methode qui affiche la fenetre de l'aide de vocalyse
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void open()
	{
		fenetrePrincipale.open();
		
	    while (!fenetrePrincipale.isDisposed() && (sortie == false))
	    {
	    	if (!i.getDisplay().readAndDispatch())
	    		i.getDisplay().sleep();
	    }
	    
	    i.setHelpClosed();
	    fenetrePrincipale.dispose();
	}
	
	/**
	 * Methode qui active la fenetre d'aide en la mettant au premier plan si possible
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setActive()
	{
		if(fenetrePrincipale != null)
			fenetrePrincipale.setActive();
	}
	
}
