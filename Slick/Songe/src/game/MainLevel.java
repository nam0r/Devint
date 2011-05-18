package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class MainLevel extends AbstractGameplayState {

	public MainLevel(int id) {
		super(id, "sky2.jpg", "niveau1.txt", 1f, 1.7f);
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
	throws SlickException {
		super.enter(gc, sbg);
	}

}
