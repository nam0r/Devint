/*
 *	SequenceAudioInputStream.java
 * Copyright (c) 1999 - 2001 by Matthias Pfisterer
 */
package t2s.son;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

public class SequenceAudioInputStream extends AudioInputStream {

    private List m_audioInputStreamList;
    private int	m_nCurrentStream;

    @SuppressWarnings("unchecked")
	public SequenceAudioInputStream(AudioFormat audioFormat, Collection audioInputStreams)
    {
    	super(new ByteArrayInputStream(new byte[0]), audioFormat, AudioSystem.NOT_SPECIFIED);
		m_audioInputStreamList = new ArrayList(audioInputStreams);
		m_nCurrentStream = 0;
    }

    private AudioInputStream getCurrentStream()
    {
    	return (AudioInputStream) m_audioInputStreamList.get(m_nCurrentStream);
    }

    private boolean advanceStream()
    {
    	m_nCurrentStream++;
    	boolean	bAnotherStreamAvailable = (m_nCurrentStream < m_audioInputStreamList.size());
    	return bAnotherStreamAvailable;
    }

    public long getFrameLength()
    {
    	long lLengthInFrames = 0;
    	Iterator streamIterator = m_audioInputStreamList.iterator();
    	while (streamIterator.hasNext())
    	{
    		AudioInputStream stream = (AudioInputStream) streamIterator.next();
    		long lLength = stream.getFrameLength();
    		if (lLength == AudioSystem.NOT_SPECIFIED)
    		{
    			return AudioSystem.NOT_SPECIFIED;
    		}
    		else 
    			lLengthInFrames += lLength;
    	}
    	return lLengthInFrames;
    }

    public int read() throws IOException
    {
    	AudioInputStream stream = getCurrentStream();
    	int nByte = stream.read();
    	if (nByte == -1)
    	{ /* The end of the current stream has been signaled.We try to advance to the next stream.  */
    		boolean	bAnotherStreamAvailable = advanceStream();
    		if (bAnotherStreamAvailable)
    		{ /* There is another stream. We recurse into this method to read from it.*/
    			return read();
    		}
    		else
    		{ /*No more data. We signal EOF.*/
    			return -1;
    		}
    	}
    	else
    	{/* The most common case: We return the byte.*/
    		return nByte;
    	}
    }

    public int read(byte[] abData, int nOffset, int nLength) throws IOException
    {
    	AudioInputStream stream = getCurrentStream();
    	int	nBytesRead = stream.read(abData, nOffset, nLength);
    	if (nBytesRead == -1)
    	{ /*The end of the current stream has been signaled. We try to advance to the next stream.*/
    		boolean	bAnotherStreamAvailable = advanceStream();
    		if (bAnotherStreamAvailable)
    		{ /* There is another stream. We recurse into this method to read from it.*/
    			return read(abData, nOffset, nLength);
    		}
    		else
    		{ /* No more data. We signal EOF.*/
    			return -1;
    		}
    	}
    	else
    	{ /* The most common case: We return the length.*/
    		return nBytesRead;
    	}
    }

    public long skip(long lLength) throws IOException
    {
    	throw new IOException("skip() is not implemented.");
    }

    public int available() throws IOException
    {
    	return getCurrentStream().available();
    }

    public void close() throws	IOException {}

    public void mark(int nReadLimit)
    {
    	throw new RuntimeException("mark() is not implemented.");
    }

    public void reset()	throws	IOException
    {
    	throw new IOException("reset() is not implemented.");
    }

    public boolean markSupported()
    {
	return false;
    }

    private static void out(String strMessage) 
    {
    	System.out.println(strMessage);
    }
}

/*** SequenceAudioInputStream.java ***/
