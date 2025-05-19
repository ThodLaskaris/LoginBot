import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private final WebDriver dr;

    private final By usernameField = By.id("username");
    private final By passwordField = By.id("password");
    private final By loginButton = By.cssSelector(("button[type='submit']"));
    private final By flashMsg = By.id("flash");

    public LoginPage(WebDriver dr) {
        this.dr = dr;
    }

    public void openPage() {
        dr.get("https://the-internet.herokuapp.com/login");
    };
    public void enterUsername(String username) {
        dr.findElement(usernameField).sendKeys(username);
    }
    public void enterPassword(String password) {
        dr.findElement(passwordField).sendKeys(password);
    }
    public void enterLogin() {
        dr.findElement(loginButton).click();
    }

    public String getFlashMsg() {
        return dr.findElement(flashMsg).getText();
    }
}
