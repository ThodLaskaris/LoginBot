
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.Duration;

public class LoginTest {
    private WebDriver testDriver;
    private LoginPage loginPage;
    private static final String correctUsername = "tomsmith";
    private static final String correctPassword = "SuperSecretPassword!";
    private static final String emptyUsername = "";
    private static final String emptyPassword = "";
    private static final String wrongUsername = "wrongJUnitTest";
    private static final String wrongPassword = "wrongJUnitTest";

    @BeforeEach
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/thodlaskaris/Desktop/chromeDriver/chromedriver");
        testDriver = new ChromeDriver();
        testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        loginPage = new LoginPage(testDriver);
        loginPage.openPage();
    }

    @AfterEach
    public void clearUp() {
        if (testDriver != null) {
            System.out.println("Clearing...");
            testDriver.quit();
        }
    }

    private void perfomLogin(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.enterLogin();
    }

    @Test
    public void LoginSuccess() {
        perfomLogin(correctUsername, correctPassword);
    }

    @Test
    public void LoginUsernameEmpty() {
        perfomLogin(emptyUsername, correctPassword);
    }

    @Test
    public void LoginUsernameWrong() {
        perfomLogin(wrongUsername, correctPassword);
    }

    @Test
    public void LoginPasswordEmpty() {
        perfomLogin(correctUsername, emptyPassword);
    }

    @Test
    public void LoginPasswordWrong() {
        perfomLogin(correctUsername, wrongPassword);
    }


}
