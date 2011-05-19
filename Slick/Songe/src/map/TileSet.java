package map;

import game.Crate;
import game.Enemy;

import java.awt.Dimension;
import java.util.HashMap;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.util.xml.XMLElement;
import org.newdawn.slick.util.xml.XMLElementList;
import org.newdawn.slick.util.xml.XMLParser;

import utils.Conf;
import actors.PhysicalEntity;

/**
 * Reads an XML file and creates a HashMap associating the character to a tile
 */
public class TileSet {

	private HashMap<Character, Tile> tiles = new HashMap<Character, Tile>();
	private HashMap<Character, PhysicalEntity> objects = new HashMap<Character, PhysicalEntity>();
	private HashMap<Character, Dimension> nodes = new HashMap<Character, Dimension>();
	
	public TileSet(String ref) {
		XMLParser parser = new XMLParser();
		XMLElement root = null;
		try {
			root = parser.parse(ref);
		} catch (SlickException e) {
			System.err.println("Impossible d'ouvrir ou de parser le fichier XML " + ref);
		}
		
		//tiles
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
		//objects
		XMLElementList objectsList = root.getChildrenByName("objects");
		for (int i=0;i<objectsList.size();i++) {
			XMLElementList objectList = objectsList.get(i).getChildrenByName("object");
			for (int j=0;j<objectList.size();j++) {
				XMLElement element = objectList.get(j);
				char id = element.getAttribute("id").charAt(0);
				int mass = Integer.parseInt(element.getAttribute("mass"));
				String image = element.getAttribute("image");
				int size = 0, height = 0, width = 0, spritenum = 0;
				//if it's a crate
				if(element.getAttribute("type").equals("crate")){
					size = Integer.parseInt(element.getAttribute("size"));
					objects.put(id, new Crate(Conf.IMG_TEXTURES_PATH+image, 0, 0, size, size, mass));
				}
				//if it's an enemy
				else if(element.getAttribute("type").equals("enemy")){
					width = Integer.parseInt(element.getAttribute("width"));
					height = Integer.parseInt(element.getAttribute("height"));
					spritenum = Integer.parseInt(element.getAttribute("spritenum"));
					objects.put(id, new Enemy(Conf.IMG_SPRITES_PATH+image, spritenum, 0, 0, width, height, mass));
				}
			}
		}
		//nodes
		XMLElementList nodesList = root.getChildrenByName("nodes");
		for (int i=0;i<nodesList.size();i++) {
			XMLElementList nodeList = nodesList.get(i).getChildrenByName("node");
			for (int j=0;j<nodeList.size();j++) {
				XMLElement element = nodeList.get(j);
				char id = element.getAttribute("id").charAt(0);
				nodes.put(id, new Dimension());
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
	
	/**
	 * Return the node corresponding to a character
	 * @param c the character identifying the node
	 * @return the node corresponding to the character
	 */
	public Dimension getNode(char c) {
		Dimension d =  nodes.get(c);
		if(d != null) {
			return (Dimension) d.clone();
		}
		return null;
	}
}