package com.apavlidi.api;

import com.apavlidi.domain.Note;
import com.apavlidi.service.NoteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.singletonList;

@Path("/notes")
public class NotificationsResource {

    @Inject
    private NoteService noteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotes(@QueryParam("text") String text) {
        List<Note> allNotes;
        if (!text.isEmpty()) {
            allNotes = singletonList(noteService.findAllNotes().get(0));
        } else {
            allNotes = noteService.findAllNotes();
        }
        return Response.ok().entity(allNotes).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNote(@PathParam("id") Long noteId) {
        Optional<Note> note = noteService.findNoteById(noteId);
        if (note.isPresent()) {
            return Response.ok().entity(note).build();
        } else {
            return Response.noContent().build();
        }
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