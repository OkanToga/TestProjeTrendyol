package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;

public class GooglePage {

    public GooglePage(){

        PageFactory.initElements(DriverManager.getDriver(),this);

    }

    @FindBy(xpath="//textarea[contains(@id, 'APjF')]")
    public WebElement googleAramaKutusu;

    @FindBy(xpath = "(//span[contains(text(),'Trendyol')])[1]")
    public WebElement googleTrendyolLinkiniAc;
}
