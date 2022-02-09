package com.AutomationScript.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

//Finding Top cities in Diagnostic Page.
public class Automatetopcities extends BaseUI {
	@FindBy(xpath="//a[@event='Nav:Interacted:Book diagnostic tests']")
	public static WebElement diagonisticselement;

	// clicking on the diagnostic page.
	public void diagnostic() throws InterruptedException, IOException {
//		clickElement("diagnostic_xpath");
//		Thread.sleep(2000);
//		ResultScreenShot("Topcities.png");
		String e = diagonisticselement.getAttribute("href");

		driver.navigate().to(e);
	}

	// listing the topcities on diagnostic page.
	public void ListCities() throws IOException {
		List<WebElement> topCity = (List<WebElement>) getElements("city_xpath");
		
		System.out.println("*************************List Of Top Cities**********************");
		System.out.println("****************************************************************");
		File file = new File(System.getProperty("user.dir")+"\\testdata\\TopCitiesDataSheet.xlsx");
		String fileName = "TopCitiesDataSheet.xlsx";
		FileInputStream inputstream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(inputstream);
		Sheet datasheet = workbook.getSheetAt(0);
		int i = 1;
		Row row = datasheet.createRow(0);
		
		for (WebElement city : topCity) {

			System.out.println(city.getText());
			row = datasheet.createRow(i);
			row.createCell(0).setCellValue(city.getText());
			i++;

		}
		inputstream.close();
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		outputStream.close();
		
		
	}
	


	// navigating back
	public void back() {
		driver.navigate().back();
	}

	public static corperateWellness nextPage() {
		// sending driver to next page
		return PageFactory.initElements(driver, corperateWellness.class);
	}

}