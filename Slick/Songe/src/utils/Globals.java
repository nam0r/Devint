package utils;

import game.AlienIA;
import game.Crate;
import game.HomerIA;
import game.MarioIA;

import java.awt.Dimension;
import java.util.LinkedList;

import nodes.Node;
import actors.Actor;
import actors.PhysicalEntity;

/**
 * Global variables of the whole game.
 */

public class Globals {
	/** The global score */
	public static int score=0;
	/** current player type */
	public static int playerType;
	/** Current player */
	public static Actor player;
	/** Return to state */
	public static int returnState;
	/** The current scenario Node */ 
	public static Node node;
	/** Tells whether the current node has changed */
	public static boolean nodeHasChanged;
	
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
	
	public static PhysicalEntity getEntityFromCurrentNode() {
		String entityID = Globals.node.getEntityToDisplay();
		
		PhysicalEntity entity = null;
		
		Dimension d = nodes.poll();
		if(entityID.equals("homer")) {
			entity = new HomerIA((int)d.getWidth(), (int)d.getHeight(), Globals.node);
		}
		else if(entityID.equals("mario")) {
			entity = new MarioIA((int)d.getWidth(), (int)d.getHeight(), Globals.node);
		}
		else if(entityID.equals("alien")) {
			entity = new AlienIA((int)d.getWidth(), (int)d.getHeight(), Globals.node);
		}
		else if(entityID.equals("crate")) {
			entity = new Crate(Conf.IMG_TEXTURES_PATH+"crate3.jpg", (int)d.getWidth(), (int)d.getHeight(), 40, 40, 500);
		}
		return entity;
	}
	
}
