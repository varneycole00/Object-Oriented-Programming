import java.util.GregorianCalendar;
import java.util.LinkedList;

/**
 * A DailyWeatherReport is an implementation of IReport that stores the date, a list of temperature readings from that
 * date, and a list of rainfall readings for that date. The class has a method that checks the date, and two getters for
 * temperature readings and rainfall readings.
 */
public class DailyWeatherReport implements IReport {

    private GregorianCalendar date;
    private LinkedList<Double> tempReadings;
    private LinkedList<Double> rainfallReadings;

    /**
     * Daily weather report is a type of IReport that takes in a date, a list of temperature readings, and a list of
     * rainfall readings.
     *
     * @param date
     * @param tempReadings
     * @param rainfallReadings
     */
    DailyWeatherReport(GregorianCalendar date, LinkedList<Double> tempReadings, LinkedList<Double> rainfallReadings) {
        this.date = date;
        this.tempReadings = tempReadings;
        this.rainfallReadings = rainfallReadings;
    }

    /**
     * Checks if the month and year of the DailyWeatherReport lines up with the inputted month and year.
     *
     * @param month
     * @param year
     * @return true if the month and year of the DailyWeather report are equal to the given month and year values.
     */
    public boolean isRightMonthAndYear(int month, int year) {
        return (date.get(GregorianCalendar.MONTH) == month) &&
                (date.get(GregorianCalendar.YEAR) == year);
    }

    /**
     * Caculates the total rainfall from a given day from data within a report.
     * @return a total rainfall for a given day.
     */
    public double calcRainfallOneDay() {
        double dailyTotalRainfall = 0.0;

        for (Double aRainfallReading : this.rainfallReadings) {
            dailyTotalRainfall += aRainfallReading;
        }

        return dailyTotalRainfall;
    }

    /**
     * Caculates the average temperature from a given day from data within a report.
     * @return an average temperature for a given day.
     */
    public double dailyAvgTemp() {
        double dataFromDay = 0;
        double numReadingFromDay = this.rainfallReadings.size();

        for (Double aTempReading : this.tempReadings) {
            dataFromDay += aTempReading;
        }

        return dataFromDay / numReadingFromDay;
    }


}
