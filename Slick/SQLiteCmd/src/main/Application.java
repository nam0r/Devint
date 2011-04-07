package main;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import bdd.DBInteractor;

public class Application {

	private BufferedReader reader;
	private DBInteractor db;
	
	public Application() {
		
		db = new DBInteractor();
		//db.fromFile("scriptBdd.txt");
		createQuestionsFromFile();
	}
	
	private void createQuestionsFromFile() {
		ArrayList< ArrayList<String[]> > questions = createFromFile("questions.txt");
		for(ArrayList<String[]> q : questions) {
			String query = "INSERT INTO questions(id, enonce, scenario, fichiervoix) " +
					"VALUES(" + q.get(0)[1] + ", '" + q.get(1)[1] + "', " + q.get(2)[1] + ", '" + q.get(3)[1] + "')";
			for(int i = 4; i < q.size(); i++) {
				String[] line = q.get(i);
				if(line[0].equals("choice")) {
					System.out.println(line[1]);
					System.out.println(line[2]);
					if(Integer.valueOf(q.get(3)[1]) == 1) {
						System.out.println(line[3]);
					}
				}
			}
			System.out.println(query);
		}
	}
	
	private ArrayList<ArrayList<String[]>> createFromFile(String filename) {
		
		ArrayList<ArrayList<String[]>> questions = new ArrayList<ArrayList<String[]>>();
		ArrayList<String[]> al = new ArrayList<String[]>();
		
		String data;
		InputStream ips = null;
		try {
			ips = new FileInputStream(filename);
		} catch (FileNotFoundException e1) {
			System.err.println("Erreur lors de l'ouverture du fichier " + filename + " : File not found");
		}
		reader = new BufferedReader(new InputStreamReader(ips));
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