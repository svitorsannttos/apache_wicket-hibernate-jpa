package com.github.svitorsannttos.wicket.model.enums;

public enum GenderEnum {

    MASCULINE("Masculine"), FEMININE("Feminine");

    private final String label;

    private GenderEnum(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

}
