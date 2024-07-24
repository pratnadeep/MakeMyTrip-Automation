package demo;

import java.time.Duration;
import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

import org.bouncycastle.its.ITSValidityPeriod.Unit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeDriverService;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.interactions.Actions;
import dev.failsafe.internal.util.Assert;
import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver.Options;
public class TestCases {
    static ChromeDriver driver;
   
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
       ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("start-maximized");
        options.addArguments("--disable-blink-features=AutomationControlled");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

       driver = new ChromeDriver(options);

        // Set browser to maximize and wait
       driver.manage().window().maximize();
        


new WebDriverWait(driver, Duration.ofSeconds(30));
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        driver.get(" https://www.makemytrip.com/");
       
        String currentURL = driver.getCurrentUrl();
     
        Assert.isTrue(currentURL.contains("makemytrip"), "MakeMyTrip homepage URL does not contain 'makemytrip'","URL is Present");
       if((currentURL.contains("makemytrip"))) {
        System.out.println("Makemytrip is Present");
       }
     
        System.out.println("end Test case: testCase01");
       // driver.quit();
    }

    public void testdemo() throws InterruptedException{
       
       System.out.println("TestCase 02 : Get Flight Details from Bangalore to New Delhi");
        driver.get("https://www.makemytrip.com/");
        Thread.sleep(20000);
        WebElement fromcity= driver.findElement(By.id("fromCity"));
        fromcity.click();
        driver.findElement(By.xpath("//input[@aria-autocomplete='list']")).sendKeys("blr");
        Thread.sleep(2000);
        List<WebElement> list = driver.findElements(By.xpath("//ul[@class='react-autosuggest__suggestions-list']/li"));
        for(WebElement l : list)
        {
            if(l.getText().contains("Bengaluru"))
            {
                l.click();
                break;
            }
        }
        
       
        Thread.sleep(2000);
        Boolean status = driver.findElement(By.xpath("//input[@id='fromCity']")).getAttribute("value").contains("Bengaluru");
        if(status)
        System.out.println("Select Bangalore as the departure location");
      
        else
        System.out.println("Faile");
          

        driver.findElement(By.xpath("//input[@id='toCity']")).click();
        driver.findElement(By.xpath("//input[@aria-autocomplete='list']")).sendKeys("del");
        List<WebElement> list1 = driver.findElements(By.xpath("//p[@class='font14 appendBottom5 blackText']"));
        for(WebElement l1 : list1)
        {
            if(l1.getText().contains("New Delhi"))
            {
                
                l1.click();
                break;
            }
        }
       
      
        status = driver.findElement(By.xpath("//input[@id='toCity']")).getAttribute("value").contains("New Delhi");
        if(status)

           {
            System.out.println("Test Case Pass");
           }
        else
        {
            System.out.println("Test Case Fail");
        }
           
    
        selectDateForFlight();
       
        status = driver.findElement(By.xpath("(//div[@class='DayPicker-Caption']/div)[1]")).getText().contains("April2024");
        
        selectDateForReturnFlight();

        if(status)
        
           {
            
            System.out.println("pass");
           }
        else
          { 
           System.out.print("failed");
           }
        
      //  Thread.sleep(5000);
        driver.findElement(By.xpath("//a[text()='Search']")).click();
        //Thread.sleep(5000);
        String flightPrice;
        
        try{
             flightPrice = driver.findElement(By.xpath("//div[@class='weeklyFareItems activeDate glider-slide visible left-1']/a/p/following-sibling::p")).getText();
        }
        catch(Exception e)
        {
            flightPrice = driver.findElement(By.xpath("(//div[@class='makeFlex column relative splitfare textRight ']/p)[1]")).getText();
        }

        System.out.println(flightPrice);
    }

    public static void selectDateForFlight() throws InterruptedException {
        driver.findElement(By.xpath("//div[@class='flt_fsw_inputBox dates inactiveWidget activeWidget']")).click();
        WebElement years = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']/div[1]"));
        while(!years.getText().equals("April2024"))
        {
            driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
            years = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']/div[1]"));
        }
        List<WebElement> date = driver.findElements(By.xpath("//div[@class='DayPicker-Month']/div[3]/div/div/div/p[1]"));
        driver.findElement(By.xpath("//p[@class='font16 blackText latoBold appendRight30']")).click();
        // Thread.sleep(2000);
        for(WebElement dateToSelect : date)
        {
            if(dateToSelect.getText().equals("29"))
            {

                dateToSelect.click();
                Thread.sleep(10000);
                break;
            }       
        }
    }


    public static void selectDateForReturnFlight() throws InterruptedException {
        
        WebElement years = driver.findElement(By.xpath("(//div[@class='DayPicker-Caption']/div)[1]"));
        while(!years.getText().equals("April2024"))
        {
            driver.findElement(By.xpath("//span[@class='DayPicker-NavButton DayPicker-NavButton--next']")).click();
            years = driver.findElement(By.xpath("//div[@class='DayPicker-Caption']/div[1]"));
        }
        List<WebElement> date = driver.findElements(By.xpath("//div[@class='DayPicker-Month']/div[3]/div/div/div/p[1]"));
        driver.findElement(By.xpath("//p[@class='font16 blackText latoBold appendRight30']")).click();
        // Thread.sleep(2000);
        for(WebElement dateToSelect : date)
        {
            if(dateToSelect.getText().equals("29"))
            {

                dateToSelect.click();
                Thread.sleep(10000);
                break;
            }       
        }
    }


    public void testCase03() throws InterruptedException {
        
             driver.get("https://www.makemytrip.com/");
             Thread.sleep(5000);
             
         WebElement trainsTab = driver.findElement(By.xpath("//span[@class='chNavIcon appendBottom2 chSprite chTrains']"));
         trainsTab.click();
         Thread.sleep(3000);
         WebElement fromStation = driver.findElement(By.xpath("//label[@for='fromCity']//span[1]"));
         fromStation.click();
         Thread.sleep(2000);
         
         WebElement SelectBangalore = driver.findElement(By.xpath("//input[@placeholder='From']"));
         SelectBangalore.click();
         SelectBangalore.sendKeys("ypr");
         Thread.sleep(2000);
         WebElement confirmBangalore = driver.findElement(By.xpath("(//div[@class='calc50']//p)[1]"));
         confirmBangalore.click();
         Thread.sleep(2000);
             //selecting Arrival station
         WebElement selectArrivalStation = driver.findElement(By.xpath("//label[@for='toCity']//span[1]"));
         selectArrivalStation.click();
         Thread.sleep(2000);
         WebElement searchStation = driver.findElement(By.xpath("//input[@title='To']"));
         searchStation.sendKeys("ndls");
         WebElement selectNewDelhi = driver.findElement(By.xpath("//span[normalize-space()='NDLS']"));
         selectNewDelhi.click();
         Thread.sleep(2000);

         // Selecting Date and class

             WebElement selectDateForTrain = driver.findElement(By.xpath("//input[@id='travelDate']"));
             selectDateForTrain.click();
             WebElement confirmDate = driver.findElement(By.xpath("//div[@aria-label='Fri Apr 19 2024']/following-sibling::div[1]"));
             confirmDate.click();
             Thread.sleep(2000);
             WebElement selectClass = driver.findElement(By.xpath("//span[@class='appendBottom5 downArrow']"));
             Thread.sleep(2000);
             selectClass.click();
             WebElement confirmClass = driver.findElement(By.xpath("//li[text()='Third AC']"));
             Thread.sleep(2000);
             confirmClass.click();
             WebElement searchTrainBtn = driver.findElement(By.xpath("//a[contains(text(),'Search')]"));
             searchTrainBtn.click();


             new WebDriverWait(driver, Duration.ofSeconds(5));
             List<WebElement> trainPriceElems = driver.findElements(By.xpath("//div[@id='train_options_20-04-2024_0']"));
             String trainPrice = trainPriceElems.get(0).getText();

             assert !trainPrice.isEmpty();
             System.out.println(trainPrice);
             System.out.println("end Test case: testCase03");


    }

    public void testCase04() throws InterruptedException {

        driver.get("https://www.makemytrip.com/");
        Thread.sleep(3000);
        WebElement selectBuses = driver.findElement(By.xpath("//span[text()='Buses']"));
       Thread.sleep(5000);
        selectBuses.click();
        Thread.sleep(5000);
        //Selecting departure city for buses

        WebElement departureCity = driver.findElement(By.xpath("(//div[@class='bussw_inputBox selectHtlCity']//label)[1]"));
        departureCity.click();
        Thread.sleep(3000);
        WebElement putCityName = driver.findElement(By.xpath("//input[@placeholder='From']"));
        Thread.sleep(2000);
        putCityName.sendKeys("bangl");
        Thread.sleep(3000);
        WebElement confirmCityName = driver.findElement(By.xpath("//span[text()='Bangalore, Karnataka']"));
        Thread.sleep(2000);
        confirmCityName.click();

        //Selecting arrival city for buses

        WebElement to = driver.findElement(By.xpath("//input[@id='toCity']"));
        Thread.sleep(2000);
        WebElement tocity = driver.findElement(By.xpath("//input[contains(@class,'react-autosuggest__input react-autosuggest__input--open')]"));
        tocity.sendKeys("ran");
        Thread.sleep(5000);
        WebElement toci = driver.findElement(By.xpath("//p[@class='searchedResult font14 darkText'][1]"));
        toci.click();
        // Selecting Date of Journey
        Thread.sleep(2000);
        String date = "May 2024";

        while(driver.findElement(By.xpath("//div[@role='heading']//div[1]")).isDisplayed()){

            System.out.println("Testcase04 : " + driver.findElement(By.xpath("//div[@role='heading']//div[1]")).getText());
            Thread.sleep(1000);
            WebElement nextbutton=driver.findElement(By.xpath("//span[@aria-label='Next Month']"));
            if(nextbutton.isDisplayed()){
                nextbutton.click();
            }
            Thread.sleep(1000);
            String currMonth =driver.findElement(By.xpath("//div[@role='heading']//div[1]")).getText();
            int c=date.compareTo(currMonth);
            if(c==0)
            {
                driver.findElement(By.xpath("//div[@aria-label='Wed May 29 2024']")).click();
                break;
            }
        }

        WebElement searchbtn = driver.findElement(By.xpath("//button[@data-cy='submit']"));
        searchbtn.click();
        Thread.sleep(2000);
        
        
        try{
        WebElement noBusesElement = driver.findElement(By.xpath("//span[@class='error-title']"));
        
        
        String displayedText = noBusesElement.getText();
        
        if(displayedText.contains("No buses found")){
            System.out.println(displayedText);
        }
    }
    catch(Exception e){
        System.out.println("bus is shown");
    }
        System.out.println("end Test case: testCase04");
        }
}


