package com.apavlidi.api.routes;

import com.apavlidi.api.beans.NoteFilterBean;
import com.apavlidi.domain.Note;
import com.apavlidi.service.NoteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/notes")
public class NotificationsResource {

    @Inject
    private NoteService noteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotes(@QueryParam("text") String text,
                             @QueryParam("limit") int limit) {
        if (!text.isEmpty()) {
            Note noteByText = noteService.findNoteByText(text);
            return Response.ok().entity(noteByText).build();
        } else {
            List<Note> allNotes = noteService.findAllNotes();
            return Response.ok().entity(allNotes.subList(0, limit)).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotesWithFilterBean(@BeanParam NoteFilterBean filterBean) {
        if (!filterBean.getText().isEmpty()) {
            Note noteByText = noteService.findNoteByText(filterBean.getText());
            return Response.ok().entity(noteByText).build();
        } else {
            List<Note> allNotes = noteService.findAllNotes();
            return Response.ok().entity(allNotes.subList(0, filterBean.getLimit())).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNote(@PathParam("id") Long noteId) {
        Note note = noteService.findNoteById(noteId);
        return Response.ok().entity(note).build();
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteNote(@PathParam("id") Long noteId) {
        noteService.deleteNoteById(noteId);
        return Response.ok().build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postNote(Note note) {
        noteService.persist(note);
        return Response.status(201).entity(note).build();
    }
}