import java.util.LinkedList;

/**
 * Contains methods for organization of list of shows into
 * a valid ShowSummary
 */
class ShowManager2 {
	
	ShowManager2() {}

	/**
	 * Organizes shows into each respective list within a show summary.
	 * (no respect to whether or not it is special)
	 * @param shows
	 * @return A ShowSummary with shows parsed into correct
	 * lists.
	 */
	public ShowSummary organizeShows(LinkedList<Show> shows) {
		ShowSummary summary2 = new ShowSummary();
		for(Show aShow : shows) {
			if(isDaytime(aShow)) {
				summary2.daytime.add(aShow);
			}
			if(isPrimetime(aShow)) {
				summary2.primetime.add(aShow);
			}
			if(isLateNight(aShow)) {
				summary2.latenight.add(aShow);
			}
		}
		cleanOutSpecials(summary2);
		return summary2;
	}

	/**
	 * Checks if the show is a daytime show.
	 * @param aShow
	 * @return True if the show is a daytime show.
	 */
	public boolean isDaytime(Show aShow) {
		return (aShow.broadcastTime >= 600) &&
				(aShow.broadcastTime < 1700);
	}

	/**
	 * Checks if the show is a primetime show.
	 * @param aShow
	 * @return True if the show is a primetime show.
	 */
	public boolean isPrimetime(Show aShow) {
		return (aShow.broadcastTime >= 1700) &&
				(aShow.broadcastTime < 2200);
	}

	/**
	 * Checks if the show is a latenight show.
	 * @param aShow
	 * @return True if the show is a latenight show.
	 */
	public boolean isLateNight(Show aShow) {
		return (((aShow.broadcastTime >= 2200) && (aShow.broadcastTime <= 2400))  ||
				((aShow.broadcastTime > 0) && (aShow.broadcastTime < 100)));
	}

	/**
	 * Cleans the special shows out of a ShowSummary.
	 * @param aSummary
	 * @return a ShowSummary without any special shows included.
	 */
	public ShowSummary cleanOutSpecials(ShowSummary aSummary) {
		for(Show aShow : aSummary.daytime) {
			if(aShow.isSpecial) {
				aSummary.daytime.remove(aShow);
			}
		}
		for (Show aShow : aSummary.primetime) {
			if(aShow.isSpecial) {
				aSummary.primetime.remove(aShow);
			}
		}
		for(Show aShow : aSummary.primetime) {
			if(aShow.isSpecial) {
				aSummary.latenight.remove(aShow);
			}
		}
		return aSummary;
	}
}
