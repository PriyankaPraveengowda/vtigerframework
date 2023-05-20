package genericLibraries;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class SampleRetryImp implements IRetryAnalyzer {

	int count;
	int maxRetries;
	@Override
	public boolean retry(ITestResult arg0) {
		if(count < maxRetries) {
			count++;
			return true;
		}
		return false;
	}

}
