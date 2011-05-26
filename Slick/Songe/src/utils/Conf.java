package utils;

import java.io.File;

/**
 * This class is useful for easy configuration of the game
 *
 * @author namor
 * @author Afnarel
 */
public class Conf {
	
	// ressources paths
	public static final String RESS_PATH = "../ressources/";
	public static final String IMG_PATH = RESS_PATH+"images/";
	public static final String IMG_SPRITES_PATH = RESS_PATH+"images/sprites/";
	public static final String IMG_TEXTURES_PATH = RESS_PATH+"images/textures/";
	public static final String IMG_TILES_PATH = RESS_PATH+"images/tiles/";
	public static final String SND_PATH = RESS_PATH+"sons/";
	public static final String SND_DEPLACEMENT_PATH = RESS_PATH+"sons/deplacement/";
	public static final String SND_ENVIRONEMENT_PATH = RESS_PATH+"sons/environement/";
	public static final String SND_BIP_PATH = RESS_PATH+"sons/bip/";
	public static final String SND_VOIX_PATH = RESS_PATH+"sons/voix/julie/";
	public static final String SND_MUSIC_PATH = RESS_PATH+"sons/musiques/";
	public static final String SND_PERSOS_PATH = RESS_PATH+"sons/persos/";
	public static final String EMITTERS_PATH = RESS_PATH+"emitters/";
	public static final String LEVELS_PATH = RESS_PATH+"levels/";
	public static final String FONTS_PATH = RESS_PATH+"fonts/";
	
	/** The game path (for database and so) */
	public static final File HOME = new File(new File(System.getProperty("user.home")), "Songe");
	/** The name of the scenario database file */
	public static final String SCENARIO_DB = "data.db3";
	/** The name of the score database file */
	public static final String SCORE_DB = "scores.db";
	
	//Constants
	public static final int TILE_HEIGHT = 64;
	public static final int TILE_WIDTH = 64;
	
	
	/**
	 * Get the whole adress of a voice file with just its name
	 * 
	 * @param name
	 *            the name of the voice file
	 * @return the adress of the voice file
	 */
    public static String getVoice(String name){
    	return Conf.SND_VOIX_PATH+name+".ogg";
    }
}
