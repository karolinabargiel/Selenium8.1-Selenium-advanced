package configuration.reader;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import configuration.models.Config;
import java.io.File;
import java.io.IOException;

public class YamlReader {
    public static Config config;


    public YamlReader() {
        ObjectMapper objectMapper = new ObjectMapper(new YAMLFactory());
        try {
            config = objectMapper.readValue(new File("src/main/resources/config.yaml"), Config.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public Config getConfig() {
        return config;
    }
}
