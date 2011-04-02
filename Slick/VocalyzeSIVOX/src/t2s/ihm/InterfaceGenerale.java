package t2s.ihm;

import t2s.*;
import t2s.exception.*;
import t2s.util.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * Classe de l'Interface generale de l'appication heritant de InterfaceBase
 * @author Ecole Polytechnique de Sophia Antipolis
 *
 */

public class InterfaceGenerale extends InterfaceBase {
	
	// mode (parlant ou silencieux)
	enum modeParlant { SILENCIEUX, PARLANT };
	private modeParlant mode = modeParlant.SILENCIEUX; 
	
	// numero de la prosodie ( 1 2 3 )
	private int choixNumeroProsodie = 1;
	
	// voix pour le mode parlant
	SIVOXDevint assistant = null;
	
	//module Singer
	InterfaceSingerGenerale singer = null;
	
	// booleen pour l aide et about
	private boolean aboutOpen = false;
	private boolean helpOpen = false;
	private boolean configurationSivoxOpen = false;
	private boolean singerSivoxOpen = false;
	
	// interface aide , about, configurationSivox
	private InterfaceAboutVocalyse iab = null;
	private InterfaceAideVocalyse iai = null;
	private InterfaceConfigurationSIVOX ics = null;
	
	// objet courant
	InterfaceGenerale objetCourant = this;
	
	// fenetre intro avec progressBar
	private Shell fenetreIntro = null;
	private ProgressBar bar = null;
	private Label labelIntro = null;
	private Label labelVersion = null;
	private FormData barData = null;
	private FormData labelIntroData = null;
	private FormData labelVersionData = null;
	
	// shell et display et booleen de sortie
	private Shell fenetrePrincipale = null;
	
	private FormLayout layoutCentral = null;
	boolean sortie = false;
	
	// label d'information de l application
	private CLabel information = null;
	private FormData infoData = null;
	
	// le ctabfolder des projets
	SIVOXTabFolder folderProjet = null;
	FormData folderProjetData = null;
	
	// menu popup du choix des voix
	private Menu menuPopupVoix = null;
	private MenuItem menuPopupVoixThierry = null;
	private MenuItem menuPopupVoixCeline = null;
	private MenuItem menuPopupVoixVincent = null;
	private MenuItem menuPopupVoixAnneCarole = null;
	private MenuItem menuPopupVoixFabrice =  null;
	private MenuItem menuPopupVoixXavier = null;
	private MenuItem menuPopupVoixSoldatInconnu = null;
	
	// menu popup du mode sonore
	private Menu menuPopupModeSonore = null;
	private MenuItem menuPopupModeSilencieux = null;
	private MenuItem menuPopupModeParlant = null;
	
	// menu principal
	private Menu mainMenu = null;
	
	// elements du menu FICHIER
	private MenuItem menuFichier = null;
	private Menu menuOptionFichier = null;
	private MenuItem menuNouveauProjet = null;
	private MenuItem menuOuvrirProjet = null;
	private MenuItem menuImporterTexte = null;
	private MenuItem menuImporterProsodie = null;
	private MenuItem menuImporterChant = null;
	private MenuItem menuEnregistrer = null;
	private MenuItem menuEnregistrerSous = null;
	private MenuItem menuEnregistrerProsodieSous = null;
	private MenuItem menuEnregistrerTout = null;
	private MenuItem menuFermer = null;
	private MenuItem menuFermerTout = null;
	private MenuItem menuQuitter = null;
	
	// element du menu PROSODIE
	private MenuItem menuProsodie = null;
	private Menu menuOptionProsodie = null;
	private MenuItem menuAjouterProsodie = null;
	private MenuItem menuRenommerProsodie = null;
	private MenuItem menuSupprimerProsodie = null;
	private MenuItem menuInsererFrequence = null;
	private MenuItem menuInsererRapidite = null;
	private MenuItem menuInsererCommentaire = null;
	private MenuItem menuSupprimerInsertion = null;
	
	// element du menu LECTURE
	private MenuItem menuLecture = null;
	private Menu menuOptionLecture = null;
	private MenuItem menuSelectionProsodie = null;
	private Menu menuOptionSelectionProsodie = null;
	private MenuItem menuProsodieBase = null;
	private MenuItem menuProsodie2 = null;
	private MenuItem menuProsodie3 = null;
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
	private MenuItem menuEnregistrerWav = null;
	
	//element du menu CHANT
	private MenuItem menuChant = null;
	private Menu menuOptionChant = null;
	private MenuItem menuSivoxSinger = null;

	// elements du menu AFFICHAGE
	private MenuItem menuAffichage = null;
	private Menu menuOptionAffichage = null;
	private MenuItem menuBarreOutil = null;
	private Menu menuOptionBarreOutil = null;
	private MenuItem menuGestionFichier = null;
	private MenuItem menuGestionPlayer = null;
	private MenuItem menuGestionVoix = null;
	private MenuItem menuGestionEnregistrementWav = null;
	private MenuItem menuGestionModeSonore = null;
	private MenuItem menuGestionSynchronisation = null;
	
	// elements du menu CONFIGURATION
	private MenuItem menuConfiguration = null;
	private Menu menuOptionConfiguration = null;
	private MenuItem menuModeSonore = null;
	private Menu menuOptionModeSonore = null;
	private MenuItem menuModeSilencieux = null;
	private MenuItem menuModeParlant = null;
	private MenuItem menuVoixModeSonore = null;
	private Menu menuOptionVoixModeSonore = null;
	private MenuItem menuVoixModeSonoreThierry = null;
	private MenuItem menuVoixModeSonoreCeline = null;
	private MenuItem menuVoixModeSonoreVincent = null;
	private MenuItem menuVoixModeSonoreAnneCarole = null;
	private MenuItem menuVoixModeSonoreFabrice = null;
	private MenuItem menuVoixModeSonoreXavier = null;
	private MenuItem menuVoixModeSonoreSoldatInconnu = null;
	private MenuItem menuConfigurationSiVox = null;
	
	// elements du menu AIDE
	private MenuItem menuAide = null;
	private Menu menuOptionAide = null;
	private MenuItem menuAPropos = null;
	private MenuItem menuAideSiVox = null;
	
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
	
	private ToolBar toolBarGestionWav = null;
	private CoolItem coolItemGestionWav = null;
	private ToolItem bouttonEnregistrerWav = null;
	
	private ToolBar toolBarGestionModeSonore = null;
	private CoolItem coolItemGestionModeSonore = null;
	private ToolItem bouttonModeSonore = null;
	
	// constructeur par defaut
	public InterfaceGenerale()
	{	
		//chaine temporaire
		String temp = "";
		
		// creation de l assistant
		assistant = new SIVOXDevint();
		
		// creation et affichage de la fenetre d introduction
		fenetreIntro = new Shell(ecran, SWT.NONE);
		fenetreIntro.setLayout(new FormLayout());
		fenetreIntro.setSize(640, 369);
		fenetreIntro.setText("Chargement...");
		fenetreIntro.setLocation(ecran.getClientArea().x+((ecran.getClientArea().width-640)/2),ecran.getClientArea().y+((ecran.getClientArea().height-369)/2));
		bar = new ProgressBar(fenetreIntro, SWT.SMOOTH);
		bar.setMinimum(0);
		bar.setMaximum(100);
		barData = new FormData();
		barData.left = new FormAttachment(20);
		barData.right = new FormAttachment(80);
		barData.top = new FormAttachment(93);
		barData.bottom = new FormAttachment(97);
		bar.setLayoutData(barData);
		labelVersion = new Label(fenetreIntro, SWT.FLAT);
		labelVersion.setBackground(new Color(ecran, 255, 255, 255));
		labelVersion.setText("2007");
		labelVersionData = new FormData();
		labelVersionData.left = new FormAttachment(94);
		labelVersionData.right = new FormAttachment(100);
		labelVersionData.top = new FormAttachment(95);
		labelVersionData.bottom = new FormAttachment(100);
		labelVersion.setLayoutData(labelVersionData);
		labelIntro = new Label(fenetreIntro, SWT.NONE);
		labelIntro.setBackground(new Color(ecran, 255, 255, 255));
		labelIntro.setForeground(new Color(ecran, 0, 0, 0));
		labelIntro.setText("Chargement...");
		labelIntroData = new FormData();
		labelIntroData.left = new FormAttachment(45);
		labelIntroData.right = new FormAttachment(65);
		labelIntroData.top = new FormAttachment(87);
		labelIntroData.bottom = new FormAttachment(93);
		labelIntro.setLayoutData(labelIntroData);
		try {
			fenetreIntro.setBackgroundImage(new Image(ecran, InformationSysteme.getRepertoireLogo()+"logo_sivox.png"));
			fenetreIntro.setImage(new Image(ecran, InformationSysteme.getRepertoireImage32()+"icone_application_32_32.png"));
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(fenetreIntro, SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("Le chargement des images a echoue, le programme doit quitter !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
		}
		fenetreIntro.open();
		bar.setSelection(0);
		getPause();
		
		// creation de la fenetre principale , fenetre d intro et de l ecran
		fenetrePrincipale = new Shell(ecran);
		fenetrePrincipale.setText("VOCALISE S.I. Vox");
		layoutCentral = new FormLayout();
		fenetrePrincipale.setLayout(layoutCentral);
		
		bar.setSelection(12);
		getPause();
		
		// creation de la COOLBAR
		try {
			barreOutil = new CoolBar(fenetrePrincipale, SWT.NONE);
			toolBarGestionFichier = new ToolBar(barreOutil, SWT.NONE);
			bouttonNouveau = new ToolItem(toolBarGestionFichier, SWT.PUSH);
			bouttonNouveau.setImage(new Image(ecran, InformationSysteme.getRepertoireImage32()+"nouveau_projet_32_32.png"));
			bouttonNouveau.setText("Nouveau Projet");
			bouttonNouveau.setToolTipText("Creer un nouveau projet sivox");
			bouttonOuvrir = new ToolItem(toolBarGestionFichier, SWT.PUSH);
			bouttonOuvrir.setImage(new Image(ecran, InformationSysteme.getRepertoireImage32()+"ouvrir_projet_32_32.png"));
			bouttonOuvrir.setText("Ouvrir Projet");
			bouttonOuvrir.setToolTipText("Ouvrir un projet sivox existant");
			bouttonSauver = new ToolItem(toolBarGestionFichier, SWT.PUSH);
			bouttonSauver.setImage(new Image(ecran, InformationSysteme.getRepertoireImage32()+"enregistrer_32_32.png"));
			bouttonSauver.setText("Enregistrer");
			bouttonSauver.setToolTipText("Enregistrer la projet courant");
			bouttonFermer = new ToolItem(toolBarGestionFichier, SWT.PUSH);
			bouttonFermer.setImage(new Image(ecran, InformationSysteme.getRepertoireImage32()+"fermer_32_32.png"));
			bouttonFermer.setText("Fermer");
			bouttonFermer.setToolTipText("Fermer le projet en cours");
			bouttonQuitter = new ToolItem(toolBarGestionFichier, SWT.PUSH);
			bouttonQuitter.setImage(new Image(ecran, InformationSysteme.getRepertoireImage32()+"quitter_32_32.png"));
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
			bouttonSynchroniser.setImage(new Image(ecran, InformationSysteme.getRepertoireImage32()+"synchroniser_prosodie_32_32.png"));
			bouttonSynchroniser.setText("Synchroniser");
			bouttonSynchroniser.setToolTipText("Synchroniser la prosodie courante");
			toolBarGestionSynchro.pack();
			size = toolBarGestionSynchro.getSize();
			coolItemGestionSynchro = new CoolItem(barreOutil, SWT.NONE);
			coolItemGestionSynchro.setControl(toolBarGestionSynchro);
			preferred = coolItemGestionSynchro.computeSize(size.x, size.y);
			coolItemGestionSynchro.setPreferredSize(preferred);
			coolItemGestionSynchro.setMinimumSize(preferred);
			
			toolBarGestionPlayer = new ToolBar(barreOutil, SWT.NONE);
			bouttonJouer = new ToolItem(toolBarGestionPlayer, SWT.PUSH);
			bouttonJouer.setImage(new Image(ecran, InformationSysteme.getRepertoireImage32()+"jouer_32_32.png"));
			bouttonJouer.setText("Jouer");
			bouttonJouer.setToolTipText("Jouer la prosodie");
			bouttonStop = new ToolItem(toolBarGestionPlayer, SWT.PUSH);
			bouttonStop.setImage(new Image(ecran, InformationSysteme.getRepertoireImage32()+"stop_32_32.png"));
			bouttonStop.setText("Stop");
			bouttonStop.setToolTipText("Arreter la lecture de la prosodie");
			toolBarGestionPlayer.pack();
			size = toolBarGestionPlayer.getSize();
			coolItemGestionPlayer = new CoolItem(barreOutil, SWT.NONE);
			coolItemGestionPlayer.setControl(toolBarGestionPlayer);
			preferred = coolItemGestionPlayer.computeSize(size.x, size.y);
			coolItemGestionPlayer.setPreferredSize(preferred);
			coolItemGestionPlayer.setMinimumSize(preferred);
			
			toolBarGestionVoix = new ToolBar(barreOutil, SWT.NONE);
			bouttonSelectionVoix = new ToolItem(toolBarGestionVoix, SWT.DROP_DOWN);
			bouttonSelectionVoix.setImage(new Image(ecran, InformationSysteme.getRepertoireImage32()+"selection_voix_32_32.png"));
			bouttonSelectionVoix.setText("Voix : "+getMaxStringVoix());
			bouttonSelectionVoix.setToolTipText("Voix de la prosodie");
			toolBarGestionVoix.pack();
			size = toolBarGestionVoix.getSize();
			coolItemGestionVoix = new CoolItem(barreOutil, SWT.NONE);
			coolItemGestionVoix.setControl(toolBarGestionVoix);
			preferred = coolItemGestionVoix.computeSize(size.x, size.y);
			coolItemGestionVoix.setPreferredSize(preferred);
			coolItemGestionVoix.setMinimumSize(preferred);
			
			toolBarGestionWav = new ToolBar(barreOutil, SWT.NONE);
			bouttonEnregistrerWav = new ToolItem(toolBarGestionWav, SWT.PUSH);
			bouttonEnregistrerWav.setImage(new Image(ecran, InformationSysteme.getRepertoireImage32()+"enregistrer_wav_32_32.png"));
			bouttonEnregistrerWav.setText("Enregistrer le Wav");
			bouttonEnregistrerWav.setToolTipText("Enregistrer la prosodie en wav");
			toolBarGestionWav.pack();
			size = toolBarGestionWav.getSize();
			coolItemGestionWav = new CoolItem(barreOutil, SWT.NONE);
			coolItemGestionWav.setControl(toolBarGestionWav);
			preferred = coolItemGestionWav.computeSize(size.x, size.y);
			coolItemGestionWav.setPreferredSize(preferred);
			coolItemGestionWav.setMinimumSize(preferred);
			
			toolBarGestionModeSonore = new ToolBar(barreOutil, SWT.NONE);
			bouttonModeSonore = new ToolItem(toolBarGestionModeSonore, SWT.DROP_DOWN);
			bouttonModeSonore.setImage(new Image(ecran, InformationSysteme.getRepertoireImage32()+"mode_sonore_32_32.png"));
			bouttonModeSonore.setText("Mode Sonore : Silencieux");
			bouttonModeSonore.setToolTipText("Mode sonore de l'application");
			toolBarGestionModeSonore.pack();
			size = toolBarGestionModeSonore.getSize();
			coolItemGestionModeSonore = new CoolItem(barreOutil, SWT.NONE);
			coolItemGestionModeSonore.setControl(toolBarGestionModeSonore);
			preferred = coolItemGestionModeSonore.computeSize(size.x, size.y);
			coolItemGestionModeSonore.setPreferredSize(preferred);
			coolItemGestionModeSonore.setMinimumSize(preferred);
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(fenetrePrincipale, SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("Le chargement des images a echoue, le programme doit quitter !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
	        sortie = true;
		}
		
		coolData = new FormData();
		coolData.left = new FormAttachment(0);
		coolData.right = new FormAttachment(100);
		coolData.top = new FormAttachment(0);
		barreOutil.setLayoutData(coolData);
		
		bar.setSelection(24);
		getPause();
		
		// creation du MENU POPUP du mode sonore
		menuPopupModeSonore = new Menu(fenetrePrincipale, SWT.POP_UP);
		menuPopupModeSilencieux = new MenuItem(menuPopupModeSonore, SWT.RADIO);
		menuPopupModeSilencieux.setText("Mode silencieux");
		menuPopupModeParlant = new MenuItem(menuPopupModeSonore, SWT.RADIO);
		menuPopupModeParlant.setText("Mode parlant");
		menuPopupModeSilencieux.setSelection(true);
		
		// creation du MENU POPUP des voix
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
		
		bar.setSelection(36);
		getPause();
		
	    // creation du MENU dans la fenetre principale
		mainMenu = new Menu(fenetrePrincipale, SWT.BAR);
		
		// ZONE FICHIER
		menuFichier = new MenuItem(mainMenu, SWT.CASCADE);
		menuFichier.setText("&Fichier");
		menuOptionFichier = new Menu(fenetrePrincipale, SWT.DROP_DOWN);
		menuNouveauProjet = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuNouveauProjet.setText("&Nouveau projet\tCtrl+N");
		menuNouveauProjet.setAccelerator(SWT.CTRL+'N');
		menuOuvrirProjet = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuOuvrirProjet.setText("&Ouvrir un projet...\tCtrl+O");
		menuOuvrirProjet.setAccelerator(SWT.CTRL+'O');
		new MenuItem(menuOptionFichier, SWT.SEPARATOR);
		menuImporterTexte = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuImporterTexte.setText("Import&er un texte...\tCtrl+E");
		menuImporterTexte.setAccelerator(SWT.CTRL+'E');
		menuImporterProsodie = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuImporterProsodie.setText("Im&porter une prosodie...\tCtrl+P");
		menuImporterProsodie.setAccelerator(SWT.CTRL+'P');
		menuImporterChant = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuImporterChant.setText("&Importer un chant...\tCtrl+D");
		menuImporterChant.setAccelerator(SWT.CTRL+'D');
		new MenuItem(menuOptionFichier, SWT.SEPARATOR);
		menuEnregistrer = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuEnregistrer.setText("Enregi&strer le projet\tCtrl+S");
		menuEnregistrer.setAccelerator(SWT.CTRL+'S');
		menuEnregistrerSous = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuEnregistrerSous.setText("Enregistrer le projet sous...");
		menuEnregistrerProsodieSous = new MenuItem(menuOptionFichier, SWT.PUSH);
		menuEnregistrerProsodieSous.setText("Enregistrer la prosodie ...");
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
		
		bar.setSelection(48);
		getPause();
		
		// ZONE LECTURE
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
		new MenuItem(menuOptionLecture, SWT.SEPARATOR);
		menuEnregistrerWav = new MenuItem(menuOptionLecture, SWT.PUSH);
		menuEnregistrerWav.setText("Enregistrer le &Wav...\tCtrl+W");
		menuEnregistrerWav.setAccelerator(SWT.CTRL+'W');
		menuSelectionVoix.setMenu(menuOptionSelectionVoix);
		menuLecture.setMenu(menuOptionLecture);
		
		// ZONE PROSODIE
		menuProsodie = new MenuItem(mainMenu, SWT.CASCADE);
		menuProsodie.setText("&Prosodie");
		menuOptionProsodie = new Menu(fenetrePrincipale, SWT.DROP_DOWN);
		menuSelectionProsodie = new MenuItem(menuOptionProsodie, SWT.CASCADE);
		menuSelectionProsodie.setText("Selection de l'algorithme de prosodie");
		menuSelectionProsodie.setAccelerator(SWT.CTRL+'I');
		menuOptionSelectionProsodie = new Menu(fenetrePrincipale, SWT.DROP_DOWN);
		menuProsodieBase = new MenuItem(menuOptionSelectionProsodie, SWT.RADIO);
		menuProsodieBase.setText("Algorithme 1");
		menuProsodie2 = new MenuItem(menuOptionSelectionProsodie, SWT.RADIO);
		menuProsodie2.setText("Algorithme 2");
		menuProsodie3 = new MenuItem(menuOptionSelectionProsodie, SWT.RADIO);
		menuProsodie3.setText("Algorithme 3");
		menuProsodieBase.setSelection(true);
		new MenuItem(menuOptionProsodie, SWT.SEPARATOR);
		menuAjouterProsodie = new MenuItem(menuOptionProsodie, SWT.PUSH);
		menuAjouterProsodie.setText("&Ajouter une nouvelle prosodie");
		menuRenommerProsodie = new MenuItem(menuOptionProsodie, SWT.PUSH);
		menuRenommerProsodie.setText("&Renommer la prosodie...");
		menuSupprimerProsodie = new MenuItem(menuOptionProsodie, SWT.PUSH);
		menuSupprimerProsodie.setText("&Supprimer la prosodie");
		new MenuItem(menuOptionProsodie, SWT.SEPARATOR);
		menuInsererFrequence = new MenuItem(menuOptionProsodie, SWT.PUSH);
		menuInsererFrequence.setText("&Inserer une ligne de frequence...");
		menuInsererRapidite = new MenuItem(menuOptionProsodie, SWT.PUSH);
		menuInsererRapidite.setText("&Inserer une ligne de rapidite...");
		menuInsererCommentaire = new MenuItem(menuOptionProsodie, SWT.PUSH);
		menuInsererCommentaire.setText("&Inserer une ligne de commentaire...");
		menuSupprimerInsertion = new MenuItem(menuOptionProsodie, SWT.PUSH);
		menuSupprimerInsertion.setText("&Supprimer la/les ligne(s)");
		menuSelectionProsodie.setMenu(menuOptionSelectionProsodie);
		menuProsodie.setMenu(menuOptionProsodie);
		
		bar.setSelection(60);
		getPause();
		
		//ZONE CHANT
		menuChant = new MenuItem(mainMenu, SWT.CASCADE);
		menuChant.setText("&Chant");
		menuOptionChant = new Menu(fenetrePrincipale, SWT.DROP_DOWN);
		menuSivoxSinger = new MenuItem(menuOptionChant, SWT.CASCADE);
		menuSivoxSinger.setText("Lancer Singer S.I. Vox");
		menuChant.setMenu(menuOptionChant);
		
		// ZONE AFFICHAGE
		menuAffichage = new MenuItem(mainMenu, SWT.CASCADE);
		menuAffichage.setText("&Affichage");
		menuOptionAffichage = new Menu(fenetrePrincipale, SWT.DROP_DOWN);
		menuBarreOutil = new MenuItem(menuOptionAffichage, SWT.CASCADE);
		menuBarreOutil.setText("Barre d'outil");
		menuOptionBarreOutil = new Menu(fenetrePrincipale, SWT.DROP_DOWN);
		menuGestionFichier = new MenuItem(menuOptionBarreOutil, SWT.CHECK);
		menuGestionFichier.setText("Gestion des fichiers\tCtrl+H");
		menuGestionFichier.setAccelerator(SWT.CTRL+'H');
		menuGestionFichier.setSelection(true);
		menuGestionPlayer = new MenuItem(menuOptionBarreOutil, SWT.CHECK);
		menuGestionPlayer.setText("Gestion du player\tCtrl+Y");
		menuGestionPlayer.setAccelerator(SWT.CTRL+'Y');
		menuGestionPlayer.setSelection(true);
		menuGestionVoix = new MenuItem(menuOptionBarreOutil, SWT.CHECK);
		menuGestionVoix.setText("Gestion des voix\tCtrl+G");
		menuGestionVoix.setAccelerator(SWT.CTRL+'G');
		menuGestionVoix.setSelection(true);
		menuGestionEnregistrementWav = new MenuItem(menuOptionBarreOutil, SWT.CHECK);
		menuGestionEnregistrementWav.setText("Gestion d'enregistrement Wav\tCtrl+K");
		menuGestionEnregistrementWav.setAccelerator(SWT.CTRL+'K');
		menuGestionEnregistrementWav.setSelection(true);
		menuGestionModeSonore = new MenuItem(menuOptionBarreOutil, SWT.CHECK);
		menuGestionModeSonore.setText("Gestion du mode sonore\tCtrl+Z");
		menuGestionModeSonore.setAccelerator(SWT.CTRL+'Z');
		menuGestionModeSonore.setSelection(true);
		menuGestionSynchronisation = new MenuItem(menuOptionBarreOutil, SWT.CHECK);
		menuGestionSynchronisation.setText("Gestion de synchronisation\tCtrl+U");
		menuGestionSynchronisation.setAccelerator(SWT.CTRL+'U');
		menuGestionSynchronisation.setSelection(true);
		menuBarreOutil.setMenu(menuOptionBarreOutil);
		menuAffichage.setMenu(menuOptionAffichage);
		
		bar.setSelection(72);
		getPause();
		
		// ZONE CONFIGURATION
		menuConfiguration = new MenuItem(mainMenu, SWT.CASCADE);
		menuConfiguration.setText("Co&nfiguration");
		menuOptionConfiguration = new Menu(fenetrePrincipale, SWT.DROP_DOWN);
		menuVoixModeSonore = new MenuItem(menuOptionConfiguration, SWT.CASCADE);
		menuVoixModeSonore.setText("Voix du mode sonore");
		menuOptionVoixModeSonore = new Menu(fenetrePrincipale, SWT.DROP_DOWN);
		menuVoixModeSonoreThierry = new MenuItem(menuOptionVoixModeSonore, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_1"))!= null)
			menuVoixModeSonoreThierry.setText(temp);
		else
			menuVoixModeSonoreThierry.setText("Thierry");
		menuVoixModeSonoreCeline = new MenuItem(menuOptionVoixModeSonore, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_2"))!= null)
			menuVoixModeSonoreCeline.setText(temp);
		else
			menuVoixModeSonoreCeline.setText("Celine");
		menuVoixModeSonoreVincent = new MenuItem(menuOptionVoixModeSonore, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_3"))!= null)
			menuVoixModeSonoreVincent.setText(temp);
		else
			menuVoixModeSonoreVincent.setText("Vincent");
		menuVoixModeSonoreAnneCarole = new MenuItem(menuOptionVoixModeSonore, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_4"))!= null)
			menuVoixModeSonoreAnneCarole.setText(temp);
		else
			menuVoixModeSonoreAnneCarole.setText("Anne Carole");
		menuVoixModeSonoreFabrice = new MenuItem(menuOptionVoixModeSonore, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_5"))!= null)
			menuVoixModeSonoreFabrice.setText(temp);
		else
			menuVoixModeSonoreFabrice.setText("Fabrice");
		menuVoixModeSonoreXavier = new MenuItem(menuOptionVoixModeSonore, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_6"))!= null)
			menuVoixModeSonoreXavier.setText(temp);
		else
			menuVoixModeSonoreXavier.setText("Xavier");
		menuVoixModeSonoreSoldatInconnu = new MenuItem(menuOptionVoixModeSonore, SWT.RADIO);
		if((temp = ConfigFile.rechercher("NOM_VOIX_7"))!= null)
			menuVoixModeSonoreSoldatInconnu.setText(temp);
		else
			menuVoixModeSonoreSoldatInconnu.setText("Solat inconnu");
		menuModeSonore = new MenuItem(menuOptionConfiguration, SWT.CASCADE);
		menuModeSonore.setText("Mode sonore");
		menuOptionModeSonore = new Menu(fenetrePrincipale, SWT.DROP_DOWN);
		menuModeSilencieux = new MenuItem(menuOptionModeSonore, SWT.RADIO);
		menuModeSilencieux.setText("Mode silencieux");
		menuModeSilencieux.setSelection(true);
		menuModeParlant = new MenuItem(menuOptionModeSonore, SWT.RADIO);
		menuModeParlant.setText("Mode parlant");
		menuConfigurationSiVox = new MenuItem(menuOptionConfiguration, SWT.PUSH);
		menuConfigurationSiVox.setText("Configuration de VOCALYSE Si-Vox...\tCtrl+X");
		menuConfigurationSiVox.setAccelerator(SWT.CTRL+'X');
		menuVoixModeSonore.setMenu(menuOptionVoixModeSonore);
		menuModeSonore.setMenu(menuOptionModeSonore);
		menuConfiguration.setMenu(menuOptionConfiguration);
		
		bar.setSelection(84);
		getPause();
		
		// ZONE AIDE
		menuAide = new MenuItem(mainMenu, SWT.CASCADE);
		menuAide.setText("&Aide");
		menuOptionAide = new Menu(fenetrePrincipale, SWT.DROP_DOWN);
		menuAPropos = new MenuItem(menuOptionAide, SWT.PUSH);
		menuAPropos.setText("A Propos...\tAlt+F1");
		menuAPropos.setAccelerator(SWT.F1);
		menuAideSiVox = new MenuItem(menuOptionAide, SWT.PUSH);
		menuAideSiVox.setText("Aide VOCALYSE Si-Vox\tAlt+F2");
		menuAideSiVox.setAccelerator(SWT.F2);
		menuAide.setMenu(menuOptionAide);
		
		bar.setSelection(96);
		getPause();
		
		// affectation des images au menus
		try {
			fenetrePrincipale.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"icone_application_16_16.png"));
			menuNouveauProjet.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"nouveau_projet_16_16.png"));
			menuOuvrirProjet.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"ouvrir_projet_16_16.png"));
			menuImporterTexte.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"importer_texte_16_16.png"));
			menuImporterProsodie.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"importer_prosodie_16_16.png"));
			menuImporterChant.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"importer_chant_16_16.png"));
			menuEnregistrer.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"enregistrer_16_16.png"));
			menuEnregistrerTout.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"enregistrer_tout_16_16.png"));
			menuFermer.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"fermer_16_16.png"));
			menuQuitter.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"quitter_16_16.png"));
			menuAjouterProsodie.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"nouvelle_prosodie_16_16.png"));
			menuRenommerProsodie.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"renommer_prosodie_16_16.png"));
			menuSupprimerProsodie.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"supprimer_prosodie_16_16.png"));
			menuInsererFrequence.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"inserer_frequence_16_16.png"));
			menuInsererRapidite.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"inserer_rapidite_16_16.png"));
			menuInsererCommentaire.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"inserer_commentaire_16_16.png"));
			menuSupprimerInsertion.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"supprimer_ligne_16_16.png"));
			menuSivoxSinger.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"icone_application_singer_16_16.png"));
			menuGestionFichier.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"gestion_fichier_16_16.png"));
			menuGestionPlayer.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"gestion_player_16_16.png"));
			menuGestionVoix.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"gestion_voix_16_16.png"));
			menuGestionEnregistrementWav.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"gestion_enregistrement_wav_16_16.png"));
			menuGestionModeSonore.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"gestion_mode_sonore_16_16.png"));
			menuGestionSynchronisation.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"synchroniser_prosodie_16_16.png"));
			menuSelectionVoix.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"selection_voix_16_16.png"));
			menuSelectionProsodie.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"selection_prosodie_16_16.png"));
			menuSynchroniser.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"synchroniser_prosodie_16_16.png"));
			menuJouer.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"jouer_16_16.png"));
			menuStop.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"stop_16_16.png"));
			menuEnregistrerWav.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"enregistrer_wav_16_16.png"));
			menuVoixModeSonore.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"enceinte_16_16.png"));
			menuModeSonore.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"mode_sonore_16_16.png"));
			menuConfigurationSiVox.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"configuration_sivox_16_16.png"));
			menuAPropos.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"a_propos_16_16.png"));
			menuAideSiVox.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"aide_sivox_16_16.png"));
		} catch(Exception e) {
			MessageBox messageBox = new MessageBox(fenetrePrincipale, SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("Le chargement des images a echoue, le programme doit quitter !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
	        sortie = true;
		}
		
		// affectation de la barre de menu a la fenetre
		fenetrePrincipale.setMenuBar(mainMenu);
		bar.setSelection(100);
		
		// creation et placement du CLabel d information
		information = new CLabel(fenetrePrincipale, SWT.SHADOW_IN | SWT.LEFT);
		information.setText("Pret");
		infoData = new FormData();
		infoData.left = new FormAttachment(0);
		infoData.right = new FormAttachment(100);
		infoData.bottom = new FormAttachment(100);
		information.setLayoutData(infoData);
		
		// creation du SIVOXTabfolder
		folderProjet = new SIVOXTabFolder(this, fenetrePrincipale, SWT.MULTI | SWT.RESIZE | SWT.CLOSE);
		folderProjet.setBorderVisible(true);
		folderProjet.setLayoutData(new GridData(SWT.FILL, SWT.FILL, false, false));
		folderProjet.setSimple(false);
		folderProjet.setMinimizeVisible(false);
		folderProjet.setMaximizeVisible(false);
		
		folderProjetData = new FormData();
		folderProjetData.left = new FormAttachment(0);
		folderProjetData.right = new FormAttachment(100);
		folderProjetData.top = new FormAttachment(barreOutil);
		folderProjetData.bottom = new FormAttachment(information);
		folderProjet.setLayoutData(folderProjetData);
		
		configurerVoix();
		
	    //-----------------------------------------------------------------
	    //-EVENEMENTS------------------------------------------------------
	    //-----------------------------------------------------------------
	    
		// auto resize kan on bouge la coolBar
		barreOutil.addListener(SWT.Resize, new Listener() {
		      public void handleEvent(Event event) {
		    	  fenetrePrincipale.layout();
		      }
		});
		
		
		// evenement sur le boutton fermer (croix rouge)
		fenetrePrincipale.addShellListener(new ShellListener() {

			public void shellActivated(ShellEvent arg0) {}
			public void shellClosed(ShellEvent arg0) {
				arg0.doit = false;
				objetCourant.quitter();
			}
			public void shellDeactivated(ShellEvent arg0) {}
			public void shellDeiconified(ShellEvent arg0) {}
			public void shellIconified(ShellEvent arg0) {}
		});
		
		
	    // EVENEMENTS COOLBAR
	    
	    // evenement sur le clic nouveau projet de la coolbar
		bouttonNouveau.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(mode == modeParlant.PARLANT)
				{
					lectureAssistant("nouveau projet ess i vox");
				}
				InterfaceNouveauProjet ip = new InterfaceNouveauProjet(objetCourant,"");
				if(!ip.open() && (mode == modeParlant.PARLANT))
				{
					lectureAssistant("annulation");
				}
			}
		});
		
		// evenement sur le clic ouvrir un projet
		bouttonOuvrir.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(mode == modeParlant.PARLANT)
				{
					lectureAssistant("ouvrir un projet ess i vox");
				}
				try {
					folderProjet.ouvrirProjet();
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// evenement sur le clic sauver le projet courant (enregistrer)
		bouttonSauver.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.sauverProjet(false);
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// evenement sur le clic fermer le projet courant
		bouttonFermer.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				folderProjet.fermerProjet();
			}
		});
		
		// evenement sur le clic quitter l application
		bouttonQuitter.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				objetCourant.quitter();
			}
		});
		
		// evenement sur le clic prosodie de base
		menuProsodieBase.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				choixNumeroProsodie = 1;
			}
		});
		
		// evenement sur le clic prosodie 2
		menuProsodie2.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				choixNumeroProsodie = 2;
			}
		});
		
		// evenement sur le clic prosodie 3
		menuProsodie3.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				choixNumeroProsodie = 3;
			}
		});
		// evenement sur le clic synchroniser
		bouttonSynchroniser.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.synchroniserProsodie();
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// evenement sur le clic play
		bouttonJouer.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.jouerProsodie();
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// evenement sur le clic stop
		bouttonStop.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.stopperProsodie();
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// evenement du boutton Mode sonore (menu popup)
		bouttonModeSonore.addListener(SWT.Selection, new Listener() {
			  public void handleEvent(Event event) {
			    if (event.detail == SWT.ARROW) {
			      Rectangle rect = bouttonModeSonore.getBounds();
			      Point pt = new Point(rect.x, rect.y + rect.height);
			      pt = toolBarGestionModeSonore.toDisplay(pt);
			      menuPopupModeSonore.setLocation(pt.x, pt.y);
			      menuPopupModeSonore.setVisible(true);
			      if(mode == modeParlant.PARLANT)
			      {
			    	  lectureAssistant("Sailection du mode sonore");
			      }
			    }
			  }
			});
		
		// evenement du menu popup du mode sonore
		menuPopupModeSilencieux.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(menuPopupModeSilencieux.getSelection() == true)
				{
					menuPopupModeSilencieux.setSelection(true);
					menuPopupModeParlant.setSelection(false);
					menuModeSilencieux.setSelection(true);
					menuModeParlant.setSelection(false);
					bouttonModeSonore.setText("Mode Sonore : Silencieux");
					mode = modeParlant.SILENCIEUX;
					objetCourant.setIconInformationInfo();
					objetCourant.setInformation("Selection du mode SILENCIEUX");
					lectureAssistant("mode silencieu actife");
				}
			}
		});
		
		menuPopupModeParlant.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(menuPopupModeParlant.getSelection() == true)
				{
					menuPopupModeSilencieux.setSelection(false);
					menuPopupModeParlant.setSelection(true);
					menuModeSilencieux.setSelection(false);
					menuModeParlant.setSelection(true);
					bouttonModeSonore.setText("Mode Sonore : Parlant");
					mode = modeParlant.PARLANT;
					objetCourant.setIconInformationInfo();
					objetCourant.setInformation("Selection du mode PARLANT");
					lectureAssistant("mode parlan actife");
				}
			}
		});
		
		// evenement du boutton selection voix (popup)
		bouttonSelectionVoix.addListener(SWT.Selection, new Listener() {
			  public void handleEvent(Event event) {
			    if (event.detail == SWT.ARROW) {
			      Rectangle rect = bouttonSelectionVoix.getBounds();
			      Point pt = new Point(rect.x, rect.y + rect.height);
			      pt = toolBarGestionVoix.toDisplay(pt);
			      menuPopupVoix.setLocation(pt.x, pt.y);
			      menuPopupVoix.setVisible(true);
			      if(mode == modeParlant.PARLANT)
			      {
			    	  lectureAssistant("Sailection des voi");
			      }
			    }
			  }
			});
		
		// evenement des menu des voix
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
		
		bouttonEnregistrerWav.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.sauverWav();
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant("enregistrement du son waiiv raiussi");
					}
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// EVENEMENT MENU PRINCIPAL
		
		
		// evenement du menu nouveau projet
		menuNouveauProjet.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(mode == modeParlant.PARLANT)
				{
					lectureAssistant("nouveau projet ess i vox");
				}
				InterfaceNouveauProjet ip = new InterfaceNouveauProjet(objetCourant,"");
				if(!ip.open() && (mode == modeParlant.PARLANT))
				{
					lectureAssistant("annulation");
				}
			}
		});
		
		// evenement du menu ouvrir projet
		menuOuvrirProjet.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(mode == modeParlant.PARLANT)
				{
					lectureAssistant("ouvrir un projet ess i vox");
				}
				try {
					folderProjet.ouvrirProjet();
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// evenement du menu importer texte
		menuImporterTexte.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(mode == modeParlant.PARLANT)
				{
					lectureAssistant("importai un texte");
				}
				FileDialog browser = new FileDialog(fenetrePrincipale, SWT.OPEN);
				browser.setFileName("");
				browser.setText("Importer un texte...");
				browser.setFilterNames(new String [] {"Fichier texte (*.txt)","Tous les fichiers (*.*)"});
				browser.setFilterExtensions(new String [] {"*.txt","*.*"});
				String chaine = browser.open();
				if(chaine != null)
				{
					InterfaceNouveauProjet inp = new InterfaceNouveauProjet(objetCourant,"(importation texte)");
					if(inp.open() == true)
					{
						try {
							folderProjet.importerText(chaine);
						} catch (SIVOXException z) {
							setIconInformationWarning();
							setInformation(z.getMessage());
							if(mode == modeParlant.PARLANT)
							{
								lectureAssistant(z.getMessageSonore());
							}
						}
					}
				}
				else
				{
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant("anulation");
					}
					setIconInformationInfo();
					setInformation("Annulation d'importation de texte");
				}
			}
		});
		
		// evenmeent sur le menu importer prosodie
		menuImporterProsodie.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(mode == modeParlant.PARLANT)
				{
					lectureAssistant("importai une prosodi");
				}
				FileDialog browser = new FileDialog(fenetrePrincipale, SWT.OPEN);
				browser.setFileName("");
				browser.setText("Importer une prosodie...");
				browser.setFilterNames(new String [] {"Fichier prosodie (*.pho)"});
				browser.setFilterExtensions(new String [] {"*.pho"});
				String chaine = browser.open();
				if(chaine != null)
				{
					InterfaceNouveauProjet inp = new InterfaceNouveauProjet(objetCourant,"(importation prosodie)");
					if(inp.open() == true)
					{
						try {
							folderProjet.importerProsodie(chaine);
						} catch (SIVOXException z) {
							setIconInformationWarning();
							setInformation(z.getMessage());
							if(mode == modeParlant.PARLANT)
							{
								lectureAssistant(z.getMessageSonore());
							}
						}
					}
				}
				else
				{
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant("anulation");
					}
					objetCourant.setIconInformationInfo();
					objetCourant.setInformation("Annulation d'importation de prosodie");
				}
			}
		});
		
		// evenement sur l'importation de chant
		menuImporterChant.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(mode == modeParlant.PARLANT)
				{
					lectureAssistant("importeai un chan");
				}
				FileDialog browser = new FileDialog(fenetrePrincipale, SWT.OPEN);
				browser.setFileName("");
				browser.setText("Importer un chant...");
				browser.setFilterNames(new String [] {"Fichier chant (*.svc)"});
				browser.setFilterExtensions(new String [] {"*.svc"});
				String chaine = browser.open();
				if(chaine != null)
				{
					InterfaceNouveauProjet inp = new InterfaceNouveauProjet(objetCourant,"(importation chant)");
					if(inp.open() == true)
					{
						try {
							folderProjet.importerChant(chaine);
						} catch (SIVOXException z) {
							setIconInformationWarning();
							setInformation(z.getMessage());
							if(mode == modeParlant.PARLANT)
							{
								lectureAssistant(z.getMessageSonore());
							}
						}
					}
				}
				else
				{
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant("anulation");
					}
					objetCourant.setIconInformationInfo();
					objetCourant.setInformation("Annulation d'importation de chant");
				}	
			}
		});
		
		// evenement du menu enregistrer
		menuEnregistrer.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.sauverProjet(false);
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// evenement du menu enregistrer sous
		menuEnregistrerSous.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.sauverProjet(true);
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		menuEnregistrerProsodieSous.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.enregistrerProsodie();
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// evenement du menu enregistrer tout
		menuEnregistrerTout.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.sauverAllProjets();
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// evenement du menu fermer
		menuFermer.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				folderProjet.fermerProjet();
			}
		});
		
		// evenement du menu fermer tous les projet / fermer tout
		menuFermerTout.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
					folderProjet.fermerAllProjets();
			}
		});
		
		// evenement du menu quitter
		menuQuitter.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				objetCourant.quitter();
			}
		});
		
		// evenement de l ajout de prosodie
		menuAjouterProsodie.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					
					folderProjet.ajouterProsodie();
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant("prosodi ajoutai corectement");
					}
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// eveneement de renommage de la prosodie
		menuRenommerProsodie.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.renommerProsodie();
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant("la prosodi a aitai renommai correctement");
					}
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// evenement de suppression de prosodie
		menuSupprimerProsodie.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.supprimerProsodie();
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant("la prosodi a aitai supprimai correctement");
					}
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		menuInsererFrequence.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.insererFrequence();
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant("la fraiquence a aitai correctement ajoutai");
					}
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		menuInsererRapidite.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.insererRapidite();
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant("la rapiditai a aitai correctement ajoutai");
					}
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		menuInsererCommentaire.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.insererCommentaire();
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant("le commentaire a aitai correctement ajoutai");
					}
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		menuSupprimerInsertion.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.supprimerInsertion();
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant("linsertion a aitai correctement supprimai");
					}
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// evenement du menu synchroniser
		menuSynchroniser.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.synchroniserProsodie();
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		
		// evenement du menu play
		menuJouer.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.jouerProsodie();
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// evenement du menu stop
		menuStop.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.stopperProsodie();
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// evenement sur le menu enregistrer wav
		menuEnregistrerWav.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				try {
					folderProjet.sauverWav();
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant("enregistrement du son waiiv raiussi");
					}
				} catch (SIVOXException z) {
					setIconInformationWarning();
					setInformation(z.getMessage());
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant(z.getMessageSonore());
					}
				}
			}
		});
		
		// evenement du menu de la selection des voix
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
		
		// evenement sur les menu voix du mode sonore
		menuVoixModeSonoreThierry.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				assistant.setVoix(1);
			}
		});
		
		menuVoixModeSonoreCeline.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				assistant.setVoix(2);
			}
		});
		
		menuVoixModeSonoreVincent.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				assistant.setVoix(3);
			}
		});
		
		menuVoixModeSonoreAnneCarole.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				assistant.setVoix(4);
			}
		});
		
		menuVoixModeSonoreFabrice.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				assistant.setVoix(5);
			}
		});
		
		menuVoixModeSonoreXavier.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				assistant.setVoix(6);
			}
		});
		
		menuVoixModeSonoreSoldatInconnu.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				assistant.setVoix(7);
			}
		});
		
		// evenement sur le menu du mode silencieux
		menuModeSilencieux.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(menuModeSilencieux.getSelection() == true)
				{
					menuPopupModeSilencieux.setSelection(true);
					menuPopupModeParlant.setSelection(false);
					menuModeSilencieux.setSelection(true);
					menuModeParlant.setSelection(false);
					bouttonModeSonore.setText("Mode Sonore : Silencieux");
					mode = modeParlant.SILENCIEUX;
					objetCourant.setInformation("Selection du mode SILENCIEUX");
					lectureAssistant("mode silencieu actife");
				}
			}
		});
		
		// evenement sur le menu du mode parlant
		menuModeParlant.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(menuModeParlant.getSelection() == true)
				{
					menuPopupModeSilencieux.setSelection(false);
					menuPopupModeParlant.setSelection(true);
					menuModeSilencieux.setSelection(false);
					menuModeParlant.setSelection(true);
					bouttonModeSonore.setText("Mode Sonore : Parlant");
					mode = modeParlant.PARLANT;
					objetCourant.setInformation("Selection du mode PARLANT");
					lectureAssistant("mode parlan actife");
				}
			}
		});
		
		//evenement sur le menu Sivox Singer
		menuSivoxSinger.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				//creation d un sivox singer si pas lancé
				if(singerSivoxOpen == false)
				{
					lectureAssistant("lancement de si ngueur essi vox");
					singerSivoxOpen = true;
					singer = new InterfaceSingerGenerale(objetCourant);
					singer.open();
				}
				else
				{
					if(singer != null)
					{
						singer.setActivate();
					}
				}
			}
		});
		
		// evenement sur le menu configuration si vox
		menuConfigurationSiVox.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(configurationSivoxOpen == false)
				{
					configurationSivoxOpen = true;
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant("configuration de la ess i vox");
					}
					objetCourant.setIconInformationInfo();
					objetCourant.setInformation("Ouverture de la configuration Si-Vox");
					ics = new InterfaceConfigurationSIVOX(objetCourant);
					ics.open();
				}
				else
				{
					if(ics != null)
						ics.setActive();
				}
			}
		});
		
		// evenement appui sur le menu a propos
		menuAPropos.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(aboutOpen == false)
				{
					aboutOpen = true;
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant("apropo");
					}
					objetCourant.setIconInformationInfo();
					objetCourant.setInformation("A propos de VOCALYSE Si-Vox");
					iab = new InterfaceAboutVocalyse(objetCourant);
					iab.open();
				}
				else
				{
					if(iab != null)
						iab.setActive();
				}
			}
		});
		
		// evenement appui sur le menu aide si vox
		menuAideSiVox.addSelectionListener(new SelectionAdapter() {
			public void widgetSelected(SelectionEvent e) {
				if(helpOpen == false)
				{
					helpOpen = true;
					if(mode == modeParlant.PARLANT)
					{
						lectureAssistant("aide ess i vox");
					}
					objetCourant.setIconInformationInfo();
					objetCourant.setInformation("Ouverture de l'aide de VOCALYSE Si-Vox");
					iai = new InterfaceAideVocalyse(objetCourant);
					iai.open();
				}
				else
				{
					if(iai != null)
						iai.setActive();
				}
			}
		});
		
		menuGestionFichier.addSelectionListener(new SelectionListener () {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(menuGestionFichier.getSelection() == false)
				{
					toolBarGestionFichier.setVisible(false);
					coolItemGestionFichier.dispose();
					fenetrePrincipale.layout();
				}
				else
				{
					toolBarGestionFichier.setVisible(true);
					toolBarGestionFichier.pack();
					Point size = toolBarGestionFichier.getSize();
					coolItemGestionFichier = new CoolItem(barreOutil, SWT.NONE);
					coolItemGestionFichier.setControl(toolBarGestionFichier);
					Point preferred = coolItemGestionFichier.computeSize(size.x, size.y);
					coolItemGestionFichier.setPreferredSize(preferred);
					coolItemGestionFichier.setMinimumSize(preferred);
				}
			}
		});
		
		menuGestionPlayer.addSelectionListener(new SelectionListener () {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(menuGestionPlayer.getSelection() == false)
				{
					toolBarGestionPlayer.setVisible(false);
					coolItemGestionPlayer.dispose();
					fenetrePrincipale.layout();
				}
				else
				{
					toolBarGestionPlayer.setVisible(true);
					toolBarGestionPlayer.pack();
					Point size = toolBarGestionPlayer.getSize();
					coolItemGestionPlayer = new CoolItem(barreOutil, SWT.NONE);
					coolItemGestionPlayer.setControl(toolBarGestionPlayer);
					Point preferred = coolItemGestionPlayer.computeSize(size.x, size.y);
					coolItemGestionPlayer.setPreferredSize(preferred);
					coolItemGestionPlayer.setMinimumSize(preferred);
				}
			}
		});
		
		menuGestionVoix.addSelectionListener(new SelectionListener () {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(menuGestionVoix.getSelection() == false)
				{
					toolBarGestionVoix.setVisible(false);
					coolItemGestionVoix.dispose();
					fenetrePrincipale.layout();
				}
				else
				{
					toolBarGestionVoix.setVisible(true);
					toolBarGestionVoix.pack();
					Point size = toolBarGestionVoix.getSize();
					coolItemGestionVoix = new CoolItem(barreOutil, SWT.NONE);
					coolItemGestionVoix.setControl(toolBarGestionVoix);
					Point preferred = coolItemGestionVoix.computeSize(size.x, size.y);
					coolItemGestionVoix.setPreferredSize(preferred);
					coolItemGestionVoix.setMinimumSize(preferred);
				}
			}
		});
		
		menuGestionEnregistrementWav.addSelectionListener(new SelectionListener () {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(menuGestionEnregistrementWav.getSelection() == false)
				{
					toolBarGestionWav.setVisible(false);
					coolItemGestionWav.dispose();
					fenetrePrincipale.layout();
				}
				else
				{
					toolBarGestionWav.setVisible(true);
					toolBarGestionWav.pack();
					Point size = toolBarGestionWav.getSize();
					coolItemGestionWav = new CoolItem(barreOutil, SWT.NONE);
					coolItemGestionWav.setControl(toolBarGestionWav);
					Point preferred = coolItemGestionWav.computeSize(size.x, size.y);
					coolItemGestionWav.setPreferredSize(preferred);
					coolItemGestionWav.setMinimumSize(preferred);
				}
			}
		});
		
		menuGestionModeSonore.addSelectionListener(new SelectionListener () {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(menuGestionModeSonore.getSelection() == false)
				{
					toolBarGestionModeSonore.setVisible(false);
					coolItemGestionModeSonore.dispose();
					fenetrePrincipale.layout();
				}
				else
				{
					toolBarGestionModeSonore.setVisible(true);
					toolBarGestionModeSonore.pack();
					Point size = toolBarGestionModeSonore.getSize();
					coolItemGestionModeSonore = new CoolItem(barreOutil, SWT.NONE);
					coolItemGestionModeSonore.setControl(toolBarGestionModeSonore);
					Point preferred = coolItemGestionModeSonore.computeSize(size.x, size.y);
					coolItemGestionModeSonore.setPreferredSize(preferred);
					coolItemGestionModeSonore.setMinimumSize(preferred);
				}
			}
		});
		
		menuGestionSynchronisation.addSelectionListener(new SelectionListener () {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(menuGestionSynchronisation.getSelection() == false)
				{
					toolBarGestionSynchro.setVisible(false);
					coolItemGestionSynchro.dispose();
					fenetrePrincipale.layout();
				}
				else
				{
					toolBarGestionSynchro.setVisible(true);
					toolBarGestionSynchro.pack();
					Point size = toolBarGestionSynchro.getSize();
					coolItemGestionSynchro = new CoolItem(barreOutil, SWT.NONE);
					coolItemGestionSynchro.setControl(toolBarGestionSynchro);
					Point preferred = coolItemGestionSynchro.computeSize(size.x, size.y);
					coolItemGestionSynchro.setPreferredSize(preferred);
					coolItemGestionSynchro.setMinimumSize(preferred);
				}
			}
		});
		
		// EVENEMENTS SUR LE CTABFOLDER
		
		// appui de la touche F1 ou F2 avec focus sur folderProjet
		folderProjet.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {				
				if(e.keyCode == 16777226)
				{
					if(aboutOpen == false)
					{
						aboutOpen = true;
						if(mode == modeParlant.PARLANT)
						{
							lectureAssistant("apropo");
						}
						objetCourant.setIconInformationInfo();
						objetCourant.setInformation("A propos de VOCALYSE Si-Vox");
						iab = new InterfaceAboutVocalyse(objetCourant);
						iab.open();
					}
					else
					{
						if(iab != null)
							iab.setActive();
					}
				}
				else if(e.keyCode == 16777227)
				{
					if(helpOpen == false)
					{
						helpOpen = true;
						if(mode == modeParlant.PARLANT)
						{
							lectureAssistant("aide ess i vox");
						}
						objetCourant.setIconInformationInfo();
						objetCourant.setInformation("Ouverture de l'aide de VOCALYSE Si-Vox");
						iai = new InterfaceAideVocalyse(objetCourant);
						iai.open();
					}
					else
					{
						if(iai != null)
							iai.setActive();
					}
				}
			}
			public void keyReleased(KeyEvent arg0) {}
		});
		
	    // reglage des dimensions de la fenetre et affichage
		this.setIconInformationInfo();
		fenetrePrincipale.setSize(1280,800);
	    fenetrePrincipale.setLocation(ecran.getClientArea().x+((ecran.getClientArea().width-1280)/2),ecran.getClientArea().y+((ecran.getClientArea().height-800)/2));
	    fenetreIntro.setVisible(false);
  }
	
	/**
	 * Methode qui ajoute un nouveau projet
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param nom Le nom du nouveau projet
	 * @param auteur Le nom d'auteur du nouveau projet
	 * @param commentaire Le commentaire du nouveau projet
	 * @param nomFichier Le nom de fichier d'enregistrement
	 */
	public void ajouterProjet(String nom, String auteur, String commentaire, String nomFichier)
	{
		folderProjet.ajouterProjet(this, nom, auteur, commentaire, nomFichier);
	}
	
	/**
	 * Methode qui modifie le message d'information
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param info Le message d'information
	 */
	public void setInformation(String info)
	{	
		information.setText(info);
	}
	
	/**
	 * Methode qui met l'icone d 'information a info
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setIconInformationInfo()
	{
		information.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"information_16_16.png"));
	}
	
	/**
	 * Methode qui met l'icone d'information a warning
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setIconInformationWarning()
	{
		information.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"attention_16_16.png"));
		java.awt.Toolkit.getDefaultToolkit().beep();
	}
	
	/**
	 * Methode qui tente de fermer l'application (en fermant tous les projet ouvert)
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void quitter()
	{
		folderProjet.fermerAllProjets();
		if(folderProjet.getItemCount() == 0)
		{
			if(singerSivoxOpen == true)
			{
				if(singer != null)
				{
					singer.setActivate();
				}
			}
			else
			{
				sortie = true;
			}
		}
	}
	
	/**
	 * Methode qui retourne le numero de la prosodie courante
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le numero de la prosodie
	 */
	public int getNumeroProsodie()
	{
		return(choixNumeroProsodie);
	}
	
	/**
	 * Methode qui retourne la fenetre principale
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return
	 */
	public Shell getShell()
	{
		return(fenetrePrincipale);
	}
	
	/**
	 * Methode qui fait lire une phrase a l assistant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param phrase
	 */
	public void lectureAssistant(String phrase)
	{
		try {
			assistant.playShortText(phrase);
		} catch (Exception e) {}
	}
	
	/**
	 * Methode qui indique si le mode parlant est actif ou pas
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si le mode parlant est actif
	 */
	public boolean getModeParlantActif()
	{
		if(mode == modeParlant.PARLANT)
			return(true);
		else
			return(false);
	}
	
	/**
	 * Methode qui effectue une courte pause
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void getPause()
	{
		try {
			Thread.sleep(150);
		} catch (InterruptedException e) {}
	}
	
	/**
	 * Methode qui retourne le numero de la voix courante
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
	
	/**
	 * Methode qui ouvre la fenetre principale
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void open()
	{
		fenetrePrincipale.open();
		if(sortie == false)
		{
			lectureAssistant("Bienvenue sur vocalise ess i vox");
		}
		while (!fenetrePrincipale.isDisposed() && (sortie == false))
	    {
	    	if (!ecran.readAndDispatch())
	    		ecran.sleep();
	    }
	    fenetrePrincipale.dispose();
	    fenetreIntro.dispose();
	    ecran.dispose();
	}
	
	/**
	 * Methode qui met toutes les prosodie du projet courant en desynchro
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setAllDesynchro()
	{
		try {
			folderProjet.setAllDesynchro();
		} catch (SIVOXException z) {
			//plus de prosodie a desynchro
		}
	}
	
	/**
	 * Methode qui indique que la fenetre about n est plus ouverte
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setAboutClosed()
	{
		aboutOpen = false;
	}
	
	/**
	 * Methode qui indique que la fenetre help n'est plus ouverte
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setHelpClosed()
	{
		helpOpen = false;
	}
	
	/**
	 * Methode qui indique que le singer sivox n'est plus ouvert
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setSingerSivoxClosed()
	{
		singerSivoxOpen = false;
	}
	
	/**
	 * Methode qui indique que la configuration sivox n'est plus ouverte
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setConfigurationSIVOXClosed()
	{
		configurationSivoxOpen = false;
	}
	
	/**
	 * Methode qui met le projet courant dans l'etat modifie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setModified()
	{
		folderProjet.setModified();
	}
	
	/**
	 * Methode qui met le projet courant dans l'etat a jour
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setUpdate()
	{
		folderProjet.setUpdate();
	}
	
	/**
	 * Methode qui met a jour le titre du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param nom Le nouveau nom de projet
	 */
	public void setNomProjet(String nom)
	{
		folderProjet.setNomProjet(nom);
	}
	
	/**
	 * Methode qui retourne le nom de la voix la plus longue parmis toutes
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le nom de la voix la plus longue
	 */
	public String getMaxStringVoix()
	{
		String temp = "";
		String temp2 = "";
		for(int i = 1 ; i < 8 ; i++)
		{
			if(InformationSysteme.testerVoix(i))
			{
				temp2 = ConfigFile.rechercher("NOM_VOIX_"+String.valueOf(i));
				if(temp2 != null)
				{
					if(temp2.length() > temp.length())
					{
						temp = temp2;
					}
				}
			}
		}
		return(temp);
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
			menuVoixModeSonoreThierry.setEnabled(false);
		}
		else
		{
			if(numeroVoixPlusLongue == 1)
			{
				assistant.setVoix(1);
				menuPopupVoixThierry.setSelection(true);
				menuVoixThierry.setSelection(true);
				menuVoixModeSonoreThierry.setSelection(true);
			}
		}
		if(!InformationSysteme.testerVoix(2))
		{
			menuPopupVoixCeline.setEnabled(false);
			menuVoixCeline.setEnabled(false);
			menuVoixModeSonoreCeline.setEnabled(false);
		}
		else
		{
			if(numeroVoixPlusLongue == 2)
			{
				assistant.setVoix(2);
				menuPopupVoixCeline.setSelection(true);
				menuVoixCeline.setSelection(true);
				menuVoixModeSonoreCeline.setSelection(true);
			}
		}
		if(!InformationSysteme.testerVoix(3))
		{
			menuPopupVoixVincent.setEnabled(false);
			menuVoixVincent.setEnabled(false);
			menuVoixModeSonoreVincent.setEnabled(false);
		}
		else
		{
			if(numeroVoixPlusLongue == 3)
			{
				assistant.setVoix(3);
				menuPopupVoixVincent.setSelection(true);
				menuVoixVincent.setSelection(true);
				menuVoixModeSonoreVincent.setSelection(true);
			}
		}
		if(!InformationSysteme.testerVoix(4))
		{
			menuPopupVoixAnneCarole.setEnabled(false);
			menuVoixAnneCarole.setEnabled(false);
			menuVoixModeSonoreAnneCarole.setEnabled(false);
		}
		else
		{
			if(numeroVoixPlusLongue == 4)
			{
				assistant.setVoix(4);
				menuPopupVoixAnneCarole.setSelection(true);
				menuVoixAnneCarole.setSelection(true);
				menuVoixModeSonoreAnneCarole.setSelection(true);
			}
		}
		if(!InformationSysteme.testerVoix(5))
		{
			menuPopupVoixFabrice.setEnabled(false);
			menuVoixFabrice.setEnabled(false);
			menuVoixModeSonoreFabrice.setEnabled(false);
		}
		else
		{
			if(numeroVoixPlusLongue == 5)
			{
				assistant.setVoix(5);
				menuPopupVoixFabrice.setSelection(true);
				menuVoixFabrice.setSelection(true);
				menuVoixModeSonoreFabrice.setSelection(true);
			}
		}
		if(!InformationSysteme.testerVoix(6))
		{
			menuPopupVoixXavier.setEnabled(false);
			menuVoixXavier.setEnabled(false);
			menuVoixModeSonoreXavier.setEnabled(false);
		}
		else
		{
			if(numeroVoixPlusLongue == 6)
			{
				assistant.setVoix(6);
				menuPopupVoixXavier.setSelection(true);
				menuVoixXavier.setSelection(true);
				menuVoixModeSonoreXavier.setSelection(true);
			}
		}
		if(!InformationSysteme.testerVoix(7))
		{
			menuPopupVoixSoldatInconnu.setEnabled(false);
			menuVoixSoldatInconnu.setEnabled(false);
			menuVoixModeSonoreSoldatInconnu.setEnabled(false);
		}
		else
		{
			if(numeroVoixPlusLongue == 7)
			{
				assistant.setVoix(7);
				menuPopupVoixSoldatInconnu.setSelection(true);
				menuVoixSoldatInconnu.setSelection(true);
				menuVoixModeSonoreSoldatInconnu.setSelection(true);
			}
		}
		// test d au moins 1 voix
		if(numeroVoixPlusLongue == 0)
		{
			MessageBox messageBox = new MessageBox(fenetrePrincipale, SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("Aucune voix n'a ete trouvee, le programme doit se fermer !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
			quitter();
		}
	}
	
	/**
	 * Methode qui maximise les proprietes du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserPropriete()
	{
		folderProjet.maximiserPropriete();
	}
	
	/**
	 * Methode qui demaximise les proprietes du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void demaximiserPropriete()
	{
		folderProjet.demaximiserPropriete();
	}
	
	/**
	 * Methode qui maximise le texte du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserTexte()
	{
		folderProjet.maximiserTexte();
	}
	
	/**
	 * Methode qui demaximise le texte du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void demaximiserTexte()
	{
		folderProjet.demaximiserText();
	}
	
	/**
	 * Methode qui maximise la prosodie du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserProsodie()
	{
		folderProjet.maximiserProsodie();
	}
	
	/**
	 * Methode qui demaximise la prosodie du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void demaximiserProsodie()
	{
		folderProjet.demaximiserProsodie();
	}
	
	/**
	 * Methode qui maximise le graphe du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void maximiserGraphe()
	{
		folderProjet.maximiserGraphe();
	}
	
	/**
	 * Methode qui demaximise le graphe du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void demaximiserGraphe()
	{
		folderProjet.demaximiserGraphe();
	}
	
	/**
	 * Methode qui met a jour le graphe du projet courant avec la nouvelle prosodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param chainePhoneme
	 */
	public void miseAJourGraphe(String chainePhoneme)
	{
		folderProjet.miseAJourGraphe(chainePhoneme);
	}
	
	/**
	 * Methode qui met a jour le tableau de prosodie du projet courant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param chaineProsodie La chaine a utiliser pour la mise a jour
	 */
	public void miseAJourTableauProsodie(String chaineProsodie)
	{
		try {
			folderProjet.miseAJourTableauProsodie(chaineProsodie);
		} catch (SIVOXException z) {
			setIconInformationWarning();
			setInformation(z.getMessage());
			if(mode == modeParlant.PARLANT)
			{
				lectureAssistant(z.getMessageSonore());
			}
		}
	}
	
	/**
	 * Methode qui met en avant la fenetre principale
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setActive()
	{
		fenetrePrincipale.setActive();
	}
}
