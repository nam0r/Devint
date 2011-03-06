package game;

import java.util.ArrayList;

import main.Globals;
import main.Hoorah;
import game.AbstractGameState;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import utils.Utils;
import actors.Actor;
import actors.PhysicalEntity;

public class GameplayState extends AbstractGameState {
	
	private enum States {
		IN_GAME, PAUSE, HIGHSCORE, GAME_OVER
	}
	private States currentState;
	
	private Music musique;
	private Sound sonSaut;
	

	public GameplayState(int id) {
		super(id, "res/cave.png", "res/tiles.xml", "res/testmap.txt", 32, 32);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		super.init(gc, sbg);
		//musique = new Music("../Slick/snd/requiem.wav");
		sonSaut = new Sound("res/snd/over.wav");
		restart();
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		super.render(gc, sbg, g);
		g.drawString("Votre score : "+Globals.score, 4*gc.getWidth()/5, 30);
		Utils.drawCenteredString(g,"Cursors - Move   Ctrl - Jump   B - Show Bounds   R - Restart", gc.getWidth(), gc.getHeight()-20, Color.black);
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
		//clear events
		Input input = gc.getInput();
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
	}	
	
	@Override
	protected void notTimedEvents(GameContainer gc, StateBasedGame sbg, int delta) {
		
		Input input = gc.getInput();
		
		// Gestion des évènements clavier
		if (input.isKeyPressed(Input.KEY_R)) {
			try {
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
				sonSaut.play();
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
	protected ArrayList<PhysicalEntity> createEntities() {
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
	