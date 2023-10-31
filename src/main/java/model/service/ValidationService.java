package model.service;

import java.lang.reflect.Field;

public class ValidationService {

    public static <T> boolean validateEntity(T entity) throws IllegalAccessException {
        for (Field field : entity.getClass().getDeclaredFields()) {
            field.setAccessible(true);
            Object value = field.get(entity);
            if (value == null || value.toString().trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
}

