package org.jui.core.api.win32;

import com.sun.jna.NativeMapped;
import com.sun.jna.Pointer;
import com.sun.jna.platform.WindowUtils;
import com.sun.jna.platform.win32.*;
import org.apache.logging.log4j.LogManager;
import org.jui.core.Application;
import org.jui.core.api.win32.window.IControl;
import org.jui.core.api.win32.window.Window;
import org.jui.core.api.win32.window.controls.Button;
import org.jui.util.event.Handlers;
import org.jui.util.event.events.ButtonClickEvent;
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
                IControl h = app.getWindowByHandle(hwnd)
                        .getControls().filter(lParam.longValue());
                Handlers<ButtonClickEvent> events = (Handlers<ButtonClickEvent>) FieldAccessor.getField(h.getClass(), h, "clickEventHandlers");
                if (events == null ) break;
                events.invokeAll(new ButtonClickEvent());
                break;

        }
        return User32.INSTANCE.DefWindowProc(hwnd, uMsg, wParam, lParam);
    }
}
