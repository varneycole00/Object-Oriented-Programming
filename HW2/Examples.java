import org.junit.Test;

import java.util.LinkedList;

import static org.junit.Assert.*;

public class Examples {

    @Test
    public void testTournament() {
        LinkedList<Match> matchesInInitRound2 = new LinkedList<Match>();
        matchesInInitRound2.add(rugbyMatchSpainVsNorway);
        matchesInInitRound2.add(rugbyMatchDenmarkSwitzerland);
        matchesInInitRound2.add(rugbyMatchNorwaySwitzerland);

        InitRound tourniInit = new InitRound(matchesInInitRound2);

        LinkedList<IContestant> initRoundWinners2 = new LinkedList<>();
        initRoundWinners2.add(switzerland);
        initRoundWinners2.add(norway);

        LinkedList<Match> matchesInAdvancedRound2 = new LinkedList<Match>();
        matchesInAdvancedRound2.add(rugbyMatchDenmarkSwitzerland);
        matchesInAdvancedRound2.add(rugbyMatchNorwaySwitzerland);

        AdvancedRound tourniAdv = new AdvancedRound(matchesInAdvancedRound2, initRoundWinners2);

        LinkedList<IWinner> tournamentRounds = new LinkedList<IWinner>();
        tournamentRounds.add(tourniInit);
        tournamentRounds.add(tourniAdv);

        Tournament tournament = new Tournament(tournamentRounds);

        assertTrue(tournament.finalWinnerIsValid(norway));
        assertFalse(tournament.finalWinnerIsValid(denmark));

    }

    @Test
    public void testInitRound() {
        LinkedList<Match> matchesInInitRound = new LinkedList<Match>();
        matchesInInitRound.add(rugbyMatchDenmarkSwitzerland);
        matchesInInitRound.add(rugbyMatchNorwaySwitzerland);

        LinkedList<IContestant> initRoundWinners = new LinkedList<>();
        initRoundWinners.add(switzerland);
        initRoundWinners.add(norway);

        InitRound initRound = new InitRound(matchesInInitRound);

        assertEquals(initRoundWinners, initRound.getMatchWinners());
        assertEquals(2, initRound.getNumWinners());
        assertTrue(initRound.isWinner(norway));
        assertFalse(initRound.isWinner(denmark));
    }

    @Test
    public void testAdvancedRound() {
        LinkedList<Match> matchesInAdvancedRound = new LinkedList<Match>();
        matchesInAdvancedRound.add(rugbyMatchDenmarkSwitzerland);
        matchesInAdvancedRound.add(rugbyMatchNorwaySwitzerland);

        LinkedList<IContestant> advancedRoundWinners = new LinkedList<IContestant>();
        advancedRoundWinners.add(switzerland);
        advancedRoundWinners.add(norway);

        AdvancedRound advancedRound = new AdvancedRound(matchesInAdvancedRound, advancedRoundWinners);

        assertEquals(advancedRoundWinners, advancedRound.getMatchWinners());
        assertEquals(2, advancedRound.getNumWinners());
        assertTrue(advancedRound.isWinner(switzerland));
        assertFalse(advancedRound.isWinner(denmark));
    }

    RugbyTeam norway = new RugbyTeam("Norway", "red", true, 2, 3);
    RugbyTeam switzerland = new RugbyTeam("Switzerland", "blue", true, 5, 3);
    RugbyTeam denmark = new RugbyTeam("Denmark", "orange", false, 2, 2);
    RugbyTeam spain = new RugbyTeam("Spain", "orange", true, 3,2);

    RugbyResult spainVsNorway = new RugbyResult(spain, norway, 70, 80);
    RugbyResult denmarkVsSwitzerland = new RugbyResult(denmark, switzerland, 60, 90);
    RugbyResult norwayVsSwitzerland = new RugbyResult(norway, switzerland, 50.0, 30);
    RugbyResult denmarkVsNorway = new RugbyResult(denmark, norway, 160.0, 100.0);

    Match rugbyMatchSpainVsNorway = new Match(spain, norway, spainVsNorway);
    Match rugbyMatchDenmarkNorway = new Match(denmark, norway, denmarkVsNorway);
    Match rugbyMatchNorwaySwitzerland = new Match(norway, switzerland, norwayVsSwitzerland);
    Match rugbyMatchDenmarkSwitzerland = new Match(denmark, switzerland, denmarkVsSwitzerland);

    @Test
    public void testExpectToBeatByIntimidationTrue() {
        assertTrue(norway.expectToBeat(denmark));
    }

    @Test
    public void testExpectToBeatByIntimidationFalse() {
        assertFalse(denmark.expectToBeat(norway));
    }

    @Test
    public void testExpectToBeatByRecordTrue() {
        assertTrue(switzerland.expectToBeat(norway));
    }

    @Test
    public void TestExpectToBeatByRecordFalse() {
        assertFalse(norway.expectToBeat(switzerland));
    }

    @Test
    public void testInvalidRugbyWinner() {
        assertNull(rugbyMatchDenmarkNorway.winner());
    }

    @Test
    public void testValidRugbyWInner() {
        assertEquals(norway, rugbyMatchNorwaySwitzerland.winner());
    }

    @Test
    public void testNorwayVsSwitzerlandResultValid() {
        assertTrue(norwayVsSwitzerland.isValid());
    }

    @Test
    public void testDenmarkVsNorwayResultValid() {
        assertFalse(denmarkVsNorway.isValid());
    }

    RoboticsTeam lakeGeorge = new RoboticsTeam("Lake George", "water gun", 10);
    RoboticsTeam queensbury = new RoboticsTeam("Queensbury", "battleaxe", 6);
    RoboticsTeam granvile = new RoboticsTeam("Granville", "claw", 7);
    RoboticsTeam teamWNoRecord1 = new RoboticsTeam("N/A", "Something", 0);
    RoboticsTeam teamWNoRecord2 = new RoboticsTeam("N/A", "Something", 0);

    RoboticsResult lakeGeorgeVsGranville = new RoboticsResult(
            lakeGeorge, granvile,
            10, 5, false,
            8, 10, true);

    RoboticsResult queensburyVsLakeGeorge = new RoboticsResult(
            queensbury, lakeGeorge,
            4, 3, true,
            6, 5, false);

    Match matchQueensburyLakeGeorge = new Match(queensbury, lakeGeorge, queensburyVsLakeGeorge);
    Match matchLakgeGeorgeGranville = new Match(lakeGeorge,granvile, lakeGeorgeVsGranville);


    @Test
    public void expectToBeatTrue() {
        assertTrue(lakeGeorge.expectToBeat(queensbury));
    }

    @Test
    public void expectToBeatFalse() {
        assertFalse(queensbury.expectToBeat(lakeGeorge));
    }

    @Test
    public void expectTobeatFalseByNoRecords() {
        assertFalse(teamWNoRecord1.expectToBeat(teamWNoRecord2));
    }

    @Test
    public void testInvalidWinner() {
        assertNull(matchLakgeGeorgeGranville.winner());
    }

    @Test
    public void testValidWinner() {
        assertEquals(lakeGeorge, matchQueensburyLakeGeorge.winner());
    }

    @Test
    public void testValidScoreCalculation2() {
        assertEquals(4, RoboticsResult.getScore(2.0, 2, false));
    }
    @Test
    public void testValidScoreCalculation() {
        assertEquals(2, RoboticsResult.getScore(4, 3, true));
    }

    @Test
    public void testValidQueensburyVsLakeGeorge() {
        assertTrue(queensburyVsLakeGeorge.isValid());
    }

    @Test
    public void testValidLakgeGeorgevsGranville() {
        assertFalse(lakeGeorgeVsGranville.isValid());
    }

}
