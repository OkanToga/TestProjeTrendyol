package stepdef;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import io.cucumber.java.*;
import io.cucumber.java.en.*;
import org.junit.Test;
import org.openqa.selenium.*;
import pages.TrendyolPage;
import utils.ConfigReader;
import utils.DriverManager;
import utils.ExtentReportManager;

import utils.ReusableMethods;
import pages.GooglePage;
import pages.TrendyolUrunPage;
import utils.ExtentReportManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;


public class Bilgisayar_sepete_ekle /*extends ExtentReportManager*/{
    WebDriver driver;
    String selectedProductName;
    String selectedProductPrice;
    String selectedProductBrand;
    String remainingProductName;

    String remainingCartProductName;
    String cartProductBrand;
    String cartProductName;
    String cartProductPrice;


    TrendyolPage TrendyolPage = new TrendyolPage();
    GooglePage GooglePage = new GooglePage();
    TrendyolUrunPage TrendyolUrunPage = new TrendyolUrunPage();

    //ExtentReportManager extentReportManager = new ExtentReportManager();

   /* @Before
    public void setUp() {
        try {
            ExtentReportManager.beforeAll();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/
   @Before
   public void setUp() {
       driver = DriverManager.getDriver(); // Driver'ı burada başlatıyoruz
   }

    @Given("Google sitesine gidilir")
    public void Google_sitesine_gidilir() {
        //extentReportManager.extentTest = extentReportManager.extent.createTest("Google sitesine gidilir", "Şuan yolda");
        String googleUrl = ConfigReader.getProperty("googleUrl");
        DriverManager.getDriver().get(googleUrl);

    }

        @When("{string} aratilir")
        public void aratilir(String searchTerm) {
            //extentReportManager.extentTest = extentReportManager.extent.createTest("Trendyol aratılır", "Aratılıyor");
            try
            {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ReusableMethods.sendText(GooglePage.googleAramaKutusu, searchTerm);
            GooglePage.googleAramaKutusu.submit();
            //extentReportManager.extentTest.log(Status.PASS,"Trendyol aratıldı.");
        }

        @And("Trendyol websitesi acilir")
        public void Trendyol_websitesi_acilir() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ReusableMethods.jsClick(GooglePage.googleTrendyolLinkiniAc);

        }

        @Then("Trendyol reklam kapat")
        public void Trendyol_reklam_kapat() {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TrendyolPage.reklamKapatmaTusu.click();
            //ReusableMethods.jsclick(TrendyolPage.reklamKapatmaTusu);
        }

        @Then("{string} trendyolda aratilir")
        public void Bilgisayar_aratilir(String productName) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ReusableMethods.sendText(TrendyolPage.trendyolAramaKutusu, productName);
            ReusableMethods.jsClick(TrendyolPage.trendyolAramaKutusuButonu);
        }

        @When("Liste acilir")
        public void Liste_acilir() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ReusableMethods.jsClick(TrendyolPage.trendyolUrunListelemeKutusu);

        }

        @Then("En yuksek fiyat secilir")
        public void En_yuksek_fiyat_secilir() {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ReusableMethods.jsClick(TrendyolPage.trendyolUrunListelemeKutusuEnYuksekFiyat);

        }

        @And("Bilgisayar sayfasi acilir")
        public void Bilgisayar_sayfasi_acilir() {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            String urun = TrendyolPage.trendyolUrun.getText();

            ReusableMethods.jsClick(TrendyolPage.trendyolEnPahaliUrununSayfayaGit);
            ReusableMethods.switchToNewTab();
            //switchToNewTab();
        }

        @When("Bildirim kapat")
        public void Bildirim_kapat() {

            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            ReusableMethods.jsClick(TrendyolPage.trendyolUrunSayfasiPopupKapatma);
        }

        @Then("Urunu sepete ekler")
        public void Urunu_sepete_ekler() {

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ReusableMethods.jsClick(TrendyolPage.trendyolUrunuSepeteEkle);

            selectedProductName = TrendyolUrunPage.urunIsim.getText();
            selectedProductPrice = TrendyolUrunPage.urunFiyat.getText();
            selectedProductBrand = TrendyolUrunPage.urunMarka.getText();

            //System.out.println(selectedProductName);
            //System.out.println(selectedProductPrice);

            remainingProductName = removeBrandName(selectedProductName, selectedProductBrand);

            //System.out.println(remainingProductName);
        }

        public static String removeBrandName(String fullProductName, String brand) {
            if (fullProductName.startsWith(brand)) {
                return fullProductName.substring(brand.length()).trim();
            } else {
                return fullProductName;
            }

        }

        @When("Sepeti acar")
        public void Sepeti_acar() {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ReusableMethods.jsClick(TrendyolUrunPage.trendyolSepetAc);
        }

        @Then("Sepet kontrol")
        public void Sepet_kontrol() {

            cartProductName = TrendyolUrunPage.sepettekiUrunIsmi.getText();
            cartProductPrice = TrendyolUrunPage.sepettekiUrunFiyat.getText();
            cartProductBrand = selectedProductBrand;

            remainingCartProductName = removeBrandName(cartProductName, cartProductBrand);

            cartProductPrice = cartProductPrice.replaceAll("\\s+", "");
            String[] prices = cartProductPrice.split("TL");
            selectedProductPrice = selectedProductPrice.replaceAll("\\s+", "");

            boolean isPricePresent = false;
            for (String price : prices) {
                if (price.equals(selectedProductPrice.replace("TL", ""))) {
                    isPricePresent = true;
                    break;
                }
            }

            if ((Objects.equals(remainingCartProductName, remainingProductName))) {
                if (isPricePresent) {
                    System.out.println("Ürün doğru seçilmiş!");
                } else {
                    System.out.println("Ürün yanlış seçilmiş!");
                }

            }


            //extentReportManager.flushReports();
        }

    @AfterStep
    public void takeScreenshot(Scenario scenario){

        final byte [] screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
        scenario.attach(screenshot, "image/png", "image");

    }
    @After
    public void close(){
        DriverManager.closeDriver();
    }

}














