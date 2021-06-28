package com.leverx.service.impl;

import com.leverx.dao.UserDao;
import com.leverx.entity.User;
import com.leverx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    @Transactional
    public void updateUserByAdmin(User user) {
        userDao.updateUserByAdmin(user);
    }

    @Override
    @Transactional
    public boolean registrationUser(User user, String username) {
        userDao.registrationUser(user, username);
        return true;
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        List<User> userList = userDao.getAllUsers();
        return userList;
    }

    @Override
    @Transactional
    public User getUserById(int id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public boolean deleteUser(int id) {
        return userDao.deleteUser(id);
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userDao.loadUserByUsername(username);
    }

    @Override
    @Transactional
    public User findUserByActivationCode(String code) {
        return userDao.findUserByActivationCode(code);
    }

    @Override
    @Transactional
    public boolean activateUser(String code) {
        return userDao.activateUser(code);
    }
}
