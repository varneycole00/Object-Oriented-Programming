import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Scanner;

/**
 * ElectionData handles the data, votes, candidate, and the backend computations of the voting machine.
 */
class ElectionData {

    private Hashtable<String, Integer> firstVotes = new Hashtable<>();
    private Hashtable<String, Integer> secondVotes = new Hashtable<>();
    private Hashtable<String, Integer> thirdVotes = new Hashtable<>();

    private LinkedList<String> ballot = new LinkedList<String>();
    private Scanner keyboard = new Scanner(System.in);

    /**
     * An ElectionData object has no fields because manipulation of data only happens within methods.
     */
    ElectionData() {
    }

    /**
     * Prints the candidates in a list to the console.
     */
    public void printBallot() {
        System.out.println("The candidates are ");
        for (String s : ballot) {
            System.out.println(s);
        }
    }

    /**
     * Adds a candidate to the voting system through adding his/her name to the ballot and adding keys on the HashTables
     * for their votes.
     * @param cand
     * @throws CandidateExistsException when inputted cand is a candidate that already exists.
     */
    public void addCandidate(String cand) throws CandidateExistsException {
        if (firstVotes.get(cand) != null ||
                secondVotes.get(cand) != null ||
                thirdVotes.get(cand) != null) {
            throw new CandidateExistsException(cand);
        }
        firstVotes.putIfAbsent(cand.toLowerCase(), 0);
        secondVotes.putIfAbsent(cand.toLowerCase(), 0);
        thirdVotes.putIfAbsent(cand.toLowerCase(), 0);
        ballot.add(cand);
    }

    /**
     * Takes in a first, second, and third vote and adds one to the candidates key in the HashTables respectively
     * depending on if they were a first, second, or third choice vote.
     * @param first
     * @param second
     * @param third
     * @throws UnknownCandidateException if an inputted String for candidate does not exist on the ballot.
     * @throws DuplicateVotesException if the user votes for the same candidate twice.
     */
    public void processVote(String first, String second, String third) throws UnknownCandidateException, DuplicateVotesException {
        if (firstVotes.get(first.toLowerCase()) == null) {
            throw new UnknownCandidateException(first);
        }
        if (secondVotes.get(second.toLowerCase()) == null) {
            throw new UnknownCandidateException(second);
        }
        if (thirdVotes.get(third.toLowerCase()) == null) {
            throw new UnknownCandidateException(third);
        }
        if (first.toLowerCase().equals(second.toLowerCase())) {
            throw new DuplicateVotesException(first);
        }
        if (second.toLowerCase().equals(third.toLowerCase())) {
            throw new DuplicateVotesException(second);
        }
        if (third.toLowerCase().equals(first.toLowerCase())) {
            throw new DuplicateVotesException(first);
        }
        firstVotes.put(first.toLowerCase(), firstVotes.get(first.toLowerCase()) + 1);
        secondVotes.put(second.toLowerCase(), secondVotes.get(second.toLowerCase()) + 1);
        thirdVotes.put(third.toLowerCase(), thirdVotes.get(third.toLowerCase()) + 1);

    }

    /**
     * Finds the winner by determining if any candidate has more than 50% of the total number of first votes placed.
     * @return the candidate with 50% or more first votes.
     */
    public String findWinnerMostFirstVotes() {
        int totalNumFirstVotes = totalFirstVotes();
        String currWinner = "Runoff required";

        for (String aCand : ballot) {
            double numFirstVotes = firstVotes.get(aCand.toLowerCase());
            if (numFirstVotes / totalNumFirstVotes >= .5) {
                return aCand;
            }
        }
        return "Runoff required";
    }

    /**
     * Calculates the total number of times that a candidate was chosen as a first vote.
     * @return the number of first votes that a candidate received.
     */
    private int totalFirstVotes() {
        int numFirstVotes = 0;
        for(String aCand : ballot) {
            if ( firstVotes.get(aCand.toLowerCase()) == null) {
                numFirstVotes += 0;
            }
            else {
                numFirstVotes += firstVotes.get(aCand.toLowerCase());
            }
        }
        return numFirstVotes;
    }

    /**
     * Using a system where a first vote is 3 points, second is 2 points, and third is 1 point, findWinnerMostPoints
     * determines the winner by calculating each candidates score and returning whoever has the highest. In the
     * occurence of a tie, only one candidate is returned.
     * @return a String for a the winning candidate.
     */
    public String findWinnerMostPoints() {
        String currentWinner = "Undecided";
        int currWinnerPoints = 0;
        for (String aCand : ballot) {
            int currPoints = 0;
            currPoints += determineFirstVotePoints(aCand);
            currPoints += determineSecondVotePoints(aCand);
            currPoints += determineThirdVotePoints(aCand);
            if (currPoints > currWinnerPoints) {
                currentWinner = aCand;
                currWinnerPoints = currPoints;
            }
        }
        return currentWinner;
    }

    /**
     * Determines the number of points a candidate has from their number of first votes. 1 first vote is worth 3 points.
     * @param aCand
     * @return the points earned ONLY from firstVotes.
     */
    public int determineFirstVotePoints(String aCand) {
        int totalPoints = 0;
        if (firstVotes.get(aCand.toLowerCase()) == null) {
            return 0;
        } else {
            totalPoints += 3 * firstVotes.get(aCand.toLowerCase());
        }
        return totalPoints;
    }

    /**
     * Determines the number of points a candidate has from their number of first votes. 1 first vote is worth 2 points.
     * @param aCand
     * @return the number of points a candidate earned from second votes.
     */
    public int determineSecondVotePoints(String aCand) {
        int totalPoints = 0;
        if (secondVotes.get(aCand.toLowerCase()) == null) {
            return 0;
        } else {
            totalPoints += 2 * secondVotes.get(aCand.toLowerCase());
        }
        return totalPoints;
    }

    /**
     * Determines the number of points a candidate has from their number of third votes. 1 third vote is worth 1 point.
     * @param aCand
     * @return the number of points a candidate earned from third votes.
     */
    public int determineThirdVotePoints(String aCand) {
        int totalPoints = 0;
        if (thirdVotes.get(aCand.toLowerCase()) == null) {
            return 0;
        } else {
            totalPoints += thirdVotes.get(aCand.toLowerCase());
        }
        return totalPoints;
    }

    /**
     * Getter for the ballot of an ElectionData object
     * @return a LinkedList<String> representing the ballot.
     */
    public LinkedList<String> getBallot() {
        return this.ballot;
    }

}
