package genericLibraries;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * This class contains reusable methods to perform java related operations
 * 
 * @author 91897
 *
 */
public class JavaUtility {
	/**
	 * This method generates and returns a random number within specified limit
	 * 
	 * @param limit
	 * @return
	 */
	public int generateRandomNumber(int limit) {
		Random random = new Random();
		return random.nextInt(limit);
	}

	/**
	 * This method is used to return the current time
	 */
	public String getCurrentTime() {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("dd_MM_yy_hh_mm_sss");
		return sdf.format(date);

	}
}
