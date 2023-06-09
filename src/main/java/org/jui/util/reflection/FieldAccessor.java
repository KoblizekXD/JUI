package org.jui.util.reflection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.Arrays;

public final class FieldAccessor {
    private static final Logger LOGGER = LogManager.getLogger();
    private final Class<?> type;
    private final Object instance;

    public FieldAccessor(Class<?> type, Object instance) {
        this.type = type;
        this.instance = instance;
    }
    public Field[] getFields() {
        return type.getDeclaredFields();
    }
    public Field[] getFieldsAnnotated(Class<? extends Annotation> annotation) {
        return Arrays.stream(type.getDeclaredFields()).filter(f -> f.isAnnotationPresent(annotation))
                .toArray(Field[]::new);
    }
    public void getFieldAndSet(Field field, Object data) {
        if (field.trySetAccessible()) {
            try {
                field.set(instance, data);
            } catch (IllegalAccessException e) {
                LOGGER.error("Access to field denied");
            }
        } else {
            LOGGER.error("Field accessibility set failed");
        }
    }
    public void getFieldAndSet(String fieldName, Object data) {
        try {
            Field field = type.getDeclaredField(fieldName);
            if (field.trySetAccessible()) {
                field.set(instance, data);
            } else {
                LOGGER.error("Field accessibility set failed");
            }
        } catch (NoSuchFieldException e) {
            LOGGER.error("No such field found: {}", e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.error("Access to field denied");
        }
    }
    public void getUndeclaredFieldAndSet(String fieldName, Object data) {
        try {
            Field field = type.getField(fieldName);
            if (field.trySetAccessible()) {
                field.set(instance, data);
            } else {
                LOGGER.error("Field accessibility set failed");
            }
        } catch (NoSuchFieldException e) {
            LOGGER.error("No such field found: {}", e.getMessage());
        } catch (IllegalAccessException e) {
            LOGGER.error("Access to field denied");
        }
    }
    public static boolean find(Class<?> type, String fieldName) {
        try {
            type.getDeclaredField(fieldName);
            return true;
        } catch (NoSuchFieldException e) {
            return false;
        }
    }
    public Object getField(String fieldName) {
        try {
            Field f = type.getDeclaredField(fieldName);
            if (f.trySetAccessible()) {
                return f.get(instance);
            } else {
                LOGGER.error("Field accessibility set failed");
            }
        } catch (IllegalAccessException e) {
            LOGGER.error("Access to field denied");
        } catch (NoSuchFieldException e) {
            LOGGER.error("No such field found: {}", e.getMessage());
        }
        return null;
    }
    public static Object getField(Class<?> type, Object typeInstance, String fieldName) {
        return new FieldAccessor(type, typeInstance).getField(fieldName);
    }

    public static void getFieldAndSet(Class<?> type, Object instance, String fieldName, Object data) {
        new FieldAccessor(type, instance).getFieldAndSet(fieldName, data);
    }
    public static void getUndeclaredFieldAndSet(Class<?> type, Object instance, String fieldName, Object data) {
        new FieldAccessor(type, instance).getUndeclaredFieldAndSet(fieldName, data);
    }
}
