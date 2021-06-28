package com.leverx.dao.impl;

import com.leverx.dao.UserDao;
import com.leverx.entity.Authority;
import com.leverx.entity.User;
import com.leverx.service.impl.MailServiceImpl;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private MailDaoImpl mailSender;


    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.getCurrentSession();
        return session.createQuery("from User", User.class).getResultList();
    }


    @Override
    public UserDetails loadUserByUsername(String username) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User where username =: username");
        query.setParameter("username", username);

        List<User> userFromDB = query.getResultList();
        if (userFromDB.size() != 0) {
            throw new UsernameNotFoundException("User not found");
        }

        return userFromDB.get(0);
    }


    @Override
    public void updateUserByAdmin(User user) {
        Session session = sessionFactory.getCurrentSession();
        Authority authority = session.get(Authority.class, 1);
        user.setAuthority(authority);
        session.update(user);

    }


    @Override
    public boolean registrationUser(User user, String username) {
        Session session = sessionFactory.getCurrentSession();

        Query<User> query = session.createQuery("from User where username =: username");
        query.setParameter("username", username);
        List<User> userFromDB = query.getResultList();

        if (userFromDB.size() != 0) {
            return false;
        }

        Authority authority = session.get(Authority.class, 1);
        user.setAuthority(authority);
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        user.setCreatedAt(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        user.setEnabled(1);
        user.setActivationCode(UUID.randomUUID().toString());
        session.save(user);

        if (!(user.getEmail().isEmpty())) {
            String message = String.format("Hello, %s!\n" +
                            "Welcome to Blog. Please, visit next link: http://localhost:8080/levexBlog/activate/%s",
                    user.getUsername(),
                    user.getActivationCode());
            mailSender.sendMessage(user.getEmail(), "Activation code", message);
        }
        return true;
    }

    @Override
    public User getUserById(int id) {
        Session session = sessionFactory.getCurrentSession();
        return session.get(User.class, id);
    }

    @Override
    public boolean deleteUser(int id) {
        Session session = sessionFactory.getCurrentSession();
        if (session.get(User.class, id) != null) {
            Query<User> query = session.createQuery("delete from User "
                    + "where id =:userId");
            query.setParameter("userId", id);
            query.executeUpdate();
            return true;
        }
        return false;

    }

    @Override
    public User findUserByActivationCode(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User where activationCode =: code");
        query.setParameter("code", code);
        List<User> userFromDB = query.getResultList();


        return userFromDB.get(0);
    }

    @Override
    public boolean activateUser(String code) {
        Session session = sessionFactory.getCurrentSession();
        Query<User> query = session.createQuery("from User where activationCode =: code");
        query.setParameter("code", code);
        List<User> user = query.getResultList();
        if (user.get(0) == null) {
            return false;
        }
        user.get(0).setActivationCode(null);
        session.update(user.get(0));
        return true;
    }
}
