package marthon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

public class TatacliqTestcase {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		ChromeDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://www.tatacliq.com/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		//Thread.sleep(3000);
		driver.findElement(By.xpath("//button[text()='Ask me later']")).click();
		Actions ac=new Actions(driver);
        ac.moveToElement(driver.findElement(By.xpath("//div[text()='Brands']"))).perform();
		ac.moveToElement(driver.findElement(By.xpath("//div[text()='Watches & Accessories']"))).perform();
		ac.moveToElement(driver.findElement(By.xpath("//div[text()='Casio']"))).perform();
		driver.findElement(By.xpath("//div[text()='Casio']")).click();
		WebElement s1 = driver.findElement(By.className("SelectBoxDesktop__hideSelect"));
		Select s2=new Select(s1);
		s2.selectByVisibleText("New Arrivals");
		Thread.sleep(3000);
		driver.findElement(By.xpath("(//div[@class='FilterDesktop__newFilCheckboxBlock'])[1]")).click();
		List<WebElement> print = driver.findElements(By.xpath("//div[@class='ProductDescription__content']//h3"));
		for (int i = 1; i <= print.size(); i++) {
			Thread.sleep(3000);
		String text = driver.findElement(By.xpath("(//div[@class='ProductDescription__content']//h3)["+ i +"]")).getText();
		//String text =print.get(i).getText();
		System.out.println("Price of all Watches :"+text);
		}
		String parent = driver.findElement(By.xpath("//h3[text()='₹11995']")).getText();
		System.out.println("Price of the firstwatches :"+parent);
		Thread.sleep(1000);
		driver.findElement(By.xpath("(//div[@class='ProductModule__dummyDiv'])[1]")).click();
		Set<String> win = driver.getWindowHandles();
		List<String> win1=new ArrayList<String>(win);
		driver.switchTo().window(win1.get(1));
		String child = driver.findElement(By.xpath("//div[@class='ProductDetailsMainCard__price']/h3")).getText();
		System.out.println("The price of first element :"+child);
		if (child.contains(parent)) {
			System.out.println("The price is Matching");
		}
			
          else {
        	  System.out.println("The price is not Matching");
        	 

		}
		Actions act=new Actions(driver);
		ac.scrollToElement(driver.findElement(By.xpath("//div[text()='Titan Company Ltd']"))).perform();
		driver.findElement(By.xpath("//span[text()='ADD TO BAG']")).click();
		String count = driver.findElement(By.xpath("(//span[text()='1'])[1]")).getText();
		System.out.println("The Cart Count in the Icon :"+count);
		Thread.sleep(3000);
		driver.findElement(By.xpath("//span[@class='DesktopHeader__cartCount']")).click();
		Thread.sleep(3000);
		//Set<String> wh2 = driver.getWindowHandles();
		//List<String> wh3=new ArrayList<String>(wh2);
	     //driver.switchTo().window(win1.get(1));
		//Thread.sleep(10000);
		File src1=driver.getScreenshotAs(OutputType.FILE);
		File des1=new File("./snap/tata.png");
		FileUtils.copyFile(src1, des1);
		driver.close();
		driver.switchTo().window(win1.get(0));
		driver.close();
		

	}

}
