package minigame;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import sound.Sound2;
import utils.Globals;

public class CheckTonScore extends BasicGameState {
	private int stateID;
	private Sound2 gagne;


	public CheckTonScore(int stateID) {
		this.stateID = stateID;
	}
	
	public int getID() {
		return stateID;
	}



	@Override
	public void init(GameContainer container, StateBasedGame game) throws SlickException {
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		Input input = gc.getInput();
		if (input.isKeyPressed(Input.KEY_ENTER) || input.isKeyPressed(Input.KEY_ESCAPE)) {
			Globals.nextEvent(sbg);
		}
	}

	@Override
	public void render(GameContainer container, StateBasedGame game, Graphics g)
			throws SlickException {
		
	}

	@Override
	public void leave(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
	}
	
	public void enter(GameContainer gc, StateBasedGame sbg)
	throws SlickException {
		
	}
}
