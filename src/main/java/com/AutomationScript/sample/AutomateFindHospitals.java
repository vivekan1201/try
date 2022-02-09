package com.AutomationScript.sample;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//Search for hospital with specified location
public class AutomateFindHospitals extends BaseUI {
	int itemCount = 0;
	String searchPageId, itemPageId;
	private XSSFWorkbook wb;
	

	// finding the hospitals that are 24*7 hours open and has parking facilities.
	public void SearchHospitals() throws IOException {

		FileInputStream fi = new FileInputStream(
				System.getProperty("user.dir") + "\\testdata\\testdata hackathon.xlsx");
		wb = new XSSFWorkbook(fi);
		XSSFSheet ws = wb.getSheetAt(0);
		XSSFRow wr = ws.getRow(1);
		XSSFCell wc = wr.getCell(0);
		XSSFCell wc1 = wr.getCell(1);
		String text1 = wc.getStringCellValue();
		String text2 = wc1.getStringCellValue();

		
		getElement("searchLocation_xpath").click();
		clickElement("clearSearchLocationButton_xpath");

		// entering city as bangalore.
		enterText("searchLocation_xpath", text1);

		// entering hospital in search box.
		new WebDriverWait(driver, 30).until(ExpectedConditions
				.visibilityOfElementLocated(By.xpath(prop.getProperty("bangaloreLocationButton_xpath"))));
		clickElement("bangaloreLocationButton_xpath");
		enterText("searchBox_xpath", text2);

		new WebDriverWait(driver, 30).until(
				ExpectedConditions.visibilityOfElementLocated(By.xpath(prop.getProperty("hospitalButton_xpath"))));
		clickElement("hospitalButton_xpath");
	}

	// finding the hospitals that are 24*7 open
	public void Hospitalsopen() {
		
		clickElement("open24X7CB_xpath");
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {

			e.printStackTrace();
		}

	}

	// apply filter for hospitals having parkings.
	public void applyfilter() throws IOException, InterruptedException {
		clickElement("allFilters_xpath");
		clickElement("hasParkingCB_xpath");
		Thread.sleep(2000);
		ResultScreenShot("Hospitals.png");

		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	// Displaying the search results where rating greater than 3.5
	public void ListHospitals() throws IOException, InterruptedException {
		
	
		
		int i = 0;
		float[] ratingsFloat = new float[10];
		List<WebElement> ratings = getElements("ratings_xpath");
		Iterator<WebElement> rItr = ratings.iterator();
		while (rItr.hasNext()) {
			WebElement ob = rItr.next();
			ratingsFloat[i] = Float.parseFloat(ob.getText());
			i++;
		}

		// listing the hospitals names.
		List<WebElement> hospitalNames = getElements("hospitalNames_xpath");
		Iterator<WebElement> hnItr = hospitalNames.iterator();
		i = 0;
		
		File file = new File(System.getProperty("user.dir")+"\\testdata\\HospitalsList.xlsx");
		String fileName = "HospitalsList.xlsx";
		FileInputStream inputstream = new FileInputStream(file);
		Workbook workbook = new XSSFWorkbook(inputstream);
		Sheet datasheet = workbook.getSheetAt(0);
		Row row = datasheet.createRow(0);


		System.out.println("*************************List of Hospitals open 24*7 *********************************");
		System.out.println("****************************************************************************");
		while (hnItr.hasNext()) {
			WebElement ob = hnItr.next();
			if (ratingsFloat[i] > 3.5) {

				System.out.println(ob.getText());
				row = datasheet.createRow(i);
				row.createCell(0).setCellValue(ob.getText());
				

			}
			i++;
		}
		inputstream.close();
		FileOutputStream outputStream = new FileOutputStream(file);
		workbook.write(outputStream);
		outputStream.close();
	}

	public static Automatetopcities nextPage() {
		// sending driver to next page
		return PageFactory.initElements(driver, Automatetopcities.class);
	}

}