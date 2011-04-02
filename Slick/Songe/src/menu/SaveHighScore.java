package menu;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import main.Globals;
import main.Hoorah;

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

/**
 * 
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
	/** indicates if the score is already submitted */
	private boolean submitted;
	/** Default name */
	private final String DEFAULTNAME = "Anonyme";
	/** Current name typed at screen */
	private String currentName;
	/** Current score database entry */
	private int currentScoreID;
	/** current game */
	private StateBasedGame game;

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
		image = new Image("ressources/images/sky2.jpg");
		font = new AngelCodeFont("ressources/hiero.fnt", "ressources/hiero.png");
		submitted = false;
		currentName = "";
		nameField = new TextField(container, container.getDefaultFont(), 200, 120, 400, 30, this);
	}

	/**
	 * @see org.newdawn.slick.BasicGame#render(org.newdawn.slick.GameContainer,
	 *      org.newdawn.slick.Graphics)
	 */
	public void render(GameContainer container, StateBasedGame game, Graphics g) {
		image.draw(0, 0, 1024, 768);
		if(Globals.score != 0) {
			font.drawString(200, 32, "Votre score : " + Globals.score);
			// The name textfield is displayed only if the player has a real highscore
			if(submitting){
				nameField.render(container, g);
			}
		}
		//else{
		//	font.drawString(200, 32, "Votre score : " + Globals.score);
		//}
		displayScores(container, g);
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
			sbg.enterState(Hoorah.MAINMENUSTATE);
		}
		
		if (!submitted) {
			submitted = true;
			// We put it here to make it happen only one time
			if(Globals.score != 0){
				writeScore(DEFAULTNAME);
				submitting = isHighScore(Globals.score);
			}
			//If just watching highscores whthout playing
			else
				submitting = false;
		}
	}
	
	@Override
	public void enter(GameContainer gc, StateBasedGame sbg) throws SlickException {
		super.enter(gc, sbg);
		
		nameField.setFocus(true);
	}

	/**
	 * @see org.newdawn.slick.BasicGame#keyPressed(int, char)
	 */
	public void keyPressed(int key, char c) {
		if (key == Input.KEY_ESCAPE) {
			game.enterState(Hoorah.MAINMENUSTATE, new FadeOutTransition(Color.black),	new FadeInTransition(Color.black));
		}
	}

	/**
	 * @see org.newdawn.slick.gui.ComponentListener#componentActivated(org.newdawn.slick.gui.AbstractComponent)
	 */
	public void componentActivated(AbstractComponent source) {
		if (source == nameField) {
			nameValue = nameField.getText();
			updateName(nameValue);
		}
	}
	
	public boolean isHighScore(int scoreNumber){
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:scores.db");
			Statement stat = conn.createStatement();
			ResultSet r = stat
			.executeQuery("SELECT * FROM scores ORDER BY score DESC LIMIT 10;");
			int i=0;
			while (r.next()) {
				int score = Integer.parseInt(r.getString("score"));
				// If one score among the 10 best is smaller than users score, then he has a highscore
				if (score < scoreNumber) {
					conn.close();
					return true;
				}
				i++;
			}
			conn.close();
			// If there is less than 10 scores in the DB, the player has a highscore
			if(i<10) return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public void updateName(String name) {
		try {
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:scores.db");
			Statement stat = conn.createStatement();
			stat.executeUpdate("UPDATE scores SET name='" + name + "', score="
					+ Globals.score + " WHERE id="
					+ currentScoreID + ";");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	// This must be executed only one time
	public void writeScore(String name) {
		try {
			File f = new File("scores.db");
			boolean newFile = !f.exists();
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:scores.db");
			Statement stat = conn.createStatement();
			if (newFile) {
				stat
						.executeUpdate("CREATE TABLE scores (id integer PRIMARY KEY AUTOINCREMENT, name text, score integer);");
			}
			stat.executeUpdate("INSERT INTO scores (name, score) values ('"
					+ name + "', " + Globals.score + ");");
			ResultSet r = stat.executeQuery("SELECT * FROM scores ORDER BY id DESC LIMIT 1;");
			currentScoreID = Integer.parseInt(r.getString("id"));
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void displayScores(GameContainer container, Graphics g) {
		int x = 200;
		int y = 200;
		font.drawString(x, y, "High Scores:");
		
		File f = new File("scores.db");
		if (f.exists()) {
			try {
				Class.forName("org.sqlite.JDBC");
				Connection conn = DriverManager
						.getConnection("jdbc:sqlite:scores.db");
				Statement stat = conn.createStatement();
				ResultSet r = stat
						.executeQuery("SELECT * FROM scores ORDER BY score DESC LIMIT 10;");
				x += 30;
				y += 20;
				while (r.next()) {
					y += 47;
					int id = Integer.parseInt(r.getString("id"));
					// Player's score
					if (id == currentScoreID) {
						font.drawString(x, y, r.getString("score") + " "
								+ r.getString("name") + " <-- Votre Score");
					}
					// Other Scores
					else {
						font.drawString(x, y, r.getString("score") + " "
								+ r.getString("name"));
					}
				}
				r.close();
				conn.close();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
