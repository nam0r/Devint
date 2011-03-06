package main;

import game.GameplayState;
import menu.ChoicePersoState;
import menu.LoadingState;
import menu.MainMenuState;
import menu.SaveHighScore;
import minigame.HoverCave;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

import questions.QuestionState;


public class Hoorah extends StateBasedGame {

	// Attributes
	private GameContainer container;
	private boolean vsync = true;
	
	// States
	public static final int LOADINGSTATE = 0;
	public static final int CHOICEPERSOSTATE = 1;
	public static final int MAINMENUSTATE = 2;
	public static final int GAMEPLAYSTATE = 3;
	public static final int HOVERCAVESTATE = 4;
	public static final int SAVEHIGHSCORE = 5;
	public static final int QUESTIONSTATE = 6;
	
	public Hoorah() {
		super("My Last Hoorah");
	}

	@Override
	public void initStatesList(GameContainer gc) throws SlickException {
		//this.getState(MAINMENUSTATE).init(gc, this);
		//this.getState(GAMEPLAYSTATE).init(gc, this);
		
		this.container = gc;
		
		/*
		try {
			container.setFullscreen(true);
		} catch (SlickException e) {
			System.err.println("Erreur lors de la mise en plein écran.");
		}
		*/
		
		// True if vertical sync is turned on
		container.setTargetFrameRate(100);
		container.setVSync(vsync);
		
		this.addState(new LoadingState(LOADINGSTATE));
		this.addState(new ChoicePersoState(CHOICEPERSOSTATE));
		this.addState(new MainMenuState(MAINMENUSTATE));
		this.addState(new GameplayState(GAMEPLAYSTATE));
		this.addState(new HoverCave(HOVERCAVESTATE));
		this.addState(new SaveHighScore(SAVEHIGHSCORE));
		this.enterState(LOADINGSTATE);
		this.addState(new QuestionState(QUESTIONSTATE));
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
