package org.groundres.services;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import org.groundres.model.User;

@Stateless
@LocalBean
public class UserBean {

    @PersistenceContext
    private EntityManager em;

    public User addUser(User user) {
        em.persist(user);
        return user;
    }
    
    public User findUserByUsernameAndPassword(String username, String password) {
        TypedQuery<User> query = em.createNamedQuery("findUserByUsernameAndPassword", User.class);
        query.setParameter("username", username);
        query.setParameter("password", password);
        
        User user = null;
        try {
            user = query.getSingleResult();
        } catch (NoResultException nre) {}
        
        return user;
    }
}