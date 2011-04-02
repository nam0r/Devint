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
 * Classe InterfaceAideSinger qui affiche l aide de singer
 * @author Ecole Polytechnique de Sophia Antipolis
 *
 */
public class InterfaceAideSinger {

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
	
	private InterfaceSingerGenerale i = null;
	
	/**
	 * Constructeur par defaut de InterfaceAideSinger
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param i1 L'interface singer appelante
	 */
	public InterfaceAideSinger(InterfaceSingerGenerale i1)
	{
		i = i1;
		fenetrePrincipale = new Shell(i.getDisplay(), SWT.CLOSE | SWT.RESIZE);
		fenetrePrincipale.setText("SINGER S.I. Vox - Aide");
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
		final TreeItem t1 = new TreeItem(treeAide, 0);
		t1.setText("AIDE SINGER S.I. Vox");
		final TreeItem t11 = new TreeItem(t1, 0);
		t11.setText("Manipulation des chants");
		final TreeItem t111 = new TreeItem(t11, 0);
		t111.setText("Definition d'un chant");
		final TreeItem t112 = new TreeItem(t11, 0);
		t112.setText("Creation d'un chant");
		final TreeItem t113 = new TreeItem(t11, 0);
		t113.setText("Ouverture d'un chant");
		final TreeItem t12 = new TreeItem(t1, 0);
		t12.setText("Creation du texte en syllabes");
		final TreeItem t13 = new TreeItem(t1, 0);	
		t13.setText("Melodie");
		final TreeItem t131 = new TreeItem(t13, 0);	
		t131.setText("Configuration de note");
		final TreeItem t132 = new TreeItem(t13, 0);	
		t132.setText("Ajout/Suppression de note");
		final TreeItem t133 = new TreeItem(t13, 0);	
		t133.setText("Insertion de note");
		final TreeItem t134 = new TreeItem(t13, 0);	
		t134.setText("Modification de note");
		final TreeItem t135 = new TreeItem(t13, 0);	
		t135.setText("Modification d'octave");
		final TreeItem t136 = new TreeItem(t13, 0);	
		t136.setText("Decalage de note");
		final TreeItem t137 = new TreeItem(t13, 0);	
		t137.setText("Modification du tempo");
		final TreeItem t14 = new TreeItem(t1, 0);	
		t14.setText("Selection de voix");
		final TreeItem t15 = new TreeItem(t1, 0);	
		t15.setText("Synchronisation");
		
		// accrochage de l arbre
		ctabItemTree.setControl(treeAide);
		
		// chargement des images
		try {
			fenetrePrincipale.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"aide_sivox_32_32.png"));
			ctabItemTree.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"aide_sivox_16_16.png"));
			ctabItemExplorateur.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"aide_sivox_16_16.png"));
			t1.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t11.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t13.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t14.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t15.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"section_16_16.png"));
			t111.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t112.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t113.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t12.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t131.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t132.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t133.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t134.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t135.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t136.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t137.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t14.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
			t15.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"sous_section_16_16.png"));
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
					path = "aideSingerDefinitionChant.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t112))
				{
					path = "aideSingerCreerChant.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t113))
				{
					path = "aideSingerOuvrirChant.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t12))
				{
					path = "aideSingerCreationTexteSyllabe.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t131))
				{
					path = "aideSingerConfigurerNote.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t132))
				{
					path = "aideSingerAjoutSuppression.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t133))
				{
					path = "aideSingerInsertionNote.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t134))
				{
					path = "aideSingerModifierNote.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t135))
				{
					path = "aideSingerModifierOctave.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t136))
				{
					path = "aideSingerDecalerNote.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t137))
				{
					path = "aideSingerModificationTempo.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t14))
				{
					path = "aideSingerSelectionVoix.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
				else if(t[0].equals(t15))
				{
					path = "aideSingerSynchronisation.html";
					loadExplorateur(InformationSysteme.getRepertoireAide()+path);
				}
			}
			public void mouseUp(MouseEvent arg0) {}
	    });
	    
	    loadExplorateur(InformationSysteme.getRepertoireAide()+"introSinger.html");
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
	 * Methode qui affiche la fenetre de l'aide de singer
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
