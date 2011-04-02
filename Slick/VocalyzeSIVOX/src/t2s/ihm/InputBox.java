package t2s.ihm;

import org.eclipse.swt.layout.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.widgets.*;
import org.eclipse.swt.*;

/**
 * Classe InputBox qui permet de demander une valeur a l'utilisateur
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class InputBox {

	private Shell fenetre = null;
	private Text text = null;
	private FormData textData = null;
	private Button bouttonOk = null;
	private FormData bouttonOkData = null;
	private Button bouttonAnnuler = null;
	private FormData bouttonAnnulerData = null;
	private Display ecran = null;
	private boolean sortie = false;
	
	/**
	 * Constructeur par defaut de InputBox
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param i L'interface generale appelante
	 * @param titre Le titre correspondant a la valeur demandee a l'utilisateur
	 * @param initialValue La valeur de reponse par defaut
	 */
	public InputBox(InterfaceBase i, String titre, String initialValue) {
		
		ecran = i.getDisplay();
		fenetre = new Shell(ecran, SWT.CLOSE);
		fenetre.setText(titre);
		fenetre.setSize(new Point(300, 120));
		fenetre.setLayout(new FormLayout());
		fenetre.setLocation(ecran.getClientArea().x+((ecran.getClientArea().width-300)/2),ecran.getClientArea().y+((ecran.getClientArea().height-200)/2));
		text = new Text(fenetre, SWT.BORDER);
		text.setText(initialValue);
		textData = new FormData();
		textData.left = new FormAttachment(2);
		textData.right = new FormAttachment(98);
		textData.top = new FormAttachment(4);
		textData.bottom = new FormAttachment(40);
		text.setLayoutData(textData);
		bouttonOk = new Button(fenetre, SWT.PUSH);
		bouttonOk.setText("&Ok");
		bouttonOkData = new FormData();
		bouttonOkData.left = new FormAttachment(40);
		bouttonOkData.right = new FormAttachment(68);
		bouttonOkData.top = new FormAttachment(45);
		bouttonOkData.bottom = new FormAttachment(95);
		bouttonOk.setLayoutData(bouttonOkData);
		bouttonAnnuler = new Button(fenetre, SWT.PUSH);
		bouttonAnnuler.setText("&Annuler");
		bouttonAnnulerData = new FormData();
		bouttonAnnulerData.left = new FormAttachment(70);
		bouttonAnnulerData.right = new FormAttachment(98);
		bouttonAnnulerData.top = new FormAttachment(45);
		bouttonAnnulerData.bottom = new FormAttachment(95);
		bouttonAnnuler.setLayoutData(bouttonAnnulerData);
		
		// chargement des images
		try {
			fenetre.setImage(new Image(ecran, InformationSysteme.getRepertoireImage32()+"icone_application_32_32.png"));
			bouttonOk.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"appliquer_16_16.png"));
			bouttonAnnuler.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"annuler_16_16.png"));
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(fenetre, SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("Le chargement des images a echoue, le programme peut ne pas fonctionner correctement !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
		}
		
		// boutton escape qui annule l action (quitter en retournant "" )
		text.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 27)
				{
					text.setText("");
					sortie = true;
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
		
		bouttonOk.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 27)
				{
					text.setText("");
					sortie = true;
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
		
		bouttonAnnuler.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				if(e.keyCode == 27)
				{
					text.setText("");
					sortie = true;
				}
			}
			public void keyReleased(KeyEvent e) {}
		});
		
		// evenement sur le boutton annuler
		bouttonAnnuler.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				text.setText("");
				sortie = true;
			}
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		// evenement sur le boutton ok
		bouttonOk.addSelectionListener(new SelectionListener() {
			public void widgetSelected(SelectionEvent e) {
				sortie = true;
			}
			public void widgetDefaultSelected(SelectionEvent arg0) {}
		});
		
		// evenement sur le boutton fermer (croix rouge)
		fenetre.addShellListener(new ShellListener() {

			public void shellActivated(ShellEvent arg0) {}
			public void shellClosed(ShellEvent arg0) {
				arg0.doit = false;
				sortie = true;
			}
			public void shellDeactivated(ShellEvent arg0) {}
			public void shellDeiconified(ShellEvent arg0) {}
			public void shellIconified(ShellEvent arg0) {}	
		});
		
	}
	
	/**
	 * Methode d'ouverture de la fenetre InputBox
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le texte entre par l'utilisateur ou "" si l'utilisateur annule l'action
	 */
	public String open()
	{
		fenetre.open();
		while (!fenetre.isDisposed() && (sortie == false))
	    {
	    	if (!ecran.readAndDispatch())
	    		ecran.sleep();
	    }
		String result = text.getText();
		fenetre.dispose();
		return(result);
	}

}
