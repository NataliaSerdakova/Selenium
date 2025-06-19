import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Main {
    public static void main(String[] args) {
        String chromeDriverPath = "/путь/к/vашему/chromedriver"; // Путь к вашему драйверу
        System.setProperty("webdriver.chrome.driver", chromeDriverPath);

        WebDriver driver = new ChromeDriver();
        driver.get("https://yandex.ru/");
        System.out.println("Текущая страница: " + driver.getCurrentUrl());
        driver.quit();
    }
}