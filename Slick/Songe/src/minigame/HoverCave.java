package minigame;

import java.awt.Dimension;
import java.io.IOException;
import java.util.Vector;

import main.Songe;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.particles.ConfigurableEmitter;
import org.newdawn.slick.particles.ParticleIO;
import org.newdawn.slick.particles.ParticleSystem;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import sound.AlUtils;
import sound.Sound2;
import utils.Conf;
import utils.Globals;

/**
 * Mini-game : spacecraft falling in a cave
 * 
 * @author namor
 * @author guodman
 */
public class HoverCave extends BasicGameState {
	private int stateID;
	
	/** The minimum width between the 2 walls */
	private final int MIN_WIDTH = 60 ;
	/** The resolution of the wall */
	private final int WALL_RES = 30;
	/** the sensitivity of the object to touch the wall */
	private final int SENSITIVITY = 10;
	private GameContainer container;
	/** The object's y position */
	private float dudeHeight;
	/** The object's x position */
	private float dudeWidth;
	private Dimension dudeSize;
	private boolean movingUp;
	private Vector<Integer> upperWall;
	private Vector<Integer> lowerWall;
	private float wallOffset;
	private boolean dead;
	private double speed;
	private int distance;
	private Sound2 sonG, sonD;
	private float distSonHaut, distSonBas;
	/** The explanation sound when entering to this state */
	private Sound2 enterSound;
	/** For vocalize SIVOX */
	protected t2s.SIVOXDevint voix;
	/** Indicates if we begin the game */
	private boolean playTheGame;
	/** The actual upper wall exact height (just over the player) */
	private int actualUpperWall;
	/** The actual lower wall exact height (just under the player) */
	private int actualLowerWall;
	/** The smoke trail particle system */
	private ParticleSystem trail;
	/** The explosion particle system */
	private ParticleSystem explosion;
	/** The space x translation */
	private float spX;
	/** The current wall number */
	private int currentWallNo;

	public HoverCave(int stateID) {
		this.stateID = stateID;
	}
	
	public int getID() {
		return stateID;
	}

	private void popWall() {
		upperWall.remove(0);
		lowerWall.remove(0);
	}

	private void addToWall() {
		// TODO Do some of these numbers need to be tweaked?
		int offset1 = (int) (Math.random() * 50 - 20);
		int offset2 = (int) (Math.random() * 50 - 30);
		int nextUpper = upperWall.get(upperWall.size() - 1) + offset1;
		int nextLower = lowerWall.get(lowerWall.size() - 1) + offset2;
		if (nextUpper >= container.getHeight() - MIN_WIDTH) {
			nextUpper = container.getHeight() - MIN_WIDTH;
		}
		if (nextUpper < 0) {
			nextUpper = 0;
		}
		if (nextLower <= MIN_WIDTH) {
			nextLower = MIN_WIDTH;
		}
		if (nextLower > container.getHeight()) {
			nextLower = container.getHeight();
		}
		// TODO fixme: this behaves a little bit strange, but maybe it's okay
		// for now.
		if (nextLower - nextUpper <= MIN_WIDTH) {
			nextLower += nextLower - (nextUpper - MIN_WIDTH);
			nextUpper -= nextLower - (nextUpper - MIN_WIDTH);
		}
		upperWall.add(nextUpper);
		lowerWall.add(nextLower);
	}

	public void reset() throws SlickException {
		upperWall = new Vector<Integer>();
		lowerWall = new Vector<Integer>();
		upperWall.add(0);
		lowerWall.add(container.getHeight());
		for (int i = 0; i < container.getWidth() / WALL_RES; i++) {
			addToWall();
		}
		dudeHeight = container.getHeight() / 2;
		currentWallNo = 10;
		dudeWidth = WALL_RES*(currentWallNo-1) + dudeSize.width/2;
		wallOffset = 0;
		dead = false;
		speed = 0.06;
		distance = 0;
		movingUp = false;
		playTheGame = false;
		actualUpperWall = 0;
		actualLowerWall = 0;
		spX = 0;
		try {
			explosion = ParticleIO.loadConfiguredSystem(Conf.EMITTERS_PATH+"explosion.xml");
			trail = ParticleIO.loadConfiguredSystem(Conf.EMITTERS_PATH+"smoketrail.xml");
			
		} catch (IOException e) {
			throw new SlickException("Failed to load particle systems", e);
		}
	}

	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		sonG = new Sound2(Conf.SND_BIP_PATH+"bip5.ogg");
		sonD = new Sound2(Conf.SND_BIP_PATH+"bip5.ogg");
		enterSound = new Sound2(Conf.getVoice("minijeuvaisseauF1"));
		this.container = container;
		dudeSize = new Dimension(20, 30);
		reset();
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		//If the beginning explanation is finished
		if(playTheGame){
			//if the player is not dead
			if (!dead) {
				if (movingUp) {
					dudeHeight -= ((double) delta) / 10.0;
				} else {
					dudeHeight += ((double) delta) / 10.0;
				}
				float coeff = (wallOffset-dudeSize.width/2)/WALL_RES;
				actualUpperWall = (int)(upperWall.get(currentWallNo-1) - coeff * (upperWall.get(currentWallNo) - upperWall.get(currentWallNo-1)));
				actualLowerWall = (int)(lowerWall.get(currentWallNo-1) - coeff * (lowerWall.get(currentWallNo) - lowerWall.get(currentWallNo-1)));

				// TODO The speed can be adjusted here
				wallOffset -= (float) delta * speed;
				speed += ((double) delta / 1000000000.0) * 2000;
				if (wallOffset <= -WALL_RES) {
					wallOffset += WALL_RES;
					popWall();
					addToWall();
				}
				distance += delta;
				// detect collisions
				// TODO Improve collision detection to find the edge of the box
				// against the edge of the cave.
				if ((dudeHeight + SENSITIVITY) > actualLowerWall
						|| (dudeHeight - SENSITIVITY) < actualUpperWall) {
					dead = true;
					//voix.playText("Le je est terminé, votre score est de "+ distance/1000);
				}
				//the start explosion
				explosion.update(delta*2);
				//the smoke trail
				trail.update(delta);
			}
		
			// If we are dead
			else{
				sonD.stop(1);
				sonG.stop();
					
				// if we are in main game
				if(Globals.returnState != Songe.MAINMENUSTATE){
					//The score is set
					Globals.score += distance/1000;
					Globals.nextEvent(sbg);
				}
				// if playing from the stand-alone version
				else{	
					sbg.enterState(Globals.returnState, new FadeOutTransition(Color.black),
						new FadeInTransition(Color.black));
				}
					
			}
		}
		//if not yet started to play
		else {
			sonG.setVolume(0f, 0);
			sonD.setVolume(0f, 1);
			if(input.isKeyPressed(Input.KEY_UP)){
				playTheGame = true;
				sonG.setVolume(600f, 0);
				sonD.setVolume(600f, 1);
				
				//sonG.setPitch(0.8f, 0);
				//sonD.setPitch(0.8f, 1);
				enterSound.stop();
			}
			else if(input.isKeyPressed(Input.KEY_F1)){
				enterSound.stop();
				enterSound.play();
			}
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(Globals.returnState, new FadeOutTransition(
					Color.black), new FadeInTransition(Color.black));
		}
		spX -= delta * 4.0f * speed;
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		// TODO Graphics?
		g.drawString("Distance: " + distance, 10, 25);
		g.drawString(
				"Speed: " + ((double) ((int) (speed * 10000.0))) / 10000.0, 10,
				40);
		
		//all the graphics below will be affected by the translation
		g.translate(spX, 0);
		
		//The smoke
		((ConfigurableEmitter) trail.getEmitter(0)).setPosition(dudeWidth - dudeSize.width/2 - spX, dudeHeight);
		trail.render();
		//the death explosion
		((ConfigurableEmitter) explosion.getEmitter(0)).setPosition(dudeWidth - dudeSize.width/2 - spX, dudeHeight);
		((ConfigurableEmitter) explosion.getEmitter(1)).setPosition(dudeWidth - dudeSize.width/2 - spX, dudeHeight);
		explosion.render();
		
		//the walls
		//upper wall
		for (int i = 0; i < upperWall.size() - 1; i++) {
			for(int j=0; j<5; j++){
				g.drawLine(i * WALL_RES + wallOffset - spX, upperWall.get(i) - j, (i + 1)
					* WALL_RES + wallOffset - spX, upperWall.get(i + 1) - j);
			}
		}
		//lower wall
		for (int i = 0; i < lowerWall.size() - 1; i++) {
			for(int j=0; j<5; j++){
				g.drawLine(i * WALL_RES + wallOffset - spX, lowerWall.get(i) + j, (i + 1)
					* WALL_RES + wallOffset - spX, lowerWall.get(i + 1) + j);
			}
		}
		//The object
		g.fillRect(dudeWidth - dudeSize.width/2 - spX, (int) dudeHeight - dudeSize.height / 2,
				dudeSize.width, dudeSize.height);
		
		//if(dead) g.drawString("Le jeu est terminé, appuyez sur Entrée pour continuer", 250, 150);
		
		//Sounds
		AlUtils.setAlListenerPosition((float)(WALL_RES + dudeSize.width/2), dudeHeight, 0f);
		
		if (movingUp)
			AlUtils.setAlListenerVelocity((float)(WALL_RES + dudeSize.width/2), -20, 0f);
		else 
			AlUtils.setAlListenerVelocity((float)(WALL_RES + dudeSize.width/2), 20, 0f);
		enterSound.setSourcePosition((float)(WALL_RES + dudeSize.width/2), dudeHeight, 0f, 0);
		sonG.setSourcePosition((float)(WALL_RES + dudeSize.width/2), actualUpperWall, 0f, 0);
		sonD.setSourcePosition((float)(WALL_RES + dudeSize.width/2), actualLowerWall, 0f, 1);
		sonG.setSourceVelocity((float)(WALL_RES + dudeSize.width/2), upperWall.get(currentWallNo-1)-upperWall.get(currentWallNo), 0f, 0);
		sonD.setSourceVelocity((float)(WALL_RES + dudeSize.width/2), lowerWall.get(currentWallNo)-lowerWall.get(currentWallNo-1), 0f, 1);

		distSonBas = (float) (1.0 / ((actualLowerWall - dudeHeight) / 50.0));
		distSonHaut = (float) (1.0 / ((dudeHeight - actualUpperWall) / 50.0));
		sonG.setPitch(distSonHaut);
		sonD.setPitch(distSonBas, 1);
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		reset();
		AlUtils.stopAllSounds();
		AlUtils.resetAlListener();
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg)
	throws SlickException {
		//The listener is reset
		AlUtils.resetAlListener();
		//the orientation is changed
		AlUtils.setAlListenerOrientation(0.0f, 0.0f, -1.0f,  -1.0f, 0.0f, 0.0f);
		//we set sound context
		AL10.alDistanceModel(AL11.AL_EXPONENT_DISTANCE);
		AL10.alDopplerFactor(0.8f);
		//We play the two sounds since we enter
		sonG.loop(1f, 1f, 0f, 0f, 0f);
		sonD.loop(1f, 1f, 0f, 0f, 0f);
		AL10.alSourcef(sonG.getIndex(), AL10.AL_ROLLOFF_FACTOR, 1.5f);
		AL10.alSourcef(sonD.getIndex(), AL10.AL_ROLLOFF_FACTOR,1.5f);
		//We play the beginning explanation sound
		enterSound.play();
	}

	public void keyPressed(int key, char c) {
		if (key == Input.KEY_UP) {
			movingUp = true;
		}
	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_UP) {
			movingUp = false;
		}
	}
}
