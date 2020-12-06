public class RoboticsTeam implements IContestant {

    String school;
    String specialFeature;
    int previousScore = 0;

    public RoboticsTeam(String school, String specialFeature, int previousScore) {
        this.school = school;
        this.specialFeature = specialFeature;
        this.previousScore = previousScore;

    }

    //Does a previous score of 0 imply that there is no clear expected winner?
    public boolean expectToBeat(RoboticsTeam opponent) {
        if(this.previousScore ==  0 || opponent.previousScore == 0) {
            return false;
        } else return this.previousScore > opponent.previousScore;
    }

    //RoboticsResult constructor
}
