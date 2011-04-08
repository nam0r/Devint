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
			float ratio = (float)app.getScreenWidth() / (float)app.getScreenHeight();
			switch((int)(ratio*100)){
				case 160: height = 640; break;
				case 178: 
				case 177: height = 576; break;
			}
			//we first try to play in 1024*768 full screen
			try{
				app.setDisplayMode(width, height, true);
<<<<<<< HEAD
				//throw new Exception();
=======
				throw new Exception();
>>>>>>> eb1b764e2c09bf74cca51d66547b4f80218fe1de
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