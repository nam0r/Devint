package utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Extracting tools. Useful for deploying with jnlp webstart, to extract
 * executable from a jar to play it
 * 
 * @author namor
 * @author patatos http://patatos.over-blog.com
 */

public class ExtractFile {

	/**
	 * Extracts a resource out of the jar file, to be exploited
	 * 
	 * @param dest
	 *            the destination where the resource will be extracted
	 * @param resourceName
	 *            the name of the resource to extract from the jar
	 */
	public static void extractFileFromJAR(String dest, String resource,
			String fileName) {
		try {
			// the url to that place (file path or http)
			URL file = new URL(resource + fileName);
			InputStream in = file.openStream();
			// the destination file
			File efile = new File(dest, fileName);
			OutputStream out = new BufferedOutputStream(new FileOutputStream(
					efile));
			byte[] buffer = new byte[2048];
			for (;;) {
				int nBytes = in.read(buffer);
				if (nBytes <= 0)
					break;
				out.write(buffer, 0, nBytes);
			}
			out.flush();
			out.close();
			in.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Extracts files from a zip or jar file
	 * 
	 * @param dest
	 *            The destination to extract to
	 * @param file
	 *            the file to unzip
	 */
	public static void unzip(String dest, String file) {
		try {
			File zipFile = new File(file);
			File folder = new File(dest);
			// création de la ZipInputStream qui va servir à lire les données du
			// fichier zip
			ZipInputStream zis = new ZipInputStream(new BufferedInputStream(
					new FileInputStream(zipFile.getCanonicalFile())));

			// extractions des entrées du fichiers zip (i.e. le contenu du zip)
			ZipEntry ze = null;
			try {
				while ((ze = zis.getNextEntry()) != null) {

					// Pour chaque entrée, on crée un fichier
					// dans le répertoire de sortie "folder"
					File f = new File(folder.getCanonicalPath(), ze.getName());

					// Si l'entrée est un répertoire,
					// on le crée dans le répertoire de sortie
					// et on passe à l'entrée suivante (continue)
					if (ze.isDirectory()) {
						f.mkdirs();
						continue;
					}

					// L'entrée est un fichier, on crée une OutputStream
					// pour écrire le contenu du nouveau fichier
					f.getParentFile().mkdirs();
					OutputStream fos = new BufferedOutputStream(
							new FileOutputStream(f));

					// On écrit le contenu du nouveau fichier
					// qu'on lit à partir de la ZipInputStream
					// au moyen d'un buffer (byte[])
					try {
						try {
							final byte[] buf = new byte[8192];
							int bytesRead;
							while (-1 != (bytesRead = zis.read(buf)))
								fos.write(buf, 0, bytesRead);
						} finally {
							fos.close();
						}
					} catch (final IOException ioe) {
						// en cas d'erreur on efface le fichier
						f.delete();
						throw ioe;
					}
				}
			} finally {
				// fermeture de la ZipInputStream
				zis.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Deletes a file or a directory with its files
	 * 
	 * @param fileName
	 *            the file or directory
	 */
	public static void deleteFile(String fileName) {
		try {
			// A File object to represent the filename
			File f = new File(fileName);

			// Make sure the file or directory exists and isn't write
			// protected
			if (!f.exists())
				throw new IllegalArgumentException(
						"Delete: no such file or directory: " + fileName);
			if (!f.canWrite())
				throw new IllegalArgumentException("Delete: write protected: "
						+ fileName);

			// If it is a directory
			if (f.isDirectory()) {
				String[] files = f.list();
				// if it contains files, we delete also them
				if (files.length > 0) {
					for (int i = 0; i < files.length; i++)
						deleteFile(fileName + File.separator + files[i]);
				}

			}
			// Attempt to delete it
			boolean success = f.delete();
			if (!success)
				throw new IllegalArgumentException("Delete: deletion failed");
		} catch (final Exception e) {
			System.out.println(e);
			e.printStackTrace();
		}
	}

}
