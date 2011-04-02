package t2s.ihm;

import java.io.*;
import t2s.util.*;

/**
 * Classe d'information systeme (repertoire/fichier)
 * @author Ecole Polytechnique de Sophia Antipolis
 * @version 1.0
 */

public class InformationSysteme {
	
	/**
	 * Methode qui teste si un chemin est absolu ou relatif
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param path Le chemin du repertoire ou fichier a tester
	 * @return True si il s'agit d'un chemin absolu ou false sinon
	 */
	public static boolean isAbsolutePath(String path)
	{
		try {
			if(System.getProperty("os.name").contains("Windows") || System.getProperty("os.name").contains("windows"))
			{
				if(path.charAt(1) == ':')
					return(true);
				else
					return(false);
			}
			else
			{
				if(path.charAt(0) == '/')
					return(true);
				else
					return(false);
			}
		} catch (Exception e) {
			return(false);
		}
	}
	
	/**
	 * Methode qui retourne le nom de fichier a partir du chemin complet
	 * @author Ecole Ploytechnique de Sophia Antipolis
	 * @param path Le chemin complet du fichier
	 * @return Le nom du fichier
	 */
	public static String getNameFile(String path)
	{
		File f = new File(path);
		return(f.getName());
	}
	
	/**
	 * Methode qui teste si un repertoire existe ou non
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param path Le chemin du repertoire a tester
	 * @return True si le repertoire existe ou false sinon
	 */
	public static boolean existFolder(String path)
	{
		File fichierTemp = new File(path);
		return(fichierTemp.isDirectory());
	}
	
	/**
	 * Methode qui teste si un fichier existe ou non
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @param path Le chemin du fichier a tester
	 * @return True si le fichier existe ou false sinon
	 */
	public static boolean existFile(String path)
	{
		File fichierTemp = new File(path);
		return(fichierTemp.isFile());
	}
	
	/**
	 * Methode qui retourne une chaine de caractere contenant le nom de l'os
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return La chaine de caractere representant l'os
	 */
	public static String getOs()
	{
		if(System.getProperty("os.name").contains("Windows") || System.getProperty("os.name").contains("windows"))
		{
			return("Windows");
		}
		else if(System.getProperty("os.name").contains("Linux") || System.getProperty("os.name").contains("linux"))
		{
			return("Linux");
		}
		else
		{
			return("Mac");
		}
	}
	
	/**
	 * Methode qui retourne le repertoire des donnees de l'application SIVOX
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le chemin absolu du repertoire de donnees
	 */
	public static String getRepertoireDonnees()
	{
		if(System.getProperty("os.name").contains("Windows") || System.getProperty("os.name").contains("windows"))
		{
			return(getRepertoireApplication()+"\\ressources\\");
		}
		else
		{
			return(getRepertoireApplication()+"/ressources/");
		}
	}
	
	/**
	 * Methode qui retourne le repertoire des images 16x16 de l'application SIVOX
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le chemin absolu du repertoire des images 16x16
	 */
	public static String getRepertoireImage16()
	{
		String temp = "";
		temp = ConfigFile.rechercher("IMG_PATH");
		if(temp != null)
		{
			if(System.getProperty("os.name").contains("Windows") || System.getProperty("os.name").contains("windows"))
			{
				return(temp.replace("/", "\\")+"\\16\\");
			}
			else
			{
				return(temp+"/16/");
			}
		}
		else
		{
			return("");
		}
	}
	
	/**
	 * Methode qui retourne le repertoire des images 32x32 de l'application SIVOX
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le chemin absolu du repertoire des images 32x32
	 */
	public static String getRepertoireImage32()
	{
		String temp = "";
		temp = ConfigFile.rechercher("IMG_PATH");
		if(temp != null)
		{
			if(System.getProperty("os.name").contains("Windows") || System.getProperty("os.name").contains("windows"))
			{
				return(temp.replace("/", "\\")+"\\32\\");
			}
			else
			{
				return(temp+"/32/");
			}
		}
		else
		{
			return("");
		}
	}
	
	/**
	 * Methode qui retourne le repertoire du logo de l'application SIVOX
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le chemin absolu du repertoire de logo
	 */
	public static String getRepertoireLogo()
	{
		String temp = "";
		temp = ConfigFile.rechercher("IMG_PATH");
		if(temp != null)
		{
			if(System.getProperty("os.name").contains("Windows") || System.getProperty("os.name").contains("windows"))
			{
				return(temp.replace("/", "\\")+"\\logo\\");
			}
			else
			{
				return(temp+"/logo/");
			}
		}
		else
		{
			return("");
		}
	}
	
	/**
	 * Methode qui retourne le repertoire d'aide de l'application SIVOX
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le chemin absolu du repertoire d'aide
	 */
	public static String getRepertoireAide()
	{
		String temp = ConfigFile.rechercher("AIDE_PATH");
		if(temp != null)
		{
			if(System.getProperty("os.name").contains("Windows") || System.getProperty("os.name").contains("windows"))
			{
				return(getRepertoireApplication() + "\\" + temp.replace("/", "\\"));
			}
			else
			{
				return(getRepertoireApplication() + "/" + temp);
			}
		}
		else
		{
			return("");
		}
	}
	
	/**
	 * Methode qui retourne le repertoire des images de notes de l'application SIVOX
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le chemin absolu du repertoire des images de notes
	 */
	public static String getRepertoireImageNote()
	{
		String temp = "";
		temp = ConfigFile.rechercher("IMG_PATH");
		if(temp != null)
		{
			if(System.getProperty("os.name").contains("Windows") || System.getProperty("os.name").contains("windows"))
			{
				return(temp.replace("/", "\\")+"\\notes\\");
			}
			else
			{
				return(temp+"/notes/");
			}
		}
		else
		{
			return("");
		}
	}
	
	/**
	 * Methode qui retourne le repertoire de l'application SIVOX
	 * @author Ecole Polytechnique de Sophia Antipolis
	 * @return Le chemin absolu du repertoire de l'application elle-meme
	 */
	public static String getRepertoireApplication()
	{
		return(System.getProperty("user.dir"));
	}
	
	/**
	 * Methode qui teste si une voix est disponible (fichier present)
	 * @param value Le numero de la voix a tester
	 * @return True si le fichier de voix est disponible et false sinon
	 */
	public static boolean testerVoix(int value)
	{
		if((value > 0) && (value < 8))
		{
			String chemin = new String("");
			chemin = ConfigFile.rechercher("VOIX_"+value);
			if(chemin != null)
			{
				File f = new File(chemin);
				if(f.exists())
					return(true);
				else
					return(false);
			}
			else
			{
				return(false);
			}
		}
		else
		{
			return(false);
		}
	}
	
}
