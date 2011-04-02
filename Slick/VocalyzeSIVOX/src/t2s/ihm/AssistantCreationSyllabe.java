package t2s.ihm;

import org.eclipse.swt.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * Classe d assistance pour la creation de texte decoupe en syllabe
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */
public class AssistantCreationSyllabe {

	private String chaine = "";
	private int cpt=1;
	private boolean sortie = false;
	private InterfaceSingerGenerale i = null;
	private Shell fenetrePrincipale = null;
	
	private Label labelSyllabe = null;
	private FormData labelSyllabeData = null;
	private Text textSyllabe = null;
	private FormData textSyllabeData = null;
	private Button bouttonSuivant = null;
	private FormData bouttonSuivantData = null;
	private Button bouttonTerminer = null;
	private FormData bouttonTerminerData = null;
	
	
	/**
	 * Constructeur par defaut de AssistantCreationSyllabe
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param i1 L'interface Singer appelante
	 */
	public AssistantCreationSyllabe(InterfaceSingerGenerale i1)
	{
		//affectation de l interfacee singer generale
		i = i1;
		
		//creation de la fenetre Pricipale
		fenetrePrincipale = new Shell(i.getDisplay(), SWT.CLOSE);
		fenetrePrincipale.setSize(500, 150);
		fenetrePrincipale.setLayout(new FormLayout());
		fenetrePrincipale.setLocation(i.getDisplay().getClientArea().x+((i.getDisplay().getClientArea().width-500)/2),i.getDisplay().getClientArea().y+((i.getDisplay().getClientArea().height-150)/2));
		fenetrePrincipale.setText("SINGER S.I. Vox - Creation de syllabes");
		
		//creation du label et de la zone de texte
		labelSyllabe = new Label(fenetrePrincipale, SWT.FLAT);
		labelSyllabe.setText("Entrez la syllabe ("+cpt+") :");
		labelSyllabeData = new FormData();
		labelSyllabeData.left = new FormAttachment(2);
		labelSyllabeData.top = new FormAttachment(20);
		labelSyllabeData.right = new FormAttachment(29);
		labelSyllabeData.bottom = new FormAttachment(40);
		labelSyllabe.setLayoutData(labelSyllabeData);
		
		textSyllabe = new Text(fenetrePrincipale, SWT.BORDER);
		textSyllabeData = new FormData();
		textSyllabeData.left = new FormAttachment(30);
		textSyllabeData.top = new FormAttachment(20);
		textSyllabeData.right = new FormAttachment(98);
		textSyllabeData.bottom = new FormAttachment(40);
		textSyllabe.setLayoutData(textSyllabeData);
		
		bouttonSuivant = new Button(fenetrePrincipale, SWT.PUSH);
		bouttonSuivant.setText("Suivant");
		bouttonSuivantData = new FormData();
		bouttonSuivantData.left = new FormAttachment(50);
		bouttonSuivantData.top = new FormAttachment(65);
		bouttonSuivantData.right = new FormAttachment(73);
		bouttonSuivantData.bottom = new FormAttachment(90);
		bouttonSuivant.setLayoutData(bouttonSuivantData);
		
		bouttonTerminer = new Button(fenetrePrincipale, SWT.PUSH);
		bouttonTerminer.setText("Terminer");
		bouttonTerminerData = new FormData();
		bouttonTerminerData.left = new FormAttachment(75);
		bouttonTerminerData.right = new FormAttachment(98);
		bouttonTerminerData.top = new FormAttachment(65);
		bouttonTerminerData.bottom = new FormAttachment(90);
		bouttonTerminer.setLayoutData(bouttonTerminerData);
		
		//affectation des images
		try {
			fenetrePrincipale.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"syllabe_32_32.png"));
		} catch (Exception e) {
			
		}
		
		textSyllabe.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent arg0) {}
			public void keyReleased(KeyEvent arg0) {
				if(arg0.keyCode == 13)
				{
					actionSuivant();
				}
			}
		});
		
		//evenement sur le boutton suivant
		bouttonSuivant.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				actionSuivant();
			}
		});
		
		//evenement sur le boutton terminer
		bouttonTerminer.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(textSyllabe.getText().length() > 0)
				{
					if(chaine.length() == 0)
					{
						chaine = textSyllabe.getText();
					}
					else
					{
						chaine = chaine + "/" + textSyllabe.getText();
					}
				}
				sortie = true;
			}
		});
	}
	
	/**
	 * Methode qui ouvre la fenetre d assistant de creation des syllabes
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return
	 */
	public String open()
	{
		fenetrePrincipale.open();
		while (!fenetrePrincipale.isDisposed() && (sortie == false))
	    {
	    	if (!i.getDisplay().readAndDispatch())
	    		i.getDisplay().sleep();
	    }
	    fenetrePrincipale.dispose();
	    return(chaine);
	}
	
	/**
	 * Methode qui stcke la syllabe et passe a la syllabe suivante
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void actionSuivant()
	{
		if(textSyllabe.getText().length() > 0)
		{
			if(chaine.length() == 0)
			{
				chaine = textSyllabe.getText();
			}
			else
			{
				chaine = chaine + "/" + textSyllabe.getText();
			}
			textSyllabe.setText("");
			textSyllabe.setFocus();
			cpt++;
			labelSyllabe.setText("Entrez la syllabe ("+cpt+") :");
		}
		else
		{
			MessageBox messageBox = new MessageBox(i.getShell(), SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("Veuillez entrer au moins 1 lettre pour creer une syllabe");
	        messageBox.setText("SINGER Si-Vox - Erreur");
	        messageBox.open();
		}
	}
	
}
