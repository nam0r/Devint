package map;

import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.xml.XMLElement;
import org.newdawn.slick.util.xml.XMLElementList;
import org.newdawn.slick.util.xml.XMLParser;

/**
 * Lit un fichier XML et cree une HashMap associant un caractere
 * a une Tuile
 */
public class TileSet {

	private HashMap<Character, Tile> entries = new HashMap<Character, Tile>();
	
	public TileSet(String ref) {
		XMLParser parser = new XMLParser();
		XMLElement root = null;
		try {
			root = parser.parse(ref);
		} catch (SlickException e) {
			System.err.println("Impossible d'ouvrir ou de parser le fichier XML " + ref);
		}
		
		XMLElementList list = root.getChildrenByName("tile");
		for (int i=0;i<list.size();i++) {
			XMLElement element = list.get(i);
			char id = element.getAttribute("id").charAt(0);
			String image = element.getAttribute("image");
			Shape shape;
			try {
				shape = TileShapes.getShapeByName(element.getAttribute("shape"));
				entries.put(id, new Tile(new Image(image), shape));
			} catch (SlickException e) {
				System.err.println(e.getMessage());
			}
		}
	}
	
	/**
	 * Renvoie la tuile correspondant au caractere passe en parametre
	 * @param c Le caractere identifiant la tuile
	 * @return La tuile correspondant au caractere
	 */
	public Tile getTile(char c) {
		return entries.get(c);
	}
}