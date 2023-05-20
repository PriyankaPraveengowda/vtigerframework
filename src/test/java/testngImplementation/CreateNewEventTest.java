package testngImplementation;

import java.util.Map;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import genericLibraries.BaseClass;
import genericLibraries.IConstantPath;

public class CreateNewEventTest extends BaseClass {
	@Test
	public void createNewEventTest() {
		SoftAssert soft = new SoftAssert();
		home.selectFromQuickCreate(web, "Events");

		Map<String, String> map = excel.getDataFromExcel("EventsTestData", "Create New Event");
		String subject = map.get("Subject") + jutil.generateRandomNumber(100);
		createEvent.setSubjectTF(subject);

		createEvent.clickStartDatePicker();
		String startDate = map.get("Start Date");
		createEvent.datePicker(startDate, web);

		createEvent.clickDueDatePicker();
		String dueDate = map.get("Due Date");
		createEvent.datePicker(dueDate, web);
		createEvent.clickSaveButton();
		
		soft.assertTrue(eventInfo.getPageHeader().contains(subject));
		if (eventInfo.getPageHeader().contains(subject)) {
			excel.writeDataToExcel("EventsTestData", "Create New Event", "pass", IConstantPath.EXCEL_PATH);
		} 
		else {
			excel.writeDataToExcel("EventsTestData", "Create New Event", "fail", IConstantPath.EXCEL_PATH);
		}
		soft.assertAll();
	}

}
