package global;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class ConfigReader {
    private static final String CONFIG_FILE = "src/main/resources/configs/application.json";

    public static Config getConfigForEnv(String env) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<Config> configs = mapper.readValue(new File(CONFIG_FILE), new TypeReference<List<Config>>() {});

        return configs.stream()
                .filter(config -> config.getEnv().equalsIgnoreCase(env))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Environment not found: " + env));
    }
}
