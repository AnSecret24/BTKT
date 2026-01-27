package com.lab5;

import org.junit.Test;
import static org.junit.Assert.*;

public class SuiteTest2 {

    @Test
    public void testMessage2() {
        String message = "Hello from SuiteTest2";
        System.out.println(message);
        assertEquals("Hello from SuiteTest2", message);
    }
}
