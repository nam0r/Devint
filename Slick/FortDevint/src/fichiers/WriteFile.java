package fichiers;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteFile
{
    private BufferedWriter bw;

    public WriteFile(File file)
        throws IOException
    {
        bw = new BufferedWriter(new FileWriter(file));
    }

    public void write(String s)
        throws IOException
    {
        bw.write(s);
    }

    public void newLine()
        throws IOException
    {
        bw.newLine();
    }

    public void close()
        throws IOException
    {
        bw.close();
    }
}
