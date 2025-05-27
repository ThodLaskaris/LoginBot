import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver dr;

    private final By usernameField = By.id("username"); // αρχικοποιηση για να μην χρησιμιοποιω hardcoded μεσα στον κωδικα και βρηκα το By.id μεσω του developer tools
    private final By passwordField = By.id("password"); // αρχικοποιηση για να μην χρησιμιοποιω hardcoded μεσα στον κωδικα  βρηκα το By.id μεσω του developer tools
    private final By loginButton = By.cssSelector(("button[type='submit']"));   // αρχικοποιηση για να μην χρησιμιοποιω hardcoded μεσα στον κωδικα  βρηκα το by.css μεσω του developer tools
    private final By flashMsg = By.id("flash"); // αρχικοποιηση για να μην χρησιμιοποιω hardcoded μεσα στον κωδικα βρηκα το By.id μεσω του developer tools

    public LoginPage(WebDriver dr) {
        this.dr = dr; // Constructor για να μπορεσω να τον καλεσω
    }

    public void openPage() {
        dr.get("https://the-internet.herokuapp.com/login");
    }; // ανοιγμα της σελιδας
    public void enterUsername(String username) {
        dr.findElement(usernameField).sendKeys(username);
    } //  χρησιμιοποιηω το usernamefield και στελνω κλειδι ( data ) το username
    public void enterPassword(String password) {
        dr.findElement(passwordField).sendKeys(password);
    } // χρησιμιοποιηω το passwordField και στελνω κλειδι ( data ) το password
    public void enterLogin() {
        dr.findElement(loginButton).click();
    }   // κλικ το κουμπι

    public String getFlashMsg() {
        String rawMsg = dr.findElement(flashMsg).getText(); // παιρνω το text message  και στγην απο κατω γραμμη βγαζω τα regex με και αναπληρωση με ""
        return rawMsg.replaceAll("[×?,]", "").trim();
    }

}
