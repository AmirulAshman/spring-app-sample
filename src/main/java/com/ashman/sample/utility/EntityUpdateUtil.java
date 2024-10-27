package com.ashman.sample.utility;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class EntityUpdateUtil {

    public static void updateFieldValue(Object object, Object newValue, String field)
            throws IllegalAccessException, InvocationTargetException, NoSuchMethodException {

        StringBuilder stringBuilder = new StringBuilder(field);
        stringBuilder.setCharAt(0, Character.toUpperCase(stringBuilder.charAt(0)));
        stringBuilder.insert(0, "set");

        String setterMethod = stringBuilder.toString();

        Method method = object.getClass().getMethod(setterMethod, newValue.getClass());
        method.invoke(object, newValue);
    }
}
