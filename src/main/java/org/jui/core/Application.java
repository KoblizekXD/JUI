package org.jui.core;

import org.jui.core.api.API;
import org.jui.core.api.Configurator;

public abstract class Application<T extends API> {
    private Configurator config = new Configurator();
    public abstract void onLaunch();

    public Configurator getConfigurator() {
        return config;
    }
}
