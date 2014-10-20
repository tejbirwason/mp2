package ca.ubc.ece.eece210.mp2;

import java.util.ArrayList;

import org.apache.commons.lang3.StringUtils;

/**
 * 
 * @author Sathish Gopalakrishnan
 * 
 * This class contains the information needed to represent 
 * an album in our application.
 * 
 */

public final class Album extends Element { 
	public String performer; 
	public ArrayList<String> songlist = new ArrayList<String>();
	private final int INITIAL_VALUE = 0;
	public Genre genre;
	
	
	/**
	 * Builds an album with the given title, performer and song list
	 * 
	 * @param title
	 *            the title of the album
	 * @param author
	 *            the performer 
	 * @param songlist
	 * 			  the list of songs in the album
	 */
	public Album(String title, String performer, ArrayList<String> songlist) {
		this.name = title;
		this.performer = performer;
		this.songlist = songlist;
	}
	
	
	/**
	 * Builds an album from the string representation of the object. It is used
	 * when restoring an album from a file.
	 * 
	 * @param stringRepresentation
	 *            the string representation
	 */
	public Album(String stringRepresentation) {

		// find all songs inside album and store as temporary string array
		String[] tempSongsArray = StringUtils.substringsBetween(stringRepresentation, "<song>", "</song>");
		
		// construct ArrayList<String> of songs from tempSongs
		if ( tempSongsArray.length == 0 ) {
			throw new IllegalArgumentException("Album is empty! No songs!");
		} else {
			for ( int index = 0; index < tempSongsArray.length; index++ ){
				this.songlist.add(tempSongsArray[index]);
			}
		}
		
		String tempPerformerArray[] = StringUtils.substringsBetween(stringRepresentation, "<performer>", "</performer>");
		if ( tempPerformerArray.length != 1 ) {
			throw new IllegalArgumentException("Album cannot contain zero or more than one performer!");
		} else {
			this.performer = tempPerformerArray[INITIAL_VALUE];
			//System.out.println(this.performer);
		}
		

		String tempTitleArray[] = StringUtils.substringsBetween(stringRepresentation, "<albumtitle>", "</albumtitle>");
		if ( tempTitleArray.length != 1 ) {
			throw new IllegalArgumentException("Album contains zero or more than one title!");
		} else {
			this.name = tempTitleArray[INITIAL_VALUE];
			//System.out.println(this.name);
		}
		
	}

	/**
	 * Returns the string representation of the given album. The representation
	 * contains the title, performer and songlist, as well as all the genre
	 * that the book belongs to.
	 * 
	 * @return the string representation
	 */
	public String toString() {

		String stringRepresentation = "";
		for (int index = 0; index < songlist.size(); index++) {
			stringRepresentation = stringRepresentation.concat("<song>" + songlist.get(index) + "</song>");
		}

		String prefix = "<performer>" + this.performer + "</performer>";
		stringRepresentation = prefix.concat(stringRepresentation);
		
		prefix = "<albumtitle>" + this.name + "</albumtitle>";
		stringRepresentation = prefix.concat(stringRepresentation);
		
		return stringRepresentation;
	}

	/**
	 * Add the book to the given genre
	 * 
	 * @param genre
	 *            the genre to add the album to.
	 */
	public void addToGenre(Genre genre) {
		genre.addToGenre(this);
		this.genre = genre;
	}
	
	/**
	 * removes an this album from the given genre
	 * @param genre
	 */
	public void removeFromGenre(Genre genre){
		genre.removeFromGenre(this);
		this.genre = null;
	}

	/**
	 * Returns the genre that this album belongs to.
	 * 
	 * @return the genre that this album belongs to
	 */
	public Genre getGenre() {
		return this.genre;
	}

	/**
	 * Returns the title of the album
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return name;
	}

	/**
	 * Returns the performer of the album
	 * 
	 * @return the performer
	 */
	public String getPerformer() {
		return performer;
	}
	
	/**
	 * Returns the song array list of the album
	 * 
	 * @return the songlist
	 */
	public ArrayList<String> getSongs() {
		return songlist;
	}
	

	/**
	 * An album cannot have any children (it cannot contain anything).
	 */
	@Override
	public boolean hasChildren() {
		return false;
	}
}