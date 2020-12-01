package com.example.bankakstest.Models;

public class Type {
    private String data_type;
    private Boolean is_array;
    private String array_name;

    public String getData_type() {
        return data_type;
    }

    public void setData_type(String data_type) {
        this.data_type = data_type;
    }

    public Boolean getIs_array() {
        return is_array;
    }

    public void setIs_array(Boolean is_array) {
        this.is_array = is_array;
    }

    public String getArray_name() {
        return array_name;
    }

    public void setArray_name(String array_name) {
        this.array_name = array_name;
    }

    public Type(String data_type, Boolean is_array, String array_name) {
        this.data_type = data_type;
        this.is_array = is_array;
        this.array_name = array_name;
    }
}
