/**
 * Cette classe ne doit pas etre modifiee.
 * 
 * Cette classe permet d'editer le fichier de configuration du SiVOX.
 * 
 */


package menuGraphique;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.StringTokenizer;

import t2s.util.*;

public class ConfigurationSIVOX {
	
	private static String file="../ressources/si_vox.conf";
	
	/*
	 * Methode qui modifie dans le fichier de configuration  
	 * du SiVOX la valeur de la rapidite.
	 * 
	 * @param rapidite a modifier
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws IOException
	 */
	public static void modifierSIVOX(double rapid)throws FileNotFoundException, IOException{
//		String totalInitial = "";
//		String totalFinal = "";
//		String ligne;
//		String tmp;
//		BufferedReader bufr = new BufferedReader(new FileReader(file));
//		while((ligne = bufr.readLine())!=null) {
//			totalInitial += ligne;
//			totalInitial += "\n";
//		}
//		StringTokenizer texTok = new StringTokenizer(totalInitial,"\n");
//		while(texTok.hasMoreTokens()) {
//			tmp = texTok.nextToken();
//			if(tmp.contains("RAPIDITE")){
//				tmp = "RAPIDITE=" + rapid;
//			}
//			totalFinal += tmp;
//			totalFinal += "\n";
//		}
//		
//		BufferedWriter bufw = new BufferedWriter(new FileWriter(file));
//		bufw.write(totalFinal);
//		bufw.close();	
		ConfigFile.changer("RAPIDITE", ""+rapid);
	}
	
}
