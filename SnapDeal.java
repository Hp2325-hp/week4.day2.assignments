package week4.day2.assignments;

import java.io.File;

import java.io.IOException;
import java.time.Duration;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;


import io.github.bonigarcia.wdm.WebDriverManager;

public class SnapDeal {

	public static void main(String[] args) throws InterruptedException, IOException {
		// Webdriver setup
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		////Actions setup
		Actions a=new Actions(driver);
		
		//url
		driver.get("https://www.snapdeal.com/");
		Thread.sleep(2000);
		driver.manage().window().maximize();
		
		//Go to Mens Fashion Go to Sports Shoes
		WebElement mensfashion = driver.findElement(By.xpath("//span[@class='catText']"));
		WebElement sportshoes = driver.findElement(By.xpath("//span[text()='Sports Shoes' and @class='linkTest']"));
		
		a.moveToElement(mensfashion).moveToElement(sportshoes).click().perform();
		Thread.sleep(2000);
		//Get the count of the sports shoes
		String countofsportshoes = driver.findElement(By.xpath("//span[contains(@class,'category-count')]")).getText();
		System.out.println("Sport shoes count: "+countofsportshoes);
		Thread.sleep(3000);
		
		//Click Training shoes
		driver.findElement(By.xpath("//div[text()='Training Shoes']")).click();
		Thread.sleep(3000);
		
		//Sort by Low to High
		driver.findElement(By.xpath("//div[contains(@class,'sort-drop clearfix')]")).click();
		Thread.sleep(1000);
		driver.findElement(By.xpath("//ul[@class='sort-value']/li[2]")).click();
		Thread.sleep(3000);
		
		//Check if the items displayed are sorted correctly
		String order = driver.findElement(By.xpath("//div[@class='sort-selected']")).getText();
		order=order.trim();
		//System.out.println(order);
		System.out.println("Sorted or Not:");
		if(order.equals("Price Low To High"))
		{
			System.out.println("Sorted in low to high");
		}
		else
		{
			System.out.println("Not sorted");
		}
		
		//Select the price range (900-1200)
		driver.findElement(By.xpath("(//div[@class='price-input']//input[@class='input-filter'])[1]")).clear();
		driver.findElement(By.xpath("(//div[@class='price-input']//input[@class='input-filter'])[1]")).sendKeys("900");
		driver.findElement(By.xpath("(//div[@class='price-input']//input[@class='input-filter'])[2]")).clear();
		driver.findElement(By.xpath("(//div[@class='price-input']//input[@class='input-filter'])[2]")).sendKeys("1200");
		driver.findElement(By.xpath("//div[contains(@class,'price-go-arrow btn btn-line btn-theme-secondary')]")).click();
		Thread.sleep(2000);
		
		//Filter with color Navy --> Navy color not availble
		//driver.findElement(By.xpath("//input[@value='Blue']")).click();
		
		//verify the all applied filters
		List<WebElement> filters = driver.findElements(By.xpath("//div[@class='filters']//a[@class='clear-filter-pill']"));
		System.out.println("Applied Filters:");
		for (WebElement filter : filters) {
			System.out.println(filter.getText());
		}
		
		
		//Mouse Hover on first resulting Training shoes
		//click QuickView button
		WebElement image = driver.findElement(By.xpath("//div[@class='product-tuple-image ']"));
		WebElement quickview = driver.findElement(By.xpath("//div[contains(@class,'clearfix row-disc')]/div"));
		a.moveToElement(image).moveToElement(quickview).click().perform();
		Thread.sleep(2000);
		
		//Print the cost and the discount percentage
		
		String price = driver.findElement(By.xpath("//span[@class='payBlkBig']")).getText();
		
		System.out.println("The Shoe Price is : "+price);
		String off = driver.findElement(By.xpath("//span[@class='percent-desc ']")).getText();
		System.out.println("Offer is : "+off);
		
		//Take the snapshot of the shoes.
		File f=driver.getScreenshotAs(OutputType.FILE);
		File destination=new File("C:/Users/PC/Desktop/ss1.png");
		FileUtils.copyFile(f, destination);
		Thread.sleep(2000);
		
		//Close the current window
		driver.findElement(By.xpath("//div[@class='close close1 marR10']/i[contains(@class,'icon-delete-sign')]"))
		.click();
		
		//Close the main window
		driver.close();
		
		
		
		}
		
	}


