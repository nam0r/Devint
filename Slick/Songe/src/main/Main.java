package main;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

public class Main {

	public static void main(String[] args) {
		AppGameContainer app;
		int width = 1024;
		int height = 768;
		try {
			app = new AppGameContainer(new Hoorah());
			//we first try to play in 1024*768 full screen
			try{
				app.setDisplayMode(width, height, true);
				//throw new Exception();
			}
			//if fails, we play in the native resolution, full screen
			catch(Exception e){
				try{
					app.setDisplayMode(app.getScreenWidth(), app.getScreenHeight(), true);
					// à des fins de test, pour ne pas charger en fullscreen en permanence
					throw new Exception();
				}
				//if again fails, we don't play in full screen
				catch(Exception e2){
					if(app.getScreenWidth()<width) width = app.getScreenWidth();
					if(app.getScreenHeight()<height) height = app.getScreenHeight();
					app.setDisplayMode(width, height, false);
				}
			}
			app.start();
		}
		// There is a serious problem, the game can't be launched
		catch (SlickException e) {
			e.printStackTrace();
		}
	}

}