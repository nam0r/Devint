package t2s.ihm;

import java.util.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import t2s.chant.*;
import t2s.exception.*;

/**
 * Classe SIVOXSingerMelodie qui represente la melodie d'un chant
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class SIVOXSingerMelodie extends CTabFolder {
	
	final static int SELECTION_COLOR_R = 132;
	final static int SELECTION_COLOR_V = 217;
	final static int SELECTION_COLOR_B = 241;
	final static int SELECTION_NON_SYNCHRO_COLOR_R = 251;
	final static int SELECTION_NON_SYNCHRO_COLOR_V = 140;
	final static int SELECTION_NON_SYNCHRO_COLOR_B = 140;
	final static int SELECTION_SYNCHRO_COLOR_R = 140;
	final static int SELECTION_SYNCHRO_COLOR_V = 251;
	final static int SELECTION_SYNCHRO_COLOR_B = 187;
	
	final static int INTER_PORTEE = 80;
	final static int INTER_LIGNE = 15;
	final static int INTER_MILIEU = 140;
	final static int LARGEUR_NOTE = 60;
	final static int DEPART_INITAL = 90;
	final static int LARGEUR_IMAGE_NOTE = 22;
	final static int LARGEUR_IMAGE_ALTERATION = 7;
	
	private Chant chant = null;
	
	private boolean ctrAppuye = false;
	private boolean isMaximized = false;
	
	private InterfaceSingerGenerale i = null;
	private CTabItem melodieItem = null;
	
	private Image imageNoire = null;
	private Image imageBlanche = null;
	private Image imageRonde = null;
	private Image imageCroche = null;
	private Image imageDoubleCroche = null;
	
	private Image imageBadNote = null;
	private Image imageGoodNote = null;
	
	private Image imageSilence1Temps = null;
	private Image imageSilence2Temps = null;
	private Image imageSilence4Temps = null;
	private Image imageSilenceDemiTemps = null;
	private Image imageSilenceQuartTemps = null;
	
	private Image imageBemol = null;
	private Image imageDiese = null;
	private Image imagePointee = null;
	
	private Image imageCleDeHut = null;
	private Image imageCleDeSol = null;
	private Image imageCleDeFa = null;
	
	private int tempo = 120;
	private Vector<NoteGraphique> listeNote = null;
	private Vector<NoteGraphique> listeSelectionNote = null;
	private Vector<NoteGraphique> listeNoteNonSynchro = null;
	private Vector<NoteGraphique> listeNoteSynchro = null;
	private int positionCouranteX = DEPART_INITAL;
	
	private Composite cadre = null;
	
	private Group compoBoutton = null;
	private FormData compoBouttonData = null;
	private Label labelNote = null;
	private FormData labelNoteData = null;
	private Button bouttonDo = null;
	private FormData bouttonDoData = null;
	private Button bouttonRe = null;
	private FormData bouttonReData = null;
	private Button bouttonMi = null;
	private FormData bouttonMiData = null;
	private Button bouttonFa = null;
	private FormData bouttonFaData = null;
	private Button bouttonSol = null;
	private FormData bouttonSolData = null;
	private Button bouttonLa = null;
	private FormData bouttonLaData = null;
	private Button bouttonSi = null;
	private FormData bouttonSiData = null;
	private Label labelOctave = null;
	private FormData labelOctaveData = null;
	private Button boutton2 = null;
	private FormData boutton2Data = null;
	private Button boutton3 = null;
	private FormData boutton3Data = null;
	private Button boutton4 = null;
	private FormData boutton4Data = null;
	private Button boutton5 = null;
	private FormData boutton5Data = null;
	private Label labelAlteration = null;
	private FormData labelAlterationData = null;
	private Button bouttonBemol = null;
	private FormData bouttonBemolData = null;
	private Button bouttonDiese = null;
	private FormData bouttonDieseData = null;
	private Button checkPointee = null;
	private FormData checkPointeeData = null;
	private Label labelLongueur = null;
	private FormData labelLongueurData = null;
	private Button bouttonNoire = null;
	private FormData bouttonNoireData = null;
	private Button bouttonBlanche = null;
	private FormData bouttonBlancheData = null;
	private Button bouttonRonde = null;
	private FormData bouttonRondeData = null;
	private Button bouttonCroche = null;
	private FormData bouttonCrocheData = null;
	private Button bouttonDoubleCroche = null;
	private FormData bouttonDoubleCrocheData = null;
	private Button bouttonSilence = null;
	private FormData bouttonSilenceData = null;
	
	private Group compoAction = null;
	private FormData compoActionData = null;
	private Button bouttonAjouterNote = null;
	private FormData bouttonAjouterNoteData = null;
	private Button bouttonSupprimerNote = null;
	private FormData bouttonSupprimerNoteData = null;
	private Button bouttonInsererNote = null;
	private FormData bouttonInsererNoteData = null;
	private Button bouttonDecalerGauche = null;
	private FormData bouttonDecalerGaucheData = null;
	private Button bouttonDecalerDroite = null;
	private FormData bouttonDecalerDroiteData = null;
	private Button bouttonDecalerHaut = null;
	private FormData bouttonDecalerHautData = null;
	private Button bouttonDecalerBas = null;
	private FormData bouttonDecalerBasData = null;
	private Button bouttonTempo = null;
	private FormData bouttonTempoData = null;
	
	private Composite compoCanvas = null;
	private FormData compoCanvasData = null;
	private Canvas canvas = null;
	private ScrollBar verticalBar = null;
	private ScrollBar horizontalBar = null;
	private Point positionCanvas = null;
	private int heightMini = 0;
	private int widthMini = 0;
	private int largeurCanvas = 0;
	private int hauteurCanvas = 0;
	private FormData canvasData = null;
	private GC gc = null;
	private Group compoInfo = null;
	private FormData compoInfoData = null;
	private Label labelInfoNote = null;
	private FormData labelInfoNoteData = null;
	private Combo comboNote = null;
	private FormData comboNoteData = null;
	private Label labelInfoOctave = null;
	private FormData labelInfoOctaveData = null;
	private Combo comboOctave = null;
	private FormData comboOctaveData = null;
	private Label labelInfoAlteration = null;
	private FormData labelInfoAlterationData = null;
	private Combo comboAlteration = null;
	private FormData comboAlterationData = null;
	private Label labelInfoPointee = null;
	private FormData labelInfoPointeeData = null;
	private Combo comboPointee = null;
	private FormData comboPointeeData = null;
	private Label labelInfoLongueur = null;
	private FormData labelInfoLongueurData = null;
	private Combo comboLongueur = null;
	private FormData comboLongueurData = null;
	private Label labelInfotexte = null;
	private FormData labelInfoTexteData = null;
	private Text textInfoTexte = null;
	private FormData textInfoTexteData = null;
	private Label labelInfoTempo = null;
	private FormData labelInfoTempoData = null;
	private Text textInfoTempo = null;
	private FormData textInfoTempoData = null;
	
	/**
	 * Constructeur par defaut de SIVOXSingerMelodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public SIVOXSingerMelodie(Composite arg0, int arg1, InterfaceSingerGenerale i1)
	{
		super(arg0, arg1);
		super.setSimple(false);
		super.setMaximizeVisible(true);
		
		//affectation de l interface singer generale
		i = i1;
		
		listeNote = new Vector<NoteGraphique>();
		listeSelectionNote = new Vector<NoteGraphique>();
		listeNoteNonSynchro = new Vector<NoteGraphique>();
		listeNoteSynchro = new Vector<NoteGraphique>();
		
		//creation du ctabItem
		melodieItem = new CTabItem(this, SWT.BORDER);
		melodieItem.setText("Melodie");
		this.setSelection(melodieItem);
		
		//creation du cadre principal
		cadre = new Composite(this, SWT.NONE);
		cadre.setLayout(new FormLayout());
		melodieItem.setControl(cadre);
		
		//le group d action de gauche
		compoAction = new Group(cadre, SWT.FLAT);
		compoAction.setText("Action");
		compoAction.setLayout(new FormLayout());
		compoActionData = new FormData();
		compoActionData.left = new FormAttachment(0, 5);
		compoActionData.top = new FormAttachment(0, 5);
		compoActionData.bottom = new FormAttachment(100, -5);
		compoAction.setLayoutData(compoActionData);
		
		bouttonAjouterNote = new Button(compoAction, SWT.PUSH);
		bouttonAjouterNote.setToolTipText("Ajouter une nouvelle note a la melodie");
		bouttonAjouterNoteData = new FormData();
		bouttonAjouterNoteData.left = new FormAttachment(0, 5);
		bouttonAjouterNoteData.top = new FormAttachment(0, 5);
		bouttonAjouterNoteData.right = new FormAttachment(100, -5);
		bouttonAjouterNote.setLayoutData(bouttonAjouterNoteData);
		
		bouttonSupprimerNote = new Button(compoAction, SWT.PUSH);
		bouttonSupprimerNote.setToolTipText("Supprimer une ou plusieurs notes de la melodie");
		bouttonSupprimerNoteData = new FormData();
		bouttonSupprimerNoteData.left = new FormAttachment(0, 5);
		bouttonSupprimerNoteData.top = new FormAttachment(bouttonAjouterNote);
		bouttonSupprimerNoteData.right = new FormAttachment(100, -5);
		bouttonSupprimerNote.setLayoutData(bouttonSupprimerNoteData);
		
		bouttonInsererNote = new Button(compoAction, SWT.PUSH);
		bouttonInsererNote.setToolTipText("Inserer une note");
		bouttonInsererNoteData = new FormData();
		bouttonInsererNoteData.left = new FormAttachment(0, 5);
		bouttonInsererNoteData.top = new FormAttachment(bouttonSupprimerNote, 10);
		bouttonInsererNoteData.right = new FormAttachment(100, -5);
		bouttonInsererNote.setLayoutData(bouttonInsererNoteData);
		
		bouttonDecalerGauche = new Button(compoAction, SWT.PUSH);
		bouttonDecalerGauche.setToolTipText("Decaler une note vers la gauche");
		bouttonDecalerGaucheData = new FormData();
		bouttonDecalerGaucheData.left = new FormAttachment(0, 5);
		bouttonDecalerGaucheData.top = new FormAttachment(bouttonInsererNote, 10);
		bouttonDecalerGaucheData.right = new FormAttachment(100, -5);
		bouttonDecalerGauche.setLayoutData(bouttonDecalerGaucheData);
		
		bouttonDecalerDroite = new Button(compoAction, SWT.PUSH);
		bouttonDecalerDroite.setToolTipText("Decaler une note vers la droite");
		bouttonDecalerDroiteData = new FormData();
		bouttonDecalerDroiteData.left = new FormAttachment(0, 5);
		bouttonDecalerDroiteData.top = new FormAttachment(bouttonDecalerGauche);
		bouttonDecalerDroiteData.right = new FormAttachment(100, -5);
		bouttonDecalerDroite.setLayoutData(bouttonDecalerDroiteData);
		
		bouttonDecalerHaut = new Button(compoAction, SWT.PUSH);
		bouttonDecalerHaut.setToolTipText("Augmenter l'octave d'une ou de plusieurs note");
		bouttonDecalerHautData = new FormData();
		bouttonDecalerHautData.left = new FormAttachment(0, 5);
		bouttonDecalerHautData.top = new FormAttachment(bouttonDecalerDroite, 10);
		bouttonDecalerHautData.right = new FormAttachment(100, -5);
		bouttonDecalerHaut.setLayoutData(bouttonDecalerHautData);
		
		bouttonDecalerBas = new Button(compoAction, SWT.PUSH);
		bouttonDecalerBas.setToolTipText("Diminuer l'octave d'une ou de plusieurs note");
		bouttonDecalerBasData = new FormData();
		bouttonDecalerBasData.left = new FormAttachment(0, 5);
		bouttonDecalerBasData.top = new FormAttachment(bouttonDecalerHaut);
		bouttonDecalerBasData.right = new FormAttachment(100, -5);
		bouttonDecalerBas.setLayoutData(bouttonDecalerBasData);
		
		bouttonTempo = new Button(compoAction, SWT.PUSH);
		bouttonTempo.setToolTipText("Modifier le tempo de la melodie");
		bouttonTempoData = new FormData();
		bouttonTempoData.left = new FormAttachment(0, 5);
		bouttonTempoData.top = new FormAttachment(bouttonDecalerBas, 10);
		bouttonTempoData.right = new FormAttachment(100, -5);
		bouttonTempo.setLayoutData(bouttonTempoData);
		
		//creation du group de boutton du haut
		compoBoutton = new Group(cadre, SWT.FLAT);
		compoBoutton.setText("Configuration des notes");
		compoBoutton.setLayout(new FormLayout());
		compoBouttonData = new FormData();
		compoBouttonData.left = new FormAttachment(compoAction, 5);
		compoBouttonData.right = new FormAttachment(100, -5);
		compoBouttonData.top = new FormAttachment(0, 5);
		compoBoutton.setLayoutData(compoBouttonData);
		
		labelNote = new Label(compoBoutton, SWT.FLAT);
		labelNote.setText(" Note ");
		labelNoteData = new FormData();
		labelNoteData.left = new FormAttachment(0, 5);
		labelNoteData.top = new FormAttachment(25);
		labelNoteData.bottom = new FormAttachment(100);
		labelNote.setLayoutData(labelNoteData);
		
		bouttonDo = new Button(compoBoutton, SWT.TOGGLE);
		bouttonDo.setText(" Do ");
		bouttonDo.setToolTipText("note Do");
		bouttonDoData = new FormData();
		bouttonDoData.left = new FormAttachment(labelNote);
		bouttonDoData.top = new FormAttachment(0, 5);
		bouttonDoData.bottom = new FormAttachment(100, -5);
		bouttonDo.setLayoutData(bouttonDoData);
		
		bouttonRe = new Button(compoBoutton, SWT.TOGGLE);
		bouttonRe.setText(" Re ");
		bouttonRe.setToolTipText("note Re");
		bouttonReData = new FormData();
		bouttonReData.left = new FormAttachment(bouttonDo);
		bouttonReData.top = new FormAttachment(0, 5);
		bouttonReData.bottom = new FormAttachment(100, -5);
		bouttonRe.setLayoutData(bouttonReData);
		
		bouttonMi = new Button(compoBoutton, SWT.TOGGLE);
		bouttonMi.setText(" Mi ");
		bouttonMi.setToolTipText("note Mi");
		bouttonMiData = new FormData();
		bouttonMiData.left = new FormAttachment(bouttonRe);
		bouttonMiData.top = new FormAttachment(0, 5);
		bouttonMiData.bottom = new FormAttachment(100, -5);
		bouttonMi.setLayoutData(bouttonMiData);
		
		bouttonFa = new Button(compoBoutton, SWT.TOGGLE);
		bouttonFa.setText(" Fa ");
		bouttonFa.setToolTipText("note Fa");
		bouttonFaData = new FormData();
		bouttonFaData.left = new FormAttachment(bouttonMi);
		bouttonFaData.top = new FormAttachment(0, 5);
		bouttonFaData.bottom = new FormAttachment(100, -5);
		bouttonFa.setLayoutData(bouttonFaData);
		
		bouttonSol = new Button(compoBoutton, SWT.TOGGLE);
		bouttonSol.setText(" Sol ");
		bouttonSol.setToolTipText("note Sol");
		bouttonSolData = new FormData();
		bouttonSolData.left = new FormAttachment(bouttonFa);
		bouttonSolData.top = new FormAttachment(0, 5);
		bouttonSolData.bottom = new FormAttachment(100, -5);
		bouttonSol.setLayoutData(bouttonSolData);
		
		bouttonLa = new Button(compoBoutton, SWT.TOGGLE);
		bouttonLa.setText(" La ");
		bouttonLa.setToolTipText("note La");
		bouttonLaData = new FormData();
		bouttonLaData.left = new FormAttachment(bouttonSol);
		bouttonLaData.top = new FormAttachment(0, 5);
		bouttonLaData.bottom = new FormAttachment(100, -5);
		bouttonLa.setLayoutData(bouttonLaData);
		
		bouttonSi = new Button(compoBoutton, SWT.TOGGLE);
		bouttonSi.setText(" Si ");
		bouttonSi.setToolTipText("note Si");
		bouttonSiData = new FormData();
		bouttonSiData.left = new FormAttachment(bouttonLa);
		bouttonSiData.top = new FormAttachment(0, 5);
		bouttonSiData.bottom = new FormAttachment(100, -5);
		bouttonSi.setLayoutData(bouttonSiData);
		
		bouttonSilence = new Button(compoBoutton, SWT.TOGGLE);
		bouttonSilence.setToolTipText("note Silence");
		bouttonSilenceData = new FormData();
		bouttonSilenceData.left = new FormAttachment(bouttonSi);
		bouttonSilenceData.top = new FormAttachment(0, 5);
		bouttonSilenceData.bottom = new FormAttachment(100, -5);
		bouttonSilence.setLayoutData(bouttonSilenceData);
		
		labelOctave = new Label(compoBoutton, SWT.FLAT);
		labelOctave.setText(" octave ");
		labelOctaveData = new FormData();
		labelOctaveData.left = new FormAttachment(bouttonSilence, 10);
		labelOctaveData.top = new FormAttachment(25);
		labelOctaveData.bottom = new FormAttachment(100);
		labelOctave.setLayoutData(labelOctaveData);
		
		boutton2 = new Button(compoBoutton, SWT.TOGGLE);
		boutton2.setText(" 2 ");
		boutton2.setToolTipText("octave 2");
		boutton2Data = new FormData();
		boutton2Data.left = new FormAttachment(labelOctave);
		boutton2Data.top = new FormAttachment(0, 5);
		boutton2Data.bottom = new FormAttachment(100, -5);
		boutton2.setLayoutData(boutton2Data);
		
		boutton3 = new Button(compoBoutton, SWT.TOGGLE);
		boutton3.setText(" 3 ");
		boutton3.setToolTipText("octave 3");
		boutton3Data = new FormData();
		boutton3Data.left = new FormAttachment(boutton2);
		boutton3Data.top = new FormAttachment(0, 5);
		boutton3Data.bottom = new FormAttachment(100, -5);
		boutton3.setLayoutData(boutton3Data);
		
		boutton4 = new Button(compoBoutton, SWT.TOGGLE);
		boutton4.setText(" 4 ");
		boutton4.setToolTipText("octave 4");
		boutton4Data = new FormData();
		boutton4Data.left = new FormAttachment(boutton3);
		boutton4Data.top = new FormAttachment(0, 5);
		boutton4Data.bottom = new FormAttachment(100, -5);
		boutton4.setLayoutData(boutton4Data);
		
		boutton5 = new Button(compoBoutton, SWT.TOGGLE);
		boutton5.setText(" 5 ");
		boutton5.setToolTipText("octave 5");
		boutton5Data = new FormData();
		boutton5Data.left = new FormAttachment(boutton4);
		boutton5Data.top = new FormAttachment(0, 5);
		boutton5Data.bottom = new FormAttachment(100, -5);
		boutton5.setLayoutData(boutton5Data);
		
		labelAlteration = new Label(compoBoutton, SWT.FLAT);
		labelAlteration.setText(" Alteration ");
		labelAlterationData = new FormData();
		labelAlterationData.left = new FormAttachment(boutton5, 10);
		labelAlterationData.top = new FormAttachment(25);
		labelAlterationData.bottom = new FormAttachment(100);
		labelAlteration.setLayoutData(labelAlterationData);
		
		bouttonBemol = new Button(compoBoutton, SWT.TOGGLE);
		bouttonBemol.setToolTipText("note en bemole");
		bouttonBemolData = new FormData();
		bouttonBemolData.left = new FormAttachment(labelAlteration);
		bouttonBemolData.top = new FormAttachment(0, 5);
		bouttonBemolData.bottom = new FormAttachment(100, -5);
		bouttonBemol.setLayoutData(bouttonBemolData);
		
		bouttonDiese = new Button(compoBoutton, SWT.TOGGLE);
		bouttonDiese.setToolTipText("note en diese");
		bouttonDieseData = new FormData();
		bouttonDieseData.left = new FormAttachment(bouttonBemol);
		bouttonDieseData.top = new FormAttachment(0, 5);
		bouttonDieseData.bottom = new FormAttachment(100, -5);
		bouttonDiese.setLayoutData(bouttonDieseData);
		
		checkPointee = new Button(compoBoutton, SWT.CHECK);
		checkPointee.setText("Pointee");
		checkPointee.setToolTipText("note pointee");
		checkPointeeData = new FormData();
		checkPointeeData.left = new FormAttachment(bouttonDiese, 10);
		checkPointeeData.top = new FormAttachment(0, 5);
		checkPointeeData.bottom = new FormAttachment(100, -5);
		checkPointee.setLayoutData(checkPointeeData);
		
		labelLongueur = new Label(compoBoutton, SWT.FLAT);
		labelLongueur.setText(" Longueur ");
		labelLongueurData = new FormData();
		labelLongueurData.left = new FormAttachment(checkPointee, 10);
		labelLongueurData.top = new FormAttachment(25);
		labelLongueurData.bottom = new FormAttachment(100);
		labelLongueur.setLayoutData(labelLongueurData);
		
		bouttonNoire = new Button(compoBoutton, SWT.TOGGLE);
		bouttonNoire.setToolTipText("Noire (1 temps)");
		bouttonNoireData = new FormData();
		bouttonNoireData.left = new FormAttachment(labelLongueur);
		bouttonNoireData.top = new FormAttachment(0, 5);
		bouttonNoireData.bottom = new FormAttachment(100, -5);
		bouttonNoire.setLayoutData(bouttonNoireData);
		
		bouttonBlanche = new Button(compoBoutton, SWT.TOGGLE);
		bouttonBlanche.setToolTipText("Blanche (2 temps)");
		bouttonBlancheData = new FormData();
		bouttonBlancheData.left = new FormAttachment(bouttonNoire);
		bouttonBlancheData.top = new FormAttachment(0, 5);
		bouttonBlancheData.bottom = new FormAttachment(100, -5);
		bouttonBlanche.setLayoutData(bouttonBlancheData);
		
		bouttonRonde = new Button(compoBoutton, SWT.TOGGLE);
		bouttonRonde.setToolTipText("Ronde (4 temps)");
		bouttonRondeData = new FormData();
		bouttonRondeData.left = new FormAttachment(bouttonBlanche);
		bouttonRondeData.top = new FormAttachment(0, 5);
		bouttonRondeData.bottom = new FormAttachment(100, -5);
		bouttonRonde.setLayoutData(bouttonRondeData);
		
		bouttonCroche = new Button(compoBoutton, SWT.TOGGLE);
		bouttonCroche.setToolTipText("Croche (1/2 temps)");
		bouttonCrocheData = new FormData();
		bouttonCrocheData.left = new FormAttachment(bouttonRonde);
		bouttonCrocheData.top = new FormAttachment(0, 5);
		bouttonCrocheData.bottom = new FormAttachment(100, -5);
		bouttonCroche.setLayoutData(bouttonCrocheData);
		
		bouttonDoubleCroche = new Button(compoBoutton, SWT.TOGGLE);
		bouttonDoubleCroche.setToolTipText("Double Croche (1/4 temps)");
		bouttonDoubleCrocheData = new FormData();
		bouttonDoubleCrocheData.left = new FormAttachment(bouttonCroche);
		bouttonDoubleCrocheData.top = new FormAttachment(0, 5);
		bouttonDoubleCrocheData.bottom = new FormAttachment(100, -5);
		bouttonDoubleCroche.setLayoutData(bouttonDoubleCrocheData);
		
		//le composite d information du bas
		compoInfo = new Group(cadre, SWT.FLAT);
		compoInfo.setText("Information");
		compoInfo.setLayout(new FormLayout());
		compoInfoData = new FormData();
		compoInfoData.left = new FormAttachment(compoAction, 5);
		compoInfoData.right = new FormAttachment(100, -5);
		compoInfoData.bottom = new FormAttachment(100, -5);
		compoInfo.setLayoutData(compoInfoData);
		
		labelInfoNote = new Label(compoInfo, SWT.FLAT);
		labelInfoNote.setText(" Note ");
		labelInfoNoteData = new FormData();
		labelInfoNoteData.left = new FormAttachment(0, 5);
		labelInfoNoteData.top = new FormAttachment(25);
		labelInfoNote.setLayoutData(labelInfoNoteData);
		
		comboNote = new Combo(compoInfo, SWT.READ_ONLY);
		comboNoteData = new FormData();
		comboNoteData.left = new FormAttachment(labelInfoNote);
		comboNoteData.top = new FormAttachment(0, 5);
		comboNoteData.bottom = new FormAttachment(100, -5);
		comboNote.setLayoutData(comboNoteData);
		comboNote.add("Do");
		comboNote.add("Re");
		comboNote.add("Mi");
		comboNote.add("Fa");
		comboNote.add("Sol");
		comboNote.add("La");
		comboNote.add("Si");
		comboNote.add("Silence");
		
		labelInfoOctave = new Label(compoInfo, SWT.FLAT);
		labelInfoOctave.setText(" Octave ");
		labelInfoOctaveData = new FormData();
		labelInfoOctaveData.left = new FormAttachment(comboNote, 5);
		labelInfoOctaveData.top = new FormAttachment(25);
		labelInfoOctaveData.bottom = new FormAttachment(100);
		labelInfoOctave.setLayoutData(labelInfoOctaveData);
		
		comboOctave = new Combo(compoInfo, SWT.READ_ONLY);
		comboOctaveData = new FormData();
		comboOctaveData.left = new FormAttachment(labelInfoOctave);
		comboOctaveData.top = new FormAttachment(0, 5);
		comboOctaveData.bottom = new FormAttachment(100, -5);
		comboOctave.setLayoutData(comboOctaveData);
		comboOctave.add("2");
		comboOctave.add("3");
		comboOctave.add("4");
		comboOctave.add("5");
		
		labelInfoAlteration = new Label(compoInfo, SWT.FLAT);
		labelInfoAlteration.setText(" Alteration ");
		labelInfoAlterationData = new FormData();
		labelInfoAlterationData.left = new FormAttachment(comboOctave, 5);
		labelInfoAlterationData.top = new FormAttachment(25);
		labelInfoAlterationData.bottom = new FormAttachment(100);
		labelInfoAlteration.setLayoutData(labelInfoAlterationData);
		
		comboAlteration = new Combo(compoInfo, SWT.READ_ONLY);
		comboAlterationData = new FormData();
		comboAlterationData.left = new FormAttachment(labelInfoAlteration);
		comboAlterationData.top = new FormAttachment(0, 5);
		comboAlterationData.bottom = new FormAttachment(100, -5);
		comboAlteration.setLayoutData(comboAlterationData);
		comboAlteration.add("Aucune");
		comboAlteration.add("Bemol");
		comboAlteration.add("Diese");
		
		labelInfoPointee = new Label(compoInfo, SWT.FLAT);
		labelInfoPointee.setText(" Pointee ");
		labelInfoPointeeData = new FormData();
		labelInfoPointeeData.left = new FormAttachment(comboAlteration, 5);
		labelInfoPointeeData.top = new FormAttachment(25);
		labelInfoPointeeData.bottom = new FormAttachment(100);
		labelInfoPointee.setLayoutData(labelInfoPointeeData);
		
		comboPointee = new Combo(compoInfo, SWT.READ_ONLY);
		comboPointeeData = new FormData();
		comboPointeeData.left = new FormAttachment(labelInfoPointee);
		comboPointeeData.top = new FormAttachment(0, 5);
		comboPointeeData.bottom = new FormAttachment(100, -5);
		comboPointee.setLayoutData(comboPointeeData);
		comboPointee.add("Normale");
		comboPointee.add("Pointee");
		
		labelInfoLongueur = new Label(compoInfo, SWT.FLAT);
		labelInfoLongueur.setText(" Longueur ");
		labelInfoLongueurData = new FormData();
		labelInfoLongueurData.left = new FormAttachment(comboPointee, 5);
		labelInfoLongueurData.top = new FormAttachment(25);
		labelInfoLongueurData.bottom = new FormAttachment(100);
		labelInfoLongueur.setLayoutData(labelInfoLongueurData);
		
		comboLongueur = new Combo(compoInfo, SWT.READ_ONLY);
		comboLongueurData = new FormData();
		comboLongueurData.left = new FormAttachment(labelInfoLongueur);
		comboLongueurData.top = new FormAttachment(0, 5);
		comboLongueurData.bottom = new FormAttachment(100, -5);
		comboLongueur.setLayoutData(comboLongueurData);
		comboLongueur.add("Noire");
		comboLongueur.add("Blanche");
		comboLongueur.add("Ronde");
		comboLongueur.add("Croche");
		comboLongueur.add("Double Croche");
		
		labelInfotexte = new Label(compoInfo, SWT.FLAT);
		labelInfotexte.setText(" Texte ");
		labelInfoTexteData = new FormData();
		labelInfoTexteData.left = new FormAttachment(comboLongueur, 5);
		labelInfoTexteData.top = new FormAttachment(25);
		labelInfoTexteData.bottom = new FormAttachment(100);
		labelInfotexte.setLayoutData(labelInfoTexteData);
		
		textInfoTexte = new Text(compoInfo, SWT.READ_ONLY | SWT.BORDER);
		textInfoTexte.setTextLimit(10);
		textInfoTexte.setText("      Aucun      ");
		textInfoTexteData = new FormData();
		textInfoTexteData.left = new FormAttachment(labelInfotexte);
		textInfoTexteData.top = new FormAttachment(0, 5);
		textInfoTexteData.bottom = new FormAttachment(100, -5);
		textInfoTexte.setLayoutData(textInfoTexteData);
		
		labelInfoTempo = new Label(compoInfo, SWT.FLAT);
		labelInfoTempo.setText(" Tempo ");
		labelInfoTempoData = new FormData();
		labelInfoTempoData.left = new FormAttachment(textInfoTexte, 5);
		labelInfoTempoData.top = new FormAttachment(25);
		labelInfoTempoData.bottom = new FormAttachment(100);
		labelInfoTempo.setLayoutData(labelInfoTempoData);
		
		textInfoTempo = new Text(compoInfo, SWT.READ_ONLY | SWT.BORDER);
		textInfoTempo.setText(String.valueOf(tempo));
		textInfoTempoData = new FormData();
		textInfoTempoData.left = new FormAttachment(labelInfoTempo);
		textInfoTempoData.top = new FormAttachment(0, 5);
		textInfoTempoData.bottom = new FormAttachment(100, -5);
		textInfoTempo.setLayoutData(textInfoTempoData);
		
		//la zone de dessin
		compoCanvas = new Composite(cadre, SWT.BORDER | SWT.HORIZONTAL | SWT.VERTICAL);
		compoCanvas.setLayout(new FormLayout());
		compoCanvasData = new FormData();
		compoCanvasData.left = new FormAttachment(compoAction, 5);
		compoCanvasData.right = new FormAttachment(100);
		compoCanvasData.top = new FormAttachment(compoBoutton, 5);
		compoCanvasData.bottom = new FormAttachment(compoInfo, -5);
		compoCanvas.setLayoutData(compoCanvasData);
		verticalBar = compoCanvas.getVerticalBar();
		horizontalBar = compoCanvas.getHorizontalBar();
		verticalBar.setVisible(false);
		horizontalBar.setVisible(false);
		verticalBar.setMinimum(0);
		verticalBar.setMaximum(100);
		horizontalBar.setMinimum(0);
		horizontalBar.setMaximum(100);
		
		canvas = new Canvas(compoCanvas, SWT.BORDER | SWT.NO_REDRAW_RESIZE);
		positionCanvas = new Point(0, 0);
		canvas.setBackground(new Color(i.getDisplay(), 255, 255, 255));
		canvas.setForeground(new Color(i.getDisplay(), 0, 0, 0));
		canvasData = new FormData();
		canvasData.left = new FormAttachment(0);
		canvasData.right = new FormAttachment(100);
		canvasData.top = new FormAttachment(0);
		canvasData.bottom = new FormAttachment(100);
		canvas.setLayoutData(canvasData);
		
		//configuration du contexte graphique
		gc = new GC(canvas, SWT.BORDER);
		gc.setBackground(canvas.getBackground());
		gc.setForeground(canvas.getForeground());
		
		//affectation des images
		try {
			melodieItem.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"melodie_16_16.png"));
			bouttonBemol.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"bemole_16_16.png"));
			bouttonDiese.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"diese_16_16.png"));
			bouttonNoire.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"noire_16_16.png"));
			bouttonBlanche.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"blanche_16_16.png"));
			bouttonRonde.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"ronde_16_16.png"));
			bouttonCroche.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"croche_16_16.png"));
			bouttonDoubleCroche.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"double_croche_16_16.png"));
			bouttonSilence.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"silence_16_16.png"));
			bouttonAjouterNote.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"ajouter_32_32.png"));
			bouttonSupprimerNote.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"supprimer_32_32.png"));
			bouttonInsererNote.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"inserer_32_32.png"));
			bouttonDecalerDroite.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"decaler_droite_32_32.png"));
			bouttonDecalerGauche.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"decaler_gauche_32_32.png"));
			bouttonDecalerHaut.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"decaler_haut_32_32.png"));
			bouttonDecalerBas.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"decaler_bas_32_32.png"));
			bouttonTempo.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage32()+"tempo_32_32.png"));
			imageNoire = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"noire.png");
			imageBlanche = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"blanche.png");
			imageRonde = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"ronde.png");
			imageCroche = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"croche.png");
			imageDoubleCroche = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"double_croche.png");
			imageDiese = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"diese.png");
			imageBemol = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"bemol.png");
			imagePointee = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"pointee.png");
			imageCleDeHut = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"cle_de_hut.png");
			imageCleDeSol = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"cle_de_sol.png");
			imageCleDeFa = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"cle_de_fa.png");
			imageSilence1Temps = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"silence1Temps.png");
			imageSilence2Temps = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"silence2Temps.png");
			imageSilence4Temps = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"silence4Temps.png");
			imageSilenceDemiTemps = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"silenceDemiTemps.png");
			imageSilenceQuartTemps = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"silenceQuartTemps.png");
			imageBadNote = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"bad_note.png");
			imageGoodNote = new Image(i.getDisplay(), InformationSysteme.getRepertoireImageNote()+"good_note.png");
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(i.getShell(), SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("les images n'ont pas pu etre charge, le programme risque de ne pas fonctionner correctement !");
	        messageBox.setText("SINGER Si-Vox - Erreur");
	        messageBox.open();
		}
		
		//evenement sur le boutton reduire ou agrandir
		this.addCTabFolder2Listener(new CTabFolder2Listener() {
			public void close(CTabFolderEvent e) {}
			public void maximize(CTabFolderEvent arg0) {
				if(isMaximized == false)
				{
					setMaximized(true);
					isMaximized = true;
					i.maximiserMelodie();
				}
			}
			public void minimize(CTabFolderEvent arg0) {}
			public void restore(CTabFolderEvent arg0) {
				if(isMaximized == true)
				{
					setMaximized(false);
					isMaximized = false;
					i.restaurerMaximisation();
				}
			}
			public void showList(CTabFolderEvent arg0) {}
		});
		
		//evenement sur le boutton de modification du tempo
		bouttonTempo.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				InputBox ipb = new InputBox(i, "tempo", String.valueOf(tempo));
				String result = ipb.open();
				try {
					int val = Integer.parseInt(result);
					if((val > 0) && (val < 1000))
					{
						tempo = val;
						textInfoTempo.setText(String.valueOf(tempo));
					}
				} catch (Exception e) {}
			}
		});
		
		//evenement sur le mouvement de la scrollbar horizontale
		horizontalBar.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event e) {
				positionCanvas.x = (int)(((double)canvas.getBounds().width - compoCanvas.getClientArea().width) * horizontalBar.getSelection() / 90.0);
				canvas.setLocation(-positionCanvas.x, -positionCanvas.y);
				//canvas.redraw();
			}
		});
		
		//evenement sur le mouvement de la scrollbar verticale
		verticalBar.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event e) {
				positionCanvas.y = (int)(((double)canvas.getBounds().height - compoCanvas.getClientArea().height) * verticalBar.getSelection() / 90.0);
				canvas.setLocation(-positionCanvas.x, -positionCanvas.y);
				//canvas.redraw();
			}
		});
		
		//evenement d appui sur le boutton do
		bouttonDo.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonDo.getSelection())
				{
					bouttonRe.setSelection(false);
					bouttonMi.setSelection(false);
					bouttonFa.setSelection(false);
					bouttonSol.setSelection(false);
					bouttonLa.setSelection(false);
					bouttonSi.setSelection(false);
					bouttonSilence.setSelection(false);
				}
				else
				{
					bouttonDo.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de la note Do");
			}
		});
		
		//evenement d'appui sur le boutton Re
		bouttonRe.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonRe.getSelection())
				{
					bouttonDo.setSelection(false);
					bouttonMi.setSelection(false);
					bouttonFa.setSelection(false);
					bouttonSol.setSelection(false);
					bouttonLa.setSelection(false);
					bouttonSi.setSelection(false);
					bouttonSilence.setSelection(false);
				}
				else
				{
					bouttonRe.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de la note Re");
			}
		});
		
		//evenement d'appui sur le boutton Mi
		bouttonMi.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonMi.getSelection())
				{
					bouttonDo.setSelection(false);
					bouttonRe.setSelection(false);
					bouttonFa.setSelection(false);
					bouttonSol.setSelection(false);
					bouttonLa.setSelection(false);
					bouttonSi.setSelection(false);
					bouttonSilence.setSelection(false);
				}
				else
				{
					bouttonMi.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de la note Mi");
			}
		});
		
		//evenement d'appui sur le boutton Fa
		bouttonFa.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonFa.getSelection())
				{
					bouttonDo.setSelection(false);
					bouttonRe.setSelection(false);
					bouttonMi.setSelection(false);
					bouttonSol.setSelection(false);
					bouttonLa.setSelection(false);
					bouttonSi.setSelection(false);
					bouttonSilence.setSelection(false);
				}
				else
				{
					bouttonFa.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de la note Fa");
			}
		});
		
		//evenement d'appui sur le boutton Sol
		bouttonSol.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonSol.getSelection())
				{
					bouttonDo.setSelection(false);
					bouttonRe.setSelection(false);
					bouttonMi.setSelection(false);
					bouttonFa.setSelection(false);
					bouttonLa.setSelection(false);
					bouttonSi.setSelection(false);
					bouttonSilence.setSelection(false);
				}
				else
				{
					bouttonSol.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de la note Sol");
			}
		});
		
		//evenement d'appui sur le boutton La
		bouttonLa.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonLa.getSelection())
				{
					bouttonDo.setSelection(false);
					bouttonRe.setSelection(false);
					bouttonMi.setSelection(false);
					bouttonFa.setSelection(false);
					bouttonSol.setSelection(false);
					bouttonSi.setSelection(false);
					bouttonSilence.setSelection(false);
				}
				else
				{
					bouttonLa.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de la note La");
			}
		});
		
		//evenement d'appui sur le boutton Si
		bouttonSi.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonSi.getSelection())
				{
					bouttonDo.setSelection(false);
					bouttonRe.setSelection(false);
					bouttonMi.setSelection(false);
					bouttonFa.setSelection(false);
					bouttonSol.setSelection(false);
					bouttonLa.setSelection(false);
					bouttonSilence.setSelection(false);
				}
				else
				{
					bouttonSi.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de la note Si");
			}
		});		
		
		//evenement d'appui sur le boutton silence
		bouttonSilence.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonSilence.getSelection())
				{
					bouttonDo.setSelection(false);
					bouttonRe.setSelection(false);
					bouttonMi.setSelection(false);
					bouttonFa.setSelection(false);
					bouttonSol.setSelection(false);
					bouttonLa.setSelection(false);
					bouttonSi.setSelection(false);
				}
				else
				{
					bouttonSilence.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de silence");
			}
		});		
		
		//evenement d'appui sur le boutton octave 2
		boutton2.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(boutton2.getSelection())
				{
					boutton3.setSelection(false);
					boutton4.setSelection(false);
					boutton5.setSelection(false);
				}
				else
				{
					boutton2.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de l'octave 2");
			}
		});
		
		//evenement d'appui sur le boutton octave 3
		boutton3.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(boutton3.getSelection())
				{
					boutton2.setSelection(false);
					boutton4.setSelection(false);
					boutton5.setSelection(false);
				}
				else
				{
					boutton3.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de l'octave 3");
			}
		});
		
		//evenement d'appui sur le boutton octave 4
		boutton4.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(boutton4.getSelection())
				{
					boutton2.setSelection(false);
					boutton3.setSelection(false);
					boutton5.setSelection(false);
				}
				else
				{
					boutton4.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de l'octave 4");
			}
		});
		
		//evenement d'appui sur le boutton octave 5
		boutton5.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(boutton5.getSelection())
				{
					boutton2.setSelection(false);
					boutton3.setSelection(false);
					boutton4.setSelection(false);
				}
				else
				{
					boutton5.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de l'octave 5");
			}
		});
		
		//evenement d'appui sur le boutton bemol
		bouttonBemol.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				bouttonDiese.setSelection(false);
				i.setIconeInformation();
				i.setInformation("Selection de l'alteration bemol");
			}
		});
		
		//evenement d'appui sur le boutton diese
		bouttonDiese.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				bouttonBemol.setSelection(false);
				i.setIconeInformation();
				i.setInformation("Selection de l'alteration diese");
			}
		});
		
		//evenement d'appui sur le boutton noire
		bouttonNoire.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonNoire.getSelection())
				{
					bouttonBlanche.setSelection(false);
					bouttonRonde.setSelection(false);
					bouttonCroche.setSelection(false);
					bouttonDoubleCroche.setSelection(false);
				}
				else
				{
					bouttonNoire.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de longueur : noire");
			}
		});
		
		//evenement d'appui sur le boutton blanche
		bouttonBlanche.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonBlanche.getSelection())
				{
					bouttonNoire.setSelection(false);
					bouttonRonde.setSelection(false);
					bouttonCroche.setSelection(false);
					bouttonDoubleCroche.setSelection(false);
				}
				else
				{
					bouttonBlanche.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de longueur : blanche");
			}
		});
		
		//evenement d'appui sur le boutton ronde
		bouttonRonde.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonRonde.getSelection())
				{
					bouttonNoire.setSelection(false);
					bouttonBlanche.setSelection(false);
					bouttonCroche.setSelection(false);
					bouttonDoubleCroche.setSelection(false);
				}
				else
				{
					bouttonRonde.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de longueur : ronde");
			}
		});
		
		//evenement d'appui sur le boutton croche
		bouttonCroche.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonCroche.getSelection())
				{
					bouttonNoire.setSelection(false);
					bouttonBlanche.setSelection(false);
					bouttonRonde.setSelection(false);
					bouttonDoubleCroche.setSelection(false);
				}
				else
				{
					bouttonCroche.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de longueur : croche");
			}
		});
		
		//evenement d'appui sur le boutton double croche
		bouttonDoubleCroche.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonDoubleCroche.getSelection())
				{
					bouttonNoire.setSelection(false);
					bouttonBlanche.setSelection(false);
					bouttonRonde.setSelection(false);
					bouttonCroche.setSelection(false);
				}
				else
				{
					bouttonDoubleCroche.setSelection(true);
				}
				i.setIconeInformation();
				i.setInformation("Selection de longueur : double croche");
			}
		});
		
		//evenement d'appui sur le boutton ajouter note
		bouttonAjouterNote.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				String note = creerNote();
				listeNoteNonSynchro.clear();
				listeNoteSynchro.clear();
				listeNote.add(new NoteGraphique(note, positionCouranteX));
				positionCouranteX = positionCouranteX + LARGEUR_NOTE;
				canvas.redraw();
				i.setIconeInformation();
				i.setInformation("Note ajoutee");
				i.setNonAJour();
			}
		});
		
		//evenement d'appui sur supprimer note
		bouttonSupprimerNote.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				listeNoteNonSynchro.clear();
				listeNoteSynchro.clear();
				if(listeSelectionNote.size() == 0)
				{
					if(listeNote.size() > 0)
					{
						listeNote.remove(listeNote.size()-1);
						canvas.redraw();
					}
				}
				else
				{
					for(Iterator<NoteGraphique> it = listeSelectionNote.iterator() ; it.hasNext() ; )
					{
						listeNote.remove(it.next());
					}
					listeSelectionNote.clear();
				}
				i.setIconeInformation();
				i.setInformation("Note supprimee");
				i.setNonAJour();
				reordonnerPositionNote();
				desactiverComboInformation();
				canvas.redraw();
			}
		});
		
		//evenement repaint du canvas
		canvas.addPaintListener(new PaintListener() {
			public void paintControl(PaintEvent arg0) {
				repaint();
			}
		});
		
		//evenement de resize
		canvas.addListener(SWT.Resize, new Listener() {
			public void handleEvent(Event arg0) {
					positionCanvas.x = 0;
					positionCanvas.y = 0;
					verticalBar.setSelection(0);
					horizontalBar.setSelection(0);
					canvas.setLocation(positionCanvas);
					canvas.redraw();
			}
		});
		
		//ecouteur de l appui sur CTRL
		canvas.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent arg0) {
				if(arg0.keyCode == 262144)
				{
					ctrAppuye = true;
				}
				else if(arg0.keyCode == 16777220)
				{
					if(listeSelectionNote.size() == 1)
					{
						NoteGraphique sel = listeSelectionNote.firstElement();
						NoteGraphique ng = null;
						boolean trouve = false;
						for(Iterator<NoteGraphique> it = listeNote.iterator() ; it.hasNext() ; )
						{
							ng = it.next();
							if(trouve == true)
							{
								listeSelectionNote.clear();
								listeSelectionNote.add(ng);
								recupererInfoNote();
								canvas.redraw();
								return;
							}
							if(ng.getDepartX() == sel.getDepartX())
							{
								trouve = true;
							}
						}
					}
				}
				else if(arg0.keyCode == 16777219)
				{
					//decalage gauche de la selection si possible
					if(listeSelectionNote.size() == 1)
					{
						NoteGraphique sel = listeSelectionNote.firstElement();
						NoteGraphique ng = null;
						NoteGraphique ng2 = null;
						for(Iterator<NoteGraphique> it = listeNote.iterator() ; it.hasNext() ; )
						{
							ng = it.next();
							if(ng.getDepartX() == sel.getDepartX())
							{
								if(ng2 != null)
								{
									listeSelectionNote.clear();
									listeSelectionNote.add(ng2);
									recupererInfoNote();
									canvas.redraw();
								}
								return;
							}
							ng2 = ng;
						}
					}
				}
			}
			public void keyReleased(KeyEvent arg0) {
				if(arg0.keyCode == 262144)
				{
					ctrAppuye = false;
				}
			}
		});
		
		//evenement de clic sur le canvas (selection)
		canvas.addMouseListener(new MouseListener() {
			public void mouseDoubleClick(MouseEvent arg0) {}
			public void mouseDown(MouseEvent arg0) {
				listeNoteNonSynchro.clear();
				listeNoteSynchro.clear();
				NoteGraphique ng = null;
				NoteGraphique ng2 = null;
				int cpt = 0;
				int index = 0;
				for(Iterator<NoteGraphique> it = listeNote.iterator() ; it.hasNext() ; )
				{
					ng = it.next();
					if((arg0.x > ng.getDepartX())&&(arg0.x <= (ng.getDepartX()+LARGEUR_NOTE)))
					{
						boolean trouve = false;
						for(Iterator<NoteGraphique> it2 = listeSelectionNote.iterator() ; it2.hasNext() ; )
						{
							ng2 = it2.next();
							if(ng2.getDepartX() == ng.getDepartX())
							{
								trouve = true;
								index = cpt;
							}
							cpt++;
						}
						if(!trouve)
						{
							if(ctrAppuye == false)
							{
								listeSelectionNote.clear();
							}
							ajouterDansSelection(ng);
						}
						else
						{
							if(ctrAppuye == false)
							{
								if(listeSelectionNote.size() > 1)
								{
									listeSelectionNote.clear();
									ajouterDansSelection(ng);
								}
								else if(listeSelectionNote.size() == 1)
								{
									listeSelectionNote.remove(index);
								}
							}
							else
							{
								listeSelectionNote.remove(index);
							}
						}
						canvas.redraw();
						//affichage des infos avec deblocage si selection = 1
						if(listeSelectionNote.size() == 1)
						{
							activerComboInformation();
							recupererInfoNote();
						}
						else
						{
							desactiverComboInformation();
						}
						return;
					}
				}
				listeSelectionNote.clear();
				desactiverComboInformation();
				canvas.redraw();
			}
			public void mouseUp(MouseEvent arg0) {}
		});
		
		//evenement de modification du combo de note
		comboNote.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				listeNoteNonSynchro.clear();
				listeNoteSynchro.clear();
				switch(comboNote.getSelectionIndex())
				{
					case 0 :
						listeSelectionNote.firstElement().setDo();
					break;
					
					case 1 :
						listeSelectionNote.firstElement().setRe();
					break;
					
					case 2 :
						listeSelectionNote.firstElement().setMi();
					break;
					
					case 3 :
						listeSelectionNote.firstElement().setFa();
					break;
					
					case 4 :
						listeSelectionNote.firstElement().setSol();
					break;
					
					case 5 :
						listeSelectionNote.firstElement().setLa();
					break;
					
					case 6 :
						listeSelectionNote.firstElement().setSi();
					break;
					
					case 7 :
						listeSelectionNote.firstElement().setSilence();
					break;
					
					default :
					break;
				}
				i.setIconeInformation();
				i.setInformation("Note modifiee");
				i.setNonAJour();
				canvas.redraw();
			}
		});
		
		//evenement de modification du combo octave
		comboOctave.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				listeNoteNonSynchro.clear();
				listeNoteSynchro.clear();
				switch(comboOctave.getSelectionIndex())
				{
					case 0 :
						listeSelectionNote.firstElement().setOctave(2);
					break;
					
					case 1 :
						listeSelectionNote.firstElement().setOctave(3);
					break;
					
					case 2 :
						listeSelectionNote.firstElement().setOctave(4);
					break;
					
					case 3 :
						listeSelectionNote.firstElement().setOctave(5);
					break;
					
					default :
					break;
				}
				i.setIconeInformation();
				i.setInformation("Octave modifie");
				i.setNonAJour();
				canvas.redraw();
			}
		});
		
		//evenement de modification du combo d alteration
		comboAlteration.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				listeNoteNonSynchro.clear();
				listeNoteSynchro.clear();
				switch(comboAlteration.getSelectionIndex())
				{
					case 0 :
						listeSelectionNote.firstElement().setNormal();
					break;
					
					case 1 :
						listeSelectionNote.firstElement().setBemol();
					break;
					
					case 2 :
						listeSelectionNote.firstElement().setDiese();
					break;
					
					default:
					break;
				}
				i.setIconeInformation();
				i.setInformation("Alteration modifiee");
				i.setNonAJour();
				canvas.redraw();
			}
		});
		
		//evenement de modification du combo pointee
		comboPointee.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				listeNoteNonSynchro.clear();
				listeNoteSynchro.clear();
				switch(comboPointee.getSelectionIndex())
				{
					case 0 :
						listeSelectionNote.firstElement().setNonPointee();
					break;
					
					case 1 :
						listeSelectionNote.firstElement().setPointee();
					break;
					
					default :
					break;
				}
				i.setIconeInformation();
				i.setInformation("Pointee modifiee");
				i.setNonAJour();
				canvas.redraw();
			}
		});
		
		//evenement de modification du combo longueur
		comboLongueur.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				listeNoteNonSynchro.clear();
				listeNoteSynchro.clear();
				switch(comboLongueur.getSelectionIndex())
				{
					case 0 :
						listeSelectionNote.firstElement().setNoire();
					break;
					
					case 1 :
						listeSelectionNote.firstElement().setBlanche();
					break;
					
					case 2 :
						listeSelectionNote.firstElement().setRonde();
					break;
					
					case 3 :
						listeSelectionNote.firstElement().setCroche();
					break;
					
					case 4 :
						listeSelectionNote.firstElement().setDoubleCroche();
					break;
					
					default :
					break;
				}
				i.setIconeInformation();
				i.setInformation("Longueur modifiee");
				i.setNonAJour();
				canvas.redraw();
			}
		});
		
		//evenement d'insertion de note
		bouttonInsererNote.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				listeNoteNonSynchro.clear();
				listeNoteSynchro.clear();
				if(listeSelectionNote.size() == 0)
				{
					i.setIconeWarning();
					i.setInformation("Vous devez selectionner une note pour en inserer une autre");
				}
				else if(listeSelectionNote.size() > 1)
				{
					i.setIconeWarning();
					i.setInformation("Vous devez selectionner une seule note pour en inserer une autre");
				}
				else
				{
					int index = 0;
					int cpt = 0;
					for(Iterator<NoteGraphique> it = listeNote.iterator() ; it.hasNext() ; )
					{
						if(it.next().getDepartX() == listeSelectionNote.firstElement().getDepartX())
						{
							index = cpt;
							break;
						}
						cpt++;
					}
					listeNote.insertElementAt(new NoteGraphique(creerNote(),0), index);
					reordonnerPositionNote();
					canvas.redraw();
					i.setIconeInformation();
					i.setInformation("Note inseree");
					i.setNonAJour();
				}
			}
		});
		
		//evenement de decalage gauche d une note
		bouttonDecalerGauche.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				listeNoteNonSynchro.clear();
				listeNoteSynchro.clear();
				if(listeSelectionNote.size() == 0)
				{
					i.setIconeWarning();
					i.setInformation("Vous devez selectionner une note pour la decaler");
				}
				else if(listeSelectionNote.size() > 1)
				{
					i.setIconeWarning();
					i.setInformation("Vous devez selectionner une seule note pour la decaler");
				}
				else
				{
					NoteGraphique ng = null;
					NoteGraphique aBouger = listeSelectionNote.firstElement();
					int index = 0;
					int cpt = 0;
					for(Iterator<NoteGraphique> it = listeNote.iterator() ; it.hasNext() ; )
					{
						ng = it.next();
						if(ng.getDepartX() == aBouger.getDepartX())
						{
							index = cpt;
							break;
						}
						cpt++;
					}
					if((index-1)>=0)
					{
						listeNote.remove(index);
						listeNote.insertElementAt(aBouger, index-1);
						reordonnerPositionNote();
						canvas.redraw();
						i.setIconeInformation();
						i.setInformation("Decalage vers la gauche");
						i.setNonAJour();
					}
					else
					{
						i.setIconeWarning();
						i.setInformation("Decalage vers la gauche impossible");
					}
				}
			}
		});
		
		//evenement de decalage droite d une note
		bouttonDecalerDroite.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				listeNoteNonSynchro.clear();
				listeNoteSynchro.clear();
				if(listeSelectionNote.size() == 0)
				{
					i.setIconeWarning();
					i.setInformation("Vous devez selectionner une note pour la decaler");
				}
				else if(listeSelectionNote.size() > 1)
				{
					i.setIconeWarning();
					i.setInformation("Vous devez selectionner une seule note pour la decaler");
				}
				else
				{
					NoteGraphique ng = null;
					NoteGraphique aBouger = listeSelectionNote.firstElement();
					int index = 0;
					int cpt = 0;
					for(Iterator<NoteGraphique> it = listeNote.iterator() ; it.hasNext() ; )
					{
						ng = it.next();
						if(ng.getDepartX() == aBouger.getDepartX())
						{
							index = cpt;
							break;
						}
						cpt++;
					}
					if(index < (listeNote.size()-1))
					{
						listeNote.remove(index);
						listeNote.insertElementAt(aBouger, index+1);
						reordonnerPositionNote();
						canvas.redraw();
						i.setIconeInformation();
						i.setInformation("Decalage vers la droite");
						i.setNonAJour();
					}
					else
					{
						i.setIconeWarning();
						i.setInformation("Decalage vers la droite impossible");
					}
				}
			}
		});
		
		//evenement de decalage d octave vers le haut de une ou plusieurs notes
		bouttonDecalerHaut.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				listeNoteNonSynchro.clear();
				listeNoteSynchro.clear();
				NoteGraphique ng = null;
				if(listeSelectionNote.size() == 0)
				{
					for(Iterator<NoteGraphique> it = listeNote.iterator() ; it.hasNext() ; )
					{
						ng = it.next();
						if(ng.getOctave() < 5)
						{
							ng.setOctave(ng.getOctave()+1);
						}
					}
				}
				else
				{
					for(Iterator<NoteGraphique> it = listeSelectionNote.iterator() ; it.hasNext() ; )
					{
						ng = it.next();
						if(ng.getOctave() < 5)
						{
							ng.setOctave(ng.getOctave()+1);
						}
					}
				}
				canvas.redraw();
				i.setIconeInformation();
				i.setInformation("Augmentation de l'octave");
				if(listeSelectionNote.size() == 1)
				{
					recupererInfoNote();
				}
				i.setNonAJour();
			}
		});
		
		//evenement de decalage d octave vers le bas de une ou plusieurs notes
		bouttonDecalerBas.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				listeNoteNonSynchro.clear();
				listeNoteSynchro.clear();
				NoteGraphique ng = null;
				if(listeSelectionNote.size() == 0)
				{
					for(Iterator<NoteGraphique> it = listeNote.iterator() ; it.hasNext() ; )
					{
						ng = it.next();
						if(ng.getOctave() > 2)
						{
							ng.setOctave(ng.getOctave()-1);
						}
					}
				}
				else
				{
					for(Iterator<NoteGraphique> it = listeSelectionNote.iterator() ; it.hasNext() ; )
					{
						ng = it.next();
						if(ng.getOctave() > 2)
						{
							ng.setOctave(ng.getOctave()-1);
						}
					}
				}
				canvas.redraw();
				i.setIconeInformation();
				i.setInformation("Diminution de l'octave");
				if(listeSelectionNote.size() == 1)
				{
					recupererInfoNote();
				}
				i.setNonAJour();
			}
		});
		
		//configuration initiale des note/alteration/pointee/longueur
		bouttonDo.setSelection(true);
		boutton2.setSelection(true);
		bouttonNoire.setSelection(true);
		desactiverComboInformation();
	}
	
	/**
	 * Methode qui recupere les informations sur la note selectionnee
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	private void recupererInfoNote()
	{
		NoteGraphique ng = null;
		ng = listeSelectionNote.firstElement();
		comboNote.select(ng.getNumeroNote());
		comboOctave.select(ng.getOctave()-2);
		comboAlteration.select(ng.getNumeroAlteration());
		comboPointee.select(ng.getNumeroPointee());
		comboLongueur.select(ng.getNumeroLongueur());
		textInfoTexte.setText(ng.getText());
	}
	
	/**
	 * Methode qui retourne le tempo de la melodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le tempo
	 */
	public int getTempo()
	{
		return(tempo);
	}
	
	/**
	 * Methode qui modifie le tempo de la melodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param val Le nouveau tempo
	 */
	public void setTempo(int val)
	{
		tempo = val;
		textInfoTempo.setText(String.valueOf(tempo));
	}
	
	/**
	 * Methode qui cre une note en fonction de la configuration
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Une chaine representant la note
	 */
	private String creerNote()
	{
		String note = "";
		if(bouttonDo.getSelection())
			note = "DO";
		else if(bouttonRe.getSelection())
			note = "RE";
		else if(bouttonMi.getSelection())
			note = "MI";
		else if(bouttonFa.getSelection())
			note = "FA";
		else if(bouttonSol.getSelection())
			note = "SO";
		else if(bouttonLa.getSelection())
			note = "LA";
		else if(bouttonSi.getSelection())
			note = "SI";
		else
			note = "SH";
		
		if(boutton2.getSelection())
			note = note + "2";
		else if(boutton3.getSelection())
			note = note + "3";
		else if(boutton4.getSelection())
			note = note + "4";
		else
			note = note + "5";
		
		if(bouttonBemol.getSelection())
			note = note + "b";
		else if(bouttonDiese.getSelection())
			note = note + "d";
		else
			note = note + "n";
		if(bouttonNoire.getSelection())
			if(checkPointee.getSelection())
				note = note + "NOP";
			else
				note = note + "NOI";
		else if(bouttonBlanche.getSelection())
			if(checkPointee.getSelection())
				note = note + "BLP";
			else
				note = note + "BLA";
		else if(bouttonRonde.getSelection())
			if(checkPointee.getSelection())
				note = note + "ROP";
			else
				note = note + "RON";
		else if(bouttonCroche.getSelection())
			if(checkPointee.getSelection())
				note = note + "CRP";
			else
				note = note + "CRO";
		else
			if(checkPointee.getSelection())
				note = note + "DCP";
			else
				note = note + "DCR";
		return(note);
	}
		
	/**
	 * Methode qui prefiltre le texte
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param texte Le texte a prefiltrer
	 * @return Le texte prefiltre
	 */
	private String preFiltrer(String texte)
	{
		texte = texte.replace("@", "");
		texte = texte.replace("\\", "");
		texte = texte.replace("(", "");
		texte = texte.replace(")", "");
		texte = texte.replace("#", "");
		texte = texte.replace("@", "");
		texte = texte.replace("°", "");
		texte = texte.replace("+", "");
		texte = texte.replace("-", "");
		texte = texte.replace("*", "");
		texte = texte.replace("ù", "");
		texte = texte.replace("%", "");
		texte = texte.replace("[", "");
		texte = texte.replace("]", "");
		return(texte);
	}
	
	/**
	 * Methode qui synchronise le texte en syllabe avec la melodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param texte Le texte a synchroniser
	 */
	public void synchroniser(String texte)
	{
		listeSelectionNote.clear();
		listeNoteNonSynchro.clear();
		listeNoteSynchro.clear();
		int nbNote = 0;
		NoteGraphique ng = null;
		if(texte.length()>0)
		{
			String [] tableauChaine = preFiltrer(texte).split("/");
			int tailleMax = tableauChaine.length;
			int cpt = 0;
			for(Iterator<NoteGraphique> it = listeNote.iterator() ; it.hasNext() ; )
			{
				ng = it.next();
				if(cpt < tailleMax)
				{
					if(!ng.isSilence())
					{
						ng.setText(tableauChaine[cpt]);
						nbNote++;
					}
					else
					{
						cpt--;
					}
					ng.setSync(true);
					listeNoteSynchro.add(ng);
				}
				else
				{
					ng.setSync(false);
					ng.setText("      Aucun      ");
					listeNoteNonSynchro.add(ng);
				}
				cpt++;
			}
			if(cpt < tailleMax)
			{
				//il reste des mot et pas assez de notes
				int k = 0;
				while(cpt <= tailleMax - 1)
				{
					listeNoteNonSynchro.add(new NoteGraphique("",positionCouranteX+(k*LARGEUR_NOTE)));
					k++;
					cpt++;
				}
			}
			if(tailleMax > nbNote)
			{
				i.setIconeWarning();
				i.setInformation("Attention : Le nombre de notes est inferieur au nombre de syllabes");
			}
			else if(nbNote > tailleMax)
			{
				i.setIconeWarning();
				i.setInformation("Attention : Le nombre de notes est supertieur au nombre de syllabes");
			}
			else
			{
				i.setIconeInformation();
				i.setInformation("Synchronisation reussie");
			}
			canvas.redraw();
		}
		else
		{
			i.setIconeWarning();
			i.setInformation("Attention : Il n'y a aucun texte a synchroniser");
		}
	}
	
	/**
	 * Methode qui chante la melodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @throws ChantException
	 * @throws NoteException
	 */
	public void jouer() throws ChantException , NoteException
	{
		Vector<NoteGraphique> listeAParcourir = null;
		chant = new Chant();
		chant.setTempo(tempo);
		NoteGraphique ng = null;
		if(listeSelectionNote.size() > 0)
		{
			listeAParcourir = listeSelectionNote;
		}
		else
		{
			listeAParcourir = listeNote;
		}
		for(Iterator<NoteGraphique> it = listeAParcourir.iterator() ; it.hasNext() ; )
		{
			ng = it.next();
			if(ng.getSync())
			{
				try {
					chant.addNote(ng.getText(), ng.getNote());
				} catch (ChantException e) {
					throw e;
				}
			}
			else
			{
				break;
			}
		}
		try {
			chant.setVoix(i.getVoix());
			chant.chanter();
		} catch (ChantException e) {
			throw e;
		} catch (NoteException r) {
			throw r;
		}
	}
	
	//methode qui stoppe
	public void stop()
	{
		if(chant != null)
		{
			chant.stopperChant();
		}
	}
	
	
	/**
	 * Methode qui desactive le combo d'information
	 * @author Ecole Polytechnique de Sophia Antipolis
	 *
	 */
	private void desactiverComboInformation()
	{
		comboNote.setEnabled(false);
		comboAlteration.setEnabled(false);
		comboLongueur.setEnabled(false);
		comboPointee.setEnabled(false);
		comboOctave.setEnabled(false);
	}
	
	/**
	 * Methode qui active le combo d'information
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	private void activerComboInformation()
	{
		comboNote.setEnabled(true);
		comboAlteration.setEnabled(true);
		comboLongueur.setEnabled(true);
		comboPointee.setEnabled(true);
		comboOctave.setEnabled(true);
	}
	
	/**
	 * Methode qui reordonne la position des notes
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	private void reordonnerPositionNote()
	{
		NoteGraphique ng = null;
		positionCouranteX = DEPART_INITAL;
		for(Iterator<NoteGraphique> it = listeNote.iterator() ; it.hasNext() ; )
		{
			ng = it.next();
			ng.setDepartX(positionCouranteX);
			positionCouranteX = positionCouranteX + LARGEUR_NOTE;
		}
	}
	
	/**
	 * Methode qui retourne la melodie sous forme de chaine
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return La melodie sous forme de chaine
	 */
	public String getMelodie()
	{
		String chaine = "";
		for(Iterator<NoteGraphique> it = listeNote.iterator() ; it.hasNext() ; )
		{
			chaine = chaine + it.next().getNote() + " ";
		}
		if(chaine.length()>=1)
		{
			return(chaine.substring(0, chaine.length()-1));
		}
		else
		{
			return("");
		}
	}
	
	/**
	 * Methode qui charge les note a partir d'une chaine
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param textMelodie La chaine des notes
	 */
	public void setMelodie(String textMelodie)
	{
		listeNote.clear();
		listeSelectionNote.clear();
		listeNoteNonSynchro.clear();
		String [] tableauNote = textMelodie.split(" ");
		for(String s : tableauNote)
		{
			listeNote.add(new NoteGraphique(s, 0));
		}
		reordonnerPositionNote();
		canvas.redraw();
	}
	
	/**
	 * Methode qui dessine les zone de selection
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	private void dessinerSelection()
	{
		NoteGraphique ng = null;
		gc.setBackground(new Color(i.getDisplay(), SELECTION_COLOR_R, SELECTION_COLOR_V, SELECTION_COLOR_B));
		for(Iterator<NoteGraphique> it = listeSelectionNote.iterator() ; it.hasNext() ; )
		{
			ng = it.next();
			gc.fillRectangle(ng.getDepartX(), 0, LARGEUR_NOTE, canvas.getClientArea().height);
		}
		gc.setBackground(canvas.getBackground());
	}
	
	/**
	 * Methode qui dessine les zone non synchro
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	private void dessinerNonSynchro()
	{
		NoteGraphique ng = null;
		for(Iterator<NoteGraphique> it = listeNoteNonSynchro.iterator() ; it.hasNext() ; )
		{
			ng = it.next();
			gc.fillRectangle(ng.getDepartX(), 0, LARGEUR_NOTE, canvas.getClientArea().height);
			gc.drawImage(imageBadNote, ng.getDepartX()+(LARGEUR_NOTE/2)-16, hauteurCanvas/2 + INTER_MILIEU + (2*INTER_LIGNE)+ 5);
		}
		gc.setBackground(canvas.getBackground());
	}
	
	/**
	 * Methode qui dessine les zones synchro
	 * @author Ecole Polytechnique de Sophia Antipolis	 
	 */
	private void dessinerSynchro()
	{
		NoteGraphique ng = null;
		for(Iterator<NoteGraphique> it = listeNoteSynchro.iterator() ; it.hasNext() ; )
		{
			ng = it.next();
			gc.fillRectangle(ng.getDepartX(), 0, LARGEUR_NOTE, canvas.getClientArea().height);
			gc.drawImage(imageGoodNote, ng.getDepartX()+(LARGEUR_NOTE/2)-16, hauteurCanvas/2 + INTER_MILIEU + (2*INTER_LIGNE)+ 5);
		}
		gc.setBackground(canvas.getBackground());
	}
	
	/**
	 * Methode qui dessine les portee
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	private void dessinerLigne()
	{
		gc.setLineWidth(3);
		gc.drawLine(5, ((hauteurCanvas/2)-INTER_MILIEU-(2*INTER_LIGNE)), 5, ((hauteurCanvas/2)+INTER_MILIEU)+(2*INTER_LIGNE));
		gc.drawLine(5, ((hauteurCanvas/2)-INTER_MILIEU)-(2*INTER_LIGNE), largeurCanvas, ((hauteurCanvas/2)-INTER_MILIEU)-(2*INTER_LIGNE));
		gc.drawLine(5, ((hauteurCanvas/2)-INTER_MILIEU)-INTER_LIGNE, largeurCanvas, ((hauteurCanvas/2)-INTER_MILIEU)-INTER_LIGNE);
		gc.drawLine(5, ((hauteurCanvas/2)-INTER_MILIEU), largeurCanvas, ((hauteurCanvas/2)-INTER_MILIEU));
		gc.drawLine(5, ((hauteurCanvas/2)-INTER_MILIEU)+INTER_LIGNE, largeurCanvas, ((hauteurCanvas/2)-INTER_MILIEU)+INTER_LIGNE);
		gc.drawLine(5, ((hauteurCanvas/2)-INTER_MILIEU)+(2*INTER_LIGNE), largeurCanvas, ((hauteurCanvas/2)-INTER_MILIEU)+(2*INTER_LIGNE));
		gc.drawLine(5, (hauteurCanvas/2)-(2*INTER_LIGNE), largeurCanvas, (hauteurCanvas/2)-(2*INTER_LIGNE));
		gc.drawLine(5, (hauteurCanvas/2)-INTER_LIGNE, largeurCanvas, (hauteurCanvas/2)-INTER_LIGNE);
		gc.drawLine(5, hauteurCanvas/2, largeurCanvas, hauteurCanvas/2);
		gc.drawLine(5, (hauteurCanvas/2)+INTER_LIGNE, largeurCanvas, (hauteurCanvas/2)+INTER_LIGNE);
		gc.drawLine(5, (hauteurCanvas/2)+(2*INTER_LIGNE), largeurCanvas, (hauteurCanvas/2)+(2*INTER_LIGNE));
		gc.drawLine(5, ((hauteurCanvas/2)+INTER_MILIEU)-(2*INTER_LIGNE), largeurCanvas, ((hauteurCanvas/2)+INTER_MILIEU)-(2*INTER_LIGNE));
		gc.drawLine(5, ((hauteurCanvas/2)+INTER_MILIEU)-INTER_LIGNE, largeurCanvas, ((hauteurCanvas/2)+INTER_MILIEU)-INTER_LIGNE);
		gc.drawLine(5, ((hauteurCanvas/2)+INTER_MILIEU), largeurCanvas, ((hauteurCanvas/2)+INTER_MILIEU));
		gc.drawLine(5, ((hauteurCanvas/2)+INTER_MILIEU)+INTER_LIGNE, largeurCanvas, ((hauteurCanvas/2)+INTER_MILIEU)+INTER_LIGNE);
		gc.drawLine(5, ((hauteurCanvas/2)+INTER_MILIEU)+(2*INTER_LIGNE), largeurCanvas, ((hauteurCanvas/2)+INTER_MILIEU)+(2*INTER_LIGNE));
		gc.setLineWidth(1);
		gc.drawImage(imageCleDeHut, 15, (hauteurCanvas/2)-INTER_MILIEU-(2*INTER_LIGNE)+10);
		gc.drawImage(imageCleDeSol, 15, (hauteurCanvas/2)-46);
		gc.drawImage(imageCleDeFa, 15, (hauteurCanvas/2)+INTER_MILIEU-(4*INTER_LIGNE)+2);
	}
	
	/**
	 * Methode qui dessine les notes
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	private void dessinerNote()
	{
		int octavePrecedent = 2;
		NoteGraphique ng = null;
		int posX = DEPART_INITAL;
		int posY;
		for(Iterator<NoteGraphique> it = listeNote.iterator() ; it.hasNext() ; )
		{
			posY = (hauteurCanvas/2)-50;
			ng = it.next();
			//gestion de la hauteur (octave)
			if(ng.getOctave() == 2)
			{
				octavePrecedent = 2;
				posY = posY + (2*INTER_LIGNE) + INTER_PORTEE + 1;
			}
			else if(ng.getOctave() == 3)
			{
				octavePrecedent = 3;
				if(ng.isDo())
				{
					posY = posY + (INTER_LIGNE/2) + 1 + (INTER_PORTEE/2) - INTER_LIGNE;
				}
				else
				{
					posY = posY + (INTER_LIGNE/2) + 1;
				}
			}
			else if(ng.getOctave() == 4)
			{
				octavePrecedent = 4;
				if(ng.isLa())
				{
					posY = posY - (2*INTER_LIGNE) - (INTER_PORTEE/2);
				}
				else if(ng.isSi())
				{
					posY = posY - INTER_LIGNE - INTER_PORTEE;
				}
				else
				{
					posY = posY - (3*INTER_LIGNE) + 1;
				}
			}
			else
			{
				octavePrecedent = 5;
				posY = posY - INTER_MILIEU - (INTER_LIGNE/2);
			}
			//gestion de la hauteur (note)
			if(ng.isDo())
			{
				posY = posY + ((2*INTER_LIGNE)+(INTER_LIGNE/2));
			}
			else if(ng.isRe())
			{
				posY = posY + (2*INTER_LIGNE);
			}
			else if(ng.isMi())
			{
				posY = posY + (INTER_LIGNE+(INTER_LIGNE/2));
			}
			else if(ng.isFa())
			{
				posY = posY + (INTER_LIGNE);
			}
			else if(ng.isSol())
			{
				posY = posY + (INTER_LIGNE/2);
			}
			else if(ng.isSi())
			{
				posY = posY - (INTER_LIGNE/2);
			}
			//dessin de la note
			if(ng.isSilence())
			{
				//recalcul de la position
				if(octavePrecedent == 2)
				{
					posY = (hauteurCanvas/2)-43 + (4*INTER_LIGNE) + INTER_PORTEE;
				}
				else if((octavePrecedent == 3) || (octavePrecedent == 4))
				{
					posY = (hauteurCanvas/2)-43;
				}
				else
				{
					posY = (hauteurCanvas/2)-43 - (4*INTER_LIGNE) - INTER_PORTEE;
				}
				if(ng.isNoire())
				{
					gc.drawImage(imageSilence1Temps, posX+(LARGEUR_NOTE/2)-(LARGEUR_IMAGE_NOTE/2), posY);
				}
				else if(ng.isBlanche())
				{
					gc.drawImage(imageSilence2Temps, posX+(LARGEUR_NOTE/2)-(LARGEUR_IMAGE_NOTE/2), posY);
				}
				else if(ng.isRonde())
				{
					gc.drawImage(imageSilence4Temps, posX+(LARGEUR_NOTE/2)-(LARGEUR_IMAGE_NOTE/2), posY);
				}
				else if(ng.isCroche())
				{
					gc.drawImage(imageSilenceDemiTemps, posX+(LARGEUR_NOTE/2)-(LARGEUR_IMAGE_NOTE/2), posY);
				}
				else if(ng.isDoubleCroche())
				{
					gc.drawImage(imageSilenceQuartTemps, posX+(LARGEUR_NOTE/2)-(LARGEUR_IMAGE_NOTE/2), posY);
				}
				
			}
			else if(ng.isNoire())
			{
				gc.drawImage(imageNoire, posX+(LARGEUR_NOTE/2)-(LARGEUR_IMAGE_NOTE/2), posY);
			}
			else if(ng.isBlanche())
			{
				gc.drawImage(imageBlanche, posX+(LARGEUR_NOTE/2)-(LARGEUR_IMAGE_NOTE/2), posY);
			}
			else if(ng.isRonde())
			{
				gc.drawImage(imageRonde, posX+(LARGEUR_NOTE/2)-(LARGEUR_IMAGE_NOTE/2), posY);
			}
			else if(ng.isCroche())
			{
				gc.drawImage(imageCroche, posX+(LARGEUR_NOTE/2)-(LARGEUR_IMAGE_NOTE/2), posY);
			}
			else if(ng.isDoubleCroche())
			{
				gc.drawImage(imageDoubleCroche, posX+(LARGEUR_NOTE/2)-(LARGEUR_IMAGE_NOTE/2), posY);
			}
			//dessin de bemol ou diese uniquement si non silence
			if(!ng.isSilence())
			{
				if(ng.isBemol())
				{
					gc.drawImage(imageBemol, posX+(LARGEUR_NOTE/2)-(LARGEUR_IMAGE_NOTE/2)-(LARGEUR_IMAGE_ALTERATION)-2, posY);
				}
				else if(ng.isDiese())
				{
					gc.drawImage(imageDiese, posX+(LARGEUR_NOTE/2)-(LARGEUR_IMAGE_NOTE/2)-(LARGEUR_IMAGE_ALTERATION)-2, posY);
				}
			}
			//dessin de pointee uniquement pour les non pause et la pause blanche
			if(ng.isPointee())
			{
				if(ng.isSilence() && ng.isBlanche())
				{
					gc.drawImage(imagePointee, posX+(LARGEUR_NOTE/2)+(LARGEUR_IMAGE_NOTE/2)+2, posY-6);
				}
				else if(!ng.isSilence())
				{
					gc.drawImage(imagePointee, posX+(LARGEUR_NOTE/2)+(LARGEUR_IMAGE_NOTE/2)+2, posY);
				}
			}
			//incrementation de la position X
			posX = posX + LARGEUR_NOTE;
		}
	}
	
	/**
	 * Methode qui redessine la melodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void repaint()
	{
		//calcul de width
		widthMini = DEPART_INITAL + (listeNote.size() * LARGEUR_NOTE);
		//calcul de hauteur
		heightMini = 440;
		//test
		if(compoCanvas.getClientArea().width > widthMini)
		{
			if(compoCanvas.getClientArea().height > heightMini)
			{
				horizontalBar.setVisible(false);
				verticalBar.setVisible(false);
				canvas.setSize(compoCanvas.getClientArea().width, compoCanvas.getClientArea().height);
			}
			else
			{
				horizontalBar.setVisible(false);
				verticalBar.setVisible(true);
				canvas.setSize(compoCanvas.getClientArea().width, heightMini);
			}
		}
		else
		{
			if(compoCanvas.getClientArea().height > heightMini)
			{
				horizontalBar.setVisible(true);
				verticalBar.setVisible(false);
				canvas.setSize(widthMini, compoCanvas.getClientArea().height);
			}
			else
			{
				horizontalBar.setVisible(true);
				verticalBar.setVisible(true);
				canvas.setSize(widthMini, heightMini);

			}
		}
		largeurCanvas = canvas.getBounds().width;
		hauteurCanvas = canvas.getBounds().height;
		dessinerSynchro();
		dessinerNonSynchro();
		dessinerSelection();
		dessinerLigne();
		dessinerNote();
	}
	
	/**
	 * Methode qui ajoute une note dans la liste des selections
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param ngi La note a ajouter
	 */
	private void ajouterDansSelection(NoteGraphique ngi)
	{
		NoteGraphique ng = null;
		int index = 0;
		for(Iterator<NoteGraphique> it = listeSelectionNote.iterator() ; it.hasNext() ; )
		{
			ng = it.next();
			if(ngi.getDepartX() < ng.getDepartX())
			{
				break;
			}
			index++;
		}
		listeSelectionNote.insertElementAt(ngi, index);
	}
	
}
