package t2s.ihm;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * Classe InterfaceAboutSinger qui est l'about de singer sivox
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */
public class InterfaceAboutSinger {

	private boolean sortie = false;
	private Shell fenetrePrincipale = null;
	private FormLayout layoutCentral = null;
	private Canvas zoneDessin = null;
	private Label separateur = null;
	private Label infoAbout = null;
	private Button bouttonOk = null;
	private FormData zoneDessinData = null;
	private FormData separateurData = null;
	private FormData infoAboutData = null;
	private FormData bouttonOkData = null;
	
	private InterfaceSingerGenerale i = null;
	
	/**
	 * Constructeur par defaut de InterfaceAboutSinger
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param i1 L'interface singer appelante
	 */
	public InterfaceAboutSinger (InterfaceSingerGenerale i1)
	{
		i=i1;
		fenetrePrincipale = new Shell(i.getDisplay(), SWT.CLOSE);
		fenetrePrincipale.setText("SINGER S.I. Vox - A Propos...");
		layoutCentral = new FormLayout();
		fenetrePrincipale.setLayout(layoutCentral);
		
		zoneDessin = new Canvas(fenetrePrincipale, SWT.NULL);
		zoneDessinData = new FormData(); 
		zoneDessinData.left = new FormAttachment(0);
		zoneDessinData.right = new FormAttachment(100);
		zoneDessinData.top = new FormAttachment(0);
		zoneDessinData.bottom = new FormAttachment(85);
		zoneDessin.setLayoutData(zoneDessinData);
		
		separateur = new Label(fenetrePrincipale, SWT.SEPARATOR | SWT.HORIZONTAL);
		separateurData = new FormData();
		separateurData.left = new FormAttachment(0);
		separateurData.right = new FormAttachment(100);
		separateurData.top = new FormAttachment(85);
		separateurData.bottom = new FormAttachment(86);
		separateur.setLayoutData(separateurData);
		
		infoAbout = new Label(fenetrePrincipale, SWT.NONE);
		infoAboutData = new FormData();
		infoAboutData.left = new FormAttachment(2);
		infoAboutData.right = new FormAttachment(78);
		infoAboutData.top = new FormAttachment(88);
		infoAboutData.bottom = new FormAttachment(98);
		infoAbout.setLayoutData(infoAboutData);
		infoAbout.setText("PROJET SINGER S.I. Vox - E.P.U.N.S.A\nVersion 1.0 @ 2007 - " + InformationSysteme.getOs() + " version");
		
		bouttonOk = new Button(fenetrePrincipale, SWT.PUSH);
		bouttonOk.setText("&Ok");
		bouttonOkData = new FormData();
		bouttonOkData.left = new FormAttachment(80);
		bouttonOkData.right = new FormAttachment(98);
		bouttonOkData.top = new FormAttachment(88);
		bouttonOkData.bottom = new FormAttachment(98);
		bouttonOk.setLayoutData(bouttonOkData);
		
		try {
			fenetrePrincipale.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"a_propos_32_32.png"));
			zoneDessin.setBackgroundImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireLogo()+ "Singer" + InformationSysteme.getOs()+".png"));
			bouttonOk.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"appliquer_16_16.png"));
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(fenetrePrincipale, SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("Le chargement des images a echoue, le programme peut ne pas fonctionner correctement !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
		}
		
		// evenement sur le boutton ok
		bouttonOk.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				sortie = true;
			}
		});
		
		// appui sur escape qui effectue la meme action que bouttonOk
		bouttonOk.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 27)
				{
					sortie = true;
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
		
		// evenement sur clic croix rouge ou ALT+F4
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
		
		// reglage des dimensions de la fenetre et affichage
		fenetrePrincipale.setSize(640,400);
	    fenetrePrincipale.setLocation(i.getDisplay().getClientArea().x+((i.getDisplay().getClientArea().width-640)/2),i.getDisplay().getClientArea().y+((i.getDisplay().getClientArea().height-400)/2));
	}
	
	/**
	 * Methode qui affiche la fenetre aboutSinger
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void open()
	{
		fenetrePrincipale.open();
		i.getShell().setEnabled(false);
		
		// boucle d attente
	    while (!fenetrePrincipale.isDisposed() && (sortie == false))
	    {
	    	if (!i.getDisplay().readAndDispatch())
	    		i.getDisplay().sleep();
	    }
	    
	    // liberation de la fenetre et de l ecran
	    i.setAboutClosed();
	    i.getShell().setEnabled(true);
	    fenetrePrincipale.dispose();
	}
	
	/**
	 * Methode qui active la fenetre about et la met au premier plan si possible
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setActive()
	{
		if(fenetrePrincipale != null)
			fenetrePrincipale.setActive();
	}
}
