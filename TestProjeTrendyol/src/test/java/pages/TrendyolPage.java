package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utils.DriverManager;

public class TrendyolPage {
    public TrendyolPage() {

        PageFactory.initElements(DriverManager.getDriver(),this);

    }

    @FindBy(xpath="//*[name()='path'][contains(@id, 'Combined')]")
    public WebElement reklamKapatmaTusu;

    @FindBy(xpath="//input[contains(@placeholder, 'Aradığınız')]")
    public WebElement trendyolAramaKutusu;

    @FindBy(xpath = "//i[contains(@class, 'cyrzo7g')]")
    public WebElement trendyolAramaKutusuButonu;

    @FindBy(xpath = "//div[contains(@class, 'selected-order')]")
    public WebElement trendyolUrunListelemeKutusu;

    @FindBy(xpath = "//span[contains(normalize-space(), 'En yüksek fiyat')]")
    public WebElement trendyolUrunListelemeKutusuEnYuksekFiyat;

    @FindBy(xpath = "//div[contains(@class, 'image-overlay-body')]")
    public WebElement trendyolEnPahaliUrununSayfayaGit;

    @FindBy(xpath = "//button[contains(normalize-space(), 'Anladım')]")
    public WebElement trendyolUrunSayfasiPopupKapatma;

    @FindBy(xpath = "//div[contains(@class, 'add-to-basket-button-text')]")
    public WebElement trendyolUrunuSepeteEkle;

    @FindBy(xpath = "//span[@class='prdct-desc-cntnr-ttl']")
    public WebElement trendyolUrun;

}
