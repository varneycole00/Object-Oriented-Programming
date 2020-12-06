import java.util.LinkedList;

/**
 * Contains methods for organization of list of shows into
 * a valid ShowSummary
 */
class ShowManager1 {
	
	ShowManager1() {}

    /**
     * Parses shows into daytime, primetime, and latenight shows
     * within a ShowSummary and cleans out shows after 1:00 AM
     * and before 6:00 AM
     * @param shows
     * @return a valid ShowSummary w correct shows in each list
     */
	public ShowSummary organizeShows(LinkedList<Show> shows) {
		ShowSummary summary1 = new ShowSummary();
		for(Show aShow : shows) {
			if(isDaytimeNonSpecial(aShow)) {
				summary1.daytime.add(aShow);
			}
			if(isPrimetimeNonSpecial(aShow)) {
				summary1.primetime.add(aShow);
			}
			if(isLateNightNonSpecial(aShow)) {
				summary1.primetime.add(aShow);
			}
		}
		return summary1;
	}

    /**
     * Checks if a show is a daytime show that the network is interested
     * in putting in the ShowSummary.
     * @param aShow
     * @return True if the show is a daytime show that is not a special.
     */
	public boolean isDaytimeNonSpecial(Show aShow) {
		return (aShow.broadcastTime >= 600) &&
				(aShow.broadcastTime < 1700) &&
				(!aShow.isSpecial);
	}

    /**
     * Checks if a show is a primetime show that the network is interested
     * in including in a ShowSummary.
     * @param aShow
     * @return True if the show is a non-special primetime show.
     */
	public boolean isPrimetimeNonSpecial(Show aShow) {
		return (aShow.broadcastTime >= 1700) &&
				(aShow.broadcastTime < 2200) &&
				(!aShow.isSpecial);
	}

    /**
     * Checks if a show is a late-night show that the network is interested
     * in including in a ShowSummary.
     * @param aShow
     * @return True if the show is a non-special late-night show.
     */
	public boolean isLateNightNonSpecial(Show aShow) {
		return (((aShow.broadcastTime >= 2200) && (aShow.broadcastTime <= 2400))  ||
                ((aShow.broadcastTime > 0) && (aShow.broadcastTime < 100))) &&
				(!aShow.isSpecial);
	}
	
}
