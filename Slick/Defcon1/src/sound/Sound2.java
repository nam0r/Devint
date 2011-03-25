package sound;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.Log;

public class Sound2{
	
	/** The internal sound effect represent this sound */
	private Audio sound;
	/** The volume of this sound */
	private float volume = 1.0f;
	/** The pitch of this sound */
	private float pitch = 1.0f;

	public Sound2(String ref) throws SlickException {
		SoundStore.get().init();
		
		try {
			if (ref.toLowerCase().endsWith(".ogg")) {
				sound = SoundStore.get().getOgg(ref);
			} else if (ref.toLowerCase().endsWith(".wav")) {
				sound = SoundStore.get().getWAV(ref);
			} else if (ref.toLowerCase().endsWith(".aif")) {
				sound = SoundStore.get().getAIF(ref);
			} else if (ref.toLowerCase().endsWith(".xm") || ref.toLowerCase().endsWith(".mod")) {
				sound = SoundStore.get().getMOD(ref);
			} else {
				throw new SlickException("Only .xm, .mod, .aif, .wav and .ogg are currently supported.");
			}
		} catch (Exception e) {
			Log.error(e);
			throw new SlickException("Failed to load sound: "+ref);
		}
	}
	
	/**
	 * Set the volume of the sound as a factor of the global volume setting
	 * @param volume The volume to play sound at. 0 - 1, 1 is Max
	 */
	public void setVolume(float volume, int index) {
		// Bounds check
		if(volume > 1) {
			volume = 1;
		} else if(volume < 0) {
			volume = 0;
		}
		this.volume = volume;
		AL10.alSourcef(index, AL10.AL_GAIN, volume);
	}
	
	/**
	 * Set the pitch of the sound as a factor of the global pitch setting
	 * @param pitch The pitch to play sound at. 0 - 1, 1 is Max
	 */
	public void setPitch(float pitch, int index) {
		// Bounds check
		/*if(pitch > 1) {
			pitch = 1;
		} else if(pitch < 0) {
			pitch = 0;
		}*/
		this.pitch = pitch;
		AL10.alSourcef(index, AL10.AL_PITCH, pitch);
	}
	
	/**
	 * Set the position of the source
	 * @param x The x position of the source
 	 * @param y The y position of the source
	 * @param z The z position of the source
	 */
	public void setSourcePosition(float x, float y, float z, int index) {
		FloatBuffer sourcePos = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] {x, y, z}).rewind();
		AL10.alSource (index, AL10.AL_POSITION, sourcePos);
	}
	
	/**
	 * Set the velocity of the source
	 * @param x The x velocity of the source
 	 * @param y The y velocity of the source
	 * @param z The z velocity of the source
	 */
	public void setSourceVelocity(float x, float y, float z, int index) {
		FloatBuffer sourceVel = (FloatBuffer)BufferUtils.createFloatBuffer(3).put(new float[] {x, y, z}).rewind();
		AL10.alSource (index, AL10.AL_VELOCITY, sourceVel);
	}
	
	/**
	 * Get the individual volume of the sound
	 * @return The volume of this sound, still effected by global SoundStore volume. 0 - 1, 1 is Max
	 */
	public float getVolume() {
		return volume;
	}
	
	/**
	 * Get the individual pitch of the sound
	 * @return The pitch of this sound, still effected by global SoundStore pitch. 0 - 1, 1 is Max
	 */
	public float getPitch() {
		return pitch;
	}
	
	/**
	 * Play this sound effect at default volume and pitch
	 */
	public int play() {
		return play(1.0f, 1.0f);
	}
	
	/**
	 * Play this sound effect at a given volume and pitch
	 * @param pitch The pitch to play the sound effect at
	 * @param volume The volume to play the sound effect at
	 */
	public int play(float pitch, float volume) {
		return sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), false);
	}

	/**
	 * Play a sound effect from a particular location
	 * @param x The x position of the source of the effect
 	 * @param y The y position of the source of the effect
	 * @param z The z position of the source of the effect
	 */
	public int playAt(float x, float y, float z) {
		return playAt(1.0f, 1.0f, x,y,z);
	}
	
	/**
	 * Play a sound effect from a particular location
	 * @param pitch The pitch to play the sound effect at
	 * @param volume The volume to play the sound effect at
	 * @param x The x position of the source of the effect
 	 * @param y The y position of the source of the effect
	 * @param z The z position of the source of the effect
	 */
	public int playAt(float pitch, float volume, float x, float y, float z) {
		return sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), false, x,y,z);
	}
	/**
	 * Loop this sound effect at default volume and pitch
	 */
	public int loop() {
		return loop(1.0f, 1.0f);
	}
	
	/**
	 * Loop this sound effect at a given volume and pitch
	 * @param pitch The pitch to play the sound effect at
	 * @param volume The volume to play the sound effect at
	 */
	public int loop(float pitch, float volume) {
		return sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), true);
	}
	
	/**
	 * Loop this sound effect at a given volume, pitch and position
	 * @param pitch The pitch to play the sound effect at
	 * @param volume The volume to play the sound effect at
	 * @param x The x position of the source of the effect
 	 * @param y The y position of the source of the effect
	 * @param z The z position of the source of the effect
	 */
	public int loop(float pitch, float volume, float x, float y, float z) {
		return sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), true, x,y,z);
	}

	/**
	 * Check if the sound is currently playing
	 * @return True if the sound is playing
	 */
	public boolean playing() {
		return sound.isPlaying();
	}
	
	/**
	 * Stop the sound being played
	 */
	public void stop() {
		sound.stop();
	}

}
