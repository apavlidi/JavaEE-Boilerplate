package com.apavlidi.repository;


import com.apavlidi.domain.User;
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
public abstract class UserRepository implements EntityRepository<User, Long>, EntityManagerDelegate<User> {

    @Inject
    private EntityManager em;

    public List<User> findAllUsers() {
        try {
            TypedQuery<User> query = em.createQuery("select DISTINCT(t) from User t",
                    User.class);
            return query.getResultList();
        } catch (
                NoResultException nre) {
            return new ArrayList<>();
        }
    }

    public Optional<User> findUserById(Long userId) {
        try {
            User user = em.createQuery("select DISTINCT(t) from User t where t.id=:userId",
                    User.class).setParameter("userId", userId).getSingleResult();
            return Optional.ofNullable(user);
        } catch (
                NoResultException nre) {
            return Optional.empty();
        }
    }

}
