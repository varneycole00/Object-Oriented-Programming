import java.util.LinkedList;
import java.util.Scanner;

/**
 * The VotingMachine class handles the user input/output.
 */
public class VotingMachine {

    private ElectionData electionData = new ElectionData();
    private Scanner keyboard = new Scanner(System.in);

    /**
     * Voting machine serves to provide user interaction with the ElectionData.
     */
    VotingMachine () {}

    /**
     * Prompts the user to vote for three different candidates on the console.
     * @throws DuplicateVotesException if the user votes for the same candidate twice.
     * @throws UnknownCandidateException if the user inputs a candidate that does not exist on the ballot.
     * @throws CandidateExistsException if the user attempts to write in a candidate that already exists on the ballot.
     */
    public void screen() throws DuplicateVotesException, UnknownCandidateException, CandidateExistsException {
        printBallot();
        System.out.println("Who do you want to vote for?");
        LinkedList<String> candidates = new LinkedList<String>();
        try {
            for (int i = 0; i < 3; i++) {
                String candidate = keyboard.next();
                candidates.addLast(candidate);
                System.out.println("You voted for " + candidate);
            }
            electionData.processVote(candidates.get(0), candidates.get(1), candidates.get(2));
        }
        catch(UnknownCandidateException e) {
            System.out.print("Would you like to add " + e.getName() + " to the ballot? ");
            String input = keyboard.next();
            if(input.toLowerCase().equals("y") || input.toLowerCase().equals("yes")) {
                this.addWriteIn(e);
            }
        }
        catch(DuplicateVotesException d) {
            System.out.print("We're sorry, unfortunately you cannot vote for the same candidate twice! ");
            screen();
        }
        for (int i = 0; i < 3; i++) {
            String candidate = keyboard.next();
            candidates.addLast(candidate);
            System.out.println("You voted for " + candidate);
        }
        electionData.processVote(candidates.get(0), candidates.get(1), candidates.get(2));
    }

    /**
     * Prints the candidates on the ballot to the console.
     */
    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : electionData.getBallot()) {
            System.out.println(s);
        }
    }

    /**
     * Adds a candidate to the ElectionData object of the Voting machine.
     * @param cand
     * @throws CandidateExistsException if the candidate to add already exists in the ballot.
     * @throws DuplicateVotesException if after completing the addition, the user votes for the same person more than
     * once.
     * @throws UnknownCandidateException  if after running screen after the addition, the user inputs another name that
     * does not exist on the ballot.
     */
    public void addWriteIn(UnknownCandidateException cand) throws CandidateExistsException,
            DuplicateVotesException, UnknownCandidateException {
        try{
            electionData.addCandidate(cand.getName());
            screen();
        }
        catch (CandidateExistsException c) {
            System.out.print("This candidate already exists ");
            screen();
        }
    }

}
