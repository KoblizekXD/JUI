package org.jui.core.api.win32;

import com.sun.jna.platform.WindowUtils;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;

import static com.sun.jna.Pointer.NULL;
import static com.sun.jna.platform.win32.WinUser.*;

public class WinProc implements WinUser.WindowProc {
    @Override
    public WinDef.LRESULT callback(WinDef.HWND hwnd, int uMsg, WinDef.WPARAM wParam, WinDef.LPARAM lParam) {
        switch (uMsg)
        {
            case WM_DESTROY:
                User32.INSTANCE.PostQuitMessage(0);
                System.exit(0);
                break;
            case WM_PAINT:
                // User32.INSTANCE.InvalidateRect(hwnd, null, true);
                break;

        }
        return User32.INSTANCE.DefWindowProc(hwnd, uMsg, wParam, lParam);
    }
}
