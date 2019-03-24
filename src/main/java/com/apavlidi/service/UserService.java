package com.apavlidi.service;

import com.apavlidi.domain.User;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@Stateless
@LocalBean
public class UserService {

    @Inject
    private EntityManager em;

    public String hiService(User user) {
        em.persist(user);
        return "hi from service";
    }

}
