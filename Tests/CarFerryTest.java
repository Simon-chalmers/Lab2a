import org.junit.jupiter.api.Test;

import java.awt.*;
import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

class CarFerryTest {

    private final CarFerry carFerry;
    private final CarFerry secondCarFerry;
    private final Saab95 saab95;
    private final Scania scania;
    private final ScaniaCarCarrier scaniaCarCarrier;

    public CarFerryTest() {
        carFerry = new CarFerry(150);
        secondCarFerry = new CarFerry(150);
        saab95 = new Saab95(new Point(50, 50));
        scania = new Scania(new Point(50, 50));
        scaniaCarCarrier = new ScaniaCarCarrier(new Point(50, 50), 10);
    }

    @Test
    void load() {
        assertTrue(carFerry.load(saab95));
        assertFalse(carFerry.load(saab95));
        assertTrue(carFerry.load(scania));
        assertTrue(carFerry.load(scaniaCarCarrier));
        assertFalse(carFerry.load(scaniaCarCarrier));
    }

    @Test
    void unload() {
        assertTrue(carFerry.load(saab95));
        assertTrue(carFerry.load(scania));
        assertTrue(carFerry.load(scaniaCarCarrier));

        assertTrue(carFerry.unload(saab95));
        assertFalse(carFerry.getVehicles().contains(saab95));
        assertFalse(carFerry.unload(scaniaCarCarrier));
        assertTrue(carFerry.unload(scania));
        assertTrue(carFerry.unload(scaniaCarCarrier));
        assertFalse(carFerry.getVehicles().contains(scania));
        assertFalse(carFerry.getVehicles().contains(scaniaCarCarrier));
    }
}