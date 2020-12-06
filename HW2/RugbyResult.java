
public class RugbyResult implements IResult {
    RugbyTeam team1;
    RugbyTeam team2;
    double team1Points;
    double team2Points;
    private RugbyResult RugbyResult;

    public RugbyResult(RugbyTeam team1, RugbyTeam team2, double team1Points, double team2Points) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1Points = team1Points;
        this.team2Points = team2Points;
    }

    //takes a RugbyResult and returns true if it is valid and false otherwise
    @Override
    public boolean isValid() {
        return this.team2Points < 150 && this.team1Points < 150;
    }

    //takes in a rugby result and returns a string for the winning team name
    @Override
    public IContestant getWinner() {
        if (team1Points > team2Points) {
            return team1;
        } else {
            return team2;
        }
    }
}
