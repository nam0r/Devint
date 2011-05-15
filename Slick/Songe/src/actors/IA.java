package actors;

import nodes.Node;

import org.lwjgl.openal.AL10;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.StateBasedGame;

import sound.Sound2;
import utils.Conf;
import utils.Globals;

public abstract class IA extends Actor {
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
	/** the walking timer */
	protected int walkingTimer;
	/** the walking total time */
	protected int walkingTime;
	/** indicates if the IA is visited */
	protected boolean visited;
	/** indicates if the IA has been far */
	protected boolean hasBeenFar;
	/** The sound to indicate an IA has already been visited */
	protected Sound2 alreadyVisited;
	/** The sound of the dialog */
	protected Sound2 soundDialog;
	/** A permanent sound from the IA */
	protected Sound2 sound;
	/** an y offset for the image */
	protected float yoffset;
	/** Indicates if the sprites have to be flipped */
	protected boolean flip;

	protected Node node;
	
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
	public IA(String pathToSpriteSheet, int nb_sprites, float yoffset, boolean flip, float x, float y,
			float width, float height, float mass, Node node) { // Node a remonter dans Actor
		super(pathToSpriteSheet, x, y, mass, width, height);
		
		this.node = node;
		
		this.nb_sprites = nb_sprites;
		this.yoffset = yoffset;
		this.flip = flip;
		walk = new SpriteSheet(image, (int) width, (int) height); // A revoir

		way = Way.LEFT;
		walkingTimer = 0;

		moveForce = 100;

		visited = false;
		walkingTime = 1000; // 1 sec
		hasBeenFar = true;
		LoadingList.setDeferredLoading(false);
		try {
			alreadyVisited = new Sound2(Conf.getVoice("deja_rencontres"));
			sound = new Sound2(Conf.SND_PERSOS_PATH + "nuit.ogg");
		} catch (SlickException e) {
			System.out.println("le son de alreadyvisited n'a pas pu être trouvé.");
		}
		LoadingList.setDeferredLoading(true);
		enter();
		
	}
	
	/**
	 * Executed when entering in the world
	 * Manages the sound of the IA
	 */
	public void enter(){
		sound.loop(1.0f, 1.0f, 1000000f, 0f, 0f);
		AL10.alSourcef(sound.getIndex(), AL10.AL_ROLLOFF_FACTOR, 2.45f);
		AL10.alSourcef(sound.getIndex(), AL10.AL_REFERENCE_DISTANCE, 35f);
		AL10.alSourcef(sound.getIndex(), AL10.AL_GAIN, 250f);
		System.out.println("iaaaaaaaaaaaaa");
	}
	
	/**
	 * Executed when leaving the game state
	 */
	public void leave(){
		
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
		if(flip) image = image.getFlippedCopy(true, false);

		// if we're facing the other direction, flip the sprite over
		if (facingRight()) {
			image = image.getFlippedCopy(true, false);
		}

		image.draw(getX() - width / 2, getY() - height / 2, width, height+yoffset);
		permanentSound();
	}
	
	/**
	 * Sets the position and the speed of the sound source of the IA
	 */
	public void permanentSound(){
		alreadyVisited.setSourcePosition(Globals.player.getX() - Globals.player.getWidth() / 2,
				Globals.player.getVelY() - Globals.player.getHeight() / 2, 0.0f);
		
		sound.setSourcePosition(getX() - getWidth() / 2, getY() - getHeight() / 2, 0f);
		sound.setSourceVelocity(getVelX(), getVelY(), 0f);
		
		if(soundDialog != null && soundDialog.playing()) {
			soundDialog.setSourcePosition(Globals.player.getX() - Globals.player.getWidth() / 2,
					Globals.player.getVelY() - Globals.player.getHeight() / 2, 0.0f);
		}
	}

	@Override
	public void update(int delta) {
		super.update(delta);

		// if is no more on the screen, then the IA is "far" from the main
		// character
		if (getX() < Globals.xoffset
				|| getX() > (Globals.xoffset + Globals.gcWidth)
				|| getY() < Globals.yoffset
				|| getY() > (Globals.yoffset + Globals.gcHeight)) {
			hasBeenFar = true;
		}
	}

	/**
	 * Makes additional actions when collision with the player
	 */
	public void onCollision(StateBasedGame sbg) {
		//si ia visitée
		if(visited){
			if(hasBeenFar()){
				alreadyVisited.stop();
				alreadyVisited.play();
				alreadyVisited.setSourcePosition(Globals.player.getX() - Globals.player.getWidth() / 2,
						Globals.player.getVelY() - Globals.player.getHeight() / 2, 0.0f);
				setHasBeenFar(false);
			}
			return;
		}
		System.out.println("Global.node "+Globals.node.getID());
		System.out.println("ia "+node.getID());
		// si noeud de l'ia même que le noeud courant (donc ia valide)
		if (Globals.node.equals(this.node)) {
			Globals.nextEvent(sbg);
		}
		//si ia invalide à ce moment
		else {
			if(hasBeenFar()){
				// TODO Play sound: "Tu dois aller voir 'l'autre' avant..."
				System.out.println("Totototototototototo");
				/*
				alreadyVisited.stop();
				alreadyVisited.play();
				alreadyVisited
						.setSourcePosition(Globals.player.getX() - Globals.player.getWidth() / 2,
								Globals.player.getVelY() - Globals.player.getHeight() / 2, 0.0f);
				*/
				setHasBeenFar(false);
			}
		}
	}

	/**
	 * Returns if the IA has already been visited by the player
	 * 
	 * @return visited if the IA has already been visited
	 */
	public boolean isVisited() {
		return visited;
	}
	
	public void setVisited(boolean visited){
		this.visited = visited;
	}

	public Node getNode(){
		return node;
	}
	
	/**
	 * Returns if the character has been far enough from an already visited IA
	 * 
	 * @return if the ia has been far from the character
	 */
	public boolean hasBeenFar() {
		return hasBeenFar;
	}

	/**
	 * Sets the hasBeenFar variable
	 * 
	 * @param hasBeenFar
	 *            if the IA has been far enough from the player
	 */
	public void setHasBeenFar(boolean hasBeenFar) {
		this.hasBeenFar = hasBeenFar;
	}
	
	

}
