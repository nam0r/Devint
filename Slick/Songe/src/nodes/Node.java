package nodes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import utils.Conf;
import actors.IA;
import actors.WalkingIA;
import bdd.SQLiteDB;

public class Node {
	
	private SQLiteDB bdd;
	
	private int id;
	private int node_id;
	
	private IA ia;
	
	private LinkedList<Event> events; 
	
	public Node(int id) {
		bdd = new SQLiteDB("data");
		HashMap<String,String> node = bdd.selectSingle("SELECT * FROM nodes WHERE id=" + id);
		
		this.id = id;
		this.node_id = Integer.valueOf(node.get("node_id"));
		
		// Create the IA to display
		 
		//ia = new HomerIA(100, 100, this);
		
		int id_ia = Integer.valueOf(node.get("ia"));
		String type_ia = node.get("type_ia");
		
		HashMap<String,String> infosIA = bdd.selectSingle("SELECT * FROM ias WHERE id=" + id_ia);
		String walk = infosIA.get("walk");
		int walknum = Integer.valueOf(infosIA.get("walknum"));
		String jump = infosIA.get("jump");
		int jumpnum = Integer.valueOf(infosIA.get("jumpnum"));
		int width = Integer.valueOf(infosIA.get("width"));
		int height = Integer.valueOf(infosIA.get("height"));
		int yoffset = Integer.valueOf(infosIA.get("yoffset"));
		String mainsound = infosIA.get("mainsound");
		String dejavusound = infosIA.get("dejavusound");
		String troptotsound = infosIA.get("troptotsound");
		// TODO A recuperer
		boolean flip = true;
		float mass = 5;
		
		if(type_ia.equals("WalkingIA")) {
			ia = new WalkingIA(Conf.IMG_SPRITES_PATH + walk, walknum, yoffset, flip, 0, 0, 
					width, height, mass, this);
		}
		else if(type_ia.equals("JumpingIA")) {
			// TODO A implementer
		}
		
		/* ****** *
		 * Events *
		 * ****** */
		this.events = new LinkedList<Event>();
		
		ArrayList<HashMap<String,String>> eventsProperties = bdd.select("SELECT * FROM events WHERE id_node=" + id + " ORDER BY ordre");
		
		for(HashMap<String,String> event : eventsProperties) {
			String type = event.get("type");
			
			// Dialog
			if(type.equals("D")) {
				String soundDialog = event.get("param");
				events.offer(new Dialog(soundDialog));
			}
			// Scenario
			else if(type.equals("S")) {
				int idQuestion = Integer.valueOf(event.get("param"));
				HashMap<String,String> question = bdd.selectSingle("SELECT * FROM qscen WHERE id=" + idQuestion);
				
				String sound = question.get("sound");
				String text = question.get("text");
				int yes = Integer.valueOf(question.get("yes"));
				int no = Integer.valueOf(question.get("no"));
				
				events.offer(new QuestionScenario(text, sound, yes, no));
			}
			// Culture
			else if(type.equals("C")) {
				ArrayList<HashMap<String,String>> ids = bdd.select("SELECT id FROM qcult");
				
				Random r = new Random();
				int indice = r.nextInt(ids.size());
				
				HashMap<String,String> question = bdd.selectSingle("SELECT * FROM qcult WHERE id=" + ids.get(indice).get("id"));
			
				String sound = question.get("sound");
				String text = question.get("text");
				int points = Integer.valueOf(question.get("points"));
				QuestionCulture theQuestion = new QuestionCulture(text, sound, points);
				//choices added
				ArrayList<HashMap<String,String>> choices = bdd.select("SELECT * FROM choices WHERE id_question=" + ids.get(indice).get("id"));
				for(HashMap<String, String> c : choices){
					if(Integer.valueOf(c.get("true")) == 0)
						theQuestion.addChoice(new ChoiceCulture(c.get("text"), c.get("sound"), false));
					else
						theQuestion.addChoice(new ChoiceCulture(c.get("text"), c.get("sound"), true));
				}
				events.offer(theQuestion);
			}
			// Transition
			else if(type.equals("Transition")) {
				int idState = Integer.valueOf(event.get("param"));
				
				events.offer(new Transition(idState));
			}
			
		}
	}
	
	public Event pollEvent() {
		return events.poll();
	}
	
	public LinkedList<Event> getEvents(){
		return events;
	}
	
	public boolean equals(Node noeud){
		return this.id == noeud.id;
	}
	
	public int getNextNodeId() {
		return this.node_id;
	}
	
	public IA getIA(){
		return ia;
	}
	
	/**
	 * Indicates if the node contains a scenario question
	 * @return if the node has a scenario question event
	 */
	public boolean hasScenarioQuestion(){
		for(Event e : events){
			if(e.getType().equals("S"))
				return true;
		}
		return false;
	}
}