package org.jui.core;

import com.sun.jna.platform.win32.WinDef;
import org.jui.core.api.API;
import org.jui.core.api.Configurator;
import org.jui.core.api.win32.Win32;
import org.jui.core.api.win32.window.Window;

public abstract class Application<T extends API> {
    private final Configurator config = new Configurator((Application<Win32>) this);
    public abstract void onLaunch(Configurator config);

    public Configurator getConfigurator() {
        return config;
    }
    public Window getWindowByHandle(WinDef.HWND hwnd) {
        return config.getWindowByHandle(hwnd);
    }
}
