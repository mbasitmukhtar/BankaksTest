package com.example.bankakstest.Models;

public class TheResponse {
    private Boolean status;
    private String message;
    private Result result;

    public TheResponse(Boolean status, String message, Result result) {
        this.status = status;
        this.message = message;
        this.result = result;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Result getResult() {
        return result;
    }

    public void setResult(Result result) {
        this.result = result;
    }
}
