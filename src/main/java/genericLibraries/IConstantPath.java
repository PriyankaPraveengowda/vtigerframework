package genericLibraries;
/**
 *This interface contains the paths of properties, excel files and JDBC url.
 * @author 91897
 *
 */
public interface IConstantPath {
	
	String PROPERTIES_PATH = "./src/test/resources/Data.properties";
	
	//    .  (or)  ( System.getProperty("user.dir")) indicates the current directory
	
	String EXCEL_PATH = "./src/test/resources/VtigerCRMTestData.xlsx";
	
	String JDBC_URL = "jdbc:mysql://localhost:3306/advsel";
}
