import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.Scanner;

import java.time.Duration;

public class LoginBot {
    public static void main(String[] args) {
        System.setProperty("webdriver.chrome.driver", "/Users/thodlaskaris/Desktop/chromeDriver/chromedriver");     // System Property(key) "path" Value
        WebDriver dr = new ChromeDriver(); //new object
        WebDriverWait wait = new WebDriverWait(dr, Duration.ofSeconds(12)); //Αναμονη μαξ των 12 σεκ αν εχει φορτωσει πιο γρηγορα δεν περιμενω.
        //  Υπαρχει και αλλη μεθοδος που δεν την θυμαμαι και ειναι ανεξαρτητου αν εχει φορτωθει η οχι αναμονη Χ secds

        try {
            LoginPage loginPage = new LoginPage(dr);
            // new Object

            loginPage.openPage();
            // Ανοιγμα page


            loginPage.enterUsername("tomsmith"); // hard coded
            loginPage.enterPassword("SuperSecretPassword!"); // hard coded
            loginPage.enterLogin();

            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("flash")));
            //Αναμονη μαξ των 12 σεκ αν εχει φορτωσει πιο γρηγορα δεν περιμενω.
            //  Υπαρχει και αλλη μεθοδος που δεν την θυμαμαι και ειναι ανεξαρτητου αν εχει φορτωθει η οχι αναμονη Χ secds
            //  Βρηκαμε το ''flash'' id απο Developer tools στον Chrome και βρηκαμε το attribute του text(error message κλπ )

            String msg = loginPage.getFlashMsg();
            // αρχικοποιηηση γι να μπορεσουμε να το καλεσουμε

            if (msg.trim().contains("You logged into a secure area!")) { //Τριμαρουμε τα space
                System.out.println("Log in");                            // debug helper
            } else {
                System.out.println("Failed");                             // debug helper
                System.out.println("Error msg: " + msg);                    // debug helper
            }
            new Scanner(System.in).nextLine();                      // Scanner για να χρησιμιοποιησω keyboard στην consola να κλεισω το προγραμμα
            System.out.println("Press a key to exit.");
        } catch (Exception e) {                                             // Γενικο Exception που περιγραφει τα exception ( απο το να βαλω καποιο ειδικο )
            e.printStackTrace();
        } finally {
            dr.quit();                                                      // Ανεξαρτητως τι θα γινει με τα εξεπτιον , το προγραμμα θα κλεισει.
        }
    }
}