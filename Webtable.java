package week4.day2.assignments;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import io.github.bonigarcia.wdm.WebDriverManager;

public class Webtable {

	public static void main(String[] args) throws InterruptedException {
		// Webdriver setup
		WebDriverManager.chromedriver().setup();
		ChromeDriver driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		
		//url
		driver.get("http://www.leafground.com/pages/table.html");
		driver.manage().window().maximize();
		
		//Get the count of number of columns
		List<WebElement> headers = driver.findElements(By.xpath("//tr/th"));
		System.out.println("No of columns: "+headers.size());
		
		//Get the count of number of rows
		List<WebElement> rows = driver.findElements(By.xpath("//tr"));
		int noofrows=rows.size();
		System.out.println("No of Rows: "+(noofrows-1));

		//Get the progress value of 'Learn to interact with Elements'
		
		System.out.println("The Progress value of 'Learn to interact with Elements' is :");
		for(int i=2;i<=noofrows;i++) {
			 String datas = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[1]")).getText();
			 if(datas.equals("Learn to interact with Elements"))
			 {
				 String data = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[2]")).getText();
				 System.out.println(data);
			 }
			
		}
			
		
		
		//Check the vital task for the least completed progress.
		int small=1000;
		for(int i=2;i<=noofrows;i++) {
			
			 String per = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[2]")).getText();
			 int index=per.indexOf('%');
			 String temp=per.substring(0, index);
			 
			 if(Integer.parseInt(temp)<small)
				 small=Integer.parseInt(temp);
			
		}
		for(int i=2;i<=noofrows;i++) {
			String per = driver.findElement(By.xpath("//tbody/tr["+i+"]/td[2]")).getText();
			int index=per.indexOf('%');
			String temp=per.substring(0, index);
			 if(Integer.parseInt(temp)==small)
			 {
				 driver.findElement(By.xpath("//tbody/tr["+i+"]/td[3]")).click();
			
			 }
			 }
		Thread.sleep(5000);
		driver.close();
		
		
		
		

	}

}
