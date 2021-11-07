import baseTest.BaseTest;
import org.junit.jupiter.api.Test;

public class RegistrationFormPageObjectsTest extends BaseTest {
    private final String FIRST_NAME = "Andrey";
    private final String LAST_NAME = "Zhmaka";
    private final String USER_EMAIL = "AndreyZhmaka@mail.com";
    private final String GENDER = "Male";
    private final String DATE_DAY = "30";
    private final String DATE_MONTH = "July";
    private final String DATE_YEAR = "2008";
    private final String USER_NUMBER = "0123456789";
    private final String SUBJECT = "English";
    private final String HOBBIES = "Sports";
    private final String PICTURE = "pic.png";
    private final String CURRENT_ADDRESS = "ul.Andrey 28/33";
    private final String STATE = "Rajasthan";
    private final String CITY = "Jaiselmer";

    @Test
    void fillFormTest() {
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
