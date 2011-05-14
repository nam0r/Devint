package nodes;

import java.util.ArrayList;
import java.util.HashMap;

import actors.IA;
import bdd.SQLiteDB;

public class Node {
	
	private SQLiteDB bdd;
	
	private int id;
	private int node_id;
	
	private IA ia;
	
	private Question question = null;
	private MiniJeu game = null;
	
	public Node(int id) {
		bdd = new SQLiteDB("data");
		HashMap<String,String> node = bdd.selectSingle("SELECT * FROM nodes WHERE id=" + id);
		
		this.id = id;
		this.node_id = Integer.valueOf(node.get("node_id"));
		
		// Create the IA to display
		int id_ia = Integer.valueOf(node.get("ia"));
		String type_ia = node.get("type_ia");
		
		HashMap<String,String> infosIA = bdd.selectSingle("SELECT * FROM ias WHERE id=" + id_ia);
		String walk = infosIA.get("walk");
		int walknum = Integer.valueOf(infosIA.get("walknum"));
		String jump = infosIA.get("jump");
		int jumpnum = Integer.valueOf(infosIA.get("jumpnum"));
		int width = Integer.valueOf(infosIA.get("width"));
		int height = Integer.valueOf(infosIA.get("height"));
		int offset = Integer.valueOf(infosIA.get("offset"));
		String mainsound = infosIA.get("mainsound");
		String dejavusound = infosIA.get("dejavusound");
		String troptotsound = infosIA.get("troptotsound");
		
		
		
		/* ****** *
		 * Events *
		 * ****** */
		ArrayList<HashMap<String,String>> events = bdd.select("SELECT * FROM events WHERE id_node=" + id);
		
		// TODO Ordonner ? (ajouter attr puis ORDER BY) Pour les choix des questions aussi ? Pas forcement ds XML.
		for(HashMap<String,String> event : events) {
			String type = event.get("type");
			
			// Dialog
			if(type.equals("D")) {
				String soundDialog = event.get("param");
			}
			// Scenario
			else if(type.equals("S")) {
				int idQuestion = Integer.valueOf(event.get("param"));
				HashMap<String,String> question = bdd.selectSingle("SELECT * FROM qscen WHERE id=" + idQuestion);
			}
			// Culture
			else if(type.equals("C")) {
				// Random
				// ArrayList<HashMap<String,String>> ids = bdd.select("SELECT id FROM qcult");
				// int idQuestion = random entre O et ids.size() - 1;
				// HashMap<String,String> question = bdd.selectSingle("SELECT * FROM qcult WHERE id=" + idQuestion);
			}
			// Transition
			else if(type.equals("Transition")) {
				int idState = Integer.valueOf(event.get("param"));
			}
			
		}
		
		// =================================================================================================== //
		
		// Add the question if there is one
		if(node.get("question") != null && !node.get("question").equals("null")) {
			HashMap<String,String> q = bdd.selectSingle("SELECT * FROM questions WHERE id=" + node.get("question"));
			
			boolean scenario = q.get("scenario").equals("1") ? true : false;
			
			// Create the choices
			ArrayList< HashMap<String,String> > cs = bdd.select("SELECT * FROM choix WHERE id_question=" + q.get("id") +
					" ORDER BY position"
			);
			
			ArrayList<Choice> choices = new ArrayList<Choice>();
			// For each choice
			for(HashMap<String,String> c : cs) {
				boolean correct = c.get("correct").equals("1") ? true : false;
				
				int nodeToGoTo = -1;
				if(scenario) {
					nodeToGoTo = Integer.valueOf(c.get("id_noeud"));
				}
				
				Choice choix = new Choice(
						c.get("enonce"),
						c.get("fichiervoix"),
						correct,
						nodeToGoTo
				);
				
				choices.add(choix);
			}
			
			// Create the question
			question = new Question(
					q.get("enonce"),
					q.get("fichiervoix"),
					scenario,
					choices.toArray(new Choice[choices.size()]),
					Integer.valueOf(q.get("points"))
			);
			
		}
		
		// Add the game if there is one
		if(node.get("mini_jeu") != null && !node.get("mini_jeu").equals("null")) {
			HashMap<String,String> g = bdd.selectSingle("SELECT * FROM minijeux WHERE id=" + node.get("mini_jeu"));
			
			game = new MiniJeu(
					Integer.valueOf(g.get("id")),
					Integer.valueOf(g.get("niv1")), Integer.valueOf(g.get("niv2")), Integer.valueOf(g.get("niv3")), Integer.valueOf(g.get("niv4")),
					Integer.valueOf(g.get("score1")), Integer.valueOf(g.get("score2")), Integer.valueOf(g.get("score3")), Integer.valueOf(g.get("score4"))
			);
		}
	}
	
	public Question getQuestion() {
		return question;
	}
	
	public MiniJeu getGame() {
		return game;
	}
	
	/*
	public String getEntityToDisplay() {
		return ;
	}
	*/
	
	public boolean equals(Node noeud){
		return this.id == noeud.id;
	}
}