package bddcreate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.newdawn.slick.util.ResourceLoader;

import utils.Conf;

public class BDDCreator {

	private BufferedReader reader;
	private DBInteractor db;
	
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
		ArrayList< ArrayList<String[]> > noeuds = createFromFile(Conf.RESS_PATH+"nodes.txt");
		for(ArrayList<String[]> n : noeuds) {
			
			String game = "NULL";
			String question = "NULL";
			for(int i = 1; i < n.size(); i++) {
				if(n.get(i)[0].equals("game")) {
					game = n.get(i)[1];
				}
				else if(n.get(i)[0].equals("question")) {
					question = n.get(i)[1];
				}
			}
			
			String queryNode = "INSERT INTO noeuds(id, question, mini_jeu) " +
					"VALUES(" + 
						n.get(0)[1] + ", " + // id
						question + ", " + // question
						game + // mini jeu
					")";
			
			// Insert the question
			//System.out.println(queryNode);
			db.executeQuery(queryNode);
		}
	}
	
	private void createGamesFromFile() {
		System.out.println("===> Insertion des mini-jeux");
		ArrayList< ArrayList<String[]> > minijeux = createFromFile(Conf.RESS_PATH+"games.txt");
		for(ArrayList<String[]> g : minijeux) {
			String queryGame = "INSERT INTO minijeux(id, niv1, niv2, niv3, niv4, score1, score2, score3, score4) " +
					"VALUES(" + 
						g.get(0)[1] + ", " + // id
						g.get(1)[1] + ", " + // niv1
						g.get(2)[1] + ", " + // niv2
						g.get(3)[1] + ", " + // niv2
						g.get(4)[1] + ", " + // niv4
						
						g.get(1)[2] + ", " + // score1
						g.get(2)[2] + ", " + // score2
						g.get(3)[2] + ", " + // score2
						g.get(4)[2] + // score4
					")";
			
			// Insert the question
			//System.out.println(queryGame);
			db.executeQuery(queryGame);
		}
	}
	
	private void createQuestionsFromFile() {
		System.out.println("===> Insertion des questions");
		ArrayList< ArrayList<String[]> > questions = createFromFile(Conf.RESS_PATH+"questions.txt");
		for(ArrayList<String[]> q : questions) {
			String queryQuestion = "INSERT INTO questions(id, enonce, fichiervoix, scenario, points) " +
					"VALUES(" + 
						q.get(0)[1] + ", " + // id
						"'" + q.get(1)[1] + "', " + // enonce
						"'" + q.get(2)[1] + "', " + // fichiervoix
						q.get(4)[1] + ", " + // scenario
						q.get(3)[1] + // points
					")";
			
			// Insert the choices
			System.out.println("     ===> Insertion des choix de la question");
			int pos = 0;
			for(int i = 5; i < q.size(); i++) { // For each choice
				String[] line = q.get(i);
				String enonce = "", fichiervoix = "", id_noeud = "NULL", correct = "";
				if(line[0].equals("choice")) {
					enonce = line[1]; // enonce
					fichiervoix = line[2]; // fichiervoix
					correct = line[3];
					if(Integer.valueOf(q.get(4)[1]) == 1) { // If it's a scenario
						id_noeud = line[4]; // Node
					}
				}
				String queryChoice = "INSERT INTO choix(enonce, fichiervoix, id_question, position, id_noeud, correct) " +
						"VALUES(" +
					"'" + enonce + "', " + 
					"'" + fichiervoix + "', " +
					q.get(0)[1] + ", " + // id_question
					pos + ", " +
					id_noeud + ", " +
					correct +
				")";
				//System.out.println(queryChoice);
				db.executeQuery(queryChoice);
				
				pos++;
			}
			
			// Insert the question
			//System.out.println(queryQuestion);
			db.executeQuery(queryQuestion);
		}
	}
	
	private ArrayList<ArrayList<String[]>> createFromFile(String filename) {
		
		ArrayList<ArrayList<String[]>> questions = new ArrayList<ArrayList<String[]>>();
		ArrayList<String[]> al = new ArrayList<String[]>();
		
		String data;
		/*InputStream ips = null;
		try {
			ips = new FileInputStream(filename);
		} catch (FileNotFoundException e1) {
			System.err.println("Erreur lors de l'ouverture du fichier " + filename + " : File not found");
		}*/
		reader = new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream(filename)));
		try {
			// Creation des questions :
			//   - une HashMap par question
			//   - un ArrayList pour stocker l'ensemble des questions
			while ((data = reader.readLine()) != null){
				if(!data.startsWith("#") && !data.isEmpty()) {
					
					/*
					System.out.println(label(data));
					for(String s : values(data)) {
						System.out.println("\t" + s);
					}
					*/
					
					String[] parsed = parse(data);
					if(parsed[0].equals("id")) {
						al = new ArrayList<String[]>();
						questions.add(al);
					}
					al.add(parsed);
					
					//executeQuery(query);
				}
			}
		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture du fichier de définition des questions.");
		}
		
		return questions;
	}

	private String[] parse(String data) {
		String[] values = values(data);
		String[] parsed = new String[values.length + 1];
		parsed[0] = label(data);
		for(int i = 0; i < values.length; i++) {
			parsed[i+1] = values[i];
		}
		return parsed;
	}
	
	private String label(String data) {
		return data.substring(0, data.indexOf(":"));
	}
	
	private String[] values(String data) {
		String rp = data.substring(data.indexOf(":")+1);
		String[] raw_values = rp.split("\\|");
		
		String[] values = new String[raw_values.length];
		for(int i=0; i<values.length; i++)
			values[i] = raw_values[i].trim();
		
		return values;
	}

}