package t2s.ihm;

import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import t2s.exception.*;
import t2s.util.*;

/**
 * Classe InterfaceSingerGenerale heritant de InterfaceBase
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */
public class InterfaceSingerGenerale extends InterfaceBase {

	private boolean aboutOpen = false;
	private InterfaceAboutSinger ias = null;
	private boolean helpOpen = false;
	private InterfaceAideSinger iais = null;
	
	private InterfaceSingerGenerale objetCourant = null;
	
	private boolean sortie = false;
	private InterfaceGenerale i = null;
	
	private Shell fenetrePrincipale = null;
	private FormLayout layoutCentral = null;
	
	private SIVOXSingerTabFolder folderChant = null;
	private FormData folderChantData = null;
	private CLabel information = null;
	private FormData informationData = null;
	
	//menu popup des voix
	private Menu menuPopupVoix = null;
	private MenuItem menuPopupVoixThierry = null;
	private MenuItem menuPopupVoixCeline = null;
	private MenuItem menuPopupVoixVincent = null;
	private MenuItem menuPopupVoixAnneCarole = null;
	private MenuItem menuPopupVoixFabrice =  null;
	private MenuItem menuPopupVoixXavier = null;
	private MenuItem menuPopupVoixSoldatInconnu = null;
	
	//elements du menu
	private Menu mainMenu = null;
	
	private MenuItem menuFichier = null;
	private Menu menuOptionFichier = null;
	private MenuItem menuNouveau = null;
	private MenuItem menuOuvrir = null;
	private MenuItem menuEnregistrer = null;
	private MenuItem menuEnregistrerSous = null;
	private MenuItem menuEnregistrerTout = null;
	private MenuItem menuFermer = null;
	private MenuItem menuFermerTout = null;
	private MenuItem menuQuitter = null;
	
	private MenuItem menuLecture = null;
	private Menu menuOptionLecture = null;
	private MenuItem menuSelectionVoix = null;
	private Menu menuOptionSelectionVoix = null;
	private MenuItem menuVoixThierry = null;
	private MenuItem menuVoixCeline = null;
	private MenuItem menuVoixVincent = null;
	private MenuItem menuVoixAnneCarole = null;
	private MenuItem menuVoixFabrice =  null;
	private MenuItem menuVoixXavier = null;
	private MenuItem menuVoixSoldatInconnu = null;
	private MenuItem menuSynchroniser = null;
	private MenuItem menuJouer = null;
	private MenuItem menuStop = null;
	
	private MenuItem menuAide = null;
	private Menu menuOptionAide = null;
	private MenuItem menuAPropos = null;
	private MenuItem menuAideSinger = null;
	
	// elements de la COOLBAR
	private FormData coolData = null;
	
	private CoolBar barreOutil = null;
	
	private ToolBar toolBarGestionFichier = null;
	private CoolItem coolItemGestionFichier = null;
	private ToolItem bouttonNouveau = null;
	private ToolItem bouttonOuvrir = null;
	private ToolItem bouttonSauver = null;
	private ToolItem bouttonFermer = null;
	private ToolItem bouttonQuitter = null;
	
	private ToolBar toolBarGestionSynchro = null;
	private CoolItem coolItemGestionSynchro = null;
	private ToolItem bouttonSynchroniser = null;
	
	private ToolBar toolBarGestionPlayer = null;
	private CoolItem coolItemGestionPlayer = null;
	private ToolItem bouttonJouer = null;
	private ToolItem bouttonStop = null;
	
	private ToolBar toolBarGestionVoix = null;
	private CoolItem coolItemGestionVoix = null;
	private ToolItem bouttonSelectionVoix = null;
	
	/**
	 * Constructeur par defaut de InterfaceSingerGenerale
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param i1 L'interface generale appelante
	 */
	public InterfaceSingerGenerale(InterfaceGenerale i1)
	{
		String temp ="";
		
		//affectation objet courant
		objetCourant = this;
		
		//affectation de l interface generale sivox
		i = i1;
		
		//creation fenetre
		fenetrePrincipale = new Shell(i.getDisplay());
		fenetrePrincipale.setSize(1200,768);
	    fenetrePrincipale.setLocation(i.getDisplay().getClientArea().x+((i.getDisplay().getClientArea().width-1200)/2),i.getDisplay().getClientArea().y+((i.getDisplay().getClientArea().height-768)/2));
		fenetrePrincipale.setText("SINGER S.I. Vox");
		
		//creation du layout
		layoutCentral = new FormLayout();
		fenetrePrincipale.setLayout(layoutCentral);
		
		//creation du MENU POPUP des voix
		menuPopupVoix = new Menu(fenetrePrincipale, SWT.POP_UP);
		menuPopupVoixThierry = new MenuItem(menuPopupVoix, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_1"))!= null)
			menuPopupVoixThierry.setText(temp);
		else
			menuPopupVoixThierry.setText("Thierry");
		menuPopupVoixCeline = new MenuItem(menuPopupVoix, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_2"))!= null)
			menuPopupVoixCeline.setText(temp);
		else
			menuPopupVoixCeline.setText("Celine");
		menuPopupVoixVincent = new MenuItem(menuPopupVoix, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_3"))!= null)
			menuPopupVoixVincent.setText(temp);
		else
			menuPopupVoixVincent.setText("Vincent");
		menuPopupVoixAnneCarole = new MenuItem(menuPopupVoix, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_4"))!= null)
			menuPopupVoixAnneCarole.setText(temp);
		else
			menuPopupVoixAnneCarole.setText("Anne Carole");
		menuPopupVoixFabrice =  new MenuItem(menuPopupVoix, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_5"))!= null)
			menuPopupVoixFabrice.setText(temp);
		else
			menuPopupVoixFabrice.setText("Fabrice");
		menuPopupVoixXavier = new MenuItem(menuPopupVoix, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_6"))!= null)
			menuPopupVoixXavier.setText(temp);
		else
			menuPopupVoixXavier.setText("Xavier");
		menuPopupVoixSoldatInconnu = new MenuItem(menuPopupVoix, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_7"))!= null)
			menuPopupVoixSoldatInconnu.setText(temp);
		else
			menuPopupVoixSoldatInconnu.setText("Soldat inconnu");
		
		
		// creation du MENU dans la fenetre principale
		mainMenu = new Menu(fenetrePrincipale, SWT.BAR);
		
		// ZONE FICHIER
		menuFichier = new MenuItem(mainMenu, SWT.CASCADE);
		menuFichier.setText("&Fichier");
		menuOptionFichier = new Menu(fenetrePrincipale, SWT.DROP_DOWN);
		menuNouveau = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuNouveau.setText("&Nouveau chant\tCtrl+N");
		menuNouveau.setAccelerator(SWT.CTRL+'N');
		menuOuvrir = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuOuvrir.setText("&Ouvrir un chant...\tCtrl+O");
		menuOuvrir.setAccelerator(SWT.CTRL+'O');
		new MenuItem(menuOptionFichier, SWT.SEPARATOR);
		menuEnregistrer = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuEnregistrer.setText("Enregi&strer le chant\tCtrl+S");
		menuEnregistrer.setAccelerator(SWT.CTRL+'S');
		menuEnregistrerSous = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuEnregistrerSous.setText("Enregistrer le chant sous...");
		menuEnregistrerTout = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuEnregistrerTout.setText("Enregistrer Tout");
		new MenuItem(menuOptionFichier, SWT.SEPARATOR);
		menuFermer = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuFermer.setText("&Fermer\tCtrl+F");
		menuFermer.setAccelerator(SWT.CTRL+'F');
		menuFermerTout = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuFermerTout.setText("Fermer Tout");
		new MenuItem(menuOptionFichier, SWT.SEPARATOR);
		menuQuitter = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuQuitter.setText("&Quitter\tCtrl+Q");
		menuQuitter.setAccelerator(SWT.CTRL+'Q');
		menuFichier.setMenu(menuOptionFichier);
		fenetrePrincipale.setMenuBar(mainMenu);
		
		menuLecture = new MenuItem(mainMenu, SWT.CASCADE);
		menuLecture.setText("&Lecture");
		menuOptionLecture = new Menu(fenetrePrincipale, SWT.DROP_DOWN);
		menuSelectionVoix = new MenuItem(menuOptionLecture, SWT.CASCADE);
		menuSelectionVoix.setText("Selection d'une vo&ix");
		menuSelectionVoix.setAccelerator(SWT.CTRL+'I');
		menuOptionSelectionVoix = new Menu(fenetrePrincipale, SWT.DROP_DOWN);
		menuVoixThierry = new MenuItem(menuOptionSelectionVoix, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_1"))!= null)
			menuVoixThierry.setText(temp);
		else
			menuVoixThierry.setText("Thierry");
		menuVoixCeline = new MenuItem(menuOptionSelectionVoix, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_2"))!= null)
			menuVoixCeline.setText(temp);
		else
			menuVoixCeline.setText("Celine");
		menuVoixVincent = new MenuItem(menuOptionSelectionVoix, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_3"))!= null)
			menuVoixVincent.setText(temp);
		else
			menuVoixVincent.setText("Vincent");
		menuVoixAnneCarole = new MenuItem(menuOptionSelectionVoix, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_4"))!= null)
			menuVoixAnneCarole.setText(temp);
		else
			menuVoixAnneCarole.setText("Anne Carole");
		menuVoixFabrice =  new MenuItem(menuOptionSelectionVoix, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_5"))!= null)
			menuVoixFabrice.setText(temp);
		else
			menuVoixFabrice.setText("Fabrice");
		menuVoixXavier = new MenuItem(menuOptionSelectionVoix, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_6"))!= null)
			menuVoixXavier.setText(temp);
		else
			menuVoixXavier.setText("Xavier");
		menuVoixSoldatInconnu = new MenuItem(menuOptionSelectionVoix, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_7"))!= null)
			menuVoixSoldatInconnu.setText(temp);
		else
			menuVoixSoldatInconnu.setText("Soldat inconnu");
		new MenuItem(menuOptionLecture, SWT.SEPARATOR);
		menuSynchroniser = new MenuItem(menuOptionLecture, SWT.PUSH);
		menuSynchroniser.setText("&Synchroniser\tCtrl+L");
		menuSynchroniser.setAccelerator(SWT.CTRL+'L');
		new MenuItem(menuOptionLecture, SWT.SEPARATOR);
		menuJouer = new MenuItem(menuOptionLecture, SWT.PUSH);
		menuJouer.setText("&Jouer\tCtrl+J");
		menuJouer.setAccelerator(SWT.CTRL+'J');
		menuStop = new MenuItem(menuOptionLecture, SWT.PUSH);
		menuStop.setText("S&top\tCtrl+T");
		menuStop.setAccelerator(SWT.CTRL+'T');
		menuSelectionVoix.setMenu(menuOptionSelectionVoix);
		menuLecture.setMenu(menuOptionLecture);
		
		// ZONE AIDE
		menuAide = new MenuItem(mainMenu, SWT.CASCADE);
		menuAide.setText("&Aide");
		menuOptionAide = new Menu(fenetrePrincipale, SWT.DROP_DOWN);
		menuAPropos = new MenuItem(menuOptionAide, SWT.PUSH);
		menuAPropos.setText("A Propos...\tAlt+F1");
		menuAPropos.setAccelerator(SWT.F1);
		menuAideSinger = new MenuItem(menuOptionAide, SWT.PUSH);
		menuAideSinger.setText("Aide SINGER Si-Vox\tAlt+F2");
		menuAideSinger.setAccelerator(SWT.F2);
		menuAide.setMenu(menuOptionAide);
		
		// creation de la COOLBAR
		try {
			barreOutil = new CoolBar(fenetrePrincipale, SWT.NONE);
			toolBarGestionFichier = new ToolBar(barreOutil, SWT.NONE);
			bouttonNouveau = new ToolItem(toolBarGestionFichier, SWT.PUSH);
			bouttonNouveau.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"nouveau_projet_32_32.png"));
			bouttonNouveau.setText("Nouveau Chant");
			bouttonNouveau.setToolTipText("Creer un nouveau chant");
			bouttonOuvrir = new ToolItem(toolBarGestionFichier, SWT.PUSH);
			bouttonOuvrir.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"ouvrir_projet_32_32.png"));
			bouttonOuvrir.setText("Ouvrir Chant");
			bouttonOuvrir.setToolTipText("Ouvrir un chant existant");
			bouttonSauver = new ToolItem(toolBarGestionFichier, SWT.PUSH);
			bouttonSauver.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"enregistrer_32_32.png"));
			bouttonSauver.setText("Enregistrer");
			bouttonSauver.setToolTipText("Enregistrer le chant courant");
			bouttonFermer = new ToolItem(toolBarGestionFichier, SWT.PUSH);
			bouttonFermer.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"fermer_32_32.png"));
			bouttonFermer.setText("Fermer");
			bouttonFermer.setToolTipText("Fermer le chant cours");
			bouttonQuitter = new ToolItem(toolBarGestionFichier, SWT.PUSH);
			bouttonQuitter.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"quitter_32_32.png"));
			bouttonQuitter.setText("Quitter");
			bouttonQuitter.setToolTipText("Quitter l'application");
			toolBarGestionFichier.pack();
			Point size = toolBarGestionFichier.getSize();
			coolItemGestionFichier = new CoolItem(barreOutil, SWT.NONE);
			coolItemGestionFichier.setControl(toolBarGestionFichier);
			Point preferred = coolItemGestionFichier.computeSize(size.x, size.y);
			coolItemGestionFichier.setPreferredSize(preferred);
			coolItemGestionFichier.setMinimumSize(preferred);
			
			toolBarGestionSynchro = new ToolBar(barreOutil, SWT.NONE);
			bouttonSynchroniser = new ToolItem(toolBarGestionSynchro, SWT.PUSH);
			bouttonSynchroniser.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"synchroniser_prosodie_32_32.png"));
			bouttonSynchroniser.setText("Synchroniser");
			bouttonSynchroniser.setToolTipText("Synchroniser les syllabes avec la melodie");
			toolBarGestionSynchro.pack();
			size = toolBarGestionSynchro.getSize();
			coolItemGestionSynchro = new CoolItem(barreOutil, SWT.NONE);
			coolItemGestionSynchro.setControl(toolBarGestionSynchro);
			preferred = coolItemGestionSynchro.computeSize(size.x, size.y);
			coolItemGestionSynchro.setPreferredSize(preferred);
			coolItemGestionSynchro.setMinimumSize(preferred);
			
			toolBarGestionPlayer = new ToolBar(barreOutil, SWT.NONE);
			bouttonJouer = new ToolItem(toolBarGestionPlayer, SWT.PUSH);
			bouttonJouer.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"jouer_32_32.png"));
			bouttonJouer.setText("Jouer");
			bouttonJouer.setToolTipText("Jouer chant");
			bouttonStop = new ToolItem(toolBarGestionPlayer, SWT.PUSH);
			bouttonStop.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"stop_32_32.png"));
			bouttonStop.setText("Stop");
			bouttonStop.setToolTipText("Arreter la lecture du chant");
			toolBarGestionPlayer.pack();
			size = toolBarGestionPlayer.getSize();
			coolItemGestionPlayer = new CoolItem(barreOutil, SWT.NONE);
			coolItemGestionPlayer.setControl(toolBarGestionPlayer);
			preferred = coolItemGestionPlayer.computeSize(size.x, size.y);
			coolItemGestionPlayer.setPreferredSize(preferred);
			coolItemGestionPlayer.setMinimumSize(preferred);
			
			toolBarGestionVoix = new ToolBar(barreOutil, SWT.NONE);
			bouttonSelectionVoix = new ToolItem(toolBarGestionVoix, SWT.DROP_DOWN);
			bouttonSelectionVoix.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"selection_voix_32_32.png"));
			bouttonSelectionVoix.setText("Voix : "+i.getMaxStringVoix());
			bouttonSelectionVoix.setToolTipText("Voix : "+i.getMaxStringVoix());
			toolBarGestionVoix.pack();
			size = toolBarGestionVoix.getSize();
			coolItemGestionVoix = new CoolItem(barreOutil, SWT.NONE);
			coolItemGestionVoix.setControl(toolBarGestionVoix);
			preferred = coolItemGestionVoix.computeSize(size.x, size.y);
			coolItemGestionVoix.setPreferredSize(preferred);
			coolItemGestionVoix.setMinimumSize(preferred);
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(fenetrePrincipale, SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("Le chargement des images a echoue, le programme doit quitter !");
	        messageBox.setText("SINGER Si-Vox - Erreur");
	        messageBox.open();
	        sortie = true;
		}
		
		coolData = new FormData();
		coolData.left = new FormAttachment(0);
		coolData.right = new FormAttachment(100);
		coolData.top = new FormAttachment(0);
		barreOutil.setLayoutData(coolData);
		
		information = new CLabel(fenetrePrincipale, SWT.SHADOW_IN | SWT.LEFT);
		information.setText("Pret");
		informationData = new FormData();
		informationData.left = new FormAttachment(0);
		informationData.right = new FormAttachment(100);
		informationData.bottom = new FormAttachment(100);
		information.setLayoutData(informationData);
		
		folderChant = new SIVOXSingerTabFolder(fenetrePrincipale, SWT.MULTI | SWT.RESIZE | SWT.CLOSE | SWT.BORDER, this);
		folderChantData = new FormData();
		folderChantData.left = new FormAttachment(0);
		folderChantData.right = new FormAttachment(100);
		folderChantData.top = new FormAttachment(barreOutil);
		folderChantData.bottom = new FormAttachment(information);
		folderChant.setLayoutData(folderChantData);
		
		//chargement des images du menu
		try {
			fenetrePrincipale.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"icone_application_singer_16_16.png"));
			menuNouveau.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"nouveau_projet_16_16.png"));
			menuOuvrir.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"ouvrir_projet_16_16.png"));
			menuEnregistrer.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"enregistrer_16_16.png"));
			menuEnregistrerTout.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"enregistrer_tout_16_16.png"));
			menuFermer.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"fermer_16_16.png"));
			menuQuitter.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"quitter_16_16.png"));
			menuSelectionVoix.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"selection_voix_16_16.png"));
			menuSynchroniser.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"synchroniser_prosodie_16_16.png"));
			menuJouer.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"jouer_16_16.png"));
			menuStop.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"stop_16_16.png"));
			menuAPropos.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"a_propos_16_16.png"));
			menuAideSinger.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"aide_sivox_16_16.png"));
			information.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"information_16_16.png"));
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(fenetrePrincipale, SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("Le chargement des images a echoue, le programme doit quitter !");
	        messageBox.setText("SINGER Si-Vox - Erreur");
	        messageBox.open();
	        sortie = true;
		}
		
		//configuration des voix
		configurerVoix();
		
		//evenement de mouvement de la coolbar
		barreOutil.addListener(SWT.Resize, new Listener() {
		      public void handleEvent(Event event) {
		    	  fenetrePrincipale.layout();
		      }
		});
		
		//evenement de nouveau chant (cool bar)
		bouttonNouveau.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				creerNouveauChant();
			}
		});
		
		//evenement de nouveau chant (menu)
		menuNouveau.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				creerNouveauChant();
			}
		});
		
		//evenement de ouvrir chant (cool bar)
		bouttonOuvrir.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				ouvrirChant();
			}
		});
		
		//evenement de ouvrir chant (menu)
		menuOuvrir.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				ouvrirChant();
			}
		});
		
		//evenement de sauver (cool bar)
		bouttonSauver.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				enregistrer(false);
			}
		});
		
		//evenement de sauver (menu)
		menuEnregistrer.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				enregistrer(false);
			}
		});
		
		//evenement de sauver sous (menu)
		menuEnregistrerSous.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				enregistrer(true);
			}
		});
		
		//evenement de enregistrer tout (menu)
		menuEnregistrerTout.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				enregistrerTout();
			}
		});
		
		//evenement de fermer le chant courant (cool bar)
		bouttonFermer.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				fermer();
			}
		});
		
		//evenement de fermer le chant courant (menu)
		menuFermer.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				fermer();
			}
		});
		
		//evenement de fermer tous les chants (menu)
		menuFermerTout.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				fermerTout();
			}
		});
		
		//evenement de quitter l application (cool bar)
		bouttonQuitter.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				quitter();
			}
		});
		
		//evenement de quitter l application (menu)
		menuQuitter.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				quitter();
			}
		});
		
		//evenement du boutton selection voix (popup)
		bouttonSelectionVoix.addListener(SWT.Selection, new Listener() {
			  public void handleEvent(Event event) {
			    if (event.detail == SWT.ARROW) {
			      Rectangle rect = bouttonSelectionVoix.getBounds();
			      Point pt = new Point(rect.x, rect.y + rect.height);
			      pt = toolBarGestionVoix.toDisplay(pt);
			      menuPopupVoix.setLocation(pt.x, pt.y);
			      menuPopupVoix.setVisible(true);
			      if(i.getModeParlantActif())
			      {
			    	  i.lectureAssistant("Selection des voi");
			      }
			    }
			  }
			});
		
		//evenement des menu des voix
		menuPopupVoixThierry.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				menuPopupVoixThierry.setSelection(true);
				menuPopupVoixCeline.setSelection(false);
				menuPopupVoixVincent.setSelection(false);
				menuPopupVoixAnneCarole.setSelection(false);
				menuPopupVoixFabrice.setSelection(false);
				menuPopupVoixXavier.setSelection(false);
				menuPopupVoixSoldatInconnu.setSelection(false);
				menuVoixThierry.setSelection(true);
				menuVoixCeline.setSelection(false);
				menuVoixVincent.setSelection(false);
				menuVoixAnneCarole.setSelection(false);
				menuVoixFabrice.setSelection(false);
				menuVoixXavier.setSelection(false);
				menuVoixSoldatInconnu.setSelection(false);
				bouttonSelectionVoix.setText("Voix : "+menuPopupVoixThierry.getText());
			}
		});
		
		menuPopupVoixCeline.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				menuPopupVoixThierry.setSelection(false);
				menuPopupVoixCeline.setSelection(true);
				menuPopupVoixVincent.setSelection(false);
				menuPopupVoixAnneCarole.setSelection(false);
				menuPopupVoixFabrice.setSelection(false);
				menuPopupVoixXavier.setSelection(false);
				menuPopupVoixSoldatInconnu.setSelection(false);
				menuVoixThierry.setSelection(false);
				menuVoixCeline.setSelection(true);
				menuVoixVincent.setSelection(false);
				menuVoixAnneCarole.setSelection(false);
				menuVoixFabrice.setSelection(false);
				menuVoixXavier.setSelection(false);
				menuVoixSoldatInconnu.setSelection(false);
				bouttonSelectionVoix.setText("Voix : "+menuPopupVoixCeline.getText());
			}
		});
		
		menuPopupVoixVincent.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				menuPopupVoixThierry.setSelection(false);
				menuPopupVoixCeline.setSelection(false);
				menuPopupVoixVincent.setSelection(true);
				menuPopupVoixAnneCarole.setSelection(false);
				menuPopupVoixFabrice.setSelection(false);
				menuPopupVoixXavier.setSelection(false);
				menuPopupVoixSoldatInconnu.setSelection(false);
				menuVoixThierry.setSelection(false);
				menuVoixCeline.setSelection(false);
				menuVoixVincent.setSelection(true);
				menuVoixAnneCarole.setSelection(false);
				menuVoixFabrice.setSelection(false);
				menuVoixXavier.setSelection(false);
				menuVoixSoldatInconnu.setSelection(false);
				bouttonSelectionVoix.setText("Voix : "+menuPopupVoixVincent.getText());
			}
		});
		
		menuPopupVoixAnneCarole.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				menuPopupVoixThierry.setSelection(false);
				menuPopupVoixCeline.setSelection(false);
				menuPopupVoixVincent.setSelection(false);
				menuPopupVoixAnneCarole.setSelection(true);
				menuPopupVoixFabrice.setSelection(false);
				menuPopupVoixXavier.setSelection(false);
				menuPopupVoixSoldatInconnu.setSelection(false);
				menuVoixThierry.setSelection(false);
				menuVoixCeline.setSelection(false);
				menuVoixVincent.setSelection(false);
				menuVoixAnneCarole.setSelection(true);
				menuVoixFabrice.setSelection(false);
				menuVoixXavier.setSelection(false);
				menuVoixSoldatInconnu.setSelection(false);
				bouttonSelectionVoix.setText("Voix : "+menuPopupVoixAnneCarole.getText());
			}
		});
		
		menuPopupVoixFabrice.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				menuPopupVoixThierry.setSelection(false);
				menuPopupVoixCeline.setSelection(false);
				menuPopupVoixVincent.setSelection(false);
				menuPopupVoixAnneCarole.setSelection(false);
				menuPopupVoixFabrice.setSelection(true);
				menuPopupVoixXavier.setSelection(false);
				menuPopupVoixSoldatInconnu.setSelection(false);
				menuVoixThierry.setSelection(false);
				menuVoixCeline.setSelection(false);
				menuVoixVincent.setSelection(false);
				menuVoixAnneCarole.setSelection(false);
				menuVoixFabrice.setSelection(true);
				menuVoixXavier.setSelection(false);
				menuVoixSoldatInconnu.setSelection(false);
				bouttonSelectionVoix.setText("Voix : "+menuPopupVoixFabrice.getText());
			}
		});
		
		menuPopupVoixXavier.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				menuPopupVoixThierry.setSelection(false);
				menuPopupVoixCeline.setSelection(false);
				menuPopupVoixVincent.setSelection(false);
				menuPopupVoixAnneCarole.setSelection(false);
				menuPopupVoixFabrice.setSelection(false);
				menuPopupVoixXavier.setSelection(true);
				menuPopupVoixSoldatInconnu.setSelection(false);
				menuVoixThierry.setSelection(false);
				menuVoixCeline.setSelection(false);
				menuVoixVincent.setSelection(false);
				menuVoixAnneCarole.setSelection(false);
				menuVoixFabrice.setSelection(false);
				menuVoixXavier.setSelection(true);
				menuVoixSoldatInconnu.setSelection(false);
				bouttonSelectionVoix.setText("Voix : "+menuPopupVoixXavier.getText());
			}
		});
		
		menuPopupVoixSoldatInconnu.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				menuPopupVoixThierry.setSelection(false);
				menuPopupVoixCeline.setSelection(false);
				menuPopupVoixVincent.setSelection(false);
				menuPopupVoixAnneCarole.setSelection(false);
				menuPopupVoixFabrice.setSelection(false);
				menuPopupVoixXavier.setSelection(false);
				menuPopupVoixSoldatInconnu.setSelection(true);
				menuVoixThierry.setSelection(false);
				menuVoixCeline.setSelection(false);
				menuVoixVincent.setSelection(false);
				menuVoixAnneCarole.setSelection(false);
				menuVoixFabrice.setSelection(false);
				menuVoixXavier.setSelection(false);
				menuVoixSoldatInconnu.setSelection(true);
				bouttonSelectionVoix.setText("Voix : "+menuPopupVoixSoldatInconnu.getText());
			}
		});
		
		//evenement du menu de la selection des voix
		menuVoixThierry.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				menuPopupVoixThierry.setSelection(true);
				menuPopupVoixCeline.setSelection(false);
				menuPopupVoixVincent.setSelection(false);
				menuPopupVoixAnneCarole.setSelection(false);
				menuPopupVoixFabrice.setSelection(false);
				menuPopupVoixXavier.setSelection(false);
				menuPopupVoixSoldatInconnu.setSelection(false);
				menuVoixThierry.setSelection(true);
				menuVoixCeline.setSelection(false);
				menuVoixVincent.setSelection(false);
				menuVoixAnneCarole.setSelection(false);
				menuVoixFabrice.setSelection(false);
				menuVoixXavier.setSelection(false);
				menuVoixSoldatInconnu.setSelection(false);
				bouttonSelectionVoix.setText("Voix : "+menuPopupVoixThierry.getText());
			}
		});
		
		menuVoixCeline.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				menuPopupVoixThierry.setSelection(false);
				menuPopupVoixCeline.setSelection(true);
				menuPopupVoixVincent.setSelection(false);
				menuPopupVoixAnneCarole.setSelection(false);
				menuPopupVoixFabrice.setSelection(false);
				menuPopupVoixXavier.setSelection(false);
				menuPopupVoixSoldatInconnu.setSelection(false);
				menuVoixThierry.setSelection(false);
				menuVoixCeline.setSelection(true);
				menuVoixVincent.setSelection(false);
				menuVoixAnneCarole.setSelection(false);
				menuVoixFabrice.setSelection(false);
				menuVoixXavier.setSelection(false);
				menuVoixSoldatInconnu.setSelection(false);
				bouttonSelectionVoix.setText("Voix : "+menuPopupVoixCeline.getText());
			}
		});
		
		menuVoixVincent.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				menuPopupVoixThierry.setSelection(false);
				menuPopupVoixCeline.setSelection(false);
				menuPopupVoixVincent.setSelection(true);
				menuPopupVoixAnneCarole.setSelection(false);
				menuPopupVoixFabrice.setSelection(false);
				menuPopupVoixXavier.setSelection(false);
				menuPopupVoixSoldatInconnu.setSelection(false);
				menuVoixThierry.setSelection(false);
				menuVoixCeline.setSelection(false);
				menuVoixVincent.setSelection(true);
				menuVoixAnneCarole.setSelection(false);
				menuVoixFabrice.setSelection(false);
				menuVoixXavier.setSelection(false);
				menuVoixSoldatInconnu.setSelection(false);
				bouttonSelectionVoix.setText("Voix : "+menuPopupVoixVincent.getText());
			}
		});
		
		menuVoixAnneCarole.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				menuPopupVoixThierry.setSelection(false);
				menuPopupVoixCeline.setSelection(false);
				menuPopupVoixVincent.setSelection(false);
				menuPopupVoixAnneCarole.setSelection(true);
				menuPopupVoixFabrice.setSelection(false);
				menuPopupVoixXavier.setSelection(false);
				menuPopupVoixSoldatInconnu.setSelection(false);
				menuVoixThierry.setSelection(false);
				menuVoixCeline.setSelection(false);
				menuVoixVincent.setSelection(false);
				menuVoixAnneCarole.setSelection(true);
				menuVoixFabrice.setSelection(false);
				menuVoixXavier.setSelection(false);
				menuVoixSoldatInconnu.setSelection(false);
				bouttonSelectionVoix.setText("Voix : "+menuPopupVoixAnneCarole.getText());
			}
		});
		
		menuVoixFabrice.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				menuPopupVoixThierry.setSelection(false);
				menuPopupVoixCeline.setSelection(false);
				menuPopupVoixVincent.setSelection(false);
				menuPopupVoixAnneCarole.setSelection(false);
				menuPopupVoixFabrice.setSelection(true);
				menuPopupVoixXavier.setSelection(false);
				menuPopupVoixSoldatInconnu.setSelection(false);
				menuVoixThierry.setSelection(false);
				menuVoixCeline.setSelection(false);
				menuVoixVincent.setSelection(false);
				menuVoixAnneCarole.setSelection(false);
				menuVoixFabrice.setSelection(true);
				menuVoixXavier.setSelection(false);
				menuVoixSoldatInconnu.setSelection(false);
				bouttonSelectionVoix.setText("Voix : "+menuPopupVoixFabrice.getText());
			}
		});
		
		menuVoixXavier.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				menuPopupVoixThierry.setSelection(false);
				menuPopupVoixCeline.setSelection(false);
				menuPopupVoixVincent.setSelection(false);
				menuPopupVoixAnneCarole.setSelection(false);
				menuPopupVoixFabrice.setSelection(false);
				menuPopupVoixXavier.setSelection(true);
				menuPopupVoixSoldatInconnu.setSelection(false);
				menuVoixThierry.setSelection(false);
				menuVoixCeline.setSelection(false);
				menuVoixVincent.setSelection(false);
				menuVoixAnneCarole.setSelection(false);
				menuVoixFabrice.setSelection(false);
				menuVoixXavier.setSelection(true);
				menuVoixSoldatInconnu.setSelection(false);
				bouttonSelectionVoix.setText("Voix : "+menuPopupVoixXavier.getText());
			}
		});
		
		menuVoixSoldatInconnu.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				menuPopupVoixThierry.setSelection(false);
				menuPopupVoixCeline.setSelection(false);
				menuPopupVoixVincent.setSelection(false);
				menuPopupVoixAnneCarole.setSelection(false);
				menuPopupVoixFabrice.setSelection(false);
				menuPopupVoixXavier.setSelection(false);
				menuPopupVoixSoldatInconnu.setSelection(true);
				menuVoixThierry.setSelection(false);
				menuVoixCeline.setSelection(false);
				menuVoixVincent.setSelection(false);
				menuVoixAnneCarole.setSelection(false);
				menuVoixFabrice.setSelection(false);
				menuVoixXavier.setSelection(false);
				menuVoixSoldatInconnu.setSelection(true);
				bouttonSelectionVoix.setText("Voix : "+menuPopupVoixSoldatInconnu.getText());
			}
		});
		
		//evenement de synchronisation (cool bar)
		bouttonSynchroniser.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				synchroniser();
			}
		});
		
		//evenement de synchronisation (menu)
		menuSynchroniser.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				synchroniser();
			}
		});
		
		//evenement de jouer (cool bar)
		bouttonJouer.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				jouer();
			}
		});
		
		//evenement de jouer (menu)
		menuJouer.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				jouer();
			}
		});
		
		//eveneemnt de stop (cool bar)
		bouttonStop.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				stopper();
			}
		});
		
		//evenement de stop
		menuStop.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				stopper();
			}
		});
		
		//evenement sur l a propos (menu)
		menuAPropos.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(aboutOpen == true)
				{
					if(ias != null)
					{
						ias.setActive();
					}
				}
				else
				{
					ias = new InterfaceAboutSinger(objetCourant);
					ias.open();
				}
			}
		});
		
		//evenement sur l aide (menu)
		menuAideSinger.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(helpOpen == true)
				{
					if(iais != null)
					{
						iais.setActive();
					}
				}
				else
				{
					helpOpen = true;
					iais = new InterfaceAideSinger(objetCourant);
					iais.open();
				}
			}
		});
		
		//evenement close sur la fenetre singer sivox
		fenetrePrincipale.addShellListener(new ShellListener() {
			public void shellActivated(ShellEvent arg0) {}
			public void shellClosed(ShellEvent arg0) {
				arg0.doit = false;
				quitter();
			}
			public void shellDeactivated(ShellEvent arg0) {}
			public void shellDeiconified(ShellEvent arg0) {}
			public void shellIconified(ShellEvent arg0) {}
		});
		
	}
	
	/**
	 * Methode qui ouvre la fenetre singer generale
	 * @author Ecole Polytechnique de Sophia Antipolis
	 *
	 */
	public void open()
	{
		fenetrePrincipale.open();
		while (!fenetrePrincipale.isDisposed() && (sortie == false))
	    {
	    	if (!i.getDisplay().readAndDispatch())
	    		i.getDisplay().sleep();
	    }
	    fenetrePrincipale.dispose();
	    i.setSingerSivoxClosed();
	    i.setActive();
	}
	
	/**
	 * Methode qui ajoute un nouveau chant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void creerNouveauChant()
	{
		folderChant.ajouterChant();
	}
	
	/**
	 * Methode qui ouvre un chant existant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void ouvrirChant()
	{
		try {
			folderChant.ouvrirChant();
		} catch (SIVOXException e) {
			setIconeWarning();
			setInformation(e.getMessage());
		}
	}
	
	/**
	 * Methode qui enregistre le chant courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param force True si on enregistre dans un nouveau fichier et false sinon
	 */
	public void enregistrer(boolean force)
	{
		try {
			folderChant.enregistrer(force);
		} catch (SIVOXException e) {
			setIconeWarning();
			setInformation(e.getMessage());
		}
	}
	
	/**
	 * Methode qui enregistre tous les chants
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void enregistrerTout()
	{
		try {
			folderChant.enregistrerTout();
		} catch (SIVOXException e) {
			setIconeWarning();
			setInformation(e.getMessage());
		}
	}
	
	/**
	 * Methode qui ferme le chant courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void fermer()
	{
		try {
			folderChant.fermerChant();
		} catch (SIVOXException e) {
			setIconeWarning();
			setInformation(e.getMessage());
		}
	}
	
	/**
	 * Methode qui ferme tous les chants
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void fermerTout()
	{
		try {
			folderChant.fermerAllChant();
		} catch (SIVOXException e) {
			setIconeWarning();
			setInformation(e.getMessage());
		}
	}
	
	/**
	 * Methode qui tente de quitter singer sivox en fermant les chants ouverts
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void quitter()
	{
		try {
			folderChant.fermerAllChant();
		} catch (SIVOXException e) {}
		if(folderChant.getItemCount() == 0)
		{
			sortie = true;
		}
	}
	
	/**
	 * Methode qui synchroniser le texte du chant courant avec sa melodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void synchroniser()
	{
		try {
			folderChant.synchroniser();
		} catch (SIVOXException e) {
			setIconeWarning();
			setInformation(e.getMessage());
		}
	}
	
	/**
	 * Methode qui chante le chant courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void jouer()
	{
		try {
			folderChant.jouer();
		} catch (ChantException c) {
			setIconeWarning();
			setInformation(c.getMessage());
		} catch (NoteException n) {
			setIconeWarning();
			setInformation(n.getMessage());
		}
	}
	
	/**
	 * Methode qui stoppe la lecture du chant courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void stopper()
	{
		try {
			folderChant.stopper();
		} catch (ChantException e) {
			setIconeWarning();
			setInformation(e.getMessage());
		}
	}
	
	/**
	 * Methode qui met le chant courant dans l'etat non a jour
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setNonAJour()
	{
		folderChant.setNonAJour();
	}
	
	/**
	 * Methode qui met le chant courant dans l'etat a jour
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setAJour()
	{
		folderChant.setAJour();
	}
	
	/**
	 * Methode qui maximise la partie melodie du chant courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserMelodie()
	{
		folderChant.maximiserMelodie();
	}
	
	/**
	 * Methode qui maximise la partie syllabe du chant courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserSyllabe()
	{
		folderChant.maximiserSyllabe();
	}
	
	/**
	 * Methode qui restaure une des maximisation
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void restaurerMaximisation()
	{
		folderChant.restaurerMaximisation();
	}
	
	/**
	 * Methode qui retourne la fenetre principale de singer sivox
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le fenetre principale
	 */
	public Shell getShell()
	{
		return(fenetrePrincipale);
	}
	
	/**
	 * Methode qui met la fenetre principale de singer sivox en active
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setActivate()
	{
		fenetrePrincipale.setActive();
	}
	
	/**
	 * Methode qui modifie la message d'information de singer sivox
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param info Le nouveau message d'information
	 */
	public void setInformation(String info)
	{
		information.setText(info);
	}
	
	/**
	 * Methode qui met l'icone d'information a info
	 * @author Ecole Polytechnique de Sophia Antipolis
	 *
	 */
	public void setIconeInformation()
	{
		try {
			information.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"information_16_16.png"));
		} catch (Exception e) {}
	}
	
	/**
	 * Methode qui met l'icone d'information a warning
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setIconeWarning()
	{
		try {
			information.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"attention_16_16.png"));
		} catch (Exception e) {}
	}
	
	/**
	 * Methode qui indique que about singer n'est plus ouvert
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setAboutClosed()
	{
		aboutOpen = false;
	}
	
	/**
	 * Methode qui indique que l'aide singer n'est plus ouverte
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setHelpClosed()
	{
		helpOpen = false;
	}
	
	/**
	 * Methode qui teste et configure les voix
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void configurerVoix()
	{
		String temp = "";
		String temp2 = "";
		int numeroVoixPlusLongue=0;
		for(int i = 1 ; i < 8 ; i++)
		{
			if(InformationSysteme.testerVoix(i))
			{
				temp2 = ConfigFile.rechercher("NOM_VOIX_"+String.valueOf(i));
				if((temp2 != null) && (InformationSysteme.testerVoix(i)))
				{
					if(temp2.length() > temp.length())
					{
						temp = temp2;
						numeroVoixPlusLongue=i;
					}
				}
			}
		}
		if(!InformationSysteme.testerVoix(1))
		{
			menuPopupVoixThierry.setEnabled(false);
			menuVoixThierry.setEnabled(false);
		}
		else
		{
			if(numeroVoixPlusLongue == 1)
			{
				menuPopupVoixThierry.setSelection(true);
				menuVoixThierry.setSelection(true);
			}
		}
		if(!InformationSysteme.testerVoix(2))
		{
			menuPopupVoixCeline.setEnabled(false);
			menuVoixCeline.setEnabled(false);
		}
		else
		{
			if(numeroVoixPlusLongue == 2)
			{
				menuPopupVoixCeline.setSelection(true);
				menuVoixCeline.setSelection(true);
			}
		}
		if(!InformationSysteme.testerVoix(3))
		{
			menuPopupVoixVincent.setEnabled(false);
			menuVoixVincent.setEnabled(false);
		}
		else
		{
			if(numeroVoixPlusLongue == 3)
			{
				menuPopupVoixVincent.setSelection(true);
				menuVoixVincent.setSelection(true);
			}
		}
		if(!InformationSysteme.testerVoix(4))
		{
			menuPopupVoixAnneCarole.setEnabled(false);
			menuVoixAnneCarole.setEnabled(false);
		}
		else
		{
			if(numeroVoixPlusLongue == 4)
			{
				menuPopupVoixAnneCarole.setSelection(true);
				menuVoixAnneCarole.setSelection(true);
			}
		}
		if(!InformationSysteme.testerVoix(5))
		{
			menuPopupVoixFabrice.setEnabled(false);
			menuVoixFabrice.setEnabled(false);
		}
		else
		{
			if(numeroVoixPlusLongue == 5)
			{
				menuPopupVoixFabrice.setSelection(true);
				menuVoixFabrice.setSelection(true);
			}
		}
		if(!InformationSysteme.testerVoix(6))
		{
			menuPopupVoixXavier.setEnabled(false);
			menuVoixXavier.setEnabled(false);
		}
		else
		{
			if(numeroVoixPlusLongue == 6)
			{
				menuPopupVoixXavier.setSelection(true);
				menuVoixXavier.setSelection(true);
			}
		}
		if(!InformationSysteme.testerVoix(7))
		{
			menuPopupVoixSoldatInconnu.setEnabled(false);
			menuVoixSoldatInconnu.setEnabled(false);
		}
		else
		{
			if(numeroVoixPlusLongue == 7)
			{
				menuPopupVoixSoldatInconnu.setSelection(true);
				menuVoixSoldatInconnu.setSelection(true);
			}
		}
	}
	
	/**
	 * Methode qui retourne le numero de la voix en cours
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le numero de voix
	 */
	public int getVoix()
	{
		if(menuVoixThierry.getSelection() == true)
			return(1);
		else if(menuVoixCeline.getSelection() == true)
			return(2);
		else if(menuVoixVincent.getSelection() == true)
			return(3);
		else if(menuVoixAnneCarole.getSelection() == true)
			return(4);
		else if(menuVoixFabrice.getSelection() == true)
			return(5);
		else if(menuVoixXavier.getSelection() == true)
			return(6);
		else
			return(7);
	}
}
