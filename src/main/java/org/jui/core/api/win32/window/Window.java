package org.jui.core.api.win32.window;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;

public abstract class Window {
    private WinDef.HWND hwnd;

    public final void show() {
        User32.INSTANCE.ShowWindow(hwnd, 1);
    }
    public final WinDef.HWND getWindowHandle() {
        return hwnd;
    }
}
