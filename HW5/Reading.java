/**
 * The Reading class has fields of the time of a reading, the temperature at that time, and the rainfall since the last
 * reading. The class also provides getters for the temperature and the rainfall for usage in WeatherMonitor.
 */
public class Reading {

    private Time time;
    private Double temperature;
    private Double rainfallSinceLastReading;

    /**
     * A reading is comprised of a time of recording, a temperature, and the amount of rainfall since the last reading
     * occured.
     * @param time
     * @param temperature
     * @param rainfallSinceLastReading
     */
    Reading(Time time, double temperature, double rainfallSinceLastReading) {
        this.time = time;
        this.temperature = temperature;
        this.rainfallSinceLastReading = rainfallSinceLastReading;
    }

    /**
     * Getter for the temperature field of a reading.
     * @return the temperature at the time of the given reading.
     */
    double getTemperature() {
       return this.temperature;
    }

    /**
     * Getter for the rainfall since the last reading.
     * @return a value for the rainfall that occurred since the last reading.
     */
    double getRainfallSinceLastReading() {
        return this.rainfallSinceLastReading;
    }
}
