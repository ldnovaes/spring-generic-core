package br.com.nexus.core.utils;

import java.lang.reflect.Field;

public class ReflectionUtil {
    
    public static Field getFieldByName(String name, Class<?> clazz) {
        try {
            Field field = clazz.getDeclaredField(name);
            return field;
        } catch (NoSuchFieldException e) {
            return null;
        }
    }

    public static boolean isFieldTypeString(String fieldName, Class<?> clazz) {
        Field field = getFieldByName(fieldName, clazz);

        if (field == null) {
            return false;
        }

        return isFieldTypeString(field);
    }

    public static boolean isFieldTypeString(Field field) {
        Class<?> fieldType = field.getType();
        return fieldType == String.class;
    }
}
