package maps;

import java.io.DataOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MapWriter {
	
	private DataOutputStream out;
	private String filename;
	
	public MapWriter(String fn) {
		filename = fn;
		
		try {
			out = new DataOutputStream(new FileOutputStream(filename));
		} catch (FileNotFoundException e) {
			System.err.println("File " + filename + " not found.");
		}
	}
	
	public void add(byte b) {
		try {
			out.writeByte(b);
		} catch (IOException e) {
			System.err.println("Erreur d'écriture dans " + filename);
		}
	}
	
	public void close() {
		try {
			out.close();
		} catch (IOException e) {
			System.err.println("Erreur lors de la fermeture de " + filename);
		}
	}

}
