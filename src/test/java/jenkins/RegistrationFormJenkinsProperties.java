package jenkins;

import attachAllure.Attach;
import baseTest.BaseTest;
import com.codeborne.selenide.Configuration;
import jenkins.config.PropertiesConfig;
import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.*;
import org.openqa.selenium.remote.DesiredCapabilities;

import static java.lang.String.format;

@Tag("RegistrationFormProperties")
public class RegistrationFormJenkinsProperties extends BaseTest {
    public PropertiesConfig properties = ConfigFactory.create(PropertiesConfig.class);
    private final String url = properties.url();
    private final String login = properties.login();
    private final String password = properties.password();

    @BeforeEach
    void setUp() {
        //Подключаем Selenoid
        Configuration.browserSize = "1920x1080";
        Configuration.remote = format("https://%s:%s@%s", login, password, url);

        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability("enableVNC", true);
        capabilities.setCapability("enableVideo", true);
        Configuration.browserCapabilities = capabilities;
    }

    @AfterEach
    public void getAttach() {
        Attach.screenshotAs("Last screenshot");
        Attach.browserConsoleLogs();
        Attach.pageSource();
        Attach.addVideo();
    }

    @Test
    void fillFormTest() {
        final String FIRST_NAME = faker.name().firstName();
        final String LAST_NAME = faker.name().lastName();
        final String USER_EMAIL = "AndreyZhmaka@mail.com";
        final String GENDER = "Male";
        final String DATE_DAY = "30";
        final String DATE_MONTH = "July";
        final String DATE_YEAR = "2008";
        final String USER_NUMBER = faker.number().digits(10);
        final String SUBJECT = "English";
        final String HOBBIES = "Sports";
        final String PICTURE = "pic.png";
        final String CURRENT_ADDRESS = faker.address().streetAddress();
        final String STATE = "Rajasthan";
        final String CITY = "Jaiselmer";

        registrationsPage.openPage().typeFirstName(FIRST_NAME);
        registrationsPage.typeLastName(LAST_NAME);
        registrationsPage.typeEmail(USER_EMAIL);
        registrationsPage.typeGender(GENDER);
        registrationsPage.typeNumber(USER_NUMBER);
        registrationsPage.calendar.setDate(DATE_DAY, DATE_MONTH, DATE_YEAR);
        registrationsPage.typeSubjects(SUBJECT);
        registrationsPage.selectHobbies(HOBBIES);
        registrationsPage.selectPicture(PICTURE);
        registrationsPage.typeAddress(CURRENT_ADDRESS);
        registrationsPage.selectState(STATE);
        registrationsPage.selectCity(CITY);
        registrationsPage.sendForm();

        registrationsPage
                .checkResultsValue("Student Name", FIRST_NAME + " " + LAST_NAME)
                .checkResultsValue("Student Email", USER_EMAIL)
                .checkResultsValue("Gender", GENDER)
                .checkResultsValue("Mobile", USER_NUMBER)
                .checkResultsValue("Date of Birth", DATE_DAY + " " + DATE_MONTH + "," + DATE_YEAR)
                .checkResultsValue("Subjects", SUBJECT)
                .checkResultsValue("Hobbies", HOBBIES)
                .checkResultsValue("Picture", PICTURE)
                .checkResultsValue("Address", CURRENT_ADDRESS)
                .checkResultsValue("State and City", STATE + " " + CITY);
    }
}
