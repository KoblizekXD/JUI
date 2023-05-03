package org.jui.util.examples;

import org.jui.core.Application;
import org.jui.core.api.win32.Win32;
import org.jui.core.init.BootstrapLauncher;

public class TestMain extends Application<Win32> {
    public static void main(String[] args) {
        BootstrapLauncher.inCustomMain(new TestMain());
    }

    @Override
    public void onLaunch() {

    }
}
