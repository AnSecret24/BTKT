package fpoly;

import com.quanlyduadon.fpoly.ErrorCollectorExample;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRuner {

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(ErrorCollectorExample.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        System.out.println("Total Failures: " + result.getFailureCount());
        System.out.println("Successful: " + result.wasSuccessful());
    }
}
