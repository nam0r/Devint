package game;

import java.util.ArrayList;

import main.Songe;
import nodes.Node;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import sound.AlUtils;
import sound.Sound2;
import utils.Conf;
import utils.Globals;
import utils.Utils;
import actors.IA;
import actors.PhysicalEntity;

/**
 * This class manages the main game
 */

public class GameplayState extends AbstractGameState {
	/**
	 * Useful parameters to consider the background more or less far and
	 * therefore moving
	 */
	public static final float BACKPAR = 1f;
	/**
	 * Useful parameters to consider the background more or less far and
	 * therefore moving
	 */
	public static final float BACKPAR2 = 1.7f;
	/** The input */
	protected Input input;

	/** Game's states */
	protected enum States {
		IN_GAME, PAUSE, HIGHSCORE, GAME_OVER
	}

	/** The current state from game's states */
	protected States currentState;
	/** For vocalize SIVOX */
	protected t2s.SIVOXDevint voix;
	/** The sound when jumping, a long one during the whole jump */
	protected Sound2 soundJump;
	/** The sound when jumping, a short one when beginning to jump */
	protected Sound2 soundJump2;
	/** The sound when walking */
	protected Sound2 soundWalk;
	/** The sound when bumping */
	protected Sound2 soundBump;
	/**
	 * Indicates if the bump sound has been played since the last move and so
	 * should or not be replayed in the current frame
	 */
	protected boolean bumpWallPlayed, bumpTopPlayed;
	/**
	 * Contains the x and y coordinate of the Actor when the bump sound has been
	 * played, if this changes the sound could again be playable by setting
	 * bumpPlayed to false
	 */
	protected float bumpWallX, bumpTopX, bumpTopY;
	/**
	 * Indicates if soundWalk has been stopped, is useful because Sound's
	 * playing() method doesn't always work very well
	 */
	protected boolean soundWalkPlaying;
	/** Indicates if jump sound is still playing */
	protected boolean soundJumpPlaying;
	/** The font we're going to use to render the score */
	private Font font;

	/** Indicates if things to do once have been done */
	protected boolean onceOnEnter;
	
	public GameplayState(int id) {
		super(id, Conf.IMG_TEXTURES_PATH + "sky2.jpg", Conf.RESS_PATH
				+ "tiles.xml", Conf.RESS_PATH + "niveau1.txt", Conf.TILE_WIDTH,
				Conf.TILE_HEIGHT, BACKPAR, BACKPAR2);
		bumpWallPlayed = false;
		bumpTopPlayed = false;
		bumpWallX = 0;
		bumpTopX = 0;
		bumpTopY = 0;
		onceOnEnter = false;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.init(gc, sbg);
		input = gc.getInput();
		soundJump = new Sound2(Conf.SND_BIP_PATH + "bip6.ogg");
		soundJump2 = new Sound2(Conf.SND_DEPLACEMENT_PATH + "saut.ogg");
		soundBump = new Sound2(Conf.SND_DEPLACEMENT_PATH + "bump.ogg");
		
		font = new AngelCodeFont(Conf.RESS_PATH + "hiero.fnt", Conf.RESS_PATH
				+ "hiero.png");
		// We set Open Al constants about physical world
		AL10.alDopplerFactor(1.0f); // Doppler effect
		AL10.alDopplerVelocity(1.0f); // Sound speed
		AL10.alDistanceModel(AL11.AL_EXPONENT_DISTANCE);
		AL10.alDopplerFactor(50.0f);

	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g)
			throws SlickException {
		super.render(gc, sbg, g);
		font.drawString(4 * gc.getWidth() / 5, 30, "" + Globals.score);
		Utils.drawCenteredString(g,
				"Cursors - Move   Ctrl - Jump   B - Show Bounds   R - Restart",
				gc.getWidth(), gc.getHeight() - 20, Color.black);

		/*for (int i = 0; i < map.getWorld().getBodies().size(); i++) {
			if (map.getEntityByBody(map.getWorld().getBodies().get(i)) instanceof HomerIA) {
				sound.setSourcePosition((map.getEntityByBody(
						map.getWorld().getBodies().get(i)).getX() - map
						.getEntityByBody(map.getWorld().getBodies().get(i))
						.getWidth() / 2), (map.getEntityByBody(
						map.getWorld().getBodies().get(i)).getY() - map
						.getEntityByBody(map.getWorld().getBodies().get(i))
						.getHeight() / 2), 0f);
			}
		}*/
		// We put the openAl listener's position and velocity
		AlUtils.setAlListenerPosition(Globals.player.getX() - Globals.player.getWidth() / 2,
				Globals.player.getVelY() - Globals.player.getHeight() / 2, 0.0f);
		AlUtils.setAlListenerVelocity(Globals.player.getVelX() * 5, -Globals.player.getVelY(),
				0.0f);
		// sound.setSourceVelocity(10f, 0f, 0f, soundIndex);
		// AlUtils.resetAlListener();
		if (AL10.alGetError() != AL10.AL_NO_ERROR)
			System.out.println("Erreur d'OpenAL" + AL10.alGetError());

		// Environment sounds
		if(soundWalk != null)
			soundWalk();
		soundBump();
		soundGround();
		soundJump();
		
	}

	private void soundWalk() {
		// if the player is moving and not jumping we play the walk sound
		if (Globals.player.moving() && !Globals.player.jumping() && !Globals.player.falling()) {
			// if the sound is still playing we let it play
			if (!soundWalk.playing()) {
				soundWalk.loop();
			}
			// We modulate the sound speed depending on the speed of movement of
			// the character
			float pitchVel = 0;
			if (Globals.player.facingRight()) {
				pitchVel = 0.5f + 1 / (1 / (Globals.player.getVelX() / Globals.player.stepRate()));
				// System.out.println(pitchVel+" lol");
			} else {
				pitchVel = 0.5f + -1 / (1 / (Globals.player.getVelX() / Globals.player.stepRate()));
				// System.out.println(pitchVel+" lool");
			}
			// for security
			if (pitchVel > 10)
				pitchVel = 10;
			if (pitchVel < 0.001)
				pitchVel = 0.001f;
			soundWalk.setPitch(pitchVel);
		}
		// we stop the sound because the character is no more walking
		else {
			soundWalk.stop();
		}
	}

	private void soundJump() {
		// if the player is jumping or falling we play the jump sound
		if (Globals.player.jumping() || Globals.player.falling()) {
			// if the sound is still playing we let it play
			if (!soundJump.playing()) {
				soundJump.loop(1f, 0.5f, Globals.player.getX() - Globals.player.getWidth() / 2,
						Globals.player.getVelY() - Globals.player.getHeight() / 2, 0.0f);
				soundJumpPlaying = true;
			} else {
				soundJump.setSourcePosition(Globals.player.getX() - Globals.player.getWidth()
						/ 2, Globals.player.getVelY() - Globals.player.getHeight() / 2, 0.0f);
			}
			// We modulate the sound pitch depending on the y speed of movement
			// of the character
			float pitchVel = 0;
			pitchVel = 0.1f + Globals.player.getVelY() / 120f;
			// System.out.println(pitchVel+" lol");
			// because the y velocity can be positive or negative depending on
			// falling or jumping
			if (pitchVel < 0)
				pitchVel = -pitchVel;
			// for security
			if (pitchVel > 10)
				pitchVel = 10;
			if (pitchVel < 0.0001)
				pitchVel = 0.0001f;
			soundJump.setPitch(pitchVel);
		}
		// we stop the sound because the character is no more jumping
		else {
			soundJump.stop();
		}
	}

	private void soundBump() {
		// we check if the sound should be replayed
		if (bumpWallPlayed || bumpTopPlayed) {
			// if the x position has really changed (3 pixels) and the player is
			// no more facing to wall (useful to avoid problem with pushing
			// crates), the bump can again be played
			if (((Globals.player.getX() - bumpWallX) > 3 || (Globals.player.getX() - bumpWallX) < -3)
					&& !Globals.player.isTotallyFacingToWall()) {
				bumpWallPlayed = false;
			}
			if (((Globals.player.getY() - bumpTopY) > 3
					|| (Globals.player.getY() - bumpTopY) < -3
					|| (Globals.player.getX() - bumpTopX) > 3 || (Globals.player.getX() - bumpTopX) < -3)) {
				bumpTopPlayed = false;
			}
		}
		// If the player is facing to a wall
		if (Globals.player.isFacingToWall()) {
			// to have the sound from the right or the left depending on the
			// position of the wall and the player
			int decal = 0;
			if (Globals.player.facingRight())
				decal = 1;
			else
				decal = -1;
			// If the sound should be replayed, it will
			if (!bumpWallPlayed) {
				soundBump.playAt(1.5f, 5f, Globals.player.getX() - Globals.player.getWidth()
						/ 2 + decal, Globals.player.getVelY() - Globals.player.getHeight() / 2,
						0.0f);
				bumpWallPlayed = true;
				bumpWallX = Globals.player.getX();
			} else {
				soundBump.setSourcePosition(Globals.player.getX() - Globals.player.getWidth()
						/ 2 + decal, Globals.player.getVelY() - Globals.player.getHeight() / 2,
						0.0f);
			}
		}
		// If the player is knocking his head somewhere when jumping
		else if (Globals.player.isTopCollided() && Globals.player.jumping()) {
			// If the sound should be replayed, it will
			if (!bumpTopPlayed) {
				soundBump.playAt(1.5f, 1f, Globals.player.getX() - Globals.player.getWidth()
						/ 2, Globals.player.getVelY() - Globals.player.getHeight() / 2, 0.0f);
				bumpTopPlayed = true;
				bumpTopX = Globals.player.getX();
				bumpTopY = Globals.player.getY();
			}
		}
	}

	private void soundGround() {

	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		super.update(gc, sbg, delta);
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.enter(gc, sbg);
		currentState = States.IN_GAME;
		
		// If the "main" previous state was not the game state, then it's
		// probably the menu state
		if (Globals.returnState != stateID) {
			
			Globals.stateToGoTo.clear();
			Globals.nodes.clear();
			
			createMap();
			
			LoadingList.setDeferredLoading(false);
			soundWalk = new Sound2(Globals.player.getSoundWalk());
			LoadingList.setDeferredLoading(true);
			
			Globals.score = 0;
			map.setMainPlayer(Globals.player);
			Globals.player.reinitPosition();
			voix = new t2s.SIVOXDevint();
			
			Globals.nodeHasChanged = true;
			
		}
		
		if(Globals.nodeHasChanged) {
			//map.addEntity(Globals.getEntityFromCurrentNode());
			Node nodeStart = new Node(1);
			map.addEntity(Globals.getEntityFromString("homer", nodeStart));
			Globals.nodeHasChanged = false;
		}
		
		// this state is important so we put it in Globals
		Globals.returnState = stateID;
		
		AL10.alDopplerFactor(1.0f);
		//we execute enter methods for all the IA
		for (int i = 0; i < map.getWorld().getBodies().size(); i++) {
			if (map.getEntityByBody(map.getWorld().getBodies().get(i)) instanceof IA) {
				((IA) map.getEntityByBody(map.getWorld().getBodies().get(i))).enter();
			}
		}
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);
		// If coming in game again, the player will be moved
		Globals.player.setPosition(Globals.player.getX() + 200, Globals.player.getY() - 100);
		AlUtils.stopAllSounds();
	}

	@Override
	protected void notTimedEvents(GameContainer gc, StateBasedGame sbg,
			int delta) {

		if (input.isKeyPressed(Input.KEY_R)) {
			try {
				Globals.returnState = -1;
				enter(gc, sbg);
			} catch (SlickException e) {
				System.err.println("Erreur lors du relancement du jeu");
			}
			return;
		}
		if (input.isKeyPressed(Input.KEY_B)) {
			map.showBounds();
		}
		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			currentState = States.GAME_OVER;
		}
		if (input.isKeyPressed(Input.KEY_P)) {
			currentState = States.PAUSE;
		}
		if (input.isKeyPressed(Input.KEY_F3)) {
			voix.stop();
			voix.playShortText("Vous avez "+Globals.score+" points.");
		}

		if (input.isKeyPressed(Input.KEY_F4)) {
			map.addEntity(new HomerIA(100,100,new Node(1)));
		}
		if (input.isKeyPressed(Input.KEY_F5)) {
			map.addEntity(new Enemy(Conf.IMG_SPRITES_PATH+"mariowalk_big.png", 3, 100, 100, 40, 62, 2));
		}
		// determines if the character moves
		Globals.player.setMoving(false);
		if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT)) {
			Globals.player.setMoving(true);
		}
		if (input.isKeyPressed(Input.KEY_F1)) {
			// jouer un son : l'aide
		}
		if (input.isKeyPressed(Input.KEY_F2)) {
			// jouer un son : « tu es un tux qui doit trouver le lamasticot »
		}
	}

	@Override
	protected void timedEvents(GameContainer gc, StateBasedGame sbg, int delta) {
		Input input = gc.getInput();

		if (input.isKeyDown(Input.KEY_LEFT)) {
			Globals.player.moveLeft();
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			Globals.player.moveRight();
		}
		if ((input.isKeyPressed(Input.KEY_LCONTROL))
					|| (input.isKeyPressed(Input.KEY_RCONTROL))
					|| (input.isKeyPressed(Input.KEY_UP))
					|| (input.isKeyPressed(Input.KEY_SPACE))) {
				Globals.player.jump();
				// soundJump2.play();
		}
		// useful to have longer jumps my maintaining CTRL
		if (!input.isKeyDown(Input.KEY_LCONTROL)
				&& !input.isKeyDown(Input.KEY_UP)
				&& !input.isKeyDown(Input.KEY_SPACE)) {
			if (Globals.player.jumping()) {
				Globals.player.setVelocity(Globals.player.getVelX(), Globals.player.getVelY() * 0.95f);
			}
		}
	}

	@Override
	protected ArrayList<PhysicalEntity> createEntities() throws SlickException {
		/*
		ArrayList<PhysicalEntity> entities = new ArrayList<PhysicalEntity>();

		entities.add(new AlienIA(920, 350, new Node(1)));
		entities.add(new HomerIA(2800, 350, new Node(1)));
		entities.add(new AlienIA(3600, 350, new Node(1)));
		
		
		return entities;
		*/
		return null;
	}

	@Override
	protected void statesManagement(GameContainer gc, StateBasedGame sbg,
			int delta) {
		switch (currentState) {
		case IN_GAME:
			break;
		case PAUSE:
			if (gc.isPaused()) {
				gc.resume();
			} else {
				gc.pause();
			}
			break;
		case GAME_OVER:
			sbg.enterState(Songe.MAINMENUSTATE, new FadeOutTransition(
					Color.black), new FadeInTransition(Color.black));
			break;
		}
	}

	/***************** Collision management ********************/
	
	@Override
	protected void collisions(IA ia) {
		//System.out.println("Collision avec " + ia);
		
		ia.onCollision();
		if(!ia.isVisited() && Globals.node.equals(ia.getNode())){
			map.addEntity(Globals.getEntityFromString("spirit", new Node(3)));
			ia.setVisited(true);
		}
	}
	
	@Override
	protected void collisions(Emitter entity){
		entity.onCollision();
		//spirit type objects disappear when touched
		if(entity.getType().equals("spirit")){
			map.removeEntity(entity);
			map.addEntity(Globals.getEntityFromString("spirit", new Node(3)));
		}
	}
	
	@Override
	protected void collisions(Enemy enemy){
		enemy.onCollision();
		
		
	}

}
