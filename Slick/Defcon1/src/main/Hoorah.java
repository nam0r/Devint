package main;

import java.io.IOException;

import game.GameplayState;
import game.LoadingState;
import minigame.HoverCave;
import game.SaveHighScore;
import menu.MainMenuState;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.StateBasedGame;

public class Hoorah extends StateBasedGame {

	// Attributes
	private GameContainer container;
	private boolean vsync = true;
	
	// States
	public static final int LOADINGSTATE = 0;
	public static final int MAINMENUSTATE = 1;
	public static final int GAMEPLAYSTATE = 2;
	public static final int HOVERCAVESTATE = 3;
	public static final int SAVEHIGHSCORE = 4;
	
	public Hoorah() {
		super("My Last Hoorah");
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		//this.getState(MAINMENUSTATE).init(gc, this);
		//this.getState(GAMEPLAYSTATE).init(gc, this);
		
		this.container = gc;
		
		// True if vertical sync is turned on
		container.setTargetFrameRate(100);
		container.setVSync(vsync);
		
		this.addState(new LoadingState(LOADINGSTATE));
		this.addState(new MainMenuState(MAINMENUSTATE));
		this.addState(new GameplayState(GAMEPLAYSTATE));
		this.addState(new HoverCave(HOVERCAVESTATE));
		this.addState(new SaveHighScore(SAVEHIGHSCORE));
		this.enterState(LOADINGSTATE);
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
