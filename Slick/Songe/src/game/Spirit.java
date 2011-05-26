package game;


import net.phys2d.raw.shapes.DynamicShape;
import nodes.Node;

import org.lwjgl.openal.AL10;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.StateBasedGame;

import sound.Sound2;
import utils.Conf;
import utils.Globals;

/**
 * A spirit
 * 
 * @author namor
 */
public class Spirit extends Emitter {
	/** the node */
	protected Node node;
	/** A permanent sound from the Spirit */
	protected Sound2 sound;
	
	/**
	 * Create a new emitter
	 * @param emitterPath the path of the xml emitter file
	 * @param yOffset the y position offset of the emitter
	 * @param emitterNumber the number of emitters
	 * @param x The x position of the centre of the emitter
	 * @param y The y position fo the centre of the emitter
	 * @param mass The mass of the emitter
	 * @param shape the shape of the object
	 * @throws SlickException Indicates a failure to load the resources
	 */
	public Spirit(String emitterPath, float yOffset, int emitterNumber, float x, float y, float mass, DynamicShape shape, Node node) {
		super(emitterPath, "spirit", yOffset, emitterNumber, x, y, mass, shape);
		this.node = node;
		
		LoadingList.setDeferredLoading(false);
		try {
			sound = new Sound2(Conf.SND_PERSOS_PATH + node.getMainSound()+".ogg");
		} catch (SlickException e) {
			System.out.println("le son "+ Conf.SND_PERSOS_PATH + node.getMainSound()+".ogg" +" n'a pas pu être trouvé.");
			e.printStackTrace();
		}
		LoadingList.setDeferredLoading(true);
	}
	
	/**
	 * Create a new emitter
	 * @param emitterPath the path of the xml emitter file
	 * @param yOffset the y position offset of the emitter
	 * @param emitterNumber the number of emitters
	 * @param x The x position of the centre of the emitter
	 * @param y The y position fo the centre of the emitter
	 * @param width The width of the emitter 
	 * @param height The height of the emitter
	 * @param mass The mass of the emitter
	 * @throws SlickException Indicates a failure to load the resources
	 */
	public Spirit(String emitterPath, float yOffset, int emitterNumber, float x, float y, float width, float height, float mass , Node node) {
		super(emitterPath, "spirit",yOffset, emitterNumber, x, y, width, height, mass);
		this.node = node;
		
		LoadingList.setDeferredLoading(false);
		try {
			sound = new Sound2(Conf.SND_PERSOS_PATH + node.getMainSound()+".ogg");
		} catch (SlickException e) {
			System.out.println("le son "+ Conf.SND_PERSOS_PATH + node.getMainSound()+".ogg" +" n'a pas pu être trouvé.");
			e.printStackTrace();
		}
		LoadingList.setDeferredLoading(true);
	}

	public void render(Graphics g) {
		super.render(g);
		
		sound.setSourcePosition(getX() - getWidth() / 2, getY() - getHeight() / 2, 0f);
		sound.setSourceVelocity(getVelX(), getVelY(), 0f);
	}

	public void update(int delta) {
		super.update(delta);
	}
	
	/**
	 * Makes additional actions when collision with the player
	 */
	@Override
	public void onCollision(StateBasedGame sbg) {
		if (Globals.node.equals(this.node)) {
			Globals.nextEvent(sbg);
		}
	}
	
	/**
	 * Executed when entering in the world
	 * Manages the sound of the Spirit
	 */
	public void enter(){
		sound.loop(1.0f, 1.0f, 1000000f, 0f, 0f);
		AL10.alSourcef(sound.getIndex(), AL10.AL_ROLLOFF_FACTOR, 2.45f);
		AL10.alSourcef(sound.getIndex(), AL10.AL_REFERENCE_DISTANCE, 35f);
		AL10.alSourcef(sound.getIndex(), AL10.AL_GAIN, 250f);
	}
	
	/**
	 * To stop the sound when the enemy is dead
	 */
	public void stopSound(){
		sound.stop();
	}
}
