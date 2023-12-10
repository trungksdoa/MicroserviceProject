package com.module.userservice.common;

public class Utility {
    private static Utility instance;

    private Utility() {}

    public static Utility getInstance() {
        if (instance == null) {
            instance = new Utility();
        }
        return instance;
    }

    public <T> T getUpdatedValue(T newValue, T defaultValue) {
        return newValue != null ? newValue : defaultValue;
    }

    public int getUpdatedNumberValue(int newValue, int defaultValue) {
        return newValue != 0 ? newValue : defaultValue;
    }
}