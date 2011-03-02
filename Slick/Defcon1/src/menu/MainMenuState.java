package menu;

import main.Hoorah;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.Music;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

import utils.Utils;
 
public class MainMenuState extends BasicGameState {
 
	private int stateID;
	
	private Image background;
	private MenuButton startGameOption;
	private MenuButton exitGameOption;
	private Sound enter;
	private Music musique;
	
    public MainMenuState(int stateID) {
    	this.stateID = stateID;
    }

	@Override
	public void init(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		
		// Background
		background = new Image("res/img/sky2.jpg");
		
		// Menus
		int menuX = 410;
		int menuY = 160;
		
		startGameOption = new MenuButton("res/img/menuoptions.png", menuX, menuY);
		startGameOption.sub(0, 0, 377, 71); // Recupere seulement une partie de l'image (crop)

		exitGameOption = new MenuButton("res/img/menuoptions.png", menuX, menuY + 80);
		exitGameOption.sub(0, 71, 377, 71);
		
		// Sound
		enter = new Sound("res/snd/enter.wav");
		
		musique = new Music("../Slick/snd/hope.ogg"); // A revoir
	}

	@Override
	public void render(GameContainer gc, StateBasedGame sbg, Graphics gfx)
			throws SlickException {
		
		background.draw();
		startGameOption.drawScaled();
		exitGameOption.drawScaled();
		
		// Affichage d'une chaine de caracteres
		Utils.drawCenteredString(gfx, "Menu", gc.getWidth(), 20, Color.orange,40);
		
	}

	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {
		
		Input input = gc.getInput();

		int mouseX = input.getMouseX();
		int mouseY = input.getMouseY();
		
		// Menu Jouer
		
		if (startGameOption.isInside(mouseX, mouseY)) { // Au survol du curseur
			startGameOption.enlarge(delta); // Augmente la taille du menu

			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON)) {
				enter.play(); // Joue un son
				sbg.enterState(Hoorah.GAMEPLAYSTATE); // Lance le jeu
			}
		} else {
			startGameOption.reduce(delta); // Souris en dehors -> retour a la taille normale
		}

		// Menu Quitter
		if (exitGameOption.isInside(mouseX, mouseY)) {
			exitGameOption.enlarge(delta);

			if (input.isMouseButtonDown(Input.MOUSE_LEFT_BUTTON))
				gc.exit(); // Quitte le jeu
		} else {
			exitGameOption.reduce(delta);
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

		musique.play();
	}
	
	// Appelee lors de la sortie de l'etat
	@Override
	public void leave(GameContainer gc, StateBasedGame sb)
			throws SlickException {
		super.leave(gc, sb);

		musique.stop();
	}

}