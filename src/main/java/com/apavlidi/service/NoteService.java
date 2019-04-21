package com.apavlidi.service;

import com.apavlidi.domain.Note;
import com.apavlidi.repository.NoteRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;
import java.util.Optional;

@Stateless
public class NoteService {

    @Inject
    private NoteRepository noteRepository;

    public List<Note> findAllNotes() {
        return noteRepository.findAllNotes();
    }

    public Optional<Note> findNoteById(Long noteId) {
        return noteRepository.findNoteById(noteId);
    }

    public void updateNoteById(Long noteId, Note newNote) {
        Optional<Note> noteById = noteRepository.findNoteById(noteId);
        if (noteById.isPresent()) {
            newNote.setNoteId(noteById.get().getNoteId());
            persist(newNote);
        }
    }

    public void deleteNoteById(Long noteId) {
        Optional<Note> noteById = noteRepository.findNoteById(noteId);
        noteById.ifPresent(note -> noteRepository.remove(note));
    }

    public void persist(Note note) {
        noteRepository.persist(note);
    }


}
