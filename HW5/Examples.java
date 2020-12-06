import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import org.junit.Test;
import static org.junit.Assert.*;

public class Examples {

    WeatherMonitor monitor = new WeatherMonitor(new LinkedList<>());

    @Test
    public void testAddDailyReport() {
        Reading reading1 = new Reading(new Time(12, 30), 30.0, 2);
        Reading reading2 = new Reading(new Time(13, 00), 28.0, 0.0);
        Reading reading3 = new Reading(new Time(13, 30), 30.0, 0.0);
        Reading reading4 = new Reading(new Time(14, 00), 29.0, 0.02);

        LinkedList<Reading> listReading1 = new LinkedList<Reading>();
        listReading1.add(reading1);
        listReading1.add(reading2);
        listReading1.add(reading3);
        listReading1.add(reading4);

        monitor.addDailyReport(new GregorianCalendar(2019, Calendar.DECEMBER, 1), listReading1);



        Reading reading5 = new Reading(new Time(12, 30), 30.0, 0.3);
        Reading reading6 = new Reading(new Time(13, 00), 28.0, 0.9);
        Reading reading7 = new Reading(new Time(13, 30), 30.0, 1.0);
        Reading reading8 = new Reading(new Time(14, 00), 29.0, 2.0);

        LinkedList<Reading> listReading2 = new LinkedList<Reading>();
        listReading2.add(reading5);
        listReading2.add(reading6);
        listReading2.add(reading7);
        listReading2.add(reading8);

        monitor.addDailyReport(new GregorianCalendar(2019, Calendar.DECEMBER, 2), listReading2);

        assertEquals(6.22, monitor.totalRainfallForMonth(Calendar.DECEMBER, 2019), 0.01);
        assertEquals(29.25, monitor.avgTempForMonth(Calendar.DECEMBER, 2019), 0.01);

    }
}
