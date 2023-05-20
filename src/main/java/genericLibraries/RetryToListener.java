package genericLibraries;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

import org.testng.annotations.ITestAnnotation;
import org.testng.internal.annotations.IAnnotationTransformer;

public class RetryToListener implements IAnnotationTransformer{

	@Override
	public void transform(ITestAnnotation arg0, Class arg1, Constructor arg2, Method arg4) {
		arg0.setRetryAnalyzer(genericLibraries.SampleRetryImp.class);
		
	}

}
