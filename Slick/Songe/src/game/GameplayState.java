package game;

import java.util.ArrayList;

import main.Globals;
import main.Hoorah;
import game.AbstractGameState;

import org.lwjgl.openal.AL10;
import org.lwjgl.openal.AL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import sound.AlUtils;
import sound.Sound2;
import utils.Utils;
import actors.Actor;
import actors.PhysicalEntity;

public class GameplayState extends AbstractGameState {
	
	private enum States {
		IN_GAME, PAUSE, HIGHSCORE, GAME_OVER
	}
	private States currentState;
	
	private Music musique;
	private Sound2 sonSaut;
	
	private Sound2 sound;
	private int soundIndex;
	
	
	public GameplayState(int id) {
		super(id, "ressources/cave.png", "ressources/tiles.xml", "ressources/testmap.txt", 32, 32);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		super.init(gc, sbg);
		//musique = new Music("../Slick/snd/requiem.wav");
		sonSaut = new Sound2("ressources/sons/over.wav");
		sound = new Sound2("ressources/sons/piano.wav");
		restart();
		//We set Open Al constants about physical
		AL10.alDopplerFactor(1.0f); // Doppler effect
		AL10.alDopplerVelocity(1.0f); // Sound speed
		AL10.alDistanceModel(AL11.AL_EXPONENT_DISTANCE);
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		super.render(gc, sbg, g);
		g.drawString("Votre score : "+Globals.score, 4*gc.getWidth()/5, 30);
		Utils.drawCenteredString(g,"Cursors - Move   Ctrl - Jump   B - Show Bounds   R - Restart", gc.getWidth(), gc.getHeight()-20, Color.black);
		
		for(int i=0; i<map.getWorld().getBodies().size(); i++){
			if (map.getEntityByBody(map.getWorld().getBodies().get(i)) instanceof HomerIA) {
				sound.setSourcePosition((map.getEntityByBody(map.getWorld().getBodies().get(i)).getX()
						- map.getEntityByBody(map.getWorld().getBodies().get(i)).getWidth() / 2),
						(map.getEntityByBody(map.getWorld().getBodies().get(i)).getY()
						- map.getEntityByBody(map.getWorld().getBodies().get(i)).getHeight() / 2),
				0f, soundIndex);
			}
		}
		//We put the openAl listener's position and velocity
		AlUtils.setAlListenerPosition(player.getX()-player.getWidth()/2, player.getVelY()-player.getHeight()/2, 0.0f);
		AlUtils.setAlListenerVelocity(player.getVelX()*5, -player.getVelY(), 0.0f);
		//AlUtils.resetAlListener();
		if (AL10.alGetError() != AL10.AL_NO_ERROR)
			System.out.println("Errrrrrreur !!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}
	
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		super.update(gc, sbg, delta);
		
	}
	
	// Appelee lors de l'entree dans l'etat
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)	throws SlickException {
		super.enter(gc, sbg);
		restart();
		//musique.loop();		
		currentState = States.IN_GAME;
		// If the "main" previous state was not the game state, then it's probably the menu state
		if(Globals.returnState != stateID)
			superRestart(gc, sbg);
		//this state is important so we put it in Globals
		Globals.returnState = stateID;
		
		soundIndex = sound.playAt(1000000f, 0f, 0f);
		AL10.alSourcef(soundIndex, AL10.AL_ROLLOFF_FACTOR, 2.2f);
		AL10.alSourcef(soundIndex, AL10.AL_REFERENCE_DISTANCE, 35f);
		AL10.alSourcef(soundIndex, AL10.AL_GAIN , 40f);
		//AL10.alSourcef(soundIndex, AL10.AL_MAX_DISTANCE, 50f);
	}
	
	// Powerful restart, if we have previously been in the menu
	public void superRestart(GameContainer gc, StateBasedGame sbg) throws SlickException {
		//player = createPlayer();
		//restart();
		super.init(gc, sbg);
		Globals.score = 0;
	}
	
	// Appelee lors de la sortie de l'etat
	@Override
	public void leave(GameContainer gc, StateBasedGame sb) throws SlickException {
		super.leave(gc, sb);
		//musique.stop();
		//If comming in game again, the player will be moved
		player.setPosition(player.getX()+100, player.getY()-50);
		//sound.stop();
	}	
	
	@Override
	protected void notTimedEvents(GameContainer gc, StateBasedGame sbg, int delta) {
		
		Input input = gc.getInput();
		
		// Gestion des évènements clavier
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
			sbg.enterState(Hoorah.MAINMENUSTATE, new FadeOutTransition(Color.black),
					new FadeInTransition(Color.black));
		}
		if (input.isKeyPressed(Input.KEY_P)) {
			if(gc.isPaused()) {
				gc.resume();
			}
			else {
				gc.pause();
			}
		}
		
		//sound.loop();
		
		// Est-ce que le personnage bouge ?
		player.setMoving(false);
		if (input.isKeyDown(Input.KEY_LEFT)) {
			player.setMoving(true);
		}
		if (input.isKeyDown(Input.KEY_RIGHT)) {
			player.setMoving(true);
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
			if ((input.isKeyPressed(Input.KEY_LCONTROL)) || 
			   (input.isKeyPressed(Input.KEY_RCONTROL))) {
				player.jump();
				AL10.alSourcePlay(soundIndex);
			}
		}
		if (!input.isKeyDown(Input.KEY_LCONTROL)) {
			if (player.jumping()) {
				player.setVelocity(player.getVelX(), player.getVelY() * 0.95f);
			}
		}
	}

	@Override
	protected Actor createPlayer() {
		switch(Globals.playerType){
		case 0:return new Homer();
		case 1:return new Homer();
		case 2:return new Alien();
		case 3:return new Mario();
		case 4:return new Homer();
		}
		return new Mario();
		
	}
	

	@Override
	protected ArrayList<PhysicalEntity> createEntities() throws SlickException{
		ArrayList<PhysicalEntity> entities = new ArrayList<PhysicalEntity>();
		entities.add(new Crate(300,100, 60,60,10));
		entities.add(new Crate(550,40, 46,46,5));
		entities.add(new Crate(555,-10, 46,46,5));
		entities.add(new Crate(545,100, 46,46,5));
		
		entities.add(new MarioIA(400,150));
		entities.add(new HomerIA(900,150));
		entities.add(new AlienIA(1300,150));
		return entities;
	}

	@Override
	protected void statesManagement(GameContainer gc, StateBasedGame sbg, int delta) {
		switch (currentState) {
		case IN_GAME:
			break;
		case PAUSE:
			break;
		case GAME_OVER:
			sbg.enterState(Hoorah.MAINMENUSTATE);
			break;
		}
	}

}
	