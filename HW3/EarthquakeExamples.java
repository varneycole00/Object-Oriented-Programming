import static org.junit.Assert.*;
import org.junit.Test;

import java.lang.reflect.Array;
import java.util.LinkedList;

public class EarthquakeExamples {
  Earthquake1 E1 = new Earthquake1();
  Earthquake2 E2 = new Earthquake2();
  LinkedList<Double> noData = new LinkedList<Double>();
  LinkedList<Double> threeDates = new LinkedList<Double>();  
  LinkedList<MaxHzReport> NovReports = new LinkedList<MaxHzReport>();
  LinkedList<MaxHzReport> OctReports = new LinkedList<MaxHzReport>();


  public EarthquakeExamples() {
    threeDates.add(20151013.0);
    threeDates.add(10.0);
    threeDates.add(5.0);
    threeDates.add(20151020.0);
    threeDates.add(40.0);
    threeDates.add(50.0);
    threeDates.add(45.0);
    threeDates.add(20151101.0);
    threeDates.add(6.0);
    NovReports.add(new MaxHzReport(20151101.0,6.0));
    OctReports.add(new MaxHzReport(20151013.0, 10.0));
    OctReports.add(new MaxHzReport(20151020.0, 50.0));
  }

  @Test
  public void instructorTestEQ() { 
    assertEquals(NovReports, 
                 E1.dailyMaxForMonth(threeDates, 11));
    assertEquals(OctReports, E1.dailyMaxForMonth(threeDates, 10));
  }

  @Test
  public void testGetMaxReading() {
    LinkedList<Double> exReadings = new LinkedList<Double>();
    exReadings.add(45.0);
    exReadings.add(100.0);
    exReadings.add(25.0);
    exReadings.add(245.0);
    assertEquals(245.0, E1.getMaxReading(exReadings), 0.0);
    assertNotEquals(100.0, E1.getMaxReading(exReadings), 0.0);
  }


  @Test
  public void testDailyMaxForMonth() {

    assertEquals(NovReports,
            E2.dailyMaxForMonth(threeDates, 11));
    assertEquals(OctReports, E2.dailyMaxForMonth(threeDates,10));
  }

  /*
  Subtasks:
  - Make sure data has no negative in final values
  - Determine whether a piece of data is a date or a reading
  - Determine if the date is in the desired
  - Find the max reading from the desired date
  - Create MaxHzReports for each applicable date and its max reading
  - Take the readings associated with the date and include them in the report
   */

}

