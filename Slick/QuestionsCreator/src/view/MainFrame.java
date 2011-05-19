package view;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

@SuppressWarnings("serial")
public class MainFrame extends JFrame {
	
	private BorderLayout layout;
	private Form form;
	
	private final static String TITLE = "Questions Creator";
	
	public MainFrame() {
		super(TITLE);
		
		setSize(600,230);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		layout = new BorderLayout();
		setLayout(layout);
		
		JLabel title = new JLabel(TITLE);
		add(title, BorderLayout.NORTH);
		
		Field[] fields = {
			new Field(
					new String[] {"Enonce : "},
					new String[] {""},
					new int[] {10}
			),
			
			new Field(
					new String[] {"Voix : "},
					new String[] {""},
					new int[] {10}
			),
			
			new Field(
					new String[] {"Points : "},
					new String[] {""},
					new int[] {10}
			),
			
			new Field(
					new String[] {"Choix 1 : ", "Voix : ", "Vrai ? ", "Noeud lié : "},
					new String[] {"", "", "", ""},
					new int[] {10, 10, 2, 2}
			),
			
			new Field(
					new String[] {"Choix 2 : ", "Voix : ", "Vrai ? ", "Noeud lié : "},
					new String[] {"", "", "", ""},
					new int[] {10, 10, 2, 3}
			),
			
			new Field(
					new String[] {"Choix 3 : ", "Voix : ", "Vrai ? ", "Noeud lié : "},
					new String[] {"", "", "", ""},
					new int[] {10, 10, 2, 2}
			)
		};
		
		form = new Form(fields, true);
		add(form, BorderLayout.CENTER);
		
		JButton button = new JButton("Save");
		add(button, BorderLayout.SOUTH);
		
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				saveQuestion();
				form.clear();
			}
		});
		
		setVisible(true);
	}
	
	private void saveQuestion() {
		boolean scenario = false;
		for(String s : form.get("Noeud")) {
			if(!s.isEmpty()) {
				scenario = true;
				break;
			}
		}
		System.out.println(form.getExact("Enonce : "));
		
		if(scenario) {
			System.out.println("Scénario");
		}
		else {
			System.out.println("Pas scénario");
		}
	}

}
