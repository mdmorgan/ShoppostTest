package theTests;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
	
	//private static String[] defaultTest = { "signupValid", "signupNoPW", "signupShortPW", "signupMismatchPW" };
	private static String[] defaultTest = { "signupValid" };
	private static String[] tests;
	
	
	public TestRunner() {
		
	}

	
	public static void main(String[] args) {
	//System.out.println("start...");
		tests = args.length > 0 ? args : defaultTest;  //this reads the command-line arguments and stores them in tests, if no args then it uses defaultTest
		
		Result result = JUnitCore.runClasses(ShoppostSignUpEmail.class);
		//Result result = JUnitCore.runClasses(ShoppostSignUpEmail.class, ShoppostSignInEmail.class);
		for (Failure failure : result.getFailures()) {
			System.out.println(failure.toString());
		}
		
		if(result.wasSuccessful()) {
			System.exit(0);
		} else {
			System.exit(1);
		}    
	}
	public static String[] getTests() { return tests; }
}
