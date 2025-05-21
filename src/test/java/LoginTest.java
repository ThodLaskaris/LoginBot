
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.Random;

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
    private static final String allKeyboard = "qwertyuiopasdfghjklzxcvbnm1234567890`~!@#$%^&*()[]\\{}|,./<>?";
    private final String fuzzUsername = randomString(25);
    private final String fuzzPassword = randomString(25);


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
    public String randomString(int length) {
        StringBuilder sb = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allKeyboard.length());
            sb.append(allKeyboard.charAt(index));
        }
        return sb.toString();
    }

    public void assertErrorMessage(String expectedMessage) {
        String actualMessage = loginPage.getFlashMsg();
        assertEquals(expectedMessage, actualMessage);
    }
    @Test
    public void loginFuzz() {

        performLogin(fuzzUsername, fuzzPassword);
    }
    private void performLogin(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.enterLogin();

    }

    @Test
    public void loginSuccess() {
        performLogin(correctUsername, correctPassword);
    }

    @Test
    public void loginUsernameEmpty() {
        performLogin(emptyUsername, correctPassword);
        assertErrorMessage("Your username is invalid!");
    }

    @Test
    public void loginUsernameWrong() {
        performLogin(wrongUsername, correctPassword);
        assertErrorMessage("Your username is invalid!");
    }

    @Test
    public void loginPasswordEmpty() {
        performLogin(correctUsername, emptyPassword);
    }

    @Test
    public void loginPasswordWrong() {
        performLogin(correctUsername, wrongPassword);
        assertErrorMessage("Your password is invalid!");
    }

    @Test
    public void loginUsernamePasswordWrong() {
        performLogin(wrongUsername, wrongPassword);
        assertErrorMessage("Your username is invalid!");

    }
}
