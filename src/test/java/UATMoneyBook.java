//นายณัฐภัทร ชาญธีระเดช 5810404995

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        format = {"pretty", "html:target/cucumber"},
        features = {"classpath:features/moneyBook.feature"}
)

public class UATMoneyBook {
}
