import static org.junit.Assert.*;
import org.junit.Test;
import java.util.LinkedList;
import java.util.Arrays;

public class ShowExamples 
{
	ShowManager1 sm1 = new ShowManager1();
	ShowManager2 sm2 = new ShowManager2();
	LinkedList<Show> shows = new LinkedList<Show>();
	ShowSummary report1 = new ShowSummary();
	
	public ShowExamples()
	{
		LinkedList<Episode> eps1 = new LinkedList<Episode>();
		eps1.add(new Episode("Best of Both Worlds", 88));
		eps1.add(new Episode("The Ultimate Computer", 49));
		eps1.add(new Episode("Trials and Tribble-ations", 43));		
		Show s1 = new Show("Star Trek", 1800, eps1, false);
		shows.add(s1);
		report1.primetime.add(s1);
		
		LinkedList<Episode> eps2 = new LinkedList<Episode>();
		eps2.add(new Episode("Fear of a Bot Planet", 23));
		eps2.add(new Episode("The Why of Fry", 21));
		eps2.add(new Episode("Roswell that Ends Well", 23));
		eps2.add(new Episode("Meanwhile", 22));
		Show s2 = new Show("Futurama", 1900, eps2, false);
		shows.add(s2);
		report1.primetime.add(s2);
		
		LinkedList<Episode> eps3 = new LinkedList<Episode>();
		eps3.add(new Episode("Yakko's World", 4));
		eps3.add(new Episode("Hello Nice Warners", 8));
		eps3.add(new Episode("Where Rodents Dare", 9));
		Show s3 = new Show("Animaniacs", 1630, eps3, false);
		shows.add(s3);
		report1.daytime.add(s3);
		
		LinkedList<Episode> eps4 = new LinkedList<Episode>();
		eps4.add(new Episode("The Letter W", 59));
		eps4.add(new Episode("The Letter P", 57));
		eps4.add(new Episode("The Letter I", 58));
		Show s4 = new Show("Sesame Street", 900, eps4, false);
		shows.add(s4);
		report1.daytime.add(s4);
	}
	
	@Test
	public void instructorTestOrganizeShows() 
	{
		ShowSummary report2 = sm1.organizeShows(shows);
		assertEquals(report1, report2);
	}

	@Test
	public void testIsDaytimeNonSpecial() {
		LinkedList<Episode> eps3 = new LinkedList<Episode>();
		eps3.add(new Episode("Yakko's World", 4));
		eps3.add(new Episode("Hello Nice Warners", 8));
		eps3.add(new Episode("Where Rodents Dare", 9));
		Show s3 = new Show("Animaniacs", 1630, eps3, false);
		assertTrue(sm1.isDaytimeNonSpecial(s3));
		assertFalse(sm1.isPrimetimeNonSpecial(s3));
	}

	@Test
	public void testIsPrimetimeNonSpecial() {
		LinkedList<Episode> eps2 = new LinkedList<Episode>();
		eps2.add(new Episode("Fear of a Bot Planet", 23));
		eps2.add(new Episode("The Why of Fry", 21));
		eps2.add(new Episode("Roswell that Ends Well", 23));
		eps2.add(new Episode("Meanwhile", 22));
		Show s2 = new Show("Futurama", 1900, eps2, false);
		assertTrue(sm1.isPrimetimeNonSpecial(s2));
		assertFalse(sm1.isLateNightNonSpecial(s2));
	}

	@Test
	public void testIsLatenightNonSpecial() {
		//changed time of show to be in latenight category
		LinkedList<Episode> eps4 = new LinkedList<Episode>();
		eps4.add(new Episode("The Letter W", 59));
		eps4.add(new Episode("The Letter P", 57));
		eps4.add(new Episode("The Letter I", 58));
		Show s5 = new Show("Sesame Street", 30, eps4, false);
		assertTrue(sm1.isLateNightNonSpecial(s5));
		assertFalse(sm1.isDaytimeNonSpecial(s5));
	}

	@Test
	public void testOrganizeShows2() {
		ShowSummary report2 = sm2.organizeShows(shows);
		assertEquals(report1, report2);
	}

	@Test
	public void testIsDaytime() {
		LinkedList<Episode> eps3 = new LinkedList<Episode>();
		eps3.add(new Episode("Yakko's World", 4));
		eps3.add(new Episode("Hello Nice Warners", 8));
		eps3.add(new Episode("Where Rodents Dare", 9));
		Show s3 = new Show("Animaniacs", 1630, eps3, false);
		assertTrue(sm2.isDaytime(s3));
		assertFalse(sm2.isPrimetime(s3));
	}

	@Test
	public void testIsLateNight() {
		//changed time of show to be in latenight category
		LinkedList<Episode> eps4 = new LinkedList<Episode>();
		eps4.add(new Episode("The Letter W", 59));
		eps4.add(new Episode("The Letter P", 57));
		eps4.add(new Episode("The Letter I", 58));
		Show s5 = new Show("Sesame Street", 30, eps4, false);
		assertTrue(sm2.isLateNight(s5));
		assertFalse(sm2.isDaytime(s5));
	}

	@Test
	public void testIsPrimetime() {
		LinkedList<Episode> eps2 = new LinkedList<Episode>();
		eps2.add(new Episode("Fear of a Bot Planet", 23));
		eps2.add(new Episode("The Why of Fry", 21));
		eps2.add(new Episode("Roswell that Ends Well", 23));
		eps2.add(new Episode("Meanwhile", 22));
		Show s2 = new Show("Futurama", 1900, eps2, false);
		assertTrue(sm2.isPrimetime(s2));
		assertFalse(sm2.isLateNight(s2));
	}

	@Test
	public void testCleanOutSpecials() {
		LinkedList<Episode> eps6 = new LinkedList<Episode>();
		eps6.add(new Episode("The Letter W", 59));
		eps6.add(new Episode("The Letter P", 57));
		eps6.add(new Episode("The Letter I", 58));
		Show s6 = new Show("The Office", 900, eps6, true);
		shows.add(s6);

		ShowSummary report3 = new ShowSummary();
		report3 = report1;
		report3.daytime.add(s6);
		assertEquals(report1, sm2.cleanOutSpecials(report3));
	}
/*
Subtasks for Problem 1:
- Clean the data of specials and shows between 100 and before 600
- Parse the data into daytime, primetime, and latenight shows and put them
in respective ShowSummary Lists
 */

}
