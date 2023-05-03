package org.jui.core.init;

import com.sun.jna.Platform;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jui.core.Application;
import org.jui.core.api.win32.WinProc;
import org.jui.util.reflection.FieldAccessor;

import java.util.List;

import static com.sun.jna.Native.getLastError;
import static com.sun.jna.platform.win32.WinUser.WS_OVERLAPPEDWINDOW;

public final class BootstrapLauncher {
    private static final Logger LOGGER = LogManager.getLogger();
    private BootstrapLauncher() {}

    public static void main(String[] args) {

    }
    public static void inCustomMain(Application<?> app) {
        checkPlatform();
        app.onLaunch();
        FieldAccessor accessor = new FieldAccessor(app.getConfigurator().getClass(), app.getConfigurator());

        WinDef.HMODULE hInstance = Kernel32.INSTANCE.GetModuleHandle("");
        WinUser.WNDCLASSEX wClass = new WinUser.WNDCLASSEX();
        wClass.hInstance = hInstance;
        wClass.lpfnWndProc = new WinProc();
        wClass.lpszClassName = app.getConfigurator().getName();
        accessor.getFieldAndSet("wndClass", wClass);
        User32.INSTANCE.RegisterClassEx(wClass);
        getLastError();
        WinDef.HWND hWnd = User32.INSTANCE
                .CreateWindowEx(
                        User32.WS_EX_TOPMOST,
                        app.getConfigurator().getName(),
                        "Test App",
                        WS_OVERLAPPEDWINDOW, 0, 0, 400, 400,
                        null, // WM_DEVICECHANGE contradicts parent=WinUser.HWND_MESSAGE
                        null, hInstance, null);
        ((List<WinDef.HWND>)accessor.getField("handles")).add(hWnd);
        getLastError();
        WinUser.MSG msg = new WinUser.MSG();
        User32.INSTANCE.ShowWindow(hWnd, 1);
        while (User32.INSTANCE.GetMessage(msg, hWnd, 0, 0) != 0) {
            User32.INSTANCE.TranslateMessage(msg);
            User32.INSTANCE.DispatchMessage(msg);
        }
    }
    public static void checkPlatform() {
        if (!Platform.isWindows()) {
            LOGGER.fatal("non Windows OS detected, application cannot continue");
        }
    }
}
