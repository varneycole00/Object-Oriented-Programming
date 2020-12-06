public class Match {

    IContestant contestant1;
    IContestant contestant2;

    public IResult result;
    public Match(IContestant contestant1, IContestant contestant2, IResult result) {
        this.contestant1 = contestant1;
        this.contestant2 = contestant2;
        this.result = result;

    }




    public IContestant winner() {
        if (result.isValid()) {
            return result.getWinner();
        }else{
            return null;
        }
    }

}
