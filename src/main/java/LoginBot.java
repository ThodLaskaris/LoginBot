import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Scanner;

import java.time.Duration;

public class LoginBot {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/thodlaskaris/Desktop/chromeDriver/chromedriver");
        WebDriver dr = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(dr, Duration.ofSeconds(12));

        try {
            LoginPage loginPage = new LoginPage(dr);

            loginPage.openPage();

            loginPage.enterUsername("tomsmith");
            loginPage.enterPassword("SuperSecretPassword!");
            loginPage.enterLogin();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));

            String msg = loginPage.getFlashMsg();
            if (msg.trim().contains("You logged into a secure area!")) {
                System.out.println("Log in");
            } else {
                System.out.println("Failed");
                System.out.println("Error msg: " + msg);
            }
            new Scanner(System.in).nextLine();
            System.out.println("Press a key to exit.");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dr.quit();
        }
    }
}