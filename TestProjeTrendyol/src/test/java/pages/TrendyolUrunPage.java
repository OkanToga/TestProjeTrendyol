package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;



public class TrendyolUrunPage {
    public TrendyolUrunPage() {
        PageFactory.initElements(DriverManager.getDriver(),this);
    }
    @FindBy(css = "div[class='pr-bx-nm with-org-prc'] span[class='prc-dsc']")
    public WebElement urunFiyat;

    @FindBy(css = ".pr-new-br")
    public WebElement urunIsim;

    @FindBy(css = ".product-brand-name-without-link")
    public WebElement urunMarka;

    @FindBy(xpath = "//p[contains(normalize-space(), 'Sepetim')]")
    public WebElement trendyolSepetAc;

    @FindBy(xpath = "//a[@class='pb-basket-item-details-info']//p")
    public WebElement sepettekiUrunIsmi;

    @FindBy(css = ".pb-basket-item-price")
    public WebElement sepettekiUrunFiyat;
}
