import static org.junit.Assert.*;
import org.junit.Test;
import java.util.LinkedList;

public class DataSmoothExamples 
{  
  LinkedList<Show> shows = new LinkedList<Show>();
  LinkedList<Double> showResults = new LinkedList<Double>();
  DataSmooth1 D1 = new DataSmooth1();
  DataSmooth2 D2 = new DataSmooth2();
  
  public DataSmoothExamples() 
  {
		LinkedList<Episode> eps1 = new LinkedList<Episode>();
		eps1.add(new Episode("Best of Both Worlds", 88));
		eps1.add(new Episode("The Ultimate Computer", 49));
		eps1.add(new Episode("Trials and Tribble-ations", 43));		
		shows.add(new Show("Star Trek", 1800, eps1, false));
		
		LinkedList<Episode> eps2 = new LinkedList<Episode>();
		eps2.add(new Episode("Fear of a Bot Planet", 23));
		eps2.add(new Episode("The Why of Fry", 21));
		eps2.add(new Episode("Roswell that Ends Well", 23));
		eps2.add(new Episode("Meanwhile", 22));
		shows.add(new Show("Futurama", 1900, eps2, false));
		
		LinkedList<Episode> eps3 = new LinkedList<Episode>();
		eps3.add(new Episode("Yakko's World", 4));
		eps3.add(new Episode("Hello Nice Warners", 8));
		eps3.add(new Episode("Where Rodents Dare", 9));
		shows.add(new Show("Animaniacs", 1630, eps3, false));
		
		LinkedList<Episode> eps4 = new LinkedList<Episode>();
		eps4.add(new Episode("The Letter W", 59));
		eps4.add(new Episode("The Letter P", 57));
		eps4.add(new Episode("The Letter I", 58));
		shows.add(new Show("Sesame Street", 900, eps4, false));

	    showResults.add(60.0);
	    showResults.add(29.75);
	    showResults.add(29.08333);
	    showResults.add(58.0);
  }
  
  @Test
  public void instructorTestDS() 
  {
	  LinkedList<Double> runtimes = D1.dataSmooth(shows);
	  
	  for(int i = 0; i < runtimes.size(); i++)
	  {
		  assertEquals(runtimes.get(i), showResults.get(i), .01);
	  }
  }

  LinkedList<Double> avgResults = new LinkedList<Double>();


  @Test
  public void testAvgRuntimes() {
  	avgResults.add(60.0);
  	avgResults.add(22.25);
  	avgResults.add(7.0);
  	avgResults.add(58.0);
  	LinkedList<Double> avgedRuntimes = D1.avgRuntimes(shows);
  	for(int i = 0; i < avgedRuntimes.size(); i++) {
		assertEquals(avgedRuntimes.get(i), avgResults.get(i), 0.0);

	}
  }


  @Test
  public void testD2AvgRuntime() {
  	assertEquals(60.0, D2.avgRuntime(shows.get(0)), 0.0);
  	assertEquals(22.25, D2.avgRuntime(shows.get(1)), 0.0);
  }

	@Test
	public void testD2SmoothTheData() {
  		LinkedList<Double> avgedRuntimes = D1.avgRuntimes(shows);
		LinkedList<Double> runtimes = D1.dataSmooth(shows);
  		for(int i = 0; i < avgedRuntimes.size(); i++ ) {
			assertEquals(runtimes.get(i), D2.smoothTheData(avgedRuntimes).get(i));
		}
}

	@Test
	public void testD2avgRuntime() {
		LinkedList<Double> avgResults = new LinkedList<Double>();
		avgResults.add(60.0);
		avgResults.add(22.25);
		avgResults.add(7.0);
		avgResults.add(58.0);
  		for(int i = 0; i < shows.size(); i++) {
  			assertEquals(avgResults.get(i), D2.avgRuntime(shows.get(i)), 0.0);
		}
	}




  /*
- find the average runtime of each individual show (the average is found by the total
runtime of a show with all episodes divided by the number of episodes in the shows) in a list
- find the smoothed runtime values for all the shows by replacing an internal element with the average of itself, its predecessor, and its successor
except for the first and the last elements of the list of average runtimes.
- Add smoothed runtime values into a list (The desired output) in the same order as the date was inputted.
   */
}
