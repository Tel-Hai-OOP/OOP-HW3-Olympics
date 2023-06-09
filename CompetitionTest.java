import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CompetitionTest {

    Judge allAroundJudge1, allAroundJudge2, allAroundJudge3, allAroundJudge4, allAroundJudge5, allAroundJudge6, allAroundJudge7,
            dressageJudge1, dressageJudge2, dressageJudge3, dressageJudge4, dressageJudge5;
    GymnastEvent allAround, pommelHorse, unevenBars;
    EquestrianEvent dressage;
    Competition competitionDressageQualifier, competitionDressageQuarterFinal, competitionDressageSemiFinal, competitionDressageFinal;
    Competition competitionAllAroundQualifier, competitionAllAroundQuarterFinal, competitionAllAroundSemifinal, competitionAllAroundFinal;

    @Before
    public void setUp() throws Exception {
        allAround = new GymnastEvent("All Around", Gender.ANY);
        pommelHorse = new GymnastEvent("Pommel Horse", Gender.MALE);
        unevenBars = new GymnastEvent("Uneven Bars", Gender.FEMALE);
        dressage = new EquestrianEvent("Dressage", Gender.ANY);

        OlympicInformation.eventsArray = new Event[4];
        OlympicInformation.eventsArray[0] = allAround;
        OlympicInformation.eventsArray[1] = pommelHorse;
        OlympicInformation.eventsArray[2] = unevenBars;
        OlympicInformation.eventsArray[3] = dressage;

        allAroundJudge1 = new Judge("Alice", "Afghanistan", allAround, LocalDate.parse("2024-01-10"));
        allAroundJudge2 = new Judge("Bob", "Bhutan", allAround, LocalDate.parse("2024-01-10"));
        allAroundJudge3 = new Judge("Cid", "China", allAround, LocalDate.parse("2023-05-10"));
        allAroundJudge4 = new Judge("Dan", "Denmark", allAround, LocalDate.parse("2023-05-11"));
        allAroundJudge5 = new Judge("Ed", "Afghanistan", allAround, LocalDate.parse("2023-05-11"));
        allAroundJudge6 = new Judge("Fran", "Afghanistan", allAround, LocalDate.parse("2023-05-11"));
        allAroundJudge7 = new Judge("Greg", "Afghanistan", allAround, LocalDate.parse("2023-05-11"));

        dressageJudge1 = new Judge("Alice", "Afghanistan", dressage, LocalDate.parse("2024-01-10"));
        dressageJudge2 = new Judge("Bob", "Afghanistan", dressage, LocalDate.parse("2024-01-10"));
        dressageJudge3 = new Judge("Cid", "China", dressage, LocalDate.parse("2023-05-10"));
        dressageJudge4 = new Judge("Dan", "Denmark", dressage, LocalDate.parse("2023-05-11"));
        dressageJudge5 = new Judge("Ed", "Afghanistan", dressage, LocalDate.parse("2023-05-11"));

        OlympicInformation.judgesReversed  = new ArrayList<>();
        OlympicInformation.judgesReversed.add(allAroundJudge1);
        OlympicInformation.judgesReversed.add(allAroundJudge2);
        OlympicInformation.judgesReversed.add(allAroundJudge3);
        OlympicInformation.judgesReversed.add(allAroundJudge4);
        OlympicInformation.judgesReversed.add(allAroundJudge5);
        OlympicInformation.judgesReversed.add(allAroundJudge6);
        OlympicInformation.judgesReversed.add(allAroundJudge7);
        OlympicInformation.judgesReversed.add(dressageJudge1);
        OlympicInformation.judgesReversed.add(dressageJudge2);
        OlympicInformation.judgesReversed.add(dressageJudge3);
        OlympicInformation.judgesReversed.add(dressageJudge4);
        OlympicInformation.judgesReversed.add(dressageJudge5);

        competitionDressageQualifier = new Competition(dressage, LocalDate.parse("2023-08-10"), OlympicInformation.judgesReversed.stream().filter(o -> o.canJudge(dressage)).toList(),
                CompetitionLevel.QUALIFIER);
        competitionDressageQuarterFinal = new Competition(dressage, LocalDate.parse("2023-08-11"), OlympicInformation.judgesReversed.stream().filter(o -> o.canJudge(dressage)).toList(),
                CompetitionLevel.QUARTER_FINAL);
        competitionDressageSemiFinal = new Competition(dressage, LocalDate.parse("2023-08-11"), OlympicInformation.judgesReversed.stream().filter(o -> o.canJudge(dressage)).toList(),
                CompetitionLevel.SEMI_FINAL);
        competitionDressageFinal = new Competition(dressage, LocalDate.parse("2023-08-12"), OlympicInformation.judgesReversed.stream().filter(o -> o.canJudge(dressage)).toList(),
                CompetitionLevel.FINAL);

        competitionAllAroundQualifier = new Competition(allAround, LocalDate.parse("2023-08-11"), OlympicInformation.judgesReversed.stream().filter( o -> o.canJudge(allAround)).toList(),
                CompetitionLevel.QUALIFIER);
        competitionAllAroundQuarterFinal = new Competition(allAround, LocalDate.parse("2023-08-11"), OlympicInformation.judgesReversed.stream().filter( o -> o.canJudge(allAround)).toList(),
                CompetitionLevel.QUARTER_FINAL);
        competitionAllAroundSemifinal = new Competition(allAround, LocalDate.parse("2023-08-12"), OlympicInformation.judgesReversed.stream().filter( o -> o.canJudge(allAround)).toList(),
                CompetitionLevel.SEMI_FINAL);
        competitionAllAroundFinal = new Competition(allAround, LocalDate.parse("2023-08-13"), OlympicInformation.judgesReversed.stream().filter( o -> o.canJudge(allAround)).toList(),
                CompetitionLevel.FINAL);
    }

    @Test
    public void parseCompetition() {

        // try two correct ones
        String dressageQualString = "Dressage;QUALIFIER;2024-01-10;Alice,Bob,Cid,Dan,Ed";
        try {
            Competition c1 = Competition.buildCompetition(dressageQualString);
            assertEquals(dressage, c1.getEvent());
            assertEquals(CompetitionLevel.QUALIFIER, c1.getCompetitionLevel());
            assertEquals(LocalDate.of(2024, 1, 10), c1.getCompetitionDate());
            assertTrue(c1.getJudges().contains(dressageJudge1));
            assertTrue(c1.getJudges().contains(dressageJudge2));
            assertTrue(c1.getJudges().contains(dressageJudge3));
            assertTrue(c1.getJudges().contains(dressageJudge4));
            assertTrue(c1.getJudges().contains(dressageJudge5));
            assertFalse(c1.getJudges().contains(allAroundJudge1));
        } catch (OlympicException e) {
            // this should not happen
            fail();
        }

        String allAroundFinalString = "All Around;FINAL;2024-01-10;Alice,Bob,Cid,Dan,Ed,Fran,Greg";
        try {
            Competition c2 = Competition.buildCompetition(allAroundFinalString);
            assertEquals(allAround, c2.getEvent());
            assertEquals(LocalDate.of(2024, 1, 10), c2.getCompetitionDate());
            assertEquals(CompetitionLevel.FINAL, c2.getCompetitionLevel());
            assertTrue(c2.getJudges().contains(allAroundJudge1));
            assertTrue(c2.getJudges().contains(allAroundJudge2));
            assertTrue(c2.getJudges().contains(allAroundJudge3));
            assertTrue(c2.getJudges().contains(allAroundJudge4));
            assertTrue(c2.getJudges().contains(allAroundJudge5));
            assertTrue(c2.getJudges().contains(allAroundJudge6));
            assertTrue(c2.getJudges().contains(allAroundJudge7));
            assertFalse(c2.getJudges().contains(dressageJudge1));
        } catch (Exception ex) {
            // this should not happen
            fail();
        }

        // competition with missing field
        String competitionMissingField = "Dressage;QUALIFIER;Alice,Bob,Cid,Dan,Ed";
        try {
            Competition c3 = Competition.buildCompetition(competitionMissingField);
            fail(); // this should not happen
        } catch (Exception e) {
            assertTrue(e instanceof OlympicException);
        }

        // competition with extra field
        String competitionExtraField = "Dressage;QUALIFIER;2023-01-01;2024-07-09;Alice,Bob,Cid,Dan,Ed";
        try {
            Competition c3 = Competition.buildCompetition(competitionExtraField);
            fail(); // this should not happen
        } catch (Exception e) {
            assertTrue(e instanceof OlympicException);
        }

        // competition with event that is not found in the list of events
        String notFoundEvent = "Zulu;FINAL;2024-01-10;Alice,Bob,Cid,Dan,Ed,Fran,Greg";
        try {
            Competition c4 = Competition.buildCompetition(notFoundEvent);
            fail(); // this should not happen
        } catch (Exception e) {
            assertTrue(e instanceof OlympicException);
        }

        // competition with an invalid level
        String invalidLevel = "Dressage;TRYOUT;2024-01-10;Alice,Bob,Cid,Dan,Ed,Fran,Greg";
        try {
            Competition c4 = Competition.buildCompetition(invalidLevel);
            fail(); // this should not happen
        } catch (Exception e) {
            assertTrue(e instanceof OlympicException);
        }

        // competition without sufficient judges for it (parameters not enough)
        String competitionWithNoJudges = "Pommel Horse;FINAL;2024-01-10;Alice,Bob,Cid,Dan,Ed,Fran,Greg";
        try {
            Competition c5 = Competition.buildCompetition(competitionWithNoJudges);
            fail(); // this should not happen
        } catch (Exception e) {
            assertTrue(e instanceof OlympicException);
        }

        // competition where only a few judges in the list can judge it
        String competitionWithSomeWrongJudges = "Dressage;FINAL;2024-01-10;Alice,Bob,Cid,Dan,Greg";
        try {
            Competition c6 = Competition.buildCompetition(competitionWithSomeWrongJudges);
            fail(); // this should not happen
        } catch (Exception e) {
            assertTrue(e instanceof OlympicException);
        }
    }

    @Test
    public void getEvent() {
        assertEquals(dressage, competitionDressageQualifier.getEvent());
        assertEquals(allAround, competitionAllAroundQualifier.getEvent());
    }

    @Test
    public void getCompetitionDate() {
        assertEquals(LocalDate.of(2023, 8, 10), competitionDressageQualifier.getCompetitionDate());
        assertEquals(LocalDate.of(2023, 8, 13), competitionAllAroundFinal.getCompetitionDate());
    }

    @Test
    public void getJudges() {
        // check the contents
        assertTrue(competitionDressageQualifier.getJudges().contains(dressageJudge1));
        assertTrue(competitionDressageQualifier.getJudges().contains(dressageJudge2));
        assertTrue(competitionDressageQualifier.getJudges().contains(dressageJudge3));
        assertTrue(competitionDressageQualifier.getJudges().contains(dressageJudge4));
        assertTrue(competitionDressageQualifier.getJudges().contains(dressageJudge5));
        assertEquals(5, competitionDressageQualifier.getJudges().size());

        // check that if we modify the judge list after retrieving that it doesn't affect the result
        List<Judge> oldDressageJudges = new ArrayList<>(competitionDressageFinal.getJudges());
        oldDressageJudges.remove(dressageJudge1);
        assertEquals(5, competitionDressageFinal.getJudges().size());
    }

    @Test
    public void getCompetitionLevel() {
        assertEquals(CompetitionLevel.QUALIFIER, competitionDressageQualifier.getCompetitionLevel());
        assertEquals(CompetitionLevel.FINAL, competitionAllAroundFinal.getCompetitionLevel());
    }

    @Test
    public void testToString() {
        assertTrue(competitionDressageQualifier.toString().matches("2023-08-10:\s+Competition\s+QUALIFIER\s+event\s+Dressage\s+with judges\s+Alice\s+Bob\s+Cid\s+Dan\s+Ed\s*"));
    }

    @Test
    public void compareTo() {
        // check equals
        assertEquals(0, competitionDressageQualifier.compareTo(competitionDressageQualifier));
        try {
            Competition c1 = new Competition(competitionDressageQualifier.getEvent(), competitionDressageQualifier.getCompetitionDate(),
                    competitionDressageQualifier.getJudges(), competitionDressageQualifier.getCompetitionLevel());
            assertEquals(0, c1.compareTo(competitionDressageQualifier));
            assertEquals(0, competitionDressageQualifier.compareTo(c1));
        } catch (OlympicException ex) {
            // this shouldn't happen
            fail();
        }

        // check less than by date - if the date is earlier, that's first
        assertTrue(competitionDressageQualifier.compareTo(competitionAllAroundQualifier) < 0);
        assertTrue(competitionAllAroundQualifier.compareTo(competitionDressageQualifier) > 0);
        assertTrue(competitionDressageQualifier.compareTo(competitionDressageQuarterFinal) < 0);
        assertTrue(competitionDressageQualifier.compareTo(competitionDressageSemiFinal) < 0);
        assertTrue(competitionDressageQualifier.compareTo(competitionDressageFinal) < 0);
        assertTrue(competitionDressageQualifier.compareTo(competitionAllAroundQualifier) < 0);
        assertTrue(competitionDressageQualifier.compareTo(competitionAllAroundQuarterFinal) < 0);
        assertTrue(competitionDressageQualifier.compareTo(competitionAllAroundSemifinal) < 0);
        assertTrue(competitionDressageQualifier.compareTo(competitionAllAroundFinal) < 0);

        // check for equal dates, based on competition level
        assertTrue(competitionDressageQuarterFinal.compareTo(competitionDressageSemiFinal) < 0);
        assertTrue(competitionAllAroundQualifier.compareTo(competitionAllAroundQuarterFinal) < 0);
        assertTrue(competitionDressageFinal.compareTo(competitionAllAroundSemifinal) > 0);

        // check for equal dates, equal level, then competition name
        assertTrue(competitionDressageQuarterFinal.compareTo(competitionAllAroundQuarterFinal) > 0);
        assertTrue(competitionAllAroundQuarterFinal.compareTo(competitionDressageQuarterFinal) < 0);
    }

    @Test
    public void testEquals() {
        assertNotEquals(competitionDressageQualifier, competitionDressageFinal);
        assertNotEquals(competitionDressageQualifier.hashCode(), competitionDressageFinal.hashCode());
        assertEquals(competitionDressageQualifier, competitionDressageQualifier);
        assertEquals(competitionDressageQualifier.hashCode(), competitionDressageQualifier.hashCode());

        try {
            Competition clone = new Competition(competitionDressageQualifier.getEvent(), competitionDressageQualifier.getCompetitionDate(),
                    competitionDressageQualifier.getJudges(), competitionDressageQualifier.getCompetitionLevel());

            assertEquals(clone, competitionDressageQualifier);
            assertEquals(clone.hashCode(), competitionDressageQualifier.hashCode());

            clone = new Competition(competitionDressageQualifier.getEvent(), competitionDressageQualifier.getCompetitionDate(),
                    competitionDressageQualifier.getJudges(), CompetitionLevel.QUARTER_FINAL);
            assertNotEquals(clone, competitionDressageQualifier);
            assertNotEquals(clone.hashCode(), competitionDressageQualifier.hashCode());

        } catch (OlympicException e) {
           assert false; // this shouldn't happen
        }
    }
}