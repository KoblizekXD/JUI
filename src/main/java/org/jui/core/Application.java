package org.jui.core;

import org.jui.core.api.API;

public abstract class Application<T extends API> {
    public abstract void onLaunch();
}
