package com.uu.grupp3.marstravel.services;

import java.util.HashMap;
import java.util.Map;

public class RadioButtonState {
    private static RadioButtonState instance;
    private final Map<String, Boolean> buttonStates;

    private RadioButtonState() {
        buttonStates = new HashMap<>();
    }

    public static RadioButtonState getInstance() {
        if (instance == null) {
            instance = new RadioButtonState();
        }
        return instance;
    }

    public void setButtonState(String buttonId, boolean isSelected) {
        buttonStates.put(buttonId, isSelected);
    }

    public boolean getButtonState(String buttonId) {
        return buttonStates.getOrDefault(buttonId, false);
    }
}