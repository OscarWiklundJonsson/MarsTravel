package com.uu.grupp3.marstravel.services;

import java.util.HashMap;
import java.util.Map;

public class DropdownState {
    private static DropdownState instance;
    private final Map<String, Object> dropdownStates;

    private DropdownState() {
        dropdownStates = new HashMap<>();
    }

    public static DropdownState getInstance() {
        if (instance == null) {
            instance = new DropdownState();
        }
        return instance;
    }

    public void setDropdownState(String dropdownId, Object selectedItem) {
        dropdownStates.put(dropdownId, selectedItem);
    }

    public Object getDropdownState(String dropdownId) {
        return dropdownStates.get(dropdownId);
    }
}