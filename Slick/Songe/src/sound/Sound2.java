package sound;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import org.lwjgl.BufferUtils;
import org.lwjgl.openal.AL10;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.openal.Audio;
import org.newdawn.slick.openal.SoundStore;
import org.newdawn.slick.util.Log;

public class Sound2 {

	/** The internal sound effect represent this sound */
	private Audio sound;
	/** The volume of this sound */
	private float volume = 1.0f;
	/** The pitch of this sound */
	private float pitch = 1.0f;
	/** The unique buffer of this sound */
	private int buffer;
	/** The OpenAL source index, changes often */
	private int index;
	/** Indicates if the sound has been played once */
	private boolean played;

	public Sound2(String ref) throws SlickException {
		SoundStore.get().init();

		try {
			if (ref.toLowerCase().endsWith(".ogg")) {
				sound = SoundStore.get().getOgg(ref);
			} else if (ref.toLowerCase().endsWith(".wav")) {
				sound = SoundStore.get().getWAV(ref);
			} else if (ref.toLowerCase().endsWith(".aif")) {
				sound = SoundStore.get().getAIF(ref);
			} else if (ref.toLowerCase().endsWith(".xm")
					|| ref.toLowerCase().endsWith(".mod")) {
				sound = SoundStore.get().getMOD(ref);
			} else {
				throw new SlickException(
						"Only .xm, .mod, .aif, .wav and .ogg are currently supported.");
			}
		} catch (Exception e) {
			Log.error(e);
			throw new SlickException("Failed to load sound: " + ref);
		}
		played = false;
		index = -1;
		// will be 0 if sound differed, the buffer id otherwise
		buffer = sound.getBufferID();
	}

	/**
	 * Set the volume of the sound as a factor of the global volume setting
	 * 
	 * @param volume
	 *            The volume to play sound at. 0 - infinity
	 * @param all
	 *            if all sources of the buffer should be affected
	 */
	public void setVolume(float volume, boolean all) {
		if (!playing())
			return;

		if (volume < 0) {
			volume = 0;
		}
		this.volume = volume;
		if (!all)
			AL10.alSourcef(index, AL10.AL_GAIN, volume);
		else {
			for (int source : getSourcesFromBuffer())
				AL10.alSourcef(source, AL10.AL_GAIN, volume);
		}
	}

	/**
	 * Set the pitch of the sound as a factor of the global pitch setting
	 * 
	 * @param pitch
	 *            The pitch to play sound at. 0 - infinity
	 * @param buf
	 *            if the buffer should be used instead of the source
	 * @param all
	 *            if all sources of the buffer should be affected
	 */
	public void setPitch(float pitch, boolean all) {
		if (!playing())
			return;
		if (pitch < 0)
			pitch = 0;
		this.pitch = pitch;
		if (!all)
			AL10.alSourcef(index, AL10.AL_PITCH, pitch);
		else {
			for (int source : getSourcesFromBuffer())
				AL10.alSourcef(source, AL10.AL_PITCH, pitch);
		}
	}

	/**
	 * Set the position of the source
	 * 
	 * @param x
	 *            The x position of the source
	 * @param y
	 *            The y position of the source
	 * @param z
	 *            The z position of the source
	 * @param all
	 *            if all sources of the buffer should be affected
	 */
	public void setSourcePosition(float x, float y, float z, boolean all) {
		if (!playing())
			return;
		FloatBuffer sourcePos = (FloatBuffer) BufferUtils.createFloatBuffer(3)
				.put(new float[] { x, y, z }).rewind();
		if (!all)
			AL10.alSource(index, AL10.AL_POSITION, sourcePos);
		else {
			for (int source : getSourcesFromBuffer())
				AL10.alSource(source, AL10.AL_POSITION, sourcePos);
		}
	}

	/**
	 * Set the velocity of the source
	 * 
	 * @param x
	 *            The x velocity of the source
	 * @param y
	 *            The y velocity of the source
	 * @param z
	 *            The z velocity of the source
	 * @param all
	 *            if all sources of the buffer should be affected
	 */
	public void setSourceVelocity(float x, float y, float z, boolean all) {
		if (!playing())
			return;
		FloatBuffer sourceVel = (FloatBuffer) BufferUtils.createFloatBuffer(3)
				.put(new float[] { x, y, z }).rewind();
		if (!all)
			AL10.alSource(index, AL10.AL_VELOCITY, sourceVel);
		else {
			for (int source : getSourcesFromBuffer())
				AL10.alSource(source, AL10.AL_VELOCITY, sourceVel);
		}
	}

	/**
	 * Collects all sources id that are from the same sound object
	 * 
	 * @return sources the sources that belong to the same sound buffer
	 */
	private ArrayList<Integer> getSourcesFromBuffer() {
		ArrayList<Integer> sources = new ArrayList<Integer>();
		for (int i = 1; i < SoundStore.get().getSourceCount(); i++) {
			if (AL10.alGetSourcei(i, AL10.AL_BUFFER) == buffer)
				sources.add(i);
		}
		return sources;
	}

	/**
	 * Collects the first source id that is from this sound object
	 * 
	 * @return the source that belongs to the sound buffer of this sound
	 */
	private void setSourceFromBuffer() {
		for (int i = 1; i < SoundStore.get().getSourceCount(); i++) {
			if (AL10.alGetSourcei(i, AL10.AL_BUFFER) == buffer) {
				index = i;
				return;
			}
		}
		index = -1;
	}

	/**
	 * Get the individual volume of the sound
	 * 
	 * @return The volume of this sound, still effected by global SoundStore
	 *         volume. 0 - 1, 1 is Max
	 */
	public float getVolume() {
		return volume;
	}

	/**
	 * Get the individual pitch of the sound
	 * 
	 * @return The pitch of this sound, still effected by global SoundStore
	 *         pitch. 0 - 1, 1 is Max
	 */
	public float getPitch() {
		return pitch;
	}

	/**
	 * Play this sound effect at default volume and pitch
	 */
	public void play() {
		play(1.0f, 1.0f);
	}

	/**
	 * Play this sound effect at a given volume and pitch
	 * 
	 * @param pitch
	 *            The pitch to play the sound effect at
	 * @param volume
	 *            The volume to play the sound effect at
	 */
	public void play(float pitch, float volume) {
		index = sound.playAsSoundEffect(pitch, volume
				* SoundStore.get().getSoundVolume(), false);
		setBuff();
	}

	/**
	 * Play a sound effect from a particular location
	 * 
	 * @param x
	 *            The x position of the source of the effect
	 * @param y
	 *            The y position of the source of the effect
	 * @param z
	 *            The z position of the source of the effect
	 */
	public void playAt(float x, float y, float z) {
		playAt(1.0f, 1.0f, x, y, z);
	}

	/**
	 * Play a sound effect from a particular location
	 * 
	 * @param pitch
	 *            The pitch to play the sound effect at
	 * @param volume
	 *            The volume to play the sound effect at
	 * @param x
	 *            The x position of the source of the effect
	 * @param y
	 *            The y position of the source of the effect
	 * @param z
	 *            The z position of the source of the effect
	 */
	public void playAt(float pitch, float volume, float x, float y, float z) {
		index = sound.playAsSoundEffect(pitch, volume
				* SoundStore.get().getSoundVolume(), false, x, y, z);
		setBuff();
	}

	/**
	 * Loop this sound effect at default volume and pitch
	 */
	public void loop() {
		loop(1.0f, 1.0f);
	}

	/**
	 * Loop this sound effect at a given volume and pitch
	 * 
	 * @param pitch
	 *            The pitch to play the sound effect at
	 * @param volume
	 *            The volume to play the sound effect at
	 */
	public void loop(float pitch, float volume) {
		index = sound.playAsSoundEffect(pitch, volume
				* SoundStore.get().getSoundVolume(), true);
		setBuff();
	}

	/**
	 * Loop this sound effect at a given volume, pitch and position
	 * 
	 * @param pitch
	 *            The pitch to play the sound effect at
	 * @param volume
	 *            The volume to play the sound effect at
	 * @param x
	 *            The x position of the source of the effect
	 * @param y
	 *            The y position of the source of the effect
	 * @param z
	 *            The z position of the source of the effect
	 */
	public void loop(float pitch, float volume, float x, float y, float z) {
		index = sound.playAsSoundEffect(pitch, volume
				* SoundStore.get().getSoundVolume(), true, x, y, z);
		setBuff();
	}

	/**
	 * Affects the buffer, should occur only one time per sound
	 * Indicates also the 1st time the sound is played
	 */
	private void setBuff() {
		if (buffer == 0)
			buffer = AL10.alGetSourcei(index, AL10.AL_BUFFER);
		played = true;
	}

	/**
	 * Check if the sound is currently playing
	 * 
	 * @return True if the sound is playing
	 */
	public boolean playing() {
		//return sound.isPlaying();
		setSourceFromBuffer();
		if (buffer == 0 || index == -1)
			return false;
		return (AL10.alGetSourcei(index, AL10.AL_SOURCE_STATE) == AL10.AL_PLAYING);
	}

	/**
	 * Stop the sound being played
	 */
	public void stop() {
		if (!playing())
			return;
		// if the source doesn't any more contain the buffer, the sound is
		// already stopped so we don't need to stop
		if (AL10.alGetSourcei(index, AL10.AL_BUFFER) != buffer)
			return;
		sound.stop();
	}

	/**
	 * Returns he OpenAL buffer id of this sound Not useful in case of differed
	 * sounds...
	 * 
	 * @return id of the sound
	 */
	public int getID() {
		return sound.getBufferID();
	}

	/**
	 * Returns the index of the sound
	 * 
	 * @return index The actual index in OpenAL listening list
	 */
	public int getIndex() {
		return index;
	}

	/**
	 * Returns the buffer id
	 * 
	 * @return buffer the unique buffer id of this sound
	 */
	public int getBuffer() {
		return buffer;
	}
	
	public boolean playedOnce(){
		return played;
	}
	
	public void reinitPlayedOnce(){
		played = false;
	}
}
