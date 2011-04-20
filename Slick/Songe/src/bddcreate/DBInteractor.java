package bddcreate;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.newdawn.slick.util.ResourceLoader;

import bdd.SQLiteDB;

public class DBInteractor extends SQLiteDB {
	
	private BufferedReader reader;
	private String query;
	
	public DBInteractor() {
		super("data2");

		//fromFile("scriptBdd.txt");
		//fromCmdLine();
		//close();
	}
	
	public void fromCmdLine() {
		reader = new BufferedReader(new InputStreamReader(System.in));
		do {
			try {
				System.out.print(">>> ");
				query = reader.readLine();
				//System.out.println(query);
				executeQuery(query);
			} catch (IOException e) {
				System.err.println("Erreur lors de la lecture de la requête.");
			}
		} while(! query.equalsIgnoreCase("close"));
	}
	
	public void fromFile(String filename) {
		/*InputStream ips = null;
		try {
			ips = new FileInputStream(filename);
		} catch (FileNotFoundException e1) {
			System.err.println("Erreur lors de l'ouverture du fichier " + filename + " : File not found");
		}*/
		reader = new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream(filename)));
		try {
			while ((query = reader.readLine()) != null){
				if(query.startsWith("#")) {
					System.out.println("===>" + query.substring(1));
				}
				else if(!query.isEmpty() && !query.startsWith("--")) {
					//System.out.println(query);
					executeQuery(query);
					/*
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					*/
				}
			}
		} catch (IOException e) {
			System.err.println("Erreur lors de la lecture de la requête.");
		}

	}

}
