package map;

import game.Crate;

import java.util.HashMap;

import main.Conf;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.xml.XMLElement;
import org.newdawn.slick.util.xml.XMLElementList;
import org.newdawn.slick.util.xml.XMLParser;

import actors.PhysicalEntity;

/**
 * Reads an XML file and creates a HashMap associating the character to a tile
 */
public class TileSet {

	private HashMap<Character, Tile> tiles = new HashMap<Character, Tile>();
	private HashMap<Character, PhysicalEntity> objects = new HashMap<Character, PhysicalEntity>();
	
	public TileSet(String ref) {
		XMLParser parser = new XMLParser();
		XMLElement root = null;
		try {
			root = parser.parse(ref);
		} catch (SlickException e) {
			System.err.println("Impossible d'ouvrir ou de parser le fichier XML " + ref);
		}
		
		XMLElementList tilesList = root.getChildrenByName("tiles");
		for (int i=0;i<tilesList.size();i++) {
			XMLElementList tileList = tilesList.get(i).getChildrenByName("tile");
			for (int j=0;j<tileList.size();j++) {
				XMLElement element = tileList.get(j);
				char id = element.getAttribute("id").charAt(0);
				String image = element.getAttribute("image");
				Shape shape;
				try {
					shape = TileShapes.getShapeByName(element.getAttribute("shape"));
					tiles.put(id, new Tile(new Image(Conf.IMG_TILES_PATH+image), shape));
				} catch (SlickException e) {
					System.err.println(e.getMessage());
				}
			}
		}
		XMLElementList objectsList = root.getChildrenByName("objects");
		for (int i=0;i<objectsList.size();i++) {
			XMLElementList objectList = objectsList.get(i).getChildrenByName("object");
			for (int j=0;j<objectList.size();j++) {
				XMLElement element = objectList.get(j);
				char id = element.getAttribute("id").charAt(0);
				//String image = element.getAttribute("image");
				int size = Integer.parseInt(element.getAttribute("size"));
				int mass = Integer.parseInt(element.getAttribute("mass"));
				String image = element.getAttribute("image");

				//else if(id == 'b') object = new Crate();
				objects.put(id, new Crate(Conf.IMG_TEXTURES_PATH+image, 0, 0, size, size, mass));
			}
		}
		
	}
	
	/**
	 * Return the physical object corresponding to a character
	 * @param c the character identifying the object
	 * @return the object corresponding to the character
	 */
	public PhysicalEntity getObject(char c) {
		return objects.get(c);
	}
	
	/**
	 * Return the tile corresponding to a character
	 * @param c the character identifying the tile
	 * @return the tile corresponding to the character
	 */
	public Tile getTile(char c) {
		return tiles.get(c);
	}
}