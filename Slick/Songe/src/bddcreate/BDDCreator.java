package bddcreate;

import java.util.ArrayList;

import org.newdawn.slick.util.xml.XMLParser;

import utils.Conf;

public class BDDCreator {

	private DBInteractor db;
	private XMLParser parser;
	
	public BDDCreator() {
		
		db = new DBInteractor();
		db.fromFile(Conf.RESS_PATH+"scriptBdd.txt");
		createNodesFromFile();
		createQuestionsFromFile();
		createGamesFromFile();
		db.executeQuery("SELECT * FROM questions");
		db.close();
		
	}

	private void createNodesFromFile() {
	System.out.println("===> Insertion des noeuds");
		
	String queryNode = "INSERT INTO noeuds(id, question, mini_jeu) " +
	"VALUES(" + 
		n.get(0)[1] + ", " + // id
		question + ", " + // question
		game + // mini jeu
	")";
		
		// Insert the question
		db.executeQuery(queryNode);
	}
	
	private void createQuestionsFromFile() {
		System.out.println("===> Insertion des questions");
		
		String queryQuestion = "INSERT INTO questions(id, enonce, fichiervoix, scenario, points) " +
		"VALUES(" + 
			q.get(0)[1] + ", " + // id
			"'" + q.get(1)[1] + "', " + // enonce
			"'" + q.get(2)[1] + "', " + // fichiervoix
			q.get(4)[1] + ", " + // scenario
			q.get(3)[1] + // points
		")";
		
		// Insert the choices
			
			
		// Insert the question
		//System.out.println(queryQuestion);
		db.executeQuery(queryQuestion);

	}

}