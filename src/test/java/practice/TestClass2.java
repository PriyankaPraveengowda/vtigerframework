package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TestClass2 {
	@Test
	public void test1() {
		System.out.println("TestClass2 => test1");
	}
	
	@Test(retryAnalyzer = genericLibraries.SampleRetryImp.class)
	public void Test2(){
		System.out.println("test2");
		Assert.fail();
	}
}
