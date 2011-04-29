package sound;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.newdawn.slick.openal.SoundStore;

/**
 * Tools to interact with OpenAl
 */

public class AlUtils {
	
	private static SoundStore store = SoundStore.get();
	
	/**
	 * Put the listener's position, velocity and orientation to default (useful for minigames)
	 */
	public static void resetAlListener(){
		setAlListenerPosition(0.0f, 0.0f, 0.0f);
		setAlListenerVelocity(0.0f, 0.0f, 0.0f);
		setAlListenerOrientation(0.0f, 0.0f, -1.0f,  0.0f, 1.0f, 0.0f);
	}
	
	/**
	 * Set the Al listener's position
	 * @param x The x position of the listener
 	 * @param y The y position of the listener
	 * @param z The z position of the listener
	 */
	public static void setAlListenerPosition(float x, float y, float z){
		FloatBuffer listenerPos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] {x, y, z }).rewind();
		AL10.alListener(AL10.AL_POSITION,    listenerPos);
	}
	
	/**
	 * Set the Al listener's velocity
	 * @param x The x velocity of the listener
 	 * @param y The y velocity of the listener
	 * @param z The z velocity of the listener
	 */
	public static void setAlListenerVelocity(float x, float y, float z){
		FloatBuffer listenerVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] { 0.0f, 0.0f, 0.0f }).rewind();
		AL10.alListener(AL10.AL_VELOCITY,    listenerVel);
	}
	
	/**
	 * Set the Al listener's orientation
	 * @param x The x position of the "at" vector
 	 * @param y The y position of the "at" vector
	 * @param z The z position of the "at" vector
	 * @param x2 The x position of the "up" vector
 	 * @param y2 The y position of the "up" vector
	 * @param z2 The z position of the "up" vector
	 */
	public static void setAlListenerOrientation(float x, float y, float z, float x2, float y2, float z2){
		FloatBuffer listenerOri = (FloatBuffer)BufferUtils.createFloatBuffer(6).put(new float[] { x, y, z, x2, y2, z2 }).rewind();
		AL10.alListener(AL10.AL_ORIENTATION, listenerOri);
	}
	
	/**
	 * Stops all sounds currently playing
	 */
	public static void stopAllSounds(){
		for (int i = 1; i < store.getSourceCount(); i++) {
			if (AL10.alGetSourcei(SoundStore.get().getSource(i), AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING) {
				AL10.alSourceStop(SoundStore.get().getSource(i));
			}
		}
	}
	
	public static boolean anySoundPlaying(){
		for (int i = 1; i < store.getSourceCount(); i++) {
			if (AL10.alGetSourcei(SoundStore.get().getSource(i), AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING) {
				return true;
			}
		}
		return false;
	}
	

}
