package com.module.course.common;

import java.time.Year;

public class Utils {
    private static final Utils INSTANCE = new Utils();

    public static Utils getInstance() {
        return INSTANCE;
    }
    public <T> T getUpdateValue(T newValue, T oldValue) {
        return newValue != null ? newValue : oldValue;
    }

    public String generateId(String value) {
        int currentYear = Year.now().getValue();
        return value + currentYear;
    }
}
