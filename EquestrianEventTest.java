import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class EquestrianEventTest {

    EquestrianEvent dressage, jumping, eventing;

    @Before
    public void setUp() {
        dressage = new EquestrianEvent("Dressage", Gender.ANY);
        jumping = new EquestrianEvent("Jumping", Gender.ANY);
        eventing = new EquestrianEvent("Eventing", Gender.ANY);
    }

    @Test
    public void buildEquestrianEvent() {
        // test two valid ones
        try {
            String testDressage = "E;Dressage;5;ANY";
            EquestrianEvent e1 = EquestrianEvent.buildEquestrianEvent(testDressage);
            assertEquals(dressage, e1);
        } catch (Exception ex) {
            // this should not happen
            assert false;
        }

        try {
            String testEventing = "E;Eventing;5;ANY";
            EquestrianEvent e2 = EquestrianEvent.buildEquestrianEvent(testEventing);
            assertEquals(eventing, e2);
        } catch (Exception ex) {
            // this should not happen
            assert false;
        }

        String invalidFirstCharacter = "H;Dressage;5;ANY";
        try {
            EquestrianEvent e3 = EquestrianEvent.buildEquestrianEvent(invalidFirstCharacter);
            // this should fail
            assert false;
        } catch (Exception ex)
        {
            assertTrue(ex instanceof OlympicException);
        }

        String tooManyParts = "E;Eventing;5;ANY;5";
        try {
            EquestrianEvent e4 = EquestrianEvent.buildEquestrianEvent(tooManyParts);
            // this should fail
            assert false;
        } catch (Exception ex)
        {
            assertTrue(ex instanceof OlympicException);
        }

        String notEnoughParts = "E;Eventing;5";
        try {
            EquestrianEvent e4 = EquestrianEvent.buildEquestrianEvent(notEnoughParts);
            // this should fail
            assert false;
        } catch (Exception ex)
        {
            assertTrue(ex instanceof OlympicException);
        }

        String invalidNumber = "E;Eventing;Hello;ANY";
        try {
            EquestrianEvent e4 = EquestrianEvent.buildEquestrianEvent(invalidNumber);
            // this should fail
            assert false;
        } catch (Exception ex)
        {
            assertTrue(ex instanceof OlympicException);
        }
    }

    @Test
    public void getJudgeCount() {
        assertEquals(5, dressage.getJudgeCount());
        assertEquals(5, jumping.getJudgeCount());
        assertEquals(5, eventing.getJudgeCount());
    }

    @Test
    public void testEquals() {
        assertNotEquals(dressage, eventing);
        assertNotEquals(dressage.hashCode(), eventing.hashCode());

        assertNotEquals(dressage, jumping);
        assertNotEquals(dressage.hashCode(), jumping.hashCode());

        assertNotEquals(eventing, jumping);
        assertNotEquals(eventing.hashCode(), jumping.hashCode());

        EquestrianEvent dressage2 = new EquestrianEvent("Dressage", Gender.ANY);
        assertEquals(dressage, dressage2);
        assertEquals(dressage.hashCode(), dressage2.hashCode());
    }
}