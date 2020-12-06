import java.util.LinkedList;

/**
 * Contains methods with functionality to smooth Show Data
 */
class DataSmooth1 {
  DataSmooth1(){}

    /**
     * Consumes a list of shows and returns a list of smoothed average runtime values.
     * @param shows
     * @return a LinkedList<Double> of smoothed runtime values.
     */
  public LinkedList<Double> dataSmooth(LinkedList<Show> shows) {
      LinkedList<Double> avgRuntimes = avgRuntimes(shows);
      LinkedList<Double> smoothedData = new LinkedList<Double>();
      for(int i = 0; i < avgRuntimes.size(); i++) {
          if(i == 0) {
              smoothedData.add(avgRuntimes.get(i));
          }

          if(i > 0) {
              if(i == (avgRuntimes.size()) - 1) {
                  smoothedData.add(avgRuntimes.get(i));
                  break;
              }
              double avgOfThree = 0;
              avgOfThree = ((avgRuntimes.get(i-1) + avgRuntimes.get(i) + avgRuntimes.get(i +1)) / 3 );
              smoothedData.add(avgOfThree);
          }
      }
      return smoothedData;
  }

    /**
     * Consumes a List of shows and returns the average runtimes
     * @param shows
     * @return LinkedList<Double> of the average runtime of each show in the original order.
     */
  public LinkedList<Double> avgRuntimes(LinkedList<Show> shows) {
      double avgRuntime = 0;
      double totalDuration = 0;
      LinkedList<Double> averagedRuntimes = new LinkedList<Double>();
      for(Show aShow : shows) {
          for(int i = 0; i < aShow.episodes.size(); i++) {
              totalDuration += aShow.episodes.get(i).runTime;
          }

          avgRuntime = totalDuration / aShow.episodes.size();
          averagedRuntimes.add(avgRuntime);
          avgRuntime = 0;
          totalDuration = 0;
      }
      return averagedRuntimes;
  }

}
