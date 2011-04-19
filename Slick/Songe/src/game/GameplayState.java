package game;

import java.util.ArrayList;

import main.Hoorah;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import sound.AlUtils;
import sound.Sound2;
import utils.Conf;
import utils.Globals;
import utils.Utils;
import actors.Actor;
import actors.IA;
import actors.PhysicalEntity;

public class GameplayState extends AbstractGameState {
	/** Useful parameters to consider the background more or less far and therefore moving */
	public static final float BACKPAR = 1f;
	/** Useful parameters to consider the background more or less far and therefore moving */
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

	protected Sound2 sound;
	/** The sound to indicate an IA has already been visited */
	protected Sound2 alreadyVisited;
	
	
	public GameplayState(int id) {
		super(id, Conf.IMG_TEXTURES_PATH+"sky2.jpg", Conf.RESS_PATH+"tiles.xml", Conf.RESS_PATH+"niveau1.txt", Conf.TILE_WIDTH, Conf.TILE_HEIGHT, BACKPAR, BACKPAR2);
		bumpWallPlayed = false;
		bumpTopPlayed = false;
		bumpWallX = 0;
		bumpTopX=0;
		bumpTopY=0;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		super.init(gc, sbg);
		input = gc.getInput();
		voix = new t2s.SIVOXDevint();
		soundJump = new Sound2(Conf.SND_BIP_PATH+"bip6.ogg");
		soundJump2 = new Sound2(Conf.SND_DEPLACEMENT_PATH+"saut.ogg");
		sound = new Sound2(Conf.SND_ENVIRONEMENT_PATH+"nuit.ogg");
		soundWalk = new Sound2(Conf.SND_DEPLACEMENT_PATH+"wooden_stairs2.ogg");
		soundBump = new Sound2(Conf.SND_DEPLACEMENT_PATH+"bump.ogg");
		alreadyVisited = new Sound2(Conf.SND_VOIX_PATH+"deja_rencontres.ogg");
		font = new AngelCodeFont(Conf.RESS_PATH+"hiero.fnt", Conf.RESS_PATH+"hiero.png");
		//We set Open Al constants about physical world
		AL10.alDopplerFactor(1.0f); // Doppler effect
		AL10.alDopplerVelocity(1.0f); // Sound speed
		AL10.alDistanceModel(AL11.AL_EXPONENT_DISTANCE);
		AL10.alDopplerFactor(50.0f);
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		super.render(gc, sbg, g);
		font.drawString(4*gc.getWidth()/5, 30, ""+Globals.score);
		Utils.drawCenteredString(g,"Cursors - Move   Ctrl - Jump   B - Show Bounds   R - Restart", gc.getWidth(), gc.getHeight()-20, Color.black);
		
		for(int i=0; i<map.getWorld().getBodies().size(); i++){
			if (map.getEntityByBody(map.getWorld().getBodies().get(i)) instanceof HomerIA) {
				sound.setSourcePosition((map.getEntityByBody(map.getWorld().getBodies().get(i)).getX()
						- map.getEntityByBody(map.getWorld().getBodies().get(i)).getWidth() / 2),
						(map.getEntityByBody(map.getWorld().getBodies().get(i)).getY()
						- map.getEntityByBody(map.getWorld().getBodies().get(i)).getHeight() / 2),
				0f, false);
			}
		}
		//We put the openAl listener's position and velocity
		AlUtils.setAlListenerPosition(player.getX()-player.getWidth()/2, player.getVelY()-player.getHeight()/2, 0.0f);
		AlUtils.setAlListenerVelocity(player.getVelX()*5, -player.getVelY(), 0.0f);
		//sound.setSourceVelocity(10f, 0f, 0f, soundIndex);
		//AlUtils.resetAlListener();
		if (AL10.alGetError() != AL10.AL_NO_ERROR)
			System.out.println("Erreur d'OpenAL"+AL10.alGetError());
		
		//Environment sounds
		soundWalk();
		soundBump();
		soundGround();
		soundJump();
		alreadyVisited.setSourcePosition(player.getX() - player.getWidth()
				/ 2, player.getVelY() - player.getHeight() / 2, 0.0f,
				false);
		System.out.println("");
		for(int i=0; i<20; i++){
			if(isPlaying(i))
				System.out.println("111111111");
			else
				System.out.println("0");
		}
	}
	boolean isPlaying(int index) {
		int state = AL10.alGetSourcei(SoundStore.get().getSource(index), AL10.AL_SOURCE_STATE);
		return (state == AL10.AL_PLAYING);
	}
	
	private void soundWalk(){
		// if the player is moving and not jumping we play the walk sound
		if (player.moving() && !player.jumping() && !player.falling()) {
			//if the sound is still playing we let it play
			if (!soundWalk.playing()){
				soundWalk.loop();
				testSound();
				//soundWalkPlaying = true;
			}
			//We modulate the sound speed depending on the speed of movement of the character
			float pitchVel = 0;
			if(player.facingRight()) {
				pitchVel = 0.5f + 1/(1/(player.getVelX()/35f));
				//System.out.println(pitchVel+" lol");
			}
			else {
				pitchVel = 0.5f + -1/(1/(player.getVelX()/35f));
				//System.out.println(pitchVel+" lool");
			}
			//for security
			if(pitchVel > 10) pitchVel = 10;
			if(pitchVel < 0.001) pitchVel = 0.001f;
			soundWalk.setPitch(pitchVel, false);
		}
		//we stop the sound because the character is no more walking
		else{
			soundWalk.stop();
		}
	}
	
	private void soundJump(){
		// if the player is jumping or falling we play the jump sound
		if (player.jumping() || player.falling()) {
			//if the sound is still playing we let it play
			if (!soundJump.playing()){
				soundJump.loop(1f, 0.5f, player.getX()
						- player.getWidth() / 2, player.getVelY()
						- player.getHeight() / 2, 0.0f);
				soundJumpPlaying = true;
				testSound();
			}
			else {
				soundJump.setSourcePosition(player.getX() - player.getWidth()
					/ 2, player.getVelY() - player.getHeight() / 2, 0.0f,
					false);
			}
			//We modulate the sound pitch depending on the y speed of movement of the character
			float pitchVel = 0;
			pitchVel = 0.1f+player.getVelY()/120f;
			//System.out.println(pitchVel+" lol");
			//because the y velocity can be positive or negative depending on falling or jumping
			if(pitchVel < 0) pitchVel = -pitchVel;
			//for security
			if(pitchVel > 10) pitchVel = 10;
			if(pitchVel < 0.0001) pitchVel = 0.0001f;
			soundJump.setPitch(pitchVel, false);
		}
		//we stop the sound because the character is no more jumping
		else{
			soundJump.stop();
		}
	}
	
	private void soundBump(){
		//we check if the sound should be replayed
		if (bumpWallPlayed || bumpTopPlayed) {
			// if the x position has really changed (3 pixels) and the player is
			// no more facing to wall (useful to avoid problem with pushing
			// crates), the bump can again be played
			if (((player.getX() - bumpWallX) > 3 || (player.getX() - bumpWallX) < -3)
					&& !player.isTotallyFacingToWall()) {
				bumpWallPlayed = false;
			}
			if (((player.getY() - bumpTopY) > 3 || (player.getY() - bumpTopY) < -3 || (player.getX() - bumpTopX) > 3 || (player.getX() - bumpTopX) < -3)) {
				bumpTopPlayed = false;
			}
		}
		// If the player is facing to a wall
		if (player.isFacingToWall()) {
			// If the sound should be replayed, it will
			if (!bumpWallPlayed) {
				soundBump.playAt(1.5f, 1f, player.getX()
						- player.getWidth() / 2, player.getVelY()
						- player.getHeight() / 2, 0.0f);
				//AL10.alSourcef(soundBumpIndex, AL10.AL_GAIN , 100f);
				bumpWallPlayed = true;
				bumpWallX = player.getX();
				testSound();
			} else {
				soundBump.setSourcePosition(player.getX() - player.getWidth()
						/ 2, player.getVelY() - player.getHeight() / 2, 0.0f,
						false);
			}
		}
		// If the player is knocking his head somewhere when jumping
		else if (player.isTopCollided() && player.jumping()) {
			// If the sound should be replayed, it will
			if (!bumpTopPlayed) {
				soundBump.playAt(1.5f, 1f, player.getX()
						- player.getWidth() / 2, player.getVelY()
						- player.getHeight() / 2, 0.0f);
				bumpTopPlayed = true;
				bumpTopX = player.getX(); bumpTopY = player.getY();
				testSound();
			}
		}
	}
	
	public void testSound(){
		System.out.println("----------------------");
		System.out.println("jumpI "+soundJump.getIndex()+" jumpB "+soundJump.getBuffer());
		System.out.println("walkI "+soundWalk.getIndex()+" walkB "+soundWalk.getBuffer());
		System.out.println("bumpI "+soundBump.getIndex()+" bumpB "+soundBump.getBuffer());
		System.out.println("soundI "+sound.getIndex() + " soundB "+sound.getBuffer());
		System.out.println("alreadyVisitedI "+alreadyVisited.getIndex()+" alreadyVisitedI "+alreadyVisited.getBuffer());
		/*System.out.println("");
		System.out.println("jumpBuff "+soundJump.getID());
		System.out.println("walkBuff "+soundWalk.getID());
		System.out.println("bumpBuff "+soundBump.getID());
		System.out.println("soundBuff "+sound.getID());
		System.out.println("alreadyVisitedBuff "+alreadyVisited.getID());*/
	}
	
	private void soundGround(){
		
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		super.update(gc, sbg, delta);
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)	throws SlickException {
		super.enter(gc, sbg);
		restart();
		currentState = States.IN_GAME;
		// If the "main" previous state was not the game state, then it's probably the menu state
		if(Globals.returnState != stateID)
			superRestart(gc, sbg);
		//this state is important so we put it in Globals
		Globals.returnState = stateID;
		
		//AL10.alSourcePlay(soundIndex);
		sound.loop(1.0f, 1.0f, 1000000f, 0f, 0f);
		AL10.alSourcef(sound.getIndex(), AL10.AL_ROLLOFF_FACTOR, 2.45f);
		AL10.alSourcef(sound.getIndex(), AL10.AL_REFERENCE_DISTANCE, 35f);
		AL10.alSourcef(sound.getIndex(), AL10.AL_GAIN , 250f);
		//AL10.alSourcef(soundIndex, AL10.AL_MAX_DISTANCE, 50f);
		/*soundWalkIndex = soundWalk.play(10f,0f);
		soundJumpIndex = soundJump.play(10f,0f);
		soundBumpIndex = soundBump.play(10f,0f);*/
	}
	
	// Powerful restart, if we have previously been in the menu
	public void superRestart(GameContainer gc, StateBasedGame sbg) throws SlickException {
		super.init(gc, sbg);
		Globals.score = 0;
	}
	
	// Appelee lors de la sortie de l'etat
	@Override
	public void leave(GameContainer gc, StateBasedGame sb) throws SlickException {
		super.leave(gc, sb);
		//If comming in game again, the player will be moved
		player.setPosition(player.getX()+200, player.getY()-100);
		sound.stop();
		soundWalk.stop();
		soundJump.stop();
	}	
	
	@Override
	protected void notTimedEvents(GameContainer gc, StateBasedGame sbg, int delta) {
		
		if (input.isKeyPressed(Input.KEY_R)) {
			try {
				superRestart(gc, sbg);
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
			voix.playShortText("Vous avez "+Globals.score+" points.");
		}
		// Est-ce que le personnage bouge ?
		player.setMoving(false);
		if (input.isKeyDown(Input.KEY_LEFT) || input.isKeyDown(Input.KEY_RIGHT)) {
			player.setMoving(true);
		}
        if (input.isKeyPressed(Input.KEY_F1)){
            // jouer un son : l'aide
        }
        if (input.isKeyPressed(Input.KEY_F2)){
            // jouer un son : « tu es un tux qui doit trouver le lamasticot »
        }

	}

	@Override
	protected void timedEvents(GameContainer gc, StateBasedGame sbg, int delta) {
		Input input = gc.getInput();
		
		if (input.isKeyDown(Input.KEY_LEFT)) {
			player.moveLeft();
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			player.moveRight();
		}
		if (player.onGround()) {
			if ((input.isKeyPressed(Input.KEY_LCONTROL))
					|| (input.isKeyPressed(Input.KEY_RCONTROL))
					|| (input.isKeyPressed(Input.KEY_UP))
					|| (input.isKeyPressed(Input.KEY_SPACE))) {
				player.jump();
				//soundJump2.play();
			}
		}
		//useful to have longer jumps my maintaining CTRL
		if (!input.isKeyDown(Input.KEY_LCONTROL) && !input.isKeyDown(Input.KEY_UP) && !input.isKeyDown(Input.KEY_SPACE)) {
			if (player.jumping()) {
				player.setVelocity(player.getVelX(), player.getVelY() * 0.95f);
			}
		}
	}

	@Override
	protected Actor createPlayer() {
		/*switch(Globals.playerType){
		case 0:return new Homer();
		case 1:return new Alien();
		case 2:return new Tux();
		case 3:return new Mario();
		}
		return new Mario();*/
		return new Tux();
	}
	

	@Override
	protected ArrayList<PhysicalEntity> createEntities() throws SlickException{
		ArrayList<PhysicalEntity> entities = new ArrayList<PhysicalEntity>();
		
		entities.add(new MarioIA(920,350));
		entities.add(new HomerIA(2800,350));
		entities.add(new AlienIA(3600,350));
		return entities;
	}

	@Override
	protected void statesManagement(GameContainer gc, StateBasedGame sbg, int delta) {
		switch (currentState) {
		case IN_GAME:
			break;
		case PAUSE:
			if(gc.isPaused()) {
				gc.resume();
			}
			else {
				gc.pause();
			}
			break;
		case GAME_OVER:
			sbg.enterState(Hoorah.MAINMENUSTATE, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
			break;
		}
	}

	@Override
	protected void collisions(IA ia) {
		if(ia.isVisited()) {
			alreadyVisited.stop();
			alreadyVisited.play();
			alreadyVisited.setSourcePosition(player.getX() - player.getWidth()
					/ 2, player.getVelY() - player.getHeight() / 2, 0.0f,
					false);
		}
		
		else {
			if(Globals.node.getQuestion() == null && Globals.node.getGame() == null) {
				stateToGoTo = Hoorah.SAVEHIGHSCORE;
			}
			if(Globals.node.getQuestion() != null) {
				stateToGoTo = Hoorah.QUESTIONSTATE;
			}
			if(Globals.node.getGame() != null) {
				stateToGoTo = Globals.node.getGame().getId();
			}
			ia.onCollision();
		}
	}

}
	