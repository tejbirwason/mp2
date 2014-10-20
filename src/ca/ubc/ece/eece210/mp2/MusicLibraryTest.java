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
		ArrayList<String> songlist = new ArrayList<String>();
		songlist.add( "Back in Black" );
		songlist.add( "Piano Stuff" );
		songlist.add( "Boulivard of Broken Dreams" );
		//create album and add it to a specific genre
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
	//Scenario 8: Recreate catalogue from file
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
		
		TestCatalogue.saveCatalogueToFile("Test.txt");
		//recreate catalogue from file
		Catalogue TestCatalogue2 = new Catalogue("Test.txt");
		
		assertTrue(TestCatalogue.equals(TestCatalogue2));
	}
	
	
	//Scenario 9: Write a test to verify the genre inclusion rules
	// check that a genre has only one parent genre,
	// no recursive hierarchy is allowed,
	// cannot add a genre to itself
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
		
		boolean addSelfGenre = false;
		try{
		Rock.addToGenre( Rock );
		} catch ( IllegalArgumentException e ) {
			addSelfGenre = true;
			System.out.println( e.getMessage() );
		}
		
		assertTrue( addAlbumFail );
		assertTrue( addSelfGenre );
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
}