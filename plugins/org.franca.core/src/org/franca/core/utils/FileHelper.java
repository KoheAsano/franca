package org.franca.core.utils;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.eclipse.emf.common.util.URI;

public class FileHelper {

	/**
	 * Helper for saving a text to file
	 *
	 * @param targetDir   the target directory
	 * @param filename    the target filename
	 * @param textToSave  the content for the file
	 * @return true if ok
	 */
	public static boolean save (String targetDir, String filename, String textToSave) {
		// ensure that directory is available
		File dir = new File(targetDir);
		if (! (dir.exists() || dir.mkdirs())) {
			System.err.println("Error: couldn't create directory " + targetDir + "!");
			return false;
		}
		
		// delete file prior to saving
		File file = new File(targetDir + "/" + filename);
		file.delete();
		
		// save contents to file
	    try {
	        BufferedWriter out = new BufferedWriter(new FileWriter(file));
	        out.write(textToSave);
	        out.close();
	        System.out.println("Created file " + file.getAbsolutePath());
	    } catch (IOException e) {
	    	return false;
	    }
	    
	    return true;
	}

	/**
	 * Platform-independent helper to create URIs from file names.
	 * 
	 * Rationale: createFileURI is platform-dependent and doesn't work
	 * for absolute paths on Unix and MacOS. This function provides 
	 * createURI from file paths for Unix, MacOS and Windows.
	 */
	public static URI createURI(String filename) {
		URI uri = URI.createURI(filename);

		String os = System.getProperty("os.name");
		boolean isWindows = os.startsWith("Windows");
		boolean isUnix = !isWindows; // this might be too clumsy...
		if (uri.scheme() != null) {
			// If we are under Windows and s starts with x: it is an absolute path
			if (isWindows && uri.scheme().length() == 1) {
				return URI.createFileURI(filename);
			}
			// otherwise it is a proper URI
			else {
				return uri;
			}
		}
		// Handle paths that start with / under Unix e.g. /local/foo.txt
		else if (isUnix && filename.startsWith("/")) { 
			return URI.createFileURI(filename);
		}
		// otherwise it is a proper URI
		else {
			return uri;
		}
	}

}