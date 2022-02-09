package com.AutomationScript.sample;

import java.awt.AWTException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.Alert;
import org.openqa.selenium.UnhandledAlertException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;



public class corperateWellness extends BaseUI {
	//for provider drop down in main page
    @FindBy(xpath="//*[@id='root']/div/div/div[1]/div[1]/div[2]/div/div[3]/div[1]/span[1]")
    public static WebElement providerelement;

    //corporate wellness option
    @FindBy(xpath="//*[@id='root']/div/div/div[1]/div[1]/div[2]/div/div[3]/div[1]/div/div[4]/a")
    public static WebElement corporatewellnesselement;

    //name field
    @FindBy(id ="name")
    public static WebElement nameelement;

    //Organization field
    @FindBy(id ="organization_name")
    public static WebElement organizationelement;

    //mail id field
    @FindBy(id ="official_email_id")
    public static WebElement mailelement;

    //phone no field
    @FindBy(id ="official_phone_no")
    public static WebElement phoneelement;

    //schedule a demo button
    @FindBy(id ="button-style")
    public static WebElement button;

    //Organization size drop down
    @FindBy(id="organization_size")
    public static WebElement organization;
    
    public void clickProvider() throws InterruptedException {

        try {

        providerelement.click();
        highLightElement(driver,providerelement);
        TimeUnit.SECONDS.sleep(5);

        //reportPass("Successfully clicked the Click Provider");
        }catch(Exception e) {
            e.printStackTrace();
            //reportFail(e.getMessage());
        }

    }
public void clickCorporateWellness() throws InterruptedException {
		
		try {
			highLightElement(driver,corporatewellnesselement);
			
		corporatewellnesselement.click();
		TimeUnit.SECONDS.sleep(15);
		
		//reportPass("Navigated to CorporateWellness Link Successfully");
		}catch(Exception e) {
			e.printStackTrace();
			//reportFail(e.getMessage());
		}
		
	}
public void enterData() throws InterruptedException, IOException , UnhandledAlertException{
	
	try {
	
	Set<String> windowids = driver.getWindowHandles();
	Iterator<String> itr = windowids.iterator();
	String mainpageId = itr.next();
	String CorporatePage = itr.next();
	
	
	//switch to corporate wellness window
	driver.switchTo().window(CorporatePage);
	readFromExcel();
	
	}
	//ReadOrWriteExcelSheet.ReadFromExcel();
//	FileInputStream fi = new FileInputStream(
//			System.getProperty("user.dir") + "\\testdata\\testdata hackathon.xlsx");
//	XSSFWorkbook wb = new XSSFWorkbook(fi);
//	XSSFSheet ws = wb.getSheetAt(1);// sheet (1)
//	XSSFRow wr = ws.getRow(1);
//	XSSFCell wc = wr.getCell(0);
//	XSSFCell wc1 = wr.getCell(1);
//	XSSFCell wc2 = wr.getCell(2);
//	XSSFCell wc3 = wr.getCell(3);
//	String data1 = wc.getStringCellValue();
//	String data2 = wc1.getStringCellValue();
//	String data3 = wc2.getStringCellValue();
//	String data4 = wc3.getStringCellValue();
//    System.out.println(data1);
// // enter the name.
//    enterText("name_id", data1);
//    // enter the organization name.
//    enterText("org_id", data2);
//    // enter the email id.
//    enterText("offemail_id", data3);
//    // enter the phone Number.
//    enterText("offphn_id", data4);
//    /// click on the submit.
//    clickElement("submit_id");
//    try {
//        Thread.sleep(3000);
//    } catch (InterruptedException e1) {
//
//        e1.printStackTrace();
//    }
//    
//	
//
//	}
	catch(Exception e) {
		e.printStackTrace();
		//reportFail(e.getMessage());
		
		
	}
	
	
}
public static void readFromExcel() throws InterruptedException, IOException, AWTException
{
	corperateWellness cs = Automatetopcities.nextPage();
	File file = new File(System.getProperty("user.dir")+"\\testdata\\testdata hackathon.xlsx");
    String fileName = "testdata hackathon.xlsx";
    FileInputStream inputstream = new FileInputStream(file);
    Workbook workbook = null;
    String fileExtensionName = fileName.substring(fileName.indexOf("."));
    
    if(fileExtensionName.equals(".xlsx")){

        workbook = new XSSFWorkbook(inputstream);

      }
      else if(fileExtensionName.equals(".xls")){
          workbook = new HSSFWorkbook(inputstream);
      }
    Sheet datasheet = workbook.getSheetAt(1);
    for(int i=1;i<=2;i++) {
		  cs.nameelement.clear();
		  highLightElement(driver,nameelement);
		  cs.nameelement.sendKeys(datasheet.getRow(i).getCell(1).getStringCellValue());
			TimeUnit.SECONDS.sleep(3);
			
			cs.organizationelement.clear();
			 highLightElement(driver,organizationelement);
			cs.organizationelement.sendKeys(datasheet.getRow(i).getCell(2).getStringCellValue());
			TimeUnit.SECONDS.sleep(3);
			
			cs.mailelement.clear();
			 highLightElement(driver,mailelement);
			cs.mailelement.sendKeys(datasheet.getRow(i).getCell(3).getStringCellValue());
			TimeUnit.SECONDS.sleep(3);
			
			cs.phoneelement.clear();
			 highLightElement(driver,phoneelement);
			DataFormatter formatter = new DataFormatter();
			String val = formatter.formatCellValue(datasheet.getRow(i).getCell(4));
			cs.phoneelement.sendKeys(val);
			TimeUnit.SECONDS.sleep(3);
			
			Select org = new  Select(cs.organization);
			 highLightElement(driver,organization);
			org.selectByVisibleText("501-1000");
			TimeUnit.SECONDS.sleep(3);
			ResultScreenShot("Alert.png");
			
			cs.button.click();
			//highLightElement(driver,button);
            TimeUnit.SECONDS.sleep(20);
            
            if(isAlertPresent())
            {
            	
            	alertBox();
            }
            
            //ResultScreenShot("CorporateWellness" + i + ".png");		
			
    }
    
	
}

public static void alertBox() {
	Alert alt = driver.switchTo().alert();
	System.out.println("*********************Alert Message for invalid details***********************");
	System.out.println("****************************************************************************");
	System.out.println(alt.getText());

	driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);

	alt.accept();
}	


}

  
    