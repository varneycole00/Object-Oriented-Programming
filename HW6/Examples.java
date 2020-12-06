import org.junit.Test;
import static org.junit.Assert.*;

public class Examples {
    ElectionData election = new ElectionData();
    VotingMachine VT = new VotingMachine();

    ElectionData Setup1 () {

        ElectionData ED = new ElectionData();

        // put candidates on the ballot
        try {

            ED.addCandidate("gompei");
            ED.addCandidate("husky");
            ED.addCandidate("ziggy");

        } catch (Exception e) {}

        // cast votes

        try {

            ED.processVote("gompei", "husky", "ziggy");
            ED.processVote("gompei", "ziggy", "husky");
            ED.processVote("husky", "gompei", "ziggy");

        } catch (Exception e) {}

        return(ED);

    }

    // now run a test on a specific election
    @Test
    public void testMostFirstWinner () {
        assertEquals ("gompei", Setup1().findWinnerMostFirstVotes());
    }

    @Test
    public void testMostFirstVotesWinner() {
        assertEquals("gompei", Setup1().findWinnerMostFirstVotes());
    }



    public ElectionData Setup2() throws CandidateExistsException {
        ElectionData ED = new ElectionData();

        ED.addCandidate("gompei");
        ED.addCandidate("husky");
        ED.addCandidate("ziggy");

        return ED;
    }

    @Test(expected = CandidateExistsException.class)
    public void testCandidateExistException() throws CandidateExistsException {
        Setup2().addCandidate("husky");
    }

    public ElectionData Setup3() throws CandidateExistsException, DuplicateVotesException, UnknownCandidateException {
        ElectionData ED = new ElectionData();

        ED.addCandidate("husky");
        ED.addCandidate("gompei");
        ED.addCandidate("cole");

        ED.processVote("cole", "cole", "gompei");

        return ED;
    }

    @Test(expected = DuplicateVotesException.class)
    public void testDuplicateVotesException() throws DuplicateVotesException, CandidateExistsException, UnknownCandidateException {
        Setup3().findWinnerMostFirstVotes();
    }



    @Test(expected = UnknownCandidateException.class)
    public void testUnknownCandidateException() throws DuplicateVotesException, UnknownCandidateException, CandidateExistsException {
        election.addCandidate("Gompei");
        election.addCandidate("Husky");
        election.addCandidate("Cole");
        election.processVote("Gompei", "David", "Cole");
    }


    ElectionData tieSetup() throws CandidateExistsException, DuplicateVotesException, UnknownCandidateException {
      ElectionData ED = new ElectionData();

      ED.addCandidate("gompei");
      ED.addCandidate("husky");
      ED.addCandidate("cole");

      ED.processVote("cole", "gompei", "husky");
      ED.processVote("cole", "gompei", "husky");
      ED.processVote("gompei", "cole", "husky");
      ED.processVote("gompei", "cole", "husky");

      return ED;
    }

    @Test
    public void testForTie() throws DuplicateVotesException, CandidateExistsException, UnknownCandidateException {
        assertEquals("gompei", tieSetup().findWinnerMostFirstVotes());

    }


    @Test
    public void testFindWinnerMostPoints() throws CandidateExistsException, DuplicateVotesException, UnknownCandidateException {
        election.addCandidate("Gompei");
        election.addCandidate("Husky");
        election.addCandidate("Cole");

        election.processVote("Gompei", "Husky", "Cole");
        election.processVote("Husky", "Gompei", "Cole");
        election.processVote("Husky", "Gompei", "Cole");
        election.processVote("Husky", "Cole", "Gompei");

        assertEquals("Husky", election.findWinnerMostPoints());

    }

    @Test
    public void testFindWinnerMostFirstVotes() throws CandidateExistsException, DuplicateVotesException, UnknownCandidateException {
        election.addCandidate("Gompei");
        election.addCandidate("Husky");
        election.addCandidate("Cole");

        election.processVote("Gompei", "Husky", "Cole");
        election.processVote("Husky", "Gompei", "Cole");
        election.processVote("Husky", "Gompei", "Cole");
        election.processVote("Husky", "Cole", "Gompei");

        assertEquals("Husky", election.findWinnerMostFirstVotes());
    }
}
