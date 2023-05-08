package org.jui.core.api.win32;

import com.sun.jna.NativeMapped;
import com.sun.jna.Pointer;
import com.sun.jna.platform.WindowUtils;
import com.sun.jna.platform.win32.*;
import org.apache.logging.log4j.LogManager;
import org.jui.core.Application;
import org.jui.core.api.win32.window.Window;
import org.jui.util.reflection.FieldAccessor;

import java.util.List;

import static com.sun.jna.Pointer.NULL;
import static com.sun.jna.platform.win32.WinUser.*;

public class WinProc implements WinUser.WindowProc {
    private final Application<Win32> app;

    public WinProc(Application<Win32> app) {
        this.app = app;
    }

    @Override
    public WinDef.LRESULT callback(WinDef.HWND hwnd, int uMsg, WinDef.WPARAM wParam, WinDef.LPARAM lParam) {
        switch (uMsg)
        {
            case WM_DESTROY:
                User32.INSTANCE.PostQuitMessage(0);
                System.exit(0);
                break;
            case WM_PAINT:
                break;
            case 0x0111: // Button Click
                System.out.println(loword(lParam).intValue());
                HWND h = app.getWindowByHandle(hwnd)
                        .getControls().get()
                        .getInternalHandle();
                LogManager.getLogger().info("H: {}", hiword(lParam));
                break;

        }
        return User32.INSTANCE.DefWindowProc(hwnd, uMsg, wParam, lParam);
    }
    public WORD loword(LPARAM lparam) {
        return new WORD(lparam.intValue() & 0xffff);
    }
    public WORD hiword(LPARAM lparam) {
        return new WORD((lparam.intValue() >> 16) & 0xffff);
    }
}
