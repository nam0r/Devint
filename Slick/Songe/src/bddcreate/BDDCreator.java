package bddcreate;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.util.xml.XMLElement;
import org.newdawn.slick.util.xml.XMLElementList;
import org.newdawn.slick.util.xml.XMLParser;

import utils.Conf;

public class BDDCreator {

	private DBInteractor db;
	private XMLParser parser;
	
	public BDDCreator() {
		parser = new XMLParser();
		
		db = new DBInteractor();
		db.fromFile(Conf.RESS_PATH+"bddtxt/scriptBdd.txt");
		createNodesFromFile();
		createQuestionsScenarioFromFile();
		createQuestionsCultureFromFile();
		createIAsFromFile();
		//db.executeQuery("SELECT * FROM questions");
		db.close();
		
	}

	private void createNodesFromFile() {
		System.out.println("===> Insertion des noeuds");
		
		XMLElement root = null;
		try {
			root = parser.parse(Conf.RESS_PATH+"bddtxt/nodes.xml");
		} catch (SlickException e) {
			System.err.println("File nodes.xml not found.");
		}
		
		XMLElementList nodesList = root.getChildrenByName("node");
		for(int i=0; i<nodesList.size(); i++) {
			String id = nodesList.get(i).getChildrenByName("id").get(0).getContent();
			String ia = nodesList.get(i).getChildrenByName("ia").get(0).getContent();
			String type_ia = nodesList.get(i).getChildrenByName("type_ia").get(0).getContent();
			String node_id = nodesList.get(i).getChildrenByName("node_id").get(0).getContent();
			
			String query = "INSERT INTO nodes(id, ia, type_ia, node_id) " +
			"VALUES(" + id + ", " +	ia + ", '" + escapeQuotes(type_ia) + "', " + node_id + ")";
			
			// Insert the node
			db.executeQuery(query);
			
			// EVENTS
			XMLElementList eventsList = nodesList.get(i).getChildrenByName("events").get(0).getChildrenByName("event");
			for(int j=0; j<eventsList.size(); j++) {
				String type = eventsList.get(j).getChildrenByName("type").get(0).getContent();
				XMLElementList paramList = eventsList.get(j).getChildrenByName("param");
				String param = (paramList.size() > 0) ? paramList.get(0).getContent() : "";
				String param2 = (paramList.size() > 1) ? paramList.get(1).getContent() : "";
				String param3 = (paramList.size() > 2) ? paramList.get(2).getContent() : "";
				
				String queryEvent = "INSERT INTO events(id_node, type, param, param2, param3, ordre) " +
				"VALUES(" + id + ", '" +	escapeQuotes(type) + "', '" + escapeQuotes(param) + "', '" 
				+ escapeQuotes(param2) + "', '" + escapeQuotes(param3) + "', " + j + ")";
				
				// Insert the node
				db.executeQuery(queryEvent);
			}
		}
		
	}
	
	private void createQuestionsScenarioFromFile() {
		System.out.println("===> Insertion des questions de scénario");
		
		XMLElement root = null;
		try {
			root = parser.parse(Conf.RESS_PATH+"bddtxt/questions_scenario.xml");
		} catch (SlickException e) {
			System.err.println("File questions_scenario.xml not found.");
		}
		
		XMLElementList questionsList = root.getChildrenByName("question");
		for(int i=0; i<questionsList.size(); i++) {
			String id = questionsList.get(i).getChildrenByName("id").get(0).getContent();
			String sound = questionsList.get(i).getChildrenByName("sound").get(0).getContent();
			String text = questionsList.get(i).getChildrenByName("text").get(0).getContent();
			String yes = questionsList.get(i).getChildrenByName("yes").get(0).getContent();
			String no = questionsList.get(i).getChildrenByName("no").get(0).getContent();
			
			String query = "INSERT INTO qscen(id, sound, text, yes, no) " +
			"VALUES(" + id + ", '" + escapeQuotes(sound) + "', '" + escapeQuotes(text) + "', " + yes + ", " + no + ")";
			
			// Insert the question
			db.executeQuery(query);
		}

	}
	
	private void createQuestionsCultureFromFile() {
		System.out.println("===> Insertion des questions de culture générale");
		
		XMLElement root = null;
		try {
			root = parser.parse(Conf.RESS_PATH+"bddtxt/questions_culture.xml");
		} catch (SlickException e) {
			System.err.println("File questions_culture.xml not found.");
		}
		
		XMLElementList questionsList = root.getChildrenByName("question");
		for(int i=0; i<questionsList.size(); i++) {
			String id = questionsList.get(i).getChildrenByName("id").get(0).getContent();
			String sound = questionsList.get(i).getChildrenByName("sound").get(0).getContent();
			String text = questionsList.get(i).getChildrenByName("text").get(0).getContent();
			String points = questionsList.get(i).getChildrenByName("points").get(0).getContent();
			
			String query = "INSERT INTO qcult(id, sound, text, points) " +
			"VALUES(" + id + ", '" + escapeQuotes(sound) + "', '" + escapeQuotes(text) + "', " + points + ")";
			
			// Insert the question
			db.executeQuery(query);
			
			
			// CHOICES
			XMLElementList choicesList = questionsList.get(i).getChildrenByName("choices").get(0).getChildrenByName("choice");
			for(int j=0; j<choicesList.size(); j++) {
				String textChoice = choicesList.get(j).getChildrenByName("text").get(0).getContent();
				String soundChoice = choicesList.get(j).getChildrenByName("sound").get(0).getContent();
				String trueChoice = choicesList.get(j).getChildrenByName("true").get(0).getContent();
				
				String queryChoice = "INSERT INTO choices(id_question, sound, text, true) " +
				"VALUES(" + id + ", '" +	escapeQuotes(soundChoice) + "', '" + escapeQuotes(textChoice) + "', " + trueChoice + ")";
				
				// Insert the choice
				db.executeQuery(queryChoice);
			}
		}

	}
	
	private void createIAsFromFile() {
		System.out.println("===> Insertion des IAs");
		
		XMLElement root = null;
		try {
			root = parser.parse(Conf.RESS_PATH+"bddtxt/ias.xml");
		} catch (SlickException e) {
			System.err.println("File ias.xml not found.");
		}
		
		XMLElementList iasList = root.getChildrenByName("ia");
		for(int i=0; i<iasList.size(); i++) {
			String id = iasList.get(i).getChildrenByName("id").get(0).getContent();
			
			XMLElement images = iasList.get(i).getChildrenByName("images").get(0);
			
			String walk = images.getChildrenByName("walk").get(0).getContent(); // string
			String walknum = images.getChildrenByName("walknum").get(0).getContent();
			String jump = images.getChildrenByName("jump").get(0).getContent(); // string
			String jumpnum = images.getChildrenByName("jumpnum").get(0).getContent();
			
			XMLElement size = iasList.get(i).getChildrenByName("size").get(0);
			
			String width = size.getChildrenByName("width").get(0).getContent();
			String height = size.getChildrenByName("height").get(0).getContent();
			String yoffset = size.getChildrenByName("yoffset").get(0).getContent();
			
			XMLElement sounds = iasList.get(i).getChildrenByName("sounds").get(0);
			
			String main = sounds.getChildrenByName("main").get(0).getContent(); // string
			String dejavu = sounds.getChildrenByName("dejavu").get(0).getContent(); // string
			String troptot = sounds.getChildrenByName("troptot").get(0).getContent(); // string
			
			String query = "INSERT INTO ias(id, walk, walknum, jump, jumpnum, width, height, yoffset, mainsound, dejavusound, troptotsound) " +
			"VALUES(" + id + ", '" + escapeQuotes(walk) + "', " + walknum + ", '" + escapeQuotes(jump) + "', " + jumpnum + 
			", " + width + ", "	+ height + ", " + yoffset + ", '" + escapeQuotes(main) + 
			"', '" + escapeQuotes(dejavu) + "', '" + escapeQuotes(troptot) + "')";
			
			// Insert the question
			db.executeQuery(query);
		}

	}
	
	private String escapeQuotes(String str) {
		return str.replace("'", "''");
	}

}