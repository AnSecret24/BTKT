package framework.test;

import framework.base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SampleTest extends BaseTest {
    @Test
    public void test1() {
        getDriver().get("https://www.saucedemo.com");
        Assert.assertTrue(getDriver().getTitle().contains("Swag Labs"));
    }

    @Test
    public void test2() {
        getDriver().get("https://www.saucedemo.com");
        Assert.assertTrue(getDriver().getTitle().contains("Swag Labs"));
    }

    @Test
    public void test3() {
        getDriver().get("https://www.saucedemo.com");
        Assert.fail("Cố tình fail để check screenshot Bài 1!");
    }
}