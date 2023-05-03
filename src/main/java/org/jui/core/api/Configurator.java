package org.jui.core.api;

public final class Configurator<T extends API> {
    private String defaultName = "MyApplication";

    public void setName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getName() {
        return defaultName;
    }
}
