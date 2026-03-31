package framework.utils;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {
    private int count = 0;
    // Số lần chạy lại tối đa (An có thể để 1 hoặc 2)
    private static final int MAX_RETRY = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (count < MAX_RETRY) {
            count++;
            System.out.println("[Retry] Đang chạy lại test case: " + result.getName() + " - Lần thứ " + count);
            return true; // Trả về true để TestNG chạy lại
        }
        return false; // Dừng lại nếu đã hết số lần retry
    }
}