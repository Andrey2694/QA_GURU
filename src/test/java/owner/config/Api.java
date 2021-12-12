package owner.config;

import org.aeonbits.owner.Config;

public interface Api extends Config {
    @Key("baseUrl")
    @DefaultValue("SomeURL")
    String getBaseUrl();

    @Key("token")
    @DefaultValue("SomeToken")
    String getToken();
}
