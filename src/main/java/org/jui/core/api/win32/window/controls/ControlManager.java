package org.jui.core.api.win32.window.controls;

import com.sun.jna.platform.win32.Kernel32;
import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinUser;
import org.jui.annotations.Autowire;
import org.jui.annotations.Win32;
import org.jui.core.Application;
import org.jui.core.api.win32.window.IControl;
import org.jui.core.api.win32.window.Window;
import org.jui.util.exception.HandleException;
import org.jui.util.reflection.FieldAccessor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Win32
public final class ControlManager {
    private final List<IControl> controls;
    private final Application<org.jui.core.api.win32.Win32> application;
    private final Window window;

    public ControlManager(Application<org.jui.core.api.win32.Win32> application, Window window) {
        this.controls = new ArrayList<>();
        this.application = application;
        this.window = window;
    }

    public Application<org.jui.core.api.win32.Win32> getApplication() {
        return application;
    }

    public Window getWindow() {
        return window;
    }

    public ControlManager put(IControl control) {
        FieldAccessor accessor = new FieldAccessor(control.getClass(), control);

        Arrays.stream(accessor.getFieldsAnnotated(Autowire.class))
                .forEach(field -> {
                    if (FieldAccessor.find(this.getClass(), field.getName())) {
                        accessor.getFieldAndSet(field,
                                FieldAccessor.getField(this.getClass(), this, field.getName()));
                    }
                });

        control.register();
        controls.add(control);
        return this;
    }
    public IControl get() {
        return controls.get(0);
    }
    public ControlManager remove(IControl control) {
        controls.remove(control);
        return this;
    }
    public IControl filter(WinDef.HWND hwnd) {
        return controls.stream().filter(c -> c.getInternalHandle().equals(hwnd)).findFirst().orElseThrow(
                () -> new HandleException("No handle found, was the control registered?")
        );
    }
    /*
    public IControl filter(int pId) {
        return controls.stream().filter(c -> GetMenu(c.getInternalHandle())).findFirst().orElseThrow(
                () -> new HandleException("No handle found, was the control registered?")
        );
    }*/
}
