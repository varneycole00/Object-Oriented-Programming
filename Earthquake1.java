import java.util.LinkedList;

/**
 * Provides methods for generating reports on readings collected on specific dates.
 */
class Earthquake1 {
  Earthquake1(){}

  // checks whether a datum is a date
  boolean isDate(double anum) { return (int)anum > 10000000; }

  // extracts the month from an 8-digit date
  int extractMonth(double dateNum) { return ((int)dateNum % 10000) / 100; }


  /**
   * Generates a list of MaxHzReports for a specific month taken from a list of data.
   * @param data
   * @param month
   * @return LinkedList<MaxHzReport> of reports from a specific month.
   */
  public LinkedList<MaxHzReport> dailyMaxForMonth(LinkedList<Double> data, int month) {
    LinkedList<MaxHzReport> MaxHzReports = new LinkedList<MaxHzReport>();
    for (int i = 0; i < data.size(); i++) {
          if(isDate(data.get(i)) && (extractMonth(data.get(i)) == month)) {
            LinkedList<Double> readings = new LinkedList<Double>();
            for(int j = i + 1; j < data.size(); j++) {
              if(!isDate(data.get(j))) {
                readings.add(data.get(j));
              }
              else break;
            }
            double maxReading = getMaxReading(readings);
            MaxHzReports.add(new MaxHzReport((data.get(i)), maxReading));
            readings.clear();
          }
        }
    return MaxHzReports;
  }

  /**
   * Gets the max reading from a list of readings on a given day.
   * @param readings
   * @return The max reading of a given day.
   */
  public Double getMaxReading(LinkedList<Double> readings) {
    Double maxReading = 0.0;
    for(Double aReading : readings) {
      if(aReading > maxReading) {
        maxReading = aReading;
      }
    }
    return maxReading;
  }

}  

