package com.apavlidi.api.beans;

import javax.ws.rs.QueryParam;

public class NoteFilterBean {

    private @QueryParam("text")
    String text;
    private @QueryParam("limit")
    int limit;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }
}
