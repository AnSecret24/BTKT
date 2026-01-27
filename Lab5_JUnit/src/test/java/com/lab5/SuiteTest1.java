package com.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

public class SuiteTest1 {

    @Test
    public void testMessage1() {
        String message = "Hello from SuiteTest1";
        System.out.println(message);
        assertEquals("Hello from SuiteTest1", message);
    }
}
