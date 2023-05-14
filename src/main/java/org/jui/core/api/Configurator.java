package org.jui.core.api;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import org.jui.core.Application;
import org.jui.core.api.win32.Win32;
import org.jui.core.api.win32.WinProc;
import org.jui.core.api.win32.window.Window;
import org.jui.core.api.win32.window.controls.ControlManager;
import org.jui.util.exception.HandleException;
import org.jui.util.reflection.FieldAccessor;

import java.util.ArrayList;
import java.util.List;

public final class Configurator {
    private final Application<Win32> app;
    private String defaultName = "MyApplication";

    private final WinDef.HINSTANCE hInstance;
    private WinUser.WNDCLASSEX wndClass;
    private final WinUser.WindowProc wndProc;
    private List<Window> windows;

    public Configurator(Application<Win32> app) {
        this.app = app;
        windows = new ArrayList<>();
        wndProc = new WinProc(app);
        hInstance = Kernel32.INSTANCE.GetModuleHandle("");
    }
    public Window getWindowByHandle(WinDef.HWND hwnd) {
        return windows.stream().filter(w -> w.getWindowHandle().equals(hwnd))
                .findFirst().orElseThrow(() -> new HandleException("No valid window found"));
    }

    public void setName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getName() {
        return defaultName;
    }
    public void registerWindow(Window window) {
        FieldAccessor.getFieldAndSet(window.getClass().getSuperclass(), window, "app", app);
        windows.add(window);
    }
}
