package nodes;

import game.Spirit;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Random;

import utils.Conf;
import utils.Globals;
import actors.PhysicalEntity;
import actors.StayingIA;
import actors.WalkingIA;

/**
 * A node with all its information
 * 
 * @author namor
 * @author Afnarel
 */
public class Node {
	
	private int id;
	private int node_id;
	
	private PhysicalEntity ia = null;
	private String type_ia;
	private int id_ia;
	private String walk;
	private int walknum;
	private String jump;
	private int jumpnum;
	private int width;
	private int height;
	private int yoffset;
	private String mainsound;
	private String dejavusound;
	private String troptotsound;
	private boolean flip = true;
	private float mass = 5;
	
	private LinkedList<Event> events;
	
	public Node(int id) {
		HashMap<String,String> node = Globals.bdd.selectSingle("SELECT * FROM nodes WHERE id=" + id);
		
		this.id = id;
		this.node_id = Integer.valueOf(node.get("node_id"));
		
		// Create the IA to display
		 
		//ia = new HomerIA(100, 100, this);
		
		id_ia = Integer.valueOf(node.get("ia"));
		if(id_ia != -1) {
			type_ia = node.get("type_ia");
			
			HashMap<String,String> infosIA = Globals.bdd.selectSingle("SELECT * FROM ias WHERE id=" + id_ia);
			walk = infosIA.get("walk");
			walknum = Integer.valueOf(infosIA.get("walknum"));
			jump = infosIA.get("jump");
			jumpnum = Integer.valueOf(infosIA.get("jumpnum"));
			width = Integer.valueOf(infosIA.get("width"));
			height = Integer.valueOf(infosIA.get("height"));
			yoffset = Integer.valueOf(infosIA.get("yoffset"));
			mainsound = infosIA.get("mainsound");
			dejavusound = infosIA.get("dejavusound");
			troptotsound = infosIA.get("troptotsound");
			flip = true;
			mass = 5;
			
			ia = createIA();
		}
		
		/* ****** *
		 * Events *
		 * ****** */
		this.events = new LinkedList<Event>();
		
		ArrayList<HashMap<String,String>> eventsProperties = Globals.bdd.select("SELECT * FROM events WHERE id_node=" + id + " ORDER BY ordre");
		
		for(HashMap<String,String> event : eventsProperties) {
			String type = event.get("type");
			
			// Dialog
			if(type.equals("D")) {
				String soundDialog = event.get("param");
				String imageDialog = event.get("param2");
				events.offer(new Dialog(soundDialog, imageDialog));
			}
			// Scenario
			else if(type.equals("S")) {
				int idQuestion = Integer.valueOf(event.get("param"));
				HashMap<String,String> question = Globals.bdd.selectSingle("SELECT * FROM qscen WHERE id=" + idQuestion);
				
				String sound = question.get("sound");
				String text = question.get("text");
				int yes = Integer.valueOf(question.get("yes"));
				int no = Integer.valueOf(question.get("no"));
				
				events.offer(new QuestionScenario(text, sound, yes, no));
			}
			// Culture
			else if(type.equals("C")) {
				
				// Select a random indice in questionsNotAsked
				Random r = new Random();
				int indice = r.nextInt(Globals.questionsNotAsked.size());
				
				// get the corresponding question
				HashMap<String,String> question = Globals.questionsNotAsked.get(indice);
			
				String sound = question.get("sound");
				String text = question.get("text");
				int points = Integer.valueOf(question.get("points"));
				QuestionCulture theQuestion = new QuestionCulture(text, sound, points);
				//choices added
				ArrayList<HashMap<String,String>> choices = Globals.bdd.select("SELECT * FROM choices WHERE id_question=" + Globals.questionsNotAsked.get(indice).get("id"));
				for(HashMap<String, String> c : choices){
					if(Integer.valueOf(c.get("true")) == 0)
						theQuestion.addChoice(new ChoiceCulture(c.get("text"), c.get("sound"), false));
					else
						theQuestion.addChoice(new ChoiceCulture(c.get("text"), c.get("sound"), true));
				}
				events.offer(theQuestion);
				
				// Finally, remove the question: it has  been asked
				Globals.questionsNotAsked.remove(indice);
			}
			// Transition
			else if(type.equals("T")) {
				int idState = Integer.valueOf(event.get("param"));
				
				events.offer(new Transition(idState));
			}
			
			// Transition speciale
			else if(type.equals("TS")) {
				int idState = Integer.valueOf(event.get("param"));
				int success = Integer.valueOf(event.get("param2"));
				int failure = Integer.valueOf(event.get("param3"));
				
				events.offer(new TransitionSpeciale(idState, success, failure));
			}
			
		}
	}
	
	public int getID(){
		return id;
	}
	
	public String getMainSound(){
		return mainsound;
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
	
	public PhysicalEntity getIA() {
		return ia;
	}
	
	private PhysicalEntity createIA(){
		if(type_ia.equals("WalkingIA")) {
			return new WalkingIA(Conf.IMG_SPRITES_PATH + walk, walknum, yoffset, flip, 0, 0, 
					width, height, mass, this);
		}
		else if(type_ia.equals("StayingIA")) {
			return new StayingIA(Conf.IMG_SPRITES_PATH + walk, walknum, yoffset, flip, 0, 0, 
					width, height, mass, this);
		}
		else if(type_ia.equals("JumpingIA")) {
			// TODO A implementer
		}
		else if(type_ia.equals("Spirit")){
			/*ROVector2f[] points = {new Vector2f(-width,0), new Vector2f(0,-3), new Vector2f(width,0)};
			DynamicShape spirit = new Polygon(points);*/
			return new Spirit(Conf.EMITTERS_PATH+"blue_spirit.xml", -50f, 1, 0f, 0f, (float)width, (float)height, (float)mass, this);
		}
		return null;
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