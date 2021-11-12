package allure_report;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.WebDriverRunner;
import io.qameta.allure.Allure;
import io.qameta.allure.AllureLifecycle;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsLambda_Test {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String NUMBER_ISSUES = "5";

    @Test
    public void GitHubTestLambda() {
        AllureLifecycle lifecycle = Allure.getLifecycle();

        step("Open GitHub", () -> {
            open("https://github.com/");
        });

        step("Search repository " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });

        step("Open repository " + REPOSITORY, () -> {
            $(linkText("eroshenkoam/allure-example")).click();
        });

        step("Assert Issue visible and has " + NUMBER_ISSUES + " records", () -> {
            $("#issues-repo-tab-count").should(Condition.visible).shouldHave(Condition.exactTextCaseSensitive(NUMBER_ISSUES));
            lifecycle.addAttachment("Screenshot", "image/png", "png", getScreenshot());
        });

        Allure.getLifecycle().updateTestCase(testCase -> {
            testCase.setName("TestLambda");
        });
        Allure.label("owner", "Zhmaka Andrey");
        Allure.feature("Authorization");
        Allure.story("Authorization by mail");
        Allure.label("severity", "NORMAL");
        Allure.link("GitHub", "https://github.com");
    }

    private byte[] getScreenshot() {
        final WebDriver driver = WebDriverRunner.getWebDriver();
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }
}
