import java.util.LinkedList;

public class InitRound extends AbsRound implements IWinner {

    public InitRound(LinkedList<Match> matches) {
        super.matches = matches;
    }

    /**
     * Checks to see if the contestant is included in the list of winners
     * @param contestant
     * @return true if the contestant is a winner
     */
    @Override
    public boolean isWinner(IContestant contestant) {
        getMatchWinners();
        return winnersList.contains(contestant);
    }




}
