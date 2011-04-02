package t2s.ihm;

import java.io.*;
import t2s.util.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;

/**
 * Classe InterfaceConfigurationSIVOX qui permet de configurer vocalyse
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class InterfaceConfigurationSIVOX {

	private boolean sortie = false;
	
	private Shell fenetrePrincipale = null;
	private Display ecran = null;
	private CTabFolder folder = null;
	private Button bouttonOk = null;
	private Button bouttonAppliquer = null;
	private Button bouttonAnnuler = null;
	
	private CTabItem tabItemCheminAcces = null;
	private Composite compoCheminAcces = null;
	
	private CTabItem tabItemParametrageVoix = null;
	private Composite compoParametrageVoix = null;
	
	private CTabItem tabItemReglageSynthetiseur = null;
	private Composite compoReglageSynthetiseur = null;
	
	private Group groupMbrola = null;
	private FormData groupMbrolaData = null;
	private Group groupRegle = null;
	private FormData groupRegleData = null;
	private Group groupGeneral = null;
	private FormData groupGeneralData = null;
	private Label labelExeWindows = null;
	private Label labelExeLinux = null;
	private Label labelExeMac = null;
	private Label labelFichierPhoWav = null;
	private Label labelPreposition = null;
	private Label labelRegle = null;
	private Label labelException = null;
	private Label labelAccronyme = null;
	private Label labelImgPath = null;
	private Label labelEncodageFichier = null;
	private Label labelAnalyseurWav = null;
	private Text textExeWindows = null;
	private Text textExeLinux = null;
	private Text textExeMac = null;
	private Text textFichierPhoWav = null;
	private Text textPreposition = null;
	private Text textRegle = null;
	private Text textException = null;
	private Text textAccronyme = null;
	private Text textImgPath = null;
	private Text textAnalyseurWav = null;
	private Combo comboEncodageFichier = null;
	private Button bouttonExeWindows = null;
	private Button bouttonExeLinux = null;
	private Button bouttonExeMac = null;
	private Button bouttonFichierPhoWav = null;
	private Button bouttonPreposition = null;
	private Button bouttonRegle = null;
	private Button bouttonException = null;
	private Button bouttonAccronyme = null;
	private Button bouttonImgPath = null;
	private Button bouttonAnalyseurWav = null;
	private FormData labelExeWindowsData = null;
	private FormData labelExeLinuxData = null;
	private FormData labelExeMacData = null;
	private FormData labelFichierPhoWavData = null;
	private FormData labelPrepositionData = null;
	private FormData labelRegleData = null;
	private FormData labelExceptionData = null;
	private FormData labelAccronymeData = null;
	private FormData labelImgPathData = null;
	private FormData labelEncodageFichierData = null;
	private FormData labelAnalyseurWavData = null;
	private FormData textExeWindowsData = null;
	private FormData textExeLinuxData = null;
	private FormData textExeMacData = null;
	private FormData textFichierPhoWavData = null;
	private FormData textPrepositionData = null;
	private FormData textRegleData = null;
	private FormData textExceptionData = null;
	private FormData textAccronymeData = null;
	private FormData textImgPathData = null;
	private FormData textAnalyseurWavData = null;
	private FormData comboEncodageFichierData = null;
	private FormData bouttonExeWindowsData = null;
	private FormData bouttonExeLinuxData = null;
	private FormData bouttonExeMacData = null;
	private FormData bouttonFichierPhoWavData = null;
	private FormData bouttonPrepositionData = null;
	private FormData bouttonRegleData = null;
	private FormData bouttonExceptionData = null;
	private FormData bouttonAccronymeData = null;
	private FormData bouttonImgPathData = null;
	private FormData bouttonAnalyseurWavData = null;
	
	private Group groupVoixGeneral = null;
	private Group groupVoix = null;
	private FormData groupVoixGeneralData = null;
	private FormData groupVoixData = null;
	private Label labelNbVoix = null;
	private Label labelFrequenceInit = null;
	private Text textNbVoix = null;
	private Text textFrequenceInit = null;
	private Table tableVoix = null;
	private TableColumn colonneNumeroVoix = null;
	private TableColumn colonneNomVoix = null;
	private TableColumn colonneEmplacement = null;
	private TableColumn colonneRatioFrequence = null;
	private FormData labelNbVoixData = null;
	private FormData labelFrequenceInitData = null;
	private FormData textNbVoixData = null;
	private FormData textFrequenceInitData = null;
	private FormData tableVoixData = null;
	private FormData folderData = null;
	private FormData bouttonOkData = null;
	private FormData bouttonAppliquerData = null;
	private FormData bouttonAnnulerData = null;
	
	private Group groupReglageGeneral = null;
	private Group groupReglageProsodie = null;
	private Group groupReglageCourbe = null;
	private FormData groupReglageGeneralData = null;
	private FormData groupReglageProsodieData = null;
	private FormData groupReglageCourbeData = null;
	private Label labelRapidite = null;
	private Label labelVolume = null;
	private Label labelPauseCourte = null;
	private Label labelPauseLongue = null;
	private Label labelPauseFin = null;
	private Label labelHauteurPalier = null;
	private Label labelPasSuite = null;
	private Label labelMinSuite = null;
	private Label labelMaxSuite = null;
	private Label labelNbVariationPitch = null;
	private Label labelCoefKMineur = null;
	private Label labelCoefKMajeur = null;
	private Label labelCoefExclamation = null;
	private Label labelCoefHauteurA = null;
	private Label labelPuissanceA = null;
	private Label labelCoefHauteurB = null;
	private Label labelPuissanceB = null;
	private Label labelCoefHauteurC = null;
	private Label labelPuissanceC = null;
	private Label labelCoefHSqrtC = null;
	private Label labelCoefHauteurD = null;
	private Label labelCoefHNmoins1D = null;
	private Label labelCoefHauteurE = null;
	private Label labelCoefHNmoins1E = null;
	private Text textRapidite = null;
	private Text textVolume = null;
	private Text textPauseCourte = null;
	private Text textPauseLongue = null;
	private Text textPauseFin = null;
	private Text textHauteurPalier = null;
	private Text textPasSuite = null;
	private Text textMinSuite = null;
	private Text textMaxSuite = null;
	private Text textNbVariationPitch = null;
	private Text textCoefKMineur = null;
	private Text textCoefKMajeur = null;
	private Text textCoefExclamation = null;
	private Text textCoefHauteurA = null;
	private Text textPuissanceA = null;
	private Text textCoefHauteurB = null;
	private Text textPuissanceB = null;
	private Text textCoefHauteurC = null;
	private Text textPuissanceC = null;
	private Text textCoefHSqrtC = null;
	private Text textCoefHauteurD = null;
	private Text textCoefHNmoins1D = null;
	private Text textCoefHauteurE = null;
	private Text textCoefHNmoins1E = null;
	private FormData labelRapiditeData = null;
	private FormData labelVolumeData = null;
	private FormData labelPauseCourteData = null;
	private FormData labelPauseLongueData = null;
	private FormData labelPauseFinData = null;
	private FormData labelHauteurPalierData = null;
	private FormData labelPasSuiteData = null;
	private FormData labelMinSuiteData = null;
	private FormData labelMaxSuiteData = null;
	private FormData labelNbVariationPitchData = null;
	private FormData labelCoefKMineurData = null;
	private FormData labelCoefKMajeurData = null;
	private FormData labelCoefExclamationData = null;
	private FormData labelCoefHauteurAData = null;
	private FormData labelPuissanceAData = null;
	private FormData labelCoefHauteurBData = null;
	private FormData labelPuissanceBData = null;
	private FormData labelCoefHauteurCData = null;
	private FormData labelPuissanceCData = null;
	private FormData labelCoefHSqrtCData = null;
	private FormData labelCoefHauteurDData = null;
	private FormData labelCoefHNmoins1DData = null;
	private FormData labelCoefHauteurEData = null;
	private FormData labelCoefHNmoins1EData = null;
	private FormData textRapiditeData = null;
	private FormData textVolumeData = null;
	private FormData textPauseCourteData = null;
	private FormData textPauseLongueData = null;
	private FormData textPauseFinData = null;
	private FormData textHauteurPalierData = null;
	private FormData textPasSuiteData = null;
	private FormData textMinSuiteData = null;
	private FormData textMaxSuiteData = null;
	private FormData textNbVariationPitchData = null;
	private FormData textCoefKMineurData = null;
	private FormData textCoefKMajeurData = null;
	private FormData textCoefExclamationData = null;
	private FormData textCoefHauteurAData = null;
	private FormData textPuissanceAData = null;
	private FormData textCoefHauteurBData = null;
	private FormData textPuissanceBData = null;
	private FormData textCoefHauteurCData = null;
	private FormData textPuissanceCData = null;
	private FormData textCoefHSqrtCData = null;
	private FormData textCoefHauteurDData = null;
	private FormData textCoefHNmoins1DData = null;
	private FormData textCoefHauteurEData = null;
	private FormData textCoefHNmoins1EData = null;
	
	private Group groupAnalyseur = null;
	private FormData groupAnalyseurData = null;
	private Label labelAnalyseurAmplitude = null;
	private Label labelAnalyseurFrequence = null;
	private Label labelAnalyseurTempsConsonne = null;
	private Label labelAnalyseurTempsVoyelle = null;
	private Label labelAnalyseurTempsLongue = null;
	private Label labelAnalyseurNombreCouple = null;
	private Text textAnalyseurAmplitude = null;
	private Text textAnalyseurFrequence = null;
	private Text textAnalyseurTempsConsonne = null;
	private Text textAnalyseurTempsVoyelle = null;
	private Text textAnalyseurTempsLongue = null;
	private Text textAnalyseurNombreCouple = null;
	private FormData labelAnalyseurAmplitudeData = null;
	private FormData labelAnalyseurFrequenceData = null;
	private FormData labelAnalyseurTempsConsonneData = null;
	private FormData labelAnalyseurTempsVoyelleData = null;
	private FormData labelAnalyseurTempsLongueData = null;
	private FormData labelAnalyseurNombreCoupleData = null;
	private FormData textAnalyseurAmplitudeData = null;
	private FormData textAnalyseurFrequenceData = null;
	private FormData textAnalyseurTempsConsonneData = null;
	private FormData textAnalyseurTempsVoyelleData = null;
	private FormData textAnalyseurTempsLongueData = null;
	private FormData textAnalyseurNombreCoupleData = null;
	
	private InterfaceGenerale i = null;
	
	/**
	 * Constructeur par defaut de InterfaceConfigurationSIVOX
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param i1 L'interfaceGenerale appelante
	 */
	public InterfaceConfigurationSIVOX(InterfaceGenerale i1)
	{
		// affectation de l interface generale et de l ecran
		i = i1;
		ecran = i.getDisplay();
		
		// configuration de la fenetre
		fenetrePrincipale = new Shell(ecran, SWT.CLOSE);
		fenetrePrincipale.setLayout(new FormLayout());
		fenetrePrincipale.setSize(900, 530);
		fenetrePrincipale.setText("Configuration de VOCALYSE S.I. Vox");
		fenetrePrincipale.setLocation(ecran.getClientArea().x+((ecran.getClientArea().width-900)/2),ecran.getClientArea().y+((ecran.getClientArea().height-530)/2));
		
		// creation du folder
		folder = new CTabFolder(fenetrePrincipale, SWT.NONE);
		folder.setSimple(false);
		folderData = new FormData();
		folderData.left = new FormAttachment(0);
		folderData.right = new FormAttachment(100);
		folderData.top = new FormAttachment(0);
		folderData.bottom = new FormAttachment(90);
		folder.setLayoutData(folderData);
		
		// creation du CTabItem des chemin d'acces
		tabItemCheminAcces = new CTabItem(folder, SWT.NONE);
		tabItemCheminAcces.setText("Repertoires et chemin d'acces");
		compoCheminAcces = new Composite(folder, SWT.NONE);
		compoCheminAcces.setLayout(new FormLayout());
		tabItemCheminAcces.setControl(compoCheminAcces);
		
		// creation du contenu du CTabItem des chemin d'acces
		// creation du group Mbrola
		groupMbrola = new Group(compoCheminAcces, SWT.NONE);
		groupMbrola.setText(" Mbrola ");
		groupMbrola.setLayout(new FormLayout());
		groupMbrolaData = new FormData();
		groupMbrolaData.left = new FormAttachment(1);
		groupMbrolaData.right = new FormAttachment(99);
		groupMbrolaData.top = new FormAttachment(1);
		groupMbrolaData.bottom = new FormAttachment(35);
		groupMbrola.setLayoutData(groupMbrolaData);
		// ligne 1
		labelExeWindows = new Label(groupMbrola, SWT.FLAT);
		labelExeWindows.setText("Executable WINDOWS");
		labelExeWindowsData = new FormData();
		labelExeWindowsData.left = new FormAttachment(1);
		labelExeWindowsData.right = new FormAttachment(27);
		labelExeWindowsData.top = new FormAttachment(4);
		labelExeWindowsData.bottom = new FormAttachment(23);
		labelExeWindows.setLayoutData(labelExeWindowsData);
		textExeWindows = new Text(groupMbrola, SWT.BORDER);
		textExeWindows.setText("");
		textExeWindowsData = new FormData();
		textExeWindowsData.left = new FormAttachment(28);
		textExeWindowsData.right = new FormAttachment(82);
		textExeWindowsData.top = new FormAttachment(1);
		textExeWindowsData.bottom = new FormAttachment(23);
		textExeWindows.setLayoutData(textExeWindowsData);
		bouttonExeWindows = new Button(groupMbrola, SWT.PUSH);
		bouttonExeWindows.setText("Parcourir...");
		bouttonExeWindowsData = new FormData();
		bouttonExeWindowsData.left = new FormAttachment(83);
		bouttonExeWindowsData.right = new FormAttachment(99);
		bouttonExeWindowsData.top = new FormAttachment(1);
		bouttonExeWindowsData.bottom = new FormAttachment(23);
		bouttonExeWindows.setLayoutData(bouttonExeWindowsData);
		//ligne 2
		labelExeLinux = new Label(groupMbrola, SWT.FLAT);
		labelExeLinux.setText("Executable LINUX");
		labelExeLinuxData = new FormData();
		labelExeLinuxData.left = new FormAttachment(1);
		labelExeLinuxData.right = new FormAttachment(27);
		labelExeLinuxData.top = new FormAttachment(27);
		labelExeLinuxData.bottom = new FormAttachment(47);
		labelExeLinux.setLayoutData(labelExeLinuxData);
		textExeLinux = new Text(groupMbrola, SWT.BORDER);
		textExeLinux.setText("");
		textExeLinuxData = new FormData();
		textExeLinuxData.left = new FormAttachment(28);
		textExeLinuxData.right = new FormAttachment(82);
		textExeLinuxData.top = new FormAttachment(24);
		textExeLinuxData.bottom = new FormAttachment(47);
		textExeLinux.setLayoutData(textExeLinuxData);
		bouttonExeLinux = new Button(groupMbrola, SWT.PUSH);
		bouttonExeLinux.setText("Parcourir...");
		bouttonExeLinuxData = new FormData();
		bouttonExeLinuxData.left = new FormAttachment(83);
		bouttonExeLinuxData.right = new FormAttachment(99);
		bouttonExeLinuxData.top = new FormAttachment(24);
		bouttonExeLinuxData.bottom = new FormAttachment(47);
		bouttonExeLinux.setLayoutData(bouttonExeLinuxData);
		//ligne 3
		labelExeMac = new Label(groupMbrola, SWT.FLAT);
		labelExeMac.setText("Executable MAC");
		labelExeMacData = new FormData();
		labelExeMacData.left = new FormAttachment(1);
		labelExeMacData.right = new FormAttachment(27);
		labelExeMacData.top = new FormAttachment(51);
		labelExeMacData.bottom = new FormAttachment(71);
		labelExeMac.setLayoutData(labelExeMacData);
		textExeMac = new Text(groupMbrola, SWT.BORDER);
		textExeMac.setText("");
		textExeMacData = new FormData();
		textExeMacData.left = new FormAttachment(28);
		textExeMacData.right = new FormAttachment(82);
		textExeMacData.top = new FormAttachment(48);
		textExeMacData.bottom = new FormAttachment(71);
		textExeMac.setLayoutData(textExeMacData);
		bouttonExeMac = new Button(groupMbrola, SWT.PUSH);
		bouttonExeMac.setText("Parcourir...");
		bouttonExeMacData = new FormData();
		bouttonExeMacData.left = new FormAttachment(83);
		bouttonExeMacData.right = new FormAttachment(99);
		bouttonExeMacData.top = new FormAttachment(48);
		bouttonExeMacData.bottom = new FormAttachment(71);
		bouttonExeMac.setLayoutData(bouttonExeMacData);
		//ligne 4
		labelFichierPhoWav = new Label(groupMbrola, SWT.FLAT);
		labelFichierPhoWav.setText("Repertoire des fichier PHO/WAVE");
		labelFichierPhoWavData = new FormData();
		labelFichierPhoWavData.left = new FormAttachment(1);
		labelFichierPhoWavData.right = new FormAttachment(27);
		labelFichierPhoWavData.top = new FormAttachment(75);
		labelFichierPhoWavData.bottom = new FormAttachment(95);
		labelFichierPhoWav.setLayoutData(labelFichierPhoWavData);
		textFichierPhoWav = new Text(groupMbrola, SWT.BORDER);
		textFichierPhoWav.setText("");
		textFichierPhoWavData = new FormData();
		textFichierPhoWavData.left = new FormAttachment(28);
		textFichierPhoWavData.right = new FormAttachment(82);
		textFichierPhoWavData.top = new FormAttachment(72);
		textFichierPhoWavData.bottom = new FormAttachment(95);
		textFichierPhoWav.setLayoutData(textFichierPhoWavData);
		bouttonFichierPhoWav = new Button(groupMbrola, SWT.PUSH);
		bouttonFichierPhoWav.setText("Parcourir...");
		bouttonFichierPhoWavData = new FormData();
		bouttonFichierPhoWavData.left = new FormAttachment(83);
		bouttonFichierPhoWavData.right = new FormAttachment(99);
		bouttonFichierPhoWavData.top = new FormAttachment(72);
		bouttonFichierPhoWavData.bottom = new FormAttachment(95);
		bouttonFichierPhoWav.setLayoutData(bouttonFichierPhoWavData);
		// creation du groupe regle
		groupRegle = new Group(compoCheminAcces, SWT.NONE);
		groupRegle.setText(" Regles ");
		groupRegle.setLayout(new FormLayout());
		groupRegleData = new FormData();
		groupRegleData.left = new FormAttachment(1);
		groupRegleData.right = new FormAttachment(99);
		groupRegleData.top = new FormAttachment(36);
		groupRegleData.bottom = new FormAttachment(70);
		groupRegle.setLayoutData(groupRegleData);
		//ligne 5
		labelPreposition = new Label(groupRegle, SWT.FLAT);
		labelPreposition.setText("Fichier de preposition");
		labelPrepositionData = new FormData();
		labelPrepositionData.left = new FormAttachment(1);
		labelPrepositionData.right = new FormAttachment(27);
		labelPrepositionData.top = new FormAttachment(4);
		labelPrepositionData.bottom = new FormAttachment(23);
		labelPreposition.setLayoutData(labelPrepositionData);
		textPreposition = new Text(groupRegle, SWT.BORDER);
		textPreposition.setText("");
		textPrepositionData = new FormData();
		textPrepositionData.left = new FormAttachment(28);
		textPrepositionData.right = new FormAttachment(82);
		textPrepositionData.top = new FormAttachment(1);
		textPrepositionData.bottom = new FormAttachment(23);
		textPreposition.setLayoutData(textPrepositionData);
		bouttonPreposition = new Button(groupRegle, SWT.PUSH);
		bouttonPreposition.setText("Parcourir...");
		bouttonPrepositionData = new FormData();
		bouttonPrepositionData.left = new FormAttachment(83);
		bouttonPrepositionData.right = new FormAttachment(99);
		bouttonPrepositionData.top = new FormAttachment(1);
		bouttonPrepositionData.bottom = new FormAttachment(23);
		bouttonPreposition.setLayoutData(bouttonPrepositionData);
		//ligne 6
		labelRegle = new Label(groupRegle, SWT.FLAT);
		labelRegle.setText("Fichier de regles");
		labelRegleData = new FormData();
		labelRegleData.left = new FormAttachment(1);
		labelRegleData.right = new FormAttachment(27);
		labelRegleData.top = new FormAttachment(27);
		labelRegleData.bottom = new FormAttachment(47);
		labelRegle.setLayoutData(labelRegleData);
		textRegle = new Text(groupRegle, SWT.BORDER);
		textRegle.setText("");
		textRegleData = new FormData();
		textRegleData.left = new FormAttachment(28);
		textRegleData.right = new FormAttachment(82);
		textRegleData.top = new FormAttachment(24);
		textRegleData.bottom = new FormAttachment(47);
		textRegle.setLayoutData(textRegleData);
		bouttonRegle = new Button(groupRegle, SWT.PUSH);
		bouttonRegle.setText("Parcourir...");
		bouttonRegleData = new FormData();
		bouttonRegleData.left = new FormAttachment(83);
		bouttonRegleData.right = new FormAttachment(99);
		bouttonRegleData.top = new FormAttachment(24);
		bouttonRegleData.bottom = new FormAttachment(47);
		bouttonRegle.setLayoutData(bouttonRegleData);
		//ligne 7
		labelException = new Label(groupRegle, SWT.FLAT);
		labelException.setText("Fichier des exceptions");
		labelExceptionData = new FormData();
		labelExceptionData.left = new FormAttachment(1);
		labelExceptionData.right = new FormAttachment(27);
		labelExceptionData.top = new FormAttachment(51);
		labelExceptionData.bottom = new FormAttachment(71);
		labelException.setLayoutData(labelExceptionData);
		textException = new Text(groupRegle, SWT.BORDER);
		textException.setText("");
		textExceptionData = new FormData();
		textExceptionData.left = new FormAttachment(28);
		textExceptionData.right = new FormAttachment(82);
		textExceptionData.top = new FormAttachment(48);
		textExceptionData.bottom = new FormAttachment(71);
		textException.setLayoutData(textExceptionData);
		bouttonException = new Button(groupRegle, SWT.PUSH);
		bouttonException.setText("Parcourir...");
		bouttonExceptionData = new FormData();
		bouttonExceptionData.left = new FormAttachment(83);
		bouttonExceptionData.right = new FormAttachment(99);
		bouttonExceptionData.top = new FormAttachment(48);
		bouttonExceptionData.bottom = new FormAttachment(71);
		bouttonException.setLayoutData(bouttonExceptionData);
		//ligne 8
		labelAccronyme = new Label(groupRegle, SWT.FLAT);
		labelAccronyme.setText("Fichier des acronymes");
		labelAccronymeData = new FormData();
		labelAccronymeData.left = new FormAttachment(1);
		labelAccronymeData.right = new FormAttachment(27);
		labelAccronymeData.top = new FormAttachment(72);
		labelAccronymeData.bottom = new FormAttachment(95);
		labelAccronyme.setLayoutData(labelAccronymeData);
		textAccronyme = new Text(groupRegle, SWT.BORDER);
		textAccronyme.setText("");
		textAccronymeData = new FormData();
		textAccronymeData.left = new FormAttachment(28);
		textAccronymeData.right = new FormAttachment(82);
		textAccronymeData.top = new FormAttachment(72);
		textAccronymeData.bottom = new FormAttachment(95);
		textAccronyme.setLayoutData(textAccronymeData);
		bouttonAccronyme = new Button(groupRegle, SWT.PUSH);
		bouttonAccronyme.setText("Parcourir...");
		bouttonAccronymeData = new FormData();
		bouttonAccronymeData.left = new FormAttachment(83);
		bouttonAccronymeData.right = new FormAttachment(99);
		bouttonAccronymeData.top = new FormAttachment(72);
		bouttonAccronymeData.bottom = new FormAttachment(95);
		bouttonAccronyme.setLayoutData(bouttonAccronymeData);
		// creation du groupe general (divers)
		groupGeneral = new Group(compoCheminAcces, SWT.NONE);
		groupGeneral.setText(" Divers ");
		groupGeneral.setLayout(new FormLayout());
		groupGeneralData = new FormData();
		groupGeneralData.left = new FormAttachment(1);
		groupGeneralData.right = new FormAttachment(99);
		groupGeneralData.top = new FormAttachment(71);
		groupGeneralData.bottom = new FormAttachment(99);
		groupGeneral.setLayoutData(groupGeneralData);
		//ligne 9
		labelImgPath = new Label(groupGeneral, SWT.FLAT);
		labelImgPath.setText("Repertoire des images");
		labelImgPathData = new FormData();
		labelImgPathData.left = new FormAttachment(1);
		labelImgPathData.right = new FormAttachment(27);
		labelImgPathData.top = new FormAttachment(3);
		labelImgPathData.bottom = new FormAttachment(30);
		labelImgPath.setLayoutData(labelImgPathData);
		textImgPath = new Text(groupGeneral, SWT.BORDER);
		textImgPath.setText("");
		textImgPathData = new FormData();
		textImgPathData.left = new FormAttachment(28);
		textImgPathData.right = new FormAttachment(82);
		textImgPathData.top = new FormAttachment(1);
		textImgPathData.bottom = new FormAttachment(30);
		textImgPath.setLayoutData(textImgPathData);
		bouttonImgPath = new Button(groupGeneral, SWT.PUSH);
		bouttonImgPath.setText("Parcourir...");
		bouttonImgPathData = new FormData();
		bouttonImgPathData.left = new FormAttachment(83);
		bouttonImgPathData.right = new FormAttachment(99);
		bouttonImgPathData.top = new FormAttachment(1);
		bouttonImgPathData.bottom = new FormAttachment(30);
		bouttonImgPath.setLayoutData(bouttonImgPathData);
		//ligne 10
		labelEncodageFichier = new Label(groupGeneral, SWT.FLAT);
		labelEncodageFichier.setText("Encodage des fichiers");
		labelEncodageFichierData = new FormData();
		labelEncodageFichierData.left = new FormAttachment(1);
		labelEncodageFichierData.right = new FormAttachment(27);
		labelEncodageFichierData.top = new FormAttachment(35);
		labelEncodageFichierData.bottom = new FormAttachment(75);
		labelEncodageFichier.setLayoutData(labelEncodageFichierData);
		comboEncodageFichier = new Combo(groupGeneral, SWT.READ_ONLY);
		comboEncodageFichier.add("ISO-8859-15");
		comboEncodageFichier.add("UTF-8");
		comboEncodageFichier.add("UTF-16");
		comboEncodageFichier.add("Unicode");
		comboEncodageFichierData = new FormData();
		comboEncodageFichierData.left = new FormAttachment(28);
		comboEncodageFichierData.right = new FormAttachment(99);
		comboEncodageFichierData.top = new FormAttachment(34);
		comboEncodageFichierData.bottom = new FormAttachment(75);
		comboEncodageFichier.setLayoutData(comboEncodageFichierData);
		comboEncodageFichier.select(0);
		//ligne 11
		labelAnalyseurWav = new Label(groupGeneral, SWT.FLAT);
		labelAnalyseurWav.setText("Analyseur wav");
		labelAnalyseurWavData = new FormData();
		labelAnalyseurWavData.left = new FormAttachment(1);
		labelAnalyseurWavData.right = new FormAttachment(27);
		labelAnalyseurWavData.top = new FormAttachment(72);
		labelAnalyseurWavData.bottom = new FormAttachment(95);
		labelAnalyseurWav.setLayoutData(labelAnalyseurWavData);
		textAnalyseurWav = new Text(groupGeneral, SWT.BORDER);
		textAnalyseurWav.setText("");
		textAnalyseurWavData = new FormData();
		textAnalyseurWavData.left = new FormAttachment(28);
		textAnalyseurWavData.right = new FormAttachment(82);
		textAnalyseurWavData.top = new FormAttachment(65);
		textAnalyseurWavData.bottom = new FormAttachment(95);
		textAnalyseurWav.setLayoutData(textAnalyseurWavData);
		bouttonAnalyseurWav = new Button(groupGeneral, SWT.PUSH);
		bouttonAnalyseurWav.setText("Parcourir...");
		bouttonAnalyseurWavData = new FormData();
		bouttonAnalyseurWavData.left = new FormAttachment(83);
		bouttonAnalyseurWavData.right = new FormAttachment(99);
		bouttonAnalyseurWavData.top = new FormAttachment(65);
		bouttonAnalyseurWavData.bottom = new FormAttachment(95);
		bouttonAnalyseurWav.setLayoutData(bouttonAnalyseurWavData);
		
		// creation du CTabItem de parametrage de voix
		tabItemParametrageVoix = new CTabItem(folder, SWT.NONE);
		tabItemParametrageVoix.setText("Parametrage des voix");
		compoParametrageVoix = new Composite(folder, SWT.NONE);
		compoParametrageVoix.setLayout(new FormLayout());
		tabItemParametrageVoix.setControl(compoParametrageVoix);
		// creation du groupe general
		groupVoixGeneral = new Group(compoParametrageVoix, SWT.FLAT);
		groupVoixGeneral.setLayout(new FormLayout());
		groupVoixGeneral.setText(" General ");
		groupVoixGeneralData = new FormData();
		groupVoixGeneralData.left = new FormAttachment(1);
		groupVoixGeneralData.right = new FormAttachment(99);
		groupVoixGeneralData.top = new FormAttachment(1);
		groupVoixGeneralData.bottom = new FormAttachment(21);
		groupVoixGeneral.setLayoutData(groupVoixGeneralData);
		//ligne 1
		labelNbVoix = new Label(groupVoixGeneral, SWT.FLAT);
		labelNbVoix.setText("Nombre de voix minimum");
		labelNbVoixData = new FormData();
		labelNbVoixData.left = new FormAttachment(1);
		labelNbVoixData.right = new FormAttachment(27);
		labelNbVoixData.top = new FormAttachment(7);
		labelNbVoixData.bottom = new FormAttachment(45);
		labelNbVoix.setLayoutData(labelNbVoixData);
		textNbVoix = new Text(groupVoixGeneral, SWT.BORDER | SWT.RIGHT);
		textNbVoix.setText("");
		textNbVoix.setEditable(false);
		textNbVoixData = new FormData();
		textNbVoixData.left = new FormAttachment(28);
		textNbVoixData.right = new FormAttachment(99);
		textNbVoixData.top = new FormAttachment(4);
		textNbVoixData.bottom = new FormAttachment(45);
		textNbVoix.setLayoutData(textNbVoixData);
		//ligne 2
		labelFrequenceInit = new Label(groupVoixGeneral, SWT.FLAT);
		labelFrequenceInit.setText("Frequence initiale");
		labelFrequenceInitData = new FormData();
		labelFrequenceInitData.left = new FormAttachment(1);
		labelFrequenceInitData.right = new FormAttachment(27);
		labelFrequenceInitData.top = new FormAttachment(50);
		labelFrequenceInitData.bottom = new FormAttachment(90);
		labelFrequenceInit.setLayoutData(labelFrequenceInitData);
		textFrequenceInit = new Text(groupVoixGeneral, SWT.BORDER | SWT.RIGHT);
		textFrequenceInit.setText("");
		textFrequenceInitData = new FormData();
		textFrequenceInitData.left = new FormAttachment(28);
		textFrequenceInitData.right = new FormAttachment(99);
		textFrequenceInitData.top = new FormAttachment(47);
		textFrequenceInitData.bottom = new FormAttachment(90);
		textFrequenceInit.setLayoutData(textFrequenceInitData);
		// groupe de voix
		groupVoix = new Group(compoParametrageVoix, SWT.FLAT);
		groupVoix.setLayout(new FormLayout());
		groupVoix.setText(" Voix ");
		groupVoixData = new FormData();
		groupVoixData.left = new FormAttachment(1);
		groupVoixData.right = new FormAttachment(99);
		groupVoixData.top = new FormAttachment(22);
		groupVoixData.bottom = new FormAttachment(99);
		groupVoix.setLayoutData(groupVoixData);
		// tableau des voix
		tableVoix = new Table(groupVoix, SWT.BORDER|SWT.H_SCROLL|SWT.V_SCROLL|SWT.SINGLE);
		tableVoix.setLinesVisible(true);
		tableVoix.setHeaderVisible(true);
		tableVoixData = new FormData();
		tableVoixData.left = new FormAttachment(1);
		tableVoixData.right = new FormAttachment(99);
		tableVoixData.top = new FormAttachment(1);
		tableVoixData.bottom = new FormAttachment(99);
		tableVoix.setLayoutData(tableVoixData);
		colonneNumeroVoix = new TableColumn(tableVoix, SWT.LEFT);
		colonneNumeroVoix.setText("Numero");
		colonneNumeroVoix.setWidth(70);
		colonneNomVoix = new TableColumn(tableVoix, SWT.LEFT);
		colonneNomVoix.setText("Nom");
		colonneNomVoix.setWidth(120);
		colonneEmplacement = new TableColumn(tableVoix, SWT.LEFT);
		colonneEmplacement.setText("Emplacement");
		colonneEmplacement.setWidth(520);
		colonneRatioFrequence = new TableColumn(tableVoix, SWT.LEFT);
		colonneRatioFrequence.setText("Ratio de frequence");
		colonneRatioFrequence.setWidth(135);
		
		// creation du CTabItem de reglage du synthetiseur
		tabItemReglageSynthetiseur = new CTabItem(folder, SWT.NONE);
		tabItemReglageSynthetiseur.setText("Parametrage du synthetiseur vocal");
		compoReglageSynthetiseur = new Composite(folder, SWT.NONE);
		compoReglageSynthetiseur.setLayout(new FormLayout());		
		tabItemReglageSynthetiseur.setControl(compoReglageSynthetiseur);
		// creation du groupe reglage general
		groupReglageGeneral = new Group(compoReglageSynthetiseur, SWT.FLAT);
		groupReglageGeneral.setText(" Reglage general ");
		groupReglageGeneral.setLayout(new FormLayout());
		groupReglageGeneralData = new FormData();
		groupReglageGeneralData.left = new FormAttachment(1);
		groupReglageGeneralData.right = new FormAttachment(50);
		groupReglageGeneralData.top = new FormAttachment(1);
		groupReglageGeneralData.bottom = new FormAttachment(44);
		groupReglageGeneral.setLayoutData(groupReglageGeneralData);
		labelRapidite = new Label(groupReglageGeneral, SWT.FLAT);
		labelRapidite.setText("Reglage de la vitesse");
		labelRapiditeData = new FormData();
		labelRapiditeData.left = new FormAttachment(1);
		labelRapiditeData.right = new FormAttachment(50);
		labelRapiditeData.top = new FormAttachment(2);
		labelRapiditeData.bottom = new FormAttachment(17);
		labelRapidite.setLayoutData(labelRapiditeData);
		textRapidite = new Text(groupReglageGeneral, SWT.BORDER | SWT.RIGHT);
		textRapidite.setText("");
		textRapiditeData = new FormData();
		textRapiditeData.left = new FormAttachment(51);
		textRapiditeData.right = new FormAttachment(98);
		textRapiditeData.top = new FormAttachment(2);
		textRapiditeData.bottom = new FormAttachment(17);
		textRapidite.setLayoutData(textRapiditeData);
		labelVolume = new Label(groupReglageGeneral, SWT.FLAT);
		labelVolume.setText("Reglage du volume");
		labelVolumeData = new FormData();
		labelVolumeData.left = new FormAttachment(1);
		labelVolumeData.right = new FormAttachment(50);
		labelVolumeData.top = new FormAttachment(18);
		labelVolumeData.bottom = new FormAttachment(33);
		labelVolume.setLayoutData(labelVolumeData);
		textVolume = new Text(groupReglageGeneral, SWT.BORDER | SWT.RIGHT);
		textVolume.setText("");
		textVolumeData = new FormData();
		textVolumeData.left = new FormAttachment(51);
		textVolumeData.right = new FormAttachment(98);
		textVolumeData.top = new FormAttachment(18);
		textVolumeData.bottom = new FormAttachment(33);
		textVolume.setLayoutData(textVolumeData);
		labelPauseCourte = new Label(groupReglageGeneral, SWT.FLAT);
		labelPauseCourte.setText("Pause courte");
		labelPauseCourteData = new FormData();
		labelPauseCourteData.left = new FormAttachment(1);
		labelPauseCourteData.right = new FormAttachment(50);
		labelPauseCourteData.top = new FormAttachment(34);
		labelPauseCourteData.bottom = new FormAttachment(49);
		labelPauseCourte.setLayoutData(labelPauseCourteData);
		textPauseCourte = new Text(groupReglageGeneral, SWT.BORDER | SWT.RIGHT);
		textPauseCourte.setText("");
		textPauseCourteData = new FormData();
		textPauseCourteData.left = new FormAttachment(51);
		textPauseCourteData.right = new FormAttachment(98);
		textPauseCourteData.top = new FormAttachment(34);
		textPauseCourteData.bottom = new FormAttachment(49);
		textPauseCourte.setLayoutData(textPauseCourteData);
		labelPauseLongue = new Label(groupReglageGeneral, SWT.FLAT);
		labelPauseLongue.setText("Pause longue");
		labelPauseLongueData = new FormData();
		labelPauseLongueData.left = new FormAttachment(1);
		labelPauseLongueData.right = new FormAttachment(50);
		labelPauseLongueData.top = new FormAttachment(50);
		labelPauseLongueData.bottom = new FormAttachment(65);
		labelPauseLongue.setLayoutData(labelPauseLongueData);
		textPauseLongue = new Text(groupReglageGeneral, SWT.BORDER | SWT.RIGHT);
		textPauseLongue.setText("");
		textPauseLongueData = new FormData();
		textPauseLongueData.left = new FormAttachment(51);
		textPauseLongueData.right = new FormAttachment(98);
		textPauseLongueData.top = new FormAttachment(50);
		textPauseLongueData.bottom = new FormAttachment(65);
		textPauseLongue.setLayoutData(textPauseLongueData);
		labelPauseFin = new Label(groupReglageGeneral, SWT.FLAT);
		labelPauseFin.setText("Pause de fin");
		labelPauseFinData = new FormData();
		labelPauseFinData.left = new FormAttachment(1);
		labelPauseFinData.right = new FormAttachment(50);
		labelPauseFinData.top = new FormAttachment(66);
		labelPauseFinData.bottom = new FormAttachment(81);
		labelPauseFin.setLayoutData(labelPauseFinData);
		textPauseFin = new Text(groupReglageGeneral, SWT.BORDER | SWT.RIGHT);
		textPauseFin.setText("");
		textPauseFinData = new FormData();
		textPauseFinData.left = new FormAttachment(51);
		textPauseFinData.right = new FormAttachment(98);
		textPauseFinData.top = new FormAttachment(66);
		textPauseFinData.bottom = new FormAttachment(81);
		textPauseFin.setLayoutData(textPauseFinData);
		labelHauteurPalier = new Label(groupReglageGeneral, SWT.FLAT);
		labelHauteurPalier.setText("Hauteur de palier");
		labelHauteurPalierData = new FormData();
		labelHauteurPalierData.left = new FormAttachment(1);
		labelHauteurPalierData.right = new FormAttachment(50);
		labelHauteurPalierData.top = new FormAttachment(82);
		labelHauteurPalierData.bottom = new FormAttachment(97);
		labelHauteurPalier.setLayoutData(labelHauteurPalierData);
		textHauteurPalier = new Text(groupReglageGeneral, SWT.BORDER | SWT.RIGHT);
		textHauteurPalier.setText("");
		textHauteurPalierData = new FormData();
		textHauteurPalierData.left = new FormAttachment(51);
		textHauteurPalierData.right = new FormAttachment(98);
		textHauteurPalierData.top = new FormAttachment(82);
		textHauteurPalierData.bottom = new FormAttachment(97);
		textHauteurPalier.setLayoutData(textHauteurPalierData);
		// creation du groupe de reglage de prosodie
		groupReglageProsodie = new Group(compoReglageSynthetiseur, SWT.FLAT);
		groupReglageProsodie.setText(" Reglage prosodie ");
		groupReglageProsodie.setLayout(new FormLayout());
		groupReglageProsodieData = new FormData();
		groupReglageProsodieData.left = new FormAttachment(1);
		groupReglageProsodieData.right = new FormAttachment(50);
		groupReglageProsodieData.top = new FormAttachment(45);
		groupReglageProsodieData.bottom = new FormAttachment(74);
		groupReglageProsodie.setLayoutData(groupReglageProsodieData);
		labelPasSuite = new Label(groupReglageProsodie, SWT.NONE);
		labelPasSuite.setText("Pas d'avancement (pas)");
		labelPasSuiteData = new FormData();
		labelPasSuiteData.left = new FormAttachment(1);
		labelPasSuiteData.right = new FormAttachment(50);
		labelPasSuiteData.top = new FormAttachment(1);
		labelPasSuiteData.bottom = new FormAttachment(23);
		labelPasSuite.setLayoutData(labelPasSuiteData);
		textPasSuite = new Text(groupReglageProsodie, SWT.BORDER | SWT.RIGHT);
		textPasSuiteData = new FormData();
		textPasSuiteData.left = new FormAttachment(51);
		textPasSuiteData.right = new FormAttachment(98);
		textPasSuiteData.top = new FormAttachment(1);
		textPasSuiteData.bottom = new FormAttachment(23);
		textPasSuite.setLayoutData(textPasSuiteData);
		labelMinSuite = new Label(groupReglageProsodie, SWT.NONE);
		labelMinSuite.setText("Pas d'avancement (min)");
		labelMinSuiteData = new FormData();
		labelMinSuiteData.left = new FormAttachment(1);
		labelMinSuiteData.right = new FormAttachment(50);
		labelMinSuiteData.top = new FormAttachment(24);
		labelMinSuiteData.bottom = new FormAttachment(47);
		labelMinSuite.setLayoutData(labelMinSuiteData);
		textMinSuite = new Text(groupReglageProsodie, SWT.BORDER | SWT.RIGHT);
		textMinSuiteData = new FormData();
		textMinSuiteData.left = new FormAttachment(51);
		textMinSuiteData.right = new FormAttachment(98);
		textMinSuiteData.top = new FormAttachment(24);
		textMinSuiteData.bottom = new FormAttachment(47);
		textMinSuite.setLayoutData(textMinSuiteData);
		labelMaxSuite = new Label(groupReglageProsodie, SWT.NONE);
		labelMaxSuite.setText("Pas d'avancement (max)");
		labelMaxSuiteData = new FormData();
		labelMaxSuiteData.left = new FormAttachment(1);
		labelMaxSuiteData.right = new FormAttachment(50);
		labelMaxSuiteData.top = new FormAttachment(48);
		labelMaxSuiteData.bottom = new FormAttachment(71);
		labelMaxSuite.setLayoutData(labelMaxSuiteData);
		textMaxSuite = new Text(groupReglageProsodie, SWT.BORDER | SWT.RIGHT);
		textMaxSuiteData = new FormData();
		textMaxSuiteData.left = new FormAttachment(51);
		textMaxSuiteData.right = new FormAttachment(98);
		textMaxSuiteData.top = new FormAttachment(48);
		textMaxSuiteData.bottom = new FormAttachment(71);
		textMaxSuite.setLayoutData(textMaxSuiteData);
		labelNbVariationPitch = new Label(groupReglageProsodie, SWT.NONE);
		labelNbVariationPitch.setText("Variation du ptich");
		labelNbVariationPitchData = new FormData();
		labelNbVariationPitchData.left = new FormAttachment(1);
		labelNbVariationPitchData.right = new FormAttachment(50);
		labelNbVariationPitchData.top = new FormAttachment(72);
		labelNbVariationPitchData.bottom = new FormAttachment(95);
		labelNbVariationPitch.setLayoutData(labelNbVariationPitchData);
		textNbVariationPitch = new Text(groupReglageProsodie, SWT.BORDER | SWT.RIGHT);
		textNbVariationPitchData = new FormData();
		textNbVariationPitchData.left = new FormAttachment(51);
		textNbVariationPitchData.right = new FormAttachment(98);
		textNbVariationPitchData.top = new FormAttachment(72);
		textNbVariationPitchData.bottom = new FormAttachment(95);
		textNbVariationPitch.setLayoutData(textNbVariationPitchData);
		// creation du groupe de reglage des courbes
		groupReglageCourbe = new Group(compoReglageSynthetiseur, SWT.FLAT);
		groupReglageCourbe.setLayout(new FormLayout());
		groupReglageCourbe.setText(" Reglage des courbes ");
		groupReglageCourbeData = new FormData();
		groupReglageCourbeData.left = new FormAttachment(51);
		groupReglageCourbeData.right = new FormAttachment(99);
		groupReglageCourbeData.top = new FormAttachment(1);
		groupReglageCourbeData.bottom = new FormAttachment(74);
		groupReglageCourbe.setLayoutData(groupReglageCourbeData);
		labelCoefKMineur = new Label(groupReglageCourbe, SWT.FLAT);
		labelCoefKMineur.setText("Coef K mineur");
		labelCoefKMineurData = new FormData();
		labelCoefKMineurData.left = new FormAttachment(1);
		labelCoefKMineurData.right = new FormAttachment(35);
		labelCoefKMineurData.top = new FormAttachment(2);
		labelCoefKMineurData.bottom = new FormAttachment(9);
		labelCoefKMineur.setLayoutData(labelCoefKMineurData);
		textCoefKMineur = new Text(groupReglageCourbe, SWT.BORDER | SWT.RIGHT);
		textCoefKMineur.setText("");
		textCoefKMineurData = new FormData();
		textCoefKMineurData.left = new FormAttachment(36);
		textCoefKMineurData.right = new FormAttachment(49);
		textCoefKMineurData.top = new FormAttachment(1);
		textCoefKMineurData.bottom = new FormAttachment(9);
		textCoefKMineur.setLayoutData(textCoefKMineurData);
		labelCoefKMajeur = new Label(groupReglageCourbe, SWT.FLAT);
		labelCoefKMajeur.setText("Coef K majeur");
		labelCoefKMajeurData = new FormData();
		labelCoefKMajeurData.left = new FormAttachment(1);
		labelCoefKMajeurData.right = new FormAttachment(35);
		labelCoefKMajeurData.top = new FormAttachment(10);
		labelCoefKMajeurData.bottom = new FormAttachment(18);
		labelCoefKMajeur.setLayoutData(labelCoefKMajeurData);
		textCoefKMajeur = new Text(groupReglageCourbe, SWT.BORDER | SWT.RIGHT);
		textCoefKMajeur.setText("");
		textCoefKMajeurData = new FormData();
		textCoefKMajeurData.left = new FormAttachment(36);
		textCoefKMajeurData.right = new FormAttachment(49);
		textCoefKMajeurData.top = new FormAttachment(10);
		textCoefKMajeurData.bottom = new FormAttachment(18);
		textCoefKMajeur.setLayoutData(textCoefKMajeurData);
		labelCoefExclamation = new Label(groupReglageCourbe, SWT.FLAT);
		labelCoefExclamation.setText("Coef d'excl.");
		labelCoefExclamationData = new FormData();
		labelCoefExclamationData.left = new FormAttachment(1);
		labelCoefExclamationData.right = new FormAttachment(35);
		labelCoefExclamationData.top = new FormAttachment(19);
		labelCoefExclamationData.bottom = new FormAttachment(27);
		labelCoefExclamation.setLayoutData(labelCoefExclamationData);
		textCoefExclamation = new Text(groupReglageCourbe, SWT.BORDER | SWT.RIGHT);
		textCoefExclamation.setText("");
		textCoefExclamationData = new FormData();
		textCoefExclamationData.left = new FormAttachment(36);
		textCoefExclamationData.right = new FormAttachment(49);
		textCoefExclamationData.top = new FormAttachment(19);
		textCoefExclamationData.bottom = new FormAttachment(27);
		textCoefExclamation.setLayoutData(textCoefExclamationData);
		labelCoefHauteurA = new Label(groupReglageCourbe, SWT.FLAT);
		labelCoefHauteurA.setText("Coef hauteur A");
		labelCoefHauteurAData = new FormData();
		labelCoefHauteurAData.left = new FormAttachment(1);
		labelCoefHauteurAData.right = new FormAttachment(35);
		labelCoefHauteurAData.top = new FormAttachment(28);
		labelCoefHauteurAData.bottom = new FormAttachment(36);
		labelCoefHauteurA.setLayoutData(labelCoefHauteurAData);
		textCoefHauteurA = new Text(groupReglageCourbe, SWT.BORDER | SWT.RIGHT);
		textCoefHauteurA.setText("");
		textCoefHauteurAData = new FormData();
		textCoefHauteurAData.left = new FormAttachment(36);
		textCoefHauteurAData.right = new FormAttachment(49);
		textCoefHauteurAData.top = new FormAttachment(28);
		textCoefHauteurAData.bottom = new FormAttachment(36);
		textCoefHauteurA.setLayoutData(textCoefHauteurAData);
		labelPuissanceA = new Label(groupReglageCourbe, SWT.FLAT);
		labelPuissanceA.setText("Puissance de A");
		labelPuissanceAData = new FormData();
		labelPuissanceAData.left = new FormAttachment(1);
		labelPuissanceAData.right = new FormAttachment(35);
		labelPuissanceAData.top = new FormAttachment(37);
		labelPuissanceAData.bottom = new FormAttachment(45);
		labelPuissanceA.setLayoutData(labelPuissanceAData);
		textPuissanceA = new Text(groupReglageCourbe, SWT.BORDER | SWT.RIGHT);
		textPuissanceA.setText("");
		textPuissanceAData = new FormData();
		textPuissanceAData.left = new FormAttachment(36);
		textPuissanceAData.right = new FormAttachment(49);
		textPuissanceAData.top = new FormAttachment(37);
		textPuissanceAData.bottom = new FormAttachment(45);
		textPuissanceA.setLayoutData(textPuissanceAData);
		labelCoefHauteurB = new Label(groupReglageCourbe, SWT.FLAT);
		labelCoefHauteurB.setText("Coef hauteur B");
		labelCoefHauteurBData = new FormData();
		labelCoefHauteurBData.left = new FormAttachment(1);
		labelCoefHauteurBData.right = new FormAttachment(35);
		labelCoefHauteurBData.top = new FormAttachment(46);
		labelCoefHauteurBData.bottom = new FormAttachment(54);
		labelCoefHauteurB.setLayoutData(labelCoefHauteurBData);
		textCoefHauteurB = new Text(groupReglageCourbe, SWT.BORDER | SWT.RIGHT);
		textCoefHauteurB.setText("");
		textCoefHauteurBData = new FormData();
		textCoefHauteurBData.left = new FormAttachment(36);
		textCoefHauteurBData.right = new FormAttachment(49);
		textCoefHauteurBData.top = new FormAttachment(46);
		textCoefHauteurBData.bottom = new FormAttachment(54);
		textCoefHauteurB.setLayoutData(textCoefHauteurBData);
		labelPuissanceB = new Label(groupReglageCourbe, SWT.FLAT);
		labelPuissanceB.setText("Puissance de B");
		labelPuissanceBData = new FormData();
		labelPuissanceBData.left = new FormAttachment(1);
		labelPuissanceBData.right = new FormAttachment(35);
		labelPuissanceBData.top = new FormAttachment(55);
		labelPuissanceBData.bottom = new FormAttachment(63);
		labelPuissanceB.setLayoutData(labelPuissanceBData);
		textPuissanceB = new Text(groupReglageCourbe, SWT.BORDER | SWT.RIGHT);
		textPuissanceB.setText("");
		textPuissanceBData = new FormData();
		textPuissanceBData.left = new FormAttachment(36);
		textPuissanceBData.right = new FormAttachment(49);
		textPuissanceBData.top = new FormAttachment(55);
		textPuissanceBData.bottom = new FormAttachment(63);
		textPuissanceB.setLayoutData(textPuissanceBData);
		labelCoefHauteurC = new Label(groupReglageCourbe, SWT.FLAT);
		labelCoefHauteurC.setText("Coef hauteur C");
		labelCoefHauteurCData = new FormData();
		labelCoefHauteurCData.left = new FormAttachment(1);
		labelCoefHauteurCData.right = new FormAttachment(35);
		labelCoefHauteurCData.top = new FormAttachment(64);
		labelCoefHauteurCData.bottom = new FormAttachment(72);
		labelCoefHauteurC.setLayoutData(labelCoefHauteurCData);
		textCoefHauteurC = new Text(groupReglageCourbe, SWT.BORDER | SWT.RIGHT);
		textCoefHauteurC.setText("");
		textCoefHauteurCData = new FormData();
		textCoefHauteurCData.left = new FormAttachment(36);
		textCoefHauteurCData.right = new FormAttachment(49);
		textCoefHauteurCData.top = new FormAttachment(64);
		textCoefHauteurCData.bottom = new FormAttachment(72);
		textCoefHauteurC.setLayoutData(textCoefHauteurCData);
		labelPuissanceC = new Label(groupReglageCourbe, SWT.FLAT);
		labelPuissanceC.setText("Puissance de C");
		labelPuissanceCData = new FormData();
		labelPuissanceCData.left = new FormAttachment(1);
		labelPuissanceCData.right = new FormAttachment(35);
		labelPuissanceCData.top = new FormAttachment(73);
		labelPuissanceCData.bottom = new FormAttachment(81);
		labelPuissanceC.setLayoutData(labelPuissanceCData);
		textPuissanceC = new Text(groupReglageCourbe, SWT.BORDER | SWT.RIGHT);
		textPuissanceC.setText("");
		textPuissanceCData = new FormData();
		textPuissanceCData.left = new FormAttachment(36);
		textPuissanceCData.right = new FormAttachment(49);
		textPuissanceCData.top = new FormAttachment(73);
		textPuissanceCData.bottom = new FormAttachment(81);
		textPuissanceC.setLayoutData(textPuissanceCData);
		labelCoefHSqrtC = new Label(groupReglageCourbe, SWT.FLAT);
		labelCoefHSqrtC.setText("Coef hauteur (S) C");
		labelCoefHSqrtCData = new FormData();
		labelCoefHSqrtCData.left = new FormAttachment(1);
		labelCoefHSqrtCData.right = new FormAttachment(35);
		labelCoefHSqrtCData.top = new FormAttachment(82);
		labelCoefHSqrtCData.bottom = new FormAttachment(90);
		labelCoefHSqrtC.setLayoutData(labelCoefHSqrtCData);
		textCoefHSqrtC = new Text(groupReglageCourbe, SWT.BORDER | SWT.RIGHT);
		textCoefHSqrtC.setText("");
		textCoefHSqrtCData = new FormData();
		textCoefHSqrtCData.left = new FormAttachment(36);
		textCoefHSqrtCData.right = new FormAttachment(49);
		textCoefHSqrtCData.top = new FormAttachment(82);
		textCoefHSqrtCData.bottom = new FormAttachment(90);
		textCoefHSqrtC.setLayoutData(textCoefHSqrtCData);
		labelCoefHauteurD = new Label(groupReglageCourbe, SWT.FLAT);
		labelCoefHauteurD.setText("Coef hauteur D");
		labelCoefHauteurDData = new FormData();
		labelCoefHauteurDData.left = new FormAttachment(1);
		labelCoefHauteurDData.right = new FormAttachment(35);
		labelCoefHauteurDData.top = new FormAttachment(91);
		labelCoefHauteurDData.bottom = new FormAttachment(99);
		labelCoefHauteurD.setLayoutData(labelCoefHauteurDData);
		textCoefHauteurD = new Text(groupReglageCourbe, SWT.BORDER | SWT.RIGHT);
		textCoefHauteurD.setText("");
		textCoefHauteurDData = new FormData();
		textCoefHauteurDData.left = new FormAttachment(36);
		textCoefHauteurDData.right = new FormAttachment(49);
		textCoefHauteurDData.top = new FormAttachment(91);
		textCoefHauteurDData.bottom = new FormAttachment(99);
		textCoefHauteurD.setLayoutData(textCoefHauteurDData);
		labelCoefHNmoins1D = new Label(groupReglageCourbe, SWT.FLAT);
		labelCoefHNmoins1D.setText("Coef hauteur N-1 D");
		labelCoefHNmoins1DData = new FormData();
		labelCoefHNmoins1DData.left = new FormAttachment(50);
		labelCoefHNmoins1DData.right = new FormAttachment(85);
		labelCoefHNmoins1DData.top = new FormAttachment(1);
		labelCoefHNmoins1DData.bottom = new FormAttachment(9);
		labelCoefHNmoins1D.setLayoutData(labelCoefHNmoins1DData);
		textCoefHNmoins1D = new Text(groupReglageCourbe, SWT.BORDER | SWT.RIGHT);
		textCoefHNmoins1D.setText("");
		textCoefHNmoins1DData = new FormData();
		textCoefHNmoins1DData.left = new FormAttachment(86);
		textCoefHNmoins1DData.right = new FormAttachment(99);
		textCoefHNmoins1DData.top = new FormAttachment(1);
		textCoefHNmoins1DData.bottom = new FormAttachment(9);
		textCoefHNmoins1D.setLayoutData(textCoefHNmoins1DData);
		labelCoefHauteurE = new Label(groupReglageCourbe, SWT.FLAT);
		labelCoefHauteurE.setText("Coef hauteur E");
		labelCoefHauteurEData = new FormData();
		labelCoefHauteurEData.left = new FormAttachment(50);
		labelCoefHauteurEData.right = new FormAttachment(85);
		labelCoefHauteurEData.top = new FormAttachment(10);
		labelCoefHauteurEData.bottom = new FormAttachment(18);
		labelCoefHauteurE.setLayoutData(labelCoefHauteurEData);
		textCoefHauteurE = new Text(groupReglageCourbe, SWT.BORDER | SWT.RIGHT);
		textCoefHauteurE.setText("");
		textCoefHauteurEData = new FormData();
		textCoefHauteurEData.left = new FormAttachment(86);
		textCoefHauteurEData.right = new FormAttachment(99);
		textCoefHauteurEData.top = new FormAttachment(10);
		textCoefHauteurEData.bottom = new FormAttachment(18);
		textCoefHauteurE.setLayoutData(textCoefHauteurEData);
		labelCoefHNmoins1E = new Label(groupReglageCourbe, SWT.FLAT);
		labelCoefHNmoins1E.setText("Coef hauteur N-1 E");
		labelCoefHNmoins1EData = new FormData();
		labelCoefHNmoins1EData.left = new FormAttachment(50);
		labelCoefHNmoins1EData.right = new FormAttachment(85);
		labelCoefHNmoins1EData.top = new FormAttachment(19);
		labelCoefHNmoins1EData.bottom = new FormAttachment(27);
		labelCoefHNmoins1E.setLayoutData(labelCoefHNmoins1EData);
		textCoefHNmoins1E = new Text(groupReglageCourbe, SWT.BORDER | SWT.RIGHT);
		textCoefHNmoins1E.setText("");
		textCoefHNmoins1EData = new FormData();
		textCoefHNmoins1EData.left = new FormAttachment(86);
		textCoefHNmoins1EData.right = new FormAttachment(99);
		textCoefHNmoins1EData.top = new FormAttachment(19);
		textCoefHNmoins1EData.bottom = new FormAttachment(27);
		textCoefHNmoins1E.setLayoutData(textCoefHNmoins1EData);
		// groupe analyseur
		groupAnalyseur = new Group(compoReglageSynthetiseur, SWT.FLAT);
		groupAnalyseur.setLayout(new FormLayout());
		groupAnalyseur.setText(" Reglage de l'analyseur ");
		groupAnalyseurData = new FormData();
		groupAnalyseurData.left = new FormAttachment(1);
		groupAnalyseurData.right = new FormAttachment(99);
		groupAnalyseurData.top = new FormAttachment(75);
		groupAnalyseurData.bottom = new FormAttachment(99);
		groupAnalyseur.setLayoutData(groupAnalyseurData);
		labelAnalyseurAmplitude = new Label(groupAnalyseur, SWT.FLAT);
		labelAnalyseurAmplitude.setText("Amplitude");
		labelAnalyseurAmplitudeData = new FormData();
		labelAnalyseurAmplitudeData.left = new FormAttachment(1);
		labelAnalyseurAmplitudeData.right = new FormAttachment(25);
		labelAnalyseurAmplitudeData.top = new FormAttachment(7);
		labelAnalyseurAmplitudeData.bottom = new FormAttachment(32);
		labelAnalyseurAmplitude.setLayoutData(labelAnalyseurAmplitudeData);
		textAnalyseurAmplitude = new Text(groupAnalyseur, SWT.BORDER | SWT.RIGHT);
		textAnalyseurAmplitudeData = new FormData();
		textAnalyseurAmplitudeData.left = new FormAttachment(26);
		textAnalyseurAmplitudeData.right = new FormAttachment(49);
		textAnalyseurAmplitudeData.top = new FormAttachment(1);
		textAnalyseurAmplitudeData.bottom = new FormAttachment(31);
		textAnalyseurAmplitude.setLayoutData(textAnalyseurAmplitudeData);
		labelAnalyseurFrequence = new Label(groupAnalyseur, SWT.FLAT);
		labelAnalyseurFrequence.setText("Frequence");
		labelAnalyseurFrequenceData = new FormData();
		labelAnalyseurFrequenceData.left = new FormAttachment(1);
		labelAnalyseurFrequenceData.right = new FormAttachment(25);
		labelAnalyseurFrequenceData.top = new FormAttachment(39);
		labelAnalyseurFrequenceData.bottom = new FormAttachment(65);
		labelAnalyseurFrequence.setLayoutData(labelAnalyseurFrequenceData);
		textAnalyseurFrequence = new Text(groupAnalyseur, SWT.BORDER | SWT.RIGHT);
		textAnalyseurFrequenceData = new FormData();
		textAnalyseurFrequenceData.left = new FormAttachment(26);
		textAnalyseurFrequenceData.right = new FormAttachment(49);
		textAnalyseurFrequenceData.top = new FormAttachment(32);
		textAnalyseurFrequenceData.bottom = new FormAttachment(63);
		textAnalyseurFrequence.setLayoutData(textAnalyseurFrequenceData);
		labelAnalyseurTempsConsonne = new Label(groupAnalyseur, SWT.FLAT);
		labelAnalyseurTempsConsonne.setText("Temps Consonne");
		labelAnalyseurTempsConsonneData = new FormData();
		labelAnalyseurTempsConsonneData.left = new FormAttachment(1);
		labelAnalyseurTempsConsonneData.right = new FormAttachment(25);
		labelAnalyseurTempsConsonneData.top = new FormAttachment(72);
		labelAnalyseurTempsConsonneData.bottom = new FormAttachment(99);
		labelAnalyseurTempsConsonne.setLayoutData(labelAnalyseurTempsConsonneData);
		textAnalyseurTempsConsonne = new Text(groupAnalyseur, SWT.BORDER | SWT.RIGHT);
		textAnalyseurTempsConsonneData = new FormData();
		textAnalyseurTempsConsonneData.left = new FormAttachment(26);
		textAnalyseurTempsConsonneData.right = new FormAttachment(49);
		textAnalyseurTempsConsonneData.top = new FormAttachment(64);
		textAnalyseurTempsConsonneData.bottom = new FormAttachment(95);
		textAnalyseurTempsConsonne.setLayoutData(textAnalyseurTempsConsonneData);
		labelAnalyseurTempsVoyelle = new Label(groupAnalyseur, SWT.FLAT);
		labelAnalyseurTempsVoyelle.setText("Temps Voyelle");
		labelAnalyseurTempsVoyelleData = new FormData();
		labelAnalyseurTempsVoyelleData.left = new FormAttachment(50);
		labelAnalyseurTempsVoyelleData.right = new FormAttachment(74);
		labelAnalyseurTempsVoyelleData.top = new FormAttachment(7);
		labelAnalyseurTempsVoyelleData.bottom = new FormAttachment(32);
		labelAnalyseurTempsVoyelle.setLayoutData(labelAnalyseurTempsVoyelleData);
		textAnalyseurTempsVoyelle = new Text(groupAnalyseur, SWT.BORDER | SWT.RIGHT);
		textAnalyseurTempsVoyelleData = new FormData();
		textAnalyseurTempsVoyelleData.left = new FormAttachment(75);
		textAnalyseurTempsVoyelleData.right = new FormAttachment(99);
		textAnalyseurTempsVoyelleData.top = new FormAttachment(1);
		textAnalyseurTempsVoyelleData.bottom = new FormAttachment(31);
		textAnalyseurTempsVoyelle.setLayoutData(textAnalyseurTempsVoyelleData);
		labelAnalyseurTempsLongue = new Label(groupAnalyseur, SWT.FLAT);
		labelAnalyseurTempsLongue.setText("Temps Longue");
		labelAnalyseurTempsLongueData = new FormData();
		labelAnalyseurTempsLongueData.left = new FormAttachment(50);
		labelAnalyseurTempsLongueData.right = new FormAttachment(74);
		labelAnalyseurTempsLongueData.top = new FormAttachment(39);
		labelAnalyseurTempsLongueData.bottom = new FormAttachment(65);
		labelAnalyseurTempsLongue.setLayoutData(labelAnalyseurTempsLongueData);
		textAnalyseurTempsLongue = new Text(groupAnalyseur, SWT.BORDER | SWT.RIGHT);
		textAnalyseurTempsLongueData = new FormData();
		textAnalyseurTempsLongueData.left = new FormAttachment(75);
		textAnalyseurTempsLongueData.right = new FormAttachment(99);
		textAnalyseurTempsLongueData.top = new FormAttachment(32);
		textAnalyseurTempsLongueData.bottom = new FormAttachment(63);
		textAnalyseurTempsLongue.setLayoutData(textAnalyseurTempsLongueData);
		labelAnalyseurNombreCouple = new Label(groupAnalyseur, SWT.FLAT);
		labelAnalyseurNombreCouple.setText("Nombre de Couple");
		labelAnalyseurNombreCoupleData = new FormData();
		labelAnalyseurNombreCoupleData.left = new FormAttachment(50);
		labelAnalyseurNombreCoupleData.right = new FormAttachment(74);
		labelAnalyseurNombreCoupleData.top = new FormAttachment(72);
		labelAnalyseurNombreCoupleData.bottom = new FormAttachment(99);
		labelAnalyseurNombreCouple.setLayoutData(labelAnalyseurNombreCoupleData);
		textAnalyseurNombreCouple = new Text(groupAnalyseur, SWT.BORDER | SWT.RIGHT);
		textAnalyseurNombreCoupleData = new FormData();
		textAnalyseurNombreCoupleData.left = new FormAttachment(75);
		textAnalyseurNombreCoupleData.right = new FormAttachment(99);
		textAnalyseurNombreCoupleData.top = new FormAttachment(64);
		textAnalyseurNombreCoupleData.bottom = new FormAttachment(95);
		textAnalyseurNombreCouple.setLayoutData(textAnalyseurNombreCoupleData);
		
		// creation des boutton
		bouttonOk = new Button(fenetrePrincipale, SWT.PUSH);
		bouttonOk.setText("Ok");
		bouttonOkData = new FormData();
		bouttonOkData.left = new FormAttachment(60);
		bouttonOkData.right = new FormAttachment(70);
		bouttonOkData.top = new FormAttachment(91);
		bouttonOkData.bottom = new FormAttachment(99);
		bouttonOk.setLayoutData(bouttonOkData);
		
		bouttonAppliquer = new Button(fenetrePrincipale, SWT.PUSH);
		bouttonAppliquer.setText("Appliquer");
		bouttonAppliquerData = new FormData();
		bouttonAppliquerData.left = new FormAttachment(71);
		bouttonAppliquerData.right = new FormAttachment(85);
		bouttonAppliquerData.top = new FormAttachment(91);
		bouttonAppliquerData.bottom = new FormAttachment(99);
		bouttonAppliquer.setLayoutData(bouttonAppliquerData);
		
		bouttonAnnuler = new Button(fenetrePrincipale, SWT.PUSH);
		bouttonAnnuler.setText("Annuler");
		bouttonAnnulerData = new FormData();
		bouttonAnnulerData.left = new FormAttachment(86);
		bouttonAnnulerData.right = new FormAttachment(99);
		bouttonAnnulerData.top = new FormAttachment(91);
		bouttonAnnulerData.bottom = new FormAttachment(99);
		bouttonAnnuler.setLayoutData(bouttonAnnulerData);
		
		// affectation des images
		try {
			fenetrePrincipale.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"icone_application_16_16.png"));
			bouttonAnnuler.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"annuler_16_16.png"));
			bouttonAppliquer.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"synchroniser_prosodie_16_16.png"));
			bouttonOk.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"appliquer_16_16.png"));
			bouttonExeWindows.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"parcourir_16_16.png"));
			bouttonExeLinux.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"parcourir_16_16.png"));
			bouttonExeMac.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"parcourir_16_16.png"));
			bouttonFichierPhoWav.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"parcourir_16_16.png"));
			bouttonPreposition.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"parcourir_16_16.png"));
			bouttonRegle.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"parcourir_16_16.png"));
			bouttonException.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"parcourir_16_16.png"));
			bouttonAccronyme.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"parcourir_16_16.png"));
			bouttonImgPath.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"parcourir_16_16.png"));
			bouttonAnalyseurWav.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"parcourir_16_16.png"));
			tabItemCheminAcces.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"configuration_sivox_16_16.png"));
			tabItemParametrageVoix.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"configuration_sivox_16_16.png"));
			tabItemReglageSynthetiseur.setImage(new Image(ecran, InformationSysteme.getRepertoireImage16()+"configuration_sivox_16_16.png"));
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(fenetrePrincipale, SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("Le chargement des images a echoue, le programme peut ne pas fonctionner correctement !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
		}
		
		// chargement des information
		chargerInformation();
		
		//filtre sur le Table
		final TableEditor editor = new TableEditor(tableVoix);
		editor.horizontalAlignment = SWT.LEFT;
		editor.grabHorizontal = true;
		tableVoix.addListener(SWT.MouseDown, new Listener() {
			public void handleEvent(Event event) {
				Rectangle clientArea = tableVoix.getClientArea();
				Point pt = new Point(event.x, event.y);
				int index = tableVoix.getTopIndex();
				while (index < tableVoix.getItemCount()) {
					boolean visible = false;
					final TableItem item = tableVoix.getItem(index);
					for (int i = 1; i < tableVoix.getColumnCount(); i++) {
						Rectangle rect = item.getBounds(i);
						if (rect.contains(pt)) {
							final int column = i;
							final Text text = new Text(tableVoix, SWT.NONE);
							Listener textListener = new Listener() {
								public void handleEvent(final Event e) {
									switch (e.type) {
									case SWT.FocusOut:
										if (column==3 ||column==2 || column==1)
										{
											item.setText(column, text.getText());
											text.dispose();
										}
										break;						
									case SWT.Traverse:
										switch (e.detail) {
										case SWT.TRAVERSE_RETURN:
											if (column==3 ||column==2 ||column ==1)
											{
												item.setText(column, text.getText());
											}
										case SWT.TRAVERSE_ESCAPE:
											text.dispose();
											e.doit = false;
										}
										break;
									}
								}
							};
							text.addListener(SWT.FocusOut, textListener);
							text.addListener(SWT.Traverse, textListener);
							editor.setEditor(text, item, i);
							text.setText(item.getText(i));
							text.selectAll();
							text.setFocus();
							return;
						}
						if (!visible && rect.intersects(clientArea)) {
							visible = true;
						}
					}
					if (!visible)
						return;
					index++;
				}
			}
		});
		
		//evenement sur le boutton executable windows (pour choisir l exe windows)
		bouttonExeWindows.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog dialogExeWindows = new FileDialog(fenetrePrincipale, SWT.OPEN);
				dialogExeWindows.setText("choix de l'executable WINDOWS");
				if(InformationSysteme.existFile(textExeWindows.getText()))
					dialogExeWindows.setFilterPath(textExeWindows.getText());
				String file = dialogExeWindows.open();
				if((file != null) && (file.length() > 0))
				{
					textExeWindows.setText(file.replace("\\", "/"));
				}
			}
		});
		
		//evenement sur le boutton executable linux (pour choisir l exe linux)
		bouttonExeLinux.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog dialogExeLinux = new FileDialog(fenetrePrincipale, SWT.OPEN);
				dialogExeLinux.setText("choix de l'executable LINUX");
				if(InformationSysteme.existFile(textExeLinux.getText()))
					dialogExeLinux.setFilterPath(textExeLinux.getText());
				String file = dialogExeLinux.open();
				if((file != null) && (file.length() > 0))
				{
					textExeLinux.setText(file.replace("\\", "/"));
				}
			}
		});
		
		//evenement sur le boutton executable mac (pour choisir l exe mac)
		bouttonExeMac.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog dialogExeMac = new FileDialog(fenetrePrincipale, SWT.OPEN);
				dialogExeMac.setText("choix de l'executable MAC");
				if(InformationSysteme.existFile(textExeMac.getText()))
					dialogExeMac.setFilterPath(textExeMac.getText());
				String file = dialogExeMac.open();
				if((file != null) && (file.length() > 0))
				{
					textExeMac.setText(file.replace("\\", "/"));
				}
			}
		});
		
		//evenement sur le boutton fichier pho wave (pour selectionner l emplacement des pho wav temporaire)
		bouttonFichierPhoWav.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				DirectoryDialog dialogPhoWav = new DirectoryDialog(fenetrePrincipale, SWT.OPEN);
				dialogPhoWav.setText("choix du repertoire des fichier temporaire PHO/WAV");
				if(InformationSysteme.existFolder(textFichierPhoWav.getText()))
					dialogPhoWav.setFilterPath(textFichierPhoWav.getText());
				String rep = dialogPhoWav.open();
				if((rep != null) && (rep.length() > 0))
				{
					if((!rep.endsWith("/")) && (!rep.endsWith("\\")))
						rep = rep+"/";
					textFichierPhoWav.setText(rep.replace("\\", "/"));
				}
			}
		});
		
		//evenement sur la selection du fichier de preposition (pour selectionner le fichier de preposition)
		bouttonPreposition.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog dialogPreposition = new FileDialog(fenetrePrincipale, SWT.OPEN);
				dialogPreposition.setText("choix du fichier de preposition");
				if(InformationSysteme.existFile(textPreposition.getText()))
					dialogPreposition.setFilterPath(textPreposition.getText());
				String file = dialogPreposition.open();
				if((file != null) && (file.length() > 0))
				{
					textPreposition.setText(file.replace("\\", "/"));
				}
			}
		});
		
		//evenement sur la selection du fichier de regle (pour selectionner le fichier de regle)
		bouttonRegle.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog dialogRegle = new FileDialog(fenetrePrincipale, SWT.OPEN);
				dialogRegle.setText("choix du fichier de regle");
				if(InformationSysteme.existFile(textRegle.getText()))
					dialogRegle.setFilterPath(textRegle.getText());
				String file = dialogRegle.open();
				if((file != null) && (file.length() > 0))
				{
					textRegle.setText(file.replace("\\", "/"));
				}
			}
		});
		
		//evenement sur la selection du fichier des exceptions (pour selectionner le fichier des exceptions)
		bouttonException.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog dialogException = new FileDialog(fenetrePrincipale, SWT.OPEN);
				dialogException.setText("choix du fichier des exceptions");
				if(InformationSysteme.existFile(textException.getText()))
					dialogException.setFilterPath(textException.getText());
				String file = dialogException.open();
				if((file != null) && (file.length() > 0))
				{
					textException.setText(file.replace("\\", "/"));
				}
			}
		});
		
		//evenement sur la selection du ficher des accronymes (pour selectionner le fichier des accronymes)
		bouttonAccronyme.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog dialogAccronyme = new FileDialog(fenetrePrincipale, SWT.OPEN);
				dialogAccronyme.setText("choix du fichier des acronymes");
				if(InformationSysteme.existFile(textAccronyme.getText()))
					dialogAccronyme.setFilterPath(textAccronyme.getText());
				String file = dialogAccronyme.open();
				if((file != null) && (file.length() > 0))
				{
					textAccronyme.setText(file.replace("\\", "/"));
				}
			}
		});
		
		//evenement sur la selection du repertoire des images (pour selectionner un repertoire differents pour les images)
		bouttonImgPath.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				DirectoryDialog dialogImgPath = new DirectoryDialog(fenetrePrincipale, SWT.OPEN);
				dialogImgPath.setText("choix du repertoire des images");
				if(InformationSysteme.existFolder(textImgPath.getText()))
					dialogImgPath.setFilterPath(textImgPath.getText());
				String rep = dialogImgPath.open();
				if((rep != null) && (rep.length() > 0))
				{
					if((!rep.endsWith("/")) && (!rep.endsWith("\\")))
						rep = rep+"/";
					textImgPath.setText(rep.replace("\\", "/"));
				}
			}
		});
		
		//evenement sur la selection de l analyseur wave externe
		bouttonAnalyseurWav.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				FileDialog dialogAnalyseurWav = new FileDialog(fenetrePrincipale, SWT.OPEN);
				dialogAnalyseurWav.setText("choix de l'analyseur wave");
				if(InformationSysteme.existFile(textAnalyseurWav.getText()))
					dialogAnalyseurWav.setFilterPath(textAnalyseurWav.getText());
				String file = dialogAnalyseurWav.open();
				if((file != null) && (file.length() > 0))
				{
					textAnalyseurWav.setText(file.replace("\\", "/"));
				}
			}
		});
		
		// evenement sur la fermeture de la fenetre
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
		
		// evenement sur le boutton appliquer (application des changements/ecriture dans le fichier de configuration)
		bouttonAppliquer.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				miseAJour();
			}
		});
		
		// evenement sur le boutton ok (application des changements/ecriture dans le fichier de configuration + quitter si ok)
		bouttonOk.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(miseAJour())
				{
					sortie = true;
				}
			}
		});
		
		// evenement sur le boutton annuler (fermeture sans prise en compte de changement)
		bouttonAnnuler.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				sortie = true;
			}
		});
	}
	
	/**
	 * Methode qui ouvre la fenetre de configuration de vocalyze
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void open()
	{
		fenetrePrincipale.open();
		i.getShell().setEnabled(false);
		while (!fenetrePrincipale.isDisposed() && (sortie == false))
	    {
	    	if (!ecran.readAndDispatch())
	    		ecran.sleep();
	    }
	    
	    // liberation de la fenetre et de l ecran
		i.setConfigurationSIVOXClosed();
		i.getShell().setEnabled(true);
	    fenetrePrincipale.dispose();
	}
	
	/**
	 * Methode qui charge les donnees du fichier de configuration
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	private void chargerInformation()
	{
		String temp = new String("");
		String temp2 = new String("");
		String temp3 = new String("");
		//EXE LINUX
		if((temp = ConfigFile.rechercher("EXE_LINUX")) == null)
			textExeLinux.setText("");
		else
			textExeLinux.setText(temp);
		if(!InformationSysteme.isAbsolutePath(textExeLinux.getText()))
			textExeLinux.setText(InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+textExeLinux.getText());
		if(!InformationSysteme.existFile(textExeLinux.getText()))
			textExeLinux.setText("");
		//EXE WINDOWS
		if((temp = ConfigFile.rechercher("EXE_WINDOWS")) == null)
			textExeWindows.setText("");
		else
			textExeWindows.setText(temp);
		if(!InformationSysteme.isAbsolutePath(textExeWindows.getText()))
			textExeWindows.setText(InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+textExeWindows.getText());
		if(!InformationSysteme.existFile(textExeWindows.getText()))
			textExeWindows.setText("");
		//EXE MAC
		if((temp = ConfigFile.rechercher("EXE_MAC")) == null)
			textExeMac.setText("");
		else
			textExeMac.setText(temp);
		if(!InformationSysteme.isAbsolutePath(textExeMac.getText()))
			textExeMac.setText(InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+textExeMac.getText());
		if(!InformationSysteme.existFile(textExeMac.getText()))
			textExeMac.setText("");
		//ANALYSEUR WAVE
		if((temp = ConfigFile.rechercher("ANALYSEUR_WAVE")) == null)
			textAnalyseurWav.setText("");
		else
			textAnalyseurWav.setText(temp);
		if(!InformationSysteme.isAbsolutePath(textAnalyseurWav.getText()))
			textAnalyseurWav.setText(InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+textAnalyseurWav.getText());
		if(!InformationSysteme.existFile(textAnalyseurWav.getText()))
			textAnalyseurWav.setText("");
		//REPERTOIRE FICHIER PHO ET WAVE
		if((temp = ConfigFile.rechercher("REPERTOIRE_PHO_WAV")) == null)
			textFichierPhoWav.setText("");
		else
			textFichierPhoWav.setText(temp);
		if(!InformationSysteme.isAbsolutePath(textFichierPhoWav.getText()))
			textFichierPhoWav.setText(InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+textFichierPhoWav.getText());
		if(!textFichierPhoWav.getText().endsWith("/"))
			textFichierPhoWav.setText(textFichierPhoWav.getText()+"/");
		if(!InformationSysteme.existFolder(textFichierPhoWav.getText()))
			textFichierPhoWav.setText("");
		//FICHIER PREPOSITIONS
		if((temp = ConfigFile.rechercher("PREPOSITIONS")) == null)
			textPreposition.setText("");
		else
			textPreposition.setText(temp);
		if(!InformationSysteme.isAbsolutePath(textPreposition.getText()))
			textPreposition.setText(InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+textPreposition.getText());
		if(!InformationSysteme.existFile(textPreposition.getText()))
			textPreposition.setText("");
		//FICHIER REGLES
		if((temp = ConfigFile.rechercher("REGLES")) == null)
			textRegle.setText("");
		else
			textRegle.setText(temp);
		if(!InformationSysteme.isAbsolutePath(textRegle.getText()))
			textRegle.setText(InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+textRegle.getText());
		if(!InformationSysteme.existFile(textRegle.getText()))
			textRegle.setText("");
		//FICHIER EXCEPTIONS
		if((temp = ConfigFile.rechercher("EXCEPTIONS")) == null)
			textException.setText("");
		else
			textException.setText(temp);
		if(!InformationSysteme.isAbsolutePath(textException.getText()))
			textException.setText(InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+textException.getText());
		if(!InformationSysteme.existFile(textException.getText()))
			textException.setText("");
		//FICHIER ACCRONYMES
		if((temp = ConfigFile.rechercher("ACCRONYMES")) == null)
			textAccronyme.setText("");
		else
			textAccronyme.setText(temp);
		if(!InformationSysteme.isAbsolutePath(textAccronyme.getText()))
			textAccronyme.setText(InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+textAccronyme.getText());
		if(!InformationSysteme.existFile(textAccronyme.getText()))
			textAccronyme.setText("");
		//IMAGE PATH
		if((temp = ConfigFile.rechercher("IMG_PATH")) == null)
			textImgPath.setText("");
		else
			textImgPath.setText(temp);
		if(!InformationSysteme.isAbsolutePath(textImgPath.getText()))
			textImgPath.setText(InformationSysteme.getRepertoireApplication()+"/"+textImgPath.getText());
		if(!textImgPath.getText().endsWith("/"))
			textImgPath.setText(textImgPath.getText()+"/");
		if(!InformationSysteme.existFolder(textImgPath.getText()))
			textImgPath.setText("");
		if((temp = ConfigFile.rechercher("ENCODAGE_FICHIER")) == null)
			comboEncodageFichier.select(0);
		else
		{
			if(temp.equalsIgnoreCase("ISO-8859-15"))
				comboEncodageFichier.select(0);
			else if(temp.equalsIgnoreCase("UTF-8"))
				comboEncodageFichier.select(1);
			else if(temp.equalsIgnoreCase("UTF-16"))
				comboEncodageFichier.select(2);
			else
				comboEncodageFichier.select(3);
		}
		//NOMBRE DE VOIX
		if((temp = ConfigFile.rechercher("NBVOIX")) == null)
			textNbVoix.setText("1");
		else
			textNbVoix.setText(temp);
		//FREQUENCE INITIALE
		if((temp = ConfigFile.rechercher("FREQUENCE_INIT")) == null)
			textFrequenceInit.setText("100");
		else
			textFrequenceInit.setText(temp);
		//NOM DE LA VOIX 1
		if((temp = ConfigFile.rechercher("NOM_VOIX_1")) == null)
			temp = "Pas de nom";
		//CHEMIN DU FICHIER DE LA VOIX 1
		if((temp2 = ConfigFile.rechercher("VOIX_1")) == null)
			temp2 = "";
		if(!InformationSysteme.isAbsolutePath(temp2))
			temp2 = InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+temp2;
		if(!InformationSysteme.existFile(temp2))
			temp2 = "";
		//RATIO DE LA VOIX 1
		if((temp3 = ConfigFile.rechercher("FR1")) == null)
			temp3 = "1.0";
		TableItem tab1 = new TableItem(tableVoix, SWT.NONE);
		tab1.setText(new String [] {"1", temp, temp2, temp3});
		//NOM DE LA VOIX 2
		if((temp = ConfigFile.rechercher("NOM_VOIX_2")) == null)
			temp = "Pas de nom";
		//CHEMIN DU FICHIER DE LA VOIX 2
		if((temp2 = ConfigFile.rechercher("VOIX_2")) == null)
			temp2 = "";
		if(!InformationSysteme.isAbsolutePath(temp2))
			temp2 = InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+temp2;
		if(!InformationSysteme.existFile(temp2))
			temp2 = "";
		//RATIO DE LA VOIX 2
		if((temp3 = ConfigFile.rechercher("FR2")) == null)
			temp3 = "1.0";
		TableItem tab2 = new TableItem(tableVoix, SWT.NONE);
		tab2.setText(new String [] {"2", temp, temp2, temp3});
		//NOM DE LA VOIX 3
		if((temp = ConfigFile.rechercher("NOM_VOIX_3")) == null)
			temp = "Pas de nom";
		//CHEMIN DU FICHIER DE LA VOIX 3
		if((temp2 = ConfigFile.rechercher("VOIX_3")) == null)
			temp2 = "";
		if(!InformationSysteme.isAbsolutePath(temp2))
			temp2 = InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+temp2;
		if(!InformationSysteme.existFile(temp2))
			temp2 = "";
		//RATIO DE LA VOIX 3
		if((temp3 = ConfigFile.rechercher("FR3")) == null)
			temp3 = "1.0";
		TableItem tab3 = new TableItem(tableVoix, SWT.NONE);
		tab3.setText(new String [] {"3", temp, temp2, temp3});
		//NOM DE LA VOIX 4
		if((temp = ConfigFile.rechercher("NOM_VOIX_4")) == null)
			temp = "Pas de nom";
		//CHEMIN DU FICHIER DE LA VOIX 4
		if((temp2 = ConfigFile.rechercher("VOIX_4")) == null)
			temp2 = "";
		if(!InformationSysteme.isAbsolutePath(temp2))
			temp2 = InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+temp2;
		if(!InformationSysteme.existFile(temp2))
			temp2 = "";
		//RATIO DE LA VOIX FR4
		if((temp3 = ConfigFile.rechercher("FR4")) == null)
			temp3 = "1.0";
		TableItem tab4 = new TableItem(tableVoix, SWT.NONE);
		tab4.setText(new String [] {"4", temp, temp2, temp3});
		//NOM DE LA VOIX 5
		if((temp = ConfigFile.rechercher("NOM_VOIX_5")) == null)
			temp = "Pas de nom";
		//CHEMIN DU FICHIER DE LA VOIX 5
		if((temp2 = ConfigFile.rechercher("VOIX_5")) == null)
			temp2 = "";
		if(!InformationSysteme.isAbsolutePath(temp2))
			temp2 = InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+temp2;
		if(!InformationSysteme.existFile(temp2))
			temp2 = "";
		//RATIO DE LA VOIX 5
		if((temp3 = ConfigFile.rechercher("FR5")) == null)
			temp3 = "1.0";
		TableItem tab5 = new TableItem(tableVoix, SWT.NONE);
		tab5.setText(new String [] {"5", temp, temp2, temp3});
		//NOM DE LA VOIX 6
		if((temp = ConfigFile.rechercher("NOM_VOIX_6")) == null)
			temp = "Pas de nom";
		//CHEMIN DU FICHIER DE LA VOIX 6
		if((temp2 = ConfigFile.rechercher("VOIX_6")) == null)
			temp2 = "";
		if(!InformationSysteme.isAbsolutePath(temp2))
			temp2 = InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+temp2;
		if(!InformationSysteme.existFile(temp2))
			temp2 = "";
		//RATIO DE LA VOIX 6
		if((temp3 = ConfigFile.rechercher("FR6")) == null)
			temp3 = "1.0";
		TableItem tab6 = new TableItem(tableVoix, SWT.NONE);
		tab6.setText(new String [] {"6", temp, temp2, temp3});
		//NOM DE LA VOIX 7
		if((temp = ConfigFile.rechercher("NOM_VOIX_7")) == null)
			temp = "Pas de nom";
		//CHEMIN DU FICHIER DE LA VOIX 7
		if((temp2 = ConfigFile.rechercher("VOIX_7")) == null)
			temp2 = "";
		if(!InformationSysteme.isAbsolutePath(temp2))
			temp2 = InformationSysteme.getRepertoireApplication().replace("\\", "/")+"/"+temp2;
		if(!InformationSysteme.existFile(temp2))
			temp2 = "";
		//RATIO DE LA VOIX 7
		if((temp3 = ConfigFile.rechercher("FR7")) == null)
			temp3 = "1.0";
		TableItem tab7 = new TableItem(tableVoix, SWT.NONE);
		tab7.setText(new String [] {"7", temp, temp2, temp3});
		// chargement des informations de l onglet parametrage du synthetiseur vocal
		if((temp = ConfigFile.rechercher("RAPIDITE")) == null)
			textRapidite.setText("");
		else
			textRapidite.setText(temp);
		if((temp = ConfigFile.rechercher("VOLUME")) == null)
			textVolume.setText("");
		else
			textVolume.setText(temp);
		if((temp = ConfigFile.rechercher("PAUSE_COURTE")) == null)
			textPauseCourte.setText("100");
		else
			textPauseCourte.setText(temp);
		if((temp = ConfigFile.rechercher("PAUSE_LONGUE")) == null)
			textPauseLongue.setText("200");
		else
			textPauseLongue.setText(temp);
		if((temp = ConfigFile.rechercher("PAUSE_FIN")) == null)
			textPauseFin.setText("100");
		else
			textPauseFin.setText(temp);
		if((temp = ConfigFile.rechercher("HAUTEUR_PALIER")) == null)
			textHauteurPalier.setText("");
		else
			textHauteurPalier.setText(temp);
		if((temp = ConfigFile.rechercher("PAS_SUITE")) == null)
			textPasSuite.setText("5");
		else
			textPasSuite.setText(temp);
		if((temp = ConfigFile.rechercher("MIN_SUITE")) == null)
			textMinSuite.setText("5");
		else
			textMinSuite.setText(temp);
		if((temp = ConfigFile.rechercher("MAX_SUITE")) == null)
			textMaxSuite.setText("5");
		else
			textMaxSuite.setText(temp);
		if((temp = ConfigFile.rechercher("NB_VARIATIONS_PITCH")) == null)
			textNbVariationPitch.setText("1");
		else
			textNbVariationPitch.setText(temp);
		if((temp = ConfigFile.rechercher("COEFF_K_MINEUR")) == null)
			textCoefKMineur.setText("1");
		else
			textCoefKMineur.setText(temp);
		if((temp = ConfigFile.rechercher("COEFF_K_MAJEUR")) == null)
			textCoefKMajeur.setText("1");
		else
			textCoefKMajeur.setText(temp);
		if((temp = ConfigFile.rechercher("COEFF_EXCLAMATION")) == null)
			textCoefExclamation.setText("1");
		else
			textCoefExclamation.setText(temp);
		if((temp = ConfigFile.rechercher("COEFF_HAUTEUR_A")) == null)
			textCoefHauteurA.setText("1");
		else
			textCoefHauteurA.setText(temp);
		if((temp = ConfigFile.rechercher("PUISSANCE_A")) == null)
			textPuissanceA.setText("1");
		else
			textPuissanceA.setText(temp);
		if((temp = ConfigFile.rechercher("COEFF_HAUTEUR_B")) == null)
			textCoefHauteurB.setText("1");
		else
			textCoefHauteurB.setText(temp);
		if((temp = ConfigFile.rechercher("PUISSANCE_B")) == null)
			textPuissanceB.setText("1");
		else
			textPuissanceB.setText(temp);
		if((temp = ConfigFile.rechercher("PUISSANCE_C")) == null)
			textPuissanceC.setText("1");
		else
			textPuissanceC.setText(temp);
		if((temp = ConfigFile.rechercher("COEFF_HAUTEUR_C")) == null)
			textCoefHauteurC.setText("1");
		else
			textCoefHauteurC.setText(temp);
		if((temp = ConfigFile.rechercher("COEFF_H_SQRT_C")) == null)
			textCoefHSqrtC.setText("1");
		else
			textCoefHSqrtC.setText(temp);
		if((temp = ConfigFile.rechercher("COEFF_HAUTEUR_D")) == null)
			textCoefHauteurD.setText("1");
		else
			textCoefHauteurD.setText(temp);
		if((temp = ConfigFile.rechercher("COEFF_H_N-1_D")) == null)
			textCoefHNmoins1D.setText("1");
		else
			textCoefHNmoins1D.setText(temp);
		if((temp = ConfigFile.rechercher("COEFF_HAUTEUR_E")) == null)
			textCoefHauteurE.setText("1");
		else
			textCoefHauteurE.setText(temp);
		if((temp = ConfigFile.rechercher("COEFF_H_N-1_E")) == null)
			textCoefHNmoins1E.setText("1");
		else
			textCoefHNmoins1E.setText(temp);
		if((temp = ConfigFile.rechercher("ANALYSER_AMPLITUDE")) == null)
			textAnalyseurAmplitude.setText("10");
		else
			textAnalyseurAmplitude.setText(temp);
		if((temp = ConfigFile.rechercher("ANALYSER_FREQUENCE")) == null)
			textAnalyseurFrequence.setText("50");
		else
			textAnalyseurFrequence.setText(temp);
		if((temp = ConfigFile.rechercher("ANALYSER_TEMPS_CONSONNE")) == null)
			textAnalyseurTempsConsonne.setText("40");
		else
			textAnalyseurTempsConsonne.setText(temp);
		if((temp = ConfigFile.rechercher("ANALYSER_TEMPS_VOYELLE")) == null)
			textAnalyseurTempsVoyelle.setText("40");
		else
			textAnalyseurTempsVoyelle.setText(temp);
		if((temp = ConfigFile.rechercher("ANALYSER_TEMPS_LONGUE")) == null)
			textAnalyseurTempsLongue.setText("20");
		else
			textAnalyseurTempsLongue.setText(temp);
		if((temp = ConfigFile.rechercher("ANALYSER_NOMBRE_COUPLES")) == null)
			textAnalyseurNombreCouple.setText("1");
		else
			textAnalyseurNombreCouple.setText(temp);
	}
	
	/**
	 * Methode qui effectue une verification de tous les champs modifiables
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si la verification est ok et false sinon
	 */
	private boolean verificationInformation()
	{
		if(!InformationSysteme.existFile(textExeLinux.getText()))
		{
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur pour EXE LINUX");
			m.setMessage("Le fichier executable pour LINUX \""+textExeLinux.getText()+"\" n'existe pas !");
			m.open();
			return(false);
		}
		if(!InformationSysteme.existFile(textExeWindows.getText()))
		{
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur pour EXE WINDOWS");
			m.setMessage("Le fichier executable pour WINDOWS \""+textExeWindows.getText()+"\" n'existe pas !");
			m.open();
			return(false);
		}
		if(!InformationSysteme.existFile(textExeMac.getText()))
		{
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur pour EXE MAC");
			m.setMessage("Le fichier executable pour MAC \""+textExeMac.getText()+"\" n'existe pas !");
			m.open();
			return(false);
		}
		if(!InformationSysteme.existFolder(textFichierPhoWav.getText()))
		{
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur pour PHO/WAV");
			m.setMessage("Le repertoire de PHO/WAV \""+textFichierPhoWav.getText()+"\" n'existe pas !");
			m.open();
			return(false);
		}
		if(!InformationSysteme.existFile(textPreposition.getText()))
		{
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur pour les prepositions");
			m.setMessage("Le fichier de preposition \""+textPreposition.getText()+"\" n'existe pas !");
			m.open();
			return(false);
		}
		if(!InformationSysteme.existFile(textRegle.getText()))
		{
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur pour les regle");
			m.setMessage("Le fichier de regle \""+textRegle.getText()+"\" n'existe pas !");
			m.open();
			return(false);
		}
		if(!InformationSysteme.existFile(textException.getText()))
		{
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur pour les exceptions");
			m.setMessage("Le fichier des exceptions \""+textException.getText()+"\" n'existe pas !");
			m.open();
			return(false);
		}
		if(!InformationSysteme.existFile(textAccronyme.getText()))
		{
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur pour les acronymes");
			m.setMessage("Le fichier d'acronyme \""+textAccronyme.getText()+"\" n'existe pas !");
			m.open();
			return(false);
		}
		if(!InformationSysteme.existFolder(textImgPath.getText()))
		{
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de repertoire d'images");
			m.setMessage("Le repertoire d'image \""+textImgPath.getText()+"\" n'existe pas !");
			m.open();
			return(false);
		}
		if(!InformationSysteme.existFile(textAnalyseurWav.getText()))
		{
			textAnalyseurWav.setText("");
		}
		try {
			int val = Integer.parseInt(textFrequenceInit.getText());
			if((val <= 0) || (val > 3000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de de frequence");
				m.setMessage("La frequence initiale doit etre comprise entre 1 et 3000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de frequence");
			m.setMessage("La frequence initiale doit etre un nombre compris entre 1 et 3000");
			m.open();
			return(false);
		}
		for(int i = 0 ; i < tableVoix.getItemCount() ; i++)
		{
			if(tableVoix.getItem(i).getText(1).length() == 0)
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur sur les voix");
				m.setMessage("La voix numero "+(i+1)+" doit avoir un nom");
				m.open();
				return(false);
			}
			if(!InformationSysteme.existFile(tableVoix.getItem(i).getText(2)))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur sur les voix");
				m.setMessage("Le fichier de la voix numero "+(i+1)+" n'existe pas !");
				m.open();
				return(false);
			}
			try {
				double val = Double.parseDouble(tableVoix.getItem(i).getText(3));
				if((val <= 0.0) || (val > 5.0))
				{
					MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
					m.setText("VOCALYSE Si-Vox - Erreur sur les voix");
					m.setMessage("Le ratio de frequence pour la voix "+(i+1)+" doit etre compris entre 0.1 et 5.0");
					m.open();
					return(false);
				}
			} catch (Exception ex) {
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur sur les voix");
				m.setMessage("Le ratio de frequence pour la voix "+(i+1)+" doit etre un nombre compris entre 0.1 et 5.0");
				m.open();
				return(false);
			}
		}
		try {
			double val = Double.parseDouble(textRapidite.getText());
			if((val < 0.1)||(val>10.0))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("La rapidite doit etre comprise entre 0.1 et 10.0");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("La rapidite est un nombre compris entre 0.1 et 10.0");
			m.open();
			return(false);
		}
		try {
			double val = Double.parseDouble(textVolume.getText());
			if((val < 0.1)||(val>100.0))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le volume doit etre compris entre 0.1 et 100.0");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le volume est un nombre compris entre 0.1 et 100.0");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textPauseCourte.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("La pause courte doit etre comprise entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("La pause courte est un nombre comprise entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textPauseLongue.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("La pause longue doit etre comprise entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("La pause longue est un nombre comprise entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textPauseFin.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("La pause de fin doit etre comprise entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("La pause de fin est un nombre comprise entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textHauteurPalier.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("La hauteur de palier doit etre comprise entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("La hauteur de palier doit etre un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textPasSuite.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le pas suite doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le pas suite est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textMinSuite.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le pas min suite doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le pas min suite est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textMaxSuite.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le pas max suite doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le pas max suite est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textNbVariationPitch.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("La variation du pitch doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("La variation du pitch est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textCoefKMineur.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("La coefficient K mineur doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le coefficient K mineur est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textCoefKMajeur.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le coefficient K Majeur doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le coefficient K Majeur est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textCoefExclamation.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le coefficient d'exclamation doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le coefficient d'exclamation est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textCoefHauteurA.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le coefficient de hauteur A doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le coefficient de hauteur A est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textPuissanceA.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le coefficient de puissance A doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le coefficient de puissance A est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textCoefHauteurB.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le coefficient de hauteur B doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le coefficient de hauteur B est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textPuissanceB.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le coefficient de puissance B doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le coefficient de puissance B est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textCoefHauteurC.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le coefficient de hauteur C doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le coefficient de hauteur C est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textPuissanceC.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le coefficient de puissance C doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le coefficient de puissance C est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textCoefHSqrtC.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le coefficient de hauteur Sqrt C doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le coefficient de hauteur Sqrt C est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textCoefHauteurD.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le coefficient de hauteur D doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le coefficient de hauteur D est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textCoefHNmoins1D.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le coefficient de hauteur N-1 D doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le coefficient de hauteur N-1 D est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textCoefHauteurE.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le coefficient de hauteur E doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le coefficient de hauteur E est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textCoefHNmoins1E.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le coefficient de hauteur N-1 E doit etre compris entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le coefficient de hauteur N-1 E est un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textAnalyseurAmplitude.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("L'amplitude doit etre comprise entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("L'amplitude doit etre un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textAnalyseurFrequence.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("La frequence doit etre comprise entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("La frequence doit etre un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textAnalyseurTempsConsonne.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le temps consonne doit etre comprise entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le temps consonne doit etre un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textAnalyseurTempsVoyelle.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le temps voyelle doit etre comprise entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le temps voyelle doit etre un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textAnalyseurTempsLongue.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le temps longue doit etre comprise entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le temps longue doit etre un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		try {
			int val = Integer.parseInt(textAnalyseurNombreCouple.getText());
			if((val<1)||(val>1000))
			{
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur de reglage");
				m.setMessage("Le nombre de couple doit etre comprise entre 1 et 1000");
				m.open();
				return(false);
			}
		} catch (Exception ex) {
			MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
			m.setText("VOCALYSE Si-Vox - Erreur de reglage");
			m.setMessage("Le nombre de couple doit etre un nombre compris entre 1 et 1000");
			m.open();
			return(false);
		}
		return(true);
	}
	
	/**
	 * Methode qui met a jour le fichier de configuration
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return True si le travail est effectue correctement et false sinon.
	 */
	private boolean miseAJour()
	{
		if(verificationInformation())
		{
			try {
				FileWriter wr = new FileWriter(InformationSysteme.getRepertoireDonnees()+"si_vox.conf");
				wr.write("# Titre  : Fichier de configuration SI_VOX - Version 1.1\n");
				wr.write("# Auteur : Sebastien Mosser donnees initiales fournies par Helene Collavizza Jean-Paul Stromboni\n");
				wr.write("# Annee  : 2007\n");
				wr.write("#\n");
				wr.write("################################\n");
				wr.write("# Reglages des chemins d'accs #\n");
				wr.write("################################\n");
				wr.write("#\n");
				wr.write("#[flag qui indique si l application est configure correctement ou pas]\n");
				wr.write("CONFIGURE=1\n");
				wr.write("#\n");
				wr.write("#[Nom des executables]\n");
				wr.write("#\n");
				wr.write("EXE_LINUX="+textExeLinux.getText()+"\n");
				wr.write("EXE_WINDOWS="+textExeWindows.getText()+"\n");
				wr.write("EXE_MAC="+textExeMac.getText()+"\n");
				wr.write("#\n");
				wr.write("#[Chemin de l'analyseur de Wave]\n");
				wr.write("#\n");
				wr.write("ANALYSEUR_WAVE="+textAnalyseurWav.getText()+"\n");
				wr.write("#\n");
				wr.write("#[Chemin et Nom (nom sans extension) des fichiers de resultats (wav et pho)\n");
				wr.write("#\n");
				wr.write("REPERTOIRE_PHO_WAV="+textFichierPhoWav.getText()+"\n");
				wr.write("FICHIER_PHO_WAV=phrase\n");
				wr.write("#\n");
				wr.write("#[Les fichiers de rgles de prononciation]\n");
				wr.write("#\n");
				wr.write("PREPOSITIONS="+textPreposition.getText()+"\n");
				wr.write("REGLES="+textRegle.getText()+"\n");
				wr.write("EXCEPTIONS="+textException.getText()+"\n");
				wr.write("ACCRONYMES="+textAccronyme.getText()+"\n");
				wr.write("#\n");
				wr.write("#[Chemin d'accs aux images de l'interface graphique]\n");
				wr.write("#\n");
				wr.write("IMG_PATH="+textImgPath.getText()+"\n");
				wr.write("#\n");
				wr.write("#[Chemin d'acces a l aide]\n");
				wr.write("#\n");
				wr.write("AIDE_PATH=donnees/aide/\n");
				wr.write("#\n");
				wr.write("#[Encodage des fichiers lus par le logiciel]\n");
				wr.write("#\n");
				wr.write("ENCODAGE_FICHIER="+comboEncodageFichier.getText()+"\n");
				wr.write("#\n");
				wr.write("############################\n");
				wr.write("# Parametrages de la voix  #\n");
				wr.write("############################\n");
				wr.write("#\n");
				wr.write("# il y a cinq voix, tirees du projet MBROLA\n");
				wr.write("# il faut regler le pitch moyen des locuteurs, 230Hz pour Celine\n");
				wr.write("# on utilise le frequency ratio, cf. commande mbrola : mbrola fr2 -f FR (FR= frequency ratio)\n");
				wr.write("#\n");
				wr.write("#[nombre de voix telechargees]\n");
				wr.write("#\n");
				wr.write("NBVOIX=1\n");
				wr.write("#\n");
				wr.write("#[Voix 1 : Thierry pitch moyen =  100Hz]\n");
				wr.write("#\n");
				wr.write("NOM_VOIX_1="+tableVoix.getItem(0).getText(1)+"\n");
				wr.write("VOIX_1="+tableVoix.getItem(0).getText(2)+"\n");
				wr.write("FR1="+tableVoix.getItem(0).getText(3)+"\n");
				wr.write("#\n");
				wr.write("#[Voix 2 : Celine pitch 230Hz]\n");
				wr.write("#\n");
				wr.write("NOM_VOIX_2="+tableVoix.getItem(1).getText(1)+"\n");
				wr.write("VOIX_2="+tableVoix.getItem(1).getText(2)+"\n");
				wr.write("FR2="+tableVoix.getItem(1).getText(3)+"\n");
				wr.write("#\n");
				wr.write("#[Voix 3 : Vincent pitch 100Hz]\n");
				wr.write("#\n");
				wr.write("NOM_VOIX_3="+tableVoix.getItem(2).getText(1)+"\n");
				wr.write("VOIX_3="+tableVoix.getItem(2).getText(2)+"\n");
				wr.write("FR3="+tableVoix.getItem(2).getText(3)+"\n");
				wr.write("#\n");
				wr.write("#[Voix 4 : Anne-Carole pitch moyen =200 Hz]\n");
				wr.write("#\n");
				wr.write("NOM_VOIX_4="+tableVoix.getItem(3).getText(1)+"\n");
				wr.write("VOIX_4="+tableVoix.getItem(3).getText(2)+"\n");
				wr.write("FR4="+tableVoix.getItem(3).getText(3)+"\n");
				wr.write("#\n");
				wr.write("#[Voix 5 : Fabrice pitch 100Hz]\n");
				wr.write("#\n");
				wr.write("NOM_VOIX_5="+tableVoix.getItem(4).getText(1)+"\n");
				wr.write("VOIX_5="+tableVoix.getItem(4).getText(2)+"\n");
				wr.write("FR5="+tableVoix.getItem(4).getText(3)+"\n");
				wr.write("#\n");
				wr.write("#[Voix 6 : Xavier]\n");
				wr.write("#\n");
				wr.write("NOM_VOIX_6="+tableVoix.getItem(5).getText(1)+"\n");
				wr.write("VOIX_6="+tableVoix.getItem(5).getText(2)+"\n");
				wr.write("FR6="+tableVoix.getItem(5).getText(3)+"\n");
				wr.write("#\n");
				wr.write("#[Voix le soldat inconnu]\n");
				wr.write("#\n");
				wr.write("NOM_VOIX_7="+tableVoix.getItem(6).getText(1)+"\n");
				wr.write("VOIX_7="+tableVoix.getItem(6).getText(2)+"\n");
				wr.write("FR7="+tableVoix.getItem(6).getText(3)+"\n");
				wr.write("#\n");
				wr.write("#[Frequence initiale de la voix synthetisee]\n");
				wr.write("#\n");
				wr.write("FREQUENCE_INIT="+textFrequenceInit.getText()+"\n");
				wr.write("#\n");
				wr.write("#################################\n");
				wr.write("# Reglage du synthetiseur vocal #\n");
				wr.write("#################################\n");
				wr.write("#\n");
				wr.write("# Reglage de la vitesse (option -t TR, time ratio)\n");
				wr.write("# ratio de rapidite. 1 est la vitesse de base du synthetiseur,\n");
				wr.write("# 0.9 est adapte pour les DV adultes, habitues aux syntheses vocales\n");
				wr.write("# 2.0 est deux fois plus lent, 0.5 deux fois plus rapide que 1.0\n");
				wr.write("#\n");
				wr.write("RAPIDITE="+textRapidite.getText()+"\n");
				wr.write("VOLUME="+textVolume.getText()+"\n");
				wr.write("#\n");
				wr.write("# on n'exploite pas le volume ratio (à verifier): \n");
				wr.write("#   mbrola fr4 -v 2.0 machin.pho truc.wav\n");
				wr.write("# Si mbrola.exe est place dans c:/windows/system32\n");
				wr.write("#    mbrola -h donne les options possibles de la commande\n");
				wr.write("#    mais dans le fichier pho, on peut donner des commandes\n");
				wr.write("#    ;xxxx est un commentaire \n");
				wr.write("#    mais la ligne ;;T=2.0 regle le time ratio à 2, \n");
				wr.write("#    idem pour ;;F=2.3, et le frequency ratio\n");
				wr.write("#    pour ;;V=1.2, cela ne marche pas semble t'il ?\n");
				wr.write("#\n");
				wr.write("# La duree des pauses :\n");
				wr.write("# Des valeurs comme 10, 30 et 60 semblent adaptees \n");
				wr.write("# ! c'est trop court (comparaison avec Mbrolign, JP Stromboni, aout 2006)\n");
				wr.write("# Pause pour les conjonctions de coordinations\n");
				wr.write("# Placez 10 si le robot mange la fin des phrases\n");
				wr.write("#\n");
				wr.write("PAUSE_COURTE="+textPauseCourte.getText()+"\n");
				wr.write("#\n");
				wr.write("# Pause pour les conjonctions de subordination\n");
				wr.write("# Placez 30 si le robot mange la fin des phrases\n");
				wr.write("#\n");
				wr.write("PAUSE_LONGUE="+textPauseLongue.getText()+"\n");
				wr.write("#\n");
				wr.write("# Pause finale en fin de phrase\n");
				wr.write("#\n");
				wr.write("PAUSE_FIN="+textPauseFin.getText()+"\n");
				wr.write("#\n");
				wr.write("# Reglages avances\n");
				wr.write("# Ecarts entre les paliers de prosodie\n");
				wr.write("# Ce parametre modifie de maniere grossiere l'intonation prise par le robot.\n");
				wr.write("# Si vous augmentez ce parametre, la voix devient chantante.\n");
				wr.write("# Au contraire, plus vous le descendez, plus la voix devient monocorde et morne\n");
				wr.write("# La valeur '1' est interessante, et rappellera\n");
				wr.write("# certains cours d'amphis tres monotones !\n");
				wr.write("# Une valeur adapte est comprise entre 20 et 30.\n");
				wr.write("#\n");
				wr.write("HAUTEUR_PALIER="+textHauteurPalier.getText()+"\n");
				wr.write("#\n");
				wr.write("# Exemples de reglages : \n");
				wr.write("# Mode Dark Vador : Frequence  40  Hz, Hauteur de palier 5  unites\n");
				wr.write("# Mode pre pubere : Frequence  140 Hz, Hauteur de palier 50 unites\n");
				wr.write("#\n");
				wr.write("# Reglage de la prosodie\n");
				wr.write("#\n");
				wr.write("# Pas d'avancement\n");
				wr.write("# ----------------\n");
				wr.write("#\n");
				wr.write("PAS_SUITE="+textPasSuite.getText()+"\n");
				wr.write("MIN_SUITE="+textMinSuite.getText()+"\n");
				wr.write("MAX_SUITE="+textMinSuite.getText()+"\n");
				wr.write("#\n");
				wr.write("# Nombre de variations de pitch maximales pour un Phoneme\n");
				wr.write("# -------------------------------------------------------\n");
				wr.write("#\n");
				wr.write("NB_VARIATIONS_PITCH="+textNbVariationPitch.getText()+"\n");
				wr.write("#\n");
				wr.write("# Reglages des courbes de prosodies\n");
				wr.write("# ---------------------------------\n");
				wr.write("#\n");
				wr.write("# Coefficient K (courbes A et B)\n");
				wr.write("# -----------------------------\n");
				wr.write("#\n");
				wr.write("COEFF_K_MINEUR="+textCoefKMineur.getText()+"\n");
				wr.write("COEFF_K_MAJEUR="+textCoefKMajeur.getText()+"\n");
				wr.write("#\n");
				wr.write("# Variation pour la fin d'une exclamation \n");
				wr.write("# ---------------------------------------\n");
				wr.write("#\n");
				wr.write("COEFF_EXCLAMATION="+textCoefExclamation.getText()+"\n");
				wr.write("#\n");
				wr.write("# Reglages fins de chacune des courbes\n");
				wr.write("# ------------------------------------\n");
				wr.write("#\n");
				wr.write("# Courbe A : Debut de phrase\n");
				wr.write("# --------------------------\n");
				wr.write("#\n");
				wr.write("COEFF_HAUTEUR_A="+textCoefHauteurA.getText()+"\n");
				wr.write("PUISSANCE_A="+textPuissanceA.getText()+"\n");
				wr.write("#\n");
				wr.write("# Courbe B : Milieu de phrase\n");
				wr.write("# ---------------------------\n");
				wr.write("#\n");
				wr.write("COEFF_HAUTEUR_B="+textCoefHauteurB.getText()+"\n");
				wr.write("PUISSANCE_B="+textPuissanceB.getText()+"\n");
				wr.write("#\n");
				wr.write("# Courbe C : Question\n");
				wr.write("# -------------------\n");
				wr.write("#\n");
				wr.write("PUISSANCE_C="+textPuissanceC.getText()+"\n");
				wr.write("COEFF_HAUTEUR_C="+textCoefHauteurC.getText()+"\n");
				wr.write("COEFF_H_SQRT_C="+textCoefHSqrtC.getText()+"\n");
				wr.write("#\n");
				wr.write("# Courbe D : Fin de phrase\n");
				wr.write("# ------------------------\n");
				wr.write("#\n");
				wr.write("COEFF_HAUTEUR_D="+textCoefHauteurD.getText()+"\n");
				wr.write("COEFF_H_N-1_D="+textCoefHNmoins1D.getText()+"\n");
				wr.write("#\n");
				wr.write("# Courbe E : Exclamation\n");
				wr.write("# ----------------------\n");
				wr.write("#\n");
				wr.write("COEFF_HAUTEUR_E="+textCoefHauteurE.getText()+"\n");
				wr.write("COEFF_H_N-1_E="+textCoefHNmoins1E.getText()+"\n");
				wr.write("#\n");
				wr.write("#\n");
				wr.write("#\n");
				wr.write("# Classe Analyser\n");
				wr.write("#\n");
				wr.write("ANALYSER_AMPLITUDE="+textAnalyseurAmplitude.getText()+"\n");
				wr.write("ANALYSER_FREQUENCE="+textAnalyseurFrequence.getText()+"\n");
				wr.write("ANALYSER_TEMPS_CONSONNE="+textAnalyseurTempsConsonne.getText()+"\n");
				wr.write("ANALYSER_TEMPS_VOYELLE="+textAnalyseurTempsVoyelle.getText()+"\n");
				wr.write("ANALYSER_TEMPS_LONGUE="+textAnalyseurTempsLongue.getText()+"\n");
				wr.write("ANALYSER_NOMBRE_COUPLES="+textAnalyseurNombreCouple.getText()+"\n");
				wr.write("#\n");
				wr.write("#\n");
				wr.flush();
				wr.close();
				return(true);
			} catch (Exception ex) {
				MessageBox m = new MessageBox(fenetrePrincipale,SWT.ICON_ERROR | SWT.OK);
				m.setText("VOCALYSE Si-Vox - Erreur");
				m.setMessage("Impossible d'enregistrer les changements !");
				m.open();
				return(false);
			}
		}
		else
		{
			return(false);
		}
	}
	
	/**
	 * Methode qui met en avant le fenetre de configuration
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void setActive()
	{
		if(fenetrePrincipale != null)
			fenetrePrincipale.setActive();
	}
}
