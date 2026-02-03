package com.quanlyduadon.fpoly;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ErrorCollector;

public class ErrorCollectorExample {

    @Rule
    public ErrorCollector collector = new ErrorCollector();

    @Test
    public void exampleTest() {

        collector.addError(new AssertionError("Lỗi 1"));
        collector.addError(new AssertionError("Lỗi 2"));
        collector.addError(new AssertionError("Lỗi 3"));

        System.out.println("Test vẫn chạy hết dù có lỗi");
    }
}
