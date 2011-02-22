package maps;

import java.io.DataInputStream;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;

public class MapReader {
	
	private final int LAYERS = 3;
	
	private DataInputStream in;
	
	private String filename;
	private int window;
	LinkedList<Byte> bytes;
	
	public MapReader(String fn, int w) {
		filename = fn;
		window = w;
		bytes = new LinkedList<Byte>();
		
		try {
			in = new DataInputStream(new FileInputStream(filename));
		} catch (FileNotFoundException e) {
			System.err.println("La carte " + filename + " n'a pas pu être chargée (fichier non trouvé).");
		}
		
		if(init() == null) {
			System.out.println("Map terminée.");
		}
	}
	
	public void close() {
		try {
			in.close();
		}
		catch (IOException e) {
			System.err.println("La carte " + filename + " n'a pas pu être chargée (erreur d'entrée/sortie).");
		}
	}
	
	public void write() {
				
	}
	
	private byte[] init() {
		int success = -1;
		byte[] tab_bytes = new byte[window * LAYERS];
		
		try {
			success = in.read(tab_bytes);
		}
		catch (IOException e) {
			System.err.println("La carte " + filename + " n'a pas pu être chargée (erreur d'entrée/sortie).");
		}
			
		for(byte b : tab_bytes)
			bytes.add(b);
		
		return success == -1 ? null : tab_bytes;
	}
	
	public byte[] next() {
		byte tab_bytes[] = new byte[LAYERS];
		
		try {
			for(int i=0; i < LAYERS; i++) {
				tab_bytes[i] = in.readByte();
				bytes.remove();
				bytes.add(tab_bytes[i]);
			}
		}
		catch(EOFException e) {
			return null;
		}
		catch (IOException e) {
			System.err.println("La carte " + filename + " n'a pas pu être chargée (erreur d'entrée/sortie).");
		}
		
		return tab_bytes;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<LAYERS; i++) {
			for(int j=i; j<bytes.size(); j+=LAYERS) {
				sb.append(bytes.get(j));
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
