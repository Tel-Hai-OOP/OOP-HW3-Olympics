import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GymnastEventTest {

    GymnastEvent allAround, pommelHorse, unevenBars;

    @Before
    public void setUp() {
        allAround = new GymnastEvent("All Around", Gender.ANY);
        pommelHorse = new GymnastEvent("Pommel Horse", Gender.MALE);
        unevenBars = new GymnastEvent("Uneven Bars", Gender.FEMALE);
    }

    @Test
    public void getJudgeCount() {
        assertEquals(7, allAround.getJudgeCount());
        assertEquals(7, pommelHorse.getJudgeCount());
        assertEquals(7, unevenBars.getJudgeCount());
    }

    @Test
    public void buildGymnastEvent() {
        try {
            String allAroundString = "G;All Around;7;ANY";
            var ge = GymnastEvent.buildGymnastEvent(allAroundString);
            assertEquals(ge, allAround);
        } catch (Exception ex) {
            // this shouldn't happen
            assert false;
        }

        String invalidFirstCharacter = "K;All Around;7;ANY";
        try {
            var g3 = GymnastEvent.buildGymnastEvent(invalidFirstCharacter);
            // this should fail
            assert false;
        } catch (Exception ex)
        {
            assertTrue(ex instanceof OlympicException);
        }

        String tooManyParts = "G;All Around;7;ANY;MALE";
        try {
            var g2 = GymnastEvent.buildGymnastEvent(tooManyParts);
            // this should fail
            assert false;
        } catch (Exception ex)
        {
            assertTrue(ex instanceof OlympicException);
        }

        String notEnoughParts = "G;All Around;ANY";
        try {
            var g3 = GymnastEvent.buildGymnastEvent(notEnoughParts);
            // this should fail
            assert false;
        } catch (Exception ex)
        {
            assertTrue(ex instanceof OlympicException);
        }

        String invalidNumber = "G;All Around;MALE;ANY";
        try {
            var g4 = GymnastEvent.buildGymnastEvent(invalidNumber);
            // this should fail
            assert false;
        } catch (Exception ex)
        {
            assertTrue(ex instanceof OlympicException);
        }
    }

    @Test
    public void testEquals() {
        assertNotEquals(pommelHorse, unevenBars);
        assertNotEquals(pommelHorse.hashCode(), unevenBars.hashCode());

        assertNotEquals(pommelHorse, allAround);
        assertNotEquals(pommelHorse.hashCode(), allAround.hashCode());

        assertNotEquals(unevenBars, pommelHorse);
        assertNotEquals(unevenBars.hashCode(), pommelHorse.hashCode());

        GymnastEvent ge = new GymnastEvent("Pommel Horse", Gender.MALE);
        assertEquals(ge, pommelHorse);
        assertEquals(ge.hashCode(), pommelHorse.hashCode());

        GymnastEvent g2 = new GymnastEvent("Pommel Horse",Gender.ANY);
        assertNotEquals(g2, pommelHorse);
        assertNotEquals(g2.hashCode(), pommelHorse.hashCode());
    }
}