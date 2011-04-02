package son;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;


public class LecteurFichierWav extends Thread{

	private AudioFormat format;
	private byte[] samples;
	private InputStream source;
	
	public LecteurFichierWav(String nomFichier) {
		try {
			AudioInputStream stream = AudioSystem.getAudioInputStream(new File(nomFichier));
			format = stream.getFormat();
			samples = getSamples(stream);
		}
		catch(UnsupportedAudioFileException e) {
			e.printStackTrace();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		source = new ByteArrayInputStream(getSamples());
	}
	
	public byte[] getSamples() {
		return samples;
	}
	
	public byte[] getSamples(AudioInputStream stream) {
		int length =(int)(stream.getFrameLength() * format.getFrameSize());
		byte[] samples = new byte[length];
		DataInputStream in = new DataInputStream(stream);
		try {
			in.readFully(samples);
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		return samples;
	}
	
	public void play() {
		start();
	}
	
	public void start() {
		int bufferSize = format.getFrameSize() * Math.round(format.getSampleRate() / 10);
		byte[] buffer = new byte[bufferSize];
		SourceDataLine line;
		try {
			DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
			line = (SourceDataLine) AudioSystem.getLine(info);
			line.open(format, bufferSize);
		}
		catch(LineUnavailableException e) {
			e.printStackTrace();
			return;
		}
		line.start();
		try {
			int numBytesRead = 0;
			while(numBytesRead != -1) {
				numBytesRead = source.read(buffer,0,buffer.length);
				if (numBytesRead != -1)
					line.write(buffer,0,numBytesRead);
			}
		}
		catch(IOException e) {
			e.printStackTrace();
		}
		line.drain();
		line.close();
	}
	
	
}
