
import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

//infos sur http://ubiquarium.polytech.unice.fr/AcapelaWebService/Acapela.asmx
public class ServiceAcapela {

    private final static String SERVEUR = 
	"http://ubiquarium.polytech.unice.fr/AcapelaWebService/Acapela.asmx";
    private static  String texte;
    private final static String EXE = ".\\ACAPELA_CSHARP\\Acapela.exe -g";

    public ServiceAcapela() {
	super();
    }

    /**
     * Genere un fichier .wav a partir de la chaine de caracteres passee en premier parametre
     * @param texte : le texte qui sera utilisé par TTS
     * @param fichier : le nom fichier wav qui sera généré
     */
    public static void  generer(String texte, String fichier){

	String fichier_wav = fichier;
	if(!fichier_wav.endsWith(".wav")){
	    fichier_wav = fichier_wav + ".wav";
	}
	
	Runtime current = Runtime.getRuntime();
	String cmd = EXE +  " \""+  texte + "\"" + " " + "\"Bruno22kM\""  + " " + "\"105\"" + " " + "\"" + fichier_wav + "\"" + " " + "\"" + SERVEUR + "\"";
	Process script = null;
	try{
	    System.out.println(cmd);
	    script = current.exec(cmd);
	}catch(IOException e){
	    System.out.println("[GENERATION] Erreur lors de l'execution de Acapela en CSHARP");
	    e.printStackTrace();
	    script.destroy();
	    System.exit(0);
	}
        
    }

    public static void main(String st[]) {
	if (st.length==2) {
	    texte="Bonjour, comment ça va ?";
	    generer(texte,"claire2.wav");
	    //generer(st[0],st[1]);
	    System.out.println("fichier généré avec succés");
	}
	else {
	    try {
		BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Texte à générer:");
		String texte = bf.readLine();
		System.out.println("Nom du fichier .wav");
		String fichier = bf.readLine();
		generer(texte,fichier);
		System.out.println("Fichier généré avec succés");
	    }
	    catch (IOException e) {
		System.out.println("Problème lecture fichier");
	    }
	}
    }
}


