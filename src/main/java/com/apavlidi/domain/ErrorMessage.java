package com.apavlidi.domain;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ErrorMessage {

    private String errorMsg;

    private int errorCode;

    private String documentation;

    public ErrorMessage() {
    }

    public ErrorMessage(String errorMsg, int errorCode, String documentation) {
        this.errorMsg = errorMsg;
        this.errorCode = errorCode;
        this.documentation = documentation;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    public int getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(int errorCode) {
        this.errorCode = errorCode;
    }

    public String getDocumentation() {
        return documentation;
    }

    public void setDocumentation(String documentation) {
        this.documentation = documentation;
    }
}
