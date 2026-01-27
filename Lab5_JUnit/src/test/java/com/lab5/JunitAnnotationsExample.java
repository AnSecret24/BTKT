package com.lab5;

import org.junit.*;

public class JunitAnnotationsExample {

    @BeforeClass
    public static void beforeClass() {
        System.out.println("BeforeClass - chạy 1 lần trước tất cả test");
    }

    @AfterClass
    public static void afterClass() {
        System.out.println("AfterClass - chạy 1 lần sau tất cả test");
    }

    @Before
    public void before() {
        System.out.println("Before - chạy trước mỗi test");
    }

    @After
    public void after() {
        System.out.println("After - chạy sau mỗi test");
    }

    @Test
    public void testCase1() {
        System.out.println("Test case 1 đang chạy");
    }

    @Ignore
    @Test
    public void testCase2() {
        System.out.println("Test case 2 bị bỏ qua");
    }

    @Test
    public void testCase3() {
        System.out.println("Test case 3 đang chạy");
    }
}
