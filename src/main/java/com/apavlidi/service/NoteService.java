package com.apavlidi.service;

import com.apavlidi.api.exceptions.DataNotFoundException;
import com.apavlidi.domain.Note;
import com.apavlidi.repository.NoteRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;

@Stateless
public class NoteService {

    @Inject
    private NoteRepository noteRepository;

    @Inject
    private EntityManager entityManager;

    public List<Note> findAllNotes() {
        return noteRepository.findAllNotes();
    }

    public Note findNoteById(Long noteId) {
        Optional<Note> noteById = noteRepository.findNoteById(noteId);
        if (noteById.isPresent()) {
            return noteById.get();
        } else {
            throw new DataNotFoundException("Note with id:" + noteById + " not found");
        }
    }

    public Note findNoteByText(String text) {
        Optional<Note> noteByText = noteRepository.findNoteByText(text);
        if (noteByText.isPresent()) {
            return noteByText.get();
        } else {
            throw new DataNotFoundException("Note with text:" + text + " not found");
        }
    }

    public void updateNoteById(Long noteId, Note newNote) {
        Optional<Note> noteById = noteRepository.findNoteById(noteId);
        if (noteById.isPresent()) {
            newNote.setNoteId(noteById.get().getNoteId());
            persist(newNote);
        } else {
            throw new DataNotFoundException("Note with id:" + noteById + " not found");
        }
    }

    public void deleteNoteById(Long noteId) {
        Optional<Note> noteById = noteRepository.findNoteById(noteId);
        if (noteById.isPresent()) {
            entityManager.remove(noteById.get());
        } else {
            throw new DataNotFoundException("Note with id:" + noteById + " not found");
        }
    }

    public void persist(Note note) {
        noteRepository.persist(note);
    }

}
