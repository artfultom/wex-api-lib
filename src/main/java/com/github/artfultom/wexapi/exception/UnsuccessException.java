package com.github.artfultom.wexapi.exception;

public class UnsuccessException extends RuntimeException {

    private int success;

    private String error;

    public UnsuccessException(int success, String error) {
        super(error);

        this.success = success;
        this.error = error;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
