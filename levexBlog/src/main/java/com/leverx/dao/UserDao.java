package com.leverx.dao;

import com.leverx.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface UserDao extends UserDetailsService {

    public List<User> getAllUsers();

    public boolean registrationUser(User user);

    public void updateUserByAdmin(User user);

    public User getUserById(int id);

    public boolean deleteUser(int id);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    public User findUserByActivationCode(String code);

    public boolean activateUser(String code);

    public boolean findUserByUsername(String username);
}

