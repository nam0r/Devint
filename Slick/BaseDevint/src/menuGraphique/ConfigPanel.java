/**
 * 
 * Cette classe n'a pas besoin d'etre modifiee.
 * 
 * Cette classe implemente l'interface graphique 
 * du panel de configuration.
 */

package menuGraphique;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JSeparator;
import javax.swing.JSlider;
import javax.swing.JToggleButton;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import t2s.util.ConfigFile;

import java.awt.Toolkit;

import classesMeres.ClasseAbstraite;
import classesMeres.MainFrame;
import classesMeres.Settings;

public class ConfigPanel extends JPanel {

	/**
	 * Attributs
	 */
	private static final long serialVersionUID = 1L;

	// hauteur et largeur
	private int width, height;
	private Color couleurTexte = Settings.couleurTexte;
	private Color couleurFond = Settings.couleurFond;
	private Color couleurJ1 = Settings.couleurJ1;
	private Color couleurJ2 = Settings.couleurJ2;
	private Color couleurObjet = Settings.couleurObjet;
	private JButton couleurTexteB = new JButton("Texte");
	private JButton couleurFondB = new JButton("Fond");
	private JButton couleurJ1B = new JButton("J1");
	private JButton couleurJ2B = new JButton("J2");
	private JButton couleurObjetB = new JButton("Objet");
	private Color selectedColor = new Color(0); // de base le noir.
	private JButton selectedColorButton = new JButton("       ");

	private MainFrame mainFrame = null;
	public JFrame jf = null;
	private boolean jfActive = false;
	protected int tailleDePolice = Settings.taillePolice;
	private ConfigurationProfil profil;
	private MenuPanel menu;
	JComboBox comboBoxVoix1;
	JComboBox comboBoxVoix2;
	JComboBox comboBoxVoix3;
	JToggleButton on;
	JToggleButton off;
	protected JProgressBar progressBar;
	private JSlider slideSpeed;
	private static double speed = Settings.rapidite;
	private int typeVoix = Settings.typeVoix;
	private int typeVoix1 = Settings.typeVoix1;
	private int typeVoix2 = Settings.typeVoix2;
	

	/**
	 * Constructeurs
	 */
	
	public ConfigPanel(MainFrame m){
		mainFrame = m;
		width = 200;
		height = 600;
		configPanel();
		profil = new ConfigurationProfil();
	}
	
	public ConfigPanel(int w, int h,MainFrame m,MenuPanel p){
		mainFrame = m;
		width = w;
		height = h;
		configPanel();
		configElementsPanel();
		menu = p;
		profil = new ConfigurationProfil();
	}


	public void configPanel(){
		setPreferredSize(new Dimension(width,height));
		setLayout(new GridLayout(4,1));
		setBackground(Color.white);
		setBorder(new LineBorder(Color.black,2));
	}


   
	
	public void configElementsPanel(){
		// panel voix
		JPanel voixPanel = new JPanel();
		voixPanel.setLayout(new GridLayout(5,1));
		
		JPanel panelChoixTypeVoix = new JPanel();
		panelChoixTypeVoix.setLayout(new GridLayout(1,2));
		voixPanel.add(panelChoixTypeVoix);
		
		JLabel text = new JLabel("Changer voix(1/2/3):");
		text.setFont(new Font(Settings.police,1,24));
		panelChoixTypeVoix.add(text);
		
		JPanel panelComboBox = new JPanel();
		panelComboBox.setLayout(new GridLayout(1,3));
		panelChoixTypeVoix.add(panelComboBox);
		
		comboBoxVoix1 = new JComboBox();
		comboBoxVoix1.setFocusable(false);
		for (int i = 1; i <= 7; i++)
			comboBoxVoix1.addItem(i);
		comboBoxVoix1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                typeVoix = (Integer)comboBoxVoix1.getSelectedItem();
            }
		});
		comboBoxVoix1.setFont(new Font(Settings.police,1,20));
		panelComboBox.add(comboBoxVoix1);
		
		comboBoxVoix2 = new JComboBox();
		comboBoxVoix2.setFocusable(false);
		for (int i = 1; i <= 7; i++)
			comboBoxVoix2.addItem(i);
		comboBoxVoix2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                typeVoix1 = (Integer)comboBoxVoix2.getSelectedItem();
            }

			});
		comboBoxVoix2.setFont(new Font(Settings.police,1,20));
		panelComboBox.add(comboBoxVoix2);
		
		comboBoxVoix3 = new JComboBox();
		comboBoxVoix3.setFocusable(false);
		for (int i = 1; i <= 7; i++)
			comboBoxVoix3.addItem(i);
		comboBoxVoix3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                typeVoix2 = (Integer)comboBoxVoix3.getSelectedItem();
            }

			});
		comboBoxVoix3.setFont(new Font(Settings.police,1,20));
		panelComboBox.add(comboBoxVoix3);
	
		
		JSeparator sep1 = new JSeparator();
		voixPanel.add(sep1);
		
		JPanel tablo2 = new JPanel();
		tablo2.setLayout(new GridLayout(2,2));
		voixPanel.add(tablo2);
		
		JLabel text3 = new JLabel("         Débit parole:");
		text3.setFont(new Font(Settings.police,1,20));
		tablo2.add(text3);
		
		JLabel text2 = new JLabel("             Son activé: ");
		text2.setFont(new Font(Settings.police,1,20));
		tablo2.add(text2);
		
		slideSpeed = new JSlider(1,20);
		slideSpeed.setFocusable(false);
		slideSpeed.addChangeListener(new ChangeListener(){
					public void stateChanged(ChangeEvent arg0){
						speed = (double)(slideSpeed.getValue())/10;
						Settings.rapidite = speed;
						ConfigFile.changer("RAPIDITE", ""+Settings.rapidite);
					}
				});  
		tablo2.add(slideSpeed);
		
		
		JPanel panelRadio = new JPanel();
		panelRadio.setLayout(new GridLayout(1,2));
		tablo2.add(panelRadio);
		
		on = new JToggleButton();
		on.setFocusable(false);
		on.setFont(new Font(Settings.police,1,16));
		on.setText("ON");
		on.setBackground(Color.BLACK);
		on.doClick();
		on.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
            	on.setSelected(true);
            	off.setSelected(false);
            	
            	mainFrame.voix.toggle();
            	mainFrame.voix1.toggle();
            	mainFrame.voix2.toggle();
            	
            	mainFrame.voix.playText("Son, activé!");
            }});
		panelRadio.add(on);
		
		off = new JToggleButton();
		off.setFocusable(false);
		off.setFont(new Font(Settings.police,1,16));
		off.setText("OFF");
		off.setBackground(Color.BLACK);
		off.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
            	off.setSelected(true);
            	on.setSelected(false);
            	
            	mainFrame.voix.playText("Son, désactivé!");
            	mainFrame.voix.toggle();
            	mainFrame.voix1.toggle();
            	mainFrame.voix2.toggle();
            }});
		panelRadio.add(off);
		
		JSeparator sep2 = new JSeparator();
		voixPanel.add(sep2);
		
		
		JPanel bouts = new JPanel();
		bouts.setLayout(new FlowLayout());
		add(bouts);
		JButton but1 = new JButton("Tester Voix 1");
		but1.setFont(new Font(Settings.police,1,18));
		but1.setBackground(Color.GREEN);
		but1.setFocusable(false);
		but1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                try {
                	Settings.typeVoix = (Integer)comboBoxVoix1.getSelectedItem();
					mainFrame.voix.setVoix(Settings.typeVoix);
					if(off.isSelected())
						mainFrame.voix.toggle();
					else{
						mainFrame.voix1.stop();
						mainFrame.voix2.stop();
						mainFrame.voix.playText("La voi, a été modifiée!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
            }});
		bouts.add(but1);
		
		JButton but2 = new JButton("Tester Voix 2");
		but2.setFont(new Font(Settings.police,1,18));
		but2.setBackground(Color.GREEN);
		but2.setFocusable(false);
		but2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                try {
                	Settings.typeVoix1 = (Integer)comboBoxVoix2.getSelectedItem();
//                	Settings.rapidite = speed;
//					//ConfigurationSIVOX.modifierSIVOX(speed);
//            		ConfigFile.changer("RAPIDITE", ""+Settings.rapidite);
					mainFrame.voix1.setVoix(Settings.typeVoix1);
					if(off.isSelected())
						mainFrame.voix1.toggle();
					else{
						mainFrame.voix.stop();
						mainFrame.voix2.stop();
						mainFrame.voix1.playText("La voi, a été modifiée!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
            }});
		bouts.add(but2);
		
		JButton but3 = new JButton("Tester Voix 3");
		but3.setFont(new Font(Settings.police,1,18));
		but3.setBackground(Color.GREEN);
		but3.setFocusable(false);
		but3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                try {
                	Settings.typeVoix2 = (Integer)comboBoxVoix3.getSelectedItem();
//                	Settings.rapidite = speed;
//					//ConfigurationSIVOX.modifierSIVOX(speed);
//            		ConfigFile.changer("RAPIDITE", ""+Settings.rapidite);
					mainFrame.voix.setVoix(Settings.typeVoix2);
					if(off.isSelected())
						mainFrame.voix2.toggle();
					else{
						mainFrame.voix.stop();
						mainFrame.voix1.stop();
						mainFrame.voix2.playText("La voi, a été modifiée!");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
            }});
		bouts.add(but3);
		
		voixPanel.add(bouts);
		voixPanel.setBorder(BorderFactory.createTitledBorder(
		         BorderFactory.createEtchedBorder(javax.swing.border.
		                  EtchedBorder.LOWERED),"Voix",2,0,new Font("Georgia",Font.BOLD,22),Color.BLUE));
		add(voixPanel);
			
		   
		 //couleurs  
		JPanel colorPanel = new JPanel();
		colorPanel.setLayout(new GridLayout(2,1));
		add(colorPanel);
		
		
		JPanel colorChooserPanel = new JPanel(); 
		colorChooserPanel.setLayout(new GridLayout(1,5));
		
		 // Premier Bouton
		couleurTexteB.setFont(new Font(Settings.police,1,24));
        couleurTexteB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                if(!jfActive){
                	askColor2((short)1);
            		jfActive=true;
                }
            }

			});
        
        colorChooserPanel.add(couleurTexteB);
        couleurTexteB.setBackground(couleurTexte);
        couleurTexteB.setFocusable(false);
        
		 // 2ème Bouton
        couleurFondB.setFont(new Font(Settings.police,1,24));
        couleurFondB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
            	if(!jfActive){
            		askColor2((short)2);
            		jfActive = true;
            	}
            }

			});
        
        colorChooserPanel.add(couleurFondB);
        couleurFondB.setBackground(couleurFond);
        couleurFondB.setFocusable(false);
        
		 // 3ème Bouton
        couleurJ1B.setFont(new Font(Settings.police,1,24));
        couleurJ1B.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
            	if(!jfActive){
            		askColor2((short)3);
            		jfActive = true;
            	}
            }

			});
        
        colorChooserPanel.add(couleurJ1B);
        couleurJ1B.setBackground(couleurJ1);
        couleurJ1B.setFocusable(false);
        
		 // 4ème Bouton
        couleurJ2B.setFont(new Font(Settings.police,1,24));
        couleurJ2B.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
            	if(!jfActive){
            		askColor2((short)4);
            		jfActive = true;
            	}
            }

			});
        
        colorChooserPanel.add(couleurJ2B);
        couleurJ2B.setBackground(couleurJ2);
        couleurJ2B.setFocusable(false);
        
        // 5ème Bouton
        couleurObjetB.setFont(new Font(Settings.police,1,24));
        couleurObjetB.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
            	if(!jfActive){
            		askColor2((short)5);
            		jfActive = true;
            	}
            }

			});
        
        colorChooserPanel.add(couleurObjetB);
        couleurObjetB.setBackground(couleurObjet);
        couleurObjetB.setFocusable(false);
        
        colorPanel.add(colorChooserPanel);
        
        JButton boutton = new JButton("Tester Couleurs");
        boutton.setFont(new Font(Settings.police,1,34));
        boutton.setBackground(Color.GREEN);
        boutton.setFocusable(false);
        boutton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
            	appliquer();
            	peindre(menu);
            }});
        colorPanel.add(boutton);
     
	        
	   colorPanel.setOpaque(true);
	   colorPanel.setBorder(BorderFactory.createTitledBorder(
	         BorderFactory.createEtchedBorder(javax.swing.border.
	                  EtchedBorder.LOWERED),"Couleurs",2,0,new Font("Georgia",Font.BOLD,22),Color.BLUE));
	   colorPanel.setFont(new Font(Settings.police,1,24));
	   


	   // Choix de la police
		JPanel policeChooserPanel = new JPanel(); 
		policeChooserPanel.setLayout(new GridLayout(2,1));
		
		JPanel taillePolice = new JPanel();
		taillePolice.setLayout(new FlowLayout());
		// 1ère ligne :
		JPanel policePanel = new JPanel();
		policePanel.setLayout(new GridLayout(3,1));
		
		// Label1
		JLabel texte = new JLabel();
		texte.setFont(new Font(Settings.police,1,24));
		texte.setText("Appuyer sur HAUT pour l'augmenter.");
		policePanel.add(texte);
		//Label2
		JLabel texte2 = new JLabel();
		texte2.setFont(new Font(Settings.police,1,24));
		texte2.setText("Appuyer sur BAS pour la diminuer.");
		policePanel.add(texte2);
		
		//Barre de progres
		progressBar = new JProgressBar(8,108);
	    progressBar.setPreferredSize(new Dimension(130,50));
	    progressBar.setForeground(Color.BLUE);
	    progressBar.setValue(tailleDePolice);
	    policePanel.add(progressBar);
		
		policeChooserPanel.add(policePanel);
		

		//policeChooserPanel.setOpaque(false);
		taillePolice.setBorder(BorderFactory.createTitledBorder(
		         BorderFactory.createEtchedBorder(javax.swing.border.
		                  EtchedBorder.LOWERED),"Taille de Police",2,0,new Font("Georgia",Font.BOLD,22),Color.BLUE));                  
	   policeChooserPanel.setFont(new Font(Settings.police,1,24));	
	   taillePolice.add(policeChooserPanel);
	   
	   add(taillePolice);
	   


		// panel explications
		JPanel explicationsPanel2 = new JPanel();
		explicationsPanel2.setLayout(new GridLayout(3,1));
		explicationsPanel2.setOpaque(true);
		
		explicationsPanel2.setBorder(BorderFactory.createTitledBorder(
			         BorderFactory.createEtchedBorder(javax.swing.border.
			                  EtchedBorder.LOWERED),"Profils",2,0,new Font("Georgia",Font.BOLD,22),Color.BLUE));
		add(explicationsPanel2);	
		
	   // Bouton Enregistrer
	   JButton boutonEnregistrer = new JButton("Enregistrer");
	   boutonEnregistrer.setFont(new Font(Settings.police,1,45));
	   boutonEnregistrer.setFocusable(false);
	   boutonEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!jfActive){
					enregistrerProfil(JOptionPane.QUESTION_MESSAGE);}
					jfActive = true;
			}
		});
		
		// Bouton Charger		
		JButton boutonCharger = new JButton("Charger");
		boutonCharger.setFont(new Font(Settings.police,1,45));
		boutonCharger.setFocusable(false);
		boutonCharger.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!jfActive){
					loadProfilFrame();
					jfActive = true;
				}
				}
		});
		
		//Bouton supprimer		
		JButton boutonSupprimer = new JButton("Supprimer");
		boutonSupprimer.setFont(new Font(Settings.police,1,45));
		boutonSupprimer.setFocusable(false);
		boutonSupprimer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!jfActive){
					loadDeleteFrame();
					jfActive = true;
				}}
		});
		
		boutonCharger.setSize(new Dimension(200,150));
		explicationsPanel2.add(boutonCharger);
		explicationsPanel2.add(boutonEnregistrer);
		explicationsPanel2.add(boutonSupprimer);  	
		
		miseAJourVoiceInterface();
	}
	
	/*
	 * Cette methode propose un choix tres detaille de couleurs.
	 * 
    private void askColor(final short s){
        final JDialog d = new JDialog();
        d.setLayout(new BorderLayout());
        final JColorChooser j = new JColorChooser();
        j.setVisible(true);
        d.add(j,BorderLayout.CENTER);
        d.setVisible(true);
        d.setSize(460,380);
        d.setLocation(150,150);
        
        
        JPanel buttonPanel = new JPanel();
        
        JButton ok = new JButton("OK");
        ok.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                Color c = j.getColor();
                if((s == 1)&&(c != null)){
                    couleurTexte = c;
                    couleurTexteB.setBackground(c);
                }
              else if ((s == 2)&&(c != null)){
                    couleurFond = c;
                    couleurFondB.setBackground(c);
                }
              else if ((s == 3)&&(c != null)){
                  couleurJ1 = c;
                  couleurJ1B.setBackground(c);
              }
              else if ((s == 4)&&(c != null)){
                  couleurJ2 = c;
                  couleurJ2B.setBackground(c);
              }
              else if ((s == 5)&&(c != null)){
                  couleurObjet = c;
                  couleurObjetB.setBackground(c);
              }
                    d.setVisible(false);
                    repaint();
                    return;
            } });
        buttonPanel.add(ok);
        
        JButton cancel = new JButton("Annuler");
        cancel.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                d.setVisible(false);
                return;
            }});
        
        buttonPanel.add(cancel);
        
        d.add(buttonPanel, BorderLayout.SOUTH);
        d.pack();
    }
    */
	
	/*
	 * Methode qui va proposer un choix de couleurs
	 */
    private void askColor2(final short s){
        JButton c1B = new JButton("");
        c1B.setBackground(Color.RED);
        JButton c2B = new JButton("");
        c2B.setBackground(Color.GREEN);
        JButton c3B = new JButton("");
        Color jaune= new Color(255,255,0);//jaune
        c3B.setBackground(jaune); 
        JButton c4B = new JButton("");
        c4B.setBackground(Color.BLUE);
        JButton c5B = new JButton("");
        c5B.setBackground(Color.LIGHT_GRAY);
        JButton c6B = new JButton("");
        c6B.setBackground(Color.WHITE);
        JButton c7B = new JButton("");
        c7B.setBackground(Color.BLACK);
        JButton c8B = new JButton("");
        Color bleuc= new Color(0,153,255);//bleu ciel
        c8B.setBackground(bleuc);
        JButton c9B = new JButton("");
        Color orange= new Color(255,102,0);//orange
        c9B.setBackground(orange); 
        JButton c10B = new JButton("");
        Color violet= new Color(102,0,102);//violet (magenta avant)
        c10B.setBackground(violet);
        JButton c11B = new JButton("");
        Color vertf= new Color(0,102,0);//vert foncé
        c11B.setBackground(vertf);
        JButton c12B = new JButton("");
        Color rose= new Color(255,65,173);//rose
        c12B.setBackground(rose);
        

        
            
        jf = new JFrame("Choisissez une couleur");  
        jf.setAlwaysOnTop(true);
        // Container Frame
        Container contentPane = jf.getContentPane();
        contentPane.setLayout(new BorderLayout());
        contentPane.setPreferredSize(new Dimension(400,400));
        
        // Color panel
        JPanel colorPanel = new JPanel();
        colorPanel.setLayout(new GridLayout(3,4));      
                
        // Boutons couleurs
        c1B.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                selectedColor = Color.RED;
                selectedColorButton.setBackground(selectedColor);
            }
                       });
        c2B.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                selectedColor = Color.GREEN;
                selectedColorButton.setBackground(selectedColor);
            }
                       });
        c3B.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                selectedColor = new Color(255,255,0);//jaune
                selectedColorButton.setBackground(selectedColor);
            }
                       });
        c4B.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                selectedColor = Color.BLUE;
                selectedColorButton.setBackground(selectedColor);
            }
                       });
        c5B.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                selectedColor = Color.LIGHT_GRAY;
                selectedColorButton.setBackground(selectedColor);
            }
                       });
        c6B.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                selectedColor = Color.WHITE;
                selectedColorButton.setBackground(selectedColor);
            }
                       });
        c7B.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                selectedColor = Color.BLACK;
                selectedColorButton.setBackground(selectedColor);
            }
                       });
        c8B.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                selectedColor = new Color(0,153,255); //bleu ciel
                selectedColorButton.setBackground(selectedColor);
            }
                       });
        c9B.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                selectedColor = new Color(255,102,0);//orange
                selectedColorButton.setBackground(selectedColor);
            }
                       });
        c10B.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                selectedColor = new Color(102,0,102);//violet (magenta avant)
                selectedColorButton.setBackground(selectedColor);
            }
                       });
        c11B.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                selectedColor = new Color(0,102,0);//vert foncé
                selectedColorButton.setBackground(selectedColor);
            }
                       });
        c12B.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg) {
                selectedColor = new Color(255,65,173);//rose
                selectedColorButton.setBackground(selectedColor);
            }
                       });
      
        
        colorPanel.add(c1B);
        colorPanel.add(c2B);
        colorPanel.add(c3B);
        colorPanel.add(c4B);
        colorPanel.add(c5B);
        colorPanel.add(c6B);
        colorPanel.add(c7B);
        colorPanel.add(c8B);
        colorPanel.add(c9B);
        colorPanel.add(c10B);
        colorPanel.add(c11B);
        colorPanel.add(c12B);
                
                
    // Boutons panel
            JPanel buttonPanel = new JPanel();
            buttonPanel.setLayout(new FlowLayout());
            
           
    buttonPanel.add(new JLabel("Couleur selectionnée : "));
    
    
    selectedColorButton.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0) {        
        }});
    selectedColorButton.setEnabled(false);
    buttonPanel.add(selectedColorButton);
            
    buttonPanel.add(new JLabel("          "));
            JButton buttonOk = new JButton("Valider");
            buttonOk.addActionListener(new ActionListener() {
                    public void actionPerformed(ActionEvent e) {

                            
                            // traitement :
                            Color c = selectedColor;
                            if((s == 1)&&(c != null)){
                                couleurTexte = c;
                                couleurTexteB.setBackground(c);
                            }
                          else if ((s == 2)&&(c != null)){
                                couleurFond = c;
                                couleurFondB.setBackground(c);
                            }
                          else if ((s == 3)&&(c != null)){
                              couleurJ1 = c;
                              couleurJ1B.setBackground(c);
                          }
                          else if ((s == 4)&&(c != null)){
                              couleurJ2 = c;
                              couleurJ2B.setBackground(c);
                          }
                          else if ((s == 5)&&(c != null)){
                              couleurObjet = c;
                              couleurObjetB.setBackground(c);
                          }
                                jf.dispose();
                                jfActive=false;
                                repaint();
                                return;
                        } });
                  
            buttonPanel.add(buttonOk);
     

            JButton cancel = new JButton("Annuler");
    cancel.addActionListener(new ActionListener(){
        public void actionPerformed(ActionEvent arg0) {
            jf.dispose();
            jfActive = false; 
            return;
        }});
    
    buttonPanel.add(cancel);
    

    // Ajout des composants à la fenetre
    contentPane.add(colorPanel, BorderLayout.CENTER);
    contentPane.add(buttonPanel, BorderLayout.SOUTH);

            // centrer la fenetre
            Dimension dimEcran = Toolkit.getDefaultToolkit().getScreenSize();
            jf.setLocation((int) (dimEcran.getWidth() / 2 - jf.getWidth() / 2),
                            (int) (dimEcran.getHeight() / 2 - jf.getHeight() / 2));
            jf.setResizable(false);
            jf.pack();
            jf.setVisible(true);
}
    
	   
    public void chargerProfil(String nomDuProfil){
    	// Repeind la frame de droite avec ces couleurs
    	// tailleDePolice = (Integer) jc1.getSelectedItem();
    	try {
			profil.chargerUnProfilAPartirDuFicher(nomDuProfil);
			enregistrer();
			miseAJourVoiceInterface();
			peindre(menu);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
    public void loadProfilFrame() {
        jf = new JFrame("Choix du profil à charger");
        jf.setAlwaysOnTop(true);
        // Container Frame
        JPanel contentPane = (JPanel) jf.getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.setPreferredSize(new Dimension(270,75));
        
        // Ajout de boite(s) de choix
		ArrayList<String> listeProfils = new ArrayList<String>();
		listeProfils=profil.listerProfils();
        final JComboBox jc2 = new JComboBox();
        for (int i = 0; i < listeProfils.size() ; i++)
                jc2.addItem(listeProfils.get(i));
        
        JButton buttonOk = new JButton("Charger");
        buttonOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                                // Ici : chargerProfil(); (ou la bonne méthode)
                	chargerProfil((String)jc2.getSelectedItem());
                	jfActive = false;
                        }
        });
        JButton buttonClose = new JButton("Fermer");
        buttonClose.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		//fermer la boite de dialogue
        		jf.dispose();
        		jfActive = false;
        		return;
        	}
        });
        contentPane.add(jc2);
        contentPane.add(buttonOk);
        contentPane.add(buttonClose);

        // centrer la fenetre sur l'écran.
        Dimension dimEcran = Toolkit.getDefaultToolkit().getScreenSize();
        jf.setLocation((int) (dimEcran.getWidth() / 2 - jf.getWidth() / 2),
                        (int) (dimEcran.getHeight() / 2 - jf.getHeight() / 2));
        jf.setResizable(false);
        jf.pack();
        jf.setVisible(true);
    }

    public void enregistrerProfil(int message){
    	// TODO : sérialization et écriture dans le fichier 
    	// renvoyer "il faudra taper K dans le menu pour charger ce profil"
    	// tailleDePolice = (Integer) jc1.getSelectedItem();
    	//mainFrame.switchPanel(panel)
    	
    	enregistrer();
    	String nomDuProfil = JOptionPane.showInputDialog(mainFrame,"Entrez un nouveau nom de profil ","Profil existant",message);
    	if(nomDuProfil != null){
    		try {
				if(!profil.enregistrerLeProfilCourantDansLeFicher(nomDuProfil))
					enregistrerProfil(JOptionPane.ERROR_MESSAGE);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
    	}
    	
    	jf.dispose();
    	jfActive = false;
    }
    
    public void supprimerProfil(String nomDuProfil){
		profil.supprimerProfil(nomDuProfil);
    }
    
    public void loadDeleteFrame() {
    	jf = new JFrame("Choix du profil à supprimer");
        jf.setAlwaysOnTop(true);
        // Container Frame
        JPanel contentPane = (JPanel) jf.getContentPane();
        contentPane.setLayout(new FlowLayout());
        contentPane.setPreferredSize(new Dimension(270,75));
        
        // Ajout de boite(s) de choix
		ArrayList<String> listeProfils = new ArrayList<String>();
		listeProfils=profil.listerProfils();
        final JComboBox jc = new JComboBox();
        for (int i = 0; i < listeProfils.size() ; i++)
                jc.addItem(listeProfils.get(i));
        
        JButton buttonOk = new JButton("Supprimer");
        buttonOk.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                	supprimerProfil((String)jc.getSelectedItem());
                	jf.dispose();
                	jfActive = false;
                	return;
                        }
        });
        JButton buttonClose = new JButton("Fermer");
        buttonClose.addActionListener(new ActionListener(){
        	public void actionPerformed(ActionEvent e){
        		//fermer la boite de dialogue
        		jf.dispose();
        		jfActive = false;
        		return;
        	}
        });
        contentPane.add(jc);
        contentPane.add(buttonOk);
        contentPane.add(buttonClose);

        // centrer la fenetre sur l'écran.
        Dimension dimEcran = Toolkit.getDefaultToolkit().getScreenSize();
        jf.setLocation((int) (dimEcran.getWidth() / 2 - jf.getWidth() / 2),
                        (int) (dimEcran.getHeight() / 2 - jf.getHeight() / 2));
        jf.pack();
        jf.setVisible(true);
    }

	
    public void enregistrer(){
    	Settings.oldCouleurFond=Settings.couleurFond;
    	Settings.oldCouleurTexte = Settings.couleurTexte;
    	Settings.oldCouleurJ1 = Settings.couleurJ1;
    	Settings.oldCouleurJ2 = Settings.couleurJ2;
    	Settings.oldCouleurObjet = Settings.couleurObjet;
    	Settings.oldTaillePolice = Settings.taillePolice;
    	Settings.oldRapidite = Settings.rapidite;
		Settings.oldTypeVoix = Settings.typeVoix;
		Settings.oldTypeVoix1 = Settings.typeVoix1;
		Settings.oldTypeVoix2 = Settings.typeVoix2;
		try{
			//ConfigurationSIVOX.modifierSIVOX(Settings.rapidite);
			ConfigFile.changer("RAPIDITE", ""+Settings.rapidite);
		}catch (Exception e){
			e.printStackTrace();
		}
    }
    
	public void appliquer(){
		Settings.couleurFond = couleurFond;
		Settings.couleurTexte = couleurTexte;
		Settings.couleurJ1 = couleurJ1;
		Settings.couleurJ2 = couleurJ2;
		Settings.couleurObjet = couleurObjet;
		Settings.taillePolice = tailleDePolice;
		Settings.rapidite = speed;
		Settings.typeVoix = typeVoix;
		Settings.typeVoix1 = typeVoix1;
		Settings.typeVoix2 = typeVoix2;
	}
	
	public void defaire(){
		Settings.couleurFond = Settings.oldCouleurFond;
		Settings.couleurTexte = Settings.oldCouleurTexte;
		Settings.couleurJ1 = Settings.oldCouleurJ1;
		Settings.couleurJ2 = Settings.oldCouleurJ2;
		Settings.couleurObjet = Settings.oldCouleurObjet;
		Settings.taillePolice = Settings.oldTaillePolice;
		Settings.rapidite = Settings.oldRapidite;
		Settings.typeVoix = Settings.oldTypeVoix;
		Settings.typeVoix1 = Settings.oldTypeVoix1;
		Settings.typeVoix2 = Settings.oldTypeVoix2;
	}
	
	public void peindre(ClasseAbstraite panel){
		panel.miseAJour();
		panel.repaint();
	}
	
	public void miseAJourVoiceInterface(){
		couleurTexte = Settings.couleurTexte;
		couleurFond = Settings.couleurFond;
		couleurJ1 = Settings.couleurJ1;
		couleurJ2 = Settings.couleurJ2;
		couleurObjet = Settings.couleurObjet;
		tailleDePolice = Settings.taillePolice;
		typeVoix = Settings.typeVoix;
		typeVoix1 = Settings.typeVoix1;
		typeVoix2 = Settings.typeVoix2;
		speed = Settings.rapidite;
		//System.out.println("rapid: "+speed);
		speed=10*speed;
		slideSpeed.setValue((int)speed);
		speed=speed/10;
		//System.out.println("slidespeed: "+slideSpeed.getValue());
		couleurTexteB.setBackground(couleurTexte);
		couleurFondB.setBackground(couleurFond);
		couleurJ1B.setBackground(couleurJ1);
		couleurJ2B.setBackground(couleurJ2);
		couleurObjetB.setBackground(couleurObjet);
		comboBoxVoix1.setSelectedItem(typeVoix);
		comboBoxVoix2.setSelectedItem(typeVoix1);
		comboBoxVoix3.setSelectedItem(typeVoix2);
		progressBar.setValue(tailleDePolice);
	}
}
