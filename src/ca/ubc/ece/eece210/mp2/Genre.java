package ca.ubc.ece.eece210.mp2;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a genre (or collection of albums/genres).
 * 
 * @author Sathish Gopalakrishnan
 * 
 * NOTE: Parts of the code has been reference from this repository available online: 
 * https://github.com/esecules/UBC_EECE/tree/master/EECE_210_L1A7/mp3
 * 
 */
public final class Genre extends Element {
	
//	private static Album[] albumsArray;
//	private static String stringRepresentation;
//	private Genre[] subGenres;
//	private boolean isSubGenre;
	
	/**
	 * Creates a new genre with the given name.
	 * 
	 * @param name
	 *            the name of the genre.
	 */
	public Genre(String name) {
		this.name = name;
	}

	/**
	 * Restores a genre from its given string representation.
	 * 
	 * @param stringRepresentation
	 */
	public static Genre restoreCollection(String stringRepresentation) {
		//split input strings at newline character
		String[] lines = stringRepresentation.split("\n");;
		
		return restoreCollectionRecursive(lines,0,0);
	}
	
	/**
	 * Restores a genre using recursive calls on same function for subgenres
	 * 
	 * @param lines
	 * 				array with items containing single line of string input
	 * @param index
	 * 				
	 * @param layer
	 * @return
	 */
	private static Genre restoreCollectionRecursive(String[] lines, int index, int level){
		Genre internalGenre = new Genre("");
		//internalGenre.name = null;
		Boolean insideSubGenre = false;
		int depth = level;
		int numLines = lines.length;
		
		for (int i = index; i < numLines; i++){
			String lineInput = lines[i];

			Pattern SubGenreBegin = Pattern.compile("<subGenre>");
			Matcher MatchSubGenreStart = SubGenreBegin.matcher(lineInput);
			boolean subGenreBegins = MatchSubGenreStart.find();
			Pattern SubGenreEnd = Pattern.compile("</subGenre>");
			Matcher MatchSubGenreEnd = SubGenreEnd.matcher(lineInput);
			boolean subGenreEnds = MatchSubGenreEnd.find();
			
			Pattern GenreName = Pattern.compile("<genreName>(.*)</genreName>");
			Matcher MatchGenreName = GenreName.matcher(lineInput);
			boolean genreNameFound = MatchGenreName.find();
			
			Pattern Album = Pattern.compile("<album>(.*)</album>");
			Matcher MatchAlbum = Album.matcher(lineInput);
			boolean albumFound = MatchAlbum.find();
			
			Pattern End = Pattern.compile("<END>");
			Matcher MatchEnd = End.matcher(lineInput);
			boolean endFound = MatchEnd.find();
			
			if(genreNameFound && !insideSubGenre){
				internalGenre.name = MatchGenreName.group(1);
				//System.out.println("Found genre's name " + internalGenre.name );
			}
			if(albumFound && !insideSubGenre){
				internalGenre.addToGenre(new Album(MatchAlbum.group(1)));
			}
			if(subGenreBegins){
				System.out.println("Enterting new depth:" + depth);
				depth ++;
				if (!insideSubGenre){
					//recursive call returns to add subgenre to current parent genre
				    internalGenre.addToGenre(restoreCollectionRecursive(lines,i+1, depth));
				}
				insideSubGenre = true;
			}
			if(subGenreEnds && insideSubGenre){
				depth --;
				System.out.println("Now at " + depth + " layer(s) deep");
				
			}
			MatchSubGenreEnd.reset();
			if((MatchSubGenreEnd.find() || endFound) && !insideSubGenre){
				//System.out.println("Returning from genre " + internalGenre.name);
				if(internalGenre.name == null)
					throw new IllegalArgumentException("Could not find the genre's name!");
				else
					return internalGenre;
			}
			if(depth == level){
				insideSubGenre = false;	
			}	
		}
		throw new IllegalArgumentException("Could not find the end genre tag");
	}

	/**
	 * Returns the string representation of a genre
	 * 
	 * @return
	 */
	public String toString() {
		return this.getStringRepresentation() + "\n<END>";
	}
	
	/**
	 * Constructs the string representation of genre using recursive calls for sub genre
	 * 
	 * @return
	 */
	private String getStringRepresentation() {
		List<Element> childrenElements = this.getChildren();
		List<String> childStrings = new ArrayList<String>();
		
		for( Element child : childrenElements ){
			if(child.hasChildren()){
				//System.out.println(child.name + " has Children");
				childStrings.add("\n<subGenre>"+((Genre) child).getStringRepresentation()+"\n</subGenre>");
				
			}
			else{
				//System.out.println(child.name + " has NO Children");
				childStrings.add("\n<album>"+((Album) child).toString()+"</album>");
			}
		}
		return "\n<genreName>"+ name +"</genreName>"+ childStrings ;
	}
		

	/**
	 * Adds the given album or genre to this genre
	 * 
	 * @param b
	 *            the element to be added to the collection.
	 */
	public void addToGenre(Element b) {
		try{
			//System.out.println("adding child " + b.name + " to " + this.name);
			if (this.name == b.name)
				throw new IllegalArgumentException("Cannot add a genre to itself!");	
			
			for (String line : this.getAllChildren()){
				if (line.matches(".*" + b.name + ".*")){
					throw new IllegalArgumentException("Recursive hierarchy is not allowed!");
				}
			}
			this.addChild(b);
		} finally
		{}
	}
	
	/**
	 * Delete a given album or subgenre from a genre
	 * 
	 * @param c
	 */
	public void removeFromGenre(Element c){
		try{
			removeChild(c);
		}catch(IllegalArgumentException e){
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * Caller method for the recursive method getAllChildrenRecursive
	 * @return an array list of all the children (and grand children,..)  of this genre 
	 */
	
	public ArrayList<String> getAllChildren(){ 
		return getAllChildrenRecursive(this,1);
	}
	
	/**
	 * 
	 * @param E the element whose children we must discover and build into string
	 * @param level the the current level in the hierarchy
	 * @return
	 */
	private ArrayList<String> getAllChildrenRecursive( Element E, int level) {
		ArrayList<String> childrenList = new ArrayList<String>();
		if( E.hasChildren() ) {
			// E must be a genre
			for( Element child : E.getChildren() ) {
				Genre subGenre = new Genre(child.name);
				childrenList.add( subGenre.name + " " + level + getAllChildrenRecursive(subGenre, level + 1));
			}
		}
		else 
			childrenList.add(((Album)E).name + " " + ((Album)E).performer + " " + ((Album)E).songlist ); //use type casting

		return childrenList;
	}
	
	/**
	 * Returns true, since a genre can contain other albums and/or
	 * genres.
	 */
	@Override
	public boolean hasChildren() {
		return true;
	}
}