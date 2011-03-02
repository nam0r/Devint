package main;

import hoverCave.HoverCave;
import hoverCave.SaveHighScore;
import mainGame.InGameState;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * A simple platformer example that uses a combination of platformer style
 * dynamics and pure phys2d bodies by fudging the actor's physics to match those
 * expected.
 * 
 * This source is provided as a simple example where to get started for
 * platformer in Slick using Phys2D.
 * 
 * @author kevin
 */
public class Hoorah extends StateBasedGame {
	/** The container in which the game is running */
	private GameContainer container;
	/** True if vertical sync is turned on */
	private boolean vsync = true;

	public static final int MAINMENUSTATE = 0;
	public static final int GAMEPLAYSTATE = 1;
	public static final int HOVERCAVESTATE = 2;
	public static final int SAVEHIGHSCORE = 3;

	/**
	 * Create a new game
	 */
	public Hoorah() {
		super("My Last Hoorah");
	}
	
	/**
	 * @see org.newdawn.slick.state.StateBasedGame#initStatesList(org.newdawn.slick.GameContainer)
	 */
	public void initStatesList(GameContainer container) throws SlickException {
		this.container = container;

		container.setTargetFrameRate(100);
		container.setVSync(vsync);

		addState(new InGameState(GAMEPLAYSTATE));
		addState(new HoverCave(HOVERCAVESTATE));
		addState(new SaveHighScore(SAVEHIGHSCORE));
	}

	/**
	 * @see org.newdawn.slick.state.StateBasedGame#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);

		if (key == Input.KEY_F7) {
			vsync = !vsync;
			container.setVSync(vsync);
		}
	}
}
