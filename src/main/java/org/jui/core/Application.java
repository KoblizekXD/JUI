package org.jui.core;

import org.jui.core.api.API;
import org.jui.core.api.Configurator;

public abstract class Application<T extends API> {
    private Configurator<T> config = new Configurator<>();
    public abstract void onLaunch();

    public Configurator<T> getConfigurator() {
        return config;
    }
}
