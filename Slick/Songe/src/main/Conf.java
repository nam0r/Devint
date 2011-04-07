package main;

/**
 *This class is useful for easy configuration of the game
 */


public class Conf {
	
	public static String RESS_PATH = "../ressources/";
	public static String IMG_PATH = "../ressources/images/";
	public static String SND_PATH = "../ressources/sons/";
	public static String SND_DEPLACEMENT_PATH = "../ressources/sons/deplacement/";
	public static String SND_ENVIRONEMENT_PATH = "../ressources/sons/environement/";
	public static String SND_BIP_PATH = "../ressources/sons/bip/";
	public static String SND_VOIX_PATH = "../ressources/sons/voix/julie/";
	
    /** 
     * Get the whole adress of a voice file with just its name
     * @param name the name of the voice file
     * @return the adress of the voice file
     */
    public static String getVoice(String name){
    	return Conf.SND_VOIX_PATH+name+".ogg";
    }
}
