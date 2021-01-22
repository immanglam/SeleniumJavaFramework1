package test;
import utils.ExcelUtilss;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class loginTest {

	ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("extentreport.html");
	ExtentReports extent  =  new ExtentReports();


	WebDriver driver=null;

	@BeforeSuite
	public void setUpTest() {

		extent.attachReporter(htmlReporter);

		String projectPath = System.getProperty("user.dir");
		//PropertiesFile.getProperties();

		System.setProperty("webdriver.chrome.driver", projectPath+"/drivers/chromedriver/chromedriver.exe");
		driver = new ChromeDriver();
	}


	@Test(dataProvider="test1data")
	public void test1(String username, String password) {

		ExtentTest test1 = extent.createTest("Login Test" , "To check the login functionality");
		test1.log(Status.INFO,"strating test case");

		driver.get("https://opensource-demo.orangehrmlive.com/");
		test1.pass("Navigated to orangehrm");
		driver.findElement(By.name("txtUsername")).sendKeys(username);
		test1.pass("Enter text in loginbox");
		driver.findElement(By.name("txtPassword")).sendKeys(password);
		test1.pass("enter text in passwordbox");
		driver.findElement(By.name("Submit")).sendKeys(Keys.RETURN);
		test1.pass("enter login button");


		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	@DataProvider(name = "test1data")
	public static  Object[][] getData() {

		String projectPath = System.getProperty("user.dir");
		String excelPath =projectPath+"\\excel\\data.xlsx";

		Object data[][] = testData(excelPath, "Sheet1");
		return data;


	}

	public  static Object[][] testData(String excelPath, String sheetName) {

		ExcelUtilss excel = new ExcelUtilss(excelPath, sheetName);

		int rowCount = excel.getRowCount();
		int colCount = excel.getColCount();

		Object data [][] = new Object[rowCount-1][colCount];

		for(int i=1; i<rowCount; i++) {
			for(int j=0; j<colCount; j++) {

				String cellData = excel.getCellDataString(i, j);
				//System.out.print(cellData+ "  ");
				data [i-1][j] = cellData;

			}
			//System.out.println();
		}
		return data;
	}
	@AfterSuite
	public void tearDownTest() {
		driver.close();
		extent.flush();

	}
}


