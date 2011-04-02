package t2s.ihm;

import java.util.*;
import org.eclipse.swt.*;
import org.eclipse.swt.custom.*;
import org.eclipse.swt.events.*;
import org.eclipse.swt.graphics.*;
import org.eclipse.swt.layout.*;
import org.eclipse.swt.widgets.*;
import t2s.prosodie.*;

/**
 * Classe SIVOXGraphe qui represente une prosodie sous forme de graphique
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */
public class SIVOXGraphe extends CTabFolder {

	private static int EPAISSEUR_LIGNE_1 = 1;
	private static int EPAISSEUR_LIGNE_2 = 2;
	private static int BORDURE_GAUCHE = 35;
	private static int BORDURE_BAS = 30;
	private static int BORDURE_DROITE = 20;
	private static int BORDURE_HAUT = 20;
	private static int BORDURE_DRAWABLE_GAUCHE = 35;
	private static int BORDURE_DRAWABLE_HAUT = 25;
	private static int MARGE_TRIANGLE = 5;
	private static int NORD = 0;
	private static int OUEST = 1;
	private static int SUD = 2;
	private static int EST = 3;
	private static int RAYON_POINT = 4;
	private static int VALEUR_INCREMENT_DECREMENT_LONGUEUR = 5;
	private static int VALEUR_INCREMENT_DECREMENT_FREQUENCE = 5;
	private static int LONGUEUR_PHONEME_MINIMUM = 5;
	private static int FREQUENCE_MAX = 999;
	private static int SELECTION_COLOR_R = 132;
	private static int SELECTION_COLOR_V = 217;
	private static int SELECTION_COLOR_B = 241;
	private static int LIGNE_COLOR_R = 255;
	private static int LIGNE_COLOR_V = 0;
	private static int LIGNE_COLOR_B = 0;
	private static int SEPARATEUR_COLOR_R = 0;
	private static int SEPARATEUR_COLOR_V = 0;
	private static int SEPARATEUR_COLOR_B = 255;
	private static int SEPARATEUR_MOUVEMENT_COLOR_R = 255;
	private static int SEPARATEUR_MOUVEMENT_COLOR_V = 0;
	private static int SEPARATEUR_MOUVEMENT_COLOR_B = 0;
	
	enum action { SOURIS , AJOUTER_POINT , SUPPRIMER_POINT};
	private action actionGraphe = action.SOURIS;
	
	private boolean ctrlAppuye = false;
	
	private Button bouttonAugmenterLongueur = null;
	private FormData bouttonAugmenterLongueurData = null;
	private Button bouttonDiminuerLongueur = null;
	private FormData bouttonDiminuerLongueurData = null;
	private Spinner spinValeurIncrementDecrementLongueur = null;
	private FormData spinValeurIncrementDecrementLongueurData = null;
	private Button bouttonAugmenterFrequence = null;
	private FormData bouttonAugmenterFrequenceData = null;
	private Button bouttonDiminuerFrequence = null;
	private FormData bouttonDiminuerFrequenceData = null;
	private Spinner spinValeurIncrementDecrementFrequence = null;
	private FormData spinValeurIncrementDecrementFrequenceData = null;
	private Button checkAffichageCompletLargeur = null;
	private FormData checkAffichageCompletLargeurData = null;
	private Button checkAffichageCompletHauteur = null;
	private FormData checkAffichageCompletHauteurData = null;
	private Combo comboLargeur = null;
	private FormData comboLargeurData = null;
	private Combo comboHauteur = null;
	private FormData comboHauteurData = null;
	private Button bouttonSouris = null;
	private FormData bouttonSourisData = null;
	private Button bouttonAjouterPoint = null;
	private FormData bouttonAjouterPointData = null;
	private Button bouttonSupprimerPoint = null;
	private FormData bouttonSupprimerPointData = null;
	
	private CTabItem grapheItem = null;
	private Composite compoGeneral = null;
	private FormData compoGeneralData = null;
	private Composite compoBoutton = null;
	private FormData compoBouttonData = null;
	private Composite compoCanvas = null;
	private FormData compoCanvasData = null;
	private Canvas canvas = null;
	private FormData canvasData = null;
	private ScrollBar verticalBar = null;
	private ScrollBar horizontalBar = null;
	private Point positionCanvas = null;
	
	private GC gc = null;
	private int largeurMaximum = -1;
	private int hauteurMaximum = -1;
	private int largeur = 0;
	private int hauteur = 0;
	private int largeurDispo = 0;
	private int hauteurDispo = 0;
	
	private int longueurTotalPhoneme = 0;
	private int frequenceMaxPhoneme = 0;
	private Vector<PhonemeGraphique> listeSelection = null;
	private Vector<PhonemeGraphique> listePhonemeGraphique = null;
	private HashMap<Integer, String> listeComFreqRap = null;
	
	private CoupleProsodieGraphique pointABouger = null;
	private int longueurPointABouger = 0;
	private PhonemeGraphique ligneABouger = null;
	private int ligneX = -1;
	private boolean pABouger = false;
	private boolean lABouger = false;
	private boolean ligneADessiner = false;
	private boolean isTextHelped = false;
	
	private boolean isMaximized = false;
	
	private InterfaceGenerale i = null;
	
	public SIVOXGraphe(InterfaceGenerale i1, Composite arg0, int arg1)
	{
		//configuration du CTabFolder
		super(arg0, arg1);
		this.setSimple(false);
		this.setMaximizeVisible(true);
		
		// affectation de l interface generale
		i = i1;
		
		// creation du grapheItem
		grapheItem = new CTabItem(this, SWT.BORDER);
		grapheItem.setText("Graphe de prosodie");
		this.setSelection(grapheItem);
		
		//creation du composite general
		compoGeneral = new Composite(this, SWT.BORDER);
		compoGeneral.setLayout(new FormLayout());
		compoGeneralData = new FormData();
		compoGeneralData.left = new FormAttachment(0);
		compoGeneralData.right = new FormAttachment(100);
		compoGeneralData.top = new FormAttachment(0);
		compoGeneralData.bottom = new FormAttachment(100);
		compoGeneral.setLayoutData(compoGeneralData);
		grapheItem.setControl(compoGeneral);
		
		//creation du composite contenant l ensemble des bouttons
		compoBoutton = new Composite(compoGeneral, SWT.NONE);
		compoBoutton.setLayout(new FormLayout());
		compoBouttonData = new FormData();
		compoBouttonData.top = new FormAttachment(0);
		compoBouttonData.left = new FormAttachment(0);
		compoBouttonData.right = new FormAttachment(100);
		compoBoutton.setLayoutData(compoBouttonData);
		
		//creation du composite contenant le canvas
		compoCanvas = new Composite(compoGeneral, SWT.VERTICAL | SWT.HORIZONTAL);
		compoCanvas.setLayout(new FormLayout());
		compoCanvasData = new FormData();
		compoCanvasData.left = new FormAttachment(0);
		compoCanvasData.right = new FormAttachment(100);
		compoCanvasData.top = new FormAttachment(compoBoutton);
		compoCanvasData.bottom = new FormAttachment(100);
		compoCanvas.setLayoutData(compoCanvasData);
		verticalBar = compoCanvas.getVerticalBar();
		horizontalBar = compoCanvas.getHorizontalBar();
		verticalBar.setVisible(false);
		horizontalBar.setVisible(false);
		positionCanvas = new Point(0, 0);
		
		//configuration des bouttons
		bouttonAugmenterLongueur = new Button(compoBoutton, SWT.PUSH);
		bouttonAugmenterLongueur.setToolTipText("Augmente la longueur de tous les phonemes");
		bouttonAugmenterLongueurData = new FormData();
		bouttonAugmenterLongueurData.left = new FormAttachment(0);
		bouttonAugmenterLongueurData.top = new FormAttachment(0);
		bouttonAugmenterLongueur.setLayoutData(bouttonAugmenterLongueurData);
		bouttonDiminuerLongueur = new Button(compoBoutton, SWT.PUSH);
		bouttonDiminuerLongueur.setToolTipText("Diminue la longueur de tous les phonemes");
		bouttonDiminuerLongueurData = new FormData();
		bouttonDiminuerLongueurData.left = new FormAttachment(bouttonAugmenterLongueur);
		bouttonDiminuerLongueurData.top = new FormAttachment(0);
		bouttonDiminuerLongueur.setLayoutData(bouttonDiminuerLongueurData);
		spinValeurIncrementDecrementLongueur = new Spinner(compoBoutton, SWT.PUSH | SWT.BORDER);
		spinValeurIncrementDecrementLongueur.setMinimum(1);
		spinValeurIncrementDecrementLongueur.setSelection(VALEUR_INCREMENT_DECREMENT_LONGUEUR);
		spinValeurIncrementDecrementLongueur.setMaximum(500);
		spinValeurIncrementDecrementLongueur.setToolTipText("Valeur d'incrementation/decrementation de longueur");
		spinValeurIncrementDecrementLongueurData = new FormData();
		spinValeurIncrementDecrementLongueurData.left = new FormAttachment(bouttonDiminuerLongueur);
		spinValeurIncrementDecrementLongueurData.top = new FormAttachment(0);
		spinValeurIncrementDecrementLongueurData.bottom = new FormAttachment(100);
		spinValeurIncrementDecrementLongueur.setLayoutData(spinValeurIncrementDecrementLongueurData);
		bouttonAugmenterFrequence = new Button(compoBoutton, SWT.PUSH);
		bouttonAugmenterFrequence.setToolTipText("Augmente la frequence de tous les phonemes");
		bouttonAugmenterFrequenceData = new FormData();
		bouttonAugmenterFrequenceData.left = new FormAttachment(spinValeurIncrementDecrementLongueur,10);
		bouttonAugmenterFrequenceData.top = new FormAttachment(0);
		bouttonAugmenterFrequence.setLayoutData(bouttonAugmenterFrequenceData);
		bouttonDiminuerFrequence = new Button(compoBoutton, SWT.PUSH);
		bouttonDiminuerFrequence.setToolTipText("Diminue la frequence de tous les phonemes");
		bouttonDiminuerFrequenceData = new FormData();
		bouttonDiminuerFrequenceData.top = new FormAttachment(0);
		bouttonDiminuerFrequenceData.left = new FormAttachment(bouttonAugmenterFrequence);
		bouttonDiminuerFrequence.setLayoutData(bouttonDiminuerFrequenceData);
		spinValeurIncrementDecrementFrequence = new Spinner(compoBoutton, SWT.PUSH | SWT.BORDER);
		spinValeurIncrementDecrementFrequence.setMinimum(1);
		spinValeurIncrementDecrementFrequence.setSelection(VALEUR_INCREMENT_DECREMENT_FREQUENCE);
		spinValeurIncrementDecrementFrequence.setMaximum(500);
		spinValeurIncrementDecrementFrequence.setToolTipText("Valeur de decrementation de frequence");
		spinValeurIncrementDecrementFrequenceData = new FormData();
		spinValeurIncrementDecrementFrequenceData.left = new FormAttachment(bouttonDiminuerFrequence);
		spinValeurIncrementDecrementFrequenceData.top = new FormAttachment(0);
		spinValeurIncrementDecrementFrequenceData.bottom = new FormAttachment(100);
		spinValeurIncrementDecrementFrequence.setLayoutData(spinValeurIncrementDecrementFrequenceData);
		checkAffichageCompletLargeur = new Button(compoBoutton, SWT.CHECK);
		checkAffichageCompletLargeur.setText("Largeur fixe");
		checkAffichageCompletLargeur.setSelection(true);
		checkAffichageCompletLargeurData = new FormData();
		checkAffichageCompletLargeurData.top = new FormAttachment(0);
		checkAffichageCompletLargeurData.left = new FormAttachment(spinValeurIncrementDecrementFrequence,10);
		checkAffichageCompletLargeurData.bottom = new FormAttachment(100);
		checkAffichageCompletLargeur.setLayoutData(checkAffichageCompletLargeurData);
		comboLargeur = new Combo(compoBoutton, SWT.PUSH | SWT.READ_ONLY);
		comboLargeurData = new FormData();
		comboLargeurData.top = new FormAttachment(0);
		comboLargeurData.left = new FormAttachment(checkAffichageCompletLargeur);
		comboLargeurData.bottom = new FormAttachment(100);
		comboLargeur.setLayoutData(comboLargeurData);
		comboLargeur.add("2000");
		comboLargeur.add("2400");
		comboLargeur.add("2800");
		comboLargeur.add("3200");
		comboLargeur.add("3600");
		comboLargeur.add("4000");
		comboLargeur.add("4400");
		comboLargeur.add("4800");
		comboLargeur.add("5200");
		comboLargeur.add("5600");
		comboLargeur.add("6000");
		comboLargeur.add("6400");
		comboLargeur.add("6800");
		comboLargeur.add("7200");
		comboLargeur.add("7600");
		comboLargeur.add("8000");
		comboLargeur.select(0);
		comboLargeur.setEnabled(false);
		comboLargeur.setToolTipText("Largeur du graphe (en pixel)");
		checkAffichageCompletHauteur = new Button(compoBoutton, SWT.CHECK);
		checkAffichageCompletHauteur.setText("Hauteur fixe");
		checkAffichageCompletHauteur.setSelection(true);
		checkAffichageCompletHauteurData = new FormData();
		checkAffichageCompletHauteurData.top = new FormAttachment(0);
		checkAffichageCompletHauteurData.left = new FormAttachment(comboLargeur,10);
		checkAffichageCompletHauteurData.bottom = new FormAttachment(100);
		checkAffichageCompletHauteur.setLayoutData(checkAffichageCompletHauteurData);
		comboHauteur = new Combo(compoBoutton, SWT.PUSH | SWT.READ_ONLY);
		comboHauteurData = new FormData();
		comboHauteurData.top = new FormAttachment(0);
		comboHauteurData.left = new FormAttachment(checkAffichageCompletHauteur);
		comboHauteurData.bottom = new FormAttachment(100);
		comboHauteur.setLayoutData(comboHauteurData);
		comboHauteur.add("600");
		comboHauteur.add("1000");
		comboHauteur.add("1400");
		comboHauteur.add("1800");
		comboHauteur.add("2200");
		comboHauteur.add("2600");
		comboHauteur.add("3000");
		comboHauteur.add("3400");
		comboHauteur.add("3800");
		comboHauteur.add("4200");
		comboHauteur.add("4600");
		comboHauteur.add("5000");
		comboHauteur.select(0);
		comboHauteur.setEnabled(false);
		comboHauteur.setToolTipText("Hauteur du graphe (en pixel)");
		bouttonSouris = new Button(compoBoutton, SWT.TOGGLE);
		bouttonSouris.setToolTipText("Selection");
		bouttonSourisData = new FormData();
		bouttonSourisData.left = new FormAttachment(comboHauteur, 10);
		bouttonSourisData.top = new FormAttachment(0);
		bouttonSourisData.bottom = new FormAttachment(100);
		bouttonSouris.setLayoutData(bouttonSourisData);
		bouttonAjouterPoint = new Button(compoBoutton, SWT.TOGGLE);
		bouttonAjouterPoint.setToolTipText("Ajouter un point sur le graphe");
		bouttonAjouterPointData = new FormData();
		bouttonAjouterPointData.left = new FormAttachment(bouttonSouris);
		bouttonAjouterPointData.top = new FormAttachment(0);
		bouttonAjouterPointData.bottom = new FormAttachment(100);
		bouttonAjouterPoint.setLayoutData(bouttonAjouterPointData);
		bouttonSupprimerPoint = new Button(compoBoutton, SWT.TOGGLE);
		bouttonSupprimerPoint.setToolTipText("Supprimer un point du graphe");
		bouttonSupprimerPointData = new FormData();
		bouttonSupprimerPointData.left = new FormAttachment(bouttonAjouterPoint);
		bouttonSupprimerPointData.top = new FormAttachment(0);
		bouttonSupprimerPointData.bottom = new FormAttachment(100);
		bouttonSupprimerPoint.setLayoutData(bouttonSupprimerPointData);
		
		//chargement des images
		try {
			grapheItem.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"logo_graphe_16_16.png"));
			bouttonAugmenterLongueur.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"augmenter_longueur_16_16.png"));
			bouttonDiminuerLongueur.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"diminuer_longueur_16_16.png"));
			bouttonAugmenterFrequence.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"augmenter_frequence_16_16.png"));
			bouttonDiminuerFrequence.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"diminuer_frequence_16_16.png"));
			bouttonSouris.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"souris_16_16.png"));
			bouttonAjouterPoint.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"ajouter_point_16_16.png"));
			bouttonSupprimerPoint.setImage(new Image(i.getDisplay(), InformationSysteme.getRepertoireImage16()+"supprimer_point_16_16.png"));
		} catch (Exception e) {
			MessageBox messageBox = new MessageBox(i.getShell(), SWT.ICON_ERROR | SWT.OK);
	        messageBox.setMessage("Le chargement des images a echoue, le programme peut ne pas fonctionner correctement !");
	        messageBox.setText("VOCALYSE Si-Vox - Erreur");
	        messageBox.open();
		}
		
		//creation du vecteur (vide) de phoneme graphique
		listePhonemeGraphique = new Vector<PhonemeGraphique>();
		listeSelection = new Vector<PhonemeGraphique>();
		
		//creation du canvas et des scrollbars (scrollbar non visible par defaut)
		canvas = new Canvas(compoCanvas, SWT.BORDER | SWT.NO_REDRAW_RESIZE);
		canvasData = new FormData();
		canvasData.left = new FormAttachment(0);
		canvasData.right = new FormAttachment(100);
		canvasData.top = new FormAttachment(bouttonAugmenterLongueur);
		canvasData.bottom = new FormAttachment(100);
		canvas.setLayoutData(canvasData);
		
		//configuration couleur du canvas
		canvas.setBackground(new Color(i.getDisplay(), 255, 255, 255));
		canvas.setForeground(new Color(i.getDisplay(), 0, 0, 0));
		
		//creation du contexte graphique
		gc = new GC(canvas, SWT.BORDER);
		gc.setBackground(canvas.getBackground());
		gc.setForeground(canvas.getForeground());
		
		//evenement d appui sur CTRL
		canvas.addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent arg0) {
				if(arg0.keyCode == 262144)
				{
					ctrlAppuye = true;
				}
			}
			public void keyReleased(KeyEvent arg0) {
				if(arg0.keyCode == 262144)
				{
					ctrlAppuye = false;
				}
			}
		});
		
		//evenement de clic sur le boutton selection/souris
		bouttonSouris.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonSouris.getSelection())
				{
					bouttonAjouterPoint.setSelection(false);
					bouttonSupprimerPoint.setSelection(false);
					actionGraphe = action.SOURIS;
					canvas.setCursor(new Cursor(i.getDisplay(), SWT.CURSOR_ARROW));
				}
				else
				{
					bouttonSouris.setSelection(true);
				}
				canvas.setFocus();
			}
		});
		
		//evenement de clic sur le boutton ajouterPoint
		bouttonAjouterPoint.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonAjouterPoint.getSelection())
				{
					bouttonSouris.setSelection(false);
					bouttonSupprimerPoint.setSelection(false);
					actionGraphe = action.AJOUTER_POINT;
					canvas.setCursor(new Cursor(i.getDisplay(), SWT.CURSOR_CROSS));
					
				}
				else
				{
					bouttonAjouterPoint.setSelection(true);
				}
			}
		});
		
		//evenement de clicsur le boutton supprimerpoint
		bouttonSupprimerPoint.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(bouttonSupprimerPoint.getSelection())
				{
					bouttonSouris.setSelection(false);
					bouttonAjouterPoint.setSelection(false);
					actionGraphe = action.SUPPRIMER_POINT;
					canvas.setCursor(new Cursor(i.getDisplay(), SWT.CURSOR_CROSS));
				}
				else
				{
					bouttonSupprimerPoint.setSelection(true);
				}
			}
		});
		
		//evenement sur la largeur max du graphe
		comboLargeur.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				largeurMaximum = Integer.parseInt(comboLargeur.getItem(comboLargeur.getSelectionIndex()));
				canvas.setSize(largeurMaximum, hauteurMaximum);
				canvas.setFocus();
				canvas.redraw();
			}
		});
		
		//evenement sur la hauteur maximum du graphe
		comboHauteur.addListener(SWT.Selection, new Listener() {
			public void handleEvent(Event arg0) {
				hauteurMaximum = Integer.parseInt(comboHauteur.getItem(comboHauteur.getSelectionIndex()));
				canvas.setSize(largeurMaximum, hauteurMaximum);
				canvas.setFocus();
				canvas.redraw();
			}
		});
		
		//evenement sur le mouvement de la scrollbar horizontale
		horizontalBar.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event e) {
				positionCanvas.x = (int)(((double)largeurMaximum - compoCanvas.getClientArea().width) * horizontalBar.getSelection() / 90.0);
				canvas.setLocation(-positionCanvas.x, -positionCanvas.y);
			}
		});
		
		//evenement sur le mouvement de la scrollbar verticale
		verticalBar.addListener (SWT.Selection, new Listener () {
			public void handleEvent (Event e) {
				positionCanvas.y = (int)(((double)hauteurMaximum - compoCanvas.getClientArea().height) * verticalBar.getSelection() / 90.0);
				canvas.setLocation(-positionCanvas.x, -positionCanvas.y);
			}
		});
		
		//evenement sur le resize du canvas
		canvas.addListener (SWT.Resize,  new Listener () {
			public void handleEvent (Event e) {
				if((largeurMaximum == -1) || checkAffichageCompletLargeur.getSelection())
				{
					largeurMaximum = compoCanvas.getClientArea().width;
				}
				if((hauteurMaximum == -1) || checkAffichageCompletHauteur.getSelection())
				{
					hauteurMaximum = compoCanvas.getClientArea().height;
				}
				canvas.setSize(largeurMaximum, hauteurMaximum);
				canvas.redraw();
				positionCanvas.x = 0;
				positionCanvas.y = 0;
				verticalBar.setSelection(0);
				horizontalBar.setSelection(0);
				canvas.setLocation(-positionCanvas.x, -positionCanvas.y);
			}
		});
		
		//methode de repaint du canvas
		canvas.addListener (SWT.Paint, new Listener () {
			public void handleEvent (Event e) {
				repaint();
			}
		});
		
		//evenement sur le clic de affichage complet Largeur
		checkAffichageCompletLargeur.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(checkAffichageCompletLargeur.getSelection() == true)
				{
					comboLargeur.setEnabled(false);
					horizontalBar.setVisible(false);
					largeurMaximum = compoCanvas.getClientArea().width;
					verticalBar.setSelection(0);
					horizontalBar.setSelection(0);
					positionCanvas.x = 0;
					positionCanvas.y = 0;
				}
				else
				{
					comboLargeur.setEnabled(true);
					horizontalBar.setVisible(true);
					horizontalBar.setSelection(0);
					largeurMaximum = Integer.parseInt(comboLargeur.getItem(comboLargeur.getSelectionIndex()));
					verticalBar.setSelection(0);
					horizontalBar.setSelection(0);
					positionCanvas.x = 0;
					positionCanvas.y = 0;
				}
				canvas.setSize(largeurMaximum, hauteurMaximum);
				canvas.setLocation(-positionCanvas.x, -positionCanvas.y);
				canvas.setFocus();
			}
		});
		
		//evenement sur le clic de affichage complet Hauteur
		checkAffichageCompletHauteur.addSelectionListener(new SelectionListener() {

			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				if(checkAffichageCompletHauteur.getSelection() == true)
				{
					comboHauteur.setEnabled(false);
					verticalBar.setVisible(false);
					hauteurMaximum = compoCanvas.getClientArea().height;
					verticalBar.setSelection(0);
					horizontalBar.setSelection(0);
					positionCanvas.x = 0;
					positionCanvas.y = 0;
				}
				else
				{
					comboHauteur.setEnabled(true);
					verticalBar.setVisible(true);
					verticalBar.setSelection(0);
					hauteurMaximum = Integer.parseInt(comboHauteur.getItem(comboHauteur.getSelectionIndex()));
					verticalBar.setSelection(0);
					horizontalBar.setSelection(0);
					positionCanvas.x = 0;
					positionCanvas.y = 0;
				}
				canvas.setSize(largeurMaximum, hauteurMaximum);
				canvas.setLocation(-positionCanvas.x, -positionCanvas.y);
				canvas.setFocus();
			}
			
		});
		
		//evenement clic sur le boutton augmenter longueur
		bouttonAugmenterLongueur.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				PhonemeGraphique p = null;
				if(listeSelection.size() > 0)
				{
					for(Iterator<PhonemeGraphique> it = listeSelection.iterator() ; it.hasNext() ; )
					{
						p = it.next();
						p.setLongueur(p.getLongueur()+spinValeurIncrementDecrementLongueur.getSelection());
					}
				}
				else
				{
					for(Iterator<PhonemeGraphique> it = listePhonemeGraphique.iterator() ; it.hasNext() ; )
					{
						p = it.next();
						p.setLongueur(p.getLongueur()+spinValeurIncrementDecrementLongueur.getSelection());
					}
				}
				recalculerLongueurPhoneme();
				miseAJourTableauProsodie();
				canvas.setFocus();
				canvas.redraw();
			}
		});
		
		//evenement clic sur le boutton diminuer longueur
		bouttonDiminuerLongueur.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				PhonemeGraphique p = null;
				if(listeSelection.size() > 0)
				{
					if(verifierDiminutionLongueur(listeSelection, spinValeurIncrementDecrementLongueur.getSelection()))
					{
						for(Iterator<PhonemeGraphique> it = listeSelection.iterator() ; it.hasNext() ; )
						{
							p = it.next();
							p.setLongueur(p.getLongueur()-spinValeurIncrementDecrementLongueur.getSelection());
						}
					}
					else
					{
						i.setIconInformationWarning();
						i.setInformation("Un des phoneme a une longueur inferieure a "+spinValeurIncrementDecrementLongueur.getSelection());
					}
				}
				else
				{
					if(verifierDiminutionLongueur(listePhonemeGraphique, spinValeurIncrementDecrementLongueur.getSelection()))
					{
						for(Iterator<PhonemeGraphique> it = listePhonemeGraphique.iterator() ; it.hasNext() ; )
						{
							p = it.next();
							p.setLongueur(p.getLongueur()-spinValeurIncrementDecrementLongueur.getSelection());
						}
					}
					else
					{
						i.setIconInformationWarning();
						i.setInformation("Un des phoneme a une longueur inferieure a "+spinValeurIncrementDecrementLongueur.getSelection());
					}
				}
				recalculerLongueurPhoneme();
				miseAJourTableauProsodie();
				canvas.setFocus();
				canvas.redraw();
			}
		});
		
		//evenement clic sur le boutton augmenter longueur
		bouttonAugmenterFrequence.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				PhonemeGraphique p = null;
				CoupleProsodieGraphique cpg = null;
				if(listeSelection.size() > 0)
				{
					if(verifierAugmentationFrequence(listeSelection, spinValeurIncrementDecrementFrequence.getSelection()))
					{
						for(Iterator<PhonemeGraphique> it = listeSelection.iterator() ; it.hasNext() ; )
						{
							p = it.next();
							for(Iterator<CoupleProsodieGraphique> itl = p.getProsodie().iterator() ; itl.hasNext() ; )
							{
								cpg = itl.next();
								cpg.setFrequence(cpg.getFrequence()+spinValeurIncrementDecrementFrequence.getSelection());
							}
						}
					}
					else
					{
						i.setIconInformationWarning();
						i.setInformation("Un des points a une frequence trop haute ");
					}
				}
				else
				{
					if(verifierAugmentationFrequence(listePhonemeGraphique, spinValeurIncrementDecrementFrequence.getSelection()))
					{
						for(Iterator<PhonemeGraphique> it = listePhonemeGraphique.iterator() ; it.hasNext() ; )
						{
							p = it.next();
							for(Iterator<CoupleProsodieGraphique> itl = p.getProsodie().iterator() ; itl.hasNext() ; )
							{
								cpg = itl.next();
								cpg.setFrequence(cpg.getFrequence()+spinValeurIncrementDecrementFrequence.getSelection());
							}
						}
					}
					else
					{
						i.setIconInformationWarning();
						i.setInformation("Un des points a une frequence trop haute ");
					}
				}
				recalculerFrequenceMax();
				miseAJourTableauProsodie();
				canvas.setFocus();
				canvas.redraw();
			}
		});
		
		//evenement clic sur le boutton diminuer longueur
		bouttonDiminuerFrequence.addSelectionListener(new SelectionListener() {
			public void widgetDefaultSelected(SelectionEvent arg0) {}
			public void widgetSelected(SelectionEvent arg0) {
				PhonemeGraphique p = null;
				CoupleProsodieGraphique cpg = null;
				if(listeSelection.size() > 0)
				{
					if(verifierDiminutionFrequence(listeSelection, spinValeurIncrementDecrementFrequence.getSelection()) == true)
					{
						for(Iterator<PhonemeGraphique> it = listeSelection.iterator() ; it.hasNext() ; )
						{
							p = it.next();
							for(Iterator<CoupleProsodieGraphique> itl = p.getProsodie().iterator() ; itl.hasNext() ; )
							{
								cpg = itl.next();
								cpg.setFrequence(cpg.getFrequence()-spinValeurIncrementDecrementFrequence.getSelection());
							}
						}
					}
					else
					{
						i.setIconInformationWarning();
						i.setInformation("Un des points a une frequence inferieure a "+spinValeurIncrementDecrementFrequence.getSelection());
					}
				}
				else
				{
					if(verifierDiminutionFrequence(listePhonemeGraphique, spinValeurIncrementDecrementFrequence.getSelection()) == true)
					{
						for(Iterator<PhonemeGraphique> it = listePhonemeGraphique.iterator() ; it.hasNext() ; )
						{
							p = it.next();
							for(Iterator<CoupleProsodieGraphique> itl = p.getProsodie().iterator() ; itl.hasNext() ; )
							{
								cpg = itl.next();
								cpg.setFrequence(cpg.getFrequence()-spinValeurIncrementDecrementFrequence.getSelection());
							}
						}
					}
					else
					{
						i.setIconInformationWarning();
						i.setInformation("Un des points a une frequence inferieure a "+spinValeurIncrementDecrementFrequence.getSelection());
					}
				}
				recalculerFrequenceMax();
				miseAJourTableauProsodie();
				canvas.setFocus();
				canvas.redraw();
			}
		});
		
		// evenement sur le boutton reduire ou agrandir
		this.addCTabFolder2Listener(new CTabFolder2Listener() {
			public void close(CTabFolderEvent arg0) {}
			public void maximize(CTabFolderEvent arg0) {
				if(isMaximized == false)
				{
					setMaximized(true);
					isMaximized = true;
					positionCanvas.x = 0;
					positionCanvas.y = 0;
					verticalBar.setSelection(0);
					horizontalBar.setSelection(0);
					i.maximiserGraphe();
					canvas.setFocus();
					canvas.redraw();
				}
			}
			public void minimize(CTabFolderEvent arg0) {}
			public void restore(CTabFolderEvent arg0) {
				if(isMaximized == true)
				{
					setMaximized(false);
					isMaximized = false;
					positionCanvas.x = 0;
					positionCanvas.y = 0;
					verticalBar.setSelection(0);
					horizontalBar.setSelection(0);
					i.demaximiserGraphe();
					canvas.setFocus();
					canvas.redraw();
				}
			}
			public void showList(CTabFolderEvent arg0) {}
		});
		
		//evenement d appui sur le boutton de la souris
		canvas.addMouseListener(new MouseListener() {
			public void mouseDoubleClick(MouseEvent arg0) {}
			public void mouseDown(MouseEvent arg0) {
				if(actionGraphe == action.SOURIS)
				{
					if((pointABouger = chercherPointABouger(arg0.x, arg0.y)) != null)
					{
						//on bouge un point
						pABouger = true;
						lABouger = false;
						canvas.setCursor(new Cursor(i.getDisplay(), SWT.CURSOR_ARROW));
					}
					else if((ligneABouger = chercherLigneABouger(arg0.x)) != null)
					{
						//on bouge une ligne
						pABouger = false;
						lABouger = true;
					}
					else
					{
						//selection de zone
						//vidage de la selection si hors zone dessin
						if((arg0.x <= BORDURE_DRAWABLE_GAUCHE)||(arg0.x >= (BORDURE_DRAWABLE_GAUCHE+largeurDispo))||(arg0.y <= BORDURE_DRAWABLE_HAUT)||(arg0.y >= (BORDURE_DRAWABLE_HAUT+hauteurDispo)))
						{
							listeSelection.removeAllElements();
						}
						else
						{
							//on determine le phoneme concerne
							PhonemeGraphique p = chercherPhonemeGraphique(arg0.x, arg0.y);
							if(p != null)
							{
								boolean trouve = false;
								int index = 0;
								int indexFinal = 0;
								//le phoneme est trouve on teste si il est present dans la liste de selection
								for(Iterator<PhonemeGraphique> it = listeSelection.iterator() ; it.hasNext() ; )
								{
									PhonemeGraphique t = it.next();
									if(t.getDeprtX() == p.getDeprtX())
									{
										trouve = true;
										indexFinal = index;
									}
									index++;
								}
								//on eleve si trouve sinon on ajoute
								if(trouve)
								{
									if(ctrlAppuye == false)
									{
										if(listeSelection.size() > 1)
										{
											listeSelection.clear();
											ajouterListeSelection(p);
										}
										else if(listeSelection.size() == 1)
										{
											listeSelection.remove(indexFinal);
										}
									}
									else
									{
										listeSelection.remove(indexFinal);
									}
								}
								else
								{
									if(ctrlAppuye == false)
									{
										listeSelection.clear();
									}
									ajouterListeSelection(p);
								}
							}
						}
						canvas.redraw();
					}
				}
			}
			public void mouseUp(MouseEvent arg0) {
				if(actionGraphe == action.SOURIS)
				{
					if(pABouger == true)
					{
						//on bougeait un point
						miseAJourTableauProsodie();
						if((pointABouger = chercherPointABouger(arg0.x, arg0.y)) != null)
						{
							canvas.setToolTipText("");
							canvas.setToolTipText("Frequence : "+pointABouger.getFrequence()+" Hz\nLongueur : "+longueurPointABouger+" ms");
						}
						pABouger = false;
						pointABouger = null;
						recalculerFrequenceMax();
						canvas.redraw();
					}
					else if(lABouger == true)
					{
						//on bougeait une ligne
						ligneADessiner = false;
						if((arg0.x >= ligneABouger.getDeprtX())&&(arg0.x <= (BORDURE_DRAWABLE_GAUCHE+largeurDispo)))
						{
							int l = (int)(((double)(arg0.x-ligneABouger.getDeprtX()))*(double)longueurTotalPhoneme/(double)largeurDispo);
							if(l == 0)
							{
								l = LONGUEUR_PHONEME_MINIMUM;
							}
							ligneABouger.setLongueur(l);
							recalculerLongueurPhoneme();
							canvas.redraw();
							miseAJourTableauProsodie();
						}
						lABouger = false;
						ligneABouger = null;
						canvas.setCursor(new Cursor(i.getDisplay(), SWT.CURSOR_ARROW));
					}
				}
				else if(actionGraphe == action.AJOUTER_POINT)
				{
					ajouterPoint(arg0.x, arg0.y);
					canvas.redraw();
					miseAJourTableauProsodie();
				}
				else if(actionGraphe == action.SUPPRIMER_POINT)
				{
					supprimerPoint(arg0.x, arg0.y);
					canvas.redraw();
					miseAJourTableauProsodie();
				}
			}
		});
		
		//evenement sur le mouvement de souris
		canvas.addMouseMoveListener(new MouseMoveListener() {
			public void mouseMove(MouseEvent arg0) {
				if(actionGraphe == action.SOURIS)
				{
					if(pABouger == true)
					{
						//point a bouger --> mise a jour de la frequence
						if(pointABouger != null)
						{
							if((arg0.y >= BORDURE_DRAWABLE_HAUT)&&(arg0.y<=(BORDURE_DRAWABLE_HAUT+hauteurDispo)))
							{
								int f = (int)(((double)(BORDURE_DRAWABLE_HAUT+hauteurDispo-arg0.y)/(double)hauteurDispo)*(double)frequenceMaxPhoneme);
								pointABouger.setFrequence(f);
								canvas.redraw();
							}
							else if(arg0.y < BORDURE_DRAWABLE_HAUT)
							{
								pointABouger.setFrequence(pointABouger.getFrequence()+1);
								recalculerFrequenceMax();
								canvas.redraw();
							}
						}
					}
					else if(lABouger == true)
					{
						//ligne a bouger --> mise a jour de la longueur
						if(ligneABouger != null)
						{
							if((arg0.x > ligneABouger.getDeprtX())&&(arg0.x <= (BORDURE_DRAWABLE_GAUCHE+largeurDispo)))
							{
								// on affiche simplement la ligne
								ligneX = arg0.x;
								ligneADessiner = true;
								canvas.redraw();
							}
							else
							{
								canvas.redraw();
							}
						}
					}
					else if((pointABouger = chercherPointABouger(arg0.x, arg0.y)) != null)
					{
						canvas.setCursor(new Cursor(i.getDisplay(), SWT.CURSOR_ARROW));
						if(isTextHelped == false)
						{
							isTextHelped = true;
							canvas.setToolTipText("Frequence : "+pointABouger.getFrequence()+" Hz\nLongueur : "+longueurPointABouger+" ms");
							pointABouger = null;
						}
					}
					else if((arg0.y <= BORDURE_DRAWABLE_HAUT+hauteurDispo) && (arg0.y >= BORDURE_DRAWABLE_HAUT) && ((ligneABouger = chercherLigneABouger(arg0.x)) != null))
					{
						isTextHelped = false;
						canvas.setToolTipText("");
						canvas.setCursor(new Cursor(i.getDisplay(), SWT.CURSOR_SIZEWE));
						ligneABouger = null;
					}
					else
					{
						isTextHelped = false;
						canvas.setToolTipText("");
						canvas.setCursor(new Cursor(i.getDisplay(), SWT.CURSOR_ARROW));
					}
				}
			}
		});
		
		//on fixe le boutton a souris
		bouttonSouris.setSelection(true);
		bouttonAjouterPoint.setSelection(false);
		bouttonSupprimerPoint.setSelection(false);
	}
	

	/**
	 * Methode qui redessine le canvas
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void repaint()
	{
		largeur = canvas.getBounds().width;
		hauteur = canvas.getBounds().height;
		largeurDispo = largeur-120;
		hauteurDispo = hauteur-55;
		if((largeurDispo>20)&&(hauteurDispo>20))
		{
			calculPositionPoint();
			dessinerZonesSelection();
			dessinerCadre();
			dessinerPoints();
		}
		else
		{
			dessinerCadre();
		}
	}
	
	/**
	 * Methode qui redessine tout
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void redraw()
	{
		super.redraw();
		if(canvas != null)
		{
			canvas.redraw();
		}
	}
	
	/**
	 * Methode qui initialise les nouveau points
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param chainePhoneme La chaine des phonemes avec toutes les donnees
	 */
	public void initialiserPoints(String chainePhoneme)
	{
		listeSelection.clear();
		String chaineComFreqRap = new String("");
		listePhonemeGraphique = new Vector<PhonemeGraphique>();
		listeComFreqRap = new HashMap<Integer, String>();
		PhonemeGraphique pg = null;
		int f=0;
		longueurTotalPhoneme = 0;
		frequenceMaxPhoneme = 0;
		String [] tableauEnsemblePhoneme = chainePhoneme.split("\n");
		for(int i = 0 ; i < tableauEnsemblePhoneme.length ; i++)
		{
			String [] tableauPhoneme = tableauEnsemblePhoneme[i].split(" ");
			if(!tableauPhoneme[0].startsWith(";"))
			{
				try {
					pg = new PhonemeGraphique(tableauPhoneme[0],Integer.parseInt(tableauPhoneme[1]));
					longueurTotalPhoneme = longueurTotalPhoneme + Integer.parseInt(tableauPhoneme[1]);
					//ajout des couple (%,freq)
					for(int j = 2 ; j <= tableauPhoneme.length-2 ; j=j+2)
					{
						if((tableauPhoneme[j].length()>0)&&(tableauPhoneme[j+1].length()>0))
						{
							f = Integer.parseInt(tableauPhoneme[j+1]);
							pg.ajouterCoupleGraphique(Integer.parseInt(tableauPhoneme[j]), f, 0, 0);
							if(f>frequenceMaxPhoneme)
							{
								frequenceMaxPhoneme = f;
							}
						}
						else
						{
							break;
						}
					}
					listePhonemeGraphique.add(pg);
				} catch (Exception e) {}
			}
			else
			{
				chaineComFreqRap = "";
				for(int k = 0 ; k < tableauPhoneme.length ; k++)
				{
					chaineComFreqRap = chaineComFreqRap + tableauPhoneme[k] + " ";
				}
				listeComFreqRap.put(i, chaineComFreqRap);
			}
		}
		calculPositionPoint();
	}
	
	/**
	 * Methode qui recalcule la longueur totale des phonemes
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	private void recalculerLongueurPhoneme()
	{
		longueurTotalPhoneme = 0;
		PhonemeGraphique p = null;
		for(Iterator<PhonemeGraphique> it = listePhonemeGraphique.iterator() ; it.hasNext() ; )
		{
			p = it.next();
			longueurTotalPhoneme += p.getLongueur();
		}
	}
	
	/**
	 * Methode qui recalcule la frequence max des phonemes
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	private void recalculerFrequenceMax()
	{
		frequenceMaxPhoneme = 0;
		PhonemeGraphique p = null;
		CoupleProsodieGraphique cpg = null;
		for(Iterator<PhonemeGraphique> it = listePhonemeGraphique.iterator() ; it.hasNext() ; )
		{
			p = it.next();
			for(Iterator<CoupleProsodieGraphique> itl = p.getProsodie().iterator() ; itl.hasNext() ; )
			{
				cpg = itl.next();
				if(cpg.getFrequence() > frequenceMaxPhoneme)
				{
					frequenceMaxPhoneme = cpg.getFrequence(); 
				}
			}
		}	
	}
	
	/**
	 * Methode qui verifie que l'augmentation de frequence peut etre effectuee
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param liste La liste des phonemes graphique a verifier
	 * @param n La valeur d'incrementation
	 * @return True si l'operation est permise et false sinon
	 */
	private boolean verifierAugmentationFrequence(Vector<PhonemeGraphique> liste, int n)
	{
		PhonemeGraphique p = null;
		CoupleProsodieGraphique cpg = null;
		for(Iterator<PhonemeGraphique> it = liste.iterator() ; it.hasNext() ; )
		{
			p = it.next();
			for(Iterator<CoupleProsodieGraphique> itl = p.getProsodie().iterator() ; itl.hasNext() ; )
			{
				cpg = itl.next();
				if((cpg.getFrequence()+n) > FREQUENCE_MAX)
				{
					return(false);
				}
			}
		}
		return(true);
	}
	
	/**
	 * Methode qui verifie que l'on peut diminuer la frequence des phonemes
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param liste La liste des phonemes a verifier
	 * @param n La valeur de decrementation
	 * @return True si l'operation est permise et false sinon
	 */
	private boolean verifierDiminutionFrequence(Vector<PhonemeGraphique> liste, int n)
	{
		PhonemeGraphique p = null;
		CoupleProsodieGraphique cpg = null;
		for(Iterator<PhonemeGraphique> it = liste.iterator() ; it.hasNext() ; )
		{
			p = it.next();
			for(Iterator<CoupleProsodieGraphique> itl = p.getProsodie().iterator() ; itl.hasNext() ; )
			{
				cpg = itl.next();
				if((cpg.getFrequence()-n) <= 0)
				{
					return(false);
				}
			}
		}
		return(true);
	}
	
	/**
	 * Methode qui verifie que l'on peut diminuer la longueur des phonemes
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param liste La liste des phonemes a verfier
	 * @param l La valeur de decrementation
	 * @return True si l'operation est permise et false sinon
	 */
	private boolean verifierDiminutionLongueur(Vector<PhonemeGraphique> liste, int l)
	{
		PhonemeGraphique p = null;
		for(Iterator<PhonemeGraphique> it = liste.iterator() ; it.hasNext() ; )
		{
			p = it.next();
			if((p.getLongueur()-l) <= 0)
			{
				return(false);
			}
		}
		return(true);
	}
	
	/**
	 * Methode qui calcule la position de tous les points
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	private void calculPositionPoint()
	{
		largeur = canvas.getBounds().width;
		hauteur = canvas.getBounds().height;
		largeurDispo = largeur-120;
		hauteurDispo = hauteur-55;
		PhonemeGraphique p = null;
		Vector<CoupleProsodieGraphique> l = null;
		CoupleProsodieGraphique cpg = null;
		int depart_x = BORDURE_DRAWABLE_GAUCHE;
		int depart_y = BORDURE_DRAWABLE_HAUT+hauteurDispo;
		int incrementPhonemeX = 0;
		int valeurX = 0;
		for(Iterator<PhonemeGraphique> it = listePhonemeGraphique.iterator() ; it.hasNext() ; )
		{
			p = it.next();
			l = p.getProsodie();
			incrementPhonemeX = (int)(((double)largeurDispo * (double)p.getLongueur()) / (double)longueurTotalPhoneme);	
			valeurX = depart_x;
			p.setDepartX(valeurX);
			p.setIncrmeentX(incrementPhonemeX);
			for(Iterator<CoupleProsodieGraphique> itl = l.iterator() ; itl.hasNext() ; )
			{
				cpg = itl.next();
				cpg.setX(valeurX + (int)(((double)cpg.getPourcentage() * (double)incrementPhonemeX) / 100.0));
				cpg.setY(depart_y - (int)(((double)hauteurDispo * (double)cpg.getFrequence()) / (double)frequenceMaxPhoneme));
			}
			depart_x = depart_x + incrementPhonemeX;
		}
	}
	
	/**
	 * Methode qui dessine le cadre du graphe
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	private void dessinerCadre()
	{
		gc.setLineWidth(EPAISSEUR_LIGNE_2);
		gc.drawLine(BORDURE_GAUCHE, hauteur-BORDURE_BAS, largeur-BORDURE_DROITE, hauteur-BORDURE_BAS);
		gc.drawLine(BORDURE_GAUCHE, hauteur-BORDURE_BAS, BORDURE_GAUCHE, BORDURE_HAUT);
		dessinerTriangle(largeur-15, hauteur-30, OUEST);
		dessinerTriangle(BORDURE_GAUCHE, 15, NORD);
		gc.drawText("0", 5, hauteur-45);
		if(frequenceMaxPhoneme>0)
		{
			gc.drawText(""+frequenceMaxPhoneme, 5, BORDURE_DRAWABLE_HAUT-10);
		}
		gc.drawText("frequence (Hz)", BORDURE_GAUCHE+10, 3);
		gc.drawText("(ms)", largeur-65, hauteur-55);
		gc.drawText("phonemes", largeur-75, hauteur-25);
	}
	
	/**
	 * Methode qui dessine les fleches des axes
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param x Position X de la fleche (centre)
	 * @param y Position Y de la fleche (centre)
	 * @param orientation orientation de la fleche (0 a 3)
	 */
	private void dessinerTriangle(int x, int y, int orientation)
	{
		gc.setBackground(canvas.getForeground());
		if(orientation == NORD)
		{
			gc.fillPolygon(new int [] {x-MARGE_TRIANGLE, y+MARGE_TRIANGLE, x+MARGE_TRIANGLE, y+MARGE_TRIANGLE, x, y-MARGE_TRIANGLE});
		}
		else if(orientation == OUEST)
		{
			gc.fillPolygon(new int [] {x-MARGE_TRIANGLE, y-MARGE_TRIANGLE, x-MARGE_TRIANGLE, y+MARGE_TRIANGLE, x+MARGE_TRIANGLE, y});
		}
		else if(orientation == SUD)
		{
			gc.fillPolygon(new int [] {x-MARGE_TRIANGLE, y-MARGE_TRIANGLE, x+MARGE_TRIANGLE, y-MARGE_TRIANGLE, x, y+MARGE_TRIANGLE});
		}
		else if(orientation == EST)
		{
			gc.fillPolygon(new int [] {x-MARGE_TRIANGLE, y, x+MARGE_TRIANGLE, y+MARGE_TRIANGLE, x+MARGE_TRIANGLE, y-MARGE_TRIANGLE});
		}
		gc.setBackground(canvas.getBackground());
	}
	
	/**
	 * Methode qui dessine les zones selectionnees en bleu clair
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	private void dessinerZonesSelection()
	{
		PhonemeGraphique p = null;
		gc.setForeground(new Color(i.getDisplay(), SELECTION_COLOR_R, SELECTION_COLOR_V, SELECTION_COLOR_B));
		gc.setBackground(new Color(i.getDisplay(), SELECTION_COLOR_R, SELECTION_COLOR_V, SELECTION_COLOR_B));
		for(Iterator<PhonemeGraphique> it = listeSelection.iterator() ; it.hasNext() ; )
		{
			p = it.next();
			gc.fillRectangle(p.getDeprtX(), BORDURE_DRAWABLE_HAUT, p.getIncrementX(), hauteurDispo);
		}
		gc.setForeground(canvas.getForeground());
		gc.setBackground(canvas.getBackground());
	}
	
	/**
	 * Methode qui dessine l'ensemble des points
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	private void dessinerPoints()
	{
		PhonemeGraphique p = null;
		Vector<Integer> listePoint = new Vector<Integer>();
		Vector<CoupleProsodieGraphique> l = null;
		CoupleProsodieGraphique cpg = null;
		for(Iterator<PhonemeGraphique> it = listePhonemeGraphique.iterator() ; it.hasNext() ; )
		{
			p = it.next();
			l = p.getProsodie();
			gc.setLineWidth(EPAISSEUR_LIGNE_1);
			gc.setLineStyle(SWT.LINE_DASH);
			gc.setForeground(new Color(i.getDisplay(), SEPARATEUR_MOUVEMENT_COLOR_R, SEPARATEUR_MOUVEMENT_COLOR_V, SEPARATEUR_MOUVEMENT_COLOR_B));
			if(ligneADessiner == true)
			{
				gc.drawLine(ligneX, BORDURE_DRAWABLE_HAUT, ligneX, BORDURE_DRAWABLE_HAUT+hauteurDispo);
			}
			gc.setForeground(new Color(i.getDisplay(), SEPARATEUR_COLOR_R, SEPARATEUR_COLOR_V, SEPARATEUR_COLOR_B));
			gc.drawLine(p.getDeprtX(), BORDURE_DRAWABLE_HAUT, p.getDeprtX(), BORDURE_DRAWABLE_HAUT+hauteurDispo);
			gc.drawLine(p.getDeprtX()+p.getIncrementX(), BORDURE_DRAWABLE_HAUT, p.getDeprtX()+p.getIncrementX(), BORDURE_DRAWABLE_HAUT+hauteurDispo);
			gc.setForeground(canvas.getForeground());
			gc.setLineWidth(EPAISSEUR_LIGNE_2);
			gc.setLineStyle(SWT.LINE_SOLID);
			gc.drawText(p.getPhoneme(), p.getDeprtX()+(int)(p.getIncrementX()/2.0), hauteur-BORDURE_BAS+5);
			for(Iterator<CoupleProsodieGraphique> itl = l.iterator() ; itl.hasNext() ; )
			{
				cpg = itl.next();
				listePoint.add(new Integer(cpg.getX()));
				listePoint.add(new Integer(cpg.getY()));
			}
		}
		int [] liste = new int[listePoint.size()];
		for(int i = 0 ; i < listePoint.size() ; i++)
		{
			liste[i] = listePoint.get(i).intValue();
		}
		gc.setLineWidth(EPAISSEUR_LIGNE_2);
		gc.setForeground(new Color(i.getDisplay(), LIGNE_COLOR_R,LIGNE_COLOR_V,LIGNE_COLOR_B));
		gc.drawPolyline(liste);
		gc.setForeground(canvas.getForeground());
		gc.setBackground(canvas.getForeground());
		for(int i = 0 ; i < listePoint.size()-1 ; i = i + 2)
		{
			gc.fillArc(liste[i]-RAYON_POINT, liste[i+1]-RAYON_POINT, RAYON_POINT*2, RAYON_POINT*2, 0, 360);
		}
		gc.setBackground(canvas.getBackground());
	}
	
	/**
	 * Methode qui met a jour le tableau de prosodie
	 * @author Ecole Polytechnique de Sophia Antipolis
	 */
	public void miseAJourTableauProsodie()
	{
		int position = 0;
		int j = 0;
		String chaineProsodie = new String("");
		PhonemeGraphique p = null;
		CoupleProsodieGraphique cpg = null;
		while(j < listePhonemeGraphique.size())
		{
			if(listeComFreqRap.get(position) != null)
			{
				chaineProsodie = chaineProsodie + listeComFreqRap.get(position) + "\n";
			}
			else
			{
				p = listePhonemeGraphique.get(j);
				chaineProsodie = chaineProsodie + p.getPhoneme() + " " + String.valueOf(p.getLongueur());
				for(Iterator<CoupleProsodieGraphique> itl = p.getProsodie().iterator() ; itl.hasNext() ; )
				{
					cpg = itl.next();
					chaineProsodie = chaineProsodie + " " + String.valueOf(cpg.getPourcentage()) + " " + String.valueOf(cpg.getFrequence());
				}
				chaineProsodie = chaineProsodie + "\n";
				j++;
			}
			position++;
		}
		i.miseAJourTableauProsodie(chaineProsodie);
	}
	
	/**
	 * Methode qui cherche si un point de la liste est proche du curseur
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param curseurX Position X du curseur
	 * @param curseurY Position Y du curseur
	 * @return Un point de la liste ou null
	 */
	public CoupleProsodieGraphique chercherPointABouger(int curseurX, int curseurY)
	{
		PhonemeGraphique p = null;
		CoupleProsodieGraphique cpg = null;
		longueurPointABouger = 0;
		for(Iterator<PhonemeGraphique> it = listePhonemeGraphique.iterator() ; it.hasNext() ; )
		{
			p = it.next();
			for(Iterator<CoupleProsodieGraphique> itl = p.getProsodie().iterator() ; itl.hasNext() ; )
			{
				cpg = itl.next();
				if((cpg.getX() >= (curseurX-4))&&(cpg.getX() <= (curseurX+4))&&(cpg.getY() >= (curseurY-4))&&(cpg.getY() <= (curseurY+4)))
				{
					longueurPointABouger += (int)((double)cpg.getPourcentage()*(double)p.getLongueur()/100.0);
					return(cpg);
				}
			}
			longueurPointABouger += p.getLongueur();
		}
		return(null);
	}
	
	/**
	 * Methode qui cherche le phoneme le plus proche du curseur de la souris
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param curseurX Position X du curseur
	 * @param curseurY Position Y du curseur
	 * @return un phoneme ou null
	 */
	public PhonemeGraphique chercherPhonemeGraphique(int curseurX, int curseurY)
	{
		PhonemeGraphique p = null;
		for(Iterator<PhonemeGraphique> it = listePhonemeGraphique.iterator() ; it.hasNext() ; )
		{
			p = it.next();
			if((curseurX >= p.getDeprtX()) && (curseurX <= (p.getDeprtX()+p.getIncrementX())))
			{
				return(p);
			}
		}
		return(null);
	}
	
	/**
	 * Methode qui essaye d'ajouter un nouveau point
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param curseurX Position X du curseur
	 * @param curseurY Position Y du curseur
	 */
	public void ajouterPoint(int curseurX, int curseurY)
	{
		if(listePhonemeGraphique.size() > 0)
		{
			PhonemeGraphique p = null;
			if((curseurX > BORDURE_DRAWABLE_GAUCHE)&&((curseurX < BORDURE_DRAWABLE_GAUCHE+largeurDispo)))
			{
				if((curseurY > BORDURE_DRAWABLE_HAUT)&&(curseurY < (BORDURE_DRAWABLE_HAUT+hauteurDispo)))
				{
					Iterator<PhonemeGraphique> it = listePhonemeGraphique.iterator();
					p = it.next();
					while((curseurX > p.getDeprtX()+p.getIncrementX()) && (it.hasNext()))
					{
						p = it.next();
					}
					if((curseurX >= p.getDeprtX()) && (curseurX < (p.getDeprtX()+p.getIncrementX())))
					{
						if(p.getProsodie().size() < 7)
						{
							int pourcentage = (int)((curseurX-p.getDeprtX())*100.0/p.getIncrementX());
							int frequence = (int)(((double)hauteurDispo-((double)curseurY-BORDURE_DRAWABLE_HAUT))*(double)frequenceMaxPhoneme/(double)hauteurDispo);
							p.ajouterCoupleGraphique(pourcentage, frequence, curseurX, curseurY);
						}
						else
						{
							i.setIconInformationWarning();
							i.setInformation("Le nombre de couple pourcentage/frequence est limite a 7 par phoneme");
						}
					}
				}
			}
		}
	}
	
	/**
	 * Methode qui essaye de supprimer un point
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param curseurX Position X du curseur
	 * @param curseurY Position Y du curseur
	 */
	public void supprimerPoint(int curseurX, int curseurY)
	{
		PhonemeGraphique p = null;
		CoupleProsodieGraphique cpg = null;
		int cpt = 0;
		for(Iterator<PhonemeGraphique> it = listePhonemeGraphique.iterator() ; it.hasNext() ; )
		{
			p = it.next();
			cpt = 0;
			for(Iterator<CoupleProsodieGraphique> itl = p.getProsodie().iterator() ; itl.hasNext() ; )
			{
				cpg = itl.next();
				if((cpg.getX() >= (curseurX-4))&&(cpg.getX() <= (curseurX+4))&&(cpg.getY() >= (curseurY-4))&&(cpg.getY() <= (curseurY+4)))
				{
					p.getProsodie().remove(cpt);
					return;
				}
				cpt++;
			}
		}
	}
	
	/**
	 * Methode qui cherche si une ligne est proche du curseur
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param curseurX Position X du curseur
	 * @return un phoneme ou null
	 */
	public PhonemeGraphique chercherLigneABouger(int curseurX)
	{
		PhonemeGraphique old_p = null;
		PhonemeGraphique p = null;
		for(Iterator<PhonemeGraphique> it = listePhonemeGraphique.iterator() ; it.hasNext() ; )
		{
			p = it.next();
			if((p.getDeprtX() <= (curseurX+4))&&(p.getDeprtX() >= (curseurX-4)))
			{
				return(old_p);
			}
			old_p = p;
		}
		return(null);
	}
	
	/**
	 * Methode qui ajoute un phoneme a la liste  de selection de phoneme par ordre croissant
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param pgi Le phoneme a inserer
	 */
	public void ajouterListeSelection(PhonemeGraphique pgi)
	{
		int index = 0;
		PhonemeGraphique pg = null;
		for(Iterator<PhonemeGraphique> it = listeSelection.iterator() ; it.hasNext() ; )
		{
			pg = it.next();
			if(pgi.getDeprtX() < pg.getDeprtX())
			{
				break;
			}
			index++;
		}
		listeSelection.insertElementAt(pgi, index);
	}
	
	/**
	 * Methode qui retourne un tableau d'entier correspondant au position de la selection
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le tableau des postions des selections
	 */
	public int [] getListeSlection()
	{
		int [] tabSelection = new int[listeSelection.size()];
		PhonemeGraphique pl = null;
		PhonemeGraphique pls = null;
		Iterator<PhonemeGraphique> il = listePhonemeGraphique.iterator();
		Iterator<PhonemeGraphique> ils = listeSelection.iterator();
		int cpt = 0;
		int k = 0;
		if(ils.hasNext())
		{
			pls = ils.next();
			while(il.hasNext())
			{
				pl = il.next();
				if(pl.getDeprtX() == pls.getDeprtX())
				{
					tabSelection[k] = cpt;
					k++;
					if(ils.hasNext())
					{
						pls = ils.next();
					}
					else
					{
						return(tabSelection);
					}
				}
				cpt++;
			}
		}
		return(tabSelection);
	}
}
