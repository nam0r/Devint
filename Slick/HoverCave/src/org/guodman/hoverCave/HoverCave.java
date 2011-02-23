package org.guodman.hoverCave;

import java.awt.Dimension;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

public class HoverCave extends BasicGame {
	public static final int WIDTH = 1024;
	public static final int HEIGHT = 768;
	public static final int MIN_WIDTH = 50;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			AppGameContainer container = new AppGameContainer(new HoverCave(),
					WIDTH, HEIGHT, false);
			container.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private double dudeHeight = HEIGHT / 2;
	private Dimension dudeSize = new Dimension(20, 40);
	private boolean movingUp = false;
	private Vector<Integer> upperWall = new Vector<Integer>();
	private Vector<Integer> lowerWall = new Vector<Integer>();
	private float wallOffset = 0;
	private final int WALL_RES = 20;
	private boolean dead = false;
	private double speed = 0.1;
	private int distance = 0;

	public HoverCave() {
		super("Hover Cave");
	}

	private void popWall() {
		upperWall.remove(0);
		lowerWall.remove(0);
	}

	public void writeScore() {
		try {
			File f = new File("scores.db");
			boolean newFile = !f.exists();
			Class.forName("org.sqlite.JDBC");
			Connection conn = DriverManager
					.getConnection("jdbc:sqlite:scores.db");
			Statement stat = conn.createStatement();
			if (newFile) {
				stat.executeUpdate("CREATE TABLE scores (score int);");
			}
			stat.executeUpdate("INSERT INTO scores values (" + distance + ");");
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void addToWall() {
		// TODO Do some of these numbers need to be tweaked?
		int offset1 = (int) (Math.random() * 50 - 20);
		int offset2 = (int) (Math.random() * 50 - 30);
		int nextUpper = upperWall.get(upperWall.size() - 1) + offset1;
		int nextLower = lowerWall.get(lowerWall.size() - 1) + offset2;
		if (nextUpper >= HEIGHT - MIN_WIDTH) {
			nextUpper = HEIGHT - MIN_WIDTH;
		}
		if (nextUpper < 0) {
			nextUpper = 0;
		}
		if (nextLower <= MIN_WIDTH) {
			nextLower = MIN_WIDTH;
		}
		if (nextLower > HEIGHT) {
			nextLower = HEIGHT;
		}
		// TODO fixme: this behaves a little bit strange, but maybe it's okay
		// for now.
		if (nextLower - nextUpper <= MIN_WIDTH) {
			nextLower += nextLower - (nextUpper - MIN_WIDTH);
			nextUpper -= nextLower - (nextUpper - MIN_WIDTH);
		}
		upperWall.add(nextUpper);
		lowerWall.add(nextLower);
	}

	public void reset() {
		upperWall = new Vector<Integer>();
		lowerWall = new Vector<Integer>();
		upperWall.add(0);
		lowerWall.add(HEIGHT);
		for (int i = 0; i < WIDTH / WALL_RES; i++) {
			addToWall();
		}
		dudeHeight = HEIGHT / 2;
		wallOffset = 0;
		dead = false;
	}

	@Override
	public void init(GameContainer container) throws SlickException {
		reset();
	}

	@Override
	public void update(GameContainer container, int delta)
			throws SlickException {
		if (!dead) {
			if (movingUp) {
				dudeHeight -= ((double) delta) / 10.0;
			} else {
				dudeHeight += ((double) delta) / 10.0;
			}
			// TODO The speed can be adjusted here
			wallOffset -= (float) delta * speed;
			speed += (double) delta / 1000000000.0;
			if (wallOffset <= -WALL_RES) {
				wallOffset += WALL_RES;
				popWall();
				addToWall();
			}
			distance += delta;
			// detect collisions
			// TODO Improve collision detection to find the edge of the box
			// against the edge of the cave.
			if (dudeHeight > lowerWall.get(2) || dudeHeight < upperWall.get(2)) {
				dead = true;
				writeScore();
			}
		}
	}

	@Override
	public void render(GameContainer container, Graphics g)
			throws SlickException {
		// TODO Graphics?
		g.drawString("Distance: " + distance, 10, 25);
		g.drawString("Speed: " + speed, 10, 40);
		if (!dead) {
			for (int i = 0; i < upperWall.size() - 1; i++) {
				g.drawLine(i * WALL_RES + wallOffset, upperWall.get(i), (i + 1)
						* WALL_RES + wallOffset, upperWall.get(i + 1));
			}
			for (int i = 0; i < lowerWall.size() - 1; i++) {
				g.drawLine(i * WALL_RES + wallOffset, lowerWall.get(i), (i + 1)
						* WALL_RES + wallOffset, lowerWall.get(i + 1));
			}
			g.drawRect(WALL_RES, (int) dudeHeight - dudeSize.height / 2,
					dudeSize.width, dudeSize.height);
			g.drawLine(WALL_RES * 1.5f, (int) dudeHeight, WALL_RES * 1.5f,
					(int) dudeHeight);
		} else {
			g.drawString("You Are Dead, Enter to try again", 50, 55);
			displayScores(container, g);
		}
	}

	public void displayScores(GameContainer container, Graphics g) {
		File f = new File("scores.db");
		if (f.exists()) {
			try {
				Class.forName("org.sqlite.JDBC");
				Connection conn = DriverManager
						.getConnection("jdbc:sqlite:scores.db");
				Statement stat = conn.createStatement();
				ResultSet r = stat
						.executeQuery("SELECT * FROM scores ORDER BY score DESC LIMIT 10;");
				int x = 50;
				int y = 100;
				g.drawString("High Scores:", x, y);
				boolean scoreDisplayed = false;
				while (r.next()) {
					y += 15;
					int score = Integer.parseInt(r.getString("score"));
					if (score == distance && !scoreDisplayed) {
						scoreDisplayed = true;
						g.setColor(Color.red);
						g.drawString(r.getString("score") + " <-- Your Score",
								x, y);
						g.setColor(Color.white);
					} else {
						g.drawString(r.getString("score"), x, y);
					}
				}
				if (!scoreDisplayed) {
					y += 30;
					g.setColor(Color.red);
					g.drawString("Your Score Was: " + distance, x, y);
					g.setColor(Color.white);
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

	public void keyPressed(int key, char c) {
		if (key == Input.KEY_UP) {
			movingUp = true;
		} else if (key == Input.KEY_ESCAPE) {
			System.exit(0);
		}
		if (dead && key == Input.KEY_ENTER) {
			reset();
		}
	}

	public void keyReleased(int key, char c) {
		if (key == Input.KEY_UP) {
			movingUp = false;
		}
	}
}
