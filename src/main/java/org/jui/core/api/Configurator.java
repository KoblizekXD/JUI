package org.jui.core.api;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import org.jui.core.api.win32.WinProc;
import org.jui.core.api.win32.window.Window;
import org.jui.util.reflection.FieldAccessor;

import java.util.ArrayList;
import java.util.List;

import static com.sun.jna.platform.win32.WinUser.WS_OVERLAPPEDWINDOW;

public final class Configurator {
    private String defaultName = "MyApplication";

    private final WinDef.HINSTANCE hInstance;
    private WinUser.WNDCLASSEX wndClass;
    private final WinUser.WindowProc wndProc;
    private List<Window> handles;

    public Configurator() {
        handles = new ArrayList<>();
        wndProc = new WinProc();
        hInstance = Kernel32.INSTANCE.GetModuleHandle("");
    }

    public void setName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getName() {
        return defaultName;
    }
    public void registerWindow(Window window) {
        handles.add(window);
    }
}
