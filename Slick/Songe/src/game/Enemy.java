package game;

import org.lwjgl.openal.AL10;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.loading.LoadingList;

import sound.Sound2;
import utils.Conf;
import utils.Globals;
import actors.Actor;

/**
 * A generic enemy
 * 
 * @author namor
 *
 */
public class Enemy extends Actor {
	/** the walk spritesheet */
	protected SpriteSheet walk;
	/** the amount of images in the walking sprite */
	protected int nb_sprites;
	/** the enum way */
	protected enum Way {
		RIGHT, LEFT
	};
	/**the way of the IA */
	protected Way way;
	/** A permanent sound from the IA */
	protected Sound2 sound;
	/** to do an action every a certain amount of frames */
	public int temp;

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
	public Enemy(String pathToSpriteSheet, int nb_sprites, float x, float y,
			float width, float height, float mass) {
		super(pathToSpriteSheet, x, y, mass, width, height);
		
		this.nb_sprites = nb_sprites;
		walk = new SpriteSheet(image, (int) width, (int) height); // A revoir

		way = Way.LEFT;

		moveForce = 400;
		body.setMaxVelocity(20, 120);
		//the sound is deferred according to the moment the map is created
		//this is useful to avoid deferred loading problems
		if(Globals.started)
			LoadingList.setDeferredLoading(false);
		try {
			sound = new Sound2(Conf.SND_PERSOS_PATH + "madgiggle.ogg");
		} catch (SlickException e) {
			System.out.println("le son de alreadyvisited n'" +
					"a pas pu être trouvé.");
			e.printStackTrace();
		}
		if(Globals.started){
			LoadingList.setDeferredLoading(true);
			//sound.loop();
		}
		
		//enter();
		temp = 0;
		
	}
	
	/**
	 * Executed when entering in the world
	 * Manages the sound of the IA
	 */
	public void enter(){
		sound.loop(1.0f, 1.0f, 1000000f, 0f, 0f);
		AL10.alSourcef(sound.getIndex(), AL10.AL_ROLLOFF_FACTOR, 2.45f);
		AL10.alSourcef(sound.getIndex(), AL10.AL_REFERENCE_DISTANCE, 35f);
		AL10.alSourcef(sound.getIndex(), AL10.AL_GAIN, 320f);
	}
	
	/**
	 * Executed when leaving the game state
	 */
	public void leave(){
		
	}
	
	/**
	 * To stop the sound when the enemy is dead
	 */
	public void stopSound(){
		sound.stop();
	}

	@Override
	public void render(Graphics g) {

		int sx = 0;
		int sy = 0;

		if (moving() && onGround()) {
			sx = frame % nb_sprites;
			sy = 0;
		} else if (onGround()) {
			sx = 0;
			sy = 0;
		}

		// get the appropriate sprite
		Image image = walk.getSprite(sx, sy);

		// if we're facing the other direction, flip the sprite over
		if (facingRight()) {
			image = image.getFlippedCopy(true, false);
		}

		image.draw(getX() - width / 2, getY() - height / 2, width, height);
		//permanentSound();
	}
	
	/**
	 * Sets the position and the speed of the sound source of the IA
	 */
	public void permanentSound(int i){
		sound.setSourcePosition(getX() - getWidth() / 2, getY() - getHeight() / 2, 0f, i);
		sound.setSourceVelocity(getVelX(), getVelY(), 0f, i);
	}

	@Override
	public void update(int delta) {
		super.update(delta);

		setMoving(true);
		
		// we do this every 5 frames to avoid strange effect of indecision
		if(temp>=5 && isFacingToWall()){
			way = (way == Way.LEFT) ? Way.RIGHT : Way.LEFT;
			temp = 0;
		}
		temp++;
		
		if (way == Way.LEFT)
			moveLeft();
		else
			moveRight();
	}

	/**
	 * Makes additional actions when collision with the player
	 */
	public void onCollision() {
		
	}

	/**
	 * returns the amount of images in for the walking sprites
	 * @return nb_sprites
	 */
	public int getSpriteNum(){
		return nb_sprites;
	}
}
