import static org.junit.Assert.*;

public class EventTest {

    EquestrianEvent equestrianEventDressage;
    GymnastEvent gymnastEventAllAround, gymnastEventPommelHorse, gymnastEventUnevenBars;

    @org.junit.Before
    public void setUp() throws Exception {
        equestrianEventDressage = new EquestrianEvent("Dressage", Gender.ANY);
        gymnastEventAllAround = new GymnastEvent("All Around", Gender.ANY);
        gymnastEventUnevenBars = new GymnastEvent("Uneven Bars", Gender.FEMALE);
        gymnastEventPommelHorse = new GymnastEvent("Pommel Horse", Gender.MALE);
    }

    @org.junit.Test
    public void getEventName() {
        assertEquals("Dressage", equestrianEventDressage.getEventName());
        assertEquals("All Around", gymnastEventAllAround.getEventName());
        assertEquals("Uneven Bars", gymnastEventUnevenBars.getEventName());
        assertEquals("Pommel Horse", gymnastEventPommelHorse.getEventName());
    }

    @org.junit.Test
    public void getJudgeCount() {
        assertEquals(5, equestrianEventDressage.getJudgeCount());
        assertEquals(7, gymnastEventAllAround.getJudgeCount());
        assertEquals(7, gymnastEventUnevenBars.getJudgeCount());
        assertEquals(7, gymnastEventPommelHorse.getJudgeCount());
    }

    @org.junit.Test
    public void getAllowedGender() {
        assertEquals(Gender.ANY, equestrianEventDressage.getAllowedGender());
        assertEquals(Gender.ANY, gymnastEventAllAround.getAllowedGender());
        assertEquals(Gender.MALE, gymnastEventPommelHorse.getAllowedGender());
        assertEquals(Gender.FEMALE, gymnastEventUnevenBars.getAllowedGender());
    }

    @org.junit.Test
    public void testToString() {
        assertEquals("Event All Around with 7 judges for ANY athletes", gymnastEventAllAround.toString());
        assertEquals("Event Pommel Horse with 7 judges for MALE athletes", gymnastEventPommelHorse.toString());
        assertEquals("Event Uneven Bars with 7 judges for FEMALE athletes", gymnastEventUnevenBars.toString());
        assertEquals("Event Dressage with 5 judges for ANY athletes", equestrianEventDressage.toString());
    }

    @org.junit.Test
    public void testEquals() {
        EquestrianEvent eqSimilarDressage = new EquestrianEvent("Dressage", Gender.ANY);
        assertEquals(eqSimilarDressage, equestrianEventDressage);
        assertEquals(equestrianEventDressage, equestrianEventDressage);
        assertNotEquals(equestrianEventDressage, gymnastEventAllAround);
        assertNotEquals(equestrianEventDressage, null);
        assertNotEquals(eqSimilarDressage, gymnastEventAllAround);

    }

    @org.junit.Test
    public void compareTo() {
        EquestrianEvent eqSimilarDressage = new EquestrianEvent("Dressage", Gender.ANY);
        // check that the alphabetic ordering works
        assertTrue(equestrianEventDressage.compareTo(gymnastEventAllAround) > 0);
        assertTrue(gymnastEventAllAround.compareTo(gymnastEventPommelHorse) < 0);
        assertTrue(gymnastEventPommelHorse.compareTo(gymnastEventAllAround) > 0);
        assertTrue(gymnastEventUnevenBars.compareTo(gymnastEventAllAround) > 0);

        // check that similar events return 0
        assertEquals(0, equestrianEventDressage.compareTo(eqSimilarDressage));

        // Test that the gender is ignored in the comparison
        GymnastEvent gymnastAllAroundMale = new GymnastEvent("All Around", Gender.MALE);
        assertEquals(0, gymnastAllAroundMale.compareTo(gymnastEventAllAround));
    }
}