package utils;

/**
 *This class is useful for easy configuration of the game
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
	//Constants
	public static final int TILE_HEIGHT = 64;
	public static final int TILE_WIDTH = 64;
	
    /** 
     * Get the whole adress of a voice file with just its name
     * @param name the name of the voice file
     * @return the adress of the voice file
     */
    public static String getVoice(String name){
    	return Conf.SND_VOIX_PATH+name+".ogg";
    }
}