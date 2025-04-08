package com.example.journal.service;


import com.example.journal.entity.User;
import com.example.journal.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Arrays;


@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


//    //Used to return all the data into the database
//    public List<User> getAll(){
//
//        return userRepository.findAll();
//    }



    //Used to Add the data into the database
//    public User saveNewUser(User user) {
//        user.setPassword(passwordEncoder.encode(user.getPassword()));
//        user.setRoles(Arrays.asList("USER"));
//        return userRepository.save(user);
//    }


    //Used to Add the data into the database
    public User saveEntry(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(Arrays.asList("USER"));
        return userRepository.save(user);
    }


    //used to find the data based on the id
    public User findByUserName(String username){
        return userRepository.findByUserName(username);
    }



    //used to delete the data based on the id
    public void deleteByUserName(String userName){
        if(!userRepository.existsByUserName(userName)){
            throw new RuntimeException("User Not found with userName" + userName);
        }
        userRepository.deleteByUserName(userName);
    }


}
