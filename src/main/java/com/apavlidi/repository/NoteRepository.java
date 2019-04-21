package com.apavlidi.repository;


import com.apavlidi.domain.Note;
import org.apache.deltaspike.data.api.EntityManagerDelegate;
import org.apache.deltaspike.data.api.EntityRepository;
import org.apache.deltaspike.data.api.Repository;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
@Repository
public abstract class NoteRepository implements EntityRepository<Note, Long>, EntityManagerDelegate<Note> {

    @Inject
    private EntityManager em;

    public List<Note> findAllNotes() {
        try {
            TypedQuery<Note> query = em.createQuery("select DISTINCT(t) from Note t",
                    Note.class);
            return query.getResultList();
        } catch (
                NoResultException nre) {
            return new ArrayList<>();
        }
    }

    public Optional<Note> findNoteById(Long noteId) {
        try {
            Note note = em.createQuery("select DISTINCT(t) from Note t where t.id=:noteId",
                    Note.class).setParameter("noteId", noteId).getSingleResult();
            return Optional.ofNullable(note);
        } catch (
                NoResultException nre) {
            return Optional.empty();
        }
    }

    public Optional<Note> findNoteByText(String text) {
        try {
            Note note = em.createQuery("select DISTINCT(t) from Note t where t.text=:text",
                    Note.class).setParameter("text", text).getSingleResult();
            return Optional.ofNullable(note);
        } catch (
                NoResultException nre) {
            return Optional.empty();
        }
    }
}
