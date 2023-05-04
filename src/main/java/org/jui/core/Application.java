package org.jui.core;

import org.jui.core.api.API;
import org.jui.core.api.Configurator;
import org.jui.core.api.win32.Win32;

public abstract class Application<T extends API> {
    private final Configurator config = new Configurator((Application<Win32>) this);
    public abstract void onLaunch(Configurator config);

    public Configurator getConfigurator() {
        return config;
    }
}
