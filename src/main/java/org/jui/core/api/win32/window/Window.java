package org.jui.core.api.win32.window;

import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import org.jui.util.Sizing;
import org.jui.util.Dimension;

public abstract class Window {
    private WinDef.HWND hwnd;

    public final void show() {
        User32.INSTANCE.ShowWindow(hwnd, 1);
        this.onWindowShow();
    }
    public void onWindowShow() {}
    public final WinDef.HWND getWindowHandle() {
        return hwnd;
    }
    public final void setBounds(Sizing sizing) {
        User32.INSTANCE.MoveWindow(
                hwnd,
                getLocation().getX(),
                getLocation().getY(),
                sizing.getWidth(),
                sizing.getHeight(),
                true
        );
    }
    public final Dimension getLocation() {
        return new Dimension(getRect().toRectangle().x, getRect().toRectangle().y);
    }
    public final Sizing getWindowBounds() {
        WinDef.RECT rect = new WinDef.RECT();

        User32.INSTANCE.GetWindowRect(hwnd, rect);

        return new Sizing(rect.right - rect.left, rect.bottom - rect.top);
    }
    public final WinDef.RECT getRect() {
        WinDef.RECT rect = new WinDef.RECT();

        User32.INSTANCE.GetWindowRect(hwnd, rect);
        return rect;
    }
}
