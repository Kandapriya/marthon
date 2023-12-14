package marthon2;

import java.io.File;
import java.io.IOException;
import java.time.Duration;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import io.cucumber.core.gherkin.Argument;
import io.github.sukgu.Shadow;

public class Orderingmobile {

	public static void main(String[] args) throws InterruptedException, IOException {
		// TODO Auto-generated method stub
		ChromeDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
		driver.get("https://dev206556.service-now.com");

        //Login with valid credentials username as admin and password 
		driver.findElement(By.id("user_name")).sendKeys("admin");
		driver.findElement(By.id("user_password")).sendKeys("Testleaf@123");
		driver.findElement(By.id("sysverb_login")).click();
		Thread.sleep(10000);
		
		//Click-All Enter Service catalog in filter navigator and press enter or Click the ServiceCatalog
		Shadow sha=new Shadow(driver);
		sha.findElementByXPath("//div[@class='sn-polaris-tab can-animate polaris-enabled']").click();
		Thread.sleep(3000);
		sha.findElementByXPath("//span[text()='Service Catalog']").click();
		
		//Click on  mobiles
		WebElement iframe = sha.findElementByXPath("//iframe[@id='gsft_main']");
		driver.switchTo().frame(iframe);
		WebElement mob1= driver.findElement(By.xpath("//a[text()='Mobiles']"));
        driver.executeScript("arguments[0].click()", mob1);
        
        //Select Apple iphone13pro
        driver.findElement(By.xpath("//strong[text()='Apple iPhone 13']")).click();
        
        //Choose yes option in lost or broken iPhone
        driver.findElement(By.xpath("//label[text()='Yes']")).click();
        
        //Enter phonenumber as 99 in original phonenumber field
        driver.findElement(By.xpath("//input[@class='cat_item_option sc-content-pad form-control']")).sendKeys("9985247613");
        
        //Select Unlimited from the dropdown in Monthly data allowance
        Actions mo=new Actions(driver);
        mo.click(driver.findElement(By.xpath("//select[@class='form-control cat_item_option ']"))).perform();
        WebElement month = driver.findElement(By.xpath("//select[@class='form-control cat_item_option ']"));
        Select month1=new Select(month);
        month1.selectByIndex(2);
        driver.findElement(By.xpath("//label[text()='Blue']")).click();
        driver.findElement(By.xpath("//label[text()='512 GB [add $300.00]']")).click();
        mo.click(driver.findElement(By.xpath("//span[text()='Order Now']"))).perform();
        String order = driver.findElement(By.xpath("//dt[contains(text(),'Order Place')]")).getText();
        System.out.println("Order Placed :"+order);
        String requ = sha.findElementByXPath("//a[@id='requesturl']").getText();
        System.out.println( "Request Number :"+requ);
        File src=driver.getScreenshotAs(OutputType.FILE);
        File des=new File("./snap/orderin.png");
        FileUtils.copyFile(src, des);
        
       
		
		

	}

}
