package global;

import java.util.Map;

public class Config {
    private String env;
    private Map<String, Object> ui;
    private Map<String, Object> api;

    // Getters and Setters
    public String getEnv() {
        return env;
    }

    public void setEnv(String env) {
        this.env = env;
    }

    public Map<String, Object> getUi() {
        return ui;
    }

    public void setUi(Map<String, Object> ui) {
        this.ui = ui;
    }

    public Map<String, Object> getApi() {
        return api;
    }

    public void setApi(Map<String, Object> api) {
        this.api = api;
    }
}

