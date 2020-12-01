package com.example.bankakstest.Models;

public class Fields {
    private String name;
    private String placeholder;
    private String regex;
    Type type;
    Boolean is_mandatory;
    private String hint_text;
    UI_Type ui_type;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Boolean getIs_mandatory() {
        return is_mandatory;
    }

    public void setIs_mandatory(Boolean is_mandatory) {
        this.is_mandatory = is_mandatory;
    }

    public String getHint_text() {
        return hint_text;
    }

    public void setHint_text(String hint_text) {
        this.hint_text = hint_text;
    }

    public UI_Type getUi_type() {
        return ui_type;
    }

    public void setUi_type(UI_Type ui_type) {
        this.ui_type = ui_type;
    }

    public Fields(String name, String placeholder, String regex, Type type, Boolean is_mandatory, String hint_text, UI_Type ui_type) {
        this.name = name;
        this.placeholder = placeholder;
        this.regex = regex;
        this.type = type;
        this.is_mandatory = is_mandatory;
        this.hint_text = hint_text;
        this.ui_type = ui_type;
    }
}
