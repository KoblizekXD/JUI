package org.jui.core.api.win32.window;

import com.sun.jna.platform.win32.WinDef;
import org.jui.annotations.Win32;

@Win32
public abstract class IControl {
    protected WinDef.HWND handle;
    public abstract void register();
    public WinDef.HWND getInternalHandle() {
        return handle;
    }
}
