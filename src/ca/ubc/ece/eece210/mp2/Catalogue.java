package ca.ubc.ece.eece210.mp2;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Container class for all the albums and genres. Its main 
 * responsibility is to save and restore the collection from a file.
 * 
 * @author Sathish Gopalakrishnan
 * 
 */
public final class Catalogue {
	private Genre DUMMY;
	
	/**
	 * Builds a new, empty catalogue.
	 */
	public Catalogue() {
		this.DUMMY = new Genre("DUMMY");
	}

	/**
	 * Builds a new catalogue and restores its contents from the 
	 * given file.
	 * 
	 * @param fileName
	 *            the file from where to restore the library.
	 */
	public Catalogue(String fileName) {
		String stringRepresentation = null;
		try {
			stringRepresentation = readFileContents(fileName);
		} catch (IOException e) {
			e.printStackTrace();
		}
		DUMMY = Genre.restoreCollection(stringRepresentation);
	}
	
	/**
	 * Takes the input file name and returns its contents as a single string.
	 * 
	 * @param fileName		the name of the file that is being read from.
	 * @return				the contents of the file in a single string.
	 * @throws IOException	If the file is not found or cannot be read.
	 */
	private String readFileContents(String fileName ) throws IOException {
		BufferedReader LibraryBufferReader = new BufferedReader(new FileReader(fileName));
		try {
	        StringBuilder LibraryStringBuilder = new StringBuilder();
	        String line = LibraryBufferReader.readLine();

	        while (line != null) {
	        	LibraryStringBuilder.append(line);
	        	LibraryStringBuilder.append("\n");
	            line = LibraryBufferReader.readLine();
	        }
	        return LibraryStringBuilder.toString();
	    } finally {
	    	LibraryBufferReader.close();
	    }
	}

	/**
	 * Saved the contents of the catalogue to the given file.
	 * 
	 * @param fileName 	the file where to save the library
	 */
	public void saveCatalogueToFile(String fileName) {

		PrintWriter outputToFile = null;
		try {
			outputToFile = new PrintWriter(fileName);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		outputToFile.print(DUMMY.toString());
		outputToFile.close();
	}
	
	/**
	 * Adds a given Genre to catalogue
	 * @param G a Genre
	 */
	public void addToCat(Genre G){
		DUMMY.addToGenre(G);
	}
	
	/**
	 * Removes a Genre from catalogue
	 * @param G
	 */
	public void removeFromCat(Genre G){
		DUMMY.removeFromGenre(G);
	}
	
	/**
	 * Tests catalogue equality by producing a string representation for each and comparing those
	 * @param C
	 * @return true if catalogues are equal, false otherwise
	 */
	public boolean equals(Catalogue C){
		return this.DUMMY.toString().equals( C.DUMMY.toString() );
	}
}