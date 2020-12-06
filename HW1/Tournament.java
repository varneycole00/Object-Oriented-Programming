import java.util.LinkedList;

public class Tournament extends AbsRound {
    LinkedList<IWinner> rounds;

    public Tournament(LinkedList<IWinner> rounds) {
        this.rounds = rounds;
        
    }



    /**
     * Determines if the alleged winner is in fact a valid winner of the tournament.
     * @param winner
     * @return True if the alleged winner has won at least half of the rounds in the tournament.
     */
    public boolean finalWinnerIsValid(IContestant winner) {
        int numRounds = rounds.size();
        int roundsWon = 0;
            for(IWinner aRound : rounds) {
                if(aRound.isWinner(winner)) {
                    roundsWon += 1;
                }

            }

            return roundsWon >= numRounds;

    }


}
