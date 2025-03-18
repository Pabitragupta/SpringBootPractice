package com.example.journal.controller;


import com.example.journal.entity.User;
import com.example.journal.service.JournalEntryService;
import com.example.journal.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
;import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;


    //To Get all the database data
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAll();
    }


    //To add the data into the database
    @PostMapping
    public User createUser(@RequestBody User user){
        return userService.saveEntry(user);
    }



    //To Update the data
    @PutMapping("/{userName}")
    public ResponseEntity<?> updateUser(@RequestBody User newUser, @PathVariable String userName){

        User oldUser = userService.findByUserName(userName);

        if(oldUser != null){
//            oldUser.setUserName(newUser.getUserName() != null && !newUser.getUserName().equals("") ? newUser.getUserName() : oldUser.getUserName());
//            oldUser.setPassword(newUser.getPassword() != null && !newUser.getPassword().equals("") ? newUser.getPassword() : oldUser.getPassword());

            oldUser.setUserName(newUser.getUserName());
            oldUser.setPassword(newUser.getPassword());
            userService.saveEntry(oldUser);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}

