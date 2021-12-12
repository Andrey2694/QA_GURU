package owner.config;

import org.aeonbits.owner.Config;

@Config.Sources({
        "file:/tmp/auth.properties",
        "classpath:config/configApi.properties"
})
public interface ApiFromFile extends Config {
    @Key("baseUrl")
    String getBaseUrl();

    @Key("token")
    String getToken();
}
