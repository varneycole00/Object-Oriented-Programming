public class RugbyTeam implements IContestant {

    String country;
    String jerseyColor;
    boolean intimidationRitual;
    int wins;
    int losses;


    public RugbyTeam(String country, String jerseyColor, boolean intimidationRitual, int wins, int losses) {
        this.country = country;
        this.jerseyColor = jerseyColor;
        this.intimidationRitual = intimidationRitual;
        this.wins = wins;
        this.losses = losses;
    }

    public boolean expectToBeat(RugbyTeam opponent) {
        int teamWinLoss = this.wins - this.losses;
        int opponentWinLoss = opponent.wins - opponent.losses;

        if (this.intimidationRitual && !opponent.intimidationRitual) {
            return true;
        }else if (!this.intimidationRitual && opponent.intimidationRitual) {
            return false;
        }else{
            return teamWinLoss > opponentWinLoss;
        }


    }



}
