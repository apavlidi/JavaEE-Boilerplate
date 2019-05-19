package com.apavlidi.web;

import com.apavlidi.domain.Note;
import com.apavlidi.service.NoteService;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@RequestScoped
@Named(value = "myNotesController")
public class MyNotesController implements Serializable {

  @Inject
  private NoteService service;

  private List<Note> noteList;

  @PostConstruct
  public void initialize() {
    noteList = service.findAllNotes();
  }

  public List<Note> getNoteList() {
    return noteList;
  }
}
