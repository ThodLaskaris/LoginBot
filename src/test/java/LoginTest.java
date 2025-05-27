
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

    // αρχικοποιηση για να αποφυγω hardcoding ( πιο ευκολη συντηρηση + reusable)


    @BeforeEach // τα global settings πριν την εκκιηση του καθε test
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "/Users/thodlaskaris/Desktop/chromeDriver/chromedriver"); // key function, value path
        testDriver = new ChromeDriver(); // new object για το ChromeDriver Selenium
        testDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); //wait το ιδιο με αυτο που εγραψα στο selenium test
        loginPage = new LoginPage(testDriver); // new object απο την loginPage με το object testDriver(ChromeDriver)
        loginPage.openPage(); // ανοιγμα του προγραμματος Chrome

    }

    @AfterEach // tearup τα settings sto arxiko state
    public void clearUp() {
        if (testDriver != null) { // ελεγχος αν το object δεν ειναι null εκτελει την quit.
            System.out.println("Clearing...");
            testDriver.quit();
        }
    }
    public String randomString(int length) { //random string δημιουργια με μηκος 20 οπως αρχικοποιησαμε πιο πανω
                                             // επιλεγει χαρακτηρες με stringbuilder-random για το allkeyboard.lenfth
                                             // helper method
        StringBuilder sb = new StringBuilder(); // δημιουργια stringbuilder για την λοοοπ
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            int index = random.nextInt(allKeyboard.length()); // διαπεραση και αρχικοποιηση index με την βοηθεια της random
                                                                    // κατα το μηκος της allkeyboard lenth -1
            sb.append(allKeyboard.charAt(index)); // βαζει το γραμμα απο το indeχ που δημιοργησαμε στο stringbuildfer
        }
        return sb.toString(); // επιστροφη του object stringbuilder (sb) με την μεθοδο τοΣτρινγκ

    }

    public void assertErrorMessage(String expectedMessage) { //helper method για να αποφυγη repetition
        String actualMessage = loginPage.getFlashMsg(); // καλει το msgtext ( wrong login logged in κλπ )
        assertEquals(expectedMessage, actualMessage);   // συγκρινει το μηνυμα που περιμενουμε και το μηνυμα που παραλαβαμε
    }
    @Test
    public void loginFuzz() { //test με το random String
        performLogin(fuzzUsername, fuzzPassword);
    }
    private void performLogin(String username, String password) { //helper method για να αποφυγη repetition
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.enterLogin();

    }

    @Test
    public void loginSuccess() {
        performLogin(correctUsername, correctPassword);
    } // ο,τι λεει

    @Test
    public void loginUsernameEmpty() { // ο,τι λεει
        performLogin(emptyUsername, correctPassword);
        assertErrorMessage("Your username is invalid!");
    }

    @Test
    public void loginUsernameWrong() { // ο,τι λεει
        performLogin(wrongUsername, correctPassword);
        assertErrorMessage("Your username is invalid!");
    }

    @Test
    public void loginPasswordEmpty() {
        performLogin(correctUsername, emptyPassword);
    } // ο,τι λεει

    @Test
    public void loginPasswordWrong() { // ο,τι λεει
        performLogin(correctUsername, wrongPassword);
        assertErrorMessage("Your password is invalid!");
    }

    @Test
    public void loginUsernamePasswordWrong() { // ο,τι λεει
        performLogin(wrongUsername, wrongPassword);
        assertErrorMessage("Your username is invalid!");

    }
}
