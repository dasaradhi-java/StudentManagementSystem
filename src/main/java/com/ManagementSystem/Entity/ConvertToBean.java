package com.ManagementSystem.Entity;

import java.lang.reflect.Field;
import java.util.Map;

public class ConvertToBean{
public static <T> T getData(Class<T> beanClass, Map<String, Object> row) {
        try {
            T bean = beanClass.getDeclaredConstructor().newInstance();

            for (Map.Entry<String, Object> entry : row.entrySet()) {
                String columnName = entry.getKey();
                Object columnValue = entry.getValue();

                // Convert column name to property name
                String fieldName = convertToCamelCase(columnName);

                try {
                    Field field = beanClass.getDeclaredField(fieldName);
                    field.setAccessible(true);
                    field.set(bean, columnValue);
                } catch (NoSuchFieldException ignored) {
                    // Ignore fields that don't exist in the bean
                }
            }
            return bean;
        } catch (Exception e) {
            throw new RuntimeException("Failed to convert map to bean", e);
        }
    }

    private static String convertToCamelCase(String columnName) {
        String[] parts = columnName.split("_");
        StringBuilder camelCaseName = new StringBuilder(parts[0]);
        for (int i = 1; i < parts.length; i++) {
            camelCaseName.append(parts[i].substring(0, 1).toUpperCase())
                         .append(parts[i].substring(1));
        }
        return camelCaseName.toString();
    }
}