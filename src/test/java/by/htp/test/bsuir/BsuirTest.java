package by.htp.test.bsuir;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class BsuirTest {

	private WebDriver driver;

	@BeforeMethod(groups = { "runnerTest" })
	public void findBooksByHeader() {
		System.setProperty("webdriver.chrome.driver", "D:\\Install\\Install Developer\\Drivers\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get("http://books.bsuir.by/Marcweb/");

		driver.findElement(By.xpath("/html/body/div/li[1]/font/a")).click();
		WebElement frame = driver.findElement(By.id("idForm"));
		driver.switchTo().frame(frame);
		driver.findElement(By.name("D1")).click();
		driver.findElement(By.xpath("//*[@id=\"TABLE1\"]/tbody/tr[2]/td[2]/select/option[2]")).click();
		driver.findElement(By.name("T1")).sendKeys("Программирование");
		driver.findElement(By.id("idB1")).click();
		driver.switchTo().defaultContent();
		ArrayList<String> newTab = new ArrayList<String>(driver.getWindowHandles());
		driver.switchTo().window(newTab.get(1));
	}

	@Test(groups = { "numberOfBooks_test" })
	public void checkCorrectNumberOfBooks() {
		WebElement numberOfBooks = driver.findElement(By.xpath("/html/body/p/b/i"));
		Assert.assertEquals(numberOfBooks.getText(), "331", "Correct");
	}

	@Test(groups = { "numeration_test" })
	public void checkNumerationOfBooks() {
		WebElement startNumeration = driver.findElement(By.xpath("//*[@id=\"form2\"]/ol"));
		Assert.assertEquals(startNumeration.getAttribute("start"), "1");
	}

	@AfterMethod(groups = { "runnerTest" })
	public void afterMethod() {
		driver.close();
		driver.quit();
	}

}
