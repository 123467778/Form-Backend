package com.example.form;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsersServices {
    @Autowired
    private UsersRepository usersRepository;

    public List<Users> getAllUsers() {
        return usersRepository.findAll();

    }

    public ResponseEntity<?> createUsers(Users users) {

        if (users.getName() == null || users.getName().trim().isEmpty()) {
            throw new RuntimeException("Username should not be empty");
        }

        if (users.getEmail() == null || users.getEmail().trim().isEmpty()) {
            throw new RuntimeException("Email should not be empty");
        }

        if (!users.getEmail().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
            throw new RuntimeException("Invalid email format");
        }
        Users savedUser = usersRepository.save(users);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//        return usersRepository.save(users);

    }

    public Users updateUser(int id, Users newusers) {
        Users user = usersRepository.findById(id).orElseThrow();
        user.setName(newusers.getName());
        user.setEmail(newusers.getEmail());
        user.setPassword(newusers.getPassword());
        return usersRepository.save(user);

    }

    public void deleteUser(int id) {
        usersRepository.deleteById(id);
    }
}
