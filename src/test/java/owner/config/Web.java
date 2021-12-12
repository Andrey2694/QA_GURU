package owner.config;

import org.aeonbits.owner.Config;

public interface Web extends Config {

    @Key("browser")
    @DefaultValue("FIREFOX")
    Browser getBrowserName();

    @Key("browserVersion")
    @DefaultValue("93.0")
    String getBrowserVersion();

    @Key("remoteUrl")
    String getRemoteUrl();
}
