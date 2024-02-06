package utils;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Objects;

public class PageObject {
    protected WebDriver browser;

    public PageObject(WebDriver browser) {
        System.setProperty("webdriver.chrome.driver", "drivers/chromedriver.exe");

        if (Objects.isNull(browser)){
            this.browser = new ChromeDriver();
        }else {
            this.browser = browser;
        }
    }

    public void fechar() {
        this.browser.quit();
    }
}
