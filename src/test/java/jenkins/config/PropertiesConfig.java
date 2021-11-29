package jenkins.config;

import org.aeonbits.owner.Config;

@Config.Sources({"classpath:config/credentials.properties"})
public interface PropertiesConfig extends Config {
    //String url();
    String login();
    String password();
}
