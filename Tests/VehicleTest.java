import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class VehicleTest {

    Saab95 saab95;
    Volvo240 volvo240;

    public VehicleTest(){
        saab95 = new Saab95(new Point(50, 50));
        volvo240 = new Volvo240(new Point(50, 50));
    }

    @org.junit.jupiter.api.Test
    void testSpeedFactor() {
        assertEquals(1.25, saab95.speedFactor());
        assertEquals(1.25, volvo240.speedFactor());
    }

    @org.junit.jupiter.api.Test
    void testMove() {
        assertEquals(100, saab95.position.x);
        assertEquals(100, saab95.position.y);
        saab95.move();
        assertEquals(101, saab95.position.x);
        assertEquals(100, saab95.position.y);

        assertEquals(100, volvo240.position.x);
        assertEquals(100, volvo240.position.y);
        volvo240.move();
        assertEquals(101, volvo240.position.x);
        assertEquals(100, volvo240.position.y);
    }

    @org.junit.jupiter.api.Test
    void testTurnLeft() {
        saab95.turnLeft();
        volvo240.turnLeft();
        for(int i=3; i>0; i--) {
            assertEquals(i, saab95.direction);
            saab95.turnLeft();
            assertEquals(i-1, saab95.direction);

            assertEquals(i, volvo240.direction);
            volvo240.turnLeft();
            assertEquals(i-1, volvo240.direction);
        }
    }

    @org.junit.jupiter.api.Test
    void testTurnRight() {
        for(int i=0; i<3; i++) {
            assertEquals(i, saab95.direction);
            saab95.turnRight();
            assertEquals(i+1, saab95.direction);

            assertEquals(i, volvo240.direction);
            volvo240.turnRight();
            assertEquals(i+1, volvo240.direction);
        }
    }

    @org.junit.jupiter.api.Test
    void testGas(){
        assertEquals(0.0, saab95.currentSpeed);
        saab95.startEngine();
        saab95.gas(1);
        assertEquals(1.35, saab95.currentSpeed);

        assertEquals(0.0, volvo240.currentSpeed);
        volvo240.startEngine();
        volvo240.gas(1);
        assertEquals(1.35, volvo240.currentSpeed);
    }



    @org.junit.jupiter.api.Test
    void testBrake(){
        saab95.startEngine();
        saab95.gas(1);
        assertEquals(1.35, saab95.currentSpeed);
        saab95.brake(1);
        assertEquals(0.0, (int) saab95.currentSpeed);

        volvo240.startEngine();
        volvo240.gas(1);
        assertEquals(1.35, volvo240.currentSpeed);
        volvo240.brake(1);
        assertEquals(0.0, (int) volvo240.currentSpeed);
    }
}