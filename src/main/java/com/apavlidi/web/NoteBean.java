package com.apavlidi.web;


import com.apavlidi.domain.Note;

import java.util.List;

public class NoteBean {

    private List<Note> notes;

    private Note selectedNote;

    public Note getSelectedNote() {
        return selectedNote;
    }

    public void setSelectedNote(Note selectedNote) {
        this.selectedNote = selectedNote;
    }

    public List<Note> getNotes() {
        return notes;
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
    }
}
