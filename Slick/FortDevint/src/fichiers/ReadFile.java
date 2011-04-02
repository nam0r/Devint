package fichiers;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class ReadFile
{
    private BufferedReader br;
    private String buffer;
    private boolean eof;
    private boolean closed;

    public ReadFile(File file)
        throws IOException
    {
        br = new BufferedReader(new FileReader(file));
        closed = false;
        readNextLine();
    }

    public boolean eof()
        throws IOException
    {
        if(closed)
        {
            throw new IOException("Le fichier est déjà fermé");
        } else
        {
            return eof;
        }
    }

    public void close()
        throws IOException
    {
        if(closed)
        {
            throw new IOException("Le fichier est déjà fermé");
        } else
        {
            br.close();
            closed = true;
            return;
        }
    }

    public String peek()
        throws IOException
    {
        if(eof)
        {
            throw new IOException("Fin de fichier atteinte");
        }
        if(closed)
        {
            throw new IOException("Le fichier est fermé");
        } else
        {
            return buffer;
        }
    }

    public String read()
        throws IOException
    {
        if(eof)
        {
            throw new IOException("Fin de fichier atteinte");
        }
        if(closed)
        {
            throw new IOException("Le fichier est fermé");
        } else
        {
            String s = buffer;
            readNextLine();
            return s;
        }
    }

    private void readNextLine()
        throws IOException
    {
        String s = br.readLine();
        if(s == null)
        {
            eof = true;
        } else
        {
            buffer = s;
        }
    }
}
