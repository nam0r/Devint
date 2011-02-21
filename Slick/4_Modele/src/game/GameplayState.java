package game;
import main.MyGame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import entity.Entity;
 
public class GameplayState extends BasicGameState {
 
	private int stateID;
	
	private Entity background;
	private Music musique;
	private Sound son;
	
	private enum States {
		START, PAUSE, HIGHSCORE, GAME_OVER
	}
	private States currentState;
	
    public GameplayState(int stateID) {
    	this.stateID = stateID;
    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		background = new Entity("img/background.jpg");
		musique = new Music("snd/requiem.wav");
		son = new Sound("snd/piano.wav");
		
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gfx)
			throws SlickException {
		
		background.draw();
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		// Gestion des états
		switch (currentState) {
		case START:
			break;
		case HIGHSCORE:
			break;
		case PAUSE:
			break;
		case GAME_OVER:
			sbg.enterState(MyGame.MAINMENUSTATE);
			break;
		}
		
		// Gestion des evenements clavier
		Input input = gc.getInput();
		
		if(input.isKeyDown(Input.KEY_ESCAPE)) {
			sbg.enterState(MyGame.MAINMENUSTATE);
		}
		if(input.isKeyDown(Input.KEY_LEFT)) {
			son.playAt(-1, 0, 0);
		}
		if(input.isKeyDown(Input.KEY_RIGHT)) {
			son.playAt(1, 0, 0);
		}
		if(input.isKeyDown(Input.KEY_UP)) {
			son.playAt(0, 1, 0);
		}
		if(input.isKeyDown(Input.KEY_DOWN)) {
			son.playAt(0, -1, 0);
		}
		
	}

	@Override
	public int getID() {
		return this.stateID;
	}
	
	// Appelee lors de l'entree dans l'etat
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.enter(gc, sbg);

		musique.loop();
		
		currentState = States.START;
	}
	
	// Appelee lors de la sortie de l'etat
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);

		musique.stop();
	}

}