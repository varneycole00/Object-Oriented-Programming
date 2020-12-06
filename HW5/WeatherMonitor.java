import java.util.Collection;
import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 * WeatherMonitor serves to provide a way of storing and computing different values from different reports like average
 * temperature and total rainfall. The class also provides a method for adding reports to the feild reports.
 */
class WeatherMonitor {


    private Collection<IReport> reports;

    /**
     *The WeatherMonitor class stores a list of reports and methods for doing calculations based on the data.
     * @param reports
     */
    WeatherMonitor(Collection<IReport> reports)  {
        this.reports = reports;
    }



    /**
     * Determines the average temperature of a given month in a given year.
     *
     * @param month
     * @param year
     * @return The average temperature determined from recorded readings.
     */
    double avgTempForMonth(int month, int year) {
        double tempDataFromMonth = 0.0;
        int readingsFromMonth = 0;

        //For every DailyWeatherReport, adds the data to an accum for temp readings and adds the size of the daily
        // LinkedList to an accum to find and return the avg later.
        for (IReport aReport : reports) {
            if (aReport.isRightMonthAndYear(month, year)) {
                //adds the number of temp readings for the day to the accumulator.
                tempDataFromMonth += aReport.dailyAvgTemp();
               readingsFromMonth ++;

            }
        }

        //Finds the average temperature reading by dividing the sum of all the temperature readings by the total number
        //of temperature readings from that month
        return tempDataFromMonth / readingsFromMonth;
    }

    /**
     * Calculates the total amount of rainfall that was recorded in a given month of a given year.
     *
     * @param month
     * @param year
     * @return The total amount of rainfall for a specific month.
     */
    double totalRainfallForMonth(int month, int year) {

        double totalRainfall = 0.0;

        for (IReport aReport : reports) {
            if (aReport.isRightMonthAndYear(month, year)) {
                totalRainfall += aReport.calcRainfallOneDay();
            }
        }
        return totalRainfall;
    }


    /**
     * Stores a new daily report created using the inputted values.
     *
     * @param date
     * @param readings
     */
    void addDailyReport(GregorianCalendar date, LinkedList<Reading> readings) {

        LinkedList<Double> tempReadings = new LinkedList<Double>();
        LinkedList<Double> rainfallReadings = new LinkedList<Double>();

        for (Reading aReading : readings) {
            tempReadings.add(aReading.getTemperature());
            rainfallReadings.add(aReading.getRainfallSinceLastReading());
        }

        reports.add(new DailyWeatherReport(date, tempReadings, rainfallReadings));
    }

}
