package main;

import java.util.Random;

import org.lwjgl.openal.AL10;
import org.newdawn.slick.SlickException;

import sound.Sound2;


public class TestSoundPerso {

	public static void main(String[] args) throws InterruptedException{
		Sound2 m = null;
		try {
			m = new Sound2(Conf.SND_PATH+"nuit.wav");
		} catch (SlickException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int lol = m.play();
		
		float x = -10.0f;
		
		float vitesse = 0.50f;
		
		Random r = new Random();
		
		while (true)
		{
			vitesse += (0.51-r.nextFloat())/100;
		
			m.setSourcePosition(x, 0.0f, 0.0f, lol);
			m.setSourceVelocity(vitesse, 0.0f, 0.0f, lol);
			
			// Note Ça marche putain de bien
			AL10.alDopplerFactor(50.0f);
			
			x += vitesse;
			
			System.out.println(x);
			Thread.sleep(100);
		}
	}
}