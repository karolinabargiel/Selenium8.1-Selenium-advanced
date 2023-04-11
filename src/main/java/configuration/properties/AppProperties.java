package configuration.properties;

import configuration.reader.YamlReader;
import lombok.extern.slf4j.Slf4j;
import java.util.List;
import java.util.Map;
import configuration.models.Browser;
import configuration.models.Environment;

@Slf4j
public class AppProperties {
    YamlReader yamlReader = new YamlReader();

    private AppProperties() {
        setBrowserProperties();
        setEnvironmentProperties();
    }

    public static AppProperties getInstance() {
        return AppProperties.AppPropertiesSingleton.INSTANCE;
    }

    private void setEnvironmentProperties() {
        List<Environment> listOfEnvironments = yamlReader.getConfig().getEnvironments();
        for (Environment environment : listOfEnvironments) {
            if (isDefaultEnvironment(environment)) {
                Map<String, Object> environmentProperties = environment.getProperties();
                for (Map.Entry<String, Object> entry : environmentProperties.entrySet()) {
                    System.setProperty(entry.getKey(), entry.getValue().toString());
                    log.info("Load env properties: {} = {}", entry.getKey(), entry.getValue().toString());
                }
                break;
            }
        }
    }

    private boolean isDefaultEnvironment(Environment environment) {
        return environment.getProperties().get("envName").toString().equals(yamlReader.getConfig().getDefaultEnvironment());
    }

    private void setBrowserProperties() {
        Browser browser = yamlReader.getConfig().getBrowser();
        Map<String, Object> browserProperties = browser.getProperties();
        for (Map.Entry<String, Object> entry : browserProperties.entrySet()) {
            System.setProperty(entry.getKey(), entry.getValue().toString());
            log.info("Load browser properties: {} = {}", entry.getKey(), entry.getValue().toString());
        }
    }

    private static class AppPropertiesSingleton {
        private static final AppProperties INSTANCE = new AppProperties();
    }
}
