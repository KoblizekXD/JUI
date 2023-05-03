package org.jui.core.api;

public final class Configurator<T extends API> {
    private String defaultName = "MyApplication";

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getDefaultName() {
        return defaultName;
    }
}
