package utils;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.function.Function;

public class ReusableMethods {

    static Actions actions;
    static LocalDateTime date;
    static String tarih;

    static Select select;
    static WebElement ddm;

    public static Select select(WebElement ddm){ //select method

        return select = new Select(ddm);
    }

//    public static String getScreenshot(String name) throws IOException {
//        // naming the screenshot with the current date to avoid duplication
//        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
//        // TakesScreenshot is an interface of selenium that takes the screenshot
//        TakesScreenshot ts = (TakesScreenshot) Driver.getDriver();
//        File source = ts.getScreenshotAs(OutputType.FILE);
//        // full path to the screenshot location
//        String target = System.getProperty("user.dir") + "/target/Screenshots/" + name + date + ".png";
//        File finalDestination = new File(target);
//        // save the screenshot to the path given
//        FileUtils.copyFile(source, finalDestination);
//        return target;
//    }
    //========Switching Window=====//
    public static void switchToWindow(String targetTitle) {
        String origin = DriverManager.getDriver().getWindowHandle();
        for (String handle : DriverManager.getDriver().getWindowHandles()) {
            DriverManager.getDriver().switchTo().window(handle);
            if (DriverManager.getDriver().getTitle().equals(targetTitle)) {
                return;
            }
        }
        DriverManager.getDriver().switchTo().window(origin);
    }
    //========Hover Over=====//
    public static void hover(WebElement element) {
        Actions actions = new Actions(DriverManager.getDriver());
        actions.moveToElement(element).perform();
    }
    //==========Return a list of string given a list of Web Element====////
    public static List<String> getElementsText(List<WebElement> list) {
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : list) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }
    //========Returns the Text of the element given an element locator==//
    public static List<String> getElementsText(By locator) {
        List<WebElement> elems = DriverManager.getDriver().findElements(locator);
        List<String> elemTexts = new ArrayList<>();
        for (WebElement el : elems) {
            if (!el.getText().isEmpty()) {
                elemTexts.add(el.getText());
            }
        }
        return elemTexts;
    }

    public void checkTextAndButton(WebElement textElement, String expectedText, WebElement buttonElement) {
        ReusableMethods.waitForVisibility(textElement, 3);
        System.out.println(textElement.getText());
        Assert.assertEquals(textElement.getText(), expectedText);
        Assert.assertTrue(buttonElement.isDisplayed());
    }
    //   HARD WAIT WITH THREAD.SLEEP
//   waitFor(5);  => waits for 5 second
    public static void waitFor(int sec) {
        try {
            Thread.sleep(sec * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    //===============Explicit Wait==============//
    public static WebElement waitForVisibility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOf(element));
    }
    public static WebElement waitForVisibility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }
    public static WebElement waitForClickablility(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
    public static WebElement waitForClickablility(By locator, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }
    public static void clickWithTimeOut(WebElement element, int timeout) {
        for (int i = 0; i < timeout; i++) {
            try {
                element.click();
                return;
            } catch (WebDriverException e) {
                waitFor(1);
            }
        }
    }
    public static void waitForPageToLoad(long timeout) {
        ExpectedCondition<Boolean> expectation = new ExpectedCondition<Boolean>() {
            public Boolean apply(WebDriver driver) {
                return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
            }
        };
        try {
            System.out.println("Waiting for page to load...");
            WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
            wait.until(expectation);
        } catch (Throwable error) {
            System.out.println(
                    "Timeout waiting for Page Load Request to complete after " + timeout + " seconds");
        }
    }
    //======Fluent Wait====//
    public static WebElement fluentWait(final WebElement webElement, int timeout) {
        //FluentWait<WebDriver> wait = new FluentWait<WebDriver>(Driver.getDriver()).withTimeout(timeinsec, TimeUnit.SECONDS).pollingEvery(timeinsec, TimeUnit.SECONDS);
        FluentWait<WebDriver> wait = new FluentWait<WebDriver>(DriverManager.getDriver())
                .withTimeout(Duration.ofSeconds(3))//Wait 3 second each time
                .pollingEvery(Duration.ofSeconds(1));//Check for the element every 1 second
        WebElement element = wait.until(new Function<WebDriver, WebElement>() {
            public WebElement apply(WebDriver driver) {
                return webElement;
            }
        });
        return element;
    }

    //====== JS Scroll Click ====//
    public static void jsScrollClick(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        try {
            webElement.click();
        } catch (Exception e) {
            js.executeScript("arguments[0].scrollIntoView(true);", webElement);
            js.executeScript("arguments[0].click()", webElement);
            waitFor(1);
        }
    }

    //====== JS Scroll Click ====//
    public static void jsScroll(WebElement webElement) {
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
            js.executeScript("arguments[0].scrollIntoView(true);", webElement);
    }
    //====== js ======//
    public static void jsclick(WebElement webElement){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", webElement);
        try {
            webElement.click();
        } catch (Exception e) {
            JavascriptExecutor executor = (JavascriptExecutor) DriverManager.getDriver();
            executor.executeScript("arguments[0].click();", webElement);
        }
    }
    //====== js (jsclick ile aynı işlemi yapıyor fakat bazen try-catch den dolayı yukarıdaki exception fırlatabiliyor)======//
    public static void jsClick(WebElement webElement){
        JavascriptExecutor js = (JavascriptExecutor) DriverManager.getDriver();
        js.executeScript("arguments[0].click();", webElement);
    }

    //========ScreenShot Web Element(Bir webelementin resmini alma)=====//
    public static String getScreenshotWebElement(String name,WebElement element) throws IOException {
        String date = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
        // TakesScreenshot is an interface of selenium that takes the screenshot
        File source = element.getScreenshotAs(OutputType.FILE);
        // full path to the screenshot location
        String wElementSS = System.getProperty("user.dir") + "/target/WElementScreenshots/" + name + date + ".png";
        File finalDestination = new File(wElementSS);
        // save the screenshot to the path given
        FileUtils.copyFile(source, finalDestination);
        return  wElementSS;
    }

    public static void findAndClick(WebElement webElement) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(5));
        WebElement element = wait.until(ExpectedConditions.elementToBeClickable(webElement));
        ((JavascriptExecutor) DriverManager.getDriver()).executeScript("arguments[0].click();", webElement);
    }
    public static Random random() {

        Random random;
        return random = new Random();
    }

    public static WebElement waitForClickable(WebElement element, int timeout) {
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), Duration.ofSeconds(timeout));
        return wait.until(ExpectedConditions.elementToBeClickable(element));
    }
     public static void Date(){

        date = LocalDateTime.now();
        DateTimeFormatter formater = DateTimeFormatter.ofPattern("YYYYMMddHHmmss");
        tarih = date.format(formater);
    }

    public static String setTheDateByRandom (String format,int atMostYear, String direction)
    {
        int day = (int) (Math.random() * 366 + 1);
        int month = (int) (Math.random() * 13 + 1);
        int year = (int) (Math.random() * atMostYear + 1);
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
        direction = direction.toUpperCase(Locale.ROOT);
        String dateF;
        switch (direction) {
            case "FEATURE":
                date = date.plusYears(year).plusMonths(month).plusDays(day);
                dateF = formatter.format(date);
                return dateF;
            case "PAST":
                date = date.minusYears(year).minusMonths(month).minusDays(day);
                dateF = formatter.format(date);
                return dateF;
            default:
                dateF = formatter.format(date);
                return dateF;
        }
    }
    public static void sendText(WebElement element, String text) {
        try {
            waitForVisibility(element, 5);  // Elementin görünür olmasını bekler
            waitForClickablility(element, 5); // Elementin tıklanabilir olmasını bekler
            element.clear(); // Kutuyu temizler, eğer daha önce bir değer varsa
            element.sendKeys(text); // İstenilen metni yazar
        } catch (Exception e) {
            System.out.println("Yazı yazma işlemi sırasında bir hata oluştu: " + e.getMessage());
            // Gerekirse buraya başka hata yönetimi ekleyebilirsin
        }
    }

    public static void switchToNewTab() {
        // Mevcut sekme handle'ını al
        String originalTab = DriverManager.getDriver().getWindowHandle();

        // Sekmelerin tüm handle'larını al
        List<String> tabs = new ArrayList<>(DriverManager.getDriver().getWindowHandles());

        // En son sekmeye geçiş yap
        DriverManager.getDriver().switchTo().window(tabs.get(tabs.size() - 1));
    }




}