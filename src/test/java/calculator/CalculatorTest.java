package calculator;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    @org.junit.jupiter.api.Test
    void sum() {
        Calculator cal = new Calculator();
        assertEquals(2, cal.sum(1, 1), "1 + 1 must be 2");
    }

    @org.junit.jupiter.api.Test
    void sub() {
        Calculator cal = new Calculator();
        assertEquals(2,
                cal.sub(4, 2),
                "4 - 2  must be 2");
    }

}