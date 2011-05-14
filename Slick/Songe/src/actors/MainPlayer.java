package actors;

import nodes.Node;

public abstract class MainPlayer extends Actor {
	
	protected Node node;

	public MainPlayer(String pathToSpriteSheet, float x, float y, float mass,
			float width, float height, Node node) {
		super(pathToSpriteSheet, x, y, mass, width, height);
		
		this.node = node;
	}
	
	public Node getNode() {
		return this.node;
	}

}
