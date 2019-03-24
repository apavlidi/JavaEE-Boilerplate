package com.apavlidi.web;

import com.apavlidi.domain.User;
import com.apavlidi.service.UserService;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.util.ArrayList;

@Named
@RequestScoped
public class Controller {

    ArrayList<String> list = new ArrayList<String>() {{
        add("A");
        add("B");
        add("C");
    }};

    @Inject
    private UserService service;

    public String hi() {
        User newUser = new User();
        newUser.setFirstName("BOOM");
        return service.hiService(newUser);
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }
}
