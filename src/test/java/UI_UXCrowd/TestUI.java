package UI_UXCrowd;

import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;


    public class TestUI {
        public static AuthorizationPO authorizationPO;
        public static WebDriver driver;
        public static WebDriverWait wait;
        public static EnvConfig envConfig;

        @BeforeEach
        public void init() {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--remote-allow-origins=*");
            driver = new ChromeDriver(options);
            wait = new WebDriverWait(TestUI.driver, Duration.ofSeconds(20));
            driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.MINUTES);
            driver.manage().timeouts().implicitlyWait(10,TimeUnit.MINUTES);
            envConfig = new EnvConfig();
            driver.get(TestUI.envConfig.baseUrl);
            envConfig = new EnvConfig();
            driver.get(envConfig.baseUrl);
            authorizationPO = new AuthorizationPO(driver);

        }
        @AfterEach
        public void driverQuit() {
            driver.close();
            driver.quit();
        }
        @Test
        public void authTest(){

            authorizationPO.login();
            authorizationPO.setLogin(envConfig.username);
            authorizationPO.setPassword(envConfig.userPassword);
            authorizationPO.setLogBTN();
            wait.until(ExpectedConditions.alertIsPresent());
            String URL = driver.getCurrentUrl();
            Assert.assertEquals(URL,envConfig.baseUrl);
        }

    }


