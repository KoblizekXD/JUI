package org.jui.util.examples;

import org.jui.core.Application;
import org.jui.core.api.Configurator;
import org.jui.core.api.win32.Win32;
import org.jui.core.api.win32.window.Window;
import org.jui.core.api.win32.window.controls.Button;
import org.jui.core.init.BootstrapLauncher;
import org.jui.util.Sizing;

public class TestMain extends Application<Win32> {
    public static void main(String[] args) {
        BootstrapLauncher.inCustomMain(new TestMain());
    }

    @Override
    public void onLaunch(Configurator config) {
        config.registerWindow(new TestWindow());
    }
    public static class TestWindow extends Window {
        @Override
        public void onWindowShow() {
            Button button = new Button();
            button.onClick((b) -> {
                System.out.println("e");
            });
            this.getControls().put(button);
            this.setBounds(null);
        }
    }
}
