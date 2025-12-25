import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class DoenerOrderTest {

    @Test
    void testGetDetailsNotEmpty() {
        DoenerOrder order = new DoenerOrder(
                "Lamm", "Yufka", 2,
                true, true, false, true, false
        );

        String details = order.getDetails();
        assertNotNull(details);
        assertFalse(details.isEmpty());
    }

    @Test
    void testAnzahlIsCorrect() {
        DoenerOrder order = new DoenerOrder(
                "Hähnchen", "Fladenbrot", 3,
                false, false, false, false, false
        );

        assertEquals(3, order.getAnzahl());
    }

    @Test
    void testGlutenfreiFlag() {
        DoenerOrder order = new DoenerOrder(
                "Kalb", "Teller", 1,
                true, false, false, false, false
        );

        assertTrue(order.isGlutenfrei());
    }
}

