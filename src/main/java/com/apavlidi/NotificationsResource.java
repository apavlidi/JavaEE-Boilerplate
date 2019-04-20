package com.apavlidi;

import com.apavlidi.domain.Note;
import com.apavlidi.service.NoteService;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.singletonList;

@Path("/notifications")
public class NotificationsResource {

    @Inject
    private NoteService noteService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotifications(@QueryParam("text") String text) {
        List<Note> allNotes;
        if (!text.isEmpty()) {
            allNotes = singletonList(noteService.findAllNotes().get(0));
        } else {
            allNotes = noteService.findAllNotes();
        }
        return Response.ok().entity(allNotes).build();
    }

    @GET
    @Path("/get/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getNotification(@PathParam("id") int id) {
        List<Note> allNotes = noteService.findAllNotes();
        return Response.ok().entity(allNotes.get(0)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response postNotification(Note note) {
        noteService.persist(note);
        return Response.status(201).entity(note).build();
    }
}