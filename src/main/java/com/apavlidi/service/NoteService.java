package com.apavlidi.service;

import com.apavlidi.domain.Note;
import com.apavlidi.repository.NoteRepository;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class NoteService {

    @Inject
    private NoteRepository noteRepository;

    public List<Note> findAllNotes(){
        return noteRepository.findAllNotes();
    }

    public void persist(Note note){
        noteRepository.persist(note);
    }



}
