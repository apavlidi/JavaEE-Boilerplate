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
}
