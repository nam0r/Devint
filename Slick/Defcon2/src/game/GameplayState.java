package game;

import java.util.ArrayList;

import main.Hoorah;

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
		super(id, "res/blanc.png", "res/sols.xml", "res/testmap.txt", 64, 64);
	}

	@Override
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		super.init(gc, sbg);
		
		musique = new Music("../Slick/snd/requiem.wav");
		sonSaut = new Sound("res/snd/over.wav");
		
	}
	
	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		super.render(gc, sbg, g);
		
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

		musique.loop();
		
		currentState = States.IN_GAME;
	}
	
	// Appelee lors de la sortie de l'etat
	@Override
	public void leave(GameContainer gc, StateBasedGame sb) throws SlickException {
		super.leave(gc, sb);

		musique.stop();
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
		//return new Alien();
		return new Homer();
	}
	

	@Override
	protected ArrayList<PhysicalEntity> createEntities() {
		ArrayList<PhysicalEntity> entities = new ArrayList<PhysicalEntity>();
		entities.add(new Crate(460,100, 100,100,10));
		entities.add(new Crate(850,40, 70,70,5));
		entities.add(new Crate(855,-10, 70,70,5));
		entities.add(new Crate(845,100, 70,70,5));
		
		entities.add(new MarioIA(500,250));
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
	