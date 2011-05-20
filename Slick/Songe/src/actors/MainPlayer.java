package actors;

import utils.Globals;
import nodes.Node;

public abstract class MainPlayer extends Actor {

	protected int nodeID;
	
	public MainPlayer(int nodeID, String pathToSpriteSheet, float x, float y, float mass,
			float width, float height) {
		super(pathToSpriteSheet, x, y, mass, width, height);
		this.nodeID = nodeID;
		if(! Globals.hasAlreadyPlayed)
			Globals.node = new Node(10000);
		else
			Globals.node = new Node(nodeID);
	}
	
	public int getNodeId(){
		return nodeID;
	}

}
