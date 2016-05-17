package com.steps;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import lombok.extern.slf4j.Slf4j;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.beans.factory.annotation.Value;

import java.net.URL;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

/**
 * Created by arthur on 13/05/16.
 */
@Slf4j
public class WeatherSteps {

    private WebDriver browser;

    @Value("app.url")
    private String appUrl = "http://localhost:8080/";

    private WebElement city_input;
    private WebElement btn_weather;
    private String city = "London";

    @Before
    public void setup() {
        log.info("Initial setup for a page");
        System.setProperty("webdriver.chrome.driver", "lib/chromedriver");
        browser = new ChromeDriver();
        browser.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        browser.manage().timeouts().setScriptTimeout(10, TimeUnit.SECONDS);
        browser.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);

    }

    @After
    public void tearDown() {
        log.info("Closing driver");
        browser.quit();
    }

    @Given("^Our application up and running$")
    public void Our_application_up_and_running() throws Throwable {
        log.info("the app is running");
    }

    @When("^I go to home page$")
    public void I_go_to_home_page() throws Throwable {
        browser.navigate().to(new URL(appUrl));
    }

    @Then("^I should be able to see the city input field$")
    public void I_should_be_able_to_see_the_city_input_field() throws Throwable {
        log.info("Page contains city name input " + browser.getPageSource().contains("city_name"));
        city_input = browser.findElement(By.id("city_name"));
        btn_weather = browser.findElement(By.id("btn_weather"));

        assertNotNull(city_input);
        assertNotNull(btn_weather);
    }

    @Given("^The app home page$")
    public void The_app_home_page() throws Throwable {
        browser.navigate().to(new URL(appUrl));
        city_input = browser.findElement(By.id("city_name"));
        btn_weather = browser.findElement(By.id("btn_weather"));
    }

    @When("^I fill the city name$")
    public void I_fill_the_city_name() throws Throwable {
        city_input.sendKeys(city);
    }

    @When("^press enter$")
    public void press_enter() throws Throwable {
//        city_input.sendKeys(Keys.ENTER);
        btn_weather.click();
    }

    @Then("^I should see the following details: Date, City Name, Weather description, Temperature in C, temperature in F, Sunrise, Sunset.$")
    public void I_should_see_the_following_details_Date_City_Name_Weather_description_Temperature_in_C_temperature_in_F_Sunrise_Sunset() throws Throwable {
        Thread.sleep(100);

        checkTodayDate();

        checkCityName();

        checkWeatherDescription();

        checkTemperatureInCel();

        checkTemperatureInFar();

        checkSunset();

        checkSunRise();
    }

    private void checkSunRise() {
        WebElement sunrise = browser.findElement(By.id("sunrise"));
        assertNotNull(sunrise);
        assertFalse(sunrise.getText().isEmpty());
    }

    private void checkSunset() {
        WebElement sunset = browser.findElement(By.id("sunset"));
        assertNotNull(sunset);
        assertFalse(sunset.getText().isEmpty());
    }

    private void checkTemperatureInFar() {
        WebElement far_temperature = browser.findElement(By.id("temperatureFar"));
        assertNotNull(far_temperature);
        assertFalse(far_temperature.getText().isEmpty());
    }

    private void checkTemperatureInCel() {
        WebElement celsius_temperature = browser.findElement(By.id("temperatureCel"));
        assertNotNull(celsius_temperature);
        assertFalse(celsius_temperature.getText().isEmpty());
    }

    private void checkWeatherDescription() {
        WebElement weather_description = browser.findElement(By.id("weatherDescription"));
        assertNotNull(weather_description);
        assertFalse(weather_description.getText().isEmpty());
    }

    private void checkCityName() throws InterruptedException {
        WebElement city_name = browser.findElement(By.id("cityName"));
        assertNotNull(city_name);
        assertEquals(city, city_name.getText());
    }

    private void checkTodayDate() {
        WebElement todayDate = browser.findElement(By.id("todayDate"));
        assertNotNull(todayDate);
        assertFalse(todayDate.getText().isEmpty());
    }
}
