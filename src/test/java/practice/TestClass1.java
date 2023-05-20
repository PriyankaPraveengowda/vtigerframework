package practice;

import org.testng.Assert;
import org.testng.annotations.Test;

import genericLibraries.SampleBaseClass;

public class TestClass1 extends SampleBaseClass {

	@Test
	public void Test1() {
		System.out.println("test-1");
	}
	
	@Test
	public void test2() {
		System.out.println("test-2");
		Assert.fail();
	}
}
