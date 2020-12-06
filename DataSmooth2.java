import java.util.LinkedList;

/**
 * Contains methods with functionality to smooth Show Data
 */
class DataSmooth2 {
  DataSmooth2(){}

    /**
     * Consumes a LinkedList of Shows and returns a list of smoothed average runtimes.
     * @param shows
     * @return A list of smoothed list of average runtimes.
     */
  public LinkedList<Double> dataSmooth(LinkedList<Show> shows) {
      LinkedList<Double> avgRuntimes = new LinkedList<Double>();
      for (Show aShow : shows) {
           avgRuntimes.add(avgRuntime(aShow));
      }
      LinkedList<Double> smoothedData = smoothTheData(avgRuntimes);

      return smoothedData;
  }

    /**
     * Consumes a list of average runtimes and returns a Smoothed list of average runtimes.
     * @param avgRuntimes
     * @return A list of Smoothed average runtimes.
     */
  public LinkedList<Double> smoothTheData(LinkedList<Double> avgRuntimes) {
      LinkedList<Double> smoothedData = new LinkedList<Double>();
      for(int i = 0; i < avgRuntimes.size(); i++) {
          if(i == 0) {
              smoothedData.add(avgRuntimes.get(i));
          }
          if(i > 0) {
              if(i == avgRuntimes.size() - 1) {
                  smoothedData.add(avgRuntimes.get(i));
                  break;
              }
              double avgOfThree = 0;
              avgOfThree = ((avgRuntimes.get(i) + avgRuntimes.get(i-1) + avgRuntimes.get(i+1)) / 3 );
              smoothedData.add(avgOfThree);
          }
      }
      return smoothedData;
  }

    /**
     * Computes the average runtime of a show by averaging the episodes of a show.
     * @param aShow
     * @return A double average show runtime.
     */
    public double avgRuntime(Show aShow) {
        double avgRuntime = 0;
        double totalDuration = 0;

        for (int i = 0; i < aShow.episodes.size(); i++) {
            totalDuration += aShow.episodes.get(i).runTime;
        }

        avgRuntime = totalDuration / aShow.episodes.size();
        return avgRuntime;

    }


}