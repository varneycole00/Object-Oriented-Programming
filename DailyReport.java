import java.util.LinkedList;

public class DailyReport {
    double date;
    LinkedList<Double> readings;

    DailyReport(double date, LinkedList<Double> readings) {
        this.date = date;
        this.readings = readings;
    }
}
