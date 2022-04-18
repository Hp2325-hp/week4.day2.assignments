package week4.day2.assignments;

import java.time.Duration;


import org.openqa.selenium.By;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Actionss {


	public static void main(String[] args) throws InterruptedException {
		
		// Webdriver setup
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//Actions setup
		Actions a=new Actions(driver);
		
		//url-//https://jqueryui.com/resizable
		driver.get("https://jqueryui.com/resizable/");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		//finding the control element
		WebElement frame = driver.findElement(By.xpath("//iframe"));
		driver.switchTo().frame(frame);
		WebElement sizecontrol = driver.findElement(By.xpath("//div[@id='resizable']/div[contains(@class,'ui-icon-gripsmall-diagonal-se')]"));
		
		//control the size
		a.dragAndDropBy(sizecontrol,50, 20).perform();
		
		Thread.sleep(3000);
		driver.close();
		//------------------------------------------
		
		//Url->http://www.leafground.com/pages/drag.html
		driver.get("http://www.leafground.com/pages/drag.html");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		//drag
		WebElement dragbox = driver.findElement(By.xpath("//div[@id='mydiv']//div"));
		a.dragAndDropBy(dragbox, 10, 20).perform();
		Thread.sleep(3000);
		driver.close();
		//--------------------------------------------------
		
		//Url->http://www.leafground.com/pages/drag.html
		driver.get("http://www.leafground.com/pages/drop.html");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		//drop 
		WebElement dragelement = driver.findElement(By.xpath("//div[@id='draggable']"));
		WebElement drop = driver.findElement(By.id("droppable"));
		a.dragAndDrop(dragelement,drop).perform();
		Thread.sleep(3000);
		driver.close();
		//--------------------------------------------------
		
		//url->http://www.leafground.com/pages/selectable.html
		driver.get("http://www.leafground.com/pages/selectable.html");
		driver.manage().window().maximize();
		Thread.sleep(2000);
				
		//get 1st and end element
		WebElement first = driver.findElement(By.xpath("//ol[@id='selectable']/li[text()='Item 1']"));
		WebElement last = driver.findElement(By.xpath("//ol[@id='selectable']/li[text()='Item 4']"));
		
		//selection
		a.clickAndHold(first).moveToElement(last).release().perform();
		Thread.sleep(3000);
		driver.close();
		//--------------------------------------------------
		
		//url->http://www.leafground.com/pages/sortable.html
		driver.get("http://www.leafground.com/pages/sortable.html");
		driver.manage().window().maximize();
		Thread.sleep(2000);
		
		//drop 
		WebElement item1 = driver.findElement(By.xpath("//ul[@id='sortable']/li[1]"));
		WebElement item2 = driver.findElement(By.xpath("//ul[@id='sortable']/li[2]"));
		WebElement item3 = driver.findElement(By.xpath("//ul[@id='sortable']/li[3]"));
		WebElement item4 = driver.findElement(By.xpath("//ul[@id='sortable']/li[4]"));
		WebElement item5 = driver.findElement(By.xpath("//ul[@id='sortable']/li[5]"));
		WebElement item6 = driver.findElement(By.xpath("//ul[@id='sortable']/li[6]"));
		WebElement item7 = driver.findElement(By.xpath("//ul[@id='sortable']/li[7]"));

		// 3. Select Action
		a.dragAndDrop(item7, item1).pause(1000).dragAndDrop(item6, item1).pause(1000).dragAndDrop(item5, item1)
		.pause(1000).dragAndDrop(item4, item1).pause(1000).dragAndDrop(item3, item1).pause(1000)
		.dragAndDrop(item2, item1).pause(1000).dragAndDrop(item1, item1).pause(1000).perform();
		
	}

}
