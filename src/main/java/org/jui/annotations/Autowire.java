package org.jui.annotations;

import org.jui.core.api.win32.window.IControl;
import org.jui.core.api.win32.window.controls.ControlManager;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Fields annotated with Autowire will be automatically set when the control
 * is added to window. This is very useful for lazy people.
 * @see ControlManager#put(IControl)
 * @author KoblizekXD
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Autowire {
}
