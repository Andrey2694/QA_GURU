package owner;

import org.aeonbits.owner.ConfigFactory;
import org.junit.jupiter.api.Test;
import owner.config.SelenoidConfig;
import owner.config.Web;
import owner.config.WebLocal;
import owner.config.WebRemote;

import static org.assertj.core.api.Assertions.assertThat;

public class OwnerTests {
    @Test
    public void webTest() {
        Web config = ConfigFactory.create(Web.class, System.getProperties());
        assertThat(String.valueOf(config.getBrowserName())).isEqualTo("FIREFOX");
        assertThat(config.getBrowserVersion()).isEqualTo("93.0");
    }

    @Test
    public void webLocalTest() {
        System.setProperty("environment", "configLocalWeb");

        WebLocal config = ConfigFactory.create(WebLocal.class, System.getProperties());
        assertThat(String.valueOf(config.getBrowserName())).isEqualTo("CHROME");
        assertThat(config.getBrowserVersion()).isEqualTo("95.0");
    }
    @Test
    public void webRemoteTest() {
        System.setProperty("environment", "configRemoteWeb");
        SelenoidConfig.selenoidSettings();

        WebRemote config = ConfigFactory.create(WebRemote.class, System.getProperties());
        assertThat(String.valueOf(config.getBrowserName())).isEqualTo("OPERA");
        assertThat(config.getBrowserVersion()).isEqualTo("96.0");
        assertThat(config.getRemoteUrl()).isEqualTo("https://user1:1234@selenoid.autotests.cloud/wd/hub/");
    }
}