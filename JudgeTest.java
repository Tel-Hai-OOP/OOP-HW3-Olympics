import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.*;

public class JudgeTest {

    Judge allAroundJudge1, allAroundJudge2, pommelHorseJudge1, unevenBarsJudge1, allAroundJudge5, allAroundJudge6, allAroundJudge7,
            dressageJudge1, dressageJudge2, dressageJudge3, dressageJudge4, dressageJudge5;
    GymnastEvent allAround;
    GymnastEvent pommelHorse;
    GymnastEvent unevenBars;
    EquestrianEvent dressage;

    @Before
    public void setUp() {

        // get some events so we can use them
        allAround = new GymnastEvent("All Around", Gender.ANY);
        pommelHorse = new GymnastEvent("Pommel Horse", Gender.MALE);
        unevenBars = new GymnastEvent("Uneven Bars", Gender.FEMALE);
        dressage =  new EquestrianEvent("Dressage", Gender.ANY);

        OlympicInformation.eventsArray = new Event[3];
        OlympicInformation.eventsArray[0] = allAround;
        OlympicInformation.eventsArray[1] = pommelHorse;
        OlympicInformation.eventsArray[2] = unevenBars;
        allAroundJudge1 = new Judge("Alice", "Afghanistan", allAround, LocalDate.parse("2024-01-10"));
        allAroundJudge2 = new Judge("Bob", "Bhutan", allAround, LocalDate.parse("2024-01-10"));
        pommelHorseJudge1 = new Judge("Cid", "China", pommelHorse, LocalDate.parse("2023-05-10"));
        unevenBarsJudge1 = new Judge("Dan", "Denmark", unevenBars, LocalDate.parse("2023-05-11"));
        allAroundJudge5 = new Judge("Ed", "Afghanistan", allAround, LocalDate.parse("2023-05-11"));
        allAroundJudge6 = new Judge("Fran", "Afghanistan", allAround, LocalDate.parse("2023-05-11"));
        allAroundJudge7 = new Judge("Greg", "Afghanistan", allAround, LocalDate.parse("2023-05-11"));

        dressageJudge1 = new Judge("Alice", "Afghanistan", dressage, LocalDate.parse("2024-01-10"));
        dressageJudge2 = new Judge("Bob", "Afghanistan", dressage, LocalDate.parse("2024-01-10"));
        dressageJudge3 = new Judge("Cid", "China", dressage, LocalDate.parse("2023-05-10"));
        dressageJudge4 = new Judge("Dan", "Denmark", dressage, LocalDate.parse("2023-05-11"));
        dressageJudge5 = new Judge("Ed", "Afghanistan", dressage, LocalDate.parse("2023-05-11"));
    }

    @Test
    public void buildJudge() {

        // two correct ones
        String hank = "Hank;Italy;All Around;2000-01-03";
        try {
            var j1 = Judge.buildJudge(hank);
            assertEquals("Hank", j1.getJudgeName());
            assertEquals("Italy", j1.getNation());
            assertEquals("All Around", j1.getEvent().getEventName());
            assertEquals(LocalDate.of(2000, 1, 3), j1.getBirthdate());
        } catch (Exception ex) {
            // this should not happen
            assert false;
        }

        String ichabod = "Ichabod;Thailand;Pommel Horse;2000-10-20";
        try {
            var j1 = Judge.buildJudge(ichabod);
            assertEquals("Ichabod", j1.getJudgeName());
            assertEquals("Thailand", j1.getNation());
            assertEquals("Pommel Horse", j1.getEvent().getEventName());
            assertEquals(LocalDate.of(2000, 10, 20), j1.getBirthdate());
        } catch (Exception ex) {
            // this should not happen
            assert false;
        }

        // test a judge with a non-existent event
        String fran = "Fran;Holland;Jumping;1997-10-10";
        try {
            var j1 = Judge.buildJudge(fran);
            // this should fail
            assert false;
        } catch (Exception ex) {
            assertTrue(ex instanceof OlympicException);
        }

        // test with too many parameters
        String tooManyParameters = "Fran;Holland;Jumping;1997-10-10;FEMALE";
        try {
            var j1 = Judge.buildJudge(tooManyParameters);
            // this should fail
            assert false;
        } catch (Exception e) {
            assertTrue(e instanceof OlympicException);
        }

        // test with too few parameters
        String missingParameters = "Fran;Holland;1997-10-10";
        try {
            var j1 = Judge.buildJudge(missingParameters);
            // this should fail
            assert false;
        } catch (Exception e) {
            assertTrue(e instanceof OlympicException);
        }

        // test with invalid date
        String invalidDate = "Fran;Holland;1997-104-10";
        try {
            var j1 = Judge.buildJudge(invalidDate);
            // this should fail
            assert false;
        } catch (Exception e) {
            assertTrue(e instanceof OlympicException);
        }
    }

    @Test
    public void getNation() {
        assertEquals("Afghanistan", allAroundJudge1.getNation());
        assertEquals("Bhutan", allAroundJudge2.getNation());
        assertEquals("China", pommelHorseJudge1.getNation());
        assertEquals("Denmark", unevenBarsJudge1.getNation());
    }

    @Test
    public void getEvent() {
        assertEquals(allAround, allAroundJudge1.getEvent());
        assertNotEquals(pommelHorse, allAroundJudge1.getEvent());
        assertEquals(allAroundJudge1.getEvent(), allAroundJudge2.getEvent());
        assertEquals(pommelHorse, pommelHorseJudge1.getEvent());
    }

    @Test
    public void setNation() {
        String aliceOldNation = allAroundJudge1.getNation();
        allAroundJudge1.setNation("Argentina");
        assertEquals("Argentina", allAroundJudge1.getNation());
        assertNotEquals(aliceOldNation, allAroundJudge1.getNation());
        allAroundJudge1.setNation(aliceOldNation);
        assertEquals(aliceOldNation, allAroundJudge1.getNation());
        assertNotEquals("Argentina", allAroundJudge1.getNation());
    }

    @Test
    public void canJudge() {
        assertTrue(allAroundJudge1.canJudge(allAround));
        assertTrue(allAroundJudge2.canJudge(allAround));
        assertTrue(pommelHorseJudge1.canJudge(pommelHorse));
        assertTrue(unevenBarsJudge1.canJudge(unevenBars));
        assertFalse(allAroundJudge1.canJudge(pommelHorse));
        assertFalse(allAroundJudge1.canJudge(unevenBars));
        assertFalse(allAroundJudge2.canJudge(pommelHorse));
        assertFalse(allAroundJudge2.canJudge(unevenBars));
    }

    @Test
    public void testToString() {
        assertTrue(allAroundJudge1.toString().matches("Judge\s+Alice\s+born\s+2024-01-10\s+from\s+Afghanistan\s+for\s+All Around\s*"));
        assertTrue(allAroundJudge2.toString().matches("Judge\s+Bob\s+born\s+2024-01-10\s+from\s+Bhutan\s+for\s+All Around\s*"));
    }

    @Test
    public void getBirthdate() {
        assertEquals(LocalDate.of(2024, 1, 10), allAroundJudge1.getBirthdate());
        assertEquals(LocalDate.of(2023, 5 ,11), unevenBarsJudge1.getBirthdate());
    }

    @Test
    public void setBirthdate() {
        LocalDate aliceOldBirthdate = allAroundJudge1.getBirthdate();
        allAroundJudge1.setBirthdate(LocalDate.parse("2023-12-25"));
        assertEquals(LocalDate.of(2023, 12, 25), allAroundJudge1.getBirthdate());
        allAroundJudge1.setBirthdate(aliceOldBirthdate);
        assertEquals(LocalDate.of(2024, 1, 10), allAroundJudge1.getBirthdate());
        assertNotEquals(LocalDate.of(2023, 12, 25), allAroundJudge1.getBirthdate());
    }

    @Test
    public void getJudgeName() {
        assertEquals("Alice", allAroundJudge1.getJudgeName());
        assertEquals("Bob", allAroundJudge2.getJudgeName());
        assertEquals("Cid", pommelHorseJudge1.getJudgeName());
    }

    @Test
    public void compareTo() {
        // compare by name
        assertTrue(allAroundJudge1.compareTo(allAroundJudge2) < 0);
        assertTrue(allAroundJudge1.compareTo(pommelHorseJudge1) < 0);
        assertTrue(allAroundJudge1.compareTo(unevenBarsJudge1) < 0);
        assertTrue(allAroundJudge2.compareTo(allAroundJudge1) > 0);

        var judgeAliceLater = new Judge("Alice", "Afghanistan", allAround, allAroundJudge1.getBirthdate().plusDays(1));
        assertTrue(allAroundJudge1.compareTo(judgeAliceLater) < 0);

        // check when the name and birthday are the same
        var aliceClone = new Judge("Alice", "Afghanistan", allAround, allAroundJudge1.getBirthdate());
        assertEquals(0, allAroundJudge1.compareTo(aliceClone));
        assertEquals(0, allAroundJudge1.compareTo(allAroundJudge1));

        // check when name and birthday are the same, but country is different
        aliceClone.setNation("Argentina");
        assertTrue(allAroundJudge1.compareTo(aliceClone) < 0);

        // check when name, birthday, country the same, but event is different
        aliceClone = new Judge("Alice", "Afghanistan", pommelHorse, allAroundJudge1.getBirthdate());
        assertTrue(allAroundJudge1.compareTo(aliceClone) < 0);
    }

    @Test
    public void testEquals() {
        assertNotEquals(allAroundJudge1, dressageJudge1);
        assertNotEquals(allAroundJudge1.hashCode(), dressageJudge1.hashCode());

        assertNotEquals(allAroundJudge2, allAroundJudge1);
        assertNotEquals(allAroundJudge2.hashCode(), allAroundJudge1.hashCode());

        // compare two identical ones
        var aliceClone = new Judge("Alice", "Afghanistan", allAround, allAroundJudge1.getBirthdate());
        assertEquals(aliceClone, allAroundJudge1);
        assertEquals(aliceClone.hashCode(), allAroundJudge1.hashCode());
    }
}