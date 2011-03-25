package sound;

import org.lwjgl.openal.AL10;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;

public class SoundR extends Sound{
	
	/** The internal sound effect represent this sound */
	private Audio sound;
	/** The volume of this sound */
	private float volume = 1.0f;
	/** The pitch of this sound */
	private float pitch = 1.0f;

	public SoundR(String ref) throws SlickException {
		super(ref);
	}
	
	/**
	 * Set the volume of the sound as a factor of the global volume setting
	 * 
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
	 * 
	 * @param pitch The pitch to play sound at. 0 - 1, 1 is Max
	 */
	public void setPitch(float pitch, int index) {
		// Bounds check
		if(volume > 1) {
			volume = 1;
		} else if(volume < 0) {
			volume = 0;
		}
		this.pitch = pitch;
		AL10.alSourcef(index, AL10.AL_PITCH, pitch);
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
	public int playR() {
		return playR(1.0f, 1.0f);
	}
	
	/**
	 * Play this sound effect at a given volume and pitch
	 * 
	 * @param pitch The pitch to play the sound effect at
	 * @param volume The volume to play the sound effect at
	 */
	public int playR(float pitch, float volume) {
		return sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), false);
	}

	/**
	 * Play a sound effect from a particular location
	 * 
	 * @param x The x position of the source of the effect
 	 * @param y The y position of the source of the effect
	 * @param z The z position of the source of the effect
	 */
	public int playAtR(float x, float y, float z) {
		return playAtR(1.0f, 1.0f, x,y,z);
	}
	
	/**
	 * Play a sound effect from a particular location
	 * 
	 * @param pitch The pitch to play the sound effect at
	 * @param volume The volume to play the sound effect at
	 * @param x The x position of the source of the effect
 	 * @param y The y position of the source of the effect
	 * @param z The z position of the source of the effect
	 */
	public int playAtR(float pitch, float volume, float x, float y, float z) {
		return sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), false, x,y,z);
	}
	/**
	 * Loop this sound effect at default volume and pitch
	 */
	public int loopR() {
		return loopR(1.0f, 1.0f);
	}
	
	/**
	 * Loop this sound effect at a given volume and pitch
	 * 
	 * @param pitch The pitch to play the sound effect at
	 * @param volume The volume to play the sound effect at
	 */
	public int loopR(float pitch, float volume) {
		return sound.playAsSoundEffect(pitch, volume * SoundStore.get().getSoundVolume(), true);
	}

}
