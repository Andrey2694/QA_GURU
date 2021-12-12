package owner.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:/tmp/auth.properties",
        "classpath:config/${environment}.properties"})
public interface WebLocal extends Config {

    @Key("browser")
    Browser getBrowserName();

    @Key("browserVersion")
    String getBrowserVersion();

    @Key("remoteUrl")
    String getRemoteUrl();
}
