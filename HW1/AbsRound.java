import java.util.LinkedList;

public abstract class AbsRound {

    IContestant contestant;

    public LinkedList<IContestant> winnersList = new LinkedList<>();
    public LinkedList<Match> matches;

    /**
     * Creates a list of winners in a round.
     * @return a list of the winners in a list of matches.
     */
    public LinkedList<IContestant> getMatchWinners() {
        winnersList.clear();
        for(Match aMatch : matches) {
            winnersList.add(aMatch.winner());
        }
        return winnersList;
    }

    /**
     * Clears whatever is in winnerslist and calculates the number of winners of a new calculated winnersList.
     * @return the number of winners in a list of winners from the round.
     */
    public int getNumWinners() {
        winnersList.clear();
        winnersList = getMatchWinners();
        return winnersList.size();
    }



}
