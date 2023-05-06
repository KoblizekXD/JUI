package org.jui.core.api.win32.window.controls;

import com.sun.jna.platform.win32.*;
import org.jui.annotations.Autowire;
import org.jui.core.api.win32.window.IControl;
import org.jui.core.api.win32.window.Window;

import static com.sun.jna.platform.win32.WinUser.*;

public class Button implements IControl {
    @Autowire
    private Window window;
    private WinDef.HWND buttonHandle;

    public Button() {}

    @Override
    public void register() {
        buttonHandle = User32.INSTANCE.CreateWindowEx(0,  // Predefined class; Unicode assumed
                "BUTTON",
                "OK",      // Button text
                WS_TABSTOP | WS_VISIBLE | WS_CHILD | BS_DEFPUSHBUTTON,  // Styles
                10,         // x position
                10,         // y position
                100,        // Button width
                100,        // Button height
                window.getWindowHandle(),     // Parent window
                null,       // No menu.
                Kernel32.INSTANCE.GetModuleHandle(""),
                null);
    }
}
