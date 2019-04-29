package com.apavlidi.api.routes;

import com.apavlidi.api.beans.NoteFilterBean;
import com.apavlidi.domain.Note;
import com.apavlidi.service.NoteService;
import java.util.List;
import javax.inject.Inject;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/notes")
public class NotesResource {

  @Inject
  private NoteService noteService;

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNotes(@QueryParam("text") String text,
      @QueryParam("limit") int limit) {
    if (text != null) {
      Note noteByText = noteService.findNoteByText(text);
      return Response.ok().entity(noteByText).build();
    } else {
      List<Note> allNotes = noteService.findAllNotes();
      if (limit != 0) {
        return Response.ok().entity(allNotes.subList(0, limit)).build();
      } else {
        return Response.ok().entity(allNotes).build();
      }
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

  @GET
  @Path("paramConverter/{id}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getNote(@PathParam("id") Note note) {
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