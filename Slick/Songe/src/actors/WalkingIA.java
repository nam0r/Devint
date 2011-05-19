package actors;

import nodes.Node;

import org.newdawn.slick.Graphics;

public class WalkingIA extends IA {
	
	/**
	 * An IA in the game
	 * @param pathToSpriteSheet the path to walking spritesheet
	 * @param nb_sprites the number of images in that sprite
	 * @param x the x initial position
	 * @param y the y initial position
	 * @param width the width of the shape
	 * @param height the height of the shape
	 * @param mass the mass of the IA
	 * @param node the node of the IA
	 */
	public WalkingIA(String pathToSpriteSheet, int nb_sprites, float yoffset, boolean flip, float x, float y,
			float width, float height, float mass, Node node) { // Node a remonter dans Actor
		super(pathToSpriteSheet, nb_sprites, yoffset, flip, x, y, width, height, mass, node);
	}
	

	@Override
	public void render(Graphics g) {
		super.render(g);
	}
	

	@Override
	public void update(int delta) {
		super.update(delta);

		setMoving(true);

		walkingTimer += delta;

		if (walkingTimer > walkingTime) {
			walkingTimer = 0;
			way = (way == Way.LEFT) ? Way.RIGHT : Way.LEFT; // On change de sens
		}

		if (way == Way.LEFT)
			moveLeft();
		else
			moveRight();
	}
}
