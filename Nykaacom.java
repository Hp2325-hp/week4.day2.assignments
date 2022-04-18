package week4.day2.assignments;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Nykaacom {

	public static void main(String[] args) throws InterruptedException {
		// Webdriver setup
				WebDriverManager.chromedriver().setup();
				ChromeDriver driver = new ChromeDriver();
				driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));

				// url
				driver.get("https://www.nykaa.com/");
				driver.manage().window().maximize();

				// Brands mouse hover and search
				// Actions object
				Actions a = new Actions(driver);
				WebElement brands = driver.findElement(By.xpath("//li/a[text()='brands']"));
				WebElement searchBar = driver.findElement(By.id("brandSearchBox"));

				// mouse hover and select the result
				a.moveToElement(brands).moveToElement(brands).moveToElement(searchBar).click().sendKeys("L'Oreal Paris")
						.perform();

				// Click 1st result
				driver.findElement(By.xpath("//div[@class='ss-wrapper']//div/a[1]")).click();

				// print title
				System.out.println(driver.getTitle());

				// Click sort By and select customer top rated
				driver.findElement(By.xpath("//div[@id='filter-sort']//div/button")).click();
				driver.findElement(By.xpath("//div[@class='control-value']/span[text()='customer top rated']")).click();
				Thread.sleep(2000);
				// Click Category and click Hair->Click haircare->Shampoo
				driver.findElement(By.id("first-filter")).click();
				driver.findElement(By.xpath("//ul[@id='custom-scroll']//li/div/span[text()='Hair']")).click();
				driver.findElement(By.xpath("//ul[@id='custom-scroll']//li/ul/li/div/span[text()='Hair Care']")).click();
				driver.findElement(By.xpath("//div[@class='control-value']/span[text()='Shampoo']")).click();
				Thread.sleep(2000);
				// Click->Concern->Color Protection
				driver.findElement(By.xpath("//span[text()='Concern']")).click();
				driver.findElement(By.xpath("//span[text()='Color Protection']")).click();
				Thread.sleep(2000);
				// check whether the Filter is applied with Shampoo
				// boolean enabled =
				// driver.findElement(By.xpath("//div[@class='control-value']/span[text()='Shampoo']")).isEnabled();
				// System.out.println(enabled);
				// Thread.sleep(2000);
				// Click on L'Oreal Paris Colour Protect Shampoo
				driver.findElement(By.xpath("//div[@id='product-list-wrap']//div/a")).click();

				// GO to the new window and select size as 175ml
				Set<String> windowHandles = driver.getWindowHandles();
				List<String> wh = new ArrayList<String>(windowHandles);
				String secWindow = wh.get(1);
				driver.switchTo().window(secWindow);

				// size
				WebElement findElement = driver.findElement(By.xpath("//select[@title='SIZE']"));
				Select s = new Select(findElement);
				s.selectByValue("0");

				// Print the MRP of the product
				String mrp = driver.findElement(By.xpath("//span[text()='MRP:']/following-sibling::span")).getText();
				System.out.println("MRP: " + mrp.substring(1));
				Thread.sleep(2000);
				// Click on ADD to BAG
				driver.findElement(By.xpath("//button/span[text()='ADD TO BAG']")).click();
				Thread.sleep(2000);
				// Go to Shopping Bag
				driver.findElement(By.xpath("//span[text()='Account']/following::button")).click();
				Thread.sleep(2000);
				// Print the Grand Total amount
				WebElement frame = driver.findElement(By.xpath("//iframe"));
				driver.switchTo().frame(frame);
				String gtotal = driver.findElement(By.xpath("//div[contains(@class,'medium-strong')]/following-sibling::div"))
						.getText();
				System.out.println(gtotal.substring(1));
				String gtotal1 = gtotal.substring(1);

				// Click Proceed
				driver.findElement(By.xpath("//span[text()='PROCEED']")).click();
				driver.switchTo().defaultContent();
				// Click on Continue as Guest
				driver.findElement(By.xpath("//button[text()='CONTINUE AS GUEST']")).click();

				// Check if this grand total is the same in step 14
				String gtotal2 = driver
						.findElement(
								By.xpath("//div[contains(@class,'payment-details-tbl grand-total-cell')]/div[@class='value']"))
						.getText();
				if (gtotal1.equals(gtotal2.substring(1)))
					System.out.println("Both Grand Total is same");
				else
					System.out.println("Not same");

				Thread.sleep(3000);
				// driver.close();

	}

}
