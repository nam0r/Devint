package nodes;

import java.util.ArrayList;
import java.util.HashMap;


import bdd.SQLiteDB;

public class Node {
	
	private SQLiteDB bdd;
	
	private Question question = null;
	private MiniJeu game = null;
	
	public Node(int id) {
		bdd = new SQLiteDB("data2");
		
		HashMap<String,String> node = bdd.selectSingle("SELECT * FROM noeuds WHERE id=" + id);
		
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

}
