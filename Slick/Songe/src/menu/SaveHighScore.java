package menu;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import main.Songe;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.gui.AbstractComponent;
import org.newdawn.slick.gui.ComponentListener;
import org.newdawn.slick.gui.TextField;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import utils.Conf;
import utils.Globals;
import bdd.SQLiteDB;

/**
 * Saving high score state
 * 
 * @author namor
 * @author Afnarel
 * @author guodman
 */
public class SaveHighScore extends BasicGameState implements ComponentListener {
	private int stateID;
	/** The field taking the name */
	private TextField nameField;
	/** The name value */
	private String nameValue = "none";
	/** The font we're going to use to render */
	private Font font;
	/** The image */
	private Image image;
	/** indicates if we are in submitting mode */
	private boolean submitting;
	/** Default name */
	private final String DEFAULTNAME = "Anonyme";
	/** Current name typed at screen */
	private String currentName;
	/** Current score database entry */
	private int currentScoreID;
	/** current game */
	private StateBasedGame game;
	/** indicates if connection has already been set */
	private boolean submitted; 

	private String db_path;
	private SQLiteDB conn;

	/**
	 * Create a new test for font rendering
	 */
	public SaveHighScore(int stateID) {
		this.stateID = stateID;
	}

	@Override
	public int getID() {
		return stateID;
	}

	/**
	 * @see org.newdawn.slick.Game#init(org.newdawn.slick.GameContainer)
	 */
	public void init(GameContainer container, StateBasedGame game)
			throws SlickException {
		this.game = game;
		image = new Image(Conf.IMG_PATH + "sky2.jpg");
		font = new AngelCodeFont(Conf.FONTS_PATH + "hiero.fnt", Conf.FONTS_PATH
				+ "hiero.png");
		currentName = "";
		nameField = new TextField(container, container.getDefaultFont(), 200,
				120, 400, 30, this);
		submitted = false;
		}

	/**
	 * @see org.newdawn.slick.BasicGame#render(org.newdawn.slick.GameContainer,
	 *      org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer gc, StateBasedGame game, Graphics g) {
		
		if (!submitted) {
			submitted = true;
			// if under jnlp
			if (System.getProperty("javawebstart.version") != null)
				db_path = Conf.SCORE_DB;
			// if under CD Devint
			else
				db_path = Conf.SCORE_DB;

			conn = new SQLiteDB(db_path);

			conn.executeQuery("CREATE TABLE IF NOT EXISTS scores (id integer PRIMARY KEY AUTOINCREMENT, name text, score integer);");
		}
		
		image.draw(0, 0, gc.getWidth(), gc.getHeight());

		if(Globals.score != 0) {
			font.drawString(200, 32, "Votre score : " + Globals.score);
			// The name textfield is displayed only if the player has a real
			// highscore
			if (submitting) {
				nameField.render(gc, g);
			}
		}

		// else{
		// font.drawString(200, 32, "Votre score : " + Globals.score);
		// }
		if(submitted)
			displayScores(gc, g);
			
	}

	/**
	 * @see org.newdawn.slick.BasicGame#update(org.newdawn.slick.GameContainer,
	 *      int)
	 */
	@Override
	public void update(GameContainer gc, StateBasedGame sbg, int delta)
			throws SlickException {

		Input input = gc.getInput();

		if (input.isKeyPressed(Input.KEY_ESCAPE)) {
			sbg.enterState(Songe.MAINMENUSTATE);
		}
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		super.enter(gc, sbg);

		nameField.setFocus(true);
		submitting = isHighScore(Globals.score);
	}

	/**
	 * @see org.newdawn.slick.BasicGame#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			game.enterState(Songe.MAINMENUSTATE, new FadeOutTransition(
					Color.black), new FadeInTransition(Color.black));
		}
	}

	/**
	 * @see org.newdawn.slick.gui.ComponentListener#componentActivated(org.newdawn.slick.gui.AbstractComponent)
	 */
	public void componentActivated(AbstractComponent source) {
		if (source == nameField) {
			
			if(!Globals.score_submitted) {
				writeScore(DEFAULTNAME);
				Globals.score_submitted = true;
			}
			
			nameValue = nameField.getText();
			updateName(nameValue);
			
		}
	}

	public boolean isHighScore(int scoreNumber) {

		ArrayList<HashMap<String, String>> results = conn
				.select("SELECT * FROM scores ORDER BY score DESC LIMIT 10;");
		for (HashMap<String, String> result : results) {
			int score = Integer.parseInt(result.get("score"));
			// If one score among the 10 best is smaller than users score, then
			// he has a highscore
			if (score < scoreNumber)
				return true;
		}

		// If there is less than 10 scores in the DB, the player has a highscore
		if (results.size() < 10)
			return true;

		return false;
	}

	public void updateName(String name) {
		conn.executeQuery("UPDATE scores SET name='" + name + "', score="
				+ Globals.score + " WHERE id=" + currentScoreID + ";");
	}

	// This must be executed only one time
	public void writeScore(String name) {
		conn.executeQuery("INSERT INTO scores (name, score) values ('" + name
				+ "', " + Globals.score + ");");

		HashMap<String, String> result = conn
				.selectSingle("SELECT * FROM scores ORDER BY id DESC LIMIT 1;");
		currentScoreID = Integer.parseInt(result.get("id"));
	}

	public void displayScores(GameContainer container, Graphics g) {
		int x = 200;
		int y = 200;
		font.drawString(x, y, "High Scores:");

		ArrayList<HashMap<String, String>> results = conn
				.select("SELECT * FROM scores ORDER BY score DESC LIMIT 10;");

		x += 30;
		y += 20;
		for (HashMap<String, String> result : results) {
			y += 47;
			int id = Integer.parseInt(result.get("id"));
			// Player's score
			if (id == currentScoreID) {
				font.drawString(x, y,
						result.get("score") + " " + result.get("name")
								+ " <-- Votre Score");
			}
			// Other Scores
			else {
				font.drawString(x, y,
						result.get("score") + " " + result.get("name"));
			}
		}
	}

}