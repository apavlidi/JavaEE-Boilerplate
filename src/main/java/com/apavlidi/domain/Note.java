package com.apavlidi.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Note {

    @Id
    @GeneratedValue
    private Long noteId;

    @Column
    private String text;

    public Note() {
    }

    public Note(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
