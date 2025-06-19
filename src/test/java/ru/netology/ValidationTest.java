package ru.netology;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationTest {
    private WebDriver driver;

    @BeforeAll
    public static void setupAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void beforeEach() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
        driver.get("http://localhost:9999");
    }

    @AfterEach
    public void afterEach() {
        driver.quit();
        driver = null;
    }

//Валидация поля Фамилия и Имя
    @Test
    public void validationSurnameAndName() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Serdakova Natalia");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79998887766");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim());
        assertTrue(driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).isDisplayed());
    }

    @Test
    public void validationSurnameAndName1() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79998887766");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        assertEquals("Поле обязательно для заполнения", driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim());
        assertTrue(driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).isDisplayed());
    }

    @Test
    public void validationSurnameAndName2() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("23 Наталья");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79998887766");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.", driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim());
        assertTrue(driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).isDisplayed());
    }

//Валидация поля Телефон
    @Test
    public void validationPhone() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Сердакова Наталья");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+7999888776");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim());
        assertTrue(driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).isDisplayed());

    }

    @Test
    public void validationPhone1() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Сердакова Наталья");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        assertEquals("Поле обязательно для заполнения", driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim());
        assertTrue(driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).isDisplayed());

    }

    @Test
    public void validationPhone2() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Сердакова Наталья");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+Привет");
        driver.findElement(By.cssSelector("[data-test-id='agreement']")).click();
        driver.findElement(By.cssSelector("button")).click();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.", driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).getText().trim());
        assertTrue(driver.findElement(By.cssSelector("[data-test-id='phone'].input_invalid .input__sub")).isDisplayed());

    }

    //Валидация Чекбокса
    @Test
    public void validationCheckbox() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("Сердакова Наталья");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("+79998886677");
        driver.findElement(By.cssSelector("button")).click();
        assertTrue(driver.findElement(By.cssSelector("[data-test-id='agreement'].input_invalid")).isDisplayed());
    }

    @Test
    public void validationOfEmptyFields() {
        driver.findElement(By.cssSelector("[data-test-id='name'] input")).sendKeys("");
        driver.findElement(By.cssSelector("[data-test-id='phone'] input")).sendKeys("");
        driver.findElement(By.cssSelector("button")).click();
        assertEquals("Поле обязательно для заполнения", driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).getText().trim());
        assertTrue(driver.findElement(By.cssSelector("[data-test-id='name'].input_invalid .input__sub")).isDisplayed());
    }

}

