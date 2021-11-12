package allure_report;

import allure_report.gitPage.GitHubTest;
import io.qameta.allure.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class StepsAnnotatedTest {
    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final String NUMBER_ISSUES = "56";

    @Test
    @Owner("Zhmaka Andrey")
    @Feature("Authorization")
    @Story("Authorization by mail")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("TestAnnotated")
    @Link(name = "GitHub", url = "https://github.com")
    public void GitHubTestAnnotated() {
        GitHubTest step = new GitHubTest();

        step.openSite();
        step.findRepository(REPOSITORY);
        step.openRepository(REPOSITORY);
        step.shouldSeeIssueWithNumber(NUMBER_ISSUES);
        step.takeScreenshot();
    }

    @Test
    @Owner("Zhmaka Andrey")
    @Feature("Authorization")
    @Story("Authorization by mobile phone")
    @Severity(SeverityLevel.NORMAL)
    @DisplayName("TestAnnotated")
    @Link(name = "GitHub", url = "https://github.com")
    public void GitHubTestAnnotated2() {
        GitHubTest step = new GitHubTest();

        step.openSite();
        step.findRepository(REPOSITORY);
        step.openRepository(REPOSITORY);
        step.shouldSeeIssueWithNumber(NUMBER_ISSUES);
        step.takeScreenshot();
    }
}
