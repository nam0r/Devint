package main;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import bddcreate.BDDCreator;

public class Main {

	public static void main(String[] args) {
		new BDDCreator();
		
		AppGameContainer app;
		// initial ratio for 4/3 screens
		int width = 1024;
		int height = 768;
		try {
			app = new AppGameContainer(new Hoorah());
			
			float ratio = (float)app.getScreenWidth() / (float)app.getScreenHeight();
			switch((int)(ratio*100)){
				case 160: width = 1280; height = 800; break; //ratio 16/10
				case 178: 
				case 177: height = 576; break; //ratio 16/9
			}
			
			//we first try to play in 1024*768 full screen
			try{
				app.setDisplayMode(width, height, true);
				
				//if the native resolution is small, we put the native resolution in fullscreen
				if(app.getScreenWidth() < 1200) throw new Exception();
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