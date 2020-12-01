package com.example.bankakstest.Models;

import java.util.List;

public class UI_Type {
    private String type;
    private List<Values> values;

    public UI_Type(String type, List<Values> values) {
        this.type = type;
        this.values = values;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Values> getValues() {
        return values;
    }

    public void setValues(List<Values> values) {
        this.values = values;
    }
}
