package main;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	public static void main(String[] args) {
		AppGameContainer app;
		try {
			app = new AppGameContainer(new Hoorah());
			app.setDisplayMode(800, 600, false); // Mode fenêtré
			app.start();
			
			// Plus court :
			//AppGameContainer container = new AppGameContainer(new Hoorah(), 800, 600, false);
			//container.start();
		} catch (SlickException e) {
			e.printStackTrace();
		}
	}

}