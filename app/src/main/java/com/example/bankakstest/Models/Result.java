package com.example.bankakstest.Models;

import java.util.List;

public class Result {
    private int number_of_fields;
    private String screen_title;
    private String operator_name;
    private int service_id;
    List<Fields> fields;

    public int getNumber_of_fields() {
        return number_of_fields;
    }

    public void setNumber_of_fields(int number_of_fields) {
        this.number_of_fields = number_of_fields;
    }

    public String getScreen_title() {
        return screen_title;
    }

    public void setScreen_title(String screen_title) {
        this.screen_title = screen_title;
    }

    public String getOperator_name() {
        return operator_name;
    }

    public void setOperator_name(String operator_name) {
        this.operator_name = operator_name;
    }

    public int getService_id() {
        return service_id;
    }

    public void setService_id(int service_id) {
        this.service_id = service_id;
    }

    public List<Fields> getFields() {
        return fields;
    }

    public void setFields(List<Fields> fields) {
        this.fields = fields;
    }

    public Result(int number_of_fields, String screen_title, String operator_name, int service_id, List<Fields> fields) {
        this.number_of_fields = number_of_fields;
        this.screen_title = screen_title;
        this.operator_name = operator_name;
        this.service_id = service_id;
        this.fields = fields;
    }
}
