package actors;

import org.newdawn.slick.SpriteSheet;

import utils.Globals;
import nodes.Node;

/**
 * An abstract class for the main player
 * 
 * @author namor
 * @author Afnarel
 */
public abstract class MainPlayer extends Actor {
	/** The player's node id */
	protected int nodeID;
	/** Walking sprite sheet */
	protected SpriteSheet walk;
	/** Jumping sprite sheet */
	protected SpriteSheet jump;
	
	public MainPlayer(int nodeID, String pathToSpriteSheet, float x, float y, float mass,
			float width, float height) {
		super(pathToSpriteSheet, x, y, mass, width, height);
		this.nodeID = nodeID;
		if(! Globals.hasAlreadyPlayed)
			Globals.node = new Node(10000);
		else
			Globals.node = new Node(nodeID);
	}
	
	/**
	 * Returns the player's node id
	 * @return nodeID
	 */
	public int getNodeId(){
		return nodeID;
	}
	
	/**
	 * Returns the walking sprite sheet
	 * @return walk
	 */
	public SpriteSheet getWalkSheet(){
		return walk;
	}
	
	public abstract float stepRate();

}
