import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class Saab95Test {

    private final Saab95 saab95;

    public Saab95Test() {
        saab95 = new Saab95(new Point(50, 50));
    }

    @Test
    void setTurboOn() {
        assertFalse(saab95.isTurboOn());
        saab95.setTurboOn();
        assertTrue(saab95.isTurboOn());
    }

    @Test
    void setTurboOff() {
        assertFalse(saab95.isTurboOn());
        saab95.setTurboOn();
        assertTrue(saab95.isTurboOn());
        saab95.setTurboOff();
        assertFalse(saab95.isTurboOn());
    }
}