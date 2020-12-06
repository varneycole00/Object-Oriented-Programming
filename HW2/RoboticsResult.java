public class RoboticsResult implements IResult {
    RoboticsTeam team1;
    RoboticsTeam team2;
    double team1Points;
    int team1Tasks;
    boolean team1Fell;
    double team2Points;
    int team2Tasks;
    boolean team2Fell;

    public RoboticsResult(RoboticsTeam team1, RoboticsTeam team2,
                          double team1Points, int team1Tasks, boolean team1Fell,
                          double team2Points, int team2Tasks, boolean team2Fell) {
        this.team1 = team1;
        this.team2 = team2;
        this.team1Points = team1Points;
        this.team1Tasks = team1Tasks;
        this.team1Fell = team1Fell;
        this.team2Points = team2Points;
        this.team2Tasks = team2Tasks;
        this.team2Fell = team2Fell;

    }

    //takes a RugbyResult and returns true if the result is valid and false otherwise
    @Override
    public boolean isValid() {
        if ((team1Tasks <= 8) && (team2Tasks <= 8) &&
                (team1Points <= 16) && (team2Points <= 16)) {
            return true;
        }else {
            return false;
        }
    }

    public static int getScore(double points, int tasks, boolean didFall) {
        int score = (int)points + tasks;
        if (didFall){
            score -= 5;
        }

        return score;
    }

    @Override
    public IContestant getWinner() {
        double team1Score;
        double team2Score;
        IResult result;

        team1Score = getScore(team1Points, team1Tasks, team1Fell);
        team2Score = getScore(team2Points, team2Tasks, team2Fell);

        if(team1Score > team2Score){
            return team1;
        }else{
            return team2;
        }
    }


}
