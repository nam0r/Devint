package main;

import game.GameplayState;
import menu.MainMenuState;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

public class Hoorah extends StateBasedGame {

	// Attributes
	private GameContainer container;
	private boolean vsync = true;
	
	// States
	public static final int MAINMENUSTATE = 0;
	public static final int GAMEPLAYSTATE = 1;
	
	public Hoorah() {
		super("My Last Hoorah");
		
		this.addState(new MainMenuState(MAINMENUSTATE));
		this.addState(new GameplayState(GAMEPLAYSTATE));
		this.enterState(MAINMENUSTATE);
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(MAINMENUSTATE).init(gc, this);
		this.getState(GAMEPLAYSTATE).init(gc, this);
		
		this.container = gc;
		
		// True if vertical sync is turned on
		container.setTargetFrameRate(100);
		container.setVSync(vsync);
	}
	
	// ===========================

	public void keyPressed(int key, char c) {
		super.keyPressed(key, c);
		
		if (key == Input.KEY_F7) {
			vsync = !vsync;
			container.setVSync(vsync);
		}
	}
}
