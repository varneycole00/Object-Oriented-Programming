import java.util.LinkedList;

public interface IReport {
    /**
     * Determines if a Report is from the right month and year.
     *
     * @param month
     * @param year
     * @return true if the Report is from the desired month and year.
     */
    boolean isRightMonthAndYear(int month, int year);

    /**
     * Calculates the total rainfall from a given IReport
     * @return the total rainfall from the IReport
     */
    public double calcRainfallOneDay();

    /**
     * Calculates the daily average temperature from a given report.
     * @return the average temperature of a day.
     */
    public double dailyAvgTemp();

}
