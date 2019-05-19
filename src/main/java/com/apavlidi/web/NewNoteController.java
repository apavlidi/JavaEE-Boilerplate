package com.apavlidi.web;

import com.apavlidi.domain.Note;
import com.apavlidi.service.NoteService;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@ViewScoped
@Named(value = "newNoteController")
public class NewNoteController implements Serializable {

  @Inject
  private NoteBean bean;

  @Inject
  private NoteService service;

  @PostConstruct
  private void initialize() {
    System.out.println(bean);
  }

  public void saveNote() {
    Note note = new Note();
    note.setText("static text");
    service.persist(note);
  }

  public NoteBean getBean() {
    return bean;
  }

  public void setBean(NoteBean bean) {
    this.bean = bean;
  }

}
