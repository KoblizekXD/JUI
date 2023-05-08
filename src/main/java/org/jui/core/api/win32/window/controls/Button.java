package org.jui.core.api.win32.window.controls;

import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import org.jui.annotations.Autowire;
import org.jui.core.api.win32.window.IControl;
import org.jui.core.api.win32.window.Window;
import org.jui.util.event.Handlers;
import org.jui.util.event.events.ButtonClickEvent;

import java.util.function.Consumer;

import static com.sun.jna.platform.win32.WinUser.*;

public class Button extends IControl {
    @Autowire
    private Window window;
    private Handlers<ButtonClickEvent> clickEventHandlers;

    public Button() {}

    @Override
    public void register() {
        this.handle = User32.INSTANCE.CreateWindowEx(0,  // Predefined class; Unicode assumed
                "BUTTON",
                "OK",      // Button text
                WS_TABSTOP | WS_VISIBLE | WS_CHILD | BS_PUSHBUTTON,  // Styles
                10,         // x position
                10,         // y position
                100,        // Button width
                100,        // Button height
                window.getWindowHandle(),     // Parent window
                new HMENU(Pointer.createConstant(100)),       // No menu.
                Kernel32.INSTANCE.GetModuleHandle(""),
                null);
    }
    public void onClick(Consumer<ButtonClickEvent> apply) {
        clickEventHandlers.add(apply);
    }
}
