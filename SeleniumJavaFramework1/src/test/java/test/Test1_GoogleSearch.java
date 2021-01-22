package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Test1_GoogleSearch {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		googlesearch();

	}


	public static void googlesearch() {

		String projectPath = System.getProperty("user.dir");



		System.setProperty("webdriver.chrome.driver", projectPath+"/drivers/chromedriver/chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		//goto google.com
		driver.get("http://google.com");

		//enter text in the search textbox
		driver.findElement(By.name("q")).sendKeys("Happy 26th January 2021");

		//click on search button
		//driver.findElement(By.name("q")).click();
		driver.findElement(By.name("q")).sendKeys(Keys.RETURN);

		//close browser
		driver.close();

		System.out.println("Test completed successfully");



	}
}
