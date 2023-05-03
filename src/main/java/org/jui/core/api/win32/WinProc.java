package org.jui.core.api.win32;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

public class WinProc implements WinUser.WindowProc {
    @Override
    public WinDef.LRESULT callback(WinDef.HWND hwnd, int uMsg, WinDef.WPARAM wParam, WinDef.LPARAM lParam) {
        return User32.INSTANCE.DefWindowProc(hwnd, uMsg, wParam, lParam);
    }
}
