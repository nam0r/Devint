
package t2s.son;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.sound.sampled.*;

/**
 * Classe Audio Concat
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */
public class AudioConcat {

	/**
	 * Constructeur par defaut
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param travail L'ArrayList de travail
	 */
    public AudioConcat (ArrayList travail) {

	String	strOutputFilename = "discours.wav";
	AudioFormat audioFormat = null;
	List<AudioInputStream> audioInputStreamList = new ArrayList<AudioInputStream>();
	String	strFilename = null;
	for (int i = 0; i < travail.size(); i++)
	{
	    strFilename = (String)travail.get(i);
	    File soundFile = new File(strFilename);
	    AudioInputStream  audioInputStream = null;
	    try { audioInputStream =  AudioSystem.getAudioInputStream(soundFile);
	    } catch (Exception e) {
		e.printStackTrace();
		System.exit(1);
	    }
	    AudioFormat format = audioInputStream.getFormat();
	    // The first input file determines the audio format.
	    if (audioFormat == null){audioFormat = format;}
	    else if ( ! audioFormat.matches(format)){
		    out("AudioConcat.main(): WARNING: AudioFormats don't match");
		    out("AudioConcat.main(): master format: " + audioFormat);
		    out("AudioConcat.main(): this format: " + format);
	    }
	    audioInputStreamList.add(audioInputStream);
	}
	if (audioFormat == null) {
	    out("No input filenames!");
	    printUsageAndExit();
	}
	AudioInputStream audioInputStream = null;
	audioInputStream =  new SequenceAudioInputStream(audioFormat, 
					 audioInputStreamList);

	File outputFile = new File(strOutputFilename);
	try {
	    AudioSystem.write(audioInputStream, 
			      AudioFileFormat.Type.WAVE, outputFile);
	} catch (IOException e) {e.printStackTrace();}
    }
    
    /**
     * Methode d affichage d'usage
     * @author Ecole Polytechnique de Sophia Antipolis
     */
    private static void printUsageAndExit()
    {
	out("AudioConcat: usage:");
	out("\tjava AudioConcat ...");
	out("the first file determines the audio format.");
	System.exit(1);
    }
    
    /**
     * Methode de sortie de message
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param strMessage Le message
     */
    private static void out(String strMessage)
    {
    	System.out.println(strMessage);
    }
    
    /**
     * Un executable
     * @author Ecole Polytechnique de Sophia Antipolis
     * @param args La liste des arguments
     */
    public static void main(String[] args) {
		ArrayList<String> travail= new ArrayList<String>();
		travail.add("lea.wav");
		travail.add("theo.wav");
		out("nombre : " + travail.size());
		for (int i=0;i<travail.size();i++) out("\n"+travail.get(i));
    }
}
