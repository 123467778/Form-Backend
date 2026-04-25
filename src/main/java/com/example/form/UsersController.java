package com.example.form;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000")
public class UsersController {
    @Autowired
    private UsersServices usersServices;

    @GetMapping("/getAllUsers")
    public List<Users> getAllUsers(){
        return usersServices.getAllUsers();
    }

    @PostMapping("/create")
    public ResponseEntity<?> createUser(@RequestBody Users users){
//        if (users.getName() == null || users.getName().trim().isEmpty()) {
//            return ResponseEntity.badRequest().body("Username should not be empty");
//        }
//
//        if (users.getEmail() == null || users.getEmail().trim().isEmpty()) {
//            return ResponseEntity.badRequest().body("Email should not be empty");
//        }
//
//        if (!users.getEmail().matches("^[^\\s@]+@[^\\s@]+\\.[^\\s@]+$")) {
//            return ResponseEntity.badRequest().body("Invalid email format");
//        }
//
//        Users savedUser = usersServices.createUsers(users);
        return usersServices.createUsers(users);

    }

    @PutMapping("/update/{id}")
    public Users updateUser(@PathVariable int id,@RequestBody Users newusers){
        return usersServices.updateUser(id,newusers);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable int id) {
        usersServices.deleteUser(id);
    }
}
