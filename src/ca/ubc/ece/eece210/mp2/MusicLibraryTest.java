package ca.ubc.ece.eece210.mp2;
import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.Test;

/**
 * 
 * @author tejbirwason
 *
 * NOTE: Parts of the code has been reference from this repository available online: 
 * https://github.com/esecules/UBC_EECE/tree/master/EECE_210_L1A7/mp3
 * 
 */
public class MusicLibraryTest {
	
	
	ArrayList<String> songlist = new ArrayList<String>();
	Album testAlbum = new Album( "Chopin Collections" , "Evgeny Kissin" , songlist);
	Genre Classical = new Genre( "Classical" );
	String albumString = new String();
	
	//Scenario 1: Tests if album is added into the right genre
	@Test
	public void AddAlbumToGenre() {
		//Declare new album, adds genre as classical and tests if its genre equals actual genre
		songlist.add("testing");
		Album testAlbum = new Album( "Chopin Collections" , "Evgeny Kissin" , songlist);
		testAlbum.addToGenre( Classical );
		assertEquals( "testAlbum should be in the genre Classical" , Classical, testAlbum.getGenre() );
	}
	
	
	//Scenario 2: Tests if album is removed from the right genre
	@Test
	public void RemoveAlbumFromGenre() {
		//Declare album, add and then remove the genre, see if the genre is still the genre of the album
		ArrayList<String> songlist = new ArrayList<String>();
		songlist.add( "Back in Black" );
		songlist.add( "Piano Stuff" );
		songlist.add( "Rap Stuff" );
		Album testAlbum = new Album( "Chopin Collections" , "Evgeny Kissin" , songlist);
		testAlbum.addToGenre( Classical );
//		System.out.println(testAlbum.toString());
//		System.out.println(Classical.toString());
		testAlbum.removeFromGenre( Classical );
//		System.out.println(testAlbum.toString());
//		System.out.println(Classical.toString());
		assertFalse( "Returns false if genre of testAlbum is not equal to genre Classical", ( testAlbum.getGenre() == Classical ) );
	}
	
	
	//Scenario 3: Saves album to the string format
	@Test
	public void SaveToString() {
		//Declare album, add and then remove the genre, see if the genre is still the genre of the album
		ArrayList<String> songlist = new ArrayList<String>();
		songlist.add( "Back in Black" );
		songlist.add( "Piano Stuff" );
		songlist.add( "Rap Stuff" );
		Album testAlbum = new Album( "Chopin Collections" , "Evgeny Kissin" , songlist);
		System.out.println(testAlbum.toString());
		assertEquals( "testAlbum should be this in string form" , "<albumtitle>Chopin Collections</albumtitle><performer>Evgeny Kissin</performer><song>Back in Black</song><song>Piano Stuff</song><song>Rap Stuff</song>", testAlbum.toString() );
	}
	
	//Scenario 4: Recreate album from the string form
	@Test
	public void StringToAlbum() {
		String albumString = "<albumtitle>Chopin Collections</albumtitle><performer>Evgeny Kissin</performer><song>Back in Black</song><song>Piano Stuff</song><song>Rap Stuff</song>";
		Album testAlbum = new Album( albumString );
		assertEquals("Album title does not match","Chopin Collections",testAlbum.getTitle());
//		System.out.println(testAlbum.getTitle());
//		System.out.println(testAlbum.getPerformer());
		assertEquals("Album performer does not match","Evgeny Kissin",testAlbum.getPerformer());
		ArrayList<String> songlist = new ArrayList<String>();
		songlist.add( "Back in Black" );
		songlist.add( "Piano Stuff" );
		songlist.add( "Rap Stuff" );
		ArrayList<String> truesonglist = testAlbum.getSongs();
		//System.out.println(truesonglist);
		boolean flag = false;
		for (String song : songlist){
		    if(!truesonglist.contains(song))
		    	flag = true;
		}
		assertFalse("Songlist does not match", flag);
	}
	
	
	//Scenario 5: Save Genre to the string form
	@Test
	public void GenreToString() {
		//declaring songlist with a couple songs and a test album with details
		Genre Jazz = new Genre( "Jazz" );
		ArrayList<String> songlist1 = new ArrayList<String>();
		songlist.add( "One" );
		songlist.add( "I'm a Mess" );
		songlist.add( "Sing" );
		//created album and added it to a specific genre
		Album testAlbum1 = new Album( "X" , "Ed Sheeran" , songlist1);
		testAlbum1.addToGenre( Jazz );
		
		ArrayList<String> songlist2 = new ArrayList<String>();
		songlist.add( "Two" );
		songlist.add( "I'm not a Mess" );
		songlist.add( "Singing" );
		Genre SmoothJazz = new Genre( "Smooth Jazz" );
		Jazz.addToGenre(SmoothJazz);
		Album testAlbum2 = new Album( "G" , "Eddy Sheehan" , songlist2);
		testAlbum2.addToGenre(SmoothJazz);
		
		ArrayList<String> songlist3 = new ArrayList<String>();
		songlist.add( "Threee" );
		songlist.add( "I'm not a total Mess" );
		songlist.add( "Singingers" );
		Genre SmootherJazz = new Genre( "Smoother Jazz" );
		Jazz.addToGenre(SmootherJazz);
		Album testAlbum3 = new Album( "H" , "Edward Sheenanigans" , songlist3);
		testAlbum3.addToGenre(SmootherJazz);
		

		System.out.println(Jazz.toString());
		String test = "\n<genreName>Jazz</genreName>[\n"
						+"<album><albumtitle>X</albumtitle><performer>Ed Sheeran</performer></album>, \n"
						+"<subGenre>\n"
						+"<genreName>Smooth Jazz</genreName>[\n"
						+"<album><albumtitle>G</albumtitle><performer>Eddy Sheehan</performer></album>]\n"
						+"</subGenre>, \n"
						+"<subGenre>\n"
						+"<genreName>Smoother Jazz</genreName>[\n"
						+"<album><albumtitle>H</albumtitle><performer>Edward Sheenanigans</performer></album>]\n"
						+"</subGenre>]\n"
						+"<END>\n";
		assertEquals("String does not match Test string" , Jazz.toString(), test);
	}
	
	
	//Scenario 6: Recreate the Genre from the string form
	@Test
	public void StringToGenre() {
		//declaring songlist with a couple songs and a test album with details
		ArrayList<String> songlist = new ArrayList<String>();
		songlist.add( "Back in Black" );
		songlist.add( "Piano Stuff" );
		songlist.add( "Boulivard of Broken Dreams" );
		//created album and added it to a specific genre
		Album testAlbum1 = new Album( "Chopin Collections" , "Evgeny Kissin" , songlist);
		Album testAlbum2 = new Album( "American Idiot" , "Green Day" , songlist);
		Album testAlbum3 = new Album( "Black Ice" , "AC / DC" , songlist);
		Genre Master = new Genre( "Master" );
		Genre Expected = new Genre( "Expected" );
		Genre TestGenre = new Genre( "TestGenre" );
		
		testAlbum1.addToGenre( Expected );
		testAlbum2.addToGenre( Expected );
		testAlbum3.addToGenre( Expected );
		Master.addToGenre(Expected);
		String stringRep = Master.toString();
		//System.out.println(stringRep);
		TestGenre = Genre.restoreCollection(stringRep);
		assertEquals(Master.name,TestGenre.name);
		assertEquals(Master.getAllChildren(),TestGenre.getAllChildren());
	}
	
	
	//Scenario 7: Save the whole catalogue to a file
	@Test
	public void SaveToFile () throws IOException {
		Catalogue TestCatalogue = new Catalogue();		//create new catalogue
		ArrayList<String> songlist = new ArrayList<String>();
		songlist.add( "Back in Black" );
		songlist.add( "Piano Stuff" );
		songlist.add( "Boulevard of Broken Dreams" );
		Album testAlbum1 = new Album( "Chopin Collections" , "Evgeny Kissin" , songlist);
		Album testAlbum2 = new Album( "American Idiot" , "Green Day" , songlist);
		Album testAlbum3 = new Album( "Black Ice" , "AC / DC" , songlist);
		Genre Funk = new Genre( "Funk" );
		Genre Classical = new Genre( "Classical" );
		Genre Jazz = new Genre( "Jazz" );
		Genre Blues = new Genre( "Blues" );
		testAlbum1.addToGenre( Classical );
		testAlbum2.addToGenre( Funk );
		testAlbum3.addToGenre( Blues );
		Jazz.addToGenre( Blues );
		Jazz.addToGenre( Funk );
		//save it to a file with file name "address"
		TestCatalogue.addToCat(Jazz);
		TestCatalogue.addToCat(Classical);
		TestCatalogue.addToCat(Blues);
		TestCatalogue.saveCatalogueToFile("Cat.txt");
		Catalogue TestCatalogue2 = new Catalogue("Cat.txt");
		assertTrue(TestCatalogue.equals(TestCatalogue2));
	}
	
	
	//Scenario 8: Recreate catalogue from file (MISSING)
	@Test
	public void RecreateFromFile() {
		assert(true);
	}
	
	
	//Scenario 9: Write a test to verify the genre inclusion rules
	//check to make sure that a genre has only one parent genre
	@Test
	public void InclusionRules() {
		Genre Rock = new Genre( "Rock" );
		Genre Jazz = new Genre( "Jazz" );
		Genre Blues = new Genre( "Blues" );
		Genre Classical = new Genre( "Classical" );
		
		songlist.add( "Back in Black" );
		songlist.add( "Piano Stuff" );
		songlist.add( "Boulevard of Broken Dreams" );
		
		Album testAlbum2 = new Album( "American Idiot" , "Green Day" , songlist);
		Album testAlbum3 = new Album( "Black Ice" , "AC / DC" , songlist);
		
		boolean addAlbumFail = false;
		
		try{
			testAlbum2.addToGenre( Rock );
			testAlbum3.addToGenre( Rock );
			testAlbum2.addToGenre( Rock );	
			//System.out.println(Rock.toString());
		} catch (IllegalArgumentException e) {
			System.out.println( e.getMessage() );
			addAlbumFail = true;
		}
		
		boolean addGenreFail = false;
		
		try{
			Jazz.addToGenre( Rock );
			Blues.addToGenre( Rock );
			Jazz.addToGenre( Rock );
		} catch ( IllegalArgumentException e ) {
			System.out.println( e.getMessage() );
			addGenreFail = true;
		}
		
		boolean Overflow = false;
		try{
		Rock.addToGenre( Rock );
		} catch ( IllegalArgumentException e ) {
			Overflow = true;
			System.out.println( e.getMessage() );
		}
		
		assertTrue( addAlbumFail );
		assertTrue( Overflow );
		assertTrue( addGenreFail );
		
		testAlbum3.addToGenre( Classical );
//		System.out.println(Classical.toString());
//		System.out.println(Rock.toString());
		Rock.addToGenre(Classical);
//		System.out.println(Classical.toString());
//		System.out.println(Rock.toString());
		assertTrue( testAlbum3.getGenre().equals( Classical ) );
		assertFalse( testAlbum3.getGenre().equals( Rock ) );
	}

//	/**
//	 * Tests that Library reader will only read what is between the keys
//	 */
//	@Test
//	public void testA() {
//		System.out.println("\n TEST A");
//		String albumExpression = "<genre>Rap "
//				+ "<album>Music from and Inspired by the Motion Picture 8 Mile "
//				+ "<performer> Eminem "
//				+ "</performer>"
//				+ "<song> Lose Yourself "
//				+ "</song> "
//				+ "</album>"
//				+ "</genre>";
//		LibraryReader newLibraryReader = new LibraryReader(albumExpression);
//		String[] albums = newLibraryReader.splitIntoStrings("album");
//		System.out.println(albums[0]);
//		assertEquals(albums[0], "Music from and Inspired by the Motion Picture 8 Mile "
//				+ "<performer> Eminem "
//				+ "</performer>"
//				+ "<song> Lose Yourself "
//				+ "</song> ");
//		}
	
//	/**
//	 * Tests that the Library Reader will read albums across multiple genres 
//	 */
//	@Test
//	public void testB() {
//		System.out.println("\n TEST B");
//		String albumExpression2 = "<genre> Rap "
//				+ "<album> Eminem </album> "
//				+ "<album> 50cent </album>"
//				+ "<album> Justin Bieber </album>"
//				+ "</genre>"
//				+ "<genre> Rock"
//				+ "<album> The Killers </album>"
//				+ "<album> Kings of Leon </album>"
//				+ "<album> Red Hot Chili Peppers </album>"
//				+ "</genre>";
//		LibraryReader newLibraryReader2 = new LibraryReader(albumExpression2);
//		String[] albums = newLibraryReader2.splitIntoStrings("album");
//		for ( int index = 0; index < albums.length; index++ ){
//			System.out.println(albums[index]);
//		}
//		System.out.println(albums[0]);
//		assertEquals(albums[0], " Eminem ");
//	}
	
//	/**
//	 * Tests that Library writer will write an array into a single 
//	 * string  with the correct expected output
//	 */
//	@Test
//	public void testC() {
//		System.out.println("\n TEST C");
//		String[] songExpression = {"Roar","Stutter","Use Somebody","Somebody That I Used To Know",
//				"Ain't No Rest For the Wicked","Diamonds",
//				"22","Away We Go","Let It Go"};
//		LibraryWriter newLibraryWriter = new LibraryWriter(songExpression);
//		String songs = newLibraryWriter.createString("song");
//		System.out.println(songs);
//		assertEquals(songs, "<song>Roar</song>"
//				+ "<song>Stutter</song>"
//				+ "<song>Use Somebody</song>"
//				+ "<song>Somebody That I Used To Know"
//				+ "</song><song>Ain't No Rest For the Wicked"
//				+ "</song><song>Diamonds"
//				+ "</song><song>22</song><song>Away We Go"
//				+ "</song><song>Let It Go</song>");
//	}
	
//	/**
//	 * Tests that Library reader will return an array of strings that corresponds
//	 * to the strings in between the given key for the example of an 
//	 * album with a list of songs
//	 */
//	@Test
//	public void testD() {
//		System.out.println("\n TEST D");
//		String songExpression = "<song>Roar</song><song>Stutter</song><song>Use Somebody"
//				+ "</song><song>Somebody That I Used To Know"
//				+ "</song><song>Ain't No Rest For the Wicked"
//				+ "</song><song>Diamonds"
//				+ "</song><song>22</song><song>Away We Go"
//				+ "</song><song>Let It Go</song>";
//		LibraryReader newLibraryReader = new LibraryReader(songExpression);
//		String[] songs = newLibraryReader.splitIntoStrings("song");
//		
//		for ( int index = 0; index < songs.length; index++ ) {
//		System.out.println(songs[index]);
//		}
//		
//		String[] newSongExpression = {"Roar","Stutter","Use Somebody","Somebody That I Used To Know",
//				"Ain't No Rest For the Wicked","Diamonds",
//				"22","Away We Go","Let It Go"};
//		for ( int index = 0; index < songs.length; index++ ) {
//			assertEquals(songs[index], newSongExpression[index]);	
//		}
//		
//	}
//	
//	/**
//	 * Tests the constructor from a string of the object Album.
//	 */
//	@Test
//	public void testE() {
//		System.out.println("\n TEST E");
//		String newAlbumExpression = "<album>"
//				+ "<albumtitle>X</albumtitle>"
//				+ "<performer>Ed Sheeran</performer>"
//				+ "<song>One</song>"
//				+ "<song>I'm a Mess</song>"
//				+ "<song>Sing</song>"
//				+ "<song>Don't</song>"
//				+ "<song>Nina</song>"
//				+ "<song>Photograph</song>"
//				+ "<song>Bloodstream</song>"
//				+ "</album>";
//		Album testAlbum = new Album(newAlbumExpression);
//		System.out.println(testAlbum.performer);
//		System.out.println(testAlbum.name);
//		
//		assertEquals(testAlbum.performer, "Ed Sheeran");
//		assertEquals(testAlbum.name, "X");
//		
//		for ( String s: testAlbum.songlist ) {
//			System.out.println( s );
//		}
//		
//				
//	}
//
//	
//
//	/**
//	 * Tests the constructor from parameters of the object Album.
//	 */
//	@Test
//	public void testF() {
//		System.out.println("\n TEST F");
//		String title = "X";
//		String performer = "Ed Sheeran";
//		ArrayList<String> songs = new ArrayList<String>();
//		songs.add("One");
//		songs.add("I'm a Mess");
//		songs.add("Sing");
//		songs.add("Don't");
//		songs.add("Nina");
//		songs.add("Photograph");
//		songs.add("Bloodstream");
//		Album testAlbum = new Album(title, performer, songs);
//		System.out.println(testAlbum.performer);
//		System.out.println(testAlbum.name);
//		
//		assertEquals(testAlbum.performer, "Ed Sheeran");
//		assertEquals(testAlbum.name, "X");
//		
//		for ( String s: testAlbum.songlist ) {
//			System.out.println( s );
//		}
//				
//	}
//
//	/**
//	 * Tests the toString method of the object Album.
//	 */
//	@Test
//	public void testG() {
//		System.out.println("\n TEST G");
//		String title = "X";
//		String performer = "Ed Sheeran";
//		ArrayList<String> songs = new ArrayList<String>();
//		songs.add("One");
//		songs.add("I'm a Mess");
//		songs.add("Sing");
//		songs.add("Don't");
//		songs.add("Nina");
//		songs.add("Photograph");
//		songs.add("Bloodstream");
//		
//		Album testAlbum = new Album(title, performer, songs);
//		
//		String testString = testAlbum.toString();
//		System.out.println( testString );
//		
//	}

//	/**
//	 * Tests that Library reader will only read what is between the keys
//	 */
//	@Test
//	public void testH() {
//		System.out.println("\n TEST H");
//		String genreExpression = "<genre>Jazz"
//				+ "<album>album1</album>"
//				+ "<album>album2</album>"
//				+ "<subgenre>Smooth Jazz"
//				+ "<album>album3</album>"
//				+ "<album>album4</album>"
//				+ "</subgenre>"
//				+ "<subgenre>Classical Jazz"
//				+ "<album>album5</album>"
//				+ "<album>album6</album>"
//				+ "</subgenre></genre>"
//				;
//		LibraryReader newLibraryReader = new LibraryReader(genreExpression);
//		String[] genres = newLibraryReader.splitIntoStrings("genre");
//		
//		for (int index = 0; index < genres.length; index++){
//		System.out.println(genres[index]);
//		}
//	}
//	
//	/**
//	 * Tests that Library reader will only read what is between the keys
//	 */
//	@Test
//	public void testI() {
//		System.out.println("\n TEST I");
//		String genreExpression = "<genreName>Jazz</genreName>\n"
//				+ "<album><albumtitle>X</albumtitle><performer>Ed Sheeran</performer><song>One</song><song>I'm a Mess</song><song>Sing</song></album>\n"
//				+ "<subGenre>\n"
//				+ "<genreName>Smooth Jazz</genreName>\n"
//				+ "<album><albumtitle>G</albumtitle><performer>Eddy Sheehan</performer><song>Two</song><song>I'm not a Mess</song><song>Singing</song></album>\n"				
//				+ "<subGenre>\n"
//				+ "<genreName>Smooth Jazz</genreName>\n"
//				+ "<album><albumtitle>H</albumtitle><performer>Edward Sheenanigans</performer><song>Three</song><song>I'm not a total Mess</song><song>Singingers</song></album>\n"
//				+ "</subGenre>\n"
//				+ "</subGenre>\n"
//				+ "<END>";
//		System.out.println(genreExpression);
//		/*
//		LibraryReader newLibraryReader = new LibraryReader(genreExpression);
//		String[] genres = newLibraryReader.splitIntoStrings("genre");
//		*/
//		Genre jazzGenre = new Genre("Jazz");
//		jazzGenre.restoreCollection(genreExpression);
//		
//		assertEquals(jazzGenre.name, "Jazz");
//		//assertEquals(jazzGenre.getAllChildren(), "Jazz");
//		//assertEquals(jazzGenre.name, "Jazz");
//		
//		/*
//		for (int index = 0; index < genres.length; index++){
//		System.out.println(genres[index]);
//		}
//		*/
//	}
//
//	/**
//	 * Tests that Library reader will only read what is between the keys
//	 */
//	@Test
//	public void testJ() {
//		System.out.println("\n TEST J");
//		//declaring songlist with a couple songs and a test album with details
//		ArrayList<String> songlist = new ArrayList<String>();
//		songlist.add( "Back in Black" );
//		songlist.add( "Piano Stuff" );
//		songlist.add( "Boulivard of Broken Dreams" );
//		//created album and added it to a specific genre
//		Album testAlbum1 = new Album( "Chopin Collections" , "Evgeny Kissin" , songlist);
//		Album testAlbum2 = new Album( "American Idiot" , "Green Day" , songlist);
//		Genre Classical = new Genre( "Classical" );
//		Genre Master = new Genre( "Master" );
//		testAlbum1.addToGenre( Classical );
//		testAlbum2.addToGenre( Classical );
//		Master.addToGenre(Classical);
//		String Test = new String();
//		System.out.println(Master.toString());
//		//Test = ("\n<!!genre name!!>Master<!!end genre name!!>[\n<!!sub genre!!>\n<!!genre name!!>Classical<!!end genre name!!>[\n<!!album!!><!!album name!!>Chopin Collections<!!end album name!!><!!performer!!>Evgeny Kissin<!!end performer!!><!!songlist!!>[<!!song!!>Back in Black<!!end song!!><!!SongSplitPoint!!>, <!!song!!>Piano Stuff<!!end song!!><!!SongSplitPoint!!>, <!!song!!>Boulivard of Broken Dreams<!!end song!!><!!SongSplitPoint!!>]<!!end songlist!!><!!end album!!>, \n<!!album!!><!!album name!!>American Idiot<!!end album name!!><!!performer!!>Green Day<!!end performer!!><!!songlist!!>[<!!song!!>Back in Black<!!end song!!><!!SongSplitPoint!!>, <!!song!!>Piano Stuff<!!end song!!><!!SongSplitPoint!!>, <!!song!!>Boulivard of Broken Dreams<!!end song!!><!!SongSplitPoint!!>]<!!end songlist!!><!!end album!!>]\n<!!end sub genre!!>]\n<!!END!!>");
//		//assertEquals("String does not match Test string" , Master.toString(), Test);
//	}
}