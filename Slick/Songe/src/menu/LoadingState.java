package menu;

import java.io.File;
import java.io.IOException;

import main.Songe;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.loading.DeferredResource;
import org.newdawn.slick.loading.LoadingList;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

import utils.Conf;
import utils.ExtractFile;

public class LoadingState extends BasicGameState {
	/** the id of the state */
	private int stateID;
	/** The next resource to load */
	private DeferredResource nextResource;
	/** the scale depending on the resolution of the screen */
	private float scale;
	/** The game container */
	GameContainer gc;
	/** Loading bar's unit base width */
	public static final int BAR_WIDTH = 10;
	/** Loading bar's base height */
	public static final int BAR_HEIGHT = 50;
	/** Loading voice */
	private Sound voice;
	/** For vocalize SIVOX */
	protected t2s.SIVOXDevint voix;
	protected boolean contextLoaded;

	public LoadingState(int stateID) {
		this.stateID = stateID;
		contextLoaded = false;
	}

	public int getID() {
		return stateID;
	}

	@Override
	public void init(GameContainer gc, StateBasedGame game)
			throws SlickException {
		voice = new Sound(Conf.getVoice("chargement"));
		LoadingList.setDeferredLoading(true);
		this.gc = gc;
		scale = (float) (gc.getWidth()) / 1024f;
	}

	@Override
	public void render(GameContainer gc, StateBasedGame game, Graphics g)
			throws SlickException {
		int total = LoadingList.get().getTotalResources();
		int loaded = LoadingList.get().getTotalResources()
				- LoadingList.get().getRemainingResources();
		int x = gc.getWidth() / 2 - (int) ((total * BAR_WIDTH * scale) / 2);
		int y = gc.getHeight() / 2 - (int) (30 * scale);
		if (nextResource != null) {
			g.drawString("Chargement : " + nextResource.getDescription(), x, y);
		}
		g.fillRect(x, y + 50 * scale, loaded * BAR_WIDTH * scale, BAR_HEIGHT
				* scale);
		g.drawRect(x, y + 50 * scale, total * BAR_WIDTH * scale, BAR_HEIGHT
				* scale);
	}

	@Override
	public void update(GameContainer gc, StateBasedGame game, int delta)
			throws SlickException {
		if (nextResource != null) {
			try {
				nextResource.load();
				// slow down loading for example purposes
				try {
					Thread.sleep(0);
				} catch (Exception e) {
				}
			} catch (IOException e) {
				throw new SlickException("Failed to load: "
						+ nextResource.getDescription(), e);
			}

			nextResource = null;
		}

		if (LoadingList.get().getRemainingResources() > 0) {
			nextResource = LoadingList.get().getNext();
		} else {
			game.enterState(Songe.INITIALMENUSTATE, new FadeOutTransition(
					Color.black), new FadeInTransition(Color.black));
		}
		// we load resources at the home path of the user if not already
		// done
		if (!contextLoaded) {
			initializeRessources();
			contextLoaded = true;
		}

	}

	/**
	 * Initializes some ressources at the Home path of the user
	 */
	public void initializeRessources() {
		// If running through jnlp webstart, we initialize some ressources
		if (System.getProperty("javawebstart.version") != null) {
			// we see if the game has already ever been initialized and if
			// it needs to unpack special resources and databases
			File ressources = new File(Conf.HOME, Conf.RESS_PATH);
			// if ressources doesn't exist, then we initialize all
			if (!ressources.exists() || !ressources.isDirectory()) {
				if (!Conf.HOME.exists() || !Conf.HOME.isDirectory()) {
					// we create the main directory
					Conf.HOME.mkdir();
				}
				// we create the main directory
				ressources.mkdir();
				// the exact location of the actual jar file
				String jarPath = getClass().getProtectionDomain()
						.getCodeSource().getLocation().toString().replaceAll(
								"%20", " ");
				// the path without the jar filename at the end
				if (jarPath.substring(0,
						jarPath.lastIndexOf(File.separator) + 1).equals(""))
					jarPath = jarPath
							.substring(0, jarPath.lastIndexOf("/") + 1);
				else
					jarPath = jarPath.substring(0, jarPath
							.lastIndexOf(File.separator) + 1);

				// we extract the databases and the Mbrola resources in the game
				// path, these are temporary jars
				ExtractFile.extractFileFromJAR(Conf.HOME.getAbsolutePath(),
						jarPath, "db.jar");
				ExtractFile.extractFileFromJAR(Conf.HOME.getAbsolutePath(),
						jarPath, "Mbrola.jar");
				// we extract the 2 previous created jars
				ExtractFile.unzip(Conf.HOME.getPath(), Conf.HOME.getPath()
						+ File.separator + "Mbrola.jar");
				ExtractFile.unzip(Conf.HOME.getPath(), Conf.HOME.getPath()
						+ File.separator + "db.jar");
				// we delete unnecessary files
				ExtractFile.deleteFile(Conf.HOME.getPath() + File.separator
						+ "db.jar");
				ExtractFile.deleteFile(Conf.HOME.getPath() + File.separator
						+ "Mbrola.jar");
				ExtractFile.deleteFile(Conf.HOME.getPath() + File.separator
						+ "META-INF");
				// if under Gnu/linux we need to set permissions to Mbrola
				// executable
				if (System.getProperties().getProperty("os.name").contains(
						"Linux")) {
					try {
						Runtime
								.getRuntime()
								.exec(
										"chmod 777 "
												+ Conf.HOME.getAbsolutePath()
												+ "/ressources/donneesMbrola/Mbrola/LinuxMbrolaExe/mbrola-linux-i386");
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}
		// If not running in jnlp but normally
		else {
			// if game home directory already exists, then the database
			// files
			// also already exist
			if (!Conf.HOME.exists() || !Conf.HOME.isDirectory()) {
				// we create the main directory
				Conf.HOME.mkdir();
				// the place where this class is
				String path = getClass().getProtectionDomain().getCodeSource()
						.getLocation().toString().replaceAll("%20", " ");
				// we create database files
				ExtractFile.extractFileFromJAR(Conf.HOME.getAbsolutePath(),
						path + Conf.RESS_PATH, Conf.SCORE_DB);
				ExtractFile.extractFileFromJAR(Conf.HOME.getAbsolutePath(),
						path + Conf.RESS_PATH, Conf.SCENARIO_DB);
			}
		}
	}

	@Override
	public void enter(GameContainer gc, StateBasedGame sbg)
			throws SlickException {
		voice.play();
	}

}
