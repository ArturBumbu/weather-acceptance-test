package functional;

import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * Created by arthur on 13/05/16.
 */
@RunWith(Cucumber.class)
@Cucumber.Options(tags = {"@weather"}, glue = "com.steps",
        monochrome = true,
        format = "html:build/tests/weather.html")
public class WeatherTest {
}
