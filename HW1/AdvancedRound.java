import java.util.LinkedList;

public class AdvancedRound extends InitRound implements IWinner {
    LinkedList<IContestant> prevRoundWinners;

    public AdvancedRound(LinkedList<Match> matches, LinkedList<IContestant> prevRoundWinners) {
        super(matches);
        this.prevRoundWinners = prevRoundWinners;

        }

    /**
     * Checks if the contestant is in the list of previous round winners
     * @param contestant
     * @return true if the contestant was in the previous winners list
     */
    @Override
    public boolean isWinner(IContestant contestant) {
        return prevRoundWinners.contains(contestant);
    }



}
