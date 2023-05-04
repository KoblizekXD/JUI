package org.jui.core.api.win32.window.controls;

import org.jui.annotations.Win32;
import org.jui.core.Application;
import org.jui.core.api.win32.window.IControl;
import org.jui.core.api.win32.window.Window;

import java.util.ArrayList;
import java.util.List;

@Win32
public final class ControlManager {
    private final List<IControl> controls;
    private final Application<org.jui.core.api.win32.Win32> application;
    private final Window window;

    public ControlManager(Application<org.jui.core.api.win32.Win32> application, Window window) {
        this.controls = new ArrayList<>();
        this.application = application;
        this.window = window;
    }

    public Application<org.jui.core.api.win32.Win32> getApplication() {
        return application;
    }

    public Window getWindow() {
        return window;
    }

    public ControlManager put(IControl control) {
        controls.add(control);
        return this;
    }
    public ControlManager remove(IControl control) {
        controls.remove(control);
        return this;
    }
}
