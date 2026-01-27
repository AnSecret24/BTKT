package com.lab5;

import org.junit.*;
import static org.junit.Assert.*;

public class MathFuncTest {

    private MathFunc mathFunc;

    @Before
    public void setUp() {
        mathFunc = new MathFunc();
        System.out.println("Before test");
    }

    @After
    public void tearDown() {
        System.out.println("After test");
    }

    @Test
    public void calls() {
        mathFunc.plus(1, 2);
        mathFunc.factorial(3);
        assertEquals(2, mathFunc.getCalls());
    }

    @Test
    public void factorial() {
        assertEquals(120, mathFunc.factorial(5));
    }

    @Test(expected = IllegalArgumentException.class)
    public void factorialNegative() {
        mathFunc.factorial(-1);
    }

    @Ignore("Chưa cần test chức năng này")
    @Test
    public void todo() {
        assertEquals(5, mathFunc.plus(2, 3));
    }
}
