package utils;

import game.AlienIA;
import game.Crate;
import game.Emitter;
import game.HomerIA;
import game.MarioIA;
import game.Spirit;

import java.awt.Dimension;
import java.util.LinkedList;

import net.phys2d.math.ROVector2f;
import net.phys2d.math.Vector2f;
import net.phys2d.raw.shapes.DynamicShape;
import net.phys2d.raw.shapes.Polygon;
import nodes.Node;
import actors.Actor;
import actors.PhysicalEntity;

/**
 * Global variables of the whole game.
 */

public class Globals {
	/** The global score */
	public static int score = 0;
	/** current player type */
	public static int playerType;
	/** Current player */
	public static Actor player;
	/** Return to state */
	public static int returnState;
	/** The current scenario Node */
	public static Node node;
	/** the gc height */
	public static int gcHeight;
	/** the gc width */
	public static int gcWidth;
	/** the real time x screen offset */
	public static float xoffset;
	/** the real time x screen offset */
	public static float yoffset;
	/** The list of the states the player has to go */
	public static LinkedList<Integer> stateToGoTo = new LinkedList<Integer>();
	/** The list of the nodes */
	public static LinkedList<Dimension> nodes = new LinkedList<Dimension>();
	/**
	 * Indicates if the player has already played the game and therefore doesn't
	 * need a lot of explanation
	 */
	public static boolean hasAlreadyPlayed;

	/**
	 * Returns an entity from its ID
	 * @param entityID the name-ID of the entity to create in the map
	 * @param n the node to associate to it
	 * @return entity the entity corresponding to the ID we gave
	 */
	public static PhysicalEntity getEntityFromString(String entityID, Node n) {
		PhysicalEntity entity = null;
		System.out.println("================");
		for(Dimension d : nodes) {
			System.out.println(d);
		}
		System.out.println("================");
		Dimension d = nodes.poll();
		if(entityID.equals("homer")) {
			entity = new HomerIA((int)d.getWidth(), (int)d.getHeight(), n);
		}
		else if(entityID.equals("mario")) {
			entity = new MarioIA((int)d.getWidth(), (int)d.getHeight(), n);
		}
		else if(entityID.equals("alien")) {
			entity = new AlienIA((int)d.getWidth(), (int)d.getHeight(), n);
		}
		else if(entityID.equals("crate")) {
			entity = new Crate(Conf.IMG_TEXTURES_PATH+"crate3.jpg", (int)d.getWidth(), (int)d.getHeight(), 80, 80, 500);
		}
		else if(entityID.equals("spirit")) {
			ROVector2f[] points = {new Vector2f(-20,0), new Vector2f(0,-3), new Vector2f(20,0)};
			DynamicShape spirit = new Polygon(points);
			entity = new Spirit(Conf.EMITTERS_PATH+"blue_spirit.xml", -50, 1, (int)d.getWidth(), (int)d.getHeight(), 100, spirit, n);
		}
		else if(entityID.equals("flame")) {
			entity = new Emitter(Conf.EMITTERS_PATH+"flame.xml", "flame", 10, 2, (int)d.getWidth(), (int)d.getHeight(), 50, 1, 100);
		}
		return entity;
	}
	
}
