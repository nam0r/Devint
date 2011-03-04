package map;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.newdawn.slick.util.ResourceLoader;

import environment.TileEnvironment;

/**
 * A loader for a very simple tile based map text format that maps from
 * characters in a text file to tile definitions in an XML file.
 */
public class MapLoader {

	private TileSet set;
	
	/**
	 * Cree un nouveau chargeur de map
	 * 
	 * @param set Le TileSet dans lequel sont stockees les tuiles disponibles
	 */
	public MapLoader(TileSet set) {
		this.set = set;
	}
	
	/**
	 * Charge une carte a partir du fichier passe en parametre.
	 * 
	 * @param ref Le chemin vers la carte a charger
	 * @return The configured environment thats been populated with tiles
	 */
	public TileEnvironment load(String ref) {
		BufferedReader reader = new BufferedReader(new InputStreamReader(ResourceLoader.getResourceAsStream(ref)));
		int width = 0;
		ArrayList<String> lines = new ArrayList<String>();
		
		// read through the lines recording them into a list and
		// determining the maximum width.
		try {
			while (reader.ready()) {
				String line = reader.readLine();
				if (line == null) {
					break;
				}
			
				width = Math.max(line.length(), width);
				lines.add(line);
			}
		} catch (IOException e) {
			System.err.println("Erreur lors du chargement de la carte " + ref);
		}
		
		int height = lines.size();
		// create an environment based on the number of lines in the file 
		// and maximum length of any line in the file
		TileEnvironment env = new TileEnvironment(width,height);
		
		for (int x=0;x<width;x++) {
			for (int y=0;y<height;y++) {
				char c = lines.get(y).charAt(x);
				Tile tile = set.getTile(c);
				if (tile != null) {
					env.setTile(x, y, tile);
				}
			}
		}
		
		return env;
	}
}
