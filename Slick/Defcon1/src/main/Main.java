package main;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

/*
 * TODO
 * - Supprimer la classe Globals du package main qui ne contient presque rien.
 * - Finir de gerer la transition entre 2 mondes
 * - Voir le redimensionnement / fullscreen
 * 
 * - Changer le menu principal pour l'adapter aux malvoyants
 * - Gérer l'affichage des questions à l'écran, la réponse et le stockage du score
 * - Gérer l'affichage des Highscores
 */
public class Main {

	public static void main(String[] args) {
		AppGameContainer app;
		try {
			app = new AppGameContainer(new Hoorah());
			app.setDisplayMode(1024, 768, false); // Mode fenêtré
			app.start();
			
			// Plus court :
			//AppGameContainer container = new AppGameContainer(new Hoorah(), 800, 600, false);
			//container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}