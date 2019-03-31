package com.apavlidi.web;

import com.apavlidi.domain.Note;
import com.apavlidi.service.NoteService;

import javax.annotation.PostConstruct;
import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

@RequestScoped
@Named(value = "controller")
public class Controller implements Serializable {

    @Inject
    private NoteBean bean;

    @Inject
    private NoteService service;

    @PostConstruct
    public void initialize() {
        bean.createNew();
    }

    public void saveNote() {
        System.out.println(bean.getText());
        Note note = new Note();
        note.setText(bean.getSelectedNote().getText());
        service.persist(note);
    }

    public NoteBean getBean() {
        return bean;
    }

}
