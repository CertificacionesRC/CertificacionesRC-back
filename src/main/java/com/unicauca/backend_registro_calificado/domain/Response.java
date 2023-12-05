package com.unicauca.backend_registro_calificado.domain;

public class Response<T> {

    private int status;
    private String userMessage;
    private String developerMessage;
    private String errorCode;
    private String moreInfo;
    private T data;
    public int getStatus() {
        return status;
    }
    public void setStatus(int status) {
        this.status = status;
    }
    public String getUserMessage() {
        return userMessage;
    }
    public void setUserMessage(String userMessage) {
        this.userMessage = userMessage;
    }
    public String getDeveloperMessage() {
        return developerMessage;
    }
    public void setDeveloperMessage(String developerMessage) {
        this.developerMessage = developerMessage;
    }
    public String getErrorCode() {
        return errorCode;
    }
    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }
    public String getMoreInfo() {
        return moreInfo;
    }
    public void setMoreInfo(String moreInfo) {
        this.moreInfo = moreInfo;
    }
    public T getData() {
        return data;
    }
    public void setData(T data) {
        this.data = data;
    }
    @Override
    public String toString() {
        return "Response [state=" + status + ", userMessage=" + userMessage + ", developerMessage=" + developerMessage + ", moreInfo="
                + moreInfo + ", data=" + data + "]";
    }
}
