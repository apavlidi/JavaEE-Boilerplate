package com.apavlidi.web;

import com.apavlidi.domain.Note;
import com.apavlidi.service.NoteService;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@RequestScoped
public class Controller {

    @Inject
    private NoteBean bean;

    @Inject
    private NoteService service;

    @PostConstruct
    public void initialize(){
        bean.setNotes(service.findAllNotes());
    }

    public void saveNote(){
        Note note = new Note();
        note.setText("First note");
        bean.setSelectedNote(note);
        service.persist(bean.getSelectedNote());
    }

    public NoteBean getBean() {
        return bean;
    }

    public void setBean(NoteBean bean) {
        this.bean = bean;
    }
}
