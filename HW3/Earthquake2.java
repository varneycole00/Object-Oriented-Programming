import org.w3c.dom.CDATASection;

import java.util.LinkedList;

/**
  * Provides methods for generating reports on readings collected on specific dates and readings of earthquakes.
c */
class Earthquake2 {
  Earthquake2() {
  }

  // checks whether a datum is a date
  boolean isDate(double anum) {
    return (int) anum > 10000000;
  }

  // extracts the month from an 8-digit date
  int extractMonth(double dateNum) {
    return ((int) dateNum % 10000) / 100;
  }

  /**
   * Outputs a list of MaxHzReports for a given month from a list of data
   * @param data
   * @param month
   * @return A LinkedList<MaxHzReports> representing the reports generated from the data
   * in a given month
   */
  LinkedList<MaxHzReport> dailyMaxForMonth(LinkedList<Double> data, int month) {
    data.add(-999.0);
    double date = data.get(0);
    LinkedList<MaxHzReport> listMaxHzReport = new LinkedList<MaxHzReport>();
    LinkedList<MaxHzReport> finalMazHzReportList = new LinkedList<MaxHzReport>();
    double maxDailyReading = 0.0;
    for(int i = 1; i < data.size(); i++) {
      if(!isDate(data.get(i)) && (data.get(i) > maxDailyReading)) {
        maxDailyReading = data.get(i);
      }
      if(isDate(data.get(i)) || data.get(i) == -999.0) {
        listMaxHzReport.add(new MaxHzReport(date, maxDailyReading));
        date = data.get(i);
        maxDailyReading = 0.0;
      }
    }
    for(MaxHzReport aReport : listMaxHzReport) {
      if ((extractMonth(aReport.date) == month)) {
        finalMazHzReportList.add(aReport);
      }
    }
    return finalMazHzReportList;
  }

}
