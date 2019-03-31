package com.apavlidi.web;


import com.apavlidi.domain.Note;
import com.apavlidi.domain.NoteD;

import javax.faces.bean.ViewScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@ViewScoped
@Named(value="bean")
public class NoteBean implements Serializable {

    private static final long serialVersionUID = 1715935052239888761L;

    private String text;

    private List<Note> notes;

    private NoteD selectedNote = new NoteD();

    public void createNew() {
        selectedNote = new NoteD();
    }

    public NoteD getSelectedNote() {
        return selectedNote;
    }

    public void setSelectedNote(NoteD selectedNote) {
        this.selectedNote = selectedNote;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
