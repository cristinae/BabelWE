package cat.trachemys.interlingua.basics;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;


public class FileIO {
	public final static String separator = System.getProperty("file.separator");

	static final int BUFF_SIZE = 100000;
	static final byte[] buffer = new byte[BUFF_SIZE];


	/**
	 * Gets all the files with a given extension {@code ext}
	 * @param dir
	 * @param ext
	 * @return 
	 *       A List<String> with the files
	 */
	public static List<String> getFilesExt(File dir, final String ext) {
		List<String> fileList = null;
		if (dirCanBeRead(dir)) {
			fileList = new LinkedList<String>();
			String[] list = dir.list();

			for (String s : list) {
				String fileName = dir.getAbsolutePath() + separator + s;
				File f = new File(fileName);
				if (!f.isDirectory() && s.toLowerCase().endsWith(ext)) {
					fileList.add(fileName);
				}
			}
		}
		//if (fileList != null)
		//	Collections.sort(fileList, String.CASE_INSENSITIVE_ORDER);

		return fileList;
	}

	
	/**
	 * Check whether the directory exists and can be read.
	 * 
	 * @param dir  
	 * @return true if directory can be read
	 */
	public static boolean dirCanBeRead(File dir) {
		if (dir.isDirectory() && dir.canRead())
			return true;
		return false;
	}

	/**
	 * Create a directory if it does not exist
	 * @param dir
	 * @throws IOException
	 */
	public static void createDir(File dir) throws IOException {
		if (dir.isDirectory())
			return;
		dir.mkdir();
	}


	/**
	 * Opens a file and returns the lines in it
	 * 
	 * @param f
	 * @return array of
	 * @throws IOException
	 */
	public static String[] fileToLines(File f) throws IOException {
		List<String> lines = new ArrayList<String>();
		String line;

		FileReader fr = new FileReader(f);
		BufferedReader br = new BufferedReader(fr);
		try {
			while ((line = br.readLine()) != null) {
				lines.add(line);
			}
		} finally {
			br.close();
			fr.close();
		}
		return lines.toArray(new String[lines.size()]);
	}


	/**
	 * Reads a file into a string
	 * @param f
	 * @return
	 * @throws IOException
	 */
	public static String fileToString(File f) throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		FileInputStream input = new FileInputStream(f);
		FileChannel inputChannel = input.getChannel();
		try {
			ByteBuffer inputBuffer = ByteBuffer.allocateDirect(4096);
			byte[] buf = new byte[4096];

			int len;
			long c = 0;
			while ((len = inputChannel.read(inputBuffer)) >= 0) {
				inputBuffer.flip();
				inputBuffer.get(buf, 0, len);
				// Dont read UTF-8 Header
				if (c == 0 && buf[0] == -17 && buf[1] == -69 && buf[2] == -65)
					baos.write(buf, 3, len - 3);
				else
					baos.write(buf, 0, len);
				inputBuffer.flip();
				c += len;
			}
			if (c != inputChannel.size()) {
				throw new IllegalStateException(c + " != " + len);
			}
		} finally {
			inputChannel.close();
			input.close();
		}

		String result = baos.toString("UTF-8");
		baos.close();
		return result;
	}

	/***
	 * Writes a string into a file
	 * 
	 * @param f
	 * @param text
	 * @param bom
	 * @throws IOException
	 */
	public static void stringToFile(File f, String text, boolean bom)
			throws IOException {
		if ((f.getParent()) != null && !(new File(f.getParent()).exists()))
			new File(f.getParent()).mkdirs();

		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// Write UTF-8 BOM
		if (bom)
			baos.write(new byte[] { -17, -69, -65 });
		PrintStream printer = new PrintStream(baos, true, "UTF-8");
		printer.print(text);
		printer.close();
		// log.debug("new FileOutputStream: " + f.getAbsolutePath());
		FileOutputStream fos = new FileOutputStream(f);
		baos.writeTo(fos);
		baos.close();
		fos.close();
		// log.debug("close FileOutputStream: " + f.getAbsolutePath());

	}

	/**
	 * Appends a string into an existing file
	 * 
	 * @param f
	 * @param text
	 * @param bom
	 * @throws IOException
	 */
	public static void appendStringToFile(File f, String text, boolean bom)
			throws IOException {
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		// Write UTF-8 BOM
		if (bom)
			baos.write(new byte[] { -17, -69, -65 });
		PrintStream printer = new PrintStream(baos, true, "UTF-8");
		printer.print(text);
		printer.close();
		FileOutputStream fos = new FileOutputStream(f, true);   //this is the only difference for appending
		baos.writeTo(fos);
		baos.close();
		fos.close();
	}


}
